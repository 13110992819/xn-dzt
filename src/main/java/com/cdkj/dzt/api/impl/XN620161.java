package com.cdkj.dzt.api.impl;

import com.cdkj.dzt.ao.ICommentAO;
import com.cdkj.dzt.api.AProcessor;
import com.cdkj.dzt.common.JsonUtil;
import com.cdkj.dzt.core.StringValidater;
import com.cdkj.dzt.dto.req.XN620161Req;
import com.cdkj.dzt.dto.res.BooleanRes;
import com.cdkj.dzt.exception.BizException;
import com.cdkj.dzt.exception.ParaException;
import com.cdkj.dzt.spring.SpringContextHolder;

/**
 * 删除评论
 * @author: asus 
 * @since: 2017年7月19日 下午3:08:53 
 * @history:
 */
public class XN620161 extends AProcessor {
    private ICommentAO commentAO = SpringContextHolder
        .getBean(ICommentAO.class);

    private XN620161Req req = null;

    @Override
    public Object doBusiness() throws BizException {
        commentAO.dropComment(req.getCode());
        return new BooleanRes(true);
    }

    @Override
    public void doCheck(String inputparams) throws ParaException {
        req = JsonUtil.json2Bean(inputparams, XN620161Req.class);
        StringValidater.validateBlank(req.getCode());
    }

}
