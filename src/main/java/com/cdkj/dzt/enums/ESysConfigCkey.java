package com.cdkj.dzt.enums;

import java.util.HashMap;
import java.util.Map;

/**
 * 系统参数
 * @author: shan 
 * @since: 2016年12月8日 下午10:01:27 
 * @history:
 */
public enum ESysConfigCkey {
    KDF("KDF", "快递费"), BZF("BZF", "包装费");

    public static Map<String, ESysConfigCkey> getDictTypeMap() {
        Map<String, ESysConfigCkey> map = new HashMap<String, ESysConfigCkey>();
        for (ESysConfigCkey activityStatus : ESysConfigCkey.values()) {
            map.put(activityStatus.getCode(), activityStatus);
        }
        return map;
    }

    ESysConfigCkey(String code, String value) {
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
