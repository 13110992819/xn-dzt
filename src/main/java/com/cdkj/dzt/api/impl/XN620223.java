package com.cdkj.dzt.api.impl;

import org.apache.commons.lang3.StringUtils;

import com.cdkj.dzt.ao.IOrderAO;
import com.cdkj.dzt.api.AProcessor;
import com.cdkj.dzt.common.JsonUtil;
import com.cdkj.dzt.core.StringValidater;
import com.cdkj.dzt.domain.Order;
import com.cdkj.dzt.dto.req.XN620223Req;
import com.cdkj.dzt.exception.BizException;
import com.cdkj.dzt.exception.ParaException;
import com.cdkj.dzt.spring.SpringContextHolder;

/**
 * 我的订单分页查询
 * @author: asus 
 * @since: 2017年4月14日 下午5:06:16 
 * @history:
 */
public class XN620223 extends AProcessor {
    private IOrderAO orderAO = SpringContextHolder.getBean(IOrderAO.class);

    private XN620223Req req = null;

    /** 
     * @see com.cdkj.dzt.api.IProcessor#doBusiness()
     */
    @Override
    public Object doBusiness() throws BizException {
        Order condition = new Order();
        condition.setToUser(req.getToUser());
        condition.setApplyUser(req.getApplyUser());
        condition.setApplyName(req.getApplyName());
        condition.setApplyMobile(req.getApplyMobile());
        condition.setLtProvince(req.getLtProvince());
        condition.setLtCity(req.getLtCity());
        condition.setLtArea(req.getLtArea());
        condition.setLtAddress(req.getLtAddress());
        condition.setStatus(req.getStatus());
        condition.setLtUser(req.getLtUser());
        condition.setLtName(req.getLtName());
        condition.setDeliverer(req.getDeliverer());
        condition.setLogisticsCompany(req.getLogisticsCompany());
        condition.setLogisticsCode(req.getLogisticsCode());
        condition.setReceiver(req.getReceiver());
        condition.setReMobile(req.getReMobile());
        condition.setReAddress(req.getReAddress());
        condition.setUpdater(req.getUpdater());
        String orderColumn = req.getOrderColumn();
        if (StringUtils.isBlank(orderColumn)) {
            orderColumn = IOrderAO.DEFAULT_ORDER_COLUMN;
        }
        condition.setOrder(orderColumn, req.getOrderDir());
        int start = StringValidater.toInteger(req.getStart());
        int limit = StringValidater.toInteger(req.getLimit());
        return orderAO.queryOrderPage(start, limit, condition);
    }

    /** 
     * @see com.cdkj.dzt.api.IProcessor#doCheck(java.lang.String)
     */
    @Override
    public void doCheck(String inputparams) throws ParaException {
        req = JsonUtil.json2Bean(inputparams, XN620223Req.class);
        StringValidater.validateNumber(req.getStart(), req.getLimit());
    }

}
