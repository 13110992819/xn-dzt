package com.cdkj.dzt.ao;

import java.util.List;

import org.springframework.stereotype.Component;

import com.cdkj.dzt.bo.base.Paginable;
import com.cdkj.dzt.domain.Cloth;

@Component
public interface IClothAO {
    static final String DEFAULT_ORDER_COLUMN = "code";

    public String addCloth(Cloth data);

    public int dropCloth(String code);

    public int editCloth(Cloth data);

    public Paginable<Cloth> queryClothPage(int start, int limit, Cloth condition);

    public List<Cloth> queryClothList(Cloth condition);

    public Cloth getCloth(String code);

}
