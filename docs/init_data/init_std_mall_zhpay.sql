/*
-- Query: SELECT `code`,`type`,`parent_code`,`name`,`pic`,`order_no`,`status`,`company_code`,`system_code` FROM std_mall_200.tmall_category where system_code = 'CD-CZH000001'
LIMIT 0, 10000

-- Date: 2017-03-31 10:45
*/
INSERT INTO `tmall_category` (`code`,`type`,`parent_code`,`name`,`pic`,`order_no`,`status`,`company_code`,`system_code`) VALUES ('FL201700000000000001','1','0','剁手合集','',1,'1','CD-CZH000001','CD-CZH000001');
INSERT INTO `tmall_category` (`code`,`type`,`parent_code`,`name`,`pic`,`order_no`,`status`,`company_code`,`system_code`) VALUES ('FL201700000000000002','1','0','0元试购','',1,'1','CD-CZH000001','CD-CZH000001');

INSERT INTO `tmall_category` (`code`,`type`,`parent_code`,`name`,`pic`,`order_no`,`status`,`company_code`,`system_code`) VALUES ('FL201700000000000003','1','FL201700000000000001','剁手合集1','',1,'1','CD-CZH000001','CD-CZH000001');
INSERT INTO `tmall_category` (`code`,`type`,`parent_code`,`name`,`pic`,`order_no`,`status`,`company_code`,`system_code`) VALUES ('FL201700000000000004','1','FL201700000000000001','剁手合集2','',1,'1','CD-CZH000001','CD-CZH000001');
INSERT INTO `tmall_category` (`code`,`type`,`parent_code`,`name`,`pic`,`order_no`,`status`,`company_code`,`system_code`) VALUES ('FL201700000000000005','1','FL201700000000000002','0元试购1','',1,'1','CD-CZH000001','CD-CZH000001');
INSERT INTO `tmall_category` (`code`,`type`,`parent_code`,`name`,`pic`,`order_no`,`status`,`company_code`,`system_code`) VALUES ('FL201700000000000006','1','FL201700000000000002','0元试购2','',1,'1','CD-CZH000001','CD-CZH000001');

INSERT INTO `tsys_config` (`type`,`ckey`,`cvalue`,`updater`,`update_datetime`,`remark`,`company_code`,`system_code`) VALUES ('X','hb2Fr','1','admin',now(),'1红包=1分润','CD-CZH000001','CD-CZH000001');
INSERT INTO `tsys_config` (`type`,`ckey`,`cvalue`,`updater`,`update_datetime`,`remark`,`company_code`,`system_code`) VALUES ('X','hbyj2Fr','2','admin',now(),'2红包业绩=1分润','CD-CZH000001','CD-CZH000001');
INSERT INTO `tsys_config` (`type`,`ckey`,`cvalue`,`updater`,`update_datetime`,`remark`,`company_code`,`system_code`) VALUES ('X','hbyj2Gxjl','1','admin',now(),'1红包业绩=1贡献值','CD-CZH000001','CD-CZH000001');
INSERT INTO `tsys_config` (`type`,`ckey`,`cvalue`,`updater`,`update_datetime`,`remark`,`company_code`,`system_code`) VALUES ('X','fr2Cny','1','admin',now(),'1分润=1人民币','CD-CZH000001','CD-CZH000001');
INSERT INTO `tsys_config` (`type`,`ckey`,`cvalue`,`updater`,`update_datetime`,`remark`,`company_code`,`system_code`) VALUES ('X','gxjl2Cny','1','admin',now(),'1贡献值=1人民币','CD-CZH000001','CD-CZH000001');

