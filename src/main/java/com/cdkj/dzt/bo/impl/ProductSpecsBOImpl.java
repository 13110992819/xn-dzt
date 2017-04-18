package com.cdkj.dzt.bo.impl;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.cdkj.dzt.bo.IProductSpecsBO;
import com.cdkj.dzt.bo.base.PaginableBOImpl;
import com.cdkj.dzt.core.OrderNoGenerater;
import com.cdkj.dzt.dao.IProductSpecsDAO;
import com.cdkj.dzt.domain.ProductSpecs;
import com.cdkj.dzt.enums.EGeneratePrefix;
import com.cdkj.dzt.enums.EMeasure;
import com.cdkj.dzt.exception.BizException;

@Component
public class ProductSpecsBOImpl extends PaginableBOImpl<ProductSpecs> implements
        IProductSpecsBO {

    @Autowired
    private IProductSpecsDAO productSpecsDAO;

    @Override
    public boolean isProductSpecsExist(String code) {
        ProductSpecs condition = new ProductSpecs();
        condition.setCode(code);
        if (productSpecsDAO.selectTotalCount(condition) > 0) {
            return true;
        }
        return false;
    }

    @Override
    public void inputInfor(String name, String parentCode, String type,
            String pic, String orderCode, String productCode) {
        ProductSpecs data = new ProductSpecs();
        String code = OrderNoGenerater.generateM(EGeneratePrefix.PRODUCTSPECS
            .getCode());
        data.setCode(code);
        data.setName(name);
        data.setParentCode(parentCode);
        data.setType(type);
        data.setPic(pic);
        data.setOrderCode(orderCode);
        data.setProductCode(productCode);
        productSpecsDAO.insert(data);
    }

    @Override
    public List<ProductSpecs> queryProductSpecsList(ProductSpecs condition) {
        return productSpecsDAO.selectList(condition);
    }

    @Override
    public List<ProductSpecs> queryProductSpecsList(String parentCode) {
        ProductSpecs condition = new ProductSpecs();
        condition.setType(EMeasure.MEASURE_INFOR.getCode());
        condition.setParentCode(parentCode);
        return productSpecsDAO.selectList(condition);
    }

    @Override
    public ProductSpecs getProductSpecs(String code) {
        ProductSpecs data = null;
        if (StringUtils.isNotBlank(code)) {
            ProductSpecs condition = new ProductSpecs();
            condition.setCode(code);
            data = productSpecsDAO.select(condition);
            if (data == null) {
                throw new BizException("xn0000", "�� ��Ų�����");
            }
        }
        return data;
    }

}
