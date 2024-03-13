-- MySQL dump 10.13  Distrib 8.0.23, for Win64 (x86_64)
--
-- Host: localhost    Database: employee
-- ------------------------------------------------------
-- Server version	8.0.15

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
-- Table structure for table `advantage`
--

DROP TABLE IF EXISTS `advantage`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `advantage` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  `userid` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_15mxuyekf3cgulvsu3psvnlsx` (`userid`),
  CONSTRAINT `FK_15mxuyekf3cgulvsu3psvnlsx` FOREIGN KEY (`userid`) REFERENCES `user` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=41 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `advantage`
--

LOCK TABLES `advantage` WRITE;
/*!40000 ALTER TABLE `advantage` DISABLE KEYS */;
INSERT INTO `advantage` VALUES (16,'Football',36),(17,'Football',36),(18,'Football',36),(19,'Football',36),(20,'Football',36),(21,'Football',36),(22,'Football',36),(24,'Football',35),(25,'Football',35),(26,'Football',35),(27,'Football',35),(28,'Football',35),(29,'Football',35),(30,'Football',35),(31,'Football',35),(32,'Football',44),(33,'Music',44),(34,'Guitar',44),(35,'Badminton',44),(39,'Volleyball',44),(40,'Present',44);
/*!40000 ALTER TABLE `advantage` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `role`
--

DROP TABLE IF EXISTS `role`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `role` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=20 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `role`
--

LOCK TABLES `role` WRITE;
/*!40000 ALTER TABLE `role` DISABLE KEYS */;
INSERT INTO `role` VALUES (1,'EMPLOYEE'),(9,'MANAGER'),(10,'ADMIN');
/*!40000 ALTER TABLE `role` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `team`
--

DROP TABLE IF EXISTS `team`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `team` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  `namemanager` varchar(255) DEFAULT NULL,
  `number` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=91 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `team`
--

LOCK TABLES `team` WRITE;
/*!40000 ALTER TABLE `team` DISABLE KEYS */;
INSERT INTO `team` VALUES (66,'DG5','Anh Thanh',12),(67,'DG3','Thanh Bui',1),(68,'DG2','Duy Nguyen',1),(69,'DG6','Quy Nguyen',1),(70,'DSG','David',1),(71,'DG1','Quy Nguyen',1),(72,'DG4','Quy Nguyen',1),(73,'SF','Quy Nguyen',1),(74,'ADMIN','Quy Nguyen',1),(81,'DG7','Quy Nguyen',1),(82,'DG8','Quy Nguyen',1),(83,'DG9','Quy Nguyen',1),(90,'QV',NULL,0);
/*!40000 ALTER TABLE `team` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `technical`
--

DROP TABLE IF EXISTS `technical`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `technical` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `level` varchar(255) DEFAULT NULL,
  `skill` varchar(255) DEFAULT NULL,
  `userid` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_suxqft3ewv13tqbl19fuuk69` (`userid`),
  CONSTRAINT `FK_suxqft3ewv13tqbl19fuuk69` FOREIGN KEY (`userid`) REFERENCES `user` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=75 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `technical`
--

