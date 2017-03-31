package com.xnjr.mall.bo;

import java.util.List;

import com.xnjr.mall.bo.base.IPaginableBO;
import com.xnjr.mall.domain.Stock;

public interface IStockBO extends IPaginableBO<Stock> {
    public String generateStock(Long frAmount, Long rmbAmount, String userId,
            String poolCode);

    public Stock getStock(String code);

    public List<Stock> queryMyStockList(String userId);

    public List<Stock> queryStockList(Stock condition);

    public int doDailyStock(Stock ele);

    public Stock getMyNextStock(String userId);

}
