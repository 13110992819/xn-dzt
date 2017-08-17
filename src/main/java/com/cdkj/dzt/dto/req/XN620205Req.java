package com.cdkj.dzt.dto.req;

import java.util.List;

/**
 * 产品定价（H+）
 * @author: asus 
 * @since: 2017年8月17日 下午9:59:32 
 * @history:
 */
public class XN620205Req {
    // 订单编号
    private String orderCode;

    // 数量
    private String quantity;

    // 编号List
    private List<String> codeList;

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

    public List<String> getCodeList() {
        return codeList;
    }

    public void setCodeList(List<String> codeList) {
        this.codeList = codeList;
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

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

}
