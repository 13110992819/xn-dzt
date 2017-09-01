package com.cdkj.dzt.bo;

import java.util.List;

import com.cdkj.dzt.bo.base.IPaginableBO;
import com.cdkj.dzt.domain.Model;
import com.cdkj.dzt.domain.Order;
import com.cdkj.dzt.domain.Product;

public interface IProductBO extends IPaginableBO<Product> {
    public String saveProduct(Order order, Model model, Integer quantity);

    public Product getProduct(String code);

    public void removeProduct(String code);

    public Product getProductByOrderCode(String orderCode);

    public List<Product> queryRichProductList(String orderCode);

    public List<Product> queryProductList(String orderCode);

    List<Product> queryCheckProductList(String orderCode);

}
