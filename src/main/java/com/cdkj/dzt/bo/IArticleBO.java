package com.cdkj.dzt.bo;

import java.util.List;

import com.cdkj.dzt.bo.base.IPaginableBO;
import com.cdkj.dzt.domain.Article;



public interface IArticleBO extends IPaginableBO<Article> {


	public boolean isArticleExist(String code);


	public String saveArticle(Article data);


	public int removeArticle(String code);


	public int refreshArticle(Article data);


	public List<Article> queryArticleList(Article condition);


	public Article getArticle(String code);


}