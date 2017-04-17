/**
 * @Title OrderAOImpl.java 
 * @Package com.cdkj.dzt.ao.impl 
 * @Description 
 * @author leo(haiqing)  
 * @date 2017年4月16日 下午2:55:53 
 * @version V1.0   
 */
package com.cdkj.dzt.ao.impl;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cdkj.dzt.ao.IOrderAO;
import com.cdkj.dzt.bo.IOrderBO;
import com.cdkj.dzt.common.DateUtil;
import com.cdkj.dzt.core.OrderNoGenerater;
import com.cdkj.dzt.domain.Order;
import com.cdkj.dzt.dto.req.XN620200Req;
import com.cdkj.dzt.enums.EGeneratePrefix;

/** 
 * @author: haiqingzheng 
 * @since: 2017年4月16日 下午2:55:53 
 * @history:
 */
@Service
public class OrderAOImpl implements IOrderAO {

    @Autowired
    private IOrderBO orderBO;

    @Override
    public String commitOrder(XN620200Req req) {
        Order order = new Order();
        Date now = new Date();
        String code = OrderNoGenerater.generateM(EGeneratePrefix.ORDER
            .getCode());
        order.setCode(code);
        order.setToUser(userBO.getParter());
        order.setApplyUser(req.getApplyUser());
        order.setApplyName(req.getApplyName());
        order.setApplyMobile(req.getApplyMobile());

        order.setLtDatetime(DateUtil.strToDate(req.getLtDatetime(),
            DateUtil.DATA_TIME_PATTERN_1));
        order.setLtProvince(req.getLtProvince());
        order.setLtCity(req.getLtCity());
        order.setLtArea(req.getLtArea());
        order.setLtAddress(req.getLtAddress());

        order.setApplyNote(req.getApplyNote());
        order.setCreateDatetime(now);

        orderBO.saveOrder(order);
        return code;
    }
}
