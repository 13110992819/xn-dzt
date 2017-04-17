package com.cdkj.dzt.bo;

import com.cdkj.dzt.domain.User;
import com.cdkj.dzt.enums.EUserKind;

/**
 * @author: xieyj 
 * @since: 2016年5月30日 上午9:28:13 
 * @history:
 */
public interface IUserBO {

    public User getRemoteUser(String userId);

    public String isUserExist(String mobile, EUserKind kind, String systemCode);

    public User getPartner(String province, String city, String area,
            EUserKind kind);

    public String doSaveCUser(String mobile, String loginPwd, String updater,
            String remark, String systemCode);

    public String doSaveBUser(String mobile, String updater, String systemCode,
            String companyCode);

}
