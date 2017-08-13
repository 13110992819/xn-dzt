package com.cdkj.dzt.bo;

import java.util.List;
import java.util.Map;

import com.cdkj.dzt.bo.base.IPaginableBO;
import com.cdkj.dzt.domain.Craft;

public interface ICraftBO extends IPaginableBO<Craft> {

    public void refreshModelSpecs(String code, String name, String pic,
            Integer orderNo, String remark);

    public List<Craft> queryModelSpecsList(Craft condition);

    public Craft getModelSpecs(String code);

    public Map<String, Craft> getMap();

}
