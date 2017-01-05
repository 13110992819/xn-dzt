package com.xnjr.mall.bo.impl;

import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.xnjr.mall.bo.IAccountBO;
import com.xnjr.mall.dto.req.XN802503Req;
import com.xnjr.mall.dto.req.XN802512Req;
import com.xnjr.mall.dto.req.XN802518Req;
import com.xnjr.mall.dto.res.XN802503Res;
import com.xnjr.mall.exception.BizException;
import com.xnjr.mall.http.BizConnecter;
import com.xnjr.mall.http.JsonUtils;

@Component
public class AccountBOImpl implements IAccountBO {
    static Logger logger = Logger.getLogger(AccountBOImpl.class);

    /** 
     * @see com.xnjr.mall.bo.IAccountBO#getAccountByUserId(java.lang.String)
     */
    @Override
    public XN802503Res getAccountByUserId(String systemCode, String userId,
            String currency) {
        XN802503Res result = null;
        XN802503Req req = new XN802503Req();
        req.setSystemCode(systemCode);
        req.setUserId(userId);
        req.setCurrency(currency);
        String jsonStr = BizConnecter.getBizData("802503",
            JsonUtils.object2Json(req));
        Gson gson = new Gson();
        List<XN802503Res> list = gson.fromJson(jsonStr,
            new TypeToken<List<XN802503Res>>() {
            }.getType());
        if (CollectionUtils.isNotEmpty(list)) {
            result = list.get(0);
        }
        if (null == result) {
            throw new BizException("xn000000", "用户[" + userId + "]账户不存在");
        }
        return result;
    }

    /** 
     * @see com.xnjr.mall.bo.IAccountBO#doTransferAmount(java.lang.String, java.lang.String, java.lang.String, java.lang.Long, java.lang.String, java.lang.String)
     */
    @Override
    public void doTransferAmount(String systemCode, String fromAccountNumber,
            String toAccountNumber, Long amount, String bizType, String bizNote) {
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

    /** 
     * @see com.xnjr.mall.bo.IAccountBO#doExchangeAmount(java.lang.String, java.lang.String, java.lang.String, java.lang.Long, java.lang.Double, java.lang.String, java.lang.String)
     */
    @Override
    public void doExchangeAmount(String systemCode, String fromAccountNumber,
            String toAccountNumber, Long transAmount, Double rate,
            String bizType, String bizNote) {
        XN802518Req req = new XN802518Req();
        req.setSystemCode(systemCode);
        req.setFromAccountNumber(fromAccountNumber);
        req.setToAccountNumber(toAccountNumber);
        req.setTransAmount(String.valueOf(transAmount));
        req.setRate(String.valueOf(rate));
        req.setBizType(bizType);
        req.setBizNote(bizNote);
        BizConnecter.getBizData("802518", JsonUtils.object2Json(req),
            Object.class);

    }
}
