USE `befloral`;

INSERT INTO `migrations` (`name`) VALUES
	('2021_05_15_21_09_50_create_orders_table');

CREATE TABLE `orders` (
	`id` BIGINT(19) NOT NULL AUTO_INCREMENT,
	`uid` BIGINT(19) NOT NULL,
	`destination` VARCHAR(255) NOT NULL COLLATE,
	`totalProducts` INT(10) NOT NULL,
	`totalPaid` DOUBLE NOT NULL,
	`trackNumber` VARCHAR(45) NULL DEFAULT NULL COLLATE ,
	`gift` TINYINT(3) NULL DEFAULT '0',
	`giftMessage` VARCHAR(255) NULL DEFAULT NULL COLLATE ,
	`createdAtTime` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
	PRIMARY KEY (`id`) USING BTREE,
	INDEX `fk_user` (`uid`) USING BTREE,
	CONSTRAINT `fk_user` FOREIGN KEY (`uid`) REFERENCES `befloral`.`users` (`id`) ON UPDATE RESTRICT ON DELETE RESTRICT
);
	
CREATE TABLE `order_items` (
  `id` bigint AUTO_INCREMENT,
  `oid` bigint NOT NULL,
  `name` varchar(128) NOT NULL,
  `description` text NOT NULL,
  `shortDescription` varchar(45) DEFAULT NULL,
  `price` double NOT NULL,
  `weight` double NOT NULL DEFAULT 255,
  `discount` double NOT NULL DEFAULT 0,
  `quantity` int NOT NULL DEFAULT 0,
  PRIMARY KEY (`id`),
  CONSTRAINT `fk_order` FOREIGN KEY (`oid`) REFERENCES `orders` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT 
);