package com.cdkj.dzt.api.impl;

import org.apache.commons.collections.CollectionUtils;

import com.cdkj.dzt.ao.IOrderAO;
import com.cdkj.dzt.api.AProcessor;
import com.cdkj.dzt.common.JsonUtil;
import com.cdkj.dzt.core.StringValidater;
import com.cdkj.dzt.dto.req.XN620204Req;
import com.cdkj.dzt.exception.BizException;
import com.cdkj.dzt.exception.ParaException;
import com.cdkj.dzt.spring.SpringContextHolder;

/**
 * H+价格计算
 * @author: asus 
 * @since: 2017年8月17日 下午8:52:12 
 * @history:
 */
public class XN620204 extends AProcessor {
    private IOrderAO orderAO = SpringContextHolder.getBean(IOrderAO.class);

    private XN620204Req req = null;

    @Override
    public Object doBusiness() throws BizException {
        orderAO.calculatePrice(req.getOrderCode(), req.getCodeList(),
            req.getQuantity());
        return null;
    }

    @Override
    public void doCheck(String inputparams) throws ParaException {
        req = JsonUtil.json2Bean(inputparams, XN620204Req.class);
        if (CollectionUtils.isEmpty(req.getCodeList())) {
            throw new BizException("xn0000", "编号不能为空");
        }
        StringValidater.validateBlank(req.getOrderCode(), req.getQuantity());
    }

}
