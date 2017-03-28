package com.xnjr.mall.dto.req;

/**
 * @author: xieyj 
 * @since: 2017年1月15日 下午5:46:35 
 * @history:
 */
public class XN808800Req {

    // 商家编号(必填)
    private String storeOwner;

    // 手机号(必填)
    private String mobile;

    // 划转金额(必填)
    private String amount;

    // 币种(菜狗币和积分币)(必填)
    private String currency;

    public String getStoreOwner() {
        return storeOwner;
    }

    public void setStoreOwner(String storeOwner) {
        this.storeOwner = storeOwner;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }
}
