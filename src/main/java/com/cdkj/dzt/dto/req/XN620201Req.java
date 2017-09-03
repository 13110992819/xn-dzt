/**
 * @Title XN620201Req.java 
 * @Package com.cdkj.dzt.dto.req 
 * @Description 
 * @author leo(haiqing)  
 * @date 2017年4月14日 下午1:44:23 
 * @version V1.0   
 */
package com.cdkj.dzt.dto.req;

/** 
 * @author: haiqingzheng 
 * @since: 2017年4月14日 下午1:44:23 
 * @history:
 */
public class XN620201Req {

    // 下单人UserId（必填）
    private String applyUser;

    // 量体时间（必填）
    private String ltDatetime;

    // 产品编号
    private String productCode;

    public String getApplyUser() {
        return applyUser;
    }

    public void setApplyUser(String applyUser) {
        this.applyUser = applyUser;
    }

    public String getProductCode() {
        return productCode;
    }

    public void setProductCode(String productCode) {
        this.productCode = productCode;
    }

    public String getLtDatetime() {
        return ltDatetime;
    }

    public void setLtDatetime(String ltDatetime) {
        this.ltDatetime = ltDatetime;
    }
}
