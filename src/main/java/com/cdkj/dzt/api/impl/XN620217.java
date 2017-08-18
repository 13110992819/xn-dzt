package com.cdkj.dzt.api.impl;

import com.cdkj.dzt.ao.ISizeDataAO;
import com.cdkj.dzt.api.AProcessor;
import com.cdkj.dzt.common.JsonUtil;
import com.cdkj.dzt.core.StringValidater;
import com.cdkj.dzt.domain.SizeData;
import com.cdkj.dzt.dto.req.XN620217Req;
import com.cdkj.dzt.exception.BizException;
import com.cdkj.dzt.exception.ParaException;
import com.cdkj.dzt.spring.SpringContextHolder;

/**
 * 用户专属报告
 * @author: asus 
 * @since: 2017年8月19日 上午12:02:36 
 * @history:
 */
public class XN620217 extends AProcessor {
    private ISizeDataAO sizedataAO = SpringContextHolder
        .getBean(ISizeDataAO.class);

    private XN620217Req req = null;

    @Override
    public Object doBusiness() throws BizException {
        SizeData condition = new SizeData();
        condition.setUserId(req.getUserId());
        return sizedataAO.querySizeDataList(condition);
    }

    @Override
    public void doCheck(String inputparams) throws ParaException {
        req = JsonUtil.json2Bean(inputparams, XN620217Req.class);
        StringValidater.validateBlank(req.getUserId());
    }

}
