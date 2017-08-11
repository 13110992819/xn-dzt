package com.cdkj.dzt.api.impl;

import com.cdkj.dzt.ao.IKeywordAO;
import com.cdkj.dzt.api.AProcessor;
import com.cdkj.dzt.common.JsonUtil;
import com.cdkj.dzt.core.StringValidater;
import com.cdkj.dzt.dto.req.XN620156Req;
import com.cdkj.dzt.exception.BizException;
import com.cdkj.dzt.exception.ParaException;
import com.cdkj.dzt.spring.SpringContextHolder;

/**
 * 详情查询关键字
 * @author: asus 
 * @since: 2017年7月12日 下午2:43:47 
 * @history:
 */
public class XN620156 extends AProcessor {

    private IKeywordAO keywordAO = SpringContextHolder
        .getBean(IKeywordAO.class);

    private XN620156Req req = null;

    @Override
    public Object doBusiness() throws BizException {
        return keywordAO.getKeyword(req.getCode());
    }

    @Override
    public void doCheck(String inputparams) throws ParaException {
        req = JsonUtil.json2Bean(inputparams, XN620156Req.class);
        StringValidater.validateBlank(req.getCode());
    }
}
