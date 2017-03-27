package com.xnjr.mall.bo.impl;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.xnjr.mall.bo.IStockPoolBO;
import com.xnjr.mall.bo.base.PaginableBOImpl;
import com.xnjr.mall.dao.IStockPoolDAO;
import com.xnjr.mall.domain.StockPool;
import com.xnjr.mall.exception.BizException;

@Component
public class StockPoolBOImpl extends PaginableBOImpl<StockPool> implements
        IStockPoolBO {

    @Autowired
    private IStockPoolDAO stockHoldDAO;

    @Override
    public StockPool getStockPool(String code) {
        StockPool data = null;
        if (StringUtils.isNotBlank(code)) {
            StockPool condition = new StockPool();
            condition.setCode(code);
            data = stockHoldDAO.select(condition);
            if (data == null) {
                throw new BizException("xn0000", code + "对应的池不存在");
            }
        }
        return data;
    }

    @Override
    public int addAmount(StockPool pool, Long amount) {
        pool.setAmount(amount + pool.getAmount());
        return stockHoldDAO.addAmount(pool);

    }
}
