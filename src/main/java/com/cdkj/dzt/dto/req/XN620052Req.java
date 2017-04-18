package com.cdkj.dzt.dto.req;

/**
 * 修改型号规格
 * @author: asus 
 * @since: 2017年4月14日 下午3:24:11 
 * @history:
 */
public class XN620052Req {

    // 编号(必填)
    private String code;

    // 名称(必填)
    private String name;

    // 图片(选填)
    private String pic;

    // 次序(必填)
    private String orderNo;

    // 备注(选填)
    private String remark;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPic() {
        return pic;
    }

    public void setPic(String pic) {
        this.pic = pic;
    }

    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

}
