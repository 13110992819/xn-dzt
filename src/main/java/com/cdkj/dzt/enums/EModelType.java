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
public enum EModelType {
    CHENSHAN("0", "衬衫"), H("1", "H+");

    public static Map<String, EModelType> getModelTypeMap() {
        Map<String, EModelType> map = new HashMap<String, EModelType>();
        for (EModelType modelType : EModelType.values()) {
            map.put(modelType.getCode(), modelType);
        }
        return map;
    }

    EModelType(String code, String value) {
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
