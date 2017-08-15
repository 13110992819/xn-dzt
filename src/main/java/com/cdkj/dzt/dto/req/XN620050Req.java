package com.cdkj.dzt.dto.req;

/**
 * 分页获取工艺
 * @author: asus 
 * @since: 2017年4月14日 下午3:24:11 
 * @history:
 */
public class XN620050Req extends APageReq {

    /** 
     * @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么) 
     */
    private static final long serialVersionUID = 2980650736065248024L;

    // 类型(选填)
    private String type;

    // 名称(选填)
    private String name;

    // UI位置(选填)
    private String location;

    // 更新人(选填)
    private String updater;

    // 产品编号(选填)
    private String modelCode;

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

}
