package com.cdkj.dzt.bo.impl;

import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import com.cdkj.dzt.bo.IUserBO;
import com.cdkj.dzt.domain.User;
import com.cdkj.dzt.dto.req.XN001102Req;
import com.cdkj.dzt.dto.req.XN001301Req;
import com.cdkj.dzt.dto.req.XN001350Req;
import com.cdkj.dzt.dto.req.XN001400Req;
import com.cdkj.dzt.dto.req.XN001403Req;
import com.cdkj.dzt.dto.res.XN001102Res;
import com.cdkj.dzt.dto.res.XN001400Res;
import com.cdkj.dzt.dto.res.XN001403Res;
import com.cdkj.dzt.dto.res.XNUserRes;
import com.cdkj.dzt.enums.EBoolean;
import com.cdkj.dzt.enums.ESysUser;
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
    public User getRemoteUser(String userId) {
        User user = null;
        if (StringUtils.isNotBlank(userId)) {
            XN001400Req req = new XN001400Req();
            req.setTokenId(userId);
            req.setUserId(userId);
            XN001400Res res = BizConnecter.getBizData("001400",
                JsonUtils.object2Json(req), XN001400Res.class);
            if (res == null) {
                throw new BizException("XN000000", "编号为" + userId + "的用户不存在");
            }
            user = new User();
            user.setUserId(res.getUserId());
            user.setOpenId(res.getOpenId());
            user.setLoginName(res.getLoginName());
            user.setNickname(res.getNickname());
            user.setPhoto(res.getPhoto());
            user.setMobile(res.getMobile());
            user.setIdentityFlag(res.getIdentityFlag());
            user.setUserReferee(res.getUserReferee());
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
        String jsonStr = BizConnecter.getBizData("001403",
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
            user.setLoginName(result.getLoginName());
            user.setMobile(result.getMobile());
        }
        return user;
    }

    @Override
    public String isUserExist(String mobile, EUserKind kind, String systemCode) {
        String userId = null;
        XN001102Req req = new XN001102Req();
        req.setMobile(mobile);
        req.setKind(kind.getCode());
        req.setSystemCode(systemCode);
        XN001102Res res = BizConnecter.getBizData("001102",
            JsonUtils.object2Json(req), XN001102Res.class);
        if (res != null) {
            userId = res.getUserId();
        }
        return userId;
    }

    @Override
    public String doSaveBUser(String mobile, String updater, String systemCode,
            String companyCode) {
        XN001350Req req = new XN001350Req();
        req.setLoginName(mobile);
        req.setMobile(mobile);
        req.setUpdater(updater);
        req.setRemark("代注册商家");
        if (ESystemCode.DZT.getCode().equals(systemCode)) {
            req.setIsRegHx(EBoolean.YES.getCode());
        }
        req.setSystemCode(systemCode);
        req.setCompanyCode(companyCode);
        XNUserRes res = BizConnecter.getBizData("001350",
            JsonUtils.object2Json(req), XNUserRes.class);
        return res.getUserId();
    }

    @Override
    public String doSaveCUser(String mobile, String loginPwd, String updater,
            String remark, String systemCode) {
        XN001301Req req = new XN001301Req();
        req.setMobile(mobile);
        req.setLoginPwd(loginPwd);
        req.setUpdater(updater);
        req.setUserReferee(ESysUser.SYS_USER_DZT.getCode());
        req.setRemark(remark);
        req.setCompanyCode(systemCode);
        req.setSystemCode(systemCode);
        XNUserRes res = BizConnecter.getBizData("001301",
            JsonUtils.object2Json(req), XNUserRes.class);
        return res.getUserId();
    }

}
