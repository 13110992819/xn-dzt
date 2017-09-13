package com.cdkj.dzt.ao;

import java.util.List;

import org.springframework.stereotype.Component;

import com.cdkj.dzt.bo.base.Paginable;
import com.cdkj.dzt.domain.ProductCraft;

@Component
public interface IProductCraftAO {
    static final String DEFAULT_ORDER_COLUMN = "code";

    public String addProductCraft(ProductCraft data);

    public int dropProductCraft(String code);

    public int editProductCraft(ProductCraft data);

    public Paginable<ProductCraft> queryProductCraftPage(int start, int limit,
            ProductCraft condition);

    public List<ProductCraft> queryProductCraftList(ProductCraft condition);

    public ProductCraft getProductCraft(String code);

}
