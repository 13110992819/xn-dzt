package com.xnjr.mall.enums;

public enum EStockPoolType {
    CAIGO("01", "菜狗对接池"), ZHPAY("11", "正汇基金"), ZHPAY_CUSTOMER("12", "正汇消费者基金"), ZHPAY_STORE(
            "13", "正汇商家基金");

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
