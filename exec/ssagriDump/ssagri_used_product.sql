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
-- Table structure for table `used_product`
--

DROP TABLE IF EXISTS `used_product`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `used_product` (
  `used_product_no` bigint NOT NULL AUTO_INCREMENT,
  `create_date` datetime(6) DEFAULT NULL,
  `delete_date` datetime(6) DEFAULT NULL,
  `update_date` datetime(6) DEFAULT NULL,
  `used_product_category` varchar(255) NOT NULL,
  `used_product_content` varchar(1000) DEFAULT NULL,
  `used_product_likeCount` int NOT NULL DEFAULT '0',
  `used_product_price` int NOT NULL,
  `used_product_region` varchar(255) NOT NULL,
  `used_product_status` varchar(255) NOT NULL,
  `used_product_title` varchar(255) NOT NULL,
  `user_no` bigint NOT NULL,
  PRIMARY KEY (`used_product_no`),
  KEY `FKjad6r18d05pqi3piuuuwx9vfc` (`user_no`),
  CONSTRAINT `FKjad6r18d05pqi3piuuuwx9vfc` FOREIGN KEY (`user_no`) REFERENCES `users` (`user_no`)
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `used_product`
--

LOCK TABLES `used_product` WRITE;
/*!40000 ALTER TABLE `used_product` DISABLE KEYS */;
INSERT INTO `used_product` VALUES (1,'2023-10-05 23:00:42.783416','2023-10-05 23:08:08.934992','2023-10-05 23:08:08.953109','MONITER','<ul><li>상품명<ul><li><span style=\"color:hsl(0, 0%, 0%);\"><strong>삼성 커브드 모니터</strong></span></li></ul></li><li>구매시기<ul><li>올해 초</li></ul></li><li>사용기간<ul><li>1년</li></ul></li><li>하자여부<ul><li>없어용</li></ul></li></ul><p><br>* 실제 촬영한 사진과 함께 상세 정보를 입력해주세요.<br>* 카카오톡 아이디 첨부시 게시물 삭제 및 이용제재 처리될 수 있어요.<br>* 영산 URL 첨부는 하나만 해주세요.<br>* 유튜브 영상 첨부시 URL이 아닌, \"공유 - 퍼가기\" 에 나타나는 src 링크를 입력해주세요.&nbsp;<br><br>안전하고 건전한 거래환경을 위해 싸그리가 함께합니다.</p>',0,200000,'DAEJEON','READY','삼성전자 삼성 커브드 모니터',3),(2,'2023-10-05 23:04:16.287955',NULL,'2023-10-05 23:04:16.287955','MONITER','<ul><li>상품명<ul><li>모니터</li></ul></li><li>구매시기<ul><li>올해 초</li></ul></li><li>사용기간<ul><li>1년</li></ul></li><li>하자여부<ul><li>없음</li></ul></li></ul><p><br>* 실제 촬영한 사진과 함께 상세 정보를 입력해주세요.<br>* 카카오톡 아이디 첨부시 게시물 삭제 및 이용제재 처리될 수 있어요.<br>* 영산 URL 첨부는 하나만 해주세요.<br>* 유튜브 영상 첨부시 URL이 아닌, \"공유 - 퍼가기\" 에 나타나는 src 링크를 입력해주세요.&nbsp;<br><br>안전하고 건전한 거래환경을 위해 싸그리가 함께합니다.</p>',0,200000,'DAEJEON','READY','모니터',3),(3,'2023-10-05 23:09:18.143338',NULL,'2023-10-05 23:09:18.143338','MOUSE','<h2>상품명 : 토토로 마우스 패드</h2><ul><li>구매시기 : 2022.01.01</li><li>사용기간 : 약 3년</li><li>하자여부 : 겉에 찌그러진거 조금 있음.</li></ul><p><br>* 실제 촬영한 사진과 함께 상세 정보를 입력해주세요.<br>* 카카오톡 아이디 첨부시 게시물 삭제 및 이용제재 처리될 수 있어요.<br>* 영산 URL 첨부는 하나만 해주세요.<br>* 유튜브 영상 첨부시 URL이 아닌, \"공유 - 퍼가기\" 에 나타나는 src 링크를 입력해주세요.&nbsp;<br><br>안전하고 건전한 거래환경을 위해 싸그리가 함께합니다.</p>',0,15000,'DAEJEON','READY','토토로 마우스 패드',2),(4,'2023-10-05 23:09:35.798504','2023-10-05 23:10:54.097291','2023-10-05 23:10:54.097291','MONITER','<ul><li>상품명<ul><li>엘지 모니터</li></ul></li><li>구매시기<ul><li>1년!!</li></ul></li><li>사용기간<ul><li>1년</li></ul></li><li>하자여부<ul><li>없당</li></ul></li></ul><p><br>* 실제 촬영한 사진과 함께 상세 정보를 입력해주세요.<br>* 카카오톡 아이디 첨부시 게시물 삭제 및 이용제재 처리될 수 있어요.<br>* 영산 URL 첨부는 하나만 해주세요.<br>* 유튜브 영상 첨부시 URL이 아닌, \"공유 - 퍼가기\" 에 나타나는 src 링크를 입력해주세요.&nbsp;<br><br>안전하고 건전한 거래환경을 위해 싸그리가 함께합니다.</p>',0,0,'DAEJEON','READY','엘지 모니터',3),(5,'2023-10-05 23:11:28.048297',NULL,'2023-10-05 23:11:28.048297','MONITER','<ul><li>상품명<ul><li>엘지 모니터</li></ul></li><li>구매시기<ul><li>올해 초</li></ul></li><li>사용기간<ul><li>1년</li></ul></li><li>하자여부<ul><li>없음</li></ul></li></ul><p><br>* 실제 촬영한 사진과 함께 상세 정보를 입력해주세요.<br>* 카카오톡 아이디 첨부시 게시물 삭제 및 이용제재 처리될 수 있어요.<br>* 영산 URL 첨부는 하나만 해주세요.<br>* 유튜브 영상 첨부시 URL이 아닌, \"공유 - 퍼가기\" 에 나타나는 src 링크를 입력해주세요.&nbsp;<br><br>안전하고 건전한 거래환경을 위해 싸그리가 함께합니다.</p>',0,200000,'DAEJEON','READY','엘지 모니터',3),(6,'2023-10-05 23:15:46.856679',NULL,'2023-10-06 00:06:17.435289','ETC','<ul><li>상품명 : 모코코 패드</li><li>구매시기 : 2023.01.10</li><li>사용기간 : 약 9개월</li><li>하자여부 : 진짜 없어요.</li></ul><p>제가 이거 이디야에서 음료 엄청 사먹어서 간신히 당첨된거예요 ㄹㅇ 희귀템임,,</p><p>&nbsp;</p><p><br>* 실제 촬영한 사진과 함께 상세 정보를 입력해주세요.<br>* 카카오톡 아이디 첨부시 게시물 삭제 및 이용제재 처리될 수 있어요.<br>* 영산 URL 첨부는 하나만 해주세요.<br>* 유튜브 영상 첨부시 URL이 아닌, \"공유 - 퍼가기\" 에 나타나는 src 링크를 입력해주세요.&nbsp;<br><br>안전하고 건전한 거래환경을 위해 싸그리가 함께합니다.</p>',1,80000,'DAEJEON','READY','모코코 패드',2),(7,'2023-10-05 23:43:35.924367',NULL,'2023-10-05 23:43:35.924367','KEYBOARD','<ul><li>상품명 : 미니언 키보드</li><li>구매시기 : 2021.05.08</li><li>사용기간 : 약 3년</li><li>하자여부 : esc 키보드 이빨이 하나 나가있음</li></ul><p>엄청비싸게 주고 산거지만 이빨 하나 나가서 5분의1로 깎은 거예요...</p><p><br>* 실제 촬영한 사진과 함께 상세 정보를 입력해주세요.<br>* 카카오톡 아이디 첨부시 게시물 삭제 및 이용제재 처리될 수 있어요.<br>* 영산 URL 첨부는 하나만 해주세요.<br>* 유튜브 영상 첨부시 URL이 아닌, \"공유 - 퍼가기\" 에 나타나는 src 링크를 입력해주세요.&nbsp;<br><br>안전하고 건전한 거래환경을 위해 싸그리가 함께합니다.</p>',0,45000,'DAEJEON','READY','미니언 키보드',2),(8,'2023-10-06 00:03:34.126788',NULL,'2023-10-06 03:05:56.506270','MONITER','<ul><li>상품명<ul><li>맥북프로</li></ul></li><li>구매시기<ul><li>1달전</li></ul></li><li>사용기간<ul><li>1달</li></ul></li><li>하자여부<ul><li>없지용</li></ul></li></ul><p><br>* 실제 촬영한 사진과 함께 상세 정보를 입력해주세요.<br>* 카카오톡 아이디 첨부시 게시물 삭제 및 이용제재 처리될 수 있어요.<br>* 영산 URL 첨부는 하나만 해주세요.<br>* 유튜브 영상 첨부시 URL이 아닌, \"공유 - 퍼가기\" 에 나타나는 src 링크를 입력해주세요.&nbsp;<br><br>안전하고 건전한 거래환경을 위해 싸그리가 함께합니다.</p>',1,800000,'DAEJEON','READY','맥북 프로',3),(9,'2023-10-06 00:51:13.736055',NULL,'2023-10-06 00:51:13.736055','LIFE','<ul><li>상품명 : 물고기 슬리퍼</li><li>구매시기 : 7일전</li><li>사용기간 : 하루</li><li>하자여부 : 아예 새거</li></ul><blockquote><p><strong>산 지 얼마 안됐고 생각보다 엄청 푹신해요!! 근데 &nbsp;조금 비린내나는 현실고증이 있긴함.....</strong></p></blockquote><p><br>* 실제 촬영한 사진과 함께 상세 정보를 입력해주세요.<br>* 카카오톡 아이디 첨부시 게시물 삭제 및 이용제재 처리될 수 있어요.<br>* 영산 URL 첨부는 하나만 해주세요.<br>* 유튜브 영상 첨부시 URL이 아닌, \"공유 - 퍼가기\" 에 나타나는 src 링크를 입력해주세요.&nbsp;<br><br>안전하고 건전한 거래환경을 위해 싸그리가 함께합니다.</p>',0,15000,'DAEJEON','READY','물고기 슬리퍼',7),(10,'2023-10-06 01:11:41.339373',NULL,'2023-10-06 01:11:41.339373','MOUSE','<ul><li>상품명 : <strong>로지텍 G102IC 2세대 LIGHTSYNC 게이밍 유선마우스</strong></li><li>&nbsp;</li><li>구매시기 : 2022년 2월말</li><li>사용기간 : 약 16개월&nbsp;</li><li>하자여부 : 딱히 하자는 없습니다.</li></ul><p><br>* 실제 촬영한 사진과 함께 상세 정보를 입력해주세요.<br>* 카카오톡 아이디 첨부시 게시물 삭제 및 이용제재 처리될 수 있어요.<br>* 영산 URL 첨부는 하나만 해주세요.<br>* 유튜브 영상 첨부시 URL이 아닌, \"공유 - 퍼가기\" 에 나타나는 src 링크를 입력해주세요.&nbsp;<br><br>안전하고 건전한 거래환경을 위해 싸그리가 함께합니다.</p>',0,22000,'SEOUL','READY','로지텍 G102IC 2세대 마우스 팝니다.',6),(11,'2023-10-06 01:15:18.470815',NULL,'2023-10-06 01:15:18.470815','KEYBOARD','<ul><li>상품명 : 로이체 무선 키보드</li><li>구매시기 : 22년 7월말&nbsp;</li><li>사용기간 : 1년 3개월</li><li>하자여부 : 하단부분에 약간의 스크래치 있는거 말고는 깨끗합니다.</li></ul><p>&nbsp;</p><p><br>* 실제 촬영한 사진과 함께 상세 정보를 입력해주세요.<br>* 카카오톡 아이디 첨부시 게시물 삭제 및 이용제재 처리될 수 있어요.<br>* 영산 URL 첨부는 하나만 해주세요.<br>* 유튜브 영상 첨부시 URL이 아닌, \"공유 - 퍼가기\" 에 나타나는 src 링크를 입력해주세요.&nbsp;<br><br>안전하고 건전한 거래환경을 위해 싸그리가 함께합니다.</p>',0,0,'SEOUL','READY','로이체 무선 키보드 팝니다.',6),(12,'2023-10-06 01:17:57.763003',NULL,'2023-10-06 03:03:03.770799','ETC','<ul><li>상품명 : 오메가 SA 론진 시계</li><li>구매시기 : 22년 7월말&nbsp;</li><li>사용기간 : 1년 3개월</li><li>하자여부 : 하단부분에 약간의 스크래치 있는거 말고는 깨끗합니다.</li></ul><p><br>* 실제 촬영한 사진과 함께 상세 정보를 입력해주세요.<br>* 카카오톡 아이디 첨부시 게시물 삭제 및 이용제재 처리될 수 있어요.<br>* 영산 URL 첨부는 하나만 해주세요.<br>* 유튜브 영상 첨부시 URL이 아닌, \"공유 - 퍼가기\" 에 나타나는 src 링크를 입력해주세요.&nbsp;<br><br>안전하고 건전한 거래환경을 위해 싸그리가 함께합니다.</p>',1,150000,'SEOUL','READY','오메가 SA 론진 시계',6),(13,'2023-10-06 03:28:49.800983',NULL,'2023-10-06 03:28:49.800983','MONITER','<ul><li>상품명 : LG QHD모니터</li><li>구매시기 : 22년 7월말</li><li>사용기간 : 1년 3개월</li><li>하자여부 : 하단부분에 약간의 스크래치 있는거 말고는 깨끗합니다.</li></ul><p><br>* 실제 촬영한 사진과 함께 상세 정보를 입력해주세요.<br>* 카카오톡 아이디 첨부시 게시물 삭제 및 이용제재 처리될 수 있어요.<br>* 영산 URL 첨부는 하나만 해주세요.<br>* 유튜브 영상 첨부시 URL이 아닌, \"공유 - 퍼가기\" 에 나타나는 src 링크를 입력해주세요.&nbsp;<br><br>안전하고 건전한 거래환경을 위해 싸그리가 함께합니다.</p>',0,140000,'SEOUL','READY','LG QHD모니터 팝니다',6),(14,'2023-10-06 03:30:51.166938',NULL,'2023-10-06 03:30:51.166938','KEYBOARD','<ul><li>상품명: 삼성전자 SPA-KNG1CU 기계식 키보드</li><li>구매시기 : 22년 7월말&nbsp;</li><li>사용기간 : 1년 3개월</li><li>하자여부 : 하단부분에 약간의 스크래치 있는거 말고는 깨끗합니다.</li></ul><p><br>* 실제 촬영한 사진과 함께 상세 정보를 입력해주세요.<br>* 카카오톡 아이디 첨부시 게시물 삭제 및 이용제재 처리될 수 있어요.<br>* 영산 URL 첨부는 하나만 해주세요.<br>* 유튜브 영상 첨부시 URL이 아닌, \"공유 - 퍼가기\" 에 나타나는 src 링크를 입력해주세요.&nbsp;<br><br>안전하고 건전한 거래환경을 위해 싸그리가 함께합니다.</p>',0,70000,'SEOUL','READY','삼성전자 기계식 키보드 팝니다.',6),(15,'2023-10-06 03:33:00.278891',NULL,'2023-10-06 03:33:00.278891','MOUSE','<ul><li>상품명: 마우스 패드</li><li>구매시기 : 23년 7월말</li><li>사용기간 : 3개월</li><li>하자여부 : 하자 없고 깨끗하게 썻습니다.</li></ul><p><br>* 실제 촬영한 사진과 함께 상세 정보를 입력해주세요.<br>* 카카오톡 아이디 첨부시 게시물 삭제 및 이용제재 처리될 수 있어요.<br>* 영산 URL 첨부는 하나만 해주세요.<br>* 유튜브 영상 첨부시 URL이 아닌, \"공유 - 퍼가기\" 에 나타나는 src 링크를 입력해주세요.&nbsp;<br><br>안전하고 건전한 거래환경을 위해 싸그리가 함께합니다.</p>',0,5000,'SEOUL','READY','마우스 패드 팝니다.',6);
/*!40000 ALTER TABLE `used_product` ENABLE KEYS */;
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

-- Dump completed on 2023-10-06  3:33:04
