package com.cdkj.dzt.ao.impl;

import java.util.Date;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cdkj.dzt.ao.ISpecimenAO;
import com.cdkj.dzt.bo.IInteractBO;
import com.cdkj.dzt.bo.ISpecimenBO;
import com.cdkj.dzt.bo.base.Paginable;
import com.cdkj.dzt.core.OrderNoGenerater;
import com.cdkj.dzt.core.StringValidater;
import com.cdkj.dzt.domain.Specimen;
import com.cdkj.dzt.enums.EBoolean;
import com.cdkj.dzt.enums.EGeneratePrefix;
import com.cdkj.dzt.enums.EInteractCategory;
import com.cdkj.dzt.enums.EInteractType;
import com.cdkj.dzt.enums.EStatus;
import com.cdkj.dzt.exception.BizException;

@Service
public class SpecimenAOImpl implements ISpecimenAO {

    @Autowired
    private ISpecimenBO specimenBO;

    @Autowired
    private IInteractBO interactBO;

    @Override
    public String addSpecimen(String pic, String advPic, String description,
            String updater, String remark, String modelCode) {
        Specimen data = new Specimen();
        String code = OrderNoGenerater.generateM(EGeneratePrefix.SPECIMEN
            .getCode());
        data.setCode(code);
        data.setPic(pic);
        data.setAdvPic(advPic);
        data.setDescription(description);
        data.setStatus(EStatus.DRAFT.getCode());
        data.setUpdater(updater);
        data.setUpdateDatetime(new Date());
        data.setModelCode(modelCode);
        specimenBO.saveSpecimen(data);
        return code;
    }

    @Override
    public void editSpecimen(String code, String pic, String advPic,
            String description, String updater, String remark, String modelCode) {
        Specimen data = specimenBO.getSpecimen(code);
        if (EStatus.PUT_ON.getCode().equals(data.getStatus())) {
            throw new BizException("xn0000", "样品上架中,不可修改");
        }
        data.setPic(pic);
        data.setAdvPic(advPic);
        data.setDescription(description);
        data.setUpdater(updater);
        data.setUpdateDatetime(new Date());
        data.setRemark(remark);
        data.setModelCode(modelCode);
        specimenBO.refreshSpecimen(data);
    }

    @Override
    public void dropSpecimen(String code) {
        Specimen data = specimenBO.getSpecimen(code);
        if (EStatus.PUT_ON.getCode().equals(data.getStatus())) {
            throw new BizException("xn0000", "样品上架中,不可删除");
        }
        specimenBO.removeSpecimen(data);
    }

    @Override
    public Paginable<Specimen> querySpecimenPage(int start, int limit,
            Specimen condition, String userId) {
        Paginable<Specimen> page = specimenBO.getPaginable(start, limit,
            condition);
        List<Specimen> list = page.getList();
        for (Specimen specimen : list) {
            this.fullSpecimen(specimen, userId);
        }
        return page;
    }

    @Override
    public List<Specimen> querySpecimenList(Specimen condition, String userId) {
        List<Specimen> list = specimenBO.querySpecimenList(condition);
        for (Specimen specimen : list) {
            this.fullSpecimen(specimen, userId);
        }
        return list;
    }

    private void fullSpecimen(Specimen specimen, String userId) {
        String isSC = EBoolean.NO.getCode();
        if (StringUtils.isNotBlank(userId)) {
            Long num = interactBO.getTotalCount(EInteractCategory.SPECIMEN,
                EInteractType.SC, specimen.getCode(), userId);
            if (num > 0) {
                isSC = EBoolean.YES.getCode();
            }
        }
        specimen.setIsSC(isSC);
    }

    @Override
    public Specimen getSpecimen(String code, String userId) {
        Specimen specimen = specimenBO.getSpecimen(code);
        this.fullSpecimen(specimen, userId);
        return specimen;
    }

    @Override
    public void putOn(String code, String location, String orderNo,
            String updater, String remark) {
        Specimen data = specimenBO.getSpecimen(code);
        if (EStatus.PUT_ON.getCode().equals(data.getStatus())) {
            throw new BizException("xn0000", "样品已上架");
        }
        specimenBO.putOn(data, location, StringValidater.toInteger(orderNo),
            updater, remark);
    }

    @Override
    public void putOff(String code, String updater, String remark) {
        Specimen data = specimenBO.getSpecimen(code);
        if (!EStatus.PUT_ON.getCode().equals(data.getStatus())) {
            throw new BizException("xn0000", "样品未上架");
        }
        specimenBO.putOff(data, updater, remark);
    }
}
