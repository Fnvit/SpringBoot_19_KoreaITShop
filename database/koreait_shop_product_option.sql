-- MySQL dump 10.13  Distrib 8.0.42, for Win64 (x86_64)
--
-- Host: localhost    Database: koreait_shop
-- ------------------------------------------------------
-- Server version	8.4.6

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
-- Table structure for table `product_option`
--

DROP TABLE IF EXISTS `product_option`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `product_option` (
  `no` int NOT NULL AUTO_INCREMENT,
  `product_no` int NOT NULL,
  `name` varchar(45) NOT NULL,
  `price` int unsigned NOT NULL DEFAULT '0',
  PRIMARY KEY (`no`)
) ENGINE=InnoDB AUTO_INCREMENT=57 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `product_option`
--

LOCK TABLES `product_option` WRITE;
/*!40000 ALTER TABLE `product_option` DISABLE KEYS */;
INSERT INTO `product_option` VALUES (1,8,'シャークソルベ味クッキー',23400),(2,8,'ハッカ飴味クッキー',5000),(3,8,'海賊味クッキー',1800),(4,9,'毒マッシュ味クッキー',14100),(5,9,'リコリス味クッキー',10900),(6,9,'ザクロ味クッキー',33200),(7,10,'オニオン味クッキー',14200),(8,10,'ブラックベリー味クッキー',29300),(9,11,'クッキーキャッスル',33600),(10,11,'くまゼリー列車',39000),(11,18,'くまゼリー',34100),(12,18,'月ウサギ味クッキー',12100),(13,18,'シュークリーム味クッキー',39800),(14,18,'ミルキーウェイ味クッキー',33300),(15,21,'ピンクのくまゼリー',9700),(16,21,'黄色いくまぜりー',32400),(17,22,'ピンクのくまゼリー',43400),(18,22,'黄色いくまぜりー',29600),(19,26,'クッキーラン：オーブンブレイク',22300),(20,26,'クッキーラン：キングダム',49700),(21,26,'クッキーラン：魔女の城',32400),(22,32,'ピンクのくまゼリー',48500),(23,32,'黄色いくまぜりー',10400),(24,36,'シャークソルベ味クッキー',42000),(25,36,'ハッカ飴味クッキー',31700),(26,40,'ブルー',38200),(27,40,'ブラウン',40300),(28,45,'くまゼリー',48400),(29,45,'月ウサギ味クッキー',27300),(30,45,'シュークリーム味クッキー',20100),(31,45,'ミルキーウェイ味クッキー',23600),(32,48,'シュガーノーム',6300),(33,48,'海の妖精クッキー',17900),(34,48,'ピュアバニラクッキー',39000),(35,49,'シュガーノーム',23800),(36,49,'海の妖精クッキー',16200),(37,49,'ピュアバニラクッキー',35700),(38,52,'ピンクのくまゼリー',200),(39,52,'黄色いくまぜりー',32300),(40,53,'ピンクのくまゼリー',13700),(41,53,'黄色いくまぜりー',18300),(42,55,'スイートカップル',22300),(43,55,'小さな仲間たち',34500),(44,55,'雪原の仲間たち',13500),(45,55,'ピュアバニラ',23200),(46,55,'オーナメントセット',18400),(47,65,'オニオン味クッキー',25800),(48,65,'ブラックベリー味クッキー',14200),(49,66,'毒マッシュ味クッキー',42300),(50,66,'リコリス味クッキー',40700),(51,66,'ザクロ味クッキー',17200),(52,67,'シャークソルベ味クッキー',18100),(53,67,'ハッカ飴味クッキー',28600),(54,67,'海賊味クッキー',3900),(55,69,'クッキーキャッスル',44200),(56,69,'くまゼリー列車',15600);
/*!40000 ALTER TABLE `product_option` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2026-03-06  2:35:43
