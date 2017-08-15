package com.cdkj.dzt.api.impl;

import com.cdkj.dzt.ao.ICraftAO;
import com.cdkj.dzt.api.AProcessor;
import com.cdkj.dzt.common.JsonUtil;
import com.cdkj.dzt.core.StringValidater;
import com.cdkj.dzt.dto.req.XN620051Req;
import com.cdkj.dzt.exception.BizException;
import com.cdkj.dzt.exception.ParaException;
import com.cdkj.dzt.spring.SpringContextHolder;

/** 
 * 详情获取型号规格
 * @author: asus 
 * @since: 2017年4月14日 下午3:15:23 
 * @history:
 */
public class XN620051 extends AProcessor {
    private ICraftAO craftAO = SpringContextHolder
        .getBean(ICraftAO.class);

    private XN620051Req req = null;

    /** 
     * @see com.cdkj.dzt.api.IProcessor#doBusiness()
     */
    @Override
    public Object doBusiness() throws BizException {
        return craftAO.getCraft(req.getCode());
    }

    /** 
     * @see com.cdkj.dzt.api.IProcessor#doCheck(java.lang.String)
     */
    @Override
    public void doCheck(String inputparams) throws ParaException {
        req = JsonUtil.json2Bean(inputparams, XN620051Req.class);
        StringValidater.validateBlank(req.getCode());
    }

}
