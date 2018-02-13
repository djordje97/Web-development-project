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
 INSERT INTO users(userName,userPassword,nameU,surname,email,role,registrationDate) VALUES('marko','123','Marko','Markovic','marko@gmail.com','USER','2018-1-1');
INSERT INTO users(userName,userPassword,nameU,surname,email,role,registrationDate) VALUES('darko','111','Darko','Darkovic','darko@gmail.com','USER','2018-3-3');
INSERT INTO users(userName,userPassword,nameU,surname,email,role,registrationDate) VALUES('stanko','123','Stanko','Stankic','stanko@gmail.com','USER','2018-4-4');
INSERT INTO users(userName,userPassword,nameU,surname,email,role,registrationDate) VALUES('Pera','0000','Pera','Peric','pera@gmail.com','ADMIN','2018-4-4');
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
,blocked,allowRating,views,createdDate,deleted,ownerUserName) VALUES('https://www.youtube.com/embed/Q0CbN8sfihY','photos/img.jpg','Star Warse',
'Best movie','PUBLIC',true,0,0,false,true,4,'2018-4-4',false,'marko');
INSERT INTO video(videoUrl,videosPicture,videoName,description,visibility,allowComment,likeNumber,dislikeNumber
,blocked,allowRating,views,createdDate,deleted,ownerUserName) VALUES('https://www.youtube.com/embed/Hgeu5rhoxxY','photos/img.jpg','Pirates of the Caribbean',
'Best movie','PUBLIC',true,0,0,false,true,165,'2018-7-5',false,'marko');
INSERT INTO video(videoUrl,videosPicture,videoName,description,visibility,allowComment,likeNumber,dislikeNumber
,blocked,allowRating,views,createdDate,deleted,ownerUserName) VALUES('https://www.youtube.com/embed/HSzx-zryEgM','photos/img.jpg','Doctor Strange',
'Best movie','PUBLIC',true,0,0,false,true,14,'2018-5-5',false,'marko');
INSERT INTO video(videoUrl,videosPicture,videoName,description,visibility,allowComment,likeNumber,dislikeNumber
,blocked,allowRating,views,createdDate,deleted,ownerUserName) VALUES('https://www.youtube.com/embed/oUWx3ZSjaRw','photos/img.jpg','AVENGERS: INFINITY WAR',
'Best movie','PUBLIC',true,0,0,false,true,34,'2018-5-5',false,'darko');
INSERT INTO video(videoUrl,videosPicture,videoName,description,visibility,allowComment,likeNumber,dislikeNumber
,blocked,allowRating,views,createdDate,deleted,ownerUserName) VALUES('https://www.youtube.com/embed/2cv2ueYnKjg','photos/img.jpg','GUARDIANS OF THE GALAXY ',
'Best movie','PUBLIC',true,0,0,false,true,48,'2018-5-5',false,'stanko');
INSERT INTO video(videoUrl,videosPicture,videoName,description,visibility,allowComment,likeNumber,dislikeNumber
,blocked,allowRating,views,createdDate,deleted,ownerUserName) VALUES('https://www.youtube.com/embed/AntcyqJ6brc','photos/img.jpg','Transformers: The Last Knight ',
'Best movie','PRIVATE',true,0,0,false,true,52,'2018-5-5',false,'stanko');
INSERT INTO video(videoUrl,videosPicture,videoName,description,visibility,allowComment,likeNumber,dislikeNumber
,blocked,allowRating,views,createdDate,deleted,ownerUserName) VALUES('https://www.youtube.com/embed/ue80QwXMRHg','photos/img.jpg','Thor: Ragnarok ',
'Best movie','UNLISTED',true,0,0,false,true,0,'2018-5-5',false,'stanko');

CREATE TABLE comment(
id BIGINT AUTO_INCREMENT,
content VARCHAR(100),
commentDate DATE NOT NULL,
ownerUserName VARCHAR(10),
videoId BIGINT NOT NULL,
likeNumber BIGINT NOT NULL,
dislikeNumber BIGINT NOT NULL,
deleted BOOLEAN NOT NULL DEFAULT FALSE,
PRIMARY KEY (id),
FOREIGN KEY (ownerUserName) REFERENCES users(userName) ON DELETE RESTRICT,
FOREIGN KEY (videoId) REFERENCES video(id) ON DELETE RESTRICT
);
INSERT INTO comment(content,commentDate,ownerUserName,videoId,likeNumber,dislikeNumber)
VALUES('Best movie','2018-1-1','marko',4,0,0);
INSERT INTO comment(content,commentDate,ownerUserName,videoId,likeNumber,dislikeNumber)
VALUES('I love this movie','2018-3-6','darko',4,0,0);
INSERT INTO comment(content,commentDate,ownerUserName,videoId,likeNumber,dislikeNumber)
VALUES('He is the best','2018-8-8','darko',4,0,0);
INSERT INTO comment(content,commentDate,ownerUserName,videoId,likeNumber,dislikeNumber)
VALUES('He is the best','2018-8-8','stanko',5,0,0);
INSERT INTO comment(content,commentDate,ownerUserName,videoId,likeNumber,dislikeNumber)
VALUES('He is cool','2018-4-8','darko',5,0,0);
INSERT INTO comment(content,commentDate,ownerUserName,videoId,likeNumber,dislikeNumber)
VALUES('I love avengers','2018-8-8','darko',5,0,0);

CREATE TABLE likeDislike(
id BIGINT AUTO_INCREMENT,
liked BOOLEAN NOT NULL,
likeDate DATE NOT NULL,
ownerUserName VARCHAR(10),
PRIMARY KEY (id),
FOREIGN KEY (ownerUserName) REFERENCES users(userName) ON DELETE RESTRICT
);
INSERT INTO likeDislike(liked,likeDate,ownerUserName)
VALUES(true,'2018-2-5','marko');
INSERT INTO likeDislike(liked,likeDate,ownerUserName)
VALUES(true,'2018-2-5','darko');
INSERT INTO likeDislike(liked,likeDate,ownerUserName)
VALUES(false,'2018-2-5','marko');
INSERT INTO likeDislike(liked,likeDate,ownerUserName)
VALUES(false,'2018-2-5','stanko');
INSERT INTO likeDislike(liked,likeDate,ownerUserName)
VALUES(true,'2018-2-5','darko');

CREATE TABLE likeDislikeVideo(
likeId BIGINT,
videoId BIGINT,
FOREIGN KEY (likeId) REFERENCES likeDislike (id) ON DELETE RESTRICT,
FOREIGN KEY (videoId) REFERENCES video(id) ON DELETE RESTRICT
);
INSERT INTO likeDislikeVideo(likeId,videoId)
VALUES(1,1);
INSERT INTO likeDislikeVideo(likeId,videoId)
VALUES(2,1);

CREATE TABLE likeDislikeComment(
likeId BIGINT,
commentId BIGINT,
FOREIGN KEY (likeId) REFERENCES likeDislike (id) ON DELETE RESTRICT,
FOREIGN KEY (commentId) REFERENCES comment (id) ON DELETE RESTRICT
);
INSERT INTO likeDislikeComment(likeId,commentId)
VALUES(3,1);
INSERT INTO likeDislikeComment(likeId,commentId)
VALUES(4,2);
INSERT INTO likeDislikeComment(likeId,commentId)
VALUES(5,3);