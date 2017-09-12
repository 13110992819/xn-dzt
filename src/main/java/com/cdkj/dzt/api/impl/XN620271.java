package com.cdkj.dzt.api.impl;

import com.cdkj.dzt.ao.ISpecimenAO;
import com.cdkj.dzt.api.AProcessor;
import com.cdkj.dzt.common.JsonUtil;
import com.cdkj.dzt.core.StringValidater;
import com.cdkj.dzt.dto.req.XN620271Req;
import com.cdkj.dzt.exception.BizException;
import com.cdkj.dzt.exception.ParaException;
import com.cdkj.dzt.spring.SpringContextHolder;

/**
 * 产品样品详情查
 * @author: asus 
 * @since: 2017年9月12日 下午1:07:29 
 * @history:
 */
public class XN620271 extends AProcessor {
    private ISpecimenAO specimenAO = SpringContextHolder
        .getBean(ISpecimenAO.class);

    private XN620271Req req = null;

    @Override
    public Object doBusiness() throws BizException {
        return specimenAO.getSpecimen(req.getCode());
    }

    @Override
    public void doCheck(String inputparams) throws ParaException {
        req = JsonUtil.json2Bean(inputparams, XN620271Req.class);
        StringValidater.validateBlank(req.getCode());
    }

}
