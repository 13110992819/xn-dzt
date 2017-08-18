package com.cdkj.dzt.ao.impl;

import java.util.Date;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cdkj.dzt.ao.IArticleAO;
import com.cdkj.dzt.bo.IArticleBO;
import com.cdkj.dzt.bo.IInteractBO;
import com.cdkj.dzt.bo.base.Paginable;
import com.cdkj.dzt.core.OrderNoGenerater;
import com.cdkj.dzt.domain.Article;
import com.cdkj.dzt.dto.req.XN620110Req;
import com.cdkj.dzt.dto.req.XN620112Req;
import com.cdkj.dzt.dto.res.XN620123Res;
import com.cdkj.dzt.enums.EBoolean;
import com.cdkj.dzt.enums.EGeneratePrefix;
import com.cdkj.dzt.enums.EInteractCategory;
import com.cdkj.dzt.enums.EInteractType;
import com.cdkj.dzt.enums.EStatus;
import com.cdkj.dzt.exception.BizException;

@Service
public class ArticleAOImpl implements IArticleAO {

    @Autowired
    private IArticleBO articleBO;

    @Autowired
    private IInteractBO interactBO;

    @Override
    public String addArticle(XN620110Req req) {
        Article data = new Article();
        String code = OrderNoGenerater.generateM(EGeneratePrefix.ACTICLE
            .getCode());
        data.setCode(code);
        data.setType(req.getType());
        data.setTitle(req.getTitle());
        data.setPic(req.getPic());
        data.setAdvPic(req.getAdvPic());
        data.setDescription(req.getDescription());
        data.setStatus(EStatus.DRAFT.getCode());
        data.setUpdater(req.getUpdater());
        data.setUpdateDatetime(new Date());
        data.setRemark(req.getRemark());
        articleBO.saveArticle(data);
        return code;
    }

    @Override
    public void editArticle(XN620112Req req) {
        Article data = articleBO.getArticle(req.getCode());
        if (EStatus.PUT_ON.getCode().equals(data.getStatus())) {
            throw new BizException("xn0000", "正在上架,不可修改");
        }
        data.setType(req.getType());
        data.setTitle(req.getTitle());
        data.setPic(req.getPic());
        data.setAdvPic(req.getAdvPic());
        data.setDescription(req.getDescription());
        data.setUpdater(req.getUpdater());
        data.setUpdateDatetime(new Date());
        data.setRemark(req.getRemark());
        articleBO.refreshArticle(data);
    }

    @Override
    public void dropArticle(String code) {
        articleBO.getArticle(code);
        articleBO.removeArticle(code);
    }

    @Override
    public void putOn(String code, String location, String orderNo,
            String updater, String remark) {
        Article data = articleBO.getArticle(code);
        if (EStatus.PUT_ON.getCode().equals(data.getStatus())) {
            throw new BizException("xn0000", "文章已经上架");
        }
        articleBO.putOn(data, location, orderNo, updater, remark);
    }

    @Override
    public void putOff(String code, String updater, String remark) {
        Article data = articleBO.getArticle(code);
        if (!EStatus.PUT_ON.getCode().equals(data.getStatus())) {
            throw new BizException("xn0000", "文章未上架");
        }
        articleBO.putOff(data, updater, remark);
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

    @Override
    public XN620123Res getArticle(String code, String userId) {
        XN620123Res res = new XN620123Res();
        Article article = articleBO.getArticle(code);
        String isSC = EBoolean.NO.getCode();
        if (StringUtils.isNotBlank(userId)) {
            Long num = interactBO.getTotalCount(EInteractCategory.CLOTH,
                EInteractType.SC, code, userId);
            if (num > 0) {
                isSC = EBoolean.YES.getCode();
            }
        }
        res.setArticle(article);
        res.setIsSC(isSC);
        return res;
    }

}
