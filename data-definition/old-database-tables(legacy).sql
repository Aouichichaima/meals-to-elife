--@Block 01-create-table-users
CREATE TABLE `elife`.`users`(
  `id` INT NOT NULL AUTO_INCREMENT,
  `cin` VARCHAR(8) NOT NULL UNIQUE,
  `first_name` VARCHAR(45) NOT NULL,
  `last_name` VARCHAR(45) NOT NULL,
  `phone` VARCHAR(30) NOT NULL,
  `email` VARCHAR(60) NOT NULL UNIQUE,
  `password` VARCHAR(100) NOT NULL,
  `type_of_user` ENUM("admin", "client", "restaurant") NOT NULL,
  `photo_url` VARCHAR(900) NULL,
  `isAuthorized` BOOLEAN NOT NULL DEFAULT FALSE,
  PRIMARY KEY (`id`)
);

--@Block 02-insert-admin
-- les comptes des admins sont pré inséré dans la base de données elife dans le table users
INSERT INTO
    users
VALUES
    (
        null,
        '14253647',
        'asma',
        'chebbi',
        '71130172',
        'asma.chebbi@fondation-tunisie.org',
        'rXe0cxER@KS6l00XEbA%48^',
        'admin',
        null,
        TRUE
    ),
    (
        null,
        '14253648',
        'mariem',
        'm.',
        '71130172',
        'mariem@fondation-tunisie.org',
        'sg@%0x$Q696VrWk$sk',
        'admin',
        null,
        TRUE
    );

--@Block 03-create-table-restaurant
CREATE TABLE `elife`.`restaurants`(
  `id` INT PRIMARY KEY AUTO_INCREMENT,
  `name` VARCHAR(45) NULL DEFAULT "",
  `address` VARCHAR(250) NULL DEFAULT "",
  `menu` JSON NULL DEFAULT (JSON_ARRAY()),
  `rating` DECIMAL(2, 2) DEFAULT NULL,
  `id_manager` INT NULL,
  FOREIGN KEY (`id_manager`) REFERENCES `elife`.`users`(`id`)
);
    -- menu TO BE RESPECTED json format
    -- [
    --     {
    --         "name": "repas1",
    --         "price": 1.5,
    --         "description": "description1"
    --     },
    --     {
    --         "name": "repas2",
    --         "price": 2.5,
    --         "description": "description2"
    --     },
    --     {
    --         "name": "repas3",
    --         "price": 3.5,
    --         "description": "description3"
    --     }
    -- ]

--@Block 04-create-table-customer_orders
CREATE TABLE `elife`.`customer_orders` (
    `id` INT PRIMARY KEY AUTO_INCREMENT,
    `meals` JSON NULL DEFAULT (JSON_ARRAY()),
    `client_id` INT NULL,
    `restaurant_id` INT NULL,
    CONSTRAINT `fk_client_id` FOREIGN KEY (`client_id`) REFERENCES `elife`.`users`(`id`),
    CONSTRAINT `fk_restaurant_id` FOREIGN KEY (`restaurant_id`) REFERENCES `elife`.`restaurants`(`id`)
);
    -- meals TO BE RESPECTED json format
    -- [
    --     {
    --         "name": "repas01",
    --         "unitPrice": 1.5, 
    --         "quantity": 3
    --     }, 
    --     {
    --         "name": "repas02",
    --         "unitPrice": 2.5,
    --         "quantity": 2
    --     },
    --     {
    --         "name": "repas03",
    --         "unitPrice": 3.5,
    --         "quantity": 1
    --     }
    -- ]

--@Block 05-create-table-customer_orders_archive
CREATE TABLE `elife`.`customer_orders_archive` (
    `id` BIGINT,
    `meals` JSON NULL DEFAULT (JSON_ARRAY()),
    `client_id` INT NULL,
    `restaurant_id` INT NULL,
    `isDelivered` BOOLEAN NOT NULL,
    CONSTRAINT `fk_orders_arch_client_id` FOREIGN KEY (`client_id`) REFERENCES `elife`.`users`(`id`),
    CONSTRAINT `fk_orders_arch_restaurant_id` FOREIGN KEY (`restaurant_id`) REFERENCES `elife`.`restaurants`(`id`)
);

--@Block 06-avis-table
CREATE TABLE `elife`.`avis` (
        `id` INT PRIMARY KEY AUTO_INCREMENT,
        `rating` INT,
        `notice` VARCHAR(1000),
        `client_id` INT NULL,
        `restaurant_id` INT NULL,
        CONSTRAINT `fk2_client_id` FOREIGN KEY (`client_id`) REFERENCES `elife`.`users`(`id`),
        CONSTRAINT `fk2_restaurant_id` FOREIGN KEY (`restaurant_id`) REFERENCES `elife`.`restaurants`(`id`)
);