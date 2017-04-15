package com.cdkj.dzt.dao;

import com.cdkj.dzt.dao.base.IBaseDAO;
import com.cdkj.dzt.domain.ProductSpecs;

//dao层 
public interface IProductSpecsDAO extends IBaseDAO<ProductSpecs> {
    String NAMESPACE = IProductSpecsDAO.class.getName().concat(".");

    public int update(ProductSpecs data);
}
