package com.cdkj.dzt.dao;

import com.cdkj.dzt.dao.base.IBaseDAO;
import com.cdkj.dzt.domain.ProductVar;

//dao层 
public interface IProductVarDAO extends IBaseDAO<ProductVar> {
    String NAMESPACE = IProductVarDAO.class.getName().concat(".");

    int update(ProductVar data);
}
