/*
 Navicat Premium Data Transfer

 Source Server         : mysql0815
 Source Server Type    : MySQL
 Source Server Version : 50562
 Source Host           : localhost:3306
 Source Schema         : girls

 Target Server Type    : MySQL
 Target Server Version : 50562
 File Encoding         : 65001

 Date: 06/11/2019 16:26:51
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for admin
-- ----------------------------
DROP TABLE IF EXISTS `admin`;
CREATE TABLE `admin`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `password` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 3 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of admin
-- ----------------------------
INSERT INTO `admin` VALUES (1, 'john', '8888');
INSERT INTO `admin` VALUES (2, 'lyt', '6666');

-- ----------------------------
-- Table structure for beauty
-- ----------------------------
DROP TABLE IF EXISTS `beauty`;
CREATE TABLE `beauty`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `sex` char(1) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT 'Ů',
  `borndate` datetime NULL DEFAULT '1987-01-01 00:00:00',
  `phone` varchar(11) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `photo` blob NULL,
  `boyfriend_id` int(11) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 13 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of beauty
-- ----------------------------
INSERT INTO `beauty` VALUES (1, '柳岩', '女', '1988-02-03 00:00:00', '18209876577', NULL, 8);
INSERT INTO `beauty` VALUES (2, '苍老师', '女', '1987-12-30 00:00:00', '18219876577', NULL, 9);
INSERT INTO `beauty` VALUES (3, 'Angelababy', '女', '1989-02-03 00:00:00', '18209876567', NULL, 3);
INSERT INTO `beauty` VALUES (4, '热巴', '女', '1993-02-03 00:00:00', '18209876579', NULL, 2);
INSERT INTO `beauty` VALUES (5, '周冬雨', '女', '1992-02-03 00:00:00', '18209179577', NULL, 9);
INSERT INTO `beauty` VALUES (6, '周芷若', '女', '1988-02-03 00:00:00', '18209876577', NULL, 1);
INSERT INTO `beauty` VALUES (7, '岳灵珊', '女', '1987-12-30 00:00:00', '18219876577', NULL, 9);
INSERT INTO `beauty` VALUES (8, '小昭', '女', '1989-02-03 00:00:00', '18209876567', NULL, 1);
INSERT INTO `beauty` VALUES (9, '双儿', '女', '1993-02-03 00:00:00', '18209876579', NULL, 9);
INSERT INTO `beauty` VALUES (10, '王语嫣', '女', '1992-02-03 00:00:00', '18209179577', NULL, 4);
INSERT INTO `beauty` VALUES (11, '夏雪', '女', '1993-02-03 00:00:00', '18209876579', NULL, 9);
INSERT INTO `beauty` VALUES (12, '赵敏', '女', '1992-02-03 00:00:00', '18209179577', NULL, 1);

-- ----------------------------
-- Table structure for boys
-- ----------------------------
DROP TABLE IF EXISTS `boys`;
CREATE TABLE `boys`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `boyName` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `userCP` int(11) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 5 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of boys
-- ----------------------------
INSERT INTO `boys` VALUES (1, '张无忌', 100);
INSERT INTO `boys` VALUES (2, '鹿晗', 800);
INSERT INTO `boys` VALUES (3, '黄晓', 50);
INSERT INTO `boys` VALUES (4, '段誉', 300);

SET FOREIGN_KEY_CHECKS = 1;

1
2
3
4
5
6
7
8
9
10
11
12
13
14
15
16
17
18
19
20
21
22
23
24
25
26
27
28
29
30
31
32
33
34
35
36
37
38
39
40
41
42
43
44
45
46
47
48
49
50
51
52
53
54
55
56
57
58
59
60
61
62
63
64
65
66
67
68
69
70
71
72
73
74
75
76
77
78
79
80
81
82
83
84
85
86
87
88
job_grades表文件

USE myemployees;
CREATE TABLE job_grades(
	grade_level VARCHAR(3),
	lowest_sal  INT,
	highest_sal INT
);

INSERT INTO job_grades
VALUES ('A', 1000, 2999);

INSERT INTO job_grades
VALUES ('B', 3000, 5999);

INSERT INTO job_grades
VALUES('C', 6000, 9999);

INSERT INTO job_grades
VALUES('D', 10000, 14999);

INSERT INTO job_grades
VALUES('E', 15000, 24999);

INSERT INTO job_grades
VALUES('F', 25000, 40000);

————————————————
版权声明：本文为CSDN博主「昧着良心说实话」的原创文章，遵循CC 4.0 BY-SA版权协议，转载请附上原文出处链接及本声明。
原文链接：https://blog.csdn.net/GongmissYan/article/details/102937816