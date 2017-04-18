package com.cdkj.dzt.dto.req;

import java.util.Map;

/**
 * 数据录入
 * @author: asus 
 * @since: 2017年4月14日 下午5:22:15 
 * @history:
 */
public class XN620205Req {
    // 订单编号
    private String orderCode;

    // 成衣数据
    private Map<String, String> map;

    // 更新人
    private String updater;

    // 备注
    private String remark;

    public String getOrderCode() {
        return orderCode;
    }

    public void setOrderCode(String orderCode) {
        this.orderCode = orderCode;
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

    public Map<String, String> getMap() {
        return map;
    }

    public void setMap(Map<String, String> map) {
        this.map = map;
    }
}
