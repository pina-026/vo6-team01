CREATE DATABASE IF NOT EXISTS `winter`;

CREATE TABLE IF NOT EXISTS `winter`.`users` (
	id INT AUTO_INCREMENT,
    	username VARCHAR(255),
    	password VARCHAR(255),
    	PRIMARY KEY(id)
);
