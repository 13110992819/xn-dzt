package com.cdkj.dzt.bo.impl;

import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.cdkj.dzt.bo.IProductBO;
import com.cdkj.dzt.bo.IProductSpecsBO;
import com.cdkj.dzt.bo.base.PaginableBOImpl;
import com.cdkj.dzt.dao.IProductDAO;
import com.cdkj.dzt.domain.Product;
import com.cdkj.dzt.domain.ProductSpecs;

@Component
public class ProductBOImpl extends PaginableBOImpl<Product> implements
        IProductBO {

    @Autowired
    private IProductDAO productDAO;

    @Autowired
    private IProductSpecsBO productSpecsBO;

    @Override
    public Product getProductByOrderCode(String orderCode) {
        Product product = null;
        Product condition = new Product();
        condition.setOrderCode(orderCode);
        List<Product> productList = productDAO.selectList(condition);
        if (CollectionUtils.isNotEmpty(productList)) {
            product = productList.get(0);
        }
        return product;
    }

    @Override
    public List<Product> queryRichProductList(String orderCode) {
        Product condition = new Product();
        condition.setOrderCode(orderCode);
        List<Product> list = productDAO.selectList(condition);
        if (CollectionUtils.isNotEmpty(list)) {
            for (Product ele : list) {
                List<ProductSpecs> productSpecsList = productSpecsBO
                    .queryProductSpecsList(ele.getCode());
                ele.setProductSpecsList(productSpecsList);
            }
        }
        return list;
    }
}
