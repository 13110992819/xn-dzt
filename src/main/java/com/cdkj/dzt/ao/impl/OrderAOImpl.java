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
import com.cdkj.dzt.bo.ICraftBO;
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
import com.cdkj.dzt.domain.Cloth;
import com.cdkj.dzt.domain.Craft;
import com.cdkj.dzt.domain.Model;
import com.cdkj.dzt.domain.Order;
import com.cdkj.dzt.domain.Product;
import com.cdkj.dzt.domain.User;
import com.cdkj.dzt.dto.req.XN620200Req;
import com.cdkj.dzt.dto.res.BooleanRes;
import com.cdkj.dzt.dto.res.XN001400Res;
import com.cdkj.dzt.enums.EBizType;
import com.cdkj.dzt.enums.EBoolean;
import com.cdkj.dzt.enums.ECurrency;
import com.cdkj.dzt.enums.EGeneratePrefix;
import com.cdkj.dzt.enums.EMeasureKey;
import com.cdkj.dzt.enums.EOrderStatus;
import com.cdkj.dzt.enums.EPayType;
import com.cdkj.dzt.enums.ESysConfigCkey;
import com.cdkj.dzt.enums.ESysUser;
import com.cdkj.dzt.enums.ESystemCode;
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
            if (productCode.startsWith(EGeneratePrefix.MODEL.getCode())) {// 产品
                model = modelBO.getModel(productCode);
                productBO.saveProduct(order, model, 1);
            }
            if (productCode.startsWith(EGeneratePrefix.CLOTH.getCode())) {// 面料
                Cloth cloth = clothBO.getCloth(productCode);
                model = modelBO.getModel(cloth.getModelCode());
                productSpecsBO.saveProductSpecs(cloth.getCode(), null, null,
                    cloth.getPic(), cloth.getType(), null, order.getCode());
            }
            if (productCode.startsWith(EGeneratePrefix.CRAFT.getCode())) {// 工艺
                Craft craft = craftBO.getCraft(productCode);
                model = modelBO.getModel(craft.getModelCode());
                productSpecsBO.saveProductSpecs(craft.getCode(),
                    craft.getName(), null, craft.getPic(), craft.getType(),
                    null, order.getCode());

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
            Map<String, String> map = new HashMap<String, String>();
            // 确定订单类型
            String type = null;
            Model model = null;
            if (productCode.startsWith(EGeneratePrefix.MODEL.getCode())) {// 产品
                model = modelBO.getModel(productCode);
                productBO.saveProduct(order, model, 1);
            }
            if (productCode.startsWith(EGeneratePrefix.CLOTH.getCode())) {// 面料
                Cloth cloth = clothBO.getCloth(productCode);
                model = modelBO.getModel(cloth.getModelCode());

                productSpecsBO.saveProductSpecs(cloth.getCode(), null, null,
                    cloth.getPic(), cloth.getType(), null, order.getCode());
                // map.put(cloth.getType(), cloth.getCode());
                // Map<String, Cloth> clothSmap = clothBO.getMap();
                // productSpecsBO.inputInforCloth(order, null, map, clothSmap);
            }
            if (productCode.startsWith(EGeneratePrefix.CRAFT.getCode())) {// 工艺
                Craft craft = craftBO.getCraft(productCode);
                model = modelBO.getModel(craft.getModelCode());

                productSpecsBO.saveProductSpecs(craft.getCode(),
                    craft.getName(), null, craft.getPic(), craft.getType(),
                    null, order.getCode());

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
        // 合伙人和量体师进行分成
        doFenCheng(order);
    }

    private void doFenCheng(Order order) {
        // 合伙人分成
        String parterUserId = order.getToUser();
        if (StringUtils.isNotBlank(parterUserId) && !"0".equals(parterUserId)) {
            XN001400Res parter = userBO.getRemoteUser(parterUserId);
            Long amount = Double.valueOf(
                order.getAmount() * Double.valueOf(parter.getDivRate()))
                .longValue();
            // 分成金额至少是一分钱
            if (amount > 10) {
                accountBO.doTransferAmountRemote(
                    ESysUser.SYS_USER_DZT.getCode(), ECurrency.CNY,
                    parterUserId, ECurrency.CNY, amount, EBizType.AJ_HHRFC,
                    "订单：" + order.getCode() + " 合伙人分成", "订单：" + order.getCode()
                            + " 分成收入", order.getCode());
                // 短信通知
                smsOutBO.sentContent(
                    parterUserId,
                    String.format(SysConstants.FENCHENG_CONTENT, "合伙人",
                        order.getCode(), CalculationUtil.divi(amount)));
            }
        }
        // 量体师分成
        String ltUserId = order.getLtUser();
        if (StringUtils.isNotBlank(ltUserId)) {
            XN001400Res ltUser = userBO.getRemoteUser(ltUserId);
            Long amount = Double.valueOf(
                order.getAmount() * Double.valueOf(ltUser.getDivRate()))
                .longValue();
            if (amount > 10) {
                accountBO.doTransferAmountRemote(
                    ESysUser.SYS_USER_DZT.getCode(), ECurrency.CNY, ltUserId,
                    ECurrency.CNY, amount, EBizType.AJ_LTSFC,
                    "订单：" + order.getCode() + " 量体师分成", "订单：" + order.getCode()
                            + " 分成收入", order.getCode());
                // 短信通知
                smsOutBO.sentContent(
                    ltUserId,
                    String.format(SysConstants.FENCHENG_CONTENT, "量体师",
                        order.getCode(), CalculationUtil.divi(amount)));
            }
        }
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
    public Long calculatePrice(List<String> codeList) {
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
        Long truePrice = AmountUtil.rmbJinFen(price);
        return truePrice;
    }

    @Override
    public void confirmPrice(String orderCode, List<String> codeList,
            Integer quantity, String updater, String remark) {
        Order order = orderBO.getOrder(orderCode);
        Double price = 0.0;
        String productCode = null;
        Model model = null;
        List<Craft> craftList = new ArrayList<Craft>();
        List<Cloth> clothList = new ArrayList<Cloth>();
        for (String code : codeList) {
            if (code.startsWith(EGeneratePrefix.MODEL.getCode())) {
                model = modelBO.getModel(code);
                productBO.saveProduct(order, model, quantity);
            }
            if (code.startsWith(EGeneratePrefix.CLOTH.getCode())) {
                Cloth cloth = clothBO.getCloth(code);
                clothList.add(cloth);
            }
            if (code.startsWith(EGeneratePrefix.CLOTH.getCode())) {
                Craft craft = craftBO.getCraft(code);
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
            productSpecsBO.saveProductSpecs(cloth1.getCode(), null, null,
                cloth1.getPic(), cloth1.getType(), productCode, orderCode);
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
        Long truePrice = AmountUtil.rmbJinFen(price);
    }
}
