package com.cdkj.dzt.api.impl;

import com.cdkj.dzt.ao.IModelSpecsAO;
import com.cdkj.dzt.api.AProcessor;
import com.cdkj.dzt.common.JsonUtil;
import com.cdkj.dzt.core.StringValidater;
import com.cdkj.dzt.dto.req.XN620282Req;
import com.cdkj.dzt.dto.res.BooleanRes;
import com.cdkj.dzt.exception.BizException;
import com.cdkj.dzt.exception.ParaException;
import com.cdkj.dzt.spring.SpringContextHolder;

public class XN620282 extends AProcessor {
    private IModelSpecsAO modelSpecsAO = SpringContextHolder
        .getBean(IModelSpecsAO.class);

    private XN620282Req req = null;

    @Override
    public Object doBusiness() throws BizException {
        modelSpecsAO.editModelSpecs(req.getCode(), req.getName(), req.getPic(),
            req.getUpdater(), req.getRemark(), req.getModelCode());
        return new BooleanRes(true);

    }

    @Override
    public void doCheck(String inputparams) throws ParaException {
        req = JsonUtil.json2Bean(inputparams, XN620282Req.class);
        StringValidater.validateBlank(req.getCode(), req.getName(),
            req.getPic(), req.getUpdater(), req.getModelCode());
    }

}
