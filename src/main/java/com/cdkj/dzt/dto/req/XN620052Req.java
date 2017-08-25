package com.cdkj.dzt.dto.req;

/**
 * 列表获取工艺
 * @author: asus 
 * @since: 2017年4月14日 下午3:24:11 
 * @history:
 */
public class XN620052Req extends APageReq {

    /** 
     * @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么) 
     */
    private static final long serialVersionUID = 2948107036782765799L;

    // 类型(选填)
    private String type;

    // 名称(选填)
    private String name;

    // 状态(选填)
    private String status;

    // UI位置(选填)
    private String location;

    // 更新人(选填)
    private String updater;

    // 产品编号(选填)
    private String modelCode;

    // 产品状态(选填)
    private String modelStatus;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getUpdater() {
        return updater;
    }

    public void setUpdater(String updater) {
        this.updater = updater;
    }

    public String getModelCode() {
        return modelCode;
    }

    public void setModelCode(String modelCode) {
        this.modelCode = modelCode;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getModelStatus() {
        return modelStatus;
    }

    public void setModelStatus(String modelStatus) {
        this.modelStatus = modelStatus;
    }

}
