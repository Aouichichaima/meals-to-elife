CREATE TABLE ` elife `.` users ` (
  ` id ` INT NOT NULL AUTO_INCREMENT,
  ` cin ` VARCHAR(8) NOT NULL UNIQUE,
  ` first_name ` VARCHAR(45) NOT NULL,
  ` last_name ` VARCHAR(45) NOT NULL,
  ` phone ` VARCHAR(30) NOT NULL,
  ` email ` VARCHAR(60) NOT NULL UNIQUE,
  ` password ` VARCHAR(100) NOT NULL,
  ` type_of_user ` ENUM("admin", "client", "restaurant") NOT NULL,
  ` photo_url ` VARCHAR(900) NULL,
  ` isAuthorized ` BOOLEAN NOT NULL DEFAULT FALSE,
  PRIMARY KEY (` id `)
);