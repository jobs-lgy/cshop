SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for tb_order
-- ----------------------------
DROP TABLE IF EXISTS `tb_order`;
CREATE TABLE `tb_order` (
  `order_id` bigint(20) NOT NULL COMMENT '订单id',
  `total_pay` bigint(20) NOT NULL COMMENT '总金额，单位为分',
  `actual_pay` bigint(20) NOT NULL COMMENT '实付金额。单位:分。如:20007，表示:200元7分',
  `promotion_ids` varchar(255) COLLATE utf8_bin DEFAULT '',
  `payment_type` tinyint(1) unsigned zerofill NOT NULL COMMENT '支付类型，1、在线支付，2、货到付款',
  `post_fee` bigint(20) NOT NULL COMMENT '邮费。单位:分。如:20007，表示:200元7分',
  `create_time` datetime DEFAULT NULL COMMENT '订单创建时间',
  `shipping_name` varchar(20) COLLATE utf8_bin DEFAULT NULL COMMENT '物流名称',
  `shipping_code` varchar(20) COLLATE utf8_bin DEFAULT NULL COMMENT '物流单号',
  `user_id` varchar(50) COLLATE utf8_bin NOT NULL COMMENT '用户id',
  `buyer_message` varchar(100) COLLATE utf8_bin DEFAULT NULL COMMENT '买家留言',
  `buyer_name` varchar(50) COLLATE utf8_bin NOT NULL COMMENT '买家昵称',
  `buyer_rate` tinyint(1) DEFAULT NULL COMMENT '买家是否已经评价,0未评价，1已评价',
  `receiver_province` varchar(100) COLLATE utf8_bin DEFAULT '' COMMENT '收获地址（省）',
  `receiver_city` varchar(255) COLLATE utf8_bin DEFAULT '' COMMENT '收获地址（市）',
  `receiver_district` varchar(255) COLLATE utf8_bin DEFAULT '' COMMENT '收获地址（区/县）',
  `receiver_street` varchar(255) COLLATE utf8_bin DEFAULT '' COMMENT '收获地址（街道、住址等详细地址）',
  `receiver_phone` varchar(12) COLLATE utf8_bin DEFAULT NULL COMMENT '收货人手机',
  `receiver_zip` varchar(15) COLLATE utf8_bin DEFAULT NULL COMMENT '收货人邮编',
  `receiver` varchar(50) COLLATE utf8_bin DEFAULT NULL COMMENT '收货人',
  `invoice_type` int(1) DEFAULT '0' COMMENT '发票类型(0无发票1普通发票，2电子发票，3增值税发票)',
  `source_type` int(1) DEFAULT '2' COMMENT '订单来源：1:app端，2：pc端，3：M端，4：微信端，5：手机qq端',
  PRIMARY KEY (`order_id`),
  KEY `create_time` (`create_time`),
  KEY `buyer_name` (`buyer_name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;


-- ----------------------------
-- Records of tb_order
-- ----------------------------
INSERT INTO `tb_order` VALUES ('992320617728577536', '199900', '199900', '', '2', '0', '2018-05-04 16:30:46', null, null, '16', null, 'huge', '0', '上海', '上海', '浦东新区', '航头镇航头路18号传智播客 3号楼', '15800000000', '210000', '虎哥', '0', '2');
INSERT INTO `tb_order` VALUES ('992320724226150400', '169900', '169900', '', '2', '0', '2018-05-04 16:31:12', null, null, '16', null, 'huge', '0', '上海', '上海', '浦东新区', '航头镇航头路18号传智播客 3号楼', '15800000000', '210000', '虎哥', '0', '2');
INSERT INTO `tb_order` VALUES ('992320901993336832', '142900', '142900', '', '2', '0', '2018-05-04 16:31:54', null, null, '16', null, 'huge', '0', '上海', '上海', '浦东新区', '航头镇航头路18号传智播客 3号楼', '15800000000', '210000', '虎哥', '0', '2');
INSERT INTO `tb_order` VALUES ('992342776106586112', '322800', '322800', '', '2', '0', '2018-05-04 17:58:49', null, null, '16', null, 'huge', '0', '上海', '上海', '浦东新区', '航头镇航头路18号传智播客 3号楼', '15800000000', '210000', '虎哥', '0', '2');

-- ----------------------------
-- Table structure for tb_order_detail
-- ----------------------------
DROP TABLE IF EXISTS `tb_order_detail`;
CREATE TABLE `tb_order_detail` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '订单详情id ',
  `order_id` bigint(20) NOT NULL COMMENT '订单id',
  `sku_id` bigint(20) NOT NULL COMMENT 'sku商品id',
  `num` int(11) NOT NULL COMMENT '购买数量',
  `title` varchar(200) NOT NULL COMMENT '商品标题',
  `own_spec` varchar(1000) DEFAULT '' COMMENT '商品动态属性键值集',
  `price` bigint(20) NOT NULL COMMENT '价格,单位：分',
  `image` varchar(200) DEFAULT '' COMMENT '商品图片',
  PRIMARY KEY (`id`),
  KEY `key_order_id` (`order_id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=24 DEFAULT CHARSET=utf8 COMMENT='订单详情表';

-- ----------------------------
-- Records of tb_order_detail
-- ----------------------------
INSERT INTO `tb_order_detail` VALUES ('19', '992320617728577536', '27179308371', '1', '锤子（smartisan） 坚果32 手机  4GB 4GB 炫黑色特别版 27179308371', '{\"机身颜色\":\"炫黑色特别版\",\"内存\":\"4GB\",\"机身存储\":\"64GB\"}', '199900', 'http://image.leyou.com/images/1/5/1524297608684.jpg');
INSERT INTO `tb_order_detail` VALUES ('20', '992320724226150400', '27038143959', '1', '锤子（smartisan） 坚果32 手机  4GB 4GB 酒红色 27038143959', '{\"机身颜色\":\"酒红色\",\"内存\":\"4GB\",\"机身存储\":\"64GB\"}', '169900', 'http://image.leyou.com/images/8/14/1524297608290.jpg');
INSERT INTO `tb_order_detail` VALUES ('21', '992320901993336832', '10987960993', '1', '华为（HUAWEI） nova 智能手机 4G手机 皓月银 (4G+64G)高配', '{\"机身颜色\":\"皓月银\",\"内存\":\"4GB\",\"机身存储\":\"64GB\"}', '142900', 'http://image.leyou.com/images/10/5/1524297467695.jpg');
INSERT INTO `tb_order_detail` VALUES ('22', '992342776106586112', '27179308373', '1', '锤子（smartisan） 坚果32 手机  4GB 4GB 炫红色特别版 27179308373', '{\"机身存储\":\"4GB\",\"内存\":\"4GB\",\"机身颜色\":\"炫红色特别版\"}', '179900', 'http://image.leyou.com/images/5/11/1524297608996.jpg');
INSERT INTO `tb_order_detail` VALUES ('23', '992342776106586112', '10987960993', '1', '华为（HUAWEI） nova 智能手机 4G手机 皓月银 (4G+64G)高配', '{\"机身颜色\":\"皓月银\",\"内存\":\"4GB\",\"机身存储\":\"64GB\"}', '142900', 'http://image.leyou.com/images/10/5/1524297467695.jpg');

-- ----------------------------
-- Table structure for tb_order_status
-- ----------------------------
DROP TABLE IF EXISTS `tb_order_status`;
CREATE TABLE `tb_order_status` (
  `order_id` bigint(20) NOT NULL COMMENT '订单id',
  `status` int(1) DEFAULT NULL COMMENT '状态：1、未付款 2、已付款,未发货 3、已发货,未确认 4、交易成功 5、交易关闭 6、已评价',
  `create_time` datetime DEFAULT NULL COMMENT '订单创建时间',
  `payment_time` datetime DEFAULT NULL COMMENT '付款时间',
  `consign_time` datetime DEFAULT NULL COMMENT '发货时间',
  `end_time` datetime DEFAULT NULL COMMENT '交易完成时间',
  `close_time` datetime DEFAULT NULL COMMENT '交易关闭时间',
  `comment_time` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '评价时间',
  PRIMARY KEY (`order_id`),
  KEY `status` (`status`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='订单状态表';

-- ----------------------------
-- Records of tb_order_status
-- ----------------------------
INSERT INTO `tb_order_status` VALUES ('992320617728577536', '1', '2018-05-04 16:30:46', null, null, null, null, null);
INSERT INTO `tb_order_status` VALUES ('992320724226150400', '1', '2018-05-04 16:31:12', null, null, null, null, null);
INSERT INTO `tb_order_status` VALUES ('992320901993336832', '3', '2018-05-04 16:31:54', '2018-05-02 18:51:41', '2018-05-04 18:51:51', null, null, '2018-05-04 18:51:59');
INSERT INTO `tb_order_status` VALUES ('992342776106586112', '2', '2018-05-04 17:58:49', '2018-05-04 18:51:30', null, null, null, '2018-05-04 18:51:33');

