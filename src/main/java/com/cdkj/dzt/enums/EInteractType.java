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
public enum EInteractType {
    DZ("0", "点赞"), SC("1", "收藏");

    public static Map<String, EInteractType> getInteractTypeMap() {
        Map<String, EInteractType> map = new HashMap<String, EInteractType>();
        for (EInteractType interactType : EInteractType.values()) {
            map.put(interactType.getCode(), interactType);
        }
        return map;
    }

    EInteractType(String code, String value) {
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
