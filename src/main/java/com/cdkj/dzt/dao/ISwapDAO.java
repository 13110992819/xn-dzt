package com.cdkj.dzt.dao;

import com.cdkj.dzt.dao.base.IBaseDAO;
import com.cdkj.dzt.domain.Swap;

//dao层 
public interface ISwapDAO extends IBaseDAO<Swap> {
    String NAMESPACE = ISwapDAO.class.getName().concat(".");

    int update(Swap data);
}
