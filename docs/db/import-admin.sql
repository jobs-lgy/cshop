DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `username` varchar(64) NOT NULL,
  `nickname` varchar(200) DEFAULT NULL COMMENT '昵称',
  `password` varchar(128) NOT NULL,
  `icon` varchar(500) DEFAULT NULL COMMENT '头像',
  `email` varchar(100) NOT NULL COMMENT '邮箱',
  `phone` varchar(100) NOT NULL COMMENT '手机号',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `status` int(1) DEFAULT '1' COMMENT '启用状态：0->禁用；1->启用',
  `dept_id` bigint(20) ,
  PRIMARY KEY (`id`),
  UNIQUE KEY `idx_username` (`username`),
  UNIQUE KEY `idx_email` (`email`),
  UNIQUE KEY `idx_phone` (`phone`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8 COMMENT='后台用户表';

INSERT INTO `user` VALUES ('1', 'admin','管理员', '{bcrypt}$2a$10$cKF9DpPPRn9oBfEU4caNHuM3N8hMaz/aI2Q4iuH.JZzhTCXlsviVi',
'http://macro-oss.oss-cn-shenzhen.aliyuncs.com/mall/images/20180607/timg.jpg', 'junecloud@163.com', '17602538618', '2018-09-29 13:55:30', '2018-09-29 13:55:39', '1',1);

DROP TABLE IF EXISTS `admin_login_log`;
CREATE TABLE `admin_login_log` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `admin_id` bigint(20) DEFAULT NULL,
  `ip` varchar(64) NOT NULL,
  `address` varchar(100) DEFAULT NULL,
  `user_agent` varchar(100) NOT NULL COMMENT '浏览器登录类型',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8 COMMENT='后台用户登录日志表';



DROP TABLE IF EXISTS `resource`;
CREATE TABLE `resource` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(100) DEFAULT NULL COMMENT '名称',
  `label` varchar(200) DEFAULT NULL COMMENT '中文名称，用于展示',
  `description` varchar(500) DEFAULT NULL COMMENT '描述',
  `type` int(1) DEFAULT NULL COMMENT '权限类型：0->目录；1->菜单；2->按钮（接口绑定权限）',
  `uri` varchar(200) DEFAULT NULL COMMENT '前端资源路径',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8 COMMENT='后台用户权限表';

-- ----------------------------
-- Records of ums_permission
-- ----------------------------
INSERT INTO `resource` VALUES ('1', '0', '商品', null, null, '0', null, '1', '2018-09-29 16:15:14', '0');
INSERT INTO `resource` VALUES ('2', '1', '商品列表', 'pms:product:read', null, '1', '/pms/product/index', '1', '2018-09-29 16:17:01', '0');
INSERT INTO `resource` VALUES ('3', '1', '添加商品', 'pms:product:create', null, '1', '/pms/product/add', '1', '2018-09-29 16:18:51', '0');
INSERT INTO `resource` VALUES ('4', '1', '商品分类', 'pms:productCategory:read', null, '1', '/pms/productCate/index', '1', '2018-09-29 16:23:07', '0');
INSERT INTO `resource` VALUES ('5', '1', '商品类型', 'pms:productAttribute:read', null, '1', '/pms/productAttr/index', '1', '2018-09-29 16:24:43', '0');
INSERT INTO `resource` VALUES ('6', '1', '品牌管理', 'pms:brand:read', null, '1', '/pms/brand/index', '1', '2018-09-29 16:25:45', '0');
INSERT INTO `resource` VALUES ('7', '2', '编辑商品', 'pms:product:update', null, '2', '/pms/product/updateProduct', '1', '2018-09-29 16:34:23', '0');
INSERT INTO `resource` VALUES ('8', '2', '删除商品', 'pms:product:delete', null, '2', '/pms/product/delete', '1', '2018-09-29 16:38:33', '0');
INSERT INTO `resource` VALUES ('9', '4', '添加商品分类', 'pms:productCategory:create', null, '2', '/pms/productCate/create', '1', '2018-09-29 16:43:23', '0');
INSERT INTO `resource` VALUES ('10', '4', '修改商品分类', 'pms:productCategory:update', null, '2', '/pms/productCate/update', '1', '2018-09-29 16:43:55', '0');
INSERT INTO `resource` VALUES ('11', '4', '删除商品分类', 'pms:productCategory:delete', null, '2', '/pms/productAttr/delete', '1', '2018-09-29 16:44:38', '0');
INSERT INTO `resource` VALUES ('12', '5', '添加商品类型', 'pms:productAttribute:create', null, '2', '/pms/productAttr/create', '1', '2018-09-29 16:45:25', '0');
INSERT INTO `resource` VALUES ('13', '5', '修改商品类型', 'pms:productAttribute:update', null, '2', '/pms/productAttr/update', '1', '2018-09-29 16:48:08', '0');
INSERT INTO `resource` VALUES ('14', '5', '删除商品类型', 'pms:productAttribute:delete', null, '2', '/pms/productAttr/delete', '1', '2018-09-29 16:48:44', '0');
INSERT INTO `resource` VALUES ('15', '6', '添加品牌', 'pms:brand:create', null, '2', '/pms/brand/add', '1', '2018-09-29 16:49:34', '0');
INSERT INTO `resource` VALUES ('16', '6', '修改品牌', 'pms:brand:update', null, '2', '/pms/brand/update', '1', '2018-09-29 16:50:55', '0');
INSERT INTO `resource` VALUES ('17', '6', '删除品牌', 'pms:brand:delete', null, '2', '/pms/brand/delete', '1', '2018-09-29 16:50:59', '0');

DROP TABLE IF EXISTS `role`;
CREATE TABLE `role` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(100) DEFAULT NULL COMMENT '名称',
  `label` varchar(100) DEFAULT NULL COMMENT '中文名称',
  `description` varchar(500) DEFAULT NULL COMMENT '描述',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8 COMMENT='后台用户角色表';

INSERT INTO `role` VALUES ('1', 'PRODUCT','商品管理员', '商品管理员');
INSERT INTO `role` VALUES ('2', 'PRODUCT_CATA','商品分类管理员', '商品分类管理员');
INSERT INTO `role` VALUES ('3', 'PRODUCT_TYPE','商品类型管理员', '商品类型管理员');
INSERT INTO `role` VALUES ('4', 'BRAND','品牌管理员', '品牌管理员');

DROP TABLE IF EXISTS `admin_permission_rel`;
CREATE TABLE `admin_permission_rel` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `admin_id` bigint(20) DEFAULT NULL,
  `permission_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='后台用户和权限关系表';

DROP TABLE IF EXISTS `admin_role_rel`;
CREATE TABLE `admin_role_rel` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `admin_id` bigint(20) DEFAULT NULL,
  `role_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8 COMMENT='后台用户和角色关系表';


DROP TABLE IF EXISTS `role_permission_rel`;
CREATE TABLE `role_permission_rel` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `role_id` bigint(20) DEFAULT NULL,
  `permission_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=18 DEFAULT CHARSET=utf8 COMMENT='后台用户角色和权限关系表';


DROP TABLE IF EXISTS `module`;
CREATE TABLE `module` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(100) DEFAULT NULL COMMENT '名称',
  `label` varchar(200) DEFAULT NULL COMMENT '中文名称，用于展示',
  `icon` varchar(500) DEFAULT NULL COMMENT '描述',
  `sort` int(2) DEFAULT NULL COMMENT '排序',
  `uri` varchar(200) DEFAULT NULL COMMENT '前端资源路径',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8 COMMENT='模块表';


DROP TABLE IF EXISTS `section`;
CREATE TABLE `section` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(100) DEFAULT NULL COMMENT '名称',
  `label` varchar(200) DEFAULT NULL COMMENT '中文名称，用于展示',
  `icon` varchar(500) DEFAULT NULL COMMENT '描述',
  `sort` int(2) DEFAULT NULL COMMENT '排序',
  `uri` varchar(200) DEFAULT NULL COMMENT '前端资源路径',
  `module_id` bigint(20) NOT NULL COMMENT '模块id',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8 COMMENT='模块表';

DROP TABLE IF EXISTS `section_permission_rel`;
CREATE TABLE `section_permission_rel` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `section_id` bigint(20) DEFAULT NULL,
  `permission_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=18 DEFAULT CHARSET=utf8 COMMENT='菜单和权限关系表';

INSERT INTO `role_permission_rel` VALUES ('1', '1', '1');
INSERT INTO `role_permission_rel` VALUES ('2', '1', '2');
INSERT INTO `role_permission_rel` VALUES ('3', '1', '3');
INSERT INTO `role_permission_rel` VALUES ('4', '1', '7');
INSERT INTO `role_permission_rel` VALUES ('5', '1', '8');
INSERT INTO `role_permission_rel` VALUES ('6', '2', '4');
INSERT INTO `role_permission_rel` VALUES ('7', '2', '9');
INSERT INTO `role_permission_rel` VALUES ('8', '2', '10');
INSERT INTO `role_permission_rel` VALUES ('9', '2', '11');
INSERT INTO `role_permission_rel` VALUES ('10', '3', '5');
INSERT INTO `role_permission_rel` VALUES ('11', '3', '12');
INSERT INTO `role_permission_rel` VALUES ('12', '3', '13');
INSERT INTO `role_permission_rel` VALUES ('13', '3', '14');
INSERT INTO `role_permission_rel` VALUES ('14', '4', '6');
INSERT INTO `role_permission_rel` VALUES ('15', '4', '15');
INSERT INTO `role_permission_rel` VALUES ('16', '4', '16');
INSERT INTO `role_permission_rel` VALUES ('17', '4', '17');
