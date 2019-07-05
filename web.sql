/*
Navicat MySQL Data Transfer

Source Server         : database
Source Server Version : 50626
Source Host           : localhost:3306
Source Database       : web

Target Server Type    : MYSQL
Target Server Version : 50626
File Encoding         : 65001

Date: 2019-07-05 18:11:02
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for oauth_client_details
-- ----------------------------
DROP TABLE IF EXISTS `oauth_client_details`;
CREATE TABLE `oauth_client_details` (
  `client_id` varchar(128) NOT NULL,
  `resource_ids` varchar(256) DEFAULT NULL,
  `client_secret` varchar(256) DEFAULT NULL,
  `scope` varchar(256) DEFAULT NULL,
  `authorized_grant_types` varchar(256) DEFAULT NULL,
  `web_server_redirect_uri` varchar(256) DEFAULT NULL,
  `authorities` varchar(256) DEFAULT NULL,
  `access_token_validity` int(11) DEFAULT NULL,
  `refresh_token_validity` int(11) DEFAULT NULL,
  `additional_information` varchar(4096) DEFAULT NULL,
  `autoapprove` varchar(256) DEFAULT NULL,
  PRIMARY KEY (`client_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of oauth_client_details
-- ----------------------------
INSERT INTO `oauth_client_details` VALUES ('client', null, '{noop}secret', 'all', 'password,authorization_code,refresh_token,implicit,client_credentials', null, null, null, null, null, null);

-- ----------------------------
-- Table structure for r_user_role
-- ----------------------------
DROP TABLE IF EXISTS `r_user_role`;
CREATE TABLE `r_user_role` (
  `user_id` int(10) DEFAULT NULL COMMENT '用户ID',
  `role_id` int(10) DEFAULT NULL COMMENT '角色ID'
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户-角色关系表';

-- ----------------------------
-- Records of r_user_role
-- ----------------------------
INSERT INTO `r_user_role` VALUES ('4', '2');
INSERT INTO `r_user_role` VALUES ('4', '3');
INSERT INTO `r_user_role` VALUES ('1', '1');
INSERT INTO `r_user_role` VALUES ('2', '2');
INSERT INTO `r_user_role` VALUES ('3', '3');

-- ----------------------------
-- Table structure for sys_class
-- ----------------------------
DROP TABLE IF EXISTS `sys_class`;
CREATE TABLE `sys_class` (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `course_name` varchar(255) NOT NULL,
  `course_id` varchar(255) NOT NULL,
  `teacher_name` varchar(255) NOT NULL,
  `class_week` varchar(255) NOT NULL,
  `first_classtime` varchar(255) NOT NULL,
  `class_num` varchar(255) NOT NULL,
  `credit` varchar(255) NOT NULL,
  `classroom` varchar(255) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=gbk;

-- ----------------------------
-- Records of sys_class
-- ----------------------------
INSERT INTO `sys_class` VALUES ('1', '人工智能', '000001', 'teacher', '周一', '08:20', '3', '3', '东2-101');
INSERT INTO `sys_class` VALUES ('3', '机器学习', '000002', 'teacher', '周二', '14:00', '3', '3', '东3-109');

-- ----------------------------
-- Table structure for sys_menu
-- ----------------------------
DROP TABLE IF EXISTS `sys_menu`;
CREATE TABLE `sys_menu` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `name` varchar(50) DEFAULT NULL COMMENT '菜单名',
  `url` varchar(200) DEFAULT NULL COMMENT 'url',
  `parent_id` int(11) DEFAULT NULL COMMENT '父类id',
  `sort` tinyint(4) DEFAULT NULL COMMENT '排序',
  `remark` text COMMENT '描述',
  `icon` varchar(30) DEFAULT NULL COMMENT '图标',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8 COMMENT='系统菜单';

-- ----------------------------
-- Records of sys_menu
-- ----------------------------
INSERT INTO `sys_menu` VALUES ('1', '系统管理', null, '0', '1', null, 'ios-flower');
INSERT INTO `sys_menu` VALUES ('2', '信息管理', null, '0', '2', null, 'ios-flower-outline');
INSERT INTO `sys_menu` VALUES ('3', '课程管理', null, '0', '3', null, 'ios-rose');
INSERT INTO `sys_menu` VALUES ('4', '用户管理', '/base/user', '1', '1', null, 'ios-person');
INSERT INTO `sys_menu` VALUES ('5', '菜单管理', '/base/menu', '1', '2', null, 'android-list');
INSERT INTO `sys_menu` VALUES ('6', '角色管理', '/base/role', '1', '3', null, 'ios-people');
INSERT INTO `sys_menu` VALUES ('7', '学校管理', '/base/school', '2', '1', null, 'ios-reverse-camera-outline');
INSERT INTO `sys_menu` VALUES ('8', '教师管理', '/base/teacher', '2', '2', null, 'ios-person');
INSERT INTO `sys_menu` VALUES ('9', '学生管理', '/base/student', '2', '3', '', 'ios-person');
INSERT INTO `sys_menu` VALUES ('10', '班课管理', '/base/class', '3', '1', null, 'ios-eye');
INSERT INTO `sys_menu` VALUES ('11', '签到管理', '/base/sign', '3', '2', null, 'ios-eye');

-- ----------------------------
-- Table structure for sys_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_role`;
CREATE TABLE `sys_role` (
  `id` int(10) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `role` varchar(50) NOT NULL COMMENT '角色',
  `name` varchar(50) DEFAULT NULL COMMENT '角色名',
  `modules` text COMMENT '权限',
  `describe` text COMMENT '描述',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8 COMMENT='角色表';

-- ----------------------------
-- Records of sys_role
-- ----------------------------
INSERT INTO `sys_role` VALUES ('1', 'ROLE_ADMIN', '管理员', '4;5;6;7;8;9;10;11;', '管理员');
INSERT INTO `sys_role` VALUES ('2', 'ROLE_TEST1', '教师', '9;10;11;', '教师');
INSERT INTO `sys_role` VALUES ('3', 'ROLE_TEST2', '学生', '10;11', '学生');

-- ----------------------------
-- Table structure for sys_school
-- ----------------------------
DROP TABLE IF EXISTS `sys_school`;
CREATE TABLE `sys_school` (
  `school_id` int(11) NOT NULL AUTO_INCREMENT,
  `school_name` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `am_classtime` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `am_afterclasstime` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `pm_classtime` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `pm_afterclasstime` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  PRIMARY KEY (`school_id`)
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- ----------------------------
-- Records of sys_school
-- ----------------------------
INSERT INTO `sys_school` VALUES ('13', '福州大学', '08:20', '12:00', '14:00', '17:30');
INSERT INTO `sys_school` VALUES ('14', '华北电力大学', '08:00', '12:00', '14:30', '18:30');

-- ----------------------------
-- Table structure for sys_sign
-- ----------------------------
DROP TABLE IF EXISTS `sys_sign`;
CREATE TABLE `sys_sign` (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `name` varchar(50) NOT NULL,
  `student_id` varchar(255) NOT NULL,
  `course_name` varchar(255) NOT NULL,
  `sign_num` varchar(255) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=gbk;

-- ----------------------------
-- Records of sys_sign
-- ----------------------------
INSERT INTO `sys_sign` VALUES ('8', 'student', '180327000', '人工智能', '20');
INSERT INTO `sys_sign` VALUES ('9', 'student', '180327000', '机器学习', '15');

-- ----------------------------
-- Table structure for sys_student
-- ----------------------------
DROP TABLE IF EXISTS `sys_student`;
CREATE TABLE `sys_student` (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `student_name` varchar(255) NOT NULL,
  `student_id` varchar(255) NOT NULL,
  `school_name` varchar(255) NOT NULL,
  `major` varchar(255) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=gbk;

-- ----------------------------
-- Records of sys_student
-- ----------------------------
INSERT INTO `sys_student` VALUES ('3', 'student', '180327000', '福州大学', '计算机技术');

-- ----------------------------
-- Table structure for sys_teacher
-- ----------------------------
DROP TABLE IF EXISTS `sys_teacher`;
CREATE TABLE `sys_teacher` (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `teacher_name` varchar(255) NOT NULL,
  `teacher_id` varchar(255) NOT NULL,
  `school_name` varchar(255) NOT NULL,
  `course_num` varchar(255) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=gbk;

-- ----------------------------
-- Records of sys_teacher
-- ----------------------------
INSERT INTO `sys_teacher` VALUES ('4', 'teacher', '10010', '福州大学', '2');

-- ----------------------------
-- Table structure for sys_user
-- ----------------------------
DROP TABLE IF EXISTS `sys_user`;
CREATE TABLE `sys_user` (
  `id` int(10) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `name` varchar(50) DEFAULT NULL COMMENT '姓名',
  `login_name` varchar(50) NOT NULL COMMENT '登录名',
  `password` varchar(225) DEFAULT NULL COMMENT '密码',
  `email` varchar(225) DEFAULT NULL COMMENT '邮箱',
  PRIMARY KEY (`id`),
  UNIQUE KEY `login_name` (`login_name`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8 COMMENT='用户';

-- ----------------------------
-- Records of sys_user
-- ----------------------------
INSERT INTO `sys_user` VALUES ('1', 'admin', '15900000001', '{bcrypt}$2a$10$1j6HsnXhZeFO7omIkD2xqO4KrDJIPUbRjqg1rH6.KGXBBD40a5iAq', '123456@qq.com');
INSERT INTO `sys_user` VALUES ('2', 'teacher', '15900000002', '{bcrypt}$2a$10$tJt3LHFNJOi6zoXFUtoM3.enENWCPVkcRpThPDaIfL6SPjffyk6fK', '123456@qq.com');
INSERT INTO `sys_user` VALUES ('3', 'student', '15900000003', '{bcrypt}$2a$10$4XqRJrf3CMGlugXdeGrLEOqut2up6nspbuF002b3zGIAZ4jzcsNUG', '123@qq.com');

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '用户ID',
  `email` varchar(255) NOT NULL COMMENT '用户邮箱',
  `password` varchar(255) NOT NULL COMMENT '用户密码',
  `username` varchar(255) NOT NULL COMMENT '用户昵称',
  `role` varchar(255) NOT NULL COMMENT '用户身份',
  `status` int(1) NOT NULL COMMENT '用户状态',
  `regTime` datetime NOT NULL COMMENT '注册时间',
  `regIp` varchar(255) NOT NULL COMMENT '注册IP',
  PRIMARY KEY (`id`),
  UNIQUE KEY `email` (`email`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES ('1', 'xxx', 'xxxxx', 'xxxxx', 'root', '0', '2017-03-28 09:40:31', '127.0.0.1');
