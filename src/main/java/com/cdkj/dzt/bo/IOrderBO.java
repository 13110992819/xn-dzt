package com.cdkj.dzt.bo;

import java.util.Date;
import java.util.List;

import com.cdkj.dzt.bo.base.IPaginableBO;
import com.cdkj.dzt.domain.Model;
import com.cdkj.dzt.domain.Order;
import com.cdkj.dzt.enums.EOrderStatus;

public interface IOrderBO extends IPaginableBO<Order> {

    public void applyOrder(Order data);

    public void distributeOrder(Order order, String ltUser, String ltName,
            String updater, String remark);

    public void confirmPrice(Order order, Model model, Long amount,
            String updater, String remark);

    public void addPayGroup(Order order, String payGroup, String payType);

    public void PaySuccess(Order order, String payCode, Long amount);

    public void inputInfor(Order order, String reAddress, String updater,
            String remark);

    public void ltSubmit(Order order, String updater);

    public void approveOrder(Order order, EOrderStatus status, String updater,
            String remark);

    public void submitProudect(Order order, String updater, String remark);

    public void sendGoods(Order order, String deliverer, Date deliveryDatetime,
            String logisticsCompany, String logisticsCode, String pdf,
            String updater, String remark);

    public void confirmReceipt(Order order, String updater, String remark);

    public void cancelOrder(Order order, String updater, String remark);

    public List<Order> queryOrderList(Order condition);

    public Order getOrder(String code);

    public Order getRichOrder(String code);

    public Order getLastOrder(String applyUser);

    public List<Order> queryOrderListByPayGroup(String payGroup);

    // 订单数据是否完整
    public void checkInfoFull(Order order);

    public Long getTotalCount(String userId, EOrderStatus status);

    public void comment(Order order, String commenter);

    public void isFiled(Order order, String updater, String remark);

    public List<Order> getGroupTotalCount(Date createDatetimeStart,
            Date createDatetimeEnd);

}
