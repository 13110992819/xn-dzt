package com.cdkj.dzt.api.impl;

import com.cdkj.dzt.ao.IOrderAO;
import com.cdkj.dzt.api.AProcessor;
import com.cdkj.dzt.common.JsonUtil;
import com.cdkj.dzt.core.StringValidater;
import com.cdkj.dzt.dto.req.XN620219Req;
import com.cdkj.dzt.exception.BizException;
import com.cdkj.dzt.exception.ParaException;
import com.cdkj.dzt.spring.SpringContextHolder;

/**
 * VIP充值
 * @author: asus 
 * @since: 2017年8月19日 上午12:11:28 
 * @history:
 */
public class XN620219 extends AProcessor {
    private IOrderAO orderAO = SpringContextHolder.getBean(IOrderAO.class);

    private XN620219Req req = null;

    @Override
    public Object doBusiness() throws BizException {
        return orderAO.paymentVIP(req.getUserId(), req.getPayType());
    }

    @Override
    public void doCheck(String inputparams) throws ParaException {
        req = JsonUtil.json2Bean(inputparams, XN620219Req.class);
        StringValidater.validateBlank(req.getUserId(), req.getPayType());
    }

}
