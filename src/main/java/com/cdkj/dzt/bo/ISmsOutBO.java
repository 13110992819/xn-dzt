package com.cdkj.dzt.bo;

public interface ISmsOutBO {

    public void sentContent(String userId, String content);

    public void sendSmsOut(String mobile, String content, String systemCode);
}
