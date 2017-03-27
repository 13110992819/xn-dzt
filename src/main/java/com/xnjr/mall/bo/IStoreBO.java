package com.xnjr.mall.bo;

import java.util.List;

import com.xnjr.mall.bo.base.IPaginableBO;
import com.xnjr.mall.domain.Store;

/** 
 * 商家BO
 * @author: zuixian 
 * @since: 2016年9月20日 下午1:03:46 
 * @history:
 */
public interface IStoreBO extends IPaginableBO<Store> {

    public void saveStoreOss(Store data);

    public void saveStore(Store data);

    public int refreshStore(Store data);

    public void checkStore(Store dbStore, String checkResult, String checkUser,
            String remark);

    public void putOn(Store dbStore);

    public void putOff(Store dbStore);

    public void openClose(Store dbStore);

    public void upLevel(Store dbStore);

    public int refreshTotalRmbNum(Store dbStore);

    public int refreshTotalJfNum(Store dbStore);

    public int refreshTotalDzNum(Store dbStore);

    public int refreshTotalScNum(Store dbStore);

    public List<Store> queryStoreList(Store data);

    public Store getStore(String storeCode);

    public String isUserRefereeExist(String userReferee, String systemCode);

    /**
     * 一个人只能有一家店铺
     * @param bUser
     * @param mobile 
     * @create: 2017年3月27日 下午3:15:36 xieyj
     * @history:
     */
    public void checkStoreByUser(String bUser, String mobile);
}
