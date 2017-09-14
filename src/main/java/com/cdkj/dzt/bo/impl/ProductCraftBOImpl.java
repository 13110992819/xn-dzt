package com.cdkj.dzt.bo.impl;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.cdkj.dzt.bo.IProductCraftBO;
import com.cdkj.dzt.bo.base.PaginableBOImpl;
import com.cdkj.dzt.dao.IProductCraftDAO;
import com.cdkj.dzt.domain.Craft;
import com.cdkj.dzt.domain.ProductCraft;
import com.cdkj.dzt.exception.BizException;

@Component
public class ProductCraftBOImpl extends PaginableBOImpl<ProductCraft> implements
        IProductCraftBO {

    @Autowired
    private IProductCraftDAO productCraftDAO;

    @Override
    public boolean isProductCraftExist(String code) {
        ProductCraft condition = new ProductCraft();
        condition.setCode(code);
        if (productCraftDAO.selectTotalCount(condition) > 0) {
            return true;
        }
        return false;
    }

    @Override
    public void saveProductCraft(Craft craft, String productVarCode, String code) {
        ProductCraft data = new ProductCraft();
        data.setCode(craft.getCode());
        data.setType(craft.getType());
        data.setName(craft.getName());
        data.setPic(craft.getPic());
        data.setSelected(craft.getSelected());
        data.setPrice(craft.getPrice());
        data.setProductVarCode(productVarCode);
        data.setModelSpecsCode(craft.getModelCode());
        data.setOrderCode(code);
        productCraftDAO.insert(data);
    }

    @Override
    public int removeProductCraft(String code) {
        int count = 0;
        if (StringUtils.isNotBlank(code)) {
            ProductCraft data = new ProductCraft();
            data.setCode(code);
            count = productCraftDAO.delete(data);
        }
        return count;
    }

    @Override
    public int refreshProductCraft(ProductCraft data) {
        int count = 0;
        if (StringUtils.isNotBlank(data.getCode())) {
            // count = productCraftDAO.update(data);
        }
        return count;
    }

    @Override
    public List<ProductCraft> queryProductCraftList(ProductCraft condition) {
        return productCraftDAO.selectList(condition);
    }

    @Override
    public ProductCraft getProductCraft(String code) {
        ProductCraft data = null;
        if (StringUtils.isNotBlank(code)) {
            ProductCraft condition = new ProductCraft();
            condition.setCode(code);
            data = productCraftDAO.select(condition);
            if (data == null) {
                throw new BizException("xn0000", "编号不存在");
            }
        }
        return data;
    }

    @Override
    public List<ProductCraft> queryProductCraftList(String type) {
        ProductCraft condition = new ProductCraft();
        condition.setType(type);
        return productCraftDAO.selectList(condition);
    }

}
