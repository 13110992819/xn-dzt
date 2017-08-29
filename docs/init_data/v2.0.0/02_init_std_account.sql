

insert into `tsys_config` (`type`, `ckey`, `cvalue`, `updater`, `update_datetime`, `remark`, `company_code`, `system_code`) values('0','PUSERMONTIMES','5','admin',now(),'合伙人每月取现次数','CD-CDZT000009','CD-CDZT000009');
insert into `tsys_config` (`type`, `ckey`, `cvalue`, `updater`, `update_datetime`, `remark`, `company_code`, `system_code`) values('0','PUSERQXFL','0.01','admin',now(),'合伙人取现费率','CD-CDZT000009','CD-CDZT000009');
insert into `tsys_config` (`type`, `ckey`, `cvalue`, `updater`, `update_datetime`, `remark`, `company_code`, `system_code`) values('0','QXDBZDJE','5000','admin',now(),'取现单笔最大金额','CD-CDZT000009','CD-CDZT000009');
insert into `tsys_config` (`type`, `ckey`, `cvalue`, `updater`, `update_datetime`, `remark`, `company_code`, `system_code`) values('0','PUSERQXSX','T+1','admin',now(),'合伙人取现时效','CD-CDZT000009','CD-CDZT000009');
insert into `tsys_config` (`type`, `ckey`, `cvalue`, `updater`, `update_datetime`, `remark`, `company_code`, `system_code`) values('0','PUSERQXBS','5','admin',now(),'合伙人取现倍数','CD-CDZT000009','CD-CDZT000009');
insert into `tsys_config` (`type`, `ckey`, `cvalue`, `updater`, `update_datetime`, `remark`, `company_code`, `system_code`) values('0','CUSERQXBS','5','admin',now(),'C端用户取现倍数','CD-CDZT000009','CD-CDZT000009');
insert into `tsys_config` (`type`, `ckey`, `cvalue`, `updater`, `update_datetime`, `remark`, `company_code`, `system_code`) values('0','CUSERQXFL','0.01','admin',now(),'C端用户取现费率','CD-CDZT000009','CD-CDZT000009');
insert into `tsys_config` (`type`, `ckey`, `cvalue`, `updater`, `update_datetime`, `remark`, `company_code`, `system_code`) values('0','CUSERQXSX','T+1','admin',now(),'C端用户取现时效','CD-CDZT000009','CD-CDZT000009');
insert into `tsys_config` (`type`, `ckey`, `cvalue`, `updater`, `update_datetime`, `remark`, `company_code`, `system_code`) values('0','CUSERMONTIMES','5','admin',now(),'C端用户每月取现次数','CD-CDZT000009','CD-CDZT000009');
insert into `tsys_config` (`type`, `ckey`, `cvalue`, `updater`, `update_datetime`, `remark`, `company_code`, `system_code`) values('0','BUSERQXBS','5','admin',now(),'着装顾问取现倍数','CD-CDZT000009','CD-CDZT000009');
insert into `tsys_config` (`type`, `ckey`, `cvalue`, `updater`, `update_datetime`, `remark`, `company_code`, `system_code`) values('0','BUSERQXFL','0.01','admin',now(),'着装顾问取现费率','CD-CDZT000009','CD-CDZT000009');
insert into `tsys_config` (`type`, `ckey`, `cvalue`, `updater`, `update_datetime`, `remark`, `company_code`, `system_code`) values('0','BUSERQXSX','T+1','admin',now(),'着装顾问取现时效','CD-CDZT000009','CD-CDZT000009');
insert into `tsys_config` (`type`, `ckey`, `cvalue`, `updater`, `update_datetime`, `remark`, `company_code`, `system_code`) values('0','BUSERMONTIMES','5','admin',now(),'着装顾问每月取现次数','CD-CDZT000009','CD-CDZT000009');


insert into `tstd_company_channel` (`company_code`, `company_name`, `channel_type`, `status`, `channel_company`, `private_key1`, `private_key2`, `private_key3`, `private_key4`, `private_key5`, `page_url`, `error_url`, `back_url`, `fee`, `remark`, `system_code`) values('CD-CDZT000009','定制通','35','1','1429521602','r2jgDFSdiikklwlllejlwjio3242342n','wx9b874d991d7e50d5','aa1832cf32722e0fb977a6c9f6aafa20',NULL,NULL,NULL,NULL,'http://121.43.101.148:3302/xn-dzt/thirdPay/callback',NULL,NULL,'CD-CDZT000009');


