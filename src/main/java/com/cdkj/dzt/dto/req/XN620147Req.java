package com.cdkj.dzt.dto.req;

/**
 * 分页查询我的留言（c端）
 * @author: asus 
 * @since: 2017年8月16日 下午7:20:41 
 * @history:
 */
public class XN620147Req extends APageReq {
    /** 
     * @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么) 
     */
    private static final long serialVersionUID = 1309891375516104381L;

    // 留言与接收人
    private String lookUser;

    public String getLookUser() {
        return lookUser;
    }

    public void setLookUser(String lookUser) {
        this.lookUser = lookUser;
    }
}
