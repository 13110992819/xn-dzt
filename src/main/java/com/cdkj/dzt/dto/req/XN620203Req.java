package com.cdkj.dzt.dto.req;

import java.util.List;

/**
 * 产品定价（H+）
 * @author: asus 
 * @since: 2017年8月17日 下午9:59:32 
 * @history:
 */
public class XN620203Req {
    private String token;

    // 订单编号
    private String orderCode;

    // 数量
    private String modelCode;

    // 数量
    private String quantity;

    // 编号List
    private List<XN620801Req> reqList;

    // 地址
    private String address;

    // 更新人
    private String updater;

    // 备注
    private String remark;

    public String getOrderCode() {
        return orderCode;
    }

    public void setOrderCode(String orderCode) {
        this.orderCode = orderCode;
    }

    public String getUpdater() {
        return updater;
    }

    public void setUpdater(String updater) {
        this.updater = updater;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    public String getModelCode() {
        return modelCode;
    }

    public void setModelCode(String modelCode) {
        this.modelCode = modelCode;
    }

    public List<XN620801Req> getReqList() {
        return reqList;
    }

    public void setReqList(List<XN620801Req> reqList) {
        this.reqList = reqList;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

}
