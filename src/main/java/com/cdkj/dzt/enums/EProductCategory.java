/**
 * @Title EDictType.java 
 * @Package com.std.security.enums 
 * @Description 
 * @author haiqingzheng  
 * @date 2016年4月17日 下午5:15:02 
 * @version V1.0   
 */
package com.cdkj.dzt.enums;

import java.util.HashMap;
import java.util.Map;

/** 
 * @author: haiqingzheng 
 * @since: 2016年4月17日 下午5:15:02 
 * @history:
 */
public enum EProductCategory {
    CRAFTWORK("0", "工艺"), EMBROIDERY("1", "刺绣"), DRESSINGSTYLE("2", "着装风格"), EMBROIDERYCONTENT(
            "3", "刺绣内容"), EMBROIDERYCOLOR("4", "刺绣颜色");

    public static Map<String, EProductCategory> getInteractCategoryTypeMap() {
        Map<String, EProductCategory> map = new HashMap<String, EProductCategory>();
        for (EProductCategory interactCategory : EProductCategory.values()) {
            map.put(interactCategory.getCode(), interactCategory);
        }
        return map;
    }

    EProductCategory(String code, String value) {
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
