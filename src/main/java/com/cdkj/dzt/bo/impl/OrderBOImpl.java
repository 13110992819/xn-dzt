package com.cdkj.dzt.bo.impl;

import java.util.Date;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.cdkj.dzt.bo.IOrderBO;
import com.cdkj.dzt.bo.IOrderSizeDataBO;
import com.cdkj.dzt.bo.IProductBO;
import com.cdkj.dzt.bo.base.PaginableBOImpl;
import com.cdkj.dzt.dao.IOrderDAO;
import com.cdkj.dzt.domain.Model;
import com.cdkj.dzt.domain.Order;
import com.cdkj.dzt.domain.OrderSizeData;
import com.cdkj.dzt.domain.Product;
import com.cdkj.dzt.enums.EOrderStatus;
import com.cdkj.dzt.exception.BizException;

@Component
public class OrderBOImpl extends PaginableBOImpl<Order> implements IOrderBO {
    @Autowired
    private IProductBO productBO;

    @Autowired
    private IOrderSizeDataBO orderSizeDataBO;

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
        if (StringUtils.isNotBlank(remark)) {
            order.setRemark(remark);
        } else {
            order.setRemark(order.getRemark());
        }
        orderDAO.distributeOrder(order);
    }

    @Override
    public void confirmPrice(Order order, Model model, Long amount,
            Long originalPrice, String address, String updater, String remark) {
        order.setType(model.getType());
        order.setAmount(amount);
        order.setOriginalAmount(originalPrice);
        order.setReAddress(address);
        order.setUpdater(updater);
        order.setUpdateDatetime(new Date());
        if (StringUtils.isNotBlank(remark)) {
            order.setRemark(remark);
        } else {
            order.setRemark(order.getRemark());
        }
        order.setStatus(EOrderStatus.ASSIGN_PRICE.getCode());
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
        if (StringUtils.isNotBlank(remark)) {
            order.setRemark(remark);
        } else {
            order.setRemark(order.getRemark());
        }
        orderDAO.inputInfor(order);
    }

    @Override
    public void ltSubmit(Order order, String updater, String remark) {
        order.setStatus(EOrderStatus.TO_APPROVE.getCode());
        order.setUpdater(updater);
        order.setUpdateDatetime(new Date());
        order.setRemark(remark);
        orderDAO.ltSubmit(order);
    }

    @Override
    public void approveOrder(Order order, EOrderStatus status, String updater,
            String remark) {
        order.setStatus(status.getCode());
        order.setUpdater(updater);
        order.setUpdateDatetime(new Date());
        if (StringUtils.isNotBlank(remark)) {
            order.setRemark(remark);
        } else {
            order.setRemark(order.getRemark());
        }
        orderDAO.approveOrder(order);
    }

    @Override
    public void submitProudect(Order order, String updater, String remark) {
        order.setStatus(EOrderStatus.PRODU_DOING.getCode());
        order.setUpdater(updater);
        order.setUpdateDatetime(new Date());
        if (StringUtils.isNotBlank(remark)) {
            order.setRemark(remark);
        } else {
            order.setRemark(order.getRemark());
        }
        orderDAO.submitProudect(order);
    }

    @Override
    public void sendGoods(Order order, String deliverer, Date deliveryDatetime,
            String logisticsCompany, String logisticsCode, String pdf,
            String updater, String remark) {
        order.setStatus(EOrderStatus.SEND.getCode());

        order.setDeliverer(deliverer);
        order.setDeliveryDatetime(deliveryDatetime);
        order.setLogisticsCompany(logisticsCompany);
        order.setLogisticsCode(logisticsCode);
        order.setPdf(pdf);

        order.setUpdater(updater);
        order.setUpdateDatetime(new Date());
        if (StringUtils.isNotBlank(remark)) {
            order.setRemark(remark);
        } else {
            order.setRemark(order.getRemark());
        }
        orderDAO.sendGoods(order);
    }

    @Override
    public void confirmReceipt(Order order, String updater, String remark) {
        order.setStatus(EOrderStatus.RECEIVE.getCode());
        order.setUpdater(updater);
        order.setUpdateDatetime(new Date());
        if (StringUtils.isNotBlank(remark)) {
            order.setRemark(remark);
        } else {
            order.setRemark(order.getRemark());
        }
        orderDAO.confirmReceipt(order);
    }

    @Override
    public void cancelOrder(Order order, String updater, String remark) {
        order.setStatus(EOrderStatus.CANCEL.getCode());
        order.setUpdater(updater);
        order.setUpdateDatetime(new Date());
        if (StringUtils.isNotBlank(remark)) {
            order.setRemark(remark);
        } else {
            order.setRemark(order.getRemark());
        }
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
        if (order == null
                || EOrderStatus.TO_MEASURE.getCode().equals(order.getStatus())) {
            throw new BizException("xn0000", "您还未成功下过订单,不能进行一键复购操作");
        }
        return order;
    }

    @Override
    public Order getIsLastOrder(String applyUser) {
        return orderDAO.getLastOrder(applyUser);
    }

    @Override
    public Order getRichOrder(String code) {
        Order order = this.getOrder(code);
        List<Product> list = productBO.queryRichProductList(order.getCode());
        Product product = null;
        if (CollectionUtils.isNotEmpty(list)) {
            product = list.get(0);
        }
        order.setProduct(product);
        List<OrderSizeData> orderSizeData = orderSizeDataBO
            .queryOrderSizeDataList(code);
        order.setOrderSizeData(orderSizeData);
        return order;
    }

    @Override
    public Order getCheckOrder(String code) {
        Order order = this.getOrder(code);
        List<Product> list = productBO.queryRichProductList(order.getCode());
        Product product = null;
        if (CollectionUtils.isNotEmpty(list)) {
            product = list.get(0);
        }
        order.setProduct(product);
        return order;
    }

    @Override
    public Long getTotalCount(String userId, EOrderStatus status) {
        Order order = new Order();
        order.setApplyUser(userId);
        order.setStatus(status.getCode());
        return orderDAO.selectTotalCount(order);
    }

    @Override
    public void comment(Order order, String commenter) {
        order.setStatus(EOrderStatus.COMMENT.getCode());
        order.setUpdater(commenter);
        order.setUpdateDatetime(new Date());
        orderDAO.comment(order);
    }

    @Override
    public void isFiled(Order order, String updater, String remark) {
        order.setStatus(EOrderStatus.FILED.getCode());
        order.setUpdater(updater);
        order.setUpdateDatetime(new Date());
        order.setRemark(remark);
        orderDAO.isFiled(order);
    }

    @Override
    public List<Order> getGroupTotalCount(Date createDatetimeStart,
            Date createDatetimeEnd) {
        Order condition = new Order();
        condition.setCreateDatetimeStart(createDatetimeStart);
        condition.setCreateDatetimeEnd(createDatetimeEnd);
        return orderDAO.selectGroupList(condition);
    }

}
