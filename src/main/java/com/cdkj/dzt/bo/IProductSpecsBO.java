package com.cdkj.dzt.bo;

import java.util.List;

import com.cdkj.dzt.bo.base.IPaginableBO;
import com.cdkj.dzt.domain.ProductSpecs;

public interface IProductSpecsBO extends IPaginableBO<ProductSpecs> {

    public boolean isProductSpecsExist(String code);

    public String saveProductSpecs(ProductSpecs data);

    public int removeProductSpecs(String code);

    public int refreshProductSpecs(ProductSpecs data);

    public List<ProductSpecs> queryProductSpecsList(ProductSpecs condition);

    public ProductSpecs getProductSpecs(String code);

    public List<ProductSpecs> queryProductSpecsList(String productCode);

}
