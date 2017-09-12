package com.cdkj.dzt.domain;

import java.util.Date;
import java.util.List;

import com.cdkj.dzt.dao.base.ABaseDO;

/**
* 成衣品种
* @author: shan 
* @since: 2017-09-12 19:35:21
* @history:
*/
public class ProductVar extends ABaseDO {

    private static final long serialVersionUID = 1L;

    // 序号
    private String code;

    // 名称
    private String name;

    // 图片
    private String pic;

    // 更新人
    private String updater;

    // 更新时间
    private Date updateDatetime;

    // 产品编号
    private String productCode;

    // 模板编号
    private String modelCode;

    // 产品规格
    private List<ProductSpecs> productSpecs;

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

    public void setPic(String pic) {
        this.pic = pic;
    }

    public String getPic() {
        return pic;
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

    public void setProductCode(String productCode) {
        this.productCode = productCode;
    }

    public String getProductCode() {
        return productCode;
    }

    public void setModelCode(String modelCode) {
        this.modelCode = modelCode;
    }

    public String getModelCode() {
        return modelCode;
    }

    public List<ProductSpecs> getProductSpecs() {
        return productSpecs;
    }

    public void setProductSpecs(List<ProductSpecs> productSpecs) {
        this.productSpecs = productSpecs;
    }

}
