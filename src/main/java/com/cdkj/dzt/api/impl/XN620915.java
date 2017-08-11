package com.cdkj.dzt.api.impl;

import org.apache.commons.lang3.StringUtils;

import com.cdkj.dzt.ao.ISYSConfigAO;
import com.cdkj.dzt.api.AProcessor;
import com.cdkj.dzt.common.JsonUtil;
import com.cdkj.dzt.core.StringValidater;
import com.cdkj.dzt.domain.SYSConfig;
import com.cdkj.dzt.dto.req.XN620915Req;
import com.cdkj.dzt.exception.BizException;
import com.cdkj.dzt.exception.ParaException;
import com.cdkj.dzt.spring.SpringContextHolder;

/**
 * 分页查询系统参数
 * @author: xieyj 
 * @since: 2016年9月17日 下午1:55:07 
 * @history:
 */
public class XN620915 extends AProcessor {
    private ISYSConfigAO sysConfigAO = SpringContextHolder
        .getBean(ISYSConfigAO.class);

    private XN620915Req req = null;

    @Override
    public Object doBusiness() throws BizException {
        SYSConfig condition = new SYSConfig();
        condition.setType(req.getType());
        condition.setCkeyForQuery(req.getCkey());
        condition.setSystemCode(req.getSystemCode());
        String orderColumn = req.getOrderColumn();
        if (StringUtils.isBlank(orderColumn)) {
            orderColumn = ISYSConfigAO.DEFAULT_ORDER_COLUMN;
        }
        condition.setOrder(orderColumn, req.getOrderDir());
        int start = StringValidater.toInteger(req.getStart());
        int limit = StringValidater.toInteger(req.getLimit());
        return sysConfigAO.querySYSConfigPage(start, limit, condition);
    }

    @Override
    public void doCheck(String inputparams) throws ParaException {
        req = JsonUtil.json2Bean(inputparams, XN620915Req.class);
        StringValidater.validateBlank(req.getStart(), req.getLimit());
        StringValidater.validateBlank(req.getSystemCode());
    }

}
