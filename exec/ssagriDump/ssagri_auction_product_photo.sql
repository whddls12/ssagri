CREATE DATABASE  IF NOT EXISTS `ssagri` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;
USE `ssagri`;
-- MySQL dump 10.13  Distrib 8.0.33, for Win64 (x86_64)
--
-- Host: ssagri2.cvcrhp5wekli.ap-northeast-2.rds.amazonaws.com    Database: ssagri
-- ------------------------------------------------------
-- Server version	8.0.33

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
SET @MYSQLDUMP_TEMP_LOG_BIN = @@SESSION.SQL_LOG_BIN;
SET @@SESSION.SQL_LOG_BIN= 0;

--
-- GTID state at the beginning of the backup 
--

SET @@GLOBAL.GTID_PURGED=/*!80000 '+'*/ '';

--
-- Table structure for table `auction_product_photo`
--

DROP TABLE IF EXISTS `auction_product_photo`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `auction_product_photo` (
  `auction_product_photo_no` bigint NOT NULL AUTO_INCREMENT,
  `auction_product_photo_link` varchar(1000) NOT NULL,
  `auction_product_no` bigint NOT NULL,
  PRIMARY KEY (`auction_product_photo_no`),
  KEY `FKafw0emrpx06ohhl988cfbosxm` (`auction_product_no`),
  CONSTRAINT `FKafw0emrpx06ohhl988cfbosxm` FOREIGN KEY (`auction_product_no`) REFERENCES `auction_product` (`auction_product_no`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `auction_product_photo`
--

LOCK TABLES `auction_product_photo` WRITE;
/*!40000 ALTER TABLE `auction_product_photo` DISABLE KEYS */;
INSERT INTO `auction_product_photo` VALUES (2,'https://learners-high.s3.ap-northeast-2.amazonaws.com/auction/2/172c0f8e-63fc-4b73-87c7-0ab2dbac551a_A4RES201101X8JCN.jpg',2),(3,'https://learners-high.s3.ap-northeast-2.amazonaws.com/auction/3/56187d4e-4bf3-4b6c-9a95-327e419d6b8d_엘지 모니터.jpg',3),(4,'https://learners-high.s3.ap-northeast-2.amazonaws.com/auction/4/2315e59a-ee49-4d87-8272-c3a5e903c681_핑크빈 마우스.jpg',4),(5,'https://learners-high.s3.ap-northeast-2.amazonaws.com/auction/5/1da72e6b-b8c2-4c9d-bae9-4aada5145b63_미니언 마우스.png',5),(6,'https://learners-high.s3.ap-northeast-2.amazonaws.com/auction/6/55383256-236f-4033-a825-cec245d2b025_맥북.jpg',6),(7,'https://learners-high.s3.ap-northeast-2.amazonaws.com/auction/7/ac69f718-5ba8-4b97-a121-32c04d194265_palmrest.JPG',7),(8,'https://learners-high.s3.ap-northeast-2.amazonaws.com/auction/8/512197e0-0baf-48b1-9869-acac99bf0704_배추.png',8);
/*!40000 ALTER TABLE `auction_product_photo` ENABLE KEYS */;
UNLOCK TABLES;
SET @@SESSION.SQL_LOG_BIN = @MYSQLDUMP_TEMP_LOG_BIN;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2023-10-06  3:32:56
