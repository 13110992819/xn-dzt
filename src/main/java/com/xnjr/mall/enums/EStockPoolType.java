package com.xnjr.mall.enums;

public enum EStockPoolType {
    CAIGO("01", "菜狗对接池");

    EStockPoolType(String code, String value) {
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
