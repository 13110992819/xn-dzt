package com.cdkj.dzt.enums;

import java.util.HashMap;
import java.util.Map;

public enum EMeasureType {

    DZSJ("1-", "DINGZHI"), CLSJ("2-", "CELIANG"), TXSJ("4-", "TIXIN"), CXSJ(
            "5-", "CIXIU"), QT("6-", "QITA");

    public static Map<String, EMeasureType> getMap() {
        Map<String, EMeasureType> map = new HashMap<String, EMeasureType>();
        for (EMeasureType direction : EMeasureType.values()) {
            map.put(direction.getCode(), direction);
        }
        return map;
    }

    EMeasureType(String code, String value) {
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
