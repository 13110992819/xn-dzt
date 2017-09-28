package com.cdkj.dzt.enums;

import java.util.HashMap;
import java.util.Map;

public enum EMeasureKey {
    // 测量
    LW("2-01", "领围"), SW("2-02", "胸围"), ZYW("2-03", "中腰围"), KYW("2-04", "裤腰围"),

    TW("2-05", "臀围"), DTW("2-06", "大腿围"), TD("2-07", "通档"), BW("2-08", "臂围"),

    ZJK("2-09", "总肩宽"), XC("2-10", "袖长"), QJK("2-11", "前肩宽"), HYJC("2-12",
            "后腰节长"),

    HYG("2-13", "后腰高"), HYGH("2-14", "后衣长"), QYJC("2-15", "前腰节长"), QYG("2-16",
            "前腰高"),

    KC("2-17", "裤长"), XTW("2-18", "小腿围"), QXK("2-19", "前胸宽"), HBK("2-20", "后背宽"),

    FW("2-21", "腹围"),

    XBW("2-22", "小臂围"), QYC("2-23", "前衣长"), WW("2-24", "腕围"),

    //
    ZJ("4-01", "左肩"), YJ("4-02", "右肩"), BZ("4-03", "脖子"), BX("4-04", "背部"),

    DX("4-05", "肚型"), SB("4-06", "手臂"), TUNX("4-07", "臀型"), FS("4-08", "肤色"),

    SC("4-09", "色彩"), DJ("4-10", "动静"), GL("4-11", "量感"), DB("4-12", "对比"),

    SG("6-02", "身高"), TZ("6-03", "体重");

    public static Map<String, EMeasureKey> getMap() {
        Map<String, EMeasureKey> map = new HashMap<String, EMeasureKey>();
        for (EMeasureKey direction : EMeasureKey.values()) {
            map.put(direction.getCode(), direction);
        }
        return map;
    }

    EMeasureKey(String code, String value) {
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
