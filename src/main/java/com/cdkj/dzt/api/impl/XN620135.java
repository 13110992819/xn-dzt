package com.cdkj.dzt.api.impl;

import org.apache.commons.lang.StringUtils;

import com.cdkj.dzt.ao.IInteractAO;
import com.cdkj.dzt.api.AProcessor;
import com.cdkj.dzt.common.JsonUtil;
import com.cdkj.dzt.core.StringValidater;
import com.cdkj.dzt.domain.Interact;
import com.cdkj.dzt.dto.req.XN620135Req;
import com.cdkj.dzt.exception.BizException;
import com.cdkj.dzt.exception.ParaException;
import com.cdkj.dzt.spring.SpringContextHolder;

/**
 * 分页查询我的收藏
 * @author: asus 
 * @since: 2017年8月16日 下午1:57:54 
 * @history:
 */
public class XN620135 extends AProcessor {
    private IInteractAO interactAO = SpringContextHolder
        .getBean(IInteractAO.class);

    private XN620135Req req = null;

    @Override
    public Object doBusiness() throws BizException {
        Interact condition = new Interact();
        condition.setCategory(req.getCategory());
        condition.setType(req.getType());
        condition.setObjectCode(req.getObjectCode());
        condition.setOperator(req.getOperator());
        String orderColumn = req.getOrderColumn();
        if (StringUtils.isBlank(orderColumn)) {
            orderColumn = IInteractAO.DEFAULT_ORDER_COLUMN;
        }
        condition.setOrder(orderColumn, req.getOrderDir());
        int start = StringValidater.toInteger(req.getStart());
        int limit = StringValidater.toInteger(req.getLimit());
        return interactAO.queryInteractPage(start, limit, condition);
    }

    @Override
    public void doCheck(String inputparams) throws ParaException {
        req = JsonUtil.json2Bean(inputparams, XN620135Req.class);
        StringValidater.validateBlank(req.getOperator());
        StringValidater.validateNumber(req.getStart(), req.getLimit());
    }

}
