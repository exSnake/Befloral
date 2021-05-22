USE `befloral`;

ALTER TABLE `addresses`
	DROP FOREIGN KEY `FK__customers`;

DROP TABLE `customers`;

ALTER TABLE users
ADD COLUMN `firstName` varchar(45) NOT NULL AFTER `id`,
ADD COLUMN `lastName` varchar(45) NOT NULL AFTER `firstName`,
ADD COLUMN `gender` varchar(20) NOT NULL DEFAULT "Undefined" AFTER `lastName`,
ADD COLUMN `subscription` tinyint NOT NULL DEFAULT 0 `gender`,
ADD COLUMN `birthday` date NOT NULL after `subscription`;

ALTER TABLE `addresses`
	CHANGE COLUMN `cid` `uid` BIGINT(19) NOT NULL AFTER `id`,
	DROP INDEX `cid_preferred`,
	ADD UNIQUE INDEX `cid_preferred` (`uid`, `preferred`) USING BTREE;

ALTER TABLE `addresses`
	ADD CONSTRAINT `FK_addresses_users` FOREIGN KEY (`uid`) REFERENCES `users` (`id`) ON UPDATE CASCADE ON DELETE CASCADE;


INSERT INTO `migrations` (`name`) VALUES
	('2021_05_22_14_10_01_remove_customers_table');