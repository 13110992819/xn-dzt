package com.cdkj.dzt.enums;

import java.util.HashMap;
import java.util.Map;

public enum EMeasureKey {
    CSGG("11", "衬衫规格"), CSML("12", "衬衫面料"), LXXZ("13", "领型选择"), XXXZ("14",
            "袖型选择"), MZXZ("15", "门襟选择"), XBXZ("16", "下摆选择"), SXXZ("17", "收省选择"), LKYZ(
            "18", "领口颜色"), KDXZ("19", "口袋选择"), NKXZ("20", "纽扣选择"), NKYS("21",
            "纽扣颜色"), LWCL("22", "领围测量"), SWCL("23", "胸围测量"), YWCL("24", "腰围测量"), TWCL(
            "25", "臀围测量"), JKCL("26", "肩宽测量"), YCCL("27", "衣长测量"), XCCL("28",
            "袖长测量"), BWCL("29", "臀围测量"), WWCL("30", "腕围测量"), LWCY("31", "领围成衣"), SWCY(
            "32", "胸围成衣"), YWCY("33", "腰围成衣"), TWCY("34", "臀围成衣"), JKCY("35",
            "肩宽成衣"), YCCY("36", "衣长成衣"), XCCY("37", "袖长成衣"), BWCY("38", "臀围成衣"), WWCY(
            "39", "腕围成衣"), TX("40", "体型"), QXK("41", "前胸宽"), HBK("42", "后背宽"), ZTW(
            "43", "中臀围"), JXL("44", "肩斜量"), FBL("45", "腹部量"), BZ("46", "脖子"), BB(
            "47", "背部"), GXCX("51", "个性刺绣"), CXWZ("52", "刺绣位置"), CDZT("53",
            "刺绣字体"), CSYS("54", "刺绣颜色"), NL("61", "年龄"), SG("62", "身高"), TZ(
            "63", "体重"), YJDZ("64", "邮寄地址");
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
