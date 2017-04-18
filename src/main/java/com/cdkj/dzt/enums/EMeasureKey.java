package com.cdkj.dzt.enums;

public enum EMeasureKey {
    CSGG("1-1", "衬衫规格"), CSML("1-2", "衬衫面料"), LXXZ("1-3", "领型选择"), XXXZ("1-4",
            "袖型选择"), MZXZ("1-5", "门襟选择"), XBXZ("1-6", "下摆选择"), SXXZ("1-7",
            "收省选择"), LKYZ("1-8", "领口颜色"), KDXZ("1-9", "口袋选择"), NKXZ("1-10",
            "纽扣选择"), NKYS("1-11", "纽扣颜色"), LWCL("2-1", "领围测量"), SWCL("2-2",
            "胸围测量"), YWCL("2-3", "腰围测量"), TWCL("2-4", "臀围测量"), JKCL("2-5",
            "肩宽测量"), YCCL("2-6", "衣长测量"), XCCL("2-7", "袖长测量"), BWCL("2-8",
            "臀围测量"), WWCL("2-9", "腕围测量"), LWCY("3-1", "领围成衣"), SWCY("3-2",
            "胸围成衣"), YWCY("3-3", "腰围成衣"), TWCY("3-4", "臀围成衣"), JKCY("3-5",
            "肩宽成衣"), YCCY("3-6", "衣长成衣"), XCCY("3-7", "袖长成衣"), BWCY("3-8",
            "臀围成衣"), WWCY("3-9", "腕围成衣"), TX("4-1", "体型"), QXK("4-2", "前胸宽"), HBK(
            "4-3", "后背宽"), ZTW("4-4", "中臀围"), JXL("4-5", "肩斜量"), FBL("4-6",
            "腹部量"), BZ("4-7", "脖子"), BB("4-8", "背部"), GXCX("5-1", "个性刺绣"), CXWZ(
            "5-2", "刺绣位置"), CDZT("5-3", "刺绣字体"), CSYS("5-4", "刺绣颜色"), NL("6-1",
            "年龄"), SG("6-2", "身高"), TZ("6-3", "体重"), YJDZ("6-4", "邮寄地址");

    EMeasureKey(String code, String value) {
        this.code = code;
        this.value = value;
    }

    private String code;

    private String value;

    public String getCode() {
        return code;
    }

    public String getValue() {
        return value;
    }

}
