package com.cdkj.dzt.api.impl;

import org.apache.commons.lang3.StringUtils;

import com.cdkj.dzt.ao.IModelSpecsAO;
import com.cdkj.dzt.api.AProcessor;
import com.cdkj.dzt.common.JsonUtil;
import com.cdkj.dzt.domain.ModelSpecs;
import com.cdkj.dzt.dto.req.XN620287Req;
import com.cdkj.dzt.exception.BizException;
import com.cdkj.dzt.exception.ParaException;
import com.cdkj.dzt.spring.SpringContextHolder;

/**
 * 列表查询产品规格
 * @author: asus 
 * @since: 2017年9月12日 下午4:41:26 
 * @history:
 */
public class XN620287 extends AProcessor {
    private IModelSpecsAO modelSpecsAO = SpringContextHolder
        .getBean(IModelSpecsAO.class);

    private XN620287Req req = null;

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
        return modelSpecsAO.queryModelSpecsList(condition);
    }

    @Override
    public void doCheck(String inputparams) throws ParaException {
        req = JsonUtil.json2Bean(inputparams, XN620287Req.class);
    }

}
