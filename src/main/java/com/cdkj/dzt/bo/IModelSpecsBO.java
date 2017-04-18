package com.cdkj.dzt.bo;

import java.util.List;

import com.cdkj.dzt.bo.base.IPaginableBO;
import com.cdkj.dzt.domain.ModelSpecs;

public interface IModelSpecsBO extends IPaginableBO<ModelSpecs> {

    public void refreshModelSpecs(String code, String name, String pic,
            Integer orderNo, String remark);

    public List<ModelSpecs> queryModelSpecsList(ModelSpecs condition);

    public ModelSpecs getModelSpecs(String code);

}
