package com.cdkj.dzt.ao;

import java.util.List;

import org.springframework.stereotype.Component;

import com.cdkj.dzt.bo.base.Paginable;
import com.cdkj.dzt.domain.SizeData;

@Component
public interface ISizeDataAO {
    static final String DEFAULT_ORDER_COLUMN = "id";

    public String addSizeData(SizeData data);

    public int dropSizeData(String code);

    public int editSizeData(SizeData data);

    public Paginable<SizeData> querySizeDataPage(int start, int limit,
            SizeData condition);

    public List<SizeData> querySizeDataList(SizeData condition);

    public SizeData getSizeData(String code);

}
