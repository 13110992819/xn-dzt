package com.cdkj.dzt.api.impl;

import org.apache.commons.collections.CollectionUtils;

import com.cdkj.dzt.ao.IClothAO;
import com.cdkj.dzt.api.AProcessor;
import com.cdkj.dzt.common.JsonUtil;
import com.cdkj.dzt.core.StringValidater;
import com.cdkj.dzt.dto.req.XN620023Req;
import com.cdkj.dzt.dto.res.BooleanRes;
import com.cdkj.dzt.exception.BizException;
import com.cdkj.dzt.exception.ParaException;
import com.cdkj.dzt.spring.SpringContextHolder;

/** 
 * 上架面料
 * @author: asus 
 * @since: 2017年4月14日 下午3:15:23 
 * @history:
 */
public class XN620023 extends AProcessor {
    private IClothAO clothAO = SpringContextHolder.getBean(IClothAO.class);

    private XN620023Req req = null;

    /** 
     * @see com.cdkj.dzt.api.IProcessor#doBusiness()
     */
    @Override
    public Object doBusiness() throws BizException {
        clothAO.putOn(req.getCodeList(), req.getLocation(), req.getOrderNo(),
            req.getUpdater(), req.getRemark());
        return new BooleanRes(true);
    }

    /** 
     * @see com.cdkj.dzt.api.IProcessor#doCheck(java.lang.String)
     */
    @Override
    public void doCheck(String inputparams) throws ParaException {
        req = JsonUtil.json2Bean(inputparams, XN620023Req.class);
        StringValidater.validateBlank(req.getLocation(), req.getOrderNo(),
            req.getUpdater());
        if (CollectionUtils.isEmpty(req.getCodeList())) {
            throw new BizException("xn0000", "您还没有选择面料");
        }
    }

}
