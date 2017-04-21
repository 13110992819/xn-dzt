package com.cdkj.dzt.common;

public class SysConstants {

    public static final String SP_BYJE = "SP_BYJE"; // 包邮金额

    public static final String SP_YUNFEI = "SP_YUNFEI"; // 订单运费，全系统统一

    public static final String MAX_PERSON_STOCK = "MAX_PERSON_STOCK"; // 最大分红权个数

    public static final String MAX_DAY_STOCK = "MAX_DAY_STOCK"; // 每天最多有效分红权个数

    public static final String USER_STOCK_DAYBACK = "USER_STOCK_DAYBACK"; // 用户每个分红权可以领钱金额

    public static final String STORE_STOCK_DAYBACK = "STORE_STOCK_DAYBACK"; // 商家每个分红权可以领钱金额

    public static final String STORE_RMB2JF = "STORE_RMB2JF"; // 积分消费比例
                                                              // 1人民币等于多少积分

    // ********* 短信通知内容 ***********
    // 用户下单，短信通知地区合伙人
    public static final String APPLY_CONTENT = "尊敬的V&O地区合伙人，您有一个新的预约单等待派单，订单编号：%S，申请人姓名：%S。";

    // 量体师派单/用户一键复购，直接短信通知量体师
    public static final String DISTRIBUTE_CONTENT = "尊敬的V&O量体师，您有一个新的预约单等待处理，订单编号：%S，申请人姓名：%S。";

    // 量体师定价完成，短信通知用户
    public static final String CONFIRM_PRICE_CONTENT = "尊敬的%S，您的订单：%S已经定价完成，订单总价为：%d元，为了不影响您的购物体验，请及时付款。";

    // 用户支付成功，短信通知用户
    public static final String PAY_SUCCESS_CONTENT = "尊敬的%S，您的订单：%S已经支付完成，V&O会尽快处理您的订单，登录网站系统可查看订单详情。";

    // 量体师提交复核，短信通知合伙人
    public static final String LT_SUBMIT_CONTENT = "尊敬的V&O地区合伙人，您有一个新的预约单等待复核，订单编号：%S，申请人姓名：%S。";

    // 平台提交工厂开始生产，短信通知用户
    public static final String SUBMIT_CONTENT = "尊敬的%S，您的订单：%S已经提交至工厂开始生产，V&O会第一时间给您发货，登录网站系统可查看订单详情。";

    // 工厂发货，短信通知用户
    public static final String SENT_CONTENT = "尊敬的%S，您的订单：%S已经发货，登录网站系统可查看订单详情。";

}
