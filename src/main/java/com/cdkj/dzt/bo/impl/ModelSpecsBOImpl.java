package com.cdkj.dzt.bo.impl;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.cdkj.dzt.bo.IModelSpecsBO;
import com.cdkj.dzt.bo.base.PaginableBOImpl;
import com.cdkj.dzt.core.OrderNoGenerater;
import com.cdkj.dzt.dao.IModelSpecsDAO;
import com.cdkj.dzt.domain.ModelSpecs;
import com.cdkj.dzt.enums.EGeneratePrefix;
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
    public String saveModelSpecs(ModelSpecs data) {
        String code = null;
        if (data != null) {
            code = OrderNoGenerater.generateM(EGeneratePrefix.MODELSPECS
                .getCode());
            data.setCode(code);
            modelSpecsDAO.insert(data);
        }
        return code;
    }

    @Override
    public int removeModelSpecs(String code) {
        int count = 0;
        if (StringUtils.isNotBlank(code)) {
            ModelSpecs data = new ModelSpecs();
            data.setCode(code);
            count = modelSpecsDAO.delete(data);
        }
        return count;
    }

    @Override
    public int refreshModelSpecs(ModelSpecs data) {
        int count = 0;
        if (StringUtils.isNotBlank(data.getCode())) {
            count = modelSpecsDAO.update(data);
        }
        return count;
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
                throw new BizException("xn0000", "�� ��Ų�����");
            }
        }
        return data;
    }
}
