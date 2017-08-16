package com.cdkj.dzt.api.impl;

import org.apache.commons.lang.StringUtils;

import com.cdkj.dzt.ao.IClothAO;
import com.cdkj.dzt.api.AProcessor;
import com.cdkj.dzt.common.JsonUtil;
import com.cdkj.dzt.domain.Cloth;
import com.cdkj.dzt.dto.req.XN620032Req;
import com.cdkj.dzt.exception.BizException;
import com.cdkj.dzt.exception.ParaException;
import com.cdkj.dzt.spring.SpringContextHolder;

/**
 * 
 * @author: asus 
 * @since: 2017年8月16日 下午1:27:48 
 * @history:
 */
public class XN620032 extends AProcessor {
    private IClothAO clothAO = SpringContextHolder.getBean(IClothAO.class);

    private XN620032Req req = null;

    @Override
    public Object doBusiness() throws BizException {
        Cloth condition = new Cloth();
        condition.setType(req.getType());
        condition.setBrand(req.getBrand());
        condition.setModelNum(req.getModelNum());
        condition.setColor(req.getColor());
        condition.setFlowers(req.getFlowers());
        condition.setForm(req.getForm());
        condition.setYarn(req.getYarn());
        condition.setLocation(req.getLocation());
        condition.setStatus(req.getStatus());
        condition.setUpdater(req.getUpdater());
        condition.setModelCode(req.getModelCode());
        String orderColumn = req.getOrderColumn();
        if (StringUtils.isBlank(orderColumn)) {
            orderColumn = IClothAO.DEFAULT_ORDER_COLUMN;
        }
        condition.setOrder(orderColumn, req.getOrderDir());
        return clothAO.queryClothList(condition);
    }

    @Override
    public void doCheck(String inputparams) throws ParaException {
        req = JsonUtil.json2Bean(inputparams, XN620032Req.class);
    }

}
