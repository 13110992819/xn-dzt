package com.cdkj.dzt.api.impl;

import com.cdkj.dzt.ao.IOrderAO;
import com.cdkj.dzt.api.AProcessor;
import com.cdkj.dzt.common.JsonUtil;
import com.cdkj.dzt.core.StringValidater;
import com.cdkj.dzt.dto.req.XN620214Req;
import com.cdkj.dzt.dto.res.PKCodeRes;
import com.cdkj.dzt.exception.BizException;
import com.cdkj.dzt.exception.ParaException;
import com.cdkj.dzt.spring.SpringContextHolder;

/**
 * 评论
 * @author: asus 
 * @since: 2017年8月18日 上午10:13:58 
 * @history:
 */
public class XN620214 extends AProcessor {
    private IOrderAO orderAO = SpringContextHolder.getBean(IOrderAO.class);

    private XN620214Req req = null;

    @Override
    public Object doBusiness() throws BizException {
        return new PKCodeRes(orderAO.comment(req.getOrderCode(),
            req.getContent(), req.getCommenter()));
    }

    @Override
    public void doCheck(String inputparams) throws ParaException {
        req = JsonUtil.json2Bean(inputparams, XN620214Req.class);
        StringValidater.validateBlank(req.getOrderCode(), req.getContent(),
            req.getCommenter());
    }

}