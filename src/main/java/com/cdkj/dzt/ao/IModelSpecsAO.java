package com.cdkj.dzt.ao;

import java.util.List;

import com.cdkj.dzt.bo.base.Paginable;
import com.cdkj.dzt.domain.ModelSpecs;

public interface IModelSpecsAO {
    static final String DEFAULT_ORDER_COLUMN = "code";

    public int editModelSpecs(String code, String name, String parentCode,
            String type, String pic, String orderNo, String remark,
            String modelCode);

    public Paginable<ModelSpecs> queryModelSpecsPage(int start, int limit,
            ModelSpecs condition);

    public List<ModelSpecs> queryModelSpecsList(ModelSpecs condition);

    public ModelSpecs getModelSpecs(String code);

}
