package com.cdkj.dzt.ao;

import java.util.List;

import com.cdkj.dzt.bo.base.Paginable;
import com.cdkj.dzt.domain.ModelSpecs;

public interface IModelSpecsAO {
    static final String DEFAULT_ORDER_COLUMN = "code";

    public String addModelSpecs(ModelSpecs data);

    public int dropModelSpecs(String code);

    public int editModelSpecs(ModelSpecs data);

    public Paginable<ModelSpecs> queryModelSpecsPage(int start, int limit,
            ModelSpecs condition);

    public List<ModelSpecs> queryModelSpecsList(ModelSpecs condition);

    public ModelSpecs getModelSpecs(String code);

}
