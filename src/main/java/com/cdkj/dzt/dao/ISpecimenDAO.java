package com.cdkj.dzt.dao;

import com.cdkj.dzt.dao.base.IBaseDAO;
import com.cdkj.dzt.domain.Specimen;

//dao层 
public interface ISpecimenDAO extends IBaseDAO<Specimen> {
    String NAMESPACE = ISpecimenDAO.class.getName().concat(".");

    int update(Specimen data);

    int putOn(Specimen data);

    int putOff(Specimen data);
}
