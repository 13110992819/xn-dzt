package com.cdkj.dzt.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.cdkj.dzt.dao.IOrderSizeDataDAO;
import com.cdkj.dzt.dao.base.support.AMybatisTemplate;
import com.cdkj.dzt.domain.OrderSizeData;



//CHECK 。。。 
@Repository("orderSizeDataDAOImpl")
public class OrderSizeDataDAOImpl extends AMybatisTemplate implements IOrderSizeDataDAO {


	@Override
	public int insert(OrderSizeData data) {
		return super.insert(NAMESPACE.concat("insert_orderSizeData"), data);
	}


	@Override
	public int delete(OrderSizeData data) {
		return super.delete(NAMESPACE.concat("delete_orderSizeData"), data);
	}


	@Override
	public OrderSizeData select(OrderSizeData condition) {
		return super.select(NAMESPACE.concat("select_orderSizeData"), condition,OrderSizeData.class);
	}


	@Override
	public Long selectTotalCount(OrderSizeData condition) {
		return super.selectTotalCount(NAMESPACE.concat("select_orderSizeData_count"),condition);
	}


	@Override
	public List<OrderSizeData> selectList(OrderSizeData condition) {
		return super.selectList(NAMESPACE.concat("select_orderSizeData"), condition,OrderSizeData.class);
	}


	@Override
	public List<OrderSizeData> selectList(OrderSizeData condition, int start, int count) {
		return super.selectList(NAMESPACE.concat("select_orderSizeData"), start, count,condition, OrderSizeData.class);
	}


}