/**
 * @Title SYSDictBOImpl.java 
 * @Package com.xnjr.moom.bo.impl 
 * @Description 
 * @author haiqingzheng  
 * @date 2016年4月17日 下午2:50:06 
 * @version V1.0   
 */
package com.cdkj.dzt.bo.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.cdkj.dzt.bo.IProductCategoryBO;
import com.cdkj.dzt.bo.base.PaginableBOImpl;
import com.cdkj.dzt.core.OrderNoGenerater;
import com.cdkj.dzt.dao.IProductCategoryDAO;
import com.cdkj.dzt.domain.ProductCategory;
import com.cdkj.dzt.enums.EGeneratePrefix;

/**
 * @author: xieyj 
 * @since: 2016年10月21日 上午9:58:54 
 * @history:
 */
@Component
public class ProductCategoryBOImpl extends PaginableBOImpl<ProductCategory>
        implements IProductCategoryBO {
    @Autowired
    private IProductCategoryDAO productCategoryDAO;

    /** 
     * @see com.xnjr.base.bo.ISYSDictBO#saveSYSDict(com.xnjr.base.domain.SYSDict)
     */
    @Override
    public String saveProductCategory(ProductCategory data) {
        String code = null;
        if (data != null) {
            code = OrderNoGenerater.generateM(EGeneratePrefix.PRODUCTCATEGORY
                .getCode());
            data.setCode(code);
            data.setUpdateDatetime(new Date());
            productCategoryDAO.insert(data);
        }
        return code;
    }

    /** 
     * @see com.xnjr.base.bo.ISYSDictBO#removeSYSDict(java.lang.Long)
     */
    @Override
    public int removeProductCategory(String code) {
        int count = 0;
        if (code != null) {
            ProductCategory data = new ProductCategory();
            data.setCode(code);
            count = productCategoryDAO.delete(data);
        }
        return count;
    }

    /** 
     * @see com.xnjr.base.bo.ISYSDictBO#refreshSYSDict(com.xnjr.base.domain.SYSDict)
     */
    @Override
    public int refreshProductCategory(ProductCategory data) {
        int count = 0;
        if (data != null) {
            data.setUpdateDatetime(new Date());
            count = productCategoryDAO.update(data);
        }
        return count;
    }

    /** 
     * @see com.xnjr.base.bo.ISYSDictBO#getSYSDict(java.lang.Long)
     */
    @Override
    public ProductCategory getProductCategory(String code) {
        ProductCategory productCategory = null;
        if (code != null) {
            ProductCategory data = new ProductCategory();
            data.setCode(code);
            productCategory = productCategoryDAO.select(data);
        }
        return productCategory;
    }

    /** 
     * @see com.xnjr.base.bo.ISYSDictBO#querySYSDictList(com.xnjr.base.domain.SYSDict)
     */
    @Override
    public List<ProductCategory> queryProductCategoryList(
            ProductCategory condition) {
        return productCategoryDAO.selectList(condition);
    }

    @Override
    public Map<String, List<ProductCategory>> queryMapProductCategoryList(
            ProductCategory condition) {
        List<ProductCategory> sysDictList = productCategoryDAO
            .selectList(condition);
        Map<String, List<ProductCategory>> map = new HashMap<String, List<ProductCategory>>();
        List<ProductCategory> list = null;
        for (ProductCategory sysDict : sysDictList) {
            list = map.get(sysDict.getParentKey());
            if (CollectionUtils.isEmpty(list)) {
                list = new ArrayList<ProductCategory>();
                list.add(sysDict);
                map.put(sysDict.getParentKey(), list);
            } else {
                list.add(sysDict);
                map.put(sysDict.getParentKey(), list);
            }
        }
        return map;
    }

    @Override
    public List<ProductCategory> queryProductCategoryList(
            List<String> kindList, String type, String parentKey, String dkey,
            String modelCode) {
        ProductCategory condition = new ProductCategory();
        condition.setKindList(kindList);
        condition.setType(type);
        condition.setDkey(dkey);
        condition.setParentKey(parentKey);
        condition.setModelSpecsCode(modelCode);
        return productCategoryDAO.selectList(condition);
    }
}
