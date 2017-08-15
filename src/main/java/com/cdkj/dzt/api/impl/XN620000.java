package com.cdkj.dzt.api.impl;

import com.cdkj.dzt.ao.IModelAO;
import com.cdkj.dzt.api.AProcessor;
import com.cdkj.dzt.common.JsonUtil;
import com.cdkj.dzt.core.StringValidater;
import com.cdkj.dzt.dto.req.XN620000Req;
import com.cdkj.dzt.dto.res.PKCodeRes;
import com.cdkj.dzt.exception.BizException;
import com.cdkj.dzt.exception.ParaException;
import com.cdkj.dzt.spring.SpringContextHolder;

/**
 * 新增型号
 * @author: asus 
 * @since: 2017年8月14日 下午1:50:53 
 * @history:
 */
public class XN620000 extends AProcessor {
    private IModelAO modelAO = SpringContextHolder.getBean(IModelAO.class);

    private XN620000Req req = null;

    @Override
    public Object doBusiness() throws BizException {
        return new PKCodeRes(modelAO.addModel(req));
    }

    @Override
    public void doCheck(String inputparams) throws ParaException {
        req = JsonUtil.json2Bean(inputparams, XN620000Req.class);
        StringValidater.validateBlank(req.getType(), req.getName(),
            req.getPic(), req.getAdvPic(), req.getDescription(),
            req.getUpdater());
    }
}
