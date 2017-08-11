package com.cdkj.dzt.api.impl;

import org.apache.commons.lang3.StringUtils;

import com.cdkj.dzt.ao.ICommentAO;
import com.cdkj.dzt.api.AProcessor;
import com.cdkj.dzt.common.JsonUtil;
import com.cdkj.dzt.core.StringValidater;
import com.cdkj.dzt.domain.Comment;
import com.cdkj.dzt.dto.req.XN620170Req;
import com.cdkj.dzt.exception.BizException;
import com.cdkj.dzt.exception.ParaException;
import com.cdkj.dzt.spring.SpringContextHolder;

/**
 * 分页查询评论
 * @author: asus 
 * @since: 2017年7月19日 下午3:38:09 
 * @history:
 */
public class XN620170 extends AProcessor {
    private ICommentAO commentAO = SpringContextHolder
        .getBean(ICommentAO.class);

    private XN620170Req req = null;

    @Override
    public Object doBusiness() throws BizException {
        Comment condition = new Comment();
        condition.setProductCode(req.getProductCode());
        condition.setStatus(req.getStatus());
        condition.setContent(req.getContent());
        condition.setStatusList(req.getStatusList());
        String orderColumn = req.getOrderColumn();
        if (StringUtils.isBlank(orderColumn)) {
            orderColumn = ICommentAO.DEFAULT_ORDER_COLUMN;
        }
        condition.setOrder(orderColumn, req.getOrderDir());
        int start = StringValidater.toInteger(req.getStart());
        int limit = StringValidater.toInteger(req.getLimit());
        return commentAO.queryCommentPage(start, limit, condition);
    }

    @Override
    public void doCheck(String inputparams) throws ParaException {
        req = JsonUtil.json2Bean(inputparams, XN620170Req.class);
        StringValidater.validateNumber(req.getStart(), req.getLimit());
    }

}
