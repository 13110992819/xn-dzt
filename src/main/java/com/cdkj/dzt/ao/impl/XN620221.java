package com.cdkj.dzt.ao.impl;

import com.cdkj.dzt.ao.IOrderAO;
import com.cdkj.dzt.api.AProcessor;
import com.cdkj.dzt.common.JsonUtil;
import com.cdkj.dzt.core.StringValidater;
import com.cdkj.dzt.dto.req.XN620221Req;
import com.cdkj.dzt.exception.BizException;
import com.cdkj.dzt.exception.ParaException;
import com.cdkj.dzt.spring.SpringContextHolder;

/**
 * 
 * @author: asus 
 * @since: 2017年8月28日 下午4:45:05 
 * @history:
 */
public class XN620221 extends AProcessor {
    private IOrderAO orderAO = SpringContextHolder.getBean(IOrderAO.class);

    private XN620221Req req = null;

    @Override
    public Object doBusiness() throws BizException {
        return orderAO.totalAmount(req.getUserId());
    }

    @Override
    public void doCheck(String inputparams) throws ParaException {
        req = JsonUtil.json2Bean(inputparams, XN620221Req.class);
        StringValidater.validateBlank(req.getUserId());
    }

}
