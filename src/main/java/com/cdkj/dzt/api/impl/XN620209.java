package com.cdkj.dzt.api.impl;

import com.cdkj.dzt.ao.IOrderAO;
import com.cdkj.dzt.api.AProcessor;
import com.cdkj.dzt.common.JsonUtil;
import com.cdkj.dzt.core.StringValidater;
import com.cdkj.dzt.dto.req.XN620209Req;
import com.cdkj.dzt.dto.res.BooleanRes;
import com.cdkj.dzt.exception.BizException;
import com.cdkj.dzt.exception.ParaException;
import com.cdkj.dzt.spring.SpringContextHolder;

/**
 * 物流发货
 * @author: asus 
 * @since: 2017年4月14日 下午5:06:16 
 * @history:
 */
public class XN620209 extends AProcessor {
    private IOrderAO orderAO = SpringContextHolder.getBean(IOrderAO.class);

    private XN620209Req req = null;

    /** 
     * @see com.cdkj.dzt.api.IProcessor#doBusiness()
     */
    @Override
    public Object doBusiness() throws BizException {
        orderAO.sendGoods(req.getOrderCode(), req.getDeliverer(),
            req.getDeliveryDatetime(), req.getLogisticsCompany(),
            req.getLogisticsCode(), req.getPdf(), req.getUpdater(),
            req.getRemark());
        return new BooleanRes(true);
    }

    /** 
     * @see com.cdkj.dzt.api.IProcessor#doCheck(java.lang.String)
     */
    @Override
    public void doCheck(String inputparams) throws ParaException {
        req = JsonUtil.json2Bean(inputparams, XN620209Req.class);
        StringValidater
            .validateBlank(req.getToken(), req.getOrderCode(),
                req.getDeliverer(), req.getDeliveryDatetime(),
                req.getLogisticsCompany(), req.getLogisticsCode(),
                req.getUpdater());
    }

}
