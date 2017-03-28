package com.xnjr.mall.base;

import java.util.Date;

import org.junit.Test;
import org.unitils.spring.annotation.SpringBeanByType;

import com.xnjr.mall.dao.ICaigopoolBackDAO;
import com.xnjr.mall.domain.CaigopoolBack;
import com.xnjr.mall.enums.ECurrency;

public class ICaigopoolBackDAOTest extends ADAOTest {

    @SpringBeanByType
    private ICaigopoolBackDAO caigopoolBackDAO;

    @Test
    public void insert() {
        CaigopoolBack data = new CaigopoolBack();
        data.setPoolCode("poolCode");
        data.setStockCode("stockCode");
        data.setBackUser("backUser");
        data.setBackAmount(1000L);
        data.setBackCurrency(ECurrency.CNY.getCode());
        data.setBackDatetime(new Date());
        data.setCompanyCode("companyCode");
        data.setSystemCode("systemCode");
        caigopoolBackDAO.insert(data);
        logger.info("insert : {}");
    }

    @Test
    public void select() {
        CaigopoolBack condition = new CaigopoolBack();
        condition.setPoolCode("poolCode");
        CaigopoolBack data = caigopoolBackDAO.select(condition);
        logger.info("select : {}", data.getId());
    }

    @Test
    public void selectList() {
        CaigopoolBack condition = new CaigopoolBack();
        condition.setPoolCode("poolCode");
        Long count = caigopoolBackDAO.selectTotalCount(condition);
        logger.info("selectList : {}", count);
    }

}
