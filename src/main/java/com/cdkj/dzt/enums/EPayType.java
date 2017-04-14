package com.cdkj.dzt.enums;

/**
 * @author: xieyj 
 * @since: 2016年11月11日 上午10:09:32 
 * @history:
 */
public enum EPayType {
    ZH_YE("1", "正汇余额"), WEIXIN("2", "微信"), ALIPAY("3", "支付宝"), CG_YE("21",
            "菜狗余额支付"), GD_YE("40", "管道余额支付"), INTEGRAL("90", "单一虚拟币支付");

    EPayType(String code, String value) {
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
