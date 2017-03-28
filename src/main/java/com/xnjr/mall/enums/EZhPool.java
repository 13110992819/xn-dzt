package com.xnjr.mall.enums;

public enum EZhPool {
    ZHPAY_JJ("11", "正汇基金池"), ZHPAY_CUSTOMER("12", "正汇消费者池"), ZHPAY_STORE("13",
            "正汇商家池");

    EZhPool(String code, String value) {
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
