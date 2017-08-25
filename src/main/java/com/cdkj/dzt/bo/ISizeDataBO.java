package com.cdkj.dzt.bo;

import java.util.List;
import java.util.Map;

import com.cdkj.dzt.bo.base.IPaginableBO;
import com.cdkj.dzt.domain.SizeData;

public interface ISizeDataBO extends IPaginableBO<SizeData> {

    public boolean isSizeDataExist(String code);

    public void inputInforValue(String applyUser, Map<String, String> map);

    public int removeSizeData(String code);

    public int refreshSizeData(SizeData data);

    public List<SizeData> querySizeDataList(SizeData condition);

    public SizeData getSizeData(String code);

    public List<SizeData> querySizeDataList(String userId);

    public void removeSizeDataByUserId(String applyUser);

    public void removeSizeDataByKey(String applyUser, String key);

}
