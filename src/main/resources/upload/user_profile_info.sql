/*
Navicat MySQL Data Transfer

Source Server         : 内网10.100.38.155
Source Server Version : 50726
Source Host           : 10.100.38.155:3306
Source Database       : test

Target Server Type    : MYSQL
Target Server Version : 50726
File Encoding         : 65001

Date: 2019-05-16 13:42:34
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for user_profile_info
-- ----------------------------
DROP TABLE IF EXISTS `user_profile_info`;
CREATE TABLE `user_profile_info` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  `age` int(11) DEFAULT NULL,
  `create_at` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of user_profile_info
-- ----------------------------
INSERT INTO `user_profile_info` VALUES ('1', '张三', '35', '2019-05-16 13:40:14');
INSERT INTO `user_profile_info` VALUES ('2', '李四', '35', '2019-05-16 13:40:38');
INSERT INTO `user_profile_info` VALUES ('3', '李明', '22', '2019-05-16 13:41:48');
INSERT INTO `user_profile_info` VALUES ('4', '啊飞', '35', '2019-05-16 13:41:51');
INSERT INTO `user_profile_info` VALUES ('5', '混混', '18', '2019-05-16 13:42:13');
