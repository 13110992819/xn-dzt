package com.cdkj.dzt.bo;

import java.util.List;

import com.cdkj.dzt.bo.base.IPaginableBO;
import com.cdkj.dzt.domain.ProductSpecs;

public interface IProductSpecsBO extends IPaginableBO<ProductSpecs> {

    public boolean isProductSpecsExist(String code);

    public void inputInfor(String name, String parentCode, String type,
            String pic, String orderCode, String productCode);

    public List<ProductSpecs> queryProductSpecsList(ProductSpecs condition);

    public List<ProductSpecs> queryProductSpecsList(String parentCode);

    public ProductSpecs getProductSpecs(String code);

}
