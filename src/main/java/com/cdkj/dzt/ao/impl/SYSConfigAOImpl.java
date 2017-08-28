package com.cdkj.dzt.ao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cdkj.dzt.ao.ISYSConfigAO;
import com.cdkj.dzt.bo.ISYSConfigBO;
import com.cdkj.dzt.bo.base.Paginable;
import com.cdkj.dzt.domain.SYSConfig;
import com.cdkj.dzt.exception.BizException;

/**
 * 系统参数
 * @author: xieyj 
 * @since: 2016年7月23日 下午3:50:20 
 * @history:
 */
@Service
public class SYSConfigAOImpl implements ISYSConfigAO {

    @Autowired
    ISYSConfigBO sysConfigBO;

    @Override
    public int editSYSConfig(SYSConfig data) {
        int count = 0;
        if (data != null) {
            count = sysConfigBO.refreshSYSConfig(data);
        } else {
            throw new BizException("lh5031", "系统参数ID不存在！");
        }
        return count;
    }

    @Override
    public Paginable<SYSConfig> querySYSConfigPage(int start, int limit,
            SYSConfig condition) {
        return sysConfigBO.getPaginable(start, limit, condition);
    }

    @Override
    public SYSConfig getSYSConfig(Long id) {
        return sysConfigBO.getConfig(id);
    }

    @Override
    public SYSConfig getSYSConfig(String key, String companyCode,
            String systemCode) {
        return sysConfigBO.getConfigValue(key, companyCode, systemCode);
    }

    @Override
    public Map<String, String> getSYSConfig(List<String> keyList,
            String companyCode, String systemCode) {
        Map<String, String> resultMap = new HashMap<String, String>();
        for (String key : keyList) {
            SYSConfig sysconfig = sysConfigBO.getConfigValue(key, companyCode,
                systemCode);
            resultMap.put(sysconfig.getCkey(), sysconfig.getCvalue());
        }
        return resultMap;
    }
}
