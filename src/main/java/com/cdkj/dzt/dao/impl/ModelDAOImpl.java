package com.cdkj.dzt.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.cdkj.dzt.dao.IModelDAO;
import com.cdkj.dzt.dao.base.support.AMybatisTemplate;
import com.cdkj.dzt.domain.Model;

@Repository("modelDAOImpl")
public class ModelDAOImpl extends AMybatisTemplate implements IModelDAO {

    @Override
    public int insert(Model data) {
        return 0;// super.insert(NAMESPACE.concat("insert_model"), data);
    }

    @Override
    public int delete(Model data) {
        return 0;// super.delete(NAMESPACE.concat("delete_model"), data);
    }

    @Override
    public Model select(Model condition) {
        return super.select(NAMESPACE.concat("select_model"), condition,
            Model.class);
    }

    @Override
    public Long selectTotalCount(Model condition) {
        return super.selectTotalCount(NAMESPACE.concat("select_model_count"),
            condition);
    }

    @Override
    public List<Model> selectList(Model condition) {
        return super.selectList(NAMESPACE.concat("select_model"), condition,
            Model.class);
    }

    @Override
    public List<Model> selectList(Model condition, int start, int count) {
        return super.selectList(NAMESPACE.concat("select_model"), start, count,
            condition, Model.class);
    }

    @Override
    public int update(Model data) {
        return super.update(NAMESPACE.concat("update_model"), data);
    }

}
