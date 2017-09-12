package com.cdkj.dzt.domain;

import com.cdkj.dzt.dao.base.ABaseDO;

/**
* 订单量体数据
* @author: shan 
* @since: 2017-09-12 23:06:46
* @history:
*/
public class OrderSizeData extends ABaseDO {

	private static final long serialVersionUID = 1L;

	// 序号
	private String id;

	// 订单编号
	private String orderCode;

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

	public void setOrderCode(String orderCode) {
		this.orderCode = orderCode;
	}

	public String getOrderCode() {
		return orderCode;
	}

	public void setCkey(String ckey) {
		this.ckey = ckey;
	}

	public String getCkey() {
		return ckey;
	}

	public void setCvalue(String cvalue) {
		this.cvalue = cvalue;
	}

	public String getCvalue() {
		return cvalue;
	}

	public void setDkey(String dkey) {
		this.dkey = dkey;
	}

	public String getDkey() {
		return dkey;
	}

	public void setDvalue(String dvalue) {
		this.dvalue = dvalue;
	}

	public String getDvalue() {
		return dvalue;
	}

}