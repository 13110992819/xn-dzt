package com.xnjr.mall.base;

import org.junit.Test;
import org.unitils.spring.annotation.SpringBeanByType;

import com.xnjr.mall.dao.IStockPoolDAO;
import com.xnjr.mall.domain.StockPool;
import com.xnjr.mall.enums.EStockPoolType;

public class IStockPoolDAOTest extends ADAOTest {

    @SpringBeanByType
    private IStockPoolDAO stockPoolDAO;

    @Test
    public void insert() {
        StockPool data = new StockPool();
        data.setCode("stock001");
        data.setName("stockName");
        data.setType(EStockPoolType.ZHPAY.getCode());
        data.setAmount(1000L);
        data.setUsedAmount(2000L);
        data.setCompanyCode("companyCode");
        data.setSystemCode("systemCode");
        stockPoolDAO.insert(data);
        logger.info("insert : {}");
    }

    @Test
    public void select() {
        StockPool condition = new StockPool();
        condition.setCode("stock001");
        StockPool data = stockPoolDAO.select(condition);
        logger.info("select : {}", data);
    }

    @Test
    public void selectList() {
        StockPool condition = new StockPool();
        condition.setCode("stock001");
        Long count = stockPoolDAO.selectTotalCount(condition);
        logger.info("selectList : {}", count);
    }

}
