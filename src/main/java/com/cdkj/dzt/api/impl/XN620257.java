package com.cdkj.dzt.api.impl;

import org.apache.commons.lang3.StringUtils;

import com.cdkj.dzt.ao.IProductCategoryAO;
import com.cdkj.dzt.api.AProcessor;
import com.cdkj.dzt.common.JsonUtil;
import com.cdkj.dzt.domain.ProductCategory;
import com.cdkj.dzt.dto.req.XN620257Req;
import com.cdkj.dzt.exception.BizException;
import com.cdkj.dzt.exception.ParaException;
import com.cdkj.dzt.spring.SpringContextHolder;

/**
 * 列表查询数据字典
 * @author: xieyj 
 * @since: 2016年9月17日 下午1:50:02 
 * @history:
 */
public class XN620257 extends AProcessor {
    private IProductCategoryAO productCategoryAO = SpringContextHolder
        .getBean(IProductCategoryAO.class);

    private XN620257Req req = null;

    /** 
     * @see com.xnjr.base.api.IProcessor#doBusiness()
     */
    @Override
    public Object doBusiness() throws BizException {
        ProductCategory condition = new ProductCategory();
        condition.setType(req.getType());
        condition.setParentKey(req.getParentKey());
        condition.setDkey(req.getDkey());
        condition.setModelCode(req.getModelCode());
        String orderColumn = req.getOrderColumn();
        if (StringUtils.isBlank(orderColumn)) {
            orderColumn = IProductCategoryAO.DEFAULT_ORDER_COLUMN;
        }
        condition.setOrder(orderColumn, req.getOrderDir());
        return productCategoryAO.queryProductCategoryList(condition);
    }

    /** 
     * @see com.xnjr.base.api.IProcessor#doCheck(java.lang.String)
     */
    @Override
    public void doCheck(String inputparams) throws ParaException {
        req = JsonUtil.json2Bean(inputparams, XN620257Req.class);
    }
}
