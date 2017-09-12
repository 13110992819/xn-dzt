package com.cdkj.dzt.ao;

import java.util.List;

import org.springframework.stereotype.Component;

import com.cdkj.dzt.bo.base.Paginable;
import com.cdkj.dzt.domain.ProductVar;

@Component
public interface IProductVarAO {
    static final String DEFAULT_ORDER_COLUMN = "code";

    public String addProductVar(ProductVar data);

    public void dropProductVar(String code);

    public void editProductVar(ProductVar data);

    public Paginable<ProductVar> queryProductVarPage(int start, int limit,
            ProductVar condition);

    public List<ProductVar> queryProductVarList(ProductVar condition);

    public ProductVar getProductVar(String code);

}
