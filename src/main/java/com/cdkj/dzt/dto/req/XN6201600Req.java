package com.cdkj.dzt.dto.req;


/**
 * 评论
 * @author: asus 
 * @since: 2017年7月19日 上午11:35:34 
 * @history:
 */
public class XN6201600Req {

    // 内容（必填）
    private String content;

    // 评论人（必填）
    private String commer;

    // 订单编号（必填）
    private String orderCode;

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getCommer() {
        return commer;
    }

    public void setCommer(String commer) {
        this.commer = commer;
    }

    public String getOrderCode() {
        return orderCode;
    }

    public void setOrderCode(String orderCode) {
        this.orderCode = orderCode;
    }
}
