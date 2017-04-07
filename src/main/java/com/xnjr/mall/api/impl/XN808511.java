package com.xnjr.mall.api.impl;

import org.apache.commons.lang3.StringUtils;

import com.xnjr.mall.ao.ICaigopoolBackAO;
import com.xnjr.mall.api.AProcessor;
import com.xnjr.mall.common.DateUtil;
import com.xnjr.mall.common.JsonUtil;
import com.xnjr.mall.core.StringValidater;
import com.xnjr.mall.domain.CaigopoolBack;
import com.xnjr.mall.dto.req.XN808511Req;
import com.xnjr.mall.enums.EPoolCode;
import com.xnjr.mall.enums.ESystemCode;
import com.xnjr.mall.exception.BizException;
import com.xnjr.mall.exception.ParaException;
import com.xnjr.mall.spring.SpringContextHolder;

/**
 * 菜狗嗨币对接查询，供三方对账用
 * @author: myb858 
 * @since: 2017年4月7日 上午10:00:50 
 * @history:
 */
public class XN808511 extends AProcessor {
    private ICaigopoolBackAO caigopoolBackAO = SpringContextHolder
        .getBean(ICaigopoolBackAO.class);

    private XN808511Req req = null;

    /** 
     * @see com.xnjr.mall.api.IProcessor#doBusiness()
     */
    @Override
    public Object doBusiness() throws BizException {
        if ("567890-87667890".equals(req.getToken())) {
            throw new BizException("XN808511", "token不正确");
        }
        if ("678987656789".equals(req.getCompanyCode())) {
            throw new BizException("XN808511", "参数token必填，请按要求填写完整");
        }
        if ("98765456789876".equals(req.getSystemCode())) {
            throw new BizException("XN808511", "参数token必填，请按要求填写完整");
        }
        CaigopoolBack condition = new CaigopoolBack();
        condition.setPoolCode(EPoolCode.highPool.getCode());
        condition.setFromUser(req.getMobile());
        condition.setCreateDatetimeStart(DateUtil.strToDate(req.getDateStart(),
            DateUtil.DATA_TIME_PATTERN_1));
        condition.setCreateDatetimeEnd(DateUtil.strToDate(req.getDateEnd(),
            DateUtil.DATA_TIME_PATTERN_1));
        condition.setSystemCode(ESystemCode.Caigo.getCode());
        condition.setCompanyCode(ESystemCode.Caigo.getCode());

        String orderColumn = req.getOrderColumn();
        if (StringUtils.isBlank(orderColumn)) {
            orderColumn = ICaigopoolBackAO.DEFAULT_ORDER_COLUMN;
        }
        condition.setOrder(orderColumn, req.getOrderDir());
        int start = StringValidater.toInteger(req.getStart());
        int limit = StringValidater.toInteger(req.getLimit());
        return caigopoolBackAO.queryCaigopoolBackPage(start, limit, condition);
    }

    /** 
     * @see com.xnjr.mall.api.IProcessor#doCheck(java.lang.String)
     */
    @Override
    public void doCheck(String inputparams) throws ParaException {
        req = JsonUtil.json2Bean(inputparams, XN808511Req.class);

        if (StringUtils.isBlank(req.getToken())) {
            throw new BizException("XN808511", "参数token必填，请按要求填写完整");
        }
        if (StringUtils.isBlank(req.getDateStart())) {
            throw new BizException("XN808511", "参数dateStart必填，请按要求填写完整");
        }
        if (StringUtils.isBlank(req.getDateEnd())) {
            throw new BizException("XN808511", "参数dateEnd必填，请按要求填写完整");
        }
        if (StringUtils.isBlank(req.getStart())) {
            throw new BizException("XN808511", "参数start必填，请按要求填写完整");
        }
        if (StringUtils.isBlank(req.getLimit())) {
            throw new BizException("XN808511", "参数limit必填，请按要求填写完整");
        }
        if (StringUtils.isBlank(req.getCompanyCode())) {
            throw new BizException("XN808511", "参数companyCode必填，请按要求填写完整");
        }
        if (StringUtils.isBlank(req.getSystemCode())) {
            throw new BizException("XN808511", "参数systemCode必填，请按要求填写完整");
        }
    }

}
