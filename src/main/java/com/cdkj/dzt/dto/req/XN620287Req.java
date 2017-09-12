package com.cdkj.dzt.dto.req;

/**
 * 列表查询产品规格
 * @author: asus 
 * @since: 2017年9月12日 下午4:17:41 
 * @history:
 */
public class XN620287Req extends APageReq {
    /** 
     * @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么) 
     */
    private static final long serialVersionUID = -3727329985953962789L;

    // 产品编号
    private String modelCode;

    // 名称
    private String name;

    // 更新人
    private String updater;

    public String getModelCode() {
        return modelCode;
    }

    public void setModelCode(String modelCode) {
        this.modelCode = modelCode;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUpdater() {
        return updater;
    }

    public void setUpdater(String updater) {
        this.updater = updater;
    }
}
