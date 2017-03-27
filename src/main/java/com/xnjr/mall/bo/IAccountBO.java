/**
 * @Title IAccountBO.java 
 * @Package com.ibis.account.bo 
 * @Description 
 * @author miyb  
 * @date 2015-3-15 下午3:15:49 
 * @version V1.0   
 */
package com.xnjr.mall.bo;

import com.xnjr.mall.domain.Account;
import com.xnjr.mall.dto.res.PayBalanceRes;
import com.xnjr.mall.dto.res.XN002500Res;
import com.xnjr.mall.enums.EBizType;
import com.xnjr.mall.enums.ECurrency;

/** 
 * @author: miyb 
 * @since: 2015-3-15 下午3:15:49 
 * @history:
 */
public interface IAccountBO {

    /**
    * 根据用户编号和币种获取账户
    * @param userId
    * @param currency
    * @return 
    * @create: 2017年3月23日 下午12:02:11 myb858
    * @history:
    */
    public Account getRemoteAccount(String userId, ECurrency currency);

    /**
     * 根据用户编号进行账户资金划转
     * @param fromUserId
     * @param toUserId
     * @param currency
     * @param amount
     * @param bizType
     * @param fromBizNote
     * @param toBizNote 
     * @create: 2017年3月26日 下午8:42:38 xieyj
     * @history:
     */
    public void doTransferAmountRemote(String fromUserId, String toUserId,
            ECurrency currency, Long amount, EBizType bizType,
            String fromBizNote, String toBizNote);

    /**
     * 不同币种账户之间划转资金
     * @param systemCode
     * @param fromAccountNumber
     * @param toAccountNumber
     * @param amount
     * @param rate 划转比例
     * @param bizType
     * @param bizNote 
     * @create: 2017年1月6日 下午5:22:31 haiqingzheng
     * @history:
     */
    public void doTransferAmountOnRate(String systemCode,
            String fromAccountNumber, String toAccountNumber, Long amount,
            Double rate, String bizType, String bizNote);

    /**
     * 检查购物币和钱包币余额是否足够
     * @param systemCode
     * @param userId
     * @param gwbPrice
     * @param qbbPrice 
     * @create: 2017年1月10日 下午8:24:48 xieyj
     * @history:
     */
    public void checkGWBQBBAmount(String userId, Long gwbPrice, Long qbbPrice);

    /**
     * 购物和钱包币支付
     * @param systemCode
     * @param fromUserId
     * @param toUserId
     * @param gwbPrice
     * @param qbbPrice
     * @param bizType 
     * @create: 2017年1月10日 下午7:58:07 xieyj
     * @history:
     */
    public void doGWBQBBPay(String fromUserId, String toUserId, Long gwbPrice,
            Long qbbPrice, EBizType bizType);

    /**
     * 检查余额是否足够支付
     * @param systemCode
     * @param fromUserId
     * @param toUserId
     * @param price 
     * @create: 2017年1月11日 上午10:50:05 xieyj
     * @history:
     */
    public void checkBalanceAmount(String userId, Long price);

    /**
     * 余额支付
     * @param systemCode
     * @param fromUserId
     * @param toUserId
     * @param price
     * @param bizType 
     * @create: 2017年1月10日 下午5:44:02 xieyj
     * @history:
     */
    public PayBalanceRes doBalancePay(String fromUserId, String toUserId,
            Long price, EBizType bizType);

    /**
     * 检查购物币和钱包币和余额
     * @param userId
     * @param gwbPrice
     * @param qbbPrice
     * @param cnyPrice 
     * @create: 2017年1月17日 下午6:37:22 xieyj
     * @history:
     */
    public void checkGwQbAndBalance(String userId, Long gwbPrice,
            Long qbbPrice, Long cnyPrice);

    /**
     * 购物，钱包和余额支付
     * @param systemCode
     * @param fromUserId
     * @param toUserId
     * @param gwPrice
     * @param qbPrice
     * @param cnyPrice
     * @param bizType 
     * @create: 2017年1月10日 下午8:27:59 xieyj
     * @history:
     */
    public void doGwQbAndBalancePay(String fromUserId, String toUserId,
            Long gwPrice, Long qbPrice, Long cnyPrice, EBizType bizType);

    public void doCgbJfPay(String fromUserId, String toUserId, Long cgbPrice,
            Long jfPrice, EBizType bizType);

    /**
     * 微信支付
     * @param fromUserId
     * @param toUserId
     * @param amount
     * @param bizType
     * @param fromBizNote
     * @param toBizNote
     * @param payGroup
     * @return 
     * @create: 2017年3月23日 下午8:34:24 xieyj
     * @history:
     */
    public XN002500Res doWeiXinPayRemote(String fromUserId, String toUserId,
            Long amount, EBizType bizType, String fromBizNote,
            String toBizNote, String payGroup);

    public void checkCgbJf(String userId, Long cgbAmount, Long jfAmount);
}
