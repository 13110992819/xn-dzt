package com.cdkj.dzt.domain;

import java.util.Date;

import com.cdkj.dzt.dao.base.ABaseDO;
import com.cdkj.dzt.dto.res.XN620014Res;

/**
* 产品规格
* @author: shan 
* @since: 2017-09-12 11:09:08
* @history:
*/
public class ModelSpecs extends ABaseDO {

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

    // 备注
    private String remark;

    // 产品编号
    private String modelCode;

    // 备注
    private String modelName;

    private XN620014Res res;

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

    public String getModelName() {
        return modelName;
    }

    public void setModelName(String modelName) {
        this.modelName = modelName;
    }

    public XN620014Res getRes() {
        return res;
    }

    public void setRes(XN620014Res res) {
        this.res = res;
    }

}
