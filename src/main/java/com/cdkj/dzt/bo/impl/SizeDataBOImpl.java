package com.cdkj.dzt.bo.impl;

import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.cdkj.dzt.bo.ISizeDataBO;
import com.cdkj.dzt.bo.base.PaginableBOImpl;
import com.cdkj.dzt.dao.ISizeDataDAO;
import com.cdkj.dzt.domain.SizeData;
import com.cdkj.dzt.enums.EMeasureKey;
import com.cdkj.dzt.exception.BizException;

@Component
public class SizeDataBOImpl extends PaginableBOImpl<SizeData> implements
        ISizeDataBO {

    @Autowired
    private ISizeDataDAO sizeDataDAO;

    @Override
    public boolean isSizeDataExist(String code) {
        SizeData condition = new SizeData();
        condition.setId(code);
        if (sizeDataDAO.selectTotalCount(condition) > 0) {
            return true;
        }
        return false;
    }

    @Override
    public void inputInforValue(String applyUser, Map<String, String> map) {
        this.saveSG(applyUser, map.get(EMeasureKey.SG.getCode()));
        this.saveTZ(applyUser, map.get(EMeasureKey.TZ.getCode()));
    }

    private void saveTZ(String applyUser, String value) {
        SizeData data = new SizeData();
        data.setUserId(applyUser);
        data.setKey(EMeasureKey.TZ.getCode());
        data.setValue(value);
        sizeDataDAO.insert(data);
    }

    private void saveSG(String applyUser, String value) {
        SizeData data = new SizeData();
        data.setUserId(applyUser);
        data.setKey(EMeasureKey.SG.getCode());
        data.setValue(value);
        sizeDataDAO.insert(data);
    }

    @Override
    public int removeSizeData(String code) {
        int count = 0;
        if (StringUtils.isNotBlank(code)) {
            SizeData data = new SizeData();
            data.setId(code);
            count = sizeDataDAO.delete(data);
        }
        return count;
    }

    @Override
    public int refreshSizeData(SizeData data) {
        int count = 0;
        if (StringUtils.isNotBlank(data.getId())) {
            count = sizeDataDAO.update(data);
        }
        return count;
    }

    @Override
    public List<SizeData> querySizeDataList(SizeData condition) {
        return sizeDataDAO.selectList(condition);
    }

    @Override
    public SizeData getSizeData(String code) {
        SizeData data = null;
        if (StringUtils.isNotBlank(code)) {
            SizeData condition = new SizeData();
            condition.setId(code);
            data = sizeDataDAO.select(condition);
            if (data == null) {
                throw new BizException("xn0000", "编号不存在");
            }
        }
        return data;
    }
}
