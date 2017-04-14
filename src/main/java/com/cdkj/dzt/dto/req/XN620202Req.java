/**
 * @Title XN620202Req.java 
 * @Package com.cdkj.dzt.dto.req 
 * @Description 
 * @author leo(haiqing)  
 * @date 2017年4月14日 下午1:46:15 
 * @version V1.0   
 */
package com.cdkj.dzt.dto.req;

/** 
 * @author: haiqingzheng 
 * @since: 2017年4月14日 下午1:46:15 
 * @history:
 */
public class XN620202Req {

    // 订单编号（必填）
    private String orderCode;

    // 量体师UserId（必填）
    private String ltUser;

    // 量体师姓名（必填）
    private String ltName;

    public String getOrderCode() {
        return orderCode;
    }

    public void setOrderCode(String orderCode) {
        this.orderCode = orderCode;
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
}
