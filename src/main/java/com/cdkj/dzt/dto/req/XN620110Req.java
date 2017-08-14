package com.cdkj.dzt.dto.req;

/**
 * 新增文章
 * @author: asus 
 * @since: 2017年8月14日 上午11:21:32 
 * @history:
 */
public class XN620110Req {
    // 类型（必填）
    private String type;

    // 标题（必填）
    private String title;

    // 缩略图（必填）
    private String pic;

    // 广告图（必填）
    private String advPic;

    // 图文描述（必填）
    private String description;

    // 更新人（必填）
    private String updater;

    // 备注（选填）
    private String remark;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
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
}
