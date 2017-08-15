package com.cdkj.dzt.api.impl;

import com.cdkj.dzt.ao.IInteractAO;
import com.cdkj.dzt.api.AProcessor;
import com.cdkj.dzt.common.JsonUtil;
import com.cdkj.dzt.core.StringValidater;
import com.cdkj.dzt.dto.req.XN620131Req;
import com.cdkj.dzt.dto.res.BooleanRes;
import com.cdkj.dzt.exception.BizException;
import com.cdkj.dzt.exception.ParaException;
import com.cdkj.dzt.spring.SpringContextHolder;

/**
 * 取消收藏
 * @author: asus 
 * @since: 2017年8月15日 下午10:24:50 
 * @history:
 */
public class XN620131 extends AProcessor {
    private IInteractAO interactAO = SpringContextHolder
        .getBean(IInteractAO.class);

    private XN620131Req req = null;

    @Override
    public Object doBusiness() throws BizException {
        interactAO.dropInteract(req.getObjectCode(), req.getOperator());
        return new BooleanRes(true);
    }

    @Override
    public void doCheck(String inputparams) throws ParaException {
        req = JsonUtil.json2Bean(inputparams, XN620131Req.class);
        StringValidater.validateBlank(req.getObjectCode(), req.getOperator());
    }

}
