package com.cdkj.dzt.bo.impl;

import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.cdkj.dzt.bo.IArticleBO;
import com.cdkj.dzt.bo.base.PaginableBOImpl;
import com.cdkj.dzt.core.StringValidater;
import com.cdkj.dzt.dao.IArticleDAO;
import com.cdkj.dzt.domain.Article;
import com.cdkj.dzt.enums.EStatus;
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
    public void saveArticle(Article data) {
        articleDAO.insert(data);
    }

    @Override
    public void removeArticle(String code) {
        if (StringUtils.isNotBlank(code)) {
            Article data = new Article();
            data.setCode(code);
            articleDAO.delete(data);
        }
    }

    @Override
    public void refreshArticle(Article data) {
        articleDAO.update(data);
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

    @Override
    public void putOn(Article data, String location, String orderNo,
            String updater, String remark) {
        data.setStatus(EStatus.PUT_ON.getCode());
        data.setLocation(location);
        data.setOrderNo(StringValidater.toInteger(orderNo));
        data.setUpdater(updater);
        data.setUpdateDatetime(new Date());
        data.setRemark(remark);
        articleDAO.putOn(data);
    }

    @Override
    public void putOff(Article data, String updater, String remark) {
        data.setStatus(EStatus.PUT_OFF.getCode());
        data.setUpdater(updater);
        data.setUpdateDatetime(new Date());
        data.setRemark(remark);
        articleDAO.putOff(data);
    }
}
