/**
 * @Title XN620006.java 
 * @Package com.cdkj.dzt.api.impl 
 * @Description 
 * @author leo(haiqing)  
 * @date 2017年4月14日 下午2:55:39 
 * @version V1.0   
 */
package com.cdkj.dzt.api.impl;

import com.cdkj.dzt.ao.IModelAO;
import com.cdkj.dzt.api.AProcessor;
import com.cdkj.dzt.common.JsonUtil;
import com.cdkj.dzt.core.StringValidater;
import com.cdkj.dzt.dto.req.XN620006Req;
import com.cdkj.dzt.exception.BizException;
import com.cdkj.dzt.exception.ParaException;
import com.cdkj.dzt.spring.SpringContextHolder;

/** 
 * 详情查询型号
 * @author: haiqingzheng 
 * @since: 2017年4月14日 下午2:55:39 
 * @history:
 */
public class XN620006 extends AProcessor {
    private IModelAO modelAO = SpringContextHolder.getBean(IModelAO.class);

    private XN620006Req req = null;

    /** 
     * @see com.cdkj.dzt.api.IProcessor#doBusiness()
     */
    @Override
    public Object doBusiness() throws BizException {
        return modelAO.getModel(req.getCode());
    }

    /** 
     * @see com.cdkj.dzt.api.IProcessor#doCheck(java.lang.String)
     */
    @Override
    public void doCheck(String inputparams) throws ParaException {
        req = JsonUtil.json2Bean(inputparams, XN620006Req.class);
        StringValidater.validateBlank(req.getCode());
    }

}
