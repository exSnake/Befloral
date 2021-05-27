CREATE TABLE `category` (
	`id` INT(10) NOT NULL AUTO_INCREMENT,
	`name` VARCHAR(50) NOT NULL DEFAULT 'NOCATEGORY' COLLATE 'utf8_general_ci',
	`description` VARCHAR(200) NOT NULL DEFAULT 'NODESCRIPTION' COLLATE 'utf8_general_ci',
	`metaKeywords` VARCHAR(200) NOT NULL COLLATE 'utf8_general_ci',
	`metaTitle` VARCHAR(50) NOT NULL COLLATE 'utf8_general_ci',
	`metaDescription` VARCHAR(200) NOT NULL COLLATE 'utf8_general_ci',
	PRIMARY KEY (`id`) USING BTREE
)
COLLATE='utf8_general_ci'
ENGINE=InnoDB
;



CREATE TABLE `belongs` (
	`pid` BIGINT(19) NOT NULL DEFAULT '0',
	`cid` INT(10) NOT NULL,
	INDEX `pid` (`pid`) USING BTREE,
	INDEX `cid` (`cid`) USING BTREE,
	CONSTRAINT `FKcategory` FOREIGN KEY (`cid`) REFERENCES `befloral`.`category` (`id`) ON UPDATE CASCADE ON DELETE CASCADE,
	CONSTRAINT `FKproduct` FOREIGN KEY (`pid`) REFERENCES `befloral`.`products` (`id`) ON UPDATE CASCADE ON DELETE CASCADE
)
COLLATE='utf8_general_ci'
ENGINE=InnoDB
;


INSERT INTO `category` (`id`, `name`, `description`, `metaKeywords`, `metaTitle`, `metaDescription`) VALUES
	(1, 'Flowers', 'A flower is the reproductive part of flowering plants.', 'flowers bloom blossom floweret floret best finest top pick choice choicest prime cream prize pearl gem', 'Flowers Bloom', 'Flowers');
/*!40000 ALTER TABLE `category` ENABLE KEYS */;


/*!40000 ALTER TABLE `belongs` DISABLE KEYS */;
INSERT INTO `belongs` (`pid`, `cid`) VALUES
	(1, 1),
	(2, 1),
	(3, 1),
	(3, 1),
	(4, 1),
	(5, 1),
	(6, 1),
	(7, 1),
	(8, 1),
	(9, 1);
/*!40000 ALTER TABLE `belongs` ENABLE KEYS */;













