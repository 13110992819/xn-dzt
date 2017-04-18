package com.cdkj.dzt.bo;

import java.util.List;
import java.util.Map;

import com.cdkj.dzt.bo.base.IPaginableBO;
import com.cdkj.dzt.domain.ModelSpecs;
import com.cdkj.dzt.domain.Order;
import com.cdkj.dzt.domain.Product;
import com.cdkj.dzt.domain.ProductSpecs;

public interface IProductSpecsBO extends IPaginableBO<ProductSpecs> {

    public void removeProductSpecs(String productCode);

    public void inputInforValue(Order order, Product product,
            Map<String, String> map);

    public void inputInforCode(Order order, Product product,
            Map<String, String> valueMap, Map<String, ModelSpecs> modelSmap);

    public List<ProductSpecs> queryProductSpecsList(String productCode);

}
