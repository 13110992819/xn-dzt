package com.cdkj.dzt.enums;

import java.util.HashMap;
import java.util.Map;

/**
 * @author: xieyj 
 * @since: 2016年11月11日 上午10:09:32 
 * @history:
 */
public enum EBizType {
    AJ_GW("GW", "购物"), AJ_GWTK("GWTK", "购物退款"), AJ_HHRFC("HHRFC", "合伙人分成"), AJ_LTSFC(
            "LTSFC", "量体师分成");
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
