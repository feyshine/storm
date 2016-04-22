-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';

-- -----------------------------------------------------
-- Schema mydb
-- -----------------------------------------------------
-- -----------------------------------------------------
-- Schema yskj
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema yskj
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `yskj` DEFAULT CHARACTER SET utf8 ;
USE `yskj` ;

-- -----------------------------------------------------
-- Table `yskj`.`kservice`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `yskj`.`kservice` (
  `kf_account` VARCHAR(50) NOT NULL,
  `kf_headimgurl` VARCHAR(200) NULL DEFAULT NULL,
  `nickname` VARCHAR(50) NULL DEFAULT NULL,
  `password` VARCHAR(50) NOT NULL,
  PRIMARY KEY (`kf_account`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `yskj`.`sign`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `yskj`.`sign` (
  `id` INT(4) NOT NULL AUTO_INCREMENT,
  `signature` VARCHAR(50) NOT NULL,
  `timestamp` VARCHAR(50) NOT NULL,
  `nonce` VARCHAR(50) NOT NULL,
  `echostr` VARCHAR(50) NOT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `yskj`.`user`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `yskj`.`user` (
  `id` INT(4) NOT NULL AUTO_INCREMENT,
  `username` VARCHAR(50) NOT NULL DEFAULT 'admin',
  `password` VARCHAR(50) NOT NULL DEFAULT '123456',
  `age` INT(4) NOT NULL DEFAULT '0',
  `sex` INT(4) NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`))
ENGINE = InnoDB
AUTO_INCREMENT = 12
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `yskj`.`wx_access_token`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `yskj`.`wx_access_token` (
  `id` BIGINT(20) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '主键',
  `access_token` VARCHAR(600) NOT NULL COMMENT 'access_token凭证',
  `expires_in` BIGINT(20) NOT NULL COMMENT '凭证有效时间，单位：秒',
  `create_time` BIGINT(20) NOT NULL COMMENT '创建时间距离当前时间毫秒数',
  `create_date` VARCHAR(25) NOT NULL COMMENT '创建时间',
  `userid` VARCHAR(128) NOT NULL COMMENT 'userid',
  PRIMARY KEY (`id`))
ENGINE = InnoDB
AUTO_INCREMENT = 3
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `yskj`.`wx_menu`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `yskj`.`wx_menu` (
  `id` BIGINT(50) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '主键',
  `name` VARCHAR(50) NOT NULL COMMENT '菜单昵称',
  `xkey` VARCHAR(50) NOT NULL COMMENT '菜单KEY值，用于消息接口推送',
  `type` VARCHAR(50) NOT NULL COMMENT '菜单的响应动作类型',
  `url` VARCHAR(100) NOT NULL COMMENT '网页链接',
  `media_id` VARCHAR(50) NOT NULL COMMENT '调用新增永久素材接口',
  `parent` VARCHAR(50) NULL DEFAULT NULL COMMENT '父菜单',
  PRIMARY KEY (`id`))
ENGINE = InnoDB
AUTO_INCREMENT = 21
DEFAULT CHARACTER SET = utf8;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
