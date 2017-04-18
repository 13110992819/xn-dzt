package com.cdkj.dzt.bo;

import java.util.List;

import com.cdkj.dzt.bo.base.IPaginableBO;
import com.cdkj.dzt.domain.Product;

public interface IProductBO extends IPaginableBO<Product> {

    public Product getProductByOrderCode(String orderCode);

    public List<Product> queryRichProductList(String orderCode);

}
