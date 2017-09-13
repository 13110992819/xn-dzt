package com.cdkj.dzt.api.impl;

import org.apache.commons.lang3.StringUtils;

import com.cdkj.dzt.ao.ISpecimenAO;
import com.cdkj.dzt.api.AProcessor;
import com.cdkj.dzt.common.JsonUtil;
import com.cdkj.dzt.core.StringValidater;
import com.cdkj.dzt.domain.Specimen;
import com.cdkj.dzt.dto.req.XN620270Req;
import com.cdkj.dzt.exception.BizException;
import com.cdkj.dzt.exception.ParaException;
import com.cdkj.dzt.spring.SpringContextHolder;

/**
 * 产品样品分页查
 * @author: asus 
 * @since: 2017年9月12日 下午1:07:29 
 * @history:
 */
public class XN620270 extends AProcessor {
    private ISpecimenAO specimenAO = SpringContextHolder
        .getBean(ISpecimenAO.class);

    private XN620270Req req = null;

    @Override
    public Object doBusiness() throws BizException {
        Specimen condition = new Specimen();
        condition.setModelCode(req.getModelCode());
        condition.setModelStatus(req.getModelStatus());
        condition.setUpdater(req.getUpdater());
        condition.setType(req.getType());
        String orderColumn = req.getOrderColumn();
        if (StringUtils.isBlank(orderColumn)) {
            orderColumn = ISpecimenAO.DEFAULT_ORDER_COLUMN;
        }
        condition.setOrder(orderColumn, req.getOrderDir());
        int start = StringValidater.toInteger(req.getStart());
        int limit = StringValidater.toInteger(req.getLimit());
        return specimenAO.querySpecimenPage(start, limit, condition);
    }

    @Override
    public void doCheck(String inputparams) throws ParaException {
        req = JsonUtil.json2Bean(inputparams, XN620270Req.class);
        StringValidater.validateNumber(req.getStart(), req.getLimit());
    }

}
