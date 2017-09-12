package com.cdkj.dzt.dto.req;

/**
 * 量体师订单提交
 * @author: asus 
 * @since: 2017年4月14日 下午5:22:15 
 * @history:
 */
public class XN620206Req {
    // 订单编号（必填）
    private String orderCode;

    // 提交人（必填）
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
