package com.cdkj.dzt.api.impl;

import org.apache.commons.lang3.StringUtils;

import com.cdkj.dzt.ao.ISwapAO;
import com.cdkj.dzt.api.AProcessor;
import com.cdkj.dzt.common.JsonUtil;
import com.cdkj.dzt.core.StringValidater;
import com.cdkj.dzt.domain.Swap;
import com.cdkj.dzt.dto.req.XN620145Req;
import com.cdkj.dzt.exception.BizException;
import com.cdkj.dzt.exception.ParaException;
import com.cdkj.dzt.spring.SpringContextHolder;

/**
 * 分页查询客服留言
 * @author: asus 
 * @since: 2017年8月16日 下午6:27:11 
 * @history:
 */
public class XN620145 extends AProcessor {
    private ISwapAO swapAO = SpringContextHolder.getBean(ISwapAO.class);

    private XN620145Req req = null;

    @Override
    public Object doBusiness() throws BizException {
        Swap condition = new Swap();
        condition.setType(req.getType());
        condition.setCommenter(req.getCommenter());
        condition.setReceiver(req.getReceiver());
        condition.setStatus(req.getStatus());
        String orderColumn = req.getOrderColumn();
        if (StringUtils.isBlank(orderColumn)) {
            orderColumn = ISwapAO.DEFAULT_ORDER_COLUMN;
        }
        condition.setOrder(orderColumn, req.getOrderDir());
        int start = StringValidater.toInteger(req.getStart());
        int limit = StringValidater.toInteger(req.getLimit());
        return swapAO.querySwapPage(start, limit, condition);
    }

    @Override
    public void doCheck(String inputparams) throws ParaException {
        req = JsonUtil.json2Bean(inputparams, XN620145Req.class);
        StringValidater.validateNumber(req.getType());
        StringValidater.validateNumber(req.getStart(), req.getLimit());
    }

}
