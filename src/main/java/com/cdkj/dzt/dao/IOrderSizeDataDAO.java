package com.cdkj.dzt.dao;

import com.cdkj.dzt.dao.base.IBaseDAO;
import com.cdkj.dzt.domain.OrderSizeData;

//dao层 
public interface IOrderSizeDataDAO extends IBaseDAO<OrderSizeData> {
    String NAMESPACE = IOrderSizeDataDAO.class.getName().concat(".");

}
