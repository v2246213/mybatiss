/*
Navicat MySQL Data Transfer

Source Server         : wode
Source Server Version : 50637
Source Host           : localhost:3306
Source Database       : mydb

Target Server Type    : MYSQL
Target Server Version : 50637
File Encoding         : 65001

Date: 2018-02-01 23:35:21
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for menu
-- ----------------------------
DROP TABLE IF EXISTS `menu`;
CREATE TABLE `menu` (
  `id` varchar(11) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `parent_id` varchar(255) DEFAULT NULL,
  `url` varchar(255) DEFAULT NULL,
  `icon` varchar(255) DEFAULT NULL,
  `orders` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of menu
-- ----------------------------
INSERT INTO `menu` VALUES ('1', 'java', '0', 'http://www.aliouchen.com', '1', '1');
INSERT INTO `menu` VALUES ('2', '并发编程', '1', 'http://www.aliouchen.com', '1', '1');
INSERT INTO `menu` VALUES ('3', '多线程', '1', 'http://www.aliouchen.com', '1', '2');
INSERT INTO `menu` VALUES ('4', 'Threed', '3', 'http://www.aliouchen.com', '1', '1');
INSERT INTO `menu` VALUES ('5', 'Python', '0', 'http://www.aliouchen.com', '1', '2');
