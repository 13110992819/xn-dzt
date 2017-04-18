/**
 * @Title XN620201.java 
 * @Package com.cdkj.dzt.api.impl 
 * @Description 
 * @author leo(haiqing)  
 * @date 2017年4月14日 下午1:43:52 
 * @version V1.0   
 */
package com.cdkj.dzt.api.impl;

import com.cdkj.dzt.ao.IOrderAO;
import com.cdkj.dzt.api.AProcessor;
import com.cdkj.dzt.common.JsonUtil;
import com.cdkj.dzt.core.StringValidater;
import com.cdkj.dzt.dto.req.XN620201Req;
import com.cdkj.dzt.dto.res.PKCodeRes;
import com.cdkj.dzt.exception.BizException;
import com.cdkj.dzt.exception.ParaException;
import com.cdkj.dzt.spring.SpringContextHolder;

/** 
 * 一键复购
 * @author: haiqingzheng 
 * @since: 2017年4月14日 下午1:43:52 
 * @history:
 */
public class XN620201 extends AProcessor {
    private IOrderAO orderAO = SpringContextHolder.getBean(IOrderAO.class);

    private XN620201Req req = null;

    /** 
     * @see com.cdkj.dzt.api.IProcessor#doBusiness()
     */
    @Override
    public Object doBusiness() throws BizException {
        String code = orderAO.applyOrder(req.getApplyUser());
        return new PKCodeRes(code);
    }

    /** 
     * @see com.cdkj.dzt.api.IProcessor#doCheck(java.lang.String)
     */
    @Override
    public void doCheck(String inputparams) throws ParaException {
        req = JsonUtil.json2Bean(inputparams, XN620201Req.class);
        StringValidater.validateBlank(req.getApplyUser());
    }
}
