package com.cdkj.dzt.ao.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cdkj.dzt.ao.IModelAO;
import com.cdkj.dzt.bo.IModelBO;
import com.cdkj.dzt.bo.base.Paginable;
import com.cdkj.dzt.domain.Model;

@Service
public class ModelAOImpl implements IModelAO {

    @Autowired
    private IModelBO modelBO;

    @Override
    public void editModel(String code, String name, String pic, Long price,
            String updater, String remark) {
        modelBO.refreshModel(code, name, pic, price, updater, remark);
    }

    @Override
    public Paginable<Model> queryModelPage(int start, int limit, Model condition) {
        return modelBO.getPaginable(start, limit, condition);
    }

    @Override
    public List<Model> queryModelList(Model condition) {
        return modelBO.queryModelList(condition);
    }

    @Override
    public Model getModel(String code) {
        return modelBO.getModel(code);
    }
}
