package com.cdkj.dzt.api.impl;

import com.cdkj.dzt.ao.IArticleAO;
import com.cdkj.dzt.api.AProcessor;
import com.cdkj.dzt.common.JsonUtil;
import com.cdkj.dzt.core.StringValidater;
import com.cdkj.dzt.dto.req.XN620110Req;
import com.cdkj.dzt.dto.res.PKCodeRes;
import com.cdkj.dzt.exception.BizException;
import com.cdkj.dzt.exception.ParaException;
import com.cdkj.dzt.spring.SpringContextHolder;

/**
 * 新增文章
 * @author: asus 
 * @since: 2017年8月14日 上午11:50:52 
 * @history:
 */
public class XN620110 extends AProcessor {
    private IArticleAO articleAO = SpringContextHolder
        .getBean(IArticleAO.class);

    private XN620110Req req = null;

    @Override
    public Object doBusiness() throws BizException {
        return new PKCodeRes(articleAO.addArticle(req));
    }

    @Override
    public void doCheck(String inputparams) throws ParaException {
        req = JsonUtil.json2Bean(inputparams, XN620110Req.class);
        StringValidater.validateBlank(req.getType(), req.getTitle(),
            req.getPic(), req.getAdvPic(), req.getDescription(),
            req.getUpdater());
    }

}
