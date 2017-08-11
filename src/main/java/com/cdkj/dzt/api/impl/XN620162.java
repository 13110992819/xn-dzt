package com.cdkj.dzt.api.impl;

import com.cdkj.dzt.ao.ICommentAO;
import com.cdkj.dzt.api.AProcessor;
import com.cdkj.dzt.common.JsonUtil;
import com.cdkj.dzt.core.StringValidater;
import com.cdkj.dzt.dto.req.XN620162Req;
import com.cdkj.dzt.dto.res.BooleanRes;
import com.cdkj.dzt.exception.BizException;
import com.cdkj.dzt.exception.ParaException;
import com.cdkj.dzt.spring.SpringContextHolder;

/**
 * 评论审核
 * @author: asus 
 * @since: 2017年7月19日 下午3:19:45 
 * @history:
 */
public class XN620162 extends AProcessor {
    private ICommentAO commentAO = SpringContextHolder
        .getBean(ICommentAO.class);

    private XN620162Req req = null;

    @Override
    public Object doBusiness() throws BizException {
        commentAO.approveComment(req.getCode(), req.getResult(),
            req.getApprover(), req.getRemark());
        return new BooleanRes(true);
    }

    @Override
    public void doCheck(String inputparams) throws ParaException {
        req = JsonUtil.json2Bean(inputparams, XN620162Req.class);
        StringValidater.validateBlank(req.getCode(), req.getResult(),
            req.getApprover());
    }

}
