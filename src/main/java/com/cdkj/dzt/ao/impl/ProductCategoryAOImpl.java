/**
 * @Title SYSDictAOImpl.java 
 * @Package com.xnjr.moom.ao.impl 
 * @Description 
 * @author haiqingzheng  
 * @date 2016年4月17日 下午5:19:00 
 * @version V1.0   
 */
package com.cdkj.dzt.ao.impl;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cdkj.dzt.ao.IProductCategoryAO;
import com.cdkj.dzt.bo.IProductCategoryBO;
import com.cdkj.dzt.bo.base.Paginable;
import com.cdkj.dzt.domain.ProductCategory;
import com.cdkj.dzt.enums.EBoolean;
import com.cdkj.dzt.enums.EDictType;
import com.cdkj.dzt.exception.BizException;

/** 
 * @author: haiqingzheng 
 * @since: 2016年4月17日 下午5:19:00 
 * @history:
 */
@Service
public class ProductCategoryAOImpl implements IProductCategoryAO {
    @Autowired
    IProductCategoryBO productCategoryBO;

    /** 
     * @see com.xnjr.base.ao.ISYSDictAO#addSYSDict(java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String)
     */
    @Override
    public String addProductCategory(String type, String parentKey, String key,
            String value, String updater, String remark, String modelCode) {
        if (EDictType.SECOND.getCode().equals(type)) {
            if (StringUtils.isBlank(parentKey)) {
                throw new BizException("xn000000", "第二层字典数据，parentKey不能为空");
            }
            // 查看父节点是否存在
            ProductCategory fDict = new ProductCategory();
            fDict.setDkey(parentKey);
            fDict.setType(EDictType.FIRST.getCode());
            if (productCategoryBO.getTotalCount(fDict) <= 0) {
                throw new BizException("xn000000", "parentKey不存在");
            }
            // 第二层数据字典 在当前父节点下key不能重复
            ProductCategory condition = new ProductCategory();
            condition.setDkey(key);
            condition.setParentKey(parentKey);
            condition.setType(EDictType.SECOND.getCode());
            if (productCategoryBO.getTotalCount(condition) > 0) {
                throw new BizException("xn000000", "当前节点下，key不能为重复");
            }
        } else if (EDictType.FIRST.getCode().equals(type)) {
            // 第一层数据字典 key不能重复
            ProductCategory condition = new ProductCategory();
            condition.setDkey(key);
            condition.setType(EDictType.FIRST.getCode());
            if (productCategoryBO.getTotalCount(condition) > 0) {
                throw new BizException("xn000000", "第一层key不能为重复");
            }
        } else {
            throw new BizException("xn000000", "type类型不在枚举类中 0-第一层 1-第二层");
        }
        ProductCategory productCategory = new ProductCategory();
        productCategory.setType(type);
        if (EDictType.SECOND.getCode().equals(type)) {
            productCategory.setParentKey(parentKey);
        }
        productCategory.setDkey(key);
        productCategory.setDvalue(value);
        productCategory.setUpdater(updater);
        productCategory.setRemark(remark);
        productCategory.setModelCode(modelCode);
        return productCategoryBO.saveProductCategory(productCategory);
    }

    @Override
    public int dropProductCategory(String code) {
        ProductCategory condition = new ProductCategory();
        condition.setCode(code);
        if (productCategoryBO.getTotalCount(condition) <= 0) {
            throw new BizException("xn000000", "id记录不存在");
        }
        return productCategoryBO.removeProductCategory(code);
    }

    @Override
    public int editProductCategory(String code, String value, String updater,
            String remark) {
        ProductCategory data = new ProductCategory();
        data.setCode(code);
        data.setDvalue(value);
        data.setUpdater(updater);
        data.setRemark(remark);
        return productCategoryBO.refreshProductCategory(data);
    }

    @Override
    public Paginable<ProductCategory> queryProductCategoryPage(int start,
            int limit, ProductCategory condition) {
        return productCategoryBO.getPaginable(start, limit, condition);
    }

    @Override
    public List<ProductCategory> queryProductCategoryList(
            ProductCategory condition) {
        return productCategoryBO.queryProductCategoryList(condition);
    }

    @Override
    public Map<String, LinkedHashMap<String, String>> queryMapProductCategoryList() {
        Map<String, LinkedHashMap<String, String>> resultMap = new HashMap<String, LinkedHashMap<String, String>>();
        ProductCategory condition = new ProductCategory();
        condition.setType(EBoolean.NO.getCode());
        List<ProductCategory> sysDictList = productCategoryBO
            .queryProductCategoryList(condition);
        if (CollectionUtils.isNotEmpty(sysDictList)) {
            for (ProductCategory sysDict : sysDictList) {
                ProductCategory childCondition = new ProductCategory();
                childCondition.setParentKey(sysDict.getDkey());
                List<ProductCategory> childProductCategoryList = productCategoryBO
                    .queryProductCategoryList(childCondition);
                LinkedHashMap<String, String> childList = new LinkedHashMap<String, String>();
                for (ProductCategory domain : childProductCategoryList) {
                    childList.put(domain.getDkey(), domain.getDvalue());
                }
                resultMap.put(sysDict.getDkey(), childList);
            }
        }
        return resultMap;
    }

    @Override
    public ProductCategory getProductCategory(String code) {
        ProductCategory condition = new ProductCategory();
        condition.setCode(code);
        if (productCategoryBO.getTotalCount(condition) <= 0) {
            throw new BizException("xn000000", "id记录不存在");
        }
        return productCategoryBO.getProductCategory(code);
    }
}
