/*
-- Query: SELECT * FROM std_account.tstd_account where system_code = 'CD-CDZT000009' and type = 'P'
-- Date: 2017-04-19 21:58
*/
INSERT INTO `tstd_account` (`account_number`,`user_id`,`real_name`,`type`,`status`,`currency`,`amount`,`frozen_amount`,`md5`,`add_amount`,`in_amount`,`out_amount`,`create_datetime`,`last_order`,`system_code`) VALUES ('DZTA2017100000000000000','SYS_USER_DZT','平台','P','0','CNY',1497000,0,'33b28c846c000b153bc34f7f3563c631',1497000,0,0,now(),'AJ2017041920073612859','CD-CDZT000009');

INSERT INTO `tstd_company_channel` (`id`,`company_code`,`company_name`,`channel_type`,`status`,`channel_company`,`private_key1`,`private_key2`,`private_key3`,`private_key4`,`private_key5`,`page_url`,`error_url`,`back_url`,`fee`,`remark`,`system_code`) VALUES (15,'CD-CDZT000009','定制通','35','1','1430550302','r2jgDFSdiikklwlllejlwjio3242342n','wx9b874d991d7e50d5','aa1832cf32722e0fb977a6c9f6aafa20',NULL,NULL,NULL,NULL,'http://121.43.101.148:3302/xn-dzt/thirdPay/callback',NULL,NULL,'CD-CDZT000009');

