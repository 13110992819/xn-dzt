package com.cdkj.dzt.ao;

import java.util.List;

import org.springframework.stereotype.Component;

import com.cdkj.dzt.bo.base.Paginable;
import com.cdkj.dzt.domain.Cloth;
import com.cdkj.dzt.dto.req.XN620040Req;
import com.cdkj.dzt.dto.req.XN620042Req;

@Component
public interface IClothAO {
    static final String DEFAULT_ORDER_COLUMN = "code";

    public String addCloth(XN620040Req req);

    public int dropCloth(String code);

    public int editCloth(XN620042Req req);

    public Paginable<Cloth> queryClothPage(int start, int limit, Cloth condition);

    public List<Cloth> queryClothList(Cloth condition);

    public Cloth getCloth(String code);

}
