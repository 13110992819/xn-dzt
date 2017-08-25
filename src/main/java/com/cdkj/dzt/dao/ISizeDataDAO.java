package com.cdkj.dzt.dao;

import com.cdkj.dzt.dao.base.IBaseDAO;
import com.cdkj.dzt.domain.SizeData;

//dao层 
public interface ISizeDataDAO extends IBaseDAO<SizeData> {
    String NAMESPACE = ISizeDataDAO.class.getName().concat(".");

    int update(SizeData data);

    int deleteByUserId(SizeData data);

    void deleteByKey(SizeData data);
}
