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
import com.cdkj.dzt.bo.IOrderBO;
import com.cdkj.dzt.bo.IProductBO;
import com.cdkj.dzt.bo.IProductSpecsBO;
import com.cdkj.dzt.bo.ISYSConfigBO;
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
import com.cdkj.dzt.domain.Order;
import com.cdkj.dzt.domain.Product;
import com.cdkj.dzt.domain.SYSConfig;
import com.cdkj.dzt.domain.SizeData;
import com.cdkj.dzt.domain.User;
import com.cdkj.dzt.dto.req.XN620200Req;
import com.cdkj.dzt.dto.res.BooleanRes;
import com.cdkj.dzt.dto.res.XN001400Res;
import com.cdkj.dzt.dto.res.XN620218Res;
import com.cdkj.dzt.enums.EBizType;
import com.cdkj.dzt.enums.EBoolean;
import com.cdkj.dzt.enums.ECommentStatus;
import com.cdkj.dzt.enums.ECurrency;
import com.cdkj.dzt.enums.EGeneratePrefix;
import com.cdkj.dzt.enums.EMeasureKey;
import com.cdkj.dzt.enums.EOrderStatus;
import com.cdkj.dzt.enums.EPayType;
import com.cdkj.dzt.enums.EReaction;
import com.cdkj.dzt.enums.ESysConfigCkey;
import com.cdkj.dzt.enums.ESysUser;
import com.cdkj.dzt.enums.ESystemCode;
import com.cdkj.dzt.enums.EUserKind;
import com.cdkj.dzt.enums.EUserLevel;
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
    private ICraftBO craftBO;

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
    private ISmsOutBO smsOutBO;

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
        String productCode = req.getProductCode();
        if (StringUtils.isNotBlank(productCode)) {
            // Map<String, String> map = new HashMap<String, String>();
            // 确定订单类型
            String type = null;
            Model model = null;
            String dProductCode = null;
            if (productCode.startsWith(EGeneratePrefix.MODEL.getCode())) {// 产品
                model = modelBO.getModel(productCode);
                productBO.saveProduct(order, model, 1);
            }
            if (productCode.startsWith(EGeneratePrefix.CLOTH.getCode())) {// 面料
                Cloth cloth = clothBO.getCloth(productCode);
                model = modelBO.getModel(cloth.getModelCode());
                dProductCode = productBO.saveProduct(order, model, 1);
                productSpecsBO.saveProductSpecs(cloth.getCode(), null,
                    cloth.getType(), cloth.getPic(), cloth.getBrand(),
                    cloth.getModelNum(), cloth.getAdvPic(), cloth.getColor(),
                    cloth.getFlowers(), cloth.getForm(), cloth.getWeight(),
                    cloth.getYarn(), cloth.getPrice(), dProductCode,
                    order.getCode());
            }
            if (productCode.startsWith(EGeneratePrefix.CRAFT.getCode())) {// 工艺
                Craft craft = craftBO.getCraft(productCode);
                model = modelBO.getModel(craft.getModelCode());
                dProductCode = productBO.saveProduct(order, model, 1);
                productSpecsBO.saveProductSpecs(craft.getCode(),
                    craft.getName(), craft.getType(), craft.getPic(), null,
                    null, null, null, null, null, null, null, craft.getPrice(),
                    dProductCode, order.getCode());

                // map.put(craft.getType(), craft.getCode());
                // Map<String, Craft> craftSmap = craftBO.getMap();
                // productSpecsBO.inputInforCraft(order, null, map, craftSmap);
            }
            type = model.getType();
            order.setType(type);
        }
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

        order.setUpdater(req.getUpdater());
        order.setUpdateDatetime(now);
        order.setRemark(req.getRemark());

        orderBO.applyOrder(order);
        // 落地量体数据(落地身高、体重)
        productSpecsBO.inputInforValue(order, req.getMap());
        // 落地身材数据(落地身高、体重)
        sizeDataBO.inputInforValue(req.getApplyUser(), req.getMap());
        // 如果有地区合伙人，短信通知
        if (!"0".equals(userId)) {
            smsOutBO.sentContent(
                userId,
                String.format(SysConstants.APPLY_CONTENT, code,
                    order.getApplyName()));
        }

        return code;
    }

    @Override
    public String applyOrder(String applyUser, String productCode) {
        // 获取最近订单
        Order lastOrder = orderBO.getLastOrder(applyUser);
        // 开始组装order
        Order order = new Order();
        Date now = new Date();
        String code = OrderNoGenerater.generateM(EGeneratePrefix.ORDER
            .getCode());
        order.setCode(code);
        if (StringUtils.isNotBlank(productCode)) {
            // Map<String, String> map = new HashMap<String, String>();
            // 确定订单类型
            String type = null;
            Model model = null;
            String dProductCode = null;
            if (productCode.startsWith(EGeneratePrefix.MODEL.getCode())) {// 产品
                model = modelBO.getModel(productCode);
                productBO.saveProduct(order, model, 1);
            }
            if (productCode.startsWith(EGeneratePrefix.CLOTH.getCode())) {// 面料
                Cloth cloth = clothBO.getCloth(productCode);
                model = modelBO.getModel(cloth.getModelCode());
                dProductCode = productBO.saveProduct(order, model, 1);
                productSpecsBO.saveProductSpecs(cloth.getCode(), null,
                    cloth.getType(), cloth.getPic(), cloth.getBrand(),
                    cloth.getModelNum(), cloth.getAdvPic(), cloth.getColor(),
                    cloth.getFlowers(), cloth.getForm(), cloth.getWeight(),
                    cloth.getYarn(), cloth.getPrice(), dProductCode,
                    order.getCode());
                // map.put(cloth.getType(), cloth.getCode());
                // Map<String, Cloth> clothSmap = clothBO.getMap();
                // productSpecsBO.inputInforCloth(order, null, map, clothSmap);
            }
            if (productCode.startsWith(EGeneratePrefix.CRAFT.getCode())) {// 工艺
                Craft craft = craftBO.getCraft(productCode);
                model = modelBO.getModel(craft.getModelCode());
                dProductCode = productBO.saveProduct(order, model, 1);
                productSpecsBO.saveProductSpecs(craft.getCode(),
                    craft.getName(), craft.getType(), craft.getPic(), null,
                    null, null, null, null, null, null, null, craft.getPrice(),
                    dProductCode, order.getCode());

                // map.put(craft.getType(), craft.getCode());
                // Map<String, Craft> craftSmap = craftBO.getMap();
                // productSpecsBO.inputInforCraft(order, null, map, craftSmap);
            }
            type = model.getType();
            order.setType(type);
        }
        order.setToUser(lastOrder.getToUser());
        order.setApplyUser(lastOrder.getApplyUser());
        order.setApplyName(lastOrder.getApplyName());
        order.setApplyMobile(lastOrder.getApplyMobile());

        order.setLtUser(lastOrder.getLtUser());
        order.setLtName(lastOrder.getLtName());

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

        // 一键复购，直接短信通知量体师
        smsOutBO.sentContent(
            order.getLtUser(),
            String.format(SysConstants.DISTRIBUTE_CONTENT, code,
                order.getApplyName()));
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
        orderBO.distributeOrder(order, ltUser, user.getRealName(), updater,
            remark);
        // 短信通知量体师
        smsOutBO.sentContent(
            ltUser,
            String.format(SysConstants.DISTRIBUTE_CONTENT, orderCode,
                order.getApplyName()));
    }

    // 衬衫定价
    @Override
    @Transactional
    public void confirmPrice(String orderCode, String modelCode,
            Integer quantity, String updater, String remark) {
        Order order = orderBO.getOrder(orderCode);
        if (null == order.getLtUser()) {
            throw new BizException("xn0000", "订单还未分配量体师，不可以定价");
        }
        if (!EOrderStatus.TO_MEASURE.getCode().equals(order.getStatus())) {
            throw new BizException("xn0000", "订单不处于待量体状态，不可以定价");
        }
        // 落地成衣
        Model model = modelBO.getModel(modelCode);
        String code = productBO.saveProduct(order, model, quantity);

        // 更新成衣数据
        productSpecsBO.refreshProductCode(orderCode, code);
        // 更新订单
        Long amount = model.getPrice() * quantity;
        orderBO.confirmPrice(order, model, amount, updater, remark);
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
        } else {
            throw new BizException("xn000000", "暂不支持该种支付方式");
        }
        return result;
    }

    private Object toPayOrderYE(String orderCode) {
        Order order = orderBO.getOrder(orderCode);
        Long totalAmount = order.getAmount();
        String userId = order.getApplyUser();
        accountBO.doTransferAmountRemote(userId, ECurrency.CNY,
            ESysUser.SYS_USER_DZT.getCode(), ECurrency.CNY, totalAmount,
            EBizType.AJ_GWFK, "HE-SHIRTS衬衫购买订单支付", "HE-SHIRTS衬衫购买订单支付",
            orderCode);
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
        return accountBO.doWeiXinH5PayRemote(userId, user.getOpenId(),
            ESysUser.SYS_USER_DZT.getCode(), payGroup, orderCode,
            EBizType.AJ_GWFK.getCode(), "HE-SHIRTS衬衫购买订单支付", totalAmount);
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

    // 数据录入
    @Override
    @Transactional
    public void inputInfor(String orderCode, List<String> codeList,
            Map<String, String> map, String updater, String remark) {
        Order order = orderBO.getOrder(orderCode);
        if (!EOrderStatus.PAY_YES.getCode().equals(order.getStatus())) {
            throw new BizException("xn000000", "订单尚未支付,不能录入数据");
        }
        Product product = productBO.getProductByOrderCode(orderCode);
        // 更新订单
        orderBO.inputInfor(order, map.get(EMeasureKey.YJDZ.getCode()), updater,
            remark);
        // 落地量体数据
        // productSpecsBO.removeProductSpecs(product.getCode());
        Cloth cloth = null;
        Craft craft = null;
        List<Cloth> clothList = new ArrayList<Cloth>();
        List<Craft> craftList = new ArrayList<Craft>();
        for (String code : codeList) {
            if (code.startsWith(EGeneratePrefix.CLOTH.getCode())) {
                cloth = clothBO.getCloth(code);
                clothList.add(cloth);
            }
            if (code.startsWith(EGeneratePrefix.CRAFT.getCode())) {
                craft = craftBO.getCraft(code);
                craftList.add(craft);
            }
        }
        productSpecsBO.inputInforValue(order, product, map);
        productSpecsBO.inputInforCloth(order, product, clothList);
        productSpecsBO.inputInforCraft(order, product, craftList);
    }

    // 提交复核
    @Override
    public void ltSubmit(String orderCode, String updater) {
        Order order = orderBO.getRichOrder(orderCode);
        if (!EOrderStatus.PAY_YES.getCode().equals(order.getStatus())) {
            throw new BizException("xn000000", "订单不是已支付状态,不能提交审核");
        }
        // 确保所有规格已经填充完毕
        orderBO.checkInfoFull(order);
        orderBO.ltSubmit(order, updater);
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
        // 更新用户最后下单时间
        userBO
            .refreshLastOrderDatetime(order.getApplyUser(), DateUtil.dateToStr(
                order.getCreateDatetime(), DateUtil.DATA_TIME_PATTERN_1));
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
            // 退款
            accountBO.doTransferAmountRemote(ESysUser.SYS_USER_DZT.getCode(),
                ECurrency.CNY, order.getApplyUser(), ECurrency.CNY,
                order.getAmount(), EBizType.AJ_TK, "订单：" + orderCode + "取消后退款",
                "订单：" + orderCode + "取消后退款", orderCode);
        } else if (EOrderStatus.TO_MEASURE.getCode().equals(order.getStatus())
                || EOrderStatus.ASSIGN_PRICE.getCode()
                    .equals(order.getStatus())) {
            orderBO.cancelOrder(order, updater, remark);
        } else {
            throw new BizException("xn000000", "订单不处于可取消订单状态,不能取消订单");
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

    // 计算价格
    @Override
    public Long calculatePrice(String orderCode, List<String> codeList,
            String quantity) {
        Order order = orderBO.getOrder(orderCode);
        Model model = null;
        Cloth cloth = null;
        Craft craft = null;
        List<Craft> craftList = new ArrayList<Craft>();
        List<Cloth> clothList = new ArrayList<Cloth>();
        Double price = 0.0;
        for (String code : codeList) {
            if (code.startsWith(EGeneratePrefix.MODEL.getCode())) {
                model = modelBO.getModel(code);
            }
            if (code.startsWith(EGeneratePrefix.CLOTH.getCode())) {
                cloth = clothBO.getCloth(code);
                clothList.add(cloth);
            }
            if (code.startsWith(EGeneratePrefix.CLOTH.getCode())) {
                craft = craftBO.getCraft(code);
                craftList.add(craft);
            }
        }
        // 售价=1.76*（面料单价*面料单耗+加工费+工艺费）+2.06*（快递费+包装费）
        // 计算面料价格
        // 计算工艺价格
        Long clothPrice = 0L;
        Long craftPrice = 0L;
        for (Cloth cloth1 : clothList) {
            clothPrice = clothPrice + cloth1.getPrice();
        }
        for (Craft craft1 : craftList) {
            craftPrice = craftPrice + craft1.getPrice();
        }
        // 快递费
        Long kdfPrice = StringValidater.toLong(sysConfigBO.getConfigValue(
            ESysConfigCkey.KDF.getCode(), ESystemCode.DZT.getCode(),
            ESystemCode.DZT.getCode()).getCvalue());
        // 包装费
        Long bzfPrice = StringValidater.toLong(sysConfigBO.getConfigValue(
            ESysConfigCkey.BZF.getCode(), ESystemCode.DZT.getCode(),
            ESystemCode.DZT.getCode()).getCvalue());
        price = 1.76
                * (clothPrice * model.getLoss() + model.getProcessFee() + craftPrice)
                + 2.06 * (kdfPrice + bzfPrice);
        XN001400Res user = userBO.getRemoteUser(order.getApplyUser());
        Double rate = StringValidater.toDouble(sysConfigBO.getConfigValue(
            ESysConfigCkey.FHY.getCode(), ESystemCode.DZT.getCode(),
            ESystemCode.DZT.getCode()).getCvalue());

        if (StringValidater.toInteger(user.getLevel()) > 1) {
            rate = 1.0;
        }
        Long truePrice = AmountUtil.rmbJinFen(price)
                * StringValidater.toLong(quantity);
        truePrice = AmountUtil.mul(truePrice, rate);
        return truePrice;
    }

    // H+定价
    @Override
    public void confirmPrice(String orderCode, List<String> codeList,
            Integer quantity, String updater, String remark) {
        Order order = orderBO.getOrder(orderCode);
        if (!EOrderStatus.TO_MEASURE.getCode().equals(order.getStatus())) {
            throw new BizException("xn0000", "订单不处于待量体状态，不可以定价");
        }
        Double price = 0.0;
        String productCode = null;
        Model model = null;
        List<Craft> craftList = new ArrayList<Craft>();
        List<Cloth> clothList = new ArrayList<Cloth>();
        for (String code : codeList) {
            if (code.startsWith(EGeneratePrefix.MODEL.getCode())) {
                model = modelBO.getModel(code);
                productCode = productBO.saveProduct(order, model, quantity);
            }
            if (code.startsWith(EGeneratePrefix.CLOTH.getCode())) {
                Cloth cloth = clothBO.getCloth(code);
                clothList.add(cloth);
            }
            if (code.startsWith(EGeneratePrefix.CRAFT.getCode())) {
                Craft craft = craftBO.getCraft(code);
                craftList.add(craft);
            }
        }
        if (StringUtils.isBlank(productCode)) {
            productCode = productBO.getProductByOrderCode(orderCode).getCode();
        }
        // 售价=1.76*（面料单价*面料单耗+加工费+工艺费）+2.06*（快递费+包装费）
        // 计算面料价格
        // 计算工艺价格
        Long clothPrice = 0L;
        Long craftPrice = 0L;
        for (Cloth cloth1 : clothList) {
            clothPrice = clothPrice + cloth1.getPrice();
            productSpecsBO.saveProductSpecs(cloth1.getCode(), null,
                cloth1.getType(), cloth1.getPic(), cloth1.getBrand(),
                cloth1.getModelNum(), cloth1.getAdvPic(), cloth1.getColor(),
                cloth1.getFlowers(), cloth1.getForm(), cloth1.getWeight(),
                cloth1.getYarn(), cloth1.getPrice(), productCode,
                order.getCode());
        }
        for (Craft craft1 : craftList) {
            craftPrice = craftPrice + craft1.getPrice();
            productSpecsBO.saveProductSpecs(craft1.getCode(), craft1.getName(),
                craft1.getType(), craft1.getPic(), null, null, null, null,
                null, null, null, null, craft1.getPrice(), productCode,
                order.getCode());
        }
        // 快递费
        Long kdfPrice = StringValidater.toLong(sysConfigBO.getConfigValue(
            ESysConfigCkey.KDF.getCode(), ESystemCode.DZT.getCode(),
            ESystemCode.DZT.getCode()).getCvalue());
        // 包装费
        Long bzfPrice = StringValidater.toLong(sysConfigBO.getConfigValue(
            ESysConfigCkey.BZF.getCode(), ESystemCode.DZT.getCode(),
            ESystemCode.DZT.getCode()).getCvalue());
        price = 1.76
                * (clothPrice * model.getLoss() + model.getProcessFee() + craftPrice)
                + 2.06 * (kdfPrice + bzfPrice);
        XN001400Res user = userBO.getRemoteUser(order.getApplyUser());
        Double rate = StringValidater.toDouble(sysConfigBO.getConfigValue(
            ESysConfigCkey.FHY.getCode(), ESystemCode.DZT.getCode(),
            ESystemCode.DZT.getCode()).getCvalue());
        if (StringValidater.toInteger(user.getLevel()) > 1) {
            rate = 1.0;
        }
        Long truePrice = AmountUtil.rmbJinFen(price);
        truePrice = AmountUtil.mul(truePrice, rate) * quantity;
        orderBO.confirmPrice(order, model, truePrice, updater, remark);
    }

    @Override
    public void inputInfor(String orderCode, Map<String, String> map,
            String updater, String remark) {
        Order order = orderBO.getOrder(orderCode);
        if (!EOrderStatus.PAY_YES.getCode().equals(order.getStatus())) {
            throw new BizException("xn000000", "订单尚未支付,不能录入数据");
        }
        Product product = productBO.getProductByOrderCode(orderCode);
        productSpecsBO.inputInforValue(order, product, map);
        // 更新订单
        orderBO.inputInfor(order, map.get(EMeasureKey.YJDZ.getCode()), updater,
            remark);
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
                    EOrderStatus.FILED);
                // 如果是第一次下单成功,推荐人获得1500积分
                // 其他情况获得比例积分
                if (count > 1) {
                    // 获取多少积分比例
                    Long rate = StringValidater.toLong(sysConfigBO
                        .getConfigValue(ESysConfigCkey.DCTJ.getCode(),
                            ESystemCode.DZT.getCode(),
                            ESystemCode.DZT.getCode()).getCvalue()) * 1000;
                    // 兑换成多少积分
                    Long amount = order.getAmount() / rate;
                    accountBO.doTransferAmountRemote(
                        ESysUser.SYS_USER_DZT.getCode(), ECurrency.JF,
                        user.getUserReferee(), ECurrency.JF, amount,
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
                        EBizType.DCTJ, EBizType.DCTJ.getValue(),
                        EBizType.DCTJ.getValue(), order.getCode());
                }
            }
        }
        // 计算积分
        // 购买者如果是会员
        if (StringValidater.toInteger(user.getLevel()) > 1) {
            // 获取多少积分比例
            Long rate = StringValidater.toLong(sysConfigBO.getConfigValue(
                ESysConfigCkey.YHHD.getCode(), ESystemCode.DZT.getCode(),
                ESystemCode.DZT.getCode()).getCvalue()) * 1000;
            // 兑换成多少积分
            Long amount = order.getAmount() / rate;
            accountBO.doTransferAmountRemote(ESysUser.SYS_USER_DZT.getCode(),
                ECurrency.JF, user.getUserReferee(), ECurrency.JF, amount,
                EBizType.DCTJ, EBizType.DCTJ.getValue(),
                EBizType.DCTJ.getValue(), order.getCode());
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
                    EOrderStatus.FILED);
                // 如果是第一次下单成功,推荐人获得1500经验
                // 其他情况获得比例积分
                if (count > 1) {
                    if (StringValidater.toInteger(user.getLevel()) > 1) {// 如果下单用户时会员
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
                            EBizType.HSCJY, EBizType.HSCJY.getValue(),
                            EBizType.HSCJY.getValue(), order.getCode());
                    } else {// 下单用户不是会员
                        // 获取多少积分比例
                        Double rate = StringValidater.toDouble(sysConfigBO
                            .getConfigValue(ESysConfigCkey.FDCJY.getCode(),
                                ESystemCode.DZT.getCode(),
                                ESystemCode.DZT.getCode()).getCvalue());
                        // 兑换成多少积分
                        Long amount = AmountUtil.mul(order.getAmount(), rate);
                        accountBO.doTransferAmountRemote(
                            ESysUser.SYS_USER_DZT.getCode(), ECurrency.JF,
                            user.getUserReferee(), ECurrency.JF, amount,
                            EBizType.FDCJY, EBizType.FDCJY.getValue(),
                            EBizType.FDCJY.getValue(), order.getCode());
                    }
                } else {
                    // 首次直接加1500
                    Long amount = StringValidater.toLong(sysConfigBO
                        .getConfigValue(ESysConfigCkey.HSCJY.getCode(),
                            ESystemCode.DZT.getCode(),
                            ESystemCode.DZT.getCode()).getCvalue()) * 1000;
                    accountBO.doTransferAmountRemote(
                        ESysUser.SYS_USER_DZT.getCode(), ECurrency.JF,
                        user.getUserReferee(), ECurrency.JF, amount,
                        EBizType.HSCJY, EBizType.HSCJY.getValue(),
                        EBizType.HSCJY.getValue(), order.getCode());
                }
            }
            Account account = accountBO.getRemoteAccount(user.getUserReferee(),
                ECurrency.JY);
            Integer level = 0;
            for (SYSConfig sysConfig : sysConfigList) {
                if (StringValidater.toLong(sysConfig.getCvalue()) < account
                    .getAmount()) {
                    level = StringValidater.toInteger(user1.getLevel()) + 1;
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
            // 获取多少积分比例
            Double rate = StringValidater.toDouble(sysConfigBO.getConfigValue(
                ESysConfigCkey.YHJY.getCode(), ESystemCode.DZT.getCode(),
                ESystemCode.DZT.getCode()).getCvalue());
            // 兑换成多少积分
            Long amount = AmountUtil.mul(order.getAmount(), rate);
            accountBO.doTransferAmountRemote(ESysUser.SYS_USER_DZT.getCode(),
                ECurrency.JF, user.getUserReferee(), ECurrency.JF, amount,
                EBizType.DCTJ, EBizType.DCTJ.getCode(),
                EBizType.DCTJ.getCode(), order.getCode());
            Account account = accountBO.getRemoteAccount(order.getApplyUser(),
                ECurrency.JY);
            Integer level = 0;
            for (SYSConfig sysConfig : sysConfigList) {
                if (StringValidater.toLong(sysConfig.getCvalue()) < account
                    .getAmount()) {
                    level = StringValidater.toInteger(user.getLevel()) + 1;
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
        if (EPayType.WEIXIN.getCode().equals(payType)) {
            XN001400Res user = userBO.getRemoteUser(userId);
            if (StringValidater.toInteger(user.getLevel()) > 1) {
                throw new BizException("xn0000", "您已经是VIP会员了,无需重复充值");
            }
            Long totalAmount = StringValidater.toLong(sysConfigBO
                .getConfigValue(ESysConfigCkey.HYF.getCode(),
                    ESystemCode.DZT.getCode(), ESystemCode.DZT.getCode())
                .getCvalue());
            return accountBO.doWeiXinH5PayRemote(userId, user.getOpenId(),
                ESysUser.SYS_USER_DZT.getCode(), userId, userId,
                EBizType.AJ_HYCZ.getCode(), EBizType.AJ_HYCZ.getValue(),
                totalAmount);
        } else {
            throw new BizException("xn0000", "暂不支持其他支付方式");
        }
    }

    @Override
    public void paySuccessVIP(String payGroup, String payCode, Long amount) {
        XN001400Res user = userBO.getRemoteUser(payGroup);
        if (StringValidater.toInteger(user.getLevel()) < 2) {
            userBO.doUpLevel(payGroup, EUserLevel.TWO.getCode());
        } else {
            logger.info("已支付，重复回调");
        }
    }

    @Override
    public XN620218Res getLastOrder(String applyUser) {
        XN620218Res res = new XN620218Res();
        Order order = orderBO.getIsLastOrder(applyUser);
        Map<String, String> map = new HashMap<String, String>();
        List<SizeData> sizeDataList = sizeDataBO.querySizeDataList(applyUser);
        for (SizeData sizeData : sizeDataList) {
            if (sizeData.getCkey().equals(EMeasureKey.SG.getCode())) {
                map.put(sizeData.getCkey(), sizeData.getCvalue());
            }
            if (sizeData.getCkey().equals(EMeasureKey.TZ.getCode())) {
                map.put(sizeData.getCkey(), sizeData.getCvalue());
            }
        }
        res.setOrder(order);
        res.setMap(map);
        return res;
    }

    @Override
    public void refreshFrequent() {
        // 半年
        Date createDatetimeEnd = new Date();
        Date createDatetimeStartB = DateUtil.getRelativeDateOfDays(new Date(),
            -90);
        List<Order> orderListB = orderBO.getGroupTotalCount(
            createDatetimeStartB, createDatetimeEnd);
        if (CollectionUtils.isNotEmpty(orderListB)) {
            for (Order order : orderListB) {
                if (order.getCount() <= 0) {
                    userBO.refreshFrequent(order.getApplyUser(), "6");
                }
            }
        }

        // 三个月
        Date createDatetimeStartS = DateUtil.getRelativeDateOfDays(new Date(),
            -30);
        List<Order> orderListS = orderBO.getGroupTotalCount(
            createDatetimeStartS, createDatetimeEnd);
        if (CollectionUtils.isNotEmpty(orderListS)) {
            for (Order order : orderListS) {
                if (order.getCount() <= 0) {
                    userBO.refreshFrequent(order.getApplyUser(), "5");
                }
                if (order.getCount() >= 5) {
                    userBO.refreshFrequent(order.getApplyUser(), "4");
                }
                if (order.getCount() < 5 || order.getCount() >= 3) {
                    userBO.refreshFrequent(order.getApplyUser(), "3");
                }
                if (order.getCount() <= 2) {
                    userBO.refreshFrequent(order.getApplyUser(), "2");
                }
                if (order.getCount() < 2 || order.getCount() >= 1) {
                    userBO.refreshFrequent(order.getApplyUser(), "1");
                }
            }
        }
    }
}
