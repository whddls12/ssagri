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
-- Table structure for table `board_list`
--

DROP TABLE IF EXISTS `board_list`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `board_list` (
  `board_list_no` bigint NOT NULL AUTO_INCREMENT,
  `create_date` datetime(6) DEFAULT NULL,
  `delete_date` datetime(6) DEFAULT NULL,
  `update_date` datetime(6) DEFAULT NULL,
  `board_list_allow_comment` bit(1) NOT NULL DEFAULT b'1',
  `board_list_content` longtext NOT NULL,
  `board_list_like` int NOT NULL DEFAULT '0',
  `board_list_title` varchar(55) NOT NULL,
  `board_list_view` int NOT NULL DEFAULT '0',
  `board_no` bigint NOT NULL,
  `board_list_writer_no` bigint NOT NULL,
  PRIMARY KEY (`board_list_no`),
  KEY `FKr3vokm2x1p5r35ktrjc6juck1` (`board_no`),
  KEY `FKmwvwwi7ah4lqbtw9yyuxqvwkl` (`board_list_writer_no`),
  CONSTRAINT `FKmwvwwi7ah4lqbtw9yyuxqvwkl` FOREIGN KEY (`board_list_writer_no`) REFERENCES `users` (`user_no`),
  CONSTRAINT `FKr3vokm2x1p5r35ktrjc6juck1` FOREIGN KEY (`board_no`) REFERENCES `board` (`board_no`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `board_list`
--

LOCK TABLES `board_list` WRITE;
/*!40000 ALTER TABLE `board_list` DISABLE KEYS */;
INSERT INTO `board_list` VALUES (1,'2023-10-06 02:49:12.365274',NULL,'2023-10-06 03:02:00.533993',_binary '\0','10월 9알입니다. 저는 참고로 루비입니다.',0,'이번주 월요일에 투썸에서 모각코 할사람',3,5,2);
/*!40000 ALTER TABLE `board_list` ENABLE KEYS */;
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

-- Dump completed on 2023-10-06  3:33:03
