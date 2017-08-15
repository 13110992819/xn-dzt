package com.cdkj.dzt.ao;

import java.util.List;

import org.springframework.stereotype.Component;

import com.cdkj.dzt.bo.base.Paginable;
import com.cdkj.dzt.domain.Interact;

@Component
public interface IInteractAO {
    static final String DEFAULT_ORDER_COLUMN = "code";

    public String addInteract(String objectCode, String operator);

    public void dropInteract(String objectCode, String operator);

    public void editInteract(Interact data);

    public Paginable<Interact> queryInteractPage(int start, int limit,
            Interact condition);

    public List<Interact> queryInteractList(Interact condition);

    public Interact getInteract(String code);

}
