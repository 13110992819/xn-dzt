package com.xnjr.mall.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.xnjr.mall.dao.IStoreDAO;
import com.xnjr.mall.dao.base.support.AMybatisTemplate;
import com.xnjr.mall.domain.Store;

/** 
 * @author: zuixian 
 * @since: 2016年9月20日 下午1:03:09 
 * @history:
 */
@Repository("storeDAOImpl")
public class StoreDAOImpl extends AMybatisTemplate implements IStoreDAO {

    @Override
    public int insertOss(Store data) {
        return super.insert(NAMESPACE.concat("insert_store_oss"), data);
    }

    @Override
    public int insert(Store data) {
        return super.insert(NAMESPACE.concat("insert_store"), data);
    }

    @Override
    public int delete(Store data) {
        return 0;
    }

    @Override
    public int update(Store data) {
        return super.update(NAMESPACE.concat("update_store"), data);
    }

    @Override
    public int updateCheck(Store data) {
        return super.update(NAMESPACE.concat("update_check"), data);
    }

    /** 
     * @see com.xnjr.mall.dao.IStoreDAO#updatePutOn(com.xnjr.mall.domain.Store)
     */
    @Override
    public int updatePutOn(Store data) {
        return super.update(NAMESPACE.concat("update_putOn"), data);
    }

    /** 
     * @see com.xnjr.mall.dao.IStoreDAO#updatePutOff(com.xnjr.mall.domain.Store)
     */
    @Override
    public int updatePutOff(Store data) {
        return super.update(NAMESPACE.concat("update_putOff"), data);
    }

    /** 
     * @see com.xnjr.mall.dao.IStoreDAO#updateOpenClose(com.xnjr.mall.domain.Store)
     */
    @Override
    public int updateOpenClose(Store data) {
        return super.update(NAMESPACE.concat("update_openClose"), data);
    }

    /** 
     * @see com.xnjr.mall.dao.IStoreDAO#updateLevel(com.xnjr.mall.domain.Store)
     */
    @Override
    public int updateLevel(Store data) {
        return super.update(NAMESPACE.concat("update_level"), data);
    }

    /** 
     * @see com.xnjr.mall.dao.IStoreDAO#updateTotalRmbNum(com.xnjr.mall.domain.Store)
     */
    @Override
    public int updateTotalRmbNum(Store data) {
        return super.update(NAMESPACE.concat("update_totalRmbNum"), data);
    }

    /** 
     * @see com.xnjr.mall.dao.IStoreDAO#updateTotalJfNum(com.xnjr.mall.domain.Store)
     */
    @Override
    public int updateTotalJfNum(Store data) {
        return super.update(NAMESPACE.concat("update_totalJfNum"), data);
    }

    /** 
     * @see com.xnjr.mall.dao.IStoreDAO#updateTotalDzNum(com.xnjr.mall.domain.Store)
     */
    @Override
    public int updateTotalDzNum(Store data) {
        return super.update(NAMESPACE.concat("update_totalDzNum"), data);
    }

    /** 
     * @see com.xnjr.mall.dao.IStoreDAO#updateTotalScNum(com.xnjr.mall.domain.Store)
     */
    @Override
    public int updateTotalScNum(Store data) {
        return super.update(NAMESPACE.concat("update_totalScNum"), data);
    }

    @Override
    public Store select(Store condition) {
        return super.select("select_store", condition, Store.class);
    }

    @Override
    public Long selectFrontTotalCount(Store condition) {
        return super.selectTotalCount("select_front_store_count", condition);
    }

    @Override
    public List<Store> selectFrontList(Store condition, int start, int count) {
        return super.selectList("select_front_store", start, count, condition,
            Store.class);
    }

    @Override
    public Long selectTotalCount(Store condition) {
        return super.selectTotalCount("select_store_count", condition);
    }

    @Override
    public List<Store> selectList(Store condition) {
        return super.selectList("select_store", condition, Store.class);
    }

    @Override
    public List<Store> selectList(Store condition, int start, int count) {
        return super.selectList("select_store", start, count, condition,
            Store.class);
    }
}
