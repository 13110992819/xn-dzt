package com.cdkj.dzt.bo;

import java.util.List;
import java.util.Map;

import com.cdkj.dzt.bo.base.IPaginableBO;
import com.cdkj.dzt.domain.Cloth;

public interface IClothBO extends IPaginableBO<Cloth> {

    public boolean isClothExist(String code);

    public void saveCloth(Cloth data);

    public void removeCloth(Cloth data);

    public void refreshCloth(Cloth data);

    public void putOn(Cloth data, String location, String orderNo,
            String updater, String remark);

    public void putOff(Cloth data, String updater, String remark);

    public List<Cloth> queryClothList(Cloth condition);

    public Cloth getCloth(String code);

    public Map<String, Cloth> getMap();

    public long getTotalCount(String modelCode);

    public List<Cloth> queryClothList(String code);
}