/*
-- Query: SELECT `type`,`parent_key`,`dkey`,`dvalue`,`updater`,`update_datetime`,`remark`,`company_code`,`system_code` FROM tsys_dict where system_code = 'CD-CZH000001'
LIMIT 0, 10000

-- Date: 2017-03-28 22:03
*/
INSERT INTO `tsys_dict` (`type`,`parent_key`,`dkey`,`dvalue`,`updater`,`update_datetime`,`remark`,`company_code`,`system_code`) VALUES ('0',NULL,'category_status','类别状态','admin',now(),'','CD-CZH000001','CD-CZH000001');
INSERT INTO `tsys_dict` (`type`,`parent_key`,`dkey`,`dvalue`,`updater`,`update_datetime`,`remark`,`company_code`,`system_code`) VALUES ('1','category_status','0','待上架','admin',now(),'','CD-CZH000001','CD-CZH000001');
INSERT INTO `tsys_dict` (`type`,`parent_key`,`dkey`,`dvalue`,`updater`,`update_datetime`,`remark`,`company_code`,`system_code`) VALUES ('1','category_status','1','已上架','admin',now(),'','CD-CZH000001','CD-CZH000001');
INSERT INTO `tsys_dict` (`type`,`parent_key`,`dkey`,`dvalue`,`updater`,`update_datetime`,`remark`,`company_code`,`system_code`) VALUES ('1','category_status','2','已下架','admin',now(),'','CD-CZH000001','CD-CZH000001');

INSERT INTO `tsys_dict` (`type`,`parent_key`,`dkey`,`dvalue`,`updater`,`update_datetime`,`remark`,`company_code`,`system_code`) VALUES ('0',NULL,'product_location','产品位置','admin',now(),'','CD-CZH000001','CD-CZH000001');
INSERT INTO `tsys_dict` (`type`,`parent_key`,`dkey`,`dvalue`,`updater`,`update_datetime`,`remark`,`company_code`,`system_code`) VALUES ('1','product_location','0','普通','admin',now(),'','CD-CZH000001','CD-CZH000001');
INSERT INTO `tsys_dict` (`type`,`parent_key`,`dkey`,`dvalue`,`updater`,`update_datetime`,`remark`,`company_code`,`system_code`) VALUES ('1','product_location','1','首页推荐','admin',now(),'','CD-CZH000001','CD-CZH000001');

INSERT INTO `tsys_dict` (`type`,`parent_key`,`dkey`,`dvalue`,`updater`,`update_datetime`,`remark`,`company_code`,`system_code`) VALUES ('0',NULL,'product_status','产品状态','admin',now(),'','CD-CZH000001','CD-CZH000001');
INSERT INTO `tsys_dict` (`type`,`parent_key`,`dkey`,`dvalue`,`updater`,`update_datetime`,`remark`,`company_code`,`system_code`) VALUES ('1','product_status','0','待审核','admin',now(),'','CD-CZH000001','CD-CZH000001');
INSERT INTO `tsys_dict` (`type`,`parent_key`,`dkey`,`dvalue`,`updater`,`update_datetime`,`remark`,`company_code`,`system_code`) VALUES ('1','product_status','1','审批通过待上架','admin',now(),'','CD-CZH000001','CD-CZH000001');
INSERT INTO `tsys_dict` (`type`,`parent_key`,`dkey`,`dvalue`,`updater`,`update_datetime`,`remark`,`company_code`,`system_code`) VALUES ('1','product_status','91','审批不通过','admin',now(),'','CD-CZH000001','CD-CZH000001');
INSERT INTO `tsys_dict` (`type`,`parent_key`,`dkey`,`dvalue`,`updater`,`update_datetime`,`remark`,`company_code`,`system_code`) VALUES ('1','product_status','3','已上架','admin',now(),'','CD-CZH000001','CD-CZH000001');
INSERT INTO `tsys_dict` (`type`,`parent_key`,`dkey`,`dvalue`,`updater`,`update_datetime`,`remark`,`company_code`,`system_code`) VALUES ('1','product_status','4','已下架','admin',now(),'','CD-CZH000001','CD-CZH000001');

