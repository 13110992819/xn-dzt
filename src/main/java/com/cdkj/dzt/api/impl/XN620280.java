package com.cdkj.dzt.api.impl;

import com.cdkj.dzt.ao.IModelSpecsAO;
import com.cdkj.dzt.api.AProcessor;
import com.cdkj.dzt.common.JsonUtil;
import com.cdkj.dzt.core.StringValidater;
import com.cdkj.dzt.dto.req.XN620280Req;
import com.cdkj.dzt.dto.res.PKCodeRes;
import com.cdkj.dzt.exception.BizException;
import com.cdkj.dzt.exception.ParaException;
import com.cdkj.dzt.spring.SpringContextHolder;

public class XN620280 extends AProcessor {
    private IModelSpecsAO modelSpecsAO = SpringContextHolder
        .getBean(IModelSpecsAO.class);

    private XN620280Req req = null;

    @Override
    public Object doBusiness() throws BizException {
        return new PKCodeRes(
            modelSpecsAO.addModelSpecs(req.getName(), req.getPic(),
                req.getUpdater(), req.getRemark(), req.getModelCode()));
    }

    @Override
    public void doCheck(String inputparams) throws ParaException {
        req = JsonUtil.json2Bean(inputparams, XN620280Req.class);
        StringValidater.validateBlank(req.getName(), req.getPic(),
            req.getUpdater(), req.getModelCode());
    }

}
