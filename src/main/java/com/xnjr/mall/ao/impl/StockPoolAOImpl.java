package com.xnjr.mall.ao.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xnjr.mall.ao.IStockPoolAO;
import com.xnjr.mall.bo.IAccountBO;
import com.xnjr.mall.bo.ISYSConfigBO;
import com.xnjr.mall.bo.IStockPoolBO;
import com.xnjr.mall.bo.IUserBO;
import com.xnjr.mall.bo.base.Paginable;
import com.xnjr.mall.domain.StockPool;

@Service
public class StockPoolAOImpl implements IStockPoolAO {

    @Autowired
    private IStockPoolBO stockPoolBO;

    @Autowired
    private IAccountBO accountBO;

    @Autowired
    private ISYSConfigBO sysConfigBO;

    @Autowired
    private IUserBO userBO;

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

    @Override
    public String exchangeHighAmount(String mobile, String loginPwd,
            String highAmount) {
        // 根据手机号检查用户是否存在
        // 不存在先注册，再在对应的菜狗币账户上根据汇率折算加上菜狗币；
        // 对“菜狗对接池”出金，并记录stockBack
        return null;
    }
}
