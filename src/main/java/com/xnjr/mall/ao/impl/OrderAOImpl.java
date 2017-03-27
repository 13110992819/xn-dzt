/**
 * @Title OrderAOImpl.java 
 * @Package com.xnjr.mall.ao.impl 
 * @Description 
 * @author xieyj  
 * @date 2016年5月25日 上午9:37:32 
 * @version V1.0   
 */
package com.xnjr.mall.ao.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.xnjr.mall.ao.IOrderAO;
import com.xnjr.mall.bo.IAccountBO;
import com.xnjr.mall.bo.ICartBO;
import com.xnjr.mall.bo.IOrderBO;
import com.xnjr.mall.bo.IProductBO;
import com.xnjr.mall.bo.IProductOrderBO;
import com.xnjr.mall.bo.ISmsOutBO;
import com.xnjr.mall.bo.IUserBO;
import com.xnjr.mall.bo.base.Paginable;
import com.xnjr.mall.common.DateUtil;
import com.xnjr.mall.core.StringValidater;
import com.xnjr.mall.domain.Cart;
import com.xnjr.mall.domain.Order;
import com.xnjr.mall.domain.ProductOrder;
import com.xnjr.mall.dto.req.XN808050Req;
import com.xnjr.mall.dto.req.XN808051Req;
import com.xnjr.mall.dto.req.XN808054Req;
import com.xnjr.mall.dto.res.BooleanRes;
import com.xnjr.mall.enums.EBizType;
import com.xnjr.mall.enums.ECurrency;
import com.xnjr.mall.enums.EOrderStatus;
import com.xnjr.mall.enums.EPayType;
import com.xnjr.mall.enums.ESystemCode;
import com.xnjr.mall.exception.BizException;

/** 
 * @author: xieyj 
 * @since: 2016年5月25日 上午9:37:32 
 * @history:
 */
@Service
public class OrderAOImpl implements IOrderAO {
    protected static final Logger logger = LoggerFactory
        .getLogger(OrderAOImpl.class);

    @Autowired
    private IOrderBO orderBO;

    @Autowired
    private IProductOrderBO productOrderBO;

    @Autowired
    private ICartBO cartBO;

    @Autowired
    private IUserBO userBO;

    @Autowired
    private IAccountBO accountBO;

    @Autowired
    private IProductBO productBO;

    @Autowired
    private ISmsOutBO smsOutBO;

    @Override
    @Transactional
    public List<String> commitCartOrderZH(XN808051Req req) {
        List<String> result = new ArrayList<String>();
        // 按公司编号进行拆单, 遍历获取公司编号列表
        List<String> cartCodeList = req.getCartCodeList();
        Map<String, List<Cart>> companyList = cartBO.getCartMap(cartCodeList);
        // 遍历产品编号
        for (String companyCode : companyList.keySet()) {
            String orderCode = orderBO.saveOrder(companyList.get(companyCode),
                req.getPojo(), null);
            result.add(orderCode);
        }
        // @TODO清空购物车
        // 删除购物车选中记录
        for (String cartCode : cartCodeList) {
            cartBO.removeCart(cartCode);
        }
        return result;
    }

    @Override
    @Transactional
    public String commitCartOrderCG(XN808051Req req) {
        List<String> cartCodeList = req.getCartCodeList();
        List<Cart> cartList = cartBO.queryCartList(cartCodeList);
        String orderCode = orderBO.saveOrder(cartList, req.getPojo(),
            req.getToUser());
        // 删除购物车选中记录
        for (String cartCode : cartCodeList) {
            cartBO.removeCart(cartCode);
        }
        return orderCode;
    }

    @Override
    @Transactional
    public String commitOrder(XN808050Req req) {
        // 立即下单，构造成购物车单个产品下单
        Cart cart = new Cart();
        cart.setProductCode(req.getProductCode());
        cart.setQuantity(StringValidater.toInteger(req.getQuantity()));
        cart.setUserId(req.getPojo().getApplyUser());
        List<Cart> cartList = new ArrayList<Cart>();
        cartList.add(cart);
        return orderBO.saveOrder(cartList, req.getPojo(), req.getToUser());
    }

    @Override
    public Object toPayOrder(List<String> codeList, String payType) {
        // 暂时只实现单笔订单支付
        String code = codeList.get(0);
        Order order = orderBO.getOrder(code);
        if (!EOrderStatus.TO_PAY.getCode().equals(order.getStatus())) {
            throw new BizException("xn000000", "订单不处于待支付状态");
        }
        if (ESystemCode.Caigo.getCode().equals(order.getSystemCode())) {
            return toPayOrderCG(order, payType);
        } else if (ESystemCode.ZHPAY.getCode().equals(order.getSystemCode())) {
            return toPayOrderZH(order, payType);
        } else {
            throw new BizException("xn000000", "系统编号不能识别");
        }
    }

