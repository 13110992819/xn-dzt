package com.cdkj.dzt.api.impl;

import com.cdkj.dzt.ao.IClothAO;
import com.cdkj.dzt.api.AProcessor;
import com.cdkj.dzt.common.JsonUtil;
import com.cdkj.dzt.core.StringValidater;
import com.cdkj.dzt.dto.req.XN620033Req;
import com.cdkj.dzt.exception.BizException;
import com.cdkj.dzt.exception.ParaException;
import com.cdkj.dzt.spring.SpringContextHolder;

/**
 * 详情查询面料(front)
 * @author: asus 
 * @since: 2017年8月16日 下午1:37:15 
 * @history:
 */
public class XN620033 extends AProcessor {
    private IClothAO clothAO = SpringContextHolder.getBean(IClothAO.class);

    private XN620033Req req = null;

    @Override
    public Object doBusiness() throws BizException {
        return clothAO.getCloth(req.getCode(), req.getUserId());
    }

    @Override
    public void doCheck(String inputparams) throws ParaException {
        req = JsonUtil.json2Bean(inputparams, XN620033Req.class);
        StringValidater.validateBlank(req.getCode());
    }

}
