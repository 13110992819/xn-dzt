package com.cdkj.dzt.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.cdkj.dzt.dao.IProductVarDAO;
import com.cdkj.dzt.dao.base.support.AMybatisTemplate;
import com.cdkj.dzt.domain.ProductVar;

@Repository("productVarDAOImpl")
public class ProductVarDAOImpl extends AMybatisTemplate implements
        IProductVarDAO {

    @Override
    public int insert(ProductVar data) {
        return super.insert(NAMESPACE.concat("insert_productVar"), data);
    }

    @Override
    public int delete(ProductVar data) {
        return super.delete(NAMESPACE.concat("delete_productVar"), data);
    }

    @Override
    public ProductVar select(ProductVar condition) {
        return super.select(NAMESPACE.concat("select_productVar"), condition,
            ProductVar.class);
    }

    @Override
    public Long selectTotalCount(ProductVar condition) {
        return super.selectTotalCount(
            NAMESPACE.concat("select_productVar_count"), condition);
    }

    @Override
    public List<ProductVar> selectList(ProductVar condition) {
        return super.selectList(NAMESPACE.concat("select_productVar"),
            condition, ProductVar.class);
    }

    @Override
    public List<ProductVar> selectList(ProductVar condition, int start,
            int count) {
        return super.selectList(NAMESPACE.concat("select_productVar"), start,
            count, condition, ProductVar.class);
    }

    @Override
    public int update(ProductVar data) {
        return super.update(NAMESPACE.concat("update_productVar"), data);
    }

}
