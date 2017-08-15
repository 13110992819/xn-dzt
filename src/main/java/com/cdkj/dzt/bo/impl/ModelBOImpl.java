package com.cdkj.dzt.bo.impl;

import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.cdkj.dzt.bo.IModelBO;
import com.cdkj.dzt.bo.base.PaginableBOImpl;
import com.cdkj.dzt.core.StringValidater;
import com.cdkj.dzt.dao.IModelDAO;
import com.cdkj.dzt.domain.Model;
import com.cdkj.dzt.enums.EStatus;
import com.cdkj.dzt.exception.BizException;

@Component
public class ModelBOImpl extends PaginableBOImpl<Model> implements IModelBO {

    @Autowired
    private IModelDAO modelDAO;

    @Override
    public void saveModel(Model data) {
        modelDAO.insert(data);
    }

    @Override
    public void dropModel(Model data) {
        modelDAO.delete(data);
    }

    @Override
    public void refreshModel(Model data) {
        modelDAO.update(data);
    }

    @Override
    public List<Model> queryModelList(Model condition) {
        return modelDAO.selectList(condition);
    }

    @Override
    public Model getModel(String code) {
        Model data = null;
        if (StringUtils.isNotBlank(code)) {
            Model condition = new Model();
            condition.setCode(code);
            data = modelDAO.select(condition);
            if (data == null) {
                throw new BizException("xn0000", "产品不存在");
            }
        }
        return data;
    }

    @Override
    public void putOn(Model data, String location, String orderNo,
            String updater, String remark) {
        data.setLocation(location);
        data.setOrderNo(StringValidater.toInteger(orderNo));
        data.setStatus(EStatus.PUT_ON.getCode());
        data.setUpdater(updater);
        data.setUpdateDatetime(new Date());
        data.setRemark(remark);
        modelDAO.putOn(data);
    }

    @Override
    public void putOff(Model data, String updater, String remark) {
        data.setStatus(EStatus.PUT_OFF.getCode());
        data.setUpdater(updater);
        data.setUpdateDatetime(new Date());
        data.setRemark(remark);
        modelDAO.putOff(data);
    }

}
