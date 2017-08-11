package com.cdkj.dzt.ao;

import java.util.List;

import com.cdkj.dzt.bo.base.Paginable;
import com.cdkj.dzt.domain.Keyword;
import com.cdkj.dzt.dto.req.XN620150Req;
import com.cdkj.dzt.dto.req.XN620152Req;

public interface IKeywordAO {
    static final String DEFAULT_ORDER_COLUMN = "code";

    public String addKeyword(XN620150Req req);

    public void dropKeyword(String code);

    public void editKeyword(XN620152Req req);

    public Paginable<Keyword> queryKeywordPage(int start, int limit,
            Keyword condition);

    public Keyword getKeyword(String code);

    public void addKeyword(List<XN620150Req> reqList);

}
