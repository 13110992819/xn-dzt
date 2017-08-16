package com.cdkj.dzt.dto.req;

/**
 * 客服/顾问回复用户留言
 * @author: asus 
 * @since: 2017年8月16日 下午2:42:48 
 * @history:
 */
public class XN620141Req {
    // 留言人(必填)
    private String commenter;

    // 内容(必填)
    private String content;

    // 接收人(必填)
    private String receiver;

    public String getCommenter() {
        return commenter;
    }

    public void setCommenter(String commenter) {
        this.commenter = commenter;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getReceiver() {
        return receiver;
    }

    public void setReceiver(String receiver) {
        this.receiver = receiver;
    }
}
