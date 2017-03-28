package com.xnjr.mall.bo;

import java.util.List;

import com.xnjr.mall.bo.base.IPaginableBO;
import com.xnjr.mall.domain.Store;
import com.xnjr.mall.domain.StorePurchase;
import com.xnjr.mall.domain.User;

public interface IStorePurchaseBO extends IPaginableBO<StorePurchase> {

    public String storePurchaseCGcgb(User user, Store store, Long amount,
            Long fdAmount);

    public String storePurchaseCGWX(User user, Store store, Long amount, Long jf);

    public String storePurchaseZHWX(User user, Store store, Long amount);

    public String storePurchaseZHYE(User user, Store store, Long amount);

    public List<StorePurchase> queryStorePurchaseList(StorePurchase condition);

    public int refreshStatus(String code, String status);

    /** 
     * 获取店铺人民币总收入
     * @param storeCode 
     * @create: 2017年3月28日 下午5:11:25 xieyj
     * @history: 
     */
    public Long getTotalIncome(String storeCode);

}
