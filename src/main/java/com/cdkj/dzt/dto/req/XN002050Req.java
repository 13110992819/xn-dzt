package com.cdkj.dzt.dto.req;

public class XN002050Req {

    // 账户编号(必填)
    private String userId;

    // 币种(选填)
    private String currency;

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

}
