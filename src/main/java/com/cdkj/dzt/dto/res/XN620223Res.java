package com.cdkj.dzt.dto.res;

public class XN620223Res {
    // 待量体订单
    private Long toMeasureOrder;

    // 待支付订单
    private Long toPayOrder;

    // 待收货订单
    private Long toReceiverOrder;

    // 待评论订单
    private Long toCommentOrder;

    public Long getToPayOrder() {
        return toPayOrder;
    }

    public void setToPayOrder(Long toPayOrder) {
        this.toPayOrder = toPayOrder;
    }

    public Long getToReceiverOrder() {
        return toReceiverOrder;
    }

    public void setToReceiverOrder(Long toReceiverOrder) {
        this.toReceiverOrder = toReceiverOrder;
    }

    public Long getToCommentOrder() {
        return toCommentOrder;
    }

    public void setToCommentOrder(Long toCommentOrder) {
        this.toCommentOrder = toCommentOrder;
    }

    public Long getToMeasureOrder() {
        return toMeasureOrder;
    }

    public void setToMeasureOrder(Long toMeasureOrder) {
        this.toMeasureOrder = toMeasureOrder;
    }
}
