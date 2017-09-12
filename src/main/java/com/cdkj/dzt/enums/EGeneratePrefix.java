package com.cdkj.dzt.enums;

import java.util.HashMap;
import java.util.Map;

/**
 * @author: xieyj 
 * @since: 2016年5月24日 上午8:29:02 
 * @history:
 */
public enum EGeneratePrefix {

    MODEL("MO", "产品"), MODELSPECS("MOS", "产品规格"), PRODUCT("PD", "成衣"), PRODUCTVAR(
            "PSV", "成衣品种"), PRODUCTSPECS("PDS", "成衣规格"), ORDER("DD", "订单"), ACTICLE(
            "WZ", "文章"), COMMENT("CO", "评论"), KEYWORD("GJ", "关键字"), CLOTH("BL",
            "布料"), SWAP("JL", "交流"), INTERACT("IN", "交互"), CRAFT("GY", "工艺"), PRODUCTCATEGORY(
            "FL", "产品分类"), SPECIMEN("YP", "样品图");
    public static Map<String, EGeneratePrefix> getMap() {
        Map<String, EGeneratePrefix> map = new HashMap<String, EGeneratePrefix>();
        for (EGeneratePrefix orderType : EGeneratePrefix.values()) {
            map.put(orderType.getCode(), orderType);
        }
        return map;
    }

    EGeneratePrefix(String code, String value) {
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
