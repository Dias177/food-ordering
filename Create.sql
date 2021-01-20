-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema food_ordering
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema food_ordering
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `food_ordering` DEFAULT CHARACTER SET utf8 ;
USE `food_ordering` ;

-- -----------------------------------------------------
-- Table `food_ordering`.`role`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `food_ordering`.`role` (
  `id` BIGINT(10) NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `name_UNIQUE` (`name` ASC) VISIBLE)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `food_ordering`.`user`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `food_ordering`.`user` (
  `id` BIGINT(10) NOT NULL AUTO_INCREMENT,
  `role_id` BIGINT(10) NOT NULL,
  `email` VARCHAR(45) NOT NULL,
  `password` VARCHAR(45) NOT NULL,
  `first_name` VARCHAR(45) NOT NULL,
  `last_name` VARCHAR(45) NOT NULL,
  `phone_number` VARCHAR(45) NOT NULL,
  `birthday` DATE NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `email_UNIQUE` (`email` ASC) VISIBLE,
  INDEX `fk_user_role1_idx` (`role_id` ASC) VISIBLE,
  CONSTRAINT `fk_user_role1`
    FOREIGN KEY (`role_id`)
    REFERENCES `food_ordering`.`role` (`id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `food_ordering`.`food_category`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `food_ordering`.`food_category` (
  `id` BIGINT(10) NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `name_UNIQUE` (`name` ASC) VISIBLE)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `food_ordering`.`food`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `food_ordering`.`food` (
  `id` BIGINT(10) NOT NULL AUTO_INCREMENT,
  `food_category_id` BIGINT(10) NOT NULL,
  `name` VARCHAR(45) NOT NULL,
  `description` VARCHAR(45) NULL,
  `price` DOUBLE NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_food_food_category_idx` (`food_category_id` ASC) VISIBLE,
  CONSTRAINT `fk_food_food_category`
    FOREIGN KEY (`food_category_id`)
    REFERENCES `food_ordering`.`food_category` (`id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `food_ordering`.`order_status`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `food_ordering`.`order_status` (
  `id` BIGINT(10) NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `food_ordering`.`order`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `food_ordering`.`order` (
  `id` BIGINT(10) NOT NULL AUTO_INCREMENT,
  `user_id` BIGINT(10) NOT NULL,
  `order_status_id` BIGINT(10) NOT NULL,
  `price` DOUBLE NOT NULL,
  `date` TIMESTAMP(2) NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_order_user1_idx` (`user_id` ASC) VISIBLE,
  INDEX `fk_order_order_status1_idx` (`order_status_id` ASC) VISIBLE,
  CONSTRAINT `fk_order_user1`
    FOREIGN KEY (`user_id`)
    REFERENCES `food_ordering`.`user` (`id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT `fk_order_order_status1`
    FOREIGN KEY (`order_status_id`)
    REFERENCES `food_ordering`.`order_status` (`id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `food_ordering`.`order_detail`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `food_ordering`.`order_detail` (
  `id` BIGINT(10) NOT NULL AUTO_INCREMENT,
  `order_id` BIGINT(10) NOT NULL,
  `food_id` BIGINT(10) NOT NULL,
  `price` DOUBLE NOT NULL,
  `quantity` INT NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_order_detail_order1_idx` (`order_id` ASC) VISIBLE,
  INDEX `fk_order_detail_food1_idx` (`food_id` ASC) VISIBLE,
  CONSTRAINT `fk_order_detail_order1`
    FOREIGN KEY (`order_id`)
    REFERENCES `food_ordering`.`order` (`id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT `fk_order_detail_food1`
    FOREIGN KEY (`food_id`)
    REFERENCES `food_ordering`.`food` (`id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
