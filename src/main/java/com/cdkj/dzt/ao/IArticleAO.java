package com.cdkj.dzt.ao;

import java.util.List;

import org.springframework.stereotype.Component;

import com.cdkj.dzt.bo.base.Paginable;
import com.cdkj.dzt.domain.Article;
import com.cdkj.dzt.dto.req.XN620110Req;
import com.cdkj.dzt.dto.req.XN620112Req;

@Component
public interface IArticleAO {
    static final String DEFAULT_ORDER_COLUMN = "code";

    public String addArticle(XN620110Req req);

    public void dropArticle(String code);

    public void editArticle(XN620112Req req);

    public void putOn(String code, String location, String orderNo,
            String updater, String remark);

    public void putOff(String code, String updater, String remark);

    public Paginable<Article> queryArticlePage(int start, int limit,
            Article condition);

    public List<Article> queryArticleList(Article condition);

    public Article getArticle(String code);

}
