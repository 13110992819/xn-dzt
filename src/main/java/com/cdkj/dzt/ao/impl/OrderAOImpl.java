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
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cdkj.dzt.ao.IOrderAO;
import com.cdkj.dzt.bo.IAccountBO;
import com.cdkj.dzt.bo.IModelBO;
import com.cdkj.dzt.bo.IOrderBO;
import com.cdkj.dzt.bo.IUserBO;
import com.cdkj.dzt.common.DateUtil;
import com.cdkj.dzt.core.OrderNoGenerater;
import com.cdkj.dzt.domain.Model;
import com.cdkj.dzt.domain.Order;
import com.cdkj.dzt.domain.User;
import com.cdkj.dzt.dto.req.XN620200Req;
import com.cdkj.dzt.enums.EBizType;
import com.cdkj.dzt.enums.EBoolean;
import com.cdkj.dzt.enums.EGeneratePrefix;
import com.cdkj.dzt.enums.EOrderStatus;
import com.cdkj.dzt.enums.EPayType;
import com.cdkj.dzt.enums.EUserKind;
import com.cdkj.dzt.exception.BizException;

/** 
 * @author: haiqingzheng 
 * @since: 2017年4月16日 下午2:55:53 
 * @history:
 */
@Service
public class OrderAOImpl implements IOrderAO {

    @Autowired
    private IOrderBO orderBO;

    @Autowired
    private IUserBO userBO;

    @Autowired
    private IModelBO modelBO;

    @Autowired
    private IAccountBO accountBO;

    @Override
    public String commitOrder(XN620200Req req) {
        Order order = new Order();
        User user = userBO.getPartner(req.getLtProvince(), req.getLtCity(),
            req.getLtArea(), EUserKind.Partner);
        Date now = new Date();
        String code = OrderNoGenerater.generateM(EGeneratePrefix.ORDER
            .getCode());

        order.setCode(code);
        // 城市合伙人用户ID
        order.setToUser(user.getUserId());
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
        order.setStatus(EOrderStatus.TO_MEASURE.getCode());
        order.setReceiver(req.getApplyName());
        order.setReAddress(req.getLtProvince() + req.getLtCity()
                + req.getLtArea() + req.getLtAddress());

        order.setReMobile(req.getApplyMobile());
        order.setUpdater(req.getUpdater());
        order.setUpdateDatetime(now);
        order.setRemark(req.getRemark());

        orderBO.saveOrder(order);
        return code;
    }

    @Override
    public String againApply(String applyUser) {
        List<Order> orderList = orderBO.queryOrderList(applyUser);
        if (CollectionUtils.isEmpty(orderList)) {
            throw new BizException("xn0000", "您还未下过订单,不能进行一键复购操作");
        }
        Order orderOld = orderList.get(orderList.size() - 1);
        Order order = new Order();
        Date now = new Date();
        String code = OrderNoGenerater.generateM(EGeneratePrefix.ORDER
            .getCode());
        order.setCode(code);
        // 城市合伙人用户ID
        order.setToUser(orderOld.getToUser());
        order.setApplyUser(orderOld.getApplyUser());
        order.setApplyName(orderOld.getApplyName());
        order.setApplyMobile(orderOld.getApplyMobile());

        order.setLtDatetime(now);
        order.setLtProvince(orderOld.getLtProvince());
        order.setLtCity(orderOld.getLtCity());
        order.setLtArea(orderOld.getLtArea());
        order.setLtAddress(orderOld.getLtAddress());

        order.setApplyNote("用户一键复购");
        order.setCreateDatetime(now);
        order.setStatus(EOrderStatus.TO_MEASURE.getCode());
        order.setReceiver(applyUser);

        order.setReAddress(orderOld.getReAddress());
        order.setReMobile(orderOld.getApplyMobile());
        order.setUpdater(applyUser);
        order.setUpdateDatetime(now);

        orderBO.saveOrder(order);
        return code;
    }

