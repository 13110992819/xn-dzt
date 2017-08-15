package com.cdkj.dzt.dao;

import com.cdkj.dzt.dao.base.IBaseDAO;
import com.cdkj.dzt.domain.Model;

//dao层 
public interface IModelDAO extends IBaseDAO<Model> {
    String NAMESPACE = IModelDAO.class.getName().concat(".");

    public int update(Model data);

    public int putOn(Model data);

    public int putOff(Model data);
}
