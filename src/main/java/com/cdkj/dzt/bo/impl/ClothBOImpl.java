package com.cdkj.dzt.bo.impl;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.cdkj.dzt.bo.IClothBO;
import com.cdkj.dzt.bo.base.PaginableBOImpl;
import com.cdkj.dzt.core.OrderNoGenerater;
import com.cdkj.dzt.dao.IClothDAO;
import com.cdkj.dzt.domain.Cloth;
import com.cdkj.dzt.enums.EGeneratePrefix;
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
    public String saveCloth(Cloth data) {
        String code = null;
        if (data != null) {
            code = OrderNoGenerater.generateM(EGeneratePrefix.CLOTH.getCode());
            data.setCode(code);
            clothDAO.insert(data);
        }
        return code;
    }

    @Override
    public int removeCloth(String code) {
        int count = 0;
        if (StringUtils.isNotBlank(code)) {
            Cloth data = new Cloth();
            data.setCode(code);
            count = clothDAO.delete(data);
        }
        return count;
    }

    @Override
    public int refreshCloth(Cloth data) {
        int count = 0;
        if (StringUtils.isNotBlank(data.getCode())) {
            count = clothDAO.update(data);
        }
        return count;
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
}
