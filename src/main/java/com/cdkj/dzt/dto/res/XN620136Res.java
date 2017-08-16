package com.cdkj.dzt.dto.res;

import com.cdkj.dzt.domain.Article;
import com.cdkj.dzt.domain.Cloth;
import com.cdkj.dzt.domain.Craft;
import com.cdkj.dzt.domain.Interact;
import com.cdkj.dzt.domain.Model;

/**
 * 详情查询收藏
 * @author: asus 
 * @since: 2017年8月16日 下午2:13:28 
 * @history:
 */
public class XN620136Res {
    private Interact interact;

    private Model model;

    private Craft craft;

    private Cloth cloth;

    private Article article;

    public Interact getInteract() {
        return interact;
    }

    public void setInteract(Interact interact) {
        this.interact = interact;
    }

    public Model getModel() {
        return model;
    }

    public void setModel(Model model) {
        this.model = model;
    }

    public Craft getCraft() {
        return craft;
    }

    public void setCraft(Craft craft) {
        this.craft = craft;
    }

    public Cloth getCloth() {
        return cloth;
    }

    public void setCloth(Cloth cloth) {
        this.cloth = cloth;
    }

    public Article getArticle() {
        return article;
    }

    public void setArticle(Article article) {
        this.article = article;
    }
}
