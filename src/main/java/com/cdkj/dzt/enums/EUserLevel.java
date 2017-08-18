package com.cdkj.dzt.enums;

/**
 * 用户等级
 * @author: xieyj 
 * @since: 2016年5月24日 上午9:11:47 
 * @history:
 */
public enum EUserLevel {

    ONE("1", "普通用户"), TWO("2", "银卡会员"), THREE("3", "金卡会员"), FOUR("4", "铂金会员"), FIVE(
            "5", "钻石会员");
    ;

    EUserLevel(String code, String value) {
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
