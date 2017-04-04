package com.xnjr.mall.ao.impl;

import java.util.Date;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.xnjr.mall.ao.IStorePurchaseAO;
import com.xnjr.mall.bo.IAccountBO;
import com.xnjr.mall.bo.ICaigopoolBO;
import com.xnjr.mall.bo.ISYSConfigBO;
import com.xnjr.mall.bo.IStockBO;
import com.xnjr.mall.bo.IStoreBO;
import com.xnjr.mall.bo.IStorePurchaseBO;
import com.xnjr.mall.bo.IStoreTicketBO;
import com.xnjr.mall.bo.IUserBO;
import com.xnjr.mall.bo.IUserTicketBO;
import com.xnjr.mall.bo.base.Paginable;
import com.xnjr.mall.domain.Account;
import com.xnjr.mall.domain.Store;
import com.xnjr.mall.domain.StorePurchase;
import com.xnjr.mall.domain.StoreTicket;
import com.xnjr.mall.domain.User;
import com.xnjr.mall.domain.UserTicket;
import com.xnjr.mall.dto.res.XN808248Res;
import com.xnjr.mall.enums.EBizType;
import com.xnjr.mall.enums.ECurrency;
import com.xnjr.mall.enums.EPayType;
import com.xnjr.mall.enums.EStoreLevel;
import com.xnjr.mall.enums.EStorePurchaseStatus;
import com.xnjr.mall.enums.EStoreStatus;
import com.xnjr.mall.enums.EStoreTicketType;
import com.xnjr.mall.enums.ESysUser;
import com.xnjr.mall.enums.EUserKind;
import com.xnjr.mall.enums.EUserTicketStatus;
import com.xnjr.mall.enums.EZhPool;
import com.xnjr.mall.exception.BizException;

@Service
public class StorePurchaseAOImpl implements IStorePurchaseAO {
    static Logger logger = Logger.getLogger(StorePurchaseAOImpl.class);

    @Autowired
    private IStorePurchaseBO storePurchaseBO;

    @Autowired
    private IStoreBO storeBO;

    @Autowired
    private IStockBO stockBO;

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

