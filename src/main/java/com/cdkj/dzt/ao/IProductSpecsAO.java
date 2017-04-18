package com.cdkj.dzt.ao;

import java.util.List;

import com.cdkj.dzt.bo.base.Paginable;
import com.cdkj.dzt.domain.ProductSpecs;

public interface IProductSpecsAO {
    static final String DEFAULT_ORDER_COLUMN = "code";

    public Paginable<ProductSpecs> queryProductSpecsPage(int start, int limit,
            ProductSpecs condition);

    public List<ProductSpecs> queryProductSpecsList(ProductSpecs condition);

    public ProductSpecs getProductSpecs(String code);

}
