package com.cdkj.dzt.bo.impl;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.cdkj.dzt.bo.IModelSpecsBO;
import com.cdkj.dzt.bo.base.PaginableBOImpl;
import com.cdkj.dzt.dao.IModelSpecsDAO;
import com.cdkj.dzt.domain.ModelSpecs;
import com.cdkj.dzt.exception.BizException;

@Component
public class ModelSpecsBOImpl extends PaginableBOImpl<ModelSpecs> implements
        IModelSpecsBO {

    @Autowired
    private IModelSpecsDAO modelSpecsDAO;

    @Override
    public boolean isModelSpecsExist(String code) {
        ModelSpecs condition = new ModelSpecs();
        condition.setCode(code);
        if (modelSpecsDAO.selectTotalCount(condition) > 0) {
            return true;
        }
        return false;
    }

    @Override
    public void saveModelSpecs(ModelSpecs data) {
        modelSpecsDAO.insert(data);
    }

    @Override
    public void removeModelSpecs(String code) {
        ModelSpecs data = new ModelSpecs();
        data.setCode(code);
        modelSpecsDAO.delete(data);
    }

    @Override
    public void refreshModelSpecs(ModelSpecs data) {
        modelSpecsDAO.update(data);
    }

    @Override
    public List<ModelSpecs> queryModelSpecsList(ModelSpecs condition) {
        return modelSpecsDAO.selectList(condition);
    }

    @Override
    public ModelSpecs getModelSpecs(String code) {
        ModelSpecs data = null;
        if (StringUtils.isNotBlank(code)) {
            ModelSpecs condition = new ModelSpecs();
            condition.setCode(code);
            data = modelSpecsDAO.select(condition);
            if (data == null) {
                throw new BizException("xn0000", "编号不存在");
            }
        }
        return data;
    }

    @Override
    public List<ModelSpecs> queryModelSpecsList(String modelCode) {
        ModelSpecs condition = new ModelSpecs();
        condition.setModelCode(modelCode);
        return modelSpecsDAO.selectList(condition);
    }
}
