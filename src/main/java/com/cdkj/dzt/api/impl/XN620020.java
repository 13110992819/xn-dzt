package com.cdkj.dzt.api.impl;

import com.cdkj.dzt.ao.IClothAO;
import com.cdkj.dzt.api.AProcessor;
import com.cdkj.dzt.common.JsonUtil;
import com.cdkj.dzt.core.StringValidater;
import com.cdkj.dzt.dto.req.XN620040Req;
import com.cdkj.dzt.dto.res.PKCodeRes;
import com.cdkj.dzt.exception.BizException;
import com.cdkj.dzt.exception.ParaException;
import com.cdkj.dzt.spring.SpringContextHolder;

/** 
 * 新增面料
 * @author: asus 
 * @since: 2017年4月14日 下午3:15:23 
 * @history:
 */
public class XN620020 extends AProcessor {
    private IClothAO clothAO = SpringContextHolder.getBean(IClothAO.class);

    private XN620040Req req = null;

    /** 
     * @see com.cdkj.dzt.api.IProcessor#doBusiness()
     */
    @Override
    public Object doBusiness() throws BizException {
        return new PKCodeRes(clothAO.addCloth(req));
    }

    /** 
     * @see com.cdkj.dzt.api.IProcessor#doCheck(java.lang.String)
     */
    @Override
    public void doCheck(String inputparams) throws ParaException {
        req = JsonUtil.json2Bean(inputparams, XN620040Req.class);
        StringValidater.validateBlank(req.getType(), req.getName(),
            req.getPic(), req.getUpdater(), req.getModelCode());
        StringValidater.validateAmount(req.getPrice());
    }

}
