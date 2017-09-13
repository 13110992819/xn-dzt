/**
 * @Title XN620002Req.java 
 * @Package com.cdkj.dzt.dto.req 
 * @Description 
 * @author leo(haiqing)  
 * @date 2017年4月14日 下午2:48:36 
 * @version V1.0   
 */
package com.cdkj.dzt.dto.req;

/** 
 * 修改型号
 * @author: haiqingzheng 
 * @since: 2017年4月14日 下午2:48:36 
 * @history:
 */
public class XN620002Req {
    // 编号(必填)
    private String code;

    // 类型(必填)
    private String type;

    // 分类
    private String kind;

    // 名称(必填)
    private String name;

    // 缩略图(必填)
    private String pic;

    // 广告图(必填)
    private String advPic;

    // 图文描述(必填)
    private String description;

    // 损耗(选填)
    private String loss;

    // 加工费(选填)
    private String processFee;

    // 价格(选填)
    private String price;

    // 更新人(必填)
    private String updater;

    // 备注(选填)
    private String remark;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
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

    public String getAdvPic() {
        return advPic;
    }

    public void setAdvPic(String advPic) {
        this.advPic = advPic;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getLoss() {
        return loss;
    }

    public void setLoss(String loss) {
        this.loss = loss;
    }

    public String getProcessFee() {
        return processFee;
    }

    public void setProcessFee(String processFee) {
        this.processFee = processFee;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getUpdater() {
        return updater;
    }

    public void setUpdater(String updater) {
        this.updater = updater;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getKind() {
        return kind;
    }

    public void setKind(String kind) {
        this.kind = kind;
    }
}
