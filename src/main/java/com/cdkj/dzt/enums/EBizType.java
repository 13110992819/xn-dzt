package com.cdkj.dzt.enums;

import java.util.HashMap;
import java.util.Map;

/**
 * @author: xieyj 
 * @since: 2016年11月11日 上午10:09:32 
 * @history:
 */
public enum EBizType {
    AJ_GWFK("GW", "购物付款"), AJ_TK("GWTK", "退款"), AJ_HHRFC("HHRFC", "合伙人分成"), AJ_LTSFC(
            "LTSFC", "量体师分成"), AJ_HYCZ("HYCZ", "会员充值"), SCTJ("SCTJ", "首次推荐加积分"), DCTJ(
            "DCTJ", "多次推荐加积分"), HSCJY("HSCJY", "首次推荐加经验"), HDCJY("HDCJY",
            "会员多次推荐加经验"), FDCJY("FDCJY", "非会员多次推荐加经验"), YHJY("YHJY", "用户消费送经验"), YHHD(
            "YHHD", "用户消费送积分");
    public static Map<String, EBizType> getBizTypeMap() {
        Map<String, EBizType> map = new HashMap<String, EBizType>();
        for (EBizType bizType : EBizType.values()) {
            map.put(bizType.getCode(), bizType);
        }
        return map;
    }

    EBizType(String code, String value) {
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
