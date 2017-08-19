package com.cdkj.dzt.dto.req;

public class XN001304Req {
    // 必填（必填）
    private String userId;

    // 选填（必填）
    private String lastOrderDatetime;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getLastOrderDatetime() {
        return lastOrderDatetime;
    }

    public void setLastOrderDatetime(String lastOrderDatetime) {
        this.lastOrderDatetime = lastOrderDatetime;
    }

}
