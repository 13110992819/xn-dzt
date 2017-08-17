package com.cdkj.dzt.dto.req;

import java.util.List;

/**
 *我的订单分页查询
 * @author: asus 
 * @since: 2017年4月17日 下午8:11:56 
 * @history:
 */
public class XN620233Req extends APageReq {

    /** 
     * @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么) 
     */
    private static final long serialVersionUID = 3598608816628978402L;

    // 下单人UserId（必填）
    private String applyUser;

    // 状态（选填）
    private String status;

    // 搜索条件
    private List<String> statusList;

    public String getApplyUser() {
        return applyUser;
    }

    public void setApplyUser(String applyUser) {
        this.applyUser = applyUser;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<String> getStatusList() {
        return statusList;
    }

    public void setStatusList(List<String> statusList) {
        this.statusList = statusList;
    }

}
