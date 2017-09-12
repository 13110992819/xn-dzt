package com.cdkj.dzt.dto.req;

/**
 * 归档
 * @author: asus 
 * @since: 2017年8月18日 上午10:59:36 
 * @history:
 */
public class XN620212Req {
    // 订单编号（必填）
    private String orderCode;

    // 更新人（必填）
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
}
