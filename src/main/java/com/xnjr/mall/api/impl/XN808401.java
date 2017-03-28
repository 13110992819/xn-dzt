package com.xnjr.mall.api.impl;

import com.xnjr.mall.ao.IStockPoolAO;
import com.xnjr.mall.api.AProcessor;
import com.xnjr.mall.common.JsonUtil;
import com.xnjr.mall.core.StringValidater;
import com.xnjr.mall.dto.req.XN808401Req;
import com.xnjr.mall.exception.BizException;
import com.xnjr.mall.exception.ParaException;
import com.xnjr.mall.spring.SpringContextHolder;

/**
 * 菜狗嗨币对接
 * @author: myb858 
 * @since: 2017年3月28日 上午10:01:14 
 * @history:
 */
public class XN808401 extends AProcessor {
    private IStockPoolAO stockPoolAO = SpringContextHolder
        .getBean(IStockPoolAO.class);

    private XN808401Req req = null;

    @Override
    public Object doBusiness() throws BizException {
        return stockPoolAO.exchangeHighAmount(req.getMobile(),
            req.getLoginPwd(), req.getHighAmount());
    }

    @Override
    public void doCheck(String inputparams) throws ParaException {
        req = JsonUtil.json2Bean(inputparams, XN808401Req.class);
        StringValidater.validateBlank(req.getMobile(), req.getHighAmount());
    }

}
