package com.cdkj.dzt.dto.res;

import java.util.LinkedHashMap;
import java.util.Map;

public class XN620221Res {
    private String realName;

    private String mobile;

    private Long jfAmount;

    private Long sjAmount;

    private Long jyAmount;

    private Integer days;

    private String address;

    private String level;

    private Map<String, LinkedHashMap<String, String>> resultMap;

    public Long getJfAmount() {
        return jfAmount;
    }

    public void setJfAmount(Long jfAmount) {
        this.jfAmount = jfAmount;
    }

    public Long getSjAmount() {
        return sjAmount;
    }

    public void setSjAmount(Long sjAmount) {
        this.sjAmount = sjAmount;
    }

    public Long getJyAmount() {
        return jyAmount;
    }

    public void setJyAmount(Long jyAmount) {
        this.jyAmount = jyAmount;
    }

    public Integer getDays() {
        return days;
    }

    public void setDays(Integer days) {
        this.days = days;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public Map<String, LinkedHashMap<String, String>> getResultMap() {
        return resultMap;
    }

    public void setResultMap(
            Map<String, LinkedHashMap<String, String>> resultMap) {
        this.resultMap = resultMap;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }
}
