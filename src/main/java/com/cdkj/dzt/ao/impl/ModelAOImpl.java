package com.cdkj.dzt.ao.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cdkj.dzt.ao.IModelAO;
import com.cdkj.dzt.bo.IModelBO;
import com.cdkj.dzt.bo.base.Paginable;
import com.cdkj.dzt.domain.Model;
import com.cdkj.dzt.exception.BizException;



//CHECK ��鲢��ע�� 
@Service
public class ModelAOImpl implements IModelAO {

	@Autowired
	private IModelBO modelBO;

	@Override
	public String addModel(Model data) {
		return modelBO.saveModel(data);
	}

	@Override
	public int editModel(Model data) {
		if (!modelBO.isModelExist(data.getCode())) {
			throw new BizException("xn0000", "记录编号不存在");
		}
		return modelBO.refreshModel(data);
	}

	@Override
	public int dropModel(String code) {
		if (!modelBO.isModelExist(code)) {
			throw new BizException("xn0000", "记录编号不存在");
		}
		return modelBO.removeModel(code);
	}

	@Override
	public Paginable<Model> queryModelPage(int start, int limit,
			Model condition) {
		return modelBO.getPaginable(start, limit, condition);
	}

	@Override
	public List<Model> queryModelList(Model condition) {
		return modelBO.queryModelList(condition);
	}

	@Override
	public Model getModel(String code) {
		return modelBO.getModel(code);
	}
}