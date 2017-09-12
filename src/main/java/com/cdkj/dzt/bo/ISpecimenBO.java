package com.cdkj.dzt.bo;

import java.util.List;

import com.cdkj.dzt.bo.base.IPaginableBO;
import com.cdkj.dzt.domain.Specimen;

public interface ISpecimenBO extends IPaginableBO<Specimen> {

    public boolean isSpecimenExist(String code);

    public void saveSpecimen(Specimen data);

    public void removeSpecimen(Specimen data);

    public void refreshSpecimen(Specimen data);

    public List<Specimen> querySpecimenList(Specimen condition);

    public Specimen getSpecimen(String code);

    public void putOn(Specimen data, String location, Integer orderNo,
            String updater, String remark);

    public void putOff(Specimen data, String updater, String remark);

}
