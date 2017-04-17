package com.cdkj.dzt.bo;

import java.util.List;

import com.cdkj.dzt.bo.base.IPaginableBO;
import com.cdkj.dzt.domain.Order;



public interface IOrderBO extends IPaginableBO<Order> {


	public boolean isOrderExist(String code);


	public String saveOrder(Order data);


	public int removeOrder(String code);


	public int refreshOrder(Order data);


	public List<Order> queryOrderList(Order condition);


	public Order getOrder(String code);


}