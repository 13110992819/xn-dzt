package com.cdkj.dzt.dto.req;

/**
 * @author: xieyj 
 * @since: 2016年9月17日 下午4:09:03 
 * @history:
 */
public class XN620252Req {
    // 编号（必填）
    private String code;

    // value（必填）
    private String dvalue;

    // 修改人（必填）
    private String updater;

    // 备注（选填）
    private String remark;

    public String getDvalue() {
        return dvalue;
    }

    public void setDvalue(String dvalue) {
        this.dvalue = dvalue;
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

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
