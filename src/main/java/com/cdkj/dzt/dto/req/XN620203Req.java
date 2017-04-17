/**
 * @Title XN620203Req.java 
 * @Package com.cdkj.dzt.dto.req 
 * @Description 
 * @author leo(haiqing)  
 * @date 2017年4月14日 下午1:51:01 
 * @version V1.0   
 */
package com.cdkj.dzt.dto.req;

/** 
 * 订单定价
 * @author: haiqingzheng 
 * @since: 2017年4月14日 下午1:51:01 
 * @history:
 */
public class XN620203Req {

    // 订单编号（必填）
    private String orderCode;

    // 型号编号（必填）
    private String modelCode;

    // 数量（必填）
    private String quantity;

    // 更新人（必填）
    private String updater;

    // 备注（选填）
    private String remark;

    public String getOrderCode() {
        return orderCode;
    }

    public void setOrderCode(String orderCode) {
        this.orderCode = orderCode;
    }

    public String getModelCode() {
        return modelCode;
    }

    public void setModelCode(String modelCode) {
        this.modelCode = modelCode;
    }

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
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
