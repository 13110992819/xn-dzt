package com.cdkj.dzt.dto.req;

/**
 * 列表获取型号规格
 * @author: asus 
 * @since: 2017年4月14日 下午3:24:11 
 * @history:
 */
public class XN620057Req {

    // 父类编号(选填)
    private String parentCode;

    // 名称(选填)
    private String name;

    // 类型(中文)(选填)
    private String type;

    // 产品编号(选填)
    private String modelCode;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getModelCode() {
        return modelCode;
    }

    public void setModelCode(String modelCode) {
        this.modelCode = modelCode;
    }

    public String getParentCode() {
        return parentCode;
    }

    public void setParentCode(String parentCode) {
        this.parentCode = parentCode;
    }

}
