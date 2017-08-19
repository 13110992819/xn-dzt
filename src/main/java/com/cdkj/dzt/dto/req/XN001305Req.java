package com.cdkj.dzt.dto.req;

public class XN001305Req {
    // 必填（必填）
    private String userId;

    // 选填（必填）
    private String frequent;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getFrequent() {
        return frequent;
    }

    public void setFrequent(String frequent) {
        this.frequent = frequent;
    }

}
