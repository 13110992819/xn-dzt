package com.xnjr.mall.enums;

public enum EStockStatus {
    DOING("1", "返利中"), DONE("2", "已结算");

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
