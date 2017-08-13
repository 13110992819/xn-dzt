package com.cdkj.dzt.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.cdkj.dzt.dao.IClothDAO;
import com.cdkj.dzt.dao.base.support.AMybatisTemplate;
import com.cdkj.dzt.domain.Cloth;

@Repository("clothDAOImpl")
public class ClothDAOImpl extends AMybatisTemplate implements IClothDAO {

    @Override
    public int insert(Cloth data) {
        return super.insert(NAMESPACE.concat("insert_cloth"), data);
    }

    @Override
    public int delete(Cloth data) {
        return super.delete(NAMESPACE.concat("delete_cloth"), data);
    }

    @Override
    public Cloth select(Cloth condition) {
        return super.select(NAMESPACE.concat("select_cloth"), condition,
            Cloth.class);
    }

    @Override
    public Long selectTotalCount(Cloth condition) {
        return super.selectTotalCount(NAMESPACE.concat("select_cloth_count"),
            condition);
    }

    @Override
    public List<Cloth> selectList(Cloth condition) {
        return super.selectList(NAMESPACE.concat("select_cloth"), condition,
            Cloth.class);
    }

    @Override
    public List<Cloth> selectList(Cloth condition, int start, int count) {
        return super.selectList(NAMESPACE.concat("select_cloth"), start, count,
            condition, Cloth.class);
    }

    @Override
    public int update(Cloth data) {
        return super.update(NAMESPACE.concat("update_cloth"), data);
    }

}
