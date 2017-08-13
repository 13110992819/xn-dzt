package com.cdkj.dzt.bo;

import java.util.List;

import com.cdkj.dzt.bo.base.IPaginableBO;
import com.cdkj.dzt.domain.Interact;



public interface IInteractBO extends IPaginableBO<Interact> {


	public boolean isInteractExist(String code);


	public String saveInteract(Interact data);


	public int removeInteract(String code);


	public int refreshInteract(Interact data);


	public List<Interact> queryInteractList(Interact condition);


	public Interact getInteract(String code);


}