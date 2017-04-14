package com.cdkj.dzt.api.impl;

import com.cdkj.dzt.api.AProcessor;
import com.cdkj.dzt.exception.BizException;
import com.cdkj.dzt.exception.ParaException;

public class XNOther extends AProcessor {

    @Override
    public Object doBusiness() throws BizException {
        throw new BizException("702xxx", "无效API功能号");
    }

    @Override
    public void doCheck(String inputparams) throws ParaException {
        throw new ParaException("702xxx", "无效API功能号");

    }

}
