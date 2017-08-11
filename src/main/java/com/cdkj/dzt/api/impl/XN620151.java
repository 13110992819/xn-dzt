package com.cdkj.dzt.api.impl;

import com.cdkj.dzt.ao.IKeywordAO;
import com.cdkj.dzt.api.AProcessor;
import com.cdkj.dzt.common.JsonUtil;
import com.cdkj.dzt.core.StringValidater;
import com.cdkj.dzt.dto.req.XN620151Req;
import com.cdkj.dzt.dto.res.BooleanRes;
import com.cdkj.dzt.exception.BizException;
import com.cdkj.dzt.exception.ParaException;
import com.cdkj.dzt.spring.SpringContextHolder;

/**
 * 删除关键词
 * @author: asus 
 * @since: 2017年7月12日 下午2:41:14 
 * @history:
 */
public class XN620151 extends AProcessor {

    private IKeywordAO keywordAO = SpringContextHolder
        .getBean(IKeywordAO.class);

    private XN620151Req req = null;

    @Override
    public Object doBusiness() throws BizException {
        keywordAO.dropKeyword(req.getCode());
        return new BooleanRes(true);
    }

    @Override
    public void doCheck(String inputparams) throws ParaException {
        req = JsonUtil.json2Bean(inputparams, XN620151Req.class);
        StringValidater.validateBlank(req.getCode());
    }
}
