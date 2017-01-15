package com.xnjr.mall.api.impl;

import com.xnjr.mall.ao.IJewelRecordAO;
import com.xnjr.mall.api.AProcessor;
import com.xnjr.mall.core.StringValidater;
import com.xnjr.mall.dto.req.XN808307Req;
import com.xnjr.mall.dto.res.BooleanRes;
import com.xnjr.mall.exception.BizException;
import com.xnjr.mall.exception.ParaException;
import com.xnjr.mall.http.JsonUtils;
import com.xnjr.mall.spring.SpringContextHolder;

/**
 * 填写地址
 * @author: xieyj 
 * @since: 2017年1月13日 下午2:14:33 
 * @history:
 */
public class XN808307 extends AProcessor {
    private IJewelRecordAO jewelRecordAO = SpringContextHolder
        .getBean(IJewelRecordAO.class);

    private XN808307Req req = null;

    @Override
    public Object doBusiness() throws BizException {
        jewelRecordAO.fulReAddress(req.getCode(), req.getReceiver(),
            req.getReMobile(), req.getReAddress());
        return new BooleanRes(true);
    }

    @Override
    public void doCheck(String inputparams) throws ParaException {
        req = JsonUtils.json2Bean(inputparams, XN808307Req.class);
        StringValidater.validateBlank(req.getCode(), req.getReceiver(),
            req.getReMobile(), req.getReAddress());
    }
}
