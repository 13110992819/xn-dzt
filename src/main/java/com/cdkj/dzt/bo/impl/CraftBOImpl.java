package com.cdkj.dzt.bo.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.cdkj.dzt.bo.ICraftBO;
import com.cdkj.dzt.bo.base.PaginableBOImpl;
import com.cdkj.dzt.core.StringValidater;
import com.cdkj.dzt.dao.ICraftDAO;
import com.cdkj.dzt.domain.Craft;
import com.cdkj.dzt.enums.EStatus;
import com.cdkj.dzt.exception.BizException;

@Component
public class CraftBOImpl extends PaginableBOImpl<Craft> implements ICraftBO {

    @Autowired
    private ICraftDAO craftDAO;

    @Override
    public void saveCraft(Craft data) {
        craftDAO.insert(data);
    }

    @Override
    public void dropCraft(Craft data) {
        craftDAO.delete(data);
    }

    @Override
    public void refreshCraft(Craft data) {
        craftDAO.update(data);
    }

    @Override
    public List<Craft> queryCraftList(Craft condition) {
        return craftDAO.selectList(condition);
    }

    @Override
    public Craft getCraft(String code) {
        Craft data = null;
        if (StringUtils.isNotBlank(code)) {
            Craft condition = new Craft();
            condition.setCode(code);
            data = craftDAO.select(condition);
            if (data == null) {
                throw new BizException("xn0000", "不存在该工艺");
            }
        }
        return data;
    }

    @Override
    public Map<String, Craft> getMap() {
        Map<String, Craft> map = null;
        Craft condtion = new Craft();
        List<Craft> list = this.queryCraftList(condtion);
        if (CollectionUtils.isNotEmpty(list)) {
            map = new HashMap<String, Craft>();
            for (Craft ele : list) {
                map.put(ele.getCode(), ele);
            }
        }
        return map;
    }

    @Override
    public void putOn(Craft data, String location, String orderNo,
            String updater, String remark) {
        data.setStatus(EStatus.PUT_ON.getCode());
        data.setLocation(location);
        data.setOrderNo(StringValidater.toInteger(orderNo));
        data.setUpdater(updater);
        data.setUpdateDatetime(new Date());
        data.setRemark(remark);
        craftDAO.putOn(data);
    }

    @Override
    public void putOff(Craft data, String updater, String remark) {
        data.setStatus(EStatus.PUT_OFF.getCode());
        data.setUpdater(updater);
        data.setUpdateDatetime(new Date());
        data.setRemark(remark);
        craftDAO.putOff(data);
    }

    @Override
    public long getGroupTotalCount(String modelCode) {
        Craft condition = new Craft();
        condition.setModelCode(modelCode);
        return craftDAO.selectGroupCount(condition);
    }

    @Override
    public List<Craft> queryCraftList(String type) {
        Craft condition = new Craft();
        condition.setType(type);
        condition.setStatus(EStatus.PUT_ON.getCode());
        return craftDAO.selectList(condition);
    }

    @Override
    public List<Craft> queryCraftList(String type, String isDefault) {
        Craft condition = new Craft();
        condition.setOrder("order_no", "asc");
        condition.setType(type);
        condition.setIsDefault(isDefault);
        condition.setStatus(EStatus.PUT_ON.getCode());
        return craftDAO.selectList(condition);
    }

    @Override
    public List<Craft> queryCraftList(String isDefault, String modelSpecsCode,
            String modelCode) {
        Craft condition = new Craft();
        condition.setModelSpecsCode(modelSpecsCode);
        condition.setModelCode(modelCode);
        condition.setIsDefault(isDefault);
        condition.setStatus(EStatus.PUT_ON.getCode());
        return craftDAO.selectList(condition);
    }
}
