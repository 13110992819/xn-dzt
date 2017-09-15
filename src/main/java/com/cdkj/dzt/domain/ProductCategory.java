/**
 * @Title SYSDict.java 
 * @Package com.xnjr.moom.domain 
 * @Description 
 * @author haiqingzheng  
 * @date 2016年4月16日 下午9:50:21 
 * @version V1.0   
 */
package com.cdkj.dzt.domain;

import java.util.Date;
import java.util.List;

import com.cdkj.dzt.dao.base.ABaseDO;

/** 
 * @author: haiqingzheng 
 * @since: 2016年4月16日 下午9:50:21 
 * @history:
 */
public class ProductCategory extends ABaseDO {
    /** 
     * @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么) 
     */
    private static final long serialVersionUID = -9204995364517866187L;

    // 编号
    private String code;

    // 0工艺,1刺绣
    private String kind;

    // 类型（第一层/第二层）
    private String type;

    // 父key
    private String parentKey;

    // key
    private String dkey;

    // value
    private String dvalue;

    // 产品规格编号
    private String modelSpecsCode;

    // 修改人
    private String updater;

    // 修改时间
    private Date updateDatetime;

    // 备注
    private String remark;

    // 工艺
    private List<Craft> craftList;

    // 布料
    private List<Cloth> clothList;

    private ProductCraft productCraft;

    private ProductCraft colorProductCraft;

    private List<ProductCategory> productCategoryList;

    private ProductCategory productCategory;

    private List<ProductCategory> colorPcList;

    private List<Craft> colorCraftList;

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

    public Date getUpdateDatetime() {
        return updateDatetime;
    }

    public void setUpdateDatetime(Date updateDatetime) {
        this.updateDatetime = updateDatetime;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public List<Craft> getCraftList() {
        return craftList;
    }

    public void setCraftList(List<Craft> craftList) {
        this.craftList = craftList;
    }

    public List<Cloth> getClothList() {
        return clothList;
    }

    public void setClothList(List<Cloth> clothList) {
        this.clothList = clothList;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public ProductCraft getProductCraft() {
        return productCraft;
    }

    public void setProductCraft(ProductCraft productCraft) {
        this.productCraft = productCraft;
    }

    public String getModelSpecsCode() {
        return modelSpecsCode;
    }

    public void setModelSpecsCode(String modelSpecsCode) {
        this.modelSpecsCode = modelSpecsCode;
    }

    public List<ProductCategory> getProductCategoryList() {
        return productCategoryList;
    }

    public void setProductCategoryList(List<ProductCategory> productCategoryList) {
        this.productCategoryList = productCategoryList;
    }

    public List<Craft> getColorCraftList() {
        return colorCraftList;
    }

    public void setColorCraftList(List<Craft> colorCraftList) {
        this.colorCraftList = colorCraftList;
    }

    public String getKind() {
        return kind;
    }

    public void setKind(String kind) {
        this.kind = kind;
    }

    public ProductCraft getColorProductCraft() {
        return colorProductCraft;
    }

    public void setColorProductCraft(ProductCraft colorProductCraft) {
        this.colorProductCraft = colorProductCraft;
    }

    public List<ProductCategory> getColorPcList() {
        return colorPcList;
    }

    public void setColorPcList(List<ProductCategory> colorPcList) {
        this.colorPcList = colorPcList;
    }

    public ProductCategory getProductCategory() {
        return productCategory;
    }

    public void setProductCategory(ProductCategory productCategory) {
        this.productCategory = productCategory;
    }

}
