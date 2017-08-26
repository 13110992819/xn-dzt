package com.cdkj.dzt.bo;

import java.util.List;

import com.cdkj.dzt.bo.base.IPaginableBO;
import com.cdkj.dzt.domain.Swap;

public interface ISwapBO extends IPaginableBO<Swap> {

    public boolean isSwapExist(String code);

    public void saveSwap(Swap data);

    public void removeSwap(String code);

    public void refreshSwap(Swap data);

    public List<Swap> querySwapList(Swap condition);

    public Swap getSwap(String code);

    public Long getTotalCount(String lookUser);

    public List<Swap> queryGroupList(Swap condition);

    public List<Swap> queryGroupList(Swap condition, int start, int limit);

    public List<Swap> queryBLYList(Swap condition);

    public List<Swap> queryBLYList(Swap condition, int start, int pageSize);

}
