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
-- Table structure for table `used_product_photo`
--

DROP TABLE IF EXISTS `used_product_photo`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `used_product_photo` (
  `used_product_photo_no` bigint NOT NULL AUTO_INCREMENT,
  `used_product_photo_link` varchar(255) NOT NULL,
  `used_product_photo_type` varchar(255) NOT NULL,
  `used_product_no` bigint NOT NULL,
  PRIMARY KEY (`used_product_photo_no`),
  KEY `FKhnfx5u5w9qs5j78iad55sb5o` (`used_product_no`),
  CONSTRAINT `FKhnfx5u5w9qs5j78iad55sb5o` FOREIGN KEY (`used_product_no`) REFERENCES `used_product` (`used_product_no`)
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `used_product_photo`
--

LOCK TABLES `used_product_photo` WRITE;
/*!40000 ALTER TABLE `used_product_photo` DISABLE KEYS */;
INSERT INTO `used_product_photo` VALUES (1,'https://learners-high.s3.ap-northeast-2.amazonaws.com//used/f2643170-c36c-4183-bb0d-01b4d9b8230e_삼성 모니터.jpg','MAIN',1),(2,'https://learners-high.s3.ap-northeast-2.amazonaws.com//used/20756382-c60b-4d0c-86b1-5fb8d98b3366_삼성 모니터.jpg','MAIN',2),(3,'https://learners-high.s3.ap-northeast-2.amazonaws.com//used/4d56a095-536c-41e6-955c-551b34b512f9_토토로 패드.jpg','MAIN',3),(4,'https://learners-high.s3.ap-northeast-2.amazonaws.com//used/025d7039-d13c-4132-8049-3042303de99f_엘지 모니터.jpg','MAIN',4),(5,'https://learners-high.s3.ap-northeast-2.amazonaws.com//used/1b7c8e47-e992-46d5-9f03-a76080ce03db_엘지 모니터.jpg','MAIN',5),(6,'https://learners-high.s3.ap-northeast-2.amazonaws.com//used/93b14901-95f3-495c-881a-956574b16572_모코코 패드.jpg','MAIN',6),(7,'https://learners-high.s3.ap-northeast-2.amazonaws.com//used/30ea05cc-8538-411d-9448-24fd12d9197f_미니언 키보드.jpg','MAIN',7),(8,'https://learners-high.s3.ap-northeast-2.amazonaws.com//used/d901378b-4fc3-4494-8e95-d4c9d1ef3299_맥북.jpg','MAIN',8),(9,'https://learners-high.s3.ap-northeast-2.amazonaws.com//used/281e0dfa-130b-455f-a94d-7b971a3e3564_물고기 슬리퍼.jpg','MAIN',9),(10,'https://learners-high.s3.ap-northeast-2.amazonaws.com//used/0d0cb75e-099a-429a-8100-f95d1d57081d_마우스1.PNG','MAIN',10),(11,'https://learners-high.s3.ap-northeast-2.amazonaws.com//used/f8a6e309-48e5-49a8-a07b-b912476dfe2e_키보드1.PNG','MAIN',11),(12,'https://learners-high.s3.ap-northeast-2.amazonaws.com//used/ad4387ca-62dc-45ff-aad2-8bef15a559c1_오메가 SA 론진.PNG','MAIN',12),(13,'https://learners-high.s3.ap-northeast-2.amazonaws.com//used/f3a2fbb9-b9df-49c8-b9e9-e6ec71a7bba4_모니터.PNG','MAIN',13),(14,'https://learners-high.s3.ap-northeast-2.amazonaws.com//used/07d2fd3c-fe74-4dc5-9c39-d618be5beb7d_삼성전자 SPA-KNG1CU 기계식 키보드.PNG','MAIN',14),(15,'https://learners-high.s3.ap-northeast-2.amazonaws.com//used/2bc62419-2451-4bc1-9f4a-0663c3aff59a_마우스4.PNG','MAIN',15);
/*!40000 ALTER TABLE `used_product_photo` ENABLE KEYS */;
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

-- Dump completed on 2023-10-06  3:33:07
