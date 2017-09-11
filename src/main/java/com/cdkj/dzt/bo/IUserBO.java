package com.cdkj.dzt.bo;

import java.util.List;

import com.cdkj.dzt.domain.User;
import com.cdkj.dzt.dto.res.XN001400Res;
import com.cdkj.dzt.enums.EUserKind;

/**
 * @author: xieyj 
 * @since: 2016年5月30日 上午9:28:13 
 * @history:
 */
public interface IUserBO {

    public XN001400Res getRemoteUser(String userId);

    public User getPartner(String province, String city, String area,
            EUserKind kind);

    public void doUpLevel(String userId, String level);

    // 更新最后下单时间
    public void refreshLastOrderDatetime(String userId, String lastOrderDatetime);

    // 更新用户活跃度
    public void refreshFrequent(String userId, String frequent);

    public List<XN001400Res> queryUserList();

    // 修改真实姓名
    public void modifyRealName(String userId, String realName);

    // 修改归属合伙人
    public void refreshToUser(String userId, String toUser);

    // 修改归属量体师
    public void refreshLtUser(String userId, String ltUser);
}
