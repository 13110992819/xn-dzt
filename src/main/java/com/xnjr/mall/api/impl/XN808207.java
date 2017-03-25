package com.xnjr.mall.api.impl;

import com.xnjr.mall.ao.IStoreAO;
import com.xnjr.mall.api.AProcessor;
import com.xnjr.mall.common.JsonUtil;
import com.xnjr.mall.core.StringValidater;
import com.xnjr.mall.dto.req.XN808206Req;
import com.xnjr.mall.dto.res.BooleanRes;
import com.xnjr.mall.exception.BizException;
import com.xnjr.mall.exception.ParaException;
import com.xnjr.mall.spring.SpringContextHolder;

/**
 * 升级为理财型店铺
 * @author: myb858 
 * @since: 2017年3月25日 下午6:27:22 
 * @history:
 */
public class XN808207 extends AProcessor {
    private IStoreAO storeAO = SpringContextHolder.getBean(IStoreAO.class);

    private XN808206Req req = null;

    @Override
    public Object doBusiness() throws BizException {
        storeAO.closeOpen(req.getCode());
        return new BooleanRes(true);
    }

    @Override
    public void doCheck(String inputparams) throws ParaException {
        req = JsonUtil.json2Bean(inputparams, XN808206Req.class);
        StringValidater.validateBlank(req.getCode());
    }

}
