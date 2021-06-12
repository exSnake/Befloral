ALTER TABLE products
ADD COLUMN `tax` TINYINT UNSIGNED NOT NULL DEFAULT 10
	AFTER `metaKeyword`;
	
ALTER TABLE order_items
ADD COLUMN `tax` TINYINT UNSIGNED NOT NULL DEFAULT 10
	AFTER `shortDescription`;
	
INSERT INTO `migrations` (`name`) VALUES
	('2021_06_15_00_07_add_tax_to_products_table');