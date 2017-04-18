package com.cdkj.dzt.bo.impl;

import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.cdkj.dzt.bo.IModelBO;
import com.cdkj.dzt.bo.base.PaginableBOImpl;
import com.cdkj.dzt.dao.IModelDAO;
import com.cdkj.dzt.domain.Model;
import com.cdkj.dzt.exception.BizException;

@Component
public class ModelBOImpl extends PaginableBOImpl<Model> implements IModelBO {

    @Autowired
    private IModelDAO modelDAO;

    @Override
    public void refreshModel(String code, String name, String pic, Long price,
            String updater, String remark) {
        if (StringUtils.isNotBlank(code)) {
            Model data = new Model();
            data.setCode(code);
            data.setName(name);
            data.setPic(pic);
            data.setPrice(price);

            data.setUpdater(updater);
            data.setUpdateDatetime(new Date());
            data.setRemark(remark);
            modelDAO.update(data);
        }
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
                throw new BizException("xn0000", "�� ��Ų�����");
            }
        }
        return data;
    }
}
