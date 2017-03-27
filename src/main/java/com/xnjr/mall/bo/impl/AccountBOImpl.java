package com.xnjr.mall.bo.impl;

import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.xnjr.mall.bo.IAccountBO;
import com.xnjr.mall.bo.ISYSConfigBO;
import com.xnjr.mall.common.JsonUtil;
import com.xnjr.mall.common.SysConstants;
import com.xnjr.mall.domain.Account;
import com.xnjr.mall.dto.req.XN002000Req;
import com.xnjr.mall.dto.req.XN002100Req;
import com.xnjr.mall.dto.req.XN002500Req;
import com.xnjr.mall.dto.req.XN802525Req;
import com.xnjr.mall.dto.res.PayBalanceRes;
import com.xnjr.mall.dto.res.XN002000Res;
import com.xnjr.mall.dto.res.XN002500Res;
import com.xnjr.mall.enums.EBizType;
import com.xnjr.mall.enums.ECurrency;
import com.xnjr.mall.exception.BizException;
import com.xnjr.mall.http.BizConnecter;
import com.xnjr.mall.http.JsonUtils;

@Component
public class AccountBOImpl implements IAccountBO {
    static Logger logger = Logger.getLogger(AccountBOImpl.class);

    @Autowired
    private ISYSConfigBO sysConfigBO;

    @Override
    public Account getRemoteAccount(String userId, ECurrency currency) {
        XN002000Req req = new XN002000Req();
        req.setUserId(userId);
        req.setCurrency(currency.getCode());
        String jsonStr = BizConnecter.getBizData("002000",
            JsonUtils.object2Json(req));
        Gson gson = new Gson();
        List<XN002000Res> list = gson.fromJson(jsonStr,
            new TypeToken<List<XN002000Res>>() {
            }.getType());
        if (CollectionUtils.isEmpty(list)) {
            throw new BizException("xn000000", "用户[" + userId + "]账户不存在");
        }
        XN002000Res res = list.get(0);
        Account account = new Account();
        account.setAccountNumber(res.getAccountNumber());
        account.setUserId(res.getUserId());
        account.setRealName(res.getRealName());
        account.setType(res.getType());
        account.setStatus(res.getStatus());

        account.setCurrency(res.getCurrency());
        account.setAmount(res.getAmount());
        account.setFrozenAmount(res.getFrozenAmount());
        account.setCreateDatetime(res.getCreateDatetime());
        account.setLastOrder(res.getLastOrder());

        account.setSystemCode(res.getSystemCode());
        return account;
    }

    /** 
     * @see com.xnjr.mall.bo.IAccountBO#doTransferAmountRemote(java.lang.String, java.lang.String, java.lang.String, java.lang.Long, java.lang.String, java.lang.String)
     */
    @Override
    public void doTransferAmountRemote(String fromUserId, String toUserId,
            ECurrency currency, Long amount, EBizType bizType,
            String fromBizNote, String toBizNote) {
        if (amount != null && amount != 0) {
            XN002100Req req = new XN002100Req();
            req.setFromUserId(fromUserId);
            req.setToUserId(toUserId);
            req.setCurrency(currency.getCode());
            req.setTransAmount(String.valueOf(amount));
            req.setBizType(bizType.getCode());
            req.setFromBizNote(fromBizNote);
            req.setToBizNote(toBizNote);
            BizConnecter.getBizData("002100", JsonUtils.object2Json(req),
                Object.class);
        }
    }

    /**
     * @see com.xnjr.mall.bo.IAccountBO#doTransferAmountOnRate(java.lang.String, java.lang.String, java.lang.String, java.lang.Long, java.lang.Double, java.lang.String, java.lang.String)
     */
    @Override
    public void doTransferAmountOnRate(String systemCode,
            String fromAccountNumber, String toAccountNumber, Long amount,
            Double rate, String bizType, String bizNote) {
        XN802525Req req = new XN802525Req();
        req.setSystemCode(systemCode);
        req.setFromAccountNumber(fromAccountNumber);
        req.setToAccountNumber(toAccountNumber);
        req.setTransAmount(String.valueOf(amount));
        req.setRate(String.valueOf(rate));
        req.setBizType(bizType);
        req.setBizNote(bizNote);
        BizConnecter.getBizData("802525", JsonUtils.object2Json(req),
            Object.class);
    }

