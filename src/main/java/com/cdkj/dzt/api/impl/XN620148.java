package com.cdkj.dzt.api.impl;

import org.apache.commons.lang3.StringUtils;

import com.cdkj.dzt.ao.ISwapAO;
import com.cdkj.dzt.api.AProcessor;
import com.cdkj.dzt.common.JsonUtil;
import com.cdkj.dzt.core.StringValidater;
import com.cdkj.dzt.domain.Swap;
import com.cdkj.dzt.dto.req.XN620148Req;
import com.cdkj.dzt.exception.BizException;
import com.cdkj.dzt.exception.ParaException;
import com.cdkj.dzt.spring.SpringContextHolder;

/**
 * 分页查询我的留言（B端）
 * @author: asus 
 * @since: 2017年8月16日 下午7:20:41 
 * @history:
 */
public class XN620148 extends AProcessor {
    private ISwapAO swapAO = SpringContextHolder.getBean(ISwapAO.class);

    private XN620148Req req = null;

    @Override
    public Object doBusiness() throws BizException {
        Swap condition = new Swap();
        condition.setReceiver(req.getReceiver());
        condition.setType(req.getType());
        String orderColumn = req.getOrderColumn();
        if (StringUtils.isBlank(orderColumn)) {
            orderColumn = ISwapAO.DEFAULT_ORDER_COLUMN;
        }
        condition.setOrder(orderColumn, req.getOrderDir());
        int start = StringValidater.toInteger(req.getStart());
        int limit = StringValidater.toInteger(req.getLimit());
        return swapAO.queryBfrontSwapPage(start, limit, condition);
    }

    @Override
    public void doCheck(String inputparams) throws ParaException {
        req = JsonUtil.json2Bean(inputparams, XN620148Req.class);
        StringValidater.validateBlank(req.getType());
        StringValidater.validateNumber(req.getStart(), req.getLimit());
    }

}
