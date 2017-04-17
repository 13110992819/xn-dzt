package com.cdkj.dzt.ao.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cdkj.dzt.ao.IProductSpecsAO;
import com.cdkj.dzt.bo.IProductSpecsBO;
import com.cdkj.dzt.bo.base.Paginable;
import com.cdkj.dzt.domain.ProductSpecs;
import com.cdkj.dzt.exception.BizException;

@Service
public class ProductSpecsAOImpl implements IProductSpecsAO {

    @Autowired
    private IProductSpecsBO productSpecsBO;

    @Override
    public String addProductSpecs(ProductSpecs data) {
        return productSpecsBO.saveProductSpecs(data);
    }

    @Override
    public int editProductSpecs(ProductSpecs data) {
        if (!productSpecsBO.isProductSpecsExist(data.getCode())) {
            throw new BizException("xn0000", "记录编号不存在");
        }
        return productSpecsBO.refreshProductSpecs(data);
    }

    @Override
    public int dropProductSpecs(String code) {
        if (!productSpecsBO.isProductSpecsExist(code)) {
            throw new BizException("xn0000", "记录编号不存在");
        }
        return productSpecsBO.removeProductSpecs(code);
    }

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
