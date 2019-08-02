DROP TABLE IF EXISTS `admin`;
CREATE TABLE `admin` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `username` varchar(64) NOT NULL,
  `nick_name` varchar(200) DEFAULT NULL COMMENT '昵称',
  `password` varchar(128) NOT NULL,
  `icon` varchar(500) DEFAULT NULL COMMENT '头像',
  `email` varchar(100) NOT NULL COMMENT '邮箱',
  `phone` varchar(100) NOT NULL COMMENT '手机号',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `active` int(1) DEFAULT '1' COMMENT '启用状态：0->禁用；1->启用',
  PRIMARY KEY (`id`),
  UNIQUE KEY `idx_username` (`username`),
  UNIQUE KEY `idx_email` (`email`),
  UNIQUE KEY `idx_phone` (`phone`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8 COMMENT='后台用户表';

INSERT INTO `admin` VALUES ('1', 'admin','管理员', '{bcrypt}$2a$10$cKF9DpPPRn9oBfEU4caNHuM3N8hMaz/aI2Q4iuH.JZzhTCXlsviVi',
'http://macro-oss.oss-cn-shenzhen.aliyuncs.com/mall/images/20180607/timg.jpg', 'junecloud@163.com', '17602538618', '2018-09-29 13:55:30', '2018-09-29 13:55:39', '1');

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



DROP TABLE IF EXISTS `permission`;
CREATE TABLE `permission` (
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
INSERT INTO `permission` VALUES ('1', '0', '商品', null, null, '0', null, '1', '2018-09-29 16:15:14', '0');
INSERT INTO `permission` VALUES ('2', '1', '商品列表', 'pms:product:read', null, '1', '/pms/product/index', '1', '2018-09-29 16:17:01', '0');
INSERT INTO `permission` VALUES ('3', '1', '添加商品', 'pms:product:create', null, '1', '/pms/product/add', '1', '2018-09-29 16:18:51', '0');
INSERT INTO `permission` VALUES ('4', '1', '商品分类', 'pms:productCategory:read', null, '1', '/pms/productCate/index', '1', '2018-09-29 16:23:07', '0');
INSERT INTO `permission` VALUES ('5', '1', '商品类型', 'pms:productAttribute:read', null, '1', '/pms/productAttr/index', '1', '2018-09-29 16:24:43', '0');
INSERT INTO `permission` VALUES ('6', '1', '品牌管理', 'pms:brand:read', null, '1', '/pms/brand/index', '1', '2018-09-29 16:25:45', '0');
INSERT INTO `permission` VALUES ('7', '2', '编辑商品', 'pms:product:update', null, '2', '/pms/product/updateProduct', '1', '2018-09-29 16:34:23', '0');
INSERT INTO `permission` VALUES ('8', '2', '删除商品', 'pms:product:delete', null, '2', '/pms/product/delete', '1', '2018-09-29 16:38:33', '0');
INSERT INTO `permission` VALUES ('9', '4', '添加商品分类', 'pms:productCategory:create', null, '2', '/pms/productCate/create', '1', '2018-09-29 16:43:23', '0');
INSERT INTO `permission` VALUES ('10', '4', '修改商品分类', 'pms:productCategory:update', null, '2', '/pms/productCate/update', '1', '2018-09-29 16:43:55', '0');
INSERT INTO `permission` VALUES ('11', '4', '删除商品分类', 'pms:productCategory:delete', null, '2', '/pms/productAttr/delete', '1', '2018-09-29 16:44:38', '0');
INSERT INTO `permission` VALUES ('12', '5', '添加商品类型', 'pms:productAttribute:create', null, '2', '/pms/productAttr/create', '1', '2018-09-29 16:45:25', '0');
INSERT INTO `permission` VALUES ('13', '5', '修改商品类型', 'pms:productAttribute:update', null, '2', '/pms/productAttr/update', '1', '2018-09-29 16:48:08', '0');
INSERT INTO `permission` VALUES ('14', '5', '删除商品类型', 'pms:productAttribute:delete', null, '2', '/pms/productAttr/delete', '1', '2018-09-29 16:48:44', '0');
INSERT INTO `permission` VALUES ('15', '6', '添加品牌', 'pms:brand:create', null, '2', '/pms/brand/add', '1', '2018-09-29 16:49:34', '0');
INSERT INTO `permission` VALUES ('16', '6', '修改品牌', 'pms:brand:update', null, '2', '/pms/brand/update', '1', '2018-09-29 16:50:55', '0');
INSERT INTO `permission` VALUES ('17', '6', '删除品牌', 'pms:brand:delete', null, '2', '/pms/brand/delete', '1', '2018-09-29 16:50:59', '0');

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


-- ----------------------------
-- Records of ums_role_permission_relation
-- ----------------------------
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



-- ----------------------------
-- Table structure for ums_growth_change_history
-- ----------------------------
DROP TABLE IF EXISTS `ums_growth_change_history`;
CREATE TABLE `ums_growth_change_history` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `member_id` bigint(20) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `change_type` int(1) DEFAULT NULL COMMENT '改变类型：0->增加；1->减少',
  `change_count` int(11) DEFAULT NULL COMMENT '积分改变数量',
  `operate_man` varchar(100) DEFAULT NULL COMMENT '操作人员',
  `operate_note` varchar(200) DEFAULT NULL COMMENT '操作备注',
  `source_type` int(1) DEFAULT NULL COMMENT '积分来源：0->购物；1->管理员修改',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 COMMENT='成长值变化历史记录表';

-- ----------------------------
-- Records of ums_growth_change_history
-- ----------------------------
INSERT INTO `ums_growth_change_history` VALUES ('1', '1', '2018-08-29 17:16:35', '0', '1000', 'test', '测试使用', '1');

-- ----------------------------
-- Table structure for ums_integration_change_history
-- ----------------------------
DROP TABLE IF EXISTS `ums_integration_change_history`;
CREATE TABLE `ums_integration_change_history` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `member_id` bigint(20) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `change_type` int(1) DEFAULT NULL COMMENT '改变类型：0->增加；1->减少',
  `change_count` int(11) DEFAULT NULL COMMENT '积分改变数量',
  `operate_man` varchar(100) DEFAULT NULL COMMENT '操作人员',
  `operate_note` varchar(200) DEFAULT NULL COMMENT '操作备注',
  `source_type` int(1) DEFAULT NULL COMMENT '积分来源：0->购物；1->管理员修改',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='积分变化历史记录表';

-- ----------------------------
-- Records of ums_integration_change_history
-- ----------------------------

-- ----------------------------
-- Table structure for ums_integration_consume_setting
-- ----------------------------
DROP TABLE IF EXISTS `ums_integration_consume_setting`;
CREATE TABLE `ums_integration_consume_setting` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `deduction_per_amount` int(11) DEFAULT NULL COMMENT '每一元需要抵扣的积分数量',
  `max_percent_per_order` int(11) DEFAULT NULL COMMENT '每笔订单最高抵用百分比',
  `use_unit` int(11) DEFAULT NULL COMMENT '每次使用积分最小单位100',
  `coupon_status` int(1) DEFAULT NULL COMMENT '是否可以和优惠券同用；0->不可以；1->可以',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 COMMENT='积分消费设置';

-- ----------------------------
-- Records of ums_integration_consume_setting
-- ----------------------------
INSERT INTO `ums_integration_consume_setting` VALUES ('1', '100', '50', '100', '1');

-- ----------------------------
-- Table structure for ums_member
-- ----------------------------
DROP TABLE IF EXISTS `ums_member`;
CREATE TABLE `ums_member` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `member_level_id` bigint(20) DEFAULT NULL,
  `username` varchar(64) DEFAULT NULL COMMENT '用户名',
  `password` varchar(64) DEFAULT NULL COMMENT '密码',
  `nickname` varchar(64) DEFAULT NULL COMMENT '昵称',
  `phone` varchar(64) DEFAULT NULL COMMENT '手机号码',
  `status` int(1) DEFAULT NULL COMMENT '帐号启用状态:0->禁用；1->启用',
  `create_time` datetime DEFAULT NULL COMMENT '注册时间',
  `icon` varchar(500) DEFAULT NULL COMMENT '头像',
  `gender` int(1) DEFAULT NULL COMMENT '性别：0->未知；1->男；2->女',
  `birthday` date DEFAULT NULL COMMENT '生日',
  `city` varchar(64) DEFAULT NULL COMMENT '所做城市',
  `job` varchar(100) DEFAULT NULL COMMENT '职业',
  `personalized_signature` varchar(200) DEFAULT NULL COMMENT '个性签名',
  `source_type` int(1) DEFAULT NULL COMMENT '用户来源',
  `integration` int(11) DEFAULT NULL COMMENT '积分',
  `growth` int(11) DEFAULT NULL COMMENT '成长值',
  `luckey_count` int(11) DEFAULT NULL COMMENT '剩余抽奖次数',
  `history_integration` int(11) DEFAULT NULL COMMENT '历史积分数量',
  PRIMARY KEY (`id`),
  UNIQUE KEY `idx_username` (`username`),
  UNIQUE KEY `idx_phone` (`phone`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8 COMMENT='会员表';

-- ----------------------------
-- Records of ums_member
-- ----------------------------
INSERT INTO `ums_member` VALUES ('1', '4', 'test', '$2a$10$NZ5o7r2E.ayT2ZoxgjlI.eJ6OEYqjH7INR/F.mXDbjZJi9HF0YCVG', 'windir', '18061581849', '1', '2018-08-02 10:35:44', null, '1', '2009-06-01', '上海', '学生', 'test', null, '5000', null, null, null);
INSERT INTO `ums_member` VALUES ('3', '4', 'windy', '$2a$10$NZ5o7r2E.ayT2ZoxgjlI.eJ6OEYqjH7INR/F.mXDbjZJi9HF0YCVG', 'windy', '18061581848', '1', '2018-08-03 16:46:38', null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `ums_member` VALUES ('4', '4', 'zhengsan', '$2a$10$NZ5o7r2E.ayT2ZoxgjlI.eJ6OEYqjH7INR/F.mXDbjZJi9HF0YCVG', 'zhengsan', '18061581847', '1', '2018-11-12 14:12:04', null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `ums_member` VALUES ('5', '4', 'lisi', '$2a$10$NZ5o7r2E.ayT2ZoxgjlI.eJ6OEYqjH7INR/F.mXDbjZJi9HF0YCVG', 'lisi', '18061581841', '1', '2018-11-12 14:12:38', null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `ums_member` VALUES ('6', '4', 'wangwu', '$2a$10$NZ5o7r2E.ayT2ZoxgjlI.eJ6OEYqjH7INR/F.mXDbjZJi9HF0YCVG', 'wangwu', '18061581842', '1', '2018-11-12 14:13:09', null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `ums_member` VALUES ('7', '4', 'lion', '$2a$10$NZ5o7r2E.ayT2ZoxgjlI.eJ6OEYqjH7INR/F.mXDbjZJi9HF0YCVG', 'lion', '18061581845', '1', '2018-11-12 14:21:39', null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `ums_member` VALUES ('8', '4', 'shari', '$2a$10$NZ5o7r2E.ayT2ZoxgjlI.eJ6OEYqjH7INR/F.mXDbjZJi9HF0YCVG', 'shari', '18061581844', '1', '2018-11-12 14:22:00', null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `ums_member` VALUES ('9', '4', 'aewen', '$2a$10$NZ5o7r2E.ayT2ZoxgjlI.eJ6OEYqjH7INR/F.mXDbjZJi9HF0YCVG', 'aewen', '18061581843', '1', '2018-11-12 14:22:55', null, null, null, null, null, null, null, null, null, null, null);

-- ----------------------------
-- Table structure for ums_member_level
-- ----------------------------
DROP TABLE IF EXISTS `ums_member_level`;
CREATE TABLE `ums_member_level` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(100) DEFAULT NULL,
  `growth_point` int(11) DEFAULT NULL,
  `default_status` int(1) DEFAULT NULL COMMENT '是否为默认等级：0->不是；1->是',
  `free_freight_point` decimal(10,2) DEFAULT NULL COMMENT '免运费标准',
  `comment_growth_point` int(11) DEFAULT NULL COMMENT '每次评价获取的成长值',
  `priviledge_free_freight` int(1) DEFAULT NULL COMMENT '是否有免邮特权',
  `priviledge_sign_in` int(1) DEFAULT NULL COMMENT '是否有签到特权',
  `priviledge_comment` int(1) DEFAULT NULL COMMENT '是否有评论获奖励特权',
  `priviledge_promotion` int(1) DEFAULT NULL COMMENT '是否有专享活动特权',
  `priviledge_member_price` int(1) DEFAULT NULL COMMENT '是否有会员价格特权',
  `priviledge_birthday` int(1) DEFAULT NULL COMMENT '是否有生日特权',
  `note` varchar(200) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8 COMMENT='会员等级表';

-- ----------------------------
-- Records of ums_member_level
-- ----------------------------
INSERT INTO `ums_member_level` VALUES ('1', '黄金会员', '1000', '0', '199.00', '5', '1', '1', '1', '1', '1', '1', null);
INSERT INTO `ums_member_level` VALUES ('2', '白金会员', '5000', '0', '99.00', '10', '1', '1', '1', '1', '1', '1', null);
INSERT INTO `ums_member_level` VALUES ('3', '钻石会员', '15000', '0', '69.00', '15', '1', '1', '1', '1', '1', '1', null);
INSERT INTO `ums_member_level` VALUES ('4', '普通会员', '1', '1', '199.00', '20', '1', '1', '1', '1', '0', '0', null);

-- ----------------------------
-- Table structure for ums_member_login_log
-- ----------------------------
DROP TABLE IF EXISTS `ums_member_login_log`;
CREATE TABLE `ums_member_login_log` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `member_id` bigint(20) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `ip` varchar(64) DEFAULT NULL,
  `city` varchar(64) DEFAULT NULL,
  `login_type` int(1) DEFAULT NULL COMMENT '登录类型：0->PC；1->android;2->ios;3->小程序',
  `province` varchar(64) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='会员登录记录';

-- ----------------------------
-- Records of ums_member_login_log
-- ----------------------------

-- ----------------------------
-- Table structure for ums_member_member_tag_relation
-- ----------------------------
DROP TABLE IF EXISTS `ums_member_member_tag_relation`;
CREATE TABLE `ums_member_member_tag_relation` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `member_id` bigint(20) DEFAULT NULL,
  `tag_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户和标签关系表';

-- ----------------------------
-- Records of ums_member_member_tag_relation
-- ----------------------------

-- ----------------------------
-- Table structure for ums_member_product_category_relation
-- ----------------------------
DROP TABLE IF EXISTS `ums_member_product_category_relation`;
CREATE TABLE `ums_member_product_category_relation` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `member_id` bigint(20) DEFAULT NULL,
  `product_category_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='会员与产品分类关系表（用户喜欢的分类）';

-- ----------------------------
-- Records of ums_member_product_category_relation
-- ----------------------------

-- ----------------------------
-- Table structure for ums_member_receive_address
-- ----------------------------
DROP TABLE IF EXISTS `ums_member_receive_address`;
CREATE TABLE `ums_member_receive_address` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `member_id` bigint(20) DEFAULT NULL,
  `name` varchar(100) DEFAULT NULL COMMENT '收货人名称',
  `phone_number` varchar(64) DEFAULT NULL,
  `default_status` int(1) DEFAULT NULL COMMENT '是否为默认',
  `post_code` varchar(100) DEFAULT NULL COMMENT '邮政编码',
  `province` varchar(100) DEFAULT NULL COMMENT '省份/直辖市',
  `city` varchar(100) DEFAULT NULL COMMENT '城市',
  `region` varchar(100) DEFAULT NULL COMMENT '区',
  `detail_address` varchar(128) DEFAULT NULL COMMENT '详细地址(街道)',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8 COMMENT='会员收货地址表';

-- ----------------------------
-- Records of ums_member_receive_address
-- ----------------------------
INSERT INTO `ums_member_receive_address` VALUES ('1', '1', '大梨', '18033441849', '0', '518000', '广东省', '深圳市', '南山区', '科兴科学园');
INSERT INTO `ums_member_receive_address` VALUES ('3', '1', '大梨', '18033441849', '0', '518000', '广东省', '深圳市', '福田区', '清水河街道');
INSERT INTO `ums_member_receive_address` VALUES ('4', '1', '大梨', '18033441849', '1', '518000', '广东省', '深圳市', '福田区', '东晓街道');

-- ----------------------------
-- Table structure for ums_member_rule_setting
-- ----------------------------
DROP TABLE IF EXISTS `ums_member_rule_setting`;
CREATE TABLE `ums_member_rule_setting` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `continue_sign_day` int(11) DEFAULT NULL COMMENT '连续签到天数',
  `continue_sign_point` int(11) DEFAULT NULL COMMENT '连续签到赠送数量',
  `consume_per_point` decimal(10,2) DEFAULT NULL COMMENT '每消费多少元获取1个点',
  `low_order_amount` decimal(10,2) DEFAULT NULL COMMENT '最低获取点数的订单金额',
  `max_point_per_order` int(11) DEFAULT NULL COMMENT '每笔订单最高获取点数',
  `type` int(1) DEFAULT NULL COMMENT '类型：0->积分规则；1->成长值规则',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='会员积分成长规则表';

-- ----------------------------
-- Records of ums_member_rule_setting
-- ----------------------------

-- ----------------------------
-- Table structure for ums_member_statistics_info
-- ----------------------------
DROP TABLE IF EXISTS `ums_member_statistics_info`;
CREATE TABLE `ums_member_statistics_info` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `member_id` bigint(20) DEFAULT NULL,
  `consume_amount` decimal(10,2) DEFAULT NULL COMMENT '累计消费金额',
  `order_count` int(11) DEFAULT NULL COMMENT '订单数量',
  `coupon_count` int(11) DEFAULT NULL COMMENT '优惠券数量',
  `comment_count` int(11) DEFAULT NULL COMMENT '评价数',
  `return_order_count` int(11) DEFAULT NULL COMMENT '退货数量',
  `login_count` int(11) DEFAULT NULL COMMENT '登录次数',
  `attend_count` int(11) DEFAULT NULL COMMENT '关注数量',
  `fans_count` int(11) DEFAULT NULL COMMENT '粉丝数量',
  `collect_product_count` int(11) DEFAULT NULL,
  `collect_subject_count` int(11) DEFAULT NULL,
  `collect_topic_count` int(11) DEFAULT NULL,
  `collect_comment_count` int(11) DEFAULT NULL,
  `invite_friend_count` int(11) DEFAULT NULL,
  `recent_order_time` datetime DEFAULT NULL COMMENT '最后一次下订单时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='会员统计信息';

-- ----------------------------
-- Records of ums_member_statistics_info
-- ----------------------------

-- ----------------------------
-- Table structure for ums_member_tag
-- ----------------------------
DROP TABLE IF EXISTS `ums_member_tag`;
CREATE TABLE `ums_member_tag` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(100) DEFAULT NULL,
  `finish_order_count` int(11) DEFAULT NULL COMMENT '自动打标签完成订单数量',
  `finish_order_amount` decimal(10,2) DEFAULT NULL COMMENT '自动打标签完成订单金额',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户标签表';

-- ----------------------------
-- Records of ums_member_tag
-- ----------------------------

-- ----------------------------
-- Table structure for ums_member_task
-- ----------------------------
DROP TABLE IF EXISTS `ums_member_task`;
CREATE TABLE `ums_member_task` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(100) DEFAULT NULL,
  `growth` int(11) DEFAULT NULL COMMENT '赠送成长值',
  `intergration` int(11) DEFAULT NULL COMMENT '赠送积分',
  `type` int(1) DEFAULT NULL COMMENT '任务类型：0->新手任务；1->日常任务',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='会员任务表';

-- ----------------------------
-- Records of ums_member_task
-- ----------------------------

