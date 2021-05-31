CREATE TABLE `categories` (
	`id` BIGINT NOT NULL AUTO_INCREMENT,
	`name` VARCHAR(50) NOT NULL DEFAULT 'NOCATEGORY' ,
	`description` VARCHAR(400) NOT NULL DEFAULT 'NODESCRIPTION' ,
	`metaKeywords` VARCHAR(200) NOT NULL ,
	PRIMARY KEY (`id`) 
);


CREATE TABLE `categories_products` (
	`pid` BIGINT  NOT NULL,
	`cid` BIGINT  NOT NULL,
	PRIMARY KEY (`pid` ,`cid`) ,
	CONSTRAINT `FKcategory` FOREIGN KEY (`cid`) REFERENCES `befloral`.`categories` (`id`) ON UPDATE CASCADE ON DELETE CASCADE,
	CONSTRAINT `FKproduct` FOREIGN KEY (`pid`) REFERENCES `befloral`.`products` (`id`) ON UPDATE CASCADE ON DELETE CASCADE
);


INSERT INTO `categories` (`name`, `description`, `metaKeywords`) VALUES
	('Flowers', 'A flower is the reproductive part of flowering plants.', 'flowers bloom blossom floweret floret best finest top pick choice choicest prime cream prize pearl gem'),
	('Seeds', 'A seed is an embryonic plant enclosed in a protective outer covering. The formation of the seed is part of the process of reproduction in seed plants, the spermatophytes, including the gymnosperm and angiosperm plants. ', 'seed plant gymnosperm plant plants seeds ');





INSERT INTO `categories_products` (`pid`, `cid`) VALUES
	(1, 1),
	(2, 1),
	(3, 1),
	(4, 1),
	(5, 1),
	(6, 1),
	(7, 1),
	(8, 1),
	(9, 1),
    (8,2);













