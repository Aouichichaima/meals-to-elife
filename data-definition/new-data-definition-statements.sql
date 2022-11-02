-- in vscode install these extensions : SQLTools + SQLTools MySQL/MariaDB
-- https://marketplace.visualstudio.com/items?itemName=mtxr.sqltools
-- https://marketplace.visualstudio.com/items?itemName=mtxr.sqltools-driver-mysql


--@Block-database-creation
CREATE DATABASE meals_to_elife;

--@Block-table-user
CREATE TABLE users (
    id INT PRIMARY KEY AUTO_INCREMENT,
    cin VARCHAR(8) NOT NULL UNIQUE CHECK(LENGTH(cin) = 8),
    first_name VARCHAR(45) NOT NULL,
    last_name VARCHAR(45) NOT NULL,
    phone VARCHAR(30) NOT NULL,
    email VARCHAR(60) NOT NULL UNIQUE,
    password VARCHAR(999) NOT NULL,
    image_path VARCHAR(999) DEFAULT NULL
);

--@Block-table-role
CREATE TABLE role (
    type_of_user ENUM("admin", "client", "restaurant") NOT NULL,
    isAuthorized BOOLEAN NOT NULL DEFAULT FALSE,
    user_id INT PRIMARY KEY,
    FOREIGN KEY (user_id) REFERENCES users(id) ON DELETE CASCADE
);

--@Block-table-restaurants
CREATE TABLE restaurants (
  id INT PRIMARY KEY AUTO_INCREMENT,
  name VARCHAR(45),
  address VARCHAR(250),
  menu JSON DEFAULT (JSON_ARRAY()),
  rating DECIMAL(2, 1),
  manager_id INT NOT NULL,
  FOREIGN KEY (manager_id) REFERENCES users(id) ON DELETE CASCADE
);

--@Block-table-customer_orders
CREATE TABLE customer_orders (
    id INT PRIMARY KEY AUTO_INCREMENT,
    meals JSON NOT NULL DEFAULT (JSON_ARRAY()),
    ordered_at TIMESTAMP NOT NULL,
    client_id INT,
    restaurant_id INT,
    FOREIGN KEY (client_id) REFERENCES users(id) ON DELETE SET NULL,
    FOREIGN KEY (restaurant_id) REFERENCES restaurants(id) ON DELETE SET NULL
);

--@Block-table-delivery_staffs
CREATE TABLE delivery_staffs (
    id INT PRIMARY KEY AUTO_INCREMENT,
    cin VARCHAR(8) NOT NULL UNIQUE CHECK(LENGTH(cin) = 8),
    phone VARCHAR(30) NOT NULL,
    image_path VARCHAR(999),
    first_name VARCHAR(45) NOT NULL,
    last_name VARCHAR(45) NOT NULL,
    restaurant_id INT,
    FOREIGN KEY (restaurant_id) REFERENCES restaurants(id) ON DELETE CASCADE
);

--@Block-table-feedback_delivery_staffs
CREATE TABLE feedback_delivery_staffs (
    id INT PRIMARY KEY AUTO_INCREMENT,
    rating DECIMAL(2, 2),
    comment VARCHAR(999),
    delivery_staff_id INT,
    FOREIGN KEY (delivery_staff_id) REFERENCES delivery_staffs(id) ON DELETE CASCADE
);

--@Block-table-stocks
CREATE TABLE stocks (
    id INT PRIMARY KEY AUTO_INCREMENT,
    type_stock VARCHAR(99),
    description VARCHAR(999),
    restaurant_id INT,
    FOREIGN KEY (restaurant_id) REFERENCES restaurants(id) ON DELETE CASCADE
);

--@Block-table-products
CREATE TABLE products (
    id INT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(99) NOT NULL,
    price DECIMAL(13,3) NOT NULL,
    quantity INT NOT NULL,
    description VARCHAR(999),
    stock_id INT,
    FOREIGN KEY (stock_id) REFERENCES stocks(id) ON DELETE CASCADE
);