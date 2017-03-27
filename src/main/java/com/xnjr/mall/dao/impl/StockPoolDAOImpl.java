package com.xnjr.mall.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.xnjr.mall.dao.IStockPoolDAO;
import com.xnjr.mall.dao.base.support.AMybatisTemplate;
import com.xnjr.mall.domain.StockPool;

@Repository("stockPoolDAOImpl")
public class StockPoolDAOImpl extends AMybatisTemplate implements IStockPoolDAO {

    @Override
    public int insert(StockPool data) {
        return super.insert(NAMESPACE.concat("insert_stockPool"), data);
    }

    @Override
    public int delete(StockPool data) {
        return 0;
    }

    @Override
    public StockPool select(StockPool condition) {
        return super.select(NAMESPACE.concat("select_stockPool"), condition,
            StockPool.class);
    }

    @Override
    public Long selectTotalCount(StockPool condition) {
        return super.selectTotalCount(
            NAMESPACE.concat("select_stockPool_count"), condition);
    }

    @Override
    public List<StockPool> selectList(StockPool condition) {
        return super.selectList(NAMESPACE.concat("select_stockPool"),
            condition, StockPool.class);
    }

    @Override
    public List<StockPool> selectList(StockPool condition, int start, int count) {
        return super.selectList(NAMESPACE.concat("select_stockPool"), start,
            count, condition, StockPool.class);
    }

    @Override
    public int addAmount(StockPool data) {
        return super.update(NAMESPACE.concat("update_amount"), data);
    }
}
