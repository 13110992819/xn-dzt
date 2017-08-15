package com.cdkj.dzt.dto.req;

/**
 * 工艺下架
 * @author: asus 
 * @since: 2017年8月15日 下午9:57:37 
 * @history:
 */
public class XN620044Req {
    // 编号(必填)
    private String code;

    // 更新人(必填)
    private String updater;

    // 备注(选填)
    private String remark;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
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
