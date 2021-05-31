USE `befloral`;

INSERT INTO `migrations` (`name`) VALUES
	('2021_05_14_09_55_14_create_customers_table');

CREATE TABLE `customers` (
  `id` int NOT NULL AUTO_INCREMENT,
  `uid` bigint NOT NULL,
  `firstName` varchar(45) NOT NULL,
  `lastName` varchar(45) NOT NULL,
  `gender` varchar(20) DEFAULT NULL,
  `subscription` tinyint DEFAULT NULL,
  `birthday` date NOT NULL,
  PRIMARY KEY (`id`),
  CONSTRAINT `users` FOREIGN KEY (`uid`) REFERENCES `users` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT 
);

ALTER TABLE `users`
	CHANGE COLUMN `username` `email` VARCHAR(45) NOT NULL AFTER `id`,
	ADD UNIQUE INDEX `email` (`email`);
	
