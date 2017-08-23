package com.cdkj.dzt.api.impl;

import org.apache.commons.collections.CollectionUtils;

import com.cdkj.dzt.ao.IOrderAO;
import com.cdkj.dzt.api.AProcessor;
import com.cdkj.dzt.common.JsonUtil;
import com.cdkj.dzt.core.StringValidater;
import com.cdkj.dzt.dto.req.XN620205Req;
import com.cdkj.dzt.dto.res.BooleanRes;
import com.cdkj.dzt.exception.BizException;
import com.cdkj.dzt.exception.ParaException;
import com.cdkj.dzt.spring.SpringContextHolder;

/**
 * 产品定价（H+）
 * @author: asus 
 * @since: 2017年8月17日 下午10:02:21 
 * @history:
 */
public class XN620205 extends AProcessor {
    private IOrderAO orderAO = SpringContextHolder.getBean(IOrderAO.class);

    private XN620205Req req = null;

    @Override
    public Object doBusiness() throws BizException {
        orderAO.confirmPrice(req.getOrderCode(), req.getCodeList(),
            StringValidater.toInteger(req.getQuantity()), req.getMap(),
            req.getUpdater(), req.getRemark());
        return new BooleanRes(true);
    }

    @Override
    public void doCheck(String inputparams) throws ParaException {
        req = JsonUtil.json2Bean(inputparams, XN620205Req.class);
        StringValidater.validateBlank(req.getOrderCode(), req.getUpdater());
        StringValidater.validateNumber(req.getQuantity());
        if (CollectionUtils.isEmpty(req.getCodeList())) {
            throw new BizException("xn0000", "面料和工艺编号不能为空");
        }
    }

}
