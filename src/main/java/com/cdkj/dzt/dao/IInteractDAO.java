package com.cdkj.dzt.dao;

import com.cdkj.dzt.dao.base.IBaseDAO;
import com.cdkj.dzt.domain.Interact;

//dao层 
public interface IInteractDAO extends IBaseDAO<Interact> {
    String NAMESPACE = IInteractDAO.class.getName().concat(".");

    int update(Interact data);
}
