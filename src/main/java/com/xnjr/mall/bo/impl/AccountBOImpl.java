package com.xnjr.mall.bo.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.xnjr.mall.bo.IAccountBO;
import com.xnjr.mall.bo.ISYSConfigBO;
import com.xnjr.mall.common.JsonUtil;
import com.xnjr.mall.common.SysConstants;
import com.xnjr.mall.dto.req.XN802180Req;
import com.xnjr.mall.dto.req.XN802503Req;
import com.xnjr.mall.dto.req.XN802512Req;
import com.xnjr.mall.dto.req.XN802517Req;
import com.xnjr.mall.dto.req.XN802519Req;
import com.xnjr.mall.dto.req.XN802525Req;
import com.xnjr.mall.dto.res.PayBalanceRes;
import com.xnjr.mall.dto.res.XN802180Res;
import com.xnjr.mall.dto.res.XN802503Res;
import com.xnjr.mall.enums.EBizType;
import com.xnjr.mall.enums.ECurrency;
import com.xnjr.mall.enums.ESysUser;
import com.xnjr.mall.exception.BizException;
import com.xnjr.mall.http.BizConnecter;
import com.xnjr.mall.http.JsonUtils;

@Component
public class AccountBOImpl implements IAccountBO {
    static Logger logger = Logger.getLogger(AccountBOImpl.class);

    @Autowired
    private ISYSConfigBO sysConfigBO;

    /** 
     * @see com.xnjr.mall.bo.IAccountBO#getAccountByUserId(java.lang.String)
     */
    @Override
    public XN802503Res getAccountByUserId(String systemCode, String userId,
            String currency) {
        Map<String, XN802503Res> map = this.getAccountsByUser(systemCode,
            userId);
        XN802503Res result = map.get(currency);
        if (null == result) {
            throw new BizException("xn000000", "用户[" + userId + "]账户不存在");
        }
        return result;
    }

    /**
     * @see com.xnjr.mall.bo.IAccountBO#getAccountsByUser(java.lang.String, java.lang.String)
     */
    @Override
    public Map<String, XN802503Res> getAccountsByUser(String systemCode,
            String userId) {
        Map<String, XN802503Res> resultMap = new HashMap<String, XN802503Res>();
        XN802503Req req = new XN802503Req();
        req.setSystemCode(systemCode);
        req.setUserId(userId);
        String jsonStr = BizConnecter.getBizData("802503",
            JsonUtils.object2Json(req));
        Gson gson = new Gson();
        List<XN802503Res> list = gson.fromJson(jsonStr,
            new TypeToken<List<XN802503Res>>() {
            }.getType());
        for (XN802503Res xn802503Res : list) {
            resultMap.put(xn802503Res.getCurrency(), xn802503Res);
        }
        return resultMap;
    }

    /** 
     * @see com.xnjr.mall.bo.IAccountBO#doTransferAmount(java.lang.String, java.lang.String, java.lang.String, java.lang.Long, java.lang.String, java.lang.String)
     */
    @Override
    public void doTransferAmount(String systemCode, String fromAccountNumber,
            String toAccountNumber, Long amount, String bizType, String bizNote) {
        if (amount != null && amount != 0) {
            XN802512Req req = new XN802512Req();
            req.setSystemCode(systemCode);
            req.setFromAccountNumber(fromAccountNumber);
            req.setToAccountNumber(toAccountNumber);
            req.setTransAmount(String.valueOf(amount));
            req.setBizType(bizType);
            req.setBizNote(bizNote);
            BizConnecter.getBizData("802512", JsonUtils.object2Json(req),
                Object.class);
        }
    }

    /** 
     * @see com.xnjr.mall.bo.IAccountBO#doTransferAmountByUser(java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.Long, java.lang.String, java.lang.String)
     */
    @Override
    public void doTransferAmountByUser(String systemCode, String fromUserId,
            String toUserId, String currency, Long amount, String bizType,
            String bizNote) {
        if (amount != null && amount != 0) {
            XN802517Req req = new XN802517Req();
            req.setSystemCode(systemCode);
            req.setFromUserId(fromUserId);
            req.setToUserId(toUserId);
            req.setCurrency(currency);
            req.setTransAmount(String.valueOf(amount));
            req.setBizType(bizType);
            req.setBizNote(bizNote);
            BizConnecter.getBizData("802517", JsonUtils.object2Json(req),
                Object.class);
        }
    }

