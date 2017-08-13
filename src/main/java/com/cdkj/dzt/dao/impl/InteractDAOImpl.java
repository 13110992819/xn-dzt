package com.cdkj.dzt.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.cdkj.dzt.dao.IInteractDAO;
import com.cdkj.dzt.dao.base.support.AMybatisTemplate;
import com.cdkj.dzt.domain.Interact;

@Repository("interactDAOImpl")
public class InteractDAOImpl extends AMybatisTemplate implements IInteractDAO {

    @Override
    public int insert(Interact data) {
        return super.insert(NAMESPACE.concat("insert_interact"), data);
    }

    @Override
    public int delete(Interact data) {
        return super.delete(NAMESPACE.concat("delete_interact"), data);
    }

    @Override
    public Interact select(Interact condition) {
        return super.select(NAMESPACE.concat("select_interact"), condition,
            Interact.class);
    }

    @Override
    public Long selectTotalCount(Interact condition) {
        return super.selectTotalCount(
            NAMESPACE.concat("select_interact_count"), condition);
    }

    @Override
    public List<Interact> selectList(Interact condition) {
        return super.selectList(NAMESPACE.concat("select_interact"), condition,
            Interact.class);
    }

    @Override
    public List<Interact> selectList(Interact condition, int start, int count) {
        return super.selectList(NAMESPACE.concat("select_interact"), start,
            count, condition, Interact.class);
    }

    @Override
    public int update(Interact data) {
        return super.update(NAMESPACE.concat("update_interact"), data);
    }

}
