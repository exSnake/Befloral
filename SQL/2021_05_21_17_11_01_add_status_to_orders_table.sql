USE `befloral`;

INSERT INTO `migrations` (`name`) VALUES
	('2021_05_21_17_11_add_status_to_orders_table');

ALTER TABLE orders
ADD COLUMN `status` VARCHAR(255) NULL
	AFTER `trackNumber`;