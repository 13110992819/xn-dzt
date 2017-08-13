package com.cdkj.dzt.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.cdkj.dzt.dao.ISwapDAO;
import com.cdkj.dzt.dao.base.support.AMybatisTemplate;
import com.cdkj.dzt.domain.Swap;

@Repository("swapDAOImpl")
public class SwapDAOImpl extends AMybatisTemplate implements ISwapDAO {

    @Override
    public int insert(Swap data) {
        return super.insert(NAMESPACE.concat("insert_swap"), data);
    }

    @Override
    public int delete(Swap data) {
        return super.delete(NAMESPACE.concat("delete_swap"), data);
    }

    @Override
    public Swap select(Swap condition) {
        return super.select(NAMESPACE.concat("select_swap"), condition,
            Swap.class);
    }

    @Override
    public Long selectTotalCount(Swap condition) {
        return super.selectTotalCount(NAMESPACE.concat("select_swap_count"),
            condition);
    }

    @Override
    public List<Swap> selectList(Swap condition) {
        return super.selectList(NAMESPACE.concat("select_swap"), condition,
            Swap.class);
    }

    @Override
    public List<Swap> selectList(Swap condition, int start, int count) {
        return super.selectList(NAMESPACE.concat("select_swap"), start, count,
            condition, Swap.class);
    }

    @Override
    public int update(Swap data) {
        return super.update(NAMESPACE.concat("update_swap"), data);
    }

}
