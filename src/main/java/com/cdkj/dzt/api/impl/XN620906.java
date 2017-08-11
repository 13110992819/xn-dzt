package com.cdkj.dzt.api.impl;

import org.apache.commons.lang3.StringUtils;

import com.cdkj.dzt.ao.ISYSDictAO;
import com.cdkj.dzt.api.AProcessor;
import com.cdkj.dzt.common.JsonUtil;
import com.cdkj.dzt.core.StringValidater;
import com.cdkj.dzt.domain.SYSDict;
import com.cdkj.dzt.dto.req.XN620906Req;
import com.cdkj.dzt.exception.BizException;
import com.cdkj.dzt.exception.ParaException;
import com.cdkj.dzt.spring.SpringContextHolder;

/**
 * 列表查询数据字典
 * @author: xieyj 
 * @since: 2016年9月17日 下午1:50:02 
 * @history:
 */
public class XN620906 extends AProcessor {
    private ISYSDictAO sysDictAO = SpringContextHolder
        .getBean(ISYSDictAO.class);

    private XN620906Req req = null;

    /** 
     * @see com.xnjr.base.api.IProcessor#doBusiness()
     */
    @Override
    public Object doBusiness() throws BizException {
        SYSDict condition = new SYSDict();
        condition.setType(req.getType());
        condition.setParentKey(req.getParentKey());
        condition.setDkey(req.getDkey());
        condition.setSystemCode(req.getSystemCode());
        String orderColumn = req.getOrderColumn();
        if (StringUtils.isBlank(orderColumn)) {
            orderColumn = ISYSDictAO.DEFAULT_ORDER_COLUMN;
        }
        condition.setOrder(orderColumn, req.getOrderDir());
        return sysDictAO.querySysDictList(condition);
    }

    /** 
     * @see com.xnjr.base.api.IProcessor#doCheck(java.lang.String)
     */
    @Override
    public void doCheck(String inputparams) throws ParaException {
        req = JsonUtil.json2Bean(inputparams, XN620906Req.class);
        StringValidater.validateBlank(req.getSystemCode());
    }
}
