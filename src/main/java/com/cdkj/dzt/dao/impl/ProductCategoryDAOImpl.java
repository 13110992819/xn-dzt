/**
 * @Title SYSDictDAOImpl.java 
 * @Package com.xnjr.moom.dao.impl 
 * @Description 
 * @author haiqingzheng  
 * @date 2016年4月17日 上午10:22:32 
 * @version V1.0   
 */
package com.cdkj.dzt.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.cdkj.dzt.dao.IProductCategoryDAO;
import com.cdkj.dzt.dao.base.support.AMybatisTemplate;
import com.cdkj.dzt.domain.ProductCategory;

/**
 * @author: xieyj 
 * @since: 2016年10月21日 上午9:57:52 
 * @history:
 */
@Repository("productCategoryDAOImpl")
public class ProductCategoryDAOImpl extends AMybatisTemplate implements
        IProductCategoryDAO {

    /** 
     * @see com.xnjr.base.dao.base.IBaseDAO#insert(java.lang.Object)
     */
    @Override
    public int insert(ProductCategory data) {
        return super.insert("insert_productCategory", data);
    }

    /** 
     * @see com.xnjr.base.dao.base.IBaseDAO#delete(java.lang.Object)
     */
    @Override
    public int delete(ProductCategory data) {
        return super.delete("delete_productCategory", data);
    }

    /** 
     * @see com.xnjr.base.dao.base.IBaseDAO#select(java.lang.Object)
     */
    @Override
    public ProductCategory select(ProductCategory condition) {
        return super.select("select_productCategory", condition,
            ProductCategory.class);
    }

    /** 
     * @see com.xnjr.base.dao.base.IBaseDAO#selectTotalCount(java.lang.Object)
     */
    @Override
    public Long selectTotalCount(ProductCategory condition) {
        return super
            .selectTotalCount("select_productCategory_count", condition);
    }

    /** 
     * @see com.xnjr.base.dao.base.IBaseDAO#selectList(java.lang.Object)
     */
    @Override
    public List<ProductCategory> selectList(ProductCategory condition) {
        return super.selectList("select_productCategory", condition,
            ProductCategory.class);
    }

    /** 
     * @see com.xnjr.base.dao.base.IBaseDAO#selectList(java.lang.Object, int, int)
     */
    @Override
    public List<ProductCategory> selectList(ProductCategory condition,
            int start, int count) {
        return super.selectList("select_productCategory", start, count,
            condition, ProductCategory.class);
    }

    /**
     * @see com.xnjr.base.dao.ISYSDictDAO#update(com.xnjr.base.domain.SYSDict)
     */
    @Override
    public int update(ProductCategory data) {
        return super.update("update_productCategory", data);
    }

}
