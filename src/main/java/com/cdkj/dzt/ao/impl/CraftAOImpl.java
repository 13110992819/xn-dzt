package com.cdkj.dzt.ao.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cdkj.dzt.ao.ICraftAO;
import com.cdkj.dzt.bo.ICraftBO;
import com.cdkj.dzt.bo.base.Paginable;
import com.cdkj.dzt.domain.Craft;

@Service
public class CraftAOImpl implements ICraftAO {

    @Autowired
    private ICraftBO modelSpecsBO;

    @Override
    public void editModelSpecs(String code, String name, String pic,
            Integer orderNo, String remark) {
        modelSpecsBO.refreshModelSpecs(code, name, pic, orderNo, remark);
    }

    @Override
    public Paginable<Craft> queryModelSpecsPage(int start, int limit,
            Craft condition) {
        return modelSpecsBO.getPaginable(start, limit, condition);
    }

    @Override
    public List<Craft> queryModelSpecsList(Craft condition) {
        return modelSpecsBO.queryModelSpecsList(condition);
    }

    @Override
    public Craft getModelSpecs(String code) {
        return modelSpecsBO.getModelSpecs(code);
    }
}
