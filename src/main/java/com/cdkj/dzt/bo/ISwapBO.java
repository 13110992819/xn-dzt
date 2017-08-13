package com.cdkj.dzt.bo;

import java.util.List;

import com.cdkj.dzt.bo.base.IPaginableBO;
import com.cdkj.dzt.domain.Swap;



public interface ISwapBO extends IPaginableBO<Swap> {


	public boolean isSwapExist(String code);


	public String saveSwap(Swap data);


	public int removeSwap(String code);


	public int refreshSwap(Swap data);


	public List<Swap> querySwapList(Swap condition);


	public Swap getSwap(String code);


}