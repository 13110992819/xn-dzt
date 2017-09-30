package com.cdkj.dzt.bo;

import java.util.List;

import com.cdkj.dzt.bo.base.IPaginableBO;
import com.cdkj.dzt.domain.Craft;
import com.cdkj.dzt.domain.ProductCraft;

public interface IProductCraftBO extends IPaginableBO<ProductCraft> {

    public boolean isProductCraftExist(String code);

    public int removeProductCraft(String code);

    public int refreshProductCraft(ProductCraft data);

    public List<ProductCraft> queryProductCraftList(ProductCraft condition);

    public ProductCraft getProductCraft(String code);

    public void saveProductCraft(Craft craft, String productVarCode, String code);

    public List<ProductCraft> queryProductCraftList(String productVarCode,
            String type);

    public void saveProductCraft(String code, String type, String modelCode,
            String productVarCode, String orderCode);

}
