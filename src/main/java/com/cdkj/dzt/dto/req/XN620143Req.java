package com.cdkj.dzt.dto.req;

/**
 * 阅读留言
 * @author: asus 
 * @since: 2017年8月16日 下午2:52:56 
 * @history:
 */
public class XN620143Req {
    // 类型(必填)
    private String type;

    // 评论人(必填)
    private String commenter;

    // 接收人(必填)
    private String receiver;

    public String getCommenter() {
        return commenter;
    }

    public void setCommenter(String commenter) {
        this.commenter = commenter;
    }

    public String getReceiver() {
        return receiver;
    }

    public void setReceiver(String receiver) {
        this.receiver = receiver;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
