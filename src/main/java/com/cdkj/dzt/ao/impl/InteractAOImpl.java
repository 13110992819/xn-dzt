package com.cdkj.dzt.ao.impl;

import java.util.Date;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cdkj.dzt.ao.IInteractAO;
import com.cdkj.dzt.bo.IInteractBO;
import com.cdkj.dzt.bo.base.Paginable;
import com.cdkj.dzt.core.OrderNoGenerater;
import com.cdkj.dzt.domain.Interact;
import com.cdkj.dzt.enums.EGeneratePrefix;
import com.cdkj.dzt.enums.EInteractCategory;
import com.cdkj.dzt.enums.EInteractType;
import com.cdkj.dzt.exception.BizException;

@Service
public class InteractAOImpl implements IInteractAO {

    @Autowired
    private IInteractBO interactBO;

    @Override
    public String addInteract(String objectCode, String operator) {
        String category = EInteractCategory.ARTICLE.getCode();
        if (objectCode.startsWith(EGeneratePrefix.CLOTH.getCode())) {
            category = EInteractCategory.CLOTH.getCode();
        }
        if (objectCode.startsWith(EGeneratePrefix.CRAFT.getCode())) {
            category = EInteractCategory.CRAFT.getCode();
        }
        if (objectCode.startsWith(EGeneratePrefix.MODEL.getCode())) {
            category = EInteractCategory.MODEL.getCode();
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
        return interactBO.getPaginable(start, limit, condition);
    }

    @Override
    public List<Interact> queryInteractList(Interact condition) {
        return interactBO.queryInteractList(condition);
    }

    @Override
    public Interact getInteract(String code) {
        return interactBO.getInteract(code);
    }
}
