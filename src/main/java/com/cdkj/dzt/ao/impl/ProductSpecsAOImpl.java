package com.cdkj.dzt.ao.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cdkj.dzt.ao.IProductSpecsAO;
import com.cdkj.dzt.bo.IProductBO;
import com.cdkj.dzt.bo.IProductSpecsBO;
import com.cdkj.dzt.core.OrderNoGenerater;
import com.cdkj.dzt.domain.Product;
import com.cdkj.dzt.domain.ProductSpecs;
import com.cdkj.dzt.enums.EGeneratePrefix;

@Service
public class ProductSpecsAOImpl implements IProductSpecsAO {

    @Autowired
    private IProductSpecsBO productSpecsBO;

    @Autowired
    private IProductBO productBO;

    @Override
    public String addProductSpecs(String productCode, String dkey,
            String dvalue, Integer orderNo) {
        Product product = productBO.getProduct(productCode);
        String code = OrderNoGenerater.generateM(EGeneratePrefix.PRODUCT_SPECS
            .getCode());
        ProductSpecs data = new ProductSpecs();

        data.setCode(code);
        data.setProductCode(productCode);
        data.setDkey(dkey);
        data.setDvalue(dvalue);
        data.setOrderNo(orderNo);

        data.setCompanyCode(product.getCompanyCode());
        data.setSystemCode(product.getSystemCode());
        productSpecsBO.saveProductSpecs(data);

        return code;
    }

    @Override
    public void editProductSpecs(String code, String dkey, String dvalue,
            Integer orderNo) {
        ProductSpecs data = new ProductSpecs();
        data.setCode(code);
        data.setDkey(dkey);
        data.setDvalue(dvalue);
        data.setOrderNo(orderNo);
        productSpecsBO.refreshProductSpecs(data);
    }

    @Override
    public void dropProductSpecs(String code) {
        productSpecsBO.removeProductSpecs(code);
    }

    @Override
    public ProductSpecs getProductSpecs(String code) {
        return productSpecsBO.getProductSpecs(code);
    }

    @Override
    public List<ProductSpecs> queryProductSpecsList(ProductSpecs condition) {
        return productSpecsBO.queryProductSpecsList(condition);
    }

}
