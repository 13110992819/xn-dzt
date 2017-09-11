package com.cdkj.dzt.dto.req;

public class XN001307Req {
    // 必填（必填）
    private String userId;

    // 合伙人（必填）
    private String toUser;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getToUser() {
        return toUser;
    }

    public void setToUser(String toUser) {
        this.toUser = toUser;
    }

}
