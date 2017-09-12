package com.cdkj.dzt.bo.impl;

import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.cdkj.dzt.bo.ISpecimenBO;
import com.cdkj.dzt.bo.base.PaginableBOImpl;
import com.cdkj.dzt.dao.ISpecimenDAO;
import com.cdkj.dzt.domain.Specimen;
import com.cdkj.dzt.enums.EStatus;
import com.cdkj.dzt.exception.BizException;

@Component
public class SpecimenBOImpl extends PaginableBOImpl<Specimen> implements
        ISpecimenBO {

    @Autowired
    private ISpecimenDAO specimenDAO;

    @Override
    public boolean isSpecimenExist(String code) {
        Specimen condition = new Specimen();
        condition.setCode(code);
        if (specimenDAO.selectTotalCount(condition) > 0) {
            return true;
        }
        return false;
    }

    @Override
    public void saveSpecimen(Specimen data) {
        specimenDAO.insert(data);
    }

    @Override
    public void removeSpecimen(Specimen data) {
        specimenDAO.delete(data);
    }

    @Override
    public void refreshSpecimen(Specimen data) {
        specimenDAO.update(data);
    }

    @Override
    public List<Specimen> querySpecimenList(Specimen condition) {
        return specimenDAO.selectList(condition);
    }

    @Override
    public Specimen getSpecimen(String code) {
        Specimen data = null;
        if (StringUtils.isNotBlank(code)) {
            Specimen condition = new Specimen();
            condition.setCode(code);
            data = specimenDAO.select(condition);
            if (data == null) {
                throw new BizException("xn0000", "编号不存在");
            }
        }
        return data;
    }

    @Override
    public void putOn(Specimen data, String location, Integer orderNo,
            String updater, String remark) {
        data.setLocation(location);
        data.setOrderNo(orderNo);
        data.setStatus(EStatus.PUT_ON.getCode());
        data.setUpdater(updater);
        data.setUpdateDatetime(new Date());
        data.setRemark(remark);
        specimenDAO.putOn(data);
    }

    @Override
    public void putOff(Specimen data, String updater, String remark) {
        data.setStatus(EStatus.PUT_OFF.getCode());
        data.setUpdater(updater);
        data.setUpdateDatetime(new Date());
        data.setRemark(remark);
        specimenDAO.putOff(data);
    }
}
