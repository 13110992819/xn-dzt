package com.cdkj.dzt.api.impl;

import com.cdkj.dzt.ao.ISpecimenAO;
import com.cdkj.dzt.api.AProcessor;
import com.cdkj.dzt.common.JsonUtil;
import com.cdkj.dzt.core.StringValidater;
import com.cdkj.dzt.dto.req.XN620262Req;
import com.cdkj.dzt.dto.res.BooleanRes;
import com.cdkj.dzt.exception.BizException;
import com.cdkj.dzt.exception.ParaException;
import com.cdkj.dzt.spring.SpringContextHolder;

/**
 * 修改产品样品
 * @author: asus 
 * @since: 2017年9月12日 下午1:07:29 
 * @history:
 */
public class XN620262 extends AProcessor {
    private ISpecimenAO specimenAO = SpringContextHolder
        .getBean(ISpecimenAO.class);

    private XN620262Req req = null;

    @Override
    public Object doBusiness() throws BizException {
        specimenAO.editSpecimen(req.getCode(), req.getPic(), req.getAdvPic(),
            req.getDescription(), req.getUpdater(), req.getRemark(),
            req.getModelCode());
        return new BooleanRes(true);
    }

    @Override
    public void doCheck(String inputparams) throws ParaException {
        req = JsonUtil.json2Bean(inputparams, XN620262Req.class);
        StringValidater.validateBlank(req.getCode(), req.getPic(),
            req.getAdvPic(), req.getDescription(), req.getUpdater(),
            req.getModelCode());
    }

}
