package com.cdkj.dzt.dto.req;

/**
 * 收藏
 * @author: asus 
 * @since: 2017年8月15日 下午10:24:50 
 * @history:
 */
public class XN620130Req {
    // 对象编号（必填）
    private String objectCode;

    // 操作人（必填）
    private String operator;

    public String getObjectCode() {
        return objectCode;
    }

    public void setObjectCode(String objectCode) {
        this.objectCode = objectCode;
    }

    public String getOperator() {
        return operator;
    }

    public void setOperator(String operator) {
        this.operator = operator;
    }

}
