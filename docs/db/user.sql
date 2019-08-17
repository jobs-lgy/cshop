DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `username` varchar(64) NOT NULL,
  `nickname` varchar(200) DEFAULT NULL COMMENT '昵称',
  `password` varchar(128) NOT NULL,
  `icon` varchar(500) DEFAULT NULL COMMENT '头像',
  `email` varchar(100) NOT NULL COMMENT '邮箱',
  `phone` varchar(100) NOT NULL COMMENT '手机号',
  `enabled` int(1) DEFAULT '1' COMMENT '启用状态：0->禁用；1->启用',
  `dept_id` bigint(20) ,
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`),
  UNIQUE KEY `idx_username` (`username`),
  UNIQUE KEY `idx_email` (`email`),
  UNIQUE KEY `idx_phone` (`phone`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8 COMMENT='后台用户表';

INSERT INTO `user` VALUES ('1', 'admin','管理员', '{bcrypt}$2a$10$cKF9DpPPRn9oBfEU4caNHuM3N8hMaz/aI2Q4iuH.JZzhTCXlsviVi',
'http://macro-oss.oss-cn-shenzhen.aliyuncs.com/mall/images/20180607/timg.jpg', 'junecloud@163.com', '17602538618', 1,1, '2018-09-29 13:55:30', '2018-09-29 13:55:39');

DROP TABLE IF EXISTS `resource`;
CREATE TABLE `resource` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `parent_id` bigint(20) ,
  `name` varchar(100) DEFAULT NULL COMMENT '名称',
  `abbr` varchar(200) DEFAULT NULL COMMENT '简称',
  `icon` varchar(500) DEFAULT NULL COMMENT 'icon',
  `type` int(1) DEFAULT NULL COMMENT '权限类型：0->目录；1->菜单；2->按钮（接口绑定权限）',
  `uri` varchar(200) DEFAULT NULL COMMENT '前端资源路径',
  `enabled` int(1) DEFAULT '1' COMMENT '启用状态：0->禁用；1->启用',
  `sort` int(4)  COMMENT '排序',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8 COMMENT='后台用户权限表';

-- ----------------------------
-- Records of ums_permission
-- ----------------------------
INSERT INTO `resource` VALUES ('1', '0', '商品', null, null, '0', null, '1', '0');
INSERT INTO `resource` VALUES ('2', '1', '商品列表', 'pms:product:read', null, '1', '/pms/product/index', '1',  '0');
INSERT INTO `resource` VALUES ('3', '1', '添加商品', 'pms:product:create', null, '1', '/pms/product/add', '1', '0');
INSERT INTO `resource` VALUES ('4', '1', '商品分类', 'pms:productCategory:read', null, '1', '/pms/productCate/index', '1', '0');
INSERT INTO `resource` VALUES ('5', '1', '商品类型', 'pms:productAttribute:read', null, '1', '/pms/productAttr/index', '1', '0');

DROP TABLE IF EXISTS `role`;
CREATE TABLE `role` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(100) DEFAULT NULL COMMENT '名称',
  `abbr` varchar(200) DEFAULT NULL COMMENT '简称',
  `enabled` int(1) DEFAULT '1' COMMENT '启用状态：0->禁用；1->启用',
  `sort` int(4)  COMMENT '排序',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8 COMMENT='后台用户角色表';

INSERT INTO `role` VALUES ('1','商品管理员', 'PRODUCT', 1,1);
INSERT INTO `role` VALUES ('4', '品牌管理员','BRAND', 1,2);

DROP TABLE IF EXISTS `user_resource_rel`;
CREATE TABLE `user_resource_rel` (
  `user_id` bigint(20) NOT NULL,
  `resource_id` bigint(20) NOT NULL,
  PRIMARY KEY (`user_id`,`resource_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='后台用户和权限关系表';

DROP TABLE IF EXISTS `user_role_rel`;
CREATE TABLE `user_role_rel` (
  `user_id` bigint(20) NOT NULL,
  `role_id` bigint(20) NOT NULL,
  PRIMARY KEY (`user_id`,`role_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='后台用户和角色关系表';


DROP TABLE IF EXISTS `role_resource_rel`;
CREATE TABLE `role_resource_rel` (
  `role_id` bigint(20) NOT NULL,
  `resource_id` bigint(20) NOT NULL,
  PRIMARY KEY (`role_id`,`resource_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='后台用户角色和权限关系表';

