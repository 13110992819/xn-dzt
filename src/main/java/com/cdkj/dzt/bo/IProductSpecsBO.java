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
            String pic, String brand, String modelNum, String advPic,
            String color, String flowers, String form, Integer weight,
            String yarn, Long price, String productVarCode, String productCode,
            String orderCode);

    public void removeProductSpecs(String productCode);

    public void inputInforValue(Order order, Product product,
            Map<String, String> map);

    public List<ProductSpecs> queryProductSpecsList(String productVarCode);

    public void inputInforValue(Order order, Map<String, String> map);

    public List<ProductSpecs> queryPSByOrderCodeList(String orderCode);

    public void refreshProductCode(String orderCode, String productCode);

    public void inputInforCloth(Order order, Product product,
            List<Cloth> clothList);

    public void inputInforCraft(Order order, Product product,
            List<Craft> craftList);

    public void removeProductSpecs(String type, String orderCode);

}
