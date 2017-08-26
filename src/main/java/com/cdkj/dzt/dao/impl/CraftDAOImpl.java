package com.cdkj.dzt.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.cdkj.dzt.dao.ICraftDAO;
import com.cdkj.dzt.dao.base.support.AMybatisTemplate;
import com.cdkj.dzt.domain.Craft;

@Repository("craftDAOImpl")
public class CraftDAOImpl extends AMybatisTemplate implements ICraftDAO {

    @Override
    public int insert(Craft data) {
        return super.insert(NAMESPACE.concat("insert_craft"), data);
    }

    @Override
    public int delete(Craft data) {
        return super.delete(NAMESPACE.concat("delete_craft"), data);
    }

    @Override
    public Craft select(Craft condition) {
        return super.select(NAMESPACE.concat("select_craft"), condition,
            Craft.class);
    }

    @Override
    public Long selectTotalCount(Craft condition) {
        return super.selectTotalCount(NAMESPACE.concat("select_craft_count"),
            condition);
    }

    @Override
    public List<Craft> selectList(Craft condition) {
        return super.selectList(NAMESPACE.concat("select_craft"), condition,
            Craft.class);
    }

    @Override
    public List<Craft> selectList(Craft condition, int start, int count) {
        return super.selectList(NAMESPACE.concat("select_craft"), start, count,
            condition, Craft.class);
    }

    @Override
    public int update(Craft data) {
        return super.update(NAMESPACE.concat("update_craft"), data);
    }

    @Override
    public int putOn(Craft data) {
        return super.update(NAMESPACE.concat("update_putOn"), data);
    }

    @Override
    public int putOff(Craft data) {
        return super.update(NAMESPACE.concat("update_putOff"), data);
    }

    @Override
    public long selectGroupCount(Craft condition) {
        return super.selectTotalCount(NAMESPACE.concat("select_craft_group"),
            condition);
    }

}
