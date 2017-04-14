package com.cdkj.dzt.domain;

import com.cdkj.dzt.dao.base.ABaseDO;

/**
* 成衣规格
* @author: shan
* @since: 2017年04月14日
* @history:
*/
public class ProductSpecs extends ABaseDO {

	private static final long serialVersionUID = 1L;

	// 编号
	private String code;

	// 名称
	private String name;

	// key
	private String parentCode;

	// 类型
	private String type;

	// 图片
	private String pic;

	// 成衣编号
	private String productCode;

	// 订单编号
	private String orderCode;

	public void setCode(String code) {
		this.code = code;
	}

	public String getCode() {
		return code;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setParentCode(String parentCode) {
		this.parentCode = parentCode;
	}

	public String getParentCode() {
		return parentCode;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getType() {
		return type;
	}

	public void setPic(String pic) {
		this.pic = pic;
	}

	public String getPic() {
		return pic;
	}

	public void setProductCode(String productCode) {
		this.productCode = productCode;
	}

	public String getProductCode() {
		return productCode;
	}

	public void setOrderCode(String orderCode) {
		this.orderCode = orderCode;
	}

	public String getOrderCode() {
		return orderCode;
	}

}