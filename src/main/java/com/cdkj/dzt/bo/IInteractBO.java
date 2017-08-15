package com.cdkj.dzt.bo;

import java.util.List;

import com.cdkj.dzt.bo.base.IPaginableBO;
import com.cdkj.dzt.domain.Interact;
import com.cdkj.dzt.enums.EInteractCategory;
import com.cdkj.dzt.enums.EInteractType;

public interface IInteractBO extends IPaginableBO<Interact> {

    public boolean isInteractExist(String code);

    public void saveInteract(Interact data);

    public void removeInteract(Interact data);

    public void refreshInteract(Interact data);

    public List<Interact> queryInteractList(Interact condition);

    public Interact getInteract(String code);

    public Long getTotalCount(EInteractCategory category, EInteractType type,
            String code, String userId);

    public List<Interact> queryInteractList(String objectCode, String operator);

}
