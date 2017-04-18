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
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cdkj.dzt.ao.IOrderAO;
import com.cdkj.dzt.bo.IAccountBO;
import com.cdkj.dzt.bo.IModelBO;
import com.cdkj.dzt.bo.IModelSpecsBO;
import com.cdkj.dzt.bo.IOrderBO;
import com.cdkj.dzt.bo.IProductBO;
import com.cdkj.dzt.bo.IProductSpecsBO;
import com.cdkj.dzt.bo.IUserBO;
import com.cdkj.dzt.bo.base.Paginable;
import com.cdkj.dzt.common.DateUtil;
import com.cdkj.dzt.core.OrderNoGenerater;
import com.cdkj.dzt.domain.Model;
import com.cdkj.dzt.domain.ModelSpecs;
import com.cdkj.dzt.domain.Order;
import com.cdkj.dzt.domain.Product;
import com.cdkj.dzt.domain.User;
import com.cdkj.dzt.dto.req.XN620200Req;
import com.cdkj.dzt.dto.res.BooleanRes;
import com.cdkj.dzt.enums.EBizType;
import com.cdkj.dzt.enums.EBoolean;
import com.cdkj.dzt.enums.ECurrency;
import com.cdkj.dzt.enums.EGeneratePrefix;
import com.cdkj.dzt.enums.EMeasureKey;
import com.cdkj.dzt.enums.EOrderStatus;
import com.cdkj.dzt.enums.EPayType;
import com.cdkj.dzt.enums.ESysUser;
import com.cdkj.dzt.enums.EUserKind;
import com.cdkj.dzt.exception.BizException;

/** 
 * @author: haiqingzheng 
 * @since: 2017年4月16日 下午2:55:53 
 * @history:
 */
@Service
public class OrderAOImpl implements IOrderAO {
    protected static final Logger logger = LoggerFactory
        .getLogger(OrderAOImpl.class);

    @Autowired
    private IOrderBO orderBO;

    @Autowired
    private IUserBO userBO;

    @Autowired
    private IModelBO modelBO;

    @Autowired
    private IModelSpecsBO modelSpecsBO;

    @Autowired
    private IAccountBO accountBO;

    @Autowired
    private IProductBO productBO;

    @Autowired
    private IProductSpecsBO productSpecsBO;

