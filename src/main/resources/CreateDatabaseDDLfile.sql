CREATE TABLE books
(
  id INT(11) PRIMARY KEY NOT NULL AUTO_INCREMENT,
  bookTitle VARCHAR(255) NOT NULL,
  bookAuthor VARCHAR(255),
  image VARCHAR(255),
  isbn VARCHAR(255),
  descr VARCHAR(255),
  likes INT(11) DEFAULT '0',
  dislikes INT(11) DEFAULT '0',
  path VARCHAR(255)
);
CREATE TABLE comments
(
  id INT(11) PRIMARY KEY NOT NULL AUTO_INCREMENT,
  userid INT(11),
  bookid INT(11),
  description TEXT
);
CREATE TABLE dislikes
(
  id INT(11) PRIMARY KEY NOT NULL AUTO_INCREMENT,
  book_id INT(11),
  user_id INT(11),
  amount INT(11)
);
CREATE TABLE likes
(
  id INT(11) PRIMARY KEY NOT NULL AUTO_INCREMENT,
  book_id INT(11),
  user_id INT(11),
  amount INT(11)
);
CREATE TABLE roles
(
  id INT(11) PRIMARY KEY NOT NULL AUTO_INCREMENT,
  name VARCHAR(50) NOT NULL
);
CREATE TABLE tags
(
  id INT(11) PRIMARY KEY NOT NULL AUTO_INCREMENT,
  tagname VARCHAR(32) NOT NULL,
  bookid INT(11) NOT NULL
);
CREATE TABLE user_roles
(
  user_id INT(11) NOT NULL,
  role_id INT(11) NOT NULL,
  CONSTRAINT user_roles_users_id_fk FOREIGN KEY (user_id) REFERENCES users (id),
  CONSTRAINT user_roles_roles_id_fk FOREIGN KEY (role_id) REFERENCES roles (id)
);
CREATE INDEX user_roles_roles_id_fk ON user_roles (role_id);
CREATE INDEX user_roles_users_id_fk ON user_roles (user_id);
CREATE TABLE users
(
  id INT(11) PRIMARY KEY NOT NULL AUTO_INCREMENT,
  username VARCHAR(255) NOT NULL,
  password VARCHAR(255) NOT NULL
);