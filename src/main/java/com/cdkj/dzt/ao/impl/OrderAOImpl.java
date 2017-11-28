/**
 * @Title OrderAOImpl.java 
 * @Package com.cdkj.dzt.ao.impl 
 * @Description 
 * @author leo(haiqing)  
 * @date 2017年4月16日 下午2:55:53 
 * @version V1.0   
 */
package com.cdkj.dzt.ao.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cdkj.dzt.ao.IOrderAO;
import com.cdkj.dzt.bo.IAccountBO;
import com.cdkj.dzt.bo.IClothBO;
import com.cdkj.dzt.bo.ICommentBO;
import com.cdkj.dzt.bo.ICraftBO;
import com.cdkj.dzt.bo.IKeywordBO;
import com.cdkj.dzt.bo.IModelBO;
import com.cdkj.dzt.bo.IModelSpecsBO;
import com.cdkj.dzt.bo.IOrderBO;
import com.cdkj.dzt.bo.IOrderSizeDataBO;
import com.cdkj.dzt.bo.IProductBO;
import com.cdkj.dzt.bo.IProductCraftBO;
import com.cdkj.dzt.bo.IProductSpecsBO;
import com.cdkj.dzt.bo.IProductVarBO;
import com.cdkj.dzt.bo.ISYSConfigBO;
import com.cdkj.dzt.bo.ISYSDictBO;
import com.cdkj.dzt.bo.ISizeDataBO;
import com.cdkj.dzt.bo.ISmsOutBO;
import com.cdkj.dzt.bo.IUserBO;
import com.cdkj.dzt.bo.base.Paginable;
import com.cdkj.dzt.common.AmountUtil;
import com.cdkj.dzt.common.DateUtil;
import com.cdkj.dzt.common.SysConstants;
import com.cdkj.dzt.core.CalculationUtil;
import com.cdkj.dzt.core.OrderNoGenerater;
import com.cdkj.dzt.core.StringValidater;
import com.cdkj.dzt.domain.Account;
import com.cdkj.dzt.domain.Cloth;
import com.cdkj.dzt.domain.Comment;
import com.cdkj.dzt.domain.Craft;
import com.cdkj.dzt.domain.Model;
import com.cdkj.dzt.domain.ModelSpecs;
import com.cdkj.dzt.domain.Order;
import com.cdkj.dzt.domain.Product;
import com.cdkj.dzt.domain.SYSConfig;
import com.cdkj.dzt.domain.SYSDict;
import com.cdkj.dzt.domain.SizeData;
import com.cdkj.dzt.domain.User;
import com.cdkj.dzt.dto.req.XN620200Req;
import com.cdkj.dzt.dto.req.XN620801Req;
import com.cdkj.dzt.dto.res.BooleanRes;
import com.cdkj.dzt.dto.res.XN001400Res;
import com.cdkj.dzt.dto.res.XN620218Res;
import com.cdkj.dzt.dto.res.XN620221Res;
import com.cdkj.dzt.dto.res.XN620222Res;
import com.cdkj.dzt.dto.res.XN620223Res;
import com.cdkj.dzt.enums.EBizType;
import com.cdkj.dzt.enums.EBoolean;
import com.cdkj.dzt.enums.ECommentStatus;
import com.cdkj.dzt.enums.ECurrency;
import com.cdkj.dzt.enums.EGeneratePrefix;
import com.cdkj.dzt.enums.EOrderStatus;
import com.cdkj.dzt.enums.EPayType;
import com.cdkj.dzt.enums.EReaction;
import com.cdkj.dzt.enums.ESysConfigCkey;
import com.cdkj.dzt.enums.ESysUser;
import com.cdkj.dzt.enums.ESystemCode;
import com.cdkj.dzt.enums.EUserFrequent;
import com.cdkj.dzt.enums.EUserKind;
import com.cdkj.dzt.enums.EUserLevel;
import com.cdkj.dzt.enums.EUserStatus;
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
    private IKeywordBO keywordBO;

    @Autowired
    private ISYSConfigBO sysConfigBO;

    @Autowired
    private IUserBO userBO;

    @Autowired
    private IModelBO modelBO;

    @Autowired
    private IModelSpecsBO modelSpecsBO;

    @Autowired
    private IProductVarBO productVarBO;

    @Autowired
    private ICraftBO craftBO;

    @Autowired
    private IProductCraftBO productCraftBO;

    @Autowired
    private IClothBO clothBO;

    @Autowired
    private IAccountBO accountBO;

    @Autowired
    private IProductBO productBO;

    @Autowired
    private ICommentBO commentBO;

    @Autowired
    private IProductSpecsBO productSpecsBO;

    @Autowired
    private ISizeDataBO sizeDataBO;

    @Autowired
    private IOrderSizeDataBO orderSizeDataBO;

    @Autowired
    private ISmsOutBO smsOutBO;

    @Autowired
    private ISYSDictBO sysDictBO;

    @Override
    public String applyOrder(XN620200Req req) {
        Order lastOrder = orderBO.getIsLastOrder(req.getApplyUser());
        if (null != lastOrder) {
            if (EOrderStatus.TO_MEASURE.getCode().equals(lastOrder.getStatus())) {
                throw new BizException("xn0000", "您已经有一个预约单了");
            } else {
                throw new BizException("xn0000", "您已经成功下过单了,可以直接复购");
            }
        }
        // 获取城市合伙人
        User user = userBO.getPartner(req.getLtProvince(), req.getLtCity(),
            req.getLtArea(), EUserKind.Partner);
        String userId = "0";
        if (null != user) {
            userId = user.getUserId();
            userBO.refreshToUser(req.getApplyUser(), userId);
        }
        // 开始组装order
        Order order = new Order();
        Date now = new Date();
        String code = OrderNoGenerater.generateM(EGeneratePrefix.ORDER
            .getCode());
        order.setCode(code);

        order.setToUser(userId);
        order.setOrderProduct(req.getProductCode());
        order.setApplyUser(req.getApplyUser());
        order.setApplyName(req.getApplyName());
        order.setApplyMobile(req.getApplyMobile());

        order.setLtProvince(req.getLtProvince());
        order.setLtCity(req.getLtCity());
        order.setLtArea(req.getLtArea());
        order.setLtAddress(req.getLtAddress());

        order.setLtDatetime(DateUtil.strToDate(req.getLtDatetime(),
            DateUtil.FRONT_DATE_FORMAT_STRING));
        order.setApplyNote(req.getApplyNote());
        order.setCreateDatetime(now);
        order.setStatus(EOrderStatus.TO_MEASURE.getCode());

        order.setReceiver(req.getApplyName());
        order.setReMobile(req.getApplyMobile());

        order.setUpdater(req.getUpdater());
        order.setUpdateDatetime(now);
        order.setRemark(req.getRemark());

        orderBO.applyOrder(order);
        sizeDataBO.removeSizeDataByUserId(req.getApplyUser());
        // 落地身材数据(落地身高、体重)
        sizeDataBO.inputInforValue(req.getApplyUser(), req.getMap());
        // 落地量体数据(落地身高、体重)
        orderSizeDataBO.inputInforValue(order, req.getMap());
        // 更新用户姓名
        userBO.modifyRealName(req.getApplyUser(), req.getApplyName());
        // 如果有地区合伙人，短信通知
        if (!"0".equals(userId)) {
            smsOutBO.sentContent(
                userId,
                String.format(SysConstants.APPLY_CONTENT, code,
                    order.getApplyName()));
        }
        // 更新用户最后下单时间
        userBO
            .refreshLastOrderDatetime(order.getApplyUser(), DateUtil.dateToStr(
                order.getCreateDatetime(), DateUtil.DATA_TIME_PATTERN_1));
        return code;
    }

    @Override
    public String applyOrder(String applyUser, String ltDatetime,
            String productCode) {
        // 获取最近订单
        Order lastOrder = orderBO.getIsLastOrder(applyUser);
        if (null != lastOrder) {
            if (EOrderStatus.TO_MEASURE.getCode().equals(lastOrder.getStatus())) {
                throw new BizException("xn0000", "您已经有一个预约单了");
            }
        }
        if (null == lastOrder) {
            throw new BizException("xn0000", "您还未成功预约过,不能一件复购");
        }
        List<SizeData> sizeDataList = sizeDataBO.querySizeDataList(applyUser);
        // 开始组装order
        Order order = new Order();
        Date now = new Date();
        String code = OrderNoGenerater.generateM(EGeneratePrefix.ORDER
            .getCode());
        order.setCode(code);
        order.setToUser(lastOrder.getToUser());
        order.setOrderProduct(productCode);
        order.setApplyUser(lastOrder.getApplyUser());
        order.setApplyName(lastOrder.getApplyName());
        order.setApplyMobile(lastOrder.getApplyMobile());

        order.setLtUser(lastOrder.getLtUser());
        order.setLtName(lastOrder.getLtName());

        order.setLtDatetime(DateUtil.strToDate(ltDatetime,
            DateUtil.FRONT_DATE_FORMAT_STRING));
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
        Map<String, String> map = new HashMap<String, String>();
        for (SizeData sizeData : sizeDataList) {
            map.put(sizeData.getCkey(), sizeData.getDkey());
        }
        orderSizeDataBO.inputInforValue(order, map);
        // 一键复购，直接短信通知量体师
        smsOutBO.sentContent(
            order.getLtUser(),
            String.format(SysConstants.DISTRIBUTE_CONTENT, code,
                order.getApplyName()));
        // 更新用户最后下单时间
        userBO
            .refreshLastOrderDatetime(order.getApplyUser(), DateUtil.dateToStr(
                order.getCreateDatetime(), DateUtil.DATA_TIME_PATTERN_1));
        return code;
    }

    @Override
    public void distributeOrder(String orderCode, String ltUser,
            String updater, String remark) {
        Order order = orderBO.getOrder(orderCode);
        if (!EOrderStatus.TO_MEASURE.getCode().equals(order.getStatus())) {
            throw new BizException("xn0000", "订单不处于待量体状态，不可以分配订单");
        }
        XN001400Res user = userBO.getRemoteUser(ltUser);
        if (!EUserStatus.NORMAL.getCode().equals(user.getStatus())) {
            throw new BizException("xn0000", "该量体师账户异常,不能分配订单");
        }
        orderBO.distributeOrder(order, ltUser, user.getRealName(), updater,
            remark);
        userBO.refreshLtUser(order.getApplyUser(), ltUser);
        // 短信通知量体师
        smsOutBO.sentContent(
            ltUser,
            String.format(SysConstants.DISTRIBUTE_CONTENT, orderCode,
                order.getApplyName()));
    }

    @Override
    @Transactional
    public void confirmPrice(String orderCode, String modelCode,
            List<XN620801Req> list, Integer quantity, String address,
            String updater, String remark) {
        Order order = orderBO.getOrder(orderCode);
        XN001400Res ltUser = userBO.getRemoteUser(order.getLtUser());
        if (!EUserStatus.NORMAL.getCode().equals(ltUser.getStatus())) {
            throw new BizException("xn0000", "账户异常,不能定价");
        }
        if (!EOrderStatus.TO_MEASURE.getCode().equals(order.getStatus())) {
            throw new BizException("xn0000", "订单不处于待量体状态，不可以定价");
        }
        Double price = 0.0;
        // 获取产品并填充数据
        Model model = modelBO.getModel(modelCode);
        String productCode = productBO.saveProduct(order, model, quantity);
        if (StringUtils.isBlank(productCode)) {
            throw new BizException("xn0000", "产品不能为空");
        }
        // 计算面料价格
        // 计算工艺价格
        Long clothPrice = 0L;
        Long craftPrice = 0L;
        for (XN620801Req req : list) {
            ModelSpecs modelSpecs = modelSpecsBO.getModelSpecs(req
                .getModelSpecsCode());
            String productVarCode = productVarBO.saveProductVar(modelSpecs,
                order.getApplyUser(), productCode);
            if (StringUtils.isBlank(productVarCode)) {
                throw new BizException("xn0000", "您还未选择产品品种");
            }
            Cloth cloth = clothBO.getCloth(req.getClothCode());
            clothPrice = clothPrice + cloth.getPrice();
            productSpecsBO.saveProductSpecs(cloth.getCode(), null,
                cloth.getType(), cloth.getPic(), cloth.getBrand(),
                cloth.getModelNum(), cloth.getAdvPic(), cloth.getColor(),
                cloth.getFlowers(), cloth.getForm(), cloth.getWeight(),
                cloth.getYarn(), cloth.getPrice(), productVarCode, productCode,
                order.getCode());
            if (CollectionUtils.isEmpty(req.getCodeList())) {
                List<Craft> craftList = craftBO.queryCraftList(
                    EBoolean.YES.getCode(), req.getModelSpecsCode(), modelCode);
                for (Craft craft : craftList) {
                    craftPrice = craftPrice + craft.getPrice();
                    productCraftBO.saveProductCraft(craft, productVarCode,
                        order.getCode());
                }
            } else {
                for (String code : req.getCodeList()) {
                    Craft craft = craftBO.getCraft(code);
                    craftPrice = craftPrice + craft.getPrice();
                    productCraftBO.saveProductCraft(craft, productVarCode,
                        order.getCode());
                }
                for (Entry<String, String> entry : req.getMap().entrySet()) {
                    productCraftBO.saveProductCraft(entry.getValue(),
                        entry.getKey(), modelSpecs.getModelCode(),
                        productVarCode, order.getCode());
                }
            }
        }
        price = clothPrice.doubleValue() + craftPrice.doubleValue();
        XN001400Res user = userBO.getRemoteUser(order.getApplyUser());
        // 计算非会员价
        Long truePrice = AmountUtil.rmbJinFen(price);
        Long originalPrice = AmountUtil.mul(truePrice, 1) * quantity;

        Double rate = 1.0;
        if (EBoolean.YES.getCode().equals(model.getType())) {
            rate = StringValidater.toDouble(sysConfigBO.getConfigValue(
                ESysConfigCkey.FHY.getCode(), ESystemCode.DZT.getCode(),
                ESystemCode.DZT.getCode()).getCvalue());
            if (StringValidater.toInteger(user.getLevel()) < 2) {
                rate = 1.0;
            }
        }

        // 如果是会员,真实金额乘于倍数
        truePrice = AmountUtil.mul(truePrice, rate) * quantity;
        orderBO.confirmPrice(order, model, truePrice, originalPrice, address,
            updater, remark);

        // 短信通知用户付款
        smsOutBO.sentContent(
            order.getApplyUser(),
            String.format(SysConstants.CONFIRM_PRICE_CONTENT,
                order.getApplyName(), orderCode,
                CalculationUtil.divi(order.getAmount())));
    }

    // 支付
    @Override
    @Transactional
    public Object payment(String orderCode, String payType) {
        Object result = null;
        if (EPayType.WEIXIN.getCode().equals(payType)) {
            result = toPayOrderWechat(orderCode);
        } else if (EPayType.YEZF.getCode().equals(payType)) {
            result = toPayOrderYE(orderCode);
        } else if (EPayType.HYB.getCode().equals(payType)) {
            result = toPayOrderHYB(orderCode);
        } else {
            throw new BizException("xn000000", "暂不支持该种支付方式");
        }
        return result;
    }

    private Object toPayOrderHYB(String orderCode) {
        Order order = orderBO.getOrder(orderCode);
        Long totalAmount = order.getAmount();
        String userId = order.getApplyUser();
        Product product = productBO.getProductByOrderCode(orderCode);
        String payGroup = OrderNoGenerater.generateM(EGeneratePrefix.ORDER
            .getCode());
        orderBO.addPayGroup(order, payGroup, EPayType.HYB.getCode());
        accountBO.doTransferAmountRemote(userId, ECurrency.HYB,
            ESysUser.SYS_USER_DZT.getCode(), ECurrency.HYB, totalAmount,
            EBizType.AJ_GWFK, "HE-SHIRTS" + product.getModelName() + "购买订单支付",
            "HE-SHIRTS" + product.getModelName() + "购买订单支付", orderCode);
        orderBO.PaySuccess(order, null, totalAmount);
        // 短信通知用户
        smsOutBO.sentContent(
            order.getApplyUser(),
            String.format(SysConstants.PAY_SUCCESS_CONTENT,
                order.getApplyName(), orderCode));
        return new BooleanRes(true);
    }

    private Object toPayOrderYE(String orderCode) {
        Order order = orderBO.getOrder(orderCode);
        Long totalAmount = order.getAmount();
        String userId = order.getApplyUser();
        Product product = productBO.getProductByOrderCode(orderCode);
        // 生成payGroup,并把订单进行支付。
        String payGroup = OrderNoGenerater.generateM(EGeneratePrefix.ORDER
            .getCode());
        orderBO.addPayGroup(order, payGroup, EPayType.YEZF.getCode());
        accountBO.doTransferAmountRemote(userId, ECurrency.CNY,
            ESysUser.SYS_USER_DZT.getCode(), ECurrency.CNY, totalAmount,
            EBizType.AJ_GWFK, "HE-SHIRTS" + product.getModelName() + "购买订单支付",
            "HE-SHIRTS" + product.getModelName() + "购买订单支付", orderCode);
        orderBO.PaySuccess(order, null, totalAmount);
        // 短信通知用户
        smsOutBO.sentContent(
            order.getApplyUser(),
            String.format(SysConstants.PAY_SUCCESS_CONTENT,
                order.getApplyName(), orderCode));
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
        XN001400Res user = userBO.getRemoteUser(userId);
        if (!EOrderStatus.ASSIGN_PRICE.getCode().equals(order.getStatus())) {
            throw new BizException("xn000000", "订单不处于已定价状态");
        }
        orderBO.addPayGroup(order, payGroup, EPayType.WEIXIN.getCode());
        Product product = productBO.getProductByOrderCode(orderCode);
        return accountBO.doWeiXinH5PayRemote(userId, user.getH5OpenId(),
            ESysUser.SYS_USER_DZT.getCode(), payGroup, orderCode,
            EBizType.AJ_GWFK.getCode(), "HE-SHIRTS" + product.getModelName()
                    + "购买订单支付", totalAmount);
    }

    // 支付成功
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
            // 短信通知用户
            smsOutBO.sentContent(
                order.getApplyUser(),
                String.format(SysConstants.PAY_SUCCESS_CONTENT,
                    order.getApplyName(), order.getCode()));
        } else {
            logger.info("订单号：" + order.getCode() + "已支付，重复回调");
        }
    }

    // 录入数据
    @Override
    @Transactional
    public void inputInfor(String orderCode, Map<String, String> map,
            String updater, String remark) {
        Order order = orderBO.getOrder(orderCode);
        XN001400Res ltUser = userBO.getRemoteUser(order.getLtUser());
        if (!EUserStatus.NORMAL.getCode().equals(ltUser.getStatus())) {
            throw new BizException("xn0000", "账户异常,不能定价");
        }
        if (!EOrderStatus.PAY_YES.getCode().equals(order.getStatus())) {
            throw new BizException("xn000000", "订单尚未支付,不能录入数据");
        }
        for (Entry<String, String> entry : map.entrySet()) {
            orderSizeDataBO.removeOrderSizeData(entry.getKey(), orderCode);
            sizeDataBO
                .removeSizeDataByKey(order.getApplyUser(), entry.getKey());
        }

        sizeDataBO.inputInforValue(order.getApplyUser(), map);
        orderSizeDataBO.inputInforValue(order, map);
        // 更新订单
        orderBO.inputInfor(order, order.getReAddress(), updater, remark);
    }

    // 提交复核
    @Override
    public void ltSubmit(String orderCode, String updater, String remark) {
        Order order = orderBO.getRichOrder(orderCode);
        XN001400Res ltUser = userBO.getRemoteUser(order.getLtUser());
        if (!EUserStatus.NORMAL.getCode().equals(ltUser.getStatus())) {
            throw new BizException("xn0000", "账户异常,不能定价");
        }
        if (!EOrderStatus.PAY_YES.getCode().equals(order.getStatus())) {
            throw new BizException("xn000000", "订单不是已支付状态,不能提交审核");
        }
        // 确保所有规格已经填充完毕
        sysDictBO.checkOrder(order);

        orderBO.ltSubmit(order, updater, remark);
        // 如果有地区合伙人，短信通知
        if (!"0".equals(order.getToUser())) {
            smsOutBO.sentContent(
                order.getToUser(),
                String.format(SysConstants.LT_SUBMIT_CONTENT, orderCode,
                    order.getApplyName()));
        }
    }

    // 合伙人审核
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

    // 提交生产
    @Override
    public void submitProuduct(String orderCode, String updater, String remark) {
        Order order = orderBO.getOrder(orderCode);
        if (!EOrderStatus.TO_PRODU.getCode().equals(order.getStatus())) {
            throw new BizException("xn000000", "订单不处于待生产,不能提交生产");
        }
        orderBO.submitProudect(order, updater, remark);
        // 短信通知用户
        smsOutBO.sentContent(order.getApplyUser(), String.format(
            SysConstants.SUBMIT_CONTENT, order.getApplyName(), orderCode));
    }

    // 发货
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
        // 短信通知用户
        smsOutBO.sentContent(order.getApplyUser(), String.format(
            SysConstants.SENT_CONTENT, order.getApplyName(), orderCode));
    }

    // 确认收货
    @Override
    @Transactional
    public void confirmReceipt(String orderCode, String updater, String remark) {
        Order order = orderBO.getOrder(orderCode);
        if (!EOrderStatus.SEND.getCode().equals(order.getStatus())) {
            throw new BizException("xn000000", "订单还未发货,不能确认收货");
        }
        // 更改订单状态
        orderBO.confirmReceipt(order, updater, remark);
    }

    // 取消订单
    @Override
    @Transactional
    public void cancelOrder(String orderCode, String updater, String remark) {
        Order order = orderBO.getOrder(orderCode);
        if (EOrderStatus.PAY_YES.getCode().equals(order.getStatus())
                || EOrderStatus.TO_PRODU.getCode().equals(order.getStatus())
                || EOrderStatus.TO_APPROVE.getCode().equals(order.getStatus())
                || EOrderStatus.PRODU_DOING.getCode().equals(order.getStatus())
                || EOrderStatus.SEND.getCode().equals(order.getStatus())) {
            orderBO.cancelOrder(order, updater, remark);
            ECurrency currency = ECurrency.CNY;
            if (EPayType.WEIXIN.getCode().equals(order.getPayType())
                    || EPayType.YEZF.getCode().equals(order.getPayType())) {
                currency = ECurrency.CNY;
            } else if (EPayType.HYB.getCode().equals(order.getPayType())) {
                currency = ECurrency.HYB;
            }
            // 退款
            accountBO.doTransferAmountRemote(ESysUser.SYS_USER_DZT.getCode(),
                currency, order.getApplyUser(), currency, order.getAmount(),
                EBizType.AJ_TK, "订单：" + orderCode + "取消后退款", "订单：" + orderCode
                        + "取消后退款", orderCode);
            // 短信通知用户订单已被取消
            smsOutBO.sentContent(
                order.getApplyUser(),
                String.format(SysConstants.CANCEL_ORDER_CONTENT,
                    order.getApplyName(), orderCode));
        } else if (EOrderStatus.TO_MEASURE.getCode().equals(order.getStatus())
                || EOrderStatus.ASSIGN_PRICE.getCode()
                    .equals(order.getStatus())) {
            orderBO.cancelOrder(order, updater, remark);
            if (EOrderStatus.TO_MEASURE.getCode().equals(order.getStatus())) {
                sizeDataBO.removeSizeDataByUserId(order.getApplyUser());
            }
            // 短信通知用户订单已被取消
            smsOutBO.sentContent(
                order.getApplyUser(),
                String.format(SysConstants.CANCEL_ORDER_CONTENT,
                    order.getApplyName(), orderCode));
        } else {
            throw new BizException("xn000000", "订单不处于可取消订单状态,不能取消订单");
        }
    }

    @Override
    public Paginable<Order> queryOrderPage(int start, int limit, Order condition) {
        Paginable<Order> results = orderBO
            .getPaginable(start, limit, condition);
        for (Order order : results.getList()) {
            Product product = productBO.getProductByOrderCode(order.getCode());
            if (null != product) {
                order.setModelCode(product.getModelCode());
                order.setModelName(product.getModelName());
            }
            order.setLtUserDO(userBO.getRemoteUser(order.getLtUser()));
        }
        return results;
    }

    @Override
    public Order getRichOrder(String code) {
        Order order = orderBO.getRichOrder(code);
        order.setLtUserDO(userBO.getRemoteUser(order.getLtUser()));
        order.setLevel(userBO.getRemoteUser(order.getApplyUser()).getLevel());
        Comment condition = new Comment();
        condition.setParentCode(code);
        List<Comment> commentList = commentBO.queryCommentList(condition);
        if (CollectionUtils.isNotEmpty(commentList)) {
            Comment comment = commentList.get(0);
            order.setComment(comment);
        }
        return order;
    }

    @Override
    public List<Order> queryOrderlList(Order condition) {
        return orderBO.queryOrderList(condition);
    }

    @Override
    public String comment(String orderCode, String content, String commenter) {
        Order order = orderBO.getOrder(orderCode);
        if (!EOrderStatus.RECEIVE.getCode().equals(order.getStatus())) {
            throw new BizException("xn000000", "订单不处于已收货状态,不能评论");
        }
        Product product = productBO.getProductByOrderCode(orderCode);
        // 判断是否含有关键字
        String status = ECommentStatus.PUBLISHED.getCode();
        EReaction result = keywordBO.checkContent(content);
        if (EReaction.REFUSE.getCode().equals(result.getCode())) {
            status = ECommentStatus.FILTERED.getCode();
        }
        Comment data = new Comment();
        String code = OrderNoGenerater.generateME(EGeneratePrefix.COMMENT
            .getCode());
        data.setCode(code);
        data.setType(EBoolean.YES.getCode());
        data.setContent(content);
        data.setStatus(status);
        data.setCommer(commenter);
        data.setCommentDatetime(new Date());
        data.setParentCode(orderCode);
        data.setTopCode(product.getCode());
        commentBO.saveComment(data);
        if (ECommentStatus.FILTERED.getCode().equals(status)) {
            code = code + "; filter";
        }
        orderBO.comment(order, commenter);
        return code;
    }

    @Override
    @Transactional
    public void isFiled(String orderCode, String updater, String remark) {
        Order order = orderBO.getOrder(orderCode);
        if (!EOrderStatus.COMMENT.getCode().equals(order.getStatus())) {
            throw new BizException("xn000000", "订单不处于已评论状态,不能归档");
        }
        orderBO.isFiled(order, updater, remark);
        // 合伙人和量体师进行分成
        doFenChengRMB(order);
        // 计算积分
        doJF(order);
        // 计算经验
        doJY(order);
    }

    private void doFenChengRMB(Order order) {
        // 合伙人分成
        String parterUserId = order.getToUser();
        String type = order.getType();
        // 0为衬衫，1为H+产品
        // 如果为衬衫则分成
        if (EBoolean.NO.getCode().equals(type)) {
            if (StringUtils.isNotBlank(parterUserId)
                    && !"0".equals(parterUserId)) {
                Double rate = StringValidater.toDouble(sysConfigBO
                    .getConfigValue(ESysConfigCkey.CSHHRFC.getCode(),
                        ESystemCode.DZT.getCode(), ESystemCode.DZT.getCode())
                    .getCvalue());
                Long amount = AmountUtil.mul(order.getAmount(), rate);
                // 分成金额至少是一分钱
                if (amount > 10) {
                    accountBO.doTransferAmountRemote(
                        ESysUser.SYS_USER_DZT.getCode(), ECurrency.CNY,
                        parterUserId, ECurrency.CNY, amount, EBizType.AJ_HHRFC,
                        "订单：" + order.getCode() + " 合伙人分成",
                        "订单：" + order.getCode() + " 分成收入", order.getCode());
                    // 短信通知
                    smsOutBO.sentContent(parterUserId, String.format(
                        SysConstants.FENCHENG_CONTENT, "合伙人", order.getCode(),
                        CalculationUtil.divi(amount)));
                }
            }
            // 量体师分成
            String ltUserId = order.getLtUser();
            if (StringUtils.isNotBlank(ltUserId)) {
                Double rate = StringValidater.toDouble(sysConfigBO
                    .getConfigValue(ESysConfigCkey.CSLTSFC.getCode(),
                        ESystemCode.DZT.getCode(), ESystemCode.DZT.getCode())
                    .getCvalue());
                Long amount = AmountUtil.mul(order.getAmount(), rate);
                if (amount > 10) {
                    accountBO.doTransferAmountRemote(
                        ESysUser.SYS_USER_DZT.getCode(), ECurrency.CNY,
                        ltUserId, ECurrency.CNY, amount, EBizType.AJ_LTSFC,
                        "订单：" + order.getCode() + " 量体师分成",
                        "订单：" + order.getCode() + " 分成收入", order.getCode());
                    // 短信通知
                    smsOutBO.sentContent(ltUserId, String.format(
                        SysConstants.FENCHENG_CONTENT, "量体师", order.getCode(),
                        CalculationUtil.divi(amount)));
                }
            }
        } else {// 如果为H+产品则分成
            if (StringUtils.isNotBlank(parterUserId)
                    && !"0".equals(parterUserId)) {
                Double rate = StringValidater.toDouble(sysConfigBO
                    .getConfigValue(ESysConfigCkey.HHHRFC.getCode(),
                        ESystemCode.DZT.getCode(), ESystemCode.DZT.getCode())
                    .getCvalue());
                Long amount = AmountUtil.mul(order.getAmount(), rate);
                // 分成金额至少是一分钱
                if (amount > 10) {
                    accountBO.doTransferAmountRemote(
                        ESysUser.SYS_USER_DZT.getCode(), ECurrency.CNY,
                        parterUserId, ECurrency.CNY, amount, EBizType.AJ_HHRFC,
                        "订单：" + order.getCode() + " 合伙人分成",
                        "订单：" + order.getCode() + " 分成收入", order.getCode());
                    // 短信通知
                    smsOutBO.sentContent(parterUserId, String.format(
                        SysConstants.FENCHENG_CONTENT, "合伙人", order.getCode(),
                        CalculationUtil.divi(amount)));
                }
            }
            // 量体师分成
            String ltUserId = order.getLtUser();
            if (StringUtils.isNotBlank(ltUserId)) {
                Double rate = StringValidater.toDouble(sysConfigBO
                    .getConfigValue(ESysConfigCkey.HLTSFC.getCode(),
                        ESystemCode.DZT.getCode(), ESystemCode.DZT.getCode())
                    .getCvalue());
                Long amount = AmountUtil.mul(order.getAmount(), rate);
                if (amount > 10) {
                    accountBO.doTransferAmountRemote(
                        ESysUser.SYS_USER_DZT.getCode(), ECurrency.CNY,
                        ltUserId, ECurrency.CNY, amount, EBizType.AJ_LTSFC,
                        "订单：" + order.getCode() + " 量体师分成",
                        "订单：" + order.getCode() + " 分成收入", order.getCode());
                    // 短信通知
                    smsOutBO.sentContent(ltUserId, String.format(
                        SysConstants.FENCHENG_CONTENT, "量体师", order.getCode(),
                        CalculationUtil.divi(amount)));
                }
            }
        }
    }

    private void doJF(Order order) {
        XN001400Res user = userBO.getRemoteUser(order.getApplyUser());
        // 计算积分
        if (StringUtils.isNotBlank(user.getUserReferee())) {
            XN001400Res user1 = userBO.getRemoteUser(user.getUserReferee());
            // 如果是会员
            if (StringValidater.toInteger(user1.getLevel()) > 1) {
                Long count = orderBO.getTotalCount(order.getApplyUser(),
                    EOrderStatus.FILED.getCode(), null);
                // 如果是第一次下单成功,推荐人获得1500积分
                // 其他情况获得比例积分
                if (count > 1) {
                    // 获取多少积分比例
                    Double rate = StringValidater.toDouble(sysConfigBO
                        .getConfigValue(ESysConfigCkey.DCTJ.getCode(),
                            ESystemCode.DZT.getCode(),
                            ESystemCode.DZT.getCode()).getCvalue()) * 1000;
                    Long rateAmount = rate.longValue();
                    // 兑换成多少积分
                    Long amount = order.getAmount() / rateAmount;
                    accountBO.doTransferAmountRemote(
                        ESysUser.SYS_USER_DZT.getCode(), ECurrency.JF,
                        user.getUserReferee(), ECurrency.JF, amount * 1000,
                        EBizType.DCTJ, EBizType.DCTJ.getValue(),
                        EBizType.DCTJ.getValue(), order.getCode());
                } else {
                    // 首次直接加1500
                    Long amount = StringValidater.toLong(sysConfigBO
                        .getConfigValue(ESysConfigCkey.SCTJ.getCode(),
                            ESystemCode.DZT.getCode(),
                            ESystemCode.DZT.getCode()).getCvalue()) * 1000;
                    accountBO.doTransferAmountRemote(
                        ESysUser.SYS_USER_DZT.getCode(), ECurrency.JF,
                        user.getUserReferee(), ECurrency.JF, amount,
                        EBizType.SCTJ, EBizType.SCTJ.getValue(),
                        EBizType.SCTJ.getValue(), order.getCode());
                }
            }
        }
        // 计算积分
        // 购买者如果是会员
        if (StringValidater.toInteger(user.getLevel()) > 1) {
            Double rate = 1.0D;
            Double birthdayRate = 1.0D;
            if (null != user.getBirthday()) {
                int birthdayMonth = user.getBirthday().getMonth();
                int nowMonth = (new Date()).getMonth();
                if (birthdayMonth == nowMonth) {
                    if (EUserLevel.TWO.getCode().equals(user.getLevel())) {
                        // 获取多少积分比例
                        birthdayRate = StringValidater.toDouble(sysConfigBO
                            .getConfigValue(ESysConfigCkey.YKBS.getCode(),
                                ESystemCode.DZT.getCode(),
                                ESystemCode.DZT.getCode()).getCvalue());
                    } else if (EUserLevel.THREE.getCode().equals(
                        user.getLevel())) {
                        // 获取多少积分比例
                        birthdayRate = StringValidater.toDouble(sysConfigBO
                            .getConfigValue(ESysConfigCkey.JKBS.getCode(),
                                ESystemCode.DZT.getCode(),
                                ESystemCode.DZT.getCode()).getCvalue());
                    } else if (EUserLevel.FOUR.getCode()
                        .equals(user.getLevel())) {
                        // 获取多少积分比例
                        birthdayRate = StringValidater.toDouble(sysConfigBO
                            .getConfigValue(ESysConfigCkey.BSBS.getCode(),
                                ESystemCode.DZT.getCode(),
                                ESystemCode.DZT.getCode()).getCvalue());
                    } else if (EUserLevel.FIVE.getCode()
                        .equals(user.getLevel())) {
                        // 获取多少积分比例
                        birthdayRate = StringValidater.toDouble(sysConfigBO
                            .getConfigValue(ESysConfigCkey.ZSBS.getCode(),
                                ESystemCode.DZT.getCode(),
                                ESystemCode.DZT.getCode()).getCvalue());
                    }
                } else {
                    // 获取多少积分比例
                    rate = StringValidater.toDouble(sysConfigBO.getConfigValue(
                        ESysConfigCkey.YHHD.getCode(),
                        ESystemCode.DZT.getCode(), ESystemCode.DZT.getCode())
                        .getCvalue());
                }
            } else {
                // 获取多少积分比例
                rate = StringValidater.toDouble(sysConfigBO.getConfigValue(
                    ESysConfigCkey.YHHD.getCode(), ESystemCode.DZT.getCode(),
                    ESystemCode.DZT.getCode()).getCvalue());
            }
            Double rateAmount = rate * 1000;
            // 兑换成多少积分
            Long amount = AmountUtil.mul(
                (order.getAmount() / rateAmount.longValue()) * 1000,
                birthdayRate);
            accountBO.doTransferAmountRemote(ESysUser.SYS_USER_DZT.getCode(),
                ECurrency.JF, order.getApplyUser(), ECurrency.JF, amount,
                EBizType.YHHD, EBizType.YHHD.getValue(),
                EBizType.YHHD.getValue(), order.getCode());
        }
    }

    private void doJY(Order order) {
        List<SYSConfig> sysConfigList = sysConfigBO
            .querySYSConfigList(EBoolean.YES.getCode());
        XN001400Res user = userBO.getRemoteUser(order.getApplyUser());
        // 送经验,计算经验
        if (StringUtils.isNotBlank(user.getUserReferee())) {
            XN001400Res user1 = userBO.getRemoteUser(user.getUserReferee());
            // 如果是会员
            if (StringValidater.toInteger(user1.getLevel()) > 1) {
                Long count = orderBO.getTotalCount(order.getApplyUser(),
                    EOrderStatus.FILED.getCode(), null);
                // 如果是第一次下单成功,推荐人获得1500经验
                // 其他情况获得比例积分
                if (count > 1) {
                    // 获取多少积分比例
                    Double rate = StringValidater.toDouble(sysConfigBO
                        .getConfigValue(ESysConfigCkey.HDCJY.getCode(),
                            ESystemCode.DZT.getCode(),
                            ESystemCode.DZT.getCode()).getCvalue());
                    // 兑换成多少积分
                    Long amount = AmountUtil.mul(order.getAmount(), rate);
                    accountBO.doTransferAmountRemote(
                        ESysUser.SYS_USER_DZT.getCode(), ECurrency.JY,
                        user.getUserReferee(), ECurrency.JY, amount,
                        EBizType.HDCJY, EBizType.HDCJY.getValue(),
                        EBizType.HDCJY.getValue(), order.getCode());
                } else {
                    // 首次直接加1500
                    Long amount = StringValidater.toLong(sysConfigBO
                        .getConfigValue(ESysConfigCkey.HSCJY.getCode(),
                            ESystemCode.DZT.getCode(),
                            ESystemCode.DZT.getCode()).getCvalue()) * 1000;
                    accountBO.doTransferAmountRemote(
                        ESysUser.SYS_USER_DZT.getCode(), ECurrency.JY,
                        user.getUserReferee(), ECurrency.JY, amount,
                        EBizType.HSCJY, EBizType.HSCJY.getValue(),
                        EBizType.HSCJY.getValue(), order.getCode());
                }
            }
            Account account = accountBO.getRemoteAccount(user.getUserReferee(),
                ECurrency.JY);
            Integer level = StringValidater.toInteger(user1.getLevel());
            for (SYSConfig sysConfig : sysConfigList) {
                if (StringValidater.toLong(sysConfig.getCvalue()) * 1000 < account
                    .getAmount()) {
                    level = level + 1;
                }
            }
            if (level > 4) {
                level = 4;
            }
            userBO.doUpLevel(user.getUserReferee(), Integer.toString(level));
        }
        // 计算经验
        // 购买者如果是会员
        if (StringValidater.toInteger(user.getLevel()) > 1) {
            // 获取多少经验比例
            Double rate = StringValidater.toDouble(sysConfigBO.getConfigValue(
                ESysConfigCkey.YHJY.getCode(), ESystemCode.DZT.getCode(),
                ESystemCode.DZT.getCode()).getCvalue());
            // 兑换成多少经验
            Long amount = AmountUtil.mul(order.getAmount(), rate);
            accountBO.doTransferAmountRemote(ESysUser.SYS_USER_DZT.getCode(),
                ECurrency.JY, order.getApplyUser(), ECurrency.JY, amount,
                EBizType.YHJY, EBizType.YHJY.getValue(),
                EBizType.YHJY.getValue(), order.getCode());
            Account account = accountBO.getRemoteAccount(order.getApplyUser(),
                ECurrency.JY);
            Integer level = StringValidater.toInteger(user.getLevel());
            for (SYSConfig sysConfig : sysConfigList) {
                if (StringValidater.toLong(sysConfig.getCvalue()) * 1000 < account
                    .getAmount()) {
                    level = level + 1;
                }
            }
            if (level > 4) {
                level = 4;
            }
            userBO.doUpLevel(order.getApplyUser(), Integer.toString(level));
        }
    }

    @Override
    public Object paymentVIP(String userId, String payType) {
        Double totalAmount = StringValidater.toDouble(sysConfigBO
            .getConfigValue(ESysConfigCkey.HYF.getCode(),
                ESystemCode.DZT.getCode(), ESystemCode.DZT.getCode())
            .getCvalue()) * 1000;
        Long amount = totalAmount.longValue();
        XN001400Res user = userBO.getRemoteUser(userId);
        if (StringValidater.toInteger(user.getLevel()) > 1) {
            throw new BizException("xn0000", "您已经是VIP会员了,无需重复充值");
        }
        if (EPayType.WEIXIN.getCode().equals(payType)) {
            return accountBO
                .doWeiXinH5PayRemote(userId, user.getH5OpenId(),
                    ESysUser.SYS_USER_DZT.getCode(), userId, userId,
                    EBizType.AJ_HYCZ.getCode(), EBizType.AJ_HYCZ.getValue(),
                    amount);
        } else if (EPayType.YEZF.getCode().equals(payType)) {
            accountBO.doTransferAmountRemote(userId, ECurrency.CNY,
                ESysUser.SYS_USER_DZT.getCode(), ECurrency.CNY, amount,
                EBizType.AJ_HYCZ, EBizType.AJ_HYCZ.getValue(),
                EBizType.AJ_HYCZ.getValue(), userId);
            userBO.doUpLevel(userId, EUserLevel.TWO.getCode());
            // 短信通知
            smsOutBO.sentContent(userId,
                String.format(SysConstants.MEMBER_CONTENT, user.getNickname()));
            return new BooleanRes(true);
        } else if (EPayType.HYB.getCode().equals(payType)) {
            accountBO.doTransferAmountRemote(userId, ECurrency.HYB,
                ESysUser.SYS_USER_DZT.getCode(), ECurrency.HYB, amount,
                EBizType.AJ_HYCZ, EBizType.AJ_HYCZ.getValue(),
                EBizType.AJ_HYCZ.getValue(), userId);
            userBO.doUpLevel(userId, EUserLevel.TWO.getCode());
            // 短信通知
            smsOutBO.sentContent(userId,
                String.format(SysConstants.MEMBER_CONTENT, user.getNickname()));
            return new BooleanRes(true);
        } else {
            throw new BizException("xn0000", "暂不支持其他支付方式");
        }
    }

    @Override
    public void paySuccessVIP(String payGroup, String payCode, Long amount) {
        logger.info(payGroup);
        XN001400Res user = userBO.getRemoteUser(payGroup);
        if (StringValidater.toInteger(user.getLevel()) < 2) {
            userBO.doUpLevel(payGroup, EUserLevel.TWO.getCode());
            // 短信通知
            smsOutBO.sentContent(user.getUserId(),
                String.format(SysConstants.MEMBER_CONTENT, user.getNickname()));
        } else {
            logger.info("已支付，重复回调");
        }
    }

    @Override
    public XN620218Res getLastOrder(String applyUser) {
        XN620218Res res = new XN620218Res();
        Order order = orderBO.getIsLastOrder(applyUser);
        if (null != order) {
            Map<String, String> map = new HashMap<String, String>();
            List<SizeData> sizeDataList = sizeDataBO
                .querySizeDataList(applyUser);
            for (SizeData sizeData : sizeDataList) {
                map.put(sizeData.getCkey(), sizeData.getDkey());
            }
            res.setMap(map);
        }
        res.setOrder(order);
        return res;
    }

    @Override
    public void refreshFrequent() {
        // 半年
        Date createDatetimeEnd = new Date();
        Integer mothed = StringValidater.toInteger(sysConfigBO.getConfigValue(
            ESysConfigCkey.DMOTHED.getCode(), ESystemCode.DZT.getCode(),
            ESystemCode.DZT.getCode()).getCvalue());
        Date createDatetimeStartB = DateUtil.getRelativeDateOfDays(new Date(),
            -mothed * 30);
        List<XN001400Res> userList = userBO.queryUserList();
        if (CollectionUtils.isNotEmpty(userList)) {
            for (XN001400Res res : userList) {
                if (null != res.getLastOrderDatetime()) {
                    if (res.getLastOrderDatetime().before(createDatetimeStartB)) {
                        userBO.refreshFrequent(res.getUserId(),
                            EUserFrequent.SIX.getCode());
                    }
                } else if (res.getCreateDatetime().before(createDatetimeStartB)) {
                    userBO.refreshFrequent(res.getUserId(),
                        EUserFrequent.SIX.getCode());
                }
            }
        }
        List<Order> orderListB = orderBO.getGroupTotalCount(
            createDatetimeStartB, createDatetimeEnd);
        Integer count = StringValidater.toInteger(sysConfigBO.getConfigValue(
            ESysConfigCkey.LSCS.getCode(), ESystemCode.DZT.getCode(),
            ESystemCode.DZT.getCode()).getCvalue());
        if (CollectionUtils.isNotEmpty(orderListB)) {
            for (Order order : orderListB) {
                if (order.getCount() <= count) {
                    userBO.refreshFrequent(order.getApplyUser(),
                        EUserFrequent.SIX.getCode());
                }
            }
        }

        // 三个月
        mothed = StringValidater.toInteger(sysConfigBO.getConfigValue(
            ESysConfigCkey.SMOTHED.getCode(), ESystemCode.DZT.getCode(),
            ESystemCode.DZT.getCode()).getCvalue());
        Date createDatetimeStartS = DateUtil.getRelativeDateOfDays(new Date(),
            -mothed * 30);
        List<Order> orderListS = orderBO.getGroupTotalCount(
            createDatetimeStartS, createDatetimeEnd);
        Map<String, String> map = sysConfigBO.getMap();

        Integer YLSCS = StringValidater.toInteger(map.get(ESysConfigCkey.YLSCS
            .getCode()));
        Integer FCHY = StringValidater.toInteger(map.get(ESysConfigCkey.FCHY
            .getCode()));
        Integer HY = StringValidater.toInteger(map.get(ESysConfigCkey.HY
            .getCode()));
        Integer LKH = StringValidater.toInteger(map.get(ESysConfigCkey.LKH
            .getCode()));
        Integer XKH = StringValidater.toInteger(map.get(ESysConfigCkey.XKH
            .getCode()));
        if (CollectionUtils.isNotEmpty(orderListS)) {
            for (Order order : orderListS) {
                if (order.getCount() <= YLSCS) {
                    userBO.refreshFrequent(order.getApplyUser(),
                        EUserFrequent.FIVE.getCode());
                } else if (order.getCount() >= FCHY) {
                    userBO.refreshFrequent(order.getApplyUser(),
                        EUserFrequent.FOUR.getCode());
                } else if (order.getCount() >= HY && order.getCount() < FCHY) {
                    userBO.refreshFrequent(order.getApplyUser(),
                        EUserFrequent.THREE.getCode());
                } else if (order.getCount() >= LKH && order.getCount() < HY) {
                    userBO.refreshFrequent(order.getApplyUser(),
                        EUserFrequent.TWO.getCode());
                } else if (order.getCount() < LKH && order.getCount() >= XKH) {
                    userBO.refreshFrequent(order.getApplyUser(),
                        EUserFrequent.ONE.getCode());
                }
            }
        }
    }

    @Override
    public XN620221Res totalAmount(String userId) {
        XN620221Res res = new XN620221Res();
        XN001400Res user = userBO.getRemoteUser(userId);
        Account jyAccount = accountBO.getRemoteAccount(userId, ECurrency.JY);
        Account jfAccount = accountBO.getRemoteAccount(userId, ECurrency.JF);
        List<SYSConfig> sysConfigList = sysConfigBO
            .querySYSConfigList(EBoolean.YES.getCode());
        Long sjAmount = 0L;
        Integer days = DateUtil.daysBetween(user.getCreateDatetime(),
            new Date());
        if (EBoolean.YES.getCode().equals(user.getLevel())) {
            for (SYSConfig sysConfig : sysConfigList) {
                if (sysConfig.getCkey().equals("ONE")) {
                    sjAmount = 0l;
                }
            }
        } else {
            for (SYSConfig sysConfig : sysConfigList) {
                if (EUserLevel.TWO.getCode().equals(user.getLevel())
                        && sysConfig.getCkey().equals("ONE")) {
                    sjAmount = StringValidater.toDouble(sysConfig.getCvalue())
                        .longValue() * 1000L - jyAccount.getAmount() + 1000;
                } else if (EUserLevel.THREE.getCode().equals(user.getLevel())
                        && sysConfig.getCkey().equals("TWO")) {
                    sjAmount = StringValidater.toDouble(sysConfig.getCvalue())
                        .longValue() * 1000L - jyAccount.getAmount() + 1000;
                } else if (EUserLevel.FOUR.getCode().equals(user.getLevel())
                        && sysConfig.getCkey().equals("THREE")) {
                    sjAmount = StringValidater.toDouble(sysConfig.getCvalue())
                        .longValue() * 1000L - jyAccount.getAmount() + 1000;
                } else if (EUserLevel.FIVE.getCode().equals(user.getLevel())
                        && sysConfig.getCkey().equals("FOUR")) {
                    sjAmount = 0L;
                }
            }
        }
        Order order = orderBO.getIsLastOrder(userId);
        List<SizeData> sizeDataList = sizeDataBO.querySizeDataList(userId);
        res.setRealName(user.getRealName());
        res.setMobile(user.getMobile());
        res.setJfAmount(jfAccount.getAmount());
        res.setJyAmount(jyAccount.getAmount());
        res.setSjAmount(sjAmount);
        res.setDays(days);
        res.setBirthday(user.getBirthday());
        res.setLevel(user.getLevel());
        res.setSyAmount(jfAccount.getAmount());
        res.setConAmount(0l);
        Map<String, List<SYSDict>> map = sysDictBO.queryMapSYSDictList();
        for (Entry<String, List<SYSDict>> sysDictMap : map.entrySet()) {
            List<SYSDict> sysDictlist = sysDictMap.getValue();
            for (SYSDict sysDict : sysDictlist) {
                for (SizeData sizeData : sizeDataList) {
                    if (sysDict.getDkey().equals(sizeData.getCkey())) {
                        sysDict.setSizeData(sizeData);
                    }
                }
            }
        }
        res.setSysDictMap(map);
        if (null != order) {
            res.setAddress(order.getReAddress());
            if (StringUtils.isBlank(order.getReAddress())) {
                res.setAddress(order.getLtProvince() + order.getLtCity()
                        + order.getLtArea() + order.getLtAddress());
            }
        }
        return res;
    }

    @Override
    public XN620222Res getLtInfo(String userId) {
        XN620222Res res = new XN620222Res();
        Order order = orderBO.getIsLastOrder(userId);
        XN001400Res user = null;
        if (null != order) {
            user = userBO.getRemoteUser(order.getLtUser());
        }
        if (null != user) {
            res.setLtName(user.getRealName());
            res.setLtMobile(user.getMobile());
        }
        return res;
    }

    @Override
    public XN620223Res totalUnFinished(String userId) {
        XN620223Res res = new XN620223Res();
        Long toMeasureOrder = orderBO.getTotalCount(userId,
            EOrderStatus.TO_MEASURE.getCode(), null);
        Long toPayOrder = orderBO.getTotalCount(userId,
            EOrderStatus.ASSIGN_PRICE.getCode(), null);
        Long toReceiverOrder = orderBO.getTotalCount(userId,
            EOrderStatus.SEND.getCode(), null);
        List<String> statusList = new ArrayList<String>();
        statusList.add(EOrderStatus.PAY_YES.getCode());
        statusList.add(EOrderStatus.TO_APPROVE.getCode());
        statusList.add(EOrderStatus.TO_PRODU.getCode());
        statusList.add(EOrderStatus.PRODU_DOING.getCode());
        Long toSendOrder = orderBO.getTotalCount(userId, null, statusList);
        Long toCommentOrder = orderBO.getTotalCount(userId,
            EOrderStatus.RECEIVE.getCode(), null);
        res.setToMeasureOrder(toMeasureOrder);
        res.setToPayOrder(toPayOrder);
        res.setToSendOrder(toSendOrder);
        res.setToReceiverOrder(toReceiverOrder);
        res.setToCommentOrder(toCommentOrder);
        return res;
    }
}
