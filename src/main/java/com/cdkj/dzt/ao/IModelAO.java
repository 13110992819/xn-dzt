package com.cdkj.dzt.ao;

import java.util.List;

import com.cdkj.dzt.bo.base.Paginable;
import com.cdkj.dzt.domain.Model;

public interface IModelAO {
    static final String DEFAULT_ORDER_COLUMN = "code";

    public void editModel(String code, String name, String pic, Long price,
            String updater, String remark);

    public Paginable<Model> queryModelPage(int start, int limit, Model condition);

    public List<Model> queryModelList(Model condition);

    public Model getModel(String code);

}
