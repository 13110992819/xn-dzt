package com.cdkj.dzt.domain;

import java.util.Date;

import com.cdkj.dzt.dao.base.ABaseDO;

/**
* 交互
* @author: shan 
* @since: 2017-08-13 18:58:38
* @history:
*/
public class Interact extends ABaseDO {

    private static final long serialVersionUID = 1L;

    // 编号
    private String code;

    // 分类
    private String category;

    // 类型
    private String type;

    // 互动编号
    private String objectCode;

    // 互动人
    private String operator;

    // 互动时间
    private Date operatDatetime;

    private Model model;

    private Craft craft;

    private Cloth cloth;

    private Article article;

    private Specimen specimen;

    public void setCode(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getCategory() {
        return category;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }

    public void setObjectCode(String objectCode) {
        this.objectCode = objectCode;
    }

    public String getObjectCode() {
        return objectCode;
    }

    public void setOperator(String operator) {
        this.operator = operator;
    }

    public String getOperator() {
        return operator;
    }

    public void setOperatDatetime(Date operatDatetime) {
        this.operatDatetime = operatDatetime;
    }

    public Date getOperatDatetime() {
        return operatDatetime;
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

    public Specimen getSpecimen() {
        return specimen;
    }

    public void setSpecimen(Specimen specimen) {
        this.specimen = specimen;
    }

}
