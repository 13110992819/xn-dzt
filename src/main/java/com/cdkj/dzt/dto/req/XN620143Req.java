package com.cdkj.dzt.dto.req;

/**
 * 阅读留言
 * @author: asus 
 * @since: 2017年8月16日 下午2:52:56 
 * @history:
 */
public class XN620143Req {
    // 编号(必填)
    private String code;

    // 查看人(必填)
    private String lookUser;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getLookUser() {
        return lookUser;
    }

    public void setLookUser(String lookUser) {
        this.lookUser = lookUser;
    }

}
