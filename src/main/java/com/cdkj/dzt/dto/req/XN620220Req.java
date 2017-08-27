package com.cdkj.dzt.dto.req;

import java.util.Map;

/**
 * 修改用户专属报告
 * @author: asus 
 * @since: 2017年8月27日 下午4:15:16 
 * @history:
 */
public class XN620220Req {
    private String userId;

    private Map<String, String> map;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public Map<String, String> getMap() {
        return map;
    }

    public void setMap(Map<String, String> map) {
        this.map = map;
    }
}