    @Override
    public void doTransferFcBySystem(String systemCode, String userId,
            String currency, Long transAmount, String bizType, String bizNote) {
        if (transAmount == null || transAmount == 0) {
            return;
        }
        this.doTransferAmountByUser(systemCode, ESysUser.SYS_USER.getCode(),
            userId, currency, transAmount, bizType, bizNote);
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
     * @see com.xnjr.mall.bo.IAccountBO#doExchangeAmount(java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String)
     */
    @Override
    public void doExchangeAmount(String systemCode, String code, String rate,
            String approveResult, String approver, String approveNote) {
        XN802519Req req = new XN802519Req();
        req.setSystemCode(systemCode);
        req.setCode(code);
        req.setRate(rate);
        req.setApproveResult(approveResult);
        req.setApprover(approver);
        req.setApproveNote(approveNote);
        BizConnecter.getBizData("802519", JsonUtils.object2Json(req),
            Object.class);
    }

    /** 
     * @see com.xnjr.mall.bo.IAccountBO#checkBalanceAmount(java.lang.String, java.lang.String, java.lang.Long)
     */
    @Override
    public void checkBalanceAmount(String systemCode, String userId, Long price) {
        Map<String, String> rateMap = sysConfigBO.getConfigsMap(systemCode,
            null);
        // 余额支付业务规则：优先扣贡献奖励，其次扣分润
        XN802503Res gxjlAccount = this.getAccountByUserId(systemCode, userId,
            ECurrency.GXJL.getCode());
        // 查询用户分润账户
        XN802503Res frAccount = this.getAccountByUserId(systemCode, userId,
            ECurrency.FRB.getCode());
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
        Map<String, String> rateMap = sysConfigBO.getConfigsMap(systemCode,
            null);
        // 余额支付业务规则：优先扣贡献奖励，其次扣分润
        XN802503Res gxjlAccount = this.getAccountByUserId(systemCode,
            fromUserId, ECurrency.GXJL.getCode());
        // 查询用户分润账户
        XN802503Res frAccount = this.getAccountByUserId(systemCode, fromUserId,
            ECurrency.FRB.getCode());
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
        doTransferAmountByUser(systemCode, fromUserId, toUserId,
            ECurrency.GXJL.getCode(), gxjlPrice, bizType.getCode(),
            bizType.getValue());
        // 扣除分润
        doTransferAmountByUser(systemCode, fromUserId, toUserId,
            ECurrency.FRB.getCode(), frPrice, bizType.getCode(),
            bizType.getValue());
        return new PayBalanceRes(gxjlPrice, frPrice);
    }

    @Override
    public void checkGWBQBBAmount(String systemCode, String userId,
            Long gwbPrice, Long qbbPrice) {
        XN802503Res gwbRes = getAccountByUserId(systemCode, userId,
            ECurrency.GWB.getCode());
        if (gwbPrice > gwbRes.getAmount()) {
            throw new BizException("xn0000", "购物币余额不足");
        }
        XN802503Res qbbRes = getAccountByUserId(systemCode, userId,
            ECurrency.QBB.getCode());
        if (qbbPrice > qbbRes.getAmount()) {
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
        doTransferAmountByUser(systemCode, fromUserId, toUserId,
            ECurrency.GWB.getCode(), gwbPrice, bizType.getCode(),
            bizType.getValue());
        // 扣除钱包币
        doTransferAmountByUser(systemCode, fromUserId, toUserId,
            ECurrency.QBB.getCode(), qbbPrice, bizType.getCode(),
            bizType.getValue());
    }

    /** 
     * @see com.xnjr.mall.bo.IAccountBO#doGwQbAndBalancePay(java.lang.String, java.lang.String, java.lang.String, java.lang.Long, com.xnjr.mall.enums.EBizType)
     */
    @Override
    public void doGwQbAndBalancePay(String systemCode, String fromUserId,
            String toUserId, Long gwbPrice, Long qbbPrice, Long cnyPrice,
            EBizType bizType) {
        // 检验购物币和钱包币和余额是否充足
        checkGWBQBBAmount(systemCode, fromUserId, gwbPrice, qbbPrice);
        checkBalanceAmount(systemCode, fromUserId, cnyPrice);
        // 扣除购物币
        doTransferAmountByUser(systemCode, fromUserId, toUserId,
            ECurrency.GWB.getCode(), gwbPrice, bizType.getCode(),
            bizType.getValue());
        // 扣除钱包币
        doTransferAmountByUser(systemCode, fromUserId, toUserId,
            ECurrency.QBB.getCode(), qbbPrice, bizType.getCode(),
            bizType.getValue());
        // 扣除余额
        doBalancePay(systemCode, fromUserId, toUserId, cnyPrice, bizType);
    }

    @Override
    public XN802180Res doWeiXinPay(String systemCode, String userId,
            EBizType bizType, String bizNote, String body, Long cnyAmount,
            String ip) {
        if (StringUtils.isBlank(ip)) {
            throw new BizException("xn0000", "微信支付，ip地址不能为空");
        }
        // 获取微信APP支付信息
        XN802180Req req = new XN802180Req();
        req.setSystemCode(systemCode);
        req.setCompanyCode(systemCode);
        req.setUserId(userId);
        req.setBizType(bizType.getCode());
        req.setBizNote(bizNote);
        req.setBody(body);
        req.setTotalFee(String.valueOf(cnyAmount));
        req.setSpbillCreateIp(ip);
        XN802180Res res = BizConnecter.getBizData("802180",
            JsonUtil.Object2Json(req), XN802180Res.class);
        return res;
    }
}
