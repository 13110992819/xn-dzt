package com.cdkj.dzt.ao;

import java.util.List;

import org.springframework.stereotype.Component;

import com.cdkj.dzt.bo.base.Paginable;
import com.cdkj.dzt.domain.OrderSizeData;

@Component
public interface IOrderSizeDataAO {
    static final String DEFAULT_ORDER_COLUMN = "id";

    public String addOrderSizeData(OrderSizeData data);

    public int dropOrderSizeData(String code);

    public int editOrderSizeData(OrderSizeData data);

    public Paginable<OrderSizeData> queryOrderSizeDataPage(int start,
            int limit, OrderSizeData condition);

    public List<OrderSizeData> queryOrderSizeDataList(OrderSizeData condition);

    public OrderSizeData getOrderSizeData(String code);

}
