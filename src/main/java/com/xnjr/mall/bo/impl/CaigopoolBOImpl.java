package com.xnjr.mall.bo.impl;

import java.util.Date;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.xnjr.mall.bo.ICaigopoolBO;
import com.xnjr.mall.bo.base.PaginableBOImpl;
import com.xnjr.mall.dao.ICaigopoolDAO;
import com.xnjr.mall.domain.Caigopool;
import com.xnjr.mall.domain.User;
import com.xnjr.mall.exception.BizException;

@Component
public class CaigopoolBOImpl extends PaginableBOImpl<Caigopool> implements
        ICaigopoolBO {

    @Autowired
    private ICaigopoolDAO stockPoolDAO;

    @Override
    public Caigopool getCaigopool(String code) {
        Caigopool data = null;
        if (StringUtils.isNotBlank(code)) {
            Caigopool condition = new Caigopool();
            condition.setCode(code);
            data = stockPoolDAO.select(condition);
            if (data == null) {
                throw new BizException("xn0000", code + "对应的池不存在");
            }
        }
        return data;
    }

    @Override
    public int addAmount(Caigopool pool, Long amount, String addUser,
            String remark) {
        pool.setAmount(amount + pool.getAmount());
        pool.setAddUser(addUser);
        pool.setAddDatetime(new Date());
        pool.setRemark(remark);
        return stockPoolDAO.addAmount(pool);

    }

    @Override
    public int outAmount(Caigopool pool, User user, Long highAmount) {
        pool.setAmount(pool.getAmount() - highAmount);
        pool.setUsedAmount(pool.getUsedAmount() + highAmount);
        return stockPoolDAO.outAmount(pool);

    }
}