insert into `tstd_channel_bank` (`bank_code`, `bank_name`, `channel_type`, `status`, `channel_bank`, `max_order`, `order_amount`, `day_amount`, `month_amount`, `remark`) values('ICBC','中国工商银行','40','1',NULL,NULL,NULL,NULL,NULL,NULL);
insert into `tstd_channel_bank` (`bank_code`, `bank_name`, `channel_type`, `status`, `channel_bank`, `max_order`, `order_amount`, `day_amount`, `month_amount`, `remark`) values('ABC','中国农业银行','40','1',NULL,NULL,NULL,NULL,NULL,NULL);
insert into `tstd_channel_bank` (`bank_code`, `bank_name`, `channel_type`, `status`, `channel_bank`, `max_order`, `order_amount`, `day_amount`, `month_amount`, `remark`) values('CCB','中国建设银行','40','1',NULL,NULL,NULL,NULL,NULL,NULL);
insert into `tstd_channel_bank` (`bank_code`, `bank_name`, `channel_type`, `status`, `channel_bank`, `max_order`, `order_amount`, `day_amount`, `month_amount`, `remark`) values('BOC','中国银行','40','1',NULL,NULL,NULL,NULL,NULL,NULL);
insert into `tstd_channel_bank` (`bank_code`, `bank_name`, `channel_type`, `status`, `channel_bank`, `max_order`, `order_amount`, `day_amount`, `month_amount`, `remark`) values('BCM','中国交通银行','40','1',NULL,NULL,NULL,NULL,NULL,NULL);
insert into `tstd_channel_bank` (`bank_code`, `bank_name`, `channel_type`, `status`, `channel_bank`, `max_order`, `order_amount`, `day_amount`, `month_amount`, `remark`) values('CIB','兴业银行','40','1',NULL,NULL,NULL,NULL,NULL,NULL);
insert into `tstd_channel_bank` (`bank_code`, `bank_name`, `channel_type`, `status`, `channel_bank`, `max_order`, `order_amount`, `day_amount`, `month_amount`, `remark`) values('CITIC','中信银行','40','1',NULL,NULL,NULL,NULL,NULL,NULL);
insert into `tstd_channel_bank` (`bank_code`, `bank_name`, `channel_type`, `status`, `channel_bank`, `max_order`, `order_amount`, `day_amount`, `month_amount`, `remark`) values('CEB','中国光大银行','40','1',NULL,NULL,NULL,NULL,NULL,NULL);
insert into `tstd_channel_bank` (`bank_code`, `bank_name`, `channel_type`, `status`, `channel_bank`, `max_order`, `order_amount`, `day_amount`, `month_amount`, `remark`) values('PAB','平安银行','40','1',NULL,NULL,NULL,NULL,NULL,NULL);
insert into `tstd_channel_bank` (`bank_code`, `bank_name`, `channel_type`, `status`, `channel_bank`, `max_order`, `order_amount`, `day_amount`, `month_amount`, `remark`) values('PSBC','中国邮政储蓄银行','40','1',NULL,NULL,NULL,NULL,NULL,NULL);
insert into `tstd_channel_bank` (`bank_code`, `bank_name`, `channel_type`, `status`, `channel_bank`, `max_order`, `order_amount`, `day_amount`, `month_amount`, `remark`) values('SHB','上海银行','40','1',NULL,NULL,NULL,NULL,NULL,NULL);
insert into `tstd_channel_bank` (`bank_code`, `bank_name`, `channel_type`, `status`, `channel_bank`, `max_order`, `order_amount`, `day_amount`, `month_amount`, `remark`) values('SPDB','浦东发展银行','40','1',NULL,NULL,NULL,NULL,NULL,NULL);
insert into `tstd_channel_bank` (`bank_code`, `bank_name`, `channel_type`, `status`, `channel_bank`, `max_order`, `order_amount`, `day_amount`, `month_amount`, `remark`) values('CIB','兴业银行','40','1',NULL,NULL,NULL,NULL,NULL,NULL);
insert into `tstd_channel_bank` (`bank_code`, `bank_name`, `channel_type`, `status`, `channel_bank`, `max_order`, `order_amount`, `day_amount`, `month_amount`, `remark`) values('ICBC','中国工商银行','40','1',NULL,NULL,NULL,NULL,NULL,NULL);
insert into `tstd_channel_bank` (`bank_code`, `bank_name`, `channel_type`, `status`, `channel_bank`, `max_order`, `order_amount`, `day_amount`, `month_amount`, `remark`) values('ABC','中国农业银行','40','1',NULL,NULL,NULL,NULL,NULL,NULL);
insert into `tstd_channel_bank` (`bank_code`, `bank_name`, `channel_type`, `status`, `channel_bank`, `max_order`, `order_amount`, `day_amount`, `month_amount`, `remark`) values('CCB','中国建设银行','40','1',NULL,NULL,NULL,NULL,NULL,NULL);
insert into `tstd_channel_bank` (`bank_code`, `bank_name`, `channel_type`, `status`, `channel_bank`, `max_order`, `order_amount`, `day_amount`, `month_amount`, `remark`) values('BOC','中国银行','40','1',NULL,NULL,NULL,NULL,NULL,NULL);
insert into `tstd_channel_bank` (`bank_code`, `bank_name`, `channel_type`, `status`, `channel_bank`, `max_order`, `order_amount`, `day_amount`, `month_amount`, `remark`) values('BCM','中国交通银行','40','1',NULL,NULL,NULL,NULL,NULL,NULL);
insert into `tstd_channel_bank` (`bank_code`, `bank_name`, `channel_type`, `status`, `channel_bank`, `max_order`, `order_amount`, `day_amount`, `month_amount`, `remark`) values('CIB','兴业银行','40','1',NULL,NULL,NULL,NULL,NULL,NULL);
insert into `tstd_channel_bank` (`bank_code`, `bank_name`, `channel_type`, `status`, `channel_bank`, `max_order`, `order_amount`, `day_amount`, `month_amount`, `remark`) values('CITIC','中信银行','40','1',NULL,NULL,NULL,NULL,NULL,NULL);
insert into `tstd_channel_bank` (`bank_code`, `bank_name`, `channel_type`, `status`, `channel_bank`, `max_order`, `order_amount`, `day_amount`, `month_amount`, `remark`) values('CEB','中国光大银行','40','1',NULL,NULL,NULL,NULL,NULL,NULL);
insert into `tstd_channel_bank` (`bank_code`, `bank_name`, `channel_type`, `status`, `channel_bank`, `max_order`, `order_amount`, `day_amount`, `month_amount`, `remark`) values('PAB','平安银行','40','1',NULL,NULL,NULL,NULL,NULL,NULL);
insert into `tstd_channel_bank` (`bank_code`, `bank_name`, `channel_type`, `status`, `channel_bank`, `max_order`, `order_amount`, `day_amount`, `month_amount`, `remark`) values('PSBC','中国邮政储蓄银行','40','1',NULL,NULL,NULL,NULL,NULL,NULL);
insert into `tstd_channel_bank` (`bank_code`, `bank_name`, `channel_type`, `status`, `channel_bank`, `max_order`, `order_amount`, `day_amount`, `month_amount`, `remark`) values('SHB','上海银行','40','1',NULL,NULL,NULL,NULL,NULL,NULL);
insert into `tstd_channel_bank` (`bank_code`, `bank_name`, `channel_type`, `status`, `channel_bank`, `max_order`, `order_amount`, `day_amount`, `month_amount`, `remark`) values('SPDB','浦东发展银行','40','1',NULL,NULL,NULL,NULL,NULL,NULL);
insert into `tstd_channel_bank` (`bank_code`, `bank_name`, `channel_type`, `status`, `channel_bank`, `max_order`, `order_amount`, `day_amount`, `month_amount`, `remark`) values('CIB','兴业银行','40','1',NULL,NULL,NULL,NULL,NULL,NULL);



