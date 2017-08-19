package com.cdkj.dzt.domain;

import com.cdkj.dzt.dao.base.ABaseDO;

/**
* 成衣规格
* @author: shan
* @since: 2017年04月14日
* @history:
*/
public class ProductSpecs extends ABaseDO {

    private static final long serialVersionUID = 1L;

    // 编号/具体值----不是主键，是用户选择的选项
    private String code;

    // 名称(冗余)
    private String name;

    // 类型(冗余)
    private String type;

    // 图片(冗余)
    private String pic;

    // 品牌
    private String brand;

    // 规格编号
    private String modelNum;

    // 广告图
    private String advPic;

    // 色系
    private String color;

    // 花色
    private String flowers;

    // 成分
    private String form;

    // 重量
    private Integer weight;

    // 纱支
    private String yarn;

    // 价格
    private Long price;

    // 成衣编号
    private String productCode;

    // 订单编号
    private String orderCode;

    public void setCode(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }

    public void setPic(String pic) {
        this.pic = pic;
    }

    public String getPic() {
        return pic;
    }

    public void setProductCode(String productCode) {
        this.productCode = productCode;
    }

    public String getProductCode() {
        return productCode;
    }

    public void setOrderCode(String orderCode) {
        this.orderCode = orderCode;
    }

    public String getOrderCode() {
        return orderCode;
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

    public String getAdvPic() {
        return advPic;
    }

    public void setAdvPic(String advPic) {
        this.advPic = advPic;
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

    public Integer getWeight() {
        return weight;
    }

    public void setWeight(Integer weight) {
        this.weight = weight;
    }

    public String getYarn() {
        return yarn;
    }

    public void setYarn(String yarn) {
        this.yarn = yarn;
    }

    public Long getPrice() {
        return price;
    }

    public void setPrice(Long price) {
        this.price = price;
    }

}
