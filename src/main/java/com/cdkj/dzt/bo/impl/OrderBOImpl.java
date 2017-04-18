package com.cdkj.dzt.bo.impl;

import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.cdkj.dzt.bo.IOrderBO;
import com.cdkj.dzt.bo.base.PaginableBOImpl;
import com.cdkj.dzt.dao.IOrderDAO;
import com.cdkj.dzt.domain.Order;
import com.cdkj.dzt.enums.EOrderStatus;
import com.cdkj.dzt.exception.BizException;

@Component
public class OrderBOImpl extends PaginableBOImpl<Order> implements IOrderBO {

    @Autowired
    private IOrderDAO orderDAO;

    @Override
    public void applyOrder(Order data) {
        orderDAO.insert(data);
    }

    @Override
    public void distributeOrder(Order order, String ltUser, String ltName,
            String updater, String remark) {
        order.setLtUser(ltUser);
        order.setLtName(ltName);
        order.setUpdater(updater);
        order.setUpdateDatetime(new Date());
        order.setRemark(remark);
        orderDAO.distributeOrder(order);
    }

    @Override
    public void confirmPrice(Order order, Long amount, String updater,
            String remark) {
        order.setAmount(amount);
        order.setUpdater(updater);
        order.setUpdateDatetime(new Date());
        order.setRemark(remark);
        orderDAO.updateConfirm(order);
    }

    @Override
    public void addPayGroup(Order order, String payGroup, String payType) {
        order.setPayGroup(payGroup);
        order.setPayType(payType);
        orderDAO.updatePayGroup(order);
    }

    @Override
    public void PaySuccess(Order order, String payCode, Long amount) {
        order.setStatus(EOrderStatus.PAY_YES.getCode());
        order.setPayCode(payCode);
        order.setPayAmount(amount);
        order.setPayDatetime(new Date());
        orderDAO.PaySuccess(order);
    }

    @Override
    public void inputInfor(Order order, String reAddress, String updater,
            String remark) {
        order.setReAddress(reAddress);
        order.setUpdater(updater);
        order.setUpdateDatetime(new Date());
        order.setRemark(remark);
        orderDAO.inputInfor(order);
    }

    @Override
    public void ltSubmit(Order order, String updater) {
        order.setStatus(EOrderStatus.TO_APPROVE.getCode());
        order.setUpdater(updater);
        order.setUpdateDatetime(new Date());
        orderDAO.ltSubmit(order);
    }

    @Override
    public void approveOrder(Order order, EOrderStatus status, String updater,
            String remark) {
        order.setStatus(status.getCode());
        order.setUpdater(updater);
        order.setUpdateDatetime(new Date());
        order.setRemark(remark);
        orderDAO.approveOrder(order);
    }

    @Override
    public void submitProudect(Order order, String updater, String remark) {
        order.setStatus(EOrderStatus.PRODU_DOING.getCode());
        order.setUpdater(updater);
        order.setUpdateDatetime(new Date());
        order.setRemark(remark);
        orderDAO.submitProudect(order);
    }

    @Override
    public void sendGoods(Order order, String logisticsCompany,
            String logisticsCode, String deliverer, Date deliveryDatetime,
            String pdf, String updater, String remark) {
        order.setStatus(EOrderStatus.SEND.getCode());
        order.setLogisticsCompany(logisticsCompany);
        order.setLogisticsCode(logisticsCode);
        order.setDeliverer(deliverer);
        order.setDeliveryDatetime(deliveryDatetime);
        order.setPdf(pdf);
        order.setUpdater(updater);
        order.setUpdateDatetime(new Date());
        order.setRemark(remark);
        orderDAO.sendGoods(order);
    }

    @Override
    public void confirmReceipt(Order order, String updater, String remark) {
        order.setStatus(EOrderStatus.RECEIVE.getCode());
        order.setUpdater(updater);
        order.setUpdateDatetime(new Date());
        order.setRemark(remark);
        orderDAO.confirmReceipt(order);
    }

    @Override
    public void cancelOrder(Order order, String userId, String remark) {
        order.setStatus(EOrderStatus.CANCEL.getCode());
        order.setUpdater(userId);
        order.setUpdateDatetime(new Date());
        order.setRemark(remark);
        orderDAO.cancelOrder(order);
    }

    @Override
    public List<Order> queryOrderList(Order condition) {
        return orderDAO.selectList(condition);
    }

    @Override
    public Order getOrder(String code) {
        Order data = null;
        if (StringUtils.isNotBlank(code)) {
            Order condition = new Order();
            condition.setCode(code);
            data = orderDAO.select(condition);
            if (data == null) {
                throw new BizException("xn0000", "记录不存在");
            }
        }
        return data;
    }

    @Override
    public List<Order> queryOrderListByPayGroup(String payGroup) {
        Order condition = new Order();
        condition.setPayGroup(payGroup);
        return orderDAO.selectList(condition);
    }

    @Override
    public Order getLastOrder(String applyUser) {
        Order order = orderDAO.getLastOrder(applyUser);
        if (order == null) {
            throw new BizException("xn0000", "您还未下过订单,不能进行一键复购操作");
        }
        return order;
    }

}
