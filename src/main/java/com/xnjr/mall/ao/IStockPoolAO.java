package com.xnjr.mall.ao;

import com.xnjr.mall.bo.base.Paginable;
import com.xnjr.mall.domain.StockPool;

public interface IStockPoolAO {
    static final String DEFAULT_ORDER_COLUMN = "id";

    public void addAmount(String code, Long amount);

    public Paginable<StockPool> queryStockPoolPage(int start, int limit,
            StockPool condition);

    public StockPool getStockPool(String code);

}
