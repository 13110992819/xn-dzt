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

    public User getPartner(String province, String city, String area,
            EUserKind kind);

}
