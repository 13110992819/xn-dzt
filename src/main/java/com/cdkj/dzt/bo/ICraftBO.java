package com.cdkj.dzt.bo;

import java.util.List;
import java.util.Map;

import com.cdkj.dzt.bo.base.IPaginableBO;
import com.cdkj.dzt.domain.Craft;

public interface ICraftBO extends IPaginableBO<Craft> {
    public void saveCraft(Craft data);

    public void dropCraft(Craft data);

    public void refreshCraft(Craft data);

    public void putOn(Craft data, String location, String orderNo,
            String updater, String remark);

    public void putOff(Craft data, String updater, String remark);

    public List<Craft> queryCraftList(Craft condition);

    public Craft getCraft(String code);

    public Map<String, Craft> getMap();

    public List<Craft> queryCraftList(String type);

    public long getGroupTotalCount(String modelCode);

    public List<Craft> queryCraftList(String type, String isDefault);

}
