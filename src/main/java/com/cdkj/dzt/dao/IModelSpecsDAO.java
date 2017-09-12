package com.cdkj.dzt.dao;

import com.cdkj.dzt.dao.base.IBaseDAO;
import com.cdkj.dzt.domain.ModelSpecs;

//dao层 
public interface IModelSpecsDAO extends IBaseDAO<ModelSpecs> {
    String NAMESPACE = IModelSpecsDAO.class.getName().concat(".");

    int update(ModelSpecs data);
}
