/**
 * @Title XN620005.java 
 * @Package com.cdkj.dzt.api.impl 
 * @Description 
 * @author leo(haiqing)  
 * @date 2017年4月14日 下午2:51:29 
 * @version V1.0   
 */
package com.cdkj.dzt.api.impl;

import org.apache.commons.lang3.StringUtils;

import com.cdkj.dzt.ao.IModelAO;
import com.cdkj.dzt.api.AProcessor;
import com.cdkj.dzt.common.JsonUtil;
import com.cdkj.dzt.core.StringValidater;
import com.cdkj.dzt.domain.Model;
import com.cdkj.dzt.dto.req.XN620010Req;
import com.cdkj.dzt.exception.BizException;
import com.cdkj.dzt.exception.ParaException;
import com.cdkj.dzt.spring.SpringContextHolder;

/** 
 * 分页获取型号
 * @author: haiqingzheng 
 * @since: 2017年4月14日 下午2:51:29 
 * @history:
 */
public class XN620010 extends AProcessor {
    private IModelAO modelAO = SpringContextHolder.getBean(IModelAO.class);

    private XN620010Req req = null;

    @Override
    public Object doBusiness() throws BizException {
        Model condition = new Model();
        condition.setType(req.getType());
        condition.setStatus(req.getStatus());
        condition.setName(req.getName());
        condition.setUpdater(req.getUpdater());
        condition.setLocation(req.getLocation());
        String orderColumn = req.getOrderColumn();
        if (StringUtils.isBlank(orderColumn)) {
            orderColumn = IModelAO.DEFAULT_ORDER_COLUMN;
        }
        condition.setOrder(orderColumn, req.getOrderDir());
        int start = StringValidater.toInteger(req.getStart());
        int limit = StringValidater.toInteger(req.getLimit());
        return modelAO.queryModelPage(start, limit, condition, req.getUserId());
    }

    @Override
    public void doCheck(String inputparams) throws ParaException {
        req = JsonUtil.json2Bean(inputparams, XN620010Req.class);
        StringValidater.validateNumber(req.getStart(), req.getLimit());
    }

}