    @Transactional
    private Object toPayOrderCG(Order order, String payType) {
        Long cgbAmount = order.getAmount2(); // 菜狗币
        Long jfAmount = order.getAmount3(); // 积分
        String systemCode = order.getSystemCode();
        String fromUserId = order.getApplyUser();
        // 余额支付(菜狗币+积分)
        if (EPayType.YEZP.getCode().equals(payType)) {
            // 更新订单支付金额
            orderBO.refreshPaySuccess(order, 0L, cgbAmount, jfAmount);
            // 扣除金额
            if (StringUtils.isNotBlank(order.getToUser())) {// 付给加盟店
                accountBO.doCgbJfPay(fromUserId, order.getToUser(), cgbAmount,
                    jfAmount, EBizType.AJ_GW);
            } else {// 付钱给平台
                String systemUserId = userBO.getSystemUser(systemCode);
                accountBO.doCgbJfPay(fromUserId, systemUserId, cgbAmount,
                    jfAmount, EBizType.AJ_GW);
            }
        }
        return new BooleanRes(true);
    }

    @Transactional
    private Object toPayOrderZH(Order order, String payType) {
        // todo 正汇支付待实现
        return null;
    }

    /** 
     * @see com.xnjr.mall.ao.IOrderAO#userCancel(java.lang.String, java.lang.String)
     */
    @Override
    public void userCancel(String code, String userId, String remark) {
        Order data = orderBO.getOrder(code);
        if (!userId.equals(data.getApplyUser())) {
            throw new BizException("xn0000", "订单申请人和取消操作用户不符");
        }
        if (!EOrderStatus.TO_PAY.getCode().equals(data.getStatus())) {
            throw new BizException("xn0000", "订单状态不是待支付状态，不能进行取消操作");
        }
        orderBO.userCancel(code, userId, remark);
    }

    /**
     * @see com.xnjr.mall.ao.IOrderAO#userCancel(java.lang.String, java.lang.String, java.lang.String)
     */
    @Override
    public void platCancel(List<String> codeList, String updater, String remark) {
        for (String code : codeList) {
            platCancelSingle(code, updater, remark);
        }
    }

    @Transactional
    private void platCancelSingle(String code, String updater, String remark) {
        Order order = orderBO.getOrder(code);
        if (!EOrderStatus.PAY_YES.getCode().equals(order.getStatus())
                && !EOrderStatus.SEND.getCode().equals(order.getStatus())) {
            throw new BizException("xn0000", "该订单支付成功或已发货状态，无法操作");
        }
        String status = null;
        if (EOrderStatus.PAY_YES.getCode().equals(order.getStatus())) {
            status = EOrderStatus.SHYC.getCode();
        } else if (EOrderStatus.SEND.getCode().equals(order.getStatus())) {
            status = EOrderStatus.KDYC.getCode();
        }

        // 退款
        if (ESystemCode.Caigo.equals(order.getSystemCode())) {
            doBackAmountCG(order);
        } else if (ESystemCode.ZHPAY.equals(order.getSystemCode())) {
            doBackAmountZH(order);
        } else {
            throw new BizException("xn000000", "系统编号不能识别");
        }

        // 更新订单信息
        orderBO.platCancel(code, updater, remark, status);

        // 发送短信
        String userId = order.getApplyUser();
        smsOutBO.sentContent(userId, userId, "尊敬的用户，您的订单[" + order.getCode()
                + "]已取消,退款原因:[" + remark + "],请及时查看退款。");

    }

    private void doBackAmountCG(Order order) {
        Long cgbAmount = order.getPayAmount2(); // 菜狗币
        Long jfAmount = order.getPayAmount3(); // 积分
        if (cgbAmount > 0) {
            accountBO.doTransferAmountRemote(order.getToUser(),
                order.getApplyUser(), ECurrency.CGB, cgbAmount,
                EBizType.AJ_GWTK, EBizType.AJ_GWTK.getValue(),
                EBizType.AJ_GWTK.getValue());
        }
        if (jfAmount > 0) {
            accountBO.doTransferAmountRemote(order.getToUser(),
                order.getApplyUser(), ECurrency.XNB, jfAmount,
                EBizType.AJ_GWTK, EBizType.AJ_GWTK.getValue(),
                EBizType.AJ_GWTK.getValue());
        }
    }

