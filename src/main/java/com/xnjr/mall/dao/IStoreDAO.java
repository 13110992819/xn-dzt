package com.xnjr.mall.dao;

import com.xnjr.mall.dao.base.IBaseDAO;
import com.xnjr.mall.domain.Store;

/**
 * @author: xieyj 
 * @since: 2017年3月27日 下午12:25:01 
 * @history:
 */
public interface IStoreDAO extends IBaseDAO<Store> {
    String NAMESPACE = IStoreDAO.class.getName().concat(".");

    public int insertOss(Store data);

    public int update(Store data);

    public int updateCheck(Store data);

    public int updatePutOn(Store data);

    public int updatePutOff(Store data);

    public int updateOpenClose(Store data);

    public int updateLevel(Store data);

    public int updateTotalRmbNum(Store data);

    public int updateTotalJfNum(Store data);

    public int updateTotalDzNum(Store data);

    public int updateTotalScNum(Store data);
}
