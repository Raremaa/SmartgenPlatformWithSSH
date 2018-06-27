/*
 Navicat Premium Data Transfer

 Source Server         : localhost
 Source Server Type    : MySQL
 Source Server Version : 50722
 Source Host           : localhost:3306
 Source Schema         : smartgenplatform

 Target Server Type    : MySQL
 Target Server Version : 50722
 File Encoding         : 65001

 Date: 27/06/2018 11:51:08
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for browsinghistory
-- ----------------------------
DROP TABLE IF EXISTS `browsinghistory`;
CREATE TABLE `browsinghistory`  (
  `Id` int(11) NOT NULL AUTO_INCREMENT COMMENT '浏览历史表主键',
  `Product_id` int(11) NULL DEFAULT NULL COMMENT '产品表主键Id',
  `User_id` int(11) NULL DEFAULT NULL COMMENT '用户表主键',
  PRIMARY KEY (`Id`) USING BTREE,
  INDEX `FKtdt52r98v0ipkh0s0xs9iy5hc`(`Product_id`) USING BTREE,
  INDEX `FKmgk1kcjw0g8pouff8br68uqnb`(`User_id`) USING BTREE,
  CONSTRAINT `FKmgk1kcjw0g8pouff8br68uqnb` FOREIGN KEY (`User_id`) REFERENCES `user` (`User_id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `FKtdt52r98v0ipkh0s0xs9iy5hc` FOREIGN KEY (`Product_id`) REFERENCES `product` (`Id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `browsinghistory_ibfk_1` FOREIGN KEY (`Product_id`) REFERENCES `product` (`Id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `browsinghistory_ibfk_2` FOREIGN KEY (`User_id`) REFERENCES `user` (`User_id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 3 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '浏览历史表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of browsinghistory
-- ----------------------------
INSERT INTO `browsinghistory` VALUES (1, 1, 1);
INSERT INTO `browsinghistory` VALUES (2, 2, 1);

-- ----------------------------
-- Table structure for collect
-- ----------------------------
DROP TABLE IF EXISTS `collect`;
CREATE TABLE `collect`  (
  `Id` int(11) NOT NULL AUTO_INCREMENT,
  `User_id` int(11) NULL DEFAULT NULL,
  `Creproiect_id` int(11) NULL DEFAULT NULL,
  PRIMARY KEY (`Id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '项目收藏表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for collectioninfo
-- ----------------------------
DROP TABLE IF EXISTS `collectioninfo`;
CREATE TABLE `collectioninfo`  (
  `Id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `User_id` int(11) NULL DEFAULT NULL COMMENT '用户表主键',
  `Product_id` int(11) NULL DEFAULT NULL COMMENT '商品表主键',
  PRIMARY KEY (`Id`) USING BTREE,
  INDEX `FK1shxnm1717mhcws1b8e0y4uab`(`Product_id`) USING BTREE,
  INDEX `FKphd94kx6x6jn2kuygkxcfpkyh`(`User_id`) USING BTREE,
  CONSTRAINT `FK1shxnm1717mhcws1b8e0y4uab` FOREIGN KEY (`Product_id`) REFERENCES `product` (`Id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `FKphd94kx6x6jn2kuygkxcfpkyh` FOREIGN KEY (`User_id`) REFERENCES `user` (`User_id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `collectioninfo_ibfk_1` FOREIGN KEY (`User_id`) REFERENCES `user` (`User_id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `collectioninfo_ibfk_2` FOREIGN KEY (`Product_id`) REFERENCES `product` (`Id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 14 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '产品收藏表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of collectioninfo
-- ----------------------------
INSERT INTO `collectioninfo` VALUES (1, 2, 3);
INSERT INTO `collectioninfo` VALUES (2, 14, 4);
INSERT INTO `collectioninfo` VALUES (3, 14, 5);
INSERT INTO `collectioninfo` VALUES (4, 14, 9);
INSERT INTO `collectioninfo` VALUES (5, 6, 6);
INSERT INTO `collectioninfo` VALUES (6, 14, 11);
INSERT INTO `collectioninfo` VALUES (7, 14, 1);
INSERT INTO `collectioninfo` VALUES (8, 14, 2);
INSERT INTO `collectioninfo` VALUES (9, 14, 10);

-- ----------------------------
-- Table structure for company
-- ----------------------------
DROP TABLE IF EXISTS `company`;
CREATE TABLE `company`  (
  `Company_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `Company_name` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `Company_userName` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `Company_phone` varchar(11) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `Company_charterNumber` varchar(11) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `Company_password` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '长度为6--20位',
  `Company_logo` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '图片路径',
  `Company_location` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `Company_identity` int(1) NOT NULL COMMENT '0为未认证 1为认证 初始值为0',
  PRIMARY KEY (`Company_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 3 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of company
-- ----------------------------
INSERT INTO `company` VALUES (1, '九江天浔信息技术有限公司', '天浔', '14662565895', '56465683565', '4561123qq', '黄金时代和精神', '江西省-九江', 1);
INSERT INTO `company` VALUES (2, '皇家科技', '庐山', '15265412358', '1566165623', '1236547w', '科技', '江西省-宜春', 1);

-- ----------------------------
-- Table structure for creativeproject
-- ----------------------------
DROP TABLE IF EXISTS `creativeproject`;
CREATE TABLE `creativeproject`  (
  `Creproject_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `Expert_jobNumber` int(11) NULL DEFAULT NULL COMMENT '外键，初始为0表示无',
  `User_id` int(11) NOT NULL COMMENT '外键',
  `Company_id` int(11) NULL DEFAULT NULL COMMENT '外键，初始为0表示无',
  `Creproject_title` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `Creproject_content` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '文件路径',
  `Creproject_label` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `Creproject_picture` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '图片路径',
  `Creproject_video` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '视频路径',
  `Creproject_plan` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '文件路径',
  `Creproject_classify` int(1) NOT NULL COMMENT '0为生活手工 1为家具家居 2为科技数码 3为艺术娱乐 4为医疗健康 5为户外运动 6为其他',
  `Creproject_state` int(1) NOT NULL COMMENT '0为未孵化 1为孵化中 2为已孵化',
  `Creproject_praise` int(11) NULL DEFAULT NULL,
  `Creproject_modifyTime` datetime(0) NULL DEFAULT NULL,
  `Creproject_releaseTime` datetime(0) NOT NULL,
  `Creproject_evaluateTime` datetime(0) NULL DEFAULT NULL,
  `Creproject_evaluateResult` int(1) NULL DEFAULT NULL COMMENT '0为未通过 1为通过',
  `Creproject_evaluateOpinion` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`Creproject_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 23 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of creativeproject
-- ----------------------------
INSERT INTO `creativeproject` VALUES (1, 1, 2, 0, '多功能婴儿车', '适合有孩子的家庭。这次设计的目的主要是解决婴儿车在使用过程中存\r\n				在的一些问题。例如，使用周期短，占地空间大等。在功能上使其多样化，能够有一\r\n				个较长的使用周期陪伴婴儿的成长，在使用的舒适安全的方面也经过了设计，在减震 效果上进行了改良，三轮系的设计，使转弯更灵活。', '', 'http://p0vpex4u9.bkt.clouddn.com/project1.png', '', '/SmartgrnPatrform/web/file', 1, 0, 56, '2017-12-26 20:40:30', '2017-12-26 20:40:30', '2017-12-27 16:09:12', 1, '很有想法，如果实施生产出来，对于有孩子的家庭来说会有个不错的选择。');
INSERT INTO `creativeproject` VALUES (2, 1, 5, 0, '树叶回收垃圾桶', '制作简单，使用方便。垃圾桶，又名废物箱或垃圾箱，是指装放垃圾的地方。多数以金属或塑胶制，用时放入塑料袋，当垃圾一多便可扎起袋丢掉。垃圾桶是人们生活中\"藏污纳垢\"的容器，也是社会文化的一种折射。\n\n多数垃圾桶都有盖以防垃圾的异味四散，有些垃圾桶可以以脚踏开启。家居的垃圾桶多数放于厨房，以便放置厨余。有些家庭会在主要房间都各置一个。有些游乐场的垃圾桶会特别设计成可爱的人物。\n', '', 'http://p0vpex4u9.bkt.clouddn.com/project2.jpg', '', '/Smargkjkjirds/file', 1, 1, 44, '2017-12-27 20:48:08', '2017-12-27 20:48:08', '2017-12-28 16:09:19', 1, '有创意，和传统的垃圾桶不一样。');
INSERT INTO `creativeproject` VALUES (3, 2, 3, 0, '小型高脚杯', '美观实用，还能用于装饰。高脚杯，这种杯杯托上立着一只细长脚，所以才称其为高脚杯。喝红酒十分讲究一个品字，真实地品出它的原本风味首先要求一定的温度，高脚的目的就是将品尝者手掌的温度与盛酒的球部杯底隔离开，以免影响红酒温度。\r\n', '', 'http://p0vpex4u9.bkt.clouddn.com/project3.jpg', '', '/Smaehgthk/file', 0, 0, 90, '2017-12-27 20:48:08', '2017-12-27 20:50:48', '2017-12-29 16:09:24', 1, '\r\n此高脚杯外型美观，实用，广受人们欢迎');
INSERT INTO `creativeproject` VALUES (4, 1, 1, 0, '办公轻松用品', '简单装饰，实用美观。Base 是由 Mark Kelley 和 Richard Liu 两位设计师在旧金山开设的一家创意设计工作室。日前，两人推出了品牌最新的「Objects」系列办公用品组合，「001」, 「002」和「003」，采用了产自越南的宝丽石制作而成。其中，Objects 「001」与「002」是两个高度与大小皆不同的杯筒，而 Objects「003」是一个托盘与小杯子的组合，可以放在办公桌面收纳各种杂物，提供黑、白、灰与橙色 4 种选择。', '', 'http://p0vpex4u9.bkt.clouddn.com/project4.jpg', '', '/Saejkhhsnsjsa/file', 0, 1, 100, '2017-12-28 20:55:14', '2017-12-28 20:55:14', '2017-12-29 16:09:47', 1, '很好的设计，对于办公的人来说有个轻便的放置小东西是很好的；既美观又实用。');
INSERT INTO `creativeproject` VALUES (5, 1, 3, 0, '有线光电鼠标', '小巧可爱，值得拥有。有线光电鼠标，卡通创意产品，动物鼠标，例如有 熊猫鼠标 青蛙鼠标', '', 'http://p0vpex4u9.bkt.clouddn.com/project5.png', '', '/SmartgrnPlatfrong/file', 2, 0, 138, '2017-12-28 07:50:11', '2017-12-28 07:50:11', '2017-12-29 16:09:52', 1, '外形可爱，广受青少年喜欢\r\n');
INSERT INTO `creativeproject` VALUES (6, 2, 11, 0, '创意旋转乾坤插线板\r\n', '全方位插线，轻松搞定。还在为府宅内的插座数量有限而忧虑万千嘛？还在为6孔插线板只插3个插头而殚精竭虑嘛？还在为狭长插线板的合理摆放而大费周章嘛？和女友因为抢IPHONE充电插座而闹别扭？旋转乾坤插线板的诞生为您解决上述所有问题，再不担心因插头过大而浪费插座了！多种颜色，任您选择；轻易弯曲，随意安放！\r\n', '', 'http://p0vpex4u9.bkt.clouddn.com/project6.jpg', '', '/SmartgrnPlatrofknkds/file', 1, 0, 49, '2017-12-29 09:05:07', '2017-12-29 09:05:07', '2017-12-30 16:09:56', 1, '有想法，很有新意。');
INSERT INTO `creativeproject` VALUES (7, 1, 6, 0, '健身自行车', '健康骑行，娱乐又健身。健身自行车是主要用于健身类的自行车，自行车健身已经成为一种时尚。健康、时尚、新概念 全新理念设计的健身自行车，最主要的特点就是手脚双动力，时速可达40km/h;做功方式多、健身效果好。骑行本自行车时，手脚可同时对自行车驱动做功，也可单独用手或脚驱动做功。采用脚拉脚蹬直线式、手拉摆杆摆动式做功方式，既提高了自行车的行驶速度，又锻炼了腿部肌肉、臂部肌肉。获得多项国家专利，适应于中老年人作代步健身的交通工具之用，也可适应于年轻人作旅游娱乐之用。', '', 'http://p0vpex4u9.bkt.clouddn.com/project7.png', '', '/SmartgenPlatformsw/fie', 4, 0, 144, '2017-12-30 09:20:46', '2017-12-30 09:20:46', '2018-01-01 16:10:01', 1, '这里面要求对自己的实力有一个清醒的认识。首先是可投入的资金估算，这项决定了品牌选择时的定位，以及可涉及的市场范围。第二是品牌运作能力分析，如果是没有操作过品牌的经销商最好选择品牌经营能力较强的企业，这样它可以在品牌运作上提供更大支持。');
INSERT INTO `creativeproject` VALUES (8, 1, 13, 0, '太阳系旋转边桌', '旋转出新的体验。提高工作效率的秘诀之一就是保证桌面的整洁，那么，如何更好地让桌面物品各得其所呢？设计旋转边桌 Planet 将是你的工作好伴侣，它将桌面变得立体，拓展了桌子的使用范围。 Planet 以太阳系行星的环绕运动为创作灵感，利用一种人造石（可丽耐）制成三个大小不同、相互连接的圆形桌面，分别处于三个平面上。根据不同需求在不同平面上放东西，还可以随意调节三个桌面的旋转角度，让你拿取东西更加方便。 Planet 曾在 2013 年米兰设计周上展出，它设计用色简洁明快，让人感觉顺畅舒服。', '', 'http://p0vpex4u9.bkt.clouddn.com/project8.jpg', '', '/SmartgenPlatfrom/dfike', 1, 1, 202, '2017-12-30 09:44:31', '2017-12-30 09:44:31', '2017-12-02 16:10:06', 1, '提高工作效率的秘诀之一就是保证桌面的整洁，它将桌面变得立体，拓展了桌子的使用范围。');
INSERT INTO `creativeproject` VALUES (9, 1, 4, 0, '画沙机器人', '有趣的体验，找回你的童真。实用上，机器人（Robot）是自动执行工作的机器装置。机器人可接受人类指挥，也可以执行预先编排的程序，也可以根据以人工智能技术制定的原则纲领行动。机器人执行的是取代或是协助人类工作的工作，例如制造业、建筑业，或是危险的工作。画沙机器人娱乐大家，有小孩的家庭可以试试。', '', 'http://p0vpex4u9.bkt.clouddn.com/project9.jpg', '', '/SmartgrnPlatforn/file', 2, 0, 122, '2018-01-01 10:06:35', '2018-01-01 10:06:35', '2017-12-03 16:10:10', 1, '技术上没问题，可行。');
INSERT INTO `creativeproject` VALUES (10, 1, 4, 0, '巧妙创意', '平时能用来装饰，还能用来烘托浪漫的氛围。蜡烛，是一种日常照明工具，主要用石蜡制成，在古代，通常有动物油脂制造。可燃烧发出光亮。此外，蜡烛的用途也十分广泛:在生日宴会、宗教节日、集体哀悼、红白喜事等活动中也有重要用途。在文学艺术作品中，蜡烛有牺牲、奉献的象征意义。这样摆放很浪漫。', '', 'http://p0vpex4u9.bkt.clouddn.com/project10.jpg', '', '/SmartgenPlatform/file', 1, 0, 1, '2018-01-02 10:16:18', '2018-01-02 10:16:18', '2017-12-04 16:10:16', 0, '没有什么新意');
INSERT INTO `creativeproject` VALUES (11, 1, 7, 0, '创意摇摆椅', '随时躺，舒适缓解你的疲劳。此款椅子主要在传统的婴儿摇椅基础上加入了一个成人座位，改变了以往安抚孩子的方式，从之前的站着弯着腰来摇着摇椅，到现在可以与孩子一起摇摆，共享一把椅子。椅子主体主要是由竹筒和竹编组成，竹筒主要用来固定支撑整个椅子，细密的竹编的使用主要在靠背和座椅上。运用竹材料，让孩子更加亲近自然。', '', 'http://p0vpex4u9.bkt.clouddn.com/base.jpg', '', '/plan/file', 1, 0, 346, '2018-01-03 00:00:00', '2018-01-03 00:00:00', '2018-01-03 15:33:14', 1, '很好的创意设计想法，商业模式可行，计划书完整，通过。\r\n');
INSERT INTO `creativeproject` VALUES (12, 1, 6, 0, '鱼儿闹钟', '美观可爱，关键防水。', '', 'http://p0vpex4u9.bkt.clouddn.com/product1.jpg', '', '/file/plak', 1, 2, 13, '2018-06-06 10:46:24', '2018-06-06 10:46:24', '2018-06-07 10:47:12', 1, '有市场，可行。');
INSERT INTO `creativeproject` VALUES (13, 1, 12, 0, '网格落地灯', '打破传统的样子，美观实用。', NULL, 'http://p0vpex4u9.bkt.clouddn.com/product2.jpg\r\n', NULL, '/filels/fi', 1, 2, 11, '2018-05-02 10:52:29', '2018-05-02 10:52:29', '2018-05-03 10:52:57', 1, '想法很好。');
INSERT INTO `creativeproject` VALUES (14, 1, 8, 0, '玩具鸭', '可爱的外表，能有鸭子的叫声。', NULL, 'http://p0vpex4u9.bkt.clouddn.com/product3.jpg\r\n', NULL, '/shdh/sdf', 1, 2, 112, '2018-06-01 11:02:47', '2018-06-01 11:02:47', '2018-06-02 11:03:17', 1, '有新意。');
INSERT INTO `creativeproject` VALUES (15, 1, 8, 0, '迷你风扇', '小巧风力大，有香味。', NULL, 'http://p0vpex4u9.bkt.clouddn.com/product4.jpg\r\n', NULL, '/fili/sd', 1, 2, 11, '2018-06-04 11:05:30', '2018-06-04 11:05:30', '2018-06-05 11:05:47', 1, '可行。');
INSERT INTO `creativeproject` VALUES (16, 1, 9, 0, '挂碗', '方便实用。', NULL, 'http://p0vpex4u9.bkt.clouddn.com/product6.jpg\r\n', NULL, '/shhdi/fj', 1, 2, 1, '2018-06-09 11:09:29', '2018-06-09 11:09:29', '2018-06-10 11:09:44', 1, '有想法');
INSERT INTO `creativeproject` VALUES (17, 1, 10, 0, '艺术花瓶瓷', '漂亮，可以装饰，也可以插花', NULL, 'http://p0vpex4u9.bkt.clouddn.com/product7.jpg\r\n', NULL, '/filie/file', 1, 2, 32, '2018-05-10 11:12:07', '2018-05-10 11:12:07', '2018-05-12 11:12:30', 1, '与艺术结合，有创意。');
INSERT INTO `creativeproject` VALUES (18, 1, 2, 0, '藏起来吧', '可藏东西在里面，或是书籍，遥控器等等。', NULL, 'http://p0vpex4u9.bkt.clouddn.com/project11.png\r\n', NULL, '/sdddfd/fl/', 1, 2, 34, '2018-06-10 11:23:49', '2018-06-10 11:23:49', '2018-06-12 11:24:09', 1, '很有想法，在家里放置这样产品一举多得。');
INSERT INTO `creativeproject` VALUES (19, 1, 4, 0, '围栏上的花盆', '贴近生活，安全方便生活。', NULL, 'http://p0vpex4u9.bkt.clouddn.com/project12.png\r\n', NULL, '/dsdf/dfd', 1, 2, 11, '2018-06-01 11:27:53', '2018-06-01 11:27:53', '2018-06-04 11:28:11', 1, '既美化又安全。');
INSERT INTO `creativeproject` VALUES (20, 1, 7, 0, '小蒜瓣碗', '用蒜瓣碗来盛放小菜，为生活添加情趣。', NULL, 'http://p0vpex4u9.bkt.clouddn.com/project13.png\r\n', NULL, '/sdd/fg', 1, 2, 23, '2018-05-03 11:33:29', '2018-05-03 11:33:29', '2018-05-08 11:33:51', 1, '造型可爱，功能齐全。');
INSERT INTO `creativeproject` VALUES (21, 1, 10, 0, '提醒喝水智能杯环', '只要在普通的杯子上套上这个，通过蓝牙和iso设备连接，借助感应器可以检测到你的喝水量，以及在你长时间没喝水时发出提示。', NULL, 'http://p0vpex4u9.bkt.clouddn.com/project14.png\r\n', NULL, '/sd/df', 2, 2, 111, '2018-06-04 12:17:26', '2018-06-04 12:17:26', '2018-06-07 12:17:38', 1, '智能，有创意。');
INSERT INTO `creativeproject` VALUES (22, 1, 9, 0, '一分为二水果碗', '看上去没什么，黑白相间。但是在水果不够放时，可以一分为二，是容量扩充。', NULL, 'http://p0vpex4u9.bkt.clouddn.com/project15.png\r\n', NULL, '/shs/', 1, 2, 36, '2018-06-15 12:23:26', '2018-06-15 12:23:26', '2018-06-17 12:23:43', 1, '黑白相间，加上水果颜色，赏心悦目。');

-- ----------------------------
-- Table structure for creativeremark
-- ----------------------------
DROP TABLE IF EXISTS `creativeremark`;
CREATE TABLE `creativeremark`  (
  `Creremark_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `User_id` int(11) NOT NULL COMMENT '外键',
  `Creproject_id` int(11) NOT NULL COMMENT ' 外键',
  `Creremark_content` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '文件路径',
  `Creremark_praise` int(1) NULL DEFAULT NULL COMMENT '0为未点赞，1为已点赞',
  `Creremark_commentTime` datetime(0) NULL DEFAULT NULL,
  PRIMARY KEY (`Creremark_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 21 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of creativeremark
-- ----------------------------
INSERT INTO `creativeremark` VALUES (1, 2, 2, '3', 0, '2018-06-14 00:00:00');
INSERT INTO `creativeremark` VALUES (2, 2, 3, NULL, 1, NULL);
INSERT INTO `creativeremark` VALUES (3, 2, 8, NULL, 1, NULL);
INSERT INTO `creativeremark` VALUES (4, 2, 9, NULL, 0, NULL);
INSERT INTO `creativeremark` VALUES (5, 2, 11, NULL, 1, NULL);
INSERT INTO `creativeremark` VALUES (6, 14, 11, NULL, 0, NULL);
INSERT INTO `creativeremark` VALUES (7, 14, 8, NULL, 0, NULL);
INSERT INTO `creativeremark` VALUES (8, 14, 4, NULL, 0, NULL);
INSERT INTO `creativeremark` VALUES (9, 14, 3, NULL, 0, NULL);
INSERT INTO `creativeremark` VALUES (10, 15, 8, NULL, 1, NULL);
INSERT INTO `creativeremark` VALUES (11, 15, 7, NULL, 0, NULL);
INSERT INTO `creativeremark` VALUES (12, 15, 4, NULL, 1, NULL);
INSERT INTO `creativeremark` VALUES (13, 15, 9, NULL, 0, NULL);
INSERT INTO `creativeremark` VALUES (14, 15, 6, NULL, 0, NULL);
INSERT INTO `creativeremark` VALUES (15, 15, 22, NULL, 1, NULL);
INSERT INTO `creativeremark` VALUES (16, 15, 10, NULL, 1, NULL);
INSERT INTO `creativeremark` VALUES (17, 15, 11, NULL, 0, NULL);
INSERT INTO `creativeremark` VALUES (18, 15, 12, NULL, 1, NULL);
INSERT INTO `creativeremark` VALUES (19, 15, 13, NULL, 0, NULL);
INSERT INTO `creativeremark` VALUES (20, 15, 14, NULL, 1, NULL);

-- ----------------------------
-- Table structure for evaluationexpert
-- ----------------------------
DROP TABLE IF EXISTS `evaluationexpert`;
CREATE TABLE `evaluationexpert`  (
  `Expert_jobNumber` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `Expert_name` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `Expert_phone` varchar(11) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `Expert_password` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '长度为6-20位',
  `Expert_headPortrait` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '图片路径 初始给定',
  PRIMARY KEY (`Expert_jobNumber`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 3 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of evaluationexpert
-- ----------------------------
INSERT INTO `evaluationexpert` VALUES (1, '陈城', '15083857270', '123', '/SmartgenPlatform/img/expertHeadPortrait.png');
INSERT INTO `evaluationexpert` VALUES (2, '徐杰', '14785925646', '456', '/SmartgenPlatform/img/expertHeadPortrait.png');

-- ----------------------------
-- Table structure for interestingproject
-- ----------------------------
DROP TABLE IF EXISTS `interestingproject`;
CREATE TABLE `interestingproject`  (
  `Inproject_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `User_id` int(11) NOT NULL COMMENT '外键',
  `Inproject_abstract` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `Inproject_title` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `Inproject_label` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `Inproject_picture` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '图片路径',
  `Inproject_video` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '视频路径',
  `Inproject_state` int(1) NOT NULL COMMENT '0为人员招募中 1为招募已满',
  `Inproject_praise` int(11) NULL DEFAULT NULL,
  `Inproject_needPerson` int(11) NULL DEFAULT NULL,
  `Inproject_modifyTime` datetime(0) NULL DEFAULT NULL,
  `Inproject_releaseTime` datetime(0) NOT NULL,
  `Inproject_classify` int(1) NOT NULL COMMENT '0为美食1为运动2为旅行3为游戏4为动漫5为影视6为其它',
  PRIMARY KEY (`Inproject_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for interestingremark
-- ----------------------------
DROP TABLE IF EXISTS `interestingremark`;
CREATE TABLE `interestingremark`  (
  `Inremark_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `User_id` int(11) NOT NULL COMMENT '外键',
  `Inproject_id` int(11) NOT NULL COMMENT '外键',
  `Inremark_content` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '文件路径',
  `Inremark_praise` int(1) NULL DEFAULT NULL COMMENT '0为未点赞 1为已点赞',
  `Inremark_commentTime` datetime(0) NOT NULL,
  PRIMARY KEY (`Inremark_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for product
-- ----------------------------
DROP TABLE IF EXISTS `product`;
CREATE TABLE `product`  (
  `Id` int(11) NOT NULL AUTO_INCREMENT COMMENT '产品表主键',
  `Product_no` int(11) NOT NULL COMMENT '产品编号',
  `Creproject_id` int(11) NOT NULL COMMENT '创意项目主键',
  `Company_id` int(11) NOT NULL COMMENT '公司表主键',
  `Product_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '产品名',
  `Product_price` double(11, 0) NOT NULL COMMENT '产品销售价格',
  `Product_classify` tinyint(4) NOT NULL COMMENT '0为生活手工，1为家具家居，2为科技数码，3为艺术娱乐，4为医疗健康，5为户外运动，6为其他',
  `Product_label` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '产品标签',
  `Product_picture` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '图片路径',
  `Product_status` tinyint(4) NOT NULL COMMENT '产品类型:0-预购;1-直接购买;-1:下架商品',
  `Product_msg` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '产品简介',
  `Product_originPrice` double(11, 0) NOT NULL COMMENT '产品成本价',
  `Product_freight` double(11, 0) NULL DEFAULT NULL COMMENT '产品运费',
  `Product_count` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '商品库存',
  `Product_sell` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '产品销量',
  `Product_bestCount` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '好评数',
  `Product_middleCount` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '中评数量',
  `Product_badCount` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '差评数',
  `Product_requireMoney` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '(预购关系专属)需求钱数',
  `Product_currentMoney` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '(预购关系专属)当前钱数',
  `Product_countPrice` double(11, 2) NULL DEFAULT NULL COMMENT '活动价格',
  `Product_oneMsg` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '一句话简介',
  PRIMARY KEY (`Id`) USING BTREE,
  INDEX `Product_name`(`Product_name`) USING BTREE,
  INDEX `FK8uaea5byo08819c124vlgc5oq`(`Creproject_id`) USING BTREE,
  INDEX `FKm5q1c3g0ynndqf4wkn9omkr9m`(`Company_id`) USING BTREE,
  CONSTRAINT `FK8uaea5byo08819c124vlgc5oq` FOREIGN KEY (`Creproject_id`) REFERENCES `creativeproject` (`Creproject_id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `FKm5q1c3g0ynndqf4wkn9omkr9m` FOREIGN KEY (`Company_id`) REFERENCES `company` (`Company_id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `product_ibfk_1` FOREIGN KEY (`Creproject_id`) REFERENCES `creativeproject` (`Creproject_id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `product_ibfk_2` FOREIGN KEY (`Company_id`) REFERENCES `company` (`Company_id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 12 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '产品表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of product
-- ----------------------------
INSERT INTO `product` VALUES (1, 10001, 12, 1, '鱼儿闹钟', 15, 1, NULL, 'http://p0vpex4u9.bkt.clouddn.com/product1.jpg;http://p0vpex4u9.bkt.clouddn.com/product1.1.png', 1, '这种闹钟外表可爱，不是用硬质的材料制作而成，而是用软软的材料制\r\r\n作而成。不仅能够当做闹钟使用，还能当做儿童的玩具，此外还能起到\r\r\n装饰房间的作用，可谓是一举多得。重要的是这样的闹钟还是防水的，\r\r\n就不怕像普通的闹钟那样遇水就坏了。', 10, 0, '100', '300', '200', '33', '13', '0', '0', 13.00, '外观可爱，可以当玩具');
INSERT INTO `product` VALUES (2, 10002, 13, 1, '网格落地灯', 20, 1, NULL, 'http://p0vpex4u9.bkt.clouddn.com/product2.png;', 1, '这种落地灯与传统的灯不一样，采用的是中间放灯泡，四周用网格木质\r\r\n材料围起来。你可以把它放在任何你想放的地方，你不用担心会摔坏，\r\r\n因为你可以把底部固定。这样的落地灯既美观，还很实惠，是不错的选\r\r\n择。', 15, 0, '120', '390', '260', '19', '31', '0', '0', 17.00, '2018最新颖的落地灯');
INSERT INTO `product` VALUES (3, 10003, 14, 1, '玩具鸭', 14, 1, NULL, 'http://p0vpex4u9.bkt.clouddn.com/product3.jpg;', 1, '\r\n这种玩具鸭有一大一小，这两个搭配成一个玩具商品。父母可以陪着小\r\r\n孩一起玩耍。这个玩具鸭还能播放相关的音乐，受到小朋友的喜爱。', 8, 0, '90', '283', '159', '19', '10', '0', '0', 12.00, '亲子玩具鸭，父母和孩子一起玩');
INSERT INTO `product` VALUES (4, 10004, 15, 1, '迷你风扇', 30, 1, NULL, 'http://p0vpex4u9.bkt.clouddn.com/product4.jpg;', 1, '迷你小风扇，不像有的风扇只有外表，用起来的时候却不尽人意。这款产品，外表不但美观，最重要的是风力很大。你可以换电池或者是插线，都是可以的；它也很轻便，所以在夏天你可以随时携带。', 23, 0, '50', '250', '89', '4', '2', '0', '0', 25.00, '2018夏季清凉外出必备');
INSERT INTO `product` VALUES (5, 10005, 16, 1, '挂碗', 15, 1, NULL, 'http://p0vpex4u9.bkt.clouddn.com/product6.jpg;', 1, '这个用来挂碗最适合不过了，可能大多数的家庭都习惯把碗放在柜子里，正面叠起来放置，那样的话会有水渍在里面，容易弄脏。如果有个挂碗的，就能很好的解决这个问题，你能挂起来节约空间，方便好用。', 10, 0, '110', '406', '290', '50', '21', '0', '0', 13.00, '厨房方便节省空间挂碗产品');
INSERT INTO `product` VALUES (6, 10006, 17, 1, '艺术花瓶瓷', 25, 1, NULL, 'http://p0vpex4u9.bkt.clouddn.com/product7.jpg;', 1, '看这外表，就是很漂亮的。在家里放置这样的艺术花瓶瓷，提升了家里的格调。花瓶外表是雕刻的花纹，之后进行上色处理。你也可以选择各种类型的花插在里面，赏心悦目。', 18, 0, '80', '555', '326', '51', '11', '0', '0', 19.00, '有艺术有格调花瓶瓷');
INSERT INTO `product` VALUES (7, 10007, 18, 1, '藏起来吧', 50, 1, NULL, 'http://p0vpex4u9.bkt.clouddn.com/project11.png;http://p0vpex4u9.bkt.clouddn.com/project11.1.png;http://p0vpex4u9.bkt.clouddn.com/project11.2.png;http://p0vpex4u9.bkt.clouddn.com/project11.3.png;http://p0vpex4u9.bkt.clouddn.com/project11.4.png', 1, '收拾房间的时候，你一定遇到过这种失而复得——他们竟然藏在沙发靠垫或者坐垫的某个角落，于是你你连把沙发吃了的心都有。有了这款坐垫，你可以在里面藏书籍、遥控器、咖啡杯等等。', 38, 0, '40', '800', '215', '51', '10', '0', '0', 45.00, '藏起来的惊喜坐垫');
INSERT INTO `product` VALUES (8, 10008, 19, 1, '围栏上的花盆', 20, 1, NULL, 'http://p0vpex4u9.bkt.clouddn.com/project12.png;http://p0vpex4u9.bkt.clouddn.com/project12.1.png;http://p0vpex4u9.bkt.clouddn.com/project12.2.png', 1, '要不要在高楼上放花盆，往往是一个让人困扰的问题，因为高楼花盆很容易砸伤行人，可是花有时候的确是美化生活，使人开心快乐的一剂良药。这样的花盆可以在高楼上使用，坚固得很。这样的设计贴近生活，方便生活，好看又有安全感。', 14, 0, '70', '600', '312', '10', '2', '0', '0', 19.00, '好看安全，高楼上的花盆');
INSERT INTO `product` VALUES (9, 10009, 20, 1, '小蒜瓣碗', 30, 1, NULL, 'http://p0vpex4u9.bkt.clouddn.com/project13.png;http://p0vpex4u9.bkt.clouddn.com/project13.1.png;http://p0vpex4u9.bkt.clouddn.com/project13.2.png', 1, '当它们挂在架子上时，犹如一个巨大的蒜头。当你将它们取下来时，仿佛从蒜头上剥下来的一片片蒜瓣。用蒜瓣碗来盛放些小菜之类，定能增添不少生活情趣。', 12, 0, '60', '400', '220', '17', '2', '0', '0', 18.00, '造型可爱，功能齐全');
INSERT INTO `product` VALUES (10, 10010, 21, 1, '提醒喝水智能杯环', 50, 1, NULL, 'http://p0vpex4u9.bkt.clouddn.com/project14.png;http://p0vpex4u9.bkt.clouddn.com/project14.1.png;http://p0vpex4u9.bkt.clouddn.com/project14.1.png', 1, '只要将其套在杯子上，普通的水杯即刻变为智能，通过蓝牙与iso设备连接后，借助感应器可以检测到你的喝水量，以及在你长时间没喝水时发出提示。', 40, 0, '40', '560', '280', '31', '5', '0', '0', 38.00, '让科技走进你的生活');
INSERT INTO `product` VALUES (11, 10011, 22, 1, '一分为二水果碗', 18, 1, NULL, 'http://p0vpex4u9.bkt.clouddn.com/project15.png;http://p0vpex4u9.bkt.clouddn.com/project15.1.png;http://p0vpex4u9.bkt.clouddn.com/project15.2.png', 1, '它看上去就像是黑白条纹相间似乎没有什么特别。不过当水果不够放的情况下，便可以将它分离成2个独立的水果碗，一个黑色，一个白色。它的容量随之扩充了。看似简单却不是谁都可以想出来的，巧妙地节约了材料又增大了空间的利用。黑色白色，加上各种颜色的水果，赏心悦目。', 14, 0, '150', '700', '430', '100', '12', '0', '0', 17.00, '让你吃得赏心悦目');

-- ----------------------------
-- Table structure for productevalute
-- ----------------------------
DROP TABLE IF EXISTS `productevalute`;
CREATE TABLE `productevalute`  (
  `Id` int(11) NOT NULL AUTO_INCREMENT COMMENT '商品评价信息表主键',
  `Proevalute_no` int(11) NOT NULL COMMENT '评价编号',
  `Product_id` int(11) NOT NULL COMMENT '产品表主键',
  `User_id` int(11) NOT NULL COMMENT '用户表主键',
  `Purchaseitem_id` int(11) NULL DEFAULT NULL,
  `Proevalute_title` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '评价标题',
  `Proevalute_star` tinyint(4) NOT NULL COMMENT '评价等地:0-差评;1-中评;2-好评',
  `Proevalute_content` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '评价内容',
  `Proevalute_time` datetime(0) NOT NULL ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '评价时间',
  `Proevalute_picture` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '评价图片路径',
  PRIMARY KEY (`Id`) USING BTREE,
  INDEX `FK64ichhkcn5n9j0k8jcwo44xlm`(`Product_id`) USING BTREE,
  INDEX `FKpxgpp6y2184lrp4xdidrjkjsv`(`Purchaseitem_id`) USING BTREE,
  INDEX `FK5nmjho2ql15cg3emmnff01a5s`(`User_id`) USING BTREE,
  CONSTRAINT `FK5nmjho2ql15cg3emmnff01a5s` FOREIGN KEY (`User_id`) REFERENCES `user` (`User_id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `FK64ichhkcn5n9j0k8jcwo44xlm` FOREIGN KEY (`Product_id`) REFERENCES `product` (`Id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `FKpxgpp6y2184lrp4xdidrjkjsv` FOREIGN KEY (`Purchaseitem_id`) REFERENCES `purchaseitem` (`Id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `productevalute_ibfk_1` FOREIGN KEY (`Product_id`) REFERENCES `product` (`Id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `productevalute_ibfk_2` FOREIGN KEY (`User_id`) REFERENCES `user` (`User_id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '商品评价信息表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of productevalute
-- ----------------------------
INSERT INTO `productevalute` VALUES (1, 1, 1, 2, 1, '鱼儿闹钟', 2, NULL, '2018-06-13 20:38:32', NULL);

-- ----------------------------
-- Table structure for production
-- ----------------------------
DROP TABLE IF EXISTS `production`;
CREATE TABLE `production`  (
  `Production_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `Product_id` int(11) NOT NULL COMMENT '外键',
  `Company_id` int(11) NOT NULL COMMENT '外键',
  `Production_count` int(11) NOT NULL COMMENT '生产数量',
  PRIMARY KEY (`Production_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for purchase
-- ----------------------------
DROP TABLE IF EXISTS `purchase`;
CREATE TABLE `purchase`  (
  `Id` int(11) NOT NULL AUTO_INCREMENT COMMENT '订单表主键',
  `Purchase_no` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '订单编号',
  `User_id` int(11) NOT NULL COMMENT '外键:用户表主键',
  `Purchase_paymentTime` datetime(0) NULL DEFAULT NULL COMMENT '支付时间',
  `Purchase_patternOfPayment` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '支付方式',
  `Purchase_state` tinyint(4) NOT NULL COMMENT '订单状态:0为待付款 1为已付款 2为取消订单',
  `Purchase_price` double(255, 0) NULL DEFAULT NULL COMMENT '产品交易总价',
  `Purchaseaddress_id` int(11) NULL DEFAULT NULL,
  PRIMARY KEY (`Id`) USING BTREE,
  INDEX `Purchase_no`(`Purchase_no`) USING BTREE,
  INDEX `FKqlkygx9sfbgf2f8vafrs0lhtc`(`User_id`) USING BTREE,
  INDEX `FK6v9s4ttl2nqpddjey3b6uoa17`(`Purchaseaddress_id`) USING BTREE,
  CONSTRAINT `FK6v9s4ttl2nqpddjey3b6uoa17` FOREIGN KEY (`Purchaseaddress_id`) REFERENCES `purchaseaddress` (`Id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `FKqlkygx9sfbgf2f8vafrs0lhtc` FOREIGN KEY (`User_id`) REFERENCES `user` (`User_id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `purchase_ibfk_1` FOREIGN KEY (`User_id`) REFERENCES `user` (`User_id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 31 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '订单表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of purchase
-- ----------------------------
INSERT INTO `purchase` VALUES (1, '1', 1, '2018-06-14 20:33:23', '支付宝', 0, 15, NULL);
INSERT INTO `purchase` VALUES (2, '201806261009311', 1, NULL, NULL, 2, 43, 1);
INSERT INTO `purchase` VALUES (4, '201806261010542', 2, NULL, NULL, 2, 48, 1);
INSERT INTO `purchase` VALUES (5, '201806261551182', 2, NULL, NULL, 0, 20, 1);
INSERT INTO `purchase` VALUES (6, '2018062615551414', 14, NULL, NULL, 0, 20, 1);
INSERT INTO `purchase` VALUES (7, '2018062616064314', 14, NULL, NULL, 0, 20, 1);
INSERT INTO `purchase` VALUES (8, '2018062616081014', 14, NULL, NULL, 0, 20, 1);
INSERT INTO `purchase` VALUES (9, '2018062616122614', 14, NULL, NULL, 0, 20, 1);
INSERT INTO `purchase` VALUES (10, '2018062616142714', 14, NULL, NULL, 0, 20, 1);
INSERT INTO `purchase` VALUES (11, '2018062616170014', 14, NULL, NULL, 0, 20, 1);
INSERT INTO `purchase` VALUES (12, '2018062616211514', 14, NULL, NULL, 0, 20, 1);
INSERT INTO `purchase` VALUES (13, '2018062616241214', 14, NULL, NULL, 0, 20, 1);
INSERT INTO `purchase` VALUES (14, '2018062616244414', 14, NULL, NULL, 0, 20, 1);
INSERT INTO `purchase` VALUES (15, '2018062616270914', 14, NULL, NULL, 0, 20, 1);
INSERT INTO `purchase` VALUES (16, '2018062616293014', 14, NULL, NULL, 0, 14, 1);
INSERT INTO `purchase` VALUES (17, '2018062616312314', 14, NULL, NULL, 0, 14, 1);
INSERT INTO `purchase` VALUES (18, '2018062616440314', 14, NULL, NULL, 0, 20, 1);
INSERT INTO `purchase` VALUES (19, '2018062616454914', 14, NULL, NULL, 0, 15, 1);
INSERT INTO `purchase` VALUES (20, '2018062617142814', 14, NULL, NULL, 0, 15, 1);
INSERT INTO `purchase` VALUES (21, '2018062618373014', 14, NULL, NULL, 0, 20, 1);
INSERT INTO `purchase` VALUES (22, '2018062618453814', 14, NULL, NULL, 0, 18, 1);
INSERT INTO `purchase` VALUES (23, '2018062618482214', 14, NULL, NULL, 0, 20, 1);
INSERT INTO `purchase` VALUES (24, '2018062618512114', 14, NULL, NULL, 0, 15, 1);
INSERT INTO `purchase` VALUES (25, '2018062618520114', 14, NULL, NULL, 0, 14, 1);
INSERT INTO `purchase` VALUES (26, '2018062618553814', 14, NULL, NULL, 0, 14, 1);
INSERT INTO `purchase` VALUES (27, '2018062618594614', 14, NULL, NULL, 0, 20, 1);
INSERT INTO `purchase` VALUES (28, '2018062618595614', 14, NULL, NULL, 0, 20, 1);
INSERT INTO `purchase` VALUES (29, '2018062619112514', 14, NULL, NULL, 0, 20, 1);
INSERT INTO `purchase` VALUES (30, '2018062619135914', 14, NULL, NULL, 0, 20, 1);

-- ----------------------------
-- Table structure for purchaseaddress
-- ----------------------------
DROP TABLE IF EXISTS `purchaseaddress`;
CREATE TABLE `purchaseaddress`  (
  `Id` int(11) NOT NULL AUTO_INCREMENT COMMENT '收货地址表主键',
  `User_id` int(11) NULL DEFAULT NULL COMMENT '用户表主键',
  `Puraddress_isChoice` tinyint(4) NULL DEFAULT NULL COMMENT '是否为默认地址:1-是;2-不是',
  `Puraddress_userName` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '收货人姓名',
  `Puraddress_address` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '收货人详细地址',
  `Puraddress_userPhone` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '收货人手机号',
  `Puraddress_zipcode` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '邮政编码',
  `Puraddress_province` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '收货人省份',
  `Puraddress_city` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '收货人市',
  PRIMARY KEY (`Id`) USING BTREE,
  INDEX `FKissqqsf8srilnros522u5olt`(`User_id`) USING BTREE,
  CONSTRAINT `FKissqqsf8srilnros522u5olt` FOREIGN KEY (`User_id`) REFERENCES `user` (`User_id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `purchaseaddress_ibfk_1` FOREIGN KEY (`User_id`) REFERENCES `user` (`User_id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 9 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '收货地址表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of purchaseaddress
-- ----------------------------
INSERT INTO `purchaseaddress` VALUES (1, 1, 1, '麦克', '江西省九江市庐山区人民医院对面', '18870355735', '331100', '江西省', '九江市');
INSERT INTO `purchaseaddress` VALUES (2, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `purchaseaddress` VALUES (3, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `purchaseaddress` VALUES (4, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `purchaseaddress` VALUES (5, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '32');
INSERT INTO `purchaseaddress` VALUES (7, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `purchaseaddress` VALUES (8, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);

-- ----------------------------
-- Table structure for purchaseitem
-- ----------------------------
DROP TABLE IF EXISTS `purchaseitem`;
CREATE TABLE `purchaseitem`  (
  `Id` int(11) NOT NULL AUTO_INCREMENT,
  `Purchase_id` int(11) NULL DEFAULT NULL COMMENT '订单表主键',
  `Product_id` int(11) NULL DEFAULT NULL COMMENT '产品表主键',
  `Purchaseitem_count` int(11) NULL DEFAULT NULL COMMENT '商品购买数量',
  `Purchaseitem_msg` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '买家留言',
  `Purchaseitem_time` datetime(0) NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '发货时间',
  `Purchaseitem_way` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '发货方式:哪家快递 哪家空运公司',
  `Purchaseitem_singlePrice` double(255, 0) NULL DEFAULT NULL COMMENT '交易单价',
  `Purchaseitem_price` double(255, 0) NULL DEFAULT NULL COMMENT '交易总价',
  `Purchaseitem_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '商品名(冗余字段)',
  `Purchaseitem_status` tinyint(4) NULL DEFAULT NULL COMMENT '订单详情状态 0-未发货 1-已发货',
  `Purchaseitem_picture` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`Id`) USING BTREE,
  INDEX `FKa0aehpjvmsqy67f0vo20f24qo`(`Purchase_id`) USING BTREE,
  INDEX `FKikf0wteinyr53ee43c4yrt4y3`(`Product_id`) USING BTREE,
  CONSTRAINT `FKa0aehpjvmsqy67f0vo20f24qo` FOREIGN KEY (`Purchase_id`) REFERENCES `purchase` (`Id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `FKikf0wteinyr53ee43c4yrt4y3` FOREIGN KEY (`Product_id`) REFERENCES `product` (`Id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `purchaseitem_ibfk_2` FOREIGN KEY (`Purchase_id`) REFERENCES `purchase` (`Id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `purchaseitem_ibfk_3` FOREIGN KEY (`Product_id`) REFERENCES `product` (`Id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 33 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '订单详情表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of purchaseitem
-- ----------------------------
INSERT INTO `purchaseitem` VALUES (1, 1, 1, 1, NULL, '2018-06-26 15:10:53', NULL, 15, 15, '鱼儿闹钟', 0, 'http://p0vpex4u9.bkt.clouddn.com/product1.jpg;http://p0vpex4u9.bkt.clouddn.com/product1.1.png;');
INSERT INTO `purchaseitem` VALUES (2, 2, 1, 1, '测试1', '2018-06-26 14:58:46', NULL, 15, 15, '鱼儿闹钟', 0, 'http://p0vpex4u9.bkt.clouddn.com/product1.jpg;http://p0vpex4u9.bkt.clouddn.com/product1.1.png;');
INSERT INTO `purchaseitem` VALUES (3, 2, 3, 2, '测试2', NULL, NULL, 14, 28, '玩具鸭', 0, 'http://p0vpex4u9.bkt.clouddn.com/product3.jpg;');
INSERT INTO `purchaseitem` VALUES (5, 4, 2, 1, '', NULL, NULL, 20, 20, '网格落地灯', 0, 'http://p0vpex4u9.bkt.clouddn.com/product2.png;');
INSERT INTO `purchaseitem` VALUES (6, 4, 3, 2, '', NULL, NULL, 14, 28, '玩具鸭', 0, 'http://p0vpex4u9.bkt.clouddn.com/product3.jpg;');
INSERT INTO `purchaseitem` VALUES (7, 5, 2, 1, '456', NULL, NULL, 20, 20, '网格落地灯', 0, 'http://p0vpex4u9.bkt.clouddn.com/product2.png;');
INSERT INTO `purchaseitem` VALUES (8, 6, 2, 1, '', NULL, NULL, 20, 20, '网格落地灯', 0, 'http://p0vpex4u9.bkt.clouddn.com/product2.png;');
INSERT INTO `purchaseitem` VALUES (9, 7, 2, 1, '', NULL, NULL, 20, 20, '网格落地灯', 0, 'http://p0vpex4u9.bkt.clouddn.com/product2.png;');
INSERT INTO `purchaseitem` VALUES (10, 8, 2, 1, '', NULL, NULL, 20, 20, '网格落地灯', 0, 'http://p0vpex4u9.bkt.clouddn.com/product2.png;');
INSERT INTO `purchaseitem` VALUES (11, 9, 2, 1, '', NULL, NULL, 20, 20, '网格落地灯', 0, 'http://p0vpex4u9.bkt.clouddn.com/product2.png;');
INSERT INTO `purchaseitem` VALUES (12, 10, 2, 1, '', NULL, NULL, 20, 20, '网格落地灯', 0, 'http://p0vpex4u9.bkt.clouddn.com/product2.png;');
INSERT INTO `purchaseitem` VALUES (13, 11, 2, 1, '', NULL, NULL, 20, 20, '网格落地灯', 0, 'http://p0vpex4u9.bkt.clouddn.com/product2.png;');
INSERT INTO `purchaseitem` VALUES (14, 12, 2, 1, '', NULL, NULL, 20, 20, '网格落地灯', 0, 'http://p0vpex4u9.bkt.clouddn.com/product2.png;');
INSERT INTO `purchaseitem` VALUES (15, 13, 2, 1, '', NULL, NULL, 20, 20, '网格落地灯', 0, 'http://p0vpex4u9.bkt.clouddn.com/product2.png;');
INSERT INTO `purchaseitem` VALUES (16, 14, 2, 1, '', NULL, NULL, 20, 20, '网格落地灯', 0, 'http://p0vpex4u9.bkt.clouddn.com/product2.png;');
INSERT INTO `purchaseitem` VALUES (17, 15, 2, 1, '', NULL, NULL, 20, 20, '网格落地灯', 0, 'http://p0vpex4u9.bkt.clouddn.com/product2.png;');
INSERT INTO `purchaseitem` VALUES (18, 16, 3, 1, '', NULL, NULL, 14, 14, '玩具鸭', 0, 'http://p0vpex4u9.bkt.clouddn.com/product3.jpg;');
INSERT INTO `purchaseitem` VALUES (19, 17, 3, 1, '', NULL, NULL, 14, 14, '玩具鸭', 0, 'http://p0vpex4u9.bkt.clouddn.com/product3.jpg;');
INSERT INTO `purchaseitem` VALUES (20, 18, 2, 1, '', NULL, NULL, 20, 20, '网格落地灯', 0, 'http://p0vpex4u9.bkt.clouddn.com/product2.png;');
INSERT INTO `purchaseitem` VALUES (21, 19, 1, 1, '', NULL, NULL, 15, 15, '鱼儿闹钟', 0, 'http://p0vpex4u9.bkt.clouddn.com/product1.jpg;http://p0vpex4u9.bkt.clouddn.com/product1.1.png');
INSERT INTO `purchaseitem` VALUES (22, 20, 1, 1, '', NULL, NULL, 15, 15, '鱼儿闹钟', 0, 'http://p0vpex4u9.bkt.clouddn.com/product1.jpg;http://p0vpex4u9.bkt.clouddn.com/product1.1.png');
INSERT INTO `purchaseitem` VALUES (23, 21, 2, 1, '', NULL, NULL, 20, 20, '网格落地灯', 0, 'http://p0vpex4u9.bkt.clouddn.com/product2.png;');
INSERT INTO `purchaseitem` VALUES (24, 22, 11, 1, '', NULL, NULL, 18, 18, '一分为二水果碗', 0, 'http://p0vpex4u9.bkt.clouddn.com/project15.png;http://p0vpex4u9.bkt.clouddn.com/project15.1.png;http://p0vpex4u9.bkt.clouddn.com/project15.2.png');
INSERT INTO `purchaseitem` VALUES (25, 23, 2, 1, '', NULL, NULL, 20, 20, '网格落地灯', 0, 'http://p0vpex4u9.bkt.clouddn.com/product2.png;');
INSERT INTO `purchaseitem` VALUES (26, 24, 1, 1, '', NULL, NULL, 15, 15, '鱼儿闹钟', 0, 'http://p0vpex4u9.bkt.clouddn.com/product1.jpg;http://p0vpex4u9.bkt.clouddn.com/product1.1.png');
INSERT INTO `purchaseitem` VALUES (27, 25, 3, 1, '', NULL, NULL, 14, 14, '玩具鸭', 0, 'http://p0vpex4u9.bkt.clouddn.com/product3.jpg;');
INSERT INTO `purchaseitem` VALUES (28, 26, 3, 1, '', NULL, NULL, 14, 14, '玩具鸭', 0, 'http://p0vpex4u9.bkt.clouddn.com/product3.jpg;');
INSERT INTO `purchaseitem` VALUES (29, 27, 2, 1, '', NULL, NULL, 20, 20, '网格落地灯', 0, 'http://p0vpex4u9.bkt.clouddn.com/product2.png;');
INSERT INTO `purchaseitem` VALUES (30, 28, 2, 1, '', NULL, NULL, 20, 20, '网格落地灯', 0, 'http://p0vpex4u9.bkt.clouddn.com/product2.png;');
INSERT INTO `purchaseitem` VALUES (31, 29, 2, 1, '', NULL, NULL, 20, 20, '网格落地灯', 0, 'http://p0vpex4u9.bkt.clouddn.com/product2.png;');
INSERT INTO `purchaseitem` VALUES (32, 30, 2, 1, '', NULL, NULL, 20, 20, '网格落地灯', 0, 'http://p0vpex4u9.bkt.clouddn.com/product2.png;');

-- ----------------------------
-- Table structure for response
-- ----------------------------
DROP TABLE IF EXISTS `response`;
CREATE TABLE `response`  (
  `Response_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `Inproject_id` int(11) NOT NULL COMMENT '外键',
  `User_id` int(11) NOT NULL COMMENT '外键',
  PRIMARY KEY (`Response_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for shoppingcart
-- ----------------------------
DROP TABLE IF EXISTS `shoppingcart`;
CREATE TABLE `shoppingcart`  (
  `Id` int(11) NOT NULL AUTO_INCREMENT COMMENT '购物车表主键',
  `User_id` int(11) NULL DEFAULT NULL COMMENT '用户表主键',
  `Product_id` int(11) NULL DEFAULT NULL COMMENT '商品表主键',
  `Product_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '商品名（冗余字段）',
  `Product_price` double(11, 0) NULL DEFAULT NULL COMMENT '商品价格（冗余字段）',
  `Product_picture` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '商品图片（冗余字段）',
  `Product_count` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '商品数量',
  `Product_msg` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '商品简介（冗余字段）',
  PRIMARY KEY (`Id`) USING BTREE,
  INDEX `FKkhyibe5v5mlfci20lyyj5sblr`(`User_id`) USING BTREE,
  INDEX `FK661nglv0ovj7mampfqcpfc9rc`(`Product_id`) USING BTREE,
  CONSTRAINT `FK661nglv0ovj7mampfqcpfc9rc` FOREIGN KEY (`Product_id`) REFERENCES `product` (`Id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `FKkhyibe5v5mlfci20lyyj5sblr` FOREIGN KEY (`User_id`) REFERENCES `user` (`User_id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 6 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '购物车表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of shoppingcart
-- ----------------------------
INSERT INTO `shoppingcart` VALUES (5, 15, 1, '鱼儿闹钟', 15, 'http://p0vpex4u9.bkt.clouddn.com/product1.jpg;http://p0vpex4u9.bkt.clouddn.com/product1.1.png', '1', '外观可爱，可以当玩具');

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user`  (
  `User_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `User_realName` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `User_name` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `User_phone` varchar(11) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `User_password` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '长度为6-20位',
  `User_sex` int(1) NULL DEFAULT NULL COMMENT '0为男，1为女',
  `User_headPortrait` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '图片路径 初始给定',
  `User_IDNumber` varchar(18) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '身份证号',
  `User_location` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '所在地',
  `User_identity` int(1) NOT NULL COMMENT '0为未认证，1为认证 初始值为0',
  PRIMARY KEY (`User_id`) USING BTREE,
  UNIQUE INDEX `User_phone`(`User_phone`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 16 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES (1, '', '', '18870355735', 'maike66', 1, 'http://p0vpex4u9.bkt.clouddn.com/head.jpg', NULL, '江西省-九江', 0);
INSERT INTO `user` VALUES (2, '蔡云霞', '平行线', '15270579058', '6415917cc', 0, 'http://p0vpex4u9.bkt.clouddn.com/head.jpg', '362331199610293927', '江西省-九江', 1);
INSERT INTO `user` VALUES (3, '蔡雪芳', '长不大', '15070254682', '88888888ee', 0, 'http://p0vpex4u9.bkt.clouddn.com/head.jpg', '362202199610193356', '江西省-南昌', 1);
INSERT INTO `user` VALUES (4, NULL, '微微一笑', '15170579059', '666666qq', 1, 'http://p0vpex4u9.bkt.clouddn.com/head.jpg', NULL, '江西省-赣州', 0);
INSERT INTO `user` VALUES (5, NULL, '凉凉', '15270255421', 'lingling5', 0, 'http://p0vpex4u9.bkt.clouddn.com/head.jpg', NULL, '江西省-赣州', 0);
INSERT INTO `user` VALUES (6, '马赛琦', '马赛克', '13070586482', 'masaike4', 0, 'http://p0vpex4u9.bkt.clouddn.com/head.jpg', '510233199811082341', '湖北省-武汉', 1);
INSERT INTO `user` VALUES (7, NULL, '星星', '17254962548', 'starshaui3', 0, 'http://p0vpex4u9.bkt.clouddn.com/head.jpg', NULL, '湖北省-黄石', 0);
INSERT INTO `user` VALUES (8, NULL, '向荣', '15946257834', 'xiangrong2', 0, 'http://p0vpex4u9.bkt.clouddn.com/head.jpg', NULL, '湖南省-湘潭', 0);
INSERT INTO `user` VALUES (9, '', '美好时光', '15085461556', '456789e', 1, 'http://p0vpex4u9.bkt.clouddn.com/head.jpg', '', '湖南省-长沙', 0);
INSERT INTO `user` VALUES (10, NULL, '肖奈', '18951576656', 'xiaonai1', 0, 'http://p0vpex4u9.bkt.clouddn.com/head.jpg', NULL, '湖南省-湘潭', 0);
INSERT INTO `user` VALUES (11, '', '香玲', '15895462566', '111111c', 1, 'http://p0vpex4u9.bkt.clouddn.com/head.jpg', '', '江西省-九江', 0);
INSERT INTO `user` VALUES (12, NULL, '百里屠苏', '14236597564', '45678955b', 0, 'http://p0vpex4u9.bkt.clouddn.com/head.jpg', NULL, '江西省-南昌', 0);
INSERT INTO `user` VALUES (13, NULL, 'jjuxxGreat', '13570579059', '123123a', 0, 'http://p0vpex4u9.bkt.clouddn.com/head.jpg', NULL, '江西省-南昌', 0);
INSERT INTO `user` VALUES (14, NULL, '睡不醒的猪', '15270579059', '131415cai', 0, 'http://p0vpex4u9.bkt.clouddn.com/head.jpg', NULL, '江西省-南昌', 0);
INSERT INTO `user` VALUES (15, NULL, '我的包1234', '18870350069', '131415cai', NULL, 'defaultJpg', NULL, NULL, 0);

SET FOREIGN_KEY_CHECKS = 1;
