package com.cdkj.dzt.dto.req;

/**
 * 分页查询我的收藏
 * @author: asus 
 * @since: 2017年8月16日 下午1:57:54 
 * @history:
 */
public class XN620135Req extends APageReq {

    /** 
     * @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么) 
     */
    private static final long serialVersionUID = 6692209656039727525L;

    // 分类(选填)1 文章，2产品，3面料，4工艺
    private String category;

    // 类型(选填)1收藏
    private String type;

    // 收藏对象(选填)
    private String objectCode;

    // 操作人(必填)
    private String operator;

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

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
