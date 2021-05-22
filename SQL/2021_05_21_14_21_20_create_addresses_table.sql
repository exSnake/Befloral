USE `befloral`;

INSERT INTO `migrations` (`name`) VALUES
	('2021_05_21_14_21_20_create_addresses_table');
	
ALTER TABLE `customers`
	CHANGE COLUMN `id` `id` BIGINT NOT NULL FIRST;

CREATE TABLE `addresses` (
	`id` bigint AUTO_INCREMENT,
	`cid` bigint NOT NULL,
	`firstName` varchar(45) NOT NULL,
	`lastName` varchar(45) NOT NULL,
	`address` varchar(255) NOT NULL,
	`postalCode` varchar(5) NOT NULL,
	`city` varchar(60) NOT NULL,
	`province` varchar(2) NOT NULL,
	`phone` varchar(20) NULL,
	`info` varchar(255) NULL,
	`alias` varchar(45) NULL,
	`preferred`tinyint NULL DEFAULT NULL,
	PRIMARY KEY (`id`),
	UNIQUE INDEX `cid_preferred` (`cid`, `preferred`),
	CONSTRAINT `FK__customers` FOREIGN KEY (`cid`) REFERENCES `customers` (`id`) ON DELETE CASCADE ON UPDATE CASCADE 
);


