-- MySQL dump 10.16  Distrib 10.2.14-MariaDB, for Win64 (AMD64)
--
-- Host: localhost    Database: ifesoft
-- ------------------------------------------------------
-- Server version	10.2.14-MariaDB

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
-- Table structure for table `asignacion`
--

DROP TABLE IF EXISTS `asignacion`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `asignacion` (
  `fair_id` int(11) NOT NULL,
  `pavilion_id` int(11) NOT NULL,
  `stand_id` int(11) NOT NULL,
  `used_m2` int(11) NOT NULL,
  `active` tinyint(1) NOT NULL,
  PRIMARY KEY (`fair_id`,`pavilion_id`,`stand_id`),
  KEY `pavilion_id` (`pavilion_id`),
  KEY `stand_id` (`stand_id`),
  CONSTRAINT `asignacion_ibfk_1` FOREIGN KEY (`fair_id`) REFERENCES `feria` (`id`),
  CONSTRAINT `asignacion_ibfk_2` FOREIGN KEY (`pavilion_id`) REFERENCES `pabellon` (`id`),
  CONSTRAINT `asignacion_ibfk_3` FOREIGN KEY (`stand_id`) REFERENCES `stand` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `asignacion`
--

LOCK TABLES `asignacion` WRITE;
/*!40000 ALTER TABLE `asignacion` DISABLE KEYS */;
/*!40000 ALTER TABLE `asignacion` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `feria`
--

DROP TABLE IF EXISTS `feria`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `feria` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(20) NOT NULL,
  `description` varchar(155) DEFAULT NULL,
  `initDate` date NOT NULL,
  `endDate` date NOT NULL,
  `active` tinyint(1) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `feria`
--

LOCK TABLES `feria` WRITE;
/*!40000 ALTER TABLE `feria` DISABLE KEYS */;
/*!40000 ALTER TABLE `feria` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `pabellon`
--

DROP TABLE IF EXISTS `pabellon`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `pabellon` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `capacity` int(11) NOT NULL,
  `total_m2` int(11) NOT NULL,
  `util_m2` int(11) NOT NULL,
  `active` tinyint(1) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `pabellon`
--

LOCK TABLES `pabellon` WRITE;
/*!40000 ALTER TABLE `pabellon` DISABLE KEYS */;
/*!40000 ALTER TABLE `pabellon` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `participacion`
--

DROP TABLE IF EXISTS `participacion`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `participacion` (
  `fair_id` int(11) NOT NULL,
  `client_id` int(11) NOT NULL,
  `stand_id` int(11) NOT NULL,
  `active` tinyint(1) NOT NULL,
  PRIMARY KEY (`fair_id`,`client_id`,`stand_id`),
  KEY `client_id` (`client_id`),
  KEY `stand_id` (`stand_id`),
  CONSTRAINT `participacion_ibfk_1` FOREIGN KEY (`fair_id`) REFERENCES `feria` (`id`),
  CONSTRAINT `participacion_ibfk_2` FOREIGN KEY (`client_id`) REFERENCES `participante` (`id`),
  CONSTRAINT `participacion_ibfk_3` FOREIGN KEY (`stand_id`) REFERENCES `stand` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `participacion`
--

LOCK TABLES `participacion` WRITE;
/*!40000 ALTER TABLE `participacion` DISABLE KEYS */;
/*!40000 ALTER TABLE `participacion` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `participante`
--

DROP TABLE IF EXISTS `participante`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `participante` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(20) NOT NULL,
  `phone` int(11) NOT NULL,
  `active` tinyint(1) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `participante`
--

LOCK TABLES `participante` WRITE;
/*!40000 ALTER TABLE `participante` DISABLE KEYS */;
/*!40000 ALTER TABLE `participante` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `participante_internacional`
--

DROP TABLE IF EXISTS `participante_internacional`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `participante_internacional` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `country` varchar(20) NOT NULL,
  PRIMARY KEY (`id`),
  CONSTRAINT `participante_internacional_ibfk_1` FOREIGN KEY (`id`) REFERENCES `participante` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `participante_internacional`
--

LOCK TABLES `participante_internacional` WRITE;
/*!40000 ALTER TABLE `participante_internacional` DISABLE KEYS */;
/*!40000 ALTER TABLE `participante_internacional` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `participante_nacional`
--

DROP TABLE IF EXISTS `participante_nacional`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `participante_nacional` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `region` varchar(20) NOT NULL,
  PRIMARY KEY (`id`),
  CONSTRAINT `participante_nacional_ibfk_1` FOREIGN KEY (`id`) REFERENCES `participante` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `participante_nacional`
--

LOCK TABLES `participante_nacional` WRITE;
/*!40000 ALTER TABLE `participante_nacional` DISABLE KEYS */;
/*!40000 ALTER TABLE `participante_nacional` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `stand`
--

DROP TABLE IF EXISTS `stand`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `stand` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `num_at_fair` int(11) NOT NULL,
  `cost` double NOT NULL,
  `total_m2` int(11) NOT NULL,
  `active` tinyint(1) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `stand`
--

LOCK TABLES `stand` WRITE;
/*!40000 ALTER TABLE `stand` DISABLE KEYS */;
/*!40000 ALTER TABLE `stand` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping routines for database 'ifesoft'
--
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2018-04-03 21:01:05
