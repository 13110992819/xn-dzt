package com.cdkj.dzt.dao;

import com.cdkj.dzt.dao.base.IBaseDAO;
import com.cdkj.dzt.domain.Cloth;

//dao层 
public interface IClothDAO extends IBaseDAO<Cloth> {
    String NAMESPACE = IClothDAO.class.getName().concat(".");

    int update(Cloth data);

    int putOn(Cloth data);

    int putOff(Cloth data);
}
