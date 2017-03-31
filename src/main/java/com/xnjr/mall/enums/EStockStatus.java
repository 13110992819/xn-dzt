package com.xnjr.mall.enums;

public enum EStockStatus {
    TODO("1", "待形成"), DOING("2", "返利中"), DONE("3", "已结算");

    EStockStatus(String code, String value) {
        this.code = code;
        this.value = value;
    }

    private String code;

    private String value;

    public String getCode() {
        return code;
    }

    public String getValue() {
        return value;
    }

}
