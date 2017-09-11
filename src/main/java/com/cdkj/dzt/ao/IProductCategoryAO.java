/**
 * @Title ISYSDictAO.java 
 * @Package com.xnjr.moom.ao 
 * @Description 
 * @author haiqingzheng  
 * @date 2016年4月17日 下午5:12:19 
 * @version V1.0   
 */
package com.cdkj.dzt.ao;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import com.cdkj.dzt.bo.base.Paginable;
import com.cdkj.dzt.domain.ProductCategory;

/** 
 * @author: haiqingzheng 
 * @since: 2016年4月17日 下午5:12:19 
 * @history:
 */
public interface IProductCategoryAO {
    static String DEFAULT_ORDER_COLUMN = "code";

    public String addProductCategory(String type, String parentKey, String key,
            String value, String updater, String remark, String systemCode);

    public int dropProductCategory(String code);

    public int editProductCategory(String code, String value, String updater,
            String remark);

    public Paginable<ProductCategory> queryProductCategoryPage(int start,
            int limit, ProductCategory condition);

    public Map<String, LinkedHashMap<String, String>> queryMapProductCategoryList();

    public List<ProductCategory> queryProductCategoryList(
            ProductCategory condition);

    public ProductCategory getProductCategory(String code);
}