    /** 
     * @see com.xnjr.mall.bo.IAccountBO#checkBalanceAmount(java.lang.String, java.lang.String, java.lang.Long)
     */
    @Override
    public void checkBalanceAmount(String systemCode, String userId, Long price) {
        Map<String, String> rateMap = sysConfigBO.getConfigsMap(systemCode);
        // 余额支付业务规则：优先扣贡献奖励，其次扣分润
        Account gxjlAccount = getRemoteAccount(userId, ECurrency.GXJL);
        // 查询用户分润账户
        Account frAccount = getRemoteAccount(userId, ECurrency.FRB);
        Double gxjl2cny = Double.valueOf(rateMap.get(SysConstants.GXJL2CNY));
        Double fr2cny = Double.valueOf(rateMap.get(SysConstants.FR2CNY));
        Long gxjlCnyAmount = Double.valueOf(gxjlAccount.getAmount() / gxjl2cny)
            .longValue();
        Long frCnyAmount = Double.valueOf(frAccount.getAmount() / fr2cny)
            .longValue();
        // 1、贡献奖励+分润<价格 余额不足
        if (gxjlCnyAmount + frCnyAmount < price) {
            throw new BizException("xn0000", "余额不足");
        }
    }

    /** 
     * @see com.xnjr.mall.bo.IAccountBO#doBalancePay(com.xnjr.mall.enums.EBizType)
     */
    @Override
    public PayBalanceRes doBalancePay(String systemCode, String fromUserId,
            String toUserId, Long price, EBizType bizType) {
        Long gxjlPrice = 0L;
        Long frPrice = 0L;
        Map<String, String> rateMap = sysConfigBO.getConfigsMap(systemCode);
        // 余额支付业务规则：优先扣贡献奖励，其次扣分润
        Account gxjlAccount = getRemoteAccount(fromUserId, ECurrency.GXJL);
        // 查询用户分润账户
        Account frAccount = getRemoteAccount(fromUserId, ECurrency.FRB);
        Double gxjl2cny = Double.valueOf(rateMap.get(SysConstants.GXJL2CNY));
        Double fr2cny = Double.valueOf(rateMap.get(SysConstants.FR2CNY));
        Long gxjlCnyAmount = Double.valueOf(gxjlAccount.getAmount() / gxjl2cny)
            .longValue();
        Long frCnyAmount = Double.valueOf(frAccount.getAmount() / fr2cny)
            .longValue();
        // 1、贡献奖励+分润<价格 余额不足
        if (gxjlCnyAmount + frCnyAmount < price) {
            throw new BizException("xn0000", "余额不足");
        }
        // 2、贡献奖励=0 直接扣分润
        if (gxjlAccount.getAmount() <= 0L) {
            frPrice = Double.valueOf(price * fr2cny).longValue();
        }
        // 3、0<贡献奖励<price 先扣贡献奖励，再扣分润
        if (gxjlCnyAmount > 0L && gxjlCnyAmount < price) {
            // 扣除贡献奖励
            gxjlPrice = gxjlCnyAmount;
            // 再扣除分润
            frPrice = Double.valueOf((price - gxjlCnyAmount) * fr2cny)
                .longValue();
        }
        // 4、贡献奖励>=price 直接扣贡献奖励
        if (gxjlCnyAmount >= price) {
            gxjlPrice = Double.valueOf(price * gxjl2cny).longValue();
        }
        // 扣除贡献奖励
        doTransferAmountRemote(fromUserId, toUserId, ECurrency.GXJL, gxjlPrice,
            bizType, bizType.getValue(), bizType.getValue());
        // 扣除分润
        doTransferAmountRemote(fromUserId, toUserId, ECurrency.FRB, frPrice,
            bizType, bizType.getValue(), bizType.getValue());
        return new PayBalanceRes(gxjlPrice, frPrice);
    }

