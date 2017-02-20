package com.xnjr.mall.domain;

import com.xnjr.mall.dao.base.ABaseDO;

/**
* 定向红包
* @author: xieyj 
* @since: 2017年02月20日 13:17:06
* @history:
*/
public class HzbMgift extends ABaseDO {

    private static final long serialVersionUID = 1L;

    // 编号
    private String code;

    // 广告标题
    private String advTitle;

    // 树主人
    private String owner;

    // 树主人币种
    private String ownerCurrency;

    // 树主人金额
    private String ownerAmount;

    // 接收人币种
    private String receiveCurrency;

    // 接收人金额
    private String receiveAmount;

    // 接收人
    private String receiver;

    // 接收时间
    private String receiveDatetime;

    // 状态(0 待发送 1 已发送，待领取 2 已领取 3 已失效)
    private String status;

    private String createDatetime;

    private String remark;

    public void setCode(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }

    public void setAdvTitle(String advTitle) {
        this.advTitle = advTitle;
    }

    public String getAdvTitle() {
        return advTitle;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwnerCurrency(String ownerCurrency) {
        this.ownerCurrency = ownerCurrency;
    }

    public String getOwnerCurrency() {
        return ownerCurrency;
    }

    public void setOwnerAmount(String ownerAmount) {
        this.ownerAmount = ownerAmount;
    }

    public String getOwnerAmount() {
        return ownerAmount;
    }

    public void setReceiveCurrency(String receiveCurrency) {
        this.receiveCurrency = receiveCurrency;
    }

    public String getReceiveCurrency() {
        return receiveCurrency;
    }

    public void setReceiveAmount(String receiveAmount) {
        this.receiveAmount = receiveAmount;
    }

    public String getReceiveAmount() {
        return receiveAmount;
    }

    public void setReceiver(String receiver) {
        this.receiver = receiver;
    }

    public String getReceiver() {
        return receiver;
    }

    public void setReceiveDatetime(String receiveDatetime) {
        this.receiveDatetime = receiveDatetime;
    }

    public String getReceiveDatetime() {
        return receiveDatetime;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getStatus() {
        return status;
    }

    public void setCreateDatetime(String createDatetime) {
        this.createDatetime = createDatetime;
    }

    public String getCreateDatetime() {
        return createDatetime;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getRemark() {
        return remark;
    }

}
