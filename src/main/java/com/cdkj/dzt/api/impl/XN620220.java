package com.cdkj.dzt.api.impl;

import com.cdkj.dzt.ao.ISizeDataAO;
import com.cdkj.dzt.api.AProcessor;
import com.cdkj.dzt.common.JsonUtil;
import com.cdkj.dzt.core.StringValidater;
import com.cdkj.dzt.dto.req.XN620220Req;
import com.cdkj.dzt.dto.res.BooleanRes;
import com.cdkj.dzt.exception.BizException;
import com.cdkj.dzt.exception.ParaException;
import com.cdkj.dzt.spring.SpringContextHolder;

/**
 * 修改用户专属报告
 * @author: asus 
 * @since: 2017年8月27日 下午4:15:16 
 * @history:
 */
public class XN620220 extends AProcessor {
    private ISizeDataAO sizeDataAO = SpringContextHolder
        .getBean(ISizeDataAO.class);

    private XN620220Req req = null;

    @Override
    public Object doBusiness() throws BizException {
        sizeDataAO.addSizeData(req.getUserId(), req.getMap());
        return new BooleanRes(true);
    }

    @Override
    public void doCheck(String inputparams) throws ParaException {
        req = JsonUtil.json2Bean(inputparams, XN620220Req.class);
        StringValidater.validateBlank(req.getUserId());
    }

}
