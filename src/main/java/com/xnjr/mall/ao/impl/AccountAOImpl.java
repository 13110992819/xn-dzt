/**
 * @Title AccountAOImpl.java 
 * @Package com.xnjr.mall.ao.impl 
 * @Description 
 * @author xieyj  
 * @date 2017年1月4日 下午10:02:38 
 * @version V1.0   
 */
package com.xnjr.mall.ao.impl;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xnjr.mall.ao.IAccountAO;
import com.xnjr.mall.bo.IAccountBO;
import com.xnjr.mall.bo.IStoreBO;
import com.xnjr.mall.bo.IUserBO;
import com.xnjr.mall.domain.Store;
import com.xnjr.mall.enums.EBizType;
import com.xnjr.mall.enums.ECurrency;
import com.xnjr.mall.enums.EUserKind;
import com.xnjr.mall.exception.BizException;

/** 
 * @author: xieyj 
 * @since: 2017年1月4日 下午10:02:38 
 * @history:
 */
@Service
public class AccountAOImpl implements IAccountAO {

    @Autowired
    IAccountBO accountBO;

    @Autowired
    IUserBO userBO;

    @Autowired
    IStoreBO storeBO;

    /** 
     * @see com.xnjr.mall.ao.IAccountAO#doTransferB2CUserCG(java.lang.String, java.lang.String, java.lang.Long, java.lang.String)
     */
    @Override
    public void doTransferB2CUserCG(String storeOwner, String mobile, Long amount,
            String currency) {
        // 验证参数
        userBO.getRemoteUser(storeOwner);
        Store store = storeBO.getStoreByUser(storeOwner);
        String toUserId = userBO.isUserExist(mobile, EUserKind.F1,
            store.getSystemCode());
        if (StringUtils.isBlank(toUserId)) {
            throw new BizException("xn0000", "手机号用户不存在");
        }
        EBizType ebizType = null;
        if (ECurrency.CG_CGB.getCode().equals(currency)) {
            ebizType = EBizType.CG_GIVE_AMOUNT_DX;
        } else if (ECurrency.JF.getCode().equals(currency)) {
            ebizType = EBizType.CG_GIVE_AMOUNT_DF;
        } else {
            throw new BizException("xn0000", "兑换币种不支持");
        }
        ECurrency eCurrency = ECurrency.getResultMap().get(currency);
        accountBO.doTransferAmountRemote(storeOwner, toUserId, eCurrency,
            amount, ebizType, ebizType.getValue() + "，手机号用户《" + mobile + "》",
            "商家《" + store.getName() + "》" + ebizType.getValue());
    }

    /** 
     * @see com.xnjr.mall.ao.IAccountAO#doTransferB2BUserCG(java.lang.String, java.lang.String, java.lang.Long, java.lang.String)
     */
    @Override
    public void doTransferB2BUserCG(String fromUserId, String toUserId, Long amount,
            String currency) {
        // 验证参数
        userBO.getRemoteUser(fromUserId);
        userBO.getRemoteUser(toUserId);

        EBizType ebizType = null;
        if (ECurrency.CG_CGB.getCode().equals(currency)) {
            ebizType = EBizType.CG_GIVE_AMOUNT_DX;
        } else if (ECurrency.JF.getCode().equals(currency)) {
            ebizType = EBizType.CG_GIVE_AMOUNT_DF;
        } else {
            throw new BizException("xn0000", "兑换币种不支持");
        }
        ECurrency eCurrency = ECurrency.getResultMap().get(currency);
        accountBO.doTransferAmountRemote(fromUserId, toUserId, eCurrency,
            amount, ebizType, ebizType.getValue(), ebizType.getValue());
    }
}
