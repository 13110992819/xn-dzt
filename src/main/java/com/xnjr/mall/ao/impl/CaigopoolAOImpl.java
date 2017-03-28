package com.xnjr.mall.ao.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xnjr.mall.ao.ICaigopoolAO;
import com.xnjr.mall.bo.IAccountBO;
import com.xnjr.mall.bo.ICaigopoolBackBO;
import com.xnjr.mall.bo.ISYSConfigBO;
import com.xnjr.mall.bo.ICaigopoolBO;
import com.xnjr.mall.bo.IUserBO;
import com.xnjr.mall.bo.base.Paginable;
import com.xnjr.mall.domain.Caigopool;
import com.xnjr.mall.domain.User;
import com.xnjr.mall.exception.BizException;

@Service
public class CaigopoolAOImpl implements ICaigopoolAO {

    @Autowired
    private ICaigopoolBO stockPoolBO;

    @Autowired
    private ICaigopoolBackBO caigopoolBackBO;

    @Autowired
    private IAccountBO accountBO;

    @Autowired
    private ISYSConfigBO sysConfigBO;

    @Autowired
    private IUserBO userBO;

    @Override
    public Paginable<Caigopool> queryCaigopoolPage(int start, int limit,
            Caigopool condition) {
        return stockPoolBO.getPaginable(start, limit, condition);
    }

    @Override
    public Caigopool getCaigopool(String code) {
        return stockPoolBO.getCaigopool(code);
    }

    @Override
    public void addAmount(String code, Long amount, String addUser,
            String remark) {
        Caigopool pool = stockPoolBO.getCaigopool(code);
        stockPoolBO.addAmount(pool, amount, addUser, remark);
    }

    @Override
    public String exchangeHighAmount(String mobile, String loginPwd,
            Long highAmount) {
        String highPoolCode = "678909876";
        Caigopool pool = stockPoolBO.getCaigopool(highPoolCode);
        if (pool.getAmount() < highAmount) {
            throw new BizException("xn000000", "可兑换嗨币不足，请联系菜狗平台");
        }
        User user = userBO.getRemoteUser("mobile");
        // 根据手机号检查用户是否存在
        // 不存在先注册，再在对应的菜狗币账户上根据汇率折算加上菜狗币；
        // 对“菜狗对接池”出金，并记录stockBack
        Long caigoAmount = highAmount;
        stockPoolBO.outAmount(pool, user, highAmount);
        // 记录日志
        caigopoolBackBO.saveCaigopoolBack(pool, user, caigoAmount, mobile,
            highAmount);
        return null;
    }
}
