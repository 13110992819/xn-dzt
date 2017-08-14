package com.cdkj.dzt.domain;

import java.util.Date;
import java.util.List;

import com.cdkj.dzt.dao.base.ABaseDO;

/**
 * 评论
 * @author: asus 
 * @since: 2017年8月11日 下午2:06:13 
 * @history:
 */
public class Comment extends ABaseDO {

    private static final long serialVersionUID = 1L;

    // 编号
    private String code;

    // 类型
    private String type;

    // 得分
    private Integer score;

    // 评论内容
    private String content;

    // 状态
    private String status;

    // 评论人
    private String commer;

    // 评论时间
    private Date commentDatetime;

    // 审核人
    private String approver;

    // 审核时间
    private Date approveDatetime;

    // 备注
    private String remark;

    // 父类编号
    private String parentCode;

    // 顶级编号
    private String topCode;

    // **********db************
    // 评论人名称
    private String commerRealName;

    // 状态List
    private List<String> statusList;

    // 头像
    private String photo;

    public void setCode(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }

    public void setCommer(String commer) {
        this.commer = commer;
    }

    public String getCommer() {
        return commer;
    }

    public void setCommentDatetime(Date commentDatetime) {
        this.commentDatetime = commentDatetime;
    }

    public Date getCommentDatetime() {
        return commentDatetime;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getRemark() {
        return remark;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getApprover() {
        return approver;
    }

    public void setApprover(String approver) {
        this.approver = approver;
    }

    public Date getApproveDatetime() {
        return approveDatetime;
    }

    public void setApproveDatetime(Date approveDatetime) {
        this.approveDatetime = approveDatetime;
    }

    public Integer getScore() {
        return score;
    }

    public void setScore(Integer score) {
        this.score = score;
    }

    public String getCommerRealName() {
        return commerRealName;
    }

    public void setCommerRealName(String commerRealName) {
        this.commerRealName = commerRealName;
    }

    public List<String> getStatusList() {
        return statusList;
    }

    public void setStatusList(List<String> statusList) {
        this.statusList = statusList;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public String getParentCode() {
        return parentCode;
    }

    public void setParentCode(String parentCode) {
        this.parentCode = parentCode;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getTopCode() {
        return topCode;
    }

    public void setTopCode(String topCode) {
        this.topCode = topCode;
    }

}
