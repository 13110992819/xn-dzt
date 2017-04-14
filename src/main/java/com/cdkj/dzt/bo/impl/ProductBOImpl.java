/**
 * @Title ProductBOImpl.java 
 * @Package com.xnjr.mall.bo.impl 
 * @Description 
 * @author haiqingzheng  
 * @date 2016年5月16日 下午9:12:35 
 * @version V1.0   
 */
package com.cdkj.dzt.bo.impl;

import java.util.Date;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.cdkj.dzt.bo.IProductBO;
import com.cdkj.dzt.bo.base.PaginableBOImpl;
import com.cdkj.dzt.dao.IProductDAO;
import com.cdkj.dzt.domain.Product;
import com.cdkj.dzt.enums.EBoolean;
import com.cdkj.dzt.enums.EProductStatus;
import com.cdkj.dzt.exception.BizException;

/** 
 * @author: haiqingzheng 
 * @since: 2016年5月16日 下午9:12:35 
 * @history:
 */
@Component
public class ProductBOImpl extends PaginableBOImpl<Product> implements
        IProductBO {

    @Autowired
    private IProductDAO productDAO;

    /** 
     * @see com.cdkj.dzt.bo.IProductBO#saveProduct(com.cdkj.dzt.domain.Product)
     */
    @Override
    public void saveProduct(Product product) {
        if (product != null && StringUtils.isNotBlank(product.getCode())) {
            productDAO.insert(product);
        }
    }

    @Override
    public int removeProduct(String code) {
        int count = 0;
        if (StringUtils.isNotBlank(code)) {
            Product product = new Product();
            product.setCode(code);
            count = productDAO.delete(product);
        }
        return count;
    }

    @Override
    public int refreshProduct(Product product) {
        int count = 0;
        if (product != null && StringUtils.isNotBlank(product.getCode())) {
            count = productDAO.updateProduct(product);
        }
        return count;
    }

    /** 
     * @see com.cdkj.dzt.bo.IProductBO#queryProductList(com.cdkj.dzt.domain.Product)
     */
    @Override
    public List<Product> queryProductList(Product condition) {
        return productDAO.selectList(condition);
    }

    /** 
     * @see com.cdkj.dzt.bo.IProductBO#getProduct(java.lang.String)
     */
    @Override
    public Product getProduct(String code) {
        Product product = null;
        if (StringUtils.isNotBlank(code)) {
            Product condition = new Product();
            condition.setCode(code);
            product = productDAO.select(condition);
            if (product == null) {
                throw new BizException("xn000000", "产品编号不存在");
            }
        }
        return product;
    }

    /** 
     * @see com.cdkj.dzt.bo.IProductBO#approveProduct(java.lang.String, java.lang.String, java.lang.String, java.lang.String)
     */
    @Override
    public int approveProduct(String code, String approveResult,
            String approver, String approveNote) {
        int count = 0;
        if (StringUtils.isNotBlank(code)) {
            Product product = new Product();
            product.setCode(code);
            if (EBoolean.YES.getCode().equals(approveResult)) {
                product.setStatus(EProductStatus.APPROVE_YES.getCode());
            } else {
                product.setStatus(EProductStatus.APPROVE_NO.getCode());
            }
            product.setUpdater(approver);
            product.setUpdateDatetime(new Date());
            product.setRemark(approveNote);
            count = productDAO.updateStatus(product);
        }
        return count;
    }

    @Override
    public int putOff(String code, String checkUser, String checkNote) {
        int count = 0;
        if (StringUtils.isNotBlank(code)) {
            Product product = new Product();
            product.setCode(code);
            product.setUpdater(checkUser);
            product.setUpdateDatetime(new Date());
            product.setStatus(EProductStatus.PUBLISH_NO.getCode());
            product.setRemark(checkNote);
            count = productDAO.updateStatus(product);
        }
        return count;
    }

    @Override
    public int putOn(Product product) {
        int count = 0;
        if (product != null && StringUtils.isNotBlank(product.getCode())) {
            count = productDAO.updatePutOnProduct(product);
        }
        return count;
    }

    @Override
    public int updateBoughtCount(String productCode, Integer boughtCount) {
        Product data = new Product();
        data.setCode(productCode);
        data.setBoughtCount(boughtCount);
        return productDAO.updateBoughtCount(data);
    }
}
