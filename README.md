### `elife` est le nom de notre base de données mysql.
- table `users`
    ```sql
    CREATE TABLE `elife`.`users` (
        `id` INT NOT NULL AUTO_INCREMENT,
        `cin` VARCHAR(8) NOT NULL UNIQUE,
        `first_name` VARCHAR(45) NOT NULL,
        `last_name` VARCHAR(45) NOT NULL,
        `phone` VARCHAR(30) NOT NULL,
        `email` VARCHAR(60) NOT NULL UNIQUE,
        `password` VARCHAR(100) NOT NULL,
        `type_of_user` VARCHAR(45) NOT NULL,
        `photo_url` VARCHAR(900) NULL,
        `isAuthorized` TINYINT NOT NULL DEFAULT 0,
        PRIMARY KEY (`id`));
        -- les comptes des admins sont pré inséré dans 
        -- la base de données elife dans le table users
        INSERT INTO users VALUES (null,'14253647', 'asma', 'chebbi', '71130172', 
        'asma.chebbi@fondation-tunisie.org', 'rXe0cxER@KS6l00XEbA%48^', 'admin', null, 1);
        INSERT INTO users VALUES (null,'14253648', 'mariem', 'm.', '71130172', 
        'mariem@fondation-tunisie.org', 'sg@%0x$Q696VrWk$sk', 'admin', null, 1);
    ```
### [Configuration JavaFX dans Visual Studio Code](https://openjfx.io/openjfx-docs/#IDE-VSCode)

### les étapes a suivi pour utiliser `JDBC`
- importer la Bibliothèque `mysql-connector-java` 
    ```java 
        import java.sql.*; 
    ```
- Registrer le driveur
- etablire la connection
- créé une statement
- executer la requete sql
- analyse du resultat
- fermer la connexion
## :warning: Respecter le modèle MVC :warning:
