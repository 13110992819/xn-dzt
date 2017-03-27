package com.xnjr.mall.ao.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xnjr.mall.ao.IStockPoolAO;
import com.xnjr.mall.bo.IStockPoolBO;
import com.xnjr.mall.bo.base.Paginable;
import com.xnjr.mall.domain.StockPool;

@Service
public class StockPoolAOImpl implements IStockPoolAO {

    @Autowired
    private IStockPoolBO stockPoolBO;

    @Override
    public Paginable<StockPool> queryStockPoolPage(int start, int limit,
            StockPool condition) {
        return stockPoolBO.getPaginable(start, limit, condition);
    }

    @Override
    public StockPool getStockPool(String code) {
        return stockPoolBO.getStockPool(code);
    }

    @Override
    public void addAmount(String code, Long amount) {
        StockPool pool = stockPoolBO.getStockPool(code);
        stockPoolBO.addAmount(pool, amount);
    }
}
