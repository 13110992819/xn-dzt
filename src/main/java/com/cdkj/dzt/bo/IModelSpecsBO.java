package com.cdkj.dzt.bo;

import java.util.List;

import com.cdkj.dzt.bo.base.IPaginableBO;
import com.cdkj.dzt.domain.ModelSpecs;



//CHECK ��鲢��ע�� 
public interface IModelSpecsBO extends IPaginableBO<ModelSpecs> {


	public boolean isModelSpecsExist(String code);


	public String saveModelSpecs(ModelSpecs data);


	public int removeModelSpecs(String code);


	public int refreshModelSpecs(ModelSpecs data);


	public List<ModelSpecs> queryModelSpecsList(ModelSpecs condition);


	public ModelSpecs getModelSpecs(String code);


}