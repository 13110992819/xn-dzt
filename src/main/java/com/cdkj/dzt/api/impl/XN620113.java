package com.cdkj.dzt.api.impl;

import com.cdkj.dzt.ao.IArticleAO;
import com.cdkj.dzt.api.AProcessor;
import com.cdkj.dzt.common.JsonUtil;
import com.cdkj.dzt.core.StringValidater;
import com.cdkj.dzt.dto.req.XN620113Req;
import com.cdkj.dzt.dto.res.BooleanRes;
import com.cdkj.dzt.exception.BizException;
import com.cdkj.dzt.exception.ParaException;
import com.cdkj.dzt.spring.SpringContextHolder;

/**
 * 文章上架
 * @author: asus 
 * @since: 2017年8月14日 下午1:20:39 
 * @history:
 */
public class XN620113 extends AProcessor {
    private IArticleAO articleAO = SpringContextHolder
        .getBean(IArticleAO.class);

    private XN620113Req req = null;

    @Override
    public Object doBusiness() throws BizException {
        articleAO.putOn(req.getCode(), req.getLocation(), req.getOrderNo(),
            req.getUpdater(), req.getRemark());
        return new BooleanRes(true);
    }

    @Override
    public void doCheck(String inputparams) throws ParaException {
        req = JsonUtil.json2Bean(inputparams, XN620113Req.class);
        StringValidater.validateBlank(req.getCode(), req.getLocation(),
            req.getOrderNo(), req.getUpdater());
    }
}
