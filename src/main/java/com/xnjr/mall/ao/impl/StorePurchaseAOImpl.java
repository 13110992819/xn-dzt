package com.xnjr.mall.ao.impl;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.xnjr.mall.ao.IStorePurchaseAO;
import com.xnjr.mall.bo.IAccountBO;
import com.xnjr.mall.bo.ISYSConfigBO;
import com.xnjr.mall.bo.IStoreBO;
import com.xnjr.mall.bo.IStorePurchaseBO;
import com.xnjr.mall.bo.IStoreTicketBO;
import com.xnjr.mall.bo.IUserBO;
import com.xnjr.mall.bo.IUserTicketBO;
import com.xnjr.mall.bo.base.Paginable;
import com.xnjr.mall.common.SysConstants;
import com.xnjr.mall.core.CalculationUtil;
import com.xnjr.mall.domain.Store;
import com.xnjr.mall.domain.StorePurchase;
import com.xnjr.mall.domain.StoreTicket;
import com.xnjr.mall.domain.User;
import com.xnjr.mall.domain.UserTicket;
import com.xnjr.mall.dto.res.BooleanRes;
import com.xnjr.mall.dto.res.XN802180Res;
import com.xnjr.mall.dto.res.XN802503Res;
import com.xnjr.mall.enums.EBizType;
import com.xnjr.mall.enums.ECategoryType;
import com.xnjr.mall.enums.ECurrency;
import com.xnjr.mall.enums.EPayType;
import com.xnjr.mall.enums.EStorePurchaseStatus;
import com.xnjr.mall.enums.EStoreStatus;
import com.xnjr.mall.enums.EStoreTicketType;
import com.xnjr.mall.enums.ESysUser;
import com.xnjr.mall.enums.EUserKind;
import com.xnjr.mall.enums.EUserTicketStatus;
import com.xnjr.mall.exception.BizException;

@Service
public class StorePurchaseAOImpl implements IStorePurchaseAO {

    static Logger logger = Logger.getLogger(StorePurchaseAOImpl.class);

    @Autowired
    private IStorePurchaseBO storePurchaseBO;

    @Autowired
    private IStoreBO storeBO;

    @Autowired
    private IStoreTicketBO storeTicketBO;

    @Autowired
    private IUserTicketBO userTicketBO;

    @Autowired
    private IAccountBO accountBO;

    @Autowired
    private ISYSConfigBO sysConfigBO;

    @Autowired
    private IUserBO userBO;

    @Override
    public Object storePurchaseCG(String userId, String storeCode, Long amount,
            String payType, String ticketCode) {
        Store store = storeBO.getStore(storeCode);
        if (!EStoreStatus.ON_OPEN.getCode().equals(store.getStatus())) {
            throw new BizException("xn0000", "店铺不处于可消费状态");
        }
        User user = userBO.getRemoteUser(userId);
        if (EPayType.INTEGRAL.getCode().equals(payType)) {
            return storePurchaseCGcgb(user, store, amount);
        } else if (EPayType.WEIXIN.getCode().equals(payType)) {
            return storePurchaseDirectWX(user, store, amount);
        } else {
            throw new BizException("xn0000", "支付方式不存在");
        }
    }

    private Object storePurchaseDirect(User user, Store store, Long amount,
            String payType) {
        if (EPayType.ZH_YE.getCode().equals(payType)) {
            return storePurchaseDirectYE(user, store, amount);
        } else if (EPayType.WEIXIN.getCode().equals(payType)) {
            return storePurchaseDirectWX(user, store, amount);
        } else {
            throw new BizException("xn0000", "支付方式不存在");
        }
    }

    @Override
    public Object storePurchaseZH(String userId, String storeCode, Long amount,
            String payType, String ticketCode) {
        Store store = storeBO.getStore(storeCode);
        if (!EStoreStatus.ON_OPEN.getCode().equals(store.getStatus())) {
            throw new BizException("xn0000", "店铺不处于可消费状态");
        }
        User user = userBO.getRemoteUser(userId);
        if (StringUtils.isNotBlank(ticketCode)) {// 使用折扣券
            // 扣除折扣券优惠
            UserTicket userTicket = userTicketBO.getUserTicket(ticketCode);
            return storePurchaseTicket(user, store, amount, payType, userTicket);
        } else {// 不使用折扣券
            return storePurchaseDirect(user, store, amount, payType);
        }

    }

