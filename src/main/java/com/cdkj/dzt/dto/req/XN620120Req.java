package com.cdkj.dzt.dto.req;

/**
 * 文章分页查
 * @author: asus 
 * @since: 2017年8月14日 上午11:37:00 
 * @history:
 */
public class XN620120Req extends APageReq {

    /** 
     * @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么) 
     */
    private static final long serialVersionUID = 8756896036782462275L;

    // 标题（选填）
    private String title;

    // 类型（选填）
    private String type;

    // 状态（选填）
    private String status;

    // UI位置（选填）
    private String location;

    // 更新人（选填）
    private String updater;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getUpdater() {
        return updater;
    }

    public void setUpdater(String updater) {
        this.updater = updater;
    }
}
