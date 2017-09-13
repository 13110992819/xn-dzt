package com.cdkj.dzt.ao.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cdkj.dzt.ao.IModelSpecsAO;
import com.cdkj.dzt.bo.IModelBO;
import com.cdkj.dzt.bo.IModelSpecsBO;
import com.cdkj.dzt.bo.base.Paginable;
import com.cdkj.dzt.core.OrderNoGenerater;
import com.cdkj.dzt.domain.Model;
import com.cdkj.dzt.domain.ModelSpecs;
import com.cdkj.dzt.enums.EGeneratePrefix;
import com.cdkj.dzt.enums.EStatus;
import com.cdkj.dzt.exception.BizException;

@Service
public class ModelSpecsAOImpl implements IModelSpecsAO {

    @Autowired
    private IModelSpecsBO modelSpecsBO;

    @Autowired
    private IModelBO modelBO;

    @Override
    public String addModelSpecs(String name, String pic, String updater,
            String remark, String modelCode) {
        ModelSpecs data = new ModelSpecs();
        String code = OrderNoGenerater.generateM(EGeneratePrefix.MODELSPECS
            .getCode());
        data.setCode(code);
        data.setName(name);
        data.setPic(pic);
        data.setUpdater(updater);
        data.setUpdateDatetime(new Date());
        data.setRemark(remark);
        data.setModelCode(modelCode);
        modelSpecsBO.saveModelSpecs(data);
        return code;
    }

    @Override
    public void editModelSpecs(String code, String name, String pic,
            String updater, String remark, String modelCode) {
        ModelSpecs data = modelSpecsBO.getModelSpecs(code);
        Model model = modelBO.getModel(modelCode);
        if (EStatus.PUT_ON.getCode().equals(model.getStatus())) {
            throw new BizException("xn0000", "产品上架中,不可修改");
        }
        data.setName(name);
        data.setPic(pic);
        data.setUpdater(updater);
        data.setUpdateDatetime(new Date());
        data.setRemark(remark);
        data.setModelCode(modelCode);
        modelSpecsBO.refreshModelSpecs(data);
    }

    @Override
    public void dropModelSpecs(String code) {
        ModelSpecs data = modelSpecsBO.getModelSpecs(code);
        Model model = modelBO.getModel(data.getModelCode());
        if (EStatus.PUT_ON.getCode().equals(model.getStatus())) {
            throw new BizException("xn0000", "产品上架中,不可删除");
        }
        modelSpecsBO.removeModelSpecs(code);
    }

    @Override
    public Paginable<ModelSpecs> queryModelSpecsPage(int start, int limit,
            ModelSpecs condition) {
        Paginable<ModelSpecs> page = modelSpecsBO.getPaginable(start, limit,
            condition);
        List<ModelSpecs> list = page.getList();
        for (ModelSpecs modelSpecs : list) {
            fullModelSpecs(modelSpecs);
        }
        return page;
    }

    @Override
    public List<ModelSpecs> queryModelSpecsList(ModelSpecs condition) {
        List<ModelSpecs> list = modelSpecsBO.queryModelSpecsList(condition);
        for (ModelSpecs modelSpecs : list) {
            fullModelSpecs(modelSpecs);
        }
        return list;
    }

    private void fullModelSpecs(ModelSpecs modelSpecs) {
        Model model = modelBO.getModel(modelSpecs.getModelCode());
        modelSpecs.setModelName(model.getName());
    }

    @Override
    public ModelSpecs getModelSpecs(String code) {
        ModelSpecs modelSpecs = modelSpecsBO.getModelSpecs(code);
        fullModelSpecs(modelSpecs);
        return modelSpecs;
    }
}
