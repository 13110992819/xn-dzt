package com.xnjr.mall.bo.impl;

import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.xnjr.mall.bo.IStorePurchaseBO;
import com.xnjr.mall.bo.base.PaginableBOImpl;
import com.xnjr.mall.core.OrderNoGenerater;
import com.xnjr.mall.dao.IStorePurchaseDAO;
import com.xnjr.mall.domain.Store;
import com.xnjr.mall.domain.StorePurchase;
import com.xnjr.mall.domain.User;
import com.xnjr.mall.enums.ECurrency;
import com.xnjr.mall.enums.EGeneratePrefix;
import com.xnjr.mall.enums.EPayType;
import com.xnjr.mall.enums.EStorePurchaseStatus;

@Component
public class StorePurchaseBOImpl extends PaginableBOImpl<StorePurchase>
        implements IStorePurchaseBO {

    @Autowired
    private IStorePurchaseDAO storePurchaseDAO;

    @Override
    public List<StorePurchase> queryStorePurchaseList(StorePurchase condition) {
        return storePurchaseDAO.selectList(condition);
    }

    @Override
    public int refreshStatus(String code, String status) {
        int count = 0;
        if (StringUtils.isNotBlank(status)) {
            StorePurchase data = new StorePurchase();
            data.setCode(code);
            data.setStatus(status);
            data.setPayDatetime(new Date());
            count = storePurchaseDAO.updateStatus(data);
        }
        return count;
    }

    @Override
    public String storePurchaseCGcgb(User user, Store store, Long price,
            Long fdAmount) {
        String code = OrderNoGenerater.generateM(EGeneratePrefix.STORE_PURCHASW
            .getCode());
        Date now = new Date();
        StorePurchase data = new StorePurchase();
        data.setCode(code);
        data.setUserId(user.getUserId());
        data.setStoreCode(store.getCode());
        data.setPrice(price);
        data.setBackAmount(fdAmount);
        data.setBackCurrency(ECurrency.CNY.getCode());
        data.setCreateDatetime(now);
        data.setStatus(EStorePurchaseStatus.PAYED.getCode());
        data.setPayType(EPayType.INTEGRAL.getCode());

        data.setPayAmount2(price);
        data.setPayDatetime(now);
        data.setRemark("菜狗币支付O2O消费");
        data.setSystemCode(store.getSystemCode());
        data.setCompanyCode(store.getCompanyCode());

        return code;
    }

    @Override
    public String storePurchaseCGWX(User user, Store store, Long amount, Long jf) {
        String payGroup = OrderNoGenerater
            .generateM(EGeneratePrefix.STORE_PURCHASW.getCode());
        Date now = new Date();
        StorePurchase data = new StorePurchase();
        data.setCode(OrderNoGenerater.generateM(EGeneratePrefix.STORE_PURCHASW
            .getCode()));
        data.setUserId(user.getUserId());
        data.setStoreCode(store.getCode());
        data.setPrice(amount);

        data.setCreateDatetime(now);
        data.setStatus(EStorePurchaseStatus.PAYED.getCode());
        data.setPayType(EPayType.WEIXIN.getCode());
        data.setPayGroup(payGroup);

        data.setPayAmount2(jf);
        data.setPayDatetime(now);
        data.setRemark("微信支付O2O消费");
        data.setSystemCode(store.getSystemCode());
        data.setCompanyCode(store.getCompanyCode());

        return payGroup;
    }

    @Override
    public String storePurchaseZHWX(User user, Store store, Long amount) {
        /*
         * // 落地本地系统消费记录，状态为未支付 StorePurchase data = new StorePurchase();
         * data.setUserId(userId); data.setStoreCode(storeCode);
         * data.setPayType(EPayType.WEIXIN.getCode());
         * data.setPurchaseAmount(amount); data.setAmount1(yhAmount);
         * data.setStatus(EStorePurchaseStatus.TO_PAY.getCode());
         * data.setSystemCode(systemCode); data.setRemark(remark);
         * storePurchaseBO.saveStorePurchase(data);
         */
        return null;
    }

    @Override
    public String storePurchaseZHYE(User user, Store store, Long amount) {
        /*
         * StorePurchase data = new StorePurchase(); data.setUserId(userId);
         * data.setStoreCode(storeCode);
         * data.setPayType(EPayType.ZH_YE.getCode());
         * data.setPurchaseAmount(amount); data.setAmount1(yhAmount);
         * data.setAmount2(gxjlAmount); data.setAmount3(frAmount);
         * data.setStatus(EStorePurchaseStatus.PAYED.getCode());
         * data.setTicketCode(ticketCode); data.setSystemCode(systemCode);
         * data.setRemark(remark);
         */
        return null;
    }

    /** 
     * @see com.xnjr.mall.bo.IStorePurchaseBO#getTotalIncome(java.lang.String)
     */
    @Override
    public Long getTotalIncome(String storeCode) {
        Long result = 0L;
        StorePurchase condition = new StorePurchase();
        condition.setStoreCode(storeCode);
        condition.setStatus(EStorePurchaseStatus.PAYED.getCode());
        List<StorePurchase> list = storePurchaseDAO.selectList(condition);
        for (StorePurchase storePurchase : list) {
            if (EPayType.WEIXIN.getCode().equals(storePurchase.getPayType())
                    || EPayType.ALIPAY.getCode().equals(
                        storePurchase.getPayType())) {
                result += storePurchase.getPayAmount1();
            }
        }
        return result;
    }
}
