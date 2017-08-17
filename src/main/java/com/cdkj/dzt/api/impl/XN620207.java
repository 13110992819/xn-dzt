/**
 * @Title XN620205.java 
 * @Package com.cdkj.dzt.api.impl 
 * @Description 
 * @author leo(haiqing)  
 * @date 2017年4月14日 下午1:59:01 
 * @version V1.0   
 */
package com.cdkj.dzt.api.impl;

import org.apache.commons.collections.CollectionUtils;

import com.cdkj.dzt.ao.IOrderAO;
import com.cdkj.dzt.api.AProcessor;
import com.cdkj.dzt.common.JsonUtil;
import com.cdkj.dzt.core.StringValidater;
import com.cdkj.dzt.dto.req.XN620207Req;
import com.cdkj.dzt.dto.res.BooleanRes;
import com.cdkj.dzt.exception.BizException;
import com.cdkj.dzt.exception.ParaException;
import com.cdkj.dzt.spring.SpringContextHolder;

/** 
 * 数据录入
 * @author: haiqingzheng 
 * @since: 2017年4月14日 下午1:59:01 
 * @history:
 */
public class XN620207 extends AProcessor {
    private IOrderAO orderAO = SpringContextHolder.getBean(IOrderAO.class);

    private XN620207Req req = null;

    /** 
     * @see com.cdkj.dzt.api.IProcessor#doBusiness()
     */
    @Override
    public Object doBusiness() throws BizException {
        orderAO.inputInfor(req.getOrderCode(), req.getCodeList(), req.getMap(),
            req.getUpdater(), req.getRemark());
        return new BooleanRes(true);
    }

    /** 
     * @see com.cdkj.dzt.api.IProcessor#doCheck(java.lang.String)
     */
    @Override
    public void doCheck(String inputparams) throws ParaException {
        req = JsonUtil.json2Bean(inputparams, XN620207Req.class);
        StringValidater.validateBlank(req.getOrderCode(), req.getUpdater());
        if (CollectionUtils.isEmpty(req.getCodeList())) {
            throw new BizException("xn0000", "面料与工艺不能为空");
        }
    }
}
