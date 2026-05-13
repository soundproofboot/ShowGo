-- MySQL dump 10.13  Distrib 8.0.33, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: showgo
-- ------------------------------------------------------
-- Server version	8.0.33

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8mb4 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `event`
--

DROP TABLE IF EXISTS `event`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `event` (
  `id` int NOT NULL AUTO_INCREMENT,
  `event_start` datetime NOT NULL,
  `created_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `title` varchar(250) NOT NULL,
  `description` text NOT NULL,
  `ticket_price` double DEFAULT NULL,
  `venue_id` int NOT NULL,
  PRIMARY KEY (`id`),
  KEY `event__venue_fk` (`venue_id`),
  CONSTRAINT `event__venue_fk` FOREIGN KEY (`venue_id`) REFERENCES `venue` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=18 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `event`
--

LOCK TABLES `event` WRITE;
/*!40000 ALTER TABLE `event` DISABLE KEYS */;
INSERT INTO `event` VALUES (1,'2026-05-09 12:06:48','2026-04-28 17:06:48','event1','event1 description',10.5,1),(2,'2026-05-02 12:06:48','2026-04-29 17:06:48','event2','event2 description',11.5,2),(3,'2026-05-03 12:06:48','2026-04-30 17:06:48','event3','event3 description',NULL,3),(4,'2026-05-08 17:06:48','2026-04-30 18:12:37','event4','event4 description',13.5,1),(7,'2026-05-27 11:14:00','2026-05-01 16:14:16','Test Adding Performers','test description',15.5,5),(14,'2026-06-30 18:54:00','2026-05-11 23:54:42','EEEEEEvent','I can change the description',NULL,5);
/*!40000 ALTER TABLE `event` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `event_interest`
--

DROP TABLE IF EXISTS `event_interest`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `event_interest` (
  `id` int NOT NULL AUTO_INCREMENT,
  `event_id` int NOT NULL,
  `user_id` int NOT NULL,
  PRIMARY KEY (`id`),
  KEY `event_interest_event_id_fk` (`event_id`),
  KEY `event_interest_user_id_fk` (`user_id`),
  CONSTRAINT `event_interest_event_id_fk` FOREIGN KEY (`event_id`) REFERENCES `event` (`id`) ON DELETE CASCADE,
  CONSTRAINT `event_interest_user_id_fk` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`) ON DELETE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `event_interest`
--

