package com.cdkj.dzt.ao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.cdkj.dzt.bo.base.Paginable;
import com.cdkj.dzt.domain.SizeData;

@Component
public interface ISizeDataAO {
    static final String DEFAULT_ORDER_COLUMN = "id";

    public void dropSizeData(String code);

    public void editSizeData(SizeData data);

    public Paginable<SizeData> querySizeDataPage(int start, int limit,
            SizeData condition);

    public List<SizeData> querySizeDataList(SizeData condition);

    public SizeData getSizeData(String code);

    public void addSizeData(String userId, Map<String, String> map);

}
