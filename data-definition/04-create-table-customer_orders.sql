CREATE TABLE `elife`.`customer_orders` (
    `id` INT PRIMARY KEY AUTO_INCREMENT,
    `meals` JSON NULL DEFAULT (JSON_ARRAY()),
    `client_id` INT NULL,
    `restaurant_id` INT NULL,
    CONSTRAINT `fk_client_id` FOREIGN KEY (`client_id`) REFERENCES `elife`.`users`(`id`),
    CONSTRAINT `fk_restaurant_id` FOREIGN KEY (`restaurant_id`) REFERENCES `elife`.`restaurants`(`id`)
);