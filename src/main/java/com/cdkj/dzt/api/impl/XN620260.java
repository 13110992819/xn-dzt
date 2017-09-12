package com.cdkj.dzt.api.impl;

import com.cdkj.dzt.ao.ISpecimenAO;
import com.cdkj.dzt.api.AProcessor;
import com.cdkj.dzt.common.JsonUtil;
import com.cdkj.dzt.core.StringValidater;
import com.cdkj.dzt.dto.req.XN620260Req;
import com.cdkj.dzt.dto.res.PKCodeRes;
import com.cdkj.dzt.exception.BizException;
import com.cdkj.dzt.exception.ParaException;
import com.cdkj.dzt.spring.SpringContextHolder;

/**
 * 新增产品样品
 * @author: asus 
 * @since: 2017年9月12日 下午1:07:29 
 * @history:
 */
public class XN620260 extends AProcessor {
    private ISpecimenAO specimenAO = SpringContextHolder
        .getBean(ISpecimenAO.class);

    private XN620260Req req = null;

    @Override
    public Object doBusiness() throws BizException {
        return new PKCodeRes(specimenAO.addSpecimen(req.getPic(),
            req.getAdvPic(), req.getDescription(), req.getUpdater(),
            req.getRemark(), req.getModelCode()));
    }

    @Override
    public void doCheck(String inputparams) throws ParaException {
        req = JsonUtil.json2Bean(inputparams, XN620260Req.class);
        StringValidater.validateBlank(req.getPic(), req.getAdvPic(),
            req.getDescription(), req.getUpdater(), req.getModelCode());
    }

}
