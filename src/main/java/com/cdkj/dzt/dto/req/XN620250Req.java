package com.cdkj.dzt.dto.req;

/**
 * @author: xieyj 
 * @since: 2016年9月17日 下午1:39:41 
 * @history:
 */
public class XN620250Req {
    private String kind;

    // 类型（第一层/第二层）（必填）
    private String type;

    // 父key（第二层必填）
    private String parentKey;

    // key（必填）
    private String dkey;

    // value（必填）
    private String dvalue;

    // 修改人（必填）
    private String updater;

    // 备注（选填）
    private String remark;

    // 系统编号（必填）
    private String modelSpecsCode;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getParentKey() {
        return parentKey;
    }

    public void setParentKey(String parentKey) {
        this.parentKey = parentKey;
    }

    public String getDkey() {
        return dkey;
    }

    public void setDkey(String dkey) {
        this.dkey = dkey;
    }

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

    public String getModelSpecsCode() {
        return modelSpecsCode;
    }

    public void setModelSpecsCode(String modelSpecsCode) {
        this.modelSpecsCode = modelSpecsCode;
    }

    public String getKind() {
        return kind;
    }

    public void setKind(String kind) {
        this.kind = kind;
    }

}
