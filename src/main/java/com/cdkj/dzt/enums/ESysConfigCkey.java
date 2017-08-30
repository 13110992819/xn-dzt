package com.cdkj.dzt.enums;

import java.util.HashMap;
import java.util.Map;

/**
 * 系统参数
 * @author: shan 
 * @since: 2016年12月8日 下午10:01:27 
 * @history:
 */
public enum ESysConfigCkey {
    KDF("KDF", "快递费"), BZF("BZF", "包装费"), HYF("HYF", "会员费"),

    CSHHRFC("CSHHRFC", "合伙人衬衫分成"), CSLTSFC("CSLTSFC", "量体师衬衫分成"), HHHRFC(
            "HHHRFC", "合伙人H+分成"), HLTSFC("HLTSFC", "量体师H+分成"),

    SCTJ("SCTJ", "首次推荐加积分"), DCTJ("DCTJ", "多次推荐加积分"), YHHD("YHHD", "用户消费送积分"), HSCJY(
            "HSCJY", "首次推荐加积分"), HDCJY("HDCJY", "会员多次推荐加积分"), FDCJY("FDCJY",
            "非会员多次推荐加积分"), YHJY("YHJY", "用户消费送经验"), FHY("FHY", "非会员是会员价的几倍"),

    SMOTHED("SMOTHED", "多少个月没有订单为预流失客户"), DMOTHED("DMOTHED", "多少个月没有订单为流失客户"),

    LSCS("LSCS", "流失客户次数"), YLSCS("YLSCS", "预流失客户次数"), FCHY("FCHY", "非常活跃客户次数"), HY(
            "HY", "活跃客户次数"), LKH("LKH", "老客户"), XKH("XKH", "新客户");

    public static Map<String, ESysConfigCkey> getDictTypeMap() {
        Map<String, ESysConfigCkey> map = new HashMap<String, ESysConfigCkey>();
        for (ESysConfigCkey activityStatus : ESysConfigCkey.values()) {
            map.put(activityStatus.getCode(), activityStatus);
        }
        return map;
    }

    ESysConfigCkey(String code, String value) {
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
