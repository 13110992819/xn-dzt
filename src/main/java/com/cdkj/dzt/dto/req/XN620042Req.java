package com.cdkj.dzt.dto.req;

/**
 * 修改工艺
 * @author: asus 
 * @since: 2017年4月14日 下午3:24:11 
 * @history:
 */
public class XN620042Req {

    // 编号(必填)
    private String code;

    // 类型(必填)
    private String type;

    // 名称(必填)
    private String name;

    // 缩率图(必填)
    private String pic;

    // 缩率图(必填)
    private String advPic;

    // 工艺费(必填)
    private String price;

    // 更新人(必填)
    private String updater;

    // 备注(选填)
    private String remark;

    // 产品编号(必填)
    private String modelCode;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPic() {
        return pic;
    }

    public void setPic(String pic) {
        this.pic = pic;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getUpdater() {
        return updater;
    }

    public void setUpdater(String updater) {
        this.updater = updater;
    }

    public String getModelCode() {
        return modelCode;
    }

    public void setModelCode(String modelCode) {
        this.modelCode = modelCode;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getAdvPic() {
        return advPic;
    }

    public void setAdvPic(String advPic) {
        this.advPic = advPic;
    }

}
