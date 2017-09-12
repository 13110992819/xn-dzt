package com.cdkj.dzt.ao.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cdkj.dzt.ao.IProductVarAO;
import com.cdkj.dzt.bo.IProductVarBO;
import com.cdkj.dzt.bo.base.Paginable;
import com.cdkj.dzt.domain.ProductVar;
import com.cdkj.dzt.exception.BizException;

@Service
public class ProductVarAOImpl implements IProductVarAO {

    @Autowired
    private IProductVarBO productVarBO;

    @Override
    public String addProductVar(ProductVar data) {
        return null;
    }

    @Override
    public void editProductVar(ProductVar data) {
        if (!productVarBO.isProductVarExist(data.getCode())) {
            throw new BizException("xn0000", "记录编号不存在");
        }
        productVarBO.refreshProductVar(data);
    }

    @Override
    public void dropProductVar(String code) {
    }

    @Override
    public Paginable<ProductVar> queryProductVarPage(int start, int limit,
            ProductVar condition) {
        return productVarBO.getPaginable(start, limit, condition);
    }

    @Override
    public List<ProductVar> queryProductVarList(ProductVar condition) {
        return productVarBO.queryProductVarList(condition);
    }

    @Override
    public ProductVar getProductVar(String code) {
        return productVarBO.getProductVar(code);
    }
}
