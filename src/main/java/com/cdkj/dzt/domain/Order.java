package com.cdkj.dzt.domain;

import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import com.cdkj.dzt.dao.base.ABaseDO;
import com.cdkj.dzt.dto.res.XN001400Res;

/**
* 订单
* @author: haiqingzheng
* @since: 2017年04月14日 13:30:27
* @history:
*/
public class Order extends ABaseDO {

    private static final long serialVersionUID = 1L;

    // 编号
    private String code;

    // 类型
    private String type;

    // 所属合伙人
    private String toUser;

    // 下单人UserId
    private String applyUser;

    // 下单人姓名
    private String applyName;

    // 下单人手机号
    private String applyMobile;

    // 量体时间
    private Date ltDatetime;

    // 量体地址省（必填）
    private String ltProvince;

    // 量体地址市（必填）
    private String ltCity;

    // 量体地址区（必填）
    private String ltArea;

    // 量体地址详细（必填）
    private String ltAddress;

    // 量体叮嘱
    private String applyNote;

    // 下单时间
    private Date createDatetime;

    // 状态
    private String status;

    // 量体师UserId
    private String ltUser;

    // 量体师姓名
    private String ltName;

    // 订单总金额
    private Long amount;

    // 支付类型
    private String payType;

    // 支付组号
    private String payGroup;

    // 支付编号
    private String payCode;

    // 支付时间
    private Date payDatetime;

    // 支付金额
    private Long payAmount;

    // 发货人编号
    private String deliverer;

    // 发货时间
    private Date deliveryDatetime;

    // 物流公司编号
    private String logisticsCompany;

    // 物流单号
    private String logisticsCode;

    // 物流单
    private String pdf;

    // 收件人姓名
    private String receiver;

    // 收件人电话
    private String reMobile;

    // 收货地址
    private String reAddress;

    // 发票类型(1 个人，2 企业)
    private String receiptType;

    // 发票抬头
    private String receiptTitle;

    // 运费
    private Long yunfei;

    // 更新人
    private String updater;

    // 更新时间
    private Date updateDatetime;

    // 备注
    private String remark;

    // -------db propties--------------
    private List<Product> productList;

    private XN001400Res ltUserDO;

    // 搜索条件
    private List<String> statusList;

    // 创建时间开始
    private Date createDatetimeStart;

    // 创建时间结束
    private Date createDatetimeEnd;

    // 数量
    private Long count;

    // 订单号、申请人手机号、申请人姓名
    private String burry;

    // 模板编号
    private String modelCode;

    // 评论
    private Comment comment;

    // 编号
    private String codeForQuery;

    private Map<String, LinkedHashMap<String, ProductSpecs>> resultMap;

    public List<String> getStatusList() {
        return statusList;
    }