    // 菜狗币支付
    @Transactional
    private String storePurchaseCGcgb(User user, Store store, Long amount) {
        // 计算返点人民币
        Long fdAmount = Double.valueOf(amount * store.getRate3()).longValue();
        // 落地本地系统消费记录
        String code = storePurchaseBO.storePurchaseCGcgb(user, store, amount,
            fdAmount);
        // 资金划转开始--------------
        // 菜狗币从消费者回收至平台，
        String systemUser = null;
        accountBO.doTransferAmountRemote(user.getUserId(), systemUser,
            ECurrency.CGB, amount, EBizType.CG_O2O_CGB, "O2O消费使用菜狗币",
            "O2O消费回收菜狗币");
        // 商家从平台处拿到返点（人民币）
        accountBO.doTransferAmountRemote(systemUser, store.getOwner(),
            ECurrency.CNY, fdAmount, EBizType.CG_O2O_CGGFD, "O2O消费支付返点人民币",
            "O2O消费收到返点人民币");
        // 资金划转结束--------------
        return code;
    }

    private Object storePurchaseDirectYE(User user, Store store, Long amount) {
        // 优惠金额
        Long yhAmount = amount;
        String remark = store.getName() + " 消费" + CalculationUtil.divi(amount)
                + "元";
        // 余额支付业务规则：优先扣贡献奖励，其次扣分润
        Long gxjlAmount = 0L;
        Long frAmount = 0L;
        Double gxjl2cnyRate = Double.valueOf(sysConfigBO.getConfigValue(
            systemCode, ECategoryType.QBHL.getCode(), null,
            SysConstants.GXJL2CNY));
        Double fr2cnyRate = Double
            .valueOf(sysConfigBO.getConfigValue(systemCode,
                ECategoryType.QBHL.getCode(), null, SysConstants.FR2CNY));

        // 查询用户贡献奖励账户
        XN802503Res gxjlAccount = accountBO.getAccountByUserId(
            store.getSystemCode(), userId, ECurrency.GXJL.getCode());
        // 查询用户分润账户
        XN802503Res frAccount = accountBO.getAccountByUserId(
            store.getSystemCode(), userId, ECurrency.FRB.getCode());

        // 1、贡献奖励+分润<yhAmount 余额不足
        if (gxjlAccount.getAmount() / gxjl2cnyRate + frAccount.getAmount()
                / fr2cnyRate < yhAmount) {
            throw new BizException("xn0000", "余额不足");
        }
        // 2、贡献奖励=0 直接扣分润
        if (gxjlAccount.getAmount() <= 0L) {
            frAmount = Double.valueOf(yhAmount * fr2cnyRate).longValue();
        }
        // 3、0<贡献奖励<yhAmount 先扣贡献奖励，再扣分润
        if (gxjlAccount.getAmount() > 0L
                && gxjlAccount.getAmount() / gxjl2cnyRate < yhAmount) {
            gxjlAmount = gxjlAccount.getAmount();
            frAmount = Double.valueOf(
                (yhAmount - Double.valueOf(gxjlAmount / gxjl2cnyRate)
                    .longValue()) * fr2cnyRate).longValue();
        }
        // 4、贡献奖励>=yhAmount 直接扣贡献奖励
        if (gxjlAccount.getAmount() / gxjl2cnyRate >= yhAmount) {
            gxjlAmount = Double.valueOf(yhAmount * gxjl2cnyRate).longValue();
        }

        // 落地本地系统消费记录
        StorePurchase data = new StorePurchase();
        data.setUserId(userId);
        data.setStoreCode(storeCode);
        data.setPayType(EPayType.ZH_YE.getCode());
        data.setPurchaseAmount(amount);
        data.setAmount1(yhAmount);
        data.setAmount2(gxjlAmount);
        data.setAmount3(frAmount);
        data.setStatus(EStorePurchaseStatus.PAYED.getCode());
        data.setTicketCode(ticketCode);
        data.setSystemCode(systemCode);
        data.setRemark(remark);
        storePurchaseBO.saveStorePurchase(data);

        // 查询商家分润账户，加上对应分润
        XN802503Res sjAccount = accountBO.getAccountByUserId(systemCode,
            store.getOwner(), ECurrency.FRB.getCode());
        if (gxjlAmount > 0L) {
            accountBO.doTransferAmountOnRate(systemCode,
                gxjlAccount.getAccountNumber(), sjAccount.getAccountNumber(),
                gxjlAmount, 1 / gxjl2cnyRate * fr2cnyRate,
                EBizType.AJ_DPXF.getCode(), "店铺" + store.getName() + "消费买单");
        }
        if (frAmount > 0L) {
            accountBO.doTransferAmountOnRate(systemCode,
                frAccount.getAccountNumber(), sjAccount.getAccountNumber(),
                frAmount, 1 / fr2cnyRate * fr2cnyRate,
                EBizType.AJ_DPXF.getCode(), "店铺" + store.getName() + "消费买单");
        }
        // 优惠券状态修改
        if (StringUtils.isNotBlank(ticketCode)) {
            userTicketBO.refreshUserTicketStatus(ticketCode,
                EUserTicketStatus.USED.getCode());
        }
        distributeAmount(data);
        result = new BooleanRes(true);
        return null;
    }

