package com.cdkj.dzt.ao;

import java.util.List;

import org.springframework.stereotype.Component;

import com.cdkj.dzt.bo.base.Paginable;
import com.cdkj.dzt.domain.Article;

@Component
public interface IArticleAO {
    static final String DEFAULT_ORDER_COLUMN = "code";

    public String addArticle(Article data);

    public int dropArticle(String code);

    public int editArticle(Article data);

    public Paginable<Article> queryArticlePage(int start, int limit,
            Article condition);

    public List<Article> queryArticleList(Article condition);

    public Article getArticle(String code);

}
