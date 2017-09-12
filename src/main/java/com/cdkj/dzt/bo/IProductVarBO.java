package com.cdkj.dzt.bo;

import java.util.List;

import com.cdkj.dzt.bo.base.IPaginableBO;
import com.cdkj.dzt.domain.ModelSpecs;
import com.cdkj.dzt.domain.ProductVar;

public interface IProductVarBO extends IPaginableBO<ProductVar> {

    public boolean isProductVarExist(String code);

    public String saveProductVar(ModelSpecs modelSpecs, String updater,
            String productCode);

    public void removeProductVar(ProductVar data);

    public void refreshProductVar(ProductVar data);

    public List<ProductVar> queryProductVarList(ProductVar condition);

    public ProductVar getProductVar(String code);

    public void queryProductVarList(String code, String modelCode);

}
