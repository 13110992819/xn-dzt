package com.cdkj.dzt.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.cdkj.dzt.dao.ICraftDAO;
import com.cdkj.dzt.dao.base.support.AMybatisTemplate;
import com.cdkj.dzt.domain.Craft;

@Repository("modelSpecsDAOImpl")
public class CraftDAOImpl extends AMybatisTemplate implements
        ICraftDAO {

    @Override
    public int insert(Craft data) {
        return 0;// super.insert(NAMESPACE.concat("insert_modelSpecs"), data);
    }

    @Override
    public int delete(Craft data) {
        return 0;// super.delete(NAMESPACE.concat("delete_modelSpecs"), data);
    }

    @Override
    public Craft select(Craft condition) {
        return super.select(NAMESPACE.concat("select_modelSpecs"), condition,
            Craft.class);
    }

    @Override
    public Long selectTotalCount(Craft condition) {
        return super.selectTotalCount(
            NAMESPACE.concat("select_modelSpecs_count"), condition);
    }

    @Override
    public List<Craft> selectList(Craft condition) {
        return super.selectList(NAMESPACE.concat("select_modelSpecs"),
            condition, Craft.class);
    }

    @Override
    public List<Craft> selectList(Craft condition, int start,
            int count) {
        return super.selectList(NAMESPACE.concat("select_modelSpecs"), start,
            count, condition, Craft.class);
    }

    @Override
    public int update(Craft data) {
        return super.update(NAMESPACE.concat("update_modelSpecs"), data);
    }

}
