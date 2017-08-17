package com.cdkj.dzt.bo.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.cdkj.dzt.bo.IClothBO;
import com.cdkj.dzt.bo.base.PaginableBOImpl;
import com.cdkj.dzt.core.StringValidater;
import com.cdkj.dzt.dao.IClothDAO;
import com.cdkj.dzt.domain.Cloth;
import com.cdkj.dzt.enums.EStatus;
import com.cdkj.dzt.exception.BizException;

@Component
public class ClothBOImpl extends PaginableBOImpl<Cloth> implements IClothBO {

    @Autowired
    private IClothDAO clothDAO;

    @Override
    public boolean isClothExist(String code) {
        Cloth condition = new Cloth();
        condition.setCode(code);
        if (clothDAO.selectTotalCount(condition) > 0) {
            return true;
        }
        return false;
    }

    @Override
    public void saveCloth(Cloth data) {
        clothDAO.insert(data);
    }

    @Override
    public void removeCloth(Cloth data) {
        clothDAO.delete(data);
    }

    @Override
    public void refreshCloth(Cloth data) {
        clothDAO.update(data);
    }

    @Override
    public void putOn(Cloth data, String location, String orderNo,
            String updater, String remark) {
        data.setStatus(EStatus.PUT_ON.getCode());
        data.setLocation(location);
        data.setOrderNo(StringValidater.toInteger(orderNo));
        data.setUpdater(updater);
        data.setUpdateDatetime(new Date());
        data.setRemark(remark);
        clothDAO.putOn(data);
    }

    @Override
    public void putOff(Cloth data, String updater, String remark) {
        data.setStatus(EStatus.PUT_OFF.getCode());
        data.setUpdater(updater);
        data.setUpdateDatetime(new Date());
        data.setRemark(remark);
        clothDAO.putOff(data);
    }

    @Override
    public List<Cloth> queryClothList(Cloth condition) {
        return clothDAO.selectList(condition);
    }

    @Override
    public Cloth getCloth(String code) {
        Cloth data = null;
        if (StringUtils.isNotBlank(code)) {
            Cloth condition = new Cloth();
            condition.setCode(code);
            data = clothDAO.select(condition);
            if (data == null) {
                throw new BizException("xn0000", "编号不存在");
            }
        }
        return data;
    }

    @Override
    public Map<String, Cloth> getMap() {
        Map<String, Cloth> map = null;
        Cloth condition = new Cloth();
        List<Cloth> list = this.queryClothList(condition);
        if (CollectionUtils.isNotEmpty(list)) {
            map = new HashMap<String, Cloth>();
            for (Cloth ele : list) {
                map.put(ele.getCode(), ele);
            }
        }
        return map;
    }

}
