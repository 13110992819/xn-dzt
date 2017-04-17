package com.cdkj.dzt.enums;

/**
 * @author: xieyj 
 * @since: 2016年5月25日 上午9:28:36 
 * @history:
 */
public enum EOrderStatus {
    TO_MEASURE("0", "待量体"), ASSGIN_MEASURE("1", "已分配待量体"), ASSIGN_PRICE("2",
            "已定价"), PAY_YES("3", "已支付"), TO_APPROVE("4", "待复核"), TO_PRODU("5",
            "待生产"), PRODU_DOING("6", "生产中"), SEND("7", "已发货"), RECEIVE("8",
            "已收货");

    EOrderStatus(String code, String value) {
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
