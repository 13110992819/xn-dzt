package com.cdkj.dzt.dao;

import com.cdkj.dzt.dao.base.IBaseDAO;
import com.cdkj.dzt.domain.ProductCraft;

//dao层 
public interface IProductCraftDAO extends IBaseDAO<ProductCraft> {
	String NAMESPACE = IProductCraftDAO.class.getName().concat(".");
}