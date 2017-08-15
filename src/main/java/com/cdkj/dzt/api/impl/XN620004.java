package com.cdkj.dzt.api.impl;

import com.cdkj.dzt.ao.IModelAO;
import com.cdkj.dzt.api.AProcessor;
import com.cdkj.dzt.common.JsonUtil;
import com.cdkj.dzt.core.StringValidater;
import com.cdkj.dzt.dto.req.XN620004Req;
import com.cdkj.dzt.dto.res.BooleanRes;
import com.cdkj.dzt.exception.BizException;
import com.cdkj.dzt.exception.ParaException;
import com.cdkj.dzt.spring.SpringContextHolder;

/** 
 * 型号下架
 * @author: haiqingzheng 
 * @since: 2017年4月14日 下午2:48:36 
 * @history:
 */
public class XN620004 extends AProcessor {
    private IModelAO modelAO = SpringContextHolder.getBean(IModelAO.class);

    private XN620004Req req = null;

    @Override
    public Object doBusiness() throws BizException {
        modelAO.putOff(req.getCode(), req.getUpdater(), req.getRemark());
        return new BooleanRes(true);
    }

    @Override
    public void doCheck(String inputparams) throws ParaException {
        req = JsonUtil.json2Bean(inputparams, XN620004Req.class);
        StringValidater.validateBlank(req.getCode(), req.getUpdater());
    }
}
