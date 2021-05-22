USE `befloral`;

INSERT INTO `migrations` (`name`) VALUES
	('2021_05_19_20_39_40_edit_users_table');

UPDATE `befloral`.`users` SET `email`='admin@befloral.com', active = 1 WHERE  `id`=1;
UPDATE `befloral`.`users` SET `email`='customer@befloral.com', active = 1 WHERE  `id`=3;
UPDATE `befloral`.`users` SET `email`='employee@befloral.com', active = 1 WHERE  `id`=2;