CREATE TABLE `elife`.`customer_orders_archive` (
    `id` BIGINT,
    `meals` JSON NULL DEFAULT (JSON_ARRAY()),
    `client_id` INT NULL,
    `restaurant_id` INT NULL,
    `isDelivered` BOOLEAN NOT NULL,
    CONSTRAINT `fk_orders_arch_client_id` FOREIGN KEY (`client_id`) REFERENCES `elife`.`users`(`id`),
    CONSTRAINT `fk_orders_arch_restaurant_id` FOREIGN KEY (`restaurant_id`) REFERENCES `elife`.`restaurants`(`id`)
);