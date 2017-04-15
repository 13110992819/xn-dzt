package com.cdkj.dzt.ao;

import java.util.List;

import org.springframework.stereotype.Component;

import com.cdkj.dzt.bo.base.Paginable;
import com.cdkj.dzt.domain.Model;

@Component
public interface IModelAO {
    static final String DEFAULT_ORDER_COLUMN = "code";

    public String addModel(Model data);

    public int dropModel(String code);

    public int editModel(Model data);

    public Paginable<Model> queryModelPage(int start, int limit, Model condition);

    public List<Model> queryModelList(Model condition);

    public Model getModel(String code);

}
