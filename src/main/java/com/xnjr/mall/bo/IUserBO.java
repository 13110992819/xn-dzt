package com.xnjr.mall.bo;

import com.xnjr.mall.dto.res.XN805060Res;
import com.xnjr.mall.dto.res.XN805901Res;
import com.xnjr.mall.enums.EUserKind;

/**
 * @author: xieyj 
 * @since: 2016年5月30日 上午9:28:13 
 * @history:
 */
public interface IUserBO {

    public XN805901Res getRemoteUser(String userId);

    /**
     * 根据手机号获取用户编号
     * @param mobile
     * @param kind
     * @param systemCode
     * @return 
     * @create: 2016年12月28日 上午10:09:53 xieyj
     * @history:
     */
    public String isUserExist(String mobile, EUserKind kind, String systemCode);

    public XN805060Res getPartnerUser(String province, String city,
            String area, EUserKind kind);

    public String doSaveBUser(String mobile, String updater, String systemCode,
            String companyCode);
}
