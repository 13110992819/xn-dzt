package com.cdkj.dzt.bo.impl;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.cdkj.dzt.bo.IModelBO;
import com.cdkj.dzt.bo.base.PaginableBOImpl;
import com.cdkj.dzt.core.OrderNoGenerater;
import com.cdkj.dzt.dao.IModelDAO;
import com.cdkj.dzt.domain.Model;
import com.cdkj.dzt.enums.EGeneratePrefix;
import com.cdkj.dzt.exception.BizException;

@Component
public class ModelBOImpl extends PaginableBOImpl<Model> implements IModelBO {

    @Autowired
    private IModelDAO modelDAO;

    @Override
    public boolean isModelExist(String code) {
        Model condition = new Model();
        condition.setCode(code);
        if (modelDAO.selectTotalCount(condition) > 0) {
            return true;
        }
        return false;
    }

    @Override
    public String saveModel(Model data) {
        String code = null;
        if (data != null) {
            code = OrderNoGenerater.generateM(EGeneratePrefix.MODEL.getCode());
            data.setCode(code);
            modelDAO.insert(data);
        }
        return code;
    }

    @Override
    public int removeModel(String code) {
        int count = 0;
        if (StringUtils.isNotBlank(code)) {
            Model data = new Model();
            data.setCode(code);
            count = modelDAO.delete(data);
        }
        return count;
    }

    @Override
    public int refreshModel(Model data) {
        int count = 0;
        if (StringUtils.isNotBlank(data.getCode())) {
            count = modelDAO.update(data);
        }
        return count;
    }

    @Override
    public List<Model> queryModelList(Model condition) {
        return modelDAO.selectList(condition);
    }

    @Override
    public Model getModel(String code) {
        Model data = null;
        if (StringUtils.isNotBlank(code)) {
            Model condition = new Model();
            condition.setCode(code);
            data = modelDAO.select(condition);
            if (data == null) {
                throw new BizException("xn0000", "�� ��Ų�����");
            }
        }
        return data;
    }
}
