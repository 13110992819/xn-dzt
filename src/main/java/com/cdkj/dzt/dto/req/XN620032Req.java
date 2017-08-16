package com.cdkj.dzt.dto.req;

/**
 * 列表查询面料
 * @author: asus 
 * @since: 2017年8月16日 上午9:44:12 
 * @history:
 */
public class XN620032Req extends APageReq {

    /** 
     * @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么) 
     */
    private static final long serialVersionUID = 4006303746920576653L;

    // 类型(选填)
    private String type;

    // 品牌(选填)
    private String brand;

    // 规格编号(选填)
    private String modelNum;

    // 色系(选填)
    private String color;

    // 花色(选填)
    private String flowers;

    // 成分(选填)
    private String form;

    // 纱支(选填)
    private String yarn;

    // UI位置(选填)
    private String location;

    // 状态(选填)
    private String status;

    // 更新人(选填)
    private String updater;

    // 模型编号(选填)
    private String modelCode;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getModelNum() {
        return modelNum;
    }

    public void setModelNum(String modelNum) {
        this.modelNum = modelNum;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getFlowers() {
        return flowers;
    }

    public void setFlowers(String flowers) {
        this.flowers = flowers;
    }

    public String getForm() {
        return form;
    }

    public void setForm(String form) {
        this.form = form;
    }

    public String getYarn() {
        return yarn;
    }

    public void setYarn(String yarn) {
        this.yarn = yarn;
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

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
