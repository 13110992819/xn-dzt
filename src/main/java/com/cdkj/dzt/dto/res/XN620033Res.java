package com.cdkj.dzt.dto.res;

import com.cdkj.dzt.domain.Cloth;

/**
 * 详情查询面料
 * @author: asus 
 * @since: 2017年8月16日 下午1:40:25 
 * @history:
 */
public class XN620033Res {
    // 面料
    private Cloth cloth;

    // 是否收藏
    private String isSC;

    // 是否有过订单
    private String isOrder;

    public Cloth getCloth() {
        return cloth;
    }

    public void setCloth(Cloth cloth) {
        this.cloth = cloth;
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
