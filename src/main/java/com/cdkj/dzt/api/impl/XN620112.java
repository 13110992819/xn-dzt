package com.cdkj.dzt.api.impl;

import com.cdkj.dzt.ao.IArticleAO;
import com.cdkj.dzt.api.AProcessor;
import com.cdkj.dzt.common.JsonUtil;
import com.cdkj.dzt.core.StringValidater;
import com.cdkj.dzt.dto.req.XN620112Req;
import com.cdkj.dzt.dto.res.BooleanRes;
import com.cdkj.dzt.exception.BizException;
import com.cdkj.dzt.exception.ParaException;
import com.cdkj.dzt.spring.SpringContextHolder;

/**
 * 修改文章
 * @author: asus 
 * @since: 2017年8月14日 下午1:17:27 
 * @history:
 */
public class XN620112 extends AProcessor {
    private IArticleAO articleAO = SpringContextHolder
        .getBean(IArticleAO.class);

    private XN620112Req req = null;

    @Override
    public Object doBusiness() throws BizException {
        articleAO.editArticle(req);
        return new BooleanRes(true);
    }

    @Override
    public void doCheck(String inputparams) throws ParaException {
        req = JsonUtil.json2Bean(inputparams, XN620112Req.class);
        StringValidater.validateBlank(req.getCode(), req.getType(),
            req.getTitle(), req.getPic(), req.getAdvPic(),
            req.getDescription(), req.getUpdater());
    }
}