    public void setStatusList(List<String> statusList) {
        this.statusList = statusList;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getToUser() {
        return toUser;
    }

    public void setToUser(String toUser) {
        this.toUser = toUser;
    }

    public String getApplyUser() {
        return applyUser;
    }

    public void setApplyUser(String applyUser) {
        this.applyUser = applyUser;
    }

    public String getApplyName() {
        return applyName;
    }

    public void setApplyName(String applyName) {
        this.applyName = applyName;
    }

    public String getApplyMobile() {
        return applyMobile;
    }

    public void setApplyMobile(String applyMobile) {
        this.applyMobile = applyMobile;
    }

    public Date getLtDatetime() {
        return ltDatetime;
    }

    public void setLtDatetime(Date ltDatetime) {
        this.ltDatetime = ltDatetime;
    }

    public String getLtProvince() {
        return ltProvince;
    }

    public void setLtProvince(String ltProvince) {
        this.ltProvince = ltProvince;
    }

    public String getLtCity() {
        return ltCity;
    }

    public void setLtCity(String ltCity) {
        this.ltCity = ltCity;
    }

    public String getLtArea() {
        return ltArea;
    }

    public void setLtArea(String ltArea) {
        this.ltArea = ltArea;
    }

    public String getLtAddress() {
        return ltAddress;
    }

    public void setLtAddress(String ltAddress) {
        this.ltAddress = ltAddress;
    }

    public String getApplyNote() {
        return applyNote;
    }

    public void setApplyNote(String applyNote) {
        this.applyNote = applyNote;
    }

    public Date getCreateDatetime() {
        return createDatetime;
    }

    public void setCreateDatetime(Date createDatetime) {
        this.createDatetime = createDatetime;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getLtUser() {
        return ltUser;
    }

    public void setLtUser(String ltUser) {
        this.ltUser = ltUser;
    }

    public String getLtName() {
        return ltName;
    }

    public void setLtName(String ltName) {
        this.ltName = ltName;
    }

    public Long getAmount() {
        return amount;
    }

    public void setAmount(Long amount) {
        this.amount = amount;
    }

    public String getPayType() {
        return payType;
    }

    public void setPayType(String payType) {
        this.payType = payType;
    }

    public String getPayGroup() {
        return payGroup;
    }

    public void setPayGroup(String payGroup) {
        this.payGroup = payGroup;
    }

    public String getPayCode() {
        return payCode;
    }

    public void setPayCode(String payCode) {
        this.payCode = payCode;
    }

    public Date getPayDatetime() {
        return payDatetime;
    }

    public void setPayDatetime(Date payDatetime) {
        this.payDatetime = payDatetime;
    }

    public Long getPayAmount() {
        return payAmount;
    }

    public void setPayAmount(Long payAmount) {
        this.payAmount = payAmount;
    }

    public String getDeliverer() {
        return deliverer;
    }

    public void setDeliverer(String deliverer) {
        this.deliverer = deliverer;
    }

    public Date getDeliveryDatetime() {
        return deliveryDatetime;
    }

    public void setDeliveryDatetime(Date deliveryDatetime) {
        this.deliveryDatetime = deliveryDatetime;
    }

    public String getLogisticsCompany() {
        return logisticsCompany;
    }

    public void setLogisticsCompany(String logisticsCompany) {
        this.logisticsCompany = logisticsCompany;
    }

    public String getLogisticsCode() {
        return logisticsCode;
    }

    public void setLogisticsCode(String logisticsCode) {
        this.logisticsCode = logisticsCode;
    }

    public String getPdf() {
        return pdf;
    }

    public void setPdf(String pdf) {
        this.pdf = pdf;
    }

    public String getReceiver() {
        return receiver;
    }

    public void setReceiver(String receiver) {
        this.receiver = receiver;
    }

    public String getReMobile() {
        return reMobile;
    }

    public void setReMobile(String reMobile) {
        this.reMobile = reMobile;
    }

    public String getReAddress() {
        return reAddress;
    }

    public void setReAddress(String reAddress) {
        this.reAddress = reAddress;
    }

    public String getReceiptType() {
        return receiptType;
    }

    public void setReceiptType(String receiptType) {
        this.receiptType = receiptType;
    }

    public String getReceiptTitle() {
        return receiptTitle;
    }

    public void setReceiptTitle(String receiptTitle) {
        this.receiptTitle = receiptTitle;
    }

    public Long getYunfei() {
        return yunfei;
    }

    public void setYunfei(Long yunfei) {
        this.yunfei = yunfei;
    }

    public String getUpdater() {
        return updater;
    }

    public void setUpdater(String updater) {
        this.updater = updater;
    }

    public Date getUpdateDatetime() {
        return updateDatetime;
    }

    public void setUpdateDatetime(Date updateDatetime) {
        this.updateDatetime = updateDatetime;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public List<Product> getProductList() {
        return productList;
    }

    public void setProductList(List<Product> productList) {
        this.productList = productList;
    }

    public XN001400Res getLtUserDO() {
        return ltUserDO;
    }

    public void setLtUserDO(XN001400Res ltUserDO) {
        this.ltUserDO = ltUserDO;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Date getCreateDatetimeStart() {
        return createDatetimeStart;
    }

    public void setCreateDatetimeStart(Date createDatetimeStart) {
        this.createDatetimeStart = createDatetimeStart;
    }

    public Date getCreateDatetimeEnd() {
        return createDatetimeEnd;
    }

    public void setCreateDatetimeEnd(Date createDatetimeEnd) {
        this.createDatetimeEnd = createDatetimeEnd;
    }

    public Long getCount() {
        return count;
    }

    public void setCount(Long count) {
        this.count = count;
    }

    public String getBurry() {
        return burry;
    }

    public void setBurry(String burry) {
        this.burry = burry;
    }

    public Map<String, LinkedHashMap<String, ProductSpecs>> getResultMap() {
        return resultMap;
    }

    public void setResultMap(
            Map<String, LinkedHashMap<String, ProductSpecs>> resultMap) {
        this.resultMap = resultMap;
    }

    public String getModelCode() {
        return modelCode;
    }

    public void setModelCode(String modelCode) {
        this.modelCode = modelCode;
    }

    public Comment getComment() {
        return comment;
    }

    public void setComment(Comment comment) {
        this.comment = comment;
    }

    public String getCodeForQuery() {
        return codeForQuery;
    }

    public void setCodeForQuery(String codeForQuery) {
        this.codeForQuery = codeForQuery;
    }

}
