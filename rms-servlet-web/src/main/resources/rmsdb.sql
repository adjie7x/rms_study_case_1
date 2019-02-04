-- --------------------------------------------------------
-- Host:                         127.0.0.1
-- Server version:               10.1.37-MariaDB - mariadb.org binary distribution
-- Server OS:                    Win32
-- HeidiSQL Version:             10.1.0.5464
-- --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8 */;
/*!50503 SET NAMES utf8mb4 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;


-- Dumping database structure for rmsdb
CREATE DATABASE IF NOT EXISTS `rmsdb` /*!40100 DEFAULT CHARACTER SET latin1 */;
USE `rmsdb`;

-- Dumping structure for table rmsdb.customer
CREATE TABLE IF NOT EXISTS `customer` (
  `id` bigint(11) NOT NULL AUTO_INCREMENT,
  `firstname` varchar(50) NOT NULL,
  `lastname` varchar(50) NOT NULL,
  `email` varchar(50) NOT NULL,
  `mobile` varchar(50) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=latin1;

-- Dumping data for table rmsdb.customer: ~3 rows (approximately)
/*!40000 ALTER TABLE `customer` DISABLE KEYS */;
INSERT INTO `customer` (`id`, `firstname`, `lastname`, `email`, `mobile`) VALUES
	(1, 'aji', 'mulyadi7c', 'aji@gmail.com', '085742111222'),
	(7, 'aji', 'reno', 'adjie.mulyadi7x@gmail.com', '085754655');
/*!40000 ALTER TABLE `customer` ENABLE KEYS */;

-- Dumping structure for table rmsdb.menu
CREATE TABLE IF NOT EXISTS `menu` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `title` varchar(50) NOT NULL,
  `link` text NOT NULL,
  `desc` text,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=latin1;

-- Dumping data for table rmsdb.menu: ~2 rows (approximately)
/*!40000 ALTER TABLE `menu` DISABLE KEYS */;
INSERT INTO `menu` (`id`, `title`, `link`, `desc`) VALUES
	(1, 'employee link 1', '/customers/*', NULL),
	(2, 'manager link 1', '/users/*', NULL);
/*!40000 ALTER TABLE `menu` ENABLE KEYS */;

-- Dumping structure for table rmsdb.role
CREATE TABLE IF NOT EXISTS `role` (
  `role_id` varchar(10) NOT NULL,
  `role_name` varchar(50) NOT NULL,
  PRIMARY KEY (`role_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- Dumping data for table rmsdb.role: ~2 rows (approximately)
/*!40000 ALTER TABLE `role` DISABLE KEYS */;
INSERT INTO `role` (`role_id`, `role_name`) VALUES
	('EMPLOYEE', 'EMPLOYEE'),
	('MANAGER', 'MANAGER');
/*!40000 ALTER TABLE `role` ENABLE KEYS */;

-- Dumping structure for table rmsdb.role_menu
CREATE TABLE IF NOT EXISTS `role_menu` (
  `role_id` varchar(50) NOT NULL,
  `menu_id` bigint(20) NOT NULL,
  PRIMARY KEY (`role_id`,`menu_id`),
  KEY `FK_role_menu_menu` (`menu_id`),
  CONSTRAINT `FK_role_menu_menu` FOREIGN KEY (`menu_id`) REFERENCES `menu` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `FK_role_menu_role` FOREIGN KEY (`role_id`) REFERENCES `role` (`role_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- Dumping data for table rmsdb.role_menu: ~3 rows (approximately)
/*!40000 ALTER TABLE `role_menu` DISABLE KEYS */;
INSERT INTO `role_menu` (`role_id`, `menu_id`) VALUES
	('EMPLOYEE', 1),
	('MANAGER', 1),
	('MANAGER', 2);
/*!40000 ALTER TABLE `role_menu` ENABLE KEYS */;

-- Dumping structure for table rmsdb.user
CREATE TABLE IF NOT EXISTS `user` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `user_name` varchar(100) NOT NULL,
  `password` varchar(100) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `user_name` (`user_name`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=latin1;

-- Dumping data for table rmsdb.user: ~2 rows (approximately)
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` (`id`, `user_name`, `password`) VALUES
	(5, 'aji', 'ajirules'),
	(6, 'emp1', 'emprules');
/*!40000 ALTER TABLE `user` ENABLE KEYS */;

-- Dumping structure for table rmsdb.user_role
CREATE TABLE IF NOT EXISTS `user_role` (
  `user_name` varchar(50) NOT NULL,
  `role_id` varchar(50) NOT NULL,
  PRIMARY KEY (`user_name`,`role_id`),
  KEY `FK_user_role_role` (`role_id`),
  CONSTRAINT `FK_user_role_role` FOREIGN KEY (`role_id`) REFERENCES `role` (`role_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `FK_user_role_user` FOREIGN KEY (`user_name`) REFERENCES `user` (`user_name`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- Dumping data for table rmsdb.user_role: ~0 rows (approximately)
/*!40000 ALTER TABLE `user_role` DISABLE KEYS */;
INSERT INTO `user_role` (`user_name`, `role_id`) VALUES
	('aji', 'EMPLOYEE'),
	('aji', 'MANAGER'),
	('emp1', 'EMPLOYEE');
/*!40000 ALTER TABLE `user_role` ENABLE KEYS */;

/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IF(@OLD_FOREIGN_KEY_CHECKS IS NULL, 1, @OLD_FOREIGN_KEY_CHECKS) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
