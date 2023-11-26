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
-- Table structure for table `board`
--

DROP TABLE IF EXISTS `board`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `board` (
  `board_no` bigint NOT NULL AUTO_INCREMENT,
  `create_date` datetime(6) DEFAULT NULL,
  `delete_date` datetime(6) DEFAULT NULL,
  `update_date` datetime(6) DEFAULT NULL,
  `board_allow_delete` bit(1) NOT NULL DEFAULT b'1',
  `board_click` int DEFAULT NULL,
  `board_color` varchar(255) DEFAULT NULL,
  `board_life` datetime(6) NOT NULL,
  `board_show_name` varchar(255) NOT NULL,
  `board_title` varchar(255) NOT NULL,
  `board_create_user_no` bigint NOT NULL,
  PRIMARY KEY (`board_no`),
  KEY `FKsnv5n8yx4yutinh12jh42rea1` (`board_create_user_no`),
  CONSTRAINT `FKsnv5n8yx4yutinh12jh42rea1` FOREIGN KEY (`board_create_user_no`) REFERENCES `users` (`user_no`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `board`
--

LOCK TABLES `board` WRITE;
/*!40000 ALTER TABLE `board` DISABLE KEYS */;
INSERT INTO `board` VALUES (1,'2023-10-05 23:58:09.528553',NULL,'2023-10-05 23:58:09.528553',_binary '\0',0,NULL,'2023-10-12 23:58:09.523653','실명','대전2반 cs스터디',2),(2,'2023-10-05 23:59:34.620400',NULL,'2023-10-05 23:59:34.620400',_binary '\0',100,NULL,'2023-10-12 23:59:34.620178','실명','자소서 나눔 ',2),(3,'2023-10-06 02:46:38.948152',NULL,'2023-10-06 02:46:38.948152',_binary '\0',0,NULL,'2023-10-13 02:46:38.941924','실명','깊티 게시판',2),(4,'2023-10-06 02:47:10.843217',NULL,'2023-10-06 02:47:10.843217',_binary '\0',50,NULL,'2023-10-13 02:47:10.843009','실명','유온인',2),(5,'2023-10-06 02:48:12.872964',NULL,'2023-10-06 02:48:12.872964',_binary '\0',30,NULL,'2023-10-13 02:48:12.872496','실명','모각코 게시판',2);
/*!40000 ALTER TABLE `board` ENABLE KEYS */;
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

-- Dump completed on 2023-10-06  3:32:57
