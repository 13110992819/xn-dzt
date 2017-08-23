package com.cdkj.dzt.enums;

import java.util.HashMap;
import java.util.Map;

public enum EMeasureKey {
    // 面料
    // CSGG("1-1", "规格"),
    CSML("1-2", "面料"),

    // 工艺
    LXXZ("1-3", "领型选择"), XXXZ("1-4", "袖型选择"), MJXZ("1-5", "门襟选择"),

    XBXZ("1-6", "下摆选择"), SXXZ("1-7", "收省选择"), LKYZ("1-8", "领口颜色"),

    KDXZ("1-9", "口袋选择"), NKXZ("1-10", "纽扣选择"), NKYS("1-11", "纽扣颜色"),

    // 测量
    LW("2-1", "领围"), SW("2-2", "胸围"), ZYW("2-3", "中腰围"), KYW("2-4", "裤腰围"),

    TW("2-5", "臀围"), DTW("2-6", "大腿围"), TD("2-7", "通档"), BW("2-8", "臀围"),

    ZJK("2-9", "总肩宽"), XC("2-10", "袖长"), QJK("2-11", "前肩宽"), HYJC("2-12",
            "后腰节长"),

    HYG("2-13", "后腰高"), HYGH("2-14", "后衣高"), QYJC("2-15", "前腰节长"), QYG("2-16",
            "前腰高"),

    KC("2-17", "裤长"), XTW("2-18", "小腿围"), QXK("2-19", "前胸宽"), HBK("2-20", "后背宽"),

    FW("2-21", "腹围"),

    XBW("2-22", "小臂围"), QYC("2-23", "前衣长"), WW("2-24", "腕围"),

    //
    TX("4-1", "体型"), BX("4-2", "背型"), ZJ("4-3", "左肩"), YJ("4-4", "右肩"),

    BZ("4-5", "脖子"), FS("4-6", "肤色"), DX("4-7", "肚型"), SC("4-8", "色彩"),

    SB("4-9", "手臂"), DB("4-10", "对比"), TUNX("4-11", "臀型"), GL("4-12", "量感"),
    //
    GXCX("5-1", "个性刺绣"),

    CXWZ("5-2", "刺绣位置"), CXZT("5-3", "刺绣字体"), CXYS("5-4", "刺绣颜色"),
    //
    NL("6-1", "年龄"), SG("6-2", "身高"), TZ("6-3", "体重"), YJDZ("6-4", "邮寄地址"),

    BEIZHU("6-5", "备注");

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
