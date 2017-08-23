package com.cdkj.dzt.api.impl;

import org.apache.commons.lang3.StringUtils;

import com.cdkj.dzt.ao.ISwapAO;
import com.cdkj.dzt.api.AProcessor;
import com.cdkj.dzt.common.JsonUtil;
import com.cdkj.dzt.core.StringValidater;
import com.cdkj.dzt.domain.Swap;
import com.cdkj.dzt.dto.req.XN620147Req;
import com.cdkj.dzt.exception.BizException;
import com.cdkj.dzt.exception.ParaException;
import com.cdkj.dzt.spring.SpringContextHolder;

/**
 * 分页查询我的留言（c端）
 * @author: asus 
 * @since: 2017年8月16日 下午7:20:41 
 * @history:
 */
public class XN620147 extends AProcessor {
    private ISwapAO swapAO = SpringContextHolder.getBean(ISwapAO.class);

    private XN620147Req req = null;

    @Override
    public Object doBusiness() throws BizException {
        Swap condition = new Swap();
        condition.setLookUser(req.getLookUser());
        condition.setType(req.getType());
        String orderColumn = req.getOrderColumn();
        if (StringUtils.isBlank(orderColumn)) {
            orderColumn = ISwapAO.DEFAULT_ORDER_COLUMN;
        }
        condition.setOrder(orderColumn, req.getOrderDir());
        int start = StringValidater.toInteger(req.getStart());
        int limit = StringValidater.toInteger(req.getLimit());
        return swapAO.queryMySwapPage(start, limit, condition);
    }

    @Override
    public void doCheck(String inputparams) throws ParaException {
        req = JsonUtil.json2Bean(inputparams, XN620147Req.class);
        StringValidater.validateBlank(req.getLookUser(), req.getType());
        StringValidater.validateNumber(req.getStart(), req.getLimit());
    }

}