    private void doBackAmountZH(Order order) {
        // todo 待实现
        // public void doOrderAmountBackBySysetm(String systemCode, String
        // toUserId,
        // Long gwbPayAmount, Long qbbPayAmount, Long cnyPayAmount,
        // EBizType bizType, String remark) {
        // Map<String, String> rateMap = sysConfigBO.getConfigsMap(systemCode);
        // Double gxjl2cnyRate = Double
        // .valueOf(rateMap.get(SysConstants.GXJL2CNY));
        // // 扣除购物币
        // accountBO.doTransferAmountRemote(ESysUser.SYS_USER_ZHPAY.getCode(),
        // toUserId, ECurrency.GWB, gwbPayAmount, bizType, bizType.getValue()
        // + remark, bizType.getValue() + remark);
        // // 扣除钱包币
        // accountBO.doTransferAmountRemote(ESysUser.SYS_USER_ZHPAY.getCode(),
        // toUserId, ECurrency.QBB, qbbPayAmount, bizType, bizType.getValue()
        // + remark, bizType.getValue() + remark);
        // // 退人民币
        // accountBO.doTransferAmountRemote(ESysUser.SYS_USER_ZHPAY.getCode(),
        // toUserId, ECurrency.GXJL,
        // Double.valueOf(gxjl2cnyRate * cnyPayAmount).longValue(), bizType,
        // bizType.getValue() + remark, bizType.getValue() + remark);
        // }
    }

    /** 
     * @see com.xnjr.mall.ao.IOrderAO#queryOrderPage(int, int, com.xnjr.mall.domain.Order)
     */
    @Override
    public Paginable<Order> queryOrderPage(int start, int limit, Order condition) {
        Paginable<Order> page = orderBO.getPaginable(start, limit, condition);
        if (page != null && CollectionUtils.isNotEmpty(page.getList())) {
            for (Order order : page.getList()) {
                ProductOrder imCondition = new ProductOrder();
                imCondition.setOrderCode(order.getCode());
                List<ProductOrder> productOrderList = productOrderBO
                    .queryProductOrderList(imCondition);
                order.setProductOrderList(productOrderList);
            }
        }
        return page;
    }

    /** 
     * @see com.xnjr.mall.ao.IOrderAO#queryOrderList(com.xnjr.mall.domain.Order)
     */
    @Override
    public List<Order> queryOrderList(Order condition) {
        List<Order> list = orderBO.queryOrderList(condition);
        if (CollectionUtils.isNotEmpty(list)) {
            for (Order order : list) {
                ProductOrder imCondition = new ProductOrder();
                imCondition.setOrderCode(order.getCode());
                List<ProductOrder> productOrderList = productOrderBO
                    .queryProductOrderList(imCondition);
                order.setProductOrderList(productOrderList);
            }
        }
        return list;
    }

    /** 
     * @see com.xnjr.mall.ao.IOrderAO#getOrder(java.lang.String)
     */
    @Override
    public Order getOrder(String code) {
        Order order = orderBO.getOrder(code);
        ProductOrder imCondition = new ProductOrder();
        imCondition.setOrderCode(order.getCode());
        List<ProductOrder> productOrderList = productOrderBO
            .queryProductOrderList(imCondition);
        order.setProductOrderList(productOrderList);
        return order;
    }

    @Override
    public void deliverLogistics(XN808054Req req) {
        Order order = orderBO.getOrder(req.getCode());
        if (!EOrderStatus.PAY_YES.getCode().equalsIgnoreCase(order.getStatus())) {
            throw new BizException("xn000000", "订单不是已支付状态，无法操作");
        }
        orderBO.deliverLogistics(req.getCode(), req.getLogisticsCompany(),
            req.getLogisticsCode(), req.getDeliverer(),
            req.getDeliveryDatetime(), req.getPdf(), req.getUpdater(),
            req.getRemark());

        // 发送短信
        String userId = order.getApplyUser();
        String notice = "";
        if (order.getProductOrderList().size() > 1) {
            notice = "尊敬的用户，您的订单[" + order.getCode() + "]中的商品["
                    + order.getProductOrderList().get(0).getProductName()
                    + "等]已发货，请注意查收。";
        } else {
            notice = "尊敬的用户，您的订单[" + order.getCode() + "]中的商品["
                    + order.getProductOrderList().get(0).getProductName()
                    + "]已发货，请注意查收。";
        }
        smsOutBO.sentContent(userId, userId, notice);
    }

    @Override
    public void deliverXianchang(String code, String updater, String remark) {
        Order order = orderBO.getOrder(code);
        if (!EOrderStatus.PAY_YES.getCode().equalsIgnoreCase(order.getStatus())) {
            throw new BizException("xn000000", "该订单不是已支付状态，无法操作");
        }
        orderBO.deliverXianchang(code, updater, remark);
    }

