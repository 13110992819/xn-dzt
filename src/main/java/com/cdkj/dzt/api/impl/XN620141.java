package com.cdkj.dzt.api.impl;

import com.cdkj.dzt.ao.ISwapAO;
import com.cdkj.dzt.api.AProcessor;
import com.cdkj.dzt.common.JsonUtil;
import com.cdkj.dzt.core.StringValidater;
import com.cdkj.dzt.dto.req.XN620141Req;
import com.cdkj.dzt.dto.res.PKCodeRes;
import com.cdkj.dzt.exception.BizException;
import com.cdkj.dzt.exception.ParaException;
import com.cdkj.dzt.spring.SpringContextHolder;

/**
 * 客服/顾问回复用户留言
 * @author: asus 
 * @since: 2017年8月16日 下午5:36:29 
 * @history:
 */
public class XN620141 extends AProcessor {
    private ISwapAO swapAO = SpringContextHolder.getBean(ISwapAO.class);

    private XN620141Req req = null;

    @Override
    public Object doBusiness() throws BizException {
        return new PKCodeRes(swapAO.addSwapReply(req.getCommenter(),
            req.getContent(), req.getReceiver()));
    }

    @Override
    public void doCheck(String inputparams) throws ParaException {
        req = JsonUtil.json2Bean(inputparams, XN620141Req.class);
        StringValidater.validateBlank(req.getCommenter(), req.getContent(),
            req.getReceiver());
    }
}
