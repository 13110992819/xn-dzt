package com.cdkj.dzt.bo.impl;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.cdkj.dzt.bo.IArticleBO;
import com.cdkj.dzt.bo.base.PaginableBOImpl;
import com.cdkj.dzt.core.OrderNoGenerater;
import com.cdkj.dzt.dao.IArticleDAO;
import com.cdkj.dzt.domain.Article;
import com.cdkj.dzt.enums.EGeneratePrefix;
import com.cdkj.dzt.exception.BizException;

@Component
public class ArticleBOImpl extends PaginableBOImpl<Article> implements
        IArticleBO {

    @Autowired
    private IArticleDAO articleDAO;

    @Override
    public boolean isArticleExist(String code) {
        Article condition = new Article();
        condition.setCode(code);
        if (articleDAO.selectTotalCount(condition) > 0) {
            return true;
        }
        return false;
    }

    @Override
    public String saveArticle(Article data) {
        String code = null;
        if (data != null) {
            code = OrderNoGenerater
                .generateM(EGeneratePrefix.ACTICLE.getCode());
            data.setCode(code);
            articleDAO.insert(data);
        }
        return code;
    }

    @Override
    public int removeArticle(String code) {
        int count = 0;
        if (StringUtils.isNotBlank(code)) {
            Article data = new Article();
            data.setCode(code);
            count = articleDAO.delete(data);
        }
        return count;
    }

    @Override
    public int refreshArticle(Article data) {
        int count = 0;
        if (StringUtils.isNotBlank(data.getCode())) {
            count = articleDAO.update(data);
        }
        return count;
    }

    @Override
    public List<Article> queryArticleList(Article condition) {
        return articleDAO.selectList(condition);
    }

    @Override
    public Article getArticle(String code) {
        Article data = null;
        if (StringUtils.isNotBlank(code)) {
            Article condition = new Article();
            condition.setCode(code);
            data = articleDAO.select(condition);
            if (data == null) {
                throw new BizException("xn0000", "编号不存在");
            }
        }
        return data;
    }
}
