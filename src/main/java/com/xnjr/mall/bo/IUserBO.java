package com.xnjr.mall.bo;

import com.xnjr.mall.domain.User;
import com.xnjr.mall.enums.EUserKind;

/**
 * @author: xieyj 
 * @since: 2016年5月30日 上午9:28:13 
 * @history:
 */
public interface IUserBO {

    public User getRemoteUser(String userId);

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

    public User getPartner(String province, String city, String area,
            EUserKind kind);

    public String doSaveBUser(String mobile, String updater, String systemCode,
            String companyCode);
}
