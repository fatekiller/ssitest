/*
Navicat MySQL Data Transfer

Source Server         : 本地
Source Server Version : 50717
Source Host           : localhost:3306
Source Database       : net_disk

Target Server Type    : MYSQL
Target Server Version : 50717
File Encoding         : 65001

Date: 2017-11-11 21:35:10
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for directory
-- ----------------------------
DROP TABLE IF EXISTS `directory`;
CREATE TABLE `directory` (
  `directory_id` varchar(255) NOT NULL,
  `user_id` varchar(40) NOT NULL,
  `directory_name` varchar(255) NOT NULL,
  `parent_directory_id` varchar(40) DEFAULT NULL,
  `createTime` datetime DEFAULT NULL,
  PRIMARY KEY (`directory_id`),
  KEY `dir_parent` (`parent_directory_id`),
  CONSTRAINT `dir_parent` FOREIGN KEY (`parent_directory_id`) REFERENCES `directory` (`directory_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for file
-- ----------------------------
DROP TABLE IF EXISTS `file`;
CREATE TABLE `file` (
  `file_name` varchar(255) NOT NULL,
  `file_size` bigint(20) NOT NULL,
  `user_id` varchar(50) NOT NULL,
  `upload_time` datetime NOT NULL,
  `file_type` int(1) NOT NULL COMMENT '1,文本 2,多媒体 3,应用程序 4,压缩文件 5,文档 6,其它',
  `file_id` varchar(50) NOT NULL,
  `file_status` int(4) NOT NULL,
  `content_type` varchar(40) NOT NULL,
  `check_code` varchar(40) NOT NULL,
  PRIMARY KEY (`file_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for partitions
-- ----------------------------
DROP TABLE IF EXISTS `partitions`;
CREATE TABLE `partitions` (
  `partition_id` varchar(50) NOT NULL,
  `partition_index` varchar(2) NOT NULL,
  `partition_loc` varchar(255) NOT NULL,
  `file_id` varchar(50) NOT NULL,
  PRIMARY KEY (`partition_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `user_id` varchar(50) NOT NULL,
  `user_name` varchar(255) NOT NULL,
  `user_pass` varchar(255) NOT NULL,
  `user_role` int(1) NOT NULL,
  `user_status` int(1) NOT NULL,
  PRIMARY KEY (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for user_file
-- ----------------------------
DROP TABLE IF EXISTS `user_file`;
CREATE TABLE `user_file` (
  `id` varchar(50) NOT NULL,
  `user_id` varchar(50) NOT NULL,
  `file_id` varchar(50) NOT NULL,
  `upload_time` datetime NOT NULL,
  `dir_id` varchar(255) NOT NULL,
  `status` int(1) NOT NULL DEFAULT '0',
  `file_name` varchar(255) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `file_dir` (`dir_id`),
  CONSTRAINT `file_dir` FOREIGN KEY (`dir_id`) REFERENCES `directory` (`directory_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
