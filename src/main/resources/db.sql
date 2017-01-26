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

