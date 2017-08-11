package com.cdkj.dzt.api.impl;

import org.apache.commons.lang3.StringUtils;

import com.cdkj.dzt.ao.IKeywordAO;
import com.cdkj.dzt.api.AProcessor;
import com.cdkj.dzt.common.JsonUtil;
import com.cdkj.dzt.core.StringValidater;
import com.cdkj.dzt.domain.Keyword;
import com.cdkj.dzt.dto.req.XN620155Req;
import com.cdkj.dzt.exception.BizException;
import com.cdkj.dzt.exception.ParaException;
import com.cdkj.dzt.spring.SpringContextHolder;

/**
 * 分页查询关键字
 * @author: asus 
 * @since: 2017年7月12日 下午2:43:33 
 * @history:
 */
public class XN620155 extends AProcessor {

    private IKeywordAO keywordAO = SpringContextHolder
        .getBean(IKeywordAO.class);

    private XN620155Req req = null;

    @Override
    public Object doBusiness() throws BizException {
        Keyword condition = new Keyword();
        condition.setWordForQuery(req.getWord());
        condition.setLevel(req.getLevel());
        condition.setReaction(req.getReaction());
        condition.setUpdater(req.getUpdater());
        String orderColumn = req.getOrderColumn();
        if (StringUtils.isBlank(orderColumn)) {
            orderColumn = IKeywordAO.DEFAULT_ORDER_COLUMN;
        }
        condition.setOrder(orderColumn, req.getOrderDir());
        int start = StringValidater.toInteger(req.getStart());
        int limit = StringValidater.toInteger(req.getLimit());
        return keywordAO.queryKeywordPage(start, limit, condition);
    }

    @Override
    public void doCheck(String inputparams) throws ParaException {
        req = JsonUtil.json2Bean(inputparams, XN620155Req.class);
        StringValidater.validateBlank(req.getStart(), req.getLimit());
    }
}
