-- MySQL dump 10.13  Distrib 8.0.41, for Win64 (x86_64)
--
-- Host: localhost    Database: airline_db
-- ------------------------------------------------------
-- Server version	8.0.41

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
-- Table structure for table `booking`
--

DROP TABLE IF EXISTS `booking`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `booking` (
  `id` int NOT NULL AUTO_INCREMENT,
  `passenger_name` varchar(100) DEFAULT NULL,
  `airline` varchar(100) DEFAULT NULL,
  `flight_number` varchar(20) DEFAULT NULL,
  `travel_date` date DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=49 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `booking`
--

LOCK TABLES `booking` WRITE;
/*!40000 ALTER TABLE `booking` DISABLE KEYS */;
INSERT INTO `booking` VALUES (47,'GAURI VERMA','IndiGo','6E318','2025-04-10'),(48,'KRISHNA PARAKH','IndiGo','6E318','2025-04-10');
/*!40000 ALTER TABLE `booking` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `flights`
--

DROP TABLE IF EXISTS `flights`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `flights` (
  `id` int NOT NULL AUTO_INCREMENT,
  `airline` varchar(50) DEFAULT NULL,
  `flight_number` varchar(20) DEFAULT NULL,
  `source` varchar(50) DEFAULT NULL,
  `destination` varchar(50) DEFAULT NULL,
  `departure_time` varchar(10) DEFAULT NULL,
  `arrival_time` varchar(10) DEFAULT NULL,
  `price` int DEFAULT NULL,
  `travel_date` date DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=175 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `flights`
--

LOCK TABLES `flights` WRITE;
/*!40000 ALTER TABLE `flights` DISABLE KEYS */;
INSERT INTO `flights` VALUES (66,'Air India','AI101','Delhi','Mumbai','08:00','10:00',4500,'2025-04-01'),(67,'Vistara','UK201','Mumbai','Bangalore','09:00','11:00',4000,'2025-04-01'),(68,'IndiGo','6E301','Hyderabad','Kolkata','07:30','09:45',4200,'2025-04-01'),(69,'Air India','AI102','Pune','Ahmedabad','06:00','07:30',3800,'2025-04-01'),(70,'Vistara','UK202','Chennai','Delhi','10:00','12:45',4600,'2025-04-01'),(71,'IndiGo','6E302','Mumbai','Chennai','07:00','09:15',4300,'2025-04-02'),(72,'Air India','AI103','Delhi','Hyderabad','08:30','10:30',4400,'2025-04-02'),(73,'Vistara','UK203','Ahmedabad','Pune','06:30','08:00',3700,'2025-04-02'),(74,'IndiGo','6E303','Bangalore','Kolkata','09:00','11:10',4100,'2025-04-02'),(75,'Air India','AI104','Kolkata','Delhi','10:00','12:00',4700,'2025-04-02'),(76,'Vistara','UK204','Pune','Bangalore','06:15','08:00',3600,'2025-04-03'),(77,'IndiGo','6E304','Delhi','Chennai','09:30','11:45',4500,'2025-04-03'),(78,'Air India','AI105','Mumbai','Hyderabad','07:45','09:30',3900,'2025-04-03'),(79,'Vistara','UK205','Hyderabad','Ahmedabad','08:30','10:45',4100,'2025-04-03'),(80,'IndiGo','6E305','Chennai','Kolkata','07:00','09:30',4400,'2025-04-03'),(81,'Air India','AI106','Delhi','Mumbai','08:00','10:00',4500,'2025-04-04'),(82,'Vistara','UK206','Mumbai','Bangalore','09:00','11:00',4000,'2025-04-04'),(83,'IndiGo','6E306','Hyderabad','Kolkata','07:30','09:45',4200,'2025-04-04'),(84,'Air India','AI107','Pune','Ahmedabad','06:00','07:30',3800,'2025-04-04'),(85,'Vistara','UK207','Chennai','Delhi','10:00','12:45',4600,'2025-04-04'),(86,'IndiGo','6E307','Mumbai','Chennai','07:00','09:15',4300,'2025-04-05'),(87,'Air India','AI108','Delhi','Hyderabad','08:30','10:30',4400,'2025-04-05'),(88,'Vistara','UK208','Ahmedabad','Pune','06:30','08:00',3700,'2025-04-05'),(89,'IndiGo','6E308','Bangalore','Kolkata','09:00','11:10',4100,'2025-04-05'),(90,'Air India','AI109','Kolkata','Delhi','10:00','12:00',4700,'2025-04-05'),(91,'Vistara','UK310','Mumbai','Chennai','07:30','09:30',4581,'2025-04-06'),(92,'IndiGo','6E122','Delhi','Mumbai','09:00','11:00',3962,'2025-04-06'),(93,'Air India','AI236','Hyderabad','Ahmedabad','08:15','10:15',4415,'2025-04-06'),(94,'Air India','AI285','Kolkata','Delhi','08:30','10:30',4052,'2025-04-06'),(95,'Vistara','UK132','Bangalore','Kolkata','06:45','08:45',3912,'2025-04-06'),(96,'IndiGo','6E225','Delhi','Mumbai','06:15','08:15',3678,'2025-04-07'),(97,'Vistara','UK129','Chennai','Kolkata','09:30','11:30',4675,'2025-04-07'),(98,'Vistara','UK379','Hyderabad','Ahmedabad','08:45','10:45',4381,'2025-04-07'),(99,'Air India','AI287','Kolkata','Delhi','06:30','08:30',4043,'2025-04-07'),(100,'IndiGo','6E398','Mumbai','Chennai','07:00','09:00',4120,'2025-04-07'),(101,'IndiGo','6E126','Delhi','Mumbai','06:00','08:00',3764,'2025-04-08'),(102,'Vistara','UK135','Bangalore','Kolkata','07:15','09:15',4589,'2025-04-08'),(103,'Vistara','UK330','Chennai','Kolkata','08:00','10:00',4498,'2025-04-08'),(104,'Air India','AI238','Hyderabad','Ahmedabad','06:30','08:30',4299,'2025-04-08'),(105,'IndiGo','6E401','Mumbai','Chennai','09:15','11:15',4187,'2025-04-08'),(106,'Air India','AI290','Kolkata','Delhi','07:45','09:45',3981,'2025-04-08'),(107,'Air India','AI117','Delhi','Mumbai','08:00','10:00',4500,'2025-04-09'),(108,'Vistara','UK217','Mumbai','Bangalore','09:00','11:00',4000,'2025-04-09'),(109,'IndiGo','6E317','Hyderabad','Kolkata','07:30','09:45',4200,'2025-04-09'),(110,'Air India','AI118','Pune','Ahmedabad','06:00','07:30',3800,'2025-04-09'),(111,'Vistara','UK218','Chennai','Delhi','10:00','12:45',4600,'2025-04-09'),(112,'IndiGo','6E318','Mumbai','Chennai','07:00','09:15',4300,'2025-04-10'),(113,'Air India','AI119','Delhi','Hyderabad','08:30','10:30',4400,'2025-04-10'),(114,'Vistara','UK219','Ahmedabad','Pune','06:30','08:00',3700,'2025-04-10'),(115,'IndiGo','6E319','Bangalore','Kolkata','09:00','11:10',4100,'2025-04-10'),(116,'Air India','AI120','Kolkata','Delhi','10:00','12:00',4700,'2025-04-10'),(117,'Vistara','UK220','Pune','Bangalore','06:15','08:00',3600,'2025-04-11'),(118,'IndiGo','6E320','Delhi','Chennai','09:30','11:45',4500,'2025-04-11'),(119,'Air India','AI121','Mumbai','Hyderabad','07:45','09:30',3900,'2025-04-11'),(120,'Vistara','UK221','Hyderabad','Ahmedabad','08:30','10:45',4100,'2025-04-11'),(121,'IndiGo','6E321','Chennai','Kolkata','07:00','09:30',4400,'2025-04-11'),(122,'Air India','AI122','Delhi','Mumbai','08:00','10:00',4500,'2025-04-12'),(123,'Vistara','UK222','Mumbai','Bangalore','09:00','11:00',4000,'2025-04-12'),(124,'IndiGo','6E322','Hyderabad','Kolkata','07:30','09:45',4200,'2025-04-12'),(125,'Air India','AI123','Pune','Ahmedabad','06:00','07:30',3800,'2025-04-12'),(126,'Vistara','UK223','Chennai','Delhi','10:00','12:45',4600,'2025-04-12'),(127,'IndiGo','6E323','Mumbai','Chennai','07:00','09:15',4300,'2025-04-13'),(128,'Air India','AI124','Delhi','Hyderabad','08:30','10:30',4400,'2025-04-13'),(129,'Vistara','UK224','Ahmedabad','Pune','06:30','08:00',3700,'2025-04-13'),(130,'IndiGo','6E324','Bangalore','Kolkata','09:00','11:10',4100,'2025-04-13'),(131,'Air India','AI125','Kolkata','Delhi','10:00','12:00',4700,'2025-04-13'),(132,'Vistara','UK225','Pune','Bangalore','06:15','08:00',3600,'2025-04-14'),(133,'IndiGo','6E325','Delhi','Chennai','09:30','11:45',4500,'2025-04-14'),(134,'Air India','AI126','Mumbai','Hyderabad','07:45','09:30',3900,'2025-04-14'),(135,'Vistara','UK226','Hyderabad','Ahmedabad','08:30','10:45',4100,'2025-04-14'),(136,'IndiGo','6E326','Chennai','Kolkata','07:00','09:30',4400,'2025-04-14'),(137,'Air India','AI127','Delhi','Mumbai','08:00','10:00',4500,'2025-04-15'),(138,'Vistara','UK227','Mumbai','Bangalore','09:00','11:00',4000,'2025-04-15'),(139,'IndiGo','6E327','Hyderabad','Kolkata','07:30','09:45',4200,'2025-04-15'),(140,'Air India','AI128','Pune','Ahmedabad','06:00','07:30',3800,'2025-04-15'),(141,'Vistara','UK228','Chennai','Delhi','10:00','12:45',4600,'2025-04-15'),(142,'Vistara','UK2025','Indore','Delhi','10:00','12:00',49500,'2025-04-09'),(143,'Air India','AI2035','Hyderabad','Indore','03:30','05:30',49500,'2025-04-10'),(144,'IndiGo','6E8841','Delhi','Indore','14:30','16:30',49500,'2025-04-11'),(145,'Vistara','UK4567','Hyderabad','Mumbai','02:00','04:00',49500,'2025-04-12'),(146,'IndiGo','6E3321','Bangalore','Delhi','10:30','12:30',4000,'2025-04-13'),(147,'Air India','AI7880','Ahmedabad','Indore','08:30','10:30',60000,'2025-04-14'),(148,'Vistara','UK9987','Mumbai','Ahmedabad','18:00','20:00',60000,'2025-04-15'),(149,'Air India','AI1144','Ahmedabad','Mumbai','18:00','20:00',4000,'2025-04-16'),(150,'IndiGo','6E7752','Delhi','Chennai','07:30','09:30',55000,'2025-04-17'),(151,'Vistara','UK3456','Kolkata','Hyderabad','08:30','10:30',49500,'2025-04-18'),(152,'Air India','AI9098','Indore','Mumbai','20:30','22:30',60000,'2025-04-19'),(153,'IndiGo','6E1820','Indore','Delhi','17:30','19:30',49500,'2025-04-20'),(154,'Vistara','UK1123','Mumbai','Bangalore','12:30','14:30',60000,'2025-04-21'),(155,'Air India','AI5567','Delhi','Mumbai','22:30','00:30',49500,'2025-04-22'),(156,'IndiGo','6E6420','Ahmedabad','Delhi','08:30','10:30',60000,'2025-04-23'),(157,'Vistara','UK2233','Hyderabad','Delhi','08:00','10:00',49500,'2025-04-24'),(158,'Air India','AI2025','Indore','Delhi','08:00','10:00',45500,'2025-04-01'),(159,'IndiGo','6E9988','Delhi','Mumbai','09:00','11:00',37500,'2025-04-05'),(160,'IndiGo','6E7654','Mumbai','Hyderabad','11:00','13:00',39800,'2025-04-15'),(161,'Vistara','UK3322','Chennai','Delhi','07:30','09:45',42000,'2025-04-18'),(162,'Air India','AI4023','Kolkata','Delhi','13:00','15:30',48000,'2025-04-21'),(163,'IndiGo','6E1234','Bangalore','Indore','10:30','12:30',49900,'2025-04-25'),(164,'Vistara','UK8765','Delhi','Chandigarh','09:15','10:45',49500,'2025-04-28'),(165,'Air India','AI9001','Jaipur','Pune','14:00','16:30',44500,'2025-05-01'),(166,'IndiGo','6E5678','Pune','Ahmedabad','12:00','13:30',45900,'2025-05-03'),(167,'Vistara','UK4444','Hyderabad','Kolkata','07:45','10:00',47500,'2025-05-07'),(168,'Air India','AI1111','Chennai','Bangalore','17:00','18:30',48800,'2025-05-10'),(169,'IndiGo','6E2323','Delhi','Varanasi','08:15','09:45',47000,'2025-05-13'),(170,'Vistara','UK5555','Lucknow','Delhi','06:00','07:30',46000,'2025-05-15'),(171,'Air India','AI7777','Delhi','Goa','15:00','17:30',43000,'2025-05-19'),(172,'IndiGo','6E9090','Ahmedabad','Delhi','16:30','18:00',45000,'2025-05-21'),(173,'Vistara','UK2020','Delhi','Indore','19:00','20:30',49000,'2025-05-24'),(174,'Air India','AI3030','Bhopal','Mumbai','13:30','15:30',47000,'2025-05-27');
/*!40000 ALTER TABLE `flights` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `passengers`
--

DROP TABLE IF EXISTS `passengers`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `passengers` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(100) DEFAULT NULL,
  `age` int DEFAULT NULL,
  `gender` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `passengers`
--

LOCK TABLES `passengers` WRITE;
/*!40000 ALTER TABLE `passengers` DISABLE KEYS */;
INSERT INTO `passengers` VALUES (1,'KRISHNA PARAKH',20,'Male'),(2,'GAURI VERMA',20,'Female'),(3,'KRISHNA PARAKH',20,'Male'),(4,'DISHA PARAKH',20,'Female'),(5,'KRISH PARAKH',15,'Male'),(6,'KRISHNA BHAIYA',16,'Male'),(7,'MISHIKA SONI',25,'Female'),(8,'M.S DHONI',42,'Male'),(9,'Tanmay gehlod',20,'Male'),(10,'TANMAY SHARMA',22,'Male'),(11,'SIMARJEET',20,'Male'),(12,'TISHA YADAV',43,'Female');
/*!40000 ALTER TABLE `passengers` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `payments`
--

DROP TABLE IF EXISTS `payments`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `payments` (
  `id` int NOT NULL AUTO_INCREMENT,
  `passenger_name` varchar(100) DEFAULT NULL,
  `amount` decimal(10,2) DEFAULT NULL,
  `payment_mode` varchar(50) DEFAULT NULL,
  `payment_date` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=42 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `payments`
--

LOCK TABLES `payments` WRITE;
/*!40000 ALTER TABLE `payments` DISABLE KEYS */;
INSERT INTO `payments` VALUES (1,'KRISHNA PARAKH',49500.00,'UPI','2025-04-06 12:39:35'),(2,'DISHA PARAKH',49500.00,'UPI','2025-04-06 14:30:27'),(3,'GAURI VERMA',49500.00,'Card','2025-04-07 16:02:01'),(4,'DISHA PARAKH',55000.00,'Cash','2025-04-07 16:19:42'),(5,'GAURI VERMA',550000.00,'UPI','2025-04-08 07:00:06'),(6,'KRISHNA PARAKH',4000.00,'UPI','2025-04-09 14:12:08'),(7,'RUHI GUPTA',44500.00,'UPI','2025-04-09 15:00:09'),(8,'DISHA PARAKH',37500.00,'UPI','2025-04-09 15:02:56'),(9,'GAURI VERMA',49500.00,'UPI','2025-04-10 10:39:55'),(10,'GAURI VERMA',49500.00,NULL,'2025-04-10 10:43:06'),(11,'SIA MEHRA',42000.00,NULL,'2025-04-10 10:46:21'),(12,'AARAV SHARMA',39800.00,NULL,'2025-04-10 10:46:53'),(13,'GAURI VERMA',49500.00,NULL,'2025-04-10 10:49:27'),(14,'DISHA PARAKH',37500.00,NULL,'2025-04-10 10:50:19'),(15,'GAURI VERMA',49500.00,NULL,'2025-04-10 10:52:41'),(16,'GAURI VERMA',49500.00,NULL,'2025-04-10 10:55:36'),(17,'DISHA PARAKH',37500.00,NULL,'2025-04-10 11:59:59'),(18,'KRISHNA BHAIYA',4500.00,NULL,'2025-04-10 12:15:08'),(19,'KRISHNA BHAIYA',4500.00,NULL,'2025-04-10 12:21:48'),(20,'KRISHNA BHAIYA',4500.00,NULL,'2025-04-10 12:26:54'),(21,'MISHIKA SONI',3600.00,NULL,'2025-04-10 13:54:35'),(22,'MISHIKA SONI',4600.00,NULL,'2025-04-13 06:56:00'),(23,'MISHIKA SONI',4600.00,NULL,'2025-04-13 06:56:40'),(24,'M.S DHONI',4500.00,NULL,'2025-04-14 08:01:22'),(25,'M.S DHONI',4200.00,NULL,'2025-04-14 15:36:31'),(26,'KRISHNA PARAKH',4500.00,NULL,'2025-04-16 15:51:38'),(27,'KRISHNA PARAKH',4500.00,NULL,'2025-04-16 15:58:36'),(28,'DISHA PARAKH',4500.00,NULL,'2025-04-16 16:11:34'),(29,'M.S DHONI',4500.00,NULL,'2025-04-16 17:06:14'),(30,'Tanmay gehlod',4300.00,NULL,'2025-04-17 05:15:35'),(31,'KRISHNA PARAKH',4500.00,NULL,'2025-04-17 05:33:35'),(32,'KRISHNA PARAKH',4200.00,NULL,'2025-04-17 05:37:14'),(33,'TANMAY SHARMA',4400.00,NULL,'2025-04-17 05:39:26'),(34,'SIMARJEET',4500.00,NULL,'2025-04-17 07:06:22'),(35,'TISHA YADAV',4000.00,NULL,'2025-04-17 08:26:04'),(36,'M.S DHONI',3700.00,NULL,'2025-04-17 08:29:01'),(37,'SUMIT BADMASH',4000.00,NULL,'2025-04-17 08:43:38'),(38,'DISHA PARAKH',4000.00,NULL,'2025-04-20 10:28:22'),(39,'KRISH PARAKH',4300.00,NULL,'2025-05-01 17:19:24'),(40,'GAURI VERMA',4300.00,NULL,'2025-05-01 17:32:34'),(41,'KRISHNA PARAKH',4300.00,NULL,'2025-05-04 19:07:42');
/*!40000 ALTER TABLE `payments` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2026-02-22 16:23:16
