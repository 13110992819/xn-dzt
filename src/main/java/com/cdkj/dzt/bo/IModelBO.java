package com.cdkj.dzt.bo;

import java.util.List;

import com.cdkj.dzt.bo.base.IPaginableBO;
import com.cdkj.dzt.domain.Model;



//CHECK ��鲢��ע�� 
public interface IModelBO extends IPaginableBO<Model> {


	public boolean isModelExist(String code);


	public String saveModel(Model data);


	public int removeModel(String code);


	public int refreshModel(Model data);


	public List<Model> queryModelList(Model condition);


	public Model getModel(String code);


}