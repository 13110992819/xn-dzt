package com.xnjr.mall.ao;

import java.util.List;

import com.xnjr.mall.bo.base.Paginable;
import com.xnjr.mall.domain.StorePurchase;
import com.xnjr.mall.dto.res.XN802180Res;

public interface IStorePurchaseAO {
    static final String DEFAULT_ORDER_COLUMN = "code";

    /**
     * 消费买单
     * @param userId
     * @param storeCode
     * @param ticketCode
     * @param amount
     * @return 
     * @create: 2016年12月29日 上午11:03:25 xieyj
     * @history:
     */
    public XN802180Res storePurchase(String userId, String storeCode,
            String ticketCode, Long amount, String payType, String ip);

    public int dropStorePurchase(String code);

    public Paginable<StorePurchase> queryStorePurchasePage(int start,
            int limit, StorePurchase condition);

    public List<StorePurchase> queryStorePurchaseList(StorePurchase condition);

    public StorePurchase getStorePurchase(String code);

}
