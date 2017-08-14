package com.cdkj.dzt.api.impl;

import com.cdkj.dzt.ao.IArticleAO;
import com.cdkj.dzt.api.AProcessor;
import com.cdkj.dzt.common.JsonUtil;
import com.cdkj.dzt.core.StringValidater;
import com.cdkj.dzt.dto.req.XN620121Req;
import com.cdkj.dzt.exception.BizException;
import com.cdkj.dzt.exception.ParaException;
import com.cdkj.dzt.spring.SpringContextHolder;

/**
 * 删除文章
 * @author: asus 
 * @since: 2017年8月14日 下午1:07:38 
 * @history:
 */
public class XN620121 extends AProcessor {
    private IArticleAO articleAO = SpringContextHolder
        .getBean(IArticleAO.class);

    private XN620121Req req = null;

    @Override
    public Object doBusiness() throws BizException {
        return articleAO.getArticle(req.getCode());
    }

    @Override
    public void doCheck(String inputparams) throws ParaException {
        req = JsonUtil.json2Bean(inputparams, XN620121Req.class);
        StringValidater.validateBlank(req.getCode());
    }

}
