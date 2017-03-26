package com.xnjr.mall.ao.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xnjr.mall.ao.IStoreActionAO;
import com.xnjr.mall.bo.IStoreActionBO;
import com.xnjr.mall.bo.IStoreBO;
import com.xnjr.mall.bo.base.Paginable;
import com.xnjr.mall.domain.Store;
import com.xnjr.mall.domain.StoreAction;

@Service
public class StoreActionAOImpl implements IStoreActionAO {

    @Autowired
    private IStoreActionBO storeActionBO;

    @Autowired
    private IStoreBO storeBO;

    @Override
    public void doAction(String storeCode, String userId, String type) {
        StoreAction dbAction = storeActionBO.getStoreAction(storeCode, userId,
            type);
        if (dbAction != null) {// 已有则反向
            storeActionBO.removeStoreAction(dbAction.getCode());
        } else {
            Store store = storeBO.getStore(storeCode);
            storeActionBO.saveStoreAction(store, userId, type);
        }

    }

    @Override
    public Paginable<StoreAction> queryStoreActionPage(int start, int limit,
            StoreAction condition) {
        return storeActionBO.getPaginable(start, limit, condition);
    }

    @Override
    public List<StoreAction> queryStoreActionList(StoreAction condition) {
        return storeActionBO.queryStoreActionList(condition);
    }

    @Override
    public StoreAction getStoreAction(String code) {
        return storeActionBO.getStoreAction(code);
    }
}
