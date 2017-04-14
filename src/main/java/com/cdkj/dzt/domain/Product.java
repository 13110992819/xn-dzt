package com.cdkj.dzt.domain;

import com.cdkj.dzt.dao.base.ABaseDO;

/**
* 成衣
* @author: shan
* @since: 2017年04月14日
* @history:
*/
public class Product extends ABaseDO {

	private static final long serialVersionUID = 1L;

	// 编号
	private String code;

	// 订单编号
	private String orderCode;

	// 产品名字
	private String modelName;

	// 产品图片
	private String modelPic;

	// 单价
	private String price;

	// 数量
	private String quantity;

	public void setCode(String code) {
		this.code = code;
	}

	public String getCode() {
		return code;
	}

	public void setOrderCode(String orderCode) {
		this.orderCode = orderCode;
	}

	public String getOrderCode() {
		return orderCode;
	}

	public void setModelName(String modelName) {
		this.modelName = modelName;
	}

	public String getModelName() {
		return modelName;
	}

	public void setModelPic(String modelPic) {
		this.modelPic = modelPic;
	}

	public String getModelPic() {
		return modelPic;
	}

	public void setPrice(String price) {
		this.price = price;
	}

	public String getPrice() {
		return price;
	}

	public void setQuantity(String quantity) {
		this.quantity = quantity;
	}

	public String getQuantity() {
		return quantity;
	}

}