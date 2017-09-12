package com.cdkj.dzt.ao.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cdkj.dzt.ao.IOrderSizeDataAO;
import com.cdkj.dzt.bo.IOrderSizeDataBO;
import com.cdkj.dzt.bo.base.Paginable;
import com.cdkj.dzt.domain.OrderSizeData;

@Service
public class OrderSizeDataAOImpl implements IOrderSizeDataAO {

    @Autowired
    private IOrderSizeDataBO orderSizeDataBO;

    @Override
    public String addOrderSizeData(OrderSizeData data) {
        return null;
    }

    @Override
    public int editOrderSizeData(OrderSizeData data) {
        return 0;
    }

    @Override
    public int dropOrderSizeData(String code) {
        return 0;
    }

    @Override
    public Paginable<OrderSizeData> queryOrderSizeDataPage(int start,
            int limit, OrderSizeData condition) {
        return orderSizeDataBO.getPaginable(start, limit, condition);
    }

    @Override
    public List<OrderSizeData> queryOrderSizeDataList(OrderSizeData condition) {
        return orderSizeDataBO.queryOrderSizeDataList(condition);
    }

    @Override
    public OrderSizeData getOrderSizeData(String code) {
        return orderSizeDataBO.getOrderSizeData(code);
    }
}
