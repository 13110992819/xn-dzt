package com.cdkj.dzt.bo.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.cdkj.dzt.bo.IModelSpecsBO;
import com.cdkj.dzt.bo.base.PaginableBOImpl;
import com.cdkj.dzt.dao.IModelSpecsDAO;
import com.cdkj.dzt.domain.ModelSpecs;
import com.cdkj.dzt.exception.BizException;

@Component
public class ModelSpecsBOImpl extends PaginableBOImpl<ModelSpecs> implements
        IModelSpecsBO {

    @Autowired
    private IModelSpecsDAO modelSpecsDAO;

    @Override
    public void refreshModelSpecs(String code, String name, String pic,
            Integer orderNo, String remark) {
        if (StringUtils.isNotBlank(code)) {
            ModelSpecs data = new ModelSpecs();
            data.setCode(code);
            data.setName(name);
            data.setPic(pic);
            data.setOrderNo(orderNo);
            data.setRemark(remark);
            modelSpecsDAO.update(data);
        }

    }

    @Override
    public List<ModelSpecs> queryModelSpecsList(ModelSpecs condition) {
        return modelSpecsDAO.selectList(condition);
    }

    @Override
    public ModelSpecs getModelSpecs(String code) {
        ModelSpecs data = null;
        if (StringUtils.isNotBlank(code)) {
            ModelSpecs condition = new ModelSpecs();
            condition.setCode(code);
            data = modelSpecsDAO.select(condition);
            if (data == null) {
                throw new BizException("xn0000", "不存在该产品规格");
            }
        }
        return data;
    }

    @Override
    public Map<String, ModelSpecs> getMap() {
        Map<String, ModelSpecs> map = null;
        ModelSpecs condtion = new ModelSpecs();
        List<ModelSpecs> list = this.queryModelSpecsList(condtion);
        if (CollectionUtils.isNotEmpty(list)) {
            map = new HashMap<String, ModelSpecs>();
            for (ModelSpecs ele : list) {
                map.put(ele.getParentCode(), ele);
            }
        }
        return map;
    }
}
