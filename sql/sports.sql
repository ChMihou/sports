/*
Navicat MySQL Data Transfer

Source Server         : Local
Source Server Version : 50627
Source Host           : 127.0.0.1:3306
Source Database       : sports

Target Server Type    : MYSQL
Target Server Version : 50627
File Encoding         : 65001

Date: 2020-12-15 16:06:12
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for advisory
-- ----------------------------
DROP TABLE IF EXISTS `advisory`;
CREATE TABLE `advisory` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `title` varchar(50) CHARACTER SET utf8 DEFAULT NULL,
  `article` text CHARACTER SET utf8,
  `author` varchar(20) CHARACTER SET utf8 DEFAULT NULL,
  `nselect` int(11) DEFAULT NULL,
  `nimage` varchar(100) CHARACTER SET utf8 DEFAULT NULL,
  `intro` text CHARACTER SET utf8,
  `gmt_create` timestamp NOT NULL DEFAULT '1970-12-31 16:00:00' COMMENT '创建时间',
  `gmt_modified` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00' ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=latin1;

-- ----------------------------
-- Records of advisory
-- ----------------------------
INSERT INTO `advisory` VALUES ('1', 'dasd', '<p>asdasd</p>', 'dasd', '1', null, 'dasd', '2020-12-08 18:24:05', '2020-12-08 18:24:05');
INSERT INTO `advisory` VALUES ('5', 'dsad', '<p>dsad</p>', 'sadsa', '2', null, 'dasdas', '2020-12-08 18:28:29', '2020-12-08 18:28:29');
INSERT INTO `advisory` VALUES ('6', 'dsad', '<p>dsasd</p>', 'sadas', '3', null, 'dasd', '2020-12-08 18:28:35', '2020-12-08 18:28:35');
INSERT INTO `advisory` VALUES ('7', 'dsad', '<p>dasd</p>', 'asdas', '5', null, 'dasd', '2020-12-08 18:28:42', '2020-12-08 18:28:42');
INSERT INTO `advisory` VALUES ('8', 'dsa', '<p><br></p>', 'dsa', '5', null, 'dsad', '2020-12-08 18:31:21', '2020-12-08 18:31:21');

-- ----------------------------
-- Table structure for announcement
-- ----------------------------
DROP TABLE IF EXISTS `announcement`;
CREATE TABLE `announcement` (
  `id` int(20) NOT NULL AUTO_INCREMENT,
  `author` varchar(40) CHARACTER SET utf8 DEFAULT NULL,
  `title` varchar(255) CHARACTER SET utf8 DEFAULT NULL,
  `uid` int(20) DEFAULT NULL,
  `article` text CHARACTER SET utf8,
  `gmt_create` timestamp NOT NULL DEFAULT '1970-12-31 16:00:00' COMMENT '创建时间',
  `gmt_modified` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00' ON UPDATE CURRENT_TIMESTAMP,
  `image` varchar(255) CHARACTER SET utf8 DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=latin1;

-- ----------------------------
-- Records of announcement
-- ----------------------------
INSERT INTO `announcement` VALUES ('2', 'dasd', 'dsa', null, '<p>dsad</p>', '2020-12-10 15:50:38', '2020-12-10 15:50:38', null);

-- ----------------------------
-- Table structure for comment
-- ----------------------------
DROP TABLE IF EXISTS `comment`;
CREATE TABLE `comment` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `comment` varchar(255) CHARACTER SET utf8 DEFAULT NULL,
  `advisoryid` int(20) DEFAULT NULL,
  `uid` int(20) DEFAULT NULL,
  `apply` varchar(255) CHARACTER SET utf8 DEFAULT NULL,
  `flag` int(11) DEFAULT NULL,
  `aboy` varchar(255) CHARACTER SET utf8 DEFAULT NULL,
  `cid` int(11) DEFAULT NULL,
  `gmt_create` timestamp NOT NULL DEFAULT '1970-12-31 16:00:00' COMMENT '创建时间',
  `gmt_modified` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00' ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- ----------------------------
-- Records of comment
-- ----------------------------

-- ----------------------------
-- Table structure for court
-- ----------------------------
DROP TABLE IF EXISTS `court`;
CREATE TABLE `court` (
  `id` int(20) NOT NULL AUTO_INCREMENT,
  `address` varchar(30) CHARACTER SET utf8 DEFAULT NULL,
  `costtime` int(11) DEFAULT NULL COMMENT '租用时间',
  `cost` int(20) DEFAULT NULL COMMENT '订场金额',
  `type` varchar(255) CHARACTER SET utf8 DEFAULT NULL COMMENT '场地类型',
  `gmt_create` timestamp NOT NULL DEFAULT '1970-12-31 16:00:00' COMMENT '创建时间',
  `gmt_modified` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00' ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- ----------------------------
-- Records of court
-- ----------------------------

-- ----------------------------
-- Table structure for game
-- ----------------------------
DROP TABLE IF EXISTS `game`;
CREATE TABLE `game` (
  `id` int(20) NOT NULL AUTO_INCREMENT,
  `type` int(20) DEFAULT NULL,
  `challenger` varchar(30) CHARACTER SET utf8 DEFAULT NULL COMMENT '挑战者名字',
  `challengerid` int(20) DEFAULT NULL COMMENT '挑战方ID',
  `enemy` varchar(30) CHARACTER SET utf8 DEFAULT NULL COMMENT '应战方名字',
  `enemyid` int(20) DEFAULT NULL COMMENT '应战方ID',
  `result` text CHARACTER SET utf8 COMMENT '结果',
  `statement` text CHARACTER SET utf8,
  `flag` tinyint(4) DEFAULT NULL COMMENT '是否应战',
  `gmt_create` timestamp NOT NULL DEFAULT '1970-12-31 16:00:00' COMMENT '创建时间',
  `gmt_modified` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00' ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- ----------------------------
-- Records of game
-- ----------------------------

-- ----------------------------
-- Table structure for message
-- ----------------------------
DROP TABLE IF EXISTS `message`;
CREATE TABLE `message` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(20) CHARACTER SET utf8 DEFAULT NULL,
  `mess` text CHARACTER SET utf8,
  `apply` text CHARACTER SET utf8,
  `flag` int(11) DEFAULT NULL,
  `title` varchar(20) CHARACTER SET utf8 DEFAULT NULL,
  `checkboy` varchar(20) CHARACTER SET utf8 DEFAULT NULL,
  `uid` int(11) DEFAULT NULL,
  `gmt_create` timestamp NOT NULL DEFAULT '1970-12-31 16:00:00' COMMENT '创建时间',
  `gmt_modified` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00' ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- ----------------------------
-- Records of message
-- ----------------------------

-- ----------------------------
-- Table structure for sysuser
-- ----------------------------
DROP TABLE IF EXISTS `sysuser`;
CREATE TABLE `sysuser` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(30) CHARACTER SET utf8 NOT NULL,
  `password` varchar(255) CHARACTER SET utf8 NOT NULL,
  `salt` varchar(255) CHARACTER SET utf8 NOT NULL,
  `usertype` tinyint(4) NOT NULL,
  `phone` varchar(20) CHARACTER SET utf8 DEFAULT NULL,
  `status` tinyint(4) NOT NULL,
  `image` varchar(255) CHARACTER SET utf8 DEFAULT NULL,
  `sex` varchar(20) CHARACTER SET utf8 DEFAULT NULL,
  `email` varchar(100) CHARACTER SET utf8 DEFAULT NULL,
  `gmt_create` timestamp NOT NULL DEFAULT '1970-12-31 16:00:00' COMMENT '创建时间',
  `gmt_modified` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00' ON UPDATE CURRENT_TIMESTAMP,
  `studentid` varchar(100) CHARACTER SET utf8 DEFAULT NULL,
  `truename` varchar(100) CHARACTER SET utf8 DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=latin1;

-- ----------------------------
-- Records of sysuser
-- ----------------------------
INSERT INTO `sysuser` VALUES ('2', 'cmh', '4c41c8ee9e59e72c0463f87ffb7d356f', 'mj0x89ImC/rRoC4yK7bZGg==', '1', '15362478021', '1', '/images/noimage.jpg', '男', '106358@qq.com', '2020-12-04 11:45:34', '2020-12-04 17:55:12', '201653684', '陈明浩');
INSERT INTO `sysuser` VALUES ('3', 'rzh', '6373b90b4be5aa7ddcb0843265bc57a9', 'RwgAOcVSZF/TiZifb5UJgw==', '0', '15236984723', '1', '/images/noimage.jpg', '男', '1536982@qq.com', '2020-12-04 18:07:00', '2020-12-04 18:07:00', '201758964', '容志恒');

-- ----------------------------
-- Table structure for team
-- ----------------------------
DROP TABLE IF EXISTS `team`;
CREATE TABLE `team` (
  `id` int(20) NOT NULL AUTO_INCREMENT,
  `teamname` varchar(30) CHARACTER SET utf8 DEFAULT NULL,
  `teamtype` int(20) DEFAULT NULL,
  `teamleader` varchar(20) CHARACTER SET utf8 DEFAULT NULL,
  `teamleaderid` int(20) DEFAULT NULL,
  `reason` text CHARACTER SET utf8,
  `flag` tinyint(4) DEFAULT NULL COMMENT '申请建立球队',
  `cause` text CHARACTER SET utf8,
  `intro` text CHARACTER SET utf8,
  `teamemail` varchar(100) CHARACTER SET utf8 DEFAULT NULL,
  `gmt_create` timestamp NOT NULL DEFAULT '1970-12-31 16:00:00' COMMENT '创建时间',
  `gmt_modified` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00' ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- ----------------------------
-- Records of team
-- ----------------------------

-- ----------------------------
-- Table structure for user_team_ref
-- ----------------------------
DROP TABLE IF EXISTS `user_team_ref`;
CREATE TABLE `user_team_ref` (
  `id` int(20) NOT NULL AUTO_INCREMENT,
  `userid` int(20) NOT NULL,
  `teamid` int(20) NOT NULL,
  `gmt_create` timestamp NOT NULL DEFAULT '1970-12-31 16:00:00' COMMENT '创建时间',
  `gmt_modified` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00' ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- ----------------------------
-- Records of user_team_ref
-- ----------------------------
