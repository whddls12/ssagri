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
-- Table structure for table `users`
--

DROP TABLE IF EXISTS `users`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `users` (
  `user_no` bigint NOT NULL AUTO_INCREMENT,
  `user_email` varchar(40) DEFAULT NULL,
  `user_nickname` varchar(40) NOT NULL,
  `user_number` int NOT NULL,
  `user_password` varchar(20) NOT NULL,
  `user_point` int DEFAULT NULL,
  `user_profile` varchar(1000) DEFAULT NULL,
  `user_region` varchar(255) NOT NULL,
  `user_temper` int DEFAULT NULL,
  `user_create_date` datetime(6) NOT NULL,
  `user_create_type` varchar(255) NOT NULL,
  `user_delete_date` datetime(6) DEFAULT NULL,
  PRIMARY KEY (`user_no`),
  UNIQUE KEY `UK_r7e3xe2uqjaef83lw9tpmc8ql` (`user_nickname`),
  UNIQUE KEY `UK_33uo7vet9c79ydfuwg1w848f` (`user_email`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `users`
--

LOCK TABLES `users` WRITE;
/*!40000 ALTER TABLE `users` DISABLE KEYS */;
INSERT INTO `users` VALUES (1,'0909top@naver.com','종인',1,'',0,'http://k.kakaocdn.net/dn/xksFC/btr7hS2EhOw/ZhuaUwKYktcZo9wC8LC8b0/img_640x640.jpg','DAEJEON',0,'2023-10-05 22:53:15.606000','KAKAO',NULL),(2,'dbrhdwn123@naver.com','해방구리',9,'smy6082^^**',0,'https://img.khan.co.kr/news/2023/01/02/news-p.v1.20230102.1f95577a65fc42a79ae7f990b39e7c21_P1.png','DAEJEON',0,'2023-10-05 22:54:09.163000','NORMAL',NULL),(3,'tybam@naver.com','지배리',9,'qwer1234!',0,'https://i.namu.wiki/i/fJieaLShQzQDvtkrM5QAgFjk-t_lQZ1zUnlJU_QGSpwPQJIj7egx5IJABblsyYR8j2mvNzjz-R3mIFdW8Oydgg.webp','DAEJEON',0,'2023-10-05 22:57:03.015000','NORMAL',NULL),(4,'ghkdwhddls12@daum.net','취업하곳피다',9,'1q2w3e4r!',0,NULL,'DAEJEON',0,'2023-10-05 23:01:14.601000','NORMAL',NULL),(5,'15gpfk@naver.com','정원',5,'',0,'http://k.kakaocdn.net/dn/bvI4pm/btr0fVLJlMw/1ZxJeADxGDC6kgNEADIufk/img_640x640.jpg','SEOUL',0,'2023-10-05 23:10:19.937000','KAKAO',NULL),(6,'digitalstart123@gmail.com','코딩왕',9,'@wjddnjs1',0,'https://i.pinimg.com/736x/31/a2/2a/31a22a64af438d898967f8bda739bf7a.jpg','SEOUL',0,'2023-10-05 23:55:56.622000','NORMAL',NULL),(7,'handal@kakao.com','나바보아님',9,'smy6082^^**',0,'https://owldictionary.com/wp-content/uploads/2022/04/person-875165_1920-1600x1068.jpg','DAEJEON',0,'2023-10-06 00:38:29.651000','NORMAL',NULL),(8,'money4857@naver.com','임규돈',1,'',0,'http://k.kakaocdn.net/dn/deEbhm/btsp2adi8hh/LqzvXK6qMkWOdWF0He1rqk/img_640x640.jpg','DAEJEON',0,'2023-10-06 01:11:50.733000','KAKAO',NULL),(9,'jjhjjh1159@gmail.com','www',1,'dkfdlwmdnpf12@@',0,NULL,'DAEJEON',0,'2023-10-06 01:14:01.021000','NORMAL',NULL);
/*!40000 ALTER TABLE `users` ENABLE KEYS */;
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

-- Dump completed on 2023-10-06  3:32:58