    @Override
    public String commitOrder(XN620200Req req) {
        Order order = new Order();
        // 判断是否有城市合伙人
        User user = userBO.getPartner(req.getLtProvince(), req.getLtCity(),
            req.getLtArea(), EUserKind.Partner);
        String userId = "0";
        if (null != user) {
            userId = user.getUserId();
        }
        Date now = new Date();
        String code = OrderNoGenerater.generateM(EGeneratePrefix.ORDER
            .getCode());

        order.setCode(code);
        // 城市合伙人用户ID
        order.setToUser(userId);
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
        // 判断是否有城市合伙人
        User user = userBO.getPartner(orderOld.getLtProvince(),
            orderOld.getLtCity(), orderOld.getLtArea(), EUserKind.Partner);
        String userId = orderOld.getToUser();
        if (null != user) {
            userId = user.getUserId();
        }
        Order order = new Order();
        Date now = new Date();
        String code = OrderNoGenerater.generateM(EGeneratePrefix.ORDER
            .getCode());
        order.setCode(code);
        // 城市合伙人用户ID
        order.setToUser(userId);
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
    @Transactional
    public Object payment(String orderCode, String payType) {
        Object result = null;
        if (EPayType.WEIXIN.getCode().equals(payType)) {
            result = toPayOrderWechat(orderCode);
        } else if (EPayType.YEZF.getCode().equals(payType)) {
            result = toPayOrderYE(orderCode);
        } else {
            throw new BizException("xn000000", "暂不支持该种支付方式");
        }
        return result;
    }

    @Transactional
    public Object toPayOrderYE(String orderCode) {
        Order order = orderBO.getOrder(orderCode);
        Long totalAmount = order.getAmount();
        String userId = order.getApplyUser();
        accountBO.doTransferAmountRemote(userId,
            ESysUser.SYS_USER_DZT.getCode(), ECurrency.CNY, totalAmount,
            EBizType.AJ_GW, "量体衬衫购买订单支付", "量体衬衫购买订单支付");
        orderBO.PaySuccess(order, null, totalAmount);
        return new BooleanRes(true);
    }

    @Transactional
    public Object toPayOrderWechat(String orderCode) {
        // 生成payGroup,并把订单进行支付。
        String payGroup = OrderNoGenerater.generateM(EGeneratePrefix.PAY_GROUP
            .getCode());
        // 计算该组订单总金额
        Order order = orderBO.getOrder(orderCode);
        Long totalAmount = order.getAmount();
        String userId = order.getApplyUser();
        User user = userBO.getRemoteUser(userId);
        if (!EOrderStatus.ASSIGN_PRICE.getCode().equals(order.getStatus())) {
            throw new BizException("xn000000", "订单不处于已定价状态");
        }
        orderBO.addPayGroup(order, payGroup, EPayType.WEIXIN.getCode());
        return accountBO.doWeiXinH5PayRemote(userId, user.getOpenId(),
            ESysUser.SYS_USER_DZT.getCode(), totalAmount, EBizType.AJ_GW,
            "量体衬衫购买订单支付", "量体衬衫购买订单支付", EPayType.WEIXIN.getCode());
    }

    @Override
    public void paySuccess(String payGroup, String payCode, Long amount) {
        List<Order> orderList = orderBO.queryOrderListByPayGroup(payGroup);
        if (CollectionUtils.isEmpty(orderList)) {
            throw new BizException("XN000000", "找不到对应的订单记录");
        }
        Order order = orderList.get(0);
        if (EOrderStatus.ASSIGN_PRICE.getCode().equals(order.getStatus())) {
            // 更新支付金额
            orderBO.PaySuccess(order, payCode, amount);
        } else {
            logger.info("订单号：" + order.getCode() + "已支付，重复回调");
        }
    }

    @Override
    public void inputInfor(String productCode, Map<String, String> map,
            String updater, String remark) {
        Product product = productBO.getProduct(productCode);
        Order order = orderBO.getOrder(product.getOrderCode());
        if (!EOrderStatus.PAY_YES.getCode().equals(order.getStatus())) {
            throw new BizException("xn000000", "订单尚未支付,不能录入数据");
        }

        productSpecsBO.removeProductSpecs(product.getCode());
        // 存地址
        orderBO.inputInfor(order, map.get(EMeasureKey.YJDZ.getCode()), updater,
            remark);

        productSpecsBO.inputInforValue(order, product, map);
        Map<String, ModelSpecs> modelSmap = modelSpecsBO.getMap();
        productSpecsBO.inputInforCode(order, product, map, modelSmap);
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

    @Override
    public void confirmReceipt(String orderCode, String updater, String remark) {
        Order order = orderBO.getOrder(orderCode);
        if (!EOrderStatus.SEND.getCode().equals(order.getStatus())) {
            throw new BizException("xn000000", "订单还未发货,不能确认收货");
        }
        orderBO.confirmReceipt(order, updater, remark);
    }

    @Override
    public void cancelOrder(String orderCode, String userId, String remark) {
        Order order = orderBO.getOrder(orderCode);
        if (EOrderStatus.PAY_YES.getCode().equals(order.getStatus())
                || EOrderStatus.TO_PRODU.getCode().equals(order.getStatus())
                || EOrderStatus.PRODU_DOING.getCode().equals(order.getStatus())
                || EOrderStatus.SEND.getCode().equals(order.getStatus())
                || EOrderStatus.RECEIVE.getCode().equals(order.getStatus())) {
            throw new BizException("xn000000", "订单不处于可取消订单状态,不能取消订单");
        }
        if (order.getApplyUser().equals(userId)
                || order.getLtUser().equals(userId)
                || order.getToUser().equals(userId)) {
            orderBO.cancelOrder(order, userId, remark);
        } else {
            throw new BizException("xn000000", "尊敬的用户,该订单不属于您，或你管辖的范围,不可取消订单");
        }
    }

    @Override
    public Paginable<Order> queryOrderPage(int start, int limit, Order condition) {
        return orderBO.getPaginable(start, limit, condition);
    }

    @Override
    public Order getRichOrder(String code) {
        Order order = orderBO.getOrder(code);
        List<Product> list = productBO.queryRichProductList(order.getCode());
        order.setProductList(list);
        return order;
    }

    @Override
    public List<Order> queryOrderlList(Order condition) {
        return orderBO.queryOrderList(condition);
    }

}
