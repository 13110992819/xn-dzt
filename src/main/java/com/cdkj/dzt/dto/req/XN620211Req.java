/**
 * @Title XN620211Req.java 
 * @Package com.cdkj.dzt.dto.req 
 * @Description 
 * @author leo(haiqing)  
 * @date 2017年4月14日 下午6:22:13 
 * @version V1.0   
 */
package com.cdkj.dzt.dto.req;

/** 
 * @author: haiqingzheng 
 * @since: 2017年4月14日 下午6:22:13 
 * @history:
 */
public class XN620211Req {
    // 订单编号（必填）
    private String orderCode;

    // 更新人(必填)
    private String updater;

    // 备注(选填)
    private String remark;

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

    public String getOrderCode() {
        return orderCode;
    }

    public void setOrderCode(String orderCode) {
        this.orderCode = orderCode;
    }
}
