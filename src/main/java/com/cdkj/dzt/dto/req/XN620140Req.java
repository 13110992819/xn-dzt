package com.cdkj.dzt.dto.req;

/**
 * 用户向客服/顾问留言
 * @author: asus 
 * @since: 2017年8月16日 下午2:42:48 
 * @history:
 */
public class XN620140Req {
    // 类型(必填)
    private String type;

    // 留言人(必填)
    private String commenter;

    // 内容(必填)
    private String content;

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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
