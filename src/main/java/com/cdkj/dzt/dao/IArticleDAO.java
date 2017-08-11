package com.cdkj.dzt.dao;

import com.cdkj.dzt.dao.base.IBaseDAO;
import com.cdkj.dzt.domain.Article;

//dao层 
public interface IArticleDAO extends IBaseDAO<Article> {
    String NAMESPACE = IArticleDAO.class.getName().concat(".");

    public int update(Article data);
}