    @Override
    public void checkGWBQBBAmount(String systemCode, String userId,
            Long gwbPrice, Long qbbPrice) {
        Account gwbAccount = getRemoteAccount(userId, ECurrency.GWB);
        if (gwbPrice > gwbAccount.getAmount()) {
            throw new BizException("xn0000", "购物币余额不足");
        }
        Account qbbAccount = getRemoteAccount(userId, ECurrency.GWB);
        if (qbbPrice > qbbAccount.getAmount()) {
            throw new BizException("xn0000", "钱包币余额不足");
        }
    }

    /**
     * @see com.xnjr.mall.bo.IAccountBO#doGWBQBBPay(java.lang.String, java.lang.String, java.lang.String, java.lang.Long, java.lang.Long, com.xnjr.mall.enums.EBizType)
     */
    @Override
    public void doGWBQBBPay(String systemCode, String fromUserId,
            String toUserId, Long gwbPrice, Long qbbPrice, EBizType bizType) {
        // 校验购物币和钱包币
        checkGWBQBBAmount(systemCode, fromUserId, gwbPrice, qbbPrice);
        // 扣除购物币
        doTransferAmountRemote(fromUserId, toUserId, ECurrency.GWB, gwbPrice,
            bizType, bizType.getValue(), bizType.getValue());
        // 扣除钱包币
        doTransferAmountRemote(fromUserId, toUserId, ECurrency.QBB, qbbPrice,
            bizType, bizType.getValue(), bizType.getValue());
    }

    /**
     * @see com.xnjr.mall.bo.IAccountBO#checkGwQbAndBalance(java.lang.String, java.lang.String, java.lang.Long, java.lang.Long, java.lang.Long)
     */
    @Override
    public void checkGwQbAndBalance(String systemCode, String userId,
            Long gwbPrice, Long qbbPrice, Long cnyPrice) {
        // 检验购物币和钱包币和余额是否充足
        checkGWBQBBAmount(systemCode, userId, gwbPrice, qbbPrice);
        checkBalanceAmount(systemCode, userId, cnyPrice);
    }

    /** 
     * @see com.xnjr.mall.bo.IAccountBO#doGwQbAndBalancePay(java.lang.String, java.lang.String, java.lang.String, java.lang.Long, com.xnjr.mall.enums.EBizType)
     */
    @Override
    public void doGwQbAndBalancePay(String systemCode, String fromUserId,
            String toUserId, Long gwbPrice, Long qbbPrice, Long cnyPrice,
            EBizType bizType) {
        // 检验购物币和钱包币和余额是否充足
        checkGwQbAndBalance(systemCode, fromUserId, gwbPrice, qbbPrice,
            cnyPrice);
        // 扣除购物币
        doTransferAmountRemote(fromUserId, toUserId, ECurrency.GWB, gwbPrice,
            bizType, bizType.getValue(), bizType.getValue());
        // 扣除钱包币
        doTransferAmountRemote(fromUserId, toUserId, ECurrency.QBB, qbbPrice,
            bizType, bizType.getValue(), bizType.getValue());
        // 扣除余额
        doBalancePay(systemCode, fromUserId, toUserId, cnyPrice, bizType);
    }

    @Override
    public XN002500Res doWeiXinPayRemote(String fromUserId, String toUserId,
            Long amount, EBizType bizType, String fromBizNote,
            String toBizNote, String payGroup) {
        // 获取微信APP支付信息
        XN002500Req req = new XN002500Req();
        req.setFromUserId(fromUserId);
        req.setToUserId(toUserId);
        req.setBizType(bizType.getCode());
        req.setFromBizNote(fromBizNote);
        req.setToBizNote(toBizNote);
        req.setTransAmount(String.valueOf(amount));
        req.setPayGroup(payGroup);
        XN002500Res res = BizConnecter.getBizData("002500",
            JsonUtil.Object2Json(req), XN002500Res.class);
        return res;
    }

}
