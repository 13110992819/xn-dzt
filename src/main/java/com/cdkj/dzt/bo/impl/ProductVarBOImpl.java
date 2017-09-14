package com.cdkj.dzt.bo.impl;

import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.cdkj.dzt.bo.IProductCategoryBO;
import com.cdkj.dzt.bo.IProductCraftBO;
import com.cdkj.dzt.bo.IProductSpecsBO;
import com.cdkj.dzt.bo.IProductVarBO;
import com.cdkj.dzt.bo.base.PaginableBOImpl;
import com.cdkj.dzt.core.OrderNoGenerater;
import com.cdkj.dzt.dao.IProductVarDAO;
import com.cdkj.dzt.domain.ModelSpecs;
import com.cdkj.dzt.domain.ProductCategory;
import com.cdkj.dzt.domain.ProductCraft;
import com.cdkj.dzt.domain.ProductSpecs;
import com.cdkj.dzt.domain.ProductVar;
import com.cdkj.dzt.enums.EDictType;
import com.cdkj.dzt.enums.EGeneratePrefix;
import com.cdkj.dzt.exception.BizException;

@Component
public class ProductVarBOImpl extends PaginableBOImpl<ProductVar> implements
        IProductVarBO {

    @Autowired
    private IProductVarDAO productVarDAO;

    @Autowired
    private IProductCategoryBO productCategoryBO;

    @Autowired
    private IProductCraftBO productCraftBO;

    @Autowired
    private IProductSpecsBO productSpecsBO;

    @Override
    public boolean isProductVarExist(String code) {
        ProductVar condition = new ProductVar();
        condition.setCode(code);
        if (productVarDAO.selectTotalCount(condition) > 0) {
            return true;
        }
        return false;
    }

    @Override
    public String saveProductVar(ModelSpecs modelSpecs, String updater,
            String productCode) {
        ProductVar data = new ProductVar();
        String code = OrderNoGenerater.generateM(EGeneratePrefix.PRODUCTVAR
            .getCode());
        data.setCode(code);
        data.setName(modelSpecs.getName());
        data.setPic(modelSpecs.getPic());
        data.setUpdater(updater);
        data.setUpdateDatetime(new Date());
        data.setProductCode(productCode);
        data.setModelSpecsCode(modelSpecs.getCode());
        productVarDAO.insert(data);
        return code;
    }

    @Override
    public void removeProductVar(ProductVar data) {
        productVarDAO.delete(data);
    }

    @Override
    public void refreshProductVar(ProductVar data) {
        if (StringUtils.isNotBlank(data.getCode())) {
            productVarDAO.update(data);
        }
    }

    @Override
    public List<ProductVar> queryProductVarList(ProductVar condition) {
        return productVarDAO.selectList(condition);
    }

    @Override
    public ProductVar getProductVar(String code) {
        ProductVar data = null;
        if (StringUtils.isNotBlank(code)) {
            ProductVar condition = new ProductVar();
            condition.setCode(code);
            data = productVarDAO.select(condition);
            if (data == null) {
                throw new BizException("xn0000", "编号不存在");
            }
        }
        return data;
    }

    @Override
    public List<ProductVar> queryProductVarList(String code) {
        ProductVar condition = new ProductVar();
        condition.setProductCode(code);
        List<ProductVar> list = productVarDAO.selectList(condition);
        for (ProductVar productVar : list) {
            // 找到布料
            List<ProductSpecs> PSlist = productSpecsBO
                .queryProductSpecsList(productVar.getCode());
            // 找到分类
            List<ProductCategory> PClist = productCategoryBO
                .queryProductCategoryList(EDictType.FIRST.getCode(), null,
                    productVar.getModelSpecsCode());
            for (ProductCategory productCategory : PClist) {
                // 找到工艺
                List<ProductCraft> productCraftList = productCraftBO
                    .queryProductCraftList(productCategory.getDkey());
                for (ProductCraft productCraft : productCraftList) {
                    if (productCategory.getDkey()
                        .equals(productCraft.getType())) {
                        productCategory.setProductCraft(productCraft);
                        break;
                    }
                }
                List<ProductCategory> PCList = productCategoryBO
                    .queryProductCategoryList(EDictType.SECOND.getCode(),
                        productCategory.getDkey(),
                        productVar.getModelSpecsCode());
                for (ProductCategory productCate : PCList) {
                    List<ProductCraft> productCList = productCraftBO
                        .queryProductCraftList(productCate.getDkey());
                    for (ProductCraft productCraft2 : productCList) {
                        if (productCategory.getDkey().equals(
                            productCraft2.getType())) {
                            productCategory.setColorProductCraft(productCraft2);
                        }
                    }
                }
            }
            productVar.setProductSpecs(PSlist);
            productVar.setProductCategory(PClist);
        }
        return list;
    }
}
