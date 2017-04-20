package com.cdkj.dzt.enums;

import java.util.HashMap;
import java.util.Map;

public enum EMeasureKey {
    CSGG("1-1", "衬衫规格"), CSML("1-2", "衬衫面料"), LXXZ("1-3", "领型选择"), XXXZ("1-4",
            "袖型选择"), MJXZ("1-5", "门襟选择"), XBXZ("1-6", "下摆选择"), SXXZ("1-7",
            "收省选择"), LKYZ("1-8", "领口颜色"), KDXZ("1-9", "口袋选择"), NKXZ("1-10",
            "纽扣选择"), NKYS("1-11", "纽扣颜色"), LWCL("2-1", "领围测量"), SWCL("2-2",
            "胸围测量"), YWCL("2-3", "腰围测量"), TWCL("2-4", "臀围测量"), JKCL("2-5",
            "肩宽测量"), YCCL("2-6", "衣长测量"), XCCL("2-7", "袖长测量"), BWCL("2-8",
            "臀围测量"), WWCL("2-9", "腕围测量"), LWCY("2-10", "领围成衣"), SWCY("2-11",
            "胸围成衣"), YWCY("2-12", "腰围成衣"), TWCY("2-13", "臀围成衣"), JKCY("2-14",
            "肩宽成衣"), YCCY("2-15", "衣长成衣"), XCCY("2-16", "袖长成衣"), BWCY("2-17",
            "臀围成衣"), WWCY("2-18", "腕围成衣"), TX("4-1", "体型"), QXK("4-2", "前胸宽"), HBK(
            "4-3", "后背宽"), ZTW("4-4", "中臀围"), JXL("4-5", "肩斜量"), FBL("4-6",
            "腹部量"), BZ("4-7", "脖子"), BB("4-8", "背部"), GXCX("5-1", "个性刺绣"), CXWZ(
            "5-2", "刺绣位置"), CXZT("5-3", "刺绣字体"), CXYS("5-4", "刺绣颜色"), NL("6-1",
            "年龄"), SG("6-2", "身高"), TZ("6-3", "体重"), YJDZ("6-4", "邮寄地址"), BEIZHU(
            "6-5", "备注");

    // CSGG("11", "衬衫规格"), CSML("12", "衬衫面料"), LXXZ("13", "领型选择"), XXXZ("14",
    // "袖型选择"), MZXZ("15", "门襟选择"), XBXZ("16", "下摆选择"), SXXZ("17", "收省选择"),
    // LKYZ(
    // "18", "领口颜色"), KDXZ("19", "口袋选择"), NKXZ("20", "纽扣选择"), NKYS("21",
    // "纽扣颜色"), LWCL("22", "领围测量"), SWCL("23", "胸围测量"), YWCL("24", "腰围测量"),
    // TWCL(
    // "25", "臀围测量"), JKCL("26", "肩宽测量"), YCCL("27", "衣长测量"), XCCL("28",
    // "袖长测量"), BWCL("29", "臀围测量"), WWCL("30", "腕围测量"), LWCY("31", "领围成衣"),
    // SWCY(
    // "32", "胸围成衣"), YWCY("33", "腰围成衣"), TWCY("34", "臀围成衣"), JKCY("35",
    // "肩宽成衣"), YCCY("36", "衣长成衣"), XCCY("37", "袖长成衣"), BWCY("38", "臀围成衣"),
    // WWCY(
    // "39", "腕围成衣"), TX("40", "体型"), QXK("41", "前胸宽"), HBK("42", "后背宽"), ZTW(
    // "43", "中臀围"), JXL("44", "肩斜量"), FBL("45", "腹部量"), BZ("46", "脖子"), BB(
    // "47", "背部"), ;
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
