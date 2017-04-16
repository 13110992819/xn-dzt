package com.cdkj.dzt.api.impl;

import com.cdkj.dzt.api.AProcessor;
import com.cdkj.dzt.dto.req.XN620208Req;
import com.cdkj.dzt.exception.BizException;
import com.cdkj.dzt.exception.ParaException;

/**
 * 平台提交生产
 * @author: asus 
 * @since: 2017年4月14日 下午5:06:16 
 * @history:
 */
public class XN620208 extends AProcessor {

    private XN620208Req req = null;

    /** 
     * @see com.cdkj.dzt.api.IProcessor#doBusiness()
     */
    @Override
    public Object doBusiness() throws BizException {
        // TODO Auto-generated method stub
        return null;
    }

    /** 
     * @see com.cdkj.dzt.api.IProcessor#doCheck(java.lang.String)
     */
    @Override
    public void doCheck(String inputparams) throws ParaException {
        // TODO Auto-generated method stub

    }

}
