package com.cdkj.dzt.api.impl;

import com.cdkj.dzt.ao.ISYSDictAO;
import com.cdkj.dzt.api.AProcessor;
import com.cdkj.dzt.common.JsonUtil;
import com.cdkj.dzt.core.StringValidater;
import com.cdkj.dzt.dto.req.XN620901Req;
import com.cdkj.dzt.dto.res.BooleanRes;
import com.cdkj.dzt.exception.BizException;
import com.cdkj.dzt.exception.ParaException;
import com.cdkj.dzt.spring.SpringContextHolder;

/**
 * 删除数据字典
 * @author: xieyj 
 * @since: 2016年9月17日 下午1:47:19 
 * @history:
 */
public class XN620901 extends AProcessor {
    private ISYSDictAO sysDictAO = SpringContextHolder
        .getBean(ISYSDictAO.class);

    private XN620901Req req = null;

    /** 
     * @see com.xnjr.base.api.IProcessor#doBusiness()
     */
    @Override
    public Object doBusiness() throws BizException {
        sysDictAO.dropSYSDict(StringValidater.toLong(req.getId()));
        return new BooleanRes(true);
    }

    /** 
     * @see com.xnjr.base.api.IProcessor#doCheck(java.lang.String)
     */
    @Override
    public void doCheck(String inputparams) throws ParaException {
        req = JsonUtil.json2Bean(inputparams, XN620901Req.class);
        StringValidater.validateBlank(req.getId());
    }
}
