/*
Navicat MySQL Data Transfer

Source Server         : Local
Source Server Version : 50627
Source Host           : 127.0.0.1:3306
Source Database       : sports

Target Server Type    : MYSQL
Target Server Version : 50627
File Encoding         : 65001

Date: 2021-01-20 15:43:06
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for advisory
-- ----------------------------
DROP TABLE IF EXISTS `advisory`;
CREATE TABLE `advisory` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `title` varchar(50) DEFAULT NULL,
  `article` text,
  `author` varchar(20) DEFAULT NULL,
  `nselect` int(11) DEFAULT NULL,
  `wonderful` int(10) unsigned zerofill DEFAULT '0000000000',
  `nimage` varchar(100) DEFAULT NULL,
  `intro` text,
  `gmt_create` timestamp NOT NULL DEFAULT '1970-12-31 16:00:00' COMMENT '创建时间',
  `gmt_modified` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00' ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of advisory
-- ----------------------------
INSERT INTO `advisory` VALUES ('1', 'dasd', '<p>asdasd</p>', 'dasd', '1', '0000000000', null, 'dasd', '2020-12-08 18:24:05', '2021-01-20 10:50:07');
INSERT INTO `advisory` VALUES ('5', 'dsad', '<p>dsad</p>', 'sadsa', '2', '0000000000', null, 'dasdas', '2020-12-08 18:28:29', '2021-01-20 10:40:04');
INSERT INTO `advisory` VALUES ('6', 'dsad', '<p>dsasd</p>', 'sadas', '3', '0000000000', null, 'dasd', '2020-12-08 18:28:35', '2021-01-20 10:40:05');
INSERT INTO `advisory` VALUES ('7', 'dsad', '<p>dasd</p>', 'asdas', '5', '0000000000', null, 'dasd', '2020-12-08 18:28:42', '2021-01-20 10:40:07');
INSERT INTO `advisory` VALUES ('8', 'dsa', '<p><br></p>', 'dsa', '5', '0000000000', null, 'dsad', '2020-12-08 18:31:21', '2021-01-20 10:40:08');
INSERT INTO `advisory` VALUES ('9', null, null, null, null, null, null, null, '2021-01-20 11:56:16', '2021-01-20 11:56:16');
INSERT INTO `advisory` VALUES ('10', null, null, null, null, null, null, null, '2021-01-20 11:56:28', '2021-01-20 11:56:28');
INSERT INTO `advisory` VALUES ('11', null, null, null, null, null, null, null, '2021-01-20 11:57:39', '2021-01-20 11:57:39');

-- ----------------------------
-- Table structure for announcement
-- ----------------------------
DROP TABLE IF EXISTS `announcement`;
CREATE TABLE `announcement` (
  `id` int(20) NOT NULL AUTO_INCREMENT,
  `author` varchar(40) DEFAULT NULL,
  `title` varchar(255) DEFAULT NULL,
  `uid` int(20) DEFAULT NULL,
  `article` text,
  `gmt_create` timestamp NOT NULL DEFAULT '1970-12-31 16:00:00' COMMENT '创建时间',
  `gmt_modified` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00' ON UPDATE CURRENT_TIMESTAMP,
  `image` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

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
  `comment` varchar(255) DEFAULT NULL,
  `advisoryid` int(20) DEFAULT NULL,
  `cuid` int(20) DEFAULT NULL,
  `apply` varchar(255) DEFAULT NULL,
  `flag` int(11) DEFAULT NULL,
  `aboy` varchar(255) DEFAULT NULL,
  `cid` int(11) DEFAULT NULL,
  `gmt_create` timestamp NOT NULL DEFAULT '1970-12-31 16:00:00' COMMENT '创建时间',
  `gmt_modified` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00' ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of comment
-- ----------------------------

-- ----------------------------
-- Table structure for court
-- ----------------------------
DROP TABLE IF EXISTS `court`;
CREATE TABLE `court` (
  `id` int(20) NOT NULL AUTO_INCREMENT,
  `address` varchar(30) DEFAULT NULL,
  `costtime` int(11) DEFAULT NULL COMMENT '租用时间',
  `cost` int(20) DEFAULT NULL COMMENT '订场金额',
  `type` varchar(255) DEFAULT NULL COMMENT '场地类型',
  `tid` int(10) DEFAULT NULL COMMENT '订场球队',
  `teamname` varchar(100) DEFAULT NULL,
  `gmt_create` timestamp NOT NULL DEFAULT '1970-12-31 16:00:00' COMMENT '创建时间',
  `gmt_modified` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00' ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=67 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of court
-- ----------------------------
INSERT INTO `court` VALUES ('3', '篮球4', '2', '50', '篮球', null, null, '2020-12-29 17:38:22', '2020-12-28 11:38:22');
INSERT INTO `court` VALUES ('4', '篮球4', '2', '50', '篮球', null, null, '2020-12-29 19:38:22', '2020-12-28 11:38:22');
INSERT INTO `court` VALUES ('5', '篮球4', '2', '50', '篮球', null, null, '2020-12-29 21:38:22', '2020-12-28 11:38:22');
INSERT INTO `court` VALUES ('6', '篮球4', '2', '50', '篮球', null, null, '2020-12-29 23:38:22', '2020-12-28 11:38:22');
INSERT INTO `court` VALUES ('7', '篮球3', '2', '50', '篮球', null, null, '2020-12-29 17:38:22', '2020-12-28 11:38:22');
INSERT INTO `court` VALUES ('8', '篮球3', '2', '50', '篮球', null, null, '2020-12-29 19:38:22', '2020-12-28 11:38:22');
INSERT INTO `court` VALUES ('9', '篮球3', '2', '50', '篮球', null, null, '2020-12-29 21:38:22', '2020-12-28 11:38:22');
INSERT INTO `court` VALUES ('10', '篮球3', '2', '50', '篮球', null, null, '2020-12-29 23:38:22', '2020-12-28 11:38:22');
INSERT INTO `court` VALUES ('11', '篮球2', '2', '50', '篮球', null, null, '2020-12-29 17:38:22', '2020-12-28 11:38:22');
INSERT INTO `court` VALUES ('12', '篮球2', '2', '50', '篮球', null, null, '2020-12-29 19:38:22', '2020-12-28 11:38:22');
INSERT INTO `court` VALUES ('13', '篮球2', '2', '50', '篮球', null, null, '2020-12-29 21:38:22', '2020-12-28 11:38:22');
INSERT INTO `court` VALUES ('14', '篮球2', '2', '50', '篮球', null, null, '2020-12-29 23:38:22', '2020-12-28 11:38:22');
INSERT INTO `court` VALUES ('15', '篮球1', '2', '50', '篮球', null, null, '2020-12-29 17:38:22', '2020-12-28 11:38:22');
INSERT INTO `court` VALUES ('16', '篮球1', '2', '50', '篮球', null, null, '2020-12-29 19:38:22', '2020-12-28 11:38:22');
INSERT INTO `court` VALUES ('17', '篮球1', '2', '50', '篮球', null, null, '2020-12-29 21:38:22', '2020-12-28 11:38:22');
INSERT INTO `court` VALUES ('18', '篮球1', '2', '50', '篮球', null, null, '2020-12-29 23:38:22', '2020-12-28 11:38:22');
INSERT INTO `court` VALUES ('19', '足球4', '2', '50', '足球', null, null, '2020-12-29 17:38:22', '2020-12-28 11:38:22');
INSERT INTO `court` VALUES ('20', '足球4', '2', '50', '足球', null, null, '2020-12-29 19:38:22', '2020-12-28 11:38:22');
INSERT INTO `court` VALUES ('21', '足球4', '2', '50', '足球', null, null, '2020-12-29 21:38:22', '2020-12-28 11:38:22');
INSERT INTO `court` VALUES ('22', '足球4', '2', '50', '足球', null, null, '2020-12-29 23:38:22', '2020-12-28 11:38:22');
INSERT INTO `court` VALUES ('23', '足球3', '2', '50', '足球', null, null, '2020-12-29 17:38:22', '2020-12-28 11:38:22');
INSERT INTO `court` VALUES ('24', '足球3', '2', '50', '足球', null, null, '2020-12-29 19:38:22', '2020-12-28 11:38:22');
INSERT INTO `court` VALUES ('25', '足球3', '2', '50', '足球', null, null, '2020-12-29 21:38:22', '2020-12-28 11:38:22');
INSERT INTO `court` VALUES ('26', '足球3', '2', '50', '足球', null, null, '2020-12-29 23:38:22', '2020-12-28 11:38:22');
INSERT INTO `court` VALUES ('27', '足球2', '2', '50', '足球', null, null, '2020-12-29 17:38:22', '2020-12-28 11:38:22');
INSERT INTO `court` VALUES ('28', '足球2', '2', '50', '足球', null, null, '2020-12-29 19:38:22', '2020-12-28 11:38:22');
INSERT INTO `court` VALUES ('29', '足球2', '2', '50', '足球', null, null, '2020-12-29 21:38:22', '2020-12-28 11:38:22');
INSERT INTO `court` VALUES ('30', '足球2', '2', '50', '足球', null, null, '2020-12-29 23:38:22', '2020-12-28 11:38:22');
INSERT INTO `court` VALUES ('31', '足球1', '2', '50', '足球', null, null, '2020-12-29 17:38:22', '2020-12-28 11:38:22');
INSERT INTO `court` VALUES ('32', '足球1', '2', '50', '足球', null, null, '2020-12-29 19:38:22', '2020-12-28 11:38:22');
INSERT INTO `court` VALUES ('33', '足球1', '2', '50', '足球', null, null, '2020-12-29 21:38:22', '2020-12-28 11:38:22');
INSERT INTO `court` VALUES ('34', '足球1', '2', '50', '足球', null, null, '2020-12-29 23:38:22', '2020-12-28 11:38:22');
INSERT INTO `court` VALUES ('35', '网球4', '2', '50', '网球', null, null, '2020-12-29 17:38:22', '2020-12-28 11:38:22');
INSERT INTO `court` VALUES ('36', '网球4', '2', '50', '网球', null, null, '2020-12-29 19:38:22', '2020-12-28 11:38:22');
INSERT INTO `court` VALUES ('37', '网球4', '2', '50', '网球', null, null, '2020-12-29 21:38:22', '2020-12-28 11:38:22');
INSERT INTO `court` VALUES ('38', '网球4', '2', '50', '网球', null, null, '2020-12-29 23:38:22', '2020-12-28 11:38:22');
INSERT INTO `court` VALUES ('39', '网球3', '2', '50', '网球', null, null, '2020-12-29 17:38:22', '2020-12-28 11:38:22');
INSERT INTO `court` VALUES ('40', '网球3', '2', '50', '网球', null, null, '2020-12-29 19:38:22', '2020-12-28 11:38:22');
INSERT INTO `court` VALUES ('41', '网球3', '2', '50', '网球', null, null, '2020-12-29 21:38:22', '2020-12-28 11:38:22');
INSERT INTO `court` VALUES ('42', '网球3', '2', '50', '网球', null, null, '2020-12-29 23:38:22', '2020-12-28 11:38:22');
INSERT INTO `court` VALUES ('43', '网球2', '2', '50', '网球', null, null, '2020-12-29 17:38:22', '2020-12-28 11:38:22');
INSERT INTO `court` VALUES ('44', '网球2', '2', '50', '网球', null, null, '2020-12-29 19:38:22', '2020-12-28 11:38:22');
INSERT INTO `court` VALUES ('45', '网球2', '2', '50', '网球', null, null, '2020-12-29 21:38:22', '2020-12-28 11:38:22');
INSERT INTO `court` VALUES ('46', '网球2', '2', '50', '网球', null, null, '2020-12-29 23:38:22', '2020-12-28 11:38:22');
INSERT INTO `court` VALUES ('47', '网球1', '2', '50', '网球', null, null, '2020-12-29 17:38:22', '2020-12-28 11:38:22');
INSERT INTO `court` VALUES ('48', '网球1', '2', '50', '网球', null, null, '2020-12-29 19:38:22', '2020-12-28 11:38:22');
INSERT INTO `court` VALUES ('49', '网球1', '2', '50', '网球', null, null, '2020-12-29 21:38:22', '2020-12-28 11:38:22');
INSERT INTO `court` VALUES ('50', '网球1', '2', '50', '网球', null, null, '2020-12-29 23:38:22', '2020-12-28 11:38:22');
INSERT INTO `court` VALUES ('51', '排球4', '2', '50', '排球', null, null, '2020-12-29 17:38:22', '2020-12-28 11:38:22');
INSERT INTO `court` VALUES ('52', '排球4', '2', '50', '排球', null, null, '2020-12-29 19:38:22', '2020-12-28 11:38:22');
INSERT INTO `court` VALUES ('53', '排球4', '2', '50', '排球', null, null, '2020-12-29 21:38:22', '2020-12-28 11:38:22');
INSERT INTO `court` VALUES ('54', '排球4', '2', '50', '排球', null, null, '2020-12-29 23:38:22', '2020-12-28 11:38:22');
INSERT INTO `court` VALUES ('55', '排球3', '2', '50', '排球', null, null, '2020-12-29 17:38:22', '2020-12-28 11:38:22');
INSERT INTO `court` VALUES ('56', '排球3', '2', '50', '排球', null, null, '2020-12-29 19:38:22', '2020-12-28 11:38:22');
INSERT INTO `court` VALUES ('57', '排球3', '2', '50', '排球', null, null, '2020-12-29 21:38:22', '2020-12-28 11:38:22');
INSERT INTO `court` VALUES ('58', '排球3', '2', '50', '排球', null, null, '2020-12-29 23:38:22', '2020-12-28 11:38:22');
INSERT INTO `court` VALUES ('59', '排球2', '2', '50', '排球', null, null, '2020-12-29 17:38:22', '2020-12-28 11:38:22');
INSERT INTO `court` VALUES ('60', '排球2', '2', '50', '排球', null, null, '2020-12-29 19:38:22', '2020-12-28 11:38:22');
INSERT INTO `court` VALUES ('61', '排球2', '2', '50', '排球', null, null, '2020-12-29 21:38:22', '2020-12-28 11:38:22');
INSERT INTO `court` VALUES ('62', '排球2', '2', '50', '排球', null, null, '2020-12-29 23:38:22', '2020-12-28 11:38:22');
INSERT INTO `court` VALUES ('63', '排球1', '2', '50', '排球', null, null, '2020-12-29 17:38:22', '2020-12-28 11:38:22');
INSERT INTO `court` VALUES ('64', '排球1', '2', '50', '排球', null, null, '2020-12-29 19:38:22', '2020-12-28 11:38:22');
INSERT INTO `court` VALUES ('65', '排球1', '2', '50', '排球', null, null, '2020-12-29 21:38:22', '2020-12-28 11:38:22');
INSERT INTO `court` VALUES ('66', '排球1', '2', '50', '排球', null, null, '2020-12-29 23:38:22', '2020-12-28 11:38:22');

-- ----------------------------
-- Table structure for game
-- ----------------------------
DROP TABLE IF EXISTS `game`;
CREATE TABLE `game` (
  `id` int(20) NOT NULL AUTO_INCREMENT,
  `type` int(20) DEFAULT NULL,
  `challenger` varchar(30) DEFAULT NULL COMMENT '挑战者名字',
  `challengerid` int(20) DEFAULT NULL COMMENT '挑战方ID',
  `enemy` varchar(30) DEFAULT NULL COMMENT '应战方名字',
  `enemyid` int(20) DEFAULT NULL COMMENT '应战方ID',
  `result` text COMMENT '结果',
  `statement` text,
  `flag` tinyint(4) DEFAULT NULL COMMENT '是否应战',
  `gmt_create` timestamp NOT NULL DEFAULT '1970-12-31 16:00:00' COMMENT '创建时间',
  `gmt_modified` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00' ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of game
-- ----------------------------
INSERT INTO `game` VALUES ('1', null, 'dsadasdasdasdsad', '3', 'dsadasdasdasdsad', '3', null, '<p>sdas</p>', '0', '2021-01-20 13:50:03', '2021-01-20 13:50:03');
INSERT INTO `game` VALUES ('2', null, 'dsadasdasdasdsad', '3', 'dsadasdasdasdsad', '3', null, '<p>sdas</p>/n---------------------------------------------------------------------/n队伍名称：dsadasdasdasdsad   队长名字：cmh/n----------------------------------------------------------------------------/n比赛时间:null', '0', '2021-01-20 13:56:39', '2021-01-20 13:56:39');
INSERT INTO `game` VALUES ('3', null, 'dsadasdasdasdsad', '3', 'dsadasdasdasdsad', '3', null, '<p>sdas</p>/n---------------------------------------------------------------------/n队伍名称：dsadasdasdasdsad   队长名字：cmh/n----------------------------------------------------------------------------/n比赛时间:null', '0', '2021-01-20 13:57:46', '2021-01-20 13:57:46');
INSERT INTO `game` VALUES ('4', null, 'dsadasdasdasdsad', '3', 'dsadasdasdasdsad', '3', 'dsadasd', '<h1>准时赴约</h1>/n---------------------------------------------------------------------/n队伍名称：dsadasdasdasdsad   队长名字：cmh/n----------------------------------------------------------------------------/n比赛时间:null', '0', '2021-01-20 14:02:37', '2021-01-20 15:13:55');

-- ----------------------------
-- Table structure for message
-- ----------------------------
DROP TABLE IF EXISTS `message`;
CREATE TABLE `message` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(20) DEFAULT NULL,
  `mess` text,
  `apply` text,
  `flag` int(11) DEFAULT NULL,
  `title` varchar(20) DEFAULT NULL,
  `checkboy` varchar(20) DEFAULT NULL,
  `uid` int(11) DEFAULT NULL,
  `gmt_create` timestamp NOT NULL DEFAULT '1970-12-31 16:00:00' COMMENT '创建时间',
  `gmt_modified` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00' ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of message
-- ----------------------------

-- ----------------------------
-- Table structure for sysuser
-- ----------------------------
DROP TABLE IF EXISTS `sysuser`;
CREATE TABLE `sysuser` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(30) NOT NULL,
  `password` varchar(255) NOT NULL,
  `salt` varchar(255) NOT NULL,
  `usertype` tinyint(4) NOT NULL,
  `phone` varchar(20) DEFAULT NULL,
  `status` tinyint(4) NOT NULL,
  `image` varchar(255) DEFAULT NULL,
  `sex` varchar(20) DEFAULT NULL,
  `email` varchar(100) DEFAULT NULL,
  `gmt_create` timestamp NOT NULL DEFAULT '1970-12-31 16:00:00' COMMENT '创建时间',
  `gmt_modified` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00' ON UPDATE CURRENT_TIMESTAMP,
  `studentid` varchar(100) DEFAULT NULL,
  `truename` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sysuser
-- ----------------------------
INSERT INTO `sysuser` VALUES ('2', 'cmh', '4c41c8ee9e59e72c0463f87ffb7d356f', 'mj0x89ImC/rRoC4yK7bZGg==', '1', '13433623007', '1', '/images/noimage.jpg', '男', '1063565468@qq.com', '2020-12-04 11:45:34', '2020-12-30 11:37:58', '201653684', '陈明浩');
INSERT INTO `sysuser` VALUES ('3', 'rzh', '6373b90b4be5aa7ddcb0843265bc57a9', 'RwgAOcVSZF/TiZifb5UJgw==', '1', '15236984723', '1', '/images/noimage.jpg', '男', '1536982@qq.com', '2020-12-04 18:07:00', '2021-01-20 14:44:21', '201758964', '容志恒');

-- ----------------------------
-- Table structure for team
-- ----------------------------
DROP TABLE IF EXISTS `team`;
CREATE TABLE `team` (
  `id` int(20) NOT NULL AUTO_INCREMENT,
  `teamname` varchar(30) DEFAULT NULL,
  `teamtype` int(20) DEFAULT NULL,
  `teamleader` varchar(20) DEFAULT NULL,
  `teamleaderid` int(20) DEFAULT NULL,
  `reason` text,
  `flag` tinyint(4) DEFAULT NULL COMMENT '申请建立球队',
  `cause` text,
  `intro` text,
  `teamemail` varchar(100) DEFAULT NULL,
  `gmt_create` timestamp NOT NULL DEFAULT '1970-12-31 16:00:00' COMMENT '创建时间',
  `gmt_modified` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00' ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of team
-- ----------------------------
INSERT INTO `team` VALUES ('3', 'dsadasdasdasdsad', '1', 'cmh', '2', null, '1', '<p>dasdasdasda</p>', null, '106358@qq.com', '2020-12-24 18:39:45', '2021-01-15 11:49:43');

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
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user_team_ref
-- ----------------------------
INSERT INTO `user_team_ref` VALUES ('1', '2', '3', '2021-01-15 11:49:43', '2021-01-15 11:49:43');
