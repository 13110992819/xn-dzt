package com.cdkj.dzt.api.impl;

import com.cdkj.dzt.ao.IModelAO;
import com.cdkj.dzt.api.AProcessor;
import com.cdkj.dzt.common.JsonUtil;
import com.cdkj.dzt.core.StringValidater;
import com.cdkj.dzt.dto.req.XN620003Req;
import com.cdkj.dzt.dto.res.BooleanRes;
import com.cdkj.dzt.exception.BizException;
import com.cdkj.dzt.exception.ParaException;
import com.cdkj.dzt.spring.SpringContextHolder;

/**
 * 上架型号
 * @author: asus 
 * @since: 2017年8月15日 下午4:24:13 
 * @history:
 */
public class XN620003 extends AProcessor {
    private IModelAO modelAO = SpringContextHolder.getBean(IModelAO.class);

    private XN620003Req req = null;

    @Override
    public Object doBusiness() throws BizException {
        modelAO.putOn(req.getCode(), req.getLocation(), req.getOrderNo(),
            req.getUpdater(), req.getRemark());
        return new BooleanRes(true);
    }

    @Override
    public void doCheck(String inputparams) throws ParaException {
        req = JsonUtil.json2Bean(inputparams, XN620003Req.class);
        StringValidater.validateBlank(req.getCode(), req.getLocation(),
            req.getUpdater());
        StringValidater.validateNumber(req.getOrderNo());
    }

}
