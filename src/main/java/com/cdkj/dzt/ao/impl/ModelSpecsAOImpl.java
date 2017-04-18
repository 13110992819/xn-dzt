package com.cdkj.dzt.ao.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cdkj.dzt.ao.IModelSpecsAO;
import com.cdkj.dzt.bo.IModelSpecsBO;
import com.cdkj.dzt.bo.base.Paginable;
import com.cdkj.dzt.domain.ModelSpecs;

@Service
public class ModelSpecsAOImpl implements IModelSpecsAO {

    @Autowired
    private IModelSpecsBO modelSpecsBO;

    @Override
    public void editModelSpecs(String code, String name, String pic,
            Integer orderNo, String remark) {
        modelSpecsBO.refreshModelSpecs(code, name, pic, orderNo, remark);
    }

    @Override
    public Paginable<ModelSpecs> queryModelSpecsPage(int start, int limit,
            ModelSpecs condition) {
        return modelSpecsBO.getPaginable(start, limit, condition);
    }

    @Override
    public List<ModelSpecs> queryModelSpecsList(ModelSpecs condition) {
        return modelSpecsBO.queryModelSpecsList(condition);
    }

    @Override
    public ModelSpecs getModelSpecs(String code) {
        return modelSpecsBO.getModelSpecs(code);
    }
}
