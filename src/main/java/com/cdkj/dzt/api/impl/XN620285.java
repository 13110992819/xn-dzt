package com.cdkj.dzt.api.impl;

import org.apache.commons.lang3.StringUtils;

import com.cdkj.dzt.ao.IModelSpecsAO;
import com.cdkj.dzt.api.AProcessor;
import com.cdkj.dzt.common.JsonUtil;
import com.cdkj.dzt.core.StringValidater;
import com.cdkj.dzt.domain.ModelSpecs;
import com.cdkj.dzt.dto.req.XN620285Req;
import com.cdkj.dzt.exception.BizException;
import com.cdkj.dzt.exception.ParaException;
import com.cdkj.dzt.spring.SpringContextHolder;

/**
 * 分页查询产品规格
 * @author: asus 
 * @since: 2017年9月12日 下午4:41:26 
 * @history:
 */
public class XN620285 extends AProcessor {
    private IModelSpecsAO modelSpecsAO = SpringContextHolder
        .getBean(IModelSpecsAO.class);

    private XN620285Req req = null;

    @Override
    public Object doBusiness() throws BizException {
        ModelSpecs condition = new ModelSpecs();
        condition.setModelCode(req.getModelCode());
        condition.setName(req.getName());
        condition.setUpdater(req.getUpdater());
        String orderColumn = req.getOrderColumn();
        if (StringUtils.isBlank(orderColumn)) {
            orderColumn = IModelSpecsAO.DEFAULT_ORDER_COLUMN;
        }
        condition.setOrder(orderColumn, req.getOrderDir());
        int start = StringValidater.toInteger(req.getStart());
        int limit = StringValidater.toInteger(req.getLimit());
        return modelSpecsAO.queryModelSpecsPage(start, limit, condition);
    }

    @Override
    public void doCheck(String inputparams) throws ParaException {
        req = JsonUtil.json2Bean(inputparams, XN620285Req.class);
        StringValidater.validateNumber(req.getStart(), req.getLimit());
    }

}
