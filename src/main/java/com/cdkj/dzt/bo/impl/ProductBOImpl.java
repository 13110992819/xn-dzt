package com.cdkj.dzt.bo.impl;

import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.cdkj.dzt.bo.IProductBO;
import com.cdkj.dzt.bo.IProductSpecsBO;
import com.cdkj.dzt.bo.base.PaginableBOImpl;
import com.cdkj.dzt.core.OrderNoGenerater;
import com.cdkj.dzt.dao.IProductDAO;
import com.cdkj.dzt.domain.Model;
import com.cdkj.dzt.domain.Order;
import com.cdkj.dzt.domain.Product;
import com.cdkj.dzt.domain.ProductSpecs;
import com.cdkj.dzt.enums.EGeneratePrefix;
import com.cdkj.dzt.exception.BizException;

@Component
public class ProductBOImpl extends PaginableBOImpl<Product> implements
        IProductBO {

    @Autowired
    private IProductDAO productDAO;

    @Autowired
    private IProductSpecsBO productSpecsBO;

    @Override
    public String saveProduct(Order order, Model model, Integer quantity) {
        Product data = new Product();
        String code = OrderNoGenerater.generateM(EGeneratePrefix.PRODUCT
            .getCode());
        data.setCode(code);
        data.setOrderCode(order.getCode());
        data.setModelCode(model.getCode());
        data.setModelName(model.getName());
        data.setModelPic(model.getPic());
        data.setAdvPic(model.getAdvPic());
        data.setDescription(model.getDescription());
        data.setLoss(model.getLoss());
        data.setProcessFee(model.getProcessFee());
        data.setPrice(model.getPrice());
        data.setQuantity(quantity);
        productDAO.insert(data);
        return code;
    }

    @Override
    public Product getProductByOrderCode(String orderCode) {
        Product product = null;
        Product condition = new Product();
        condition.setOrderCode(orderCode);
        List<Product> productList = productDAO.selectList(condition);
        if (CollectionUtils.isNotEmpty(productList)) {
            product = productList.get(0);
        }
        return product;
    }

    @Override
    public List<Product> queryRichProductList(String orderCode) {
        Product condition = new Product();
        condition.setOrderCode(orderCode);
        List<Product> list = productDAO.selectList(condition);
        if (CollectionUtils.isNotEmpty(list)) {
            for (Product ele : list) {
                List<ProductSpecs> productSpecsList = productSpecsBO
                    .queryProductSpecsList(ele.getCode());
                ele.setProductSpecsList(productSpecsList);
            }
        } else {
            Product data = new Product();
            List<ProductSpecs> productSpecsList = productSpecsBO
                .queryPSByOrderCodeList(orderCode);
            data.setProductSpecsList(productSpecsList);
            list.add(data);
        }
        return list;
    }

    @Override
    public List<Product> queryCheckProductList(String orderCode) {
        Product condition = new Product();
        condition.setOrderCode(orderCode);
        List<Product> list = productDAO.selectList(condition);
        if (CollectionUtils.isNotEmpty(list)) {
            for (Product ele : list) {
                List<ProductSpecs> productSpecsList = productSpecsBO
                    .queryProductSpecsList(ele.getCode());
                ele.setProductSpecsList(productSpecsList);
            }
        }
        return list;
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
                throw new BizException("xn0000", code + "编号的产品不存在");
            }
        }
        return data;
    }

    @Override
    public void removeProduct(String code) {
        Product data = new Product();
        data.setOrderCode(code);
        productDAO.delete(data);
    }
}
