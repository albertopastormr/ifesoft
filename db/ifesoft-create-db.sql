-- MySQL dump 10.16  Distrib 10.1.32-MariaDB, for Win64 (AMD64)
--
-- Host: localhost    Database: ifesoft
-- ------------------------------------------------------
-- Server version	10.1.32-MariaDB

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
-- Current Database: `ifesoft`
--

CREATE DATABASE /*!32312 IF NOT EXISTS*/ `ifesoft` /*!40100 DEFAULT CHARACTER SET latin1 */;

USE `ifesoft`;


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
-- Table structure for table `participante`
--

DROP TABLE IF EXISTS `participante`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `participante` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(20) NOT NULL,
  `phone` varchar(9) NOT NULL,
  `type` varchar(20) NOT NULL,
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
-- Table structure for table `part_internacional`
--

DROP TABLE IF EXISTS `part_internacional`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `part_internacional` (
  `id` int(11) NOT NULL,
  `country` varchar(20) NOT NULL,
  PRIMARY KEY (`id`),
  CONSTRAINT `part_internacional_ibfk_1` FOREIGN KEY (`id`) REFERENCES `participante` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `part_internacional`
--

LOCK TABLES `part_internacional` WRITE;
/*!40000 ALTER TABLE `part_internacional` DISABLE KEYS */;
/*!40000 ALTER TABLE `part_internacional` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `part_nacional`
--

DROP TABLE IF EXISTS `part_nacional`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `part_nacional` (
  `id` int(11) NOT NULL,
  `region` varchar(20) NOT NULL,
  PRIMARY KEY (`id`),
  CONSTRAINT `part_nacional_ibfk_1` FOREIGN KEY (`id`) REFERENCES `participante` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `part_nacional`
--

LOCK TABLES `part_nacional` WRITE;
/*!40000 ALTER TABLE `part_nacional` DISABLE KEYS */;
/*!40000 ALTER TABLE `part_nacional` ENABLE KEYS */;
UNLOCK TABLES;



--
-- Table structure for table `asignacion`
--

DROP TABLE IF EXISTS `asignacion`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `asignacion` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `fair_id` int(11) NOT null,
  `pavilion_id` int(11) NOT null,
  `total_m2` int(11) NOT NULL,
  `used_m2` int(11) NOT NULL,
  `active` tinyint(1) NOT NULL,
  PRIMARY KEY (`id`),
  CONSTRAINT `asignacion_ibfk_1` FOREIGN KEY (`fair_id`) REFERENCES `feria` (`id`),
  CONSTRAINT `asignacion_ibfk_2` FOREIGN KEY (`pavilion_id`) REFERENCES `pabellon` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `asignacion`
--

LOCK TABLES `asignacion` WRITE;
/*!40000 ALTER TABLE `asignacion` DISABLE KEYS */;
/*!40000 ALTER TABLE `asignacion` ENABLE KEYS */;
UNLOCK TABLES;



--
-- Table structure for table `participacion`
--

DROP TABLE IF EXISTS `participacion`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `participacion` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `fair_id` int(11) NOT null,
  `client_id` int(11) NOT null,
  `active` tinyint(1) NOT NULL,
  PRIMARY KEY (`id`),
  CONSTRAINT `participacion_ibfk_1` FOREIGN KEY (`fair_id`) REFERENCES `feria` (`id`),
  CONSTRAINT `participacion_ibfk_2` FOREIGN KEY (`client_id`) REFERENCES `participante` (`id`)
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
-- Table structure for table `stand`
--

DROP TABLE IF EXISTS `stand`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `stand` (
  `id`int(11) NOT NULL AUTO_INCREMENT primary key,
  `assignation_id` int(11) NOT null,
  `participation_id` int(11) NOT null,
  `num_at_fair` int(11) NOT NULL,
  `cost` double NOT NULL,
  `used_m2` int(11) NOT NULL,
  `active` tinyint(1) NOT NULL,
  CONSTRAINT `stand_ibfk_1` FOREIGN KEY (`assignation_id`) REFERENCES `asignacion` (`id`),
  CONSTRAINT `stand_ibfk_2` FOREIGN KEY (`participation_id`) REFERENCES `participacion` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `stand`
--

LOCK TABLES `stand` WRITE;
/*!40000 ALTER TABLE `stand` DISABLE KEYS */;
/*!40000 ALTER TABLE `stand` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2018-04-02 15:49:30
