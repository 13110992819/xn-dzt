
SET NAMES utf8;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
--  Table structure for `tdzt_model`
-- ----------------------------
DROP TABLE IF EXISTS `tdzt_model`;
CREATE TABLE `tdzt_model` (
  `code` varchar(32) NOT NULL COMMENT '编号',
  `name` varchar(255) DEFAULT NULL COMMENT '名称',
  `pic` text COMMENT 'pic',
  `price` bigint(20) DEFAULT NULL COMMENT '价格',
  `create_datetime` datetime DEFAULT NULL COMMENT '创建时间',
  `updater` varchar(32) DEFAULT NULL COMMENT '更新人',
  `update_datetime` datetime DEFAULT NULL COMMENT '更新时间',
  `remark` varchar(255) DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`code`) COMMENT '产品表'
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
--  Table structure for `tdzt_model_specs`
-- ----------------------------
DROP TABLE IF EXISTS `tdzt_model_specs`;
CREATE TABLE `tdzt_model_specs` (
  `code` varchar(32) NOT NULL COMMENT '编号',
  `name` varchar(255) DEFAULT NULL COMMENT '名称',
  `parent_code` varchar(32) DEFAULT NULL COMMENT 'key',
  `type` varchar(255) DEFAULT NULL COMMENT '类型(中文)',
  `pic` text COMMENT '图片',
  `order_no` int(11) DEFAULT NULL COMMENT '次序',
  `remark` varchar(255) DEFAULT NULL COMMENT '备注',
  `model_code` varchar(32) DEFAULT NULL COMMENT '产品编号',
  PRIMARY KEY (`code`) COMMENT '产品规格表'
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


-- ----------------------------
--  Table structure for `tdzt_order`
-- ----------------------------
DROP TABLE IF EXISTS `tdzt_order`;
CREATE TABLE `tdzt_order` (
  `code` varchar(32) NOT NULL COMMENT '编号',
  `to_user` varchar(32) DEFAULT NULL COMMENT '所属合伙人',
  `apply_user` varchar(32) DEFAULT NULL COMMENT '下单人UserId',
  `apply_name` varchar(32) DEFAULT NULL COMMENT '下单人姓名',
  `apply_mobile` varchar(32) DEFAULT NULL COMMENT '下单人手机号',
  `lt_datetime` datetime DEFAULT NULL COMMENT '量体时间',
  `lt_province` varchar(32) DEFAULT NULL COMMENT '量体地址省',
  `lt_city` varchar(32) DEFAULT NULL COMMENT '量体地址市',
  `lt_area` varchar(32) DEFAULT NULL COMMENT '量体地址区',
  `lt_address` varchar(255) DEFAULT NULL COMMENT '量体详细地址',
  `apply_note` varchar(255) DEFAULT NULL COMMENT '量体叮嘱',
  `create_datetime` datetime DEFAULT NULL COMMENT '下单时间',
  `status` varchar(4) DEFAULT NULL COMMENT '状态',
  `lt_user` varchar(32) DEFAULT NULL COMMENT '量体师UserId',
  `lt_name` varchar(32) DEFAULT NULL COMMENT '量体师姓名',
  `amount` bigint(20) DEFAULT NULL COMMENT '订单总金额',
  `pay_type` varchar(4) DEFAULT NULL COMMENT '支付类型',
  `pay_group` varchar(32) DEFAULT NULL COMMENT '支付组号',
  `pay_code` varchar(32) DEFAULT NULL COMMENT '支付编号',
  `pay_datetime` datetime DEFAULT NULL COMMENT '支付时间',
  `pay_amount` bigint(20) DEFAULT NULL COMMENT '支付金额',
  `deliverer` varchar(32) DEFAULT NULL COMMENT '发货人编号',
  `delivery_datetime` datetime DEFAULT NULL COMMENT '发货时间',
  `logistics_company` varchar(32) DEFAULT NULL COMMENT '物流公司编号',
  `logistics_code` varchar(32) DEFAULT NULL COMMENT '物流单号',
  `pdf` varchar(255) DEFAULT NULL COMMENT '物流单',
  `receiver` varchar(255) DEFAULT NULL COMMENT '收件人姓名',
  `re_mobile` varchar(32) DEFAULT NULL COMMENT '收件人电话',
  `re_address` varchar(255) DEFAULT NULL COMMENT '收货地址',
  `receipt_type` varchar(4) DEFAULT NULL COMMENT '发票类型(1 个人，2 企业)',
  `receipt_title` varchar(32) DEFAULT NULL COMMENT '发票抬头',
  `yunfei` bigint(20) DEFAULT NULL COMMENT '运费',
  `updater` varchar(32) DEFAULT NULL COMMENT '更新人',
  `update_datetime` datetime DEFAULT NULL COMMENT '更新时间',
  `remark` varchar(255) DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`code`) COMMENT '订单'
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


-- ----------------------------
--  Table structure for `tdzt_product`
-- ----------------------------
DROP TABLE IF EXISTS `tdzt_product`;
CREATE TABLE `tdzt_product` (
  `code` varchar(32) NOT NULL COMMENT '编号',
  `order_code` varchar(32) DEFAULT NULL COMMENT '订单编号',
  `model_name` varchar(32) DEFAULT NULL COMMENT '产品名字',
  `model_pic` text COMMENT '产品图片',
  `price` bigint(20) DEFAULT NULL COMMENT '单价',
  `quantity` int(11) DEFAULT NULL COMMENT '数量',
  PRIMARY KEY (`code`) COMMENT '成衣表'
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


-- ----------------------------
--  Table structure for `tdzt_product_specs`
-- ----------------------------
DROP TABLE IF EXISTS `tdzt_product_specs`;
CREATE TABLE `tdzt_product_specs` (
  `code` varchar(32) DEFAULT NULL COMMENT '编号',
  `name` varchar(255) DEFAULT NULL COMMENT '名称',
  `parent_code` varchar(255) DEFAULT NULL COMMENT 'key',
  `type` varchar(4) DEFAULT NULL COMMENT '类型',
  `pic` text COMMENT '图片',
  `product_code` varchar(32) DEFAULT NULL COMMENT '成衣编号',
  `order_code` varchar(32) DEFAULT NULL COMMENT '订单编号'
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


SET FOREIGN_KEY_CHECKS = 1;
