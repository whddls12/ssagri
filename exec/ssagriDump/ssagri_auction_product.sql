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
-- Table structure for table `auction_product`
--

DROP TABLE IF EXISTS `auction_product`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `auction_product` (
  `auction_product_no` bigint NOT NULL AUTO_INCREMENT,
  `create_date` datetime(6) DEFAULT NULL,
  `delete_date` datetime(6) DEFAULT NULL,
  `update_date` datetime(6) DEFAULT NULL,
  `auction_product_status` varchar(255) DEFAULT NULL,
  `auction_product_comment` varchar(255) DEFAULT NULL,
  `auction_product_down_price` int NOT NULL,
  `auction_product_end_date` datetime(6) NOT NULL,
  `auction_product_finally_price` int DEFAULT NULL,
  `auction_product_name` varchar(255) NOT NULL,
  `auction_product_origin_price` int DEFAULT NULL,
  `auction_product_price_count` int NOT NULL,
  `auction_product_start_date` datetime(6) NOT NULL,
  `auction_product_type` varchar(255) DEFAULT NULL,
  `auction_product_up_price` int NOT NULL,
  `user_no` bigint NOT NULL,
  PRIMARY KEY (`auction_product_no`),
  KEY `FKi88ksfa3hyms14699jmfmwf9p` (`user_no`),
  CONSTRAINT `FKi88ksfa3hyms14699jmfmwf9p` FOREIGN KEY (`user_no`) REFERENCES `users` (`user_no`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `auction_product`
--

LOCK TABLES `auction_product` WRITE;
/*!40000 ALTER TABLE `auction_product` DISABLE KEYS */;
INSERT INTO `auction_product` VALUES (2,'2023-10-05 23:03:03.649798',NULL,'2023-10-06 02:30:11.822340','진행중','제가 일본 오사카가서 직접 구매해서 조립한 거 입니다 !! 어디가서 절대 못 구해요!! 타자감도 좋습니다!!',30000,'2023-10-09 09:00:00.000000',36000,'토토로 키보드',50000,2000,'2023-10-05 09:00:00.000000','모니터',0,2),(3,'2023-10-05 23:12:40.378717',NULL,'2023-10-06 01:17:36.758996','진행중','모니터는 엘지인거 알지?',200000,'2023-10-08 09:00:00.000000',200000,'엘지 모니터 아주 짱짱 모니터',300000,10000,'2023-10-05 09:00:00.000000','모니터',0,3),(4,'2023-10-05 23:13:01.982233',NULL,'2023-10-05 23:13:01.982233','예정','메이플에 도른자가 경매하는 핑크빈 마우스!! 무소음에다 산지 3개월도 안되서 거의 새거입니다!!',10000,'2023-10-17 09:00:00.000000',0,'메이플 핑크빈 마우스',35000,1000,'2023-10-07 09:00:00.000000','마우스',0,2),(5,'2023-10-05 23:49:06.009212',NULL,'2023-10-05 23:49:06.009212','예정','제가 일본 오사카 유니버셜에 가서 10000엔 주고 산거예요 ㅠㅠㅠ 이제는 취직해서 쓸 일이 없어서 경매 합니다만,,,',30000,'2023-10-24 09:00:00.000000',0,'미니언 마우스',100000,5000,'2023-10-10 09:00:00.000000','모니터',0,2),(6,'2023-10-05 23:56:30.970192',NULL,'2023-10-06 02:35:34.774719','진행중','노트북은 맥북인거 알지?',800000,'2023-10-12 09:00:00.000000',1000000,'매크북 짱짱북',1200000,50000,'2023-10-05 09:00:00.000000','기타용품',0,3),(7,'2023-10-06 00:47:17.180250',NULL,'2023-10-06 00:47:17.180250','예정','2주 정도 사용했는데 기스 하나도 없고 저는 아크릴 팜레스트가 더 이뻐서 싸게 드립니다 ㅎㅎ',10000,'2023-10-08 09:00:00.000000',0,'호두나무 원목 팜레스트',29500,1000,'2023-10-06 09:00:00.000000','기타용품',0,6),(8,'2023-10-06 01:02:27.541164',NULL,'2023-10-06 03:05:08.104317','진행중','생각보다 배추냄새 안나고 엄청 포근해요,,,ㅎㅎ 왜 벌레들이 배추 속으로 들어가는 지 알겠어요',10000,'2023-10-18 09:00:00.000000',46000,'배추 담요',20000,1000,'2023-10-05 09:00:00.000000','기타용품',0,7);
/*!40000 ALTER TABLE `auction_product` ENABLE KEYS */;
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

-- Dump completed on 2023-10-06  3:33:05
