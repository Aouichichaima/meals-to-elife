### `elife` est le nom de notre base de données mysql.
```sql
CREATE DATABASE elife;
```
- Table `users`
    ```sql
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
    ```
- Table `restaurants`
    ```sql
    CREATE TABLE `elife`.`restaurants`(
        `id` INT PRIMARY KEY AUTO_INCREMENT,
        `name` VARCHAR(45) NULL DEFAULT "",
        `address` VARCHAR(250) NULL DEFAULT "",
        `menu` JSON NULL DEFAULT (JSON_ARRAY()),
        `rating` DECIMAL(2, 2) DEFAULT NULL,
        `id_manager` INT NULL,
        FOREIGN KEY (`id_manager`) REFERENCES `elife`.`users`(`id`)
    );
    ```
    La colonne `menu` de type JSON doit prendre le format suivant :
    ```json
    [
        {
            "name": "repas1",
            "price": 1.5,
            "description": "description1"
        },
        {
            "name": "repas2",
            "price": 2.5,
            "description": "description2"
        },
        {
            "name": "repas3",
            "price": 3.5,
            "description": "description3"
        }
    ]
    ```
- Table `customer_orders` ( contient les livraisons qui sont en cours du traitement )
    ```sql
    CREATE TABLE `elife`.`customer_orders` (
        `id` INT PRIMARY KEY AUTO_INCREMENT,
        `meals` JSON NULL DEFAULT (JSON_ARRAY()),
        `client_id` INT NULL,
        `restaurant_id` INT NULL,
        CONSTRAINT `fk_client_id` FOREIGN KEY (`client_id`) REFERENCES `elife`.`users`(`id`),
        CONSTRAINT `fk_restaurant_id` FOREIGN KEY (`restaurant_id`) REFERENCES `elife`.`restaurants`(`id`)
    );
    ```
    La colonne `meals` de type JSON doit prendre la format suivant:
    ```json
    [
        {
            "name": "repas01",
            "unitPrice": 1.5, 
            "quantity": 3
        }, 
        {
            "name": "repas02",
            "unitPrice": 2.5,
            "quantity": 2
        },
        {
            "name": "repas03",
            "unitPrice": 3.5,
            "quantity": 1
        }
    ]
    ```
- Table `customer_orders_archive` ( les commande qui sont livrée ou annuler par le chef du resto seront déplacer du tableau customer_orders vers le tableau customer_orders_archive )
    ```sql
    CREATE TABLE `elife`.`customer_orders_archive` (
        `id` BIGINT,
        `meals` JSON NULL DEFAULT (JSON_ARRAY()),
        `client_id` INT NULL,
        `restaurant_id` INT NULL,
        `isDelivered` BOOLEAN NOT NULL,
        CONSTRAINT `fk_orders_arch_client_id` FOREIGN KEY (`client_id`) REFERENCES `elife`.`users`(`id`),
        CONSTRAINT `fk_orders_arch_restaurant_id` FOREIGN KEY (`restaurant_id`) REFERENCES `elife`.`restaurants`(`id`)
    );
    ```


### [Configuré JavaFX dans Visual Studio Code](https://openjfx.io/openjfx-docs/#IDE-VSCode)
### [Télécharger mysql-connector-java-5.1.49](http://ftp.iij.ad.jp/pub/db/mysql/Downloads/Connector-J/)

### [Télécharger jackson-all-1.9.9.jar](http://www.java2s.com/Code/Jar/j/Downloadjacksonall199jar.htm)

### les étapes a suivi pour utiliser `JDBC`
- ajouter `mysql-connector-java-5.1.49.jar` à `Referenced Libraries` et importer la Bibliothèque
    ```java 
    import java.sql.*;
    ```
- Registrer le driveur
- etablire la connection
- créé une statement
- executer la requête sql
- analyse du resultat
- fermer la connexion
## :warning: Respecter le modèle MVC :warning:
