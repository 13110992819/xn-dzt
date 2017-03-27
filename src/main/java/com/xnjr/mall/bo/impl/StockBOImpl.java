package com.xnjr.mall.bo.impl;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.xnjr.mall.bo.IStockBO;
import com.xnjr.mall.bo.base.PaginableBOImpl;
import com.xnjr.mall.dao.IStockDAO;
import com.xnjr.mall.domain.Stock;
import com.xnjr.mall.enums.EDiviFlag;
import com.xnjr.mall.exception.BizException;

@Component
public class StockBOImpl extends PaginableBOImpl<Stock> implements IStockBO {

    @Autowired
    private IStockDAO stockDAO;

    /*
     * @Override public int saveStock(Stock data) { int count = 0; if (data !=
     * null) { data.setCode(OrderNoGenerater.generateM(EGeneratePrefix.STOCK
     * .getCode())); count = stockDAO.insert(data); } return count; }
     */

    @Override
    public Stock getStock(String code) {
        Stock data = null;
        if (StringUtils.isNotBlank(code)) {
            Stock condition = new Stock();
            condition.setCode(code);
            data = stockDAO.select(condition);
            if (data == null) {
                throw new BizException("xn0000", "股份编号不存在");
            }
        }
        return data;
    }

    @Override
    public List<Stock> queryMyStockList(String userId) {
        Stock condition = new Stock();
        condition.setUserId(userId);
        condition.setStatus(EDiviFlag.EFFECT.getCode());
        return stockDAO.selectList(condition);
    }

    @Override
    public List<Stock> queryStockList(Stock condition) {
        return stockDAO.selectList(condition);
    }

    @Override
    public int doDailyStock(Stock ele) {
        int count = 0;
        if (ele != null && StringUtils.isNotBlank(ele.getCode())) {
            count = stockDAO.doDailyStock(ele);
        }
        return count;
    }
}
