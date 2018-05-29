-- MySQL dump 10.13  Distrib 5.6.10, for Win64 (x86_64)
--
-- Host: localhost    Database: school
-- ------------------------------------------------------
-- Server version	5.6.10

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
-- Table structure for table `acc_accountdetails`
--

DROP TABLE IF EXISTS `acc_accountdetails`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `acc_accountdetails` (
  `accountdetailsid` int(11) NOT NULL AUTO_INCREMENT,
  `accountsubgroupmasterid` int(11) DEFAULT NULL,
  `accountname` varchar(100) NOT NULL,
  `accountgroupid` int(11) NOT NULL,
  `branchid` int(11) DEFAULT NULL,
  PRIMARY KEY (`accountdetailsid`),
  KEY `accountdetailsfk_idx` (`accountsubgroupmasterid`),
  CONSTRAINT `accountdetailsfk` FOREIGN KEY (`accountsubgroupmasterid`) REFERENCES `acc_accountsubgroupmaster` (`accountsubgroupmasterid`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=34 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `acc_accountdetails`
--

LOCK TABLES `acc_accountdetails` WRITE;
/*!40000 ALTER TABLE `acc_accountdetails` DISABLE KEYS */;
INSERT INTO `acc_accountdetails` VALUES (23,23,'transportation a/c',4,0),(24,38,'musaib a/c',7,0),(25,38,'wasif a/c',7,0),(26,25,'buliding rent',7,0),(27,25,'electricity bill',7,0),(28,25,'postage expenses',7,0),(29,24,'fees collection',5,0),(30,18,'musab',1,0),(31,2,'cash in hand',2,0),(32,2,'pu a/c',2,0),(33,18,'capital A/c',1,0);
/*!40000 ALTER TABLE `acc_accountdetails` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `acc_accountdetailsbalance`
--

DROP TABLE IF EXISTS `acc_accountdetailsbalance`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `acc_accountdetailsbalance` (
  `accountdetailsbalanceid` int(11) NOT NULL AUTO_INCREMENT,
  `accountdetailsid` int(11) DEFAULT NULL,
  `openingbalance` decimal(20,4) DEFAULT NULL,
  `currentbalance` decimal(20,4) DEFAULT NULL,
  `financialid` int(11) DEFAULT NULL,
  `crdr` varchar(40) DEFAULT NULL,
  `enteredon` date DEFAULT NULL,
  `branchid` int(11) DEFAULT NULL,
  PRIMARY KEY (`accountdetailsbalanceid`),
  KEY `accoutdetailsid_idx` (`accountdetailsid`),
  KEY `financialidfk_idx` (`financialid`),
  CONSTRAINT `accoutdetailsidfk` FOREIGN KEY (`accountdetailsid`) REFERENCES `acc_accountdetails` (`accountdetailsid`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `financialidfk` FOREIGN KEY (`financialid`) REFERENCES `acc_financialaccountingyear` (`financialid`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=34 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `acc_accountdetailsbalance`
--

LOCK TABLES `acc_accountdetailsbalance` WRITE;
/*!40000 ALTER TABLE `acc_accountdetailsbalance` DISABLE KEYS */;
INSERT INTO `acc_accountdetailsbalance` VALUES (23,23,0.0000,0.0000,6,'Dr','2018-05-13',0),(24,24,0.0000,NULL,6,'Dr',NULL,0),(25,25,0.0000,0.0000,6,'Dr','2018-05-13',0),(26,26,0.0000,0.0000,6,'Dr','2018-05-13',0),(27,27,0.0000,0.0000,6,'Dr','2018-05-13',0),(28,28,0.0000,100.0000,6,'Dr','2018-05-13',0),(29,29,0.0000,1000.0000,6,'Cr','2018-05-13',0),(30,30,50000.0000,50000.0000,6,'Cr','2018-05-13',0),(31,31,10000.0000,10900.0000,6,'Dr','2018-05-13',0),(32,32,0.0000,-10000.0000,6,'Dr','2018-05-13',0),(33,33,500000.0000,510000.0000,6,'Cr','2018-05-13',0);
/*!40000 ALTER TABLE `acc_accountdetailsbalance` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `acc_accountgroupmaster`
--

DROP TABLE IF EXISTS `acc_accountgroupmaster`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `acc_accountgroupmaster` (
  `accountgroupid` int(11) NOT NULL AUTO_INCREMENT,
  `accountgroupname` varchar(150) NOT NULL,
  `branchid` int(11) DEFAULT NULL,
  PRIMARY KEY (`accountgroupid`)
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `acc_accountgroupmaster`
--

LOCK TABLES `acc_accountgroupmaster` WRITE;
/*!40000 ALTER TABLE `acc_accountgroupmaster` DISABLE KEYS */;
INSERT INTO `acc_accountgroupmaster` VALUES (1,'Capital',0),(2,'Current Assets',0),(3,'Current Liabilities',0),(4,'Direct Expense',0),(5,'Direct Income',0),(6,'Fixed Assets',0),(7,'Indirect Expense',0),(8,'Indirect Income',0),(9,'Investments',0),(10,'Loans(Asset)',0),(11,'Loans(Liability)',0),(12,'Miscellaneous Expenses(Assets)',0),(13,'Reserves',0);
/*!40000 ALTER TABLE `acc_accountgroupmaster` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `acc_accountsubgroupmaster`
--

DROP TABLE IF EXISTS `acc_accountsubgroupmaster`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `acc_accountsubgroupmaster` (
  `accountsubgroupmasterid` int(11) NOT NULL AUTO_INCREMENT,
  `accountsubgroupname` varchar(100) DEFAULT NULL,
  `accountgroupid` int(11) DEFAULT NULL,
  `branchid` int(11) DEFAULT NULL,
  PRIMARY KEY (`accountsubgroupmasterid`),
  KEY `accountgroupid_idx` (`accountgroupid`),
  CONSTRAINT `accountgroupid` FOREIGN KEY (`accountgroupid`) REFERENCES `acc_accountgroupmaster` (`accountgroupid`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=39 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `acc_accountsubgroupmaster`
--

LOCK TABLES `acc_accountsubgroupmaster` WRITE;
/*!40000 ALTER TABLE `acc_accountsubgroupmaster` DISABLE KEYS */;
INSERT INTO `acc_accountsubgroupmaster` VALUES (1,'Bank',2,0),(2,'Cash',2,0),(3,'Inventory',2,0),(4,'Loans and Advances',2,0),(5,'Sundry Debtors',2,0),(6,'Provisions',3,0),(7,'Sundry Creditors for Expenses',3,0),(8,'Sundry Creditors for Purchases',3,0),(9,'Building',6,0),(10,'Furniture',6,0),(11,'Land',6,0),(12,'Plant & Machinery',6,0),(13,'Bank Fixed Deposits',9,0),(14,'Shares & Debentures',9,0),(15,'Secured',11,0),(16,'Unsecured',11,0),(18,'',1,0),(20,'',10,0),(21,'',12,0),(22,'',13,0),(23,'',4,0),(24,'',5,0),(25,'',7,0),(26,'',8,0),(37,'new capital sub group',1,0),(38,'salary',7,0);
/*!40000 ALTER TABLE `acc_accountsubgroupmaster` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `acc_contratransactions`
--

DROP TABLE IF EXISTS `acc_contratransactions`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `acc_contratransactions` (
  `transactionsid` int(11) NOT NULL AUTO_INCREMENT,
  `draccountid` int(11) DEFAULT NULL,
  `craccountid` int(11) DEFAULT NULL,
  `dramount` decimal(10,5) DEFAULT NULL,
  `cramount` decimal(10,5) DEFAULT NULL,
  `vouchertype` int(11) DEFAULT NULL,
  `date` date DEFAULT NULL,
  `narration` varchar(500) DEFAULT NULL,
  `financialyear` int(11) NOT NULL,
  `branchid` int(11) DEFAULT NULL,
  PRIMARY KEY (`transactionsid`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `acc_contratransactions`
--

LOCK TABLES `acc_contratransactions` WRITE;
/*!40000 ALTER TABLE `acc_contratransactions` DISABLE KEYS */;
INSERT INTO `acc_contratransactions` VALUES (1,1,1,0.00000,0.00000,3,'2018-03-13','',4,0),(2,1,1,0.00000,0.00000,3,'2018-03-13','',4,0),(3,1,1,0.00000,0.00000,3,'2018-03-13','',4,0),(4,1,1,0.00000,0.00000,3,'2018-03-13','',4,0),(5,1,1,0.00000,0.00000,3,'2018-03-13','',4,0),(6,7,1,0.00000,0.00000,3,'2018-03-13','',4,0),(7,7,1,0.00000,0.00000,3,'2018-03-13','',4,0),(8,7,1,50.00000,50.00000,3,'2018-03-13','',4,0);
/*!40000 ALTER TABLE `acc_contratransactions` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `acc_financialaccountingyear`
--

DROP TABLE IF EXISTS `acc_financialaccountingyear`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `acc_financialaccountingyear` (
  `financialid` int(11) NOT NULL AUTO_INCREMENT,
  `financialstartdate` date NOT NULL,
  `financialenddate` date NOT NULL,
  `active` varchar(10) NOT NULL,
  `branchid` int(11) DEFAULT NULL,
  PRIMARY KEY (`financialid`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `acc_financialaccountingyear`
--

LOCK TABLES `acc_financialaccountingyear` WRITE;
/*!40000 ALTER TABLE `acc_financialaccountingyear` DISABLE KEYS */;
INSERT INTO `acc_financialaccountingyear` VALUES (1,'2018-02-01','2018-02-27','no',0),(2,'2018-01-01','2018-02-27','no',0),(3,'2018-02-01','2018-02-15','no',0),(4,'2018-04-01','2019-03-31','no',0),(5,'2018-02-01','2018-02-28','no',0),(6,'2017-04-01','2018-03-31','yes',0);
/*!40000 ALTER TABLE `acc_financialaccountingyear` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `acc_journaltransactions`
--

DROP TABLE IF EXISTS `acc_journaltransactions`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `acc_journaltransactions` (
  `transactionsid` int(11) NOT NULL AUTO_INCREMENT,
  `draccountid` int(11) DEFAULT NULL,
  `craccountid` int(11) DEFAULT NULL,
  `dramount` decimal(10,5) DEFAULT NULL,
  `cramount` decimal(10,5) DEFAULT NULL,
  `vouchertype` int(11) DEFAULT NULL,
  `date` date DEFAULT NULL,
  `narration` varchar(500) DEFAULT NULL,
  `financialyear` int(11) NOT NULL,
  `branchid` int(11) DEFAULT NULL,
  PRIMARY KEY (`transactionsid`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `acc_journaltransactions`
--

LOCK TABLES `acc_journaltransactions` WRITE;
/*!40000 ALTER TABLE `acc_journaltransactions` DISABLE KEYS */;
INSERT INTO `acc_journaltransactions` VALUES (1,11,10,5000.00000,5000.00000,4,'2018-03-16','purchased HP 404 I7 computer for asif office',4,0),(2,3,10,280.00000,5000.00000,4,'2018-03-20','',4,0);
/*!40000 ALTER TABLE `acc_journaltransactions` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `acc_paymenttransactions`
--

DROP TABLE IF EXISTS `acc_paymenttransactions`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `acc_paymenttransactions` (
  `transactionsid` int(11) NOT NULL AUTO_INCREMENT,
  `draccountid` int(11) DEFAULT NULL,
  `craccountid` int(11) DEFAULT NULL,
  `dramount` decimal(10,5) DEFAULT NULL,
  `cramount` decimal(10,5) DEFAULT NULL,
  `vouchertype` int(11) DEFAULT NULL,
  `date` date DEFAULT NULL,
  `narration` varchar(500) DEFAULT NULL,
  `financialyear` int(11) NOT NULL,
  `cancelvoucher` varchar(5) DEFAULT NULL,
  `branchid` int(11) DEFAULT NULL,
  PRIMARY KEY (`transactionsid`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `acc_paymenttransactions`
--

LOCK TABLES `acc_paymenttransactions` WRITE;
/*!40000 ALTER TABLE `acc_paymenttransactions` DISABLE KEYS */;
INSERT INTO `acc_paymenttransactions` VALUES (1,31,28,100.00000,100.00000,2,'2018-05-13','Being Cash Paid to Postage for Office Letter Bidar to Gulbarga.',6,'no',0),(2,32,33,10000.00000,10000.00000,2,'2018-05-13','paid to pu',6,'no',0);
/*!40000 ALTER TABLE `acc_paymenttransactions` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `acc_receipttransactions`
--

DROP TABLE IF EXISTS `acc_receipttransactions`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `acc_receipttransactions` (
  `transactionsid` int(11) NOT NULL AUTO_INCREMENT,
  `draccountid` int(11) DEFAULT NULL,
  `craccountid` int(11) DEFAULT NULL,
  `dramount` decimal(10,5) DEFAULT NULL,
  `cramount` decimal(10,5) DEFAULT NULL,
  `vouchertype` int(11) DEFAULT NULL,
  `date` date DEFAULT NULL,
  `narration` varchar(500) DEFAULT NULL,
  `financialyear` int(11) NOT NULL,
  `cancelvoucher` varchar(5) DEFAULT NULL,
  `branchid` int(11) DEFAULT NULL,
  PRIMARY KEY (`transactionsid`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `acc_receipttransactions`
--

LOCK TABLES `acc_receipttransactions` WRITE;
/*!40000 ALTER TABLE `acc_receipttransactions` DISABLE KEYS */;
INSERT INTO `acc_receipttransactions` VALUES (1,31,28,100.00000,100.00000,1,'2018-05-13','Being Cash Paid to Postage Exp ',6,'yes',0),(2,31,29,1000.00000,1000.00000,1,'2018-05-13','Being Cash Received From Iqbal ',6,'no',0);
/*!40000 ALTER TABLE `acc_receipttransactions` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `adminexpenses`
--

DROP TABLE IF EXISTS `adminexpenses`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `adminexpenses` (
  `idAdminExpenses` int(11) NOT NULL AUTO_INCREMENT,
  `item description` varchar(500) DEFAULT NULL,
  `priceofitem` int(11) DEFAULT NULL,
  `entrydate` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `quantity` int(11) DEFAULT NULL,
  `branchid` int(11) DEFAULT NULL,
  PRIMARY KEY (`idAdminExpenses`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `adminexpenses`
--

LOCK TABLES `adminexpenses` WRITE;
/*!40000 ALTER TABLE `adminexpenses` DISABLE KEYS */;
INSERT INTO `adminexpenses` VALUES (1,'pen',343,'2018-10-09 18:30:00',23,0),(2,'paper',250,'2020-04-03 18:30:00',100,0),(3,'1',11,'2020-05-04 18:30:00',111,0);
/*!40000 ALTER TABLE `adminexpenses` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `att_attendancemaster`
--

DROP TABLE IF EXISTS `att_attendancemaster`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `att_attendancemaster` (
  `idattendancemaster` int(11) NOT NULL AUTO_INCREMENT,
  `attendeeid` varchar(10) NOT NULL,
  `intime` varchar(10) DEFAULT NULL,
  `outtime` varchar(10) DEFAULT NULL,
  `weeklyoff` varchar(50) DEFAULT NULL,
  `holidayname` varchar(200) DEFAULT NULL,
  `branchid` int(11) DEFAULT NULL,
  PRIMARY KEY (`idattendancemaster`)
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `att_attendancemaster`
--

LOCK TABLES `att_attendancemaster` WRITE;
/*!40000 ALTER TABLE `att_attendancemaster` DISABLE KEYS */;
INSERT INTO `att_attendancemaster` VALUES (2,'2','07:00:AM','07:00:AM','1,2','3,4,5',0),(8,'00011','07:00 AM',NULL,'1','12',0),(10,'1','00:24','12:24','1,2','11,12,13',0),(11,'1','09:00','15:00','1,2','3,4,5,6,12',0),(12,'2','09:00','15:00','1,2','3,4,5,6,12',0),(13,'1','01:12','13:12','1','3,4',0),(14,'2','01:12','13:12','1','3,4',0),(15,'9','08:16','16:16','2,3','3,4,5,6,10,11,12,13',0),(16,'13','02:33','01:33','1,2','3,4,5,13',0);
/*!40000 ALTER TABLE `att_attendancemaster` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `att_holidaysmaster`
--

DROP TABLE IF EXISTS `att_holidaysmaster`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `att_holidaysmaster` (
  `shid` int(11) NOT NULL AUTO_INCREMENT,
  `fromdate` date NOT NULL,
  `todate` date NOT NULL,
  `holidayname` varchar(40) NOT NULL,
  `academicyear` varchar(10) NOT NULL,
  `branchid` int(11) DEFAULT NULL,
  PRIMARY KEY (`shid`)
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `att_holidaysmaster`
--

LOCK TABLES `att_holidaysmaster` WRITE;
/*!40000 ALTER TABLE `att_holidaysmaster` DISABLE KEYS */;
INSERT INTO `att_holidaysmaster` VALUES (3,'2016-01-16','2017-01-16','Republic day','2017/18',0),(4,'2017-01-16','2017-01-16','MuZeee','2017/18',0),(5,'2017-01-17','2017-01-17','test','2017/18',0),(6,'2017-01-17','2017-01-17','Republic day','2017/18',0),(7,'2016-01-16','2017-01-16','MuZeee','2017/18',0),(8,'2017-01-17','2017-01-17','test','2017/18',0),(9,'2018-01-02','2018-01-23','Republic day','2017/18',0),(10,'2018-01-01','2018-01-30','Republic day','2017/18',0),(11,'2018-01-01','2018-01-08','test','2017/18',0),(12,'2018-01-03','2018-01-31','winter vacation','2017/18',0),(13,'2018-02-02','2018-02-02','FEB','2017/18',0);
/*!40000 ALTER TABLE `att_holidaysmaster` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `att_staffdailyattendance`
--

DROP TABLE IF EXISTS `att_staffdailyattendance`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `att_staffdailyattendance` (
  `attendanceid` int(11) NOT NULL AUTO_INCREMENT,
  `attendeeid` varchar(45) NOT NULL,
  `intime` varchar(15) DEFAULT NULL,
  `outtime` varchar(15) DEFAULT NULL,
  `date` date DEFAULT NULL,
  `attendancestatus` varchar(45) DEFAULT NULL,
  `academicyear` varchar(10) DEFAULT NULL,
  `branchid` int(11) DEFAULT NULL,
  PRIMARY KEY (`attendanceid`)
) ENGINE=InnoDB AUTO_INCREMENT=36 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `att_staffdailyattendance`
--

LOCK TABLES `att_staffdailyattendance` WRITE;
/*!40000 ALTER TABLE `att_staffdailyattendance` DISABLE KEYS */;
INSERT INTO `att_staffdailyattendance` VALUES (1,'ZK0T','10:00 AM','10:00 AM','2018-01-31','A','2017/18',0),(2,'ZK0T','01:05','13:05','2018-02-01','a','2017/18',0),(3,'HX4I','00:40','01:40','2018-02-01','a','2017/18',0),(5,'ZK0T','01:05','13:05','2018-02-03','P','2017/18',0),(6,'ZK0T','01:05','13:05','2018-02-04','P','2017/18',0),(7,'ZK0T','01:05','13:05','2018-02-05','P','2017/18',0),(8,'ZK0T','01:05','13:05','2018-02-06','A','2017/18',0),(9,'ZK0T','01:05','13:05','2018-02-07','P','2017/18',0),(10,'ZK0T','01:05','13:05','2018-02-08','P','2017/18',0),(11,'ZK0T','01:05','13:05','2018-02-09','P','2017/18',0),(12,'ZK0T','01:05','13:05','2018-02-10','A','2017/18',0),(13,'ZK0T','01:05','13:05','2018-02-11','P','2017/18',0),(14,'ZK0T','01:05','13:05','2018-02-12','P','2017/18',0),(15,'ZK0T','01:05','13:05','2018-02-13','P','2017/18',0),(16,'ZK0T','01:05','13:05','2018-02-14','P','2017/18',0),(17,'ZK0T','01:05','13:05','2018-02-15','L','2017/18',0),(18,'ZK0T','01:05','13:05','2018-02-16','P','2017/18',0),(19,'ZK0T','01:05','13:05','2018-02-17','P','2017/18',0),(20,'ZK0T','01:05','13:05','2018-02-18','P','2017/18',0),(21,'ZK0T','01:05','13:05','2018-02-19','P','2017/18',0),(22,'ZK0T','01:05','13:05','2018-02-20','L','2017/18',0),(23,'ZK0T','01:05','13:05','2018-02-21','P','2017/18',0),(24,'ZK0T','01:05','13:05','2018-02-22','P','2017/18',0),(25,'ZK0T','01:05','13:05','2018-02-23','P','2017/18',0),(26,'ZK0T','01:05','13:05','2018-02-24','P','2017/18',0),(27,'ZK0T','01:05','13:05','2018-02-25','H','2017/18',0),(28,'ZK0T','01:05','13:05','2018-02-26','P','2017/18',0),(29,'ZK0T','01:05','13:05','2018-02-27','P','2017/18',0),(30,'ZK0T','01:05','13:05','2018-02-28','P','2017/18',0),(31,'ZK0T',NULL,NULL,'2018-02-02','H','2017/18',0),(32,'HX4I',NULL,NULL,'2018-02-04','H','2017/18',0),(33,'HX4I',NULL,NULL,'2018-02-11','H','2017/18',0),(34,'HX4I',NULL,NULL,'2018-02-18','H','2017/18',0),(35,'HX4I',NULL,NULL,'2018-02-19','H','2017/18',0);
/*!40000 ALTER TABLE `att_staffdailyattendance` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `att_studentdailyattendance`
--

DROP TABLE IF EXISTS `att_studentdailyattendance`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `att_studentdailyattendance` (
  `attendanceid` int(11) NOT NULL AUTO_INCREMENT,
  `attendeeid` varchar(45) NOT NULL,
  `intime` varchar(45) DEFAULT NULL,
  `outtime` varchar(45) DEFAULT NULL,
  `date` date DEFAULT NULL,
  `attendancestatus` varchar(15) DEFAULT NULL,
  `academicyear` varchar(10) DEFAULT NULL,
  `branchid` int(11) DEFAULT NULL,
  PRIMARY KEY (`attendanceid`),
  KEY `stdfk_idx` (`attendeeid`),
  CONSTRAINT `stdfk` FOREIGN KEY (`attendeeid`) REFERENCES `student` (`studentexternalid`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=200 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `att_studentdailyattendance`
--

LOCK TABLES `att_studentdailyattendance` WRITE;
/*!40000 ALTER TABLE `att_studentdailyattendance` DISABLE KEYS */;
INSERT INTO `att_studentdailyattendance` VALUES (1,'HHJ7','10:00 AM','10:00 AM','2018-01-27','P','2017/18',0),(2,'RVWZ','10:00 AM','10:00 AM','2018-01-27','P','2017/18',0),(3,'QTHO','10:00 AM','10:00 AM','2018-01-27','P','2017/18',0),(4,'HHJ7','10:00 AM','10:00 AM','2017-12-27','P','2017/18',0),(5,'HHJ7','10:00 AM','10:00 AM','2017-11-27','P','2017/18',0),(6,'HHJ7','10:00 AM','10:00 AM','2017-10-27','A','2017/18',0),(7,'HHJ7','10:00 AM','10:00 AM','2017-09-27','A','2017/18',0),(8,'HHJ7','10:00 AM','10:00 AM','2017-08-27','A','2017/18',0),(9,'HHJ7','10:00 AM','10:00 AM','2017-07-27','A','2017/18',0),(10,'HHJ7','10:00 AM','10:00 AM','2017-06-27','A','2017/18',0),(11,'HHJ7','10:00 AM','10:00 AM','2017-05-27','P','2017/18',0),(12,'HHJ7','10:00 AM','10:00 AM','2017-04-27','P','2017/18',0),(13,'HHJ7','10:00 AM','10:00 AM','2017-03-27','P','2017/18',0),(14,'HHJ7','00:00',NULL,'2018-01-29','P','2017/18',0),(17,'QTHO','00:00',NULL,'2018-01-29','A','2017/18',0),(18,'RVWZ','00:00',NULL,'2018-01-29','A','2017/18',0),(19,'HHJ7',NULL,NULL,'2018-01-30','A','2017/18',0),(20,'RVWZ',NULL,NULL,'2018-01-30','A','2017/18',0),(23,'QTHO',NULL,NULL,'2018-01-30','H','2017/18',0),(24,'HHJ7','10:00 AM','10:00 AM','2018-01-28','P','2017/18',0),(25,'HHJ7','10:00 AM','10:00 AM','2018-01-26','P','2017/18',0),(26,'HHJ7','10:00 AM','10:00 AM','2018-01-25','P','2017/18',0),(27,'HHJ7','10:00 AM','10:00 AM','2018-01-24','P','2017/18',0),(28,'HHJ7','10:00 AM','10:00 AM','2018-01-23','P','2017/18',0),(29,'HHJ7','10:00 AM','10:00 AM','2018-01-22','A','2017/18',0),(30,'HHJ7','10:00 AM','10:00 AM','2018-01-21','P','2017/18',0),(31,'HHJ7','10:00 AM','10:00 AM','2018-01-20','P','2017/18',0),(32,'HHJ7','10:00 AM','10:00 AM','2018-01-19','P','2017/18',0),(33,'HHJ7','10:00 AM','10:00 AM','2018-01-18','P','2017/18',0),(34,'HHJ7','10:00 AM','10:00 AM','2018-01-17','A','2017/18',0),(35,'HHJ7','10:00 AM','10:00 AM','2018-01-16','P','2017/18',0),(36,'HHJ7','10:00 AM','10:00 AM','2018-01-15','P','2017/18',0),(37,'HHJ7','10:00 AM','10:00 AM','2018-01-14','P','2017/18',0),(38,'HHJ7','10:00 AM','10:00 AM','2018-01-13','P','2017/18',0),(39,'HHJ7','10:00 AM','10:00 AM','2018-01-12','A','2017/18',0),(40,'HHJ7','10:00 AM','10:00 AM','2018-01-11','P','2017/18',0),(41,'HHJ7','10:00 AM','10:00 AM','2018-01-10','P','2017/18',0),(42,'HHJ7','10:00 AM','10:00 AM','2018-01-09','A','2017/18',0),(43,'HHJ7','10:00 AM','10:00 AM','2018-01-08','P','2017/18',0),(44,'HHJ7','10:00 AM','10:00 AM','2018-01-07','P','2017/18',0),(45,'HHJ7','10:00 AM','10:00 AM','2018-01-06','P','2017/18',0),(46,'HHJ7','10:00 AM','10:00 AM','2018-01-05','P','2017/18',0),(47,'HHJ7','10:00 AM','10:00 AM','2018-01-04','P','2017/18',0),(48,'HHJ7','10:00 AM','10:00 AM','2018-01-03','P','2017/18',0),(49,'HHJ7','10:00 AM','10:00 AM','2018-01-02','P','2017/18',0),(50,'HHJ7','10:00 AM','10:00 AM','2018-01-01','P','2017/18',0),(51,'HHJ7','10:00 AM','10:00 AM','2018-01-31','A','2017/18',0),(58,'HHJ7','10:00 AM','10:30 PM','2018-02-01','A','2017/18',0),(59,'HHJ7','00:00',NULL,'2018-02-14','P','2017/18',0),(60,'RVWZ','00:00',NULL,'2018-02-14','P','2017/18',0),(61,'QTHO','00:00',NULL,'2018-02-14','P','2017/18',0),(62,'HHJ7',NULL,NULL,'2018-02-18','H','2017/18',0),(63,'RVWZ',NULL,NULL,'2018-02-18','H','2017/18',0),(64,'QTHO',NULL,NULL,'2018-02-18','H','2017/18',0),(65,'PAUR',NULL,NULL,'2018-02-18','H','2017/18',0),(66,'HHJ7','00:00',NULL,'2018-03-06','a','2017/18',0),(67,'RVWZ','00:00',NULL,'2018-03-06','P','2017/18',0),(68,'QTHO','00:00',NULL,'2018-03-06','a','2017/18',0),(69,'HHJ7','00:00',NULL,'2018-03-07','P','2017/18',0),(70,'RVWZ','00:00',NULL,'2018-03-07','a','2017/18',0),(71,'QTHO','00:00',NULL,'2018-03-07','P','2017/18',0),(72,'HHJ7',NULL,NULL,'2018-03-11','H','2017/18',0),(73,'RVWZ',NULL,NULL,'2018-03-11','H','2017/18',0),(74,'QTHO',NULL,NULL,'2018-03-11','H','2017/18',0),(75,'PAUR',NULL,NULL,'2018-03-11','H','2017/18',0),(76,'HHJ7','00:00',NULL,'2018-03-27','P','2017/18',0),(77,'RVWZ','00:00',NULL,'2018-03-27','P','2017/18',0),(78,'QTHO','00:00',NULL,'2018-03-27','P','2017/18',0),(79,'HHJ7','00:00',NULL,'2018-04-03','P','2017/18',0),(80,'RVWZ','00:00',NULL,'2018-04-03','P','2017/18',0),(81,'QTHO','00:00',NULL,'2018-04-03','a','2017/18',0),(82,'RQEO','00:00',NULL,'2018-04-03','P','2017/18',0),(83,'HHJ7',NULL,NULL,'2018-04-15','H','2017/18',0),(84,'RVWZ',NULL,NULL,'2018-04-15','H','2017/18',0),(85,'QTHO',NULL,NULL,'2018-04-15','H','2017/18',0),(86,'PAUR',NULL,NULL,'2018-04-15','H','2017/18',0),(87,'RQEO',NULL,NULL,'2018-04-15','H','2017/18',0),(88,'C801',NULL,NULL,'2018-04-15','H','2017/18',0),(89,'SF5K',NULL,NULL,'2018-04-15','H','2017/18',0),(90,'0GXP',NULL,NULL,'2018-04-15','H','2017/18',0),(91,'M3O6',NULL,NULL,'2018-04-15','H','2017/18',0),(92,'P3AG',NULL,NULL,'2018-04-15','H','2017/18',0),(93,'TF26',NULL,NULL,'2018-04-15','H','2017/18',0),(94,'JDB5',NULL,NULL,'2018-04-15','H','2017/18',0),(95,'Y6QP',NULL,NULL,'2018-04-15','H','2017/18',0),(96,'7908',NULL,NULL,'2018-04-15','H','2017/18',0),(97,'PP78',NULL,NULL,'2018-04-15','H','2017/18',0),(98,'25KF',NULL,NULL,'2018-04-15','H','2017/18',0),(99,'B99O',NULL,NULL,'2018-04-15','H','2017/18',0),(100,'HHJ7',NULL,NULL,'2018-04-22','H','2017/18',0),(101,'RVWZ',NULL,NULL,'2018-04-22','H','2017/18',0),(102,'QTHO',NULL,NULL,'2018-04-22','H','2017/18',0),(103,'PAUR',NULL,NULL,'2018-04-22','H','2017/18',0),(104,'RQEO',NULL,NULL,'2018-04-22','H','2017/18',0),(105,'C801',NULL,NULL,'2018-04-22','H','2017/18',0),(106,'SF5K',NULL,NULL,'2018-04-22','H','2017/18',0),(107,'0GXP',NULL,NULL,'2018-04-22','H','2017/18',0),(108,'M3O6',NULL,NULL,'2018-04-22','H','2017/18',0),(109,'P3AG',NULL,NULL,'2018-04-22','H','2017/18',0),(110,'TF26',NULL,NULL,'2018-04-22','H','2017/18',0),(111,'JDB5',NULL,NULL,'2018-04-22','H','2017/18',0),(112,'Y6QP',NULL,NULL,'2018-04-22','H','2017/18',0),(113,'7908',NULL,NULL,'2018-04-22','H','2017/18',0),(114,'PP78',NULL,NULL,'2018-04-22','H','2017/18',0),(115,'25KF',NULL,NULL,'2018-04-22','H','2017/18',0),(116,'B99O',NULL,NULL,'2018-04-22','H','2017/18',0),(117,'RQEO','00:00',NULL,'2018-04-23','P','2017/18',0),(118,'RQEO',NULL,NULL,'2018-03-01','P','2017/18',0),(119,'RQEO',NULL,NULL,'2018-03-02','P','2017/18',0),(120,'RQEO',NULL,NULL,'2018-03-03','P','2017/18',0),(121,'RQEO',NULL,NULL,'2018-03-04','P','2017/18',0),(122,'RQEO',NULL,NULL,'2018-03-05','P','2017/18',0),(123,'RQEO',NULL,NULL,'2018-03-06','P','2017/18',0),(124,'RQEO',NULL,NULL,'2018-03-07','P','2017/18',0),(125,'RQEO',NULL,NULL,'2018-03-08','P','2017/18',0),(126,'RQEO',NULL,NULL,'2018-03-09','P','2017/18',0),(127,'RQEO',NULL,NULL,'2018-03-10','P','2017/18',0),(128,'RQEO',NULL,NULL,'2018-03-11','P','2017/18',0),(129,'RQEO',NULL,NULL,'2018-03-12','P','2017/18',0),(130,'RQEO',NULL,NULL,'2018-03-13','H','2017/18',0),(131,'RQEO',NULL,NULL,'2018-03-14','A','2017/18',0),(132,'RQEO',NULL,NULL,'2018-03-15','A','2017/18',0),(133,'RQEO',NULL,NULL,'2018-03-16','A','2017/18',0),(134,'RQEO',NULL,NULL,'2018-03-17','H','2017/18',0),(135,'RQEO',NULL,NULL,'2018-03-18','H','2017/18',0),(136,'RQEO',NULL,NULL,'2018-03-19','P','2017/18',0),(137,'RQEO',NULL,NULL,'2018-03-20','P','2017/18',0),(138,'RQEO',NULL,NULL,'2018-03-21','P','2017/18',0),(139,'RQEO',NULL,NULL,'2018-03-22','P','2017/18',0),(140,'RQEO',NULL,NULL,'2018-03-23','P','2017/18',0),(141,'RQEO',NULL,NULL,'2018-03-24','P','2017/18',0),(142,'RQEO',NULL,NULL,'2018-03-25','P','2017/18',0),(143,'RQEO',NULL,NULL,'2018-03-26','P','2017/18',0),(144,'RQEO',NULL,NULL,'2018-03-27','P','2017/18',0),(145,'RQEO',NULL,NULL,'2018-03-28','P','2017/18',0),(146,'RQEO',NULL,NULL,'2018-03-29','P','2017/18',0),(147,'RQEO',NULL,NULL,'2018-03-30','P','2017/18',0),(148,'RQEO',NULL,NULL,'2018-03-31','P','2017/18',0),(149,'HHJ7',NULL,NULL,'2018-04-29','H','2017/18',0),(150,'RVWZ',NULL,NULL,'2018-04-29','H','2017/18',0),(151,'QTHO',NULL,NULL,'2018-04-29','H','2017/18',0),(152,'PAUR',NULL,NULL,'2018-04-29','H','2017/18',0),(153,'RQEO',NULL,NULL,'2018-04-29','H','2017/18',0),(154,'C801',NULL,NULL,'2018-04-29','H','2017/18',0),(155,'SF5K',NULL,NULL,'2018-04-29','H','2017/18',0),(156,'0GXP',NULL,NULL,'2018-04-29','H','2017/18',0),(157,'M3O6',NULL,NULL,'2018-04-29','H','2017/18',0),(158,'P3AG',NULL,NULL,'2018-04-29','H','2017/18',0),(159,'TF26',NULL,NULL,'2018-04-29','H','2017/18',0),(160,'JDB5',NULL,NULL,'2018-04-29','H','2017/18',0),(161,'Y6QP',NULL,NULL,'2018-04-29','H','2017/18',0),(162,'7908',NULL,NULL,'2018-04-29','H','2017/18',0),(163,'PP78',NULL,NULL,'2018-04-29','H','2017/18',0),(164,'25KF',NULL,NULL,'2018-04-29','H','2017/18',0),(165,'B99O',NULL,NULL,'2018-04-29','H','2017/18',0),(166,'HHJ7',NULL,NULL,'2018-05-06','H','2017/18',0),(167,'RVWZ',NULL,NULL,'2018-05-06','H','2017/18',0),(168,'QTHO',NULL,NULL,'2018-05-06','H','2017/18',0),(169,'PAUR',NULL,NULL,'2018-05-06','H','2017/18',0),(170,'RQEO',NULL,NULL,'2018-05-06','H','2017/18',0),(171,'C801',NULL,NULL,'2018-05-06','H','2017/18',0),(172,'SF5K',NULL,NULL,'2018-05-06','H','2017/18',0),(173,'0GXP',NULL,NULL,'2018-05-06','H','2017/18',0),(174,'M3O6',NULL,NULL,'2018-05-06','H','2017/18',0),(175,'P3AG',NULL,NULL,'2018-05-06','H','2017/18',0),(176,'TF26',NULL,NULL,'2018-05-06','H','2017/18',0),(177,'JDB5',NULL,NULL,'2018-05-06','H','2017/18',0),(178,'Y6QP',NULL,NULL,'2018-05-06','H','2017/18',0),(179,'7908',NULL,NULL,'2018-05-06','H','2017/18',0),(180,'PP78',NULL,NULL,'2018-05-06','H','2017/18',0),(181,'25KF',NULL,NULL,'2018-05-06','H','2017/18',0),(182,'B99O',NULL,NULL,'2018-05-06','H','2017/18',0),(183,'HHJ7',NULL,NULL,'2018-05-13','H','2017/18',0),(184,'RVWZ',NULL,NULL,'2018-05-13','H','2017/18',0),(185,'QTHO',NULL,NULL,'2018-05-13','H','2017/18',0),(186,'PAUR',NULL,NULL,'2018-05-13','H','2017/18',0),(187,'RQEO',NULL,NULL,'2018-05-13','H','2017/18',0),(188,'C801',NULL,NULL,'2018-05-13','H','2017/18',0),(189,'SF5K',NULL,NULL,'2018-05-13','H','2017/18',0),(190,'0GXP',NULL,NULL,'2018-05-13','H','2017/18',0),(191,'M3O6',NULL,NULL,'2018-05-13','H','2017/18',0),(192,'P3AG',NULL,NULL,'2018-05-13','H','2017/18',0),(193,'TF26',NULL,NULL,'2018-05-13','H','2017/18',0),(194,'JDB5',NULL,NULL,'2018-05-13','H','2017/18',0),(195,'Y6QP',NULL,NULL,'2018-05-13','H','2017/18',0),(196,'7908',NULL,NULL,'2018-05-13','H','2017/18',0),(197,'PP78',NULL,NULL,'2018-05-13','H','2017/18',0),(198,'25KF',NULL,NULL,'2018-05-13','H','2017/18',0),(199,'B99O',NULL,NULL,'2018-05-13','H','2017/18',0);
/*!40000 ALTER TABLE `att_studentdailyattendance` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `att_weeklyoff`
--

DROP TABLE IF EXISTS `att_weeklyoff`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `att_weeklyoff` (
  `wid` int(11) NOT NULL AUTO_INCREMENT,
  `weeklyoffday` varchar(100) NOT NULL,
  `academicyear` varchar(10) DEFAULT NULL,
  `branchid` int(11) DEFAULT NULL,
  PRIMARY KEY (`wid`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `att_weeklyoff`
--

LOCK TABLES `att_weeklyoff` WRITE;
/*!40000 ALTER TABLE `att_weeklyoff` DISABLE KEYS */;
INSERT INTO `att_weeklyoff` VALUES (1,'Sunday','2017/18',0),(2,'Monday','2017/18',0),(3,'Sunday','2017/18',0);
/*!40000 ALTER TABLE `att_weeklyoff` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `branch`
--

DROP TABLE IF EXISTS `branch`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `branch` (
  `idbranch` int(11) NOT NULL AUTO_INCREMENT,
  `branchname` varchar(50) DEFAULT NULL,
  `branchid` int(11) DEFAULT NULL,
  PRIMARY KEY (`idbranch`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `branch`
--

LOCK TABLES `branch` WRITE;
/*!40000 ALTER TABLE `branch` DISABLE KEYS */;
INSERT INTO `branch` VALUES (1,'Head Office',NULL),(2,'Boys High School',NULL),(3,'Girls High School',NULL),(4,'P.U. College',NULL),(5,'Degree College',NULL);
/*!40000 ALTER TABLE `branch` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `classsec`
--

DROP TABLE IF EXISTS `classsec`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `classsec` (
  `stdrdid` int(11) NOT NULL AUTO_INCREMENT,
  `classandsec` varchar(45) DEFAULT NULL,
  `branchid` int(11) DEFAULT NULL,
  PRIMARY KEY (`stdrdid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `classsec`
--

LOCK TABLES `classsec` WRITE;
/*!40000 ALTER TABLE `classsec` DISABLE KEYS */;
/*!40000 ALTER TABLE `classsec` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `currentacademicyear`
--

DROP TABLE IF EXISTS `currentacademicyear`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `currentacademicyear` (
  `cayid` int(11) NOT NULL AUTO_INCREMENT,
  `currentacademicyear` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`cayid`)
) ENGINE=InnoDB AUTO_INCREMENT=22 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `currentacademicyear`
--

LOCK TABLES `currentacademicyear` WRITE;
/*!40000 ALTER TABLE `currentacademicyear` DISABLE KEYS */;
INSERT INTO `currentacademicyear` VALUES (1,'2017/18'),(2,'2017/18'),(3,'2015/16'),(4,'2017/18'),(5,'2016/17'),(6,'2015/16'),(7,'2016/17'),(8,'2015/16'),(9,'2018/19'),(10,'2015/16'),(11,'2017/18'),(12,'2017/18'),(13,''),(14,'2017/18'),(15,''),(16,'2017/18'),(17,'2017/18'),(18,'2017/18'),(19,'2017/18'),(20,'2015/16'),(21,'2016/17');
/*!40000 ALTER TABLE `currentacademicyear` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `department`
--

DROP TABLE IF EXISTS `department`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `department` (
  `depid` int(11) NOT NULL AUTO_INCREMENT,
  `departmentname` varchar(100) DEFAULT NULL,
  `branchid` int(11) DEFAULT NULL,
  PRIMARY KEY (`depid`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `department`
--

LOCK TABLES `department` WRITE;
/*!40000 ALTER TABLE `department` DISABLE KEYS */;
INSERT INTO `department` VALUES (1,'Teaching',0),(2,'non teaching',0);
/*!40000 ALTER TABLE `department` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `exams`
--

DROP TABLE IF EXISTS `exams`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `exams` (
  `exid` int(11) NOT NULL AUTO_INCREMENT,
  `examname` varchar(45) DEFAULT NULL,
  `branchid` int(11) DEFAULT NULL,
  PRIMARY KEY (`exid`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `exams`
--

LOCK TABLES `exams` WRITE;
/*!40000 ALTER TABLE `exams` DISABLE KEYS */;
INSERT INTO `exams` VALUES (1,'unit test 1',0),(2,'unit test 2',0),(3,'mid term ',0);
/*!40000 ALTER TABLE `exams` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `examschedule`
--

DROP TABLE IF EXISTS `examschedule`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `examschedule` (
  `idexamschedule` int(11) NOT NULL AUTO_INCREMENT,
  `date` date DEFAULT NULL,
  `starttime` varchar(20) DEFAULT NULL,
  `endtime` varchar(20) DEFAULT NULL,
  `subject` varchar(30) DEFAULT NULL,
  `examname` varchar(30) DEFAULT NULL,
  `classes` varchar(45) DEFAULT NULL,
  `academicyear` varchar(45) DEFAULT NULL,
  `branchid` int(11) DEFAULT NULL,
  PRIMARY KEY (`idexamschedule`)
) ENGINE=InnoDB AUTO_INCREMENT=28 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `examschedule`
--

LOCK TABLES `examschedule` WRITE;
/*!40000 ALTER TABLE `examschedule` DISABLE KEYS */;
INSERT INTO `examschedule` VALUES (21,'2017-05-01','09:00 AM','12:00 PM','maths','unit test 1','I-I','2017/18',0),(22,'2018-12-01','07:00 AM','02:02 PM','social','unit test 2','II-II','2017/18',0),(23,'2017-12-05','09:00 AM','12:00 PM','social','unit test 1','I-I','2017/18',0),(24,'2018-12-02','09:00 AM','12:00 PM','maths','unit test 1','I-I','2017/18',0),(25,'2018-05-05','09:00 AM','12:00 PM','maths','unit test 1','I-I','2017/18',0),(26,'2222-12-06','09:00 AM','12:00 PM','maths','unit test 1','I-I','2017/18',0),(27,'2018-04-23','09:00 AM','12:00 PM','social','unit test 1','I-I','2017/18',0);
/*!40000 ALTER TABLE `examschedule` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `fee_academicfeesstructure`
--

DROP TABLE IF EXISTS `fee_academicfeesstructure`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `fee_academicfeesstructure` (
  `feesstructureid` int(11) NOT NULL AUTO_INCREMENT,
  `sid` int(11) NOT NULL,
  `totalfees` varchar(45) DEFAULT NULL,
  `paidfees` varchar(45) DEFAULT NULL,
  `academicyear` varchar(45) DEFAULT NULL,
  `branchid` int(11) DEFAULT NULL,
  PRIMARY KEY (`feesstructureid`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `fee_academicfeesstructure`
--

LOCK TABLES `fee_academicfeesstructure` WRITE;
/*!40000 ALTER TABLE `fee_academicfeesstructure` DISABLE KEYS */;
INSERT INTO `fee_academicfeesstructure` VALUES (4,1,'2000',NULL,'2017/18',0),(5,2,'2000',NULL,'2017/18',0),(6,3,'2000',NULL,'2017/18',0),(7,10,'450',NULL,'2017/18',0),(8,5,'1500',NULL,'2017/18',0);
/*!40000 ALTER TABLE `fee_academicfeesstructure` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `fee_feescategory`
--

DROP TABLE IF EXISTS `fee_feescategory`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `fee_feescategory` (
  `idfeescategory` int(11) NOT NULL AUTO_INCREMENT,
  `feescategoryname` varchar(150) DEFAULT NULL,
  `amount` int(11) DEFAULT NULL,
  `particularname` varchar(150) DEFAULT NULL,
  `branchid` int(11) DEFAULT NULL,
  PRIMARY KEY (`idfeescategory`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `fee_feescategory`
--

LOCK TABLES `fee_feescategory` WRITE;
/*!40000 ALTER TABLE `fee_feescategory` DISABLE KEYS */;
INSERT INTO `fee_feescategory` VALUES (1,'tution fees',5000,'L.K.G-U.K.G',0),(2,'tution fees',10000,'I-V',0),(3,'admission fees',350,'ALL',0),(4,'test',500,'nursery',0),(5,'uniform fees',1000,'ALL',0),(6,'building fund',6500,'VIII-X',0),(7,'monthly fees',100,'VIII-X',0),(8,'yearly fees',2000,'ALL',0);
/*!40000 ALTER TABLE `fee_feescategory` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `fee_feescollection`
--

DROP TABLE IF EXISTS `fee_feescollection`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `fee_feescollection` (
  `feecollectionid` int(11) NOT NULL AUTO_INCREMENT,
  `sfsid` int(11) NOT NULL,
  `amountpaid` decimal(10,0) DEFAULT NULL,
  `sid` int(11) NOT NULL,
  `fine` decimal(10,0) DEFAULT NULL,
  `date` date DEFAULT NULL,
  `academicyear` varchar(45) DEFAULT NULL,
  `receiptnumber` int(11) NOT NULL,
  `branchid` int(11) DEFAULT NULL,
  PRIMARY KEY (`feecollectionid`),
  KEY `sid_idx` (`sid`),
  KEY `sfsid_idx` (`sfsid`),
  KEY `receiptnumber_idx` (`receiptnumber`),
  CONSTRAINT `fk` FOREIGN KEY (`sid`) REFERENCES `student` (`sid`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `receiptnumber` FOREIGN KEY (`receiptnumber`) REFERENCES `fee_receiptinfo` (`receiptnumber`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `sfsid` FOREIGN KEY (`sfsid`) REFERENCES `fee_studentfeesstructure` (`sfsid`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=111 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `fee_feescollection`
--

LOCK TABLES `fee_feescollection` WRITE;
/*!40000 ALTER TABLE `fee_feescollection` DISABLE KEYS */;
INSERT INTO `fee_feescollection` VALUES (1,2,41,2,0,'2018-02-07','2017/18',7,0),(2,5,22,2,0,'2018-02-07','2017/18',7,0),(3,4,500,1,0,'2018-02-07','2017/18',8,0),(4,1,20,1,0,'2018-02-07','2017/18',8,0),(5,1,2,1,0,'2018-02-07','2017/18',9,0),(6,4,2,1,0,'2018-02-07','2017/18',9,0),(7,4,5,1,0,'2018-02-07','2017/18',10,0),(8,1,8,1,0,'2018-02-07','2017/18',10,0),(9,1,2,1,0,'2018-02-07','2017/18',11,0),(10,4,3,1,0,'2018-02-07','2017/18',11,0),(11,4,80,1,0,'2018-02-07','2017/18',12,0),(12,1,8,1,0,'2018-02-07','2017/18',12,0),(13,4,10,1,0,'2018-02-07','2017/18',13,0),(14,1,10,1,0,'2018-02-07','2017/18',13,0),(15,1,5,1,0,'2018-02-07','2017/18',14,0),(16,4,5,1,0,'2018-02-07','2017/18',14,0),(17,1,5,1,0,'2018-02-07','2017/18',15,0),(18,4,5,1,0,'2018-02-07','2017/18',15,0),(19,1,5,1,0,'2018-02-07','2017/18',16,0),(20,4,5,1,0,'2018-02-07','2017/18',16,0),(21,1,5,1,0,'2018-02-07','2017/18',17,0),(22,4,5,1,0,'2018-02-07','2017/18',17,0),(23,1,22,1,0,'2018-02-07','2017/18',18,0),(24,4,22,1,0,'2018-02-07','2017/18',18,0),(25,1,5,1,0,'2018-02-07','2017/18',19,0),(26,4,5,1,0,'2018-02-07','2017/18',19,0),(27,1,3,1,0,'2018-02-07','2017/18',20,0),(28,4,3,1,0,'2018-02-07','2017/18',20,0),(29,4,2,1,0,'2018-02-07','2017/18',21,0),(30,1,2,1,0,'2018-02-07','2017/18',21,0),(31,4,2,1,0,'2018-02-07','2017/18',22,0),(32,1,2,1,0,'2018-02-07','2017/18',22,0),(33,4,1,1,0,'2018-02-07','2017/18',23,0),(34,1,1,1,0,'2018-02-07','2017/18',23,0),(35,1,2,1,0,'2018-02-07','2017/18',24,0),(36,4,2,1,0,'2018-02-07','2017/18',24,0),(37,4,3,1,0,'2018-02-07','2017/18',25,0),(38,1,5,1,0,'2018-02-07','2017/18',25,0),(39,4,340,1,0,'2018-02-07','2017/18',26,0),(40,1,38,1,0,'2018-02-07','2017/18',26,0),(41,1,10,1,0,'2018-02-08','2017/18',27,0),(42,4,10,1,0,'2018-02-08','2017/18',27,0),(43,4,22,1,0,'2018-02-09','2017/18',28,0),(44,1,22,1,0,'2018-02-09','2017/18',28,0),(45,1,55,1,0,'2018-02-09','2017/18',29,0),(46,4,55,1,0,'2018-02-09','2017/18',29,0),(47,4,3,1,0,'2018-02-09','2017/18',30,0),(48,1,3,1,0,'2018-02-09','2017/18',30,0),(49,1,110,1,0,'2018-02-11','2017/18',31,0),(50,4,910,1,0,'2018-02-11','2017/18',31,0),(51,1,0,1,0,'2018-02-14','2017/18',32,0),(52,4,2000,1,0,'2018-02-14','2017/18',32,0),(53,1,0,1,0,'2018-02-15','2017/18',33,0),(54,4,0,1,0,'2018-02-15','2017/18',33,0),(55,7,500,2,0,'2018-03-06','2017/18',34,0),(56,5,100,2,0,'2018-03-06','2017/18',34,0),(57,2,309,2,0,'2018-03-06','2017/18',34,0),(58,7,0,2,0,'2018-03-10','2017/18',35,0),(59,2,0,2,0,'2018-03-10','2017/18',35,0),(60,5,878,2,0,'2018-03-10','2017/18',35,0),(61,2,0,2,0,'2018-03-11','2017/18',36,0),(62,5,2000,2,0,'2018-03-11','2017/18',36,0),(63,7,0,2,0,'2018-03-11','2017/18',36,0),(64,5,0,2,0,'2018-03-12','2017/18',37,0),(65,7,0,2,0,'2018-03-12','2017/18',37,0),(66,2,0,2,0,'2018-03-12','2017/18',37,0),(67,10,120000,1,0,'2018-03-27','2017/18',38,0),(68,4,4000,1,0,'2018-03-27','2017/18',38,0),(69,9,0,1,0,'2018-03-27','2017/18',38,0),(70,1,0,1,0,'2018-03-27','2017/18',38,0),(71,4,0,1,0,'2018-03-27','2017/18',39,0),(72,15,0,1,0,'2018-03-27','2017/18',39,0),(73,1,350,1,0,'2018-03-27','2017/18',39,0),(74,9,0,1,0,'2018-03-27','2017/18',39,0),(75,10,120000,1,0,'2018-03-27','2017/18',39,0),(76,10,0,1,0,'2018-04-05','2017/18',40,0),(77,4,0,1,0,'2018-04-05','2017/18',40,0),(78,9,500,1,0,'2018-04-05','2017/18',40,0),(79,1,0,1,0,'2018-04-05','2017/18',40,0),(80,15,0,1,0,'2018-04-05','2017/18',40,0),(81,3,200,3,0,'2018-04-17','2017/18',41,0),(82,13,0,3,0,'2018-04-17','2017/18',41,0),(83,14,0,3,0,'2018-04-17','2017/18',41,0),(84,6,0,3,0,'2018-04-17','2017/18',41,0),(85,8,0,3,0,'2018-04-17','2017/18',41,0),(86,17,0,3,0,'2018-04-17','2017/18',41,0),(87,13,200,3,0,'2018-04-17','2017/18',42,0),(88,14,0,3,0,'2018-04-17','2017/18',42,0),(89,6,0,3,0,'2018-04-17','2017/18',42,0),(90,8,0,3,0,'2018-04-17','2017/18',42,0),(91,17,0,3,0,'2018-04-17','2017/18',42,0),(92,14,200,3,0,'2018-04-17','2017/18',43,0),(93,6,0,3,0,'2018-04-17','2017/18',43,0),(94,8,0,3,0,'2018-04-17','2017/18',43,0),(95,17,0,3,0,'2018-04-17','2017/18',43,0),(96,1,200,1,0,'2018-04-17','2017/18',44,0),(97,1,200,1,0,'2018-04-17','2017/18',45,0),(98,15,500,1,0,'2018-04-17','2017/18',46,0),(99,4,500,1,0,'2018-04-17','2017/18',47,0),(100,9,10,1,0,'2018-04-17','2017/18',51,0),(101,15,200,1,0,'2018-04-17','2017/18',52,0),(102,2,0,2,0,'2018-04-23','2017/18',53,0),(103,16,500,2,0,'2018-04-23','2017/18',53,0),(104,7,0,2,0,'2018-04-23','2017/18',53,0),(105,5,0,2,0,'2018-04-23','2017/18',53,0),(106,12,0,2,0,'2018-04-23','2017/18',53,0),(107,11,0,2,0,'2018-04-23','2017/18',53,0),(108,16,500,2,0,'2018-04-23','2017/18',54,0),(109,13,200,3,0,'2018-05-02','2017/18',55,0),(110,27,200,5,0,'2018-05-14','2017/18',56,0);
/*!40000 ALTER TABLE `fee_feescollection` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `fee_feesdetails`
--

DROP TABLE IF EXISTS `fee_feesdetails`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `fee_feesdetails` (
  `feesdetailsid` int(11) NOT NULL AUTO_INCREMENT,
  `sid` int(11) DEFAULT NULL,
  `dateoffees` varchar(100) DEFAULT NULL,
  `grandtotal` varchar(45) DEFAULT NULL,
  `miscamount` varchar(45) DEFAULT NULL,
  `balamount` varchar(45) DEFAULT NULL,
  `amountpercat` varchar(45) DEFAULT NULL,
  `academicyear` varchar(45) DEFAULT NULL,
  `branchid` int(11) DEFAULT NULL,
  PRIMARY KEY (`feesdetailsid`),
  KEY `fk_idx` (`sid`),
  CONSTRAINT `fk_fees` FOREIGN KEY (`sid`) REFERENCES `student` (`sid`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='				';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `fee_feesdetails`
--

LOCK TABLES `fee_feesdetails` WRITE;
/*!40000 ALTER TABLE `fee_feesdetails` DISABLE KEYS */;
/*!40000 ALTER TABLE `fee_feesdetails` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `fee_receiptinfo`
--

DROP TABLE IF EXISTS `fee_receiptinfo`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `fee_receiptinfo` (
  `receiptnumber` int(11) NOT NULL AUTO_INCREMENT,
  `sid` int(11) NOT NULL,
  `date` date DEFAULT NULL,
  `totalamount` decimal(10,0) DEFAULT NULL,
  `academicyear` varchar(15) DEFAULT NULL,
  `branchid` int(11) DEFAULT NULL,
  PRIMARY KEY (`receiptnumber`),
  KEY `studentid_idx` (`sid`),
  CONSTRAINT `studentidreceipt` FOREIGN KEY (`sid`) REFERENCES `student` (`sid`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=57 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `fee_receiptinfo`
--

LOCK TABLES `fee_receiptinfo` WRITE;
/*!40000 ALTER TABLE `fee_receiptinfo` DISABLE KEYS */;
INSERT INTO `fee_receiptinfo` VALUES (1,1,'2018-02-07',1500,'2017/18',0),(2,2,'2018-02-07',5350,'2017/18',0),(3,3,'2018-02-07',500,'2017/18',0),(4,3,'2018-02-07',500,'2017/18',0),(5,1,'2018-02-07',200,'2017/18',0),(6,1,'2018-02-07',700,'2017/18',0),(7,2,'2018-02-07',63,'2017/18',0),(8,1,'2018-02-07',520,'2017/18',0),(9,1,'2018-02-07',4,'2017/18',0),(10,1,'2018-02-07',13,'2017/18',0),(11,1,'2018-02-07',5,'2017/18',0),(12,1,'2018-02-07',88,'2017/18',0),(13,1,'2018-02-07',20,'2017/18',0),(14,1,'2018-02-07',10,'2017/18',0),(15,1,'2018-02-07',10,'2017/18',0),(16,1,'2018-02-07',10,'2017/18',0),(17,1,'2018-02-07',10,'2017/18',0),(18,1,'2018-02-07',44,'2017/18',0),(19,1,'2018-02-07',10,'2017/18',0),(20,1,'2018-02-07',6,'2017/18',0),(21,1,'2018-02-07',4,'2017/18',0),(22,1,'2018-02-07',4,'2017/18',0),(23,1,'2018-02-07',2,'2017/18',0),(24,1,'2018-02-07',4,'2017/18',0),(25,1,'2018-02-07',8,'2017/18',0),(26,1,'2018-02-07',378,'2017/18',0),(27,1,'2018-02-08',20,'2017/18',0),(28,1,'2018-02-09',44,'2017/18',0),(29,1,'2018-02-09',110,'2017/18',0),(30,1,'2018-02-09',6,'2017/18',0),(31,1,'2018-02-11',1020,'2017/18',0),(32,1,'2018-02-14',2000,'2017/18',0),(33,1,'2018-02-15',0,'2017/18',0),(34,2,'2018-03-06',909,'2017/18',0),(35,2,'2018-03-10',878,'2017/18',0),(36,2,'2018-03-11',2000,'2017/18',0),(37,2,'2018-03-12',0,'2017/18',0),(38,1,'2018-03-27',124000,'2017/18',0),(39,1,'2018-03-27',120350,'2017/18',0),(40,1,'2018-04-05',500,'2017/18',0),(41,3,'2018-04-17',200,'2017/18',0),(42,3,'2018-04-17',200,'2017/18',0),(43,3,'2018-04-17',200,'2017/18',0),(44,1,'2018-04-17',200,'2017/18',0),(45,1,'2018-04-17',300,'2017/18',0),(46,1,'2018-04-17',540,'2017/18',0),(47,1,'2018-04-17',520,'2017/18',0),(48,1,'2018-04-17',350,'2017/18',0),(49,1,'2018-04-17',10,'2017/18',0),(50,1,'2018-04-17',50,'2017/18',0),(51,1,'2018-04-17',220,'2017/18',0),(52,1,'2018-04-17',200,'2017/18',0),(53,2,'2018-04-23',500,'2017/18',0),(54,2,'2018-04-23',500,'2017/18',0),(55,3,'2018-05-02',200,'2017/18',0),(56,5,'2018-05-14',200,'2017/18',0);
/*!40000 ALTER TABLE `fee_receiptinfo` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `fee_studentfeesstructure`
--

DROP TABLE IF EXISTS `fee_studentfeesstructure`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `fee_studentfeesstructure` (
  `sfsid` int(11) NOT NULL AUTO_INCREMENT,
  `sid` int(11) NOT NULL,
  `idfeescategory` int(11) NOT NULL,
  `feesamount` decimal(10,0) DEFAULT NULL,
  `academicyear` varchar(45) DEFAULT NULL,
  `branchid` int(11) DEFAULT NULL,
  PRIMARY KEY (`sfsid`),
  KEY `fk_sfs_idx` (`sid`),
  KEY `feescategoryid_idx` (`idfeescategory`),
  CONSTRAINT `feescategoryid` FOREIGN KEY (`idfeescategory`) REFERENCES `fee_feescategory` (`idfeescategory`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_sfs` FOREIGN KEY (`sid`) REFERENCES `student` (`sid`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=28 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `fee_studentfeesstructure`
--

LOCK TABLES `fee_studentfeesstructure` WRITE;
/*!40000 ALTER TABLE `fee_studentfeesstructure` DISABLE KEYS */;
INSERT INTO `fee_studentfeesstructure` VALUES (1,1,3,350,'2017/18',0),(2,2,3,350,'2017/18',0),(3,3,3,350,'2017/18',0),(4,1,1,5000,'2017/18',0),(5,2,1,5000,'2017/18',0),(6,3,1,5000,'2017/18',0),(7,2,4,500,'2017/18',0),(8,3,4,500,'2017/18',0),(9,1,6,6500,'2017/18',0),(10,1,2,120000,'2017/18',0),(11,2,6,6500,'2017/18',0),(12,2,2,120000,'2017/18',0),(13,3,6,6500,'2017/18',0),(14,3,2,120000,'2017/18',0),(15,1,7,1200,'2017/18',0),(16,2,7,1200,'2017/18',0),(17,3,7,1200,'2017/18',0),(23,5,1,5000,'2017/18',0),(24,1,8,2000,'2017/18',0),(25,2,8,2000,'2017/18',0),(26,3,8,2000,'2017/18',0),(27,5,8,1500,'2017/18',0);
/*!40000 ALTER TABLE `fee_studentfeesstructure` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `fileuploaddetails`
--

DROP TABLE IF EXISTS `fileuploaddetails`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `fileuploaddetails` (
  `fudid` int(11) NOT NULL AUTO_INCREMENT,
  `date` date NOT NULL,
  `uploadstatus` varchar(10) DEFAULT NULL,
  `branchid` int(11) DEFAULT NULL,
  PRIMARY KEY (`fudid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `fileuploaddetails`
--

LOCK TABLES `fileuploaddetails` WRITE;
/*!40000 ALTER TABLE `fileuploaddetails` DISABLE KEYS */;
/*!40000 ALTER TABLE `fileuploaddetails` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `hr_leaveapplication`
--

DROP TABLE IF EXISTS `hr_leaveapplication`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `hr_leaveapplication` (
  `idleaveapplication` int(11) NOT NULL AUTO_INCREMENT,
  `idteacher` int(11) DEFAULT NULL,
  `fromdate` date DEFAULT NULL,
  `todate` date DEFAULT NULL,
  `leavetype` varchar(45) DEFAULT NULL,
  `totalleaves` int(11) DEFAULT NULL,
  `reason` varchar(500) DEFAULT NULL,
  `academicyear` varchar(45) DEFAULT NULL,
  `status` varchar(45) DEFAULT NULL,
  `dateofapply` date DEFAULT NULL,
  `dateofapproval` date DEFAULT NULL,
  `branchid` int(11) DEFAULT NULL,
  PRIMARY KEY (`idleaveapplication`),
  KEY `idteacherleaveapp_idx` (`idteacher`),
  CONSTRAINT `idteacherleaveapp` FOREIGN KEY (`idteacher`) REFERENCES `teacher` (`tid`) ON DELETE CASCADE ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `hr_leaveapplication`
--

LOCK TABLES `hr_leaveapplication` WRITE;
/*!40000 ALTER TABLE `hr_leaveapplication` DISABLE KEYS */;
INSERT INTO `hr_leaveapplication` VALUES (1,1,'2018-04-02','2018-04-19','2',NULL,'1','2017/18','rejected',NULL,NULL,0),(2,9,'2018-02-01','2018-02-05','2',3,'reason','2017/18','approved',NULL,NULL,0),(3,9,'2018-02-01','2018-02-05','2',3,'reason','2017/18','approved',NULL,NULL,0),(4,9,'2018-02-01','2018-02-05','2',2,'reason','2017/18','approved',NULL,NULL,0),(5,9,'2018-04-26','2018-04-26','2',1,'reason','2017/18','approved',NULL,NULL,0),(6,9,'2018-04-26','2018-04-30','2',3,'reason','2017/18','approved','2018-04-28',NULL,0);
/*!40000 ALTER TABLE `hr_leaveapplication` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `hr_leavedetails`
--

DROP TABLE IF EXISTS `hr_leavedetails`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `hr_leavedetails` (
  `idleavedetails` int(11) NOT NULL AUTO_INCREMENT,
  `idleavetypemaster` int(11) DEFAULT NULL,
  `idteacher` int(11) DEFAULT NULL,
  `numberofleaves` int(11) DEFAULT NULL,
  `academicyear` varchar(45) DEFAULT NULL,
  `branchid` int(11) DEFAULT NULL,
  PRIMARY KEY (`idleavedetails`),
  KEY `leavetype_idx` (`idleavetypemaster`),
  KEY `teacherid_idx` (`idteacher`),
  CONSTRAINT `leavetype` FOREIGN KEY (`idleavetypemaster`) REFERENCES `hr_leavetypemaster` (`idleavetypemaster`) ON DELETE CASCADE ON UPDATE NO ACTION,
  CONSTRAINT `teacherid` FOREIGN KEY (`idteacher`) REFERENCES `teacher` (`tid`) ON DELETE CASCADE ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8 COMMENT='		';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `hr_leavedetails`
--

LOCK TABLES `hr_leavedetails` WRITE;
/*!40000 ALTER TABLE `hr_leavedetails` DISABLE KEYS */;
INSERT INTO `hr_leavedetails` VALUES (1,2,1,4,NULL,0),(2,3,1,5,NULL,0),(3,2,2,4,NULL,0),(4,3,2,5,'2016/17',0),(5,2,1,1,'2017/18',0),(6,2,2,1,'2017/18',0);
/*!40000 ALTER TABLE `hr_leavedetails` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `hr_leavetypemaster`
--

DROP TABLE IF EXISTS `hr_leavetypemaster`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `hr_leavetypemaster` (
  `idleavetypemaster` int(11) NOT NULL AUTO_INCREMENT,
  `leavetypename` varchar(100) DEFAULT NULL,
  `branchid` int(11) DEFAULT NULL,
  PRIMARY KEY (`idleavetypemaster`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `hr_leavetypemaster`
--

LOCK TABLES `hr_leavetypemaster` WRITE;
/*!40000 ALTER TABLE `hr_leavetypemaster` DISABLE KEYS */;
INSERT INTO `hr_leavetypemaster` VALUES (2,'leave two',0),(3,'sick leave',0),(4,'',0);
/*!40000 ALTER TABLE `hr_leavetypemaster` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `hr_payadvancesalary`
--

DROP TABLE IF EXISTS `hr_payadvancesalary`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `hr_payadvancesalary` (
  `idpayadvancesalary` int(11) NOT NULL AUTO_INCREMENT,
  `idteacher` int(11) DEFAULT NULL,
  `year` varchar(45) DEFAULT NULL,
  `month` varchar(45) DEFAULT NULL,
  `salaryfordays` int(11) DEFAULT NULL,
  `deductionpermonth` decimal(20,4) DEFAULT NULL,
  `amount` decimal(20,4) DEFAULT NULL,
  `deductionstartmonth` varchar(45) DEFAULT NULL,
  `deductionstartyear` varchar(45) DEFAULT NULL,
  `status` varchar(40) DEFAULT NULL,
  `reason` varchar(500) DEFAULT NULL,
  `date` date DEFAULT NULL,
  `branchid` int(11) DEFAULT NULL,
  PRIMARY KEY (`idpayadvancesalary`),
  KEY `idteacheradv_idx` (`idteacher`),
  CONSTRAINT `idteacheradv` FOREIGN KEY (`idteacher`) REFERENCES `teacher` (`tid`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `hr_payadvancesalary`
--

LOCK TABLES `hr_payadvancesalary` WRITE;
/*!40000 ALTER TABLE `hr_payadvancesalary` DISABLE KEYS */;
INSERT INTO `hr_payadvancesalary` VALUES (1,3,'2018','February',90,12.0000,123.0000,'February','2018','555','555',NULL,0),(2,1,'2019','February',90,12.0000,658.0000,'February','2019','rejected','test123',NULL,0),(5,3,'2018','January',90,12.0000,123.0000,'January','2018','approved','hgjhg','2018-04-26',0),(6,1,'2018','January',90,12.0000,123.0000,'February','2018','apply',NULL,'2018-04-28',0);
/*!40000 ALTER TABLE `hr_payadvancesalary` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `hr_paybasic`
--

DROP TABLE IF EXISTS `hr_paybasic`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `hr_paybasic` (
  `idpaybasic` int(11) NOT NULL AUTO_INCREMENT,
  `idteacher` int(11) NOT NULL,
  `basicpay` decimal(20,4) DEFAULT NULL,
  `paymenttype` varchar(45) DEFAULT NULL,
  `accountno` varchar(40) DEFAULT NULL,
  `overtime` varchar(10) DEFAULT NULL,
  `academicyear` varchar(45) DEFAULT NULL,
  `branchid` int(11) DEFAULT NULL,
  PRIMARY KEY (`idpaybasic`),
  KEY `idteacher_idx` (`idteacher`),
  CONSTRAINT `teachersid` FOREIGN KEY (`idteacher`) REFERENCES `teacher` (`tid`) ON DELETE CASCADE ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=24 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `hr_paybasic`
--

LOCK TABLES `hr_paybasic` WRITE;
/*!40000 ALTER TABLE `hr_paybasic` DISABLE KEYS */;
INSERT INTO `hr_paybasic` VALUES (1,1,5.0000,'Cash','0','yes','2017/18',0),(2,2,10.0000,'Bank','12555','yes','2017/18',0),(3,3,15.0000,'Cash','0','no','2017/18',0),(4,1,0.0000,'','0','no','2017/18',0),(5,2,0.0000,'','0','no','2017/18',0),(6,3,0.0000,'','0','no','2017/18',0),(7,9,25000.0000,'','0','no','2017/18',0),(8,12,0.0000,'','0','no','2017/18',0),(9,13,25000.0000,'Cash','0','no','2017/18',0),(10,1,23000.0000,'Cash','0','no','2017/18',0),(11,2,36000.0000,'Cash','0','no','2017/18',0),(12,1,25000.0000,'Cash','0','no','2017/18',0),(13,2,0.0000,'','0','no','2017/18',0),(14,3,0.0000,'','0','no','2017/18',0),(15,9,0.0000,'','0','no','2017/18',0),(16,12,0.0000,'','0','no','2017/18',0),(17,13,0.0000,'','0','no','2017/18',0),(18,1,0.0000,'','0','no','2017/18',0),(19,2,0.0000,'','0','no','2017/18',0),(20,3,0.0000,'','0','no','2017/18',0),(21,9,0.0000,'','0','no','2017/18',0),(22,12,250.0000,'Cash','0','no','2017/18',0),(23,13,0.0000,'','0','no','2017/18',0);
/*!40000 ALTER TABLE `hr_paybasic` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `hr_payhead`
--

DROP TABLE IF EXISTS `hr_payhead`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `hr_payhead` (
  `idpayhead` int(11) NOT NULL AUTO_INCREMENT,
  `payheadname` varchar(200) DEFAULT NULL,
  `payheadtype` varchar(40) DEFAULT NULL,
  `validatory` varchar(45) DEFAULT NULL,
  `description` varchar(200) DEFAULT NULL,
  `academicyear` varchar(45) DEFAULT NULL,
  `branchid` int(11) DEFAULT NULL,
  PRIMARY KEY (`idpayhead`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `hr_payhead`
--

LOCK TABLES `hr_payhead` WRITE;
/*!40000 ALTER TABLE `hr_payhead` DISABLE KEYS */;
INSERT INTO `hr_payhead` VALUES (1,'head name','Earning','','test description										\r\n								','2017/18',0),(2,'head name','Earning','','										\r\ndes								','2017/18',0),(3,'abc','Earning','','test										\r\n								','2017/18',0),(4,'pay','Earning','Every Month','desc										\r\n								','2017/18',0),(5,'pf','Deduction','Every Month','des										\r\n								','2017/18',0);
/*!40000 ALTER TABLE `hr_payhead` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `hr_payheadstaffdetails`
--

DROP TABLE IF EXISTS `hr_payheadstaffdetails`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `hr_payheadstaffdetails` (
  `idpayheadstaffdetails` int(11) NOT NULL AUTO_INCREMENT,
  `idteacher` int(11) NOT NULL,
  `value` decimal(20,4) DEFAULT NULL,
  `amountperc` varchar(20) DEFAULT NULL,
  `academicyear` varchar(45) DEFAULT NULL,
  `payheadid` int(11) DEFAULT NULL,
  `branchid` int(11) DEFAULT NULL,
  PRIMARY KEY (`idpayheadstaffdetails`),
  KEY `teacherid_idx` (`idteacher`),
  KEY `payheadid_idx` (`payheadid`),
  CONSTRAINT `idteacher` FOREIGN KEY (`idteacher`) REFERENCES `teacher` (`tid`) ON DELETE CASCADE ON UPDATE NO ACTION,
  CONSTRAINT `payheadid` FOREIGN KEY (`payheadid`) REFERENCES `hr_payhead` (`idpayhead`) ON DELETE CASCADE ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=28 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `hr_payheadstaffdetails`
--

LOCK TABLES `hr_payheadstaffdetails` WRITE;
/*!40000 ALTER TABLE `hr_payheadstaffdetails` DISABLE KEYS */;
INSERT INTO `hr_payheadstaffdetails` VALUES (2,2,62.0000,'amount','2017/18',2,0),(5,12,22.0000,'amount','2017/18',2,0),(10,12,2.0000,'percentage','2017/18',5,0),(13,9,525.0000,'amount','2017/18',3,0),(14,1,1000.0000,'amount','2017/18',1,0),(16,1,320.0000,'amount','2017/18',1,0),(17,2,0.0000,'amount','2017/18',1,0),(18,3,0.0000,'amount','2017/18',1,0),(19,9,0.0000,'amount','2017/18',1,0),(20,12,0.0000,'amount','2017/18',1,0),(21,13,0.0000,'amount','2017/18',1,0),(22,1,5.0000,'percentage','2017/18',5,0),(23,2,0.0000,'percentage','2017/18',5,0),(24,3,0.0000,'percentage','2017/18',5,0),(25,9,0.0000,'percentage','2017/18',5,0),(26,12,0.0000,'percentage','2017/18',5,0),(27,13,0.0000,'percentage','2017/18',5,0);
/*!40000 ALTER TABLE `hr_payheadstaffdetails` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `hr_pf`
--

DROP TABLE IF EXISTS `hr_pf`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `hr_pf` (
  `idpf` int(11) NOT NULL AUTO_INCREMENT,
  `paidbymanagement` int(11) DEFAULT NULL,
  `paidbyemployee` int(11) DEFAULT NULL,
  `date` date DEFAULT NULL,
  `branchid` int(11) DEFAULT NULL,
  PRIMARY KEY (`idpf`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 COMMENT='		';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `hr_pf`
--

LOCK TABLES `hr_pf` WRITE;
/*!40000 ALTER TABLE `hr_pf` DISABLE KEYS */;
INSERT INTO `hr_pf` VALUES (1,22,2,'2018-04-24',0),(2,4,6,'2018-04-25',0);
/*!40000 ALTER TABLE `hr_pf` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `hr_processsalarydetails`
--

DROP TABLE IF EXISTS `hr_processsalarydetails`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `hr_processsalarydetails` (
  `idprocesssalarydetails` int(11) NOT NULL AUTO_INCREMENT,
  `teacherid` int(11) DEFAULT NULL,
  `month` varchar(10) DEFAULT NULL,
  `year` int(11) DEFAULT NULL,
  `netpayment` decimal(20,0) DEFAULT NULL,
  `status` varchar(45) DEFAULT NULL,
  `academicyear` varchar(45) DEFAULT NULL,
  `processeddate` date DEFAULT NULL,
  `issueddate` date DEFAULT NULL,
  `paymenttype` varchar(20) DEFAULT NULL,
  `branchid` int(11) DEFAULT NULL,
  PRIMARY KEY (`idprocesssalarydetails`),
  KEY `processteacherid_idx` (`teacherid`),
  CONSTRAINT `processteacherid` FOREIGN KEY (`teacherid`) REFERENCES `teacher` (`tid`) ON DELETE CASCADE ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `hr_processsalarydetails`
--

LOCK TABLES `hr_processsalarydetails` WRITE;
/*!40000 ALTER TABLE `hr_processsalarydetails` DISABLE KEYS */;
INSERT INTO `hr_processsalarydetails` VALUES (1,13,'January',2018,NULL,'ISSUED','2017/18','2018-04-30',NULL,'Cash',0),(2,13,'December',2018,25000,'ISSUED','2017/18','2018-04-30',NULL,'Bank',0),(3,13,'April',2018,29500,'ISSUED','2017/18','2018-04-30',NULL,'Cash',0),(10,1,'January',2018,25070,'CANCELLED','2017/18','2018-05-05',NULL,'Cash',0),(11,12,'September',2018,267,'PROCESSED','2017/18','2018-05-05',NULL,'Cash',0);
/*!40000 ALTER TABLE `hr_processsalarydetails` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `hr_processsalarydetailsheads`
--

DROP TABLE IF EXISTS `hr_processsalarydetailsheads`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `hr_processsalarydetailsheads` (
  `idprocesssalarydetailsheads` int(11) NOT NULL AUTO_INCREMENT,
  `idprocesssalary` int(11) DEFAULT NULL,
  `payheadname` varchar(45) DEFAULT NULL,
  `payheadtype` varchar(45) DEFAULT NULL,
  `amount` decimal(20,0) DEFAULT NULL,
  `branchid` int(11) DEFAULT NULL,
  PRIMARY KEY (`idprocesssalarydetailsheads`),
  KEY `idprocesssalary_idx` (`idprocesssalary`),
  CONSTRAINT `idprocesssalary` FOREIGN KEY (`idprocesssalary`) REFERENCES `hr_processsalarydetails` (`idprocesssalarydetails`) ON DELETE CASCADE ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=30 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `hr_processsalarydetailsheads`
--

LOCK TABLES `hr_processsalarydetailsheads` WRITE;
/*!40000 ALTER TABLE `hr_processsalarydetailsheads` DISABLE KEYS */;
INSERT INTO `hr_processsalarydetailsheads` VALUES (1,2,'Basic Pay','Earning',25000,0),(2,2,'abc','Earning',5000,0),(3,2,'pf','Deduction',500,0),(4,3,'Basic Pay','Earning',25000,0),(5,3,'abc','Earning',5000,0),(6,3,'pf','Deduction',500,0),(21,10,'Basic Pay','Earning',25000,0),(22,10,'head name','Earning',1000,0),(23,10,'head name','Earning',320,0),(24,10,'pf','Deduction',1250,0),(25,11,'Basic Pay','Earning',250,0),(26,11,'head name','Earning',22,0),(27,11,'pf','Deduction',5,0),(28,11,'head name','Earning',0,0),(29,11,'pf','Deduction',0,0);
/*!40000 ALTER TABLE `hr_processsalarydetailsheads` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `login`
--

DROP TABLE IF EXISTS `login`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `login` (
  `lid` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(45) NOT NULL,
  `password` varchar(45) NOT NULL,
  `usertype` varchar(45) DEFAULT NULL,
  `branchid` int(11) DEFAULT NULL,
  PRIMARY KEY (`lid`),
  KEY `branchid_idx` (`branchid`),
  CONSTRAINT `branchid` FOREIGN KEY (`branchid`) REFERENCES `branch` (`idbranch`) ON DELETE CASCADE ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `login`
--

LOCK TABLES `login` WRITE;
/*!40000 ALTER TABLE `login` DISABLE KEYS */;
INSERT INTO `login` VALUES (1,'admin','admin','admin',1),(2,'reception','reception','reception',1),(3,'MS83','qwe','staff',2),(4,'PODL','9QAY','staff',2),(5,'YWO9','OEZ2','staff',2),(6,'9NXS','test','staff',3);
/*!40000 ALTER TABLE `login` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `marks`
--

DROP TABLE IF EXISTS `marks`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `marks` (
  `marksid` int(11) NOT NULL AUTO_INCREMENT,
  `sid` int(11) DEFAULT NULL,
  `subid` int(11) DEFAULT NULL,
  `examid` int(11) DEFAULT NULL,
  `marksobtained` int(11) DEFAULT NULL,
  `academicyear` varchar(20) DEFAULT NULL,
  `branchid` int(11) DEFAULT NULL,
  PRIMARY KEY (`marksid`),
  UNIQUE KEY `sid` (`sid`,`subid`,`examid`,`academicyear`),
  KEY `sid_idx` (`sid`),
  KEY `subid_idx` (`subid`),
  KEY `examid_idx` (`examid`),
  CONSTRAINT `examinationid` FOREIGN KEY (`examid`) REFERENCES `exams` (`exid`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `studentid` FOREIGN KEY (`sid`) REFERENCES `student` (`sid`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `subjectid` FOREIGN KEY (`subid`) REFERENCES `subject` (`subid`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `marks`
--

LOCK TABLES `marks` WRITE;
/*!40000 ALTER TABLE `marks` DISABLE KEYS */;
INSERT INTO `marks` VALUES (2,1,1,1,100,'2017/18',0),(3,2,1,1,50,'2017/18',0),(4,3,1,1,100,'2017/18',0),(5,5,1,1,50,'2017/18',0),(6,1,2,1,35,'2017/18',0),(7,2,2,1,36,'2017/18',0),(8,3,2,1,45,'2017/18',0),(9,5,2,1,46,'2017/18',0);
/*!40000 ALTER TABLE `marks` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `parents`
--

DROP TABLE IF EXISTS `parents`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `parents` (
  `pid` int(11) NOT NULL AUTO_INCREMENT,
  `fathersname` varchar(100) DEFAULT NULL,
  `mothersname` varchar(100) DEFAULT NULL,
  `addresspermanent` varchar(500) DEFAULT NULL,
  `addresstemporary` varchar(500) DEFAULT NULL,
  `professsion` varchar(100) DEFAULT NULL,
  `parentsannualincome` varchar(100) DEFAULT NULL,
  `noofdependents` int(11) DEFAULT NULL,
  `sid` int(11) DEFAULT NULL,
  `tid` int(11) DEFAULT NULL,
  `remarks` varchar(500) DEFAULT NULL,
  `contactnumber` varchar(50) DEFAULT NULL,
  `cocontactnumber` varchar(50) DEFAULT NULL,
  `email` varchar(45) DEFAULT NULL,
  `branchid` int(11) DEFAULT NULL,
  PRIMARY KEY (`pid`),
  KEY `sid_idx` (`sid`),
  KEY `tid_idx` (`tid`),
  CONSTRAINT `sid` FOREIGN KEY (`sid`) REFERENCES `student` (`sid`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `tid` FOREIGN KEY (`tid`) REFERENCES `teacher` (`tid`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=22 DEFAULT CHARSET=utf8 COMMENT='		';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `parents`
--

LOCK TABLES `parents` WRITE;
/*!40000 ALTER TABLE `parents` DISABLE KEYS */;
INSERT INTO `parents` VALUES (1,'fathers name','mothers name','permanent address','temporary address',NULL,'45218',5,1,NULL,'notes','5555555555','5555555555','email@test.com',0),(2,'fathers name','mothers name','permanent address','temporary address','business','45218',5,2,NULL,'notes','9998633226','5555555555','test@test.com',0),(3,'fathers name','mothers name','pa','ta','business','45218',5,3,NULL,'notes','9998633226','5555555555','test@test.com',0),(4,'fathers name','mothers name','pa','ta',NULL,'45218',5,4,NULL,'remarks','9998633226','5555555555','test@test.com',0),(5,'fathers name ','mothers name','permanent address','temp addre',NULL,'85852',8,5,NULL,'notes','9986553225','9986338332','email@rmail.com',0),(6,'fathers name','mothers name','','',NULL,'85852',0,6,NULL,'','','','',0),(7,'fathers name','','','',NULL,'',0,7,NULL,'','','','',0),(8,'fathers name','','','',NULL,'',0,8,NULL,'','','','',0),(9,'fathers name','','','',NULL,'',0,9,NULL,'','','','',0),(10,'fathers name','','','',NULL,'',0,10,NULL,'','','','',0),(11,'fathers name','','','',NULL,'',0,11,NULL,'','','','',0),(12,'fathers name','','','',NULL,'',0,12,NULL,'','','','',0),(13,'fathers name','','','',NULL,'',0,13,NULL,'','','','',0),(14,'fathers name','','','',NULL,'',0,14,NULL,'','','','',0),(15,'fathers name','','','',NULL,'',0,15,NULL,'','','','',0),(16,'fathers name','','','',NULL,'',0,16,NULL,'','','','',0),(17,'fathers name','','','',NULL,'',0,17,NULL,'','','','',0),(18,'fathers name','','','',NULL,'',0,18,NULL,'','','','',0),(19,'fathers name','','','',NULL,'',0,19,NULL,'','','','',0),(20,'fathers name','','','',NULL,'',0,20,NULL,'','','','',0),(21,'sdf','','','',NULL,'',0,21,NULL,'','','','',0);
/*!40000 ALTER TABLE `parents` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `perioddetails`
--

DROP TABLE IF EXISTS `perioddetails`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `perioddetails` (
  `idperioddetails` int(11) NOT NULL AUTO_INCREMENT,
  `periodmasterid` int(11) DEFAULT NULL,
  `periods` varchar(45) DEFAULT NULL,
  `timings` varchar(100) DEFAULT NULL,
  `days` varchar(60) DEFAULT NULL,
  `subject` varchar(50) DEFAULT NULL,
  `staff` varchar(200) DEFAULT NULL,
  `branchid` int(11) DEFAULT NULL,
  PRIMARY KEY (`idperioddetails`),
  KEY `periodd_idx` (`periodmasterid`),
  CONSTRAINT `periodd` FOREIGN KEY (`periodmasterid`) REFERENCES `periodmaster` (`idperiodmaster`) ON DELETE CASCADE ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=104 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `perioddetails`
--

LOCK TABLES `perioddetails` WRITE;
/*!40000 ALTER TABLE `perioddetails` DISABLE KEYS */;
INSERT INTO `perioddetails` VALUES (1,1,'period1','1:00: AM To 2:00 AM','tuesday','maths',NULL,0),(2,1,'period2','2:00: AM To 3:00 AM','tuesday','social',NULL,0),(3,1,'period1','1:00: AM To 2:00 AM','monday','maths',NULL,0),(4,1,'period2','2:00: AM To 3:00 AM','monday','social',NULL,0),(13,5,'period1','2:05: PM To 6:10 PM','saturday','maths',NULL,0),(14,5,'period2','9:15: PM To 4:15 PM','saturday','social',NULL,0),(15,5,'period3','8:05: PM To 5:10 PM','saturday','maths',NULL,0),(16,5,'period4','7:40: PM To 11:35 PM','saturday','social',NULL,0),(17,5,'period5','10:35: PM To 7:25 PM','saturday','maths',NULL,0),(18,5,'period6','4:10: PM To 3:15 PM','saturday','social',NULL,0),(19,5,'period7','5:10: AM To 1:00 AM','saturday','maths',NULL,0),(20,5,'period1','1:00: AM To 1:00 AM','tuesday','maths',NULL,0),(21,5,'period2','2:05: PM To 1:05 AM','tuesday','social',NULL,0),(22,5,'period3','1:00: AM To 1:00 AM','tuesday','maths',NULL,0),(23,5,'period4','2:10: PM To 2:05 PM','tuesday','social',NULL,0),(24,5,'period5','4:15: PM To 5:10 PM','tuesday','maths',NULL,0),(25,5,'period6','2:05: AM To 1:00 PM','tuesday','social',NULL,0),(26,5,'period7','4:05: PM To 4:15 PM','tuesday','maths',NULL,0),(27,5,'period1','2:05: AM To 1:05 AM','wednesday','maths',NULL,0),(28,5,'period2','2:00: PM To 1:00 AM','wednesday','social',NULL,0),(29,5,'period3','3:05: PM To 2:05 PM','wednesday','maths',NULL,0),(30,5,'period4','3:05: PM To 2:00 PM','wednesday','social',NULL,0),(31,5,'period5','3:10: PM To 6:15 PM','wednesday','maths',NULL,0),(32,5,'period6','3:10: PM To 4:10 PM','wednesday','social',NULL,0),(33,5,'period7','12:15: PM To 5:10 PM','wednesday','maths',NULL,0),(34,5,'period1','3:05: AM To 1:00 PM','thursday','maths',NULL,0),(35,5,'period2','2:05: AM To 1:00 AM','thursday','social',NULL,0),(36,5,'period3','4:10: AM To 2:00 PM','thursday','maths',NULL,0),(37,5,'period4','3:05: AM To 4:05 AM','thursday','social',NULL,0),(38,5,'period5','4:15: PM To 5:10 PM','thursday','maths',NULL,0),(39,5,'period6','2:05: AM To 1:00 AM','thursday','social',NULL,0),(40,5,'period7','4:15: PM To 5:15 PM','thursday','maths',NULL,0),(41,5,'period1','3:15: PM To 4:15 PM','friday','maths',NULL,0),(42,5,'period2','4:10: PM To 2:05 PM','friday','social',NULL,0),(43,5,'period3','4:15: PM To 4:20 PM','friday','maths',NULL,0),(44,5,'period4','4:15: PM To 7:20 PM','friday','social',NULL,0),(45,5,'period5','3:15: PM To 3:10 PM','friday','maths',NULL,0),(46,5,'period6','5:10: PM To 7:20 PM','friday','social',NULL,0),(47,5,'period7','11:15: PM To 10:20 PM','friday','maths',NULL,0),(48,5,'period1','1:00: AM To 2:05 PM','monday','maths',NULL,0),(49,5,'period2','2:00: PM To 1:05 AM','monday','social',NULL,0),(50,5,'period3','2:05: PM To 3:05 PM','monday','social',NULL,0),(51,5,'period4','3:00: PM To 1:05 AM','monday','social',NULL,0),(52,5,'period5','3:05: PM To 2:05 PM','monday','social',NULL,0),(53,5,'period6','2:05: PM To 3:10 PM','monday','maths',NULL,0),(54,5,'period7','2:05: AM To 2:05 AM','monday','social',NULL,0),(87,8,'period3','1:00: PM To 1:00 AM','tuesday','maths',NULL,0),(88,8,'period2','1:00: AM To 1:00 PM','wednesday','social',NULL,0),(89,8,'period3','1:05: AM To 2:00 AM','thursday','social',NULL,0),(90,8,'period2','1:00: AM To 1:00 AM','monday','maths',NULL,0),(97,10,'period2','1:00: AM To 1:00 AM','tuesday','maths',NULL,0),(98,10,'period2','1:00: AM To 1:00 PM','tuesday','maths',NULL,0),(99,10,'period1','1:00: AM To 1:00 AM','monday','maths',NULL,0),(100,10,'period2','1:00: AM To 1:00 AM','monday','social',NULL,0),(101,11,'period3','1:05: PM To 1:05 AM','tuesday','maths','staff name one',0),(102,11,'period2','1:00: PM To 1:00 AM','wednesday','maths','staff name one',0),(103,11,'period1','1:00: AM To 1:00 AM','monday','social','staff name',0);
/*!40000 ALTER TABLE `perioddetails` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `periodmaster`
--

DROP TABLE IF EXISTS `periodmaster`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `periodmaster` (
  `idperiodmaster` int(11) NOT NULL AUTO_INCREMENT,
  `class` varchar(45) DEFAULT NULL,
  `totalperiods` int(11) DEFAULT NULL,
  `totalbreaks` int(11) DEFAULT NULL,
  `daystart` varchar(45) DEFAULT NULL,
  `dayend` varchar(45) DEFAULT NULL,
  `durationofperiod` varchar(45) DEFAULT NULL,
  `academicyear` varchar(45) DEFAULT NULL,
  `branchid` int(11) DEFAULT NULL,
  PRIMARY KEY (`idperiodmaster`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `periodmaster`
--

LOCK TABLES `periodmaster` WRITE;
/*!40000 ALTER TABLE `periodmaster` DISABLE KEYS */;
INSERT INTO `periodmaster` VALUES (1,'L.K.G-L.K.G',2,NULL,'9:00 AM','3:00 PM','1:00','2017/18',0),(5,'ALL-ALL',7,NULL,'9:00 AM','3:05 AM','1:00','2017/18',0),(8,'nursery-nursery',1,NULL,'1:00 AM','1:00 AM','1:00','2017/18',0),(10,'ALL-ALL',2,NULL,'Hour:Minuts AM','Hour:Minuts AM','00:00','2017/18',0),(11,'ALL-ALL',1,NULL,'Hour:Minuts AM','Hour:Minuts AM','00:00','2017/18',0);
/*!40000 ALTER TABLE `periodmaster` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `position`
--

DROP TABLE IF EXISTS `position`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `position` (
  `positionid` int(11) NOT NULL AUTO_INCREMENT,
  `positionname` varchar(100) DEFAULT NULL,
  `branchid` int(11) DEFAULT NULL,
  PRIMARY KEY (`positionid`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `position`
--

LOCK TABLES `position` WRITE;
/*!40000 ALTER TABLE `position` DISABLE KEYS */;
INSERT INTO `position` VALUES (1,'HM',0);
/*!40000 ALTER TABLE `position` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `student`
--

DROP TABLE IF EXISTS `student`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `student` (
  `sid` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(200) NOT NULL,
  `classstudying` varchar(45) DEFAULT NULL,
  `classadmittedin` varchar(45) DEFAULT NULL,
  `age` int(11) DEFAULT NULL,
  `gender` varchar(45) DEFAULT NULL,
  `dateofbirth` date DEFAULT NULL,
  `bloodgroup` varchar(45) DEFAULT NULL,
  `nationality` varchar(45) DEFAULT NULL,
  `religion` varchar(45) DEFAULT NULL,
  `caste` varchar(45) DEFAULT NULL,
  `admissiondate` date DEFAULT NULL,
  `admissionnumber` varchar(20) DEFAULT NULL,
  `mothertongue` varchar(45) DEFAULT NULL,
  `Remarks` varchar(500) DEFAULT NULL,
  `schoollastattended` varchar(100) DEFAULT NULL,
  `stdlaststudied` varchar(45) DEFAULT NULL,
  `createddate` date DEFAULT NULL,
  `archive` int(11) DEFAULT NULL,
  `studentpic` longtext,
  `studentexternalid` varchar(45) NOT NULL,
  `crecord` varchar(45) DEFAULT NULL,
  `crecorddate` date DEFAULT NULL,
  `placeofbirth` varchar(100) DEFAULT NULL,
  `nooftc` int(11) DEFAULT NULL,
  `dateoftc` date DEFAULT NULL,
  `classonleaving` varchar(45) DEFAULT NULL,
  `dateleaving` date DEFAULT NULL,
  `reasonleaving` varchar(500) DEFAULT NULL,
  `notcissued` int(11) DEFAULT NULL,
  `datetcissued` date DEFAULT NULL,
  `guardiandetails` varchar(200) DEFAULT NULL,
  `subsequentprogress` varchar(500) DEFAULT NULL,
  `branchid` int(11) DEFAULT NULL,
  PRIMARY KEY (`sid`),
  UNIQUE KEY `studentexternalid_UNIQUE` (`studentexternalid`)
) ENGINE=InnoDB AUTO_INCREMENT=22 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `student`
--

LOCK TABLES `student` WRITE;
/*!40000 ALTER TABLE `student` DISABLE KEYS */;
INSERT INTO `student` VALUES (1,'student one','I A','nursery ',11,'Male','2007-01-04','A +ve','Indian','Islam','caste','2018-01-20','2018/52','Urdu','notes','last school attended','nursery','2018-01-20',0,'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AFJHQkMAAAADAAAAAFJkICBkb3ViQG/gAAAAAAAAAAAAR3JuIGRvdWJAb+AAAAAAAAAAAABCbCAgZG91YkBv4AAAAAAAAAAAAEJyZFRVbnRGI1JsdAAAAAAAAAAAAAAAAEJsZCBVbnRGI1JsdAAAAAAAAAAAAAAAAFJzbHRVbnRGI1B4bEBSAAAAAAAAAAAACnZlY3RvckRhdGFib29sAQAAAABQZ1BzZW51bQAAAABQZ1BzAAAAAFBnUEMAAAAATGVmdFVudEYjUmx0AAAAAAAAAAAAAAAAVG9wIFVudEYjUmx0AAAAAAAAAAAAAAAAU2NsIFVudEYjUHJjQFkAAAAAAAAAAAAQY3JvcFdoZW5QcmludGluZ2Jvb2wAAAAADmNyb3BSZWN0Qm90dG9tbG9uZwAAAAAAAAAMY3JvcFJlY3RMZWZ0bG9uZwAAAAAAAAANY3JvcFJlY3RSaWdodGxvbmcAAAAAAAAAC2Nyb3BSZWN0VG9wbG9uZwAAAAAAOEJJTQPtAAAAAAAQAEgAAAABAAIASAAAAAEAAjhCSU0EJgAAAAAADgAAAAAAAAAAAAA/gAAAOEJJTQQNAAAAAAAEAAAAHjhCSU0EGQAAAAAABAAAAB44QklNA/MAAAAAAAkAAAAAAAAAAAEAOEJJTScQAAAAAAAKAAEAAAAAAAAAAjhCSU0D9QAAAAAASAAvZmYAAQBsZmYABgAAAAAAAQAvZmYAAQChmZoABgAAAAAAAQAyAAAAAQBaAAAABgAAAAAAAQA1AAAAAQAtAAAABgAAAAAAAThCSU0D+AAAAAAAcAAA/////////////////////////////wPoAAAAAP////////////////////////////8D6AAAAAD/////////////////////////////A+gAAAAA/////////////////////////////wPoAAA4QklNBAgAAAAAABAAAAABAAACQAAAAkAAAAAAOEJJTQQeAAAAAAAEAAAAADhCSU0EGgAAAAADOwAAAAYAAAAAAAAAAAAAAIAAAABjAAAAAwBiAG8AeQAAAAEAAAAAAAAAAAAAAAAAAAAAAAAAAQAAAAAAAAAAAAAAYwAAAIAAAAAAAAAAAAAAAAAAAAAAAQAAAAAAAAAAAAAAAAAAAAAAAAAQAAAAAQAAAAAAAG51bGwA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AAABAQABAQD/4QzdaHR0cDovL25zLmFkb2JlLmNvbS94YXAvMS4wLwA8P3hwYWNrZXQgYmVnaW49Iu+7vyIgaWQ9Ilc1TTBNcENlaGlIenJlU3pOVGN6a2M5ZCI/PiA8eDp4bXBtZXRhIHhtbG5zOng9ImFkb2JlOm5zOm1ldGEvIiB4OnhtcHRrPSJBZG9iZSBYTVAgQ29yZSA1LjMtYzAxMSA2Ni4xNDU2NjEsIDIwMTIvMDIvMDYtMTQ6NTY6MjcgICAgICAgICI+IDxyZGY6UkRGIHhtbG5zOnJkZj0iaHR0cDovL3d3dy53My5vcmcvMTk5OS8wMi8yMi1yZGYtc3ludGF4LW5zIyI+IDxyZGY6RGVzY3JpcHRpb24gcmRmOmFib3V0PSIiIHhtbG5zOnhtcE1NPSJodHRwOi8vbnMuYWRvYmUuY29tL3hhcC8xLjAvbW0vIiB4bWxuczpzdEV2dD0iaHR0cDovL25zLmFkb2JlLmNvbS94YXAvMS4wL3NUeXBlL1Jlc291cmNlRXZlbnQjIiB4bWxuczpkYz0iaHR0cDovL3B1cmwub3JnL2RjL2VsZW1lbnRzLzEuMS8iIHhtbG5zOnBob3Rvc2hvcD0iaHR0cDovL25zLmFkb2JlLmNvbS9waG90b3Nob3AvMS4wLyIgeG1sbnM6eG1wPSJodHRwOi8vbnMuYWRvYmUuY29tL3hhcC8xLjAvIiB4bXBNTTpEb2N1bWVudElEPSI5OUIwNTdEOTAxQTRBNDI4MEZDQzNGQjdEOTdDMjhDNiIgeG1wTU06SW5zdGFuY2VJRD0ieG1wLmlpZDpCNEFBRkRFREIwOTJFNzExODA5NkZGNkNFNDI0MkFGMyIgeG1wTU06T3JpZ2luYWxEb2N1bWVudElEPSI5OUIwNTdEOTAxQTRBNDI4MEZDQzNGQjdEOTdDMjhDNiIgZGM6Zm9ybWF0PSJpbWFnZS9qcGVnIiBwaG90b3Nob3A6Q29sb3JNb2RlPSIzIiBwaG90b3Nob3A6SUNDUHJvZmlsZT0iQWRvYmUgUkdCICgxOTk4KSIgeG1wOkNyZWF0ZURhdGU9IjIwMTctMDktMDZUMDg6MzY6NDUrMDU6MzAiIHhtcDpNb2RpZnlEYXRlPSIyMDE3LTA5LTA2VDA4OjQwOjI2KzA1OjMwIiB4bXA6TWV0YWRhdGFEYXRlPSIyMDE3LTA5LTA2VDA4OjQwOjI2KzA1OjMwIj4gPHhtcE1NOkhpc3Rvcnk+IDxyZGY6U2VxPiA8cmRmOmxpIHN0RXZ0OmFjdGlvbj0ic2F2ZWQiIHN0RXZ0Omluc3RhbmNlSUQ9InhtcC5paWQ6QjRBQUZERURCMDkyRTcxMTgwOTZGRjZDRTQyNDJBRjMiIHN0RXZ0OndoZW49IjIwMTctMDktMDZUMDg6NDA6MjYrMDU6MzAiIHN0RXZ0OnNvZnR3YXJlQWdlbnQ9IkFkb2JlIFBob3Rvc2hvcCBDUzYgKFdpbmRvd3MpIiBzdEV2dDpjaGFuZ2VkPSIvIi8+IDwvcmRmOlNlcT4gPC94bXBNTTpIaXN0b3J5PiA8L3JkZjpEZXNjcmlwdGlvbj4gPC9yZGY6UkRGPiA8L3g6eG1wbWV0YT4gICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICA8P3hwYWNrZXQgZW5kPSJ3Ij8+/+ICQElDQ19QUk9GSUxFAAEBAAACMEFEQkUCEAAAbW50clJHQiBYWVogB88ABgADAAAAAAAAYWNzcEFQUEwAAAAAbm9uZQAAAAAAAAAAAAAAAAAAAAAAAPbWAAEAAAAA0y1BREJFAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAKY3BydAAAAPwAAAAyZGVzYwAAATAAAABrd3RwdAAAAZwAAAAUYmtwdAAAAbAAAAAUclRSQwAAAcQAAAAOZ1RSQwAAAdQAAAAOYlRSQwAAAeQAAAAOclhZWgAAAfQAAAAUZ1hZWgAAAggAAAAUYlhZWgAAAhwAAAAUdGV4dAAAAABDb3B5cmlnaHQgMTk5OSBBZG9i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','HHJ7','25','2018-02-27','',0,NULL,'',NULL,'',0,NULL,'','',0),(2,'student two','I A','nursery B',13,'Female','2005-01-06','B +ve','Other','Hinduism','caste','2018-01-27','2018/53','Hindi','notes','last school attended','L.K.G','2018-01-27',0,'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AFJHQkMAAAADAAAAAFJkICBkb3ViQG/gAAAAAAAAAAAAR3JuIGRvdWJAb+AAAAAAAAAAAABCbCAgZG91YkBv4AAAAAAAAAAAAEJyZFRVbnRGI1JsdAAAAAAAAAAAAAAAAEJsZCBVbnRGI1JsdAAAAAAAAAAAAAAAAFJzbHRVbnRGI1B4bEBSAAAAAAAAAAAACnZlY3RvckRhdGFib29sAQAAAABQZ1BzZW51bQAAAABQZ1BzAAAAAFBnUEMAAAAATGVmdFVudEYjUmx0AAAAAAAAAAAAAAAAVG9wIFVudEYjUmx0AAAAAAAAAAAAAAAAU2NsIFVudEYjUHJjQFkAAAAAAAAAAAAQY3JvcFdoZW5QcmludGluZ2Jvb2wAAAAADmNyb3BSZWN0Qm90dG9tbG9uZwAAAAAAAAAMY3JvcFJlY3RMZWZ0bG9uZwAAAAAAAAANY3JvcFJlY3RSaWdodGxvbmcAAAAAAAAAC2Nyb3BSZWN0VG9wbG9uZwAAAAAAOEJJTQPtAAAAAAAQAEgAAAABAAIASAAAAAEAAjhCSU0EJgAAAAAADgAAAAAAAAAAAAA/gAAAOEJJTQQNAAAAAAAEAAAAHjhCSU0EGQAAAAAABAAAAB44QklNA/MAAAAAAAkAAAAAAAAAAAEAOEJJTScQAAAAAAAKAAEAAAAAAAAAAjhCSU0D9QAAAAAASAAvZmYAAQBsZmYABgAAAAAAAQAvZmYAAQChmZoABgAAAAAAAQAyAAAAAQBaAAAABgAAAAAAAQA1AAAAAQAtAAAABgAAAAAAAThCSU0D+AAAAAAAcAAA/////////////////////////////wPoAAAAAP////////////////////////////8D6AAAAAD/////////////////////////////A+gAAAAA/////////////////////////////wPoAAA4QklNBAgAAAAAABAAAAABAAACQAAAAkAAAAAAOEJJTQQeAAAAAAAEAAAAADhCSU0EGgAAAAADOwAAAAYAAAAAAAAAAAAAAIAAAABjAAAAAwBiAG8AeQAAAAEAAAAAAAAAAAAAAAAAAAAAAAAAAQAAAAAAAAAAAAAAYwAAAIAAAAAAAAAAAAAAAAAAAAAAAQAAAAAAAAAAAAAAAAAAAAAAAAAQAAAAAQAAAAAAAG51bGwA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AAABAQABAQD/4QzdaHR0cDovL25zLmFkb2JlLmNvbS94YXAvMS4wLwA8P3hwYWNrZXQgYmVnaW49Iu+7vyIgaWQ9Ilc1TTBNcENlaGlIenJlU3pOVGN6a2M5ZCI/PiA8eDp4bXBtZXRhIHhtbG5zOng9ImFkb2JlOm5zOm1ldGEvIiB4OnhtcHRrPSJBZG9iZSBYTVAgQ29yZSA1LjMtYzAxMSA2Ni4xNDU2NjEsIDIwMTIvMDIvMDYtMTQ6NTY6MjcgICAgICAgICI+IDxyZGY6UkRGIHhtbG5zOnJkZj0iaHR0cDovL3d3dy53My5vcmcvMTk5OS8wMi8yMi1yZGYtc3ludGF4LW5zIyI+IDxyZGY6RGVzY3JpcHRpb24gcmRmOmFib3V0PSIiIHhtbG5zOnhtcE1NPSJodHRwOi8vbnMuYWRvYmUuY29tL3hhcC8xLjAvbW0vIiB4bWxuczpzdEV2dD0iaHR0cDovL25zLmFkb2JlLmNvbS94YXAvMS4wL3NUeXBlL1Jlc291cmNlRXZlbnQjIiB4bWxuczpkYz0iaHR0cDovL3B1cmwub3JnL2RjL2VsZW1lbnRzLzEuMS8iIHhtbG5zOnBob3Rvc2hvcD0iaHR0cDovL25zLmFkb2JlLmNvbS9waG90b3Nob3AvMS4wLyIgeG1sbnM6eG1wPSJodHRwOi8vbnMuYWRvYmUuY29tL3hhcC8xLjAvIiB4bXBNTTpEb2N1bWVudElEPSI5OUIwNTdEOTAxQTRBNDI4MEZDQzNGQjdEOTdDMjhDNiIgeG1wTU06SW5zdGFuY2VJRD0ieG1wLmlpZDpCNEFBRkRFREIwOTJFNzExODA5NkZGNkNFNDI0MkFGMyIgeG1wTU06T3JpZ2luYWxEb2N1bWVudElEPSI5OUIwNTdEOTAxQTRBNDI4MEZDQzNGQjdEOTdDMjhDNiIgZGM6Zm9ybWF0PSJpbWFnZS9qcGVnIiBwaG90b3Nob3A6Q29sb3JNb2RlPSIzIiBwaG90b3Nob3A6SUNDUHJvZmlsZT0iQWRvYmUgUkdCICgxOTk4KSIgeG1wOkNyZWF0ZURhdGU9IjIwMTctMDktMDZUMDg6MzY6NDUrMDU6MzAiIHhtcDpNb2RpZnlEYXRlPSIyMDE3LTA5LTA2VDA4OjQwOjI2KzA1OjMwIiB4bXA6TWV0YWRhdGFEYXRlPSIyMDE3LTA5LTA2VDA4OjQwOjI2KzA1OjMwIj4gPHhtcE1NOkhpc3Rvcnk+IDxyZGY6U2VxPiA8cmRmOmxpIHN0RXZ0OmFjdGlvbj0ic2F2ZWQiIHN0RXZ0Omluc3RhbmNlSUQ9InhtcC5paWQ6QjRBQUZERURCMDkyRTcxMTgwOTZGRjZDRTQyNDJBRjMiIHN0RXZ0OndoZW49IjIwMTctMDktMDZUMDg6NDA6MjYrMDU6MzAiIHN0RXZ0OnNvZnR3YXJlQWdlbnQ9IkFkb2JlIFBob3Rvc2hvcCBDUzYgKFdpbmRvd3MpIiBzdEV2dDpjaGFuZ2VkPSIvIi8+IDwvcmRmOlNlcT4gPC94bXBNTTpIaXN0b3J5PiA8L3JkZjpEZXNjcmlwdGlvbj4gPC9yZGY6UkRGPiA8L3g6eG1wbWV0YT4gICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICA8P3hwYWNrZXQgZW5kPSJ3Ij8+/+ICQElDQ19QUk9GSUxFAAEBAAACMEFEQkUCEAAAbW50clJHQiBYWVogB88ABgADAAAAAAAAYWNzcEFQUEwAAAAAbm9uZQAAAAAAAAAAAAAAAAAAAAAAAPbWAAEAAAAA0y1BREJFAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAKY3BydAAAAPwAAAAyZGVzYwAAATAAAABrd3RwdAAAAZwAAAAUYmtwdAAAAbAAAAAUclRSQwAAAcQAAAAOZ1RSQwAAAdQAAAAOYlRSQwAAAeQAAAAOclhZWgAAAfQAAAAUZ1hZWgAAAggAAAAUYlhZWgAAAhwAAAAUdGV4dAAAAABDb3B5cmlnaHQgMTk5OSBBZG9iZSBTeXN0ZW1zIEluY29ycG9yYXRlZAAAAGRlc2MAAAAAAAAAEUFkb2JlIFJHQiAoMTk5OCkAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAFhZWiAAAAAAAADzUQABAAAAARbMWFlaIAAAAAAAAAAAAAAAAAAAAABjdXJ2AAAAAAAAAAECMwAAY3VydgAAAAAAAAABAjMAAGN1cnYAAAAAAAAAAQIzAABYWVogAAAAAAAAnBgAAE+lAAAE/FhZWiAAAAAAAAA0jQAAoCwAAA+VWFlaIAAAAAAAACYxAAAQLwAAvpz/7gAhQWRvYmUAZIAAAAABAwAQAwIDBgAAAAAAAAAAAAAAAP/bAIQAEAsLCwwLEAwMEBcPDQ8XGxQQEBQbHxcXFxcXHxEMDAwMDAwRDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAERDw8RExEVEhIVFA4ODhQUDg4ODhQRDAwMDAwREQwMDAwMDBEMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwM/8IAEQgAgABjAwEiAAIRAQMRAf/EALMAAAEFAQEAAAAAAAAAAAAAAAABAgMEBQYHAQADAQEAAAAAAAAAAAAAAAAAAQIDBBAAAgICAgEEAgIDAQAAAAAAAQIDBAAREgUQICExEyIUMgYwQSMVEQACAAQCBgcEBgkFAAAAAAABAgAREgMhMUFRIjJCBBAgYXGBUhOhYnKCkbGy0kNjMJKiwuIjM1Nzg7MUJAUSAAEEAQQDAAAAAAAAAAAAAAEAIBEhEDBAMUFRYRL/2gAMAwEBAhEDEQAAAOmAGBTRbXjMqa9ITzjWDsUgsVKAAAA4BlTmtmPDTmqHbUo05abZrOXdTyW1U74JtmogDgQMa3i7eHRcWpaZBU1aqefHYyJXVDHdPOogDkEDm9Srrc/ThallLlmJstTxqu9nQ+hVjunmUQBQApzSV8t79RtdW65myJzVH2yJkDfnAAURQK9jKVXnZ1jHonijroZt871WuFZHMuHCACz3WVa+qgclZvwc/RUksKnBvRTdHORzsqahdE1citEcjQjgsqnnFqSbZMq3mMe0HCAf/9oACAECAAEFAPHMYHHpdiTsjN4jaPk/I1hAz/fke+HWhx2dA+WXR8KpJ8t8bwkZF/HxrCPYqRmsReK5ryQDipo+f//aAAgBAwABBQDxxzifSq5xzjjL7eVHsQcGEe3kjiRvZ3rROHyjAgKdkEY7BR5X+Q3nvknz5B0QwOE47cj6ASMZ9jz/AP/aAAgBAQABBQD08Wzi3+LsezrddFd/svasJLll2S7ZV6X9i7OmKV6rfi9XZXlpVzA8012s8jGByTEEytIwmrS/U3WX/wB2D03onnsfWob/AM4NG/T8prXT/XM/WShlglXOuP69r03rJ/agUGSNRggUG1TEhNZlx40Q8o+e9jyPl3aTtEaOJV7SmphtwSiRlGS2qqZNJDItyYwvEwMW834JAwIyd7MsKm3309adGcS9i/1wW78FJC9Oxna7OKAqeWUOrwcXjRZENGAtKvDLgPGSjBYSSnEuSxq9mEOsO/RYjcTVmHEOqraNljalty5Xb/ladVFZQ9k+l0DjiI5HLhW7VQX7RWyvNPM1kMTXrGEn1XJYoUimUgwqxNZUDy/Vk/ZrVmYcW9X9pLQUIpPsiW+EVuzhONzmaauLHbywGQMjpgPlQWMdJ873qx2fWdYQ1d4Bv6EXBHodTU+3sgABrHhibP1Ysr1OeJGEBH4MPyvdX9UyryHA4teaZoYY4I1/IkY4zguRrrCMHuiudSWChahtzxBWGeGKNuUagAHPcnYxN6Y/iPYumz9SldawVV+4ISx0APYH4+DsZ//aAAgBAgIGPwDFaMdMOjalk9HPqbYXwoOAG2pLP//aAAgBAwIGPwDbQFzKqlDADyAqEqx8o+YpgxbZUjEtpQGf/9oACAEBAQY/AOrkZRkf0Vd41XCNiyu+38EOwYcpbO6iAF/h9V4RfWuGs4GtpY73FtwlF64syQGDtslfm4oJ5j/t8vvEsZ3BPHYcf7bwbvLPVTL1EODoTw3U69cqrrmm0mtvupDX707l9zIk9m1l7sMWWeoCKrmzLIDQNUFpHMCX70CoYU0Ouhl4fnWLd+xcNq4RNbgzGil/7luv8N4m4Ccxb2b1sa/7ifl3OsznJNlJ5D4Ptx6aAgkY65cKfN+JFNMmfPugyGzMwVGTZHtjATOGMBJTk3shLgO9sXE1ht24v+NusbS8LSMFzmdMA6eiYMpaIAljrjACYP1wpGy6e0Tievq3U/MMV3GCqNJwiRuYnUCfswCjAz8DGOUSa6oOqcTR1bTgQYtuNJkYtkZFQR4jqTOAGZi5UJC6xZe4iPWvipbYwBxA+WLdkcpbtrdUNbFwydqtzYtq23s7kenetBLikBqTMCe4ytAIxJhHu8n6qXVqVmcKWE6dlW3t2CE5c8rfAVqZSwbaS5aZdl/jiykplm0fqwqjIKAPAdRkOTAqfESjl2Ey1tyonoUDaHywJicBqFJ0TE5d0AaBl3mLc+7xgetaW4BlUMp+XyxVKZGR0wLhWs2lJRchUTsM0W1uGbhRWe2XVqWUpE46asHgDorsorkSprYqB5qqQ0InpCie3UZEj8qmAr7wEp64JhVK4E1T1hdrrSbwIzEFBOWBE9RgNmBoGcUtau1aghgStXe8pL64J9M214a8CYpzM5Ya4JZgzSkJDAebr22uGTu1Nr3jxr8MSOeqJyEjpiajGDjjHLOy1i7dW2fm4k96Cp4TLr8nzWXp3Wn4ipYS8hlUoYHviVwEduYiSksdQBipxSuhfvR/5FgiY/5Qcjstg3W+xBdd+Z8Y2gR1KVBLahiYDXcMRs6fmi9ygH81CL1j404P9RNiAh4cJaoxyOmNn2dFzn23OURrNrtu3f6rL/is7EARM/RE2tr4YH9mOLPXo1QHuTkckGBPvN5YpVQg93CBLRiIDroh+c5Zf5V3G9aHC3FeT3W/EjDEa4ygWbI224jko87QnLWty3m3mY/1LjQToGA6PZ4mMuzwicDtgjTlEm8YFu3vHNpTC/eiu2wBYbSkSBPmWndgjSMCIZiCbzMGCpgQOBHaAVEi2EjoPEsADIdC9pn9A6COJc4B7RBioeMdox+9AqwlkdBh7yjFjMuxyP5duMD3mBLw6VHZ9fR//9k=','RVWZ',NULL,NULL,NULL,0,NULL,NULL,NULL,'',NULL,NULL,NULL,NULL,0),(3,'student three','I A','L.K.G B',13,'Male','2005-01-06','B +ve','Other','Hinduism','caste','2018-01-27','2018/54','Hindi','notes','last school attended','L.K.G','2018-01-27',0,'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AFJHQkMAAAADAAAAAFJkICBkb3ViQG/gAAAAAAAAAAAAR3JuIGRvdWJAb+AAAAAAAAAAAABCbCAgZG91YkBv4AAAAAAAAAAAAEJyZFRVbnRGI1JsdAAAAAAAAAAAAAAAAEJsZCBVbnRGI1JsdAAAAAAAAAAAAAAAAFJzbHRVbnRGI1B4bEBSAAAAAAAAAAAACnZlY3RvckRhdGFib29sAQAAAABQZ1BzZW51bQAAAABQZ1BzAAAAAFBnUEMAAAAATGVmdFVudEYjUmx0AAAAAAAAAAAAAAAAVG9wIFVudEYjUmx0AAAAAAAAAAAAAAAAU2NsIFVudEYjUHJjQFkAAAAAAAAAAAAQY3JvcFdoZW5QcmludGluZ2Jvb2wAAAAADmNyb3BSZWN0Qm90dG9tbG9uZwAAAAAAAAAMY3JvcFJlY3RMZWZ0bG9uZwAAAAAAAAANY3JvcFJlY3RSaWdodGxvbmcAAAAAAAAAC2Nyb3BSZWN0VG9wbG9uZwAAAAAAOEJJTQPtAAAAAAAQAEgAAAABAAIASAAAAAEAAjhCSU0EJgAAAAAADgAAAAAAAAAAAAA/gAAAOEJJTQQNAAAAAAAEAAAAHjhCSU0EGQAAAAAABAAAAB44QklNA/MAAAAAAAkAAAAAAAAAAAEAOEJJTScQAAAAAAAKAAEAAAAAAAAAAjhCSU0D9QAAAAAASAAvZmYAAQBsZmYABgAAAAAAAQAvZmYAAQChmZoABgAAAAAAAQAyAAAAAQBaAAAABgAAAAAAAQA1AAAAAQAtAAAABgAAAAAAAThCSU0D+AAAAAAAcAAA/////////////////////////////wPoAAAAAP////////////////////////////8D6AAAAAD/////////////////////////////A+gAAAAA/////////////////////////////wPoAAA4QklNBAgAAAAAABAAAAABAAACQAAAAkAAAAAAOEJJTQQeAAAAAAAEAAAAADhCSU0EGgAAAAADOwAAAAYAAAAAAAAAAAAAAIAAAABjAAAAAwBiAG8AeQAAAAEAAAAAAAAAAAAAAAAAAAAAAAAAAQAAAAAAAAAAAAAAYwAAAIAAAAAAAAAAAAAAAAAAAAAAAQAAAAAAAAAAAAAAAAAAAAAAAAAQAAAAAQAAAAAAAG51bGwA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AAABAQABAQD/4QzdaHR0cDovL25zLmFkb2JlLmNvbS94YXAvMS4wLwA8P3hwYWNrZXQgYmVnaW49Iu+7vyIgaWQ9Ilc1TTBNcENlaGlIenJlU3pOVGN6a2M5ZCI/PiA8eDp4bXBtZXRhIHhtbG5zOng9ImFkb2JlOm5zOm1ldGEvIiB4OnhtcHRrPSJBZG9iZSBYTVAgQ29yZSA1LjMtYzAxMSA2Ni4xNDU2NjEsIDIwMTIvMDIvMDYtMTQ6NTY6MjcgICAgICAgICI+IDxyZGY6UkRGIHhtbG5zOnJkZj0iaHR0cDovL3d3dy53My5vcmcvMTk5OS8wMi8yMi1yZGYtc3ludGF4LW5zIyI+IDxyZGY6RGVzY3JpcHRpb24gcmRmOmFib3V0PSIiIHhtbG5zOnhtcE1NPSJodHRwOi8vbnMuYWRvYmUuY29tL3hhcC8xLjAvbW0vIiB4bWxuczpzdEV2dD0iaHR0cDovL25zLmFkb2JlLmNvbS94YXAvMS4wL3NUeXBlL1Jlc291cmNlRXZlbnQjIiB4bWxuczpkYz0iaHR0cDovL3B1cmwub3JnL2RjL2VsZW1lbnRzLzEuMS8iIHhtbG5zOnBob3Rvc2hvcD0iaHR0cDovL25zLmFkb2JlLmNvbS9waG90b3Nob3AvMS4wLyIgeG1sbnM6eG1wPSJodHRwOi8vbnMuYWRvYmUuY29tL3hhcC8xLjAvIiB4bXBNTTpEb2N1bWVudElEPSI5OUIwNTdEOTAxQTRBNDI4MEZDQzNGQjdEOTdDMjhDNiIgeG1wTU06SW5zdGFuY2VJRD0ieG1wLmlpZDpCNEFBRkRFREIwOTJFNzExODA5NkZGNkNFNDI0MkFGMyIgeG1wTU06T3JpZ2luYWxEb2N1bWVudElEPSI5OUIwNTdEOTAxQTRBNDI4MEZDQzNGQjdEOTdDMjhDNiIgZGM6Zm9ybWF0PSJpbWFnZS9qcGVnIiBwaG90b3Nob3A6Q29sb3JNb2RlPSIzIiBwaG90b3Nob3A6SUNDUHJvZmlsZT0iQWRvYmUgUkdCICgxOTk4KSIgeG1wOkNyZWF0ZURhdGU9IjIwMTctMDktMDZUMDg6MzY6NDUrMDU6MzAiIHhtcDpNb2RpZnlEYXRlPSIyMDE3LTA5LTA2VDA4OjQwOjI2KzA1OjMwIiB4bXA6TWV0YWRhdGFEYXRlPSIyMDE3LTA5LTA2VDA4OjQwOjI2KzA1OjMwIj4gPHhtcE1NOkhpc3Rvcnk+IDxyZGY6U2VxPiA8cmRmOmxpIHN0RXZ0OmFjdGlvbj0ic2F2ZWQiIHN0RXZ0Omluc3RhbmNlSUQ9InhtcC5paWQ6QjRBQUZERURCMDkyRTcxMTgwOTZGRjZDRTQyNDJBRjMiIHN0RXZ0OndoZW49IjIwMTctMDktMDZUMDg6NDA6MjYrMDU6MzAiIHN0RXZ0OnNvZnR3YXJlQWdlbnQ9IkFkb2JlIFBob3Rvc2hvcCBDUzYgKFdpbmRvd3MpIiBzdEV2dDpjaGFuZ2VkPSIvIi8+IDwvcmRmOlNlcT4gPC94bXBNTTpIaXN0b3J5PiA8L3JkZjpEZXNjcmlwdGlvbj4gPC9yZGY6UkRGPiA8L3g6eG1wbWV0YT4gICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICA8P3hwYWNrZXQgZW5kPSJ3Ij8+/+ICQElDQ19QUk9GSUxFAAEBAAACMEFEQkUCEAAAbW50clJHQiBYWVogB88ABgADAAAAAAAAYWNzcEFQUEwAAAAAbm9uZQAAAAAAAAAAAAAAAAAAAAAAAPbWAAEAAAAA0y1BREJFAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAKY3BydAAAAPwAAAAyZGVzYwAAATAAAABrd3RwdAAAAZwAAAAUYmtwdAAAAbAAAAAUclRSQwAAAcQAAAAOZ1RSQwAAAdQAAAAOYlRSQwAAAeQAAAAOclhZWgAAAfQAAAAUZ1hZWgAAAggAAAAUYlhZWgAAAhwAAAAUdGV4dAAAAABDb3B5cmlnaHQgMTk5OSBBZG9i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','QTHO',NULL,NULL,NULL,0,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,0),(4,'test','L.K.G A','nursery A',13,'Male','2005-01-06','A +ve','Indian','Islam','caste','2018-02-14','525/2018','Urdu','remarks','last school attended','nursery','2018-02-14',0,'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AFJHQkMAAAADAAAAAFJkICBkb3ViQG/gAAAAAAAAAAAAR3JuIGRvdWJAb+AAAAAAAAAAAABCbCAgZG91YkBv4AAAAAAAAAAAAEJyZFRVbnRGI1JsdAAAAAAAAAAAAAAAAEJsZCBVbnRGI1JsdAAAAAAAAAAAAAAAAFJzbHRVbnRGI1B4bEBSAAAAAAAAAAAACnZlY3RvckRhdGFib29sAQAAAABQZ1BzZW51bQAAAABQZ1BzAAAAAFBnUEMAAAAATGVmdFVudEYjUmx0AAAAAAAAAAAAAAAAVG9wIFVudEYjUmx0AAAAAAAAAAAAAAAAU2NsIFVudEYjUHJjQFkAAAAAAAAAAAAQY3JvcFdoZW5QcmludGluZ2Jvb2wAAAAADmNyb3BSZWN0Qm90dG9tbG9uZwAAAAAAAAAMY3JvcFJlY3RMZWZ0bG9uZwAAAAAAAAANY3JvcFJlY3RSaWdodGxvbmcAAAAAAAAAC2Nyb3BSZWN0VG9wbG9uZwAAAAAAOEJJTQPtAAAAAAAQAEgAAAABAAIASAAAAAEAAjhCSU0EJgAAAAAADgAAAAAAAAAAAAA/gAAAOEJJTQQNAAAAAAAEAAAAHjhCSU0EGQAAAAAABAAAAB44QklNA/MAAAAAAAkAAAAAAAAAAAEAOEJJTScQAAAAAAAKAAEAAAAAAAAAAjhCSU0D9QAAAAAASAAvZmYAAQBsZmYABgAAAAAAAQAvZmYAAQChmZoABgAAAAAAAQAyAAAAAQBaAAAABgAAAAAAAQA1AAAAAQAtAAAABgAAAAAAAThCSU0D+AAAAAAAcAAA/////////////////////////////wPoAAAAAP////////////////////////////8D6AAAAAD/////////////////////////////A+gAAAAA/////////////////////////////wPoAAA4QklNBAgAAAAAABAAAAABAAACQAAAAkAAAAAAOEJJTQQeAAAAAAAEAAAAADhCSU0EGgAAAAADOwAAAAYAAAAAAAAAAAAAAIAAAABjAAAAAwBiAG8AeQAAAAEAAAAAAAAAAAAAAAAAAAAAAAAAAQAAAAAAAAAAAAAAYwAAAIAAAAAAAAAAAAAAAAAAAAAAAQAAAAAAAAAAAAAAAAAAAAAAAAAQAAAAAQAAAAAAAG51bGwA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AAABAQABAQD/4QzdaHR0cDovL25zLmFkb2JlLmNvbS94YXAvMS4wLwA8P3hwYWNrZXQgYmVnaW49Iu+7vyIgaWQ9Ilc1TTBNcENlaGlIenJlU3pOVGN6a2M5ZCI/PiA8eDp4bXBtZXRhIHhtbG5zOng9ImFkb2JlOm5zOm1ldGEvIiB4OnhtcHRrPSJBZG9iZSBYTVAgQ29yZSA1LjMtYzAxMSA2Ni4xNDU2NjEsIDIwMTIvMDIvMDYtMTQ6NTY6MjcgICAgICAgICI+IDxyZGY6UkRGIHhtbG5zOnJkZj0iaHR0cDovL3d3dy53My5vcmcvMTk5OS8wMi8yMi1yZGYtc3ludGF4LW5zIyI+IDxyZGY6RGVzY3JpcHRpb24gcmRmOmFib3V0PSIiIHhtbG5zOnhtcE1NPSJodHRwOi8vbnMuYWRvYmUuY29tL3hhcC8xLjAvbW0vIiB4bWxuczpzdEV2dD0iaHR0cDovL25zLmFkb2JlLmNvbS94YXAvMS4wL3NUeXBlL1Jlc291cmNlRXZlbnQjIiB4bWxuczpkYz0iaHR0cDovL3B1cmwub3JnL2RjL2VsZW1lbnRzLzEuMS8iIHhtbG5zOnBob3Rvc2hvcD0iaHR0cDovL25zLmFkb2JlLmNvbS9waG90b3Nob3AvMS4wLyIgeG1sbnM6eG1wPSJodHRwOi8vbnMuYWRvYmUuY29tL3hhcC8xLjAvIiB4bXBNTTpEb2N1bWVudElEPSI5OUIwNTdEOTAxQTRBNDI4MEZDQzNGQjdEOTdDMjhDNiIgeG1wTU06SW5zdGFuY2VJRD0ieG1wLmlpZDpCNEFBRkRFREIwOTJFNzExODA5NkZGNkNFNDI0MkFGMyIgeG1wTU06T3JpZ2luYWxEb2N1bWVudElEPSI5OUIwNTdEOTAxQTRBNDI4MEZDQzNGQjdEOTdDMjhDNiIgZGM6Zm9ybWF0PSJpbWFnZS9qcGVnIiBwaG90b3Nob3A6Q29sb3JNb2RlPSIzIiBwaG90b3Nob3A6SUNDUHJvZmlsZT0iQWRvYmUgUkdCICgxOTk4KSIgeG1wOkNyZWF0ZURhdGU9IjIwMTctMDktMDZUMDg6MzY6NDUrMDU6MzAiIHhtcDpNb2RpZnlEYXRlPSIyMDE3LTA5LTA2VDA4OjQwOjI2KzA1OjMwIiB4bXA6TWV0YWRhdGFEYXRlPSIyMDE3LTA5LTA2VDA4OjQwOjI2KzA1OjMwIj4gPHhtcE1NOkhpc3Rvcnk+IDxyZGY6U2VxPiA8cmRmOmxpIHN0RXZ0OmFjdGlvbj0ic2F2ZWQiIHN0RXZ0Omluc3RhbmNlSUQ9InhtcC5paWQ6QjRBQUZERURCMDkyRTcxMTgwOTZGRjZDRTQyNDJBRjMiIHN0RXZ0OndoZW49IjIwMTctMDktMDZUMDg6NDA6MjYrMDU6MzAiIHN0RXZ0OnNvZnR3YXJlQWdlbnQ9IkFkb2JlIFBob3Rvc2hvcCBDUzYgKFdpbmRvd3MpIiBzdEV2dDpjaGFuZ2VkPSIvIi8+IDwvcmRmOlNlcT4gPC94bXBNTTpIaXN0b3J5PiA8L3JkZjpEZXNjcmlwdGlvbj4gPC9yZGY6UkRGPiA8L3g6eG1wbWV0YT4gICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICA8P3hwYWNrZXQgZW5kPSJ3Ij8+/+ICQElDQ19QUk9GSUxFAAEBAAACMEFEQkUCEAAAbW50clJHQiBYWVogB88ABgADAAAAAAAAYWNzcEFQUEwAAAAAbm9uZQAAAAAAAAAAAAAAAAAAAAAAAPbWAAEAAAAA0y1BREJFAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAKY3BydAAAAPwAAAAyZGVzYwAAATAAAABrd3RwdAAAAZwAAAAUYmtwdAAAAbAAAAAUclRSQwAAAcQAAAAOZ1RSQwAAAdQAAAAOYlRSQwAAAeQAAAAOclhZWgAAAfQAAAAUZ1hZWgAAAggAAAAUYlhZWgAAAhwAAAAUdGV4dAAAAABDb3B5cmlnaHQgMTk5OSBBZG9i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','PAUR','5','2018-02-14','bidar',5,'2018-02-14','I','2018-02-14','reason',52,'2018-02-14','guardian','2',0),(5,'abraham','I A','I A',11,'Male','2007-04-01','A +ve','Indian','Islam','','2018-04-03','5285/2018','Urdu','notes','','L.K.G','2018-04-03',0,'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AFJHQkMAAAADAAAAAFJkICBkb3ViQG/gAAAAAAAAAAAAR3JuIGRvdWJAb+AAAAAAAAAAAABCbCAgZG91YkBv4AAAAAAAAAAAAEJyZFRVbnRGI1JsdAAAAAAAAAAAAAAAAEJsZCBVbnRGI1JsdAAAAAAAAAAAAAAAAFJzbHRVbnRGI1B4bEBSAAAAAAAAAAAACnZlY3RvckRhdGFib29sAQAAAABQZ1BzZW51bQAAAABQZ1BzAAAAAFBnUEMAAAAATGVmdFVudEYjUmx0AAAAAAAAAAAAAAAAVG9wIFVudEYjUmx0AAAAAAAAAAAAAAAAU2NsIFVudEYjUHJjQFkAAAAAAAAAAAAQY3JvcFdoZW5QcmludGluZ2Jvb2wAAAAADmNyb3BSZWN0Qm90dG9tbG9uZwAAAAAAAAAMY3JvcFJlY3RMZWZ0bG9uZwAAAAAAAAANY3JvcFJlY3RSaWdodGxvbmcAAAAAAAAAC2Nyb3BSZWN0VG9wbG9uZwAAAAAAOEJJTQPtAAAAAAAQAEgAAAABAAIASAAAAAEAAjhCSU0EJgAAAAAADgAAAAAAAAAAAAA/gAAAOEJJTQQNAAAAAAAEAAAAHjhCSU0EGQAAAAAABAAAAB44QklNA/MAAAAAAAkAAAAAAAAAAAEAOEJJTScQAAAAAAAKAAEAAAAAAAAAAjhCSU0D9QAAAAAASAAvZmYAAQBsZmYABgAAAAAAAQAvZmYAAQChmZoABgAAAAAAAQAyAAAAAQBaAAAABgAAAAAAAQA1AAAAAQAtAAAABgAAAAAAAThCSU0D+AAAAAAAcAAA/////////////////////////////wPoAAAAAP////////////////////////////8D6AAAAAD/////////////////////////////A+gAAAAA/////////////////////////////wPoAAA4QklNBAgAAAAAABAAAAABAAACQAAAAkAAAAAAOEJJTQQeAAAAAAAEAAAAADhCSU0EGgAAAAADOwAAAAYAAAAAAAAAAAAAAIAAAABjAAAAAwBiAG8AeQAAAAEAAAAAAAAAAAAAAAAAAAAAAAAAAQAAAAAAAAAAAAAAYwAAAIAAAAAAAAAAAAAAAAAAAAAAAQAAAAAAAAAAAAAAAAAAAAAAAAAQAAAAAQAAAAAAAG51bGwA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AAABAQABAQD/4QzdaHR0cDovL25zLmFkb2JlLmNvbS94YXAvMS4wLwA8P3hwYWNrZXQgYmVnaW49Iu+7vyIgaWQ9Ilc1TTBNcENlaGlIenJlU3pOVGN6a2M5ZCI/PiA8eDp4bXBtZXRhIHhtbG5zOng9ImFkb2JlOm5zOm1ldGEvIiB4OnhtcHRrPSJBZG9iZSBYTVAgQ29yZSA1LjMtYzAxMSA2Ni4xNDU2NjEsIDIwMTIvMDIvMDYtMTQ6NTY6MjcgICAgICAgICI+IDxyZGY6UkRGIHhtbG5zOnJkZj0iaHR0cDovL3d3dy53My5vcmcvMTk5OS8wMi8yMi1yZGYtc3ludGF4LW5zIyI+IDxyZGY6RGVzY3JpcHRpb24gcmRmOmFib3V0PSIiIHhtbG5zOnhtcE1NPSJodHRwOi8vbnMuYWRvYmUuY29tL3hhcC8xLjAvbW0vIiB4bWxuczpzdEV2dD0iaHR0cDovL25zLmFkb2JlLmNvbS94YXAvMS4wL3NUeXBlL1Jlc291cmNlRXZlbnQjIiB4bWxuczpkYz0iaHR0cDovL3B1cmwub3JnL2RjL2VsZW1lbnRzLzEuMS8iIHhtbG5zOnBob3Rvc2hvcD0iaHR0cDovL25zLmFkb2JlLmNvbS9waG90b3Nob3AvMS4wLyIgeG1sbnM6eG1wPSJodHRwOi8vbnMuYWRvYmUuY29tL3hhcC8xLjAvIiB4bXBNTTpEb2N1bWVudElEPSI5OUIwNTdEOTAxQTRBNDI4MEZDQzNGQjdEOTdDMjhDNiIgeG1wTU06SW5zdGFuY2VJRD0ieG1wLmlpZDpCNEFBRkRFREIwOTJFNzExODA5NkZGNkNFNDI0MkFGMyIgeG1wTU06T3JpZ2luYWxEb2N1bWVudElEPSI5OUIwNTdEOTAxQTRBNDI4MEZDQzNGQjdEOTdDMjhDNiIgZGM6Zm9ybWF0PSJpbWFnZS9qcGVnIiBwaG90b3Nob3A6Q29sb3JNb2RlPSIzIiBwaG90b3Nob3A6SUNDUHJvZmlsZT0iQWRvYmUgUkdCICgxOTk4KSIgeG1wOkNyZWF0ZURhdGU9IjIwMTctMDktMDZUMDg6MzY6NDUrMDU6MzAiIHhtcDpNb2RpZnlEYXRlPSIyMDE3LTA5LTA2VDA4OjQwOjI2KzA1OjMwIiB4bXA6TWV0YWRhdGFEYXRlPSIyMDE3LTA5LTA2VDA4OjQwOjI2KzA1OjMwIj4gPHhtcE1NOkhpc3Rvcnk+IDxyZGY6U2VxPiA8cmRmOmxpIHN0RXZ0OmFjdGlvbj0ic2F2ZWQiIHN0RXZ0Omluc3RhbmNlSUQ9InhtcC5paWQ6QjRBQUZERURCMDkyRTcxMTgwOTZGRjZDRTQyNDJBRjMiIHN0RXZ0OndoZW49IjIwMTctMDktMDZUMDg6NDA6MjYrMDU6MzAiIHN0RXZ0OnNvZnR3YXJlQWdlbnQ9IkFkb2JlIFBob3Rvc2hvcCBDUzYgKFdpbmRvd3MpIiBzdEV2dDpjaGFuZ2VkPSIvIi8+IDwvcmRmOlNlcT4gPC94bXBNTTpIaXN0b3J5PiA8L3JkZjpEZXNjcmlwdGlvbj4gPC9yZGY6UkRGPiA8L3g6eG1wbWV0YT4gICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICA8P3hwYWNrZXQgZW5kPSJ3Ij8+/+ICQElDQ19QUk9GSUxFAAEBAAACMEFEQkUCEAAAbW50clJHQiBYWVogB88ABgADAAAAAAAAYWNzcEFQUEwAAAAAbm9uZQAAAAAAAAAAAAAAAAAAAAAAAPbWAAEAAAAA0y1BREJFAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAKY3BydAAAAPwAAAAyZGVzYwAAATAAAABrd3RwdAAAAZwAAAAUYmtwdAAAAbAAAAAUclRSQwAAAcQAAAAOZ1RSQwAAAdQAAAAOYlRSQwAAAeQAAAAOclhZWgAAAfQAAAAUZ1hZWgAAAggAAAAUYlhZWgAAAhwAAAAUdGV4dAAAAABDb3B5cmlnaHQgMTk5OSBBZG9i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','RQEO','25','2018-04-03','bangalore',52,'2018-04-03','','2018-04-03','',0,'2018-04-03','','',0),(6,'anil','II','I',10,'Male','2008-04-01','A +ve','Indian','Christianity','','2018-04-06','525/2018','Urdu','','last','nursery','2018-04-06',0,'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AFJHQkMAAAADAAAAAFJkICBkb3ViQG/gAAAAAAAAAAAAR3JuIGRvdWJAb+AAAAAAAAAAAABCbCAgZG91YkBv4AAAAAAAAAAAAEJyZFRVbnRGI1JsdAAAAAAAAAAAAAAAAEJsZCBVbnRGI1JsdAAAAAAAAAAAAAAAAFJzbHRVbnRGI1B4bEBSAAAAAAAAAAAACnZlY3RvckRhdGFib29sAQAAAABQZ1BzZW51bQAAAABQZ1BzAAAAAFBnUEMAAAAATGVmdFVudEYjUmx0AAAAAAAAAAAAAAAAVG9wIFVudEYjUmx0AAAAAAAAAAAAAAAAU2NsIFVudEYjUHJjQFkAAAAAAAAAAAAQY3JvcFdoZW5QcmludGluZ2Jvb2wAAAAADmNyb3BSZWN0Qm90dG9tbG9uZwAAAAAAAAAMY3JvcFJlY3RMZWZ0bG9uZwAAAAAAAAANY3JvcFJlY3RSaWdodGxvbmcAAAAAAAAAC2Nyb3BSZWN0VG9wbG9uZwAAAAAAOEJJTQPtAAAAAAAQAEgAAAABAAIASAAAAAEAAjhCSU0EJgAAAAAADgAAAAAAAAAAAAA/gAAAOEJJTQQNAAAAAAAEAAAAHjhCSU0EGQAAAAAABAAAAB44QklNA/MAAAAAAAkAAAAAAAAAAAEAOEJJTScQAAAAAAAKAAEAAAAAAAAAAjhCSU0D9QAAAAAASAAvZmYAAQBsZmYABgAAAAAAAQAvZmYAAQChmZoABgAAAAAAAQAyAAAAAQBaAAAABgAAAAAAAQA1AAAAAQAtAAAABgAAAAAAAThCSU0D+AAAAAAAcAAA/////////////////////////////wPoAAAAAP////////////////////////////8D6AAAAAD/////////////////////////////A+gAAAAA/////////////////////////////wPoAAA4QklNBAgAAAAAABAAAAABAAACQAAAAkAAAAAAOEJJTQQeAAAAAAAEAAAAADhCSU0EGgAAAAADOwAAAAYAAAAAAAAAAAAAAIAAAABjAAAAAwBiAG8AeQAAAAEAAAAAAAAAAAAAAAAAAAAAAAAAAQAAAAAAAAAAAAAAYwAAAIAAAAAAAAAAAAAAAAAAAAAAAQAAAAAAAAAAAAAAAAAAAAAAAAAQAAAAAQAAAAAAAG51bGwAAAACAAAABmJvdW5kc09iamMAAAABAAAAAAAAUmN0MQAAAAQAAAAAVG9wIGxvbmcAAAAAAAAAAExlZnRsb25nAAAAAAAAAABCdG9tbG9uZwAAAIAAAAAAUmdodGxvbmcAAABjAAAABnNsaWNlc1ZsTHMAAAABT2JqYwAAAAEAAAAAAAVzbGljZQAAABIAAAAHc2xpY2VJRGxvbmcAAAAAAAAAB2dyb3VwSURsb25nAAAAAAAAAAZvcmlnaW5lbnVtAAAADEVTbGljZU9yaWdpbgAAAA1hdXRvR2VuZXJhdGVkAAAAAFR5cGVlbnVtAAAACkVTbGljZVR5cGUAAAAASW1nIAAAAAZib3VuZHNPYmpjAAAAAQAAAAAAAFJjdDEAAAAEAAAAAFRvcCBsb25nAAAAAAAAAABMZWZ0bG9uZwAAAAAAAAAAQnRvbWxvbmcAAACAAAAAAFJnaHRsb25nAAAAYwAAAAN1cmxURVhUAAAAAQAAAAAAAG51bGxURVhUAAAAAQAAAAAAAE1zZ2VURVhUAAAAAQAAAAAABmFsdFRhZ1RFWFQAAAABAAAAAAAOY2VsbFRleHRJc0hUTUxib29sAQAAAAhjZWxsVGV4dFRFWFQAAAABAAAAAAAJaG9yekFsaWduZW51bQAAAA9FU2xpY2VIb3J6QWxpZ24AAAAHZGVmYXVsdAAAAAl2ZXJ0QWxpZ25lbnVtAAAAD0VTbGljZVZlcnRBbGlnbgAAAAdkZWZhdWx0AAAAC2JnQ29sb3JUeXBlZW51bQAAABFFU2xpY2VCR0NvbG9yVHlwZQAAAABOb25lAAAACXRvcE91dHNldGxvbmcAAAAAAAAACmxlZnRPdXRzZXRsb25nAAAAAAAAAAxib3R0b21PdXRzZXRsb25nAAAAAAAAAAtyaWdodE91dHNldGxvbmcAAAAAADhCSU0EKAAAAAAADAAAAAI/8AAAAAAAADhCSU0EFAAAAAAABAAAAAE4QklNBAwAAAAACqwAAAABAAAAYwAAAIAAAAEsAACWAAAACpAAGAAB/9j/7QAMQWRvYmVfQ00AAf/uAA5BZG9iZQBkgAAAAAH/2wCEAAwICAgJCAwJCQwRCwoLERUPDAwPFRgTExUTExgRDAwMDAwMEQwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwBDQsLDQ4NEA4OEBQODg4UFA4ODg4UEQwMDAwMEREMDAwMDAwRDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDP/AABEIAIAAYwMBIgACEQEDEQH/3QAEAAf/xAE/AAABBQEBAQEBAQAAAAAAAAADAAECBAUGBwgJCgsBAAEFAQEBAQEBAAAAAAAAAAEAAgMEBQYHCAkKCxAAAQQBAwIEAgUHBggFAwwzAQACEQMEIRIxBUFRYRMicYEyBhSRobFCIyQVUsFiMzRygtFDByWSU/Dh8WNzNRaisoMmRJNUZEXCo3Q2F9JV4mXys4TD03Xj80YnlKSFtJXE1OT0pbXF1eX1VmZ2hpamtsbW5vY3R1dnd4eXp7fH1+f3EQACAgECBAQDBAUGBwcGBTUBAAIRAyExEgRBUWFxIhMFMoGRFKGxQiPBUtHwMyRi4XKCkkNTFWNzNPElBhaisoMHJjXC0kSTVKMXZEVVNnRl4vKzhMPTdePzRpSkhbSVxNTk9KW1xdXl9VZmdoaWprbG1ub2JzdHV2d3h5ent8f/2gAMAwEAAhEDEQA/AO0SSSQSpMks/rHW8LpFHqZDt1rhNWO36b/l+az+WkSBqVAW6CfY6JgwvOup/XTrzhY/eMCo/QrrAL/6otf7v7f5iwbeo5tr62fabSLDoTY7br9KPd+kcozmHQWvGI9X2PY7mD9yivH6+p5rLaxVk2t3EhrhY4bSzy3fnre6b9cOudPDjlj9oYmjyXum0btTseP+jW/2JDKOopJxnzfQklV6b1TC6pQbsOzftj1azo+skTttZ+arSku1ikySSSKUkkkkp//Q7RJMkglo9Y6mzpuJ6kb7rDsor/ecfh+az85cccSzIyX5OTNuTYYJPgPdo381rV0XUqLMnLdYeK/ayeB/UH/TQ/Ra1/o1gtLh7vEj81n9p3vsVbLIk+AbGOGniXiup4Ntz3ue0kdmt8Pisl2JaXF9vtj6LRyANNq9O/Y7X07NsOsOpjss236uh+SYb7JP4KK5Bm9sF4R1Ar3Pg/SADR+D1ZwrntyQXAwK/Ssb2cySWn+uxy6TO+rnp5BaJ2u4PmqVvQ72v9o3HTUfFHiWmDLCuFLqsrFtdRc4Sy4ct7bbP9JXvb/NvXcdF6r+0sWbQ2vLq9uRU3gEf4Rn/B2/mrg2YmQyKw0mHkwR2XRdIP2TNZc0n3/o7mfvB30bG/8AFuT8WSjXQseSGl9Q9UmSSVpgUkmSSU//0ezSTSkOUFzz3Usw/bTSw/RdDgtDGYDabCPcdJ8lzT7XW9cvrGp9Z2ngAumrfVSzfc8MaBy4wqZNyPm3YCojydCpoIB7+KkMZgII/wBYWe3rfTmGDbqewBP/AFKu4+djXgGt4dPyP3FPBj4JIPYtfO6eLiHAxHIVB2E9sNiSOCfitu17BqTAVK/Owq9H3sB7gkJsoi1RJra3LsprrcYaJadNPFA31eo0thtlevxAKuZFuNa0mqxr+/tIJK5/qWQce2qwdyQUy6KpgGJL24MgHxEpIdDg6ipw4cxpHzCnKvNFdJRlJJT/AP/S7FIuA1JgDUlMovaHscw6hwLT8xCaV/m8g2qyr61W+oIFzi9n9UiVuZLMdh+0ZLd7ah7WnUD4N/eVO3E2W4jmyXU2FoJ7MA9w/srcqrbbW3cJVONmydzq35REDQ1A/J5bqH1sysPLqxm4FNTLmh1QudFjg6dnsra73+36C3arLBf6WRSK7WEB+wy0SPa5rv5StnpeMXh/psLhqCQCR/VlK9myB2B0+JUsuGu1MceLiOtg7aUj6vb6WMC2STpAXNdR6vi9Nrrsu6f69d7N7Xue1pcJLZa1/wBPdtcuj6i07Kt3HHzULel4uZW05NLLgB7dwmJ52/upo4eI3qmXFw1E084benZYLasQ4OS1rXlkbdHe9llTme2xv8tUuvbiMauJc94mOdfaupu6dQz3xJHBOpA8P6qx76m2Zzbiz1DQ0mthMDeTDHPP7rYUcqBJ6LgDKo9To9YxobWxo4a1oHyCRChQLG49TbTusDBvPnGqlKujYNA7ldJNKSKn/9Pr0kpTILmjlUv9fc2AIcTI0IcIcrGE8bA3wRHsD27XfIjQhVg0VWuYJjQifAqvOBiSehLbxZBMCJ+aIr6B1BYxrZVDNOY478etljhG31HFrR+8XbQ5279xNYbA0O1cB2HJVR3XGNcWOou3DSBWTqPmm2CzCEifSOJlm359+yv0G7A79JvO0kR/gtq0MR36EMs+m0QT4rIt62x0RRcfM1ka+HuRMPIyshxPomln5vqaE/JN2N7plCQAscLZz7GsaSs/DYLMtrHM0cd249w33IuYHF2zkzGnc9lcxsQ0El7g50bRA0A/ORhEykNPSDqx5JxgDr6iDwtgnVMkUlaaKkkkklP/1OtSTSkmrmSqdQvox2VOtdtfY/ZSP3j+c3+qrKxPry52L0rAzuBVe+e3LdzZ+5Cfylfj+cOrRkNcIJ1HZTdQ15mBB7wsii31qK8isxvaHA/ESrbOqitsXNI8xqFVB7t0E9G0cRlYlok+Kr2X+jOuvihO63jOEMJe790Ayqr/AFcl++wbW9mf+SQkR0SbO5+1DmdbZg5GG9zfUbfkNpP9qfcz+U1dG9pY9zDy0wuTy8MZXX+gYpEg5otI/k1NNrv+oXb3YxtBsb9OSfjKsYNYX4tTmPm+jTTKT67K/ptI/IoAqRhXSTJJKf/V6uU7Q552sBc48NGpVnEwfUh9oO06trGhP8px/NatGuoVjaxorH8nSfn9JIBdbSp6c/2uuO2SBsHP9pVPrP0Qda6LkYAH6astyMb+vX+Z/wBcZ7FuOb7BGkQQovBDxY380ykYggjugSIIPZ4bopa7DbW7hntjuFdsxWzrq091pdS6KKMh/UMNv6G/3ZFLfzXfnXM/ku/wirsbubpqOxVOUDE8Jb0ZiQ4otE4tbT7Y18EQUw1W/TdxCduLfkPGPQP0jvzjw0fvuTeHsFxPUlq9CwDf1m3qj/5vArdj0ed10es5v/FUjZ/1xdM1oDQELHx6caqvEoH6Omdzu7nnWyx38pyOz3Eu7DQK5jhwRAaWWfHInp0VtMSdf5KDZjUP1dU35aH/AKKtOCg8eHkPmU9Y0PsVHg6J8e3h/wCZJLR9Nvh/J+SSVBT/AP/W9KqbAJ8VMjjz0Ua5LSPzmmCE7z7Qf5QTlKA3VlvfUITLDEO54KM3RxChZXLtzeSNUlIrsssIpq+m7lxEhv8A5JyrO6ZL/UreAXD3NIgE+Ldv0Vb9Brmz+cNZ/wCqTkRAfpHB7HzCbKIluLXxkY/KactxaCRw4aEeaYUZNFD3EE3ucHtYzQgD6LHuV0YbPXsvY33OMmxxmD/wbPoojayXaH4k8pkMdGz9F88tih9WFTt1ILBDn6Qex/Ob/ZVhjQAGjhqcwAABxwnGgUjEoqGpLB+84n5AKZ4UTo5o8j+KKGUj+CSjISQU/wD/2ThCSU0EIQAAAAAAVQAAAAEBAAAADwBBAGQAbwBiAGUAIABQAGgAbwB0AG8AcwBoAG8AcAAAABMAQQBkAG8AYgBlACAAUABoAG8AdABvAHMAaABvAHAAIABDAFMANgAAAAEAOEJJTQQGAAAAAAAHAAABAQABAQD/4QzdaHR0cDovL25zLmFkb2JlLmNvbS94YXAvMS4wLwA8P3hwYWNrZXQgYmVnaW49Iu+7vyIgaWQ9Ilc1TTBNcENlaGlIenJlU3pOVGN6a2M5ZCI/PiA8eDp4bXBtZXRhIHhtbG5zOng9ImFkb2JlOm5zOm1ldGEvIiB4OnhtcHRrPSJBZG9iZSBYTVAgQ29yZSA1LjMtYzAxMSA2Ni4xNDU2NjEsIDIwMTIvMDIvMDYtMTQ6NTY6MjcgICAgICAgICI+IDxyZGY6UkRGIHhtbG5zOnJkZj0iaHR0cDovL3d3dy53My5vcmcvMTk5OS8wMi8yMi1yZGYtc3ludGF4LW5zIyI+IDxyZGY6RGVzY3JpcHRpb24gcmRmOmFib3V0PSIiIHhtbG5zOnhtcE1NPSJodHRwOi8vbnMuYWRvYmUuY29tL3hhcC8xLjAvbW0vIiB4bWxuczpzdEV2dD0iaHR0cDovL25zLmFkb2JlLmNvbS94YXAvMS4wL3NUeXBlL1Jlc291cmNlRXZlbnQjIiB4bWxuczpkYz0iaHR0cDovL3B1cmwub3JnL2RjL2VsZW1lbnRzLzEuMS8iIHhtbG5zOnBob3Rvc2hvcD0iaHR0cDovL25zLmFkb2JlLmNvbS9waG90b3Nob3AvMS4wLyIgeG1sbnM6eG1wPSJodHRwOi8vbnMuYWRvYmUuY29tL3hhcC8xLjAvIiB4bXBNTTpEb2N1bWVudElEPSI5OUIwNTdEOTAxQTRBNDI4MEZDQzNGQjdEOTdDMjhDNiIgeG1wTU06SW5zdGFuY2VJRD0ieG1wLmlpZDpCNEFBRkRFREIwOTJFNzExODA5NkZGNkNFNDI0MkFGMyIgeG1wTU06T3JpZ2luYWxEb2N1bWVudElEPSI5OUIwNTdEOTAxQTRBNDI4MEZDQzNGQjdEOTdDMjhDNiIgZGM6Zm9ybWF0PSJpbWFnZS9qcGVnIiBwaG90b3Nob3A6Q29sb3JNb2RlPSIzIiBwaG90b3Nob3A6SUNDUHJvZmlsZT0iQWRvYmUgUkdCICgxOTk4KSIgeG1wOkNyZWF0ZURhdGU9IjIwMTctMDktMDZUMDg6MzY6NDUrMDU6MzAiIHhtcDpNb2RpZnlEYXRlPSIyMDE3LTA5LTA2VDA4OjQwOjI2KzA1OjMwIiB4bXA6TWV0YWRhdGFEYXRlPSIyMDE3LTA5LTA2VDA4OjQwOjI2KzA1OjMwIj4gPHhtcE1NOkhpc3Rvcnk+IDxyZGY6U2VxPiA8cmRmOmxpIHN0RXZ0OmFjdGlvbj0ic2F2ZWQiIHN0RXZ0Omluc3RhbmNlSUQ9InhtcC5paWQ6QjRBQUZERURCMDkyRTcxMTgwOTZGRjZDRTQyNDJBRjMiIHN0RXZ0OndoZW49IjIwMTctMDktMDZUMDg6NDA6MjYrMDU6MzAiIHN0RXZ0OnNvZnR3YXJlQWdlbnQ9IkFkb2JlIFBob3Rvc2hvcCBDUzYgKFdpbmRvd3MpIiBzdEV2dDpjaGFuZ2VkPSIvIi8+IDwvcmRmOlNlcT4gPC94bXBNTTpIaXN0b3J5PiA8L3JkZjpEZXNjcmlwdGlvbj4gPC9yZGY6UkRGPiA8L3g6eG1wbWV0YT4gICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICA8P3hwYWNrZXQgZW5kPSJ3Ij8+/+ICQElDQ19QUk9GSUxFAAEBAAACMEFEQkUCEAAAbW50clJHQiBYWVogB88ABgADAAAAAAAAYWNzcEFQUEwAAAAAbm9uZQAAAAAAAAAAAAAAAAAAAAAAAPbWAAEAAAAA0y1BREJFAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAKY3BydAAAAPwAAAAyZGVzYwAAATAAAABrd3RwdAAAAZwAAAAUYmtwdAAAAbAAAAAUclRSQwAAAcQAAAAOZ1RSQwAAAdQAAAAOYlRSQwAAAeQAAAAOclhZWgAAAfQAAAAUZ1hZWgAAAggAAAAUYlhZWgAAAhwAAAAUdGV4dAAAAABDb3B5cmlnaHQgMTk5OSBBZG9i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','C801','25','2018-04-06','bangalore',52,'2018-04-06','','2018-04-06','',0,'2018-04-06','g','',0),(7,'neha','nursery','',12,'Female','2006-04-05','','','','','2018-04-06','654/2018','','','','','2018-04-06',0,NULL,'SF5K','25','2018-04-06','bangalore',0,'2018-04-06','','2018-04-06','',0,'2018-04-06','','',0),(8,'john','L.K.G','',12,'Male','2006-04-05','','','','','2018-04-06','417/2018','','','','','2018-04-06',0,NULL,'0GXP','25','2018-04-06','',0,'2018-04-06','','2018-04-06','',0,'2018-04-06','','',0),(9,'pooja','U.K.G','',12,'Female','2006-04-05','','','','','2018-04-06','963/2018','','','','','2018-04-06',0,NULL,'M3O6','25','2018-04-06','',0,'2018-04-06','','2018-04-06','',0,'2018-04-06','','',0),(10,'abdul hakeem','IV ',' ',12,'Male','2006-04-05','','','','','2018-04-06','852/2018','','','','','2018-04-06',0,'','P3AG','','2018-04-06','',0,'2018-04-06','','2018-04-06','',0,'2018-04-06','','',0),(11,'fatima','V','',0,'Female',NULL,'','','','','2018-04-06','531/2018','','','','','2018-04-06',0,NULL,'TF26','','2018-04-06','',0,'2018-04-06','','2018-04-06','',0,'2018-04-06','','',0),(12,'maneesh','V','',0,'Male',NULL,'','','','','2018-04-06','317/2018','','','','','2018-04-06',0,NULL,'JDB5','','2018-04-06','',0,'2018-04-06','','2018-04-06','',0,'2018-04-06','','',0),(13,'patil','VI','',0,'Male',NULL,'','','','','2018-04-06','632/2018','','','','','2018-04-06',0,NULL,'Y6QP','','2018-04-06','',0,'2018-04-06','','2018-04-06','',0,'2018-04-06','','',0),(14,'ramesh','VI','',0,'Male',NULL,'','','','','2018-04-06','631/2018','','','','','2018-04-06',0,NULL,'7908','','2018-04-06','',0,'2018-04-06','','2018-04-06','',0,'2018-04-06','','',0),(15,'geeta','VI','',0,'Female',NULL,'','','','','2018-04-06','621/2018','','','','','2018-04-06',0,NULL,'PP78','','2018-04-06','',0,'2018-04-06','','2018-04-06','',0,'2018-04-06','','',0),(16,'tina','III','',11,'Female','2007-04-01','','','','','2018-04-06','612/2018','','','','','2018-04-06',0,NULL,'25KF','','2018-04-06','',0,'2018-04-06','','2018-04-06','',0,'2018-04-06','','',0),(17,'priya','III','',0,'Female',NULL,'','','','','2018-04-06','373/2018','','','','','2018-04-06',0,NULL,'B99O','','2018-04-06','',0,'2018-04-06','','2018-04-06','',0,'2018-04-06','','',0),(18,'test','','',0,NULL,NULL,'','','','','2018-05-29','256/2018','','','','','2018-05-29',0,NULL,'BZF6','','2018-05-29','',0,'2018-05-29','','2018-05-29','',0,'2018-05-29','','',0),(19,'asdf','','',0,NULL,NULL,'','','','','2018-05-29','25/2018','','','','','2018-05-29',0,NULL,'Y0KK','','2018-05-29','',0,'2018-05-29','','2018-05-29','',0,'2018-05-29','','',0),(20,'test','','',0,NULL,NULL,'','','','','2018-05-29','25/20147','','','','','2018-05-29',0,NULL,'8PZT','','2018-05-29','',0,'2018-05-29','','2018-05-29','',0,'2018-05-29','','',0),(21,'sdf','','',0,NULL,NULL,'','','','','2018-05-29','20/2254','','','','','2018-05-29',0,NULL,'FYVQ','','2018-05-29','',0,'2018-05-29','','2018-05-29','',0,'2018-05-29','','',0);
/*!40000 ALTER TABLE `student` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `student_documents`
--

DROP TABLE IF EXISTS `student_documents`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `student_documents` (
  `studdocid` int(11) NOT NULL AUTO_INCREMENT,
  `tcoriginal` int(11) DEFAULT NULL,
  `tcoriginalissuedate` date DEFAULT NULL,
  `tcduplicateno` int(11) DEFAULT NULL,
  `tcduplicateissuedate` date DEFAULT NULL,
  `fksidstudent` int(11) DEFAULT NULL,
  `branchid` int(11) DEFAULT NULL,
  PRIMARY KEY (`studdocid`),
  KEY `sid_idx` (`fksidstudent`),
  CONSTRAINT `fksidstudent` FOREIGN KEY (`fksidstudent`) REFERENCES `student` (`sid`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='	';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `student_documents`
--

LOCK TABLES `student_documents` WRITE;
/*!40000 ALTER TABLE `student_documents` DISABLE KEYS */;
/*!40000 ALTER TABLE `student_documents` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `subject`
--

DROP TABLE IF EXISTS `subject`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `subject` (
  `subid` int(11) NOT NULL AUTO_INCREMENT,
  `subjectname` varchar(45) DEFAULT NULL,
  `minmarks` int(11) DEFAULT NULL,
  `maxmarks` int(11) DEFAULT NULL,
  `branchid` int(11) DEFAULT NULL,
  PRIMARY KEY (`subid`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `subject`
--

LOCK TABLES `subject` WRITE;
/*!40000 ALTER TABLE `subject` DISABLE KEYS */;
INSERT INTO `subject` VALUES (1,'maths',35,100,0),(2,'social',35,100,0);
/*!40000 ALTER TABLE `subject` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `teacher`
--

DROP TABLE IF EXISTS `teacher`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `teacher` (
  `tid` int(11) NOT NULL AUTO_INCREMENT,
  `teachername` varchar(100) NOT NULL,
  `designation` varchar(45) DEFAULT NULL,
  `subjectsteaching` varchar(500) DEFAULT NULL,
  `dateofjoining` date DEFAULT NULL,
  `classesteaching` varchar(200) DEFAULT NULL,
  `salary` varchar(50) DEFAULT NULL,
  `department` varchar(100) DEFAULT NULL,
  `qualification` varchar(45) DEFAULT NULL,
  `totalexperience` varchar(45) DEFAULT NULL,
  `address` varchar(500) DEFAULT NULL,
  `contactnumber` varchar(20) DEFAULT NULL,
  `email` varchar(45) DEFAULT NULL,
  `remarks` varchar(400) DEFAULT NULL,
  `gender` varchar(45) DEFAULT NULL,
  `teacherexternalid` varchar(45) NOT NULL,
  `branchid` int(11) DEFAULT NULL,
  PRIMARY KEY (`tid`),
  UNIQUE KEY `teacherexternalid_UNIQUE` (`teacherexternalid`)
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `teacher`
--

LOCK TABLES `teacher` WRITE;
/*!40000 ALTER TABLE `teacher` DISABLE KEYS */;
INSERT INTO `teacher` VALUES (1,'staff name','',NULL,'2004-05-01',NULL,'232332','Teaching','bed','2','address','9998633226','test@test.com','remarks','male','ZK0T',0),(2,'staff name one','',NULL,'2004-05-01',NULL,'232332','Teaching','bed','2','address','9998633226','test@test.com','remarks','male','HX4I',0),(3,'staff three','HM',NULL,'2018-01-04',NULL,'5254','Teaching','be','2','address','9986552558','test@test.com','585','female','RNA2',0),(9,'staff with login','HM',NULL,'2018-01-04',NULL,'5254','Teaching','','2','address','9986553225','test@test.com','585','male','MS83',0),(12,'non teaching','',NULL,NULL,NULL,'','non teaching','','','','','','','','PODL',0),(13,'Andrew nathan pane ','HM',NULL,'2018-01-04',NULL,'5254','non teaching','be','2','address','9986552558','demomfi@mifos.org','remarks','male','',0),(14,'login staff ','HM',NULL,'2019-11-05',NULL,'23232','Teaching','test','3','test','7411688470','vgjfhibr@sharklasers.com','remarks','male','9NXS',0);
/*!40000 ALTER TABLE `teacher` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `transfercertificate`
--

DROP TABLE IF EXISTS `transfercertificate`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `transfercertificate` (
  `tcid` int(11) NOT NULL AUTO_INCREMENT,
  `sid` int(11) DEFAULT NULL,
  `applicationstatus` varchar(20) DEFAULT NULL,
  `noofissues` int(11) DEFAULT NULL,
  `dateofissues` date DEFAULT NULL,
  `generalconduct` varchar(500) DEFAULT NULL,
  `progress` varchar(100) DEFAULT NULL,
  `branchid` int(11) DEFAULT NULL,
  PRIMARY KEY (`tcid`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `transfercertificate`
--

LOCK TABLES `transfercertificate` WRITE;
/*!40000 ALTER TABLE `transfercertificate` DISABLE KEYS */;
INSERT INTO `transfercertificate` VALUES (1,1,'applied',1,'2018-03-21',NULL,NULL,0);
/*!40000 ALTER TABLE `transfercertificate` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `vouchermaster`
--

DROP TABLE IF EXISTS `vouchermaster`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `vouchermaster` (
  `vouchermasterid` int(11) NOT NULL AUTO_INCREMENT,
  `vouchername` varchar(100) DEFAULT NULL,
  `branchid` int(11) DEFAULT NULL,
  PRIMARY KEY (`vouchermasterid`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `vouchermaster`
--

LOCK TABLES `vouchermaster` WRITE;
/*!40000 ALTER TABLE `vouchermaster` DISABLE KEYS */;
INSERT INTO `vouchermaster` VALUES (1,'Receipt',0),(2,'Payment',0),(3,'Contra',0),(4,'Journal',0),(5,'Sale',0),(6,'Purchase',0),(7,'Sale Return',0),(8,'Purchase Return',0),(9,'Credit Note',0),(10,'Debit Note',0);
/*!40000 ALTER TABLE `vouchermaster` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2018-05-29 15:48:33
