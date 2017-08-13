package com.cdkj.dzt.bo.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.cdkj.dzt.bo.ICraftBO;
import com.cdkj.dzt.bo.base.PaginableBOImpl;
import com.cdkj.dzt.dao.ICraftDAO;
import com.cdkj.dzt.domain.Craft;
import com.cdkj.dzt.exception.BizException;

@Component
public class CraftBOImpl extends PaginableBOImpl<Craft> implements
        ICraftBO {

    @Autowired
    private ICraftDAO modelSpecsDAO;

    @Override
    public void refreshModelSpecs(String code, String name, String pic,
            Integer orderNo, String remark) {
        if (StringUtils.isNotBlank(code)) {
            Craft data = new Craft();
            data.setCode(code);
            data.setName(name);
            data.setPic(pic);
            data.setOrderNo(orderNo);
            data.setRemark(remark);
            modelSpecsDAO.update(data);
        }

    }

    @Override
    public List<Craft> queryModelSpecsList(Craft condition) {
        return modelSpecsDAO.selectList(condition);
    }

    @Override
    public Craft getModelSpecs(String code) {
        Craft data = null;
        if (StringUtils.isNotBlank(code)) {
            Craft condition = new Craft();
            condition.setCode(code);
            data = modelSpecsDAO.select(condition);
            if (data == null) {
                throw new BizException("xn0000", "不存在该产品规格");
            }
        }
        return data;
    }

    @Override
    public Map<String, Craft> getMap() {
        Map<String, Craft> map = null;
        Craft condtion = new Craft();
        List<Craft> list = this.queryModelSpecsList(condtion);
        if (CollectionUtils.isNotEmpty(list)) {
            map = new HashMap<String, Craft>();
            for (Craft ele : list) {
                map.put(ele.getCode(), ele);
            }
        }
        return map;
    }
}
