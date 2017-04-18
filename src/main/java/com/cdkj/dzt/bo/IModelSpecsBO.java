package com.cdkj.dzt.bo;

import java.util.List;
import java.util.Map;

import com.cdkj.dzt.bo.base.IPaginableBO;
import com.cdkj.dzt.domain.ModelSpecs;

public interface IModelSpecsBO extends IPaginableBO<ModelSpecs> {

    public boolean isModelSpecsExist(String code);

    public int refreshModelSpecs(String code, String name, String parentCode,
            String type, String pic, Integer orderNo, String remark,
            String modelCode);

    public List<ModelSpecs> queryModelSpecsList(ModelSpecs condition);

    public ModelSpecs getModelSpecs(String code);

    public Map<String, ModelSpecs> getMap();

}
