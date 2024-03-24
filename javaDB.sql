-- --------------------------------------------------------
-- Host:                         127.0.0.1
-- Versi server:                 11.3.2-MariaDB - mariadb.org binary distribution
-- OS Server:                    Win64
-- HeidiSQL Versi:               12.6.0.6765
-- --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8 */;
/*!50503 SET NAMES utf8mb4 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;


-- Membuang struktur basisdata untuk simpletaskdb
CREATE DATABASE IF NOT EXISTS `simpletaskdb` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci */;
USE `simpletaskdb`;

-- membuang struktur untuk table simpletaskdb.sensors
CREATE TABLE IF NOT EXISTS `sensors` (
  `id` varchar(50) NOT NULL,
  `title` varchar(255) NOT NULL,
  `unit` varchar(10) NOT NULL,
  `sensorType` varchar(50) NOT NULL,
  `icon` varchar(50) NOT NULL,
  `createdAt` varchar(50) NOT NULL DEFAULT '',
  `value` float NOT NULL DEFAULT 0,
  `node` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- Membuang data untuk tabel simpletaskdb.sensors: ~5 rows (lebih kurang)
INSERT INTO `sensors` (`id`, `title`, `unit`, `sensorType`, `icon`, `createdAt`, `value`, `node`) VALUES
	('5faea98f9b2df8001b92dfad', 'Pressure', 'Pa', 'BME280', 'osem-barometer', '2024-02-05T02:59:12.394Z', 101501, '5faea98f9b2df8001b92dfac'),
	('5faea98f9b2df8001b92dfae', 'Humidity', '%', 'BME280', 'osem-humidity', '2024-02-05T02:59:12.394Z', 73.48, '5faea98f9b2df8001b92dfac'),
	('5faea98f9b2df8001b92dfaf', 'Temperature', '°C', 'BME280', 'osem-thermometer', '2024-02-05T02:59:12.394Z', 29.99, '5faea98f9b2df8001b92dfac'),
	('5faea98f9b2df8001b92dfb0', 'Pollutant Measurement', 'µg/m³', 'SDS 011', 'osem-cloud', '2024-02-05T02:56:40.838Z', 7.63, '5faea98f9b2df8001b92dfac'),
	('5faea98f9b2df8001b92dfb1', 'Newest Pollutant Measurement', 'µg/m³', 'SDS 022', 'osem-cloud', '2024-02-05T02:56:40.838Z', 7.63, '5faea98f9b2df8001b92dfac');

/*!40103 SET TIME_ZONE=IFNULL(@OLD_TIME_ZONE, 'system') */;
/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IFNULL(@OLD_FOREIGN_KEY_CHECKS, 1) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40111 SET SQL_NOTES=IFNULL(@OLD_SQL_NOTES, 1) */;