/*
-- Query: SELECT * FROM std_account.tsys_dict where system_code ='CD-CDZT000009'
-- Date: 2017-04-19 22:01
*/
INSERT INTO `tsys_dict` (`id`,`type`,`parent_key`,`dkey`,`dvalue`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES (403,'0',NULL,'bankcard_status','银行卡状态','admin','2017-04-13 10:49:54','','CD-CDZT000009');
INSERT INTO `tsys_dict` (`id`,`type`,`parent_key`,`dkey`,`dvalue`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES (404,'1','bankcard_status','0','弃用','admin','2017-04-13 10:49:54','','CD-CDZT000009');
INSERT INTO `tsys_dict` (`id`,`type`,`parent_key`,`dkey`,`dvalue`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES (405,'1','bankcard_status','1','启用','admin','2017-04-13 10:49:54','','CD-CDZT000009');
INSERT INTO `tsys_dict` (`id`,`type`,`parent_key`,`dkey`,`dvalue`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES (406,'0',NULL,'currency','货币','admin','2017-04-13 10:49:54','','CD-CDZT000009');
INSERT INTO `tsys_dict` (`id`,`type`,`parent_key`,`dkey`,`dvalue`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES (407,'1','currency','CNY','人民币','admin','2017-04-13 10:49:54','','CD-CDZT000009');
INSERT INTO `tsys_dict` (`id`,`type`,`parent_key`,`dkey`,`dvalue`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES (408,'0',NULL,'account_type','账户类型','admin','2017-04-13 10:49:54','','CD-CDZT000009');
INSERT INTO `tsys_dict` (`id`,`type`,`parent_key`,`dkey`,`dvalue`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES (409,'1','account_type','C','C端用户','admin','2017-04-13 10:49:54','','CD-CDZT000009');
INSERT INTO `tsys_dict` (`id`,`type`,`parent_key`,`dkey`,`dvalue`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES (410,'1','account_type','B','B端用户','admin','2017-04-13 10:49:54','','CD-CDZT000009');
INSERT INTO `tsys_dict` (`id`,`type`,`parent_key`,`dkey`,`dvalue`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES (411,'1','account_type','PA','合伙人用户','admin','2017-04-13 10:49:54','','CD-CDZT000009');
INSERT INTO `tsys_dict` (`id`,`type`,`parent_key`,`dkey`,`dvalue`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES (412,'1','account_type','P','平台用户','admin','2017-04-13 10:49:54','','CD-CDZT000009');
INSERT INTO `tsys_dict` (`id`,`type`,`parent_key`,`dkey`,`dvalue`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES (413,'0',NULL,'account_status','账户状态','admin','2017-04-13 10:49:54','','CD-CDZT000009');
INSERT INTO `tsys_dict` (`id`,`type`,`parent_key`,`dkey`,`dvalue`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES (414,'1','account_status','0','正常','admin','2017-04-13 10:49:54','','CD-CDZT000009');
INSERT INTO `tsys_dict` (`id`,`type`,`parent_key`,`dkey`,`dvalue`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES (415,'1','account_status','1','程序锁定','admin','2017-04-13 10:49:54','','CD-CDZT000009');
INSERT INTO `tsys_dict` (`id`,`type`,`parent_key`,`dkey`,`dvalue`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES (416,'1','account_status','2','人工锁定','admin','2017-04-13 10:49:54','','CD-CDZT000009');
INSERT INTO `tsys_dict` (`id`,`type`,`parent_key`,`dkey`,`dvalue`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES (417,'0',NULL,'jour_status','流水状态','admin','2017-04-13 10:49:54','','CD-CDZT000009');
INSERT INTO `tsys_dict` (`id`,`type`,`parent_key`,`dkey`,`dvalue`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES (418,'1','jour_status','0','刚生成待回调','admin','2017-04-13 10:49:54','','CD-CDZT000009');
INSERT INTO `tsys_dict` (`id`,`type`,`parent_key`,`dkey`,`dvalue`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES (419,'1','jour_status','1','已回调通过待对账','admin','2017-04-13 10:49:55','','CD-CDZT000009');
INSERT INTO `tsys_dict` (`id`,`type`,`parent_key`,`dkey`,`dvalue`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES (420,'1','jour_status','2','回调不通过','admin','2017-04-13 10:49:55','','CD-CDZT000009');
INSERT INTO `tsys_dict` (`id`,`type`,`parent_key`,`dkey`,`dvalue`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES (421,'1','jour_status','3','已对账且账不平','admin','2017-04-13 10:49:55','','CD-CDZT000009');
INSERT INTO `tsys_dict` (`id`,`type`,`parent_key`,`dkey`,`dvalue`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES (422,'1','jour_status','4','账不平待调账','admin','2017-04-13 10:49:55','','CD-CDZT000009');
INSERT INTO `tsys_dict` (`id`,`type`,`parent_key`,`dkey`,`dvalue`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES (423,'1','jour_status','5','已调账','admin','2017-04-13 10:49:55','','CD-CDZT000009');
INSERT INTO `tsys_dict` (`id`,`type`,`parent_key`,`dkey`,`dvalue`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES (424,'1','jour_status','9','无需对账','admin','2017-04-13 10:49:55','','CD-CDZT000009');
INSERT INTO `tsys_dict` (`id`,`type`,`parent_key`,`dkey`,`dvalue`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES (425,'1','jour_status','6','待审批','admin','2017-04-13 10:49:55','','CD-CDZT000009');
INSERT INTO `tsys_dict` (`id`,`type`,`parent_key`,`dkey`,`dvalue`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES (426,'1','jour_status','7','审批通过','admin','2017-04-13 10:49:55','','CD-CDZT000009');
INSERT INTO `tsys_dict` (`id`,`type`,`parent_key`,`dkey`,`dvalue`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES (427,'1','jour_status','8','审批不通过','admin','2017-04-13 10:49:55','','CD-CDZT000009');
INSERT INTO `tsys_dict` (`id`,`type`,`parent_key`,`dkey`,`dvalue`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES (428,'0',NULL,'channel_type','渠道类型','admin','2017-04-13 10:49:55','','CD-CDZT000009');
INSERT INTO `tsys_dict` (`id`,`type`,`parent_key`,`dkey`,`dvalue`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES (429,'1','channel_type','01','线下_橙账本','admin','2017-04-13 10:49:55','','CD-CDZT000009');
INSERT INTO `tsys_dict` (`id`,`type`,`parent_key`,`dkey`,`dvalue`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES (430,'1','channel_type','0','内部账','admin','2017-04-13 10:49:55','','CD-CDZT000009');
INSERT INTO `tsys_dict` (`id`,`type`,`parent_key`,`dkey`,`dvalue`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES (431,'1','channel_type','9','调账','admin','2017-04-13 10:49:55','','CD-CDZT000009');
INSERT INTO `tsys_dict` (`id`,`type`,`parent_key`,`dkey`,`dvalue`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES (432,'1','channel_type','10','轧账','admin','2017-04-13 10:49:55','','CD-CDZT000009');
INSERT INTO `tsys_dict` (`id`,`type`,`parent_key`,`dkey`,`dvalue`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES (434,'1','channel_type','35','微信公众号支付','admin','2017-04-13 10:49:55','','CD-CDZT000009');
INSERT INTO `tsys_dict` (`id`,`type`,`parent_key`,`dkey`,`dvalue`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES (435,'0',NULL,'biz_type','业务类型','admin','2017-04-13 10:49:55','','CD-CDZT000009');
INSERT INTO `tsys_dict` (`id`,`type`,`parent_key`,`dkey`,`dvalue`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES (436,'1','biz_type','11','充值','admin','2017-04-13 10:49:55','','CD-CDZT000009');
INSERT INTO `tsys_dict` (`id`,`type`,`parent_key`,`dkey`,`dvalue`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES (437,'1','biz_type','-11','取现','admin','2017-04-13 10:49:55','','CD-CDZT000009');
INSERT INTO `tsys_dict` (`id`,`type`,`parent_key`,`dkey`,`dvalue`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES (438,'1','biz_type','19','蓝补','admin','2017-04-13 10:49:55','','CD-CDZT000009');
INSERT INTO `tsys_dict` (`id`,`type`,`parent_key`,`dkey`,`dvalue`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES (439,'1','biz_type','-19','红冲','admin','2017-04-13 10:49:55','','CD-CDZT000009');

