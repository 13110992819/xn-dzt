DROP TABLE IF EXISTS `tdzt_article`;
CREATE TABLE `tdzt_article` (
  `code` varchar(32) NOT NULL COMMENT '编号',
  `type` varchar(4) DEFAULT NULL COMMENT '类型',
  `title` text COMMENT '标题',
  `pic` text COMMENT '缩略图',
  `adv_pic` text COMMENT '广告图',
  `description` text COMMENT '图文描述',
  `location` varchar(32) DEFAULT NULL COMMENT 'UI位置',
  `order_no` int(11) DEFAULT NULL COMMENT 'UI顺序',
  `status` varchar(4) DEFAULT NULL COMMENT '状态',
  `updater` varchar(32) DEFAULT NULL COMMENT '发布人',
  `update_datetime` datetime DEFAULT NULL COMMENT '发布时间',
  `remark` text COMMENT '备注',
  PRIMARY KEY (`code`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


DROP TABLE IF EXISTS `tdzt_cloth`;
CREATE TABLE `tdzt_cloth` (
  `code` varchar(32) NOT NULL COMMENT '编号',
  `type` varchar(32) DEFAULT NULL COMMENT '类型',
  `brand` varchar(32) DEFAULT NULL COMMENT '品牌',
  `model_num` varchar(32) DEFAULT NULL COMMENT '规格编号',
  `pic` text COMMENT '缩率图',
  `adv_pic` text COMMENT '广告图',
  `color` varchar(32) DEFAULT NULL COMMENT '色系',
  `flowers` varchar(32) DEFAULT NULL COMMENT '花色',
  `form` varchar(32) DEFAULT NULL COMMENT '成分',
  `weight` varchar(32) DEFAULT NULL COMMENT '重量',
  `yarn` varchar(32) DEFAULT NULL COMMENT '纱支',
  `price` bigint(20) DEFAULT NULL COMMENT '价格',
  `location` varchar(32) DEFAULT NULL COMMENT 'UI位置',
  `order_no` int(11) DEFAULT NULL COMMENT 'UI顺序',
  `status` varchar(4) DEFAULT NULL COMMENT '状态',
  `updater` varchar(32) DEFAULT NULL COMMENT '更新人',
  `update_datetime` datetime DEFAULT NULL COMMENT '更新时间',
  `remark` text COMMENT '备注',
  `model_code` varchar(32) DEFAULT NULL COMMENT '模型编号',
  PRIMARY KEY (`code`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS `tdzt_comment`;
CREATE TABLE `tdzt_comment` (
  `code` varchar(32) NOT NULL COMMENT '编号',
  `type` varchar(4) DEFAULT NULL COMMENT '类型',
  `content` text COMMENT '评论内容',
  `status` varchar(4) DEFAULT NULL COMMENT '状态',
  `commer` varchar(32) DEFAULT NULL COMMENT '评论人',
  `comment_datetime` datetime DEFAULT NULL COMMENT '评论时间',
  `approver` varchar(32) DEFAULT NULL COMMENT '审核人',
  `approve_datetime` datetime DEFAULT NULL COMMENT '审核时间',
  `remark` text COMMENT '备注',
  `top_code` varchar(32) DEFAULT NULL COMMENT '顶级编号',
  `parent_code` varchar(32) DEFAULT NULL COMMENT '父类编号',
  PRIMARY KEY (`code`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

DROP TABLE IF EXISTS `tdzt_craft`;
CREATE TABLE `tdzt_craft` (
  `code` varchar(32) NOT NULL COMMENT '编号',
  `type` varchar(32) DEFAULT NULL COMMENT '类型',
  `name` varchar(255) DEFAULT NULL COMMENT '名称',
  `pic` text COMMENT '图片',
  `selected` text COMMENT '缩率图',
  `price` bigint(20) DEFAULT NULL COMMENT '工艺费',
  `location` varchar(32) DEFAULT NULL COMMENT 'UI位置',
  `order_no` int(11) DEFAULT NULL COMMENT 'UI顺序',
  `status` varchar(4) DEFAULT NULL COMMENT '状态',
  `updater` varchar(32) DEFAULT NULL COMMENT '更新人',
  `update_datetime` datetime DEFAULT NULL COMMENT '更新时间',
  `remark` text COMMENT '备注',
  `model_code` varchar(32) DEFAULT NULL COMMENT '模型编号',
  PRIMARY KEY (`code`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS `tdzt_interact`;
CREATE TABLE `tdzt_interact` (
  `code` varchar(32) NOT NULL COMMENT '编号',
  `category` varchar(4) DEFAULT NULL COMMENT '分类',
  `type` varchar(4) DEFAULT NULL COMMENT '类型',
  `object_code` varchar(32) DEFAULT NULL COMMENT '互动对象',
  `operator` varchar(32) DEFAULT NULL COMMENT '操作人',
  `operat_datetime` datetime DEFAULT NULL COMMENT '操作时间',
  PRIMARY KEY (`code`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS `tdzt_keyword`;
CREATE TABLE `tdzt_keyword` (
  `code` varchar(32) NOT NULL COMMENT '编号',
  `word` varchar(255) DEFAULT NULL COMMENT '词语',
  `weight` int(11) DEFAULT NULL COMMENT '权重(0-1 0.5以上黑，0.5以下白)',
  `level` varchar(4) DEFAULT NULL COMMENT '作用等级',
  `reaction` varchar(4) DEFAULT NULL COMMENT '反应(1 直接拦截/2 替换**)',
  `updater` varchar(32) DEFAULT NULL COMMENT '更新人',
  `update_datetime` datetime DEFAULT NULL COMMENT '更新时间',
  `remark` varchar(255) DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`code`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

DROP TABLE IF EXISTS `tdzt_model`;
CREATE TABLE `tdzt_model` (
  `code` varchar(32) NOT NULL COMMENT '编号',
  `type` varchar(32) DEFAULT NULL COMMENT '类型',
  `name` varchar(255) DEFAULT NULL COMMENT '名称',
  `pic` text COMMENT '图片',
  `adv_pic` text COMMENT '广告图',
  `description` text COMMENT '图文描述',
  `loss` bigint(20) DEFAULT NULL COMMENT '面料损耗',
  `process_fee` bigint(20) DEFAULT NULL COMMENT '工艺费',
  `price` bigint(20) DEFAULT NULL COMMENT '价格',
  `location` varchar(32) DEFAULT NULL COMMENT 'UI位置',
  `order_no` int(11) DEFAULT NULL COMMENT 'UI顺序',
  `status` varchar(4) DEFAULT NULL COMMENT '状态',
  `create_datetime` datetime DEFAULT NULL COMMENT '创建时间',
  `updater` varchar(32) DEFAULT NULL COMMENT '更新人',
  `update_datetime` datetime DEFAULT NULL COMMENT '更新时间',
  `remark` varchar(255) DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`code`) COMMENT '产品表'
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

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

DROP TABLE IF EXISTS `tdzt_order`;
CREATE TABLE `tdzt_order` (
  `code` varchar(32) NOT NULL COMMENT '编号',
  `type` varchar(4) DEFAULT NULL COMMENT '订单类型',
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

DROP TABLE IF EXISTS `tdzt_product`;
CREATE TABLE `tdzt_product` (
  `code` varchar(32) NOT NULL COMMENT '编号',
  `order_code` varchar(32) DEFAULT NULL COMMENT '订单编号',
  `model_code` varchar(32) DEFAULT NULL COMMENT '型号编号',
  `model_name` varchar(32) DEFAULT NULL COMMENT '产品名字',
  `model_pic` text COMMENT '产品图片',
  `adv_pic` text COMMENT '产品缩率图',
  `description` text COMMENT '图文描述',
  `loss` bigint(20) DEFAULT NULL COMMENT '损耗',
  `process_fee` bigint(20) DEFAULT NULL COMMENT '工艺费',
  `price` bigint(20) DEFAULT NULL COMMENT '单价',
  `quantity` int(11) DEFAULT NULL COMMENT '数量',
  PRIMARY KEY (`code`) COMMENT '成衣表'
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS `tdzt_product_specs`;
CREATE TABLE `tdzt_product_specs` (
  `code` varchar(255) DEFAULT NULL COMMENT '编号',
  `type` varchar(255) DEFAULT NULL COMMENT '类型',
  `name` varchar(255) DEFAULT NULL COMMENT '名称',
  `brand` varchar(255) DEFAULT NULL COMMENT '品牌',
  `model_num` varchar(255) DEFAULT NULL COMMENT '型号',
  `pic` text COMMENT '缩率图',
  `adv_pic` text COMMENT '广告图',
  `color` varchar(255) DEFAULT NULL COMMENT '色系',
  `flowers` varchar(255) DEFAULT NULL COMMENT '花色',
  `form` varchar(255) DEFAULT NULL COMMENT '组成',
  `weight` varchar(255) DEFAULT NULL COMMENT '重量',
  `yarn` varchar(255) DEFAULT NULL COMMENT '纱支',
  `price` bigint(20) DEFAULT NULL COMMENT '单价',
  `product_code` varchar(32) DEFAULT NULL COMMENT '成衣编号',
  `order_code` varchar(32) DEFAULT NULL COMMENT '订单编号'
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS `tdzt_size_data`;
CREATE TABLE `tdzt_size_data` (
  `id` int(32) NOT NULL AUTO_INCREMENT COMMENT '编号',
  `user_id` varchar(32) DEFAULT NULL COMMENT '用户编号',
  `ckey` varchar(32) DEFAULT NULL COMMENT 'key',
  `cvalue` varchar(32) DEFAULT NULL COMMENT 'value',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=92 DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS `tdzt_swap`;
CREATE TABLE `tdzt_swap` (
  `code` varchar(32) NOT NULL COMMENT '编号',
  `type` varchar(32) DEFAULT NULL COMMENT '类型',
  `commenter` varchar(32) DEFAULT NULL COMMENT '留言人',
  `receiver` varchar(32) DEFAULT NULL COMMENT '接收人',
  `content` text COMMENT '内容',
  `comment_datetime` datetime DEFAULT NULL COMMENT '留言时间',
  `order_no` int(11) DEFAULT NULL COMMENT '顺序',
   `is_new` varchar(32) DEFAULT NULL COMMENT '是否最新',
  `status` varchar(4) DEFAULT NULL COMMENT '状态',
  PRIMARY KEY (`code`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS `tsys_config`;
CREATE TABLE `tsys_config` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '编号',
  `type` varchar(32) DEFAULT NULL COMMENT '类型',
  `ckey` varchar(32) DEFAULT NULL COMMENT 'key值',
  `cvalue` varchar(255) DEFAULT NULL COMMENT '值',
  `updater` varchar(32) DEFAULT NULL COMMENT '更新人',
  `update_datetime` datetime DEFAULT NULL COMMENT '更新时间',
  `remark` varchar(255) DEFAULT NULL COMMENT '备注',
  `company_code` varchar(32) DEFAULT NULL COMMENT '公司编号',
  `system_code` varchar(32) DEFAULT NULL COMMENT '系统编号',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=32 DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS `tsys_dict`;
CREATE TABLE `tsys_dict` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '编号（自增长）',
  `type` char(1) DEFAULT NULL COMMENT '类型（第一层/第二层）',
  `parent_key` varchar(32) DEFAULT NULL COMMENT '父key',
  `dkey` varchar(32) DEFAULT NULL COMMENT 'key',
  `dvalue` varchar(255) DEFAULT NULL COMMENT '值',
  `updater` varchar(32) DEFAULT NULL COMMENT '更新人',
  `update_datetime` datetime DEFAULT NULL COMMENT '更新时间',
  `remark` varchar(255) DEFAULT NULL COMMENT '备注',
  `system_code` varchar(32) DEFAULT NULL COMMENT '系统编号',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
