package com.cdkj.dzt.enums;

public enum EUserKind {
    Partner("11", "城市合伙人");

    EUserKind(String code, String value) {
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
