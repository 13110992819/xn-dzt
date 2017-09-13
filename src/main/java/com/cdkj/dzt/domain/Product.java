package com.cdkj.dzt.domain;

import java.util.List;

import com.cdkj.dzt.dao.base.ABaseDO;

/**
* 成衣
* @author: shan
* @since: 2017年04月14日
* @history:
*/
public class Product extends ABaseDO {

    private static final long serialVersionUID = 1L;

    // 编号
    private String code;

    // 订单编号
    private String orderCode;

    // 型号编号
    private String modelCode;

    // 产品名字
    private String modelName;

    // 产品图片
    private String modelPic;

    // 广告图
    private String advPic;

    // 图文描述
    private String description;

    // 损耗
    private Double loss;

    // 加工费
    private Long processFee;

    // 单价
    private Long price;

    // 数量
    private Integer quantity;

    // -------db propties--------------
    private List<ProductSpecs> productSpecsList;

    private List<ProductVar> productVarList;

    public void setCode(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }

    public void setOrderCode(String orderCode) {
        this.orderCode = orderCode;
    }

    public String getOrderCode() {
        return orderCode;
    }

    public String getModelCode() {
        return modelCode;
    }

    public void setModelCode(String modelCode) {
        this.modelCode = modelCode;
    }

    public void setModelName(String modelName) {
        this.modelName = modelName;
    }

    public String getModelName() {
        return modelName;
    }

    public void setModelPic(String modelPic) {
        this.modelPic = modelPic;
    }

    public String getModelPic() {
        return modelPic;
    }

    public void setPrice(Long price) {
        this.price = price;
    }

    public Long getPrice() {
        return price;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public List<ProductSpecs> getProductSpecsList() {
        return productSpecsList;
    }

    public void setProductSpecsList(List<ProductSpecs> productSpecsList) {
        this.productSpecsList = productSpecsList;
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

    public Double getLoss() {
        return loss;
    }

    public void setLoss(Double loss) {
        this.loss = loss;
    }

    public Long getProcessFee() {
        return processFee;
    }

    public void setProcessFee(Long processFee) {
        this.processFee = processFee;
    }

    public List<ProductVar> getProductVarList() {
        return productVarList;
    }

    public void setProductVarList(List<ProductVar> productVarList) {
        this.productVarList = productVarList;
    }

}
