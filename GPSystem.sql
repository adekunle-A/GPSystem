-- MySQL dump 10.13  Distrib 5.7.17, for macos10.12 (x86_64)
--
-- Host: 127.0.0.1    Database: GPSystem
-- ------------------------------------------------------
-- Server version	5.7.19

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `appointments`
--

DROP TABLE IF EXISTS `appointments`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `appointments` (
  `appointmentid` int(11) NOT NULL AUTO_INCREMENT,
  `appointmentdate` varchar(45) NOT NULL,
  `starttime` varchar(45) NOT NULL,
  `endtime` varchar(45) NOT NULL,
  `doctorname` varchar(45) NOT NULL,
  `patientname` varchar(45) NOT NULL,
  `doctors_doctorid` int(11) NOT NULL,
  `patients_patientid` int(11) NOT NULL,
  `apps_appid` int(11) NOT NULL,
  PRIMARY KEY (`appointmentid`),
  UNIQUE KEY `apps_appid_UNIQUE` (`apps_appid`),
  KEY `docid_idx` (`doctors_doctorid`),
  KEY `patientid_idx` (`patients_patientid`),
  KEY `apps_appid_idx` (`apps_appid`),
  CONSTRAINT `apps_appid` FOREIGN KEY (`apps_appid`) REFERENCES `doctorsavailability` (`addappointmentid`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `docid` FOREIGN KEY (`doctors_doctorid`) REFERENCES `doctors` (`doctorid`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `patientid` FOREIGN KEY (`patients_patientid`) REFERENCES `users` (`userid`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=90 DEFAULT CHARSET=big5;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `appointments`
--

LOCK TABLES `appointments` WRITE;
/*!40000 ALTER TABLE `appointments` DISABLE KEYS */;
INSERT INTO `appointments` VALUES (20,'22/08/2017','9:30 ','10:00','Presh Steven','kevin Smith',1,7,25),(87,'13/09/2017','17:00','17:30','Shilfi Shilfi','alice bob',2,6,46),(88,'15/09/2017','15:00','15:30','Shilfi Shilfi','Stella Evans',2,1,47),(89,'19/09/2017','15:00','15:30','Shilfi Shilfi','alice bob',2,6,48);
/*!40000 ALTER TABLE `appointments` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `doctors`
--

DROP TABLE IF EXISTS `doctors`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `doctors` (
  `doctorid` int(11) NOT NULL AUTO_INCREMENT,
  `category` varchar(45) DEFAULT NULL,
  `users_userid` int(11) NOT NULL,
  PRIMARY KEY (`doctorid`),
  KEY `userid_idx` (`users_userid`),
  CONSTRAINT `userid` FOREIGN KEY (`users_userid`) REFERENCES `users` (`userid`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=big5;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `doctors`
--

LOCK TABLES `doctors` WRITE;
/*!40000 ALTER TABLE `doctors` DISABLE KEYS */;
INSERT INTO `doctors` VALUES (1,'Allegist',3),(2,'dentist ',4);
/*!40000 ALTER TABLE `doctors` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `doctorsavailability`
--

DROP TABLE IF EXISTS `doctorsavailability`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `doctorsavailability` (
  `addappointmentid` int(11) NOT NULL AUTO_INCREMENT,
  `appointmentdate` varchar(45) NOT NULL,
  `starttime` varchar(45) NOT NULL,
  `endtime` varchar(45) NOT NULL,
  `status` tinyint(1) NOT NULL,
  `doctors_doctorid` int(11) NOT NULL,
  PRIMARY KEY (`addappointmentid`),
  UNIQUE KEY `addappointmentid_UNIQUE` (`addappointmentid`),
  KEY `doctors_doctorid_idx` (`doctors_doctorid`),
  CONSTRAINT `doctors_doctorid` FOREIGN KEY (`doctors_doctorid`) REFERENCES `users` (`userid`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=66 DEFAULT CHARSET=big5;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `doctorsavailability`
--

LOCK TABLES `doctorsavailability` WRITE;
/*!40000 ALTER TABLE `doctorsavailability` DISABLE KEYS */;
INSERT INTO `doctorsavailability` VALUES (1,'02/08/2017','9:00','9:30',1,1),(2,'02/08/2017','9:30 ','10:00',1,1),(3,'02/08/2017','10:00','10:30',1,1),(4,'16/08/2017','9:30 ','10:00',1,2),(5,'16/08/2017','12:00','12:30',0,2),(6,'16/08/2017','13:00','13:30',1,2),(7,'15/08/2017','11:30','12:00',1,1),(8,'15/08/2017','12:00','12:30',1,1),(9,'15/08/2017','12:30','13:00',1,1),(12,'11/08/2017','13:00','13:30',1,1),(14,'11/08/2017','16:00','16:30',1,1),(15,'23/08/2017','15:00','15:30',1,1),(17,'10/08/2017','15:00','15:30',1,1),(18,'16/08/2017','15:30','16:00',1,1),(19,'24/08/2017','11:00','11:30',1,2),(20,'24/08/2017','11:30','12:00',1,2),(21,'24/08/2017','12:00','12:30',1,2),(22,'15/08/2017','10:00','10:30',0,1),(24,'16/08/2017','10:30','11:00',1,1),(25,'22/08/2017','9:30 ','10:00',0,1),(26,'05/09/2017','11:30','12:00',1,1),(30,'20/09/2017','16:00','16:30',1,1),(31,'08/09/2017','15:30','16:00',1,1),(32,'30/08/2017','10:00','10:30',1,2),(33,'30/08/2017','10:30','11:00',1,2),(34,'05/09/2017','17:00','17:30',1,2),(35,'05/09/2017','17:30','18:00',1,2),(36,'07/09/2017','11:00','11:30',1,1),(38,'07/09/2017','12:00','12:30',1,1),(39,'01/08/2017','9:00','9:30',1,1),(40,'31/08/2017','11:30','12:00',1,1),(42,'13/09/2017','12:30','13:00',1,1),(43,'12/09/2017','15:30','16:00',1,1),(44,'13/09/2017','14:00','14:30',1,1),(45,'07/09/2017','16:00','16:30',1,2),(46,'13/09/2017','17:00','17:30',0,2),(47,'15/09/2017','15:00','15:30',0,2),(48,'19/09/2017','15:00','15:30',0,2),(49,'19/09/2017','13:00','13:30',1,2),(50,'06/09/2017','9:30 ','10:00',1,1),(51,'05/09/2017','9:00','9:30',1,1),(52,'10/10/2017','9:30 ','10:00',1,1),(53,'10/10/2017','10:30','11:00',1,1),(54,'10/10/2017','11:30','12:00',1,1),(55,'21/09/2017','11:00','11:30',1,1),(56,'21/09/2017','11:30','12:00',1,1),(57,'21/09/2017','12:00','12:30',1,1),(58,'29/09/2017','15:30','16:00',1,2),(59,'29/09/2017','17:00','17:30',1,2),(61,'21/09/2017','19:30','20:00',1,1),(62,'15/09/2017','18:00','18:30',1,1),(64,'14/09/2017','12:30','13:00',1,1),(65,'14/09/2017','16:00','16:30',1,1);
/*!40000 ALTER TABLE `doctorsavailability` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `patienthealthrecord`
--

DROP TABLE IF EXISTS `patienthealthrecord`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `patienthealthrecord` (
  `patienthealthrecordid` int(11) NOT NULL AUTO_INCREMENT,
  `appointmentdate` varchar(45) NOT NULL,
  `symptoms` varchar(45) NOT NULL,
  `disease` varchar(45) NOT NULL,
  `treatment` varchar(45) NOT NULL,
  `doctorname` varchar(45) NOT NULL,
  `doctorid` int(11) NOT NULL,
  `patientid` int(11) NOT NULL,
  PRIMARY KEY (`patienthealthrecordid`)
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=big5;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `patienthealthrecord`
--

LOCK TABLES `patienthealthrecord` WRITE;
/*!40000 ALTER TABLE `patienthealthrecord` DISABLE KEYS */;
INSERT INTO `patienthealthrecord` VALUES (4,'2017-08-02','pain, bleeding','flu','panadol','Steven Presh',3,1),(5,'2017-08-02','vomitting','weak system','rest','Steven Presh',3,1),(6,'2017-08-02','vomitting','weak system','rest','Steven Presh',3,6),(7,'2017-08-02','pain','vomitting','panadol','Steven Presh',3,6),(8,'2017-08-02','fever, headache','flu','panadol','Steven Presh',3,1),(9,'2017-08-02','fever, headache','flu','panadol','Steven Presh',3,1),(10,'2017-09-05','headache','flu','ibuprofen','Steven Presh',3,6),(11,'2017-09-05','headache','flu','ibuprofen','Steven Presh',3,6),(12,'2017-09-05','flu','flu','','Steven Presh',3,6),(13,'2017-09-05','headache','flu','panadol','Steven Presh',3,6),(14,'2017-09-05','pain, headache','heart disease','Caramel','Steven Presh',3,6);
/*!40000 ALTER TABLE `patienthealthrecord` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `prescriptions`
--

DROP TABLE IF EXISTS `prescriptions`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `prescriptions` (
  `idprecriptions` int(11) NOT NULL AUTO_INCREMENT,
  `appdate` varchar(45) NOT NULL,
  `drug` varchar(45) NOT NULL,
  `status` varchar(45) NOT NULL,
  `patientid` int(11) NOT NULL,
  PRIMARY KEY (`idprecriptions`),
  KEY `userid_idx` (`patientid`),
  CONSTRAINT `usid` FOREIGN KEY (`patientid`) REFERENCES `users` (`userid`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=big5;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `prescriptions`
--

LOCK TABLES `prescriptions` WRITE;
/*!40000 ALTER TABLE `prescriptions` DISABLE KEYS */;
INSERT INTO `prescriptions` VALUES (1,'2017-08-02','panadol',' Pending ',1),(2,'2017-09-05','ibuprofen',' Issued ',6),(3,'2017-09-05','ibuprofen',' Issued ',6),(4,'2017-09-05','IBUPROFEN',' Issued ',6),(5,'2017-09-05','panadol',' Issued ',6),(6,'2017-09-05','Caramel',' Issued ',6);
/*!40000 ALTER TABLE `prescriptions` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `users`
--

DROP TABLE IF EXISTS `users`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `users` (
  `userid` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(45) NOT NULL,
  `fname` varchar(45) NOT NULL,
  `lname` varchar(45) NOT NULL,
  `email` varchar(45) NOT NULL,
  `address` varchar(45) NOT NULL,
  `password` varchar(100) NOT NULL,
  `role` varchar(45) NOT NULL,
  `dob` varchar(45) NOT NULL,
  `gender` char(1) NOT NULL,
  `phone` varchar(15) NOT NULL,
  `salt` varchar(60) NOT NULL,
  PRIMARY KEY (`userid`),
  UNIQUE KEY `username_UNIQUE` (`username`)
) ENGINE=InnoDB AUTO_INCREMENT=21 DEFAULT CHARSET=big5;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `users`
--

LOCK TABLES `users` WRITE;
/*!40000 ALTER TABLE `users` DISABLE KEYS */;
INSERT INTO `users` VALUES (1,'p001','Stella','Evans','p001@le.ac.uk','High street Leicester','$2a$08$DyJWqAH2t.yJq6z5sUcRaucZp/nr6ZcU9NPZX2ksMqRvSq65gYmC6','patient','2010-10-10','F','07769000701','$2a$08$DyJWqAH2t.yJq6z5sUcRau'),(2,'admin','admin1','admin1','admin@le.ac.uk','jungle street','admin101','admin','2001-11-12','M','07769000701',''),(3,'doc001','Presh','Steven','doc001@le.ac.uk','evington road leicester','$2a$08$zYQmSHOZcvQPkup0SdkNi.sbGvg60xJrZCYIxm5Kuh38R4RXW23EK','doctor','2000-10-10','M','07769000701','$2a$08$zYQmSHOZcvQPkup0SdkNi.'),(4,'doc002','Shilfi','Shilfi','doc002@le.ac.uk','Miles Lane, Leicester','$2a$08$zYQmSHOZcvQPkup0SdkNi.Mle83AhCIPZ4bN1c9ONKWRUNjfmjsX2','doctor','2010-10-10','F','07769000701','$2a$08$zYQmSHOZcvQPkup0SdkNi.'),(6,'p002','alice','bob','p002@le.ac.uk','alice street','$2a$08$zYQmSHOZcvQPkup0SdkNi.MYWiy38vSSixUc/hVxo7xZic8vm8HSm','patient','1990-10-10','F','07769000701','$2a$08$zYQmSHOZcvQPkup0SdkNi.'),(7,'p003','kevin','Smith','p003@le.ac.uk','cannon road','$2a$08$N3D3I7jjK14r0xzvSIFmY.IwPiluHoPOAmRO6sXHW4PQXnQN.G74W','patient','2001-10-10','M','07769000701','$2a$08$N3D3I7jjK14r0xzvSIFmY.'),(8,'p004','Scott','Mark','p004@le.ac.uk','student way','$2a$08$zYQmSHOZcvQPkup0SdkNi.0mjC5SyPBxPJT7j6MUQ/FXbyCvJZCQ.','patient','2010-10-10','M','07769000701','$2a$08$zYQmSHOZcvQPkup0SdkNi.'),(9,'p005','Mark','Field','p005@le.ac.uk','field street','$2a$08$CeMqu7TeFRj0dLDY1eoBbu5XEg.pz/dr7yA2FFu0Eq2IweTi2mFLe','patient','2010-10-10','M','07769000701','$2a$08$CeMqu7TeFRj0dLDY1eoBbu'),(10,'p006','Bob','Marley','p006@le.ac.uk','Marley','$2a$08$6ukG/6FFoumKAfRzOnZE2.dS186gEoliGgcOGSkszAMVdNhNphSzu','patient','10/10/2000','M','0776900701','$2a$08$6ukG/6FFoumKAfRzOnZE2.'),(20,'p007','hert','ken','p006@le.ac.uk','ken street','$2a$08$5oKqUFD0nxZWWbrH0Fh3VeYAg1m7iKXaF2giM61I3T5Eq5PumK5Be','patient','2010-10-10','F','0776900701','$2a$08$5oKqUFD0nxZWWbrH0Fh3Ve');
/*!40000 ALTER TABLE `users` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2018-02-28 23:05:44
