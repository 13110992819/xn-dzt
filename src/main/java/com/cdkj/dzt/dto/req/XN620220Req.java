package com.cdkj.dzt.dto.req;

import com.cdkj.dzt.dto.req.APageReq;

/**
 * 订单分页查询
 * @author: asus 
 * @since: 2017年4月17日 下午8:11:56 
 * @history:
 */
public class XN620220Req extends APageReq {

    /** 
     * @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么) 
     */
    private static final long serialVersionUID = 3598608816628978402L;

    // 所属合伙人（选填）
    private String toUser;

    // 下单人UserId（选填）
    private String applyUser;

    // 下单人姓名（选填）
    private String applyName;

    // 下单人手机号（选填）
    private String applyMobile;

    // 量体地址省（必填）
    private String ltProvince;

    // 量体地址市（选填）
    private String ltCity;

    // 量体地址区（选填）
    private String ltArea;

    // 量体地址详细（选填）
    private String ltAddress;

    // 状态（选填）
    private String status;

    // 量体师UserId（选填）
    private String ltUser;

    // 量体师姓名（选填）
    private String ltName;

    // 发货人编号（选填）
    private String deliverer;

    // 物流公司编号（选填）
    private String logisticsCompany;

    // 物流单号（选填）
    private String logisticsCode;

    // 收件人姓名（选填）
    private String receiver;

    // 收件人电话（选填）
    private String reMobile;

    // 收货地址（选填）
    private String reAddress;

    // 更新人（选填）
    private String updater;

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

    public String getDeliverer() {
        return deliverer;
    }

    public void setDeliverer(String deliverer) {
        this.deliverer = deliverer;
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

    public String getUpdater() {
        return updater;
    }

    public void setUpdater(String updater) {
        this.updater = updater;
    }

}
