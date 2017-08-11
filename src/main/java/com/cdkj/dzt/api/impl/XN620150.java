package com.cdkj.dzt.api.impl;

import com.cdkj.dzt.ao.IKeywordAO;
import com.cdkj.dzt.api.AProcessor;
import com.cdkj.dzt.common.JsonUtil;
import com.cdkj.dzt.core.StringValidater;
import com.cdkj.dzt.dto.req.XN620150Req;
import com.cdkj.dzt.dto.res.PKCodeRes;
import com.cdkj.dzt.exception.BizException;
import com.cdkj.dzt.exception.ParaException;
import com.cdkj.dzt.spring.SpringContextHolder;

/**
 * 新增关键词
 * @author: asus 
 * @since: 2017年7月12日 下午2:40:44 
 * @history:
 */
public class XN620150 extends AProcessor {

    private IKeywordAO keywordAO = SpringContextHolder
        .getBean(IKeywordAO.class);

    private XN620150Req req = null;

    @Override
    public Object doBusiness() throws BizException {
        return new PKCodeRes(keywordAO.addKeyword(req));
    }

    @Override
    public void doCheck(String inputparams) throws ParaException {
        req = JsonUtil.json2Bean(inputparams, XN620150Req.class);
        StringValidater.validateBlank(req.getWord(), req.getLevel(),
            req.getWeight(), req.getReaction(), req.getUpdater());
    }
}
