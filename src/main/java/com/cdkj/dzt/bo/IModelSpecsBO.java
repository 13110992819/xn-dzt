package com.cdkj.dzt.bo;

import java.util.List;

import com.cdkj.dzt.bo.base.IPaginableBO;
import com.cdkj.dzt.domain.ModelSpecs;

public interface IModelSpecsBO extends IPaginableBO<ModelSpecs> {

    public boolean isModelSpecsExist(String code);

    public void saveModelSpecs(ModelSpecs data);

    public void removeModelSpecs(String code);

    public void refreshModelSpecs(ModelSpecs data);

    public List<ModelSpecs> queryModelSpecsList(ModelSpecs condition);

    public ModelSpecs getModelSpecs(String code);

}
