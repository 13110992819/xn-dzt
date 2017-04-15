package com.cdkj.dzt.ao.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cdkj.dzt.ao.IModelSpecsAO;
import com.cdkj.dzt.bo.IModelSpecsBO;
import com.cdkj.dzt.bo.base.Paginable;
import com.cdkj.dzt.domain.ModelSpecs;
import com.cdkj.dzt.exception.BizException;



//CHECK ��鲢��ע�� 
@Service
public class ModelSpecsAOImpl implements IModelSpecsAO {

	@Autowired
	private IModelSpecsBO modelSpecsBO;

	@Override
	public String addModelSpecs(ModelSpecs data) {
		return modelSpecsBO.saveModelSpecs(data);
	}

	@Override
	public int editModelSpecs(ModelSpecs data) {
		if (!modelSpecsBO.isModelSpecsExist(data.getCode())) {
			throw new BizException("xn0000", "记录编号不存在");
		}
		return modelSpecsBO.refreshModelSpecs(data);
	}

	@Override
	public int dropModelSpecs(String code) {
		if (!modelSpecsBO.isModelSpecsExist(code)) {
			throw new BizException("xn0000", "记录编号不存在");
		}
		return modelSpecsBO.removeModelSpecs(code);
	}

	@Override
	public Paginable<ModelSpecs> queryModelSpecsPage(int start, int limit,
			ModelSpecs condition) {
		return modelSpecsBO.getPaginable(start, limit, condition);
	}

	@Override
	public List<ModelSpecs> queryModelSpecsList(ModelSpecs condition) {
		return modelSpecsBO.queryModelSpecsList(condition);
	}

	@Override
	public ModelSpecs getModelSpecs(String code) {
		return modelSpecsBO.getModelSpecs(code);
	}
}