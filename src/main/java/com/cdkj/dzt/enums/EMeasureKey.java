package com.cdkj.dzt.enums;

import java.util.HashMap;
import java.util.Map;

public enum EMeasureKey {
    CSML("1-02", "面料"),

    // 测量
    LW("2-01", "领围"), SW("2-02", "胸围"), ZYW("2-03", "中腰围"), KYW("2-04", "裤腰围"),

    TW("2-05", "臀围"), DTW("2-06", "大腿围"), TD("2-07", "通档"), BW("2-08", "臂围"),

    ZJK("2-09", "总肩宽"), XC("2-10", "袖长"), QJK("2-11", "前肩宽"), HYJC("2-12",
            "后腰节长"),

    HYG("2-13", "后腰高"), HYGH("2-14", "后衣高"), QYJC("2-15", "前腰节长"), QYG("2-16",
            "前腰高"),

    KC("2-17", "裤长"), XTW("2-18", "小腿围"), QXK("2-19", "前胸宽"), HBK("2-20", "后背宽"),

    FW("2-21", "腹围"),

    XBW("2-22", "小臂围"), QYC("2-23", "前衣长"), WW("2-24", "腕围"),

    //
    TX("4-01", "体型"), BX("4-02", "背型"), ZJ("4-03", "左肩"), YJ("4-04", "右肩"),

    BZ("4-05", "脖子"), FS("4-06", "肤色"), DX("4-07", "肚型"), SC("4-08", "色彩"),

    SB("4-09", "手臂"), DB("4-10", "对比"), TUNX("4-11", "臀型"), GL("4-12", "量感"),

    SG("6-02", "身高"), TZ("6-03", "体重"),

    YJDZ("6-04", "邮寄地址"),

    BEIZHU("6-05", "备注");

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
