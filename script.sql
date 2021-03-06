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
  `address` VARCHAR(200) NULL,
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
  `selling_price` FLOAT NULL,
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
  `order_date` TIMESTAMP NULL,
  PRIMARY KEY (`book_id`),
  CONSTRAINT `fk_supplier_order`
    FOREIGN KEY (`book_id`)
    REFERENCES `mydb`.`BOOK` (`isbn`)
    ON DELETE RESTRICT
    ON UPDATE CASCADE)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`USER`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`USER` (
  `email_address` VARCHAR(45) NOT NULL,
  `first_name` VARCHAR(45) NULL,
  `last_name` VARCHAR(45) NULL,
  `password` VARCHAR(45) NOT NULL,
  `phone_number` VARCHAR(15) NULL,
  `shipping_address` VARCHAR(200) NULL,
  `user_name` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`email_address`),
  UNIQUE INDEX `email_address_UNIQUE` (`email_address` ASC) VISIBLE)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`CART`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`CART` (
  `user_email` VARCHAR(45) NOT NULL,
  `book_id` INT NOT NULL,
  `count` INT NOT NULL,
  PRIMARY KEY (`user_email`, `book_id`),
  INDEX `fk_cart_book_idx` (`book_id` ASC) VISIBLE,
  CONSTRAINT `fk_cart_book`
    FOREIGN KEY (`book_id`)
    REFERENCES `mydb`.`BOOK` (`isbn`)
    ON DELETE RESTRICT
    ON UPDATE CASCADE,
  CONSTRAINT `fk_cart_email`
    FOREIGN KEY (`user_email`)
    REFERENCES `mydb`.`USER` (`email_address`)
    ON DELETE RESTRICT
    ON UPDATE CASCADE)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`CLIENT_ORDER`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`CLIENT_ORDER` (
  `order_id` INT NOT NULL AUTO_INCREMENT,
  `user_email` VARCHAR(45) NULL,
  `order_date` TIMESTAMP NULL,
  PRIMARY KEY (`order_id`),
  INDEX `fk_client_order_idx` (`user_email` ASC) VISIBLE,
  CONSTRAINT `fk_client_order`
    FOREIGN KEY (`user_email`)
    REFERENCES `mydb`.`USER` (`email_address`)
    ON DELETE RESTRICT
    ON UPDATE CASCADE)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`CLIENT_ORDER_DETAILS`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`CLIENT_ORDER_DETAILS` (
  `order_id` INT NOT NULL,
  `book_id` INT NOT NULL,
  `order_count` INT NOT NULL,
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
    REFERENCES `mydb`.`USER` (`email_address`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB;

USE `mydb`;

DELIMITER $$
USE `mydb`$$
CREATE DEFINER = CURRENT_USER TRIGGER `mydb`.`BOOK_BEFORE_UPDATE` BEFORE UPDATE ON `BOOK` FOR EACH ROW
BEGIN
IF new.current_quantity < 0 THEN
SIGNAL SQLSTATE '45000' 
SET MESSAGE_TEXT = "Quantity of books in stock shiuldn't be negative";
END IF;
END$$

USE `mydb`$$
CREATE DEFINER = CURRENT_USER TRIGGER `mydb`.`BOOK_AFTER_UPDATE` AFTER UPDATE ON `BOOK` FOR EACH ROW
BEGIN

IF new.current_quantity < old.min_quantity_threshold AND old.current_quantity >= old.min_quantity_threshold THEN
INSERT INTO SUPPLIER_ORDER VALUES(old.isbn,old.min_quantity_threshold-new.current_quantity+20,now());
END IF;

END$$

USE `mydb`$$
CREATE DEFINER = CURRENT_USER TRIGGER `mydb`.`SUPPLIER_ORDER_BEFORE_DELETE` BEFORE DELETE ON `SUPPLIER_ORDER` FOR EACH ROW
BEGIN
UPDATE BOOK
SET current_quantity = current_quantity + old.required_quantity
WHERE isbn = old.book_id;
END$$


DELIMITER ;

SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;

