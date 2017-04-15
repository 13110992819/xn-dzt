package com.cdkj.dzt.ao;

import java.util.List;

import org.springframework.stereotype.Component;

import com.cdkj.dzt.bo.base.Paginable;
import com.cdkj.dzt.domain.Product;

@Component
public interface IProductAO {
    static final String DEFAULT_ORDER_COLUMN = "code";

    public String addProduct(Product data);

    public int dropProduct(String code);

    public int editProduct(Product data);

    public Paginable<Product> queryProductPage(int start, int limit,
            Product condition);

    public List<Product> queryProductList(Product condition);

    public Product getProduct(String code);

}
