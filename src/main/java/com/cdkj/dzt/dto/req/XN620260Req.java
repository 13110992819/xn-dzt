package com.cdkj.dzt.dto.req;

public class XN620260Req {

    // 模板编号（必填）
    private String modelCode;

    // 图片（必填）
    private String pic;

    // 广告图（必填）
    private String advPic;

    // 图文描述（必填）
    private String description;

    // 更新人（必填）
    private String updater;

    // 备注
    private String remark;

    public String getModelCode() {
        return modelCode;
    }

    public void setModelCode(String modelCode) {
        this.modelCode = modelCode;
    }

    public String getPic() {
        return pic;
    }

    public void setPic(String pic) {
        this.pic = pic;
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

    public String getAdvPic() {
        return advPic;
    }

    public void setAdvPic(String advPic) {
        this.advPic = advPic;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

}
