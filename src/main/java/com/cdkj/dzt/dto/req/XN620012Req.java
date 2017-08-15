package com.cdkj.dzt.dto.req;

/**
 * 列表获取型号
 * @author: asus 
 * @since: 2017年4月14日 下午3:17:00 
 * @history:
 */
public class XN620012Req extends APageReq {

    /** 
     * @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么) 
     */
    private static final long serialVersionUID = 4027176534210966629L;

    // 名称（选填）
    private String name;

    // 类型（选填）
    private String type;

    // 状态（选填）
    private String status;

    // 更新人（选填）
    private String updater;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUpdater() {
        return updater;
    }

    public void setUpdater(String updater) {
        this.updater = updater;
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

}