LOCK TABLES `technical` WRITE;
/*!40000 ALTER TABLE `technical` DISABLE KEYS */;
INSERT INTO `technical` VALUES (46,'1','Java',34),(47,'2','Java',34),(48,'1','C#',34),(49,'2','C#',34),(50,'1','C',34),(51,'2','C',34),(52,'123','123',34),(53,'123','123',34),(54,'123','123',34),(55,'12','123',34),(56,'123','123',34),(57,'123','123',34),(58,'1','Java',36),(59,'2','Java',36),(60,'3','Java',36),(61,'4','Java',36),(62,'5','Java',36),(63,'6','Java',36),(64,'7','Java',36),(65,'8','Java',36),(66,'9','Java',36),(67,'10','Java',36),(68,'11','Java',36),(69,'2','Java',44),(70,'2','C',44),(71,'3','C++',44),(72,'4','C#',44),(73,'3','Python',44),(74,'2','.NET',44);
/*!40000 ALTER TABLE `technical` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `age` varchar(255) DEFAULT NULL,
  `daystart` varchar(255) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `nickname` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `profile` varchar(255) DEFAULT NULL,
  `sex` int(11) DEFAULT NULL,
  `status` tinyint(1) DEFAULT NULL,
  `roleId` bigint(20) DEFAULT NULL,
  `teamId` bigint(20) DEFAULT NULL,
  `image` varchar(255) DEFAULT NULL,
  `experience` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_ao5g1e3yutsfcfv8pp9ym93c2` (`roleId`),
  KEY `FK_5yl5on2fylrkb4vq06srebk17` (`teamId`),
  CONSTRAINT `FK_5yl5on2fylrkb4vq06srebk17` FOREIGN KEY (`teamId`) REFERENCES `team` (`id`),
  CONSTRAINT `FK_ao5g1e3yutsfcfv8pp9ym93c2` FOREIGN KEY (`roleId`) REFERENCES `role` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=45 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES (34,'23','2021-03-31T17:00:00.000Z','duynguyen@gmail.com','Duy Nguyen','Duy','E10ADC3949BA59ABBE56E057F20F883E','<p>not</p>\n',1,1,10,66,'quynguyen.png',2),(35,'24','2021-09-13T17:00:00.000Z','duynguyen@gmail.com','Quy Nguyen','Duy','25D55AD283AA400AF464C76D713C07AD','<p>not</p>\n',1,1,1,66,'default.png',3),(36,'22','2021-04-03T17:00:00.000Z','buiduythanh24399@gmail.com','Thanh','CauUt','202CB962AC59075B964B07152D234B70','<p>not</p>\n',0,1,10,66,'default.png',4),(37,'21','2021-04-04T17:00:00.000Z','quynguyen@gmail.com','Quy Nguyen','Quy Nguyen','E10ADC3949BA59ABBE56E057F20F883E','<p>not</p>\n',0,1,10,66,'default.png',2),(38,'22','2021-04-10T17:00:00.000Z','nguyen@gmail.com','Duy Nguyen','Duy','8D4646EB2D7067126EB08ADB0672F7BB','<p>not</p>\n',0,1,1,66,'default.png',3),(39,'22','2021-04-10T17:00:00.000Z','nguyen@gmail.com','Quy Nguyen','Duy','8D4646EB2D7067126EB08ADB0672F7BB','<p>not</p>\n',0,0,9,66,'default.png',2),(40,'22','2021-04-10T17:00:00.000Z','nguyen@gmail.com','Thanh','Duy','8D4646EB2D7067126EB08ADB0672F7BB','<p>not</p>\n',0,1,10,66,'default.png',1),(41,'23','2021-04-10T17:00:00.000Z','nguyen@gmail.com','Thanh','Duy','5CF9CD00A0C3448002B452177B765859','<p>not</p>\n',0,1,10,66,'default.png',2),(42,'23','2021-04-10T17:00:00.000Z','nguyen@gmail.com','Quy Nguyen','Duy','E10ADC3949BA59ABBE56E057F20F883E','<p>not</p>\n',0,1,10,66,'default.png',3),(43,'22','2021-02-07T17:00:00.000Z','phamhongnghia@gmail.com','Nghia Pham','Nghia','E10ADC3949BA59ABBE56E057F20F883E','<p>not</p>\n',0,1,1,66,'default.png',3),(44,'22','2021-01-18T17:00:00.000Z','buiduythanh233@gmail.com','Bui Duy Thanh','Duy Thanh','FCEA920F7412B5DA7BE0CF42B8C93759','<p>Hello ! I\'m Thanh, i from Quang Ngai. I student DCT17 class at Pham Van Dong university.</p>\n',0,1,9,66,'employee.png',4),(45,'22','2024-03-13T17:00:00.000Z','adminuser@gmail.com','Admin','Admin','FCEA920F7412B5DA7BE0CF42B8C93759','<p>Hello ! I\'m Admin, i from Quang Ngai. I student DCT17 class at Pham Van Dong university.</p>\n',0,1,10,66,'employee.png',4);
/*!40000 ALTER TABLE `user` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2021-04-16  9:38:21
