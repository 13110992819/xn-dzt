package com.cdkj.dzt.api.impl;

import com.cdkj.dzt.ao.ICraftAO;
import com.cdkj.dzt.api.AProcessor;
import com.cdkj.dzt.common.JsonUtil;
import com.cdkj.dzt.core.StringValidater;
import com.cdkj.dzt.dto.req.XN620040Req;
import com.cdkj.dzt.dto.res.PKCodeRes;
import com.cdkj.dzt.exception.BizException;
import com.cdkj.dzt.exception.ParaException;
import com.cdkj.dzt.spring.SpringContextHolder;

public class XN620040 extends AProcessor {
    private ICraftAO craftAO = SpringContextHolder.getBean(ICraftAO.class);

    private XN620040Req req = null;

    @Override
    public Object doBusiness() throws BizException {
        return new PKCodeRes(craftAO.addCraft(req));
    }

    @Override
    public void doCheck(String inputparams) throws ParaException {
        req = JsonUtil.json2Bean(inputparams, XN620040Req.class);
        StringValidater.validateBlank(req.getType(), req.getName(),
            req.getIsHit(), req.getIsDefault(), req.getPic(), req.getUpdater(),
            req.getModelSpecsCode());
        StringValidater.validateAmount(req.getPrice());
    }

}
