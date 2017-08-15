package com.cdkj.dzt.api.impl;

import com.cdkj.dzt.ao.ICraftAO;
import com.cdkj.dzt.api.AProcessor;
import com.cdkj.dzt.common.JsonUtil;
import com.cdkj.dzt.core.StringValidater;
import com.cdkj.dzt.dto.req.XN620041Req;
import com.cdkj.dzt.dto.res.BooleanRes;
import com.cdkj.dzt.exception.BizException;
import com.cdkj.dzt.exception.ParaException;
import com.cdkj.dzt.spring.SpringContextHolder;

/**
 * 删除工艺
 * @author: asus 
 * @since: 2017年8月15日 下午9:21:15 
 * @history:
 */
public class XN620041 extends AProcessor {
    private ICraftAO craftAO = SpringContextHolder.getBean(ICraftAO.class);

    private XN620041Req req = null;

    @Override
    public Object doBusiness() throws BizException {
        craftAO.dropCraft(req.getCode());
        return new BooleanRes(true);
    }

    @Override
    public void doCheck(String inputparams) throws ParaException {
        req = JsonUtil.json2Bean(inputparams, XN620041Req.class);
        StringValidater.validateBlank(req.getCode());
    }
}