insert into `tstd_account` (`account_number`, `user_id`, `real_name`, `type`, `status`, `currency`, `amount`, `frozen_amount`, `md5`, `add_amount`, `in_amount`, `out_amount`, `create_datetime`, `last_order`, `system_code`, `company_code`) values('CD-CDZT000009','SYS_USER_DZT_TG','平台托管账户','P','0','CNY','0','0','066fa99f6cb0eb12a86d21e345e1200b','0','0','0',now(),NULL,'CD-CDZT000009','CD-CDZT000009');
insert into `tstd_account` (`account_number`, `user_id`, `real_name`, `type`, `status`, `currency`, `amount`, `frozen_amount`, `md5`, `add_amount`, `in_amount`, `out_amount`, `create_datetime`, `last_order`, `system_code`, `company_code`) values('DZTA2017100000000000000','SYS_USER_DZT','平台人民币账户','P','0','CNY','0','0','d98df17789b8747846bc12541919878b','0','0','0',now(),NULL,'CD-CDZT000009','CD-CDZT000009');
insert into `tstd_account` (`account_number`, `user_id`, `real_name`, `type`, `status`, `currency`, `amount`, `frozen_amount`, `md5`, `add_amount`, `in_amount`, `out_amount`, `create_datetime`, `last_order`, `system_code`, `company_code`) values('DZTA2017100000000000001','SYS_USER_DZT','平台积分账户','P','0','JF','0','0','3acc44c3ca31c0352e84febdf1c21e04','0','0','0',now(),NULL,'CD-CDZT000009','CD-CDZT000009');
insert into `tstd_account` (`account_number`, `user_id`, `real_name`, `type`, `status`, `currency`, `amount`, `frozen_amount`, `md5`, `add_amount`, `in_amount`, `out_amount`, `create_datetime`, `last_order`, `system_code`, `company_code`) values('DZTA2017100000000000002','SYS_USER_DZT','平台经验账户','P','0','JY','0','0','3acc44c3ca31c0352e84febdf1c21e04','0','0','0',now(),NULL,'CD-CDZT000009','CD-CDZT000009');