    private Object storePurchaseDirectWX(User user, Store store, Long amount) {
        Object result = null;
        String systemCode = store.getSystemCode();
        // 优惠金额
        String remark = store.getName() + " 消费" + CalculationUtil.divi(amount)
                + "元";
        // 获取微信APP支付信息
        String bizNote = store.getName() + "——消费买单";
        String body = "正汇钱包—优店";
        XN802180Res res = accountBO.doWeiXinPay(systemCode, userId,
            EBizType.AJ_DPXF, bizNote, body, yhAmount, ip);
        // 落地本地系统消费记录，状态为未支付
        StorePurchase data = new StorePurchase();
        data.setUserId(userId);
        data.setStoreCode(storeCode);
        data.setPayType(EPayType.WEIXIN.getCode());
        data.setPurchaseAmount(amount);
        data.setAmount1(yhAmount);
        data.setStatus(EStorePurchaseStatus.TO_PAY.getCode());
        data.setSystemCode(systemCode);
        data.setRemark(remark);
        data.setJourCode(res.getJourCode());
        storePurchaseBO.saveStorePurchase(data);
        result = res;
        return null;
    }

    private Object storePurchaseTicket(User user, Store store, Long amount,
            String payType, UserTicket userTicket) {
        if (storeTicket.getValidateStart().after(new Date())) {
            throw new BizException("xn0000", "该折扣券还未生效，请选择其他折扣券");
        }
        if (!EUserTicketStatus.UNUSED.getCode().equals(userTicket.getStatus())) {
            throw new BizException("xn0000", "该折扣券不可用");
        }
        if (EStoreTicketType.MANJIAN.getCode().equals(
            userTicket.getTicketType())) {
            if (amount < userTicket.getTicketKey1()) {
                throw new BizException("xn0000", "消费金额还未达到折扣券满减金额，无法使用");
            }
            // 扣减消费金额
            yhAmount = amount - userTicket.getTicketKey2();
        }
        remark = remark + ",优惠后金额" + CalculationUtil.divi(yhAmount)
                + "元，使用折扣券:[" + storeTicket.getName() + "]";
        if (EStoreTicketType.MANJIAN.getCode().equals(storeTicket.getType())) {
            remark = remark + ",满"
                    + CalculationUtil.divi(storeTicket.getKey1()) + "减"
                    + CalculationUtil.divi(storeTicket.getKey2());
        }
        return null;
    }

