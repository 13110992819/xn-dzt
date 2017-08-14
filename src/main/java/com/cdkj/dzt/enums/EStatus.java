package com.cdkj.dzt.enums;

import java.util.HashMap;
import java.util.Map;

/**
 * 上下架状态
 * @author: asus 
 * @since: 2017年8月14日 下午12:38:42 
 * @history:
 */
public enum EStatus {
    DRAFT("0", "待上架"), PUT_ON("1", "上架"), PUT_OFF("2", "下架");
    public static Map<String, EStatus> getResultMap() {
        Map<String, EStatus> map = new HashMap<String, EStatus>();
        for (EStatus status : EStatus.values()) {
            map.put(status.getCode(), status);
        }
        return map;
    }

    EStatus(String code, String value) {
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
