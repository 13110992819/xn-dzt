package com.cdkj.dzt.dto.res;

import com.cdkj.dzt.domain.Article;

/**
 * 文章详情查（front）
 * @author: asus 
 * @since: 2017年8月18日 下午9:06:33 
 * @history:
 */
public class XN620123Res {
    private Article article;

    private String isSC;

    public Article getArticle() {
        return article;
    }

    public void setArticle(Article article) {
        this.article = article;
    }

    public String getIsSC() {
        return isSC;
    }

    public void setIsSC(String isSC) {
        this.isSC = isSC;
    }
}
