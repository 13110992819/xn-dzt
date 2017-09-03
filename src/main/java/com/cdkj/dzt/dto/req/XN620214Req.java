package com.cdkj.dzt.dto.req;

/**
 * 评论
 * @author: asus 
 * @since: 2017年8月18日 上午10:13:58 
 * @history:
 */
public class XN620214Req {
    // 订单号（必填）
    private String orderCode;

    // 评论内容（必填）
    private String content;

    // 评论人（必填）
    private String commenter;

    public String getOrderCode() {
        return orderCode;
    }

    public void setOrderCode(String orderCode) {
        this.orderCode = orderCode;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getCommenter() {
        return commenter;
    }

    public void setCommenter(String commenter) {
        this.commenter = commenter;
    }
}
