package com.xnjr.mall.dto.req;

public class XN808032Req {
    // 编号(必填)
    private String code;

    // key(必填)
    private String dkey;

    // value(必填)
    private String dvalue;

    // 相对位置编号(必填)
    private Integer orderNo;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getDkey() {
        return dkey;
    }

    public void setDkey(String dkey) {
        this.dkey = dkey;
    }

    public String getDvalue() {
        return dvalue;
    }

    public void setDvalue(String dvalue) {
        this.dvalue = dvalue;
    }

    public Integer getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(Integer orderNo) {
        this.orderNo = orderNo;
    }

}
