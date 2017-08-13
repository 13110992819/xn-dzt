package com.cdkj.dzt.ao;

import java.util.List;

import com.cdkj.dzt.bo.base.Paginable;
import com.cdkj.dzt.domain.Craft;

public interface ICraftAO {
    static final String DEFAULT_ORDER_COLUMN = "code";

    public void editModelSpecs(String code, String name, String pic,
            Integer orderNo, String remark);

    public Paginable<Craft> queryModelSpecsPage(int start, int limit,
            Craft condition);

    public List<Craft> queryModelSpecsList(Craft condition);

    public Craft getModelSpecs(String code);

}
