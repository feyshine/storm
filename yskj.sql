/*
Navicat MySQL Data Transfer

Source Server         : localhost_3306
Source Server Version : 50711
Source Host           : localhost:3306
Source Database       : yskj

Target Server Type    : MYSQL
Target Server Version : 50711
File Encoding         : 65001

Date: 2016-05-18 17:35:20
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for article
-- ----------------------------
DROP TABLE IF EXISTS `article`;
CREATE TABLE `article` (
  `id` bigint(50) NOT NULL AUTO_INCREMENT,
  `media_id` varchar(50) DEFAULT NULL,
  `title` varchar(100) DEFAULT NULL,
  `thumb_media_id` varchar(100) DEFAULT NULL,
  `author` varchar(100) DEFAULT NULL,
  `digest` varchar(100) DEFAULT NULL,
  `show_cover_pic` varchar(10) DEFAULT NULL,
  `content` varchar(2000) DEFAULT NULL,
  `content_source_url` varchar(200) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=453456346 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of article
-- ----------------------------
INSERT INTO `article` VALUES ('453456345', null, '奥利奥', '1462934182648', 'robin', '奥利奥', '1', '先扭一扭，舔一舔，再泡一泡，只有奥加奥！', 'https://www.baidu.com');

-- ----------------------------
-- Table structure for button
-- ----------------------------
DROP TABLE IF EXISTS `button`;
CREATE TABLE `button` (
  `id` bigint(50) NOT NULL,
  `name` varchar(100) CHARACTER SET ucs2 NOT NULL,
  `nkey` varchar(100) DEFAULT NULL,
  `type` varchar(100) DEFAULT NULL,
  `url` varchar(200) DEFAULT NULL,
  `media_id` varchar(200) DEFAULT NULL,
  `parentid` bigint(50) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of button
-- ----------------------------
INSERT INTO `button` VALUES ('1', '产品介绍', 'cpjs', 'click', null, null, '0');
INSERT INTO `button` VALUES ('2', '视频集合', 'spjh', 'click', null, null, '0');
INSERT INTO `button` VALUES ('3', '产品详情', 'cpxq', 'click', 'http://dasdfsdfd', 'http://dasdfsdfd', '1');
INSERT INTO `button` VALUES ('5', '个人中心', 'grzx', 'click', null, null, '0');

-- ----------------------------
-- Table structure for country
-- ----------------------------
DROP TABLE IF EXISTS `country`;
CREATE TABLE `country` (
  `id` bigint(20) NOT NULL,
  `name` varchar(45) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `name_UNIQUE` (`name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='国家表';

-- ----------------------------
-- Records of country
-- ----------------------------
INSERT INTO `country` VALUES ('1', '中国');
INSERT INTO `country` VALUES ('11', '俄罗斯');
INSERT INTO `country` VALUES ('8', '加拿大');
INSERT INTO `country` VALUES ('6', '德国');
INSERT INTO `country` VALUES ('10', '新加坡');
INSERT INTO `country` VALUES ('3', '日本');
INSERT INTO `country` VALUES ('7', '法国');
INSERT INTO `country` VALUES ('9', '澳大利亚');
INSERT INTO `country` VALUES ('2', '美国');
INSERT INTO `country` VALUES ('5', '英国');
INSERT INTO `country` VALUES ('4', '韩国');

-- ----------------------------
-- Table structure for fans
-- ----------------------------
DROP TABLE IF EXISTS `fans`;
CREATE TABLE `fans` (
  `openid` varchar(45) NOT NULL DEFAULT 'null',
  `nickname` varchar(45) DEFAULT 'null',
  `sex` varchar(45) DEFAULT 'null',
  `city` varchar(45) DEFAULT 'null',
  `country` varchar(45) DEFAULT 'null',
  `province` varchar(45) DEFAULT 'null',
  `language` varchar(45) DEFAULT 'null',
  `headimgurl` varchar(200) DEFAULT 'null',
  `subscribe` varchar(45) DEFAULT 'null',
  `subscribe_time` varchar(45) DEFAULT 'null',
  `remark` varchar(45) DEFAULT 'null',
  `groupid` varchar(45) DEFAULT 'null',
  PRIMARY KEY (`openid`),
  UNIQUE KEY `openid_UNIQUE` (`openid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of fans
-- ----------------------------
INSERT INTO `fans` VALUES ('75346346753', '李磊', '男', null, '中国', '上海市', '意大利语', 'http://www.naifdf.cn', '是', null, '曹磊', '');

-- ----------------------------
-- Table structure for image
-- ----------------------------
DROP TABLE IF EXISTS `image`;
CREATE TABLE `image` (
  `img_id` bigint(20) NOT NULL COMMENT '图片id',
  `img_name` varchar(50) DEFAULT NULL COMMENT '图片名称',
  `img_size` varchar(50) DEFAULT NULL COMMENT '图片大小',
  `img_type` varchar(50) DEFAULT NULL COMMENT '图片格式',
  `img_path` varchar(50) DEFAULT NULL COMMENT '图片存储路径',
  PRIMARY KEY (`img_id`),
  UNIQUE KEY `img_id_UNIQUE` (`img_id`),
  UNIQUE KEY `img_name_UNIQUE` (`img_name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='素材图片';

-- ----------------------------
-- Records of image
-- ----------------------------
INSERT INTO `image` VALUES ('1462932880513', '142J4503341230-5P29.jpg', '90845', 'image/jpeg', 'D:\\tomcate\\webapps\\storm\\WEB-INF\\upload');
INSERT INTO `image` VALUES ('1462934182648', '95G58PIC4qb_1024.jpg', '75039', 'image/jpeg', 'D:\\tomcate\\webapps\\storm\\WEB-INF\\upload');
INSERT INTO `image` VALUES ('1462934203084', '11284670_150403334001_2.jpg', '139398', 'image/jpeg', 'D:\\tomcate\\webapps\\storm\\WEB-INF\\upload');

-- ----------------------------
-- Table structure for kservice
-- ----------------------------
DROP TABLE IF EXISTS `kservice`;
CREATE TABLE `kservice` (
  `kf_account` varchar(50) NOT NULL,
  `kf_headimgurl` varchar(200) DEFAULT NULL,
  `nickname` varchar(50) DEFAULT NULL,
  `password` varchar(50) NOT NULL,
  PRIMARY KEY (`kf_account`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of kservice
-- ----------------------------
INSERT INTO `kservice` VALUES ('user001', 'D:\\tomcate\\webapps\\storm\\WEB-INF\\upload', 'jack', '123456');
INSERT INTO `kservice` VALUES ('user002', 'D:\\tomcate\\webapps\\storm\\WEB-INF\\upload', 'bruce', '123456');
INSERT INTO `kservice` VALUES ('USer003@xuyelijia', 'D:\\tomcate\\webapps\\storm\\WEB-INF\\upload', 'jackson', '123456');
INSERT INTO `kservice` VALUES ('user004', 'D:\\tomcate\\webapps\\storm\\WEB-INF\\upload', 'Lily', '123456');

-- ----------------------------
-- Table structure for language
-- ----------------------------
DROP TABLE IF EXISTS `language`;
CREATE TABLE `language` (
  `id` bigint(20) NOT NULL,
  `name` varchar(45) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `name_UNIQUE` (`name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='语言表';

-- ----------------------------
-- Records of language
-- ----------------------------
INSERT INTO `language` VALUES ('4', '俄语');
INSERT INTO `language` VALUES ('10', '意大利语');
INSERT INTO `language` VALUES ('8', '日语');
INSERT INTO `language` VALUES ('1', '汉语');
INSERT INTO `language` VALUES ('3', '法语');
INSERT INTO `language` VALUES ('2', '英语');
INSERT INTO `language` VALUES ('7', '葡萄牙语');
INSERT INTO `language` VALUES ('6', '西班牙语');
INSERT INTO `language` VALUES ('5', '阿拉伯语');
INSERT INTO `language` VALUES ('9', '韩语');

-- ----------------------------
-- Table structure for menu
-- ----------------------------
DROP TABLE IF EXISTS `menu`;
CREATE TABLE `menu` (
  `id` bigint(50) NOT NULL AUTO_INCREMENT,
  `name` varchar(100) CHARACTER SET ucs2 DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of menu
-- ----------------------------

-- ----------------------------
-- Table structure for node
-- ----------------------------
DROP TABLE IF EXISTS `node`;
CREATE TABLE `node` (
  `id` bigint(50) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `pname` varchar(100) DEFAULT NULL COMMENT '父名称',
  `name` varchar(50) NOT NULL COMMENT '名称',
  `state` varchar(50) NOT NULL DEFAULT 'open' COMMENT '是否展开',
  `url` varchar(100) DEFAULT NULL COMMENT '请求路径',
  `checked` tinyint(1) NOT NULL DEFAULT '0' COMMENT '是否选中',
  `attributes` varchar(100) DEFAULT NULL COMMENT '属性',
  PRIMARY KEY (`id`),
  UNIQUE KEY `name` (`name`)
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of node
-- ----------------------------
INSERT INTO `node` VALUES ('1', '', '菜单管理', 'closed', '', '0', '');
INSERT INTO `node` VALUES ('2', '', '消息管理', 'closed', '', '0', '');
INSERT INTO `node` VALUES ('3', '', '微信客服', 'closed', '', '0', '');
INSERT INTO `node` VALUES ('4', '', '网页管理', 'closed', '', '0', '');
INSERT INTO `node` VALUES ('5', '', '素材管理', 'closed', '', '0', '');
INSERT INTO `node` VALUES ('6', '', '用户管理', 'closed', '', '0', '');
INSERT INTO `node` VALUES ('7', '', '数据分析', 'closed', '', '0', '');
INSERT INTO `node` VALUES ('8', '菜单管理', '菜单管理001', 'closed', 'menu', '0', '');
INSERT INTO `node` VALUES ('9', '消息管理', '消息管理002', 'closed', 'message', '0', '');
INSERT INTO `node` VALUES ('10', '微信客服', '微信客服003', 'closed', 'kservice', '0', '');
INSERT INTO `node` VALUES ('11', '网页管理', '网页管理004', 'closed', 'web', '0', '');
INSERT INTO `node` VALUES ('12', '素材管理', '素材管理005', 'closed', 'material', '0', '');
INSERT INTO `node` VALUES ('13', '用户管理', '用户管理006', 'closed', 'userManager', '0', '');
INSERT INTO `node` VALUES ('14', '数据分析', '数据分析007', 'closed', 'dataAnalysis', '0', '');

-- ----------------------------
-- Table structure for province
-- ----------------------------
DROP TABLE IF EXISTS `province`;
CREATE TABLE `province` (
  `id` bigint(20) NOT NULL COMMENT '主键',
  `name` varchar(45) NOT NULL COMMENT '省份名称',
  `cid` bigint(20) DEFAULT NULL COMMENT '国家主键',
  PRIMARY KEY (`id`),
  UNIQUE KEY `name_UNIQUE` (`name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='省份表';

-- ----------------------------
-- Records of province
-- ----------------------------
INSERT INTO `province` VALUES ('1', '北京市', '1');
INSERT INTO `province` VALUES ('2', '天津市', '1');
INSERT INTO `province` VALUES ('3', '上海市', '1');
INSERT INTO `province` VALUES ('4', '重庆市', '1');
INSERT INTO `province` VALUES ('5', '河北省', '1');
INSERT INTO `province` VALUES ('6', '河南省', '1');
INSERT INTO `province` VALUES ('7', '云南省', '1');
INSERT INTO `province` VALUES ('8', '辽宁省', '1');
INSERT INTO `province` VALUES ('9', '黑龙江省', '1');
INSERT INTO `province` VALUES ('10', '湖南省', '1');
INSERT INTO `province` VALUES ('11', '安徽省', '1');
INSERT INTO `province` VALUES ('12', '山东省', '1');
INSERT INTO `province` VALUES ('13', '新疆维吾尔', '1');
INSERT INTO `province` VALUES ('14', '江苏省', '1');
INSERT INTO `province` VALUES ('15', '浙江省', '1');
INSERT INTO `province` VALUES ('16', '江西省', '1');
INSERT INTO `province` VALUES ('17', '湖北省', '1');
INSERT INTO `province` VALUES ('18', '广西壮族', '1');
INSERT INTO `province` VALUES ('19', '甘肃省', '1');
INSERT INTO `province` VALUES ('20', '山西省', '1');
INSERT INTO `province` VALUES ('21', '内蒙古', '1');
INSERT INTO `province` VALUES ('22', '陕西省', '1');
INSERT INTO `province` VALUES ('23', '吉林省', '1');
INSERT INTO `province` VALUES ('24', '福建省', '1');
INSERT INTO `province` VALUES ('25', '贵州省', '1');
INSERT INTO `province` VALUES ('26', '广东省', '1');
INSERT INTO `province` VALUES ('27', '青海省', '1');
INSERT INTO `province` VALUES ('28', '西藏', '1');
INSERT INTO `province` VALUES ('29', '四川省', '1');
INSERT INTO `province` VALUES ('30', '宁夏回族', '1');
INSERT INTO `province` VALUES ('31', '海南省', '1');
INSERT INTO `province` VALUES ('32', '台湾省', '1');
INSERT INTO `province` VALUES ('33', '香港特别行政区', '1');
INSERT INTO `province` VALUES ('34', '澳门特别行政区', '1');

-- ----------------------------
-- Table structure for sex
-- ----------------------------
DROP TABLE IF EXISTS `sex`;
CREATE TABLE `sex` (
  `id` bigint(20) NOT NULL COMMENT '主键',
  `name` varchar(45) NOT NULL COMMENT '性别',
  PRIMARY KEY (`id`),
  UNIQUE KEY `name_UNIQUE` (`name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='性别表';

-- ----------------------------
-- Records of sex
-- ----------------------------
INSERT INTO `sex` VALUES ('1', '女');
INSERT INTO `sex` VALUES ('0', '男');

-- ----------------------------
-- Table structure for sign
-- ----------------------------
DROP TABLE IF EXISTS `sign`;
CREATE TABLE `sign` (
  `id` int(4) NOT NULL AUTO_INCREMENT,
  `signature` varchar(50) NOT NULL,
  `timestamp` varchar(50) NOT NULL,
  `nonce` varchar(50) NOT NULL,
  `echostr` varchar(50) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sign
-- ----------------------------

-- ----------------------------
-- Table structure for state
-- ----------------------------
DROP TABLE IF EXISTS `state`;
CREATE TABLE `state` (
  `id` bigint(20) NOT NULL COMMENT '主键',
  `name` varchar(45) DEFAULT NULL COMMENT '名称',
  `code` varchar(45) DEFAULT NULL COMMENT '代号',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of state
-- ----------------------------
INSERT INTO `state` VALUES ('1', '是', '1');
INSERT INTO `state` VALUES ('2', '否', '0');

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `id` int(4) NOT NULL AUTO_INCREMENT,
  `username` varchar(50) NOT NULL DEFAULT 'admin',
  `password` varchar(50) NOT NULL DEFAULT '123456',
  `age` int(4) NOT NULL DEFAULT '0',
  `sex` int(4) NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES ('1', 'lilei', '86456565', '30', '1');
INSERT INTO `user` VALUES ('2', 'lili', '86456565', '30', '1');
INSERT INTO `user` VALUES ('3', 'lile', '86456565', '30', '1');
INSERT INTO `user` VALUES ('4', 'lili', '86456565', '30', '1');
INSERT INTO `user` VALUES ('5', 'abcd', '1234', '23', '1');
INSERT INTO `user` VALUES ('6', 'feixiang', '123456', '25', '1');
INSERT INTO `user` VALUES ('7', 'admin', '123456', '30', '2');
INSERT INTO `user` VALUES ('8', 'xiaohong', '123456', '30', '3');
INSERT INTO `user` VALUES ('9', 'lixiao', '8456', '3232', '0');
INSERT INTO `user` VALUES ('10', 'admin2', '123456', '1', '1');
INSERT INTO `user` VALUES ('11', '1', '1', '1', '1');

-- ----------------------------
-- Table structure for wx_access_token
-- ----------------------------
DROP TABLE IF EXISTS `wx_access_token`;
CREATE TABLE `wx_access_token` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键',
  `access_token` varchar(600) NOT NULL COMMENT 'access_token凭证',
  `expires_in` bigint(20) NOT NULL COMMENT '凭证有效时间，单位：秒',
  `create_time` bigint(20) NOT NULL COMMENT '创建时间距离当前时间毫秒数',
  `create_date` varchar(25) NOT NULL COMMENT '创建时间',
  `userid` varchar(128) NOT NULL COMMENT 'userid',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of wx_access_token
-- ----------------------------

-- ----------------------------
-- Table structure for wx_menu
-- ----------------------------
DROP TABLE IF EXISTS `wx_menu`;
CREATE TABLE `wx_menu` (
  `id` bigint(50) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键',
  `name` varchar(50) NOT NULL COMMENT '菜单昵称',
  `xkey` varchar(50) NOT NULL COMMENT '菜单KEY值，用于消息接口推送',
  `type` varchar(50) NOT NULL COMMENT '菜单的响应动作类型',
  `url` varchar(100) NOT NULL COMMENT '网页链接',
  `media_id` varchar(50) NOT NULL COMMENT '调用新增永久素材接口',
  `parent` varchar(50) DEFAULT NULL COMMENT '父菜单',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7889565 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of wx_menu
-- ----------------------------
INSERT INTO `wx_menu` VALUES ('3', '关于公司', '00003', 'click', 'www.bauwu.com', 'sssssss', '');
INSERT INTO `wx_menu` VALUES ('4', '我的广告', '00004', 'click', 'www.bauwu.com', 'fffffff', '');
INSERT INTO `wx_menu` VALUES ('5', '天气预报', '00001', 'click', 'www.bauwu.com', '123456789', '');
INSERT INTO `wx_menu` VALUES ('6', '地理位置', '00002', 'click', 'www.bauwu.com', 'xxxxxxx', '');
INSERT INTO `wx_menu` VALUES ('7', '关于公司', '00003', 'click', 'www.bauwu.com', 'sssssss', '');
INSERT INTO `wx_menu` VALUES ('8', '我的广告', '00004', 'click', 'www.bauwu.com', 'fffffff', '');
INSERT INTO `wx_menu` VALUES ('9', '天气预报', '00001', 'click', 'www.bauwu.com', '123456789', '');
INSERT INTO `wx_menu` VALUES ('10', '地理位置', '00002', 'click', 'www.bauwu.com', 'xxxxxxx', '');
INSERT INTO `wx_menu` VALUES ('11', '关于公司', '00003', 'click', 'www.bauwu.com', 'sssssss', '');
INSERT INTO `wx_menu` VALUES ('12', '我的广告', '00004', 'click', 'www.bauwu.com', 'fffffff', '');
INSERT INTO `wx_menu` VALUES ('13', '天气预报', '00001', 'click', 'www.bauwu.com', '123456789', '');
INSERT INTO `wx_menu` VALUES ('14', '地理位置', '00002', 'click', 'www.bauwu.com', 'xxxxxxx', '');
INSERT INTO `wx_menu` VALUES ('15', '关于公司', '00003', 'click', 'www.bauwu.com', 'sssssss', '');
INSERT INTO `wx_menu` VALUES ('16', '我的广告', '00004', 'click', 'www.bauwu.com', 'fffffff', '');
INSERT INTO `wx_menu` VALUES ('17', '我的广告', '00004', 'click', 'www.bauwu.com', 'fffffff', '');
INSERT INTO `wx_menu` VALUES ('18', '关于公司', '00003', 'click', 'www.bauwu.com', 'sssssss', '');
INSERT INTO `wx_menu` VALUES ('19', '地理位置', '00002', 'click', 'www.bauwu.com', 'xxxxxxx', '');
INSERT INTO `wx_menu` VALUES ('20', '天气预报', '00001', 'click', 'www.bauwu.com', '123456789', '');
INSERT INTO `wx_menu` VALUES ('45645', '456464', '4565464', '45645645', '456456', '456464', '45645645');
INSERT INTO `wx_menu` VALUES ('999999', 'menu9999', '99999', 'video', 'http://jkll\';lfdkflkdsf', 'asdsafdsa', 'dasdasd');
INSERT INTO `wx_menu` VALUES ('7889564', 'MENU456', '95632', '545', '6562', '98562', '4653');

-- ----------------------------
-- Table structure for wx_message
-- ----------------------------
DROP TABLE IF EXISTS `wx_message`;
CREATE TABLE `wx_message` (
  `id` bigint(50) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键',
  `MsgId` bigint(50) unsigned NOT NULL COMMENT '消息id，64位整型',
  `ToUserName` varchar(50) NOT NULL COMMENT '开发者微信号',
  `FromUserName` varchar(50) NOT NULL COMMENT '发送方帐号（一个OpenID）',
  `CreateTime` varchar(50) NOT NULL COMMENT '消息创建时间 （整型）',
  `MsgType` varchar(100) DEFAULT NULL COMMENT '网页链接',
  `Content` varchar(300) DEFAULT NULL COMMENT '文本消息内容',
  `PicUrl` varchar(100) DEFAULT NULL COMMENT '图片链接（由系统生成）',
  `MediaId` varchar(100) DEFAULT NULL COMMENT '图片/语音消息媒体id，可以调用多媒体文件下载接口拉取数据',
  `Format` varchar(50) DEFAULT NULL COMMENT '语音格式，如amr，speex等',
  `ThumbMediaId` varchar(100) DEFAULT NULL COMMENT '视频消息缩略图的媒体id',
  `Location_X` varchar(50) DEFAULT NULL COMMENT '地理位置维度',
  `Location_Y` varchar(50) DEFAULT NULL COMMENT '地理位置经度',
  `Scale` varchar(50) DEFAULT NULL COMMENT '地图缩放大小',
  `Title` varchar(50) DEFAULT NULL COMMENT '消息标题',
  `Description` varchar(200) DEFAULT NULL COMMENT '消息描述',
  `Url` varchar(200) DEFAULT NULL COMMENT '消息描述',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=46 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of wx_message
-- ----------------------------
INSERT INTO `wx_message` VALUES ('23', '1461562002025', 'gh_b680f7532fa2', 'o9fgrxPwaoR3IGtTscrUOQiFOJis', '1461518760000', 'text', '已收到您发来的语音，语音格式为：amr\n语音素材Id为：hvYBKAsRJfx62IwVa3XYs5Z9KKYkTwPlky9KDZPwEP1X-FOE9KMXJF4APWDZyMEl', null, null, null, null, null, null, null, null, null, null);
INSERT INTO `wx_message` VALUES ('24', '1461563029382', 'gh_b680f7532fa2', 'o9fgrxPwaoR3IGtTscrUOQiFOJis', '1461519780000', 'text', '你点击了天气预报', null, null, null, null, null, null, null, null, null, null);
INSERT INTO `wx_message` VALUES ('25', '1461563029938', 'gh_b680f7532fa2', 'o9fgrxPwaoR3IGtTscrUOQiFOJis', '1461519780000', 'text', '你点击了天气预报', null, null, null, null, null, null, null, null, null, null);
INSERT INTO `wx_message` VALUES ('26', '1461563047612', 'gh_b680f7532fa2', 'o9fgrxPwaoR3IGtTscrUOQiFOJis', '1461519840000', 'text', '已收到您发来的语音，语音格式为：amr\n语音素材Id为：Dv7LRIPA2tJiZ1y_0Qh7cQwAfSKq8S5C6GCfD187oG3dGh7uTZN7MX0l0TSBgm2Q', null, null, null, null, null, null, null, null, null, null);
INSERT INTO `wx_message` VALUES ('27', '1461563152095', 'gh_b680f7532fa2', 'o9fgrxPwaoR3IGtTscrUOQiFOJis', '1461519900000', null, null, 'https://ss0.bdstatic.com/5aV1bjqh_Q23odCf/static/superman/img/logo/bd_logo1_31bdc765.png', null, null, null, null, null, null, 'iOS 9.1现2大故障 iPhone基本功能遭殃', '凤凰科技讯 11月9日消息，据中关村在线报道,iOS 9.1 是个颇为稳定的版本，至少初初推出时的印象是这样，现在推出接近两星期，一些颇为严重的问题开始浮现。其中两大问题，正在困扰为数不少的用户。', 'http://tech.ifeng.com/a/20151109/41503466_0.shtml');
INSERT INTO `wx_message` VALUES ('28', '1461563295391', 'gh_b680f7532fa2', 'o9fgrxPwaoR3IGtTscrUOQiFOJis', '1461520080000', 'news', null, 'https://ss0.bdstatic.com/5aV1bjqh_Q23odCf/static/superman/img/logo/bd_logo1_31bdc765.png', null, null, null, null, null, null, 'iOS 9.1现2大故障 iPhone基本功能遭殃', '凤凰科技讯 11月9日消息，据中关村在线报道,iOS 9.1 是个颇为稳定的版本，至少初初推出时的印象是这样，现在推出接近两星期，一些颇为严重的问题开始浮现。其中两大问题，正在困扰为数不少的用户。', 'http://tech.ifeng.com/a/20151109/41503466_0.shtml');
INSERT INTO `wx_message` VALUES ('29', '1461563335913', 'gh_b680f7532fa2', 'o9fgrxPwaoR3IGtTscrUOQiFOJis', '1461520080000', 'music', null, null, null, null, 'kpNqTAk7tZ6NHhlHlEBhgUhfxjPBADbK79EfwF1RlOKAFcuKYC0eenD-ja-nTHg9', null, null, null, '蓝莲花', '蓝莲花-许巍', 'http://7te94m.com1.z0.glb.clouddn.com/lanlianhua.mp3');
INSERT INTO `wx_message` VALUES ('30', '1461563363019', 'gh_b680f7532fa2', 'o9fgrxPwaoR3IGtTscrUOQiFOJis', '1461520140000', 'text', '已收到您发来的小视频，小视频中素材ID为：WE-bFzayQEVLq3DF4xTPSeERlisHX4MNCBttLbVgna61OYEawy0ug6oyy5Osm4TI,\n小视频Id为：mf4uEko3zn8g-8j2bKM9890KIJPx_vmtkEKZeQKvk_F_q61n4IB2M3VVSI9sZ4X4', null, null, null, null, null, null, null, null, null, null);
INSERT INTO `wx_message` VALUES ('33', '894651230', 'gh_b680f7532fa2', 'o9fgrxPwaoR3IGtTscrUOQiFOJis', '1461518700000', 'text', '757455454', '', '', '', '', '', '', '', '', '', '');
INSERT INTO `wx_message` VALUES ('34', '1462419592823', 'gh_b680f7532fa2', 'o9fgrxPwaoR3IGtTscrUOQiFOJis', '1462419540000', 'text', '你点击了天气预报', null, null, null, null, null, null, null, null, null, null);
INSERT INTO `wx_message` VALUES ('35', '1462434282825', 'gh_b680f7532fa2', 'o9fgrxPwaoR3IGtTscrUOQiFOJis', '1462391040000', 'text', '你点击了天气预报', null, null, null, null, null, null, null, null, null, null);
INSERT INTO `wx_message` VALUES ('36', '1462434282993', 'gh_b680f7532fa2', 'o9fgrxPwaoR3IGtTscrUOQiFOJis', '1462391040000', 'text', '你点击了天气预报', null, null, null, null, null, null, null, null, null, null);
INSERT INTO `wx_message` VALUES ('37', '1462434389255', 'gh_b680f7532fa2', 'o9fgrxPwaoR3IGtTscrUOQiFOJis', '1462391160000', 'text', '你点击了天气预报', null, null, null, null, null, null, null, null, null, null);
INSERT INTO `wx_message` VALUES ('38', '1462434391395', 'gh_b680f7532fa2', 'o9fgrxPwaoR3IGtTscrUOQiFOJis', '1462391160000', 'text', '你点击了天气预报', null, null, null, null, null, null, null, null, null, null);
INSERT INTO `wx_message` VALUES ('39', '1462434397914', 'gh_b680f7532fa2', 'o9fgrxPwaoR3IGtTscrUOQiFOJis', '1462391160000', 'text', '你点击了天气预报', null, null, null, null, null, null, null, null, null, null);
INSERT INTO `wx_message` VALUES ('40', '1462434399924', 'gh_b680f7532fa2', 'o9fgrxPwaoR3IGtTscrUOQiFOJis', '1462391160000', 'text', '你点击了天气预报', null, null, null, null, null, null, null, null, null, null);
INSERT INTO `wx_message` VALUES ('41', '1462437492434', 'gh_b680f7532fa2', 'o9fgrxPwaoR3IGtTscrUOQiFOJis', '1462394280000', 'text', '你点击了天气预报', null, null, null, null, null, null, null, null, null, null);
INSERT INTO `wx_message` VALUES ('42', '1462498452612', 'gh_b680f7532fa2', 'o9fgrxPwaoR3IGtTscrUOQiFOJis', '1462498440000', 'text', '你点击了天气预报', null, null, null, null, null, null, null, null, null, null);
INSERT INTO `wx_message` VALUES ('43', '1462499673054', 'gh_b680f7532fa2', 'o9fgrxPwaoR3IGtTscrUOQiFOJis', '1462499640000', 'text', '你点击了天气预报', null, null, null, null, null, null, null, null, null, null);
INSERT INTO `wx_message` VALUES ('44', '1462499675194', 'gh_b680f7532fa2', 'o9fgrxPwaoR3IGtTscrUOQiFOJis', '1462499640000', 'text', '你点击了天气预报', null, null, null, null, null, null, null, null, null, null);
INSERT INTO `wx_message` VALUES ('45', '1462506357516', 'gh_b680f7532fa2', 'o9fgrxPwaoR3IGtTscrUOQiFOJis', '1462506300000', 'text', '你点击了天气预报', null, null, null, null, null, null, null, null, null, null);
