package com.cdkj.dzt.domain;

import com.cdkj.dzt.dao.base.ABaseDO;

/**
* 交互
* @author: shan 
* @since: 2017-08-13 18:58:38
* @history:
*/
public class Interact extends ABaseDO {

	private static final long serialVersionUID = 1L;

	// 编号
	private String code;

	// 分类
	private String category;

	// 类型
	private String type;

	// 互动编号
	private String objectCode;

	// 互动人
	private String operator;

	// 互动时间
	private String operatDatetime;

	public void setCode(String code) {
		this.code = code;
	}

	public String getCode() {
		return code;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getCategory() {
		return category;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getType() {
		return type;
	}

	public void setObjectCode(String objectCode) {
		this.objectCode = objectCode;
	}

	public String getObjectCode() {
		return objectCode;
	}

	public void setOperator(String operator) {
		this.operator = operator;
	}

	public String getOperator() {
		return operator;
	}

	public void setOperatDatetime(String operatDatetime) {
		this.operatDatetime = operatDatetime;
	}

	public String getOperatDatetime() {
		return operatDatetime;
	}

}