package com.cdkj.dzt.bo;

import java.util.List;
import java.util.Map;

import com.cdkj.dzt.bo.base.IPaginableBO;
import com.cdkj.dzt.domain.Cloth;
import com.cdkj.dzt.domain.Craft;
import com.cdkj.dzt.domain.Order;
import com.cdkj.dzt.domain.Product;
import com.cdkj.dzt.domain.ProductSpecs;

public interface IProductSpecsBO extends IPaginableBO<ProductSpecs> {
    public void saveProductSpecs(String code, String name, String type,
            String pic, String parentCode, String productCode, String orderCode);

    public void removeProductSpecs(String productCode);

    public void inputInforValue(Order order, Product product,
            Map<String, String> map);

    // public void inputInforCraft(Order order, Product product,
    // Map<String, String> valueMap, Map<String, Craft> modelSmap);

    public List<ProductSpecs> queryProductSpecsList(String productCode);

    public void inputInforValue(Order order, Map<String, String> map);

    // public void inputInforCloth(Order order, Product product,
    // Map<String, String> map, Map<String, Cloth> clothSmap);

    public List<ProductSpecs> queryPSByOrderCodeList(String orderCode);

    public void refreshProductCode(String orderCode, String productCode);

    public void inputInforCloth(Order order, Product product,
            List<Cloth> clothList);

    public void inputInforCraft(Order order, Product product,
            List<Craft> craftList);

}
