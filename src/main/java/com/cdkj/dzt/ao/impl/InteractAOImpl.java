package com.cdkj.dzt.ao.impl;

import java.util.Date;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cdkj.dzt.ao.IInteractAO;
import com.cdkj.dzt.bo.IArticleBO;
import com.cdkj.dzt.bo.IClothBO;
import com.cdkj.dzt.bo.ICraftBO;
import com.cdkj.dzt.bo.IInteractBO;
import com.cdkj.dzt.bo.IModelBO;
import com.cdkj.dzt.bo.ISpecimenBO;
import com.cdkj.dzt.bo.base.Paginable;
import com.cdkj.dzt.core.OrderNoGenerater;
import com.cdkj.dzt.domain.Article;
import com.cdkj.dzt.domain.Cloth;
import com.cdkj.dzt.domain.Craft;
import com.cdkj.dzt.domain.Interact;
import com.cdkj.dzt.domain.Model;
import com.cdkj.dzt.domain.Specimen;
import com.cdkj.dzt.dto.res.XN620136Res;
import com.cdkj.dzt.enums.EGeneratePrefix;
import com.cdkj.dzt.enums.EInteractCategory;
import com.cdkj.dzt.enums.EInteractType;
import com.cdkj.dzt.exception.BizException;

@Service
public class InteractAOImpl implements IInteractAO {

    @Autowired
    private IInteractBO interactBO;

    @Autowired
    private IModelBO modelBO;

    @Autowired
    private ICraftBO craftBO;

    @Autowired
    private ISpecimenBO specimenBO;

    @Autowired
    private IClothBO clothBO;

    @Autowired
    private IArticleBO articleBO;

    @Override
    public String addInteract(String objectCode, String operator) {
        String category = EInteractCategory.ARTICLE.getCode();
        if (objectCode.startsWith(EGeneratePrefix.CLOTH.getCode())) {
            category = EInteractCategory.CLOTH.getCode();
        } else if (objectCode.startsWith(EGeneratePrefix.CRAFT.getCode())) {
            category = EInteractCategory.CRAFT.getCode();
        } else if (objectCode.startsWith(EGeneratePrefix.MODEL.getCode())) {
            category = EInteractCategory.MODEL.getCode();
        } else if (objectCode.startsWith(EGeneratePrefix.SPECIMEN.getCode())) {
            category = EInteractCategory.SPECIMEN.getCode();
        }
        Interact data = new Interact();
        String code = OrderNoGenerater.generateM(EGeneratePrefix.INTERACT
            .getCode());
        data.setCode(code);
        data.setCategory(category);
        data.setType(EInteractType.SC.getCode());
        data.setObjectCode(objectCode);
        data.setOperator(operator);
        data.setOperatDatetime(new Date());
        interactBO.saveInteract(data);
        return code;
    }

    @Override
    public void editInteract(Interact data) {
        if (!interactBO.isInteractExist(data.getCode())) {
            throw new BizException("xn0000", "记录编号不存在");
        }
    }

    @Override
    public void dropInteract(String objectCode, String operator) {
        List<Interact> interactList = interactBO.queryInteractList(objectCode,
            operator);
        if (CollectionUtils.isNotEmpty(interactList)) {
            for (Interact interact : interactList) {
                interactBO.removeInteract(interact);
            }
        }
    }

    @Override
    public Paginable<Interact> queryInteractPage(int start, int limit,
            Interact condition) {
        Paginable<Interact> page = interactBO.getPaginable(start, limit,
            condition);
        List<Interact> list = page.getList();
        for (Interact interact : list) {
            String objectCode = interact.getObjectCode();
            if (EInteractCategory.ARTICLE.getCode().equals(
                interact.getCategory())) {
                Article article = articleBO.getArticle(objectCode);
                interact.setArticle(article);
            } else if (EInteractCategory.MODEL.getCode().equals(
                interact.getCategory())) {
                Model model = modelBO.getModel(objectCode);
                interact.setModel(model);
            } else if (EInteractCategory.CLOTH.getCode().equals(
                interact.getCategory())) {
                Cloth cloth = clothBO.getCloth(objectCode);
                interact.setCloth(cloth);
            } else if (EInteractCategory.CRAFT.getCode().equals(
                interact.getCategory())) {
                Craft craft = craftBO.getCraft(objectCode);
                interact.setCraft(craft);
            } else if (EInteractCategory.SPECIMEN.getCode().equals(
                interact.getCategory())) {
                Specimen specimen = specimenBO.getSpecimen(objectCode);
                interact.setSpecimen(specimen);
            }
        }
        return page;
    }

    @Override
    public List<Interact> queryInteractList(Interact condition) {
        return interactBO.queryInteractList(condition);
    }

    @Override
    public XN620136Res getInteract(String code) {
        XN620136Res res = new XN620136Res();
        Interact interact = interactBO.getInteract(code);
        String objectCode = interact.getObjectCode();
        if (EInteractCategory.ARTICLE.getCode().equals(interact.getCategory())) {
            Article article = articleBO.getArticle(objectCode);
            res.setArticle(article);
        }
        if (EInteractCategory.MODEL.getCode().equals(interact.getCategory())) {
            Model model = modelBO.getModel(objectCode);
            res.setModel(model);
        }
        if (EInteractCategory.CLOTH.getCode().equals(interact.getCategory())) {
            Cloth cloth = clothBO.getCloth(objectCode);
            res.setCloth(cloth);
        }
        if (EInteractCategory.CRAFT.getCode().equals(interact.getCategory())) {
            Craft craft = craftBO.getCraft(objectCode);
            res.setCraft(craft);
        }
        res.setInteract(interact);
        return res;
    }
}
