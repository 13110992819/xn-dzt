package com.cdkj.dzt.api.impl;

import com.cdkj.dzt.ao.ISYSConfigAO;
import com.cdkj.dzt.api.AProcessor;
import com.cdkj.dzt.common.JsonUtil;
import com.cdkj.dzt.core.StringValidater;
import com.cdkj.dzt.dto.req.XN620916Req;
import com.cdkj.dzt.exception.BizException;
import com.cdkj.dzt.exception.ParaException;
import com.cdkj.dzt.spring.SpringContextHolder;

/**
 * 详情查询系统参数
 * @author: xieyj 
 * @since: 2016年9月17日 下午1:55:26 
 * @history:
 */
public class XN620916 extends AProcessor {
    private ISYSConfigAO sysConfigAO = SpringContextHolder
        .getBean(ISYSConfigAO.class);

    private XN620916Req req = null;

    /** 
     * @see com.xnjr.base.api.IProcessor#doBusiness()
     */
    @Override
    public Object doBusiness() throws BizException {
        return sysConfigAO.getSYSConfig(StringValidater.toLong(req.getId()));
    }

    /** 
     * @see com.xnjr.base.api.IProcessor#doCheck(java.lang.String)
     */
    @Override
    public void doCheck(String inputparams) throws ParaException {
        req = JsonUtil.json2Bean(inputparams, XN620916Req.class);
        StringValidater.validateBlank(req.getId());
    }

}
