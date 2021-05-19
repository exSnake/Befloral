-- --------------------------------------------------------
-- Host:                         127.0.0.1
-- Versione server:              8.0.20 - MySQL Community Server - GPL
-- S.O. server:                  Linux
-- HeidiSQL Versione:            11.0.0.5919
-- --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8 */;
/*!50503 SET NAMES utf8mb4 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;


-- Dump della struttura del database befloral
CREATE DATABASE IF NOT EXISTS `befloral` /*!40100 DEFAULT CHARACTER SET utf8 */ /*!80016 DEFAULT ENCRYPTION='N' */;
USE `befloral`;

-- Dump della struttura di tabella befloral.migrations
CREATE TABLE `migrations` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `name` varchar(255) NOT NULL DEFAULT '0',
  `created_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
);

-- Dump della struttura di tabella befloral.products
CREATE TABLE `products` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `name` varchar(128) NOT NULL,
  `description` text NOT NULL,
  `shortDescription` varchar(45) DEFAULT NULL,
  `metaDescription` varchar(45) DEFAULT NULL,
  `metaKeyword` varchar(45) DEFAULT NULL,
  `price` double NOT NULL,
  `weight` double NOT NULL DEFAULT 255,
  `available` tinyint NOT NULL DEFAULT 0,
  `discount` double NOT NULL DEFAULT 0,
  `onSale` int NOT NULL DEFAULT 0,
  `quantity` int NOT NULL DEFAULT 0,
  PRIMARY KEY (`id`)
);


-- Dump dei dati della tabella befloral.migrations: 1 row
INSERT INTO `migrations` (`name`) VALUES
	('2021_04_05_12_01_05_create_products_table');

-- Dump dei dati della tabella befloral.products: 4 rows 
INSERT INTO `products` (`name`, `description`, `shortDescription`, `metaDescription`, `metaKeyword`, `price`, `weight`, `available`, `discount`, `onSale`, `quantity`) VALUES
	('Bouquet di Anturium', 'Bouquet di Anturium e Azalee', 'Anturium', 'bouquet_anturium', 'anturium', 20.33, 1.2, 1, 12, 1, 10),
	('Bouquet di Rose', 'Bouquet di Rose Rosse bulgare', 'Boquette Rose', 'bouquet_rose', 'boquette', 19.3, 1.6, 1, 0, 1, 9),
	('Margherita', 'Margherita bianca con petali rossi floreali', 'Margerita', 'margerita', 'margherita', 8.5, 0.5, 1, 0, 1, 120),
	('Tulipano Giallo', 'Tulipano giallo himalaiano', 'Tulipano', 'tulipano', 'tulipano', 9.25, 0.3, 1, 0, 1, 50),
	('Orchidea Gialla', 'Orchidea gialla in vaso', 'Orchidea', 'orchidea', 'orchidea', 29.90, 2.3, 1, 0, 0, 50),
	('Orchidea Rosa', 'Orchidea rosa in vaso', 'Orchidea', 'orchidea', 'orchidea', 29.90, 2.3, 1, 0, 0, 50),
	('Orchidea Verde', 'Orchidea verde in vaso', 'Orchidea', 'orchidea', 'orchidea', 29.90, 2.3, 1, 0, 0, 50),
	('Rosa rossa', 'Rosa rossa con gambo di 80cm', 'Rosa rossa', 'rosa', 'rosa', 3.90, 0.5, 1, 0, 0, 50),
	('Bouquet di Gerbera', 'Bouquet di Gerbera di colori misti', 'Bouquet Gerbera', 'bouquet_gerbera', 'gerbera', 20.90, 0.5, 1, 0, 0, 50),
	('Vaso Terra Cotta', 'Vaso di terra cotta rosso', 'Vaso', 'vaso', 'varo', 12.4, 0.5, 1, 0, 1, 12);

/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IF(@OLD_FOREIGN_KEY_CHECKS IS NULL, 1, @OLD_FOREIGN_KEY_CHECKS) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
