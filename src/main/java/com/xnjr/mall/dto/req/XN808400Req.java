package com.xnjr.mall.dto.req;

public class XN808400Req {
    // 编号（必填）
    private String code;

    // 增加的余额（必填）
    private String amount;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

}
