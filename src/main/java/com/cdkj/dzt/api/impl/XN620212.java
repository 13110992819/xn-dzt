package com.cdkj.dzt.api.impl;

import com.cdkj.dzt.ao.IOrderAO;
import com.cdkj.dzt.api.AProcessor;
import com.cdkj.dzt.common.JsonUtil;
import com.cdkj.dzt.core.StringValidater;
import com.cdkj.dzt.dto.req.XN620212Req;
import com.cdkj.dzt.dto.res.BooleanRes;
import com.cdkj.dzt.exception.BizException;
import com.cdkj.dzt.exception.ParaException;
import com.cdkj.dzt.spring.SpringContextHolder;

/**
 * 归档
 * @author: asus 
 * @since: 2017年8月18日 上午10:59:36 
 * @history:
 */
public class XN620212 extends AProcessor {
    private IOrderAO orderAO = SpringContextHolder.getBean(IOrderAO.class);

    private XN620212Req req = null;

    @Override
    public Object doBusiness() throws BizException {
        orderAO.isFiled(req.getOrderCode(), req.getUpdater(), req.getRemark());
        return new BooleanRes(true);
    }

    @Override
    public void doCheck(String inputparams) throws ParaException {
        req = JsonUtil.json2Bean(inputparams, XN620212Req.class);
        StringValidater.validateBlank(req.getToken(), req.getOrderCode(),
            req.getUpdater());
    }

}
