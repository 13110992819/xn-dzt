package com.cdkj.dzt.api.impl;

import com.cdkj.dzt.ao.ISwapAO;
import com.cdkj.dzt.api.AProcessor;
import com.cdkj.dzt.common.JsonUtil;
import com.cdkj.dzt.core.StringValidater;
import com.cdkj.dzt.dto.req.XN620143Req;
import com.cdkj.dzt.dto.res.BooleanRes;
import com.cdkj.dzt.exception.BizException;
import com.cdkj.dzt.exception.ParaException;
import com.cdkj.dzt.spring.SpringContextHolder;

/**
 * 阅读留言
 * @author: asus 
 * @since: 2017年8月16日 下午2:52:56 
 * @history:
 */
public class XN620143 extends AProcessor {
    private ISwapAO swapAO = SpringContextHolder.getBean(ISwapAO.class);

    private XN620143Req req = null;

    @Override
    public Object doBusiness() throws BizException {
        swapAO.editSwap(req.getCode(), req.getLookUser());
        return new BooleanRes(true);
    }

    @Override
    public void doCheck(String inputparams) throws ParaException {
        req = JsonUtil.json2Bean(inputparams, XN620143Req.class);
        StringValidater.validateBlank(req.getCode(), req.getLookUser());
    }

}
