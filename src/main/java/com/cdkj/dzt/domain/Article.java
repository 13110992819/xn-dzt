package com.cdkj.dzt.domain;

import com.cdkj.dzt.dao.base.ABaseDO;

/**
* 文章
* @author: shan 
* @since: 2017-08-11 10:04:38
* @history:
*/
public class Article extends ABaseDO {

	private static final long serialVersionUID = 1L;

	// 编号
	private String code;

	// 类型
	private String type;

	// 标题
	private String title;

	// 广告图
	private String pic;

	// 缩略图
	private String advPic;

	// 图文描述
	private String description;

	// UI位置
	private String location;

	// UI顺序
	private String orderNo;

	// 状态
	private String status;

	// 发布人
	private String updater;

	// 发布时间
	private String updateDatetime;

	// 备注
	private String remark;

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

	public void setTitle(String title) {
		this.title = title;
	}

	public String getTitle() {
		return title;
	}

	public void setPic(String pic) {
		this.pic = pic;
	}

	public String getPic() {
		return pic;
	}

	public void setAdvPic(String advPic) {
		this.advPic = advPic;
	}

	public String getAdvPic() {
		return advPic;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getDescription() {
		return description;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getLocation() {
		return location;
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

	public void setUpdater(String updater) {
		this.updater = updater;
	}

	public String getUpdater() {
		return updater;
	}

	public void setUpdateDatetime(String updateDatetime) {
		this.updateDatetime = updateDatetime;
	}

	public String getUpdateDatetime() {
		return updateDatetime;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getRemark() {
		return remark;
	}

}