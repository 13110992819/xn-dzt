package com.cdkj.dzt.bo.impl;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.cdkj.dzt.bo.IInteractBO;
import com.cdkj.dzt.bo.base.PaginableBOImpl;
import com.cdkj.dzt.dao.IInteractDAO;
import com.cdkj.dzt.domain.Interact;
import com.cdkj.dzt.enums.EInteractCategory;
import com.cdkj.dzt.enums.EInteractType;
import com.cdkj.dzt.exception.BizException;

@Component
public class InteractBOImpl extends PaginableBOImpl<Interact> implements
        IInteractBO {

    @Autowired
    private IInteractDAO interactDAO;

    @Override
    public boolean isInteractExist(String code) {
        Interact condition = new Interact();
        condition.setCode(code);
        if (interactDAO.selectTotalCount(condition) > 0) {
            return true;
        }
        return false;
    }

    @Override
    public void saveInteract(Interact data) {
        interactDAO.insert(data);
    }

    @Override
    public void removeInteract(Interact data) {
        interactDAO.delete(data);
    }

    @Override
    public void refreshInteract(Interact data) {
        if (StringUtils.isNotBlank(data.getCode())) {
            interactDAO.update(data);
        }
    }

    @Override
    public List<Interact> queryInteractList(Interact condition) {
        return interactDAO.selectList(condition);
    }

    @Override
    public Interact getInteract(String code) {
        Interact data = null;
        if (StringUtils.isNotBlank(code)) {
            Interact condition = new Interact();
            condition.setCode(code);
            data = interactDAO.select(condition);
            if (data == null) {
                throw new BizException("xn0000", "编号不存在");
            }
        }
        return data;
    }

    @Override
    public Long getTotalCount(EInteractCategory category, EInteractType type,
            String code, String userId) {
        Interact condition = new Interact();
        condition.setCategory(category.getCode());
        condition.setType(type.getCode());
        condition.setObjectCode(code);
        condition.setOperator(userId);
        return interactDAO.selectTotalCount(condition);
    }

    @Override
    public List<Interact> queryInteractList(String objectCode, String operator) {
        Interact condition = new Interact();
        condition.setObjectCode(objectCode);
        condition.setOperator(operator);
        return interactDAO.selectList(condition);
    }
}
