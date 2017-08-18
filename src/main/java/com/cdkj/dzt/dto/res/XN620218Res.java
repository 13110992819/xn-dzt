package com.cdkj.dzt.dto.res;

import java.util.Map;

import com.cdkj.dzt.domain.Order;

/**
 * 获取最后一个订单
 * @author: asus 
 * @since: 2017年8月19日 上午12:09:21 
 * @history:
 */
public class XN620218Res {
    private Order order;

    private Map<String, String> map;

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public Map<String, String> getMap() {
        return map;
    }

    public void setMap(Map<String, String> map) {
        this.map = map;
    }
}
