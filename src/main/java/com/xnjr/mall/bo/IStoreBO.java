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

    /** 
     * 判断名称是否存在
     * @param code
     * @return 
     * @create: 2016年9月20日 下午1:03:43 zuixian
     * @history: 
     */
    public boolean isStoreExist(String code);

    /** 
     * 商家入驻
     * @param data
     * @return 
     * @create: 2016年9月20日 下午1:04:23 zuixian
     * @history: 
     */
    public void saveStore(Store data);

    public void saveStoreOss(Store store);

    /** 
     * 更新商家信息
     * @param data
     * @return 
     * @create: 2016年9月20日 下午1:05:11 zuixian
     * @history: 
     */
    public int refreshStore(Store data);

    /**
     * 更新商家点赞数
     * @param code
     * @param count 1 点赞，-1 取消点赞
     * @return 
     * @create: 2016年9月24日 下午3:30:51 xieyj
     * @history:
     */
    public int refreshStoreDz(String code, int times);

    /** 
     * 更新商家累计积分数
     * @param data
     * @return 
     * @create: 2016年9月20日 下午1:06:35 zuixian
     * @history: 
     */
    public int refreshStoreJF(Store data);

    /** 
     * 列表查询商家信息
     * @param data
     * @return 
     * @create: 2016年9月20日 下午1:41:43 zuixian
     * @history: 
     */
    public List<Store> queryStoreList(Store data);

    /**
     * 获取商家详情
     * @param storeCode
     * @return 
     * @create: 2016年9月23日 下午1:21:34 xieyj
     * @history:
     */
    public Store getStore(String storeCode);

    public String isUserRefereeExist(String userReferee, String systemCode);

    public void checkStore(Store dbStore, String checkResult, String checkUser,
            String remark);

    /**
     * 一个人只能有一家店铺
     * @param bUser
     * @return 
     * @create: 2017年3月26日 下午3:48:58 myb858
     * @history:
     */
    public Store getStoreByUser(String bUser);

    public void putOn(Store dbStore);

    public void putOff(Store dbStore);

    public void closeOpen(Store dbStore);

    public void upLevel(Store dbStore);

}
