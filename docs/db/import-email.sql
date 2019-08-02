SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

create database com.javachen.cshop.email;

DROP TABLE IF EXISTS `email_record`;
CREATE TABLE `email_record`
(
    `id`        bigint(20) NOT NULL AUTO_INCREMENT,
    `sent_time` datetime     DEFAULT NULL COMMENT '发送时间',
    `source`    varchar(255) DEFAULT NULL COMMENT '发送方',
    `target`    varchar(255) DEFAULT NULL COMMENT '接受方',
    `type`      int(11)      DEFAULT NULL COMMENT '类型：1注册',
    `user_id`   bigint(20)   DEFAULT NULL COMMENT '用户id',
    PRIMARY KEY (`id`),
    UNIQUE KEY `user_id` (`user_id`) USING BTREE
) ENGINE = InnoDB
  AUTO_INCREMENT = 28
  DEFAULT CHARSET = utf8 COMMENT ='邮件记录';

DROP TABLE IF EXISTS `email_click`;
CREATE TABLE `email_click`
(
    `id`              bigint(20) NOT NULL AUTO_INCREMENT,
    `clicked_time`    datetime     DEFAULT NULL,
    `email_record_id` bigint(20)   DEFAULT NULL,
    `query`           varchar(255) DEFAULT NULL,
    `url`             varchar(255) DEFAULT NULL,
    PRIMARY KEY (`id`),
    UNIQUE KEY `email_record_id` (`email_record_id`) USING BTREE
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8 COMMENT ='邮件点击';


DROP TABLE IF EXISTS `email_open`;
CREATE TABLE `email_open`
(
    `id`              bigint(20) NOT NULL,
    `email_record_id` bigint(20)   DEFAULT NULL,
    `open_time`       datetime     DEFAULT NULL,
    `user_agent`      varchar(255) DEFAULT NULL,
    PRIMARY KEY (`id`),
    UNIQUE KEY `email_record_id` (`email_record_id`) USING BTREE
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8 COMMENT ='邮件打开';