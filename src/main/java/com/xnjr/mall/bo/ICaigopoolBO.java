package com.xnjr.mall.bo;

import com.xnjr.mall.bo.base.IPaginableBO;
import com.xnjr.mall.domain.Caigopool;
import com.xnjr.mall.domain.User;

public interface ICaigopoolBO extends IPaginableBO<Caigopool> {

    public Caigopool getCaigopool(String code);

    public int addAmount(Caigopool pool, Long amount, String addUser,
            String remark);

    public int outAmount(Caigopool pool, User user, Long highAmount);

}
