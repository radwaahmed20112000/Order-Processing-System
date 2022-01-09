-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema mydb
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema mydb
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `mydb` DEFAULT CHARACTER SET utf8 ;
USE `mydb` ;

-- -----------------------------------------------------
-- Table `mydb`.`PUBLISHER`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`PUBLISHER` (
  `name` VARCHAR(45) NOT NULL,
  `address` VARCHAR(100) NULL,
  `phone_number` VARCHAR(15) NULL,
  PRIMARY KEY (`name`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`BOOK`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`BOOK` (
  `isbn` INT NOT NULL,
  `title` VARCHAR(45) NULL,
  `publisher` VARCHAR(45) NULL,
  `publication_year` VARCHAR(4) NULL,
  `selling_price` INT NULL,
  `category` VARCHAR(45) NULL,
  `min_quantity_threshold` INT NULL,
  `current_quantity` INT NULL,
  PRIMARY KEY (`isbn`),
  INDEX `fk_book_idx` (`publisher` ASC) VISIBLE,
  CONSTRAINT `fk_book`
    FOREIGN KEY (`publisher`)
    REFERENCES `mydb`.`PUBLISHER` (`name`)
    ON DELETE RESTRICT
    ON UPDATE CASCADE)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`BOOK_AUTHOR`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`BOOK_AUTHOR` (
  `book_id` INT NOT NULL,
  `name` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`book_id`, `name`),
  CONSTRAINT `fk_book_author`
    FOREIGN KEY (`book_id`)
    REFERENCES `mydb`.`BOOK` (`isbn`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`SUPPLIER_ORDER`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`SUPPLIER_ORDER` (
  `book_id` INT NOT NULL,
  `required_quantity` INT NOT NULL,
  `order_date` DATE NULL,
  PRIMARY KEY (`book_id`),
  CONSTRAINT `fk_supplier_order`
    FOREIGN KEY (`book_id`)
    REFERENCES `mydb`.`BOOK` (`isbn`)
    ON DELETE RESTRICT
    ON UPDATE CASCADE)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`CUSTOMER`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`CUSTOMER` (
  `email_address` VARCHAR(45) NOT NULL,
  `first_name` VARCHAR(45) NULL,
  `last_name` VARCHAR(45) NULL,
  `password` VARCHAR(45) NULL,
  `phone_number` VARCHAR(15) NULL,
  `shipping_address` VARCHAR(100) NULL,
  PRIMARY KEY (`email_address`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`CART`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`CART` (
  `user_email` VARCHAR(45) NOT NULL,
  `book_id` INT NOT NULL,
  `count` INT NULL,
  PRIMARY KEY (`user_email`, `book_id`),
  INDEX `fk_cart_book_idx` (`book_id` ASC) VISIBLE,
  CONSTRAINT `fk_cart_book`
    FOREIGN KEY (`book_id`)
    REFERENCES `mydb`.`BOOK` (`isbn`)
    ON DELETE RESTRICT
    ON UPDATE CASCADE,
  CONSTRAINT `fk_cart_email`
    FOREIGN KEY (`user_email`)
    REFERENCES `mydb`.`CUSTOMER` (`email_address`)
    ON DELETE RESTRICT
    ON UPDATE CASCADE)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`CLIENT_ORDER`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`CLIENT_ORDER` (
  `order_id` INT NOT NULL AUTO_INCREMENT,
  `user_email` VARCHAR(45) NULL,
  `order_date` VARCHAR(45) NULL,
  PRIMARY KEY (`order_id`),
  INDEX `fk_client_order_idx` (`user_email` ASC) VISIBLE,
  CONSTRAINT `fk_client_order`
    FOREIGN KEY (`user_email`)
    REFERENCES `mydb`.`CUSTOMER` (`email_address`)
    ON DELETE RESTRICT
    ON UPDATE CASCADE)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`CLIENT_ORDER_DETAILS`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`CLIENT_ORDER_DETAILS` (
  `order_id` INT NOT NULL,
  `book_id` INT NOT NULL,
  `order_count` VARCHAR(45) NULL,
  PRIMARY KEY (`order_id`, `book_id`),
  INDEX `fk_order_details_book_idx` (`book_id` ASC) VISIBLE,
  CONSTRAINT `fk_order_details_order`
    FOREIGN KEY (`order_id`)
    REFERENCES `mydb`.`CLIENT_ORDER` (`order_id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT `fk_order_details_book`
    FOREIGN KEY (`book_id`)
    REFERENCES `mydb`.`BOOK` (`isbn`)
    ON DELETE RESTRICT
    ON UPDATE CASCADE)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`MANAGER`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`MANAGER` (
  `email_address` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`email_address`),
  CONSTRAINT `fk_manager`
    FOREIGN KEY (`email_address`)
    REFERENCES `mydb`.`CUSTOMER` (`email_address`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
