package com.cdkj.dzt.api.impl;

import com.cdkj.dzt.ao.IProductCategoryAO;
import com.cdkj.dzt.api.AProcessor;
import com.cdkj.dzt.common.JsonUtil;
import com.cdkj.dzt.core.StringValidater;
import com.cdkj.dzt.dto.req.XN620250Req;
import com.cdkj.dzt.dto.res.PKCodeRes;
import com.cdkj.dzt.exception.BizException;
import com.cdkj.dzt.exception.ParaException;
import com.cdkj.dzt.spring.SpringContextHolder;

/**
 * 新增数据字典
 * @author: xieyj 
 * @since: 2016年9月17日 下午1:45:23 
 * @history:
 */
public class XN620250 extends AProcessor {
    private IProductCategoryAO productCategoryAO = SpringContextHolder
        .getBean(IProductCategoryAO.class);

    private XN620250Req req = null;

    /** 
     * @see com.xnjr.base.api.IProcessor#doBusiness()
     */
    @Override
    public Object doBusiness() throws BizException {
        return new PKCodeRes(productCategoryAO.addProductCategory(
            req.getKind(), req.getType(), req.getParentKey(), req.getDkey(),
            req.getDvalue(), req.getUpdater(), req.getRemark(),
            req.getModelSpecsCode()));
    }

    /** 
     * @see com.xnjr.base.api.IProcessor#doCheck(java.lang.String)
     */
    @Override
    public void doCheck(String inputparams) throws ParaException {
        req = JsonUtil.json2Bean(inputparams, XN620250Req.class);
        StringValidater.validateBlank(req.getType(), req.getDkey(),
            req.getDvalue(), req.getUpdater(), req.getModelSpecsCode());
    }
}
