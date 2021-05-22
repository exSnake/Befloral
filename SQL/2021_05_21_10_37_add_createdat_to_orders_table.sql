USE `befloral`;

INSERT INTO `migrations` (`name`) VALUES
	('2021_05_21_10_37_add_createdat_to_orders_table');

ALTER TABLE orders
ADD COLUMN `createdAt` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP
	AFTER `giftMessage`;