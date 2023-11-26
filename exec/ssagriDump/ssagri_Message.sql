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
-- Table structure for table `Message`
--

DROP TABLE IF EXISTS `Message`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `Message` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `content` varchar(255) DEFAULT NULL,
  `receiver_nick_name` varchar(255) DEFAULT NULL,
  `receiver_no` bigint DEFAULT NULL,
  `room_no` bigint DEFAULT NULL,
  `sender_nick_name` varchar(255) DEFAULT NULL,
  `sender_no` bigint DEFAULT NULL,
  `time` datetime(6) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=33 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Message`
--

LOCK TABLES `Message` WRITE;
/*!40000 ALTER TABLE `Message` DISABLE KEYS */;
INSERT INTO `Message` VALUES (23,'냐덜ㄴㄷㄴㄹ\nㄴㄷㄹㄴㄷㄹㄴㄷㄹ\nㄴㅁㄷㄻㄴㄹㄷㄷㄴㄹ\nㄴㄷㄹㄴㄷㄹㄴㄷㄹㄴㄹㄴㄷㄹㄴㄷㄹ','코딩왕',6,9,'취업하곳피다',4,'2023-10-06 02:53:24.642430'),(24,'안녕','www',9,10,'www',9,'2023-10-06 03:02:42.290452'),(25,'ㅎㅇ','www',9,10,'www',9,'2023-10-06 03:02:57.499460'),(26,'ㅎㅇ','www',9,10,'www',9,'2023-10-06 03:05:19.451795'),(27,'ㅎㅇ','www',9,10,'www',9,'2023-10-06 03:05:22.802923'),(28,'ㅎㅇ','www',9,10,'www',9,'2023-10-06 03:05:25.730825'),(29,'안녕','지배리',3,12,'www',9,'2023-10-06 03:06:18.806785'),(30,'안녕','지배리',3,12,'www',9,'2023-10-06 03:06:25.650104'),(31,'엔터가 안쳐지네','지배리',3,12,'www',9,'2023-10-06 03:06:31.109543'),(32,'안녕하세요! 맥북 사고 싶어서 연락드렸어요','지배리',3,13,'취업하곳피다',4,'2023-10-06 03:31:40.212025');
/*!40000 ALTER TABLE `Message` ENABLE KEYS */;
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

-- Dump completed on 2023-10-06  3:33:08
