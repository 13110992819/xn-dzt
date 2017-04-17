package com.cdkj.dzt.bo;

import java.util.Date;
import java.util.List;

import com.cdkj.dzt.bo.base.IPaginableBO;
import com.cdkj.dzt.domain.Order;
import com.cdkj.dzt.enums.EOrderStatus;

public interface IOrderBO extends IPaginableBO<Order> {

    public boolean isOrderExist(String code);

    public void saveOrder(Order data);

    public int removeOrder(String code);

    public void assignedOrder(Order order, String ltUser, String ltName,
            String updater, String remark);

    public void confirmPrice(Order order, Long amount, String updater,
            String remark);

    public void addPayGroup(Order order, String payGroup, String payType);

    public void ltSubmit(Order order, String updater);

    public void approveOrder(Order order, EOrderStatus status, String updater,
            String remark);

    public void submitProudect(Order order, String updater, String remark);

    public void sendGoods(Order order, String logisticsCompany,
            String logisticsCode, String deliverer, Date deliveryDatetime,
            String pdf, String updater, String remark);

    public void confirmReceipt(Order order, String updater, String remark);

    public void cancelOrder(Order order, String userId, String remark);

    public List<Order> queryOrderList(Order condition);

    public Order getOrder(String code);

    public List<Order> queryOrderList(String applyUser);

}
