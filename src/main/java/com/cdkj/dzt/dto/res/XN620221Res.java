package com.cdkj.dzt.dto.res;

import java.util.Date;
import java.util.List;

import com.cdkj.dzt.domain.SizeData;

public class XN620221Res {
    // 名字
    private String realName;

    // 手机号
    private String mobile;

    // 积分数
    private Long jfAmount;

    // 剩余积分
    private Long syAmount;

    // 消费积分
    private Long conAmount;

    // 升级所需积分
    private Long sjAmount;

    // 经验值
    private Long jyAmount;

    // 会员天数
    private Integer days;

    // 生日
    private Date birthday;

    // 地址
    private String address;

    // 等级
    private String level;

    // 量体数据
    private List<SizeData> sizeDataList;

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

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public List<SizeData> getSizeDataList() {
        return sizeDataList;
    }

    public void setSizeDataList(List<SizeData> sizeDataList) {
        this.sizeDataList = sizeDataList;
    }

    public Long getSyAmount() {
        return syAmount;
    }

    public void setSyAmount(Long syAmount) {
        this.syAmount = syAmount;
    }

    public Long getConAmount() {
        return conAmount;
    }

    public void setConAmount(Long conAmount) {
        this.conAmount = conAmount;
    }

}