INSERT INTO `tsys_dict` (`type`,`parent_key`,`dkey`,`dvalue`,`updater`,`update_datetime`,`remark`,`company_code`,`system_code`) VALUES ('0',NULL,'order_status','订单状态','admin',now(),'','CD-CZH000001','CD-CZH000001');
INSERT INTO `tsys_dict` (`type`,`parent_key`,`dkey`,`dvalue`,`updater`,`update_datetime`,`remark`,`company_code`,`system_code`) VALUES ('1','order_status','1','待支付','admin',now(),'','CD-CZH000001','CD-CZH000001');
INSERT INTO `tsys_dict` (`type`,`parent_key`,`dkey`,`dvalue`,`updater`,`update_datetime`,`remark`,`company_code`,`system_code`) VALUES ('1','order_status','2','已支付','admin',now(),'','CD-CZH000001','CD-CZH000001');
INSERT INTO `tsys_dict` (`type`,`parent_key`,`dkey`,`dvalue`,`updater`,`update_datetime`,`remark`,`company_code`,`system_code`) VALUES ('1','order_status','3','已发货','admin',now(),'','CD-CZH000001','CD-CZH000001');
INSERT INTO `tsys_dict` (`type`,`parent_key`,`dkey`,`dvalue`,`updater`,`update_datetime`,`remark`,`company_code`,`system_code`) VALUES ('1','order_status','4','已收货','admin',now(),'','CD-CZH000001','CD-CZH000001');
INSERT INTO `tsys_dict` (`type`,`parent_key`,`dkey`,`dvalue`,`updater`,`update_datetime`,`remark`,`company_code`,`system_code`) VALUES ('1','order_status','91','用户异常','admin',now(),'','CD-CZH000001','CD-CZH000001');
INSERT INTO `tsys_dict` (`type`,`parent_key`,`dkey`,`dvalue`,`updater`,`update_datetime`,`remark`,`company_code`,`system_code`) VALUES ('1','order_status','92','商户异常','admin',now(),'','CD-CZH000001','CD-CZH000001');
INSERT INTO `tsys_dict` (`type`,`parent_key`,`dkey`,`dvalue`,`updater`,`update_datetime`,`remark`,`company_code`,`system_code`) VALUES ('1','order_status','93','快递异常','admin',now(),'','CD-CZH000001','CD-CZH000001');

INSERT INTO `tsys_dict` (`type`,`parent_key`,`dkey`,`dvalue`,`updater`,`update_datetime`,`remark`,`company_code`,`system_code`) VALUES ('0',NULL,'kd_company','物流公司','admin',now(),'','CD-CZH000001','CD-CZH000001');
INSERT INTO `tsys_dict` (`type`,`parent_key`,`dkey`,`dvalue`,`updater`,`update_datetime`,`remark`,`company_code`,`system_code`) VALUES ('1','kd_company','EMS','邮政EMS','admin',now(),'','CD-CZH000001','CD-CZH000001');
INSERT INTO `tsys_dict` (`type`,`parent_key`,`dkey`,`dvalue`,`updater`,`update_datetime`,`remark`,`company_code`,`system_code`) VALUES ('1','kd_company','STO','申通快递','admin',now(),'','CD-CZH000001','CD-CZH000001');
INSERT INTO `tsys_dict` (`type`,`parent_key`,`dkey`,`dvalue`,`updater`,`update_datetime`,`remark`,`company_code`,`system_code`) VALUES ('1','kd_company','ZTO','中通快递','admin',now(),'','CD-CZH000001','CD-CZH000001');
INSERT INTO `tsys_dict` (`type`,`parent_key`,`dkey`,`dvalue`,`updater`,`update_datetime`,`remark`,`company_code`,`system_code`) VALUES ('1','kd_company','YTO','圆通快递','admin',now(),'','CD-CZH000001','CD-CZH000001');
INSERT INTO `tsys_dict` (`type`,`parent_key`,`dkey`,`dvalue`,`updater`,`update_datetime`,`remark`,`company_code`,`system_code`) VALUES ('1','kd_company','HTKY','汇通快递','admin',now(),'','CD-CZH000001','CD-CZH000001');
INSERT INTO `tsys_dict` (`type`,`parent_key`,`dkey`,`dvalue`,`updater`,`update_datetime`,`remark`,`company_code`,`system_code`) VALUES ('1','kd_company','SF','顺丰快递','admin',now(),'','CD-CZH000001','CD-CZH000001');
INSERT INTO `tsys_dict` (`type`,`parent_key`,`dkey`,`dvalue`,`updater`,`update_datetime`,`remark`,`company_code`,`system_code`) VALUES ('1','kd_company','TTKD','天天快递','admin',now(),'','CD-CZH000001','CD-CZH000001');
INSERT INTO `tsys_dict` (`type`,`parent_key`,`dkey`,`dvalue`,`updater`,`update_datetime`,`remark`,`company_code`,`system_code`) VALUES ('1','kd_company','ZJS','宅急送','admin',now(),'','CD-CZH000001','CD-CZH000001');

