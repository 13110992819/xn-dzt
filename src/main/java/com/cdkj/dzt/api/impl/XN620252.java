package com.cdkj.dzt.api.impl;

import com.cdkj.dzt.ao.IProductCategoryAO;
import com.cdkj.dzt.api.AProcessor;
import com.cdkj.dzt.common.JsonUtil;
import com.cdkj.dzt.core.StringValidater;
import com.cdkj.dzt.dto.req.XN620252Req;
import com.cdkj.dzt.dto.res.BooleanRes;
import com.cdkj.dzt.exception.BizException;
import com.cdkj.dzt.exception.ParaException;
import com.cdkj.dzt.spring.SpringContextHolder;

/**
 * @author: xieyj 
 * @since: 2016年9月17日 下午1:48:11 
 * @history:
 */
public class XN620252 extends AProcessor {
    private IProductCategoryAO productCategoryAO = SpringContextHolder
        .getBean(IProductCategoryAO.class);

    private XN620252Req req = null;

    /** 
     * @see com.xnjr.base.api.IProcessor#doBusiness()
     */
    @Override
    public Object doBusiness() throws BizException {
        productCategoryAO.editProductCategory(req.getCode(), req.getDvalue(),
            req.getUpdater(), req.getRemark());
        return new BooleanRes(true);
    }

    /** 
     * @see com.xnjr.base.api.IProcessor#doCheck(java.lang.String)
     */
    @Override
    public void doCheck(String inputparams) throws ParaException {
        req = JsonUtil.json2Bean(inputparams, XN620252Req.class);
        StringValidater.validateBlank(req.getCode(), req.getDvalue());
    }
}
