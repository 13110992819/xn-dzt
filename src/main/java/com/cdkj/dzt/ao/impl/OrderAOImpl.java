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
import org.apache.commons.lang.StringUtils;
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
    public String applyOrder(XN620200Req req) {
        // 获取城市合伙人
        User user = userBO.getPartner(req.getLtProvince(), req.getLtCity(),
            req.getLtArea(), EUserKind.Partner);
        String userId = "0";
        if (null != user) {
            userId = user.getUserId();
        }
        // 开始组装order
        Order order = new Order();
        Date now = new Date();
        String code = OrderNoGenerater.generateM(EGeneratePrefix.ORDER
            .getCode());
        order.setCode(code);
        order.setToUser(userId);
        order.setApplyUser(req.getApplyUser());
        order.setApplyName(req.getApplyName());
        order.setApplyMobile(req.getApplyMobile());

        order.setLtDatetime(DateUtil.strToDate(req.getLtDatetime(),
            DateUtil.FRONT_DATE_FORMAT_STRING));
        order.setLtProvince(req.getLtProvince());
        order.setLtCity(req.getLtCity());
        order.setLtArea(req.getLtArea());
        order.setLtAddress(req.getLtAddress());

        order.setApplyNote(req.getApplyNote());
        order.setCreateDatetime(now);
        order.setStatus(EOrderStatus.TO_MEASURE.getCode());

        order.setReceiver(req.getApplyName());
        order.setReMobile(req.getApplyMobile());
        // order.setReAddress(req.getLtProvince() + req.getLtCity()
        // + req.getLtArea() + req.getLtAddress());

        order.setUpdater(req.getUpdater());
        order.setUpdateDatetime(now);
        order.setRemark(req.getRemark());

        orderBO.applyOrder(order);
        return code;
    }

    @Override
    public String applyOrder(String applyUser) {
        // 获取最近订单
        Order lastOrder = orderBO.getLastOrder(applyUser);
        // 开始组装order
        Order order = new Order();
        Date now = new Date();
        String code = OrderNoGenerater.generateM(EGeneratePrefix.ORDER
            .getCode());
        order.setCode(code);
        order.setToUser(lastOrder.getToUser());
        order.setApplyUser(lastOrder.getApplyUser());
        order.setApplyName(lastOrder.getApplyName());
        order.setApplyMobile(lastOrder.getApplyMobile());

        order.setLtDatetime(now);
        order.setLtProvince(lastOrder.getLtProvince());
        order.setLtCity(lastOrder.getLtCity());
        order.setLtArea(lastOrder.getLtArea());
        order.setLtAddress(lastOrder.getLtAddress());

        order.setApplyNote("根据<" + lastOrder.getCode() + ">订单一键复购形成");
        order.setCreateDatetime(now);
        order.setStatus(EOrderStatus.TO_MEASURE.getCode());

        order.setReceiver(lastOrder.getReceiver());
        order.setReMobile(lastOrder.getApplyMobile());
        order.setReAddress(lastOrder.getReAddress());

        order.setUpdater(applyUser);
        order.setUpdateDatetime(now);
        order.setRemark("根据<" + lastOrder.getCode() + ">订单一键复购形成");

        orderBO.applyOrder(order);
        return code;
    }

    @Override
    public void distributeOrder(String orderCode, String ltUser,
            String updater, String remark) {
        Order order = orderBO.getOrder(orderCode);
        if (!EOrderStatus.TO_MEASURE.getCode().equals(order.getStatus())) {
            throw new BizException("xn0000", "订单不处于待量体状态，不可以分配订单");
        }
        User user = userBO.getRemoteUser(ltUser);
        orderBO.distributeOrder(order, ltUser, user.getRealName(), updater,
            remark);
    }

    @Override
    @Transactional
    public void confirmPrice(String orderCode, String modelCode,
            Integer quantity, String updater, String remark) {
        Order order = orderBO.getOrder(orderCode);
        if (!EOrderStatus.TO_MEASURE.getCode().equals(order.getStatus())) {
            throw new BizException("xn0000", "订单不处于待量体状态，不可以定价");
        }
        // 落地成衣
        Model model = modelBO.getModel(modelCode);
        productBO.saveProduct(order, model, quantity);
        // 更新订单
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

    private Object toPayOrderYE(String orderCode) {
        Order order = orderBO.getOrder(orderCode);
        Long totalAmount = order.getAmount();
        String userId = order.getApplyUser();
        accountBO.doTransferAmountRemote(userId,
            ESysUser.SYS_USER_DZT.getCode(), ECurrency.CNY, totalAmount,
            EBizType.AJ_GW, "量体衬衫购买订单支付", "量体衬衫购买订单支付");
        orderBO.PaySuccess(order, null, totalAmount);
        return new BooleanRes(true);
    }

    private Object toPayOrderWechat(String orderCode) {
        // 生成payGroup,并把订单进行支付。
        String payGroup = OrderNoGenerater.generateM(EGeneratePrefix.ORDER
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
    @Transactional
    public void inputInfor(String orderCode, Map<String, String> map,
            String updater, String remark) {
        Order order = orderBO.getOrder(orderCode);
        if (!EOrderStatus.PAY_YES.getCode().equals(order.getStatus())) {
            throw new BizException("xn000000", "订单尚未支付,不能录入数据");
        }
        Product product = productBO.getProductByOrderCode(orderCode);
        // 更新订单
        orderBO.inputInfor(order, map.get(EMeasureKey.YJDZ.getCode()), updater,
            remark);
        // 落地量体数据
        productSpecsBO.removeProductSpecs(product.getCode());
        productSpecsBO.inputInforValue(order, product, map);
        Map<String, ModelSpecs> modelSmap = modelSpecsBO.getMap();
        productSpecsBO.inputInforCode(order, product, map, modelSmap);
    }

    @Override
    public void ltSubmit(String orderCode, String updater) {
        Order order = orderBO.getRichOrder(orderCode);
        if (!EOrderStatus.PAY_YES.getCode().equals(order.getStatus())) {
            throw new BizException("xn000000", "订单不是已支付状态,不能提交审核");
        }
        // 确保所有规格已经填充完毕
        orderBO.checkInfoFull(order);
        orderBO.ltSubmit(order, updater);
    }

    @Override
    public void approveOrder(String orderCode, String result, String updater,
            String remark) {
        Order order = orderBO.getOrder(orderCode);
        if (!EOrderStatus.TO_APPROVE.getCode().equals(order.getStatus())) {
            throw new BizException("xn000000", "订单不处于待审核状态,不能审核");
        }
        EOrderStatus status = null;
        if (EBoolean.YES.getCode().equals(result)) {
            status = EOrderStatus.TO_PRODU;
        } else {
            status = EOrderStatus.PAY_YES;// 审核不通过，回退到已支付待提交
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
    public void sendGoods(String orderCode, String deliverer,
            String deliveryDatetime, String logisticsCompany,
            String logisticsCode, String pdf, String updater, String remark) {
        Order order = orderBO.getOrder(orderCode);
        if (!EOrderStatus.PRODU_DOING.getCode().equals(order.getStatus())) {
            throw new BizException("xn000000", "订单不处于生产中,不能发货");
        }
        Date sendTime = DateUtil.strToDate(deliveryDatetime,
            DateUtil.FRONT_DATE_FORMAT_STRING);
        orderBO.sendGoods(order, deliverer, sendTime, logisticsCompany,
            logisticsCode, pdf, updater, remark);
    }

    @Override
    @Transactional
    public void confirmReceipt(String orderCode, String updater, String remark) {
        Order order = orderBO.getOrder(orderCode);
        if (!EOrderStatus.SEND.getCode().equals(order.getStatus())) {
            throw new BizException("xn000000", "订单还未发货,不能确认收货");
        }
        // 更改订单状态
        orderBO.confirmReceipt(order, updater, remark);
        // 合伙人和量体师进行分成
        doFenCheng(order);
    }

    private void doFenCheng(Order order) {
        // 合伙人分成
        String parterUserId = order.getToUser();
        if (StringUtils.isNotBlank(parterUserId) && !"0".equals(parterUserId)) {
            User parter = userBO.getRemoteUser(parterUserId);
            accountBO.doTransferAmountRemote(ESysUser.SYS_USER_DZT.getCode(),
                parterUserId, ECurrency.CNY,
                Double.valueOf(order.getAmount() * parter.getDivRate())
                    .longValue(), EBizType.AJ_HHRFC, "订单：" + order.getCode()
                        + " 合伙人分成", "订单：" + order.getCode() + " 分成收入");
        }
        // 量体师分成
        String ltUserId = order.getLtUser();
        if (StringUtils.isNotBlank(ltUserId)) {
            User ltUser = userBO.getRemoteUser(ltUserId);
            accountBO.doTransferAmountRemote(ESysUser.SYS_USER_DZT.getCode(),
                ltUserId, ECurrency.CNY,
                Double.valueOf(order.getAmount() * ltUser.getDivRate())
                    .longValue(), EBizType.AJ_HHRFC, "订单：" + order.getCode()
                        + " 量体师分成", "订单：" + order.getCode() + " 分成收入");
        }
    }

    @Override
    public void cancelOrder(String orderCode, String updater, String remark) {
        Order order = orderBO.getOrder(orderCode);
        if (EOrderStatus.PAY_YES.getCode().equals(order.getStatus())
                || EOrderStatus.TO_PRODU.getCode().equals(order.getStatus())
                || EOrderStatus.PRODU_DOING.getCode().equals(order.getStatus())
                || EOrderStatus.SEND.getCode().equals(order.getStatus())
                || EOrderStatus.RECEIVE.getCode().equals(order.getStatus())) {
            throw new BizException("xn000000", "订单不处于可取消订单状态,不能取消订单");
        }
        if (order.getApplyUser().equals(updater)
                || order.getLtUser().equals(updater)
                || order.getToUser().equals(updater)) {
            orderBO.cancelOrder(order, updater, remark);
        } else {
            throw new BizException("xn000000", "尊敬的用户,该订单不属于您，或你管辖的范围,不可取消订单");
        }
    }

    @Override
    public Paginable<Order> queryOrderPage(int start, int limit, Order condition) {
        Paginable<Order> results = orderBO
            .getPaginable(start, limit, condition);
        for (Order order : results.getList()) {
            order.setLtUserDO(userBO.getRemoteUser(order.getLtUser()));
        }
        return results;
    }

    @Override
    public Order getRichOrder(String code) {
        Order order = orderBO.getRichOrder(code);
        order.setLtUserDO(userBO.getRemoteUser(order.getLtUser()));
        return order;
    }

    @Override
    public List<Order> queryOrderlList(Order condition) {
        return orderBO.queryOrderList(condition);
    }

}
