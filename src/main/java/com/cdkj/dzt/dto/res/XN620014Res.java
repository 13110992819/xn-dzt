package com.cdkj.dzt.dto.res;

import java.util.List;

import com.cdkj.dzt.domain.Cloth;
import com.cdkj.dzt.domain.Model;
import com.cdkj.dzt.domain.ProductCategory;

public class XN620014Res {
    private Model model;

    private List<Cloth> clothList;

    private List<ProductCategory> productCategoryList;

    public Model getModel() {
        return model;
    }

    public void setModel(Model model) {
        this.model = model;
    }

    public List<ProductCategory> getProductCategoryList() {
        return productCategoryList;
    }

    public void setProductCategoryList(List<ProductCategory> productCategoryList) {
        this.productCategoryList = productCategoryList;
    }

    public List<Cloth> getClothList() {
        return clothList;
    }

    public void setClothList(List<Cloth> clothList) {
        this.clothList = clothList;
    }
}
