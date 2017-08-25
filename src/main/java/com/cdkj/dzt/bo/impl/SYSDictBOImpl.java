/**
 * @Title SYSDictBOImpl.java 
 * @Package com.xnjr.moom.bo.impl 
 * @Description 
 * @author haiqingzheng  
 * @date 2016年4月17日 下午2:50:06 
 * @version V1.0   
 */
package com.cdkj.dzt.bo.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.cdkj.dzt.bo.ISYSDictBO;
import com.cdkj.dzt.bo.base.PaginableBOImpl;
import com.cdkj.dzt.dao.ISYSDictDAO;
import com.cdkj.dzt.domain.SYSDict;

/**
 * @author: xieyj 
 * @since: 2016年10月21日 上午9:58:54 
 * @history:
 */
@Component
public class SYSDictBOImpl extends PaginableBOImpl<SYSDict> implements
        ISYSDictBO {
    @Autowired
    private ISYSDictDAO sysDictDAO;

    /** 
     * @see com.xnjr.base.bo.ISYSDictBO#saveSYSDict(com.xnjr.base.domain.SYSDict)
     */
    @Override
    public Long saveSYSDict(SYSDict data) {
        Long id = null;
        if (data != null) {
            data.setUpdateDatetime(new Date());
            sysDictDAO.insert(data);
            id = data.getId();
        }
        return id;
    }

    /** 
     * @see com.xnjr.base.bo.ISYSDictBO#removeSYSDict(java.lang.Long)
     */
    @Override
    public int removeSYSDict(Long id) {
        int count = 0;
        if (id != null) {
            SYSDict data = new SYSDict();
            data.setId(id);
            count = sysDictDAO.delete(data);
        }
        return count;
    }

    /** 
     * @see com.xnjr.base.bo.ISYSDictBO#refreshSYSDict(com.xnjr.base.domain.SYSDict)
     */
    @Override
    public int refreshSYSDict(SYSDict data) {
        int count = 0;
        if (data != null) {
            data.setUpdateDatetime(new Date());
            count = sysDictDAO.update(data);
        }
        return count;
    }

    /** 
     * @see com.xnjr.base.bo.ISYSDictBO#getSYSDict(java.lang.Long)
     */
    @Override
    public SYSDict getSYSDict(Long id) {
        SYSDict sysDict = null;
        if (id != null) {
            SYSDict data = new SYSDict();
            data.setId(id);
            sysDict = sysDictDAO.select(data);
        }
        return sysDict;
    }

    /** 
     * @see com.xnjr.base.bo.ISYSDictBO#querySYSDictList(com.xnjr.base.domain.SYSDict)
     */
    @Override
    public List<SYSDict> querySYSDictList(SYSDict condition) {
        return sysDictDAO.selectList(condition);
    }

    @Override
    public Map<String, List<SYSDict>> queryMapSYSDictList(SYSDict condition) {
        List<SYSDict> sysDictList = sysDictDAO.selectList(condition);
        Map<String, List<SYSDict>> map = null;
        List<SYSDict> list = new ArrayList<SYSDict>();
        for (SYSDict sysDict : sysDictList) {
            list = map.get(sysDict.getDkey());
            if (CollectionUtils.isEmpty(list)) {
                map = new HashMap<String, List<SYSDict>>();
                list.add(sysDict);
                map.put(sysDict.getDkey(), list);
            } else {
                list.add(sysDict);
                map.put(sysDict.getDkey(), list);
            }
        }
        return map;
    }
}
