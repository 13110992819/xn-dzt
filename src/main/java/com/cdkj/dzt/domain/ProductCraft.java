package com.cdkj.dzt.domain;

import com.cdkj.dzt.dao.base.ABaseDO;

/**
* 成衣工艺
* @author: shan 
* @since: 2017-09-13 10:09:52
* @history:
*/
public class ProductCraft extends ABaseDO {

    private static final long serialVersionUID = 1L;

    // 编号
    private String code;

    // 类型
    private String type;

    // 名称
    private String name;

    // 缩率图
    private String pic;

    // 选中图
    private String selected;

    // 工艺费
    private Long price;

    // 产品规格编号
    private String modelSpecsCode;

    // 成衣规格编号
    private String productVarCode;

    // 订单编号
    private String orderCode;

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

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setPic(String pic) {
        this.pic = pic;
    }

    public String getPic() {
        return pic;
    }

    public void setSelected(String selected) {
        this.selected = selected;
    }

    public String getSelected() {
        return selected;
    }

    public void setPrice(Long price) {
        this.price = price;
    }

    public Long getPrice() {
        return price;
    }

    public void setModelSpecsCode(String modelSpecsCode) {
        this.modelSpecsCode = modelSpecsCode;
    }

    public String getModelSpecsCode() {
        return modelSpecsCode;
    }

    public void setProductVarCode(String productVarCode) {
        this.productVarCode = productVarCode;
    }

    public String getProductVarCode() {
        return productVarCode;
    }

    public void setOrderCode(String orderCode) {
        this.orderCode = orderCode;
    }

    public String getOrderCode() {
        return orderCode;
    }

}
