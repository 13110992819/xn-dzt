package com.cdkj.dzt.ao;

import java.util.List;

import org.springframework.stereotype.Component;

import com.cdkj.dzt.bo.base.Paginable;
import com.cdkj.dzt.domain.Specimen;

@Component
public interface ISpecimenAO {
    static final String DEFAULT_ORDER_COLUMN = "code";

    public String addSpecimen(String pic, String advPic, String description,
            String updater, String remark, String modelCode);

    public void dropSpecimen(String code);

    public void editSpecimen(String code, String pic, String advPic,
            String description, String updater, String remark, String modelCode);

    public void putOn(String code, String location, String orderNo,
            String updater, String remark);

    public void putOff(String code, String updater, String remark);

    public Paginable<Specimen> querySpecimenPage(int start, int limit,
            Specimen condition);

    public List<Specimen> querySpecimenList(Specimen condition);

    public Specimen getSpecimen(String code);

}