    @Override
    public void confirm(String code, String updater, String remark) {
        Order order = orderBO.getOrder(code);
        if (!EOrderStatus.SEND.getCode().equalsIgnoreCase(order.getStatus())) {
            throw new BizException("xn000000", "订单不是已发货状态，无法操作");
        }

        if (ESystemCode.Caigo.equals(order.getSystemCode())) {
            doConfirmCG(order, updater, remark);
        } else if (ESystemCode.ZHPAY.equals(order.getSystemCode())) {
            doConfirmZH(order, updater, remark);
        } else {
            throw new BizException("xn000000", "系统编号不能识别");
        }

    }

    private void doConfirmCG(Order order, String updater, String remark) {
        orderBO.confirm(order, updater, remark);
    }

    private void doConfirmZH(Order order, String updater, String remark) {
        // orderBO.approveOrder(code, updater, EOrderStatus.RECEIVE.getCode(),
        // remark);
        // Long cnyAmount = order.getPayAmount1();
        // String systemCode = order.getSystemCode();
        // // 打款给商家分润账户,将人民币转出分润
        // Map<String, String> rateMap = sysConfigBO.getConfigsMap(systemCode,
        // null);
        // Double fr2cny = Double.valueOf(rateMap.get(SysConstants.FR2CNY));
        // Long frAmount = Double.valueOf(fr2cny * cnyAmount).longValue();
        // String frAmountStr = CalculationUtil.divi(frAmount);
        // accountBO.doTransferAmountByUser(order.getSystemCode(),
        // ESysUser.SYS_USER.getCode(), order.getCompanyCode(),
        // ECurrency.FRB.getCode(), frAmount, EBizType.AJ_QRSH.getCode(),
        // "用户确认收货，系统需支付人民币:" + CalculationUtil.divi(cnyAmount) + "元，商户收到分润:"
        // + frAmountStr);
        // smsOutBO
        // .sentContent(order.getCompanyCode(), order.getCompanyCode(),
        // "尊敬的商户，订单号[" + code + "]的用户已确认收货,本次收入分润：" + frAmountStr
        // + ",请注意查收!");
    }

    /**
     * @see com.xnjr.mall.ao.IOrderAO#paySuccess(java.lang.String)
     */
    @Override
    @Transactional
    public void paySuccess(String payCode) {
        // Order condition = new Order();
        // condition.setPayCode(payCode);
        // List<Order> result = orderBO.queryOrderList(condition);
        // if (CollectionUtils.isEmpty(result)) {
        // throw new BizException("XN000000", "找不到对应的消费记录");
        // }
        // String systemCode = null;
        // String applyUser = null;
        // Long gwAmount = 0L; // 购物币
        // Long qbAmount = 0L; // 钱包币
        // for (Order order : result) {
        // if (EOrderStatus.TO_PAY.getCode().equals(order.getStatus())) {
        // // 更新支付金额
        // orderBO.refreshOrderPayAmount(order.getCode(),
        // order.getAmount1(), order.getAmount2(), order.getAmount3());
        // } else {
        // logger.info("订单号：" + order.getCode() + "已支付，重复回调");
        // }
        // gwAmount += order.getAmount2(); // 购物币
        // qbAmount += order.getAmount3(); // 钱包币
        // if (StringUtils.isBlank(applyUser)) {
        // applyUser = order.getApplyUser();
        // systemCode = order.getSystemCode();
        // }
        // }
        // // 扣除金额(购物币和钱包币)
        // accountBO.doGWBQBBPay(systemCode, applyUser,
        // ESysUser.SYS_USER.getCode(), gwAmount, qbAmount, EBizType.AJ_GW);
    }

    /** 
     * @see com.xnjr.mall.ao.IOrderAO#doChangeOrderStatusDaily()
     */
    @Override
    public void doChangeOrderStatusDaily() {
        doChangeNoPayOrder();
    }

    private void doChangeNoPayOrder() {
        logger.info("***************开始扫描未支付订单***************");
        Order condition = new Order();
        condition.setStatus(EOrderStatus.TO_PAY.getCode());
        // 前3天还未支付的订单
        condition.setApplyDatetimeEnd(DateUtil.getRelativeDate(new Date(),
            -(60 * 60 * 24 * 3 + 1)));
        List<Order> orderList = orderBO.queryOrderList(condition);
        if (CollectionUtils.isNotEmpty(orderList)) {
            for (Order order : orderList) {
                orderBO.userCancel(order.getCode(), "system", "超时未支付，系统自动取消");

            }
        }
        logger.info("***************结束扫描未支付订单***************");
    }

}
