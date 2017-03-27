package com.xnjr.mall.api.impl;

import com.xnjr.mall.ao.IStockPoolAO;
import com.xnjr.mall.api.AProcessor;
import com.xnjr.mall.common.JsonUtil;
import com.xnjr.mall.core.StringValidater;
import com.xnjr.mall.dto.req.XN808400Req;
import com.xnjr.mall.dto.res.BooleanRes;
import com.xnjr.mall.exception.BizException;
import com.xnjr.mall.exception.ParaException;
import com.xnjr.mall.spring.SpringContextHolder;

/**
 * 菜狗对接池增加金额
 * @author: myb858 
 * @since: 2017年3月27日 下午6:06:53 
 * @history:
 */
public class XN808400 extends AProcessor {
    private IStockPoolAO stockPoolAO = SpringContextHolder
        .getBean(IStockPoolAO.class);

    private XN808400Req req = null;

    @Override
    public Object doBusiness() throws BizException {
        stockPoolAO.addAmount(req.getCode(),
            StringValidater.toLong(req.getAmount()));
        return new BooleanRes(true);
    }

    @Override
    public void doCheck(String inputparams) throws ParaException {
        req = JsonUtil.json2Bean(inputparams, XN808400Req.class);
        StringValidater.validateBlank(req.getCode(), req.getAmount());

    }

}
