package com.cdkj.dzt.bo;

import java.util.List;

import com.cdkj.dzt.bo.base.IPaginableBO;
import com.cdkj.dzt.domain.Article;

public interface IArticleBO extends IPaginableBO<Article> {

    public boolean isArticleExist(String code);

    public void saveArticle(Article data);

    public void removeArticle(String code);

    public void refreshArticle(Article data);

    public List<Article> queryArticleList(Article condition);

    public Article getArticle(String code);

    public void putOn(Article data, String location, String orderNo,
            String updater, String remark);

    public void putOff(Article data, String updater, String remark);

}
