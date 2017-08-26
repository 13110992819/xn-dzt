package com.cdkj.dzt.dto.req;

/**
 * 分页查询我的留言（c端）
 * @author: asus 
 * @since: 2017年8月16日 下午7:20:41 
 * @history:
 */
public class XN620149Req extends APageReq {
    /** 
     * @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么) 
     */
    private static final long serialVersionUID = 1309891375516104381L;

    // 留言人
    private String receiver;

    // 接收人
    private String commenter;

    // 类型
    private String type;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getReceiver() {
        return receiver;
    }

    public void setReceiver(String receiver) {
        this.receiver = receiver;
    }

    public String getCommenter() {
        return commenter;
    }

    public void setCommenter(String commenter) {
        this.commenter = commenter;
    }
}
