package com.cdkj.dzt.domain;

import java.util.Date;

import com.cdkj.dzt.dao.base.ABaseDO;

/**
* 产品样品
* @author: shan 
* @since: 2017-09-12 11:19:42
* @history:
*/
public class Specimen extends ABaseDO {

    private static final long serialVersionUID = 1L;

    // 序号
    private String code;

    // 图片
    private String pic;

    // 广告图
    private String advPic;

    // 图文描述
    private String description;

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

    // 产品编号
    private String modelCode;

    // 模板状态
    private String modelStatus;

    // 类型
    private String type;

    private String isSC;

    public void setCode(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
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

    public String getModelStatus() {
        return modelStatus;
    }

    public void setModelStatus(String modelStatus) {
        this.modelStatus = modelStatus;
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

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public Integer getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(Integer orderNo) {
        this.orderNo = orderNo;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getIsSC() {
        return isSC;
    }

    public void setIsSC(String isSC) {
        this.isSC = isSC;
    }

}
