-- --------------------------------------------------------
-- Host:                         127.0.0.1
-- Versione server:              8.0.23 - MySQL Community Server - GPL
-- S.O. server:                  Win64
-- HeidiSQL Versione:            11.2.0.6213
-- --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8 */;
/*!50503 SET NAMES utf8mb4 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;


-- Dump della struttura del database befloral
CREATE DATABASE IF NOT EXISTS `befloral` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;
USE `befloral`;

-- Dump della struttura di tabella befloral.products
CREATE TABLE IF NOT EXISTS `products` (
  `idproduct` int NOT NULL,
  `name` varchar(45) NOT NULL,
  `description` varchar(45) NOT NULL,
  `shortDescription` varchar(45) DEFAULT NULL,
  `metaDescription` varchar(45) DEFAULT NULL,
  `metaKeyword` varchar(45) DEFAULT NULL,
  `price` double DEFAULT NULL,
  `weight` varchar(45) DEFAULT NULL,
  `available` tinyint DEFAULT NULL,
  `discount` double DEFAULT NULL,
  `onSale` int DEFAULT NULL,
  `quantity` int DEFAULT NULL,
  PRIMARY KEY (`idproduct`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- Dump dei dati della tabella befloral.products: ~0 rows (circa)
/*!40000 ALTER TABLE `products` DISABLE KEYS */;
INSERT IGNORE INTO `products` (`idproduct`, `name`, `description`, `shortDescription`, `metaDescription`, `metaKeyword`, `price`, `weight`, `available`, `discount`, `onSale`, `quantity`) VALUES
	(1, 'Pinguino', 'Pinguino pupazzo bianco/nero', 'Pingu kitty', 'abccba', 'peluche', 20.33, '1.2', 1, 12, 1, 10),
	(2, 'Babbeo', 'Babbeo Babbeo', 'babby', 'babby123', 'babbebbe', 52.23, '1.4', 1, 0, 0, 56),
	(3, 'Maldini', 'Maldini', 'capitano', 'capitano123', 'cap', 10000, '90.0', 1, 0, 0, 1);
/*!40000 ALTER TABLE `products` ENABLE KEYS */;

/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IFNULL(@OLD_FOREIGN_KEY_CHECKS, 1) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40111 SET SQL_NOTES=IFNULL(@OLD_SQL_NOTES, 1) */;
