package com.cdkj.dzt.dao;

import com.cdkj.dzt.dao.base.IBaseDAO;
import com.cdkj.dzt.domain.Craft;

//dao层 
public interface ICraftDAO extends IBaseDAO<Craft> {
    String NAMESPACE = ICraftDAO.class.getName().concat(".");

    public int update(Craft data);
}
