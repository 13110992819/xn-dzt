package com.cdkj.dzt.ao.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cdkj.dzt.ao.IArticleAO;
import com.cdkj.dzt.bo.IArticleBO;
import com.cdkj.dzt.bo.base.Paginable;
import com.cdkj.dzt.domain.Article;
import com.cdkj.dzt.exception.BizException;



@Service
public class ArticleAOImpl implements IArticleAO {

	@Autowired
	private IArticleBO articleBO;

	@Override
	public String addArticle(Article data) {
		return articleBO.saveArticle(data);
	}

	@Override
	public int editArticle(Article data) {
		if (!articleBO.isArticleExist(data.getCode())) {
			throw new BizException("xn0000", "记录编号不存在");
		}
		return articleBO.refreshArticle(data);
	}

	@Override
	public int dropArticle(String code) {
		if (!articleBO.isArticleExist(code)) {
			throw new BizException("xn0000", "记录编号不存在");
		}
		return articleBO.removeArticle(code);
	}

	@Override
	public Paginable<Article> queryArticlePage(int start, int limit,
			Article condition) {
		return articleBO.getPaginable(start, limit, condition);
	}

	@Override
	public List<Article> queryArticleList(Article condition) {
		return articleBO.queryArticleList(condition);
	}

	@Override
	public Article getArticle(String code) {
		return articleBO.getArticle(code);
	}
}