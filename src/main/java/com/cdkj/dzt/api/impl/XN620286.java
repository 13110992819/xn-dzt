package com.cdkj.dzt.api.impl;

import com.cdkj.dzt.ao.IModelSpecsAO;
import com.cdkj.dzt.api.AProcessor;
import com.cdkj.dzt.common.JsonUtil;
import com.cdkj.dzt.core.StringValidater;
import com.cdkj.dzt.dto.req.XN620286Req;
import com.cdkj.dzt.exception.BizException;
import com.cdkj.dzt.exception.ParaException;
import com.cdkj.dzt.spring.SpringContextHolder;

/**
 * 详情查询产品规格
 * @author: asus 
 * @since: 2017年9月12日 下午4:56:17 
 * @history:
 */
public class XN620286 extends AProcessor {
    private IModelSpecsAO modelSpecsAO = SpringContextHolder
        .getBean(IModelSpecsAO.class);

    private XN620286Req req = null;

    @Override
    public Object doBusiness() throws BizException {
        return modelSpecsAO.getModelSpecs(req.getCode());
    }

    @Override
    public void doCheck(String inputparams) throws ParaException {
        req = JsonUtil.json2Bean(inputparams, XN620286Req.class);
        StringValidater.validateBlank(req.getCode());
    }

}
