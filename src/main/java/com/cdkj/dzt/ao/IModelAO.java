package com.cdkj.dzt.ao;

import java.util.List;

import com.cdkj.dzt.bo.base.Paginable;
import com.cdkj.dzt.domain.Model;
import com.cdkj.dzt.dto.req.XN620000Req;
import com.cdkj.dzt.dto.req.XN620002Req;
import com.cdkj.dzt.dto.res.XN620013Res;

public interface IModelAO {
    static final String DEFAULT_ORDER_COLUMN = "code";

    public String addModel(XN620000Req req);

    public void dropModel(String code);

    public void editModel(XN620002Req req);

    public void putOn(String code, String location, String orderNo,
            String updater, String remark);

    public void putOff(String code, String updater, String remark);

    public Paginable<Model> queryModelPage(int start, int limit,
            Model condition, String userId);

    public List<Model> queryModelList(Model condition);

    public Model getModel(String code);

    public XN620013Res getModel(String code, String userId);
}