INSERT INTO `tsys_dict` (`type`,`parent_key`,`dkey`,`dvalue`,`updater`,`update_datetime`,`remark`,`company_code`,`system_code`) VALUES ('0',NULL,'store_level','店铺等级','admin',now(),'','CD-CZH000001','CD-CZH000001');
INSERT INTO `tsys_dict` (`type`,`parent_key`,`dkey`,`dvalue`,`updater`,`update_datetime`,`remark`,`company_code`,`system_code`) VALUES ('1','store_level','1','普通商家','admin',now(),'','CD-CZH000001','CD-CZH000001');
INSERT INTO `tsys_dict` (`type`,`parent_key`,`dkey`,`dvalue`,`updater`,`update_datetime`,`remark`,`company_code`,`system_code`) VALUES ('1','store_level','2','理财型商家','admin',now(),'','CD-CZH000001','CD-CZH000001');

INSERT INTO `tsys_dict` (`type`,`parent_key`,`dkey`,`dvalue`,`updater`,`update_datetime`,`remark`,`company_code`,`system_code`) VALUES ('0',NULL,'store_status','店铺状态','admin',now(),'','CD-CZH000001','CD-CZH000001');
INSERT INTO `tsys_dict` (`type`,`parent_key`,`dkey`,`dvalue`,`updater`,`update_datetime`,`remark`,`company_code`,`system_code`) VALUES ('1','store_status','0','待审核','admin',now(),'','CD-CZH000001','CD-CZH000001');
INSERT INTO `tsys_dict` (`type`,`parent_key`,`dkey`,`dvalue`,`updater`,`update_datetime`,`remark`,`company_code`,`system_code`) VALUES ('1','store_status','91','审核不通过','admin',now(),'','CD-CZH000001','CD-CZH000001');
INSERT INTO `tsys_dict` (`type`,`parent_key`,`dkey`,`dvalue`,`updater`,`update_datetime`,`remark`,`company_code`,`system_code`) VALUES ('1','store_status','1','审核通过待上架','admin',now(),'','CD-CZH000001','CD-CZH000001');
INSERT INTO `tsys_dict` (`type`,`parent_key`,`dkey`,`dvalue`,`updater`,`update_datetime`,`remark`,`company_code`,`system_code`) VALUES ('1','store_status','2','已上架，开店','admin',now(),'','CD-CZH000001','CD-CZH000001');
INSERT INTO `tsys_dict` (`type`,`parent_key`,`dkey`,`dvalue`,`updater`,`update_datetime`,`remark`,`company_code`,`system_code`) VALUES ('1','store_status','3','已上架，关店','admin',now(),'','CD-CZH000001','CD-CZH000001');
INSERT INTO `tsys_dict` (`type`,`parent_key`,`dkey`,`dvalue`,`updater`,`update_datetime`,`remark`,`company_code`,`system_code`) VALUES ('1','store_status','4','已下架','admin',now(),'','CD-CZH000001','CD-CZH000001');

INSERT INTO `tsys_dict` (`type`,`parent_key`,`dkey`,`dvalue`,`updater`,`update_datetime`,`remark`,`company_code`,`system_code`) VALUES ('0',NULL,'store_ticket_type','折扣券类型','admin',now(),'','CD-CZH000001','CD-CZH000001');
INSERT INTO `tsys_dict` (`type`,`parent_key`,`dkey`,`dvalue`,`updater`,`update_datetime`,`remark`,`company_code`,`system_code`) VALUES ('1','store_ticket_type','1','满减','admin',now(),'','CD-CZH000001','CD-CZH000001');
INSERT INTO `tsys_dict` (`type`,`parent_key`,`dkey`,`dvalue`,`updater`,`update_datetime`,`remark`,`company_code`,`system_code`) VALUES ('1','store_ticket_type','2','返现','admin',now(),'','CD-CZH000001','CD-CZH000001');

