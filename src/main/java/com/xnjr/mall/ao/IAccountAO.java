package com.xnjr.mall.ao;

/** 
 * @author: xieyj 
 * @since: 2017年1月4日 下午9:11:54 
 * @history:
 */
public interface IAccountAO {

    /**
     * B端商户代销代发C端用户菜狗币或者积分
     * @create: 2017年3月28日 下午12:57:39 xieyj
     * @history:
     */
    public void doTransferB2CUserCG(String storeOwner, String mobile, Long amount,
            String currency);

    /**
     * B端对B端用户代销代发菜狗币或者积分
     * @param fromUser
     * @param toUser
     * @param amount
     * @param currency 
     * @create: 2017年3月28日 下午3:37:41 xieyj
     * @history:
     */
    public void doTransferB2BUserCG(String fromUser, String toUser, Long amount,
            String currency);
}
