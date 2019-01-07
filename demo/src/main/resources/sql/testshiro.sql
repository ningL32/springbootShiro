/*
Navicat MySQL Data Transfer

Source Server         : myDemo
Source Server Version : 50624
Source Host           : localhost:3306
Source Database       : testshiro

Target Server Type    : MYSQL
Target Server Version : 50624
File Encoding         : 65001

Date: 2019-01-07 23:03:06
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for contacts
-- ----------------------------
DROP TABLE IF EXISTS `contacts`;
CREATE TABLE `contacts` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `first_name` varchar(50) NOT NULL,
  `last_name` varchar(50) NOT NULL,
  `email` varchar(255) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of contacts
-- ----------------------------
INSERT INTO `contacts` VALUES ('1', 'Carine ', 'Schmitt', 'carine.schmitt@qq.com');
INSERT INTO `contacts` VALUES ('2', 'Jean', 'King', 'jean.king@yiibai.com');
INSERT INTO `contacts` VALUES ('3', 'Peter', 'Ferguson', 'peter.ferguson@google.com');
INSERT INTO `contacts` VALUES ('4', 'Janine ', 'Labrune', 'janine.labrune@aol.com');
INSERT INTO `contacts` VALUES ('5', 'Jonas ', 'Bergulfsen', 'jonas.bergulfsen@mac.com');
INSERT INTO `contacts` VALUES ('6', 'Janine ', 'Labrune', 'janine.labrune@aol.com');
INSERT INTO `contacts` VALUES ('7', 'Susan', 'Nelson', 'susan.nelson@qq.com');
INSERT INTO `contacts` VALUES ('8', 'Zbyszek ', 'Piestrzeniewicz', 'zbyszek.piestrzeniewicz@qq.com');
INSERT INTO `contacts` VALUES ('9', 'Roland', 'Keitel', 'roland.keitel@yahoo.com');
INSERT INTO `contacts` VALUES ('10', 'Julie', 'Murphy', 'julie.murphy@yahoo.com');
INSERT INTO `contacts` VALUES ('11', 'Kwai', 'Lee', 'kwai.lee@google.com');
INSERT INTO `contacts` VALUES ('12', 'Jean', 'King', 'jean.king@qq.com');
INSERT INTO `contacts` VALUES ('13', 'Susan', 'Nelson', 'susan.nelson@qq.comt');
INSERT INTO `contacts` VALUES ('14', 'Roland', 'Keitel', 'roland.keitel@yahoo.com');

-- ----------------------------
-- Table structure for permission
-- ----------------------------
DROP TABLE IF EXISTS `permission`;
CREATE TABLE `permission` (
  `pid` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `name` varchar(255) NOT NULL,
  `url` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`pid`),
  UNIQUE KEY `pid` (`pid`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of permission
-- ----------------------------
INSERT INTO `permission` VALUES ('1', 'add', '');
INSERT INTO `permission` VALUES ('2', 'delete', '');
INSERT INTO `permission` VALUES ('3', 'edit', '');
INSERT INTO `permission` VALUES ('4', 'query', '');
INSERT INTO `permission` VALUES ('5', 'hello', null);

-- ----------------------------
-- Table structure for permission_role
-- ----------------------------
DROP TABLE IF EXISTS `permission_role`;
CREATE TABLE `permission_role` (
  `rid` int(11) NOT NULL,
  `pid` int(11) NOT NULL,
  PRIMARY KEY (`pid`,`rid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of permission_role
-- ----------------------------
INSERT INTO `permission_role` VALUES ('1', '1');
INSERT INTO `permission_role` VALUES ('2', '1');
INSERT INTO `permission_role` VALUES ('1', '2');
INSERT INTO `permission_role` VALUES ('1', '3');
INSERT INTO `permission_role` VALUES ('1', '4');
INSERT INTO `permission_role` VALUES ('2', '4');
INSERT INTO `permission_role` VALUES ('2', '5');

-- ----------------------------
-- Table structure for role
-- ----------------------------
DROP TABLE IF EXISTS `role`;
CREATE TABLE `role` (
  `rid` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `rname` varchar(255) NOT NULL,
  PRIMARY KEY (`rid`),
  UNIQUE KEY `rid` (`rid`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of role
-- ----------------------------
INSERT INTO `role` VALUES ('1', 'admin');
INSERT INTO `role` VALUES ('2', 'customer');

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `uid` int(11) NOT NULL,
  `username` varchar(255) NOT NULL,
  `password` varchar(255) NOT NULL,
  UNIQUE KEY `id` (`uid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES ('1', 'admin', '123456');
INSERT INTO `user` VALUES ('2', 'demo', '123456');

-- ----------------------------
-- Table structure for user_role
-- ----------------------------
DROP TABLE IF EXISTS `user_role`;
CREATE TABLE `user_role` (
  `rid` int(11) NOT NULL,
  `uid` int(11) NOT NULL,
  PRIMARY KEY (`uid`,`rid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user_role
-- ----------------------------
INSERT INTO `user_role` VALUES ('1', '1');
INSERT INTO `user_role` VALUES ('2', '2');
