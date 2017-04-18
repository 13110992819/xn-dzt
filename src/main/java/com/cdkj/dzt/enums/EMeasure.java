package com.cdkj.dzt.enums;

import java.util.HashMap;
import java.util.Map;

/**
 * @author: xieyj 
 * @since: 2016年11月11日 上午10:09:32 
 * @history:
 */
public enum EMeasure {
    MEASURE_INFOR("1", "测量数据"), REALLY("2", "成衣数据");
    public static Map<String, EMeasure> getBizTypeMap() {
        Map<String, EMeasure> map = new HashMap<String, EMeasure>();
        for (EMeasure bizType : EMeasure.values()) {
            map.put(bizType.getCode(), bizType);
        }
        return map;
    }

    EMeasure(String code, String value) {
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
