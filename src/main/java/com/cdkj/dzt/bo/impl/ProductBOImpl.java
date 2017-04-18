package com.cdkj.dzt.bo.impl;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.cdkj.dzt.bo.IProductBO;
import com.cdkj.dzt.bo.base.PaginableBOImpl;
import com.cdkj.dzt.core.OrderNoGenerater;
import com.cdkj.dzt.dao.IProductDAO;
import com.cdkj.dzt.domain.Product;
import com.cdkj.dzt.enums.EGeneratePrefix;
import com.cdkj.dzt.exception.BizException;

@Component
public class ProductBOImpl extends PaginableBOImpl<Product> implements
        IProductBO {

    @Autowired
    private IProductDAO productDAO;

    @Override
    public boolean isProductExist(String code) {
        Product condition = new Product();
        condition.setCode(code);
        if (productDAO.selectTotalCount(condition) > 0) {
            return true;
        }
        return false;
    }

    @Override
    public String saveProduct(Product data) {
        String code = null;
        if (data != null) {
            code = OrderNoGenerater
                .generateM(EGeneratePrefix.PRODUCT.getCode());
            data.setCode(code);
            productDAO.insert(data);
        }
        return code;
    }

    @Override
    public int removeProduct(String code) {
        int count = 0;
        if (StringUtils.isNotBlank(code)) {
            Product data = new Product();
            data.setCode(code);
            count = productDAO.delete(data);
        }
        return count;
    }

    @Override
    public int refreshProduct(Product data) {
        int count = 0;
        if (StringUtils.isNotBlank(data.getCode())) {
            count = productDAO.update(data);
        }
        return count;
    }

    @Override
    public List<Product> queryProductList(Product condition) {
        return productDAO.selectList(condition);
    }

    @Override
    public List<Product> queryProductList(String orderCode) {
        Product condition = new Product();
        condition.setOrderCode(orderCode);
        return productDAO.selectList(condition);
    }

    @Override
    public Product getProduct(String code) {
        Product data = null;
        if (StringUtils.isNotBlank(code)) {
            Product condition = new Product();
            condition.setCode(code);
            data = productDAO.select(condition);
            if (data == null) {
                throw new BizException("xn0000", "�� ��Ų�����");
            }
        }
        return data;
    }

}
