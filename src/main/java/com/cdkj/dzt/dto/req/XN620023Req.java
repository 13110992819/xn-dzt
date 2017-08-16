package com.cdkj.dzt.dto.req;

/**
 * 面料上架
 * @author: asus 
 * @since: 2017年8月16日 上午9:38:41 
 * @history:
 */
public class XN620023Req {
    // 编号(必填)
    private String code;

    // UI位置(必填)
    private String location;

    // UI顺序(必填)
    private String orderNo;

    // 更新人(必填)
    private String updater;

    // 备注
    private String remark;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
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