    @Override
    public void assignedOrder(String orderCode, String ltUser, String ltName,
            String updater, String remark) {
        Order order = orderBO.getOrder(orderCode);
        if (!EOrderStatus.TO_MEASURE.getCode().equals(order.getStatus())) {
            throw new BizException("xn0000", "订单不处于待量体状态，不可以分配订单");
        }
        orderBO.assignedOrder(order, ltUser, ltName, updater, remark);
    }

    @Override
    public void confirmPrice(String orderCode, String modelCode,
            Integer quantity, String updater, String remark) {
        Order order = orderBO.getOrder(orderCode);
        Model model = modelBO.getModel(modelCode);
        if (!EOrderStatus.TO_MEASURE.getCode().equals(order.getStatus())) {
            throw new BizException("xn0000", "订单不处于待量体状态，不可以分配订单");
        }
        Long amount = model.getPrice() * quantity;
        orderBO.confirmPrice(order, amount, updater, remark);
    }

    @Override
    public Object payment(String orderCode, String payType) {
        Order order = orderBO.getOrder(orderCode);
        Object result = null;
        if (EPayType.WEIXIN.getCode().equals(payType)) {
            result = toPayOrderWechat(orderCode, payType);
        }
        return result;
    }

    private Object toPayOrderWechat(String orderCode, String payType) {
        // 生成payGroup,并把订单进行支付。
        String payGroup = OrderNoGenerater.generateM(EGeneratePrefix.ORDER_PAY
            .getCode());
        // 计算该组订单总金额
        Long totalAmount = 0L;
        Order order = orderBO.getOrder(orderCode);
        String userId = order.getApplyUser();
        if (!EOrderStatus.ASSIGN_PRICE.getCode().equals(order.getStatus())) {
            throw new BizException("xn000000", "订单不处于已定价状态");
        }
        orderBO.addPayGroup(order, payGroup, payType);
        return accountBO.doWeiXinPayRemote(userId, userId, totalAmount,
            EBizType.AJ_GW, "商品支付", "商品支付", payGroup);
    }

    @Override
    public void ltSubmit(String orderCode, String updater) {
        Order order = orderBO.getOrder(orderCode);
        if (!EOrderStatus.PAY_YES.getCode().equals(order.getStatus())) {
            throw new BizException("xn000000", "订单尚未支付,不能提交审核");
        }
        if (!order.getLtUser().equals(updater)) {
            throw new BizException("xn000000", "您不是该笔订单的量体师,不能提交订单");
        }
        orderBO.ltSubmit(order, updater);
    }

    @Override
    public void approveOrder(String orderCode, String result, String updater,
            String remark) {
        Order order = orderBO.getOrder(orderCode);
        if (!EOrderStatus.TO_APPROVE.getCode().equals(order.getStatus())) {
            throw new BizException("xn000000", "订单不处于待审核状态,不能审核");
        }
        EOrderStatus status = EOrderStatus.PAY_YES;
        if (EBoolean.YES.getCode().equals(result)) {
            status = EOrderStatus.TO_PRODU;
        }
        orderBO.approveOrder(order, status, updater, remark);
    }

    @Override
    public void submitProudect(String orderCode, String updater, String remark) {
        Order order = orderBO.getOrder(orderCode);
        if (!EOrderStatus.TO_PRODU.getCode().equals(order.getStatus())) {
            throw new BizException("xn000000", "订单不处于待生产,不能提交生产");
        }
        orderBO.submitProudect(order, updater, remark);
    }

    @Override
    public void sendGoods(String orderCode, String logisticsCompany,
            String logisticsCode, String deliverer, String deliveryDatetime,
            String pdf, String updater, String remark) {
        Order order = orderBO.getOrder(orderCode);
        if (!EOrderStatus.PRODU_DOING.getCode().equals(order.getStatus())) {
            throw new BizException("xn000000", "订单不处于生产中,不能发货");
        }
        Date sendTime = DateUtil.strToDate(deliveryDatetime,
            DateUtil.FRONT_DATE_FORMAT_STRING);
        orderBO.sendGoods(order, logisticsCompany, logisticsCode, deliverer,
            sendTime, pdf, updater, remark);
    }
}
