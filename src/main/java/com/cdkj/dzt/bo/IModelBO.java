package com.cdkj.dzt.bo;

import java.util.List;

import com.cdkj.dzt.bo.base.IPaginableBO;
import com.cdkj.dzt.domain.Model;

public interface IModelBO extends IPaginableBO<Model> {
    public void saveModel(Model data);

    public void dropModel(Model data);

    public void refreshModel(Model data);

    public List<Model> queryModelList(Model condition);

    public Model getModel(String code);

    public void putOn(Model data, String location, String orderNo,
            String updater, String remark);

    public void putOff(Model data, String updater, String remark);

}
