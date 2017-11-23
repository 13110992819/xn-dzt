/**
 * @Title ISYSDictBO.java 
 * @Package com.xnjr.moom.bo 
 * @Description 
 * @author haiqingzheng  
 * @date 2016年4月17日 下午2:40:19 
 * @version V1.0   
 */
package com.cdkj.dzt.bo;

import java.util.List;
import java.util.Map;

import com.cdkj.dzt.bo.base.IPaginableBO;
import com.cdkj.dzt.domain.ProductCategory;

/** 
 * @author: haiqingzheng 
 * @since: 2016年4月17日 下午2:40:19 
 * @history:
 */
public interface IProductCategoryBO extends IPaginableBO<ProductCategory> {

    public String saveProductCategory(ProductCategory data);

    public int removeProductCategory(String code);

    public int refreshProductCategory(ProductCategory data);

    public ProductCategory getProductCategory(String code);

    public List<ProductCategory> queryProductCategoryList(
            ProductCategory condition);

    public List<ProductCategory> queryProductCategoryList(
            List<String> kindList, String type, String parentKey, String dkey,
            String modelCode);

    public Map<String, List<ProductCategory>> queryMapProductCategoryList(
            ProductCategory condition);
}
