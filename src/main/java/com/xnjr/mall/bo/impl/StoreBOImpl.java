package com.xnjr.mall.bo.impl;

import java.util.Date;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.xnjr.mall.bo.IStoreBO;
import com.xnjr.mall.bo.IUserBO;
import com.xnjr.mall.bo.base.PaginableBOImpl;
import com.xnjr.mall.core.OrderNoGenerater;
import com.xnjr.mall.dao.IStoreDAO;
import com.xnjr.mall.domain.Store;
import com.xnjr.mall.enums.EBoolean;
import com.xnjr.mall.enums.EStoreStatus;
import com.xnjr.mall.enums.ESystemCode;
import com.xnjr.mall.enums.EUserKind;
import com.xnjr.mall.exception.BizException;

/** 
 * 商家BO
 * @author: zuixian 
 * @since: 2016年9月20日 下午1:23:19 
 * @history:
 */
@Component
public class StoreBOImpl extends PaginableBOImpl<Store> implements IStoreBO {

    @Autowired
    private IStoreDAO storeDAO;

    @Autowired
    private IUserBO userBO;

    @Override
    public void saveStore(Store data) {
        if (data != null) {
            storeDAO.insert(data);
        }
    }

    @Override
    public void saveStoreOss(Store store) {
        if (store != null) {
            storeDAO.insert(store);
        }
    }

    @Override
    public int refreshStore(Store data) {
        int count = 0;
        if (data != null) {
            count = storeDAO.update(data);
        }
        return count;
    }

    @Override
    public Store getStore(String code) {
        if (StringUtils.isNotBlank(code)) {
            Store condition = new Store();
            condition.setCode(code);
            return storeDAO.select(condition);
        } else {
            throw new BizException("xn000000", "商家编号不存在");
        }
    }

    @Override
    public List<Store> queryStoreList(Store data) {
        return storeDAO.selectList(data);
    }

    @Override
    public String isUserRefereeExist(String userReferee, String systemCode) {
        if (ESystemCode.ZHPAY.getCode().equals(systemCode)) {
            String userId = userBO.isUserExist(userReferee, EUserKind.F1,
                systemCode);
            if (StringUtils.isBlank(userId)) {
                throw new BizException("xn702002", "推荐人不存在");
            }
            return userId;
        } else {// 加盟商帮商家代注册，所以userReferee是加盟商的userId
            return userReferee;
        }

    }

    @Override
    public void checkStore(Store dbStore, String checkResult, String checkUser,
            String remark) {
        if (EBoolean.NO.getCode().equals(checkResult)) {
            dbStore.setStatus(EStoreStatus.UNPASS.getCode());
        } else {
            dbStore.setStatus(EStoreStatus.PASS.getCode());
            // 第一次审核通过产生合同编号
            if (StringUtils.isBlank(dbStore.getContractNo())) {
                dbStore.setContractNo(OrderNoGenerater.generateM("ZHS-"));
            }
        }
        dbStore.setUpdater(checkUser);
        dbStore.setUpdateDatetime(new Date());
        dbStore.setRemark(remark);
        storeDAO.updateCheck(dbStore);

    }

    @Override
    public Store getStoreByUser(String bUser) {
        Store a = null;
        Store condition = new Store();
        condition.setOwner(bUser);
        List<Store> list = this.queryStoreList(condition);
        if (CollectionUtils.isNotEmpty(list)) {
            a = list.get(0);
        }
        if (a == null) {
            throw new BizException("xn000000", bUser + "没有店铺");
        }
        return a;
    }

    @Override
    public void putOn(Store dbStore) {
        if (dbStore != null && StringUtils.isNotBlank(dbStore.getCode())) {
            storeDAO.updatePutOn(dbStore);
        }
    }

    @Override
    public void putOff(Store dbStore) {
        if (dbStore != null && StringUtils.isNotBlank(dbStore.getCode())) {
            storeDAO.updatePutOff(dbStore);
        }
    }

    @Override
    public void openClose(Store dbStore) {
        if (dbStore != null && StringUtils.isNotBlank(dbStore.getCode())) {
            storeDAO.updateOpenClose(dbStore);
        }
    }

    @Override
    public void upLevel(Store dbStore) {
        if (dbStore != null && StringUtils.isNotBlank(dbStore.getCode())) {
            storeDAO.updateLevel(dbStore);
        }
    }

    @Override
    public int refreshTotalDzNum(Store data) {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public int refreshTotalJfNum(Store data) {
        // TODO Auto-generated method stub
        return 0;
    }

    /** 
     * @see com.xnjr.mall.bo.IStoreBO#refreshTotalRmbNum(com.xnjr.mall.domain.Store)
     */
    @Override
    public int refreshTotalRmbNum(Store dbStore) {
        return 0;
    }

    /** 
     * @see com.xnjr.mall.bo.IStoreBO#refreshTotalScNum(com.xnjr.mall.domain.Store)
     */
    @Override
    public int refreshTotalScNum(Store dbStore) {
        // TODO Auto-generated method stub
        return 0;
    }

}
