package com.cdkj.dzt.dao;

import com.cdkj.dzt.dao.base.IBaseDAO;
import com.cdkj.dzt.domain.Product;

//dao层 
public interface IProductDAO extends IBaseDAO<Product> {
    String NAMESPACE = IProductDAO.class.getName().concat(".");

    public int update(Product data);
}
