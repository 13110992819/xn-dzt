package com.cdkj.dzt.dto.req;

/**
 * 下架面料
 * @author: asus 
 * @since: 2017年8月16日 上午9:38:41 
 * @history:
 */
public class XN620024Req {
    // 编号(必填)
    private String code;

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
