package com.cdkj.dzt.ao.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cdkj.dzt.ao.IClothAO;
import com.cdkj.dzt.bo.IClothBO;
import com.cdkj.dzt.bo.base.Paginable;
import com.cdkj.dzt.domain.Cloth;
import com.cdkj.dzt.exception.BizException;



@Service
public class ClothAOImpl implements IClothAO {

	@Autowired
	private IClothBO clothBO;

	@Override
	public String addCloth(Cloth data) {
		return clothBO.saveCloth(data);
	}

	@Override
	public int editCloth(Cloth data) {
		if (!clothBO.isClothExist(data.getCode())) {
			throw new BizException("xn0000", "记录编号不存在");
		}
		return clothBO.refreshCloth(data);
	}

	@Override
	public int dropCloth(String code) {
		if (!clothBO.isClothExist(code)) {
			throw new BizException("xn0000", "记录编号不存在");
		}
		return clothBO.removeCloth(code);
	}

	@Override
	public Paginable<Cloth> queryClothPage(int start, int limit,
			Cloth condition) {
		return clothBO.getPaginable(start, limit, condition);
	}

	@Override
	public List<Cloth> queryClothList(Cloth condition) {
		return clothBO.queryClothList(condition);
	}

	@Override
	public Cloth getCloth(String code) {
		return clothBO.getCloth(code);
	}
}