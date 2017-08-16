package com.cdkj.dzt.api.impl;

import com.cdkj.dzt.ao.IInteractAO;
import com.cdkj.dzt.api.AProcessor;
import com.cdkj.dzt.common.JsonUtil;
import com.cdkj.dzt.core.StringValidater;
import com.cdkj.dzt.dto.req.XN620136Req;
import com.cdkj.dzt.exception.BizException;
import com.cdkj.dzt.exception.ParaException;
import com.cdkj.dzt.spring.SpringContextHolder;

public class XN620136 extends AProcessor {
    private IInteractAO interactAO = SpringContextHolder
        .getBean(IInteractAO.class);

    private XN620136Req req = null;

    @Override
    public Object doBusiness() throws BizException {
        return interactAO.getInteract(req.getCode());
    }

    @Override
    public void doCheck(String inputparams) throws ParaException {
        req = JsonUtil.json2Bean(inputparams, XN620136Req.class);
        StringValidater.validateBlank(req.getCode());
    }
}