    @Override
    public Paginable<StorePurchase> queryStorePurchasePage(int start,
            int limit, StorePurchase condition) {
        Paginable<StorePurchase> page = storePurchaseBO.getPaginable(start,
            limit, condition);
        List<StorePurchase> list = page.getList();
        for (StorePurchase storePurchase : list) {
            Store store = storeBO.getStore(storePurchase.getStoreCode());
            storePurchase.setStore(store);
            if (StringUtils.isNotBlank(storePurchase.getTicketCode())) {
                UserTicket userTicket = userTicketBO
                    .getUserTicket(storePurchase.getTicketCode());
                StoreTicket storeTicket = storeTicketBO
                    .getStoreTicket(userTicket.getTicketCode());
                storePurchase.setStoreTicket(storeTicket);
            }
        }
        return page;
    }

    @Override
    @Transactional
    public void paySuccess(String jourCode) {
        int count = 0;
        StorePurchase condition = new StorePurchase();
        condition.setJourCode(jourCode);
        List<StorePurchase> result = storePurchaseBO
            .queryStorePurchaseList(condition);
        if (CollectionUtils.isEmpty(result)) {
            throw new BizException("XN000000", "找不到对应的消费记录");
        }
        StorePurchase storePurchase = result.get(0);
        storePurchaseBO.refreshStatus(storePurchase.getCode(),
            EStorePurchaseStatus.PAYED.getCode());
        // 优惠券状态修改
        String ticketCode = storePurchase.getTicketCode();
        if (StringUtils.isNotBlank(ticketCode)) {
            userTicketBO.refreshUserTicketStatus(ticketCode,
                EUserTicketStatus.USED.getCode());
        }
        distributeAmount(storePurchase);
    }

