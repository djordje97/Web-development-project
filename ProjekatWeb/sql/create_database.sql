DROP SCHEMA IF EXISTS WebProject;
CREATE SCHEMA WebProject DEFAULT CHARACTER SET utf8 COLLATE utf8_general_ci;
USE WebProject;

CREATE TABLE users(
userName VARCHAR(10) NOT NULL,
userPassword VARCHAR(15) NOT NULL,
nameu VARCHAR(10),
surname VARCHAR(15),
email VARCHAR(30) NOT NULL,
channelDescription VARCHAR(50),
role ENUM ('USER','ADMIN') NOT NULL DEFAULT 'USER',
registrationDate DATE NOT NULL,
blocked BOOLEAN NOT NULL DEFAULT FALSE,
deleted BOOLEAN NOT NULL DEFAULT FALSE,
PRIMARY KEY (userName)
);
 INSERT INTO users(userName,userPassword,nameU,surname,email,role,registrationDate) VALUES('marko','123','Marko','Markovic','marko@gmail.com','USER','1.1.2018');
INSERT INTO users(userName,userPassword,nameU,surname,email,role,registrationDate) VALUES('darko','123','Marko','Markovic','darko@gmail.com','USER','1.1.2018');
INSERT INTO users(userName,userPassword,nameU,surname,email,role,registrationDate) VALUES('stanko','123','Marko','Markovic','marko@gmail.com','USER','1.1.2018');

CREATE TABLE subscribe(
masterUser VARCHAR(10),
subscriber VARCHAR(10),
FOREIGN KEY (masterUser) REFERENCES users(userName) ON DELETE RESTRICT,
FOREIGN KEY (subscriber) REFERENCES users(userName) ON DELETE RESTRICT
);
INSERT INTO subscribe(masterUser,subscriber)VALUES('marko','darko');
INSERT INTO subscribe(masterUser,subscriber)VALUES('darko','stanko');

CREATE TABLE video(
id BIGINT AUTO_INCREMENT,
videoUrl VARCHAR(100) NOT NULL,
videosPicture VARCHAR(100) NOT NULL,
videoName VARCHAR(50) NOT NULL,
description VARCHAR(50),
visibility ENUM ('PRIVATE','PUBLIC','UNLISTED') NOT NULL,
allowComment BOOLEAN NOT NULL DEFAULT TRUE,
likeNumber BIGINT NOT NULL ,
dislikeNumber BIGINT NOT NULL,
blocked BOOLEAN NOT NULL DEFAULT FALSE,
allowRating BOOLEAN DEFAULT TRUE,
views BIGINT NOT NULL,
createdDate  DATE NOT NULL,
deleted BOOLEAN NOT NULL DEFAULT FALSE,
ownerUserName VARCHAR(10) NOT NULL,
PRIMARY KEY (id),
FOREIGN KEY (ownerUserName) REFERENCES users(userName) ON DELETE RESTRICT
);

INSERT INTO video(videoUrl,videosPicture,videoName,description,visibility,allowComment,likeNumber,dislikeNumber
,blocked,allowRating,views,createdDate,deleted,ownerUserName) VALUES('https://www.youtube.com/embed/Q0CbN8sfihY','photos/slika.jpg','Star Warse',
'','PUBLIC',true,0,0,false,true,0,'24.01.2018',false,'marko');

CREATE TABLE comment(
id BIGINT AUTO_INCREMENT,
content VARCHAR(100),
commentDate DATE NOT NULL,
ownerUserName VARCHAR(10),
videoId BIGINT NOT NULL,
likeNumber BIGINT NOT NULL,
dislikeNumber BIGINT NOT NULL,
PRIMARY KEY (id),
FOREIGN KEY (ownerUserName) REFERENCES users(userName) ON DELETE RESTRICT,
FOREIGN KEY (videoId) REFERENCES video(id) ON DELETE RESTRICT
);

CREATE TABLE likeDislike(
id BIGINT AUTO_INCREMENT,
liked BOOLEAN NOT NULL,
likeDate DATE NOT NULL,
ownerUserName VARCHAR(10),
PRIMARY KEY (id),
FOREIGN KEY (ownerUserName) REFERENCES users(userName) ON DELETE RESTRICT
);

CREATE TABLE likeDislikeVideo(
likeId BIGINT,
videoId BIGINT,
FOREIGN KEY (likeId) REFERENCES likeDislike (id) ON DELETE RESTRICT,
FOREIGN KEY (videoId) REFERENCES video(id) ON DELETE RESTRICT
);

CREATE TABLE likeDislikeComment(
likeId BIGINT,
commentId BIGINT,
FOREIGN KEY (likeId) REFERENCES likeDislike (id) ON DELETE RESTRICT,
FOREIGN KEY (commentId) REFERENCES comment (id) ON DELETE RESTRICT
);