package com.cdkj.dzt.ao;

import java.util.List;

import com.cdkj.dzt.bo.base.Paginable;
import com.cdkj.dzt.domain.Craft;
import com.cdkj.dzt.dto.req.XN620040Req;
import com.cdkj.dzt.dto.req.XN620042Req;
import com.cdkj.dzt.dto.res.XN620053Res;
import com.cdkj.dzt.dto.res.XN620054Res;

public interface ICraftAO {
    static final String DEFAULT_ORDER_COLUMN = "code";

    public String addCraft(XN620040Req req);

    public void dropCraft(String code);

    public void editCraft(XN620042Req req);

    public void putOn(List<String> codeList, String location, String orderNo,
            String updater, String remark);

    public void putOff(List<String> codeList, String updater, String remark);

    public Paginable<Craft> queryCraftPage(int start, int limit, Craft condition);

    public List<Craft> queryCraftList(Craft condition);

    public Craft getCraft(String code);

    public XN620053Res getCraft(String code, String userId);

    XN620054Res getCraftList(String modelSpecsCode);
}
