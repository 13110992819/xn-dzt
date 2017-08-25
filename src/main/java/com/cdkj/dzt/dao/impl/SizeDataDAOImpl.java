package com.cdkj.dzt.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.cdkj.dzt.dao.ISizeDataDAO;
import com.cdkj.dzt.dao.base.support.AMybatisTemplate;
import com.cdkj.dzt.domain.SizeData;

@Repository("sizeDataDAOImpl")
public class SizeDataDAOImpl extends AMybatisTemplate implements ISizeDataDAO {

    @Override
    public int insert(SizeData data) {
        return super.insert(NAMESPACE.concat("insert_sizeData"), data);
    }

    @Override
    public int delete(SizeData data) {
        return super.delete(NAMESPACE.concat("delete_sizeData"), data);
    }

    @Override
    public SizeData select(SizeData condition) {
        return super.select(NAMESPACE.concat("select_sizeData"), condition,
            SizeData.class);
    }

    @Override
    public Long selectTotalCount(SizeData condition) {
        return super.selectTotalCount(
            NAMESPACE.concat("select_sizeData_count"), condition);
    }

    @Override
    public List<SizeData> selectList(SizeData condition) {
        return super.selectList(NAMESPACE.concat("select_sizeData"), condition,
            SizeData.class);
    }

    @Override
    public List<SizeData> selectList(SizeData condition, int start, int count) {
        return super.selectList(NAMESPACE.concat("select_sizeData"), start,
            count, condition, SizeData.class);
    }

    @Override
    public int update(SizeData data) {
        return super.update(NAMESPACE.concat("update_sizeData"), data);
    }

    @Override
    public int deleteByUserId(SizeData data) {
        return super.delete(NAMESPACE.concat("delete_byUserId"), data);
    }

    @Override
    public void deleteByKey(SizeData data) {
        super.delete(NAMESPACE.concat("delete_byType"), data);
    }

}
