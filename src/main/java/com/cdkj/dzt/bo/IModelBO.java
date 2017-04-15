package com.cdkj.dzt.bo;

import java.util.List;

import com.cdkj.dzt.bo.base.IPaginableBO;
import com.cdkj.dzt.domain.Model;

public interface IModelBO extends IPaginableBO<Model> {

    public boolean isModelExist(String code);

    public void refreshModel(String code, String name, String pic, Long price,
            String updater, String remark);

    public List<Model> queryModelList(Model condition);

    public Model getModel(String code);

}
