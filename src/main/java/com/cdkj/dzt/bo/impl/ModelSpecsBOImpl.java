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
    public boolean isModelSpecsExist(String code) {
        ModelSpecs condition = new ModelSpecs();
        condition.setCode(code);
        if (modelSpecsDAO.selectTotalCount(condition) > 0) {
            return true;
        }
        return false;
    }

    @Override
    public int refreshModelSpecs(String code, String name, String parentCode,
            String type, String pic, Integer orderNo, String remark,
            String modelCode) {
        int count = 0;
        if (StringUtils.isNotBlank(code)) {
            ModelSpecs data = new ModelSpecs();
            data.setCode(code);
            data.setName(name);
            data.setParentCode(parentCode);
            data.setType(type);
            data.setPic(pic);
            data.setOrderNo(orderNo);
            data.setRemark(remark);
            data.setModelCode(modelCode);
            count = modelSpecsDAO.update(data);
        }
        return count;
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
                map.put(ele.getCode(), ele);
            }
        }
        return map;
    }
}
