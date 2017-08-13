package com.cdkj.dzt.domain;

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
	private String commentDatetime;

	// 次序
	private String orderNo;

	// 状态
	private String status;

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

	public void setCommentDatetime(String commentDatetime) {
		this.commentDatetime = commentDatetime;
	}

	public String getCommentDatetime() {
		return commentDatetime;
	}

	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}

	public String getOrderNo() {
		return orderNo;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getStatus() {
		return status;
	}

}