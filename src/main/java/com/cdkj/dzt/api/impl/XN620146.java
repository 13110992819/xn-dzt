package com.cdkj.dzt.api.impl;

import com.cdkj.dzt.ao.ISwapAO;
import com.cdkj.dzt.api.AProcessor;
import com.cdkj.dzt.common.JsonUtil;
import com.cdkj.dzt.core.StringValidater;
import com.cdkj.dzt.dto.req.XN620146Req;
import com.cdkj.dzt.exception.BizException;
import com.cdkj.dzt.exception.ParaException;
import com.cdkj.dzt.spring.SpringContextHolder;

/**
 * 详情查看留言
 * @author: asus 
 * @since: 2017年8月16日 下午6:29:36 
 * @history:
 */
public class XN620146 extends AProcessor {
    private ISwapAO swapAO = SpringContextHolder.getBean(ISwapAO.class);

    private XN620146Req req = null;

    @Override
    public Object doBusiness() throws BizException {
        return swapAO.getSwap(req.getCode());
    }

    @Override
    public void doCheck(String inputparams) throws ParaException {
        req = JsonUtil.json2Bean(inputparams, XN620146Req.class);
        StringValidater.validateBlank(req.getCode());
    }

}
