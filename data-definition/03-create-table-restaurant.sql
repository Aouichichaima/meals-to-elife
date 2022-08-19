CREATE TABLE `elife`.`restaurants`(
  `id` INT PRIMARY KEY AUTO_INCREMENT,
  `name` VARCHAR(45) NULL DEFAULT "",
  `address` VARCHAR(250) NULL DEFAULT "",
  `menu` JSON NULL DEFAULT (JSON_ARRAY()),
  `rating` DECIMAL(2, 2) DEFAULT NULL,
  `id_manager` INT NULL,
  FOREIGN KEY (`id_manager`) REFERENCES `elife`.`users`(`id`)
);