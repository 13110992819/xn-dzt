package com.cdkj.dzt.api.impl;

import org.apache.commons.collections.CollectionUtils;

import com.cdkj.dzt.ao.IKeywordAO;
import com.cdkj.dzt.api.AProcessor;
import com.cdkj.dzt.common.JsonUtil;
import com.cdkj.dzt.dto.req.XN620153Req;
import com.cdkj.dzt.dto.res.BooleanRes;
import com.cdkj.dzt.exception.BizException;
import com.cdkj.dzt.exception.ParaException;
import com.cdkj.dzt.spring.SpringContextHolder;

/**
 * 导入
 * @author: asus 
 * @since: 2017年7月19日 下午4:11:57 
 * @history:
 */
public class XN620153 extends AProcessor {
    private IKeywordAO keywordAO = SpringContextHolder
        .getBean(IKeywordAO.class);

    private XN620153Req req = null;

    @Override
    public Object doBusiness() throws BizException {
        keywordAO.addKeyword(req.getReqList());
        return new BooleanRes(true);
    }

    @Override
    public void doCheck(String inputparams) throws ParaException {
        req = JsonUtil.json2Bean(inputparams, XN620153Req.class);
        if (CollectionUtils.isEmpty(req.getReqList())) {
            throw new BizException("xn0000", "内容不能为空");
        }
    }

}
