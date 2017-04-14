/**
 * @Title IProductDAO.java 
 * @Package com.xnjr.mall.dao 
 * @Description 
 * @author haiqingzheng  
 * @date 2016年5月16日 下午8:53:19 
 * @version V1.0   
 */
package com.cdkj.dzt.dao;

import com.cdkj.dzt.dao.base.IBaseDAO;
import com.cdkj.dzt.domain.Product;

/** 
 * @author: haiqingzheng 
 * @since: 2016年5月16日 下午8:53:19 
 * @history:
 */
public interface IProductDAO extends IBaseDAO<Product> {
    String NAMESPACE = IProductDAO.class.getName().concat(".");

    public int updateProduct(Product product);

    public int updateStatus(Product product);

    public int updatePutOnProduct(Product product);

    public int updateQuantity(Product data);

    public Long selectBoughtCount(Product data);

    public int updateBoughtCount(Product data);
}
