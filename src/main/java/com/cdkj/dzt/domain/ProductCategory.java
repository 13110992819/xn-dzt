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

    // 类型（第一层/第二层）
    private String type;

    // 父key
    private String parentKey;

    // key
    private String dkey;

    // value
    private String dvalue;

    // 产品编号
    private String modelCode;

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

    public String getModelCode() {
        return modelCode;
    }

    public void setModelCode(String modelCode) {
        this.modelCode = modelCode;
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

}
