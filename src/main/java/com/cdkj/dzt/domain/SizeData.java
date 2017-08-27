package com.cdkj.dzt.domain;

import com.cdkj.dzt.dao.base.ABaseDO;

/**
* 身材数据
* @author: shan 
* @since: 2017-08-13 19:05:49
* @history:
*/
public class SizeData extends ABaseDO {

    private static final long serialVersionUID = 1L;

    // 序号
    private String id;

    // 用户编号
    private String userId;

    // 类型
    private String ckey;

    // 类型名称
    private String cvalue;

    // 值类型
    private String dkey;

    // 值名称
    private String dvalue;

    public void setId(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserId() {
        return userId;
    }

    public String getCkey() {
        return ckey;
    }

    public void setCkey(String ckey) {
        this.ckey = ckey;
    }

    public String getCvalue() {
        return cvalue;
    }

    public void setCvalue(String cvalue) {
        this.cvalue = cvalue;
    }

    public String getDkey() {
        return dkey;
    }

    public void setDkey(String dkey) {
        this.dkey = dkey;
    }

    public String getDvalue() {
        return dvalue;
    }

    public void setDvalue(String dvalue) {
        this.dvalue = dvalue;
    }

}
