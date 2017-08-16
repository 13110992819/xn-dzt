/**
 * @Title XN620200Req.java 
 * @Package com.cdkj.dzt.dto.req 
 * @Description 
 * @author leo(haiqing)  
 * @date 2017年4月14日 下午1:36:44 
 * @version V1.0   
 */
package com.cdkj.dzt.dto.req;

/** 
 * @author: haiqingzheng 
 * @since: 2017年4月14日 下午1:36:44 
 * @history:
 */
public class XN620200Req {

    // 下单人UserId（必填）
    private String applyUser;

    // 下单人姓名（必填）
    private String applyName;

    // 下单人手机号（必填）
    private String applyMobile;

    // 身高（必填）
    private String height;

    // 体重（必填）
    private String weight;

    // 量体时间（必填）
    private String ltDatetime;

    // 量体地址省（必填）
    private String ltProvince;

    // 量体地址市（必填）
    private String ltCity;

    // 量体地址区（必填）
    private String ltArea;

    // 量体地址详细（必填）
    private String ltAddress;

    // 量体叮嘱（选填）
    private String applyNote;

    // 更新人（必填）
    private String updater;

    // 备注（选填）
    private String remark;

    public String getApplyUser() {
        return applyUser;
    }

    public void setApplyUser(String applyUser) {
        this.applyUser = applyUser;
    }

    public String getApplyName() {
        return applyName;
    }

    public void setApplyName(String applyName) {
        this.applyName = applyName;
    }

    public String getApplyMobile() {
        return applyMobile;
    }

    public void setApplyMobile(String applyMobile) {
        this.applyMobile = applyMobile;
    }

    public String getLtDatetime() {
        return ltDatetime;
    }

    public void setLtDatetime(String ltDatetime) {
        this.ltDatetime = ltDatetime;
    }

    public String getLtProvince() {
        return ltProvince;
    }

    public void setLtProvince(String ltProvince) {
        this.ltProvince = ltProvince;
    }

    public String getLtCity() {
        return ltCity;
    }

    public void setLtCity(String ltCity) {
        this.ltCity = ltCity;
    }

    public String getLtArea() {
        return ltArea;
    }

    public void setLtArea(String ltArea) {
        this.ltArea = ltArea;
    }

    public String getLtAddress() {
        return ltAddress;
    }

    public void setLtAddress(String ltAddress) {
        this.ltAddress = ltAddress;
    }

    public String getApplyNote() {
        return applyNote;
    }

    public void setApplyNote(String applyNote) {
        this.applyNote = applyNote;
    }

    public String getUpdater() {
        return updater;
    }

    public void setUpdater(String updater) {
        this.updater = updater;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getHeight() {
        return height;
    }

    public void setHeight(String height) {
        this.height = height;
    }

    public String getWeight() {
        return weight;
    }

    public void setWeight(String weight) {
        this.weight = weight;
    }

}