LOCK TABLES `event_interest` WRITE;
/*!40000 ALTER TABLE `event_interest` DISABLE KEYS */;
INSERT INTO `event_interest` VALUES (1,1,1),(12,1,6);
/*!40000 ALTER TABLE `event_interest` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `event_performer`
--

DROP TABLE IF EXISTS `event_performer`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `event_performer` (
  `id` int NOT NULL AUTO_INCREMENT,
  `event_id` int NOT NULL,
  `performer_id` int NOT NULL,
  PRIMARY KEY (`id`),
  KEY `event_performer_event_id_fk` (`event_id`),
  KEY `event_performer_performer_id_fk` (`performer_id`),
  CONSTRAINT `event_performer_event_id_fk` FOREIGN KEY (`event_id`) REFERENCES `event` (`id`) ON DELETE CASCADE,
  CONSTRAINT `event_performer_performer_id_fk` FOREIGN KEY (`performer_id`) REFERENCES `performer` (`id`) ON DELETE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=61 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `event_performer`
--

LOCK TABLES `event_performer` WRITE;
/*!40000 ALTER TABLE `event_performer` DISABLE KEYS */;
INSERT INTO `event_performer` VALUES (1,1,1),(2,1,2),(3,1,3),(4,2,1),(5,2,2),(6,3,2),(7,3,3),(55,14,6),(56,14,4),(57,7,3),(58,7,6);
/*!40000 ALTER TABLE `event_performer` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `performer`
--

DROP TABLE IF EXISTS `performer`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `performer` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(100) DEFAULT NULL,
  `description` text NOT NULL,
  `genre` varchar(30) NOT NULL,
  `user_id` int NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `name` (`name`),
  KEY `performer_user_id_fk` (`user_id`),
  CONSTRAINT `performer_user_id_fk` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `performer`
--

LOCK TABLES `performer` WRITE;
/*!40000 ALTER TABLE `performer` DISABLE KEYS */;
INSERT INTO `performer` VALUES (1,'performer1','This is the description for performer1','Rock',1),(2,'performer2','This is the description for performer2','Pop',2),(3,'performer3','This is the description for performer3','Indie',3),(4,'Some Performer','Some description of some performer.','Bebop',6),(6,'Eep','We are a cool band','Alt Rock',6);
/*!40000 ALTER TABLE `performer` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `performer_follow`
--

DROP TABLE IF EXISTS `performer_follow`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `performer_follow` (
  `id` int NOT NULL AUTO_INCREMENT,
  `performer_id` int NOT NULL,
  `user_id` int NOT NULL,
  PRIMARY KEY (`id`),
  KEY `performer_follow_performer_id_fk` (`performer_id`),
  KEY `performer_follow_user_id_fk` (`user_id`),
  CONSTRAINT `performer_follow_performer_id_fk` FOREIGN KEY (`performer_id`) REFERENCES `performer` (`id`) ON DELETE CASCADE,
  CONSTRAINT `performer_follow_user_id_fk` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `performer_follow`
--

LOCK TABLES `performer_follow` WRITE;
/*!40000 ALTER TABLE `performer_follow` DISABLE KEYS */;
INSERT INTO `performer_follow` VALUES (1,1,1),(2,1,2),(3,1,3),(4,2,1),(5,2,2),(6,3,1),(14,1,6);
/*!40000 ALTER TABLE `performer_follow` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user` (
  `id` int NOT NULL AUTO_INCREMENT,
  `username` varchar(45) DEFAULT NULL,
  `city` varchar(100) DEFAULT NULL,
  `state` char(2) DEFAULT NULL,
  `email` varchar(100) NOT NULL,
  `cognito_id` varchar(100) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES (1,'user1','Madison','WI','user1@email.com','12345'),(2,'user2','Milwaukee','WI','user2@email.com','23456'),(3,'user3','Chicago','IL','user3@email.com','34567'),(6,'colingmail','Madison','WI','colinedwinbares@gmail.com','21ebb5f0-70c1-7037-148b-a76c14fedb1d');
/*!40000 ALTER TABLE `user` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `venue`
--

DROP TABLE IF EXISTS `venue`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `venue` (
  `id` int NOT NULL AUTO_INCREMENT,
  `user_id` int NOT NULL,
  `name` varchar(100) NOT NULL,
  `city` varchar(100) NOT NULL,
  `state` char(2) DEFAULT NULL,
  `street_address` varchar(100) NOT NULL,
  `description` text NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `name` (`name`),
  KEY `venue_user_id_fk` (`user_id`),
  CONSTRAINT `venue_user_id_fk` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `venue`
--

LOCK TABLES `venue` WRITE;
/*!40000 ALTER TABLE `venue` DISABLE KEYS */;
INSERT INTO `venue` VALUES (1,1,'venue1','Madison','WI','123 Fake St','Description for venue1'),(2,2,'venue2','Milwaukee','WI','234 Imaginary Place','Description for venue2'),(3,3,'venue3','Chicago','IL','345 Ficticious Drive','Description for venue3'),(5,6,'A Venue','Faketon','WI','123 False Street','This is a description of a music venue'),(9,6,'Test State','Town','AA','123 Something','asd;flkj');
/*!40000 ALTER TABLE `venue` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `venue_follow`
--

DROP TABLE IF EXISTS `venue_follow`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `venue_follow` (
  `id` int NOT NULL AUTO_INCREMENT,
  `user_id` int NOT NULL,
  `venue_id` int NOT NULL,
  PRIMARY KEY (`id`),
  KEY `venue_follow_user_id_fk` (`user_id`),
  KEY `venue_follow_venue_id_fk` (`venue_id`),
  CONSTRAINT `venue_follow_user_id_fk` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`),
  CONSTRAINT `venue_follow_venue_id_fk` FOREIGN KEY (`venue_id`) REFERENCES `venue` (`id`) ON DELETE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `venue_follow`
--

LOCK TABLES `venue_follow` WRITE;
/*!40000 ALTER TABLE `venue_follow` DISABLE KEYS */;
INSERT INTO `venue_follow` VALUES (1,1,1),(2,2,1),(3,3,1),(4,1,2),(5,2,2),(6,1,3),(8,6,1);
/*!40000 ALTER TABLE `venue_follow` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2026-05-13 11:04:17
