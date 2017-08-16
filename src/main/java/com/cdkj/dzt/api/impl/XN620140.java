package com.cdkj.dzt.api.impl;

import com.cdkj.dzt.ao.ISwapAO;
import com.cdkj.dzt.api.AProcessor;
import com.cdkj.dzt.common.JsonUtil;
import com.cdkj.dzt.core.StringValidater;
import com.cdkj.dzt.dto.req.XN620140Req;
import com.cdkj.dzt.dto.res.PKCodeRes;
import com.cdkj.dzt.exception.BizException;
import com.cdkj.dzt.exception.ParaException;
import com.cdkj.dzt.spring.SpringContextHolder;

/**
 * 用户向客服/顾问留言
 * @author: asus 
 * @since: 2017年8月16日 下午4:06:59 
 * @history:
 */
public class XN620140 extends AProcessor {
    private ISwapAO swapAO = SpringContextHolder.getBean(ISwapAO.class);

    private XN620140Req req = null;

    @Override
    public Object doBusiness() throws BizException {
        return new PKCodeRes(swapAO.addSwap(req.getType(), req.getCommenter(),
            req.getContent()));
    }

    @Override
    public void doCheck(String inputparams) throws ParaException {
        req = JsonUtil.json2Bean(inputparams, XN620140Req.class);
        StringValidater.validateBlank(req.getType(), req.getCommenter(),
            req.getContent());
    }

}
