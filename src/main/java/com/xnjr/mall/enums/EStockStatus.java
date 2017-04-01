package com.xnjr.mall.enums;

public enum EStockStatus {
    TO_effect("1", "等待生效"), WILL_effect("2", "可以生效待生效"), ING_effect("3", "生效中"), DONE(
            "4", "结算完成");

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
