package com.xnjr.mall.dao;

import com.xnjr.mall.dao.base.IBaseDAO;
import com.xnjr.mall.domain.StockPool;

//dao层 
public interface IStockPoolDAO extends IBaseDAO<StockPool> {
    String NAMESPACE = IStockPoolDAO.class.getName().concat(".");

    int addAmount(StockPool pool);
}
