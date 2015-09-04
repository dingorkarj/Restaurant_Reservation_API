CREATE DATABASE  IF NOT EXISTS `rrs` /*!40100 DEFAULT CHARACTER SET utf8 */;
USE `rrs`;
-- MySQL dump 10.13  Distrib 5.6.24, for Win32 (x86)
--
-- Host: 127.0.0.1    Database: rrs
-- ------------------------------------------------------
-- Server version	5.7.8-rc-log

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
-- Table structure for table `customer`
--

DROP TABLE IF EXISTS `customer`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `customer` (
  `cid` int(11) NOT NULL AUTO_INCREMENT,
  `fname` varchar(45) NOT NULL,
  `lname` varchar(45) NOT NULL,
  `address` varchar(75) DEFAULT NULL,
  `phone` varchar(15) NOT NULL,
  `email` varchar(45) NOT NULL,
  `username` varchar(45) NOT NULL,
  `password` varchar(45) NOT NULL,
  PRIMARY KEY (`cid`),
  UNIQUE KEY `username_UNIQUE` (`username`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `customer`
--

LOCK TABLES `customer` WRITE;
/*!40000 ALTER TABLE `customer` DISABLE KEYS */;
INSERT INTO `customer` VALUES (1,'Jack','Smith','2910 Gentle View By-pass, Wharf, MI, 48095-3090','121-232-5645','jack.smith@abc.com','jsmith','jsmith'),(2,'Rachel','Baker','2635 Thunder Autoroute, Coffee, MI, 48092-1580','212-343-8796','rachel.baker@msn.com','rbaker','rbaker'),(3,'Eric','Cartman','6203 Indian Quail Private, Lukachukai, GA, 39811-4331','312-281-5330','eric.cartman@spc.com','ecartman','ecartman'),(4,'Kenny','McCormick','4437 Wishing Shadow Mews, Mannix, ME, 04504-2149','312-243-3761','kenny@spc.com','kmccormick','kmccormick'),(5,'Kyle','Broflovski','7234 Old Prairie Boulevard, Roundup, WI, 53429-8836','401-298-6587','kyle.b@wkp.com','kbroflovski','kbroflovski'),(6,'Stan','Marsh','852 Little Branch Plaza, Gallup, MS, 38710-4107','767-876-1232','stan.marsh@wkp.com','smarsh','smarsh'),(7,'Nick','Parker','7234 Rocky Point, Loco Hills, IN, 47973-4192','201-850-9172','nick.parker@zmail.com','nparker','nparker'),(8,'Sarah','Jackson','603 High Chase, Dakoming, ME, 04189-9144','302-191-4977','sarah.jackson@zmail.com','sjackson','sjackson'),(9,'Jim','Page','2150 Cotton Wood, Singing Brook, WI, 53561-1159','762-420-8634','jim.page@zmail.com','jpage','jpage'),(10,'Robert','Bonham','5266 Red Blossom Circle, Metropolis, MI, 48846-2828','732-769-5401','robert.bonham@zmail.com','jbonham','jbonham'),(11,'Paul','Jones','2861 Merry Centre, Hardmoney, MI, 48130-4128','402-925-4008','paul.jones@zmail.com','pjones','pjones'),(12,'Jay','Watt','2910 Gentle View By-pass, Wharf, MI, 48095-3090','121-232-5645','jack.smith@abc.com','hadesara','hadesara');
/*!40000 ALTER TABLE `customer` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `reservation`
--

DROP TABLE IF EXISTS `reservation`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `reservation` (
  `rid` int(11) NOT NULL AUTO_INCREMENT,
  `tid` int(11) NOT NULL,
  `cid` int(11) NOT NULL,
  `confirmationcode` int(11) NOT NULL,
  `date` datetime DEFAULT NULL,
  PRIMARY KEY (`rid`)
) ENGINE=InnoDB AUTO_INCREMENT=54 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `reservation`
--

LOCK TABLES `reservation` WRITE;
/*!40000 ALTER TABLE `reservation` DISABLE KEYS */;
INSERT INTO `reservation` VALUES (22,2,1,1,'2015-07-20 18:09:00'),(23,4,1,1,'2015-08-02 14:51:01'),(24,6,1,1,'2015-07-07 09:30:55'),(25,8,1,1,'2015-07-10 02:09:19'),(26,1,2,1,'2015-08-05 15:17:09'),(27,3,2,1,'2015-08-22 06:14:54'),(28,7,2,1,'2015-07-05 06:15:47'),(29,2,3,1,'2015-08-31 10:15:53'),(30,8,4,1,'2015-08-20 00:51:34'),(31,4,4,1,'2015-08-10 17:47:52'),(32,3,4,1,'2015-08-17 23:06:00'),(33,1,5,1,'2015-07-31 10:01:29'),(34,5,5,1,'2015-08-20 00:51:34'),(35,7,5,1,'2015-08-31 20:20:02'),(36,5,6,1,'2015-08-03 00:51:58'),(37,8,6,1,'2015-07-13 22:56:33'),(38,3,7,1,'2015-07-10 02:09:19'),(39,2,7,1,'2015-07-24 10:05:22'),(40,6,7,1,'2015-08-08 11:16:20'),(41,8,8,1,'2015-07-02 00:04:49'),(42,1,9,1,'2015-08-27 23:45:39'),(43,8,10,1,'2015-07-22 11:14:47'),(44,9,0,1,'2015-08-31 23:46:23'),(45,9,0,1,'2015-07-06 16:34:47'),(47,9,12,1,'2015-08-01 02:35:56'),(48,9,12,1,'2015-07-13 14:01:21'),(49,9,12,1,'2015-08-28 15:39:36');
/*!40000 ALTER TABLE `reservation` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `seating_table`
--

DROP TABLE IF EXISTS `seating_table`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `seating_table` (
  `tid` int(11) NOT NULL AUTO_INCREMENT,
  `tnumber` varchar(45) NOT NULL,
  `tseating` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`tid`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `seating_table`
--

LOCK TABLES `seating_table` WRITE;
/*!40000 ALTER TABLE `seating_table` DISABLE KEYS */;
INSERT INTO `seating_table` VALUES (1,'1','4'),(2,'2','4'),(3,'3','4'),(4,'4','6'),(5,'5','6'),(6,'6','8'),(7,'7','8'),(8,'8','2'),(9,'9','2');
/*!40000 ALTER TABLE `seating_table` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `staff`
--

DROP TABLE IF EXISTS `staff`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `staff` (
  `cid` int(11) NOT NULL,
  `fname` varchar(45) DEFAULT NULL,
  `lname` varchar(45) DEFAULT NULL,
  `username` varchar(45) NOT NULL,
  `password` varchar(45) NOT NULL,
  PRIMARY KEY (`cid`),
  UNIQUE KEY `username_UNIQUE` (`username`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `staff`
--

LOCK TABLES `staff` WRITE;
/*!40000 ALTER TABLE `staff` DISABLE KEYS */;
INSERT INTO `staff` VALUES (1,'Cheryl','Lee','clee','clee'),(2,'Michelle','Powell','mpowell','mpowell'),(3,'Kenneth','Fisher','kfisher','kfisher'),(4,'Virginia','Gonzalez','vgonzalez','vgonzalez'),(5,'Eric','Gibson','egibson','egibson'),(6,'Terry','Butler','tbutler','tbutler'),(7,'Benjamin','Hill','bhill','bhill'),(8,'Alan','Roberts','aroberts','aroberts'),(9,'Melissa','Rivera','mrivera','mrivera'),(10,'Andrea','Medina','amedina','amedina');
/*!40000 ALTER TABLE `staff` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2015-09-04 16:43:14
