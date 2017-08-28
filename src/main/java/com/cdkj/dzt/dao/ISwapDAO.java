package com.cdkj.dzt.dao;

import java.util.List;

import com.cdkj.dzt.dao.base.IBaseDAO;
import com.cdkj.dzt.domain.Swap;

//dao层 
public interface ISwapDAO extends IBaseDAO<Swap> {
    String NAMESPACE = ISwapDAO.class.getName().concat(".");

    int update(Swap data);

    List<Swap> selectGroupList(Swap condition);

    List<Swap> selectGroupList(Swap condition, int start, int limit);

    List<Swap> selectBLYList(Swap condition);

    List<Swap> selectBLYList(Swap condition, int start, int limit);

    void updateNew(Swap swap);
}
