DROP SCHEMA IF EXISTS YouTube;
CREATE SCHEMA webshop DEFAULT CHARACTER SET utf8 COLLATE utf8_general_ci;
USE Youtube;

CREATE TABLE users{
userName VARCHAR(10) NOT NULL,
password VARCHAR(15) NOT NULL,
name VARCHAR(10),
surname VARCHAR(15),
email VARCHAR(15) NOT NULL,
channeDescription VARCHAR(50),
registrationDate DATE TIME NOT NULL,
role ENUM ('USER','ADMIN') NOT NULL DEFAULT 'USER',
blocked BOOLEAN NOT NULL DEFAULT FALSE,
}