package com.cdkj.dzt.api.impl;

import com.cdkj.dzt.ao.IModelSpecsAO;
import com.cdkj.dzt.api.AProcessor;
import com.cdkj.dzt.common.JsonUtil;
import com.cdkj.dzt.core.StringValidater;
import com.cdkj.dzt.dto.req.XN620056Req;
import com.cdkj.dzt.exception.BizException;
import com.cdkj.dzt.exception.ParaException;
import com.cdkj.dzt.spring.SpringContextHolder;

/** 
 * 详情获取型号规格
 * @author: asus 
 * @since: 2017年4月14日 下午3:15:23 
 * @history:
 */
public class XN620056 extends AProcessor {
    private IModelSpecsAO modelSpecsAO = SpringContextHolder
        .getBean(IModelSpecsAO.class);

    private XN620056Req req = null;

    /** 
     * @see com.cdkj.dzt.api.IProcessor#doBusiness()
     */
    @Override
    public Object doBusiness() throws BizException {
        return modelSpecsAO.getModelSpecs(req.getCode());
    }

    /** 
     * @see com.cdkj.dzt.api.IProcessor#doCheck(java.lang.String)
     */
    @Override
    public void doCheck(String inputparams) throws ParaException {
        req = JsonUtil.json2Bean(inputparams, XN620056Req.class);
        StringValidater.validateBlank(req.getCode());
    }

}
