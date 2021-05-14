INSERT INTO `migrations` (`name`) VALUES
	('2021_05_15_create_orders_table');

CREATE TABLE `orders` (
	`id` bigint AUTO_INCREMENT,
	`uid` bigint NOT NULL,
	`destination` varchar(255) NOT NULL,
	`totalProducts` int NOT NULL,
	`totalPaid` double NOT NULL,
	`trackNumber` varchar(45) NULL,
	`gift` tinyint DEFAULT 0,
	`giftMessage` varchar(255) NULL,
	PRIMARY KEY (`id`),
	CONSTRAINT `fk_user` FOREIGN KEY (`uid`) REFERENCES `users` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT 
);

INSERT INTO `migrations` (`name`) VALUES
	('2021_05_15_create_order_items_table');
	
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