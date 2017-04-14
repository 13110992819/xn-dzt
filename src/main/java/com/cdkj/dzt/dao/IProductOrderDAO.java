package com.cdkj.dzt.dao;

import com.cdkj.dzt.dao.base.IBaseDAO;
import com.cdkj.dzt.domain.ProductOrder;

/**
 * @author: xieyj 
 * @since: 2016年5月24日 下午9:03:38 
 * @history:
 */
public interface IProductOrderDAO extends IBaseDAO<ProductOrder> {
    String NAMESPACE = IProductOrderDAO.class.getName().concat(".");
}
