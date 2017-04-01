package com.xnjr.mall.bo;

import java.util.List;

import com.xnjr.mall.bo.base.IPaginableBO;
import com.xnjr.mall.domain.Stock;

public interface IStockBO extends IPaginableBO<Stock> {
    public void generateCStock(Long frAmount, String buyUser);

    public void generateBStock(Long frAmount, String storeOwner);

    public Stock getStock(String code);

    public List<Stock> queryMyStockList(String userId);

    public List<Stock> queryStockList(Stock condition);

    public int doDailyStock(Stock ele);

    public Stock getMyNextStock(String userId);

}
