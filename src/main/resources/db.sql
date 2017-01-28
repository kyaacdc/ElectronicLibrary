-- Table: users
CREATE TABLE electroniclibrary.users
(
    id INT PRIMARY KEY NOT NULL AUTO_INCREMENT,
    username VARCHAR(255) NOT NULL,
    password VARCHAR(255) NOT NULL
)
ENGINE = InnoDB;

-- Table: roles
CREATE TABLE electroniclibrary.roles
(
  id INT PRIMARY KEY NOT NULL AUTO_INCREMENT,
  name VARCHAR(50) NOT NULL
)
  ENGINE = InnoDB;

-- Table: mapping user and roles
CREATE TABLE electroniclibrary.user_roles
(
  user_id INT NOT NULL,
  role_id INT NOT NULL,
  CONSTRAINT user_roles_users_id_fk FOREIGN KEY (user_id) REFERENCES users (id),
  CONSTRAINT user_roles_roles_id_fk FOREIGN KEY (role_id) REFERENCES roles (id)
);
CREATE UNIQUE INDEX user_roles_user_id_uindex ON electroniclibrary.user_roles (user_id);
CREATE UNIQUE INDEX user_roles_role_id_uindex ON electroniclibrary.user_roles (role_id);


-- Table: mapping books
CREATE TABLE BOOKS
(
  ID INT(11) PRIMARY KEY NOT NULL AUTO_INCREMENT,
  BOOK_TITLE VARCHAR(255) NOT NULL,
  BOOK_AUTHOR VARCHAR(255) NOT NULL,
  ISBN VARCHAR(255) NOT NULL,
  DESCRIPTION VARCHAR(255),
  IMAGE VARCHAR(255),
  PATH VARCHAR(255),
  `LIKE` INT(11),
  DISLIKE INT(11)
);
CREATE UNIQUE INDEX BOOKS_ISBN_uindex ON BOOKS (ISBN);

-- Table: mapping tags for books
CREATE TABLE TAG
(
  ID INT(11) PRIMARY KEY NOT NULL AUTO_INCREMENT,
  NAME VARCHAR(32) NOT NULL,
  BOOK_ID INT(11) NOT NULL,
  CONSTRAINT TAG_BOOKS_ID_fk FOREIGN KEY (BOOK_ID) REFERENCES BOOKS (ID)
);
CREATE INDEX TAG_BOOKS_ID_fk ON TAG (BOOK_ID);
CREATE UNIQUE INDEX TAG_NAME_uindex ON TAG (NAME);

-- Table: mapping comentaries for books
CREATE TABLE COMENTARY
(
  ID INT(11) PRIMARY KEY NOT NULL AUTO_INCREMENT,
  USER_ID INT(11) NOT NULL,
  BOOK_ID INT(11) NOT NULL,
  column_4 INT(11),
  DESCRIPTION VARCHAR(255) NOT NULL
);