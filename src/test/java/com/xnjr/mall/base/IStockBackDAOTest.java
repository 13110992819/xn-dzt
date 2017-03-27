package com.xnjr.mall.base;

import java.util.Date;

import org.junit.Test;
import org.unitils.spring.annotation.SpringBeanByType;

import com.xnjr.mall.dao.IStockBackDAO;
import com.xnjr.mall.domain.StockBack;
import com.xnjr.mall.enums.ECurrency;

public class IStockBackDAOTest extends ADAOTest {

    @SpringBeanByType
    private IStockBackDAO stockBackDAO;

    @Test
    public void insert() {
        StockBack data = new StockBack();
        data.setPoolCode("poolCode");
        data.setStockCode("stockCode");
        data.setBackUser("backUser");
        data.setBackAmount(1000L);
        data.setBackCurrency(ECurrency.CNY.getCode());
        data.setBackDatetime(new Date());
        data.setCompanyCode("companyCode");
        data.setSystemCode("systemCode");
        stockBackDAO.insert(data);
        logger.info("insert : {}");
    }

    @Test
    public void select() {
        StockBack condition = new StockBack();
        condition.setPoolCode("poolCode");
        StockBack data = stockBackDAO.select(condition);
        logger.info("select : {}", data.getId());
    }

    @Test
    public void selectList() {
        StockBack condition = new StockBack();
        condition.setPoolCode("poolCode");
        Long count = stockBackDAO.selectTotalCount(condition);
        logger.info("selectList : {}", count);
    }
}
