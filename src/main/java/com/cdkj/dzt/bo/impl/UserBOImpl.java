package com.cdkj.dzt.bo.impl;

import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import com.cdkj.dzt.bo.IUserBO;
import com.cdkj.dzt.domain.User;
import com.cdkj.dzt.dto.req.XN001302Req;
import com.cdkj.dzt.dto.req.XN001304Req;
import com.cdkj.dzt.dto.req.XN001305Req;
import com.cdkj.dzt.dto.req.XN001306Req;
import com.cdkj.dzt.dto.req.XN001307Req;
import com.cdkj.dzt.dto.req.XN001308Req;
import com.cdkj.dzt.dto.req.XN001400Req;
import com.cdkj.dzt.dto.req.XN001401Req;
import com.cdkj.dzt.dto.req.XN001403Req;
import com.cdkj.dzt.dto.res.XN001400Res;
import com.cdkj.dzt.dto.res.XN001403Res;
import com.cdkj.dzt.enums.ESystemCode;
import com.cdkj.dzt.enums.EUserKind;
import com.cdkj.dzt.exception.BizException;
import com.cdkj.dzt.http.BizConnecter;
import com.cdkj.dzt.http.JsonUtils;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

/**
 * @author: xieyj 
 * @since: 2016年5月30日 上午9:28:30 
 * @history:
 */
@Component
public class UserBOImpl implements IUserBO {

    @Override
    public XN001400Res getRemoteUser(String userId) {
        XN001400Res user = null;
        if (StringUtils.isNotBlank(userId)) {
            XN001400Req req = new XN001400Req();
            req.setTokenId(userId);
            req.setUserId(userId);
            user = BizConnecter.getBizData("001400",
                JsonUtils.object2Json(req), XN001400Res.class);
            if (user == null) {
                throw new BizException("XN000000", "编号为" + userId + "的用户不存在");
            }
        }
        return user;
    }

    @Override
    public User getPartner(String province, String city, String area,
            EUserKind kind) {
        if (StringUtils.isBlank(city) && StringUtils.isBlank(area)) {
            city = province;
            area = province;
        } else if (StringUtils.isBlank(area)) {
            area = city;
        }
        XN001403Req req = new XN001403Req();
        req.setProvince(province);
        req.setCity(city);
        req.setArea(area);
        req.setKind(kind.getCode());
        req.setSystemCode(ESystemCode.DZT.getCode());
        req.setCompanyCode(ESystemCode.DZT.getCode());
        XN001403Res result = null;
        String jsonStr = BizConnecter.getBizData("805123",
            JsonUtils.object2Json(req));
        Gson gson = new Gson();
        List<XN001403Res> list = gson.fromJson(jsonStr,
            new TypeToken<List<XN001403Res>>() {
            }.getType());
        User user = null;
        if (CollectionUtils.isNotEmpty(list)) {
            result = list.get(0);
            user = new User();
            user.setUserId(result.getUserId());
            user.setMobile(result.getMobile());
        }
        return user;
    }

    @Override
    public void doUpLevel(String userId, String level) {
        XN001302Req req = new XN001302Req();
        req.setUserId(userId);
        req.setLevel(level);
        BizConnecter.getBizData("001302", JsonUtils.object2Json(req),
            Object.class);
    }

    // 更新最后下单时间
    @Override
    public void refreshLastOrderDatetime(String userId, String lastOrderDatetime) {
        XN001304Req req = new XN001304Req();
        req.setUserId(userId);
        req.setLastOrderDatetime(lastOrderDatetime);
        BizConnecter.getBizData("001304", JsonUtils.object2Json(req),
            Object.class);
    }

    // 更新用户活跃度
    @Override
    public void refreshFrequent(String userId, String frequent) {
        XN001305Req req = new XN001305Req();
        req.setUserId(userId);
        req.setFrequent(frequent);
        BizConnecter.getBizData("001305", JsonUtils.object2Json(req),
            Object.class);
    }

    @Override
    public List<XN001400Res> queryUserList() {
        XN001401Req req = new XN001401Req();
        req.setSystemCode(ESystemCode.DZT.getCode());
        req.setCompanyCode(ESystemCode.DZT.getCode());
        String jsonStr = BizConnecter.getBizData("001401",
            JsonUtils.object2Json(req));
        Gson gson = new Gson();
        List<XN001400Res> list = gson.fromJson(jsonStr,
            new TypeToken<List<XN001400Res>>() {
            }.getType());
        return list;
    }

    @Override
    public void modifyRealName(String userId, String realName) {
        XN001306Req req = new XN001306Req();
        req.setUserId(userId);
        req.setRealName(realName);
        BizConnecter.getBizData("001306", JsonUtils.object2Json(req),
            Object.class);
    }

    @Override
    public void refreshToUser(String userId, String toUser) {
        XN001307Req req = new XN001307Req();
        req.setUserId(userId);
        req.setToUser(toUser);
        BizConnecter.getBizData("001307", JsonUtils.object2Json(req),
            Object.class);
    }

    @Override
    public void refreshLtUser(String userId, String ltUser) {
        XN001308Req req = new XN001308Req();
        req.setUserId(userId);
        req.setLtUser(ltUser);
        BizConnecter.getBizData("001308", JsonUtils.object2Json(req),
            Object.class);
    }
}
