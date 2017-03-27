/**
 * @Title XN808201.java 
 * @Package com.xnjr.mall.api.impl 
 * @Description 
 * @author haiqingzheng  
 * @date 2016年12月17日 上午10:55:49 
 * @version V1.0   
 */
package com.xnjr.mall.api.impl;

import com.xnjr.mall.ao.IStoreAO;
import com.xnjr.mall.api.AProcessor;
import com.xnjr.mall.common.JsonUtil;
import com.xnjr.mall.core.StringValidater;
import com.xnjr.mall.dto.req.XN808201Req;
import com.xnjr.mall.dto.res.PKCodeRes;
import com.xnjr.mall.exception.BizException;
import com.xnjr.mall.exception.ParaException;
import com.xnjr.mall.spring.SpringContextHolder;

/** 
 * 店铺资料提交
 * @author: haiqingzheng 
 * @since: 2016年12月17日 上午10:55:49 
 * @history:
 */
public class XN808201 extends AProcessor {
    private IStoreAO storeAO = SpringContextHolder.getBean(IStoreAO.class);

    private XN808201Req req = null;

    @Override
    public Object doBusiness() throws BizException {
        String code = storeAO.addStore(req);
        return new PKCodeRes(code);
    }

    @Override
    public void doCheck(String inputparams) throws ParaException {
        req = JsonUtil.json2Bean(inputparams, XN808201Req.class);
        StringValidater.validateBlank(req.getName(), req.getType(),
            req.getUserReferee(), req.getRate1(), req.getRate2(),
            req.getSlogan(), req.getAdvPic(), req.getPic(),
            req.getDescription(), req.getProvince(), req.getCity(),
            req.getArea(), req.getAddress(), req.getLongitude(),
            req.getLatitude(), req.getBookMobile(), req.getSmsMobile(),
            req.getOwner(), req.getSystemCode(), req.getCompanyCode());
    }

}
