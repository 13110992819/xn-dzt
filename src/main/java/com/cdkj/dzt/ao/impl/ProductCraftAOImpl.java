package com.cdkj.dzt.ao.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cdkj.dzt.ao.IProductCraftAO;
import com.cdkj.dzt.bo.IProductCraftBO;
import com.cdkj.dzt.bo.base.Paginable;
import com.cdkj.dzt.domain.ProductCraft;
import com.cdkj.dzt.exception.BizException;

@Service
public class ProductCraftAOImpl implements IProductCraftAO {

    @Autowired
    private IProductCraftBO productCraftBO;

    @Override
    public String addProductCraft(ProductCraft data) {
        return null;
    }

    @Override
    public int editProductCraft(ProductCraft data) {
        if (!productCraftBO.isProductCraftExist(data.getCode())) {
            throw new BizException("xn0000", "记录编号不存在");
        }
        return productCraftBO.refreshProductCraft(data);
    }

    @Override
    public int dropProductCraft(String code) {
        if (!productCraftBO.isProductCraftExist(code)) {
            throw new BizException("xn0000", "记录编号不存在");
        }
        return productCraftBO.removeProductCraft(code);
    }

    @Override
    public Paginable<ProductCraft> queryProductCraftPage(int start, int limit,
            ProductCraft condition) {
        return productCraftBO.getPaginable(start, limit, condition);
    }

    @Override
    public List<ProductCraft> queryProductCraftList(ProductCraft condition) {
        return productCraftBO.queryProductCraftList(condition);
    }

    @Override
    public ProductCraft getProductCraft(String code) {
        return productCraftBO.getProductCraft(code);
    }
}
