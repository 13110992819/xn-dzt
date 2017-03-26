package com.xnjr.mall.bo.impl;

import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.xnjr.mall.bo.IUserBO;
import com.xnjr.mall.bo.base.PaginableBOImpl;
import com.xnjr.mall.domain.User;
import com.xnjr.mall.dto.req.XN805042Req;
import com.xnjr.mall.dto.req.XN805060Req;
import com.xnjr.mall.dto.req.XN805901Req;
import com.xnjr.mall.dto.req.XN805910Req;
import com.xnjr.mall.dto.res.XN805042Res;
import com.xnjr.mall.dto.res.XN805060Res;
import com.xnjr.mall.dto.res.XN805901Res;
import com.xnjr.mall.dto.res.XN805910Res;
import com.xnjr.mall.enums.EUserKind;
import com.xnjr.mall.enums.EUserStatus;
import com.xnjr.mall.exception.BizException;
import com.xnjr.mall.http.BizConnecter;
import com.xnjr.mall.http.JsonUtils;

/**
 * @author: xieyj 
 * @since: 2016年5月30日 上午9:28:30 
 * @history:
 */
@Component
public class UserBOImpl extends PaginableBOImpl<User> implements IUserBO {

    @Override
    public XN805901Res getRemoteUser(String userId) {
        XN805901Req req = new XN805901Req();
        req.setTokenId(userId);
        req.setUserId(userId);
        XN805901Res res = BizConnecter.getBizData("805901",
            JsonUtils.object2Json(req), XN805901Res.class);
        if (res == null) {
            throw new BizException("XN000000", "编号为" + userId + "的用户不存在");
        }
        return res;
    }

    @Override
    public XN805060Res getPartnerUser(String province, String city,
            String area, EUserKind kind) {
        // 只有省 province，city,area=省
        // 有省市 area=市
        if (StringUtils.isBlank(city) && StringUtils.isBlank(area)) {
            city = province;
            area = province;
        } else if (StringUtils.isBlank(area)) {
            area = city;
        }
        XN805060Req req = new XN805060Req();
        req.setProvince(province);
        req.setCity(city);
        req.setArea(area);
        req.setKind(kind.getCode());
        req.setStatus(EUserStatus.NORMAL.getCode());

        XN805060Res result = null;
        String jsonStr = BizConnecter.getBizData("805060",
            JsonUtils.object2Json(req));
        Gson gson = new Gson();
        List<XN805060Res> list = gson.fromJson(jsonStr,
            new TypeToken<List<XN805060Res>>() {
            }.getType());
        if (CollectionUtils.isNotEmpty(list)) {
            result = list.get(0);
        }
        return result;
    }

    @Override
    public String isUserExist(String mobile, EUserKind kind, String systemCode) {
        String userId = null;
        XN805910Req req = new XN805910Req();
        req.setMobile(mobile);
        req.setKind(kind.getCode());
        req.setSystemCode(systemCode);
        XN805910Res res = BizConnecter.getBizData("805910",
            JsonUtils.object2Json(req), XN805910Res.class);
        if (res != null) {
            userId = res.getUserId();
        }
        return userId;
    }

    @Override
    public String doSaveBUser(String mobile, String updater, String systemCode,
            String companyCode) {
        XN805042Req req = new XN805042Req();
        req.setLoginName(mobile);
        req.setMobile(mobile);
        req.setKind(EUserKind.F2.getCode());
        req.setUpdater(updater);
        req.setRemark("代注册商家");
        req.setSystemCode(systemCode);
        req.setCompanyCode(companyCode);
        XN805042Res res = BizConnecter.getBizData("805042",
            JsonUtils.object2Json(req), XN805042Res.class);
        return res.getUserId();

    }
}
