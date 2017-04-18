package com.cdkj.dzt.ao.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cdkj.dzt.ao.IProductSpecsAO;
import com.cdkj.dzt.bo.IProductSpecsBO;
import com.cdkj.dzt.bo.base.Paginable;
import com.cdkj.dzt.domain.ProductSpecs;

@Service
public class ProductSpecsAOImpl implements IProductSpecsAO {

    @Autowired
    private IProductSpecsBO productSpecsBO;

    @Override
    public Paginable<ProductSpecs> queryProductSpecsPage(int start, int limit,
            ProductSpecs condition) {
        return productSpecsBO.getPaginable(start, limit, condition);
    }

    @Override
    public List<ProductSpecs> queryProductSpecsList(ProductSpecs condition) {
        return productSpecsBO.queryProductSpecsList(condition);
    }

    @Override
    public ProductSpecs getProductSpecs(String code) {
        return productSpecsBO.getProductSpecs(code);
    }
}
