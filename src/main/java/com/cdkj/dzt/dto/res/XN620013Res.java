package com.cdkj.dzt.dto.res;

import com.cdkj.dzt.domain.Model;

/**
 * 详情查询型号（front）
 * @author: asus 
 * @since: 2017年8月15日 下午4:56:10 
 * @history:
 */
public class XN620013Res {
    // 型号
    private Model model;

    // 是否收藏
    private String isSC;

    // 是否有过订单
    private String isOrder;

    public Model getModel() {
        return model;
    }

    public void setModel(Model model) {
        this.model = model;
    }

    public String getIsOrder() {
        return isOrder;
    }

    public void setIsOrder(String isOrder) {
        this.isOrder = isOrder;
    }

    public String getIsSC() {
        return isSC;
    }

    public void setIsSC(String isSC) {
        this.isSC = isSC;
    }
}