INSERT INTO `tsys_dict` (`type`,`parent_key`,`dkey`,`dvalue`,`updater`,`update_datetime`,`remark`,`company_code`,`system_code`) VALUES ('0',NULL,'store_ticket_status','折扣券状态','admin',now(),'','CD-CZH000001','CD-CZH000001');
INSERT INTO `tsys_dict` (`type`,`parent_key`,`dkey`,`dvalue`,`updater`,`update_datetime`,`remark`,`company_code`,`system_code`) VALUES ('1','store_ticket_status','0','待上架','admin',now(),'','CD-CZH000001','CD-CZH000001');
INSERT INTO `tsys_dict` (`type`,`parent_key`,`dkey`,`dvalue`,`updater`,`update_datetime`,`remark`,`company_code`,`system_code`) VALUES ('1','store_ticket_status','1','已上架','admin',now(),'','CD-CZH000001','CD-CZH000001');
INSERT INTO `tsys_dict` (`type`,`parent_key`,`dkey`,`dvalue`,`updater`,`update_datetime`,`remark`,`company_code`,`system_code`) VALUES ('1','store_ticket_status','2','已下架','admin',now(),'','CD-CZH000001','CD-CZH000001');
INSERT INTO `tsys_dict` (`type`,`parent_key`,`dkey`,`dvalue`,`updater`,`update_datetime`,`remark`,`company_code`,`system_code`) VALUES ('1','store_ticket_status','91','期满作废','admin',now(),'','CD-CZH000001','CD-CZH000001');

INSERT INTO `tsys_dict` (`type`,`parent_key`,`dkey`,`dvalue`,`updater`,`update_datetime`,`remark`,`company_code`,`system_code`) VALUES ('0',NULL,'user_ticket_status','用户折扣券状态','admin',now(),'','CD-CZH000001','CD-CZH000001');
INSERT INTO `tsys_dict` (`type`,`parent_key`,`dkey`,`dvalue`,`updater`,`update_datetime`,`remark`,`company_code`,`system_code`) VALUES ('1','user_ticket_status','0','未使用','admin',now(),'','CD-CZH000001','CD-CZH000001');
INSERT INTO `tsys_dict` (`type`,`parent_key`,`dkey`,`dvalue`,`updater`,`update_datetime`,`remark`,`company_code`,`system_code`) VALUES ('1','user_ticket_status','1','已使用','admin',now(),'','CD-CZH000001','CD-CZH000001');
INSERT INTO `tsys_dict` (`type`,`parent_key`,`dkey`,`dvalue`,`updater`,`update_datetime`,`remark`,`company_code`,`system_code`) VALUES ('1','user_ticket_status','2','已过期','admin',now(),'','CD-CZH000001','CD-CZH000001');

INSERT INTO `tsys_dict` (`type`,`parent_key`,`dkey`,`dvalue`,`updater`,`update_datetime`,`remark`,`company_code`,`system_code`) VALUES ('0',NULL,'action_type','互动类型','admin',now(),'','CD-CZH000001','CD-CZH000001');
INSERT INTO `tsys_dict` (`type`,`parent_key`,`dkey`,`dvalue`,`updater`,`update_datetime`,`remark`,`company_code`,`system_code`) VALUES ('1','action_type','1','点赞','admin',now(),'','CD-CZH000001','CD-CZH000001');
INSERT INTO `tsys_dict` (`type`,`parent_key`,`dkey`,`dvalue`,`updater`,`update_datetime`,`remark`,`company_code`,`system_code`) VALUES ('1','action_type','2','收藏','admin',now(),'','CD-CZH000001','CD-CZH000001');

INSERT INTO `tsys_dict` (`type`,`parent_key`,`dkey`,`dvalue`,`updater`,`update_datetime`,`remark`,`company_code`,`system_code`) VALUES ('0',NULL,'store_purchase_status','店铺消费状态','admin',now(),'','CD-CZH000001','CD-CZH000001');
INSERT INTO `tsys_dict` (`type`,`parent_key`,`dkey`,`dvalue`,`updater`,`update_datetime`,`remark`,`company_code`,`system_code`) VALUES ('1','store_purchase_status','0','待支付','admin',now(),'','CD-CZH000001','CD-CZH000001');
INSERT INTO `tsys_dict` (`type`,`parent_key`,`dkey`,`dvalue`,`updater`,`update_datetime`,`remark`,`company_code`,`system_code`) VALUES ('1','store_purchase_status','1','已支付','admin',now(),'','CD-CZH000001','CD-CZH000001');
INSERT INTO `tsys_dict` (`type`,`parent_key`,`dkey`,`dvalue`,`updater`,`update_datetime`,`remark`,`company_code`,`system_code`) VALUES ('1','store_purchase_status','2','已取消','admin',now(),'','CD-CZH000001','CD-CZH000001');