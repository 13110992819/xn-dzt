package com.cdkj.dzt.api.impl;

import com.cdkj.dzt.ao.IOrderAO;
import com.cdkj.dzt.api.AProcessor;
import com.cdkj.dzt.common.JsonUtil;
import com.cdkj.dzt.core.StringValidater;
import com.cdkj.dzt.dto.req.XN620218Req;
import com.cdkj.dzt.exception.BizException;
import com.cdkj.dzt.exception.ParaException;
import com.cdkj.dzt.spring.SpringContextHolder;

/**
 * 获取最后一个订单
 * @author: asus 
 * @since: 2017年8月19日 上午12:11:28 
 * @history:
 */
public class XN620218 extends AProcessor {
    private IOrderAO orderAO = SpringContextHolder.getBean(IOrderAO.class);

    private XN620218Req req = null;

    @Override
    public Object doBusiness() throws BizException {
        return orderAO.getLastOrder(req.getApplyUser());
    }

    @Override
    public void doCheck(String inputparams) throws ParaException {
        req = JsonUtil.json2Bean(inputparams, XN620218Req.class);
        StringValidater.validateBlank(req.getApplyUser());
    }

}
