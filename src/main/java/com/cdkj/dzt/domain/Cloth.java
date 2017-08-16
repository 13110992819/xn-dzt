package com.cdkj.dzt.domain;

import java.util.Date;

import com.cdkj.dzt.dao.base.ABaseDO;

/**
* 布料
* @author: shan 
* @since: 2017-08-13 18:20:14
* @history:
*/
public class Cloth extends ABaseDO {

    private static final long serialVersionUID = 1L;

    // 编号
    private String code;

    // 类型
    private String type;

    // 品牌
    private String brand;

    // 规格编号
    private String modelNum;

    // 缩率图
    private String pic;

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

    // UI位置
    private String location;

    // UI顺序
    private Integer orderNo;

    // 状态
    private String status;

    // 更新人
    private String updater;

    // 更新时间
    private Date updateDatetime;

    // 备注
    private String remark;

    // 模型编号
    private String modelCode;

    public void setCode(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getBrand() {
        return brand;
    }

    public void setModelNum(String modelNum) {
        this.modelNum = modelNum;
    }

    public String getModelNum() {
        return modelNum;
    }

    public void setPic(String pic) {
        this.pic = pic;
    }

    public String getPic() {
        return pic;
    }

    public void setAdvPic(String advPic) {
        this.advPic = advPic;
    }

    public String getAdvPic() {
        return advPic;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getColor() {
        return color;
    }

    public void setFlowers(String flowers) {
        this.flowers = flowers;
    }

    public String getFlowers() {
        return flowers;
    }

    public void setForm(String form) {
        this.form = form;
    }

    public String getForm() {
        return form;
    }

    public void setWeight(Integer weight) {
        this.weight = weight;
    }

    public Integer getWeight() {
        return weight;
    }

    public void setYarn(String yarn) {
        this.yarn = yarn;
    }

    public String getYarn() {
        return yarn;
    }

    public void setPrice(Long price) {
        this.price = price;
    }

    public Long getPrice() {
        return price;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getLocation() {
        return location;
    }

    public void setOrderNo(Integer orderNo) {
        this.orderNo = orderNo;
    }

    public Integer getOrderNo() {
        return orderNo;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getStatus() {
        return status;
    }

    public void setUpdater(String updater) {
        this.updater = updater;
    }

    public String getUpdater() {
        return updater;
    }

    public void setUpdateDatetime(Date updateDatetime) {
        this.updateDatetime = updateDatetime;
    }

    public Date getUpdateDatetime() {
        return updateDatetime;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getRemark() {
        return remark;
    }

    public void setModelCode(String modelCode) {
        this.modelCode = modelCode;
    }

    public String getModelCode() {
        return modelCode;
    }

}
