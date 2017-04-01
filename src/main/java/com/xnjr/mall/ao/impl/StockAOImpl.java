package com.xnjr.mall.ao.impl;

import java.util.Date;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xnjr.mall.ao.IStockAO;
import com.xnjr.mall.bo.IAccountBO;
import com.xnjr.mall.bo.ICaigopoolBO;
import com.xnjr.mall.bo.ISYSConfigBO;
import com.xnjr.mall.bo.IStockBO;
import com.xnjr.mall.bo.IStockBackBO;
import com.xnjr.mall.bo.IUserBO;
import com.xnjr.mall.bo.base.Paginable;
import com.xnjr.mall.common.DateUtil;
import com.xnjr.mall.domain.Stock;
import com.xnjr.mall.dto.res.XN808419Res;
import com.xnjr.mall.enums.EStockStatus;

@Service
public class StockAOImpl implements IStockAO {
    static Logger logger = Logger.getLogger(StockAOImpl.class);

    @Autowired
    private IStockBO stockBO;

    @Autowired
    private ICaigopoolBO caigopoolBO;

    @Autowired
    private IStockBackBO stockBackBO;

    @Autowired
    private IAccountBO accountBO;

    @Autowired
    private ISYSConfigBO sysConfigBO;

    @Autowired
    private IUserBO userBO;

    @Override
    public Paginable<Stock> queryStockPage(int start, int limit, Stock condition) {
        return stockBO.getPaginable(start, limit, condition);
    }

    @Override
    public Stock getStock(String code) {
        return stockBO.getStock(code);
    }

    @Override
    public List<Stock> queryMyStockList(String userId) {
        return stockBO.queryMyStockList(userId);
    }

    @Override
    public void doDailyStock() {
        Date now = new Date();
        Stock condition = new Stock();
        condition.setStatus(EStockStatus.ING_effect.getCode());
        List<Stock> list = stockBO.queryStockList(condition);
        if (CollectionUtils.isNotEmpty(list)) {
            for (Stock ele : list) {
                // 确定是今天的才开始
                if (DateUtil.daysBetweenDate(now, ele.getNextBackDate()) == 0) {
                    // 更新返还金额
                    Date nextBackDate = DateUtil.getRelativeDate(
                        DateUtil.getTodayStart(),
                        ele.getBackInterval() * 24 * 60 * 60);
                    Long todayAmount = 100L;
                    Long backAmount = ele.getBackAmount() + todayAmount;
                    String status = null;
                    if (backAmount == ele.getProfitAmount()) {// 本分红权返利结束
                        status = EStockStatus.DONE.getCode();
                        nextBackDate = null;
                        stockBO.awakenStock(ele.getUserId());
                    }
                    // 更新股权
                    ele.setBackCount(ele.getBackCount() + 1);
                    ele.setBackAmount(backAmount);
                    ele.setTodayAmount(todayAmount);
                    ele.setNextBackDate(nextBackDate);
                    ele.setStatus(status);
                    stockBO.doDailyStock(ele);
                    // 落地返还记录
                    stockBackBO.saveStockBack(ele);
                }
            }
        }
    }

    @Override
    public Stock getMyNextStock(String userId) {
        return stockBO.getMyNextStock(userId);
    }

    @Override
    public XN808419Res getMyTodayStocks(String userId) {
        XN808419Res res = new XN808419Res();
        List<Stock> list = stockBO.queryMyStockList(userId);
        if (CollectionUtils.isNotEmpty(list)) {
            Long todayProfitAmount = 0L;
            for (Stock ele : list) {
                todayProfitAmount = todayProfitAmount + ele.getTodayAmount();
            }
            res.setStockCount(list.size());
            res.setTodayProfitAmount(todayProfitAmount);
        } else {
            res.setStockCount(0);
            res.setTodayProfitAmount(0L);
        }
        return res;
    }
}
