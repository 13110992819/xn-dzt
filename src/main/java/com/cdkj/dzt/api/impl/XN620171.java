package com.cdkj.dzt.api.impl;

import com.cdkj.dzt.ao.ICommentAO;
import com.cdkj.dzt.api.AProcessor;
import com.cdkj.dzt.common.JsonUtil;
import com.cdkj.dzt.core.StringValidater;
import com.cdkj.dzt.dto.req.XN620171Req;
import com.cdkj.dzt.exception.BizException;
import com.cdkj.dzt.exception.ParaException;
import com.cdkj.dzt.spring.SpringContextHolder;

/**
 * 详情查询评论
 * @author: asus 
 * @since: 2017年7月19日 下午3:48:36 
 * @history:
 */
public class XN620171 extends AProcessor {
    private ICommentAO commentAO = SpringContextHolder
        .getBean(ICommentAO.class);

    private XN620171Req req = null;

    @Override
    public Object doBusiness() throws BizException {
        return commentAO.getComment(req.getCode());
    }

    @Override
    public void doCheck(String inputparams) throws ParaException {
        req = JsonUtil.json2Bean(inputparams, XN620171Req.class);
        StringValidater.validateBlank(req.getCode());
    }

}
