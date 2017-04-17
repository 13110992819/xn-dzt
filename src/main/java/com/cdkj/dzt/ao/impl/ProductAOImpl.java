package com.cdkj.dzt.ao.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cdkj.dzt.ao.IProductAO;
import com.cdkj.dzt.bo.IProductBO;
import com.cdkj.dzt.bo.base.Paginable;
import com.cdkj.dzt.domain.Product;
import com.cdkj.dzt.exception.BizException;

@Service
public class ProductAOImpl implements IProductAO {

    @Autowired
    private IProductBO productBO;

    @Override
    public String addProduct(Product data) {
        return productBO.saveProduct(data);
    }

    @Override
    public int editProduct(Product data) {
        if (!productBO.isProductExist(data.getCode())) {
            throw new BizException("xn0000", "记录编号不存在");
        }
        return productBO.refreshProduct(data);
    }

    @Override
    public int dropProduct(String code) {
        if (!productBO.isProductExist(code)) {
            throw new BizException("xn0000", "记录编号不存在");
        }
        return productBO.removeProduct(code);
    }

    @Override
    public Paginable<Product> queryProductPage(int start, int limit,
            Product condition) {
        return productBO.getPaginable(start, limit, condition);
    }

    @Override
    public List<Product> queryProductList(Product condition) {
        return productBO.queryProductList(condition);
    }

    @Override
    public Product getProduct(String code) {
        return productBO.getProduct(code);
    }
}
