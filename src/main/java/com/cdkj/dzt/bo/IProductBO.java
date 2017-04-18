package com.cdkj.dzt.bo;

import java.util.List;

import com.cdkj.dzt.bo.base.IPaginableBO;
import com.cdkj.dzt.domain.Product;

public interface IProductBO extends IPaginableBO<Product> {

    public boolean isProductExist(String code);

    public String saveProduct(Product data);

    public int removeProduct(String code);

    public int refreshProduct(Product data);

    public List<Product> queryProductList(Product condition);

    public Product getProductByOrderCode(String orderCode);

    public Product getProduct(String code);

}
