package com.xnjr.mall.api.impl;

import com.xnjr.mall.ao.IStoreAO;
import com.xnjr.mall.api.AProcessor;
import com.xnjr.mall.common.JsonUtil;
import com.xnjr.mall.core.StringValidater;
import com.xnjr.mall.dto.req.XN808200Req;
import com.xnjr.mall.dto.res.PKCodeRes;
import com.xnjr.mall.exception.BizException;
import com.xnjr.mall.exception.ParaException;
import com.xnjr.mall.spring.SpringContextHolder;

/**
 * 商铺代入驻。直接至审核通过
 * @author: xieyj 
 * @since: 2016年12月27日 下午2:42:17 
 * @history:
 */
public class XN808200 extends AProcessor {
    private IStoreAO storeAO = SpringContextHolder.getBean(IStoreAO.class);

    private XN808200Req req = null;

    /**
     * @see com.xnjr.mall.api.IProcessor#doBusiness()
     */
    @Override
    public Object doBusiness() throws BizException {
        return new PKCodeRes(storeAO.addStoreOss(req));
    }

    /**
     * @see com.xnjr.mall.api.IProcessor#doCheck(java.lang.String)
     */
    @Override
    public void doCheck(String inputparams) throws ParaException {
        req = JsonUtil.json2Bean(inputparams, XN808200Req.class);
        StringValidater.validateBlank(req.getName(), req.getLevel(),
            req.getType(), req.getUserReferee(), req.getRate1(),
            req.getRate2(), req.getSlogan(), req.getAdvPic(), req.getPic(),
            req.getDescription(), req.getProvince(), req.getCity(),
            req.getArea(), req.getAddress(), req.getLongitude(),
            req.getLatitude(), req.getBookMobile(), req.getSmsMobile(),
            req.getMobile(), req.getUpdater(), req.getSystemCode(),
            req.getCompanyCode());
    }
}
