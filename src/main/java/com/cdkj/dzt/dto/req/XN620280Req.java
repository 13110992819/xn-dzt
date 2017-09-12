package com.cdkj.dzt.dto.req;

/**
 * 新增产品规格
 * @author: asus 
 * @since: 2017年9月12日 下午4:21:28 
 * @history:
 */
public class XN620280Req {
    private String pic;

    // 产品编号（必填）
    private String modelCode;

    // 名称（必填）
    private String name;

    // 备注
    private String remark;

    private String updater;

    public String getPic() {
        return pic;
    }

    public void setPic(String pic) {
        this.pic = pic;
    }

    public String getModelCode() {
        return modelCode;
    }

    public void setModelCode(String modelCode) {
        this.modelCode = modelCode;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getUpdater() {
        return updater;
    }

    public void setUpdater(String updater) {
        this.updater = updater;
    }
}
