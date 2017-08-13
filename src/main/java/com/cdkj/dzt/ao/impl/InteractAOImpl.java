package com.cdkj.dzt.ao.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cdkj.dzt.ao.IInteractAO;
import com.cdkj.dzt.bo.IInteractBO;
import com.cdkj.dzt.bo.base.Paginable;
import com.cdkj.dzt.domain.Interact;
import com.cdkj.dzt.exception.BizException;



@Service
public class InteractAOImpl implements IInteractAO {

	@Autowired
	private IInteractBO interactBO;

	@Override
	public String addInteract(Interact data) {
		return interactBO.saveInteract(data);
	}

	@Override
	public int editInteract(Interact data) {
		if (!interactBO.isInteractExist(data.getCode())) {
			throw new BizException("xn0000", "记录编号不存在");
		}
		return interactBO.refreshInteract(data);
	}

	@Override
	public int dropInteract(String code) {
		if (!interactBO.isInteractExist(code)) {
			throw new BizException("xn0000", "记录编号不存在");
		}
		return interactBO.removeInteract(code);
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