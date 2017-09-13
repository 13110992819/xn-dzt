package com.cdkj.dzt.dto.req;

/** 
 * 详情查询型号（Bfront）
 * @author: haiqingzheng 
 * @since: 2017年4月14日 下午2:55:39 
 * @history:
 */
public class XN620014Req extends APageReq {
    /** 
     * @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么) 
     */
    private static final long serialVersionUID = 7272273990340159824L;

    // 名称（选填）
    private String name;

    // 类型（选填）
    private String type;

    // 状态（选填）
    private String status;

    // UI位置（选填）
    private String location;

    // 更新人（选填）
    private String updater;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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
