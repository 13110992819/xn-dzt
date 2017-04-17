package com.cdkj.dzt.ao.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cdkj.dzt.ao.IModelSpecsAO;
import com.cdkj.dzt.bo.IModelSpecsBO;
import com.cdkj.dzt.bo.base.Paginable;
import com.cdkj.dzt.domain.ModelSpecs;
import com.cdkj.dzt.exception.BizException;

@Service
public class ModelSpecsAOImpl implements IModelSpecsAO {

    @Autowired
    private IModelSpecsBO modelSpecsBO;

    @Override
    public int editModelSpecs(String code, String name, String parentCode,
            String type, String pic, Integer orderNo, String remark,
            String modelCode) {
        if (!modelSpecsBO.isModelSpecsExist(code)) {
            throw new BizException("xn0000", "记录编号不存在");
        }
        return modelSpecsBO.refreshModelSpecs(code, name, parentCode, type,
            pic, orderNo, remark, modelCode);
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
