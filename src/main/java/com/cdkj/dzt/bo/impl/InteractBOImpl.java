package com.cdkj.dzt.bo.impl;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.cdkj.dzt.bo.IInteractBO;
import com.cdkj.dzt.bo.base.PaginableBOImpl;
import com.cdkj.dzt.core.OrderNoGenerater;
import com.cdkj.dzt.dao.IInteractDAO;
import com.cdkj.dzt.domain.Interact;
import com.cdkj.dzt.enums.EGeneratePrefix;
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
    public String saveInteract(Interact data) {
        String code = null;
        if (data != null) {
            code = OrderNoGenerater.generateM(EGeneratePrefix.INTERACT
                .getCode());
            data.setCode(code);
            interactDAO.insert(data);
        }
        return code;
    }

    @Override
    public int removeInteract(String code) {
        int count = 0;
        if (StringUtils.isNotBlank(code)) {
            Interact data = new Interact();
            data.setCode(code);
            count = interactDAO.delete(data);
        }
        return count;
    }

    @Override
    public int refreshInteract(Interact data) {
        int count = 0;
        if (StringUtils.isNotBlank(data.getCode())) {
            count = interactDAO.update(data);
        }
        return count;
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
}
