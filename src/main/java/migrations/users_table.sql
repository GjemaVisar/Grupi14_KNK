-- Tabela per krijim te user --

CREATE TABLE users (
  id INT NOT NULL AUTO_INCREMENT,
  username VARCHAR(50) NOT NULL,
  salted_password VARCHAR(256) NOT NULL,
  salt VARCHAR(32) NOT NULL,
  PRIMARY KEY (id)
);
-- shtimi i kolones per status--
alter table users add column is_active boolean not null;
alter table users add column date_registered date not null;


-- shtimi i tabeles per status user apo admin --
create table user_status(username varchar(50) primary key,
is_admin boolean);