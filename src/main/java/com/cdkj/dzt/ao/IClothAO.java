package com.cdkj.dzt.ao;

import java.util.List;

import org.springframework.stereotype.Component;

import com.cdkj.dzt.bo.base.Paginable;
import com.cdkj.dzt.domain.Cloth;
import com.cdkj.dzt.dto.req.XN620020Req;
import com.cdkj.dzt.dto.req.XN620022Req;
import com.cdkj.dzt.dto.res.XN620033Res;

@Component
public interface IClothAO {
    static final String DEFAULT_ORDER_COLUMN = "code";

    public String addCloth(XN620020Req req);

    public void dropCloth(String code);

    public void editCloth(XN620022Req req);

    public void putOn(String code, String location, String orderNo,
            String updater, String remark);

    public void putOff(String code, String updater, String remark);

    public Paginable<Cloth> queryClothPage(int start, int limit, Cloth condition);

    public List<Cloth> queryClothList(Cloth condition);

    public Cloth getCloth(String code);

    public XN620033Res getCloth(String code, String userId);

}
