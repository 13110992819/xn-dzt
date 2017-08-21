package com.cdkj.dzt.enums;

/**
 * 用户等级
 * @author: xieyj 
 * @since: 2016年5月24日 上午9:11:47 
 * @history:
 */
public enum EUserFrequent {

    ONE("1", "新用户"), TWO("2", "老客户"), THREE("3", "活跃老客户"), FOUR("4", "非常活跃老客户"), FIVE(
            "5", "预流失客户"), SIX("6", "流失客户");

    EUserFrequent(String code, String value) {
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
