package com.xnjr.mall.dto.req;

public class XN808510Req {

    // 手机号（必填）
    private String mobile;

    // 登录密码（选填）
    private String loginPwd;

    // 本次兑换的嗨币金额（必填）
    private String highAmount;

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getHighAmount() {
        return highAmount;
    }

    public void setHighAmount(String highAmount) {
        this.highAmount = highAmount;
    }

    public String getLoginPwd() {
        return loginPwd;
    }

    public void setLoginPwd(String loginPwd) {
        this.loginPwd = loginPwd;
    }

}
