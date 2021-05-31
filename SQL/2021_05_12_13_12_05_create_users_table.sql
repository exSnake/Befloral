USE `befloral`;

CREATE TABLE `users` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `username` varchar(45) NOT NULL,
  `password` varchar(45) NOT NULL,
  `role` varchar(45) NOT NULL,
  `active` tinyint NOT NULL DEFAULT 0,
  PRIMARY KEY (`id`)
);

INSERT INTO `users` (`username`, `password`, `role`, `active`) VALUES
	('admin', 'admin', 'administrator', 0),
	('employee', 'employee', 'employee', 0),
	('customer', 'customer', 'customer', 0);

INSERT INTO `migrations` (`name`) VALUES
	('2021_05_12_13_12_05_create_users_table');