    @Autowired
    private ICaigopoolBO caigopoolBO;

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
            return storePurchaseCGWX(user, store, amount);
        } else {
            throw new BizException("xn0000", "支付方式不存在");
        }
    }

    private Object storePurchaseCGWX(User user, Store store, Long amount) {
        // 计算折扣，即积分扣钱金额
        Long discountAmount = Double.valueOf(amount * store.getRate2())
            .longValue();

        Double cgjf2cnyRate = accountBO.getExchangeRateRemote(ECurrency.CGJF);
        Long jf = Double.valueOf(discountAmount * cgjf2cnyRate).longValue();
        // 落地本地系统消费记录
        String payGroup = storePurchaseBO.storePurchaseCGWX(user, store,
            amount, jf);
        // 资金划转开始--------------
        // 积分从消费者回收至平台，
        String systemUser = ESysUser.SYS_USER_CAIGO.getCode();
        accountBO.doTransferAmountRemote(user.getUserId(), systemUser,
            ECurrency.JF, jf, EBizType.CG_O2O_CGJF, "O2O消费使用积分", "O2O消费回收积分");
        // RMB调用微信渠道至商家
        return accountBO.doWeiXinPayRemote(user.getUserId(), store.getOwner(),
            amount - discountAmount, EBizType.CG_O2O_RMB, "O2O消费微信支付",
            "O2O消费微信支付", payGroup);
        // 资金划转结束--------------
    }

    private Object storePurchaseZHWX(User user, Store store, Long amount) {
        // 落地本地系统消费记录
        String payGroup = storePurchaseBO
            .storePurchaseZHWX(user, store, amount);
        // 资金划转开始--------------
        // RMB调用微信渠道至商家
        return accountBO.doWeiXinPayRemote(user.getUserId(), store.getOwner(),
            amount, EBizType.CG_O2O_RMB, "O2O消费微信支付", "O2O消费微信支付", payGroup);
        // 资金划转结束--------------

    }

    private Object storePurchaseZHZFB(User user, Store store, Long amount) {
        // 落地本地系统消费记录
        String payGroup = storePurchaseBO.storePurchaseZHZFB(user, store,
            amount);
        // 资金划转开始--------------
        // RMB调用支付宝渠道至商家
        return accountBO.doAlipayRemote(user.getUserId(), store.getOwner(),
            amount, EBizType.ZH_O2O, "O2O消费支付宝支付", "O2O消费支付宝支付", payGroup);
        // 资金划转结束--------------

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
        String systemUser = ESysUser.SYS_USER_CAIGO.getCode();
        accountBO.doTransferAmountRemote(user.getUserId(), systemUser,
            ECurrency.CG_CGB, amount, EBizType.CG_O2O_CGB, "O2O消费使用菜狗币",
            "O2O消费回收菜狗币");
        // 商家从平台处拿到返点（人民币）
        accountBO.doTransferAmountRemote(systemUser, store.getOwner(),
            ECurrency.CNY, fdAmount, EBizType.CG_O2O_CGGFD, "O2O消费支付返点人民币",
            "O2O消费收到返点人民币");
        // 资金划转结束--------------
        return code;
    }

    @Override
    public Object storePurchaseZH(String userId, String storeCode, Long amount,
            String payType, String ticketCode) {
        Store store = storeBO.getStore(storeCode);
        if (!EStoreStatus.ON_OPEN.getCode().equals(store.getStatus())) {
            throw new BizException("xn0000", "店铺不处于可消费状态");
        }
        User user = userBO.getRemoteUser(userId);
        // 折扣券可扣减优惠金额
        Long ticketAmount = getTicketAmount(ticketCode, amount);
        boolean isUseTickect = false;
        if (ticketAmount > 0) {
            isUseTickect = true;
            amount = amount - ticketAmount;
        }
        if (EPayType.ZH_YE.getCode().equals(payType)) {
            return storePurchaseZHYE(user, store, amount, isUseTickect);
        } else if (EPayType.WEIXIN.getCode().equals(payType)) {
            return storePurchaseZHWX(user, store, amount);
        } else if (EPayType.ALIPAY.getCode().equals(payType)) {
            return storePurchaseZHZFB(user, store, amount);
        } else {
            throw new BizException("xn0000", "支付方式不存在");
        }
    }

    private Long getTicketAmount(String ticketCode, Long amount) {
        Long ticketAmount = 0L; // 扣除折扣券优惠
        if (StringUtils.isNotBlank(ticketCode)) {// 使用折扣券
            UserTicket userTicket = userTicketBO.getUserTicket(ticketCode);
            if (!EUserTicketStatus.UNUSED.getCode().equals(
                userTicket.getStatus())) {
                throw new BizException("xn0000", "该折扣券不可用");
            }
            StoreTicket storeTicket = storeTicketBO.getStoreTicket(userTicket
                .getTicketCode());
            if (storeTicket.getValidateStart().after(new Date())) {
                throw new BizException("xn0000", "该折扣券还未生效，请选择其他折扣券");
            }
            if (EStoreTicketType.MANJIAN.getCode()
                .equals(storeTicket.getType())) {
                if (amount < storeTicket.getKey1()) {
                    // throw new BizException("xn0000", "消费金额还未达到折扣券满减金额，无法使用");
                } else {
                    // 扣减消费金额
                    ticketAmount = storeTicket.getKey2();
                }
            }
            // 优惠券状态修改
            userTicketBO.refreshUserTicketStatus(ticketCode,
                EUserTicketStatus.USED.getCode());
        }
        return ticketAmount;
    }

    private String storePurchaseZHYE(User user, Store store, Long amount,
            boolean isUseTickect) {
        Long frResultAmount = 0L;// 需要支付的分润金额
        Long gxjlResultAmount = 0L;// 需要支付的贡献值金额计算

        String buyUserId = user.getUserId();
        String storeUserId = store.getOwner();
        // 1、贡献奖励+分润<yhAmount 余额不足
        Account gxjlAccount = accountBO.getRemoteAccount(buyUserId,
            ECurrency.ZH_GXZ);
        Long gxjlAmount = gxjlAccount.getAmount();
        Account frAccount = accountBO.getRemoteAccount(buyUserId,
            ECurrency.ZH_FRB);
        Double gxjl2cnyRate = accountBO.getExchangeRateRemote(ECurrency.ZH_GXZ);
        Double fr2cnyRate = accountBO.getExchangeRateRemote(ECurrency.ZH_FRB);
        if (gxjlAccount.getAmount() / gxjl2cnyRate + frAccount.getAmount()
                / fr2cnyRate < amount) {
            throw new BizException("xn0000", "余额不足");
        }
        // 2、计算frResultAmount和gxjlResultAmount
        if (gxjlAmount <= 0L) {
            frResultAmount = Double.valueOf(amount * fr2cnyRate).longValue();
            gxjlResultAmount = 0L;
        } else if (gxjlAmount > 0L && gxjlAmount / gxjl2cnyRate < amount) {// 0<贡献奖励<yhAmount
            frResultAmount = Double
                .valueOf(
                    (amount - Double.valueOf(gxjlAmount / gxjl2cnyRate)
                        .longValue()) * fr2cnyRate).longValue();
            gxjlResultAmount = gxjlAmount;
        } else if (gxjlAccount.getAmount() / gxjl2cnyRate >= amount) { // 4、贡献奖励>=yhAmount
            frResultAmount = 0L;
            gxjlResultAmount = Double.valueOf(amount * gxjl2cnyRate)
                .longValue();
        }

        // 落地本地系统消费记录
        String code = storePurchaseBO.storePurchaseZHYE(user, store, amount);
        // ---资金划拨开始-----
        // 商家流水
        String systemUser = ESysUser.SYS_USER_ZHPAY.getCode();
        if (gxjlResultAmount > 0L) {// 贡献值是给平台的，贡献值等值的(1:1)分润有平台给商家
            accountBO.doTransferAmountRemote(buyUserId, systemUser,
                ECurrency.ZH_GXZ, gxjlResultAmount, EBizType.ZH_O2O, "正汇O2O支付",
                "正汇O2O支付");
            accountBO.doTransferAmountRemote(systemUser, storeUserId,
                ECurrency.ZH_FRB, gxjlResultAmount, EBizType.ZH_O2O, "正汇O2O支付",
                "正汇O2O支付");
        }
        if (frResultAmount > 0L) {
            accountBO.doTransferAmountRemote(buyUserId, storeUserId,
                ECurrency.ZH_FRB, frResultAmount, EBizType.ZH_O2O, "正汇O2O支付",
                "正汇O2O支付");
        }
        if (isUseTickect) {// 使用折扣券只给公司1%
            Long X1 = Double.valueOf(amount * 0.01).longValue();
            if (X1 > 0) {
                accountBO.doTransferAmountRemote(storeUserId, systemUser,
                    ECurrency.ZH_FRB, X1, EBizType.ZH_O2O, "正汇O2O抵扣卷消费佣金",
                    "正汇O2O抵扣卷消费佣金");
            }
        } else {
            if (EStoreLevel.NOMAL.getCode().equals(store.getLevel())) {
                // 1、买单用户得到消费额35%的钱包币 —— 平台发放
                Long c = Double.valueOf(amount * 0.35).longValue();
                if (c > 0) {
                    accountBO.doTransferAmountRemote(systemUser, buyUserId,
                        ECurrency.ZH_QBB, c, EBizType.ZH_O2O, "正汇O2O平台赠送钱包币",
                        "正汇O2O平台赠送钱包币");
                }
                // 21、买单用户的推荐人B可得到分润X1
                User bUser = userBO.getRemoteUser(user.getUserReferee());
                Long X1 = Double.valueOf(amount * 0.015).longValue();
                if (X1 > 0 && bUser != null) {
                    accountBO.doTransferAmountRemote(storeUserId,
                        bUser.getUserId(), ECurrency.ZH_FRB, X1,
                        EBizType.ZH_O2O, "正汇O2O一级推荐人分成", "正汇O2O一级推荐人分成");
                }
                // 22、B的推荐人A可得到分润X2
                User aUser = userBO.getRemoteUser(bUser.getUserReferee());
                Long X2 = Double.valueOf(amount * 0.015).longValue();
                if (X2 > 0 && aUser != null) {
                    accountBO.doTransferAmountRemote(storeUserId,
                        aUser.getUserId(), ECurrency.ZH_FRB, X2,
                        EBizType.ZH_O2O, "正汇O2O二级推荐人分成", "正汇O2O二级推荐人分成");
                }
                // 23、店铺推荐人可得到分润X3 —— 消费额里面扣除
                Long X3 = Double.valueOf(amount * 0.01).longValue();
                User storeReferee = userBO
                    .getRemoteUser(store.getUserReferee());
                if (X3 > 0 && storeReferee != null) {
                    accountBO.doTransferAmountRemote(storeUserId,
                        storeReferee.getUserId(), ECurrency.ZH_FRB, X3,
                        EBizType.ZH_O2O, "正汇O2O业务员分成", "正汇O2O业务员分成");
                }
                // 24、店铺所在县得到分瑞X4—— 消费额里面扣除
                Long X4 = Double.valueOf(amount * 0.015).longValue();
                User areaUser = userBO.getPartner(store.getProvince(),
                    store.getCity(), store.getArea(), EUserKind.Partner);
                if (X4 > 0 && areaUser != null) {
                    accountBO.doTransferAmountRemote(storeUserId,
                        areaUser.getUserId(), ECurrency.ZH_FRB, X4,
                        EBizType.ZH_O2O, "正汇O2O县合伙人分成", "正汇O2O县合伙人分成");
                }
                // 25、公司X5—— 消费额里面扣除
                Long X5 = Double.valueOf(amount * 0.045).longValue();
                if (X5 > 0) {
                    accountBO.doTransferAmountRemote(storeUserId, systemUser,
                        ECurrency.ZH_FRB, X5, EBizType.ZH_O2O, "正汇O2O公司分成",
                        "正汇O2O公司分成");
                }
            }
            if (EStoreLevel.FINANCIAL.getCode().equals(store.getLevel())) {

                // 21、买单用户的推荐人B可得到分润X1
                User bUser = userBO.getRemoteUser(user.getUserReferee());
                Long X1 = Double.valueOf(amount * 0.008).longValue();
                if (X1 > 0 && bUser != null) {
                    accountBO.doTransferAmountRemote(storeUserId,
                        bUser.getUserId(), ECurrency.ZH_FRB, X1,
                        EBizType.ZH_O2O, "正汇O2O一级推荐人分成", "正汇O2O一级推荐人分成");
                }
                // 22、B的推荐人A可得到分润X2
                User aUser = userBO.getRemoteUser(bUser.getUserReferee());
                Long X2 = Double.valueOf(amount * 0.008).longValue();
                if (X2 > 0 && aUser != null) {
                    accountBO.doTransferAmountRemote(storeUserId,
                        aUser.getUserId(), ECurrency.ZH_FRB, X2,
                        EBizType.ZH_O2O, "正汇O2O二级推荐人分成", "正汇O2O二级推荐人分成");
                }
                // 23、店铺推荐人可得到分润X3 —— 消费额里面扣除
                Long X3 = Double.valueOf(amount * 0.009).longValue();
                User storeReferee = userBO
                    .getRemoteUser(store.getUserReferee());
                if (X3 > 0 && storeReferee != null) {
                    accountBO.doTransferAmountRemote(storeUserId,
                        storeReferee.getUserId(), ECurrency.ZH_FRB, X3,
                        EBizType.ZH_O2O, "正汇O2O业务员分成", "正汇O2O业务员分成");
                }
                // 24、店铺所在县得到分瑞X4—— 消费额里面扣除
                Long X4 = Double.valueOf(amount * 0.015).longValue();
                User areaUser = userBO.getPartner(store.getProvince(),
                    store.getCity(), store.getArea(), EUserKind.Partner);
                if (X4 > 0 && areaUser != null) {
                    accountBO.doTransferAmountRemote(storeUserId,
                        areaUser.getUserId(), ECurrency.ZH_FRB, X4,
                        EBizType.ZH_O2O, "正汇O2O县合伙人分成", "正汇O2O县合伙人分成");

                }
                // 25、公司X5—— 消费额里面扣除
                Long X5 = Double.valueOf(amount * 0.01).longValue();
                if (X5 > 0) {
                    accountBO.doTransferAmountRemote(storeUserId, systemUser,
                        ECurrency.ZH_FRB, X5, EBizType.ZH_O2O, "正汇O2O公司分成",
                        "正汇O2O公司分成");
                }
                // 31、进基金池Y1
                Long Y1 = Double.valueOf(amount * 0.01).longValue();
                String poolUser = EZhPool.ZHPAY_JJ.getCode();
                if (Y1 > 0 && StringUtils.isNotBlank(poolUser)) {
                    accountBO.doTransferAmountRemote(storeUserId, poolUser,
                        ECurrency.ZH_FRB, Y1, EBizType.ZH_O2O, "正汇O2O入基金池",
                        "正汇O2O入基金池");
                }

                // 32、进商家池Y2
                Long Y2 = Double.valueOf(amount * 0.04).longValue();
                poolUser = EZhPool.ZHPAY_STORE.getCode();
                if (Y2 > 0 && StringUtils.isNotBlank(poolUser)) {
                    accountBO.doTransferAmountRemote(storeUserId, poolUser,
                        ECurrency.ZH_FRB, Y2, EBizType.ZH_O2O, "正汇O2O入商家池",
                        "正汇O2O入商家池");
                }

                // 31、进基金池Y3
                Long Y3 = Double.valueOf(amount * 0.15).longValue();
                poolUser = EZhPool.ZHPAY_CUSTOMER.getCode();
                if (Y3 > 0 && StringUtils.isNotBlank(poolUser)) {
                    accountBO.doTransferAmountRemote(storeUserId, poolUser,
                        ECurrency.ZH_FRB, Y3, EBizType.ZH_O2O, "正汇O2O入消费者池",
                        "正汇O2O入消费者池");
                }
                // 形成B端分红权处理
                stockBO.generateBStock(amount, storeUserId);
                // 形成C端分红权处理
                stockBO.generateCStock(frResultAmount, buyUserId);
            }
        }
        return code;
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
    public void paySuccess(String payGroup, String payCode, Long payAmount) {
        StorePurchase storePurchase = storePurchaseBO
            .getStorePurchaseByPayGroup(payGroup);
        if (EStorePurchaseStatus.TO_PAY.getCode().equals(
            storePurchase.getStatus())) {
            // 更新支付记录
            storePurchaseBO.paySuccess(storePurchase, payCode, payAmount);
            // 优惠券状态修改
            String ticketCode = storePurchase.getTicketCode();
            if (StringUtils.isNotBlank(ticketCode)) {
                userTicketBO.refreshUserTicketStatus(ticketCode,
                    EUserTicketStatus.USED.getCode());
            }
        } else {
            logger.info("订单号：" + storePurchase.getCode() + "已支付，重复回调");
        }
    }

    /** 
     * @see com.xnjr.mall.ao.IStorePurchaseAO#getLasterStorePurchase(java.lang.String)
     */
    @Override
    public XN808248Res getLasterStorePurchase(String storeCode) {
        XN808248Res result = null;
        StorePurchase condition = new StorePurchase();
        condition.setStoreCode(storeCode);
        condition.setStatus(EStorePurchaseStatus.PAYED.getCode());
        condition.setOrder("code", "desc");
        Paginable<StorePurchase> page = storePurchaseBO.getPaginable(1, 1,
            condition);

        List<StorePurchase> list = page.getList();
        if (page != null && CollectionUtils.isNotEmpty(list)) {
            StorePurchase data = list.get(0);
            if (EPayType.INTEGRAL.getCode().equals(data.getPayType())) {
                result = new XN808248Res();
                result.setAmount(data.getPayAmount2());
                result.setCurrency(ECurrency.CG_CGB.getCode());
                User user = userBO.getRemoteUser(data.getUserId());

                result.setCode(data.getCode());
                result.setPrice(data.getPrice());
                result.setMobile(user.getMobile());
                result.setNickName(user.getNickname());
                result.setCreateDatetime(data.getCreateDatetime());

                result.setStoreCode(data.getStoreCode());
                result.setCompanyCode(data.getCompanyCode());
                result.setSystemCode(data.getSystemCode());
            } else {
                result = null;
            }
        }
        return result;
    }
}
