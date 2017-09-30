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
import java.util.Map.Entry;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.cdkj.dzt.bo.ISYSDictBO;
import com.cdkj.dzt.bo.base.PaginableBOImpl;
import com.cdkj.dzt.dao.ISYSDictDAO;
import com.cdkj.dzt.domain.Order;
import com.cdkj.dzt.domain.SYSDict;
import com.cdkj.dzt.enums.EBoolean;
import com.cdkj.dzt.enums.ESystemCode;
import com.cdkj.dzt.exception.BizException;
import com.cdkj.dzt.http.BizConnecter;
import com.cdkj.dzt.http.JsonUtils;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

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
        List<SYSDict> sysDictList = selectSYSDict();
        Map<String, List<SYSDict>> map = new HashMap<String, List<SYSDict>>();
        List<SYSDict> list = null;
        for (SYSDict sysDict : sysDictList) {
            list = map.get(sysDict.getParentKey());
            if (CollectionUtils.isEmpty(list)) {
                list = new ArrayList<SYSDict>();
                list.add(sysDict);
                map.put(sysDict.getParentKey(), list);
            } else {
                list.add(sysDict);
                map.put(sysDict.getParentKey(), list);
            }
        }
        return map;
    }

    @Override
    public Map<String, List<SYSDict>> queryMapSYSDictList() {
        SYSDict condition = new SYSDict();
        condition.setType(EBoolean.NO.getCode());
        List<SYSDict> sysDictList = sysDictDAO.selectList(condition);
        List<SYSDict> list = null;
        Map<String, List<SYSDict>> map = new HashMap<String, List<SYSDict>>();
        for (SYSDict sysDict : sysDictList) {
            SYSDict data = new SYSDict();
            data.setParentKey(sysDict.getDkey());
            List<SYSDict> sysList = sysDictDAO.selectList(data);
            list = map.get(sysDict.getDkey());
            if (CollectionUtils.isEmpty(list)) {
                list = new ArrayList<SYSDict>();
                list.addAll(sysList);
                map.put(sysDict.getDkey(), list);
            } else {
                list.addAll(sysList);
                map.put(sysDict.getDkey(), list);
            }
        }
        return map;
    }

    @Override
    public List<SYSDict> selectSYSDict() {
        SYSDict condition = new SYSDict();
        condition.setSystemCode(ESystemCode.DZT.getCode());
        condition.setCompanyCode(ESystemCode.DZT.getCode());
        String jsonStr = BizConnecter.getBizData("805906",
            JsonUtils.object2Json(condition));
        Gson gson = new Gson();
        List<SYSDict> list = gson.fromJson(jsonStr,
            new TypeToken<List<SYSDict>>() {
            }.getType());
        if (CollectionUtils.isEmpty(list)) {
            throw new BizException("xn000000", "不存在该形体数据");
        }
        return list;
    }

    @Override
    public void checkOrder(Order order) {
        Map<String, List<SYSDict>> map = order.getSysDictMap();
        for (Entry<String, List<SYSDict>> sysDictMap : map.entrySet()) {
            List<SYSDict> sysDictlist = sysDictMap.getValue();
            for (SYSDict sysDict : sysDictlist) {
                if (EBoolean.YES.getCode().equals(sysDict.getRemark())) {
                    if (null == sysDict.getOrderSizeData()) {
                        throw new BizException("xn0000", "您还有信息未填写完整");
                    }
                }
            }
        }

    }
}
