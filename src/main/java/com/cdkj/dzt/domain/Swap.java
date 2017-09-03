package com.cdkj.dzt.domain;

import java.util.Date;

import com.cdkj.dzt.dao.base.ABaseDO;

/**
* 交流
* @author: shan 
* @since: 2017-08-13 18:28:11
* @history:
*/
public class Swap extends ABaseDO {

    private static final long serialVersionUID = 1L;

    // 编号
    private String code;

    // 类型
    private String type;

    // 留言人
    private String commenter;

    // 接收人
    private String receiver;

    // 留言内容
    private String content;

    // 留言时间
    private Date commentDatetime;

    // 次序
    private Integer orderNo;

    // 状态
    private String status;

    // 是否最新
    private String isNew;

    // ***************DB************
    // 查看人
    private String lookUser;

    // 查看人
    private String lookUsers;

    // 留言人姓名
    private String commentName;

    // 留言人手机号
    private String commentMobile;

    // 结收人姓名
    private String receiveName;

    // 结收人手机号
    private String receiveMobile;

    // 留言人头像
    private String commentPhoto;

    // 结收人头像
    private String receivePhoto;

    public void setCode(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }

    public void setCommenter(String commenter) {
        this.commenter = commenter;
    }

    public String getCommenter() {
        return commenter;
    }

    public void setReceiver(String receiver) {
        this.receiver = receiver;
    }

    public String getReceiver() {
        return receiver;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getContent() {
        return content;
    }

    public void setCommentDatetime(Date commentDatetime) {
        this.commentDatetime = commentDatetime;
    }

    public Date getCommentDatetime() {
        return commentDatetime;
    }

    public void setOrderNo(Integer orderNo) {
        this.orderNo = orderNo;
    }

    public Integer getOrderNo() {
        return orderNo;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getStatus() {
        return status;
    }

    public String getLookUser() {
        return lookUser;
    }

    public void setLookUser(String lookUser) {
        this.lookUser = lookUser;
    }

    public String getCommentName() {
        return commentName;
    }

    public void setCommentName(String commentName) {
        this.commentName = commentName;
    }

    public String getReceiveName() {
        return receiveName;
    }

    public void setReceiveName(String receiveName) {
        this.receiveName = receiveName;
    }

    public String getCommentMobile() {
        return commentMobile;
    }

    public void setCommentMobile(String commentMobile) {
        this.commentMobile = commentMobile;
    }

    public String getReceiveMobile() {
        return receiveMobile;
    }

    public void setReceiveMobile(String receiveMobile) {
        this.receiveMobile = receiveMobile;
    }

    public String getCommentPhoto() {
        return commentPhoto;
    }

    public void setCommentPhoto(String commentPhoto) {
        this.commentPhoto = commentPhoto;
    }

    public String getReceivePhoto() {
        return receivePhoto;
    }

    public void setReceivePhoto(String receivePhoto) {
        this.receivePhoto = receivePhoto;
    }

    public String getIsNew() {
        return isNew;
    }

    public void setIsNew(String isNew) {
        this.isNew = isNew;
    }

    public String getLookUsers() {
        return lookUsers;
    }

    public void setLookUsers(String lookUsers) {
        this.lookUsers = lookUsers;
    }

}
