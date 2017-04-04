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
import com.xnjr.mall.bo.IDistributeBO;
import com.xnjr.mall.bo.ISYSConfigBO;
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
    private IDistributeBO distributeBO;

    @Autowired
    private IStoreTicketBO storeTicketBO;

    @Autowired
    private IUserTicketBO userTicketBO;

    @Autowired
    private ISYSConfigBO sysConfigBO;

    @Autowired
    private IUserBO userBO;

    @Autowired
    private IAccountBO accountBO;

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
    @Transactional
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
        Long frAmount = frAccount.getAmount();
        Double gxjl2cnyRate = accountBO.getExchangeRateRemote(ECurrency.ZH_GXZ);
        Double fr2cnyRate = accountBO.getExchangeRateRemote(ECurrency.ZH_FRB);
        if (gxjlAmount / gxjl2cnyRate + frAmount / fr2cnyRate < amount) {
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
        // 会员扣分润和贡献值，商家收分润，先全额收掉。
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
        // 用商家的钱开始分销
        if (isUseTickect) {
            distributeBO.distribute1Amount(amount, store, user);
        } else {
            if (EStoreLevel.NOMAL.getCode().equals(store.getLevel())) {
                distributeBO.distribute10Amount(amount, store, user);
            }
            if (EStoreLevel.FINANCIAL.getCode().equals(store.getLevel())) {
                distributeBO.distribute25Amount(amount, frResultAmount, store,
                    user);
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
