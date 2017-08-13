package com.cdkj.dzt.ao.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cdkj.dzt.ao.ISwapAO;
import com.cdkj.dzt.bo.ISwapBO;
import com.cdkj.dzt.bo.base.Paginable;
import com.cdkj.dzt.domain.Swap;
import com.cdkj.dzt.exception.BizException;



@Service
public class SwapAOImpl implements ISwapAO {

	@Autowired
	private ISwapBO swapBO;

	@Override
	public String addSwap(Swap data) {
		return swapBO.saveSwap(data);
	}

	@Override
	public int editSwap(Swap data) {
		if (!swapBO.isSwapExist(data.getCode())) {
			throw new BizException("xn0000", "记录编号不存在");
		}
		return swapBO.refreshSwap(data);
	}

	@Override
	public int dropSwap(String code) {
		if (!swapBO.isSwapExist(code)) {
			throw new BizException("xn0000", "记录编号不存在");
		}
		return swapBO.removeSwap(code);
	}

	@Override
	public Paginable<Swap> querySwapPage(int start, int limit,
			Swap condition) {
		return swapBO.getPaginable(start, limit, condition);
	}

	@Override
	public List<Swap> querySwapList(Swap condition) {
		return swapBO.querySwapList(condition);
	}

	@Override
	public Swap getSwap(String code) {
		return swapBO.getSwap(code);
	}
}