package com.cdkj.dzt.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.cdkj.dzt.dao.ISpecimenDAO;
import com.cdkj.dzt.dao.base.support.AMybatisTemplate;
import com.cdkj.dzt.domain.Specimen;

@Repository("specimenDAOImpl")
public class SpecimenDAOImpl extends AMybatisTemplate implements ISpecimenDAO {

    @Override
    public int insert(Specimen data) {
        return super.insert(NAMESPACE.concat("insert_specimen"), data);
    }

    @Override
    public int delete(Specimen data) {
        return super.delete(NAMESPACE.concat("delete_specimen"), data);
    }

    @Override
    public Specimen select(Specimen condition) {
        return super.select(NAMESPACE.concat("select_specimen"), condition,
            Specimen.class);
    }

    @Override
    public Long selectTotalCount(Specimen condition) {
        return super.selectTotalCount(
            NAMESPACE.concat("select_specimen_count"), condition);
    }

    @Override
    public List<Specimen> selectList(Specimen condition) {
        return super.selectList(NAMESPACE.concat("select_specimen"), condition,
            Specimen.class);
    }

    @Override
    public List<Specimen> selectList(Specimen condition, int start, int count) {
        return super.selectList(NAMESPACE.concat("select_specimen"), start,
            count, condition, Specimen.class);
    }

    @Override
    public int update(Specimen data) {
        return super.update(NAMESPACE.concat("update_specimen"), data);
    }

    @Override
    public int putOn(Specimen data) {
        return super.update(NAMESPACE.concat("update_putOn"), data);
    }

    @Override
    public int putOff(Specimen data) {
        return super.update(NAMESPACE.concat("update_putOff"), data);
    }

}
