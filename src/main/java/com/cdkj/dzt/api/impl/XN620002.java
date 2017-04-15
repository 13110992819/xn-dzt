/**
 * @Title XN620002.java 
 * @Package com.cdkj.dzt.api.impl 
 * @Description 
 * @author leo(haiqing)  
 * @date 2017年4月14日 下午2:48:11 
 * @version V1.0   
 */
package com.cdkj.dzt.api.impl;

import com.cdkj.dzt.ao.IModelAO;
import com.cdkj.dzt.api.AProcessor;
import com.cdkj.dzt.common.JsonUtil;
import com.cdkj.dzt.core.StringValidater;
import com.cdkj.dzt.dto.req.XN620002Req;
import com.cdkj.dzt.exception.BizException;
import com.cdkj.dzt.exception.ParaException;
import com.cdkj.dzt.spring.SpringContextHolder;

/** 
 * 修改型号
 * @author: haiqingzheng 
 * @since: 2017年4月14日 下午2:48:11 
 * @history:
 */
public class XN620002 extends AProcessor {
    private IModelAO modelAO = SpringContextHolder.getBean(IModelAO.class);

    private XN620002Req req = null;

    @Override
    public Object doBusiness() throws BizException {
        modelAO.editModel(req.getCode(), req.getName(), req.getPic(),
            StringValidater.toLong(req.getPrice()), req.getUpdater(),
            req.getRemark());
        return null;
    }

    @Override
    public void doCheck(String inputparams) throws ParaException {
        req = JsonUtil.json2Bean(inputparams, XN620002Req.class);
        StringValidater.validateBlank(req.getCode(), req.getName(),
            req.getPic(), req.getUpdater());
        StringValidater.validateAmount(req.getPrice());
    }

}
