package com.cdkj.dzt.api.impl;

import org.apache.commons.lang.StringUtils;

import com.cdkj.dzt.ao.IArticleAO;
import com.cdkj.dzt.api.AProcessor;
import com.cdkj.dzt.common.JsonUtil;
import com.cdkj.dzt.core.StringValidater;
import com.cdkj.dzt.domain.Article;
import com.cdkj.dzt.dto.req.XN620120Req;
import com.cdkj.dzt.exception.BizException;
import com.cdkj.dzt.exception.ParaException;
import com.cdkj.dzt.spring.SpringContextHolder;

/**
 * 文章分页查
 * @author: asus 
 * @since: 2017年8月14日 下午1:20:39 
 * @history:
 */
public class XN620120 extends AProcessor {
    private IArticleAO articleAO = SpringContextHolder
        .getBean(IArticleAO.class);

    private XN620120Req req = null;

    @Override
    public Object doBusiness() throws BizException {
        Article condition = new Article();
        condition.setTitle(req.getTitle());
        condition.setType(req.getType());
        condition.setStatus(req.getStatus());
        condition.setLocation(req.getLocation());
        condition.setUpdater(req.getUpdater());
        String orderColumn = req.getOrderColumn();
        if (StringUtils.isEmpty(orderColumn)) {
            orderColumn = IArticleAO.DEFAULT_ORDER_COLUMN;
        }
        condition.setOrder(orderColumn, req.getOrderDir());
        int start = StringValidater.toInteger(req.getStart());
        int limit = StringValidater.toInteger(req.getLimit());
        return articleAO.queryArticlePage(start, limit, condition);
    }

    @Override
    public void doCheck(String inputparams) throws ParaException {
        req = JsonUtil.json2Bean(inputparams, XN620120Req.class);
        StringValidater.validateNumber(req.getStart(), req.getLimit());
    }
}
