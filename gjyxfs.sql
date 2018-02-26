
DROP DATABASE IF EXISTS `gjyxfs`;
CREATE DATABASE `gjyxfs`;

USE `gjyxfs`;

DROP TABLE IF EXISTS `visit`;

CREATE TABLE `visit` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `ip` varchar(45) DEFAULT NULL COMMENT '访问ip',
  `ip_address` varchar(45) DEFAULT NULL COMMENT '访问地区',
  `createtime` varchar(20) DEFAULT NULL COMMENT '访问时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;

