package com.xnjr.mall.dto.req;

public class XN808006Req {
    // 父编号(选填)
    private String parentCode;

    // 类型(1 板块，2商城)(选填)
    private String type;

    // 分类名称(选填)
    private String name;

    // 所属公司编号(选填)
    private String companyCode;

    public String getParentCode() {
        return parentCode;
    }

    public void setParentCode(String parentCode) {
        this.parentCode = parentCode;
    }

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

    public String getCompanyCode() {
        return companyCode;
    }

    public void setCompanyCode(String companyCode) {
        this.companyCode = companyCode;
    }
}