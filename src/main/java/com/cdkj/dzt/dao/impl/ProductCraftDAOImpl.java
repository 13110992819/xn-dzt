package com.cdkj.dzt.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.cdkj.dzt.dao.IProductCraftDAO;
import com.cdkj.dzt.dao.base.support.AMybatisTemplate;
import com.cdkj.dzt.domain.ProductCraft;



//CHECK 。。。 
@Repository("productCraftDAOImpl")
public class ProductCraftDAOImpl extends AMybatisTemplate implements IProductCraftDAO {


	@Override
	public int insert(ProductCraft data) {
		return super.insert(NAMESPACE.concat("insert_productCraft"), data);
	}


	@Override
	public int delete(ProductCraft data) {
		return super.delete(NAMESPACE.concat("delete_productCraft"), data);
	}


	@Override
	public ProductCraft select(ProductCraft condition) {
		return super.select(NAMESPACE.concat("select_productCraft"), condition,ProductCraft.class);
	}


	@Override
	public Long selectTotalCount(ProductCraft condition) {
		return super.selectTotalCount(NAMESPACE.concat("select_productCraft_count"),condition);
	}


	@Override
	public List<ProductCraft> selectList(ProductCraft condition) {
		return super.selectList(NAMESPACE.concat("select_productCraft"), condition,ProductCraft.class);
	}


	@Override
	public List<ProductCraft> selectList(ProductCraft condition, int start, int count) {
		return super.selectList(NAMESPACE.concat("select_productCraft"), start, count,condition, ProductCraft.class);
	}


}