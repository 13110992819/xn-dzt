package com.xnjr.mall.base;

import org.junit.Test;
import org.unitils.spring.annotation.SpringBeanByType;

import com.xnjr.mall.dao.IStockPoolDAO;
import com.xnjr.mall.domain.StockPool;

;

public class IStockPoolDAOTest extends ADAOTest {

    @SpringBeanByType
    private IStockPoolDAO stockPoolDAO;

    @Test
    public void insert() {
        StockPool data = new StockPool();
        data.setCompanyCode("companyCode");
        data.setSystemCode("systemCode");
        stockPoolDAO.insert(data);
        logger.info("insert : {}");
    }

    // @Test
    // public void select() {
    // SYSDict condition = new SYSDict();
    // condition.setId(1L);
    // SYSDict data = stockPoolDAO.select(condition);
    // logger.info("select : {}", data.getId());
    // }
    //
    // @Test
    // public void selectList() {
    // SYSDict condition = new SYSDict();
    // condition.setType("0");
    // condition.setParentKey("A");
    // Long count = stockPoolDAO.selectTotalCount(condition);
    // logger.info("selectList : {}", count);
    // }

}
