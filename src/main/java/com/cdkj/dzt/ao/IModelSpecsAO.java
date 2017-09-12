package com.cdkj.dzt.ao;

import java.util.List;

import org.springframework.stereotype.Component;

import com.cdkj.dzt.bo.base.Paginable;
import com.cdkj.dzt.domain.ModelSpecs;

@Component
public interface IModelSpecsAO {
    static final String DEFAULT_ORDER_COLUMN = "code";

    public String addModelSpecs(String name, String pic, String updater,
            String remark, String modelCode);

    public void dropModelSpecs(String code);

    public Paginable<ModelSpecs> queryModelSpecsPage(int start, int limit,
            ModelSpecs condition);

    public List<ModelSpecs> queryModelSpecsList(ModelSpecs condition);

    public ModelSpecs getModelSpecs(String code);

    public void editModelSpecs(String code, String name, String pic,
            String updater, String remark, String modelCode);

}
