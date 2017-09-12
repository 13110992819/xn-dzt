package com.cdkj.dzt.bo;

import java.util.List;
import java.util.Map;

import com.cdkj.dzt.bo.base.IPaginableBO;
import com.cdkj.dzt.domain.Order;
import com.cdkj.dzt.domain.OrderSizeData;

public interface IOrderSizeDataBO extends IPaginableBO<OrderSizeData> {

    public void saveOrderSizeData(OrderSizeData data);

    public void removeOrderSizeData(String code);

    public void refreshOrderSizeData(OrderSizeData data);

    public List<OrderSizeData> queryOrderSizeDataList(OrderSizeData condition);

    public OrderSizeData getOrderSizeData(String code);

    void inputInforValue(Order order, Map<String, String> map);

    void removeOrderSizeData(String type, String orderCode);

}
