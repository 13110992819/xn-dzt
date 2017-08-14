package com.cdkj.dzt.api.impl;

import org.apache.commons.lang.StringUtils;

import com.cdkj.dzt.ao.IArticleAO;
import com.cdkj.dzt.api.AProcessor;
import com.cdkj.dzt.common.JsonUtil;
import com.cdkj.dzt.domain.Article;
import com.cdkj.dzt.dto.req.XN620122Req;
import com.cdkj.dzt.exception.BizException;
import com.cdkj.dzt.exception.ParaException;
import com.cdkj.dzt.spring.SpringContextHolder;

/**
 * 文章列表查
 * @author: asus 
 * @since: 2017年8月14日 下午1:20:39 
 * @history:
 */
public class XN620122 extends AProcessor {
    private IArticleAO articleAO = SpringContextHolder
        .getBean(IArticleAO.class);

    private XN620122Req req = null;

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
        return articleAO.queryArticleList(condition);
    }

    @Override
    public void doCheck(String inputparams) throws ParaException {
        req = JsonUtil.json2Bean(inputparams, XN620122Req.class);
    }
}