    // 消费买单完 各方分钱
    private void distributeAmount(StorePurchase storePurchase) {
        String systemCode = storePurchase.getSystemCode();
        String userId = storePurchase.getUserId();
        Store store = storeBO.getStore(storePurchase.getStoreCode());
        Long yhAmount = storePurchase.getAmount1();
        Double fcRate = store.getRate2();
        if (StringUtils.isNotBlank(storePurchase.getTicketCode())) {
            fcRate = store.getRate1();
        }
        // 分销规则
        Map<String, String> rateMap = sysConfigBO.getConfigsMap(systemCode,
            null);
        Double o2oCUserRate = Double.valueOf(rateMap
            .get(SysConstants.O2O_CUSER));
        Double o2oBUserRate = Double.valueOf(rateMap
            .get(SysConstants.O2O_BUSER)) * fcRate;
        Double o2oAUserRate = Double.valueOf(rateMap
            .get(SysConstants.O2O_AUSER)) * fcRate;
        Double o2oYwyRate = Double.valueOf(rateMap.get(SysConstants.O2O_YWY))
                * fcRate;
        Double o2oAreaRate = Double.valueOf(rateMap.get(SysConstants.O2O_AREA))
                * fcRate;
        Double o2oCityRate = Double.valueOf(rateMap.get(SysConstants.O2O_CITY))
                * fcRate;
        Double o2oProvinceRate = Double.valueOf(rateMap
            .get(SysConstants.O2O_PROVINCE)) * fcRate;
        // 1、买单用户C得到消费额35%的钱包币 —— 平台发放
        accountBO.doTransferAmountRemote(ESysUser.SYS_USER_ZHPAY.getCode(),
            userId, ECurrency.QBB, Double.valueOf(yhAmount * o2oCUserRate)
                .longValue(), EBizType.AJ_DPXF, "优店消费买单，赠送钱包币", "优店消费买单，赠送钱包币");
        // 2、C的推荐人B可得到分润X1 B的推荐人A可得到分润X2 —— 消费额里面扣除
        User user = userBO.getRemoteUser(userId);
        User refereeUser1 = null;
        if (user != null && StringUtils.isNotBlank(user.getUserReferee())) {
            refereeUser1 = userBO.getRemoteUser(user.getUserReferee());
        }
        if (refereeUser1 == null) {
            logger.info("用户" + userId + "无一级推荐人,无需推荐人分成");
        } else {
            // 一级推荐人得到分润
            accountBO.doTransferAmountRemote(store.getOwner(),
                refereeUser1.getUserId(), ECurrency.FRB,
                Double.valueOf(yhAmount * o2oBUserRate).longValue(),
                EBizType.AJ_DPXF, "优店消费买单，一级推荐人分成", "优店消费买单，一级推荐人分成");
            User refereeUser2 = null;
            if (StringUtils.isNotBlank(refereeUser1.getUserReferee())) {
                refereeUser2 = userBO.getRemoteUser(refereeUser1
                    .getUserReferee());
            }
            if (refereeUser2 == null) {
                logger.info("用户" + userId + "无二级推荐人,无需推荐人分成");
            } else {
                // 二级推荐人得到分润
                accountBO.doTransferAmountRemote(store.getOwner(),
                    refereeUser2.getUserId(), ECurrency.FRB,
                    Double.valueOf(yhAmount * o2oAUserRate).longValue(),
                    EBizType.AJ_DPXF, "优店消费买单，二级推荐人分成", "优店消费买单，二级推荐人分成");
            }
        }
        // 3、业务员可得到分润X3 —— 消费额里面扣除
        if (StringUtils.isNotBlank(store.getUserReferee())) {
            accountBO.doTransferAmountRemote(store.getOwner(),
                store.getUserReferee(), ECurrency.FRB,
                Double.valueOf(yhAmount * o2oYwyRate).longValue(),
                EBizType.AJ_DPXF, "优店消费买单，业务员分成", "优店消费买单，业务员分成");
        } else {
            logger.info("店铺" + store.getName() + "无业务员，无需业务员分成");
        }
        // 4、店铺所在县、市、省得到分瑞X4、X5、X6 —— 消费额里面扣除
        User areaUser = null;
        User cityUser = null;
        User provinceUser = null;
        if (StringUtils.isNotBlank(store.getProvince())
                && StringUtils.isNotBlank(store.getCity())
                && StringUtils.isNotBlank(store.getArea())) {
            // 县合伙人
            areaUser = userBO.getPartner(store.getProvince(), store.getCity(),
                store.getArea(), EUserKind.Partner);
            // 市合伙人
            cityUser = userBO.getPartner(store.getProvince(), store.getCity(),
                null, EUserKind.Partner);
            // 省合伙人
            provinceUser = userBO.getPartner(store.getProvince(), null, null,
                EUserKind.Partner);
        } else if (StringUtils.isNotBlank(store.getProvince())
                && StringUtils.isNotBlank(store.getCity())) {
            // 市合伙人
            cityUser = userBO.getPartner(store.getProvince(), store.getCity(),
                null, EUserKind.Partner);
            // 省合伙人
            provinceUser = userBO.getPartner(store.getProvince(), null, null,
                EUserKind.Partner);
        } else if (StringUtils.isNotBlank(store.getProvince())) {
            // 省合伙人
            provinceUser = userBO.getPartner(store.getProvince(), null, null,
                EUserKind.Partner);
        }

        if (areaUser != null) {
            accountBO.doTransferAmountRemote(store.getOwner(),
                areaUser.getUserId(), ECurrency.FRB,
                Double.valueOf(yhAmount * o2oAreaRate).longValue(),
                EBizType.AJ_DPXF, "优店消费买单,县合伙人分成", "优店消费买单,县合伙人分成");
        }
        if (cityUser != null) {
            accountBO.doTransferAmountRemote(store.getOwner(),
                cityUser.getUserId(), ECurrency.FRB,
                Double.valueOf(yhAmount * o2oCityRate).longValue(),
                EBizType.AJ_DPXF, "优店消费买单,市合伙人分成", "优店消费买单,市合伙人分成");
        }
        if (provinceUser != null) {
            accountBO.doTransferAmountRemote(store.getOwner(),
                provinceUser.getUserId(), ECurrency.FRB,
                Double.valueOf(yhAmount * o2oProvinceRate).longValue(),
                EBizType.AJ_DPXF, "优店消费买单,省合伙人分成", "优店消费买单,省合伙人分成");
        }

    }
}
