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
public enum EInteractCategory {
    ARTICLE("1", "文章"), MODEL("2", "模板"), CLOTH("3", "布料"), CRAFT("4", "工艺");

    public static Map<String, EInteractCategory> getInteractCategoryTypeMap() {
        Map<String, EInteractCategory> map = new HashMap<String, EInteractCategory>();
        for (EInteractCategory interactCategory : EInteractCategory.values()) {
            map.put(interactCategory.getCode(), interactCategory);
        }
        return map;
    }

    EInteractCategory(String code, String value) {
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
