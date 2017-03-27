package com.xnjr.mall.bo;

import com.xnjr.mall.bo.base.IPaginableBO;
import com.xnjr.mall.domain.StockPool;

public interface IStockPoolBO extends IPaginableBO<StockPool> {

    public StockPool getStockPool(String code);

    public int addAmount(StockPool pool, Long amount);

}
