package com.cdkj.dzt.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.cdkj.dzt.dao.IModelSpecsDAO;
import com.cdkj.dzt.dao.base.support.AMybatisTemplate;
import com.cdkj.dzt.domain.ModelSpecs;

@Repository("modelSpecsDAOImpl")
public class ModelSpecsDAOImpl extends AMybatisTemplate implements
        IModelSpecsDAO {

    @Override
    public int insert(ModelSpecs data) {
        return super.insert(NAMESPACE.concat("insert_modelSpecs"), data);
    }

    @Override
    public int delete(ModelSpecs data) {
        return super.delete(NAMESPACE.concat("delete_modelSpecs"), data);
    }

    @Override
    public ModelSpecs select(ModelSpecs condition) {
        return super.select(NAMESPACE.concat("select_modelSpecs"), condition,
            ModelSpecs.class);
    }

    @Override
    public Long selectTotalCount(ModelSpecs condition) {
        return super.selectTotalCount(
            NAMESPACE.concat("select_modelSpecs_count"), condition);
    }

    @Override
    public List<ModelSpecs> selectList(ModelSpecs condition) {
        return super.selectList(NAMESPACE.concat("select_modelSpecs"),
            condition, ModelSpecs.class);
    }

    @Override
    public List<ModelSpecs> selectList(ModelSpecs condition, int start,
            int count) {
        return super.selectList(NAMESPACE.concat("select_modelSpecs"), start,
            count, condition, ModelSpecs.class);
    }

    @Override
    public int update(ModelSpecs data) {
        return super.update(NAMESPACE.concat("update_modelSpecs"), data);
    }

}
