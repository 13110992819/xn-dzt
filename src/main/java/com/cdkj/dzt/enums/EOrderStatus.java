package com.cdkj.dzt.enums;

/**
 * @author: xieyj 
 * @since: 2016年5月25日 上午9:28:36 
 * @history:
 */
public enum EOrderStatus {
    TO_MEASURE("1", "待量体"), ASSIGN_PRICE("2", "已定价"), PAY_YES("3", "已支付"), TO_APPROVE(
            "4", "待复核"), TO_PRODU("5", "待生产"), PRODU_DOING("6", "生产中"), SEND(
            "7", "已发货"), RECEIVE("8", "已收货"), COMMENT("9", "已评价"), FILED("10",
            "已归档"), CANCEL("11", "取消订单");
    // 1 待量体,2 已定价,3 已支付 ,4 待复核,5 待生产,6 生产中,7 已发货,8 已收货,9 已评价,10 已归档,11 取消订单
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
