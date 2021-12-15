CREATE DATABASE  IF NOT EXISTS `travelagency_test` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;
USE `travelagency_test`;
-- MySQL dump 10.13  Distrib 8.0.21, for Win64 (x86_64)
--
-- Host: localhost    Database: travelagency
-- ------------------------------------------------------
-- Server version	8.0.21

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `clients`
--

DROP TABLE IF EXISTS `clients`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `clients` (
  `id` int NOT NULL,
  `meno` varchar(30) NOT NULL,
  `priezvisko` varchar(30) NOT NULL,
  `dat_nar` date NOT NULL,
  `adressa` varchar(30) NOT NULL,
  `cislo` varchar(30) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `clients`
--

LOCK TABLES `clients` WRITE;
/*!40000 ALTER TABLE `clients` DISABLE KEYS */;
INSERT INTO `clients` VALUES (1,'Alex','Smith','1989-02-03','New-York','87685967'),(2,'Olivia','Hughes','1999-12-01','Paris','57859621'),(3,'Harry','Ross','1988-11-18','London','89075432'),(4,'Jack','Foster','2000-10-17','Moscow','60943217'),(5,'Mia','Gray','1975-09-13','Dubai','35671784'),(6,'Alfie ','James','1999-08-23','Tokyo','18543286'),(7,'Poppy','Cox','1997-07-08',' Los Angeles','18722450'),(8,'Isla','Ramos','1981-06-15','Barcelona','41884381'),(9,'Noah','Nelson','1976-05-25','Madrid','81231267'),(10,'Jacob','Scott','2002-04-30','Rome','15838657');
/*!40000 ALTER TABLE `clients` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `druh_jedla`
--

DROP TABLE IF EXISTS `druh_jedla`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `druh_jedla` (
  `id` int NOT NULL,
  `type` varchar(30) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `druh_jedla`
--

LOCK TABLES `druh_jedla` WRITE;
/*!40000 ALTER TABLE `druh_jedla` DISABLE KEYS */;
INSERT INTO `druh_jedla` VALUES (1,'Without food'),(2,'Breakfast'),(3,'Breakfast and dinner'),(4,'All inclusive'),(5,'Ultra all inclusive');
/*!40000 ALTER TABLE `druh_jedla` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `hotel`
--

DROP TABLE IF EXISTS `hotel`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `hotel` (
  `id` int NOT NULL,
  `hotel_name` varchar(30) NOT NULL,
  `stars` int NOT NULL,
  `type_umiestnenia` int NOT NULL,
  `price` float NOT NULL,
  `krajina` varchar(30) DEFAULT NULL,
  `mesto` varchar(30) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `f_type_umiest` (`type_umiestnenia`),
  CONSTRAINT `f_type_umiest` FOREIGN KEY (`type_umiestnenia`) REFERENCES `type_umiestnenia` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `hotel`
--

LOCK TABLES `hotel` WRITE;
/*!40000 ALTER TABLE `hotel` DISABLE KEYS */;
INSERT INTO `hotel` VALUES (1,'Kandolhu Maldives',4,8,456,'Maldives','North Ari'),(2,'Belmond Hotel Caruso',3,4,234,'Italy','Amalfi Coast'),(3,'Cotton House Hotel',4,7,170,'Spain','Barcelona'),(4,'Hotel du Cap-Eden-Roc',5,6,400,'France','Paris'),(5,'Four Seasons ',5,3,294,'Hungary','Budapest'),(6,'Ballyfin',2,1,60,'Ireland','County Laois'),(7,'Qualia',3,3,700,'Australia','Whitsundays'),(8,'Alila Villas Uluwatu',5,5,536,'Indonesia','Uluwatu'),(9,'Inkaterra La Casona',2,4,145,'Peru','Cusco'),(10,'Amangiri',5,3,2456,'United States','Utah');
/*!40000 ALTER TABLE `hotel` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `predaj`
--

DROP TABLE IF EXISTS `predaj`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `predaj` (
  `id` int NOT NULL,
  `client` int NOT NULL,
  `date` date NOT NULL,
  `price` float NOT NULL,
  `tour` int NOT NULL,
  PRIMARY KEY (`id`),
  KEY `f_client` (`client`),
  KEY `f_tour` (`tour`),
  CONSTRAINT `f_client` FOREIGN KEY (`client`) REFERENCES `clients` (`id`),
  CONSTRAINT `f_tour` FOREIGN KEY (`tour`) REFERENCES `tour` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `predaj`
--

LOCK TABLES `predaj` WRITE;
/*!40000 ALTER TABLE `predaj` DISABLE KEYS */;
INSERT INTO `predaj` VALUES (1,1,'2021-12-12',1456.6,4),(2,10,'2021-11-11',800.7,1),(3,10,'2021-12-03',5000,1),(4,9,'2021-12-03',5678.3,14),(5,2,'2021-12-15',1324.6,12),(6,7,'2021-12-17',809.9,13),(7,4,'2021-12-17',1400,8),(8,6,'2021-12-15',2350.6,7),(9,5,'2021-12-13',8790.6,3);
/*!40000 ALTER TABLE `predaj` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tour`
--

DROP TABLE IF EXISTS `tour`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tour` (
  `id` int NOT NULL,
  `type_tour` int NOT NULL,
  `date_begin` date NOT NULL,
  `date_end` date NOT NULL,
  `druh_jedla` int NOT NULL,
  `hotel` int NOT NULL,
  PRIMARY KEY (`id`),
  KEY `f_hotel` (`hotel`),
  KEY `f_druh_jedla` (`druh_jedla`),
  KEY `f_type_tour` (`type_tour`),
  CONSTRAINT `f_druh_jedla` FOREIGN KEY (`druh_jedla`) REFERENCES `druh_jedla` (`id`),
  CONSTRAINT `f_hotel` FOREIGN KEY (`hotel`) REFERENCES `hotel` (`id`),
  CONSTRAINT `f_type_tour` FOREIGN KEY (`type_tour`) REFERENCES `type_tour` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tour`
--

LOCK TABLES `tour` WRITE;
/*!40000 ALTER TABLE `tour` DISABLE KEYS */;
INSERT INTO `tour` VALUES (1,3,'2022-02-03','2022-02-20',1,1),(2,2,'2022-01-01','2022-01-12',2,8),(3,2,'2022-04-04','2022-04-17',2,5),(4,6,'2022-02-03','2022-02-08',5,6),(5,6,'2022-07-23','2022-08-03',5,10),(6,5,'2022-01-13','2022-01-20',4,9),(7,4,'2022-03-27','2022-04-03',3,2),(8,3,'2022-02-13','2022-02-20',3,3),(9,2,'2022-04-02','2022-04-09',1,4),(10,1,'2022-10-04','2022-10-10',1,10),(11,1,'2022-05-05','2022-06-04',2,5),(12,6,'2022-02-17','2022-02-25',3,6),(13,4,'2022-02-13','2022-02-20',4,7),(14,2,'2022-11-13','2022-11-20',4,1),(15,6,'2022-10-05','2022-10-20',5,8);
/*!40000 ALTER TABLE `tour` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `type_tour`
--

DROP TABLE IF EXISTS `type_tour`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `type_tour` (
  `id` int NOT NULL,
  `type` varchar(30) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `type_tour`
--

LOCK TABLES `type_tour` WRITE;
/*!40000 ALTER TABLE `type_tour` DISABLE KEYS */;
INSERT INTO `type_tour` VALUES (1,'Shopping'),(2,'Business'),(3,'Relax'),(4,'Educational'),(5,'Sports'),(6,'Beach');
/*!40000 ALTER TABLE `type_tour` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `type_umiestnenia`
--

DROP TABLE IF EXISTS `type_umiestnenia`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `type_umiestnenia` (
  `id` int NOT NULL,
  `type_umiest` varchar(30) DEFAULT NULL,
  `balkon` tinyint(1) DEFAULT NULL,
  `vyhladiakova_izba` tinyint(1) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `type_umiestnenia`
--

LOCK TABLES `type_umiestnenia` WRITE;
/*!40000 ALTER TABLE `type_umiestnenia` DISABLE KEYS */;
INSERT INTO `type_umiestnenia` VALUES (1,'Single',0,0),(2,'Double',1,0),(3,'Single',0,1),(4,'Triple',1,0),(5,'Suite',0,0),(6,'President suite',1,1),(7,'Apartments',1,0),(8,'Villa',1,1);
/*!40000 ALTER TABLE `type_umiestnenia` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2021-12-08  0:45:30
