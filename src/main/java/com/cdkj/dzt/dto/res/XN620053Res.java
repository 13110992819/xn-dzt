package com.cdkj.dzt.dto.res;

import com.cdkj.dzt.domain.Craft;

/**
 * 详情查询工艺
 * @author: asus 
 * @since: 2017年8月15日 下午9:07:27 
 * @history:
 */
public class XN620053Res {
    // 工艺
    private Craft craft;

    // 是否收藏
    private String isSC;

    // 是否有过订单
    private String isOrder;

    public Craft getCraft() {
        return craft;
    }

    public void setCraft(Craft craft) {
        this.craft = craft;
    }

    public String getIsSC() {
        return isSC;
    }

    public void setIsSC(String isSC) {
        this.isSC = isSC;
    }

    public String getIsOrder() {
        return isOrder;
    }

    public void setIsOrder(String isOrder) {
        this.isOrder = isOrder;
    }

}
