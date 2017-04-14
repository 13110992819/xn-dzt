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
}
