package com.cdkj.dzt.dto.req;

public class XN001308Req {
    // 必填（必填）
    private String userId;

    // 量体师（必填）
    private String ltUser;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getLtUser() {
        return ltUser;
    }

    public void setLtUser(String ltUser) {
        this.ltUser = ltUser;
    }

}
