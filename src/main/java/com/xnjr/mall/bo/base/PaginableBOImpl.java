package com.xnjr.mall.bo.base;

import java.util.List;

import com.xnjr.mall.dao.base.ABaseDO;
import com.xnjr.mall.dao.base.IBaseDAO;

/**
 * 
 * @author joe.chen
 * 
 */
public abstract class PaginableBOImpl<T extends ABaseDO> implements
        IPaginableBO<T> {

    private IBaseDAO<T> paginableDAO;

    @Override
    public long getTotalCount(T condition) {
        return paginableDAO.selectTotalCount(condition);
    }

    @Override
    public Paginable<T> getPaginable(int start, T condition) {

        return getPaginable(start, Paginable.DEFAULT_PAGE_SIZE, condition);
    }

    protected T prepare(T condition) {
        return condition;
    }

    protected List<T> after(List<T> data) {

        return data;
    }

    public IBaseDAO<T> getPaginableDAO() {
        return paginableDAO;
    }

    public void setPaginableDAO(IBaseDAO<T> paginableDAO) {
        this.paginableDAO = paginableDAO;
    }

    /*
     * public void setPaginableDAO(IBaseDAO<T> paginableDAO) { this.paginableDAO
     * = paginableDAO; }
     */

}
