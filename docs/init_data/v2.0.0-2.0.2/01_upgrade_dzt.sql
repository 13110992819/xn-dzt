alter table tdzt_craft add column is_default varchar(32) DEFAULT NULL COMMENT '是否默认数据' after is_hit;
SET SQL_SAFE_UPDATES = 0;
update tdzt_craft set is_default='0';