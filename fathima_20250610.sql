-- MySQL dump 10.13  Distrib 5.6.40, for linux-glibc2.12 (x86_64)
--
-- Host: localhost    Database: fathima
-- ------------------------------------------------------
-- Server version	5.6.40

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
  `accountcode` varchar(100) NOT NULL,
  `ssgroupmasterid` int(11) DEFAULT NULL,
  `userid` int(11) DEFAULT NULL,
  PRIMARY KEY (`accountdetailsid`),
  KEY `accountdetailsfk_idx` (`accountsubgroupmasterid`),
  KEY `acc_accountdetails_acc_accountssubgroupmaster_FK` (`ssgroupmasterid`),
  CONSTRAINT `acc_accountdetails_acc_accountssubgroupmaster_FK` FOREIGN KEY (`ssgroupmasterid`) REFERENCES `acc_accountssubgroupmaster` (`ssgroupmasterid`),
  CONSTRAINT `accountdetailsfk` FOREIGN KEY (`accountsubgroupmasterid`) REFERENCES `acc_accountsubgroupmaster` (`accountsubgroupmasterid`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=57 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `acc_accountdetails`
--

LOCK TABLES `acc_accountdetails` WRITE;
/*!40000 ALTER TABLE `acc_accountdetails` DISABLE KEYS */;
INSERT INTO `acc_accountdetails` VALUES (1,1,'Cash In Hand',1,2,'ACA01',1,1),(2,1,'Axis Bank',1,2,'ACA02',2,1),(3,1,'Prepaid Insurance',1,2,'ACA03',3,1),(4,1,'Prepaid License',1,2,'ACA04',3,1),(5,1,'Prepaid Rent',1,2,'ACA05',3,1),(6,1,'Unearned Students Fees',1,2,'ACA06',4,1),(7,2,'School Building',1,2,'AFA01',5,1),(8,2,'Wooden Furniture',1,2,'AFA02',6,1),(9,2,'Steel & Metal Furniture',1,2,'AFA03',6,1),(10,2,'Electrical Items',1,2,'AFA04',6,1),(11,2,'Refrigerator',1,2,'AFA05',7,1),(12,2,'Power Generator',1,2,'AFA06',8,1),(13,2,'Tools & Equipment',1,2,'AFA07',9,1),(14,2,'Accumulated Depreciation on Building',1,2,'AFA08',10,1),(15,2,'Accumulated Depreciation on Furniture & Fixture',1,2,'AFA09',10,1),(16,2,'Accumulated Depreciation on Electronics Appliances',1,2,'AFA10',10,1),(17,2,'Accumulated Depreciation on Machinery',1,2,'AFA11',10,1),(18,2,'Accumulated Depreciation on Tools & Equipment',1,2,'AFA12',10,1),(19,3,'Liabitlity1',2,2,'LCL01',11,1),(20,3,'Liabitlity2',2,2,'LCL02',11,1),(21,3,'Liabitlity3',2,2,'LCL03',11,1),(22,3,'Liabitlity4',2,2,'LCL04',11,1),(23,3,'Supplier A/c',2,2,'LCL05',12,1),(24,3,'Salary Payable',2,2,'LCL06',13,1),(25,3,'Rent Payable',2,2,'LCL07',13,1),(26,3,'Tax Payables',2,2,'LCL08',13,1),(27,3,'Other Outstanding Expenses',2,2,'LCL09',13,1),(28,4,'Other Non-Current Liabilities',2,2,'LNCL01',14,1),(29,5,'Income from Student Fees',4,2,'IR01',15,1),(30,5,'Other Income',4,2,'IR02',16,1),(31,6,'Rent Expenses',5,2,'EOE01',17,1),(32,6,'Salaries & Wages',5,2,'EOE02',17,1),(33,6,'License & Permits Expenses',5,2,'EOE03',17,1),(34,6,'Electricity Expenses',5,2,'EOE04',17,1),(35,6,'Drinking Water Expenses',5,2,'EOE05',17,1),(36,6,'Petrol & Fuel Expenses',5,2,'EOE06',17,1),(37,6,'Travelling & Transportation Expenses',5,2,'EOE07',17,1),(38,6,'Stationary Expenses',5,2,'EOE08',17,1),(39,6,'Vehicle Repair & Maintenance Exp',5,2,'EOE09',17,1),(40,6,'Miscellaneous Expenses',5,2,'EOE10',17,1),(41,7,'Capital',3,2,'EQOE01',18,1),(42,7,'Retained Earnings',3,2,'EQOE02',18,1),(43,7,'Year Profits / Losses',3,2,'EQOE03',18,1),(44,7,'Owners Deposits',3,2,'EQOE04',18,1),(45,7,'Shareholders Deposits',3,2,'EQOE05',18,1),(46,7,'Shareholders Equity',3,2,'EQOE06',18,1),(47,1,'Clerk',1,2,'ACA07',4,1),(48,5,'Unearned Student Fees ',4,2,'IR03',19,1),(49,1,'Current Stock',1,2,'ACA08',20,1),(50,8,'Expense-Stock',5,2,'EOE11',21,1),(51,3,'Payment Awaiting Settlement',2,2,'LCL10',13,1),(52,3,'Cheque Awaiting Settlement',2,2,'LCL11',13,1),(53,2,'Stock ',1,2,'AFA13',5,1),(54,5,'Sales of Stock',4,2,'IR03',15,1),(55,9,'cashinhand',5,2,'ac12',22,0),(56,10,'CIH -BANK',4,2,'C2A',23,0);
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
  `userid` int(11) DEFAULT NULL,
  PRIMARY KEY (`accountdetailsbalanceid`),
  KEY `accoutdetailsid_idx` (`accountdetailsid`),
  KEY `financialidfk_idx` (`financialid`),
  CONSTRAINT `accoutdetailsidfk` FOREIGN KEY (`accountdetailsid`) REFERENCES `acc_accountdetails` (`accountdetailsid`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `financialidfk` FOREIGN KEY (`financialid`) REFERENCES `acc_financialaccountingyear` (`financialid`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=57 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `acc_accountdetailsbalance`
--

LOCK TABLES `acc_accountdetailsbalance` WRITE;
/*!40000 ALTER TABLE `acc_accountdetailsbalance` DISABLE KEYS */;
INSERT INTO `acc_accountdetailsbalance` VALUES (1,1,0.0000,1591750.0000,1,'Dr','2025-04-01',2,1),(2,2,0.0000,0.0000,1,'Dr','2025-04-01',2,1),(3,3,0.0000,0.0000,1,'Dr','2025-04-01',2,1),(4,4,0.0000,0.0000,1,'Dr','2025-04-01',2,1),(5,5,0.0000,0.0000,1,'Dr','2025-04-01',2,1),(6,6,0.0000,1016972550.0000,1,'Dr','2025-04-01',2,1),(7,7,0.0000,0.0000,1,'Dr','2025-04-01',2,1),(8,8,0.0000,0.0000,1,'Dr','2025-04-01',2,1),(9,9,0.0000,0.0000,1,'Dr','2025-04-01',2,1),(10,10,0.0000,0.0000,1,'Dr','2025-04-01',2,1),(11,11,0.0000,0.0000,1,'Dr','2025-04-01',2,1),(12,12,0.0000,0.0000,1,'Dr','2025-04-01',2,1),(13,13,0.0000,0.0000,1,'Dr','2025-04-01',2,1),(14,14,0.0000,0.0000,1,'Dr','2025-04-01',2,1),(15,15,0.0000,0.0000,1,'Dr','2025-04-01',2,1),(16,16,0.0000,0.0000,1,'Dr','2025-04-01',2,1),(17,17,0.0000,0.0000,1,'Dr','2025-04-01',2,1),(18,18,0.0000,0.0000,1,'Dr','2025-04-01',2,1),(19,19,0.0000,0.0000,1,'Cr','2025-04-01',2,1),(20,20,0.0000,0.0000,1,'Cr','2025-04-01',2,1),(21,21,0.0000,0.0000,1,'Cr','2025-04-01',2,1),(22,22,0.0000,0.0000,1,'Cr','2025-04-01',2,1),(23,23,0.0000,0.0000,1,'Cr','2025-04-01',2,1),(24,24,0.0000,0.0000,1,'Cr','2025-04-01',2,1),(25,25,0.0000,0.0000,1,'Cr','2025-04-01',2,1),(26,26,0.0000,0.0000,1,'Cr','2025-04-01',2,1),(27,27,0.0000,0.0000,1,'Cr','2025-04-01',2,1),(28,28,0.0000,0.0000,1,'Cr','2025-04-01',2,1),(29,29,0.0000,1591750.0000,1,'Cr','2025-04-01',2,1),(30,30,0.0000,0.0000,1,'Cr','2025-04-01',2,1),(31,31,0.0000,0.0000,1,'Dr','2025-04-01',2,1),(32,32,0.0000,0.0000,1,'Dr','2025-04-01',2,1),(33,33,0.0000,0.0000,1,'Dr','2025-04-01',2,1),(34,34,0.0000,0.0000,1,'Dr','2025-04-01',2,1),(35,35,0.0000,0.0000,1,'Dr','2025-04-01',2,1),(36,36,0.0000,0.0000,1,'Dr','2025-04-01',2,1),(37,37,0.0000,0.0000,1,'Dr','2025-04-01',2,1),(38,38,0.0000,0.0000,1,'Dr','2025-04-01',2,1),(39,39,0.0000,0.0000,1,'Dr','2025-04-01',2,1),(40,40,0.0000,0.0000,1,'Dr','2025-04-01',2,1),(41,41,0.0000,0.0000,1,'Cr','2025-04-01',2,1),(42,42,0.0000,0.0000,1,'Cr','2025-04-01',2,1),(43,43,0.0000,0.0000,1,'Cr','2025-04-01',2,1),(44,44,0.0000,0.0000,1,'Cr','2025-04-01',2,1),(45,45,0.0000,0.0000,1,'Cr','2025-04-01',2,1),(46,46,0.0000,0.0000,1,'Cr','2025-04-01',2,1),(47,47,0.0000,0.0000,1,'Dr','2025-04-01',2,1),(48,48,0.0000,1017046550.0000,1,'Cr','2025-04-01',2,1),(49,49,0.0000,0.0000,1,'Dr','2025-04-01',2,1),(50,50,0.0000,0.0000,1,'Dr','2025-04-01',2,1),(51,51,0.0000,0.0000,1,'Cr','2025-04-01',2,1),(52,52,0.0000,0.0000,1,'Cr','2025-04-01',2,1),(53,53,0.0000,0.0000,1,'Dr','2025-04-01',2,1),(54,54,0.0000,0.0000,1,'Cr','2025-04-01',2,1),(55,55,0.0000,0.0000,1,'Dr','2025-05-23',2,0),(56,56,0.0000,0.0000,1,'Cr','2025-05-27',2,0);
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
  PRIMARY KEY (`accountgroupid`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `acc_accountgroupmaster`
--

LOCK TABLES `acc_accountgroupmaster` WRITE;
/*!40000 ALTER TABLE `acc_accountgroupmaster` DISABLE KEYS */;
INSERT INTO `acc_accountgroupmaster` VALUES (1,'Asset'),(2,'Liabilities'),(3,'Equity'),(4,'Income'),(5,'Expense');
/*!40000 ALTER TABLE `acc_accountgroupmaster` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `acc_accountssubgroupmaster`
--

DROP TABLE IF EXISTS `acc_accountssubgroupmaster`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `acc_accountssubgroupmaster` (
  `ssgroupmasterid` int(11) NOT NULL AUTO_INCREMENT,
  `ssgroupname` varchar(100) DEFAULT NULL,
  `subgroupmasterid` int(11) NOT NULL,
  `branchid` int(11) DEFAULT NULL,
  `userid` int(11) DEFAULT NULL,
  PRIMARY KEY (`ssgroupmasterid`),
  KEY `acc_accountssubgroupmaster_acc_accountsubgroupmaster_FK` (`subgroupmasterid`),
  CONSTRAINT `acc_accountssubgroupmaster_acc_accountsubgroupmaster_FK` FOREIGN KEY (`subgroupmasterid`) REFERENCES `acc_accountsubgroupmaster` (`accountsubgroupmasterid`)
) ENGINE=InnoDB AUTO_INCREMENT=24 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `acc_accountssubgroupmaster`
--

LOCK TABLES `acc_accountssubgroupmaster` WRITE;
/*!40000 ALTER TABLE `acc_accountssubgroupmaster` DISABLE KEYS */;
INSERT INTO `acc_accountssubgroupmaster` VALUES (1,'Cash In Hand',1,2,1),(2,'Cash In Bank',1,2,1),(3,'Prepayment',1,2,1),(4,'Receivables',1,2,1),(5,'Land & Building',2,2,1),(6,'Furniture & Fixture',2,2,1),(7,'Electronic Appliances',2,2,1),(8,'Machinery',2,2,1),(9,'Tools & Equipment',2,2,1),(10,'Accumulated Depreciation on Fixed Assets',2,2,1),(11,'Loan Liabilities',3,2,1),(12,'Payable Suppliers',3,2,1),(13,'Payable Others',3,2,1),(14,'Other Non-Current Liabilities',4,2,1),(15,'Student Fees',5,2,1),(16,'Other Income',5,2,1),(17,'General & Administrative',6,2,1),(18,'Owners\' Equity',7,2,1),(19,'Unearned Income',5,2,1),(20,'Stock',1,2,1),(21,'Cost of Sales',8,2,1),(22,'cash in hand',9,2,0),(23,'INCOME TYPE',10,2,0);
/*!40000 ALTER TABLE `acc_accountssubgroupmaster` ENABLE KEYS */;
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
  `userid` int(11) DEFAULT NULL,
  PRIMARY KEY (`accountsubgroupmasterid`),
  KEY `accountgroupid_idx` (`accountgroupid`),
  CONSTRAINT `accountgroupid` FOREIGN KEY (`accountgroupid`) REFERENCES `acc_accountgroupmaster` (`accountgroupid`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `acc_accountsubgroupmaster`
--

LOCK TABLES `acc_accountsubgroupmaster` WRITE;
/*!40000 ALTER TABLE `acc_accountsubgroupmaster` DISABLE KEYS */;
INSERT INTO `acc_accountsubgroupmaster` VALUES (1,'Current Assets',1,2,1),(2,'Fixed Assets',1,2,1),(3,'Current Liabilities',2,2,1),(4,'Non-Current Liabilities',2,2,1),(5,'Revenue',4,2,1),(6,'Operating Expenses',5,2,1),(7,'Owners\' Equity',3,2,1),(8,'Cost of Revenue',5,2,1),(9,'stationary',5,2,0),(10,'INCOME TYPE',4,2,0);
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
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `acc_contratransactions`
--

LOCK TABLES `acc_contratransactions` WRITE;
/*!40000 ALTER TABLE `acc_contratransactions` DISABLE KEYS */;
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
  `userid` int(11) DEFAULT NULL,
  PRIMARY KEY (`financialid`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `acc_financialaccountingyear`
--

LOCK TABLES `acc_financialaccountingyear` WRITE;
/*!40000 ALTER TABLE `acc_financialaccountingyear` DISABLE KEYS */;
INSERT INTO `acc_financialaccountingyear` VALUES (1,'2025-04-01','2025-03-31','yes',2,1);
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
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `acc_journaltransactions`
--

LOCK TABLES `acc_journaltransactions` WRITE;
/*!40000 ALTER TABLE `acc_journaltransactions` DISABLE KEYS */;
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
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `acc_paymenttransactions`
--

LOCK TABLES `acc_paymenttransactions` WRITE;
/*!40000 ALTER TABLE `acc_paymenttransactions` DISABLE KEYS */;
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
  `dramount` decimal(18,5) DEFAULT NULL,
  `cramount` decimal(18,5) DEFAULT NULL,
  `vouchertype` int(11) DEFAULT NULL,
  `date` date DEFAULT NULL,
  `narration` varchar(500) DEFAULT NULL,
  `financialyear` int(11) NOT NULL,
  `cancelvoucher` varchar(5) DEFAULT NULL,
  `branchid` int(11) DEFAULT NULL,
  PRIMARY KEY (`transactionsid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `acc_receipttransactions`
--

LOCK TABLES `acc_receipttransactions` WRITE;
/*!40000 ALTER TABLE `acc_receipttransactions` DISABLE KEYS */;
/*!40000 ALTER TABLE `acc_receipttransactions` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `acc_voucherentrytransactions`
--

DROP TABLE IF EXISTS `acc_voucherentrytransactions`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `acc_voucherentrytransactions` (
  `transactionsid` int(11) NOT NULL AUTO_INCREMENT,
  `draccountid` int(11) DEFAULT NULL,
  `craccountid` int(11) DEFAULT NULL,
  `dramount` decimal(18,5) DEFAULT NULL,
  `cramount` decimal(18,5) DEFAULT NULL,
  `vouchertype` int(11) DEFAULT NULL,
  `transactiondate` date DEFAULT NULL,
  `narration` varchar(500) DEFAULT NULL,
  `financialyear` int(11) NOT NULL,
  `cancelvoucher` varchar(5) DEFAULT NULL,
  `branchid` int(11) DEFAULT NULL,
  `entrydate` date DEFAULT NULL,
  `vouchercancellationdate` date DEFAULT NULL,
  `userid` int(11) DEFAULT NULL,
  PRIMARY KEY (`transactionsid`)
) ENGINE=InnoDB AUTO_INCREMENT=768 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `acc_voucherentrytransactions`
--

LOCK TABLES `acc_voucherentrytransactions` WRITE;
/*!40000 ALTER TABLE `acc_voucherentrytransactions` DISABLE KEYS */;
INSERT INTO `acc_voucherentrytransactions` VALUES (1,6,48,1353000.00000,1353000.00000,4,'2025-05-06','Towards Fees Stamp',1,'no',2,'2025-05-06',NULL,3),(2,6,48,1173000.00000,1173000.00000,4,'2025-05-06','Towards Fees Stamp',1,'no',2,'2025-05-06',NULL,3),(3,6,48,1032500.00000,1032500.00000,4,'2025-05-06','Towards Fees Stamp',1,'no',2,'2025-05-06',NULL,3),(4,6,48,684500.00000,684500.00000,4,'2025-05-06','Towards Fees Stamp',1,'no',2,'2025-05-06',NULL,3),(5,6,48,748000.00000,748000.00000,4,'2025-05-06','Towards Fees Stamp',1,'no',2,'2025-05-06',NULL,3),(6,6,48,756000.00000,756000.00000,4,'2025-05-06','Towards Fees Stamp',1,'no',2,'2025-05-06',NULL,3),(7,1,6,6500.00000,6500.00000,4,'2025-05-11',': Towards Fees Payment:    Receipt no: 001',1,'yes',2,'2025-05-12','2025-05-13',331),(8,48,29,6500.00000,6500.00000,1,'2025-05-11','Towards Fees Payment:    Receipt no: 001',1,'yes',2,'2025-05-12','2025-05-13',331),(9,6,48,0.00000,0.00000,4,'2025-05-15','Towards Fees Stamp',1,'no',2,'2025-05-15',NULL,331),(10,6,48,0.00000,0.00000,4,'2025-05-15','Towards Fees Stamp',1,'no',2,'2025-05-15',NULL,331),(11,6,48,0.00000,0.00000,4,'2025-05-15','Towards Fees Stamp',1,'no',2,'2025-05-15',NULL,331),(12,6,48,0.00000,0.00000,4,'2025-05-15','Towards Fees Stamp',1,'no',2,'2025-05-15',NULL,331),(13,6,48,15000.00000,15000.00000,4,'2025-05-15','Towards Fees Stamp',1,'no',2,'2025-05-15',NULL,331),(14,6,48,15000.00000,15000.00000,4,'2025-05-16','Towards Fees Stamp',1,'no',2,'2025-05-16',NULL,2),(15,6,48,15000.00000,15000.00000,4,'2025-05-16','Towards Fees Stamp',1,'no',2,'2025-05-16',NULL,2),(16,6,48,15000.00000,15000.00000,4,'2025-05-16','Towards Fees Stamp',1,'no',2,'2025-05-16',NULL,2),(17,6,48,15000.00000,15000.00000,4,'2025-05-16','Towards Fees Stamp',1,'no',2,'2025-05-16',NULL,2),(18,48,6,0.00000,0.00000,4,'2025-05-16','Towards Reversal of Fees Stamp',1,'no',2,'2025-05-16',NULL,2),(19,48,6,0.00000,0.00000,4,'2025-05-16','Towards Reversal of Fees Stamp',1,'no',2,'2025-05-16',NULL,2),(20,48,6,0.00000,0.00000,4,'2025-05-16','Towards Reversal of Fees Stamp',1,'no',2,'2025-05-16',NULL,2),(21,6,48,15000.00000,15000.00000,4,'2025-05-16','Towards Fees Stamp',1,'no',2,'2025-05-16',NULL,2),(22,48,6,0.00000,0.00000,4,'2025-05-16','Towards Reversal of Fees Stamp',1,'no',2,'2025-05-16',NULL,2),(23,48,6,0.00000,0.00000,4,'2025-05-16','Towards Reversal of Fees Stamp',1,'no',2,'2025-05-16',NULL,2),(24,48,6,0.00000,0.00000,4,'2025-05-16','Towards Reversal of Fees Stamp',1,'no',2,'2025-05-16',NULL,2),(25,6,48,15000.00000,15000.00000,4,'2025-05-16','Towards Fees Stamp',1,'no',2,'2025-05-16',NULL,2),(26,48,6,0.00000,0.00000,4,'2025-05-16','Towards Reversal of Fees Stamp',1,'no',2,'2025-05-16',NULL,2),(27,48,6,0.00000,0.00000,4,'2025-05-16','Towards Reversal of Fees Stamp',1,'no',2,'2025-05-16',NULL,2),(28,48,6,0.00000,0.00000,4,'2025-05-16','Towards Reversal of Fees Stamp',1,'no',2,'2025-05-16',NULL,2),(29,48,6,0.00000,0.00000,4,'2025-05-16','Towards Reversal of Fees Stamp',1,'no',2,'2025-05-16',NULL,2),(30,48,6,0.00000,0.00000,4,'2025-05-16','Towards Reversal of Fees Stamp',1,'no',2,'2025-05-16',NULL,2),(31,48,6,0.00000,0.00000,4,'2025-05-16','Towards Reversal of Fees Stamp',1,'no',2,'2025-05-16',NULL,2),(32,6,48,45000.00000,45000.00000,4,'2025-05-16','Towards Fees Stamp',1,'no',2,'2025-05-16',NULL,2),(33,6,48,15000.00000,15000.00000,4,'2025-05-16','Towards Fees Stamp',1,'no',2,'2025-05-16',NULL,2),(34,6,48,16500.00000,16500.00000,4,'2025-05-16','Towards Fees Stamp',1,'no',2,'2025-05-16',NULL,2),(35,6,48,15000.00000,15000.00000,4,'2025-05-16','Towards Fees Stamp',1,'no',2,'2025-05-16',NULL,2),(36,6,48,15000.00000,15000.00000,4,'2025-05-16','Towards Fees Stamp',1,'no',2,'2025-05-16',NULL,2),(37,6,48,15000.00000,15000.00000,4,'2025-05-16','Towards Fees Stamp',1,'no',2,'2025-05-16',NULL,2),(38,6,48,15000.00000,15000.00000,4,'2025-05-16','Towards Fees Stamp',1,'no',2,'2025-05-16',NULL,2),(39,6,48,15000.00000,15000.00000,4,'2025-05-16','Towards Fees Stamp',1,'no',2,'2025-05-16',NULL,2),(40,6,48,15000.00000,15000.00000,4,'2025-05-16','Towards Fees Stamp',1,'no',2,'2025-05-16',NULL,2),(41,6,48,15000.00000,15000.00000,4,'2025-05-16','Towards Fees Stamp',1,'no',2,'2025-05-16',NULL,2),(42,6,48,15000.00000,15000.00000,4,'2025-05-16','Towards Fees Stamp',1,'no',2,'2025-05-16',NULL,2),(43,6,48,15000.00000,15000.00000,4,'2025-05-16','Towards Fees Stamp',1,'no',2,'2025-05-16',NULL,2),(44,6,48,15000.00000,15000.00000,4,'2025-05-16','Towards Fees Stamp',1,'no',2,'2025-05-16',NULL,2),(45,6,48,16500.00000,16500.00000,4,'2025-05-16','Towards Fees Stamp',1,'no',2,'2025-05-16',NULL,2),(46,6,48,15000.00000,15000.00000,4,'2025-05-16','Towards Fees Stamp',1,'no',2,'2025-05-16',NULL,2),(47,1,6,3000.00000,3000.00000,4,'2025-05-16',': Towards Fees Payment:    Receipt no: 002',1,'no',2,'2025-05-16',NULL,2),(48,48,29,3000.00000,3000.00000,1,'2025-05-16','Towards Fees Payment:    Receipt no: 002',1,'no',2,'2025-05-16',NULL,2),(49,1,6,15000.00000,15000.00000,4,'2025-05-16',': Towards Fees Payment:    Receipt no: 003',1,'no',2,'2025-05-16',NULL,2),(50,48,29,15000.00000,15000.00000,1,'2025-05-16','Towards Fees Payment:    Receipt no: 003',1,'no',2,'2025-05-16',NULL,2),(51,1,6,10000.00000,10000.00000,4,'2025-05-18',': Towards Fees Payment:    Receipt no: 004',1,'no',2,'2025-05-18',NULL,2),(52,48,29,10000.00000,10000.00000,1,'2025-05-18','Towards Fees Payment:    Receipt no: 004',1,'no',2,'2025-05-18',NULL,2),(53,1,6,3000.00000,3000.00000,4,'2025-05-18',': Towards Fees Payment:    Receipt no: 005',1,'no',2,'2025-05-18',NULL,2),(54,48,29,3000.00000,3000.00000,1,'2025-05-18','Towards Fees Payment:    Receipt no: 005',1,'no',2,'2025-05-18',NULL,2),(55,1,6,15000.00000,15000.00000,4,'2025-05-18',': Towards Fees Payment:    Receipt no: 006',1,'no',2,'2025-05-18',NULL,2),(56,48,29,15000.00000,15000.00000,1,'2025-05-18','Towards Fees Payment:    Receipt no: 006',1,'no',2,'2025-05-18',NULL,2),(57,1,6,5000.00000,5000.00000,4,'2025-05-18',': Towards Fees Payment:    Receipt no: 007',1,'no',2,'2025-05-18',NULL,2),(58,48,29,5000.00000,5000.00000,1,'2025-05-18','Towards Fees Payment:    Receipt no: 007',1,'no',2,'2025-05-18',NULL,2),(59,1,6,5500.00000,5500.00000,4,'2025-05-18',': Towards Fees Payment:    Receipt no: 008',1,'no',2,'2025-05-18',NULL,2),(60,48,29,5500.00000,5500.00000,1,'2025-05-18','Towards Fees Payment:    Receipt no: 008',1,'no',2,'2025-05-18',NULL,2),(61,1,6,5500.00000,5500.00000,4,'2025-05-18',': Towards Fees Payment:    Receipt no: 009',1,'no',2,'2025-05-18',NULL,2),(62,48,29,5500.00000,5500.00000,1,'2025-05-18','Towards Fees Payment:    Receipt no: 009',1,'no',2,'2025-05-18',NULL,2),(63,1,6,5500.00000,5500.00000,4,'2025-05-18',': Towards Fees Payment:    Receipt no: 010',1,'no',2,'2025-05-18',NULL,2),(64,48,29,5500.00000,5500.00000,1,'2025-05-18','Towards Fees Payment:    Receipt no: 010',1,'no',2,'2025-05-18',NULL,2),(65,1,6,5500.00000,5500.00000,4,'2025-05-18',': Towards Fees Payment:    Receipt no: 011',1,'no',2,'2025-05-18',NULL,2),(66,48,29,5500.00000,5500.00000,1,'2025-05-18','Towards Fees Payment:    Receipt no: 011',1,'no',2,'2025-05-18',NULL,2),(67,1,6,5500.00000,5500.00000,4,'2025-05-18',': Towards Fees Payment:    Receipt no: 012',1,'no',2,'2025-05-18',NULL,2),(68,48,29,5500.00000,5500.00000,1,'2025-05-18','Towards Fees Payment:    Receipt no: 012',1,'no',2,'2025-05-18',NULL,2),(69,1,6,5500.00000,5500.00000,4,'2025-05-18',': Towards Fees Payment:    Receipt no: 013',1,'no',2,'2025-05-18',NULL,2),(70,48,29,5500.00000,5500.00000,1,'2025-05-18','Towards Fees Payment:    Receipt no: 013',1,'no',2,'2025-05-18',NULL,2),(71,1,6,1000.00000,1000.00000,4,'2025-05-18',': Towards Fees Payment:    Receipt no: 014',1,'no',2,'2025-05-18',NULL,2),(72,48,29,1000.00000,1000.00000,1,'2025-05-18','Towards Fees Payment:    Receipt no: 014',1,'no',2,'2025-05-18',NULL,2),(73,1,6,5500.00000,5500.00000,4,'2025-05-18',': Towards Fees Payment:    Receipt no: 015',1,'no',2,'2025-05-18',NULL,2),(74,48,29,5500.00000,5500.00000,1,'2025-05-18','Towards Fees Payment:    Receipt no: 015',1,'no',2,'2025-05-18',NULL,2),(75,1,6,3500.00000,3500.00000,4,'2025-05-18',': Towards Fees Payment:    Receipt no: 016',1,'no',2,'2025-05-18',NULL,2),(76,48,29,3500.00000,3500.00000,1,'2025-05-18','Towards Fees Payment:    Receipt no: 016',1,'no',2,'2025-05-18',NULL,2),(77,1,6,5500.00000,5500.00000,4,'2025-05-18',': Towards Fees Payment:    Receipt no: 017',1,'no',2,'2025-05-18',NULL,2),(78,48,29,5500.00000,5500.00000,1,'2025-05-18','Towards Fees Payment:    Receipt no: 017',1,'no',2,'2025-05-18',NULL,2),(79,1,6,15000.00000,15000.00000,4,'2025-05-18',': Towards Fees Payment:    Receipt no: 018',1,'no',2,'2025-05-18',NULL,2),(80,48,29,15000.00000,15000.00000,1,'2025-05-18','Towards Fees Payment:    Receipt no: 018',1,'no',2,'2025-05-18',NULL,2),(81,1,6,5500.00000,5500.00000,4,'2025-05-18',': Towards Fees Payment:    Receipt no: 019',1,'no',2,'2025-05-18',NULL,2),(82,48,29,5500.00000,5500.00000,1,'2025-05-18','Towards Fees Payment:    Receipt no: 019',1,'no',2,'2025-05-18',NULL,2),(83,1,6,2500.00000,2500.00000,4,'2025-05-18',': Towards Fees Payment:    Receipt no: 020',1,'no',2,'2025-05-18',NULL,2),(84,48,29,2500.00000,2500.00000,1,'2025-05-18','Towards Fees Payment:    Receipt no: 020',1,'no',2,'2025-05-18',NULL,2),(85,1,6,5500.00000,5500.00000,4,'2025-05-18',': Towards Fees Payment:    Receipt no: 021',1,'no',2,'2025-05-18',NULL,2),(86,48,29,5500.00000,5500.00000,1,'2025-05-18','Towards Fees Payment:    Receipt no: 021',1,'no',2,'2025-05-18',NULL,2),(87,1,6,5500.00000,5500.00000,4,'2025-05-18',': Towards Fees Payment:    Receipt no: 022',1,'no',2,'2025-05-18',NULL,2),(88,48,29,5500.00000,5500.00000,1,'2025-05-18','Towards Fees Payment:    Receipt no: 022',1,'no',2,'2025-05-18',NULL,2),(89,1,6,3000.00000,3000.00000,4,'2025-05-18',': Towards Fees Payment:    Receipt no: 023',1,'no',2,'2025-05-18',NULL,2),(90,48,29,3000.00000,3000.00000,1,'2025-05-18','Towards Fees Payment:    Receipt no: 023',1,'no',2,'2025-05-18',NULL,2),(91,1,6,6000.00000,6000.00000,4,'2025-05-18',': Towards Fees Payment:    Receipt no: 024',1,'no',2,'2025-05-18',NULL,2),(92,48,29,6000.00000,6000.00000,1,'2025-05-18','Towards Fees Payment:    Receipt no: 024',1,'no',2,'2025-05-18',NULL,2),(93,1,6,6000.00000,6000.00000,4,'2025-05-18',': Towards Fees Payment:    Receipt no: 025',1,'no',2,'2025-05-18',NULL,2),(94,48,29,6000.00000,6000.00000,1,'2025-05-18','Towards Fees Payment:    Receipt no: 025',1,'no',2,'2025-05-18',NULL,2),(95,1,6,10000.00000,10000.00000,4,'2025-05-18',': Towards Fees Payment:    Receipt no: 026',1,'no',2,'2025-05-18',NULL,2),(96,48,29,10000.00000,10000.00000,1,'2025-05-18','Towards Fees Payment:    Receipt no: 026',1,'no',2,'2025-05-18',NULL,2),(97,1,6,6000.00000,6000.00000,4,'2025-05-18',': Towards Fees Payment:    Receipt no: 027',1,'no',2,'2025-05-18',NULL,2),(98,48,29,6000.00000,6000.00000,1,'2025-05-18','Towards Fees Payment:    Receipt no: 027',1,'no',2,'2025-05-18',NULL,2),(99,1,6,6000.00000,6000.00000,4,'2025-05-18',': Towards Fees Payment:    Receipt no: 028',1,'no',2,'2025-05-18',NULL,2),(100,48,29,6000.00000,6000.00000,1,'2025-05-18','Towards Fees Payment:    Receipt no: 028',1,'no',2,'2025-05-18',NULL,2),(101,1,6,2000.00000,2000.00000,4,'2025-05-18',': Towards Fees Payment:    Receipt no: 029',1,'no',2,'2025-05-18',NULL,2),(102,48,29,2000.00000,2000.00000,1,'2025-05-18','Towards Fees Payment:    Receipt no: 029',1,'no',2,'2025-05-18',NULL,2),(103,1,6,6000.00000,6000.00000,4,'2025-05-18',': Towards Fees Payment:    Receipt no: 030',1,'no',2,'2025-05-18',NULL,2),(104,48,29,6000.00000,6000.00000,1,'2025-05-18','Towards Fees Payment:    Receipt no: 030',1,'no',2,'2025-05-18',NULL,2),(105,1,6,6000.00000,6000.00000,4,'2025-05-18',': Towards Fees Payment:    Receipt no: 031',1,'no',2,'2025-05-18',NULL,2),(106,48,29,6000.00000,6000.00000,1,'2025-05-18','Towards Fees Payment:    Receipt no: 031',1,'no',2,'2025-05-18',NULL,2),(107,1,6,6000.00000,6000.00000,4,'2025-05-18',': Towards Fees Payment:    Receipt no: 032',1,'no',2,'2025-05-18',NULL,2),(108,48,29,6000.00000,6000.00000,1,'2025-05-18','Towards Fees Payment:    Receipt no: 032',1,'no',2,'2025-05-18',NULL,2),(109,1,6,3000.00000,3000.00000,4,'2025-05-18',': Towards Fees Payment:    Receipt no: 033',1,'no',2,'2025-05-18',NULL,2),(110,48,29,3000.00000,3000.00000,1,'2025-05-18','Towards Fees Payment:    Receipt no: 033',1,'no',2,'2025-05-18',NULL,2),(111,1,6,5000.00000,5000.00000,4,'2025-05-18',': Towards Fees Payment:    Receipt no: 034',1,'no',2,'2025-05-18',NULL,2),(112,48,29,5000.00000,5000.00000,1,'2025-05-18','Towards Fees Payment:    Receipt no: 034',1,'no',2,'2025-05-18',NULL,2),(113,1,6,6000.00000,6000.00000,4,'2025-05-18',': Towards Fees Payment:    Receipt no: 035',1,'no',2,'2025-05-18',NULL,2),(114,48,29,6000.00000,6000.00000,1,'2025-05-18','Towards Fees Payment:    Receipt no: 035',1,'no',2,'2025-05-18',NULL,2),(115,1,6,6000.00000,6000.00000,4,'2025-05-18',': Towards Fees Payment:    Receipt no: 036',1,'no',2,'2025-05-18',NULL,2),(116,48,29,6000.00000,6000.00000,1,'2025-05-18','Towards Fees Payment:    Receipt no: 036',1,'no',2,'2025-05-18',NULL,2),(117,1,6,3000.00000,3000.00000,4,'2025-05-18',': Towards Fees Payment:    Receipt no: 037',1,'no',2,'2025-05-18',NULL,2),(118,48,29,3000.00000,3000.00000,1,'2025-05-18','Towards Fees Payment:    Receipt no: 037',1,'no',2,'2025-05-18',NULL,2),(119,1,6,3000.00000,3000.00000,4,'2025-05-18',': Towards Fees Payment:    Receipt no: 038',1,'no',2,'2025-05-18',NULL,2),(120,48,29,3000.00000,3000.00000,1,'2025-05-18','Towards Fees Payment:    Receipt no: 038',1,'no',2,'2025-05-18',NULL,2),(121,1,6,2000.00000,2000.00000,4,'2025-05-18',': Towards Fees Payment:    Receipt no: 039',1,'no',2,'2025-05-18',NULL,2),(122,48,29,2000.00000,2000.00000,1,'2025-05-18','Towards Fees Payment:    Receipt no: 039',1,'no',2,'2025-05-18',NULL,2),(123,1,6,6500.00000,6500.00000,4,'2025-05-18',': Towards Fees Payment:    Receipt no: 040',1,'no',2,'2025-05-18',NULL,2),(124,48,29,6500.00000,6500.00000,1,'2025-05-18','Towards Fees Payment:    Receipt no: 040',1,'no',2,'2025-05-18',NULL,2),(125,1,6,6500.00000,6500.00000,4,'2025-05-18',': Towards Fees Payment:    Receipt no: 041',1,'no',2,'2025-05-18',NULL,2),(126,48,29,6500.00000,6500.00000,1,'2025-05-18','Towards Fees Payment:    Receipt no: 041',1,'no',2,'2025-05-18',NULL,2),(127,1,6,17000.00000,17000.00000,4,'2025-05-18',': Towards Fees Payment:    Receipt no: 042',1,'no',2,'2025-05-18',NULL,2),(128,48,29,17000.00000,17000.00000,1,'2025-05-18','Towards Fees Payment:    Receipt no: 042',1,'no',2,'2025-05-18',NULL,2),(129,1,6,3000.00000,3000.00000,4,'2025-05-18',': Towards Fees Payment:    Receipt no: 043',1,'no',2,'2025-05-18',NULL,2),(130,48,29,3000.00000,3000.00000,1,'2025-05-18','Towards Fees Payment:    Receipt no: 043',1,'no',2,'2025-05-18',NULL,2),(131,1,6,2500.00000,2500.00000,4,'2025-05-18',': Towards Fees Payment:    Receipt no: 044',1,'no',2,'2025-05-18',NULL,2),(132,48,29,2500.00000,2500.00000,1,'2025-05-18','Towards Fees Payment:    Receipt no: 044',1,'no',2,'2025-05-18',NULL,2),(133,1,6,7000.00000,7000.00000,4,'2025-05-18',': Towards Fees Payment:    Receipt no: 045',1,'no',2,'2025-05-18',NULL,2),(134,48,29,7000.00000,7000.00000,1,'2025-05-18','Towards Fees Payment:    Receipt no: 045',1,'no',2,'2025-05-18',NULL,2),(135,1,6,5000.00000,5000.00000,4,'2025-05-18',': Towards Fees Payment:    Receipt no: 046',1,'no',2,'2025-05-18',NULL,2),(136,48,29,5000.00000,5000.00000,1,'2025-05-18','Towards Fees Payment:    Receipt no: 046',1,'no',2,'2025-05-18',NULL,2),(137,1,6,7000.00000,7000.00000,4,'2025-05-18',': Towards Fees Payment:    Receipt no: 047',1,'no',2,'2025-05-18',NULL,2),(138,48,29,7000.00000,7000.00000,1,'2025-05-18','Towards Fees Payment:    Receipt no: 047',1,'no',2,'2025-05-18',NULL,2),(139,1,6,2500.00000,2500.00000,4,'2025-05-18',': Towards Fees Payment:    Receipt no: 048',1,'no',2,'2025-05-18',NULL,2),(140,48,29,2500.00000,2500.00000,1,'2025-05-18','Towards Fees Payment:    Receipt no: 048',1,'no',2,'2025-05-18',NULL,2),(141,1,6,7000.00000,7000.00000,4,'2025-05-18',': Towards Fees Payment:    Receipt no: 049',1,'no',2,'2025-05-18',NULL,2),(142,48,29,7000.00000,7000.00000,1,'2025-05-18','Towards Fees Payment:    Receipt no: 049',1,'no',2,'2025-05-18',NULL,2),(143,1,6,1000.00000,1000.00000,4,'2025-05-18',': Towards Fees Payment:    Receipt no: 050',1,'no',2,'2025-05-18',NULL,2),(144,48,29,1000.00000,1000.00000,1,'2025-05-18','Towards Fees Payment:    Receipt no: 050',1,'no',2,'2025-05-18',NULL,2),(145,1,6,18700.00000,18700.00000,4,'2025-05-18',': Towards Fees Payment:    Receipt no: 051',1,'no',2,'2025-05-18',NULL,2),(146,48,29,18700.00000,18700.00000,1,'2025-05-18','Towards Fees Payment:    Receipt no: 051',1,'no',2,'2025-05-18',NULL,2),(147,1,6,2100.00000,2100.00000,4,'2025-05-18',': Towards Fees Payment:    Receipt no: 052',1,'no',2,'2025-05-18',NULL,2),(148,48,29,2100.00000,2100.00000,1,'2025-05-18','Towards Fees Payment:    Receipt no: 052',1,'no',2,'2025-05-18',NULL,2),(149,1,6,1000.00000,1000.00000,4,'2025-05-18',': Towards Fees Payment:    Receipt no: 053',1,'no',2,'2025-05-18',NULL,2),(150,48,29,1000.00000,1000.00000,1,'2025-05-18','Towards Fees Payment:    Receipt no: 053',1,'no',2,'2025-05-18',NULL,2),(151,1,6,7000.00000,7000.00000,4,'2025-05-18',': Towards Fees Payment:    Receipt no: 054',1,'no',2,'2025-05-18',NULL,2),(152,48,29,7000.00000,7000.00000,1,'2025-05-18','Towards Fees Payment:    Receipt no: 054',1,'no',2,'2025-05-18',NULL,2),(153,6,48,18900.00000,18900.00000,4,'2025-05-19','Towards Fees Stamp',1,'no',2,'2025-05-19',NULL,2),(154,6,48,15000.00000,15000.00000,4,'2025-05-19','Towards Fees Stamp',1,'no',2,'2025-05-19',NULL,2),(155,6,48,15000.00000,15000.00000,4,'2025-05-19','Towards Fees Stamp',1,'no',2,'2025-05-19',NULL,2),(156,6,48,15000.00000,15000.00000,4,'2025-05-19','Towards Fees Stamp',1,'no',2,'2025-05-19',NULL,2),(157,6,48,16500.00000,16500.00000,4,'2025-05-19','Towards Fees Stamp',1,'no',2,'2025-05-19',NULL,2),(158,6,48,15000.00000,15000.00000,4,'2025-05-19','Towards Fees Stamp',1,'no',2,'2025-05-19',NULL,2),(159,1,6,5500.00000,5500.00000,4,'2025-05-21',': Towards Fees Payment:    Receipt no: 055',1,'no',2,'2025-05-21',NULL,2),(160,48,29,5500.00000,5500.00000,1,'2025-05-21','Towards Fees Payment:    Receipt no: 055',1,'no',2,'2025-05-21',NULL,2),(161,1,6,5500.00000,5500.00000,4,'2025-05-21',': Towards Fees Payment:    Receipt no: 056',1,'no',2,'2025-05-21',NULL,2),(162,48,29,5500.00000,5500.00000,1,'2025-05-21','Towards Fees Payment:    Receipt no: 056',1,'no',2,'2025-05-21',NULL,2),(163,1,6,5500.00000,5500.00000,4,'2025-05-21',': Towards Fees Payment:    Receipt no: 057',1,'no',2,'2025-05-21',NULL,2),(164,48,29,5500.00000,5500.00000,1,'2025-05-21','Towards Fees Payment:    Receipt no: 057',1,'no',2,'2025-05-21',NULL,2),(165,1,6,5500.00000,5500.00000,4,'2025-05-21',': Towards Fees Payment:    Receipt no: 058',1,'no',2,'2025-05-21',NULL,2),(166,48,29,5500.00000,5500.00000,1,'2025-05-21','Towards Fees Payment:    Receipt no: 058',1,'no',2,'2025-05-21',NULL,2),(167,1,6,6000.00000,6000.00000,4,'2025-05-21',': Towards Fees Payment:    Receipt no: 059',1,'no',2,'2025-05-21',NULL,2),(168,48,29,6000.00000,6000.00000,1,'2025-05-21','Towards Fees Payment:    Receipt no: 059',1,'no',2,'2025-05-21',NULL,2),(169,1,6,6000.00000,6000.00000,4,'2025-05-21',': Towards Fees Payment:    Receipt no: 060',1,'no',2,'2025-05-21',NULL,2),(170,48,29,6000.00000,6000.00000,1,'2025-05-21','Towards Fees Payment:    Receipt no: 060',1,'no',2,'2025-05-21',NULL,2),(171,1,6,6000.00000,6000.00000,4,'2025-05-21',': Towards Fees Payment:    Receipt no: 061',1,'no',2,'2025-05-21',NULL,2),(172,48,29,6000.00000,6000.00000,1,'2025-05-21','Towards Fees Payment:    Receipt no: 061',1,'no',2,'2025-05-21',NULL,2),(173,1,6,6500.00000,6500.00000,4,'2025-05-21',': Towards Fees Payment:    Receipt no: 062',1,'yes',2,'2025-05-21','2025-05-22',2),(174,48,29,6500.00000,6500.00000,1,'2025-05-21','Towards Fees Payment:    Receipt no: 062',1,'yes',2,'2025-05-21','2025-05-22',2),(175,1,6,6500.00000,6500.00000,4,'2025-05-21',': Towards Fees Payment:    Receipt no: 063',1,'no',2,'2025-05-21',NULL,2),(176,48,29,6500.00000,6500.00000,1,'2025-05-21','Towards Fees Payment:    Receipt no: 063',1,'no',2,'2025-05-21',NULL,2),(177,1,6,6500.00000,6500.00000,4,'2025-05-21',': Towards Fees Payment:    Receipt no: 064',1,'no',2,'2025-05-21',NULL,2),(178,48,29,6500.00000,6500.00000,1,'2025-05-21','Towards Fees Payment:    Receipt no: 064',1,'no',2,'2025-05-21',NULL,2),(179,6,48,17000.00000,17000.00000,4,'2025-05-21','Towards Fees Stamp',1,'no',2,'2025-05-21',NULL,2),(180,48,6,6000.00000,6000.00000,4,'2025-05-21','Towards Reversal of Fees Stamp',1,'no',2,'2025-05-21',NULL,2),(181,48,6,5250.00000,5250.00000,4,'2025-05-21','Towards Reversal of Fees Stamp',1,'no',2,'2025-05-21',NULL,2),(182,48,6,5250.00000,5250.00000,4,'2025-05-21','Towards Reversal of Fees Stamp',1,'no',2,'2025-05-21',NULL,2),(183,1,6,6500.00000,6500.00000,4,'2025-05-21',': Towards Fees Payment:    Receipt no: 065',1,'no',2,'2025-05-21',NULL,2),(184,48,29,6500.00000,6500.00000,1,'2025-05-21','Towards Fees Payment:    Receipt no: 065',1,'no',2,'2025-05-21',NULL,2),(185,1,6,7000.00000,7000.00000,4,'2025-05-21',': Towards Fees Payment:    Receipt no: 066',1,'yes',2,'2025-05-21','2025-05-21',2),(186,48,29,7000.00000,7000.00000,1,'2025-05-21','Towards Fees Payment:    Receipt no: 066',1,'yes',2,'2025-05-21','2025-05-21',2),(187,1,6,7000.00000,7000.00000,4,'2025-05-21',': Towards Fees Payment:    Receipt no: 067',1,'no',2,'2025-05-21',NULL,2),(188,48,29,7000.00000,7000.00000,1,'2025-05-21','Towards Fees Payment:    Receipt no: 067',1,'no',2,'2025-05-21',NULL,2),(189,1,6,1000.00000,1000.00000,4,'2025-05-21',': Towards Fees Payment:    Receipt no: 068',1,'no',2,'2025-05-21',NULL,2),(190,48,29,1000.00000,1000.00000,1,'2025-05-21','Towards Fees Payment:    Receipt no: 068',1,'no',2,'2025-05-21',NULL,2),(191,1,6,7000.00000,7000.00000,4,'2025-05-21',': Towards Fees Payment:    Receipt no: 069',1,'yes',2,'2025-05-21','2025-06-03',2),(192,48,29,7000.00000,7000.00000,1,'2025-05-21','Towards Fees Payment:    Receipt no: 069',1,'yes',2,'2025-05-21','2025-06-03',2),(193,1,6,6000.00000,6000.00000,4,'2025-05-21',': Towards Fees Payment:    Receipt no: 070',1,'no',2,'2025-05-21',NULL,2),(194,48,29,6000.00000,6000.00000,1,'2025-05-21','Towards Fees Payment:    Receipt no: 070',1,'no',2,'2025-05-21',NULL,2),(195,1,6,5000.00000,5000.00000,4,'2025-05-21',': Towards Fees Payment:    Receipt no: 071',1,'no',2,'2025-05-21',NULL,2),(196,48,29,5000.00000,5000.00000,1,'2025-05-21','Towards Fees Payment:    Receipt no: 071',1,'no',2,'2025-05-21',NULL,2),(197,1,6,3000.00000,3000.00000,4,'2025-05-21',': Towards Fees Payment:    Receipt no: 072',1,'no',2,'2025-05-21',NULL,2),(198,48,29,3000.00000,3000.00000,1,'2025-05-21','Towards Fees Payment:    Receipt no: 072',1,'no',2,'2025-05-21',NULL,2),(199,1,6,7000.00000,7000.00000,4,'2025-05-21',': Towards Fees Payment:    Receipt no: 073',1,'no',2,'2025-05-21',NULL,2),(200,48,29,7000.00000,7000.00000,1,'2025-05-21','Towards Fees Payment:    Receipt no: 073',1,'no',2,'2025-05-21',NULL,2),(201,1,6,3000.00000,3000.00000,4,'2025-05-21',': Towards Fees Payment:    Receipt no: 074',1,'no',2,'2025-05-21',NULL,2),(202,48,29,3000.00000,3000.00000,1,'2025-05-21','Towards Fees Payment:    Receipt no: 074',1,'no',2,'2025-05-21',NULL,2),(203,6,48,15000.00000,15000.00000,4,'2025-05-21','Towards Fees Stamp',1,'no',2,'2025-05-21',NULL,2),(204,6,48,16500.00000,16500.00000,4,'2025-05-21','Towards Fees Stamp',1,'no',2,'2025-05-21',NULL,2),(205,6,48,15000.00000,15000.00000,4,'2025-05-21','Towards Fees Stamp',1,'no',2,'2025-05-21',NULL,2),(206,1,6,3500.00000,3500.00000,4,'2025-05-21',': Towards Fees Payment:    Receipt no: 075',1,'no',2,'2025-05-21',NULL,2),(207,48,29,3500.00000,3500.00000,1,'2025-05-21','Towards Fees Payment:    Receipt no: 075',1,'no',2,'2025-05-21',NULL,2),(208,1,6,6000.00000,6000.00000,4,'2025-05-21',': Towards Fees Payment:    Receipt no: 076',1,'no',2,'2025-05-21',NULL,2),(209,48,29,6000.00000,6000.00000,1,'2025-05-21','Towards Fees Payment:    Receipt no: 076',1,'no',2,'2025-05-21',NULL,2),(210,6,48,15000.00000,15000.00000,4,'2025-05-21','Towards Fees Stamp',1,'no',2,'2025-05-21',NULL,2),(211,1,6,5500.00000,5500.00000,4,'2025-05-21',': Towards Fees Payment:    Receipt no: 077',1,'no',2,'2025-05-21',NULL,2),(212,48,29,5500.00000,5500.00000,1,'2025-05-21','Towards Fees Payment:    Receipt no: 077',1,'no',2,'2025-05-21',NULL,2),(213,1,6,5500.00000,5500.00000,4,'2025-05-21',': Towards Fees Payment:    Receipt no: 078',1,'no',2,'2025-05-21',NULL,2),(214,48,29,5500.00000,5500.00000,1,'2025-05-21','Towards Fees Payment:    Receipt no: 078',1,'no',2,'2025-05-21',NULL,2),(215,1,6,6000.00000,6000.00000,4,'2025-05-21',': Towards Fees Payment:    Receipt no: 079',1,'yes',2,'2025-05-21','2025-05-21',2),(216,48,29,6000.00000,6000.00000,1,'2025-05-21','Towards Fees Payment:    Receipt no: 079',1,'yes',2,'2025-05-21','2025-05-21',2),(217,1,6,6000.00000,6000.00000,4,'2025-05-21',': Towards Fees Payment:    Receipt no: 080',1,'no',2,'2025-05-21',NULL,2),(218,48,29,6000.00000,6000.00000,1,'2025-05-21','Towards Fees Payment:    Receipt no: 080',1,'no',2,'2025-05-21',NULL,2),(219,1,6,2000.00000,2000.00000,4,'2025-05-21',': Towards Fees Payment:    Receipt no: 081',1,'no',2,'2025-05-21',NULL,2),(220,48,29,2000.00000,2000.00000,1,'2025-05-21','Towards Fees Payment:    Receipt no: 081',1,'no',2,'2025-05-21',NULL,2),(221,1,6,5500.00000,5500.00000,4,'2025-05-22',': Towards Fees Payment:    Receipt no: 082',1,'no',2,'2025-05-22',NULL,2),(222,48,29,5500.00000,5500.00000,1,'2025-05-22','Towards Fees Payment:    Receipt no: 082',1,'no',2,'2025-05-22',NULL,2),(223,1,6,6500.00000,6500.00000,4,'2025-05-22',': Towards Fees Payment:    Receipt no: 083',1,'no',2,'2025-05-22',NULL,2),(224,48,29,6500.00000,6500.00000,1,'2025-05-22','Towards Fees Payment:    Receipt no: 083',1,'no',2,'2025-05-22',NULL,2),(225,1,6,7000.00000,7000.00000,4,'2025-05-22',': Towards Fees Payment:    Receipt no: 084',1,'no',2,'2025-05-22',NULL,2),(226,48,29,7000.00000,7000.00000,1,'2025-05-22','Towards Fees Payment:    Receipt no: 084',1,'no',2,'2025-05-22',NULL,2),(227,6,48,16500.00000,16500.00000,4,'2025-05-22','Towards Fees Stamp',1,'no',2,'2025-05-22',NULL,2),(228,1,6,3000.00000,3000.00000,4,'2025-05-22',': Towards Fees Payment:    Receipt no: 085',1,'no',2,'2025-05-22',NULL,2),(229,48,29,3000.00000,3000.00000,1,'2025-05-22','Towards Fees Payment:    Receipt no: 085',1,'no',2,'2025-05-22',NULL,2),(230,1,6,17000.00000,17000.00000,4,'2025-05-23',': Towards Fees Payment:    Receipt no: 086',1,'no',2,'2025-05-23',NULL,2),(231,48,29,17000.00000,17000.00000,1,'2025-05-23','Towards Fees Payment:    Receipt no: 086',1,'no',2,'2025-05-23',NULL,2),(232,1,6,7000.00000,7000.00000,4,'2025-05-23',': Towards Fees Payment:    Receipt no: 087',1,'no',2,'2025-05-23',NULL,2),(233,48,29,7000.00000,7000.00000,1,'2025-05-23','Towards Fees Payment:    Receipt no: 087',1,'no',2,'2025-05-23',NULL,2),(234,1,6,6000.00000,6000.00000,4,'2025-05-27',': Towards Fees Payment:    Receipt no: 088',1,'no',2,'2025-05-27',NULL,2),(235,48,29,6000.00000,6000.00000,1,'2025-05-27','Towards Fees Payment:    Receipt no: 088',1,'no',2,'2025-05-27',NULL,2),(236,1,6,5000.00000,5000.00000,4,'2025-05-27',': Towards Fees Payment:    Receipt no: 089',1,'no',2,'2025-05-27',NULL,2),(237,48,29,5000.00000,5000.00000,1,'2025-05-27','Towards Fees Payment:    Receipt no: 089',1,'no',2,'2025-05-27',NULL,2),(238,1,6,6000.00000,6000.00000,4,'2025-05-27',': Towards Fees Payment:    Receipt no: 090',1,'no',2,'2025-05-27',NULL,2),(239,48,29,6000.00000,6000.00000,1,'2025-05-27','Towards Fees Payment:    Receipt no: 090',1,'no',2,'2025-05-27',NULL,2),(240,1,6,6000.00000,6000.00000,4,'2025-05-27',': Towards Fees Payment:    Receipt no: 091',1,'no',2,'2025-05-27',NULL,2),(241,48,29,6000.00000,6000.00000,1,'2025-05-27','Towards Fees Payment:    Receipt no: 091',1,'no',2,'2025-05-27',NULL,2),(242,1,6,6500.00000,6500.00000,4,'2025-05-27',': Towards Fees Payment:    Receipt no: 092',1,'no',2,'2025-05-27',NULL,2),(243,48,29,6500.00000,6500.00000,1,'2025-05-27','Towards Fees Payment:    Receipt no: 092',1,'no',2,'2025-05-27',NULL,2),(244,1,6,3500.00000,3500.00000,4,'2025-05-27',': Towards Fees Payment:    Receipt no: 093',1,'no',2,'2025-05-27',NULL,2),(245,48,29,3500.00000,3500.00000,1,'2025-05-27','Towards Fees Payment:    Receipt no: 093',1,'no',2,'2025-05-27',NULL,2),(246,1,6,5500.00000,5500.00000,4,'2025-05-27',': Towards Fees Payment:    Receipt no: 094',1,'no',2,'2025-05-27',NULL,2),(247,48,29,5500.00000,5500.00000,1,'2025-05-27','Towards Fees Payment:    Receipt no: 094',1,'no',2,'2025-05-27',NULL,2),(248,1,6,5000.00000,5000.00000,4,'2025-05-27',': Towards Fees Payment:    Receipt no: 095',1,'no',2,'2025-05-27',NULL,2),(249,48,29,5000.00000,5000.00000,1,'2025-05-27','Towards Fees Payment:    Receipt no: 095',1,'no',2,'2025-05-27',NULL,2),(250,1,6,6500.00000,6500.00000,4,'2025-05-27',': Towards Fees Payment:    Receipt no: 096',1,'no',2,'2025-05-27',NULL,2),(251,48,29,6500.00000,6500.00000,1,'2025-05-27','Towards Fees Payment:    Receipt no: 096',1,'no',2,'2025-05-27',NULL,2),(252,1,6,5000.00000,5000.00000,4,'2025-05-27',': Towards Fees Payment:    Receipt no: 097',1,'no',2,'2025-05-27',NULL,2),(253,48,29,5000.00000,5000.00000,1,'2025-05-27','Towards Fees Payment:    Receipt no: 097',1,'no',2,'2025-05-27',NULL,2),(254,1,6,1000.00000,1000.00000,4,'2025-05-27',': Towards Fees Payment:    Receipt no: 098',1,'no',2,'2025-05-27',NULL,2),(255,48,29,1000.00000,1000.00000,1,'2025-05-27','Towards Fees Payment:    Receipt no: 098',1,'no',2,'2025-05-27',NULL,2),(256,1,6,5000.00000,5000.00000,4,'2025-05-27',': Towards Fees Payment:    Receipt no: 099',1,'no',2,'2025-05-27',NULL,2),(257,48,29,5000.00000,5000.00000,1,'2025-05-27','Towards Fees Payment:    Receipt no: 099',1,'no',2,'2025-05-27',NULL,2),(258,1,6,8000.00000,8000.00000,4,'2025-05-27',': Towards Fees Payment:    Receipt no: 100',1,'no',2,'2025-05-27',NULL,2),(259,48,29,8000.00000,8000.00000,1,'2025-05-27','Towards Fees Payment:    Receipt no: 100',1,'no',2,'2025-05-27',NULL,2),(260,1,6,9000.00000,9000.00000,4,'2025-05-27',': Towards Fees Payment:    Receipt no: 101',1,'no',2,'2025-05-27',NULL,2),(261,48,29,9000.00000,9000.00000,1,'2025-05-27','Towards Fees Payment:    Receipt no: 101',1,'no',2,'2025-05-27',NULL,2),(262,1,6,2000.00000,2000.00000,4,'2025-05-27',': Towards Fees Payment:    Receipt no: 102',1,'no',2,'2025-05-27',NULL,2),(263,48,29,2000.00000,2000.00000,1,'2025-05-27','Towards Fees Payment:    Receipt no: 102',1,'no',2,'2025-05-27',NULL,2),(264,1,6,5000.00000,5000.00000,4,'2025-05-27',': Towards Fees Payment:    Receipt no: 103',1,'no',2,'2025-05-27',NULL,2),(265,48,29,5000.00000,5000.00000,1,'2025-05-27','Towards Fees Payment:    Receipt no: 103',1,'no',2,'2025-05-27',NULL,2),(266,1,6,5000.00000,5000.00000,4,'2025-05-27',': Towards Fees Payment:    Receipt no: 104',1,'no',2,'2025-05-27',NULL,2),(267,48,29,5000.00000,5000.00000,1,'2025-05-27','Towards Fees Payment:    Receipt no: 104',1,'no',2,'2025-05-27',NULL,2),(268,6,48,15000.00000,15000.00000,4,'2025-05-27','Towards Fees Stamp',1,'no',2,'2025-05-27',NULL,2),(269,6,48,15000.00000,15000.00000,4,'2025-05-27','Towards Fees Stamp',1,'no',2,'2025-05-27',NULL,2),(270,6,48,15000.00000,15000.00000,4,'2025-05-27','Towards Fees Stamp',1,'no',2,'2025-05-27',NULL,2),(271,1,6,5000.00000,5000.00000,4,'2025-05-27',': Towards Fees Payment:    Receipt no: 105',1,'no',2,'2025-05-27',NULL,2),(272,48,29,5000.00000,5000.00000,1,'2025-05-27','Towards Fees Payment:    Receipt no: 105',1,'no',2,'2025-05-27',NULL,2),(273,1,6,5500.00000,5500.00000,4,'2025-05-27',': Towards Fees Payment:    Receipt no: 106',1,'no',2,'2025-05-27',NULL,2),(274,48,29,5500.00000,5500.00000,1,'2025-05-27','Towards Fees Payment:    Receipt no: 106',1,'no',2,'2025-05-27',NULL,2),(275,1,6,5000.00000,5000.00000,4,'2025-05-27',': Towards Fees Payment:    Receipt no: 107',1,'no',2,'2025-05-27',NULL,2),(276,48,29,5000.00000,5000.00000,1,'2025-05-27','Towards Fees Payment:    Receipt no: 107',1,'no',2,'2025-05-27',NULL,2),(277,1,6,5000.00000,5000.00000,4,'2025-05-27',': Towards Fees Payment:    Receipt no: 108',1,'no',2,'2025-05-27',NULL,2),(278,48,29,5000.00000,5000.00000,1,'2025-05-27','Towards Fees Payment:    Receipt no: 108',1,'no',2,'2025-05-27',NULL,2),(279,1,6,6000.00000,6000.00000,4,'2025-05-27',': Towards Fees Payment:    Receipt no: 109',1,'no',2,'2025-05-27',NULL,2),(280,48,29,6000.00000,6000.00000,1,'2025-05-27','Towards Fees Payment:    Receipt no: 109',1,'no',2,'2025-05-27',NULL,2),(281,1,6,1900.00000,1900.00000,4,'2025-05-27',': Towards Fees Payment:    Receipt no: 110',1,'no',2,'2025-05-27',NULL,2),(282,48,29,1900.00000,1900.00000,1,'2025-05-27','Towards Fees Payment:    Receipt no: 110',1,'no',2,'2025-05-27',NULL,2),(283,1,6,1900.00000,1900.00000,4,'2025-05-27',': Towards Fees Payment:    Receipt no: 111',1,'no',2,'2025-05-27',NULL,2),(284,48,29,1900.00000,1900.00000,1,'2025-05-27','Towards Fees Payment:    Receipt no: 111',1,'no',2,'2025-05-27',NULL,2),(285,6,48,15000.00000,15000.00000,4,'2025-05-27','Towards Fees Stamp',1,'no',2,'2025-05-27',NULL,2),(286,6,48,15000.00000,15000.00000,4,'2025-05-27','Towards Fees Stamp',1,'no',2,'2025-05-27',NULL,2),(287,1,6,5500.00000,5500.00000,4,'2025-05-27',': Towards Fees Payment:    Receipt no: 112',1,'no',2,'2025-05-27',NULL,2),(288,48,29,5500.00000,5500.00000,1,'2025-05-27','Towards Fees Payment:    Receipt no: 112',1,'no',2,'2025-05-27',NULL,2),(289,1,6,3000.00000,3000.00000,4,'2025-05-27',': Towards Fees Payment:    Receipt no: 113',1,'no',2,'2025-05-27',NULL,2),(290,48,29,3000.00000,3000.00000,1,'2025-05-27','Towards Fees Payment:    Receipt no: 113',1,'no',2,'2025-05-27',NULL,2),(291,1,6,7000.00000,7000.00000,4,'2025-05-27',': Towards Fees Payment:    Receipt no: 114',1,'no',2,'2025-05-27',NULL,2),(292,48,29,7000.00000,7000.00000,1,'2025-05-27','Towards Fees Payment:    Receipt no: 114',1,'no',2,'2025-05-27',NULL,2),(293,1,6,7000.00000,7000.00000,4,'2025-05-27',': Towards Fees Payment:    Receipt no: 115',1,'no',2,'2025-05-27',NULL,2),(294,48,29,7000.00000,7000.00000,1,'2025-05-27','Towards Fees Payment:    Receipt no: 115',1,'no',2,'2025-05-27',NULL,2),(295,1,6,2000.00000,2000.00000,4,'2025-05-27',': Towards Fees Payment:    Receipt no: 116',1,'no',2,'2025-05-27',NULL,2),(296,48,29,2000.00000,2000.00000,1,'2025-05-27','Towards Fees Payment:    Receipt no: 116',1,'no',2,'2025-05-27',NULL,2),(297,1,6,3000.00000,3000.00000,4,'2025-05-27',': Towards Fees Payment:    Receipt no: 117',1,'no',2,'2025-05-27',NULL,2),(298,48,29,3000.00000,3000.00000,1,'2025-05-27','Towards Fees Payment:    Receipt no: 117',1,'no',2,'2025-05-27',NULL,2),(299,6,48,15000.00000,15000.00000,4,'2025-05-28','Towards Fees Stamp',1,'no',2,'2025-05-28',NULL,2),(300,6,48,17500.00000,17500.00000,4,'2025-05-28','Towards Fees Stamp',1,'no',2,'2025-05-28',NULL,2),(301,6,48,15000.00000,15000.00000,4,'2025-05-28','Towards Fees Stamp',1,'no',2,'2025-05-28',NULL,2),(302,6,48,15000.00000,15000.00000,4,'2025-05-28','Towards Fees Stamp',1,'no',2,'2025-05-28',NULL,2),(303,1,6,3000.00000,3000.00000,4,'2025-05-28',': Towards Fees Payment:    Receipt no: 118',1,'no',2,'2025-05-28',NULL,2),(304,48,29,3000.00000,3000.00000,1,'2025-05-28','Towards Fees Payment:    Receipt no: 118',1,'no',2,'2025-05-28',NULL,2),(305,1,6,5000.00000,5000.00000,4,'2025-05-28',': Towards Fees Payment:    Receipt no: 119',1,'no',2,'2025-05-28',NULL,2),(306,48,29,5000.00000,5000.00000,1,'2025-05-28','Towards Fees Payment:    Receipt no: 119',1,'no',2,'2025-05-28',NULL,2),(307,6,48,15000.00000,15000.00000,4,'2025-05-28','Towards Fees Stamp',1,'no',2,'2025-05-28',NULL,2),(308,1,6,5000.00000,5000.00000,4,'2025-05-28',': Towards Fees Payment:    Receipt no: 120',1,'no',2,'2025-05-28',NULL,2),(309,48,29,5000.00000,5000.00000,1,'2025-05-28','Towards Fees Payment:    Receipt no: 120',1,'no',2,'2025-05-28',NULL,2),(310,1,6,5500.00000,5500.00000,4,'2025-05-28',': Towards Fees Payment:    Receipt no: 121',1,'no',2,'2025-05-28',NULL,2),(311,48,29,5500.00000,5500.00000,1,'2025-05-28','Towards Fees Payment:    Receipt no: 121',1,'no',2,'2025-05-28',NULL,2),(312,1,6,7000.00000,7000.00000,4,'2025-05-28',': Towards Fees Payment:    Receipt no: 122',1,'no',2,'2025-05-28',NULL,2),(313,48,29,7000.00000,7000.00000,1,'2025-05-28','Towards Fees Payment:    Receipt no: 122',1,'no',2,'2025-05-28',NULL,2),(314,1,6,7000.00000,7000.00000,4,'2025-05-28',': Towards Fees Payment:    Receipt no: 123',1,'no',2,'2025-05-28',NULL,2),(315,48,29,7000.00000,7000.00000,1,'2025-05-28','Towards Fees Payment:    Receipt no: 123',1,'no',2,'2025-05-28',NULL,2),(316,1,6,3000.00000,3000.00000,4,'2025-05-28',': Towards Fees Payment:    Receipt no: 124',1,'no',2,'2025-05-28',NULL,2),(317,48,29,3000.00000,3000.00000,1,'2025-05-28','Towards Fees Payment:    Receipt no: 124',1,'no',2,'2025-05-28',NULL,2),(318,1,6,7000.00000,7000.00000,4,'2025-05-28',': Towards Fees Payment:    Receipt no: 125',1,'no',2,'2025-05-28',NULL,2),(319,48,29,7000.00000,7000.00000,1,'2025-05-28','Towards Fees Payment:    Receipt no: 125',1,'no',2,'2025-05-28',NULL,2),(320,1,6,1550.00000,1550.00000,4,'2025-05-28',': Towards Fees Payment:    Receipt no: 126',1,'no',2,'2025-05-28',NULL,2),(321,48,29,1550.00000,1550.00000,1,'2025-05-28','Towards Fees Payment:    Receipt no: 126',1,'no',2,'2025-05-28',NULL,2),(322,1,6,7000.00000,7000.00000,4,'2025-05-28',': Towards Fees Payment:    Receipt no: 127',1,'no',2,'2025-05-28',NULL,2),(323,48,29,7000.00000,7000.00000,1,'2025-05-28','Towards Fees Payment:    Receipt no: 127',1,'no',2,'2025-05-28',NULL,2),(324,1,6,7000.00000,7000.00000,4,'2025-05-28',': Towards Fees Payment:    Receipt no: 128',1,'no',2,'2025-05-28',NULL,2),(325,48,29,7000.00000,7000.00000,1,'2025-05-28','Towards Fees Payment:    Receipt no: 128',1,'no',2,'2025-05-28',NULL,2),(326,1,6,4000.00000,4000.00000,4,'2025-05-28',': Towards Fees Payment:    Receipt no: 129',1,'no',2,'2025-05-28',NULL,2),(327,48,29,4000.00000,4000.00000,1,'2025-05-28','Towards Fees Payment:    Receipt no: 129',1,'no',2,'2025-05-28',NULL,2),(328,1,6,1000.00000,1000.00000,4,'2025-05-28',': Towards Fees Payment:    Receipt no: 130',1,'no',2,'2025-05-28',NULL,2),(329,48,29,1000.00000,1000.00000,1,'2025-05-28','Towards Fees Payment:    Receipt no: 130',1,'no',2,'2025-05-28',NULL,2),(330,1,6,7000.00000,7000.00000,4,'2025-05-28',': Towards Fees Payment:    Receipt no: 131',1,'no',2,'2025-05-28',NULL,2),(331,48,29,7000.00000,7000.00000,1,'2025-05-28','Towards Fees Payment:    Receipt no: 131',1,'no',2,'2025-05-28',NULL,2),(332,1,6,1500.00000,1500.00000,4,'2025-05-28',': Towards Fees Payment:    Receipt no: 132',1,'no',2,'2025-05-28',NULL,2),(333,48,29,1500.00000,1500.00000,1,'2025-05-28','Towards Fees Payment:    Receipt no: 132',1,'no',2,'2025-05-28',NULL,2),(334,1,6,6500.00000,6500.00000,4,'2025-05-28',': Towards Fees Payment:    Receipt no: 133',1,'no',2,'2025-05-28',NULL,2),(335,48,29,6500.00000,6500.00000,1,'2025-05-28','Towards Fees Payment:    Receipt no: 133',1,'no',2,'2025-05-28',NULL,2),(336,6,48,15000.00000,15000.00000,4,'2025-06-03','Towards Fees Stamp',1,'no',2,'2025-06-03',NULL,2),(337,6,48,15000.00000,15000.00000,4,'2025-06-03','Towards Fees Stamp',1,'no',2,'2025-06-03',NULL,2),(338,6,48,15000.00000,15000.00000,4,'2025-06-03','Towards Fees Stamp',1,'no',2,'2025-06-03',NULL,2),(339,6,48,15000.00000,15000.00000,4,'2025-06-03','Towards Fees Stamp',1,'no',2,'2025-06-03',NULL,2),(340,6,48,15000.00000,15000.00000,4,'2025-06-03','Towards Fees Stamp',1,'no',2,'2025-06-03',NULL,2),(341,6,48,16500.00000,16500.00000,4,'2025-06-03','Towards Fees Stamp',1,'no',2,'2025-06-03',NULL,2),(342,6,48,18500.00000,18500.00000,4,'2025-06-03','Towards Fees Stamp',1,'no',2,'2025-06-03',NULL,2),(343,6,48,15000.00000,15000.00000,4,'2025-06-03','Towards Fees Stamp',1,'no',2,'2025-06-03',NULL,2),(344,6,48,15000.00000,15000.00000,4,'2025-06-03','Towards Fees Stamp',1,'no',2,'2025-06-03',NULL,2),(345,6,48,10500.00000,10500.00000,4,'2025-06-03','Towards Fees Stamp',1,'no',2,'2025-06-03',NULL,2),(346,6,48,15000.00000,15000.00000,4,'2025-06-03','Towards Fees Stamp',1,'no',2,'2025-06-03',NULL,2),(347,1,6,2000.00000,2000.00000,4,'2025-06-03',': Towards Fees Payment:    Receipt no: 134',1,'no',2,'2025-06-03',NULL,2),(348,48,29,2000.00000,2000.00000,1,'2025-06-03','Towards Fees Payment:    Receipt no: 134',1,'no',2,'2025-06-03',NULL,2),(349,6,48,15000.00000,15000.00000,4,'2025-06-03','Towards Fees Stamp',1,'no',2,'2025-06-03',NULL,2),(350,1,6,5500.00000,5500.00000,4,'2025-06-03',': Towards Fees Payment:    Receipt no: 135',1,'no',2,'2025-06-03',NULL,2),(351,48,29,5500.00000,5500.00000,1,'2025-06-03','Towards Fees Payment:    Receipt no: 135',1,'no',2,'2025-06-03',NULL,2),(352,1,6,5500.00000,5500.00000,4,'2025-06-03',': Towards Fees Payment:    Receipt no: 136',1,'no',2,'2025-06-03',NULL,2),(353,48,29,5500.00000,5500.00000,1,'2025-06-03','Towards Fees Payment:    Receipt no: 136',1,'no',2,'2025-06-03',NULL,2),(354,6,48,15000.00000,15000.00000,4,'2025-06-03','Towards Fees Stamp',1,'no',2,'2025-06-03',NULL,2),(355,1,6,2500.00000,2500.00000,4,'2025-06-03',': Towards Fees Payment:    Receipt no: 137',1,'no',2,'2025-06-03',NULL,2),(356,48,29,2500.00000,2500.00000,1,'2025-06-03','Towards Fees Payment:    Receipt no: 137',1,'no',2,'2025-06-03',NULL,2),(357,1,6,5500.00000,5500.00000,4,'2025-06-03',': Towards Fees Payment:    Receipt no: 138',1,'no',2,'2025-06-03',NULL,2),(358,48,29,5500.00000,5500.00000,1,'2025-06-03','Towards Fees Payment:    Receipt no: 138',1,'no',2,'2025-06-03',NULL,2),(359,6,48,15000.00000,15000.00000,4,'2025-06-03','Towards Fees Stamp',1,'no',2,'2025-06-03',NULL,2),(360,1,6,5500.00000,5500.00000,4,'2025-06-03',': Towards Fees Payment:    Receipt no: 139',1,'no',2,'2025-06-03',NULL,2),(361,48,29,5500.00000,5500.00000,1,'2025-06-03','Towards Fees Payment:    Receipt no: 139',1,'no',2,'2025-06-03',NULL,2),(362,1,6,5500.00000,5500.00000,4,'2025-06-03',': Towards Fees Payment:    Receipt no: 140',1,'no',2,'2025-06-03',NULL,2),(363,48,29,5500.00000,5500.00000,1,'2025-06-03','Towards Fees Payment:    Receipt no: 140',1,'no',2,'2025-06-03',NULL,2),(364,6,48,15000.00000,15000.00000,4,'2025-06-03','Towards Fees Stamp',1,'no',2,'2025-06-03',NULL,2),(365,1,6,3000.00000,3000.00000,4,'2025-06-03',': Towards Fees Payment:    Receipt no: 141',1,'no',2,'2025-06-03',NULL,2),(366,48,29,3000.00000,3000.00000,1,'2025-06-03','Towards Fees Payment:    Receipt no: 141',1,'no',2,'2025-06-03',NULL,2),(367,1,6,5500.00000,5500.00000,4,'2025-06-03',': Towards Fees Payment:    Receipt no: 142',1,'no',2,'2025-06-03',NULL,2),(368,48,29,5500.00000,5500.00000,1,'2025-06-03','Towards Fees Payment:    Receipt no: 142',1,'no',2,'2025-06-03',NULL,2),(369,6,48,15000.00000,15000.00000,4,'2025-06-03','Towards Fees Stamp',1,'no',2,'2025-06-03',NULL,2),(370,1,6,3500.00000,3500.00000,4,'2025-06-03',': Towards Fees Payment:    Receipt no: 143',1,'no',2,'2025-06-03',NULL,2),(371,48,29,3500.00000,3500.00000,1,'2025-06-03','Towards Fees Payment:    Receipt no: 143',1,'no',2,'2025-06-03',NULL,2),(372,6,48,15000.00000,15000.00000,4,'2025-06-03','Towards Fees Stamp',1,'no',2,'2025-06-03',NULL,2),(373,1,6,2500.00000,2500.00000,4,'2025-06-03',': Towards Fees Payment:    Receipt no: 144',1,'no',2,'2025-06-03',NULL,2),(374,48,29,2500.00000,2500.00000,1,'2025-06-03','Towards Fees Payment:    Receipt no: 144',1,'no',2,'2025-06-03',NULL,2),(375,1,6,5500.00000,5500.00000,4,'2025-06-03',': Towards Fees Payment:    Receipt no: 145',1,'no',2,'2025-06-03',NULL,2),(376,48,29,5500.00000,5500.00000,1,'2025-06-03','Towards Fees Payment:    Receipt no: 145',1,'no',2,'2025-06-03',NULL,2),(377,6,48,15000.00000,15000.00000,4,'2025-06-03','Towards Fees Stamp',1,'no',2,'2025-06-03',NULL,2),(378,1,6,1000.00000,1000.00000,4,'2025-06-03',': Towards Fees Payment:    Receipt no: 146',1,'no',2,'2025-06-03',NULL,2),(379,48,29,1000.00000,1000.00000,1,'2025-06-03','Towards Fees Payment:    Receipt no: 146',1,'no',2,'2025-06-03',NULL,2),(380,1,6,3000.00000,3000.00000,4,'2025-06-03',': Towards Fees Payment:    Receipt no: 147',1,'no',2,'2025-06-03',NULL,2),(381,48,29,3000.00000,3000.00000,1,'2025-06-03','Towards Fees Payment:    Receipt no: 147',1,'no',2,'2025-06-03',NULL,2),(382,6,48,15000.00000,15000.00000,4,'2025-06-03','Towards Fees Stamp',1,'no',2,'2025-06-03',NULL,2),(383,48,6,5250.00000,5250.00000,4,'2025-06-03','Towards Reversal of Fees Stamp',1,'no',2,'2025-06-03',NULL,2),(384,48,6,5250.00000,5250.00000,4,'2025-06-03','Towards Reversal of Fees Stamp',1,'no',2,'2025-06-03',NULL,2),(385,48,6,6000.00000,6000.00000,4,'2025-06-03','Towards Reversal of Fees Stamp',1,'no',2,'2025-06-03',NULL,2),(386,1,6,5000.00000,5000.00000,4,'2025-06-03',': Towards Fees Payment:    Receipt no: 148',1,'no',2,'2025-06-03',NULL,2),(387,48,29,5000.00000,5000.00000,1,'2025-06-03','Towards Fees Payment:    Receipt no: 148',1,'no',2,'2025-06-03',NULL,2),(388,1,6,5500.00000,5500.00000,4,'2025-06-03',': Towards Fees Payment:    Receipt no: 149',1,'no',2,'2025-06-03',NULL,2),(389,48,29,5500.00000,5500.00000,1,'2025-06-03','Towards Fees Payment:    Receipt no: 149',1,'no',2,'2025-06-03',NULL,2),(390,1,6,16500.00000,16500.00000,4,'2025-06-03',': Towards Fees Payment:    Receipt no: 150',1,'no',2,'2025-06-03',NULL,2),(391,48,29,16500.00000,16500.00000,1,'2025-06-03','Towards Fees Payment:    Receipt no: 150',1,'no',2,'2025-06-03',NULL,2),(392,1,6,6000.00000,6000.00000,4,'2025-06-03',': Towards Fees Payment:    Receipt no: 151',1,'no',2,'2025-06-03',NULL,2),(393,48,29,6000.00000,6000.00000,1,'2025-06-03','Towards Fees Payment:    Receipt no: 151',1,'no',2,'2025-06-03',NULL,2),(394,1,6,6000.00000,6000.00000,4,'2025-06-03',': Towards Fees Payment:    Receipt no: 152',1,'no',2,'2025-06-03',NULL,2),(395,48,29,6000.00000,6000.00000,1,'2025-06-03','Towards Fees Payment:    Receipt no: 152',1,'no',2,'2025-06-03',NULL,2),(396,1,6,6000.00000,6000.00000,4,'2025-06-03',': Towards Fees Payment:    Receipt no: 153',1,'no',2,'2025-06-03',NULL,2),(397,48,29,6000.00000,6000.00000,1,'2025-06-03','Towards Fees Payment:    Receipt no: 153',1,'no',2,'2025-06-03',NULL,2),(398,1,6,5000.00000,5000.00000,4,'2025-06-03',': Towards Fees Payment:    Receipt no: 154',1,'no',2,'2025-06-03',NULL,2),(399,48,29,5000.00000,5000.00000,1,'2025-06-03','Towards Fees Payment:    Receipt no: 154',1,'no',2,'2025-06-03',NULL,2),(400,1,6,6000.00000,6000.00000,4,'2025-06-03',': Towards Fees Payment:    Receipt no: 155',1,'no',2,'2025-06-03',NULL,2),(401,48,29,6000.00000,6000.00000,1,'2025-06-03','Towards Fees Payment:    Receipt no: 155',1,'no',2,'2025-06-03',NULL,2),(402,1,6,6000.00000,6000.00000,4,'2025-06-03',': Towards Fees Payment:    Receipt no: 156',1,'no',2,'2025-06-03',NULL,2),(403,48,29,6000.00000,6000.00000,1,'2025-06-03','Towards Fees Payment:    Receipt no: 156',1,'no',2,'2025-06-03',NULL,2),(404,1,6,6000.00000,6000.00000,4,'2025-06-03',': Towards Fees Payment:    Receipt no: 157',1,'no',2,'2025-06-03',NULL,2),(405,48,29,6000.00000,6000.00000,1,'2025-06-03','Towards Fees Payment:    Receipt no: 157',1,'no',2,'2025-06-03',NULL,2),(406,1,6,6000.00000,6000.00000,4,'2025-06-03',': Towards Fees Payment:    Receipt no: 158',1,'no',2,'2025-06-03',NULL,2),(407,48,29,6000.00000,6000.00000,1,'2025-06-03','Towards Fees Payment:    Receipt no: 158',1,'no',2,'2025-06-03',NULL,2),(408,1,6,5000.00000,5000.00000,4,'2025-06-03',': Towards Fees Payment:    Receipt no: 159',1,'no',2,'2025-06-03',NULL,2),(409,48,29,5000.00000,5000.00000,1,'2025-06-03','Towards Fees Payment:    Receipt no: 159',1,'no',2,'2025-06-03',NULL,2),(410,1,6,6000.00000,6000.00000,4,'2025-06-03',': Towards Fees Payment:    Receipt no: 160',1,'no',2,'2025-06-03',NULL,2),(411,48,29,6000.00000,6000.00000,1,'2025-06-03','Towards Fees Payment:    Receipt no: 160',1,'no',2,'2025-06-03',NULL,2),(412,1,6,6000.00000,6000.00000,4,'2025-06-03',': Towards Fees Payment:    Receipt no: 161',1,'no',2,'2025-06-03',NULL,2),(413,48,29,6000.00000,6000.00000,1,'2025-06-03','Towards Fees Payment:    Receipt no: 161',1,'no',2,'2025-06-03',NULL,2),(414,1,6,6000.00000,6000.00000,4,'2025-06-03',': Towards Fees Payment:    Receipt no: 162',1,'no',2,'2025-06-03',NULL,2),(415,48,29,6000.00000,6000.00000,1,'2025-06-03','Towards Fees Payment:    Receipt no: 162',1,'no',2,'2025-06-03',NULL,2),(416,1,6,6000.00000,6000.00000,4,'2025-06-03',': Towards Fees Payment:    Receipt no: 163',1,'no',2,'2025-06-03',NULL,2),(417,48,29,6000.00000,6000.00000,1,'2025-06-03','Towards Fees Payment:    Receipt no: 163',1,'no',2,'2025-06-03',NULL,2),(418,1,6,3000.00000,3000.00000,4,'2025-06-03',': Towards Fees Payment:    Receipt no: 164',1,'no',2,'2025-06-03',NULL,2),(419,48,29,3000.00000,3000.00000,1,'2025-06-03','Towards Fees Payment:    Receipt no: 164',1,'no',2,'2025-06-03',NULL,2),(420,1,6,6000.00000,6000.00000,4,'2025-06-03',': Towards Fees Payment:    Receipt no: 165',1,'no',2,'2025-06-03',NULL,2),(421,48,29,6000.00000,6000.00000,1,'2025-06-03','Towards Fees Payment:    Receipt no: 165',1,'no',2,'2025-06-03',NULL,2),(422,1,6,6000.00000,6000.00000,4,'2025-06-03',': Towards Fees Payment:    Receipt no: 166',1,'no',2,'2025-06-03',NULL,2),(423,48,29,6000.00000,6000.00000,1,'2025-06-03','Towards Fees Payment:    Receipt no: 166',1,'no',2,'2025-06-03',NULL,2),(424,1,6,6000.00000,6000.00000,4,'2025-06-03',': Towards Fees Payment:    Receipt no: 167',1,'no',2,'2025-06-03',NULL,2),(425,48,29,6000.00000,6000.00000,1,'2025-06-03','Towards Fees Payment:    Receipt no: 167',1,'no',2,'2025-06-03',NULL,2),(426,1,6,6000.00000,6000.00000,4,'2025-06-03',': Towards Fees Payment:    Receipt no: 168',1,'no',2,'2025-06-03',NULL,2),(427,48,29,6000.00000,6000.00000,1,'2025-06-03','Towards Fees Payment:    Receipt no: 168',1,'no',2,'2025-06-03',NULL,2),(428,1,6,4000.00000,4000.00000,4,'2025-06-03',': Towards Fees Payment:    Receipt no: 169',1,'no',2,'2025-06-03',NULL,2),(429,48,29,4000.00000,4000.00000,1,'2025-06-03','Towards Fees Payment:    Receipt no: 169',1,'no',2,'2025-06-03',NULL,2),(430,1,6,5000.00000,5000.00000,4,'2025-06-03',': Towards Fees Payment:    Receipt no: 170',1,'no',2,'2025-06-03',NULL,2),(431,48,29,5000.00000,5000.00000,1,'2025-06-03','Towards Fees Payment:    Receipt no: 170',1,'no',2,'2025-06-03',NULL,2),(432,1,6,2000.00000,2000.00000,4,'2025-06-03',': Towards Fees Payment:    Receipt no: 171',1,'no',2,'2025-06-03',NULL,2),(433,48,29,2000.00000,2000.00000,1,'2025-06-03','Towards Fees Payment:    Receipt no: 171',1,'no',2,'2025-06-03',NULL,2),(434,1,6,6000.00000,6000.00000,4,'2025-06-03',': Towards Fees Payment:    Receipt no: 172',1,'no',2,'2025-06-03',NULL,2),(435,48,29,6000.00000,6000.00000,1,'2025-06-03','Towards Fees Payment:    Receipt no: 172',1,'no',2,'2025-06-03',NULL,2),(436,1,6,6000.00000,6000.00000,4,'2025-06-03',': Towards Fees Payment:    Receipt no: 173',1,'no',2,'2025-06-03',NULL,2),(437,48,29,6000.00000,6000.00000,1,'2025-06-03','Towards Fees Payment:    Receipt no: 173',1,'no',2,'2025-06-03',NULL,2),(438,1,6,3000.00000,3000.00000,4,'2025-06-03',': Towards Fees Payment:    Receipt no: 174',1,'no',2,'2025-06-03',NULL,2),(439,48,29,3000.00000,3000.00000,1,'2025-06-03','Towards Fees Payment:    Receipt no: 174',1,'no',2,'2025-06-03',NULL,2),(440,1,6,6000.00000,6000.00000,4,'2025-06-03',': Towards Fees Payment:    Receipt no: 175',1,'no',2,'2025-06-03',NULL,2),(441,48,29,6000.00000,6000.00000,1,'2025-06-03','Towards Fees Payment:    Receipt no: 175',1,'no',2,'2025-06-03',NULL,2),(442,1,6,6000.00000,6000.00000,4,'2025-06-03',': Towards Fees Payment:    Receipt no: 176',1,'no',2,'2025-06-03',NULL,2),(443,48,29,6000.00000,6000.00000,1,'2025-06-03','Towards Fees Payment:    Receipt no: 176',1,'no',2,'2025-06-03',NULL,2),(444,1,6,6000.00000,6000.00000,4,'2025-06-03',': Towards Fees Payment:    Receipt no: 177',1,'no',2,'2025-06-03',NULL,2),(445,48,29,6000.00000,6000.00000,1,'2025-06-03','Towards Fees Payment:    Receipt no: 177',1,'no',2,'2025-06-03',NULL,2),(446,1,6,6000.00000,6000.00000,4,'2025-06-03',': Towards Fees Payment:    Receipt no: 178',1,'no',2,'2025-06-03',NULL,2),(447,48,29,6000.00000,6000.00000,1,'2025-06-03','Towards Fees Payment:    Receipt no: 178',1,'no',2,'2025-06-03',NULL,2),(448,1,6,6000.00000,6000.00000,4,'2025-06-03',': Towards Fees Payment:    Receipt no: 179',1,'no',2,'2025-06-03',NULL,2),(449,48,29,6000.00000,6000.00000,1,'2025-06-03','Towards Fees Payment:    Receipt no: 179',1,'no',2,'2025-06-03',NULL,2),(450,1,6,1000.00000,1000.00000,4,'2025-06-03',': Towards Fees Payment:    Receipt no: 180',1,'no',2,'2025-06-03',NULL,2),(451,48,29,1000.00000,1000.00000,1,'2025-06-03','Towards Fees Payment:    Receipt no: 180',1,'no',2,'2025-06-03',NULL,2),(452,1,6,6500.00000,6500.00000,4,'2025-06-03',': Towards Fees Payment:    Receipt no: 181',1,'no',2,'2025-06-03',NULL,2),(453,48,29,6500.00000,6500.00000,1,'2025-06-03','Towards Fees Payment:    Receipt no: 181',1,'no',2,'2025-06-03',NULL,2),(454,1,6,10000.00000,10000.00000,4,'2025-06-03',': Towards Fees Payment:    Receipt no: 182',1,'no',2,'2025-06-03',NULL,2),(455,48,29,10000.00000,10000.00000,1,'2025-06-03','Towards Fees Payment:    Receipt no: 182',1,'no',2,'2025-06-03',NULL,2),(456,1,6,5500.00000,5500.00000,4,'2025-06-03',': Towards Fees Payment:    Receipt no: 183',1,'no',2,'2025-06-03',NULL,2),(457,48,29,5500.00000,5500.00000,1,'2025-06-03','Towards Fees Payment:    Receipt no: 183',1,'no',2,'2025-06-03',NULL,2),(458,1,6,3000.00000,3000.00000,4,'2025-06-03',': Towards Fees Payment:    Receipt no: 184',1,'no',2,'2025-06-03',NULL,2),(459,48,29,3000.00000,3000.00000,1,'2025-06-03','Towards Fees Payment:    Receipt no: 184',1,'no',2,'2025-06-03',NULL,2),(460,1,6,1000.00000,1000.00000,4,'2025-06-03',': Towards Fees Payment:    Receipt no: 185',1,'no',2,'2025-06-03',NULL,2),(461,48,29,1000.00000,1000.00000,1,'2025-06-03','Towards Fees Payment:    Receipt no: 185',1,'no',2,'2025-06-03',NULL,2),(462,1,6,1500.00000,1500.00000,4,'2025-06-03',': Towards Fees Payment:    Receipt no: 186',1,'no',2,'2025-06-03',NULL,2),(463,48,29,1500.00000,1500.00000,1,'2025-06-03','Towards Fees Payment:    Receipt no: 186',1,'no',2,'2025-06-03',NULL,2),(464,1,6,1500.00000,1500.00000,4,'2025-06-03',': Towards Fees Payment:    Receipt no: 187',1,'no',2,'2025-06-03',NULL,2),(465,48,29,1500.00000,1500.00000,1,'2025-06-03','Towards Fees Payment:    Receipt no: 187',1,'no',2,'2025-06-03',NULL,2),(466,1,6,3000.00000,3000.00000,4,'2025-06-03',': Towards Fees Payment:    Receipt no: 188',1,'no',2,'2025-06-03',NULL,2),(467,48,29,3000.00000,3000.00000,1,'2025-06-03','Towards Fees Payment:    Receipt no: 188',1,'no',2,'2025-06-03',NULL,2),(468,1,6,6500.00000,6500.00000,4,'2025-06-03',': Towards Fees Payment:    Receipt no: 189',1,'no',2,'2025-06-03',NULL,2),(469,48,29,6500.00000,6500.00000,1,'2025-06-03','Towards Fees Payment:    Receipt no: 189',1,'no',2,'2025-06-03',NULL,2),(470,6,48,17000.00000,17000.00000,4,'2025-06-03','Towards Fees Stamp',1,'no',2,'2025-06-03',NULL,2),(471,48,6,6000.00000,6000.00000,4,'2025-06-03','Towards Reversal of Fees Stamp',1,'no',2,'2025-06-03',NULL,2),(472,48,6,5250.00000,5250.00000,4,'2025-06-03','Towards Reversal of Fees Stamp',1,'no',2,'2025-06-03',NULL,2),(473,48,6,5250.00000,5250.00000,4,'2025-06-03','Towards Reversal of Fees Stamp',1,'no',2,'2025-06-03',NULL,2),(474,1,6,6500.00000,6500.00000,4,'2025-06-03',': Towards Fees Payment:    Receipt no: 190',1,'no',2,'2025-06-03',NULL,2),(475,48,29,6500.00000,6500.00000,1,'2025-06-03','Towards Fees Payment:    Receipt no: 190',1,'no',2,'2025-06-03',NULL,2),(476,1,6,1000.00000,1000.00000,4,'2025-06-03',': Towards Fees Payment:    Receipt no: 191',1,'no',2,'2025-06-03',NULL,2),(477,48,29,1000.00000,1000.00000,1,'2025-06-03','Towards Fees Payment:    Receipt no: 191',1,'no',2,'2025-06-03',NULL,2),(478,1,6,5000.00000,5000.00000,4,'2025-06-03',': Towards Fees Payment:    Receipt no: 192',1,'no',2,'2025-06-03',NULL,2),(479,48,29,5000.00000,5000.00000,1,'2025-06-03','Towards Fees Payment:    Receipt no: 192',1,'no',2,'2025-06-03',NULL,2),(480,1,6,6500.00000,6500.00000,4,'2025-06-03',': Towards Fees Payment:    Receipt no: 193',1,'no',2,'2025-06-03',NULL,2),(481,48,29,6500.00000,6500.00000,1,'2025-06-03','Towards Fees Payment:    Receipt no: 193',1,'no',2,'2025-06-03',NULL,2),(482,1,6,10000.00000,10000.00000,4,'2025-06-03',': Towards Fees Payment:    Receipt no: 194',1,'no',2,'2025-06-03',NULL,2),(483,48,29,10000.00000,10000.00000,1,'2025-06-03','Towards Fees Payment:    Receipt no: 194',1,'no',2,'2025-06-03',NULL,2),(484,48,6,5250.00000,5250.00000,4,'2025-06-03','Towards Reversal of Fees Stamp',1,'no',2,'2025-06-03',NULL,2),(485,48,6,5250.00000,5250.00000,4,'2025-06-03','Towards Reversal of Fees Stamp',1,'no',2,'2025-06-03',NULL,2),(486,48,6,0.00000,0.00000,4,'2025-06-03','Towards Reversal of Fees Stamp',1,'no',2,'2025-06-03',NULL,2),(487,6,48,17000.00000,17000.00000,4,'2025-06-03','Towards Fees Stamp',1,'no',2,'2025-06-03',NULL,2),(488,1,6,2000.00000,2000.00000,4,'2025-06-03',': Towards Fees Payment:    Receipt no: 195',1,'no',2,'2025-06-03',NULL,2),(489,48,29,2000.00000,2000.00000,1,'2025-06-03','Towards Fees Payment:    Receipt no: 195',1,'no',2,'2025-06-03',NULL,2),(490,1,6,550.00000,550.00000,4,'2025-06-03',': Towards Fees Payment:    Receipt no: 196',1,'no',2,'2025-06-03',NULL,2),(491,48,29,550.00000,550.00000,1,'2025-06-03','Towards Fees Payment:    Receipt no: 196',1,'no',2,'2025-06-03',NULL,2),(492,1,6,7000.00000,7000.00000,4,'2025-06-03',': Towards Fees Payment:    Receipt no: 197',1,'no',2,'2025-06-03',NULL,2),(493,48,29,7000.00000,7000.00000,1,'2025-06-03','Towards Fees Payment:    Receipt no: 197',1,'no',2,'2025-06-03',NULL,2),(494,1,6,5700.00000,5700.00000,4,'2025-06-03',': Towards Fees Payment:    Receipt no: 198',1,'no',2,'2025-06-03',NULL,2),(495,48,29,5700.00000,5700.00000,1,'2025-06-03','Towards Fees Payment:    Receipt no: 198',1,'no',2,'2025-06-03',NULL,2),(496,1,6,5000.00000,5000.00000,4,'2025-06-03',': Towards Fees Payment:    Receipt no: 199',1,'no',2,'2025-06-03',NULL,2),(497,48,29,5000.00000,5000.00000,1,'2025-06-03','Towards Fees Payment:    Receipt no: 199',1,'no',2,'2025-06-03',NULL,2),(498,1,6,4000.00000,4000.00000,4,'2025-06-03',': Towards Fees Payment:    Receipt no: 200',1,'no',2,'2025-06-03',NULL,2),(499,48,29,4000.00000,4000.00000,1,'2025-06-03','Towards Fees Payment:    Receipt no: 200',1,'no',2,'2025-06-03',NULL,2),(500,1,6,0.00000,0.00000,4,'2025-06-03',': Towards Fees Payment:    Receipt no: 201',1,'yes',2,'2025-06-03','2025-06-03',2),(501,48,29,0.00000,0.00000,1,'2025-06-03','Towards Fees Payment:    Receipt no: 201',1,'yes',2,'2025-06-03','2025-06-03',2),(502,1,6,7000.00000,7000.00000,4,'2025-06-03',': Towards Fees Payment:    Receipt no: 202',1,'no',2,'2025-06-03',NULL,2),(503,48,29,7000.00000,7000.00000,1,'2025-06-03','Towards Fees Payment:    Receipt no: 202',1,'no',2,'2025-06-03',NULL,2),(504,1,6,7000.00000,7000.00000,4,'2025-06-03',': Towards Fees Payment:    Receipt no: 203',1,'no',2,'2025-06-03',NULL,2),(505,48,29,7000.00000,7000.00000,1,'2025-06-03','Towards Fees Payment:    Receipt no: 203',1,'no',2,'2025-06-03',NULL,2),(506,1,6,5000.00000,5000.00000,4,'2025-06-03',': Towards Fees Payment:    Receipt no: 204',1,'no',2,'2025-06-03',NULL,2),(507,48,29,5000.00000,5000.00000,1,'2025-06-03','Towards Fees Payment:    Receipt no: 204',1,'no',2,'2025-06-03',NULL,2),(508,1,6,5000.00000,5000.00000,4,'2025-06-03',': Towards Fees Payment:    Receipt no: 205',1,'no',2,'2025-06-03',NULL,2),(509,48,29,5000.00000,5000.00000,1,'2025-06-03','Towards Fees Payment:    Receipt no: 205',1,'no',2,'2025-06-03',NULL,2),(510,1,6,5000.00000,5000.00000,4,'2025-06-03',': Towards Fees Payment:    Receipt no: 206',1,'no',2,'2025-06-03',NULL,2),(511,48,29,5000.00000,5000.00000,1,'2025-06-03','Towards Fees Payment:    Receipt no: 206',1,'no',2,'2025-06-03',NULL,2),(512,1,6,7000.00000,7000.00000,4,'2025-06-03',': Towards Fees Payment:    Receipt no: 207',1,'no',2,'2025-06-03',NULL,2),(513,48,29,7000.00000,7000.00000,1,'2025-06-03','Towards Fees Payment:    Receipt no: 207',1,'no',2,'2025-06-03',NULL,2),(514,1,6,3000.00000,3000.00000,4,'2025-06-03',': Towards Fees Payment:    Receipt no: 208',1,'no',2,'2025-06-03',NULL,2),(515,48,29,3000.00000,3000.00000,1,'2025-06-03','Towards Fees Payment:    Receipt no: 208',1,'no',2,'2025-06-03',NULL,2),(516,1,6,1000.00000,1000.00000,4,'2025-06-03',': Towards Fees Payment:    Receipt no: 209',1,'no',2,'2025-06-03',NULL,2),(517,48,29,1000.00000,1000.00000,1,'2025-06-03','Towards Fees Payment:    Receipt no: 209',1,'no',2,'2025-06-03',NULL,2),(518,1,6,3000.00000,3000.00000,4,'2025-06-03',': Towards Fees Payment:    Receipt no: 210',1,'no',2,'2025-06-03',NULL,2),(519,48,29,3000.00000,3000.00000,1,'2025-06-03','Towards Fees Payment:    Receipt no: 210',1,'no',2,'2025-06-03',NULL,2),(520,1,6,5000.00000,5000.00000,4,'2025-06-03',': Towards Fees Payment:    Receipt no: 211',1,'no',2,'2025-06-03',NULL,2),(521,48,29,5000.00000,5000.00000,1,'2025-06-03','Towards Fees Payment:    Receipt no: 211',1,'no',2,'2025-06-03',NULL,2),(522,1,6,3000.00000,3000.00000,4,'2025-06-03',': Towards Fees Payment:    Receipt no: 212',1,'no',2,'2025-06-03',NULL,2),(523,48,29,3000.00000,3000.00000,1,'2025-06-03','Towards Fees Payment:    Receipt no: 212',1,'no',2,'2025-06-03',NULL,2),(524,1,6,1000.00000,1000.00000,4,'2025-06-03',': Towards Fees Payment:    Receipt no: 213',1,'no',2,'2025-06-03',NULL,2),(525,48,29,1000.00000,1000.00000,1,'2025-06-03','Towards Fees Payment:    Receipt no: 213',1,'no',2,'2025-06-03',NULL,2),(526,1,6,7000.00000,7000.00000,4,'2025-06-03',': Towards Fees Payment:    Receipt no: 214',1,'no',2,'2025-06-03',NULL,2),(527,48,29,7000.00000,7000.00000,1,'2025-06-03','Towards Fees Payment:    Receipt no: 214',1,'no',2,'2025-06-03',NULL,2),(528,1,6,4000.00000,4000.00000,4,'2025-06-03',': Towards Fees Payment:    Receipt no: 215',1,'no',2,'2025-06-03',NULL,2),(529,48,29,4000.00000,4000.00000,1,'2025-06-03','Towards Fees Payment:    Receipt no: 215',1,'no',2,'2025-06-03',NULL,2),(530,1,6,2500.00000,2500.00000,4,'2025-06-03',': Towards Fees Payment:    Receipt no: 216',1,'no',2,'2025-06-03',NULL,2),(531,48,29,2500.00000,2500.00000,1,'2025-06-03','Towards Fees Payment:    Receipt no: 216',1,'no',2,'2025-06-03',NULL,2),(532,1,6,1200.00000,1200.00000,4,'2025-06-03',': Towards Fees Payment:    Receipt no: 217',1,'no',2,'2025-06-03',NULL,2),(533,48,29,1200.00000,1200.00000,1,'2025-06-03','Towards Fees Payment:    Receipt no: 217',1,'no',2,'2025-06-03',NULL,2),(534,1,6,5000.00000,5000.00000,4,'2025-06-03',': Towards Fees Payment:    Receipt no: 218',1,'no',2,'2025-06-03',NULL,2),(535,48,29,5000.00000,5000.00000,1,'2025-06-03','Towards Fees Payment:    Receipt no: 218',1,'no',2,'2025-06-03',NULL,2),(536,1,6,3000.00000,3000.00000,4,'2025-06-03',': Towards Fees Payment:    Receipt no: 219',1,'no',2,'2025-06-03',NULL,2),(537,48,29,3000.00000,3000.00000,1,'2025-06-03','Towards Fees Payment:    Receipt no: 219',1,'no',2,'2025-06-03',NULL,2),(538,1,6,7000.00000,7000.00000,4,'2025-06-03',': Towards Fees Payment:    Receipt no: 220',1,'no',2,'2025-06-03',NULL,2),(539,48,29,7000.00000,7000.00000,1,'2025-06-03','Towards Fees Payment:    Receipt no: 220',1,'no',2,'2025-06-03',NULL,2),(540,1,6,2500.00000,2500.00000,4,'2025-06-03',': Towards Fees Payment:    Receipt no: 221',1,'no',2,'2025-06-03',NULL,2),(541,48,29,2500.00000,2500.00000,1,'2025-06-03','Towards Fees Payment:    Receipt no: 221',1,'no',2,'2025-06-03',NULL,2),(542,1,6,5000.00000,5000.00000,4,'2025-06-03',': Towards Fees Payment:    Receipt no: 222',1,'no',2,'2025-06-03',NULL,2),(543,48,29,5000.00000,5000.00000,1,'2025-06-03','Towards Fees Payment:    Receipt no: 222',1,'no',2,'2025-06-03',NULL,2),(544,1,6,1500.00000,1500.00000,4,'2025-06-03',': Towards Fees Payment:    Receipt no: 223',1,'no',2,'2025-06-03',NULL,2),(545,48,29,1500.00000,1500.00000,1,'2025-06-03','Towards Fees Payment:    Receipt no: 223',1,'no',2,'2025-06-03',NULL,2),(546,1,6,1900.00000,1900.00000,4,'2025-06-03',': Towards Fees Payment:    Receipt no: 224',1,'no',2,'2025-06-03',NULL,2),(547,48,29,1900.00000,1900.00000,1,'2025-06-03','Towards Fees Payment:    Receipt no: 224',1,'no',2,'2025-06-03',NULL,2),(548,1,6,7000.00000,7000.00000,4,'2025-06-03',': Towards Fees Payment:    Receipt no: 225',1,'no',2,'2025-06-04',NULL,2),(549,48,29,7000.00000,7000.00000,1,'2025-06-03','Towards Fees Payment:    Receipt no: 225',1,'no',2,'2025-06-04',NULL,2),(550,1,6,7000.00000,7000.00000,4,'2025-06-04',': Towards Fees Payment:    Receipt no: 226',1,'no',2,'2025-06-04',NULL,2),(551,48,29,7000.00000,7000.00000,1,'2025-06-04','Towards Fees Payment:    Receipt no: 226',1,'no',2,'2025-06-04',NULL,2),(552,1,6,7000.00000,7000.00000,4,'2025-06-04',': Towards Fees Payment:    Receipt no: 227',1,'no',2,'2025-06-04',NULL,2),(553,48,29,7000.00000,7000.00000,1,'2025-06-04','Towards Fees Payment:    Receipt no: 227',1,'no',2,'2025-06-04',NULL,2),(554,1,6,7000.00000,7000.00000,4,'2025-06-04',': Towards Fees Payment:    Receipt no: 228',1,'no',2,'2025-06-04',NULL,2),(555,48,29,7000.00000,7000.00000,1,'2025-06-04','Towards Fees Payment:    Receipt no: 228',1,'no',2,'2025-06-04',NULL,2),(556,1,6,4500.00000,4500.00000,4,'2025-06-04',': Towards Fees Payment:    Receipt no: 229',1,'no',2,'2025-06-04',NULL,2),(557,48,29,4500.00000,4500.00000,1,'2025-06-04','Towards Fees Payment:    Receipt no: 229',1,'no',2,'2025-06-04',NULL,2),(558,1,6,7000.00000,7000.00000,4,'2025-06-04',': Towards Fees Payment:    Receipt no: 230',1,'no',2,'2025-06-04',NULL,2),(559,48,29,7000.00000,7000.00000,1,'2025-06-04','Towards Fees Payment:    Receipt no: 230',1,'no',2,'2025-06-04',NULL,2),(560,1,6,3000.00000,3000.00000,4,'2025-06-04',': Towards Fees Payment:    Receipt no: 231',1,'no',2,'2025-06-04',NULL,2),(561,48,29,3000.00000,3000.00000,1,'2025-06-04','Towards Fees Payment:    Receipt no: 231',1,'no',2,'2025-06-04',NULL,2),(562,1,6,1500.00000,1500.00000,4,'2025-06-04',': Towards Fees Payment:    Receipt no: 232',1,'no',2,'2025-06-04',NULL,2),(563,48,29,1500.00000,1500.00000,1,'2025-06-04','Towards Fees Payment:    Receipt no: 232',1,'no',2,'2025-06-04',NULL,2),(564,1,6,2000.00000,2000.00000,4,'2025-06-04',': Towards Fees Payment:    Receipt no: 233',1,'no',2,'2025-06-04',NULL,2),(565,48,29,2000.00000,2000.00000,1,'2025-06-04','Towards Fees Payment:    Receipt no: 233',1,'no',2,'2025-06-04',NULL,2),(566,1,6,7000.00000,7000.00000,4,'2025-06-04',': Towards Fees Payment:    Receipt no: 234',1,'no',2,'2025-06-04',NULL,2),(567,48,29,7000.00000,7000.00000,1,'2025-06-04','Towards Fees Payment:    Receipt no: 234',1,'no',2,'2025-06-04',NULL,2),(568,1,6,4500.00000,4500.00000,4,'2025-06-04',': Towards Fees Payment:    Receipt no: 235',1,'no',2,'2025-06-04',NULL,2),(569,48,29,4500.00000,4500.00000,1,'2025-06-04','Towards Fees Payment:    Receipt no: 235',1,'no',2,'2025-06-04',NULL,2),(570,1,6,1750.00000,1750.00000,4,'2025-06-04',': Towards Fees Payment:    Receipt no: 236',1,'no',2,'2025-06-04',NULL,2),(571,48,29,1750.00000,1750.00000,1,'2025-06-04','Towards Fees Payment:    Receipt no: 236',1,'no',2,'2025-06-04',NULL,2),(572,1,6,7000.00000,7000.00000,4,'2025-06-04',': Towards Fees Payment:    Receipt no: 237',1,'no',2,'2025-06-04',NULL,2),(573,48,29,7000.00000,7000.00000,1,'2025-06-04','Towards Fees Payment:    Receipt no: 237',1,'no',2,'2025-06-04',NULL,2),(574,1,6,1500.00000,1500.00000,4,'2025-06-04',': Towards Fees Payment:    Receipt no: 238',1,'no',2,'2025-06-04',NULL,2),(575,48,29,1500.00000,1500.00000,1,'2025-06-04','Towards Fees Payment:    Receipt no: 238',1,'no',2,'2025-06-04',NULL,2),(576,1,6,7000.00000,7000.00000,4,'2025-06-04',': Towards Fees Payment:    Receipt no: 239',1,'no',2,'2025-06-04',NULL,2),(577,48,29,7000.00000,7000.00000,1,'2025-06-04','Towards Fees Payment:    Receipt no: 239',1,'no',2,'2025-06-04',NULL,2),(578,1,6,3000.00000,3000.00000,4,'2025-06-04',': Towards Fees Payment:    Receipt no: 240',1,'no',2,'2025-06-04',NULL,2),(579,48,29,3000.00000,3000.00000,1,'2025-06-04','Towards Fees Payment:    Receipt no: 240',1,'no',2,'2025-06-04',NULL,2),(580,1,6,2000.00000,2000.00000,4,'2025-06-04',': Towards Fees Payment:    Receipt no: 241',1,'no',2,'2025-06-04',NULL,2),(581,48,29,2000.00000,2000.00000,1,'2025-06-04','Towards Fees Payment:    Receipt no: 241',1,'no',2,'2025-06-04',NULL,2),(582,1,6,3000.00000,3000.00000,4,'2025-06-04',': Towards Fees Payment:    Receipt no: 242',1,'no',2,'2025-06-04',NULL,2),(583,48,29,3000.00000,3000.00000,1,'2025-06-04','Towards Fees Payment:    Receipt no: 242',1,'no',2,'2025-06-04',NULL,2),(584,1,6,2000.00000,2000.00000,4,'2025-06-04',': Towards Fees Payment:    Receipt no: 243',1,'no',2,'2025-06-04',NULL,2),(585,48,29,2000.00000,2000.00000,1,'2025-06-04','Towards Fees Payment:    Receipt no: 243',1,'no',2,'2025-06-04',NULL,2),(586,1,6,6500.00000,6500.00000,4,'2025-06-04',': Towards Fees Payment:    Receipt no: 244',1,'no',2,'2025-06-04',NULL,2),(587,48,29,6500.00000,6500.00000,1,'2025-06-04','Towards Fees Payment:    Receipt no: 244',1,'no',2,'2025-06-04',NULL,2),(588,1,6,2750.00000,2750.00000,4,'2025-06-04',': Towards Fees Payment:    Receipt no: 245',1,'no',2,'2025-06-04',NULL,2),(589,48,29,2750.00000,2750.00000,1,'2025-06-04','Towards Fees Payment:    Receipt no: 245',1,'no',2,'2025-06-04',NULL,2),(590,1,6,7000.00000,7000.00000,4,'2025-06-04',': Towards Fees Payment:    Receipt no: 246',1,'no',2,'2025-06-04',NULL,2),(591,48,29,7000.00000,7000.00000,1,'2025-06-04','Towards Fees Payment:    Receipt no: 246',1,'no',2,'2025-06-04',NULL,2),(592,1,6,4000.00000,4000.00000,4,'2025-06-04',': Towards Fees Payment:    Receipt no: 247',1,'no',2,'2025-06-04',NULL,2),(593,48,29,4000.00000,4000.00000,1,'2025-06-04','Towards Fees Payment:    Receipt no: 247',1,'no',2,'2025-06-04',NULL,2),(594,1,6,5700.00000,5700.00000,4,'2025-06-04',': Towards Fees Payment:    Receipt no: 248',1,'no',2,'2025-06-04',NULL,2),(595,48,29,5700.00000,5700.00000,1,'2025-06-04','Towards Fees Payment:    Receipt no: 248',1,'no',2,'2025-06-04',NULL,2),(596,1,6,7000.00000,7000.00000,4,'2025-06-04',': Towards Fees Payment:    Receipt no: 249',1,'no',2,'2025-06-04',NULL,2),(597,48,29,7000.00000,7000.00000,1,'2025-06-04','Towards Fees Payment:    Receipt no: 249',1,'no',2,'2025-06-04',NULL,2),(598,1,6,5000.00000,5000.00000,4,'2025-06-04',': Towards Fees Payment:    Receipt no: 250',1,'no',2,'2025-06-04',NULL,2),(599,48,29,5000.00000,5000.00000,1,'2025-06-04','Towards Fees Payment:    Receipt no: 250',1,'no',2,'2025-06-04',NULL,2),(600,1,6,550.00000,550.00000,4,'2025-06-04',': Towards Fees Payment:    Receipt no: 251',1,'no',2,'2025-06-04',NULL,2),(601,48,29,550.00000,550.00000,1,'2025-06-04','Towards Fees Payment:    Receipt no: 251',1,'no',2,'2025-06-04',NULL,2),(602,1,6,1000.00000,1000.00000,4,'2025-06-04',': Towards Fees Payment:    Receipt no: 252',1,'no',2,'2025-06-04',NULL,2),(603,48,29,1000.00000,1000.00000,1,'2025-06-04','Towards Fees Payment:    Receipt no: 252',1,'no',2,'2025-06-04',NULL,2),(604,1,6,1000.00000,1000.00000,4,'2025-06-04',': Towards Fees Payment:    Receipt no: 253',1,'no',2,'2025-06-04',NULL,2),(605,48,29,1000.00000,1000.00000,1,'2025-06-04','Towards Fees Payment:    Receipt no: 253',1,'no',2,'2025-06-04',NULL,2),(606,1,6,2000.00000,2000.00000,4,'2025-06-04',': Towards Fees Payment:    Receipt no: 254',1,'no',2,'2025-06-04',NULL,2),(607,48,29,2000.00000,2000.00000,1,'2025-06-04','Towards Fees Payment:    Receipt no: 254',1,'no',2,'2025-06-04',NULL,2),(608,1,6,7000.00000,7000.00000,4,'2025-06-04',': Towards Fees Payment:    Receipt no: 255',1,'no',2,'2025-06-04',NULL,2),(609,48,29,7000.00000,7000.00000,1,'2025-06-04','Towards Fees Payment:    Receipt no: 255',1,'no',2,'2025-06-04',NULL,2),(610,1,6,1000.00000,1000.00000,4,'2025-06-04',': Towards Fees Payment:    Receipt no: 256',1,'no',2,'2025-06-04',NULL,2),(611,48,29,1000.00000,1000.00000,1,'2025-06-04','Towards Fees Payment:    Receipt no: 256',1,'no',2,'2025-06-04',NULL,2),(612,1,6,5000.00000,5000.00000,4,'2025-06-04',': Towards Fees Payment:    Receipt no: 257',1,'no',2,'2025-06-04',NULL,2),(613,48,29,5000.00000,5000.00000,1,'2025-06-04','Towards Fees Payment:    Receipt no: 257',1,'no',2,'2025-06-04',NULL,2),(614,1,6,3000.00000,3000.00000,4,'2025-06-04',': Towards Fees Payment:    Receipt no: 258',1,'no',2,'2025-06-04',NULL,2),(615,48,29,3000.00000,3000.00000,1,'2025-06-04','Towards Fees Payment:    Receipt no: 258',1,'no',2,'2025-06-04',NULL,2),(616,1,6,4000.00000,4000.00000,4,'2025-06-04',': Towards Fees Payment:    Receipt no: 259',1,'no',2,'2025-06-04',NULL,2),(617,48,29,4000.00000,4000.00000,1,'2025-06-04','Towards Fees Payment:    Receipt no: 259',1,'no',2,'2025-06-04',NULL,2),(618,1,6,7000.00000,7000.00000,4,'2025-06-04',': Towards Fees Payment:    Receipt no: 260',1,'no',2,'2025-06-04',NULL,2),(619,48,29,7000.00000,7000.00000,1,'2025-06-04','Towards Fees Payment:    Receipt no: 260',1,'no',2,'2025-06-04',NULL,2),(620,1,6,1700.00000,1700.00000,4,'2025-06-04',': Towards Fees Payment:    Receipt no: 261',1,'no',2,'2025-06-04',NULL,2),(621,48,29,1700.00000,1700.00000,1,'2025-06-04','Towards Fees Payment:    Receipt no: 261',1,'no',2,'2025-06-04',NULL,2),(622,1,6,7000.00000,7000.00000,4,'2025-06-04',': Towards Fees Payment:    Receipt no: 262',1,'no',2,'2025-06-04',NULL,2),(623,48,29,7000.00000,7000.00000,1,'2025-06-04','Towards Fees Payment:    Receipt no: 262',1,'no',2,'2025-06-04',NULL,2),(624,1,6,2000.00000,2000.00000,4,'2025-06-04',': Towards Fees Payment:    Receipt no: 263',1,'no',2,'2025-06-04',NULL,2),(625,48,29,2000.00000,2000.00000,1,'2025-06-04','Towards Fees Payment:    Receipt no: 263',1,'no',2,'2025-06-04',NULL,2),(626,1,6,4000.00000,4000.00000,4,'2025-06-05',': Towards Fees Payment:    Receipt no: 264',1,'no',2,'2025-06-05',NULL,2),(627,48,29,4000.00000,4000.00000,1,'2025-06-05','Towards Fees Payment:    Receipt no: 264',1,'no',2,'2025-06-05',NULL,2),(628,1,6,3000.00000,3000.00000,4,'2025-06-05',': Towards Fees Payment:    Receipt no: 265',1,'no',2,'2025-06-05',NULL,2),(629,48,29,3000.00000,3000.00000,1,'2025-06-05','Towards Fees Payment:    Receipt no: 265',1,'no',2,'2025-06-05',NULL,2),(630,1,6,3000.00000,3000.00000,4,'2025-06-05',': Towards Fees Payment:    Receipt no: 266',1,'no',2,'2025-06-05',NULL,2),(631,48,29,3000.00000,3000.00000,1,'2025-06-05','Towards Fees Payment:    Receipt no: 266',1,'no',2,'2025-06-05',NULL,2),(632,1,6,7000.00000,7000.00000,4,'2025-06-05',': Towards Fees Payment:    Receipt no: 267',1,'no',2,'2025-06-05',NULL,2),(633,48,29,7000.00000,7000.00000,1,'2025-06-05','Towards Fees Payment:    Receipt no: 267',1,'no',2,'2025-06-05',NULL,2),(634,1,6,5000.00000,5000.00000,4,'2025-06-05',': Towards Fees Payment:    Receipt no: 268',1,'no',2,'2025-06-05',NULL,2),(635,48,29,5000.00000,5000.00000,1,'2025-06-05','Towards Fees Payment:    Receipt no: 268',1,'no',2,'2025-06-05',NULL,2),(636,1,6,15000.00000,15000.00000,4,'2025-06-05',': Towards Fees Payment:    Receipt no: 269',1,'no',2,'2025-06-05',NULL,2),(637,48,29,15000.00000,15000.00000,1,'2025-06-05','Towards Fees Payment:    Receipt no: 269',1,'no',2,'2025-06-05',NULL,2),(638,1,6,1000.00000,1000.00000,4,'2025-06-05',': Towards Fees Payment:    Receipt no: 270',1,'no',2,'2025-06-05',NULL,2),(639,48,29,1000.00000,1000.00000,1,'2025-06-05','Towards Fees Payment:    Receipt no: 270',1,'no',2,'2025-06-05',NULL,2),(640,1,6,7000.00000,7000.00000,4,'2025-06-05',': Towards Fees Payment:    Receipt no: 271',1,'no',2,'2025-06-05',NULL,2),(641,48,29,7000.00000,7000.00000,1,'2025-06-05','Towards Fees Payment:    Receipt no: 271',1,'no',2,'2025-06-05',NULL,2),(642,1,6,7000.00000,7000.00000,4,'2025-06-09',': Towards Fees Payment:    Receipt no: 272',1,'no',2,'2025-06-09',NULL,2),(643,48,29,7000.00000,7000.00000,1,'2025-06-09','Towards Fees Payment:    Receipt no: 272',1,'no',2,'2025-06-09',NULL,2),(644,1,6,7000.00000,7000.00000,4,'2025-06-09',': Towards Fees Payment:    Receipt no: 273',1,'no',2,'2025-06-09',NULL,2),(645,48,29,7000.00000,7000.00000,1,'2025-06-09','Towards Fees Payment:    Receipt no: 273',1,'no',2,'2025-06-09',NULL,2),(646,1,6,3000.00000,3000.00000,4,'2025-06-09',': Towards Fees Payment:    Receipt no: 274',1,'no',2,'2025-06-09',NULL,2),(647,48,29,3000.00000,3000.00000,1,'2025-06-09','Towards Fees Payment:    Receipt no: 274',1,'no',2,'2025-06-09',NULL,2),(648,1,6,2000.00000,2000.00000,4,'2025-06-09',': Towards Fees Payment:    Receipt no: 275',1,'no',2,'2025-06-09',NULL,2),(649,48,29,2000.00000,2000.00000,1,'2025-06-09','Towards Fees Payment:    Receipt no: 275',1,'no',2,'2025-06-09',NULL,2),(650,1,6,1000.00000,1000.00000,4,'2025-06-09',': Towards Fees Payment:    Receipt no: 276',1,'no',2,'2025-06-09',NULL,2),(651,48,29,1000.00000,1000.00000,1,'2025-06-09','Towards Fees Payment:    Receipt no: 276',1,'no',2,'2025-06-09',NULL,2),(652,1,6,7000.00000,7000.00000,4,'2025-06-09',': Towards Fees Payment:    Receipt no: 277',1,'no',2,'2025-06-09',NULL,2),(653,48,29,7000.00000,7000.00000,1,'2025-06-09','Towards Fees Payment:    Receipt no: 277',1,'no',2,'2025-06-09',NULL,2),(654,1,6,2000.00000,2000.00000,4,'2025-06-09',': Towards Fees Payment:    Receipt no: 278',1,'no',2,'2025-06-09',NULL,2),(655,48,29,2000.00000,2000.00000,1,'2025-06-09','Towards Fees Payment:    Receipt no: 278',1,'no',2,'2025-06-09',NULL,2),(656,1,6,5000.00000,5000.00000,4,'2025-06-09',': Towards Fees Payment:    Receipt no: 279',1,'no',2,'2025-06-09',NULL,2),(657,48,29,5000.00000,5000.00000,1,'2025-06-09','Towards Fees Payment:    Receipt no: 279',1,'no',2,'2025-06-09',NULL,2),(658,1,6,2000.00000,2000.00000,4,'2025-06-09',': Towards Fees Payment:    Receipt no: 280',1,'no',2,'2025-06-09',NULL,2),(659,48,29,2000.00000,2000.00000,1,'2025-06-09','Towards Fees Payment:    Receipt no: 280',1,'no',2,'2025-06-09',NULL,2),(660,1,6,5500.00000,5500.00000,4,'2025-06-10',': Towards Fees Payment:    Receipt no: 281',1,'no',2,'2025-06-10',NULL,2),(661,48,29,5500.00000,5500.00000,1,'2025-06-10','Towards Fees Payment:    Receipt no: 281',1,'no',2,'2025-06-10',NULL,2),(662,1,6,5000.00000,5000.00000,4,'2025-06-10',': Towards Fees Payment:    Receipt no: 282',1,'no',2,'2025-06-10',NULL,2),(663,48,29,5000.00000,5000.00000,1,'2025-06-10','Towards Fees Payment:    Receipt no: 282',1,'no',2,'2025-06-10',NULL,2),(664,1,6,3000.00000,3000.00000,4,'2025-06-10',': Towards Fees Payment:    Receipt no: 283',1,'no',2,'2025-06-10',NULL,2),(665,48,29,3000.00000,3000.00000,1,'2025-06-10','Towards Fees Payment:    Receipt no: 283',1,'no',2,'2025-06-10',NULL,2),(666,1,6,1000.00000,1000.00000,4,'2025-06-10',': Towards Fees Payment:    Receipt no: 284',1,'no',2,'2025-06-10',NULL,2),(667,48,29,1000.00000,1000.00000,1,'2025-06-10','Towards Fees Payment:    Receipt no: 284',1,'no',2,'2025-06-10',NULL,2),(668,1,6,2000.00000,2000.00000,4,'2025-06-10',': Towards Fees Payment:    Receipt no: 285',1,'no',2,'2025-06-10',NULL,2),(669,48,29,2000.00000,2000.00000,1,'2025-06-10','Towards Fees Payment:    Receipt no: 285',1,'no',2,'2025-06-10',NULL,2),(670,1,6,6000.00000,6000.00000,4,'2025-06-10',': Towards Fees Payment:    Receipt no: 286',1,'no',2,'2025-06-10',NULL,2),(671,48,29,6000.00000,6000.00000,1,'2025-06-10','Towards Fees Payment:    Receipt no: 286',1,'no',2,'2025-06-10',NULL,2),(672,1,6,2000.00000,2000.00000,4,'2025-06-10',': Towards Fees Payment:    Receipt no: 287',1,'no',2,'2025-06-10',NULL,2),(673,48,29,2000.00000,2000.00000,1,'2025-06-10','Towards Fees Payment:    Receipt no: 287',1,'no',2,'2025-06-10',NULL,2),(674,1,6,1050.00000,1050.00000,4,'2025-06-10',': Towards Fees Payment:    Receipt no: 288',1,'no',2,'2025-06-10',NULL,2),(675,48,29,1050.00000,1050.00000,1,'2025-06-10','Towards Fees Payment:    Receipt no: 288',1,'no',2,'2025-06-10',NULL,2),(676,1,6,2750.00000,2750.00000,4,'2025-06-10',': Towards Fees Payment:    Receipt no: 289',1,'no',2,'2025-06-10',NULL,2),(677,48,29,2750.00000,2750.00000,1,'2025-06-10','Towards Fees Payment:    Receipt no: 289',1,'no',2,'2025-06-10',NULL,2),(678,1,6,2500.00000,2500.00000,4,'2025-06-10',': Towards Fees Payment:    Receipt no: 290',1,'no',2,'2025-06-10',NULL,2),(679,48,29,2500.00000,2500.00000,1,'2025-06-10','Towards Fees Payment:    Receipt no: 290',1,'no',2,'2025-06-10',NULL,2),(680,1,6,6500.00000,6500.00000,4,'2025-06-10',': Towards Fees Payment:    Receipt no: 291',1,'no',2,'2025-06-10',NULL,2),(681,48,29,6500.00000,6500.00000,1,'2025-06-10','Towards Fees Payment:    Receipt no: 291',1,'no',2,'2025-06-10',NULL,2),(682,1,6,7000.00000,7000.00000,4,'2025-06-10',': Towards Fees Payment:    Receipt no: 292',1,'no',2,'2025-06-10',NULL,2),(683,48,29,7000.00000,7000.00000,1,'2025-06-10','Towards Fees Payment:    Receipt no: 292',1,'no',2,'2025-06-10',NULL,2),(684,1,6,7000.00000,7000.00000,4,'2025-06-10',': Towards Fees Payment:    Receipt no: 293',1,'no',2,'2025-06-10',NULL,2),(685,48,29,7000.00000,7000.00000,1,'2025-06-10','Towards Fees Payment:    Receipt no: 293',1,'no',2,'2025-06-10',NULL,2),(686,1,6,2000.00000,2000.00000,4,'2025-06-10',': Towards Fees Payment:    Receipt no: 294',1,'no',2,'2025-06-10',NULL,2),(687,48,29,2000.00000,2000.00000,1,'2025-06-10','Towards Fees Payment:    Receipt no: 294',1,'no',2,'2025-06-10',NULL,2),(688,1,6,6000.00000,6000.00000,4,'2025-06-10',': Towards Fees Payment:    Receipt no: 295',1,'no',2,'2025-06-10',NULL,2),(689,48,29,6000.00000,6000.00000,1,'2025-06-10','Towards Fees Payment:    Receipt no: 295',1,'no',2,'2025-06-10',NULL,2),(690,1,6,7000.00000,7000.00000,4,'2025-06-10',': Towards Fees Payment:    Receipt no: 296',1,'no',2,'2025-06-10',NULL,2),(691,48,29,7000.00000,7000.00000,1,'2025-06-10','Towards Fees Payment:    Receipt no: 296',1,'no',2,'2025-06-10',NULL,2),(692,1,6,2000.00000,2000.00000,4,'2025-06-10',': Towards Fees Payment:    Receipt no: 297',1,'no',2,'2025-06-10',NULL,2),(693,48,29,2000.00000,2000.00000,1,'2025-06-10','Towards Fees Payment:    Receipt no: 297',1,'no',2,'2025-06-10',NULL,2),(694,1,6,4000.00000,4000.00000,4,'2025-06-10',': Towards Fees Payment:    Receipt no: 298',1,'no',2,'2025-06-10',NULL,2),(695,48,29,4000.00000,4000.00000,1,'2025-06-10','Towards Fees Payment:    Receipt no: 298',1,'no',2,'2025-06-10',NULL,2),(696,1,6,1000.00000,1000.00000,4,'2025-06-10',': Towards Fees Payment:    Receipt no: 299',1,'no',2,'2025-06-10',NULL,2),(697,48,29,1000.00000,1000.00000,1,'2025-06-10','Towards Fees Payment:    Receipt no: 299',1,'no',2,'2025-06-10',NULL,2),(698,1,6,2000.00000,2000.00000,4,'2025-06-10',': Towards Fees Payment:    Receipt no: 300',1,'no',2,'2025-06-10',NULL,2),(699,48,29,2000.00000,2000.00000,1,'2025-06-10','Towards Fees Payment:    Receipt no: 300',1,'no',2,'2025-06-10',NULL,2),(700,6,48,15000.00000,15000.00000,4,'2025-06-10','Towards Fees Stamp',1,'no',2,'2025-06-10',NULL,2),(701,6,48,18900.00000,18900.00000,4,'2025-06-10','Towards Fees Stamp',1,'no',2,'2025-06-10',NULL,2),(702,6,48,17000.00000,17000.00000,4,'2025-06-10','Towards Fees Stamp',1,'no',2,'2025-06-10',NULL,2),(703,6,48,15000.00000,15000.00000,4,'2025-06-10','Towards Fees Stamp',1,'no',2,'2025-06-10',NULL,2),(704,6,48,15000.00000,15000.00000,4,'2025-06-10','Towards Fees Stamp',1,'no',2,'2025-06-10',NULL,2),(705,6,48,15000.00000,15000.00000,4,'2025-06-10','Towards Fees Stamp',1,'no',2,'2025-06-10',NULL,2),(706,1,6,3000.00000,3000.00000,4,'2025-06-10',': Towards Fees Payment:    Receipt no: 301',1,'no',2,'2025-06-10',NULL,2),(707,48,29,3000.00000,3000.00000,1,'2025-06-10','Towards Fees Payment:    Receipt no: 301',1,'no',2,'2025-06-10',NULL,2),(708,1,6,3000.00000,3000.00000,4,'2025-06-10',': Towards Fees Payment:    Receipt no: 302',1,'no',2,'2025-06-10',NULL,2),(709,48,29,3000.00000,3000.00000,1,'2025-06-10','Towards Fees Payment:    Receipt no: 302',1,'no',2,'2025-06-10',NULL,2),(710,1,6,3000.00000,3000.00000,4,'2025-06-10',': Towards Fees Payment:    Receipt no: 303',1,'no',2,'2025-06-10',NULL,2),(711,48,29,3000.00000,3000.00000,1,'2025-06-10','Towards Fees Payment:    Receipt no: 303',1,'no',2,'2025-06-10',NULL,2),(712,1,6,5000.00000,5000.00000,4,'2025-06-10',': Towards Fees Payment:    Receipt no: 304',1,'no',2,'2025-06-10',NULL,2),(713,48,29,5000.00000,5000.00000,1,'2025-06-10','Towards Fees Payment:    Receipt no: 304',1,'no',2,'2025-06-10',NULL,2),(714,1,6,5000.00000,5000.00000,4,'2025-06-10',': Towards Fees Payment:    Receipt no: 305',1,'no',2,'2025-06-10',NULL,2),(715,48,29,5000.00000,5000.00000,1,'2025-06-10','Towards Fees Payment:    Receipt no: 305',1,'no',2,'2025-06-10',NULL,2),(716,6,48,18900.00000,18900.00000,4,'2025-06-10','Towards Fees Stamp',1,'no',2,'2025-06-10',NULL,2),(717,1,6,4000.00000,4000.00000,4,'2025-06-10',': Towards Fees Payment:    Receipt no: 306',1,'yes',2,'2025-06-10','2025-06-10',2),(718,48,29,4000.00000,4000.00000,1,'2025-06-10','Towards Fees Payment:    Receipt no: 306',1,'yes',2,'2025-06-10','2025-06-10',2),(719,6,48,18900.00000,18900.00000,4,'2025-06-10','Towards Fees Stamp',1,'no',2,'2025-06-10',NULL,2),(720,1,6,4000.00000,4000.00000,4,'2025-06-10',': Towards Fees Payment:    Receipt no: 307',1,'no',2,'2025-06-10',NULL,2),(721,48,29,4000.00000,4000.00000,1,'2025-06-10','Towards Fees Payment:    Receipt no: 307',1,'no',2,'2025-06-10',NULL,2),(722,1,6,5500.00000,5500.00000,4,'2025-06-10',': Towards Fees Payment:    Receipt no: 308',1,'no',2,'2025-06-10',NULL,2),(723,48,29,5500.00000,5500.00000,1,'2025-06-10','Towards Fees Payment:    Receipt no: 308',1,'no',2,'2025-06-10',NULL,2),(724,6,48,15000.00000,15000.00000,4,'2025-06-10','Towards Fees Stamp',1,'no',2,'2025-06-10',NULL,2),(725,1,6,2000.00000,2000.00000,4,'2025-06-10',': Towards Fees Payment:    Receipt no: 309',1,'no',2,'2025-06-10',NULL,2),(726,48,29,2000.00000,2000.00000,1,'2025-06-10','Towards Fees Payment:    Receipt no: 309',1,'no',2,'2025-06-10',NULL,2),(727,1,6,6000.00000,6000.00000,4,'2025-06-10',': Towards Fees Payment:    Receipt no: 310',1,'no',2,'2025-06-10',NULL,2),(728,48,29,6000.00000,6000.00000,1,'2025-06-10','Towards Fees Payment:    Receipt no: 310',1,'no',2,'2025-06-10',NULL,2),(729,1,6,6000.00000,6000.00000,4,NULL,': Towards Fees Payment:    Receipt no: 311',1,'no',2,'2025-06-10',NULL,2),(730,48,29,6000.00000,6000.00000,1,NULL,'Towards Fees Payment:    Receipt no: 311',1,'no',2,'2025-06-10',NULL,2),(731,1,6,6000.00000,6000.00000,4,'2025-06-10',': Towards Fees Payment:    Receipt no: 312',1,'no',2,'2025-06-10',NULL,2),(732,48,29,6000.00000,6000.00000,1,'2025-06-10','Towards Fees Payment:    Receipt no: 312',1,'no',2,'2025-06-10',NULL,2),(733,1,6,5000.00000,5000.00000,4,'2025-06-10',': Towards Fees Payment:    Receipt no: 313',1,'no',2,'2025-06-10',NULL,2),(734,48,29,5000.00000,5000.00000,1,'2025-06-10','Towards Fees Payment:    Receipt no: 313',1,'no',2,'2025-06-10',NULL,2),(735,1,6,6000.00000,6000.00000,4,'2025-06-10',': Towards Fees Payment:    Receipt no: 314',1,'no',2,'2025-06-10',NULL,2),(736,48,29,6000.00000,6000.00000,1,'2025-06-10','Towards Fees Payment:    Receipt no: 314',1,'no',2,'2025-06-10',NULL,2),(737,1,6,6000.00000,6000.00000,4,'2025-06-10',': Towards Fees Payment:    Receipt no: 315',1,'no',2,'2025-06-10',NULL,2),(738,48,29,6000.00000,6000.00000,1,'2025-06-10','Towards Fees Payment:    Receipt no: 315',1,'no',2,'2025-06-10',NULL,2),(739,1,6,6000.00000,6000.00000,4,'2025-06-10',': Towards Fees Payment:    Receipt no: 316',1,'no',2,'2025-06-10',NULL,2),(740,48,29,6000.00000,6000.00000,1,'2025-06-10','Towards Fees Payment:    Receipt no: 316',1,'no',2,'2025-06-10',NULL,2),(741,1,6,7000.00000,7000.00000,4,'2025-06-10',': Towards Fees Payment:    Receipt no: 317',1,'no',2,'2025-06-10',NULL,2),(742,48,29,7000.00000,7000.00000,1,'2025-06-10','Towards Fees Payment:    Receipt no: 317',1,'no',2,'2025-06-10',NULL,2),(743,1,6,4000.00000,4000.00000,4,'2025-06-10',': Towards Fees Payment:    Receipt no: 318',1,'no',2,'2025-06-10',NULL,2),(744,48,29,4000.00000,4000.00000,1,'2025-06-10','Towards Fees Payment:    Receipt no: 318',1,'no',2,'2025-06-10',NULL,2),(745,1,6,2500.00000,2500.00000,4,'2025-06-10',': Towards Fees Payment:    Receipt no: 319',1,'no',2,'2025-06-10',NULL,2),(746,48,29,2500.00000,2500.00000,1,'2025-06-10','Towards Fees Payment:    Receipt no: 319',1,'no',2,'2025-06-10',NULL,2),(747,1,6,3000.00000,3000.00000,4,'2025-06-10',': Towards Fees Payment:    Receipt no: 320',1,'no',2,'2025-06-10',NULL,2),(748,48,29,3000.00000,3000.00000,1,'2025-06-10','Towards Fees Payment:    Receipt no: 320',1,'no',2,'2025-06-10',NULL,2),(749,1,6,10000.00000,10000.00000,4,'2025-06-10',': Towards Fees Payment:    Receipt no: 321',1,'no',2,'2025-06-10',NULL,2),(750,48,29,10000.00000,10000.00000,1,'2025-06-10','Towards Fees Payment:    Receipt no: 321',1,'no',2,'2025-06-10',NULL,2),(751,1,6,7000.00000,7000.00000,4,'2025-06-10',': Towards Fees Payment:    Receipt no: 322',1,'no',2,'2025-06-10',NULL,2),(752,48,29,7000.00000,7000.00000,1,'2025-06-10','Towards Fees Payment:    Receipt no: 322',1,'no',2,'2025-06-10',NULL,2),(753,1,6,7000.00000,7000.00000,4,'2025-06-10',': Towards Fees Payment:    Receipt no: 323',1,'no',2,'2025-06-10',NULL,2),(754,48,29,7000.00000,7000.00000,1,'2025-06-10','Towards Fees Payment:    Receipt no: 323',1,'no',2,'2025-06-10',NULL,2),(755,1,6,7000.00000,7000.00000,4,'2025-06-10',': Towards Fees Payment:    Receipt no: 324',1,'no',2,'2025-06-10',NULL,2),(756,48,29,7000.00000,7000.00000,1,'2025-06-10','Towards Fees Payment:    Receipt no: 324',1,'no',2,'2025-06-10',NULL,2),(757,6,48,15000.00000,15000.00000,4,'2025-06-10','Towards Fees Stamp',1,'no',2,'2025-06-10',NULL,2),(758,1,6,7000.00000,7000.00000,4,'2025-06-10',': Towards Fees Payment:    Receipt no: 325',1,'no',2,'2025-06-10',NULL,2),(759,48,29,7000.00000,7000.00000,1,'2025-06-10','Towards Fees Payment:    Receipt no: 325',1,'no',2,'2025-06-10',NULL,2),(760,1,6,5000.00000,5000.00000,4,'2025-06-10',': Towards Fees Payment:    Receipt no: 326',1,'no',2,'2025-06-10',NULL,2),(761,48,29,5000.00000,5000.00000,1,'2025-06-10','Towards Fees Payment:    Receipt no: 326',1,'no',2,'2025-06-10',NULL,2),(762,1,6,5000.00000,5000.00000,4,'2025-06-10',': Towards Fees Payment:    Receipt no: 327',1,'no',2,'2025-06-10',NULL,2),(763,48,29,5000.00000,5000.00000,1,'2025-06-10','Towards Fees Payment:    Receipt no: 327',1,'no',2,'2025-06-10',NULL,2),(764,1,6,2000.00000,2000.00000,4,'2025-06-10',': Towards Fees Payment:    Receipt no: 328',1,'no',2,'2025-06-10',NULL,2),(765,48,29,2000.00000,2000.00000,1,'2025-06-10','Towards Fees Payment:    Receipt no: 328',1,'no',2,'2025-06-10',NULL,2),(766,1,6,1000.00000,1000.00000,4,'2025-06-10',': Towards Fees Payment:    Receipt no: 329',1,'no',2,'2025-06-10',NULL,2),(767,48,29,1000.00000,1000.00000,1,'2025-06-10','Towards Fees Payment:    Receipt no: 329',1,'no',2,'2025-06-10',NULL,2);
/*!40000 ALTER TABLE `acc_voucherentrytransactions` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `adminexpenses`
--

DROP TABLE IF EXISTS `adminexpenses`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `adminexpenses` (
  `idAdminExpenses` int(11) NOT NULL AUTO_INCREMENT,
  `itemdescription` varchar(500) DEFAULT NULL,
  `priceofitem` varchar(20) DEFAULT NULL,
  `entrydate` date NOT NULL,
  `vno` int(11) DEFAULT NULL,
  `branchid` int(11) DEFAULT NULL,
  `paidto` varchar(500) DEFAULT NULL,
  `chequeno` varchar(30) DEFAULT NULL,
  `voucherstatus` varchar(20) DEFAULT NULL,
  `paymenttype` varchar(10) DEFAULT NULL,
  `chequedate` date DEFAULT NULL,
  `bankname` varchar(50) DEFAULT NULL,
  `userid` int(11) DEFAULT NULL,
  PRIMARY KEY (`idAdminExpenses`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `adminexpenses`
--

LOCK TABLES `adminexpenses` WRITE;
/*!40000 ALTER TABLE `adminexpenses` DISABLE KEYS */;
/*!40000 ALTER TABLE `adminexpenses` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `admissionenquiry`
--

DROP TABLE IF EXISTS `admissionenquiry`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `admissionenquiry` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(200) DEFAULT NULL,
  `gender` varchar(45) DEFAULT NULL,
  `caste` varchar(45) DEFAULT NULL,
  `placeofbirth` varchar(100) DEFAULT NULL,
  `surname` varchar(100) DEFAULT NULL,
  `previousclasspassed` varchar(45) DEFAULT NULL,
  `previousschoolname` varchar(100) DEFAULT NULL,
  `religion` varchar(45) DEFAULT NULL,
  `fathername` varchar(100) DEFAULT NULL,
  `fatherqualification` varchar(45) DEFAULT NULL,
  `mothername` varchar(100) DEFAULT NULL,
  `motherqualification` varchar(45) DEFAULT NULL,
  `admissionclass` varchar(45) DEFAULT NULL,
  `brothereducation` varchar(45) DEFAULT NULL,
  `sistereducation` varchar(45) DEFAULT NULL,
  `occupation` varchar(45) DEFAULT NULL,
  `dateofbirth` date DEFAULT NULL,
  `address` varchar(1000) DEFAULT NULL,
  `mobileno` varchar(45) DEFAULT NULL,
  `academicyear` varchar(45) DEFAULT NULL,
  `notes` varchar(1000) DEFAULT NULL,
  `branchid` int(11) DEFAULT NULL,
  `userid` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `admissionenquiry`
--

LOCK TABLES `admissionenquiry` WRITE;
/*!40000 ALTER TABLE `admissionenquiry` DISABLE KEYS */;
/*!40000 ALTER TABLE `admissionenquiry` ENABLE KEYS */;
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
  `userid` int(11) DEFAULT NULL,
  PRIMARY KEY (`idattendancemaster`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `att_attendancemaster`
--

LOCK TABLES `att_attendancemaster` WRITE;
/*!40000 ALTER TABLE `att_attendancemaster` DISABLE KEYS */;
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
  `userid` int(11) DEFAULT NULL,
  PRIMARY KEY (`shid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `att_holidaysmaster`
--

LOCK TABLES `att_holidaysmaster` WRITE;
/*!40000 ALTER TABLE `att_holidaysmaster` DISABLE KEYS */;
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
  `userid` int(11) DEFAULT NULL,
  PRIMARY KEY (`attendanceid`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `att_staffdailyattendance`
--

LOCK TABLES `att_staffdailyattendance` WRITE;
/*!40000 ALTER TABLE `att_staffdailyattendance` DISABLE KEYS */;
INSERT INTO `att_staffdailyattendance` VALUES (1,'FNPS02','09:00','','2025-06-03','P','2025/26',2,0),(2,'FNPS02','09:32','','2025-06-04','P','2025/26',2,0);
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
  `userid` int(11) DEFAULT NULL,
  PRIMARY KEY (`attendanceid`),
  KEY `stdfk_idx` (`attendeeid`),
  CONSTRAINT `stdfk` FOREIGN KEY (`attendeeid`) REFERENCES `student` (`studentexternalid`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `att_studentdailyattendance`
--

LOCK TABLES `att_studentdailyattendance` WRITE;
/*!40000 ALTER TABLE `att_studentdailyattendance` DISABLE KEYS */;
INSERT INTO `att_studentdailyattendance` VALUES (1,'FNPS0085','00:00',NULL,'2025-05-27','P','2025/26',2,0),(2,'FNPS0088','00:00',NULL,'2025-05-27','A','2025/26',2,0),(3,'FNPS0091','00:00',NULL,'2025-05-27','P','2025/26',2,0),(4,'FNPS0094','00:00',NULL,'2025-05-27','P','2025/26',2,0),(5,'FNPS0099','00:00',NULL,'2025-05-27','P','2025/26',2,0);
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
  `userid` int(11) DEFAULT NULL,
  PRIMARY KEY (`wid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `att_weeklyoff`
--

LOCK TABLES `att_weeklyoff` WRITE;
/*!40000 ALTER TABLE `att_weeklyoff` DISABLE KEYS */;
/*!40000 ALTER TABLE `att_weeklyoff` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `book`
--

DROP TABLE IF EXISTS `book`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `book` (
  `bid` int(11) NOT NULL AUTO_INCREMENT,
  `bookname` varchar(200) DEFAULT NULL,
  `subject` varchar(200) DEFAULT NULL,
  `author` varchar(200) DEFAULT NULL,
  `publisher` varchar(200) DEFAULT NULL,
  `isbn` varchar(200) DEFAULT NULL,
  `status` varchar(50) DEFAULT NULL,
  `bookholder` varchar(100) DEFAULT NULL,
  `shelf` varchar(100) DEFAULT NULL,
  `startdate` date DEFAULT NULL,
  `enddate` date DEFAULT NULL,
  `noofdays` varchar(20) DEFAULT NULL,
  `availableqty` int(11) DEFAULT NULL,
  `issuedqty` int(11) DEFAULT NULL,
  `branchid` int(11) DEFAULT NULL,
  `userid` int(11) DEFAULT NULL,
  PRIMARY KEY (`bid`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `book`
--

LOCK TABLES `book` WRITE;
/*!40000 ALTER TABLE `book` DISABLE KEYS */;
/*!40000 ALTER TABLE `book` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `bookhistory`
--

DROP TABLE IF EXISTS `bookhistory`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `bookhistory` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `bid` varchar(200) DEFAULT NULL,
  `bookName` varchar(200) DEFAULT NULL,
  `studentName` varchar(200) DEFAULT NULL,
  `uid` varchar(200) DEFAULT NULL,
  `issueDate` date DEFAULT NULL,
  `expectedReturnDate` date DEFAULT NULL,
  `sid` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `bookhistory`
--

LOCK TABLES `bookhistory` WRITE;
/*!40000 ALTER TABLE `bookhistory` DISABLE KEYS */;
/*!40000 ALTER TABLE `bookhistory` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `bookissue`
--

DROP TABLE IF EXISTS `bookissue`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `bookissue` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `bookholder` varchar(100) DEFAULT NULL,
  `bookname` varchar(100) DEFAULT NULL,
  `startdate` date DEFAULT NULL,
  `enddate` date DEFAULT NULL,
  `actualreturndate` date DEFAULT NULL,
  `noofdays` int(11) DEFAULT NULL,
  `sid` int(11) DEFAULT NULL,
  `bookid` int(11) DEFAULT NULL,
  `studentname` varchar(200) DEFAULT NULL,
  `returned` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `bookissue`
--

LOCK TABLES `bookissue` WRITE;
/*!40000 ALTER TABLE `bookissue` DISABLE KEYS */;
/*!40000 ALTER TABLE `bookissue` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `branch`
--

DROP TABLE IF EXISTS `branch`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `branch` (
  `idbranch` int(11) NOT NULL AUTO_INCREMENT,
  `branchname` varchar(100) DEFAULT NULL,
  `userid` int(11) DEFAULT NULL,
  `branchcode` varchar(50) DEFAULT NULL,
  `address` varchar(500) DEFAULT NULL,
  `contact` varchar(200) DEFAULT NULL,
  PRIMARY KEY (`idbranch`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `branch`
--

LOCK TABLES `branch` WRITE;
/*!40000 ALTER TABLE `branch` DISABLE KEYS */;
INSERT INTO `branch` VALUES (1,'Head Office',1,'FNPS','Deevu Street,Kayalpatnam, Dist. Tuticorin-628204','Office: 04639796371 Email:fathimaschoolkpm@gmail.com'),(2,'Fathima Nursery & Primary School',1,'FNPS','Deevu Street,Kayalpatnam, Dist. Tuticorin-628204','Office: 04639796371 Email:fathimaschoolkpm@gmail.com');
/*!40000 ALTER TABLE `branch` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `classhierarchy`
--

DROP TABLE IF EXISTS `classhierarchy`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `classhierarchy` (
  `idclasshierarchy` int(11) NOT NULL AUTO_INCREMENT,
  `lowerclass` varchar(45) DEFAULT NULL,
  `upperclass` varchar(45) DEFAULT NULL,
  `branchid` int(11) DEFAULT NULL,
  `userid` int(11) DEFAULT NULL,
  PRIMARY KEY (`idclasshierarchy`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `classhierarchy`
--

LOCK TABLES `classhierarchy` WRITE;
/*!40000 ALTER TABLE `classhierarchy` DISABLE KEYS */;
INSERT INTO `classhierarchy` VALUES (1,'Nursery','L.K.G',2,3),(2,'L.K.G','U.K.G',2,3),(3,'U.K.G','I',2,3),(4,'I','II',2,3),(5,'II','III',2,3),(6,'III','IV',2,3),(7,'IV','V',2,3);
/*!40000 ALTER TABLE `classhierarchy` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `classsec`
--

DROP TABLE IF EXISTS `classsec`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `classsec` (
  `stdrdid` int(11) NOT NULL AUTO_INCREMENT,
  `classdetails` varchar(45) DEFAULT NULL,
  `section` varchar(45) DEFAULT NULL,
  `branchid` int(11) DEFAULT NULL,
  `userid` int(11) DEFAULT NULL,
  PRIMARY KEY (`stdrdid`)
) ENGINE=InnoDB AUTO_INCREMENT=19 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `classsec`
--

LOCK TABLES `classsec` WRITE;
/*!40000 ALTER TABLE `classsec` DISABLE KEYS */;
INSERT INTO `classsec` VALUES (1,'Nursery','',2,2),(2,'L.K.G','',2,2),(3,'U.K.G','',2,2),(4,'I','',2,2),(5,'II','',2,2),(6,'III','',2,2),(7,'IV','',2,2),(8,'V','',2,2),(15,'','A',2,2),(16,'','B',2,2),(17,'','C',2,2),(18,'','D',2,2);
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
  `userid` int(11) DEFAULT NULL,
  PRIMARY KEY (`cayid`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `currentacademicyear`
--

LOCK TABLES `currentacademicyear` WRITE;
/*!40000 ALTER TABLE `currentacademicyear` DISABLE KEYS */;
INSERT INTO `currentacademicyear` VALUES (1,'2025/26',1),(2,'2025/26',NULL);
/*!40000 ALTER TABLE `currentacademicyear` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `degreedetails`
--

DROP TABLE IF EXISTS `degreedetails`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `degreedetails` (
  `iddegreedetails` int(11) NOT NULL AUTO_INCREMENT,
  `exampassedappearance` int(11) DEFAULT NULL,
  `exampassedyear` varchar(45) DEFAULT NULL,
  `exampassedregno` varchar(45) DEFAULT NULL,
  `exampassedresultwithclass` varchar(100) DEFAULT NULL,
  `pumediuminstruction` varchar(45) DEFAULT NULL,
  `subjectsqualifingexampartone` varchar(200) DEFAULT NULL,
  `subjectsqualifingexamparttwo` varchar(500) DEFAULT NULL,
  `subjectsdegreecoursepartone` varchar(200) DEFAULT NULL,
  `subjectsdegreecourseparttwo` varchar(500) DEFAULT NULL,
  `pumarkscard` int(11) DEFAULT NULL,
  `medicalreport` int(11) DEFAULT NULL,
  `incomecertificate` int(11) DEFAULT NULL,
  `migrationcertificate` int(11) DEFAULT NULL,
  `transfercertificate` int(11) DEFAULT NULL,
  `castecertificate` int(11) DEFAULT NULL,
  `proficiencysports` varchar(400) DEFAULT NULL,
  `extracurricular` varchar(400) DEFAULT NULL,
  `areyouemployee` varchar(45) DEFAULT NULL,
  `karnataka` int(11) DEFAULT NULL,
  `userid` int(11) DEFAULT NULL,
  PRIMARY KEY (`iddegreedetails`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `degreedetails`
--

LOCK TABLES `degreedetails` WRITE;
/*!40000 ALTER TABLE `degreedetails` DISABLE KEYS */;
/*!40000 ALTER TABLE `degreedetails` ENABLE KEYS */;
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
  `userid` int(11) DEFAULT NULL,
  PRIMARY KEY (`depid`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `department`
--

LOCK TABLES `department` WRITE;
/*!40000 ALTER TABLE `department` DISABLE KEYS */;
INSERT INTO `department` VALUES (1,'TEACHING',2,0),(2,'NON-TEACHING',2,0);
/*!40000 ALTER TABLE `department` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `diary`
--

DROP TABLE IF EXISTS `diary`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `diary` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `classsec` varchar(45) DEFAULT NULL,
  `academicyear` varchar(45) DEFAULT NULL,
  `subject` varchar(100) DEFAULT NULL,
  `message` varchar(1000) DEFAULT NULL,
  `startdate` date DEFAULT NULL,
  `enddate` date DEFAULT NULL,
  `createddate` date DEFAULT NULL,
  `branchid` int(11) DEFAULT NULL,
  `userid` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `diary`
--

LOCK TABLES `diary` WRITE;
/*!40000 ALTER TABLE `diary` DISABLE KEYS */;
/*!40000 ALTER TABLE `diary` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `enquiry`
--

DROP TABLE IF EXISTS `enquiry`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `enquiry` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(100) DEFAULT NULL,
  `fathername` varchar(100) DEFAULT NULL,
  `mothername` varchar(100) DEFAULT NULL,
  `admissionclass` varchar(45) DEFAULT NULL,
  `address` varchar(200) DEFAULT NULL,
  `mobileno` varchar(45) DEFAULT NULL,
  `siblings` varchar(300) DEFAULT NULL,
  `status` varchar(45) DEFAULT NULL,
  `academicyear` varchar(45) DEFAULT NULL,
  `createddate` date DEFAULT NULL,
  `branchid` int(11) DEFAULT NULL,
  `userid` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `enquiry`
--

LOCK TABLES `enquiry` WRITE;
/*!40000 ALTER TABLE `enquiry` DISABLE KEYS */;
/*!40000 ALTER TABLE `enquiry` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `examrank`
--

DROP TABLE IF EXISTS `examrank`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `examrank` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `sid` int(11) DEFAULT NULL,
  `examid` int(11) DEFAULT NULL,
  `marksobtained` int(11) DEFAULT NULL,
  `status` varchar(20) DEFAULT NULL,
  `rank` int(11) DEFAULT NULL,
  `academicyear` varchar(20) DEFAULT NULL,
  `branchid` int(11) DEFAULT NULL,
  `userid` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `sid` (`sid`),
  KEY `examid` (`examid`),
  CONSTRAINT `examrank_ibfk_1` FOREIGN KEY (`sid`) REFERENCES `student` (`sid`),
  CONSTRAINT `examrank_ibfk_2` FOREIGN KEY (`examid`) REFERENCES `exams` (`exid`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `examrank`
--

LOCK TABLES `examrank` WRITE;
/*!40000 ALTER TABLE `examrank` DISABLE KEYS */;
/*!40000 ALTER TABLE `examrank` ENABLE KEYS */;
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
  `userid` int(11) DEFAULT NULL,
  PRIMARY KEY (`exid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `exams`
--

LOCK TABLES `exams` WRITE;
/*!40000 ALTER TABLE `exams` DISABLE KEYS */;
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
  `userid` int(11) DEFAULT NULL,
  PRIMARY KEY (`idexamschedule`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `examschedule`
--

LOCK TABLES `examschedule` WRITE;
/*!40000 ALTER TABLE `examschedule` DISABLE KEYS */;
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
  `totalfees` decimal(10,0) DEFAULT NULL,
  `paidfees` decimal(10,0) DEFAULT '0',
  `academicyear` varchar(45) DEFAULT NULL,
  `branchid` int(11) DEFAULT NULL,
  `userid` int(11) DEFAULT NULL,
  PRIMARY KEY (`feesstructureid`)
) ENGINE=InnoDB AUTO_INCREMENT=401 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `fee_academicfeesstructure`
--

LOCK TABLES `fee_academicfeesstructure` WRITE;
/*!40000 ALTER TABLE `fee_academicfeesstructure` DISABLE KEYS */;
INSERT INTO `fee_academicfeesstructure` VALUES (1,32,16500,NULL,'2025/26',2,3),(2,64,16500,NULL,'2025/26',2,3),(3,57,16500,NULL,'2025/26',2,3),(4,50,16500,NULL,'2025/26',2,3),(5,25,16500,NULL,'2025/26',2,3),(6,43,16500,NULL,'2025/26',2,3),(7,82,16500,NULL,'2025/26',2,3),(8,75,16500,NULL,'2025/26',2,3),(9,68,16500,NULL,'2025/26',2,3),(10,36,33500,NULL,'2025/26',2,3),(11,61,16500,NULL,'2025/26',2,3),(12,54,16500,NULL,'2025/26',2,3),(13,29,16500,NULL,'2025/26',2,3),(14,47,16500,NULL,'2025/26',2,3),(15,40,16500,NULL,'2025/26',2,3),(16,79,16500,NULL,'2025/26',2,3),(17,72,16500,NULL,'2025/26',2,3),(18,33,16500,NULL,'2025/26',2,3),(19,65,16500,NULL,'2025/26',2,3),(20,58,16500,NULL,'2025/26',2,3),(21,51,16500,NULL,'2025/26',2,3),(22,26,16500,NULL,'2025/26',2,3),(23,44,16500,NULL,'2025/26',2,3),(24,76,16500,NULL,'2025/26',2,3),(25,69,16500,NULL,'2025/26',2,3),(26,37,16500,NULL,'2025/26',2,3),(27,62,16500,NULL,'2025/26',2,3),(28,55,16500,NULL,'2025/26',2,3),(29,30,16500,NULL,'2025/26',2,3),(30,23,16500,NULL,'2025/26',2,3),(31,48,16500,NULL,'2025/26',2,3),(32,41,16500,NULL,'2025/26',2,3),(33,80,16500,NULL,'2025/26',2,3),(34,73,16500,NULL,'2025/26',2,3),(35,34,33500,NULL,'2025/26',2,3),(36,66,16500,NULL,'2025/26',2,3),(37,59,16500,NULL,'2025/26',2,3),(38,52,16500,NULL,'2025/26',2,3),(39,27,16500,NULL,'2025/26',2,3),(40,45,16500,NULL,'2025/26',2,3),(41,77,16500,NULL,'2025/26',2,3),(42,70,16500,NULL,'2025/26',2,3),(43,38,16500,NULL,'2025/26',2,3),(44,31,16500,NULL,'2025/26',2,3),(45,63,16500,NULL,'2025/26',2,3),(46,56,16500,NULL,'2025/26',2,3),(47,49,16500,NULL,'2025/26',2,3),(48,24,16500,NULL,'2025/26',2,3),(49,42,16500,NULL,'2025/26',2,3),(50,81,16500,NULL,'2025/26',2,3),(51,74,16500,NULL,'2025/26',2,3),(52,35,16500,NULL,'2025/26',2,3),(53,67,16500,NULL,'2025/26',2,3),(54,60,16500,NULL,'2025/26',2,3),(55,53,16500,NULL,'2025/26',2,3),(56,28,16500,NULL,'2025/26',2,3),(57,46,16500,NULL,'2025/26',2,3),(58,39,16500,NULL,'2025/26',2,3),(59,78,16500,NULL,'2025/26',2,3),(60,71,16500,NULL,'2025/26',2,3),(61,22,16500,NULL,'2025/26',2,3),(62,12,16500,NULL,'2025/26',2,3),(63,9,16500,NULL,'2025/26',2,3),(64,13,16500,NULL,'2025/26',2,3),(65,14,16500,NULL,'2025/26',2,3),(66,15,16500,NULL,'2025/26',2,3),(67,16,16500,NULL,'2025/26',2,3),(68,17,16500,NULL,'2025/26',2,3),(69,3,16500,NULL,'2025/26',2,3),(70,18,16500,NULL,'2025/26',2,3),(71,21,16500,NULL,'2025/26',2,3),(72,1,16500,NULL,'2025/26',2,3),(73,19,16500,NULL,'2025/26',2,3),(74,7,16500,NULL,'2025/26',2,3),(75,8,16500,NULL,'2025/26',2,3),(76,20,16500,NULL,'2025/26',2,3),(77,4,16500,NULL,'2025/26',2,3),(78,6,16500,NULL,'2025/26',2,3),(79,2,16500,NULL,'2025/26',2,3),(80,5,16500,NULL,'2025/26',2,3),(81,10,16500,NULL,'2025/26',2,3),(82,11,16500,NULL,'2025/26',2,3),(83,110,17000,NULL,'2025/26',2,3),(84,149,17000,NULL,'2025/26',2,3),(85,103,17000,NULL,'2025/26',2,3),(86,96,17000,NULL,'2025/26',2,3),(87,89,17000,NULL,'2025/26',2,3),(88,142,17000,NULL,'2025/26',2,3),(89,135,17000,NULL,'2025/26',2,3),(90,128,17000,NULL,'2025/26',2,3),(91,121,17000,NULL,'2025/26',2,3),(92,114,17000,NULL,'2025/26',2,3),(93,100,17000,NULL,'2025/26',2,3),(94,107,17000,NULL,'2025/26',2,3),(95,146,17000,NULL,'2025/26',2,3),(96,139,17000,NULL,'2025/26',2,3),(97,93,17000,NULL,'2025/26',2,3),(98,132,17000,NULL,'2025/26',2,3),(99,86,17000,NULL,'2025/26',2,3),(100,118,17000,NULL,'2025/26',2,3),(101,125,17000,NULL,'2025/26',2,3),(102,111,17000,NULL,'2025/26',2,3),(103,150,17000,NULL,'2025/26',2,3),(104,104,17000,NULL,'2025/26',2,3),(105,97,17000,NULL,'2025/26',2,3),(106,136,17000,NULL,'2025/26',2,3),(107,90,17000,NULL,'2025/26',2,3),(108,143,17000,NULL,'2025/26',2,3),(109,129,17000,NULL,'2025/26',2,3),(110,83,17000,NULL,'2025/26',2,3),(111,122,17000,NULL,'2025/26',2,3),(112,115,17000,NULL,'2025/26',2,3),(113,108,17000,NULL,'2025/26',2,3),(114,101,17000,NULL,'2025/26',2,3),(115,147,17000,NULL,'2025/26',2,3),(116,94,17000,NULL,'2025/26',2,3),(117,140,17000,NULL,'2025/26',2,3),(118,133,17000,NULL,'2025/26',2,3),(119,87,17000,NULL,'2025/26',2,3),(120,119,17000,NULL,'2025/26',2,3),(121,126,17000,NULL,'2025/26',2,3),(122,112,17000,NULL,'2025/26',2,3),(123,151,17000,NULL,'2025/26',2,3),(124,105,17000,NULL,'2025/26',2,3),(125,98,17000,NULL,'2025/26',2,3),(126,137,17000,NULL,'2025/26',2,3),(127,91,17000,NULL,'2025/26',2,3),(128,144,17000,NULL,'2025/26',2,3),(129,130,17000,NULL,'2025/26',2,3),(130,84,17000,NULL,'2025/26',2,3),(131,123,17000,NULL,'2025/26',2,3),(132,116,17000,NULL,'2025/26',2,3),(133,109,17000,NULL,'2025/26',2,3),(134,148,17000,NULL,'2025/26',2,3),(135,102,17000,NULL,'2025/26',2,3),(136,95,17000,NULL,'2025/26',2,3),(137,141,17000,NULL,'2025/26',2,3),(138,134,17000,NULL,'2025/26',2,3),(139,88,17000,NULL,'2025/26',2,3),(140,127,17000,NULL,'2025/26',2,3),(141,120,17000,NULL,'2025/26',2,3),(142,113,17000,NULL,'2025/26',2,3),(143,99,17000,NULL,'2025/26',2,3),(144,106,17000,NULL,'2025/26',2,3),(145,145,17000,NULL,'2025/26',2,3),(146,138,17000,NULL,'2025/26',2,3),(147,92,17000,NULL,'2025/26',2,3),(148,131,17000,NULL,'2025/26',2,3),(149,85,17000,NULL,'2025/26',2,3),(150,117,17000,NULL,'2025/26',2,3),(151,124,17000,NULL,'2025/26',2,3),(152,183,17500,NULL,'2025/26',2,3),(153,208,17500,NULL,'2025/26',2,3),(154,201,17500,NULL,'2025/26',2,3),(155,176,17500,NULL,'2025/26',2,3),(156,169,17500,NULL,'2025/26',2,3),(157,194,17500,NULL,'2025/26',2,3),(158,162,17500,NULL,'2025/26',2,3),(159,155,17500,NULL,'2025/26',2,3),(160,187,17500,NULL,'2025/26',2,3),(161,205,17500,NULL,'2025/26',2,3),(162,180,17500,NULL,'2025/26',2,3),(163,173,17500,NULL,'2025/26',2,3),(164,166,17500,NULL,'2025/26',2,3),(165,198,17500,NULL,'2025/26',2,3),(166,191,17500,NULL,'2025/26',2,3),(167,159,17500,NULL,'2025/26',2,3),(168,152,17500,NULL,'2025/26',2,3),(169,184,17500,NULL,'2025/26',2,3),(170,209,17500,NULL,'2025/26',2,3),(171,202,17500,NULL,'2025/26',2,3),(172,177,17500,NULL,'2025/26',2,3),(173,170,17500,NULL,'2025/26',2,3),(174,195,17500,NULL,'2025/26',2,3),(175,163,17500,NULL,'2025/26',2,3),(176,156,17500,NULL,'2025/26',2,3),(177,188,17500,NULL,'2025/26',2,3),(178,206,17500,NULL,'2025/26',2,3),(179,181,17500,NULL,'2025/26',2,3),(180,174,17500,NULL,'2025/26',2,3),(181,167,17500,NULL,'2025/26',2,3),(182,199,17500,NULL,'2025/26',2,3),(183,192,17500,NULL,'2025/26',2,3),(184,160,17500,NULL,'2025/26',2,3),(185,153,17500,NULL,'2025/26',2,3),(186,185,17500,NULL,'2025/26',2,3),(187,210,17500,NULL,'2025/26',2,3),(188,203,17500,NULL,'2025/26',2,3),(189,178,17500,NULL,'2025/26',2,3),(190,164,17500,NULL,'2025/26',2,3),(191,196,17500,NULL,'2025/26',2,3),(192,171,17500,NULL,'2025/26',2,3),(193,157,17500,NULL,'2025/26',2,3),(194,189,17500,NULL,'2025/26',2,3),(195,182,17500,NULL,'2025/26',2,3),(196,207,17500,NULL,'2025/26',2,3),(197,200,17500,NULL,'2025/26',2,3),(198,175,17500,NULL,'2025/26',2,3),(199,168,17500,NULL,'2025/26',2,3),(200,193,17500,NULL,'2025/26',2,3),(201,161,17500,NULL,'2025/26',2,3),(202,154,17500,NULL,'2025/26',2,3),(203,186,17500,NULL,'2025/26',2,3),(204,204,17500,NULL,'2025/26',2,3),(205,179,17500,NULL,'2025/26',2,3),(206,165,17500,NULL,'2025/26',2,3),(207,197,17500,NULL,'2025/26',2,3),(208,172,17500,NULL,'2025/26',2,3),(209,158,17500,NULL,'2025/26',2,3),(210,190,17500,NULL,'2025/26',2,3),(211,238,18500,NULL,'2025/26',2,3),(212,231,18500,NULL,'2025/26',2,3),(213,224,18500,NULL,'2025/26',2,3),(214,217,18500,NULL,'2025/26',2,3),(215,242,18500,NULL,'2025/26',2,3),(216,228,18500,NULL,'2025/26',2,3),(217,235,18500,NULL,'2025/26',2,3),(218,221,18500,NULL,'2025/26',2,3),(219,214,18500,NULL,'2025/26',2,3),(220,246,18500,NULL,'2025/26',2,3),(221,239,18500,NULL,'2025/26',2,3),(222,232,18500,NULL,'2025/26',2,3),(223,225,18500,NULL,'2025/26',2,3),(224,218,18500,NULL,'2025/26',2,3),(225,211,18500,NULL,'2025/26',2,3),(226,243,18500,NULL,'2025/26',2,3),(227,236,18500,NULL,'2025/26',2,3),(228,229,18500,NULL,'2025/26',2,3),(229,222,18500,NULL,'2025/26',2,3),(230,215,18500,NULL,'2025/26',2,3),(231,247,18500,NULL,'2025/26',2,3),(232,240,18500,NULL,'2025/26',2,3),(233,233,18500,NULL,'2025/26',2,3),(234,226,18500,NULL,'2025/26',2,3),(235,219,18500,NULL,'2025/26',2,3),(236,212,18500,NULL,'2025/26',2,3),(237,244,18500,NULL,'2025/26',2,3),(238,237,18500,NULL,'2025/26',2,3),(239,230,18500,NULL,'2025/26',2,3),(240,223,18500,NULL,'2025/26',2,3),(241,216,18500,NULL,'2025/26',2,3),(242,241,18500,NULL,'2025/26',2,3),(243,227,18500,NULL,'2025/26',2,3),(244,234,18500,NULL,'2025/26',2,3),(245,220,18500,NULL,'2025/26',2,3),(246,213,18500,NULL,'2025/26',2,3),(247,245,18500,NULL,'2025/26',2,3),(248,275,18700,NULL,'2025/26',2,3),(249,268,18700,NULL,'2025/26',2,3),(250,261,18700,NULL,'2025/26',2,3),(251,254,18700,NULL,'2025/26',2,3),(252,286,18700,NULL,'2025/26',2,3),(253,279,18700,NULL,'2025/26',2,3),(254,272,18700,NULL,'2025/26',2,3),(255,265,18700,NULL,'2025/26',2,3),(256,258,18700,NULL,'2025/26',2,3),(257,251,18700,NULL,'2025/26',2,3),(258,283,18700,NULL,'2025/26',2,3),(259,276,18700,NULL,'2025/26',2,3),(260,269,18700,NULL,'2025/26',2,3),(261,262,18700,NULL,'2025/26',2,3),(262,255,18700,NULL,'2025/26',2,3),(263,248,18700,NULL,'2025/26',2,3),(264,287,18700,NULL,'2025/26',2,3),(265,280,18700,NULL,'2025/26',2,3),(266,273,18700,NULL,'2025/26',2,3),(267,266,18700,NULL,'2025/26',2,3),(268,259,18700,NULL,'2025/26',2,3),(269,252,18700,NULL,'2025/26',2,3),(270,284,18700,NULL,'2025/26',2,3),(271,277,18700,NULL,'2025/26',2,3),(272,270,18700,NULL,'2025/26',2,3),(273,263,18700,NULL,'2025/26',2,3),(274,256,18700,NULL,'2025/26',2,3),(275,249,18700,NULL,'2025/26',2,3),(276,281,18700,NULL,'2025/26',2,3),(277,274,18700,NULL,'2025/26',2,3),(278,267,18700,NULL,'2025/26',2,3),(279,260,18700,NULL,'2025/26',2,3),(280,253,18700,NULL,'2025/26',2,3),(281,285,18700,NULL,'2025/26',2,3),(282,278,18700,NULL,'2025/26',2,3),(283,271,18700,NULL,'2025/26',2,3),(284,264,18700,NULL,'2025/26',2,3),(285,257,18700,NULL,'2025/26',2,3),(286,250,18700,NULL,'2025/26',2,3),(287,282,18700,NULL,'2025/26',2,3),(288,315,18900,NULL,'2025/26',2,3),(289,308,18900,NULL,'2025/26',2,3),(290,301,18900,NULL,'2025/26',2,3),(291,294,18900,NULL,'2025/26',2,3),(292,326,18900,NULL,'2025/26',2,3),(293,319,18900,NULL,'2025/26',2,3),(294,312,18900,NULL,'2025/26',2,3),(295,305,18900,NULL,'2025/26',2,3),(296,298,18900,NULL,'2025/26',2,3),(297,291,18900,NULL,'2025/26',2,3),(298,323,18900,NULL,'2025/26',2,3),(299,316,18900,NULL,'2025/26',2,3),(300,309,18900,NULL,'2025/26',2,3),(301,302,18900,NULL,'2025/26',2,3),(302,295,18900,NULL,'2025/26',2,3),(303,288,18900,NULL,'2025/26',2,3),(304,327,18900,NULL,'2025/26',2,3),(305,320,18900,NULL,'2025/26',2,3),(306,313,18900,NULL,'2025/26',2,3),(307,306,18900,NULL,'2025/26',2,3),(308,299,18900,NULL,'2025/26',2,3),(309,292,18900,NULL,'2025/26',2,3),(310,324,18900,NULL,'2025/26',2,3),(311,317,18900,NULL,'2025/26',2,3),(312,310,18900,NULL,'2025/26',2,3),(313,303,18900,NULL,'2025/26',2,3),(314,296,18900,NULL,'2025/26',2,3),(315,289,18900,NULL,'2025/26',2,3),(316,321,18900,NULL,'2025/26',2,3),(317,314,18900,NULL,'2025/26',2,3),(318,307,18900,NULL,'2025/26',2,3),(319,300,18900,NULL,'2025/26',2,3),(320,293,18900,NULL,'2025/26',2,3),(321,325,18900,NULL,'2025/26',2,3),(322,318,18900,NULL,'2025/26',2,3),(323,311,18900,NULL,'2025/26',2,3),(324,304,18900,NULL,'2025/26',2,3),(325,297,18900,NULL,'2025/26',2,3),(326,290,18900,NULL,'2025/26',2,3),(327,322,18900,NULL,'2025/26',2,3),(328,328,15000,NULL,'2025/26',2,331),(329,329,15000,NULL,'2025/26',2,331),(330,330,15000,NULL,'2025/26',2,331),(331,331,15000,NULL,'2025/26',2,331),(332,333,15000,NULL,'2025/26',2,331),(333,335,15000,NULL,'2025/26',2,2),(334,336,15000,NULL,'2025/26',2,2),(335,337,15000,NULL,'2025/26',2,2),(336,334,15000,NULL,'2025/26',2,2),(337,332,15000,NULL,'2025/26',2,2),(338,338,15000,NULL,'2025/26',2,2),(339,339,16500,NULL,'2025/26',2,2),(340,340,15000,NULL,'2025/26',2,2),(341,341,15000,NULL,'2025/26',2,2),(342,342,15000,NULL,'2025/26',2,2),(343,343,15000,NULL,'2025/26',2,2),(344,344,15000,NULL,'2025/26',2,2),(345,345,15000,NULL,'2025/26',2,2),(346,346,15000,NULL,'2025/26',2,2),(347,347,15000,NULL,'2025/26',2,2),(348,348,15000,NULL,'2025/26',2,2),(349,349,15000,NULL,'2025/26',2,2),(350,350,16500,NULL,'2025/26',2,2),(351,351,15000,NULL,'2025/26',2,2),(352,352,18900,NULL,'2025/26',2,2),(353,353,15000,NULL,'2025/26',2,2),(354,354,15000,NULL,'2025/26',2,2),(355,355,15000,NULL,'2025/26',2,2),(356,356,16500,NULL,'2025/26',2,2),(357,357,15000,NULL,'2025/26',2,2),(358,358,15000,NULL,'2025/26',2,2),(359,359,16500,NULL,'2025/26',2,2),(360,360,15000,NULL,'2025/26',2,2),(361,361,15000,NULL,'2025/26',2,2),(362,362,16500,NULL,'2025/26',2,2),(363,363,15000,NULL,'2025/26',2,2),(364,364,15000,NULL,'2025/26',2,2),(365,365,15000,NULL,'2025/26',2,2),(366,366,15000,NULL,'2025/26',2,2),(367,367,15000,NULL,'2025/26',2,2),(368,368,15000,NULL,'2025/26',2,2),(369,370,17500,NULL,'2025/26',2,2),(370,371,15000,NULL,'2025/26',2,2),(371,372,15000,NULL,'2025/26',2,2),(372,369,15000,NULL,'2025/26',2,2),(373,373,15000,NULL,'2025/26',2,2),(374,375,15000,NULL,'2025/26',2,2),(375,376,15000,NULL,'2025/26',2,2),(376,378,15000,NULL,'2025/26',2,2),(377,381,15000,NULL,'2025/26',2,2),(378,383,31500,NULL,'2025/26',2,2),(379,386,18500,NULL,'2025/26',2,2),(380,387,15000,NULL,'2025/26',2,2),(381,388,15000,NULL,'2025/26',2,2),(382,389,27500,NULL,'2025/26',2,2),(383,390,15000,NULL,'2025/26',2,2),(384,391,15000,NULL,'2025/26',2,2),(385,374,15000,NULL,'2025/26',2,2),(386,377,15000,NULL,'2025/26',2,2),(387,384,15000,NULL,'2025/26',2,2),(388,380,15000,NULL,'2025/26',2,2),(389,382,15000,NULL,'2025/26',2,2),(390,385,15000,NULL,'2025/26',2,2),(391,392,15000,NULL,'2025/26',2,2),(392,393,18900,NULL,'2025/26',2,2),(393,394,17000,NULL,'2025/26',2,2),(394,395,15000,NULL,'2025/26',2,2),(395,396,15000,NULL,'2025/26',2,2),(396,397,15000,NULL,'2025/26',2,2),(397,398,18900,NULL,'2025/26',2,2),(398,379,18900,NULL,'2025/26',2,2),(399,399,15000,NULL,'2025/26',2,2),(400,400,15000,NULL,'2025/26',2,2);
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
  `userid` int(11) DEFAULT NULL,
  `academicyear` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`idfeescategory`)
) ENGINE=InnoDB AUTO_INCREMENT=22 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `fee_feescategory`
--

LOCK TABLES `fee_feescategory` WRITE;
/*!40000 ALTER TABLE `fee_feescategory` DISABLE KEYS */;
INSERT INTO `fee_feescategory` VALUES (1,'I Term',5500,'L.K.G--',2,3,'2025/26'),(2,'II Term',4750,'L.K.G--',2,3,'2025/26'),(3,'III Term',4750,'L.K.G--',2,3,'2025/26'),(4,'I Term',6000,'U.K.G--',2,3,'2025/26'),(5,'II Term',5250,'U.K.G--',2,3,'2025/26'),(6,'II Term',5250,'I--',2,3,'2025/26'),(7,'II Term',5250,'II--',2,3,'2025/26'),(8,'III Term',5250,'U.K.G--',2,3,'2025/26'),(9,'III Term',5250,'I--',2,3,'2025/26'),(10,'III Term',5250,'II--',2,3,'2025/26'),(11,'I Term',6500,'I--',2,3,'2025/26'),(12,'I Term',7000,'II--',2,3,'2025/26'),(13,'I Term',7000,'III--',2,3,'2025/26'),(14,'I Term',7000,'IV--',2,3,'2025/26'),(15,'I Term',7000,'V--',2,3,'2025/26'),(16,'II Term',5750,'III--',2,3,'2025/26'),(17,'III Term',5750,'III--',2,3,'2025/26'),(18,'II Term',5850,'IV--',2,3,'2025/26'),(19,'III Term',5850,'IV--',2,3,'2025/26'),(20,'II Term',5950,'V--',2,3,'2025/26'),(21,'III Term',5950,'V--',2,3,'2025/26');
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
  `userid` int(11) DEFAULT NULL,
  PRIMARY KEY (`feecollectionid`),
  KEY `sid_idx` (`sid`),
  KEY `sfsid_idx` (`sfsid`),
  KEY `receiptnumber_idx` (`receiptnumber`),
  CONSTRAINT `fk` FOREIGN KEY (`sid`) REFERENCES `student` (`sid`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `receiptnumber` FOREIGN KEY (`receiptnumber`) REFERENCES `fee_receiptinfo` (`receiptnumber`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `sfsid` FOREIGN KEY (`sfsid`) REFERENCES `fee_studentfeesstructure` (`sfsid`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=354 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `fee_feescollection`
--

LOCK TABLES `fee_feescollection` WRITE;
/*!40000 ALTER TABLE `fee_feescollection` DISABLE KEYS */;
INSERT INTO `fee_feescollection` VALUES (1,417,6500,88,0,'2025-05-11','2025/26',1,2,331),(2,1009,3000,328,0,'2025-05-16','2025/26',2,2,2),(3,1012,5500,329,0,'2025-05-16','2025/26',3,2,2),(4,1013,4750,329,0,'2025-05-16','2025/26',3,2,2),(5,1014,4750,329,0,'2025-05-16','2025/26',3,2,2),(6,1015,5500,330,0,'2025-05-18','2025/26',4,2,2),(7,1016,4500,330,0,'2025-05-18','2025/26',4,2,2),(8,1036,3000,342,0,'2025-05-18','2025/26',5,2,2),(9,1021,5500,332,0,'2025-05-18','2025/26',6,2,2),(10,1022,4750,332,0,'2025-05-18','2025/26',6,2,2),(11,1023,4750,332,0,'2025-05-18','2025/26',6,2,2),(12,1018,5000,331,0,'2025-05-18','2025/26',7,2,2),(13,994,5500,333,0,'2025-05-18','2025/26',8,2,2),(14,1006,5500,334,0,'2025-05-18','2025/26',9,2,2),(15,997,5500,335,0,'2025-05-18','2025/26',10,2,2),(16,1003,5500,337,0,'2025-05-18','2025/26',11,2,2),(17,1024,5500,338,0,'2025-05-18','2025/26',12,2,2),(18,1030,5500,340,0,'2025-05-18','2025/26',13,2,2),(19,1033,1000,341,0,'2025-05-18','2025/26',14,2,2),(20,1039,5500,343,0,'2025-05-18','2025/26',15,2,2),(21,1042,3500,344,0,'2025-05-18','2025/26',16,2,2),(22,1057,5500,349,0,'2025-05-18','2025/26',17,2,2),(23,1045,5500,345,0,'2025-05-18','2025/26',18,2,2),(24,1046,4750,345,0,'2025-05-18','2025/26',18,2,2),(25,1047,4750,345,0,'2025-05-18','2025/26',18,2,2),(26,1048,5500,346,0,'2025-05-18','2025/26',19,2,2),(27,1051,2500,347,0,'2025-05-18','2025/26',20,2,2),(28,1054,5500,348,0,'2025-05-18','2025/26',21,2,2),(29,1063,5500,351,0,'2025-05-18','2025/26',22,2,2),(30,187,3000,9,0,'2025-05-18','2025/26',23,2,2),(31,46,6000,79,0,'2025-05-18','2025/26',24,2,2),(32,184,6000,12,0,'2025-05-18','2025/26',25,2,2),(33,1027,6000,339,0,'2025-05-18','2025/26',26,2,2),(34,1028,4000,339,0,'2025-05-18','2025/26',26,2,2),(35,169,6000,46,0,'2025-05-18','2025/26',27,2,2),(36,199,6000,16,0,'2025-05-18','2025/26',28,2,2),(37,31,2000,61,0,'2025-05-18','2025/26',29,2,2),(38,148,6000,81,0,'2025-05-18','2025/26',30,2,2),(39,196,6000,15,0,'2025-05-18','2025/26',31,2,2),(40,193,6000,14,0,'2025-05-18','2025/26',32,2,2),(41,1060,3000,350,0,'2025-05-18','2025/26',33,2,2),(42,100,5000,73,0,'2025-05-18','2025/26',34,2,2),(43,49,6000,72,0,'2025-05-18','2025/26',35,2,2),(44,58,6000,58,0,'2025-05-18','2025/26',36,2,2),(45,76,3000,37,0,'2025-05-18','2025/26',37,2,2),(46,226,3000,20,0,'2025-05-18','2025/26',38,2,2),(47,223,2000,8,0,'2025-05-18','2025/26',39,2,2),(48,342,6500,101,0,'2025-05-18','2025/26',40,2,2),(49,264,6500,142,0,'2025-05-18','2025/26',41,2,2),(50,433,5250,145,0,'2025-05-18','2025/26',42,2,2),(51,434,5250,145,0,'2025-05-18','2025/26',42,2,2),(52,435,6500,145,0,'2025-05-18','2025/26',42,2,2),(53,528,3000,156,0,'2025-05-18','2025/26',43,2,2),(54,504,2500,152,0,'2025-05-18','2025/26',44,2,2),(55,582,7000,189,0,'2025-05-18','2025/26',45,2,2),(56,715,5000,230,0,'2025-05-18','2025/26',46,2,2),(57,697,7000,233,0,'2025-05-18','2025/26',47,2,2),(58,730,2500,234,0,'2025-05-18','2025/26',48,2,2),(59,814,7000,270,0,'2025-05-18','2025/26',49,2,2),(60,748,1000,261,0,'2025-05-18','2025/26',50,2,2),(61,754,7000,286,0,'2025-05-18','2025/26',51,2,2),(62,755,5850,286,0,'2025-05-18','2025/26',51,2,2),(63,756,5850,286,0,'2025-05-18','2025/26',51,2,2),(64,892,2100,323,0,'2025-05-18','2025/26',52,2,2),(65,907,1000,288,0,'2025-05-18','2025/26',53,2,2),(66,522,7000,195,0,'2025-05-18','2025/26',54,2,2),(67,1069,5500,353,0,'2025-05-21','2025/26',55,2,2),(68,1072,5500,354,0,'2025-05-21','2025/26',56,2,2),(69,1075,5500,355,0,'2025-05-21','2025/26',57,2,2),(70,1081,5500,357,0,'2025-05-21','2025/26',58,2,2),(71,1,6000,32,0,'2025-05-21','2025/26',59,2,2),(72,1078,6000,356,0,'2025-05-21','2025/26',60,2,2),(73,10,6000,50,0,'2025-05-21','2025/26',61,2,2),(74,166,6000,28,0,'2025-05-21','2025/26',62,2,2),(75,167,500,28,0,'2025-05-21','2025/26',62,2,2),(76,408,6500,95,0,'2025-05-21','2025/26',63,2,2),(77,261,6500,89,0,'2025-05-21','2025/26',64,2,2),(78,1086,6500,36,0,'2025-05-21','2025/26',65,2,2),(79,456,7000,183,0,'2025-05-21','2025/26',66,2,2),(80,534,7000,206,0,'2025-05-21','2025/26',67,2,2),(81,456,1000,183,0,'2025-05-21','2025/26',68,2,2),(82,603,7000,161,0,'2025-05-21','2025/26',69,2,2),(83,733,6000,220,0,'2025-05-21','2025/26',70,2,2),(84,829,5000,274,0,'2025-05-21','2025/26',71,2,2),(85,1066,3000,352,0,'2025-05-21','2025/26',72,2,2),(86,970,7000,304,0,'2025-05-21','2025/26',73,2,2),(87,964,3000,318,0,'2025-05-21','2025/26',74,2,2),(88,1093,3500,360,0,'2025-05-21','2025/26',75,2,2),(89,1090,6000,359,0,'2025-05-21','2025/26',76,2,2),(90,1096,5500,361,0,'2025-05-21','2025/26',77,2,2),(91,1087,5500,358,0,'2025-05-21','2025/26',78,2,2),(92,555,6000,153,0,'2025-05-21','2025/26',79,2,2),(93,85,6000,30,0,'2025-05-21','2025/26',80,2,2),(94,318,2000,136,0,'2025-05-21','2025/26',81,2,2),(95,166,5500,28,0,'2025-05-22','2025/26',82,2,2),(96,420,6500,127,0,'2025-05-22','2025/26',83,2,2),(97,555,7000,153,0,'2025-05-22','2025/26',84,2,2),(98,1099,3000,362,0,'2025-05-22','2025/26',85,2,2),(99,325,5250,129,0,'2025-05-23','2025/26',86,2,2),(100,326,5250,129,0,'2025-05-23','2025/26',86,2,2),(101,327,6500,129,0,'2025-05-23','2025/26',86,2,2),(102,760,7000,272,0,'2025-05-23','2025/26',87,2,2),(103,13,6000,25,0,'2025-05-27','2025/26',88,2,2),(104,229,5000,4,0,'2025-05-27','2025/26',89,2,2),(105,220,6000,7,0,'2025-05-27','2025/26',90,2,2),(106,127,6000,38,0,'2025-05-27','2025/26',91,2,2),(107,354,6500,133,0,'2025-05-27','2025/26',92,2,2),(108,276,3500,114,0,'2025-05-27','2025/26',93,2,2),(109,453,5500,124,0,'2025-05-27','2025/26',94,2,2),(110,390,5000,84,0,'2025-05-27','2025/26',95,2,2),(111,333,6500,122,0,'2025-05-27','2025/26',96,2,2),(112,480,5000,187,0,'2025-05-27','2025/26',97,2,2),(113,591,1000,200,0,'2025-05-27','2025/26',98,2,2),(114,727,5000,227,0,'2025-05-27','2025/26',99,2,2),(115,853,7000,257,0,'2025-05-27','2025/26',100,2,2),(116,854,1000,257,0,'2025-05-27','2025/26',100,2,2),(117,772,7000,283,0,'2025-05-27','2025/26',101,2,2),(118,773,2000,283,0,'2025-05-27','2025/26',101,2,2),(119,886,2000,298,0,'2025-05-27','2025/26',102,2,2),(120,889,5000,291,0,'2025-05-27','2025/26',103,2,2),(121,88,5000,23,0,'2025-05-27','2025/26',104,2,2),(122,1108,5000,365,0,'2025-05-27','2025/26',105,2,2),(123,1105,5500,364,0,'2025-05-27','2025/26',106,2,2),(124,1102,5000,363,0,'2025-05-27','2025/26',107,2,2),(125,775,5000,276,0,'2025-05-27','2025/26',108,2,2),(126,73,6000,69,0,'2025-05-27','2025/26',109,2,2),(127,688,1900,215,0,'2025-05-27','2025/26',110,2,2),(128,916,1900,313,0,'2025-05-27','2025/26',111,2,2),(129,1111,5500,366,0,'2025-05-27','2025/26',112,2,2),(130,1114,3000,367,0,'2025-05-27','2025/26',113,2,2),(131,573,7000,196,0,'2025-05-27','2025/26',114,2,2),(132,718,7000,223,0,'2025-05-27','2025/26',115,2,2),(133,679,2000,236,0,'2025-05-27','2025/26',116,2,2),(134,742,3000,275,0,'2025-05-27','2025/26',117,2,2),(135,1126,3000,372,0,'2025-05-28','2025/26',118,2,2),(136,1117,5000,368,0,'2025-05-28','2025/26',119,2,2),(137,1129,5000,369,0,'2025-05-28','2025/26',120,2,2),(138,1123,5500,371,0,'2025-05-28','2025/26',121,2,2),(139,537,7000,181,0,'2025-05-28','2025/26',122,2,2),(140,612,7000,204,0,'2025-05-28','2025/26',123,2,2),(141,501,3000,159,0,'2025-05-28','2025/26',124,2,2),(142,1122,7000,370,0,'2025-05-28','2025/26',125,2,2),(143,630,1550,190,0,'2025-05-28','2025/26',126,2,2),(144,691,7000,247,0,'2025-05-28','2025/26',127,2,2),(145,808,7000,284,0,'2025-05-28','2025/26',128,2,2),(146,898,4000,309,0,'2025-05-28','2025/26',129,2,2),(147,958,1000,293,0,'2025-05-28','2025/26',130,2,2),(148,922,7000,299,0,'2025-05-28','2025/26',131,2,2),(149,901,1500,302,0,'2025-05-28','2025/26',132,2,2),(150,372,6500,105,0,'2025-05-28','2025/26',133,2,2),(151,603,2000,161,0,'2025-06-03','2025/26',134,2,2),(152,1165,5500,391,0,'2025-06-03','2025/26',135,2,2),(153,1132,5500,373,0,'2025-06-03','2025/26',136,2,2),(154,1135,2500,375,0,'2025-06-03','2025/26',137,2,2),(155,1138,5500,376,0,'2025-06-03','2025/26',138,2,2),(156,1171,5500,377,0,'2025-06-03','2025/26',139,2,2),(157,1141,5500,378,0,'2025-06-03','2025/26',140,2,2),(158,1174,3000,384,0,'2025-06-03','2025/26',141,2,2),(159,1144,5500,381,0,'2025-06-03','2025/26',142,2,2),(160,1177,3500,380,0,'2025-06-03','2025/26',143,2,2),(161,1180,2500,382,0,'2025-06-03','2025/26',144,2,2),(162,1153,5500,387,0,'2025-06-03','2025/26',145,2,2),(163,1183,1000,385,0,'2025-06-03','2025/26',146,2,2),(164,1156,3000,388,0,'2025-06-03','2025/26',147,2,2),(165,1186,5000,383,0,'2025-06-03','2025/26',148,2,2),(166,1168,5500,374,0,'2025-06-03','2025/26',149,2,2),(167,178,6000,71,0,'2025-06-03','2025/26',150,2,2),(168,179,5250,71,0,'2025-06-03','2025/26',150,2,2),(169,180,5250,71,0,'2025-06-03','2025/26',150,2,2),(170,40,6000,47,0,'2025-06-03','2025/26',151,2,2),(171,130,6000,31,0,'2025-06-03','2025/26',152,2,2),(172,115,6000,27,0,'2025-06-03','2025/26',153,2,2),(173,214,5000,1,0,'2025-06-03','2025/26',154,2,2),(174,208,6000,18,0,'2025-06-03','2025/26',155,2,2),(175,4,6000,64,0,'2025-06-03','2025/26',156,2,2),(176,211,6000,21,0,'2025-06-03','2025/26',157,2,2),(177,142,6000,24,0,'2025-06-03','2025/26',158,2,2),(178,124,5000,70,0,'2025-06-03','2025/26',159,2,2),(179,244,6000,11,0,'2025-06-03','2025/26',160,2,2),(180,232,6000,6,0,'2025-06-03','2025/26',161,2,2),(181,190,6000,13,0,'2025-06-03','2025/26',162,2,2),(182,7,6000,57,0,'2025-06-03','2025/26',163,2,2),(183,37,3000,29,0,'2025-06-03','2025/26',164,2,2),(184,109,6000,59,0,'2025-06-03','2025/26',165,2,2),(185,118,6000,45,0,'2025-06-03','2025/26',166,2,2),(186,106,6000,66,0,'2025-06-03','2025/26',167,2,2),(187,67,6000,44,0,'2025-06-03','2025/26',168,2,2),(188,91,4000,48,0,'2025-06-03','2025/26',169,2,2),(189,205,5000,3,0,'2025-06-03','2025/26',170,2,2),(190,145,2000,42,0,'2025-06-03','2025/26',171,2,2),(191,79,6000,62,0,'2025-06-03','2025/26',172,2,2),(192,64,6000,26,0,'2025-06-03','2025/26',173,2,2),(193,19,3000,82,0,'2025-06-03','2025/26',174,2,2),(194,97,6000,80,0,'2025-06-03','2025/26',175,2,2),(195,16,6000,43,0,'2025-06-03','2025/26',176,2,2),(196,82,6000,55,0,'2025-06-03','2025/26',177,2,2),(197,94,6000,41,0,'2025-06-03','2025/26',178,2,2),(198,172,6000,39,0,'2025-06-03','2025/26',179,2,2),(199,70,1000,76,0,'2025-06-03','2025/26',180,2,2),(200,309,6500,150,0,'2025-06-03','2025/26',181,2,2),(201,380,3500,91,0,'2025-06-03','2025/26',182,2,2),(202,381,6500,91,0,'2025-06-03','2025/26',182,2,2),(203,273,5500,121,0,'2025-06-03','2025/26',183,2,2),(204,258,3000,96,0,'2025-06-03','2025/26',184,2,2),(205,396,1000,116,0,'2025-06-03','2025/26',185,2,2),(206,270,1500,128,0,'2025-06-03','2025/26',186,2,2),(207,282,1500,107,0,'2025-06-03','2025/26',187,2,2),(208,288,3000,139,0,'2025-06-03','2025/26',188,2,2),(209,447,6500,85,0,'2025-06-03','2025/26',189,2,2),(210,1191,6500,34,0,'2025-06-03','2025/26',190,2,2),(211,399,1000,109,0,'2025-06-03','2025/26',191,2,2),(212,387,5000,130,0,'2025-06-03','2025/26',192,2,2),(213,252,6500,149,0,'2025-06-03','2025/26',193,2,2),(214,338,3500,108,0,'2025-06-03','2025/26',194,2,2),(215,339,6500,108,0,'2025-06-03','2025/26',194,2,2),(216,1194,2000,389,0,'2025-06-03','2025/26',195,2,2),(217,450,550,117,0,'2025-06-03','2025/26',196,2,2),(218,531,7000,188,0,'2025-06-03','2025/26',197,2,2),(219,558,5700,185,0,'2025-06-03','2025/26',198,2,2),(220,486,5000,180,0,'2025-06-03','2025/26',199,2,2),(221,516,4000,177,0,'2025-06-03','2025/26',200,2,2),(222,477,7000,155,0,'2025-06-03','2025/26',202,2,2),(223,519,7000,170,0,'2025-06-03','2025/26',203,2,2),(224,474,5000,162,0,'2025-06-03','2025/26',204,2,2),(225,600,5000,193,0,'2025-06-03','2025/26',205,2,2),(226,579,5000,157,0,'2025-06-03','2025/26',206,2,2),(227,507,7000,184,0,'2025-06-03','2025/26',207,2,2),(228,618,3000,165,0,'2025-06-03','2025/26',208,2,2),(229,543,1000,167,0,'2025-06-03','2025/26',209,2,2),(230,495,3000,198,0,'2025-06-03','2025/26',210,2,2),(231,585,5000,182,0,'2025-06-03','2025/26',211,2,2),(232,621,3000,197,0,'2025-06-03','2025/26',212,2,2),(233,462,1000,201,0,'2025-06-03','2025/26',213,2,2),(234,513,7000,202,0,'2025-06-03','2025/26',214,2,2),(235,540,4000,174,0,'2025-06-03','2025/26',215,2,2),(236,609,2500,186,0,'2025-06-03','2025/26',216,2,2),(237,471,1200,194,0,'2025-06-03','2025/26',217,2,2),(238,606,5000,154,0,'2025-06-03','2025/26',218,2,2),(239,624,3000,172,0,'2025-06-03','2025/26',219,2,2),(240,546,7000,199,0,'2025-06-03','2025/26',220,2,2),(241,552,2500,160,0,'2025-06-03','2025/26',221,2,2),(242,564,5000,203,0,'2025-06-03','2025/26',222,2,2),(243,627,1500,158,0,'2025-06-03','2025/26',223,2,2),(244,468,1900,169,0,'2025-06-03','2025/26',224,2,2),(245,576,7000,171,0,'2025-06-03','2025/26',225,2,2),(246,721,7000,216,0,'2025-06-04','2025/26',226,2,2),(247,649,7000,235,0,'2025-06-04','2025/26',227,2,2),(248,652,7000,221,0,'2025-06-04','2025/26',228,2,2),(249,670,4500,218,0,'2025-06-04','2025/26',229,2,2),(250,694,7000,240,0,'2025-06-04','2025/26',230,2,2),(251,712,3000,237,0,'2025-06-04','2025/26',231,2,2),(252,685,1500,222,0,'2025-06-04','2025/26',232,2,2),(253,703,2000,219,0,'2025-06-04','2025/26',233,2,2),(254,700,7000,226,0,'2025-06-04','2025/26',234,2,2),(255,664,4500,232,0,'2025-06-04','2025/26',235,2,2),(256,673,1750,211,0,'2025-06-04','2025/26',236,2,2),(257,661,7000,239,0,'2025-06-04','2025/26',237,2,2),(258,662,1500,239,0,'2025-06-04','2025/26',238,2,2),(259,640,7000,217,0,'2025-06-04','2025/26',239,2,2),(260,706,3000,212,0,'2025-06-04','2025/26',240,2,2),(261,1150,2000,386,0,'2025-06-04','2025/26',241,2,2),(262,739,3000,245,0,'2025-06-04','2025/26',242,2,2),(263,655,2000,214,0,'2025-06-04','2025/26',243,2,2),(264,676,6500,243,0,'2025-06-04','2025/26',244,2,2),(265,634,2750,231,0,'2025-06-04','2025/26',245,2,2),(266,682,7000,229,0,'2025-06-04','2025/26',246,2,2),(267,820,4000,256,0,'2025-06-04','2025/26',247,2,2),(268,793,5700,280,0,'2025-06-04','2025/26',248,2,2),(269,751,7000,254,0,'2025-06-04','2025/26',249,2,2),(270,757,5000,279,0,'2025-06-04','2025/26',250,2,2),(271,802,550,259,0,'2025-06-04','2025/26',251,2,2),(272,763,1000,265,0,'2025-06-04','2025/26',252,2,2),(273,826,1000,281,0,'2025-06-04','2025/26',253,2,2),(274,832,2000,267,0,'2025-06-04','2025/26',254,2,2),(275,859,7000,282,0,'2025-06-04','2025/26',255,2,2),(276,817,1000,263,0,'2025-06-04','2025/26',256,2,2),(277,844,5000,278,0,'2025-06-04','2025/26',257,2,2),(278,778,3000,269,0,'2025-06-04','2025/26',258,2,2),(279,823,4000,249,0,'2025-06-04','2025/26',259,2,2),(280,769,7000,251,0,'2025-06-04','2025/26',260,2,2),(281,847,1700,271,0,'2025-06-04','2025/26',261,2,2),(282,838,7000,253,0,'2025-06-04','2025/26',262,2,2),(283,781,2000,262,0,'2025-06-04','2025/26',263,2,2),(284,931,4000,317,0,'2025-06-05','2025/26',264,2,2),(285,865,3000,308,0,'2025-06-05','2025/26',265,2,2),(286,862,3000,315,0,'2025-06-05','2025/26',266,2,2),(287,949,7000,314,0,'2025-06-05','2025/26',267,2,2),(288,895,5000,316,0,'2025-06-05','2025/26',268,2,2),(289,943,7000,289,0,'2025-06-05','2025/26',269,2,2),(290,944,5950,289,0,'2025-06-05','2025/26',269,2,2),(291,945,2050,289,0,'2025-06-05','2025/26',269,2,2),(292,871,1000,294,0,'2025-06-05','2025/26',270,2,2),(293,928,7000,324,0,'2025-06-05','2025/26',271,2,2),(294,910,7000,327,0,'2025-06-09','2025/26',272,2,2),(295,868,7000,301,0,'2025-06-09','2025/26',273,2,2),(296,925,3000,292,0,'2025-06-09','2025/26',274,2,2),(297,877,2000,319,0,'2025-06-09','2025/26',275,2,2),(298,874,1000,326,0,'2025-06-09','2025/26',276,2,2),(299,979,7000,322,0,'2025-06-09','2025/26',277,2,2),(300,919,2000,306,0,'2025-06-09','2025/26',278,2,2),(301,955,5000,300,0,'2025-06-09','2025/26',279,2,2),(302,967,2000,311,0,'2025-06-09','2025/26',280,2,2),(303,1162,5500,390,0,'2025-06-10','2025/26',281,2,2),(304,100,1000,73,0,'2025-06-10','2025/26',282,2,2),(305,101,4000,73,0,'2025-06-10','2025/26',282,2,2),(306,52,3000,33,0,'2025-06-10','2025/26',283,2,2),(307,151,1000,74,0,'2025-06-10','2025/26',284,2,2),(308,22,2000,75,0,'2025-06-10','2025/26',285,2,2),(309,133,6000,63,0,'2025-06-10','2025/26',286,2,2),(310,348,2000,94,0,'2025-06-10','2025/26',287,2,2),(311,444,1050,131,0,'2025-06-10','2025/26',288,2,2),(312,438,2750,138,0,'2025-06-10','2025/26',289,2,2),(313,378,2500,137,0,'2025-06-10','2025/26',290,2,2),(314,402,6500,148,0,'2025-06-10','2025/26',291,2,2),(315,489,7000,173,0,'2025-06-10','2025/26',292,2,2),(316,588,7000,207,0,'2025-06-10','2025/26',293,2,2),(317,549,2000,192,0,'2025-06-10','2025/26',294,2,2),(318,483,6000,205,0,'2025-06-10','2025/26',295,2,2),(319,724,7000,241,0,'2025-06-10','2025/26',296,2,2),(320,646,2000,228,0,'2025-06-10','2025/26',297,2,2),(321,745,4000,268,0,'2025-06-10','2025/26',298,2,2),(322,799,1000,266,0,'2025-06-10','2025/26',299,2,2),(323,946,2000,321,0,'2025-06-10','2025/26',300,2,2),(324,1195,3000,392,0,'2025-06-10','2025/26',301,2,2),(325,1204,3000,395,0,'2025-06-10','2025/26',302,2,2),(326,1207,3000,396,0,'2025-06-10','2025/26',303,2,2),(327,1203,5000,394,0,'2025-06-10','2025/26',304,2,2),(328,1198,5000,393,0,'2025-06-10','2025/26',305,2,2),(329,1213,4000,398,0,'2025-06-10','2025/26',306,2,2),(330,1216,4000,379,0,'2025-06-10','2025/26',307,2,2),(331,1210,5500,397,0,'2025-06-10','2025/26',308,2,2),(332,1219,2000,399,0,'2025-06-10','2025/26',309,2,2),(333,25,6000,68,0,'2025-06-10','2025/26',310,2,2),(334,217,6000,19,0,NULL,'2025/26',311,2,2),(335,217,6000,19,0,'2025-06-10','2025/26',312,2,2),(336,139,5000,49,0,'2025-06-10','2025/26',313,2,2),(337,34,6000,54,0,'2025-06-10','2025/26',314,2,2),(338,202,6000,17,0,'2025-06-10','2025/26',315,2,2),(339,324,6000,143,0,'2025-06-10','2025/26',316,2,2),(340,459,7000,208,0,'2025-06-10','2025/26',317,2,2),(341,736,4000,213,0,'2025-06-10','2025/26',318,2,2),(342,643,2500,242,0,'2025-06-10','2025/26',319,2,2),(343,631,3000,238,0,'2025-06-10','2025/26',320,2,2),(344,784,7000,255,0,'2025-06-10','2025/26',321,2,2),(345,785,3000,255,0,'2025-06-10','2025/26',321,2,2),(346,856,7000,250,0,'2025-06-10','2025/26',322,2,2),(347,841,7000,285,0,'2025-06-10','2025/26',323,2,2),(348,880,7000,312,0,'2025-06-10','2025/26',324,2,2),(349,567,7000,178,0,'2025-06-10','2025/26',325,2,2),(350,597,5000,168,0,'2025-06-10','2025/26',326,2,2),(351,847,5000,271,0,'2025-06-10','2025/26',327,2,2),(352,1009,2000,328,0,'2025-06-10','2025/26',328,2,2),(353,1183,1000,385,0,'2025-06-10','2025/26',329,2,2);
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
  `userid` int(11) DEFAULT NULL,
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
  `cancelreceipt` int(11) DEFAULT '0',
  `branchreceiptnumber` varchar(20) DEFAULT NULL,
  `paymenttype` varchar(100) DEFAULT NULL,
  `userid` int(11) DEFAULT NULL,
  `classsec` varchar(20) DEFAULT NULL,
  `receiptvoucher` int(11) DEFAULT NULL,
  `journalvoucher` int(11) DEFAULT NULL,
  `fine` decimal(10,0) DEFAULT NULL,
  `misc` decimal(10,0) DEFAULT NULL,
  PRIMARY KEY (`receiptnumber`),
  KEY `studentid_idx` (`sid`),
  CONSTRAINT `studentidreceipt` FOREIGN KEY (`sid`) REFERENCES `student` (`sid`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=330 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `fee_receiptinfo`
--

LOCK TABLES `fee_receiptinfo` WRITE;
/*!40000 ALTER TABLE `fee_receiptinfo` DISABLE KEYS */;
INSERT INTO `fee_receiptinfo` VALUES (1,88,'2025-05-11',6500,'2025/26',2,1,'001','Cash',331,'I--A',7,8,0,0),(2,328,'2025-05-16',3000,'2025/26',2,0,'002','Cash',2,'L.K.G--',47,48,0,0),(3,329,'2025-05-16',15000,'2025/26',2,0,'003','Cash',2,'L.K.G--',49,50,0,0),(4,330,'2025-05-18',10000,'2025/26',2,0,'004','Cash',2,'L.K.G--',51,52,0,0),(5,342,'2025-05-18',3000,'2025/26',2,0,'005','Cash',2,'L.K.G--',53,54,0,0),(6,332,'2025-05-18',15000,'2025/26',2,0,'006','Cash',2,'L.K.G--',55,56,0,0),(7,331,'2025-05-18',5000,'2025/26',2,0,'007','Cash',2,'L.K.G--',57,58,0,0),(8,333,'2025-05-18',5500,'2025/26',2,0,'008','Cash',2,'L.K.G--',59,60,0,0),(9,334,'2025-05-18',5500,'2025/26',2,0,'009','Cash',2,'L.K.G--',61,62,0,0),(10,335,'2025-05-18',5500,'2025/26',2,0,'010','Cash',2,'L.K.G--',63,64,0,0),(11,337,'2025-05-18',5500,'2025/26',2,0,'011','Cash',2,'L.K.G--',65,66,0,0),(12,338,'2025-05-18',5500,'2025/26',2,0,'012','Cash',2,'L.K.G--',67,68,0,0),(13,340,'2025-05-18',5500,'2025/26',2,0,'013','Cash',2,'L.K.G--',69,70,0,0),(14,341,'2025-05-18',1000,'2025/26',2,0,'014','Cash',2,'L.K.G--',71,72,0,0),(15,343,'2025-05-18',5500,'2025/26',2,0,'015','Cash',2,'L.K.G--',73,74,0,0),(16,344,'2025-05-18',3500,'2025/26',2,0,'016','Cash',2,'L.K.G--',75,76,0,0),(17,349,'2025-05-18',5500,'2025/26',2,0,'017','Cash',2,'L.K.G--',77,78,0,0),(18,345,'2025-05-18',15000,'2025/26',2,0,'018','Cash',2,'L.K.G--',79,80,0,0),(19,346,'2025-05-18',5500,'2025/26',2,0,'019','Cash',2,'L.K.G--',81,82,0,0),(20,347,'2025-05-18',2500,'2025/26',2,0,'020','Cash',2,'L.K.G--',83,84,0,0),(21,348,'2025-05-18',5500,'2025/26',2,0,'021','Cash',2,'L.K.G--',85,86,0,0),(22,351,'2025-05-18',5500,'2025/26',2,0,'022','Cash',2,'L.K.G--',87,88,0,0),(23,9,'2025-05-18',3000,'2025/26',2,0,'023','Cash',2,'U.K.G--A',89,90,0,0),(24,79,'2025-05-18',6000,'2025/26',2,0,'024','Cash',2,'U.K.G--D',91,92,0,0),(25,12,'2025-05-18',6000,'2025/26',2,0,'025','Cash',2,'U.K.G--A',93,94,0,0),(26,339,'2025-05-18',10000,'2025/26',2,0,'026','Cash',2,'U.K.G--',95,96,0,0),(27,46,'2025-05-18',6000,'2025/26',2,0,'027','Cash',2,'U.K.G--C',97,98,0,0),(28,16,'2025-05-18',6000,'2025/26',2,0,'028','Cash',2,'U.K.G--A',99,100,0,0),(29,61,'2025-05-18',2000,'2025/26',2,0,'029','Cash',2,'U.K.G--C',101,102,0,0),(30,81,'2025-05-18',6000,'2025/26',2,0,'030','Cash',2,'U.K.G--D',103,104,0,0),(31,15,'2025-05-18',6000,'2025/26',2,0,'031','Cash',2,'U.K.G--A',105,106,0,0),(32,14,'2025-05-18',6000,'2025/26',2,0,'032','Cash',2,'U.K.G--A',107,108,0,0),(33,350,'2025-05-18',3000,'2025/26',2,0,'033','Cash',2,'U.K.G--',109,110,0,0),(34,73,'2025-05-18',5000,'2025/26',2,0,'034','Cash',2,'U.K.G--D',111,112,0,0),(35,72,'2025-05-18',6000,'2025/26',2,0,'035','Cash',2,'U.K.G--D',113,114,0,0),(36,58,'2025-05-18',6000,'2025/26',2,0,'036','Cash',2,'U.K.G--C',115,116,0,0),(37,37,'2025-05-18',3000,'2025/26',2,0,'037','Cash',2,'U.K.G--B',117,118,0,0),(38,20,'2025-05-18',3000,'2025/26',2,0,'038','Cash',2,'U.K.G--A',119,120,0,0),(39,8,'2025-05-18',2000,'2025/26',2,0,'039','Cash',2,'U.K.G--A',121,122,0,0),(40,101,'2025-05-18',6500,'2025/26',2,0,'040','Cash',2,'I--A',123,124,0,0),(41,142,'2025-05-18',6500,'2025/26',2,0,'041','Cash',2,'I--C',125,126,0,0),(42,145,'2025-05-18',17000,'2025/26',2,0,'042','Cash',2,'I--C',127,128,0,0),(43,156,'2025-05-18',3000,'2025/26',2,0,'043','Cash',2,'II--A',129,130,0,0),(44,152,'2025-05-18',2500,'2025/26',2,0,'044','Cash',2,'II--A',131,132,0,0),(45,189,'2025-05-18',7000,'2025/26',2,0,'045','Cash',2,'II--B',133,134,0,0),(46,230,'2025-05-18',5000,'2025/26',2,0,'046','Cash',2,'III--B',135,136,0,0),(47,233,'2025-05-18',7000,'2025/26',2,0,'047','Cash',2,'III--B',137,138,0,0),(48,234,'2025-05-18',2500,'2025/26',2,0,'048','Cash',2,'III--B',139,140,0,0),(49,270,'2025-05-18',7000,'2025/26',2,0,'049','Cash',2,'IV--B',141,142,0,0),(50,261,'2025-05-18',1000,'2025/26',2,0,'050','Cash',2,'IV--A',143,144,0,0),(51,286,'2025-05-18',18700,'2025/26',2,0,'051','Cash',2,'IV--B',145,146,0,0),(52,323,'2025-05-18',2100,'2025/26',2,0,'052','Cash',2,'V--B',147,148,0,0),(53,288,'2025-05-18',1000,'2025/26',2,0,'053','Cash',2,'V--A',149,150,0,0),(54,195,'2025-05-18',7000,'2025/26',2,0,'054','Cash',2,'II--B',151,152,0,0),(55,353,'2025-05-21',5500,'2025/26',2,0,'055','Cash',2,'L.K.G--',159,160,0,0),(56,354,'2025-05-21',5500,'2025/26',2,0,'056','Cash',2,'L.K.G--',161,162,0,0),(57,355,'2025-05-21',5500,'2025/26',2,0,'057','Cash',2,'L.K.G--',163,164,0,0),(58,357,'2025-05-21',5500,'2025/26',2,0,'058','Cash',2,'L.K.G--',165,166,0,0),(59,32,'2025-05-21',6000,'2025/26',2,0,'059','Cash',2,'U.K.G--B',167,168,0,0),(60,356,'2025-05-21',6000,'2025/26',2,0,'060','Cash',2,'U.K.G--',169,170,0,0),(61,50,'2025-05-21',6000,'2025/26',2,0,'061','Cash',2,'U.K.G--C',171,172,0,0),(62,28,'2025-05-21',6500,'2025/26',2,1,'062','Cash',2,'U.K.G--B',173,174,0,0),(63,95,'2025-05-21',6500,'2025/26',2,0,'063','Cash',2,'I--A',175,176,0,0),(64,89,'2025-05-21',6500,'2025/26',2,0,'064','Cash',2,'I--A',177,178,0,0),(65,36,'2025-05-21',6500,'2025/26',2,0,'065','Cash',2,'I--B',183,184,0,0),(66,183,'2025-05-21',7000,'2025/26',2,1,'066','Cash',2,'II--B',185,186,0,0),(67,206,'2025-05-21',7000,'2025/26',2,0,'067','Cash',2,'II--B',187,188,0,0),(68,183,'2025-05-21',1000,'2025/26',2,0,'068','Cash',2,'II--B',189,190,0,0),(69,161,'2025-05-21',7000,'2025/26',2,1,'069','Cash',2,'II--A',191,192,0,0),(70,220,'2025-05-21',6000,'2025/26',2,0,'070','Cash',2,'III--A',193,194,0,0),(71,274,'2025-05-21',5000,'2025/26',2,0,'071','Cash',2,'IV--B',195,196,0,0),(72,352,'2025-05-21',3000,'2025/26',2,0,'072','Cash',2,'V--',197,198,0,0),(73,304,'2025-05-21',7000,'2025/26',2,0,'073','Cash',2,'V--A',199,200,0,0),(74,318,'2025-05-21',3000,'2025/26',2,0,'074','Cash',2,'V--B',201,202,0,0),(75,360,'2025-05-21',3500,'2025/26',2,0,'075','Cash',2,'L.K.G--',206,207,0,0),(76,359,'2025-05-21',6000,'2025/26',2,0,'076','Cash',2,'U.K.G--',208,209,0,0),(77,361,'2025-05-21',5500,'2025/26',2,0,'077','Cash',2,'L.K.G--',211,212,0,0),(78,358,'2025-05-21',5500,'2025/26',2,0,'078','Cash',2,'L.K.G--',213,214,0,0),(79,153,'2025-05-21',6000,'2025/26',2,1,'079','Cash',2,'II--A',215,216,0,0),(80,30,'2025-05-21',6000,'2025/26',2,0,'080','Cash',2,'U.K.G--B',217,218,0,0),(81,136,'2025-05-21',2000,'2025/26',2,0,'081','Cash',2,'I--C',219,220,0,0),(82,28,'2025-05-22',5500,'2025/26',2,0,'082','Cash',2,'U.K.G--B',221,222,0,0),(83,127,'2025-05-22',6500,'2025/26',2,0,'083','Cash',2,'I--B',223,224,0,0),(84,153,'2025-05-22',7000,'2025/26',2,0,'084','Cash',2,'II--A',225,226,0,0),(85,362,'2025-05-22',3000,'2025/26',2,0,'085','Cash',2,'U.K.G--',228,229,0,0),(86,129,'2025-05-23',17000,'2025/26',2,0,'086','Cash',2,'I--C',230,231,0,0),(87,272,'2025-05-23',7000,'2025/26',2,0,'087','Cash',2,'IV--B',232,233,0,0),(88,25,'2025-05-27',6000,'2025/26',2,0,'088','Cash',2,'U.K.G--B',234,235,0,0),(89,4,'2025-05-27',5000,'2025/26',2,0,'089','Cash',2,'U.K.G--A',236,237,0,0),(90,7,'2025-05-27',6000,'2025/26',2,0,'090','Cash',2,'U.K.G--A',238,239,0,0),(91,38,'2025-05-27',6000,'2025/26',2,0,'091','Cash',2,'U.K.G--B',240,241,0,0),(92,133,'2025-05-27',6500,'2025/26',2,0,'092','Cash',2,'I--C',242,243,0,0),(93,114,'2025-05-27',3500,'2025/26',2,0,'093','Cash',2,'I--B',244,245,0,0),(94,124,'2025-05-27',5500,'2025/26',2,0,'094','Cash',2,'I--B',246,247,0,0),(95,84,'2025-05-27',5000,'2025/26',2,0,'095','Cash',2,'I--A',248,249,0,0),(96,122,'2025-05-27',6500,'2025/26',2,0,'096','Cash',2,'I--B',250,251,0,0),(97,187,'2025-05-27',5000,'2025/26',2,0,'097','Cash',2,'II--B',252,253,0,0),(98,200,'2025-05-27',1000,'2025/26',2,0,'098','Cash',2,'II--B',254,255,0,0),(99,227,'2025-05-27',5000,'2025/26',2,0,'099','Cash',2,'III--B',256,257,0,0),(100,257,'2025-05-27',8000,'2025/26',2,0,'100','Cash',2,'IV--A',258,259,0,0),(101,283,'2025-05-27',9000,'2025/26',2,0,'101','Cash',2,'IV--B',260,261,0,0),(102,298,'2025-05-27',2000,'2025/26',2,0,'102','Cash',2,'V--A',262,263,0,0),(103,291,'2025-05-27',5000,'2025/26',2,0,'103','Cash',2,'V--A',264,265,0,0),(104,23,'2025-05-27',5000,'2025/26',2,0,'104','Cash',2,'U.K.G--B',266,267,0,0),(105,365,'2025-05-27',5000,'2025/26',2,0,'105','Cash',2,'L.K.G--',271,272,0,0),(106,364,'2025-05-27',5500,'2025/26',2,0,'106','Cash',2,'L.K.G--',273,274,0,0),(107,363,'2025-05-27',5000,'2025/26',2,0,'107','Cash',2,'L.K.G--',275,276,0,0),(108,276,'2025-05-27',5000,'2025/26',2,0,'108','Cash',2,'IV--B',277,278,0,0),(109,69,'2025-05-27',6000,'2025/26',2,0,'109','Cash',2,'U.K.G--D',279,280,0,0),(110,215,'2025-05-27',1900,'2025/26',2,0,'110','Cash',2,'III--A',281,282,0,0),(111,313,'2025-05-27',1900,'2025/26',2,0,'111','Cash',2,'V--B',283,284,0,0),(112,366,'2025-05-27',5500,'2025/26',2,0,'112','Cash',2,'L.K.G--',287,288,0,0),(113,367,'2025-05-27',3000,'2025/26',2,0,'113','Cash',2,'L.K.G--',289,290,0,0),(114,196,'2025-05-27',7000,'2025/26',2,0,'114','Cash',2,'II--B',291,292,0,0),(115,223,'2025-05-27',7000,'2025/26',2,0,'115','Cash',2,'III--A',293,294,0,0),(116,236,'2025-05-27',2000,'2025/26',2,0,'116','Cash',2,'III--B',295,296,0,0),(117,275,'2025-05-27',3000,'2025/26',2,0,'117','Cash',2,'IV--B',297,298,0,0),(118,372,'2025-05-28',3000,'2025/26',2,0,'118','Cash',2,'L.K.G--',303,304,0,0),(119,368,'2025-05-28',5000,'2025/26',2,0,'119','Cash',2,'L.K.G--',305,306,0,0),(120,369,'2025-05-28',5000,'2025/26',2,0,'120','Cash',2,'L.K.G--',308,309,0,0),(121,371,'2025-05-28',5500,'2025/26',2,0,'121','Cash',2,'L.K.G--',310,311,0,0),(122,181,'2025-05-28',7000,'2025/26',2,0,'122','Cash',2,'II--B',312,313,0,0),(123,204,'2025-05-28',7000,'2025/26',2,0,'123','Cash',2,'II--B',314,315,0,0),(124,159,'2025-05-28',3000,'2025/26',2,0,'124','Cash',2,'II--A',316,317,0,0),(125,370,'2025-05-28',7000,'2025/26',2,0,'125','Cash',2,'II--',318,319,0,0),(126,190,'2025-05-28',1550,'2025/26',2,0,'126','Cash',2,'II--B',320,321,0,0),(127,247,'2025-05-28',7000,'2025/26',2,0,'127','Cash',2,'III--B',322,323,0,0),(128,284,'2025-05-28',7000,'2025/26',2,0,'128','Cash',2,'IV--B',324,325,0,0),(129,309,'2025-05-28',4000,'2025/26',2,0,'129','Cash',2,'V--B',326,327,0,0),(130,293,'2025-05-28',1000,'2025/26',2,0,'130','Cash',2,'V--A',328,329,0,0),(131,299,'2025-05-28',7000,'2025/26',2,0,'131','Cash',2,'V--A',330,331,0,0),(132,302,'2025-05-28',1500,'2025/26',2,0,'132','Cash',2,'V--A',332,333,0,0),(133,105,'2025-05-28',6500,'2025/26',2,0,'133','Cash',2,'I--A',334,335,0,0),(134,161,'2025-06-03',2000,'2025/26',2,0,'134','Cash',2,'II--A',347,348,0,0),(135,391,'2025-06-03',5500,'2025/26',2,0,'135','Cash',2,'L.K.G--',350,351,0,0),(136,373,'2025-06-03',5500,'2025/26',2,0,'136','Cash',2,'L.K.G--',352,353,0,0),(137,375,'2025-06-03',2500,'2025/26',2,0,'137','Cash',2,'L.K.G--',355,356,0,0),(138,376,'2025-06-03',5500,'2025/26',2,0,'138','Cash',2,'L.K.G--',357,358,0,0),(139,377,'2025-06-03',5500,'2025/26',2,0,'139','Cash',2,'L.K.G--',360,361,0,0),(140,378,'2025-06-03',5500,'2025/26',2,0,'140','Cash',2,'L.K.G--',362,363,0,0),(141,384,'2025-06-03',3000,'2025/26',2,0,'141','Cash',2,'L.K.G--',365,366,0,0),(142,381,'2025-06-03',5500,'2025/26',2,0,'142','Cash',2,'L.K.G--',367,368,0,0),(143,380,'2025-06-03',3500,'2025/26',2,0,'143','Cash',2,'L.K.G--',370,371,0,0),(144,382,'2025-06-03',2500,'2025/26',2,0,'144','Cash',2,'L.K.G--',373,374,0,0),(145,387,'2025-06-03',5500,'2025/26',2,0,'145','Cash',2,'L.K.G--',375,376,0,0),(146,385,'2025-06-03',1000,'2025/26',2,0,'146','Cash',2,'L.K.G--',378,379,0,0),(147,388,'2025-06-03',3000,'2025/26',2,0,'147','Cash',2,'L.K.G--',380,381,0,0),(148,383,'2025-06-03',5000,'2025/26',2,0,'148','Cash',2,'L.K.G--',386,387,0,0),(149,374,'2025-06-03',5500,'2025/26',2,0,'149','Cash',2,'L.K.G--',388,389,0,0),(150,71,'2025-06-03',16500,'2025/26',2,0,'150','Cash',2,'U.K.G--D',390,391,0,0),(151,47,'2025-06-03',6000,'2025/26',2,0,'151','Cash',2,'U.K.G--C',392,393,0,0),(152,31,'2025-06-03',6000,'2025/26',2,0,'152','Cash',2,'U.K.G--B',394,395,0,0),(153,27,'2025-06-03',6000,'2025/26',2,0,'153','Cash',2,'U.K.G--B',396,397,0,0),(154,1,'2025-06-03',5000,'2025/26',2,0,'154','Cash',2,'U.K.G--A',398,399,0,0),(155,18,'2025-06-03',6000,'2025/26',2,0,'155','Cash',2,'U.K.G--A',400,401,0,0),(156,64,'2025-06-03',6000,'2025/26',2,0,'156','Cash',2,'U.K.G--D',402,403,0,0),(157,21,'2025-06-03',6000,'2025/26',2,0,'157','Cash',2,'U.K.G--A',404,405,0,0),(158,24,'2025-06-03',6000,'2025/26',2,0,'158','Cash',2,'U.K.G--B',406,407,0,0),(159,70,'2025-06-03',5000,'2025/26',2,0,'159','Cash',2,'U.K.G--D',408,409,0,0),(160,11,'2025-06-03',6000,'2025/26',2,0,'160','Cash',2,'U.K.G--A',410,411,0,0),(161,6,'2025-06-03',6000,'2025/26',2,0,'161','Cash',2,'U.K.G--A',412,413,0,0),(162,13,'2025-06-03',6000,'2025/26',2,0,'162','Cash',2,'U.K.G--A',414,415,0,0),(163,57,'2025-06-03',6000,'2025/26',2,0,'163','Cash',2,'U.K.G--C',416,417,0,0),(164,29,'2025-06-03',3000,'2025/26',2,0,'164','Cash',2,'U.K.G--B',418,419,0,0),(165,59,'2025-06-03',6000,'2025/26',2,0,'165','Cash',2,'U.K.G--C',420,421,0,0),(166,45,'2025-06-03',6000,'2025/26',2,0,'166','Cash',2,'U.K.G--C',422,423,0,0),(167,66,'2025-06-03',6000,'2025/26',2,0,'167','Cash',2,'U.K.G--D',424,425,0,0),(168,44,'2025-06-03',6000,'2025/26',2,0,'168','Cash',2,'U.K.G--C',426,427,0,0),(169,48,'2025-06-03',4000,'2025/26',2,0,'169','Cash',2,'U.K.G--C',428,429,0,0),(170,3,'2025-06-03',5000,'2025/26',2,0,'170','Cash',2,'U.K.G--A',430,431,0,0),(171,42,'2025-06-03',2000,'2025/26',2,0,'171','Cash',2,'U.K.G--B',432,433,0,0),(172,62,'2025-06-03',6000,'2025/26',2,0,'172','Cash',2,'U.K.G--C',434,435,0,0),(173,26,'2025-06-03',6000,'2025/26',2,0,'173','Cash',2,'U.K.G--B',436,437,0,0),(174,82,'2025-06-03',3000,'2025/26',2,0,'174','Cash',2,'U.K.G--D',438,439,0,0),(175,80,'2025-06-03',6000,'2025/26',2,0,'175','Cash',2,'U.K.G--D',440,441,0,0),(176,43,'2025-06-03',6000,'2025/26',2,0,'176','Cash',2,'U.K.G--C',442,443,0,0),(177,55,'2025-06-03',6000,'2025/26',2,0,'177','Cash',2,'U.K.G--C',444,445,0,0),(178,41,'2025-06-03',6000,'2025/26',2,0,'178','Cash',2,'U.K.G--B',446,447,0,0),(179,39,'2025-06-03',6000,'2025/26',2,0,'179','Cash',2,'U.K.G--B',448,449,0,0),(180,76,'2025-06-03',1000,'2025/26',2,0,'180','Cash',2,'U.K.G--D',450,451,0,0),(181,150,'2025-06-03',6500,'2025/26',2,0,'181','Cash',2,'I--C',452,453,0,0),(182,91,'2025-06-03',10000,'2025/26',2,0,'182','Cash',2,'I--A',454,455,0,0),(183,121,'2025-06-03',5500,'2025/26',2,0,'183','Cash',2,'I--B',456,457,0,0),(184,96,'2025-06-03',3000,'2025/26',2,0,'184','Cash',2,'I--A',458,459,0,0),(185,116,'2025-06-03',1000,'2025/26',2,0,'185','Cash',2,'I--B',460,461,0,0),(186,128,'2025-06-03',1500,'2025/26',2,0,'186','Cash',2,'I--B',462,463,0,0),(187,107,'2025-06-03',1500,'2025/26',2,0,'187','Cash',2,'I--B',464,465,0,0),(188,139,'2025-06-03',3000,'2025/26',2,0,'188','Cash',2,'I--C',466,467,0,0),(189,85,'2025-06-03',6500,'2025/26',2,0,'189','Cash',2,'I--A',468,469,0,0),(190,34,'2025-06-03',6500,'2025/26',2,0,'190','Cash',2,'I--B',474,475,0,0),(191,109,'2025-06-03',1000,'2025/26',2,0,'191','Cash',2,'I--B',476,477,0,0),(192,130,'2025-06-03',5000,'2025/26',2,0,'192','Cash',2,'I--C',478,479,0,0),(193,149,'2025-06-03',6500,'2025/26',2,0,'193','Cash',2,'I--C',480,481,0,0),(194,108,'2025-06-03',10000,'2025/26',2,0,'194','Cash',2,'I--B',482,483,0,0),(195,389,'2025-06-03',2000,'2025/26',2,0,'195','Cash',2,'I--',488,489,0,0),(196,117,'2025-06-03',550,'2025/26',2,0,'196','Cash',2,'I--B',490,491,0,0),(197,188,'2025-06-03',7000,'2025/26',2,0,'197','Cash',2,'II--B',492,493,0,0),(198,185,'2025-06-03',5700,'2025/26',2,0,'198','Cash',2,'II--B',494,495,0,0),(199,180,'2025-06-03',5000,'2025/26',2,0,'199','Cash',2,'II--B',496,497,0,0),(200,177,'2025-06-03',4000,'2025/26',2,0,'200','Cash',2,'II--B',498,499,0,0),(201,171,'2025-06-03',7000,'2025/26',2,1,'201','Cash',2,'II--A',500,501,7000,0),(202,155,'2025-06-03',7000,'2025/26',2,0,'202','Cash',2,'II--A',502,503,0,0),(203,170,'2025-06-03',7000,'2025/26',2,0,'203','Cash',2,'II--A',504,505,0,0),(204,162,'2025-06-03',5000,'2025/26',2,0,'204','Cash',2,'II--A',506,507,0,0),(205,193,'2025-06-03',5000,'2025/26',2,0,'205','Cash',2,'II--B',508,509,0,0),(206,157,'2025-06-03',5000,'2025/26',2,0,'206','Cash',2,'II--A',510,511,0,0),(207,184,'2025-06-03',7000,'2025/26',2,0,'207','Cash',2,'II--B',512,513,0,0),(208,165,'2025-06-03',3000,'2025/26',2,0,'208','Cash',2,'II--A',514,515,0,0),(209,167,'2025-06-03',1000,'2025/26',2,0,'209','Cash',2,'II--A',516,517,0,0),(210,198,'2025-06-03',3000,'2025/26',2,0,'210','Cash',2,'II--B',518,519,0,0),(211,182,'2025-06-03',5000,'2025/26',2,0,'211','Cash',2,'II--B',520,521,0,0),(212,197,'2025-06-03',3000,'2025/26',2,0,'212','Cash',2,'II--B',522,523,0,0),(213,201,'2025-06-03',1000,'2025/26',2,0,'213','Cash',2,'II--B',524,525,0,0),(214,202,'2025-06-03',7000,'2025/26',2,0,'214','Cash',2,'II--B',526,527,0,0),(215,174,'2025-06-03',4000,'2025/26',2,0,'215','Cash',2,'II--A',528,529,0,0),(216,186,'2025-06-03',2500,'2025/26',2,0,'216','Cash',2,'II--B',530,531,0,0),(217,194,'2025-06-03',1200,'2025/26',2,0,'217','Cash',2,'II--B',532,533,0,0),(218,154,'2025-06-03',5000,'2025/26',2,0,'218','Cash',2,'II--A',534,535,0,0),(219,172,'2025-06-03',3000,'2025/26',2,0,'219','Cash',2,'II--A',536,537,0,0),(220,199,'2025-06-03',7000,'2025/26',2,0,'220','Cash',2,'II--B',538,539,0,0),(221,160,'2025-06-03',2500,'2025/26',2,0,'221','Cash',2,'II--A',540,541,0,0),(222,203,'2025-06-03',5000,'2025/26',2,0,'222','Cash',2,'II--B',542,543,0,0),(223,158,'2025-06-03',1500,'2025/26',2,0,'223','Cash',2,'II--A',544,545,0,0),(224,169,'2025-06-03',1900,'2025/26',2,0,'224','Cash',2,'II--A',546,547,0,0),(225,171,'2025-06-03',7000,'2025/26',2,0,'225','Cash',2,'II--A',548,549,0,0),(226,216,'2025-06-04',7000,'2025/26',2,0,'226','Cash',2,'III--A',550,551,0,0),(227,235,'2025-06-04',7000,'2025/26',2,0,'227','Cash',2,'III--B',552,553,0,0),(228,221,'2025-06-04',7000,'2025/26',2,0,'228','Cash',2,'III--A',554,555,0,0),(229,218,'2025-06-04',4500,'2025/26',2,0,'229','Cash',2,'III--A',556,557,0,0),(230,240,'2025-06-04',7000,'2025/26',2,0,'230','Cash',2,'III--B',558,559,0,0),(231,237,'2025-06-04',3000,'2025/26',2,0,'231','Cash',2,'III--B',560,561,0,0),(232,222,'2025-06-04',1500,'2025/26',2,0,'232','Cash',2,'III--A',562,563,0,0),(233,219,'2025-06-04',2000,'2025/26',2,0,'233','Cash',2,'III--A',564,565,0,0),(234,226,'2025-06-04',7000,'2025/26',2,0,'234','Cash',2,'III--B',566,567,0,0),(235,232,'2025-06-04',4500,'2025/26',2,0,'235','Cash',2,'III--B',568,569,0,0),(236,211,'2025-06-04',1750,'2025/26',2,0,'236','Cash',2,'III--A',570,571,0,0),(237,239,'2025-06-04',7000,'2025/26',2,0,'237','Cash',2,'III--B',572,573,0,0),(238,239,'2025-06-04',1500,'2025/26',2,0,'238','Cash',2,'III--B',574,575,0,0),(239,217,'2025-06-04',7000,'2025/26',2,0,'239','Cash',2,'III--A',576,577,0,0),(240,212,'2025-06-04',3000,'2025/26',2,0,'240','Cash',2,'III--A',578,579,0,0),(241,386,'2025-06-04',2000,'2025/26',2,0,'241','Cash',2,'III--',580,581,0,0),(242,245,'2025-06-04',3000,'2025/26',2,0,'242','Cash',2,'III--B',582,583,0,0),(243,214,'2025-06-04',2000,'2025/26',2,0,'243','Cash',2,'III--A',584,585,0,0),(244,243,'2025-06-04',6500,'2025/26',2,0,'244','Cash',2,'III--B',586,587,0,0),(245,231,'2025-06-04',2750,'2025/26',2,0,'245','Cash',2,'III--B',588,589,0,0),(246,229,'2025-06-04',7000,'2025/26',2,0,'246','Cash',2,'III--B',590,591,0,0),(247,256,'2025-06-04',4000,'2025/26',2,0,'247','Cash',2,'IV--A',592,593,0,0),(248,280,'2025-06-04',5700,'2025/26',2,0,'248','Cash',2,'IV--B',594,595,0,0),(249,254,'2025-06-04',7000,'2025/26',2,0,'249','Cash',2,'IV--A',596,597,0,0),(250,279,'2025-06-04',5000,'2025/26',2,0,'250','Cash',2,'IV--B',598,599,0,0),(251,259,'2025-06-04',550,'2025/26',2,0,'251','Cash',2,'IV--A',600,601,0,0),(252,265,'2025-06-04',1000,'2025/26',2,0,'252','Cash',2,'IV--B',602,603,0,0),(253,281,'2025-06-04',1000,'2025/26',2,0,'253','Cash',2,'IV--B',604,605,0,0),(254,267,'2025-06-04',2000,'2025/26',2,0,'254','Cash',2,'IV--B',606,607,0,0),(255,282,'2025-06-04',7000,'2025/26',2,0,'255','Cash',2,'IV--B',608,609,0,0),(256,263,'2025-06-04',1000,'2025/26',2,0,'256','Cash',2,'IV--A',610,611,0,0),(257,278,'2025-06-04',5000,'2025/26',2,0,'257','Cash',2,'IV--B',612,613,0,0),(258,269,'2025-06-04',3000,'2025/26',2,0,'258','Cash',2,'IV--B',614,615,0,0),(259,249,'2025-06-04',4000,'2025/26',2,0,'259','Cash',2,'IV--A',616,617,0,0),(260,251,'2025-06-04',7000,'2025/26',2,0,'260','Cash',2,'IV--A',618,619,0,0),(261,271,'2025-06-04',1700,'2025/26',2,0,'261','Cash',2,'IV--B',620,621,0,0),(262,253,'2025-06-04',7000,'2025/26',2,0,'262','Cash',2,'IV--A',622,623,0,0),(263,262,'2025-06-04',2000,'2025/26',2,0,'263','Cash',2,'IV--A',624,625,0,0),(264,317,'2025-06-05',4000,'2025/26',2,0,'264','Cash',2,'V--B',626,627,0,0),(265,308,'2025-06-05',3000,'2025/26',2,0,'265','Cash',2,'V--B',628,629,0,0),(266,315,'2025-06-05',3000,'2025/26',2,0,'266','Cash',2,'V--B',630,631,0,0),(267,314,'2025-06-05',7000,'2025/26',2,0,'267','Cash',2,'V--B',632,633,0,0),(268,316,'2025-06-05',5000,'2025/26',2,0,'268','Cash',2,'V--B',634,635,0,0),(269,289,'2025-06-05',15000,'2025/26',2,0,'269','Cash',2,'V--A',636,637,0,0),(270,294,'2025-06-05',1000,'2025/26',2,0,'270','Cash',2,'V--A',638,639,0,0),(271,324,'2025-06-05',7000,'2025/26',2,0,'271','Cash',2,'V--B',640,641,0,0),(272,327,'2025-06-09',7000,'2025/26',2,0,'272','Cash',2,'V--B',642,643,0,0),(273,301,'2025-06-09',7000,'2025/26',2,0,'273','Cash',2,'V--A',644,645,0,0),(274,292,'2025-06-09',3000,'2025/26',2,0,'274','Cash',2,'V--A',646,647,0,0),(275,319,'2025-06-09',2000,'2025/26',2,0,'275','Cash',2,'V--B',648,649,0,0),(276,326,'2025-06-09',1000,'2025/26',2,0,'276','Cash',2,'V--B',650,651,0,0),(277,322,'2025-06-09',7000,'2025/26',2,0,'277','Cash',2,'V--B',652,653,0,0),(278,306,'2025-06-09',2000,'2025/26',2,0,'278','Cash',2,'V--A',654,655,0,0),(279,300,'2025-06-09',5000,'2025/26',2,0,'279','Cash',2,'V--A',656,657,0,0),(280,311,'2025-06-09',2000,'2025/26',2,0,'280','Cash',2,'V--B',658,659,0,0),(281,390,'2025-06-10',5500,'2025/26',2,0,'281','Cash',2,'L.K.G--',660,661,0,0),(282,73,'2025-06-10',5000,'2025/26',2,0,'282','Cash',2,'U.K.G--D',662,663,0,0),(283,33,'2025-06-10',3000,'2025/26',2,0,'283','Cash',2,'U.K.G--B',664,665,0,0),(284,74,'2025-06-10',1000,'2025/26',2,0,'284','Cash',2,'U.K.G--D',666,667,0,0),(285,75,'2025-06-10',2000,'2025/26',2,0,'285','Cash',2,'U.K.G--D',668,669,0,0),(286,63,'2025-06-10',6000,'2025/26',2,0,'286','Cash',2,'U.K.G--C',670,671,0,0),(287,94,'2025-06-10',2000,'2025/26',2,0,'287','Cash',2,'I--A',672,673,0,0),(288,131,'2025-06-10',1050,'2025/26',2,0,'288','Cash',2,'I--C',674,675,0,0),(289,138,'2025-06-10',2750,'2025/26',2,0,'289','Cash',2,'I--C',676,677,0,0),(290,137,'2025-06-10',2500,'2025/26',2,0,'290','Cash',2,'I--C',678,679,0,0),(291,148,'2025-06-10',6500,'2025/26',2,0,'291','Cash',2,'I--C',680,681,0,0),(292,173,'2025-06-10',7000,'2025/26',2,0,'292','Cash',2,'II--A',682,683,0,0),(293,207,'2025-06-10',7000,'2025/26',2,0,'293','Cash',2,'II--B',684,685,0,0),(294,192,'2025-06-10',2000,'2025/26',2,0,'294','Cash',2,'II--B',686,687,0,0),(295,205,'2025-06-10',6000,'2025/26',2,0,'295','Cash',2,'II--B',688,689,0,0),(296,241,'2025-06-10',7000,'2025/26',2,0,'296','Cash',2,'III--B',690,691,0,0),(297,228,'2025-06-10',2000,'2025/26',2,0,'297','Cash',2,'III--B',692,693,0,0),(298,268,'2025-06-10',4000,'2025/26',2,0,'298','Cash',2,'IV--B',694,695,0,0),(299,266,'2025-06-10',1000,'2025/26',2,0,'299','Cash',2,'IV--B',696,697,0,0),(300,321,'2025-06-10',2000,'2025/26',2,0,'300','Cash',2,'V--B',698,699,0,0),(301,392,'2025-06-10',3000,'2025/26',2,0,'301','Cash',2,'L.K.G--',706,707,0,0),(302,395,'2025-06-10',3000,'2025/26',2,0,'302','Cash',2,'L.K.G--',708,709,0,0),(303,396,'2025-06-10',3000,'2025/26',2,0,'303','Cash',2,'L.K.G--',710,711,0,0),(304,394,'2025-06-10',5000,'2025/26',2,0,'304','Cash',2,'I--',712,713,0,0),(305,393,'2025-06-10',5000,'2025/26',2,0,'305','Cash',2,'V--',714,715,0,0),(306,398,'2025-06-10',4000,'2025/26',2,1,'306','Cash',2,'V--',717,718,0,0),(307,379,'2025-06-10',4000,'2025/26',2,0,'307','Cash',2,'V--',720,721,0,0),(308,397,'2025-06-10',5500,'2025/26',2,0,'308','Cash',2,'L.K.G--',722,723,0,0),(309,399,'2025-06-10',2000,'2025/26',2,0,'309','Cash',2,'L.K.G--',725,726,0,0),(310,68,'2025-06-10',6000,'2025/26',2,0,'310','Cash',2,'U.K.G--D',727,728,0,0),(311,19,NULL,6000,'2025/26',2,0,'311','Cash',2,'U.K.G--A',729,730,0,0),(312,19,'2025-06-10',6000,'2025/26',2,0,'312','Cash',2,'U.K.G--A',731,732,0,0),(313,49,'2025-06-10',5000,'2025/26',2,0,'313','Cash',2,'U.K.G--C',733,734,0,0),(314,54,'2025-06-10',6000,'2025/26',2,0,'314','Cash',2,'U.K.G--C',735,736,0,0),(315,17,'2025-06-10',6000,'2025/26',2,0,'315','Cash',2,'U.K.G--A',737,738,0,0),(316,143,'2025-06-10',6000,'2025/26',2,0,'316','Cash',2,'I--C',739,740,0,0),(317,208,'2025-06-10',7000,'2025/26',2,0,'317','Cash',2,'II--B',741,742,0,0),(318,213,'2025-06-10',4000,'2025/26',2,0,'318','Cash',2,'III--A',743,744,0,0),(319,242,'2025-06-10',2500,'2025/26',2,0,'319','Cash',2,'III--B',745,746,0,0),(320,238,'2025-06-10',3000,'2025/26',2,0,'320','Cash',2,'III--B',747,748,0,0),(321,255,'2025-06-10',10000,'2025/26',2,0,'321','Cash',2,'IV--A',749,750,0,0),(322,250,'2025-06-10',7000,'2025/26',2,0,'322','Cash',2,'IV--A',751,752,0,0),(323,285,'2025-06-10',7000,'2025/26',2,0,'323','Cash',2,'IV--B',753,754,0,0),(324,312,'2025-06-10',7000,'2025/26',2,0,'324','Cash',2,'V--B',755,756,0,0),(325,178,'2025-06-10',7000,'2025/26',2,0,'325','Cash',2,'II--B',758,759,0,0),(326,168,'2025-06-10',5000,'2025/26',2,0,'326','Cash',2,'II--A',760,761,0,0),(327,271,'2025-06-10',5000,'2025/26',2,0,'327','Cash',2,'IV--B',762,763,0,0),(328,328,'2025-06-10',2000,'2025/26',2,0,'328','Cash',2,'L.K.G--',764,765,0,0),(329,385,'2025-06-10',1000,'2025/26',2,0,'329','Cash',2,'L.K.G--',766,767,0,0);
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
  `concession` int(11) DEFAULT NULL,
  `feespaid` decimal(10,0) DEFAULT '0',
  `waiveoff` decimal(10,0) DEFAULT '0',
  `totalinstallment` int(11) DEFAULT '0',
  `userid` int(11) DEFAULT NULL,
  PRIMARY KEY (`sfsid`),
  KEY `fk_sfs_idx` (`sid`),
  KEY `feescategoryid_idx` (`idfeescategory`),
  CONSTRAINT `feescategoryid` FOREIGN KEY (`idfeescategory`) REFERENCES `fee_feescategory` (`idfeescategory`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_sfs` FOREIGN KEY (`sid`) REFERENCES `student` (`sid`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=1225 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `fee_studentfeesstructure`
--

LOCK TABLES `fee_studentfeesstructure` WRITE;
/*!40000 ALTER TABLE `fee_studentfeesstructure` DISABLE KEYS */;
INSERT INTO `fee_studentfeesstructure` VALUES (1,32,4,6000,'2025/26',2,0,6000,0,1,3),(2,32,5,5250,'2025/26',2,0,0,0,1,3),(3,32,8,5250,'2025/26',2,0,0,0,1,3),(4,64,4,6000,'2025/26',2,0,6000,0,1,3),(5,64,5,5250,'2025/26',2,0,0,0,1,3),(6,64,8,5250,'2025/26',2,0,0,0,1,3),(7,57,4,6000,'2025/26',2,0,6000,0,1,3),(8,57,5,5250,'2025/26',2,0,0,0,1,3),(9,57,8,5250,'2025/26',2,0,0,0,1,3),(10,50,4,6000,'2025/26',2,0,6000,0,1,3),(11,50,5,5250,'2025/26',2,0,0,0,1,3),(12,50,8,5250,'2025/26',2,0,0,0,1,3),(13,25,4,6000,'2025/26',2,0,6000,0,1,3),(14,25,5,5250,'2025/26',2,0,0,0,1,3),(15,25,8,5250,'2025/26',2,0,0,0,1,3),(16,43,4,6000,'2025/26',2,0,6000,0,1,3),(17,43,5,5250,'2025/26',2,0,0,0,1,3),(18,43,8,5250,'2025/26',2,0,0,0,1,3),(19,82,4,6000,'2025/26',2,0,3000,0,1,3),(20,82,5,5250,'2025/26',2,0,0,0,1,3),(21,82,8,5250,'2025/26',2,0,0,0,1,3),(22,75,4,6000,'2025/26',2,0,2000,0,1,3),(23,75,5,5250,'2025/26',2,0,0,0,1,3),(24,75,8,5250,'2025/26',2,0,0,0,1,3),(25,68,4,6000,'2025/26',2,0,6000,0,1,3),(26,68,5,5250,'2025/26',2,0,0,0,1,3),(27,68,8,5250,'2025/26',2,0,0,0,1,3),(31,61,4,6000,'2025/26',2,0,2000,0,1,3),(32,61,5,5250,'2025/26',2,0,0,0,1,3),(33,61,8,5250,'2025/26',2,0,0,0,1,3),(34,54,4,6000,'2025/26',2,0,6000,0,1,3),(35,54,5,5250,'2025/26',2,0,0,0,1,3),(36,54,8,5250,'2025/26',2,0,0,0,1,3),(37,29,4,6000,'2025/26',2,0,3000,0,1,3),(38,29,5,5250,'2025/26',2,0,0,0,1,3),(39,29,8,5250,'2025/26',2,0,0,0,1,3),(40,47,4,6000,'2025/26',2,0,6000,0,1,3),(41,47,5,5250,'2025/26',2,0,0,0,1,3),(42,47,8,5250,'2025/26',2,0,0,0,1,3),(43,40,4,6000,'2025/26',2,0,0,0,1,3),(44,40,5,5250,'2025/26',2,0,0,0,1,3),(45,40,8,5250,'2025/26',2,0,0,0,1,3),(46,79,4,6000,'2025/26',2,0,6000,0,1,3),(47,79,5,5250,'2025/26',2,0,0,0,1,3),(48,79,8,5250,'2025/26',2,0,0,0,1,3),(49,72,4,6000,'2025/26',2,0,6000,0,1,3),(50,72,5,5250,'2025/26',2,0,0,0,1,3),(51,72,8,5250,'2025/26',2,0,0,0,1,3),(52,33,4,6000,'2025/26',2,0,3000,0,1,3),(53,33,5,5250,'2025/26',2,0,0,0,1,3),(54,33,8,5250,'2025/26',2,0,0,0,1,3),(55,65,4,6000,'2025/26',2,0,0,0,1,3),(56,65,5,5250,'2025/26',2,0,0,0,1,3),(57,65,8,5250,'2025/26',2,0,0,0,1,3),(58,58,4,6000,'2025/26',2,0,6000,0,1,3),(59,58,5,5250,'2025/26',2,0,0,0,1,3),(60,58,8,5250,'2025/26',2,0,0,0,1,3),(61,51,4,6000,'2025/26',2,0,0,0,1,3),(62,51,5,5250,'2025/26',2,0,0,0,1,3),(63,51,8,5250,'2025/26',2,0,0,0,1,3),(64,26,4,6000,'2025/26',2,0,6000,0,1,3),(65,26,5,5250,'2025/26',2,0,0,0,1,3),(66,26,8,5250,'2025/26',2,0,0,0,1,3),(67,44,4,6000,'2025/26',2,0,6000,0,1,3),(68,44,5,5250,'2025/26',2,0,0,0,1,3),(69,44,8,5250,'2025/26',2,0,0,0,1,3),(70,76,4,6000,'2025/26',2,0,1000,0,1,3),(71,76,5,5250,'2025/26',2,0,0,0,1,3),(72,76,8,5250,'2025/26',2,0,0,0,1,3),(73,69,4,6000,'2025/26',2,0,6000,0,1,3),(74,69,5,5250,'2025/26',2,0,0,0,1,3),(75,69,8,5250,'2025/26',2,0,0,0,1,3),(76,37,4,6000,'2025/26',2,0,3000,0,1,3),(77,37,5,5250,'2025/26',2,0,0,0,1,3),(78,37,8,5250,'2025/26',2,0,0,0,1,3),(79,62,4,6000,'2025/26',2,0,6000,0,1,3),(80,62,5,5250,'2025/26',2,0,0,0,1,3),(81,62,8,5250,'2025/26',2,0,0,0,1,3),(82,55,4,6000,'2025/26',2,0,6000,0,1,3),(83,55,5,5250,'2025/26',2,0,0,0,1,3),(84,55,8,5250,'2025/26',2,0,0,0,1,3),(85,30,4,6000,'2025/26',2,0,6000,0,1,3),(86,30,5,5250,'2025/26',2,0,0,0,1,3),(87,30,8,5250,'2025/26',2,0,0,0,1,3),(88,23,4,6000,'2025/26',2,0,5000,0,1,3),(89,23,5,5250,'2025/26',2,0,0,0,1,3),(90,23,8,5250,'2025/26',2,0,0,0,1,3),(91,48,4,6000,'2025/26',2,0,4000,0,1,3),(92,48,5,5250,'2025/26',2,0,0,0,1,3),(93,48,8,5250,'2025/26',2,0,0,0,1,3),(94,41,4,6000,'2025/26',2,0,6000,0,1,3),(95,41,5,5250,'2025/26',2,0,0,0,1,3),(96,41,8,5250,'2025/26',2,0,0,0,1,3),(97,80,4,6000,'2025/26',2,0,6000,0,1,3),(98,80,5,5250,'2025/26',2,0,0,0,1,3),(99,80,8,5250,'2025/26',2,0,0,0,1,3),(100,73,4,6000,'2025/26',2,0,6000,0,1,3),(101,73,5,5250,'2025/26',2,0,4000,0,1,3),(102,73,8,5250,'2025/26',2,0,0,0,1,3),(106,66,4,6000,'2025/26',2,0,6000,0,1,3),(107,66,5,5250,'2025/26',2,0,0,0,1,3),(108,66,8,5250,'2025/26',2,0,0,0,1,3),(109,59,4,6000,'2025/26',2,0,6000,0,1,3),(110,59,5,5250,'2025/26',2,0,0,0,1,3),(111,59,8,5250,'2025/26',2,0,0,0,1,3),(112,52,4,6000,'2025/26',2,0,0,0,1,3),(113,52,5,5250,'2025/26',2,0,0,0,1,3),(114,52,8,5250,'2025/26',2,0,0,0,1,3),(115,27,4,6000,'2025/26',2,0,6000,0,1,3),(116,27,5,5250,'2025/26',2,0,0,0,1,3),(117,27,8,5250,'2025/26',2,0,0,0,1,3),(118,45,4,6000,'2025/26',2,0,6000,0,1,3),(119,45,5,5250,'2025/26',2,0,0,0,1,3),(120,45,8,5250,'2025/26',2,0,0,0,1,3),(121,77,4,6000,'2025/26',2,0,0,0,1,3),(122,77,5,5250,'2025/26',2,0,0,0,1,3),(123,77,8,5250,'2025/26',2,0,0,0,1,3),(124,70,4,6000,'2025/26',2,0,5000,0,1,3),(125,70,5,5250,'2025/26',2,0,0,0,1,3),(126,70,8,5250,'2025/26',2,0,0,0,1,3),(127,38,4,6000,'2025/26',2,0,6000,0,1,3),(128,38,5,5250,'2025/26',2,0,0,0,1,3),(129,38,8,5250,'2025/26',2,0,0,0,1,3),(130,31,4,6000,'2025/26',2,0,6000,0,1,3),(131,31,5,5250,'2025/26',2,0,0,0,1,3),(132,31,8,5250,'2025/26',2,0,0,0,1,3),(133,63,4,6000,'2025/26',2,0,6000,0,1,3),(134,63,5,5250,'2025/26',2,0,0,0,1,3),(135,63,8,5250,'2025/26',2,0,0,0,1,3),(136,56,4,6000,'2025/26',2,0,0,0,1,3),(137,56,5,5250,'2025/26',2,0,0,0,1,3),(138,56,8,5250,'2025/26',2,0,0,0,1,3),(139,49,4,6000,'2025/26',2,0,5000,0,1,3),(140,49,5,5250,'2025/26',2,0,0,0,1,3),(141,49,8,5250,'2025/26',2,0,0,0,1,3),(142,24,4,6000,'2025/26',2,0,6000,0,1,3),(143,24,5,5250,'2025/26',2,0,0,0,1,3),(144,24,8,5250,'2025/26',2,0,0,0,1,3),(145,42,4,6000,'2025/26',2,0,2000,0,1,3),(146,42,5,5250,'2025/26',2,0,0,0,1,3),(147,42,8,5250,'2025/26',2,0,0,0,1,3),(148,81,4,6000,'2025/26',2,0,6000,0,1,3),(149,81,5,5250,'2025/26',2,0,0,0,1,3),(150,81,8,5250,'2025/26',2,0,0,0,1,3),(151,74,4,6000,'2025/26',2,0,1000,0,1,3),(152,74,5,5250,'2025/26',2,0,0,0,1,3),(153,74,8,5250,'2025/26',2,0,0,0,1,3),(154,35,4,6000,'2025/26',2,0,0,0,1,3),(155,35,5,5250,'2025/26',2,0,0,0,1,3),(156,35,8,5250,'2025/26',2,0,0,0,1,3),(157,67,4,6000,'2025/26',2,0,0,0,1,3),(158,67,5,5250,'2025/26',2,0,0,0,1,3),(159,67,8,5250,'2025/26',2,0,0,0,1,3),(160,60,4,6000,'2025/26',2,0,0,0,1,3),(161,60,5,5250,'2025/26',2,0,0,0,1,3),(162,60,8,5250,'2025/26',2,0,0,0,1,3),(163,53,4,6000,'2025/26',2,0,0,0,1,3),(164,53,5,5250,'2025/26',2,0,0,0,1,3),(165,53,8,5250,'2025/26',2,0,0,0,1,3),(166,28,4,6000,'2025/26',2,0,5500,0,1,3),(167,28,5,5250,'2025/26',2,0,0,0,1,3),(168,28,8,5250,'2025/26',2,0,0,0,1,3),(169,46,4,6000,'2025/26',2,0,6000,0,1,3),(170,46,5,5250,'2025/26',2,0,0,0,1,3),(171,46,8,5250,'2025/26',2,0,0,0,1,3),(172,39,4,6000,'2025/26',2,0,6000,0,1,3),(173,39,5,5250,'2025/26',2,0,0,0,1,3),(174,39,8,5250,'2025/26',2,0,0,0,1,3),(175,78,4,6000,'2025/26',2,0,0,0,1,3),(176,78,5,5250,'2025/26',2,0,0,0,1,3),(177,78,8,5250,'2025/26',2,0,0,0,1,3),(178,71,4,6000,'2025/26',2,0,6000,0,1,3),(179,71,5,5250,'2025/26',2,0,5250,0,1,3),(180,71,8,5250,'2025/26',2,0,5250,0,1,3),(181,22,4,6000,'2025/26',2,0,0,0,1,3),(182,22,5,5250,'2025/26',2,0,0,0,1,3),(183,22,8,5250,'2025/26',2,0,0,0,1,3),(184,12,4,6000,'2025/26',2,0,6000,0,1,3),(185,12,5,5250,'2025/26',2,0,0,0,1,3),(186,12,8,5250,'2025/26',2,0,0,0,1,3),(187,9,4,6000,'2025/26',2,0,3000,0,1,3),(188,9,5,5250,'2025/26',2,0,0,0,1,3),(189,9,8,5250,'2025/26',2,0,0,0,1,3),(190,13,4,6000,'2025/26',2,0,6000,0,1,3),(191,13,5,5250,'2025/26',2,0,0,0,1,3),(192,13,8,5250,'2025/26',2,0,0,0,1,3),(193,14,4,6000,'2025/26',2,0,6000,0,1,3),(194,14,5,5250,'2025/26',2,0,0,0,1,3),(195,14,8,5250,'2025/26',2,0,0,0,1,3),(196,15,4,6000,'2025/26',2,0,6000,0,1,3),(197,15,5,5250,'2025/26',2,0,0,0,1,3),(198,15,8,5250,'2025/26',2,0,0,0,1,3),(199,16,4,6000,'2025/26',2,0,6000,0,1,3),(200,16,5,5250,'2025/26',2,0,0,0,1,3),(201,16,8,5250,'2025/26',2,0,0,0,1,3),(202,17,4,6000,'2025/26',2,0,6000,0,1,3),(203,17,5,5250,'2025/26',2,0,0,0,1,3),(204,17,8,5250,'2025/26',2,0,0,0,1,3),(205,3,4,6000,'2025/26',2,0,5000,0,1,3),(206,3,5,5250,'2025/26',2,0,0,0,1,3),(207,3,8,5250,'2025/26',2,0,0,0,1,3),(208,18,4,6000,'2025/26',2,0,6000,0,1,3),(209,18,5,5250,'2025/26',2,0,0,0,1,3),(210,18,8,5250,'2025/26',2,0,0,0,1,3),(211,21,4,6000,'2025/26',2,0,6000,0,1,3),(212,21,5,5250,'2025/26',2,0,0,0,1,3),(213,21,8,5250,'2025/26',2,0,0,0,1,3),(214,1,4,6000,'2025/26',2,0,5000,0,1,3),(215,1,5,5250,'2025/26',2,0,0,0,1,3),(216,1,8,5250,'2025/26',2,0,0,0,1,3),(217,19,4,6000,'2025/26',2,0,12000,0,1,3),(218,19,5,5250,'2025/26',2,0,0,0,1,3),(219,19,8,5250,'2025/26',2,0,0,0,1,3),(220,7,4,6000,'2025/26',2,0,6000,0,1,3),(221,7,5,5250,'2025/26',2,0,0,0,1,3),(222,7,8,5250,'2025/26',2,0,0,0,1,3),(223,8,4,6000,'2025/26',2,0,2000,0,1,3),(224,8,5,5250,'2025/26',2,0,0,0,1,3),(225,8,8,5250,'2025/26',2,0,0,0,1,3),(226,20,4,6000,'2025/26',2,0,3000,0,1,3),(227,20,5,5250,'2025/26',2,0,0,0,1,3),(228,20,8,5250,'2025/26',2,0,0,0,1,3),(229,4,4,6000,'2025/26',2,0,5000,0,1,3),(230,4,5,5250,'2025/26',2,0,0,0,1,3),(231,4,8,5250,'2025/26',2,0,0,0,1,3),(232,6,4,6000,'2025/26',2,0,6000,0,1,3),(233,6,5,5250,'2025/26',2,0,0,0,1,3),(234,6,8,5250,'2025/26',2,0,0,0,1,3),(235,2,4,6000,'2025/26',2,0,0,0,1,3),(236,2,5,5250,'2025/26',2,0,0,0,1,3),(237,2,8,5250,'2025/26',2,0,0,0,1,3),(238,5,4,6000,'2025/26',2,0,0,0,1,3),(239,5,5,5250,'2025/26',2,0,0,0,1,3),(240,5,8,5250,'2025/26',2,0,0,0,1,3),(241,10,4,6000,'2025/26',2,0,0,0,1,3),(242,10,5,5250,'2025/26',2,0,0,0,1,3),(243,10,8,5250,'2025/26',2,0,0,0,1,3),(244,11,4,6000,'2025/26',2,0,6000,0,1,3),(245,11,5,5250,'2025/26',2,0,0,0,1,3),(246,11,8,5250,'2025/26',2,0,0,0,1,3),(247,110,6,5250,'2025/26',2,0,0,0,1,3),(248,110,9,5250,'2025/26',2,0,0,0,1,3),(249,110,11,6500,'2025/26',2,0,0,0,1,3),(250,149,6,5250,'2025/26',2,0,0,0,1,3),(251,149,9,5250,'2025/26',2,0,0,0,1,3),(252,149,11,6500,'2025/26',2,0,6500,0,1,3),(253,103,6,5250,'2025/26',2,0,0,0,1,3),(254,103,9,5250,'2025/26',2,0,0,0,1,3),(255,103,11,6500,'2025/26',2,0,0,0,1,3),(256,96,6,5250,'2025/26',2,0,0,0,1,3),(257,96,9,5250,'2025/26',2,0,0,0,1,3),(258,96,11,6500,'2025/26',2,0,3000,0,1,3),(259,89,6,5250,'2025/26',2,0,0,0,1,3),(260,89,9,5250,'2025/26',2,0,0,0,1,3),(261,89,11,6500,'2025/26',2,0,6500,0,1,3),(262,142,6,5250,'2025/26',2,0,0,0,1,3),(263,142,9,5250,'2025/26',2,0,0,0,1,3),(264,142,11,6500,'2025/26',2,0,6500,0,1,3),(265,135,6,5250,'2025/26',2,0,0,0,1,3),(266,135,9,5250,'2025/26',2,0,0,0,1,3),(267,135,11,6500,'2025/26',2,0,0,0,1,3),(268,128,6,5250,'2025/26',2,0,0,0,1,3),(269,128,9,5250,'2025/26',2,0,0,0,1,3),(270,128,11,6500,'2025/26',2,0,1500,0,1,3),(271,121,6,5250,'2025/26',2,0,0,0,1,3),(272,121,9,5250,'2025/26',2,0,0,0,1,3),(273,121,11,6500,'2025/26',2,0,5500,0,1,3),(274,114,6,5250,'2025/26',2,0,0,0,1,3),(275,114,9,5250,'2025/26',2,0,0,0,1,3),(276,114,11,6500,'2025/26',2,0,3500,0,1,3),(277,100,6,5250,'2025/26',2,0,0,0,1,3),(278,100,9,5250,'2025/26',2,0,0,0,1,3),(279,100,11,6500,'2025/26',2,0,0,0,1,3),(280,107,6,5250,'2025/26',2,0,0,0,1,3),(281,107,9,5250,'2025/26',2,0,0,0,1,3),(282,107,11,6500,'2025/26',2,0,1500,0,1,3),(283,146,6,5250,'2025/26',2,0,0,0,1,3),(284,146,9,5250,'2025/26',2,0,0,0,1,3),(285,146,11,6500,'2025/26',2,0,0,0,1,3),(286,139,6,5250,'2025/26',2,0,0,0,1,3),(287,139,9,5250,'2025/26',2,0,0,0,1,3),(288,139,11,6500,'2025/26',2,0,3000,0,1,3),(289,93,6,5250,'2025/26',2,0,0,0,1,3),(290,93,9,5250,'2025/26',2,0,0,0,1,3),(291,93,11,6500,'2025/26',2,0,0,0,1,3),(292,132,6,5250,'2025/26',2,0,0,0,1,3),(293,132,9,5250,'2025/26',2,0,0,0,1,3),(294,132,11,6500,'2025/26',2,0,0,0,1,3),(295,86,6,5250,'2025/26',2,0,0,0,1,3),(296,86,9,5250,'2025/26',2,0,0,0,1,3),(297,86,11,6500,'2025/26',2,0,0,0,1,3),(298,118,6,5250,'2025/26',2,0,0,0,1,3),(299,118,9,5250,'2025/26',2,0,0,0,1,3),(300,118,11,6500,'2025/26',2,0,0,0,1,3),(301,125,6,5250,'2025/26',2,0,0,0,1,3),(302,125,9,5250,'2025/26',2,0,0,0,1,3),(303,125,11,6500,'2025/26',2,0,0,0,1,3),(304,111,6,5250,'2025/26',2,0,0,0,1,3),(305,111,9,5250,'2025/26',2,0,0,0,1,3),(306,111,11,6500,'2025/26',2,0,0,0,1,3),(307,150,6,5250,'2025/26',2,0,0,0,1,3),(308,150,9,5250,'2025/26',2,0,0,0,1,3),(309,150,11,6500,'2025/26',2,0,6500,0,1,3),(310,104,6,5250,'2025/26',2,0,0,0,1,3),(311,104,9,5250,'2025/26',2,0,0,0,1,3),(312,104,11,6500,'2025/26',2,0,0,0,1,3),(313,97,6,5250,'2025/26',2,0,0,0,1,3),(314,97,9,5250,'2025/26',2,0,0,0,1,3),(315,97,11,6500,'2025/26',2,0,0,0,1,3),(316,136,6,5250,'2025/26',2,0,0,0,1,3),(317,136,9,5250,'2025/26',2,0,0,0,1,3),(318,136,11,6500,'2025/26',2,0,2000,0,1,3),(319,90,6,5250,'2025/26',2,0,0,0,1,3),(320,90,9,5250,'2025/26',2,0,0,0,1,3),(321,90,11,6500,'2025/26',2,0,0,0,1,3),(322,143,6,5250,'2025/26',2,0,0,0,1,3),(323,143,9,5250,'2025/26',2,0,0,0,1,3),(324,143,11,6500,'2025/26',2,0,6000,0,1,3),(325,129,6,5250,'2025/26',2,0,5250,0,1,3),(326,129,9,5250,'2025/26',2,0,5250,0,1,3),(327,129,11,6500,'2025/26',2,0,6500,0,1,3),(328,83,6,5250,'2025/26',2,0,0,0,1,3),(329,83,9,5250,'2025/26',2,0,0,0,1,3),(330,83,11,6500,'2025/26',2,0,0,0,1,3),(331,122,6,5250,'2025/26',2,0,0,0,1,3),(332,122,9,5250,'2025/26',2,0,0,0,1,3),(333,122,11,6500,'2025/26',2,0,6500,0,1,3),(334,115,6,5250,'2025/26',2,0,0,0,1,3),(335,115,9,5250,'2025/26',2,0,0,0,1,3),(336,115,11,6500,'2025/26',2,0,0,0,1,3),(337,108,6,5250,'2025/26',2,0,0,0,1,3),(338,108,9,5250,'2025/26',2,0,3500,0,1,3),(339,108,11,6500,'2025/26',2,0,6500,0,1,3),(340,101,6,5250,'2025/26',2,0,0,0,1,3),(341,101,9,5250,'2025/26',2,0,0,0,1,3),(342,101,11,6500,'2025/26',2,0,6500,0,1,3),(343,147,6,5250,'2025/26',2,0,0,0,1,3),(344,147,9,5250,'2025/26',2,0,0,0,1,3),(345,147,11,6500,'2025/26',2,0,0,0,1,3),(346,94,6,5250,'2025/26',2,0,0,0,1,3),(347,94,9,5250,'2025/26',2,0,0,0,1,3),(348,94,11,6500,'2025/26',2,0,2000,0,1,3),(349,140,6,5250,'2025/26',2,0,0,0,1,3),(350,140,9,5250,'2025/26',2,0,0,0,1,3),(351,140,11,6500,'2025/26',2,0,0,0,1,3),(352,133,6,5250,'2025/26',2,0,0,0,1,3),(353,133,9,5250,'2025/26',2,0,0,0,1,3),(354,133,11,6500,'2025/26',2,0,6500,0,1,3),(355,87,6,5250,'2025/26',2,0,0,0,1,3),(356,87,9,5250,'2025/26',2,0,0,0,1,3),(357,87,11,6500,'2025/26',2,0,0,0,1,3),(358,119,6,5250,'2025/26',2,0,0,0,1,3),(359,119,9,5250,'2025/26',2,0,0,0,1,3),(360,119,11,6500,'2025/26',2,0,0,0,1,3),(361,126,6,5250,'2025/26',2,0,0,0,1,3),(362,126,9,5250,'2025/26',2,0,0,0,1,3),(363,126,11,6500,'2025/26',2,0,0,0,1,3),(364,112,6,5250,'2025/26',2,0,0,0,1,3),(365,112,9,5250,'2025/26',2,0,0,0,1,3),(366,112,11,6500,'2025/26',2,0,0,0,1,3),(367,151,6,5250,'2025/26',2,0,0,0,1,3),(368,151,9,5250,'2025/26',2,0,0,0,1,3),(369,151,11,6500,'2025/26',2,0,0,0,1,3),(370,105,6,5250,'2025/26',2,0,0,0,1,3),(371,105,9,5250,'2025/26',2,0,0,0,1,3),(372,105,11,6500,'2025/26',2,0,6500,0,1,3),(373,98,6,5250,'2025/26',2,0,0,0,1,3),(374,98,9,5250,'2025/26',2,0,0,0,1,3),(375,98,11,6500,'2025/26',2,0,0,0,1,3),(376,137,6,5250,'2025/26',2,0,0,0,1,3),(377,137,9,5250,'2025/26',2,0,0,0,1,3),(378,137,11,6500,'2025/26',2,0,2500,0,1,3),(379,91,6,5250,'2025/26',2,0,0,0,1,3),(380,91,9,5250,'2025/26',2,0,3500,0,1,3),(381,91,11,6500,'2025/26',2,0,6500,0,1,3),(382,144,6,5250,'2025/26',2,0,0,0,1,3),(383,144,9,5250,'2025/26',2,0,0,0,1,3),(384,144,11,6500,'2025/26',2,0,0,0,1,3),(385,130,6,5250,'2025/26',2,0,0,0,1,3),(386,130,9,5250,'2025/26',2,0,0,0,1,3),(387,130,11,6500,'2025/26',2,0,5000,0,1,3),(388,84,6,5250,'2025/26',2,0,0,0,1,3),(389,84,9,5250,'2025/26',2,0,0,0,1,3),(390,84,11,6500,'2025/26',2,0,5000,0,1,3),(391,123,6,5250,'2025/26',2,0,0,0,1,3),(392,123,9,5250,'2025/26',2,0,0,0,1,3),(393,123,11,6500,'2025/26',2,0,0,0,1,3),(394,116,6,5250,'2025/26',2,0,0,0,1,3),(395,116,9,5250,'2025/26',2,0,0,0,1,3),(396,116,11,6500,'2025/26',2,0,1000,0,1,3),(397,109,6,5250,'2025/26',2,0,0,0,1,3),(398,109,9,5250,'2025/26',2,0,0,0,1,3),(399,109,11,6500,'2025/26',2,0,1000,0,1,3),(400,148,6,5250,'2025/26',2,0,0,0,1,3),(401,148,9,5250,'2025/26',2,0,0,0,1,3),(402,148,11,6500,'2025/26',2,0,6500,0,1,3),(403,102,6,5250,'2025/26',2,0,0,0,1,3),(404,102,9,5250,'2025/26',2,0,0,0,1,3),(405,102,11,6500,'2025/26',2,0,0,0,1,3),(406,95,6,5250,'2025/26',2,0,0,0,1,3),(407,95,9,5250,'2025/26',2,0,0,0,1,3),(408,95,11,6500,'2025/26',2,0,6500,0,1,3),(409,141,6,5250,'2025/26',2,0,0,0,1,3),(410,141,9,5250,'2025/26',2,0,0,0,1,3),(411,141,11,6500,'2025/26',2,0,0,0,1,3),(412,134,6,5250,'2025/26',2,0,0,0,1,3),(413,134,9,5250,'2025/26',2,0,0,0,1,3),(414,134,11,6500,'2025/26',2,0,0,0,1,3),(415,88,6,5250,'2025/26',2,0,0,0,1,3),(416,88,9,5250,'2025/26',2,0,0,0,1,3),(417,88,11,6500,'2025/26',2,0,0,0,1,3),(418,127,6,5250,'2025/26',2,0,0,0,1,3),(419,127,9,5250,'2025/26',2,0,0,0,1,3),(420,127,11,6500,'2025/26',2,0,6500,0,1,3),(421,120,6,5250,'2025/26',2,0,0,0,1,3),(422,120,9,5250,'2025/26',2,0,0,0,1,3),(423,120,11,6500,'2025/26',2,0,0,0,1,3),(424,113,6,5250,'2025/26',2,0,0,0,1,3),(425,113,9,5250,'2025/26',2,0,0,0,1,3),(426,113,11,6500,'2025/26',2,0,0,0,1,3),(427,99,6,5250,'2025/26',2,0,0,0,1,3),(428,99,9,5250,'2025/26',2,0,0,0,1,3),(429,99,11,6500,'2025/26',2,0,0,0,1,3),(430,106,6,5250,'2025/26',2,0,0,0,1,3),(431,106,9,5250,'2025/26',2,0,0,0,1,3),(432,106,11,6500,'2025/26',2,0,0,0,1,3),(433,145,6,5250,'2025/26',2,0,5250,0,1,3),(434,145,9,5250,'2025/26',2,0,5250,0,1,3),(435,145,11,6500,'2025/26',2,0,6500,0,1,3),(436,138,6,5250,'2025/26',2,0,0,0,1,3),(437,138,9,5250,'2025/26',2,0,0,0,1,3),(438,138,11,6500,'2025/26',2,0,2750,0,1,3),(439,92,6,5250,'2025/26',2,0,0,0,1,3),(440,92,9,5250,'2025/26',2,0,0,0,1,3),(441,92,11,6500,'2025/26',2,0,0,0,1,3),(442,131,6,5250,'2025/26',2,0,0,0,1,3),(443,131,9,5250,'2025/26',2,0,0,0,1,3),(444,131,11,6500,'2025/26',2,0,1050,0,1,3),(445,85,6,5250,'2025/26',2,0,0,0,1,3),(446,85,9,5250,'2025/26',2,0,0,0,1,3),(447,85,11,6500,'2025/26',2,0,6500,0,1,3),(448,117,6,5250,'2025/26',2,0,0,0,1,3),(449,117,9,5250,'2025/26',2,0,0,0,1,3),(450,117,11,6500,'2025/26',2,0,550,0,1,3),(451,124,6,5250,'2025/26',2,0,0,0,1,3),(452,124,9,5250,'2025/26',2,0,0,0,1,3),(453,124,11,6500,'2025/26',2,0,5500,0,1,3),(454,183,7,5250,'2025/26',2,0,0,0,1,3),(455,183,10,5250,'2025/26',2,0,0,0,1,3),(456,183,12,7000,'2025/26',2,0,1000,0,1,3),(457,208,7,5250,'2025/26',2,0,0,0,1,3),(458,208,10,5250,'2025/26',2,0,0,0,1,3),(459,208,12,7000,'2025/26',2,0,7000,0,1,3),(460,201,7,5250,'2025/26',2,0,0,0,1,3),(461,201,10,5250,'2025/26',2,0,0,0,1,3),(462,201,12,7000,'2025/26',2,0,1000,0,1,3),(463,176,7,5250,'2025/26',2,0,0,0,1,3),(464,176,10,5250,'2025/26',2,0,0,0,1,3),(465,176,12,7000,'2025/26',2,0,0,0,1,3),(466,169,7,5250,'2025/26',2,0,0,0,1,3),(467,169,10,5250,'2025/26',2,0,0,0,1,3),(468,169,12,7000,'2025/26',2,0,1900,0,1,3),(469,194,7,5250,'2025/26',2,0,0,0,1,3),(470,194,10,5250,'2025/26',2,0,0,0,1,3),(471,194,12,7000,'2025/26',2,0,1200,0,1,3),(472,162,7,5250,'2025/26',2,0,0,0,1,3),(473,162,10,5250,'2025/26',2,0,0,0,1,3),(474,162,12,7000,'2025/26',2,0,5000,0,1,3),(475,155,7,5250,'2025/26',2,0,0,0,1,3),(476,155,10,5250,'2025/26',2,0,0,0,1,3),(477,155,12,7000,'2025/26',2,0,7000,0,1,3),(478,187,7,5250,'2025/26',2,0,0,0,1,3),(479,187,10,5250,'2025/26',2,0,0,0,1,3),(480,187,12,7000,'2025/26',2,0,5000,0,1,3),(481,205,7,5250,'2025/26',2,0,0,0,1,3),(482,205,10,5250,'2025/26',2,0,0,0,1,3),(483,205,12,7000,'2025/26',2,0,6000,0,1,3),(484,180,7,5250,'2025/26',2,0,0,0,1,3),(485,180,10,5250,'2025/26',2,0,0,0,1,3),(486,180,12,7000,'2025/26',2,0,5000,0,1,3),(487,173,7,5250,'2025/26',2,0,0,0,1,3),(488,173,10,5250,'2025/26',2,0,0,0,1,3),(489,173,12,7000,'2025/26',2,0,7000,0,1,3),(490,166,7,5250,'2025/26',2,0,0,0,1,3),(491,166,10,5250,'2025/26',2,0,0,0,1,3),(492,166,12,7000,'2025/26',2,0,0,0,1,3),(493,198,7,5250,'2025/26',2,0,0,0,1,3),(494,198,10,5250,'2025/26',2,0,0,0,1,3),(495,198,12,7000,'2025/26',2,0,3000,0,1,3),(496,191,7,5250,'2025/26',2,0,0,0,1,3),(497,191,10,5250,'2025/26',2,0,0,0,1,3),(498,191,12,7000,'2025/26',2,0,0,0,1,3),(499,159,7,5250,'2025/26',2,0,0,0,1,3),(500,159,10,5250,'2025/26',2,0,0,0,1,3),(501,159,12,7000,'2025/26',2,0,3000,0,1,3),(502,152,7,5250,'2025/26',2,0,0,0,1,3),(503,152,10,5250,'2025/26',2,0,0,0,1,3),(504,152,12,7000,'2025/26',2,0,2500,0,1,3),(505,184,7,5250,'2025/26',2,0,0,0,1,3),(506,184,10,5250,'2025/26',2,0,0,0,1,3),(507,184,12,7000,'2025/26',2,0,7000,0,1,3),(508,209,7,5250,'2025/26',2,0,0,0,1,3),(509,209,10,5250,'2025/26',2,0,0,0,1,3),(510,209,12,7000,'2025/26',2,0,0,0,1,3),(511,202,7,5250,'2025/26',2,0,0,0,1,3),(512,202,10,5250,'2025/26',2,0,0,0,1,3),(513,202,12,7000,'2025/26',2,0,7000,0,1,3),(514,177,7,5250,'2025/26',2,0,0,0,1,3),(515,177,10,5250,'2025/26',2,0,0,0,1,3),(516,177,12,7000,'2025/26',2,0,4000,0,1,3),(517,170,7,5250,'2025/26',2,0,0,0,1,3),(518,170,10,5250,'2025/26',2,0,0,0,1,3),(519,170,12,7000,'2025/26',2,0,7000,0,1,3),(520,195,7,5250,'2025/26',2,0,0,0,1,3),(521,195,10,5250,'2025/26',2,0,0,0,1,3),(522,195,12,7000,'2025/26',2,0,7000,0,1,3),(523,163,7,5250,'2025/26',2,0,0,0,1,3),(524,163,10,5250,'2025/26',2,0,0,0,1,3),(525,163,12,7000,'2025/26',2,0,0,0,1,3),(526,156,7,5250,'2025/26',2,0,0,0,1,3),(527,156,10,5250,'2025/26',2,0,0,0,1,3),(528,156,12,7000,'2025/26',2,0,3000,0,1,3),(529,188,7,5250,'2025/26',2,0,0,0,1,3),(530,188,10,5250,'2025/26',2,0,0,0,1,3),(531,188,12,7000,'2025/26',2,0,7000,0,1,3),(532,206,7,5250,'2025/26',2,0,0,0,1,3),(533,206,10,5250,'2025/26',2,0,0,0,1,3),(534,206,12,7000,'2025/26',2,0,7000,0,1,3),(535,181,7,5250,'2025/26',2,0,0,0,1,3),(536,181,10,5250,'2025/26',2,0,0,0,1,3),(537,181,12,7000,'2025/26',2,0,7000,0,1,3),(538,174,7,5250,'2025/26',2,0,0,0,1,3),(539,174,10,5250,'2025/26',2,0,0,0,1,3),(540,174,12,7000,'2025/26',2,0,4000,0,1,3),(541,167,7,5250,'2025/26',2,0,0,0,1,3),(542,167,10,5250,'2025/26',2,0,0,0,1,3),(543,167,12,7000,'2025/26',2,0,1000,0,1,3),(544,199,7,5250,'2025/26',2,0,0,0,1,3),(545,199,10,5250,'2025/26',2,0,0,0,1,3),(546,199,12,7000,'2025/26',2,0,7000,0,1,3),(547,192,7,5250,'2025/26',2,0,0,0,1,3),(548,192,10,5250,'2025/26',2,0,0,0,1,3),(549,192,12,7000,'2025/26',2,0,2000,0,1,3),(550,160,7,5250,'2025/26',2,0,0,0,1,3),(551,160,10,5250,'2025/26',2,0,0,0,1,3),(552,160,12,7000,'2025/26',2,0,2500,0,1,3),(553,153,7,5250,'2025/26',2,0,0,0,1,3),(554,153,10,5250,'2025/26',2,0,0,0,1,3),(555,153,12,7000,'2025/26',2,0,7000,0,1,3),(556,185,7,5250,'2025/26',2,0,0,0,1,3),(557,185,10,5250,'2025/26',2,0,0,0,1,3),(558,185,12,7000,'2025/26',2,0,5700,0,1,3),(559,210,7,5250,'2025/26',2,0,0,0,1,3),(560,210,10,5250,'2025/26',2,0,0,0,1,3),(561,210,12,7000,'2025/26',2,0,0,0,1,3),(562,203,7,5250,'2025/26',2,0,0,0,1,3),(563,203,10,5250,'2025/26',2,0,0,0,1,3),(564,203,12,7000,'2025/26',2,0,5000,0,1,3),(565,178,7,5250,'2025/26',2,0,0,0,1,3),(566,178,10,5250,'2025/26',2,0,0,0,1,3),(567,178,12,7000,'2025/26',2,0,7000,0,1,3),(568,164,7,5250,'2025/26',2,0,0,0,1,3),(569,164,10,5250,'2025/26',2,0,0,0,1,3),(570,164,12,7000,'2025/26',2,0,0,0,1,3),(571,196,7,5250,'2025/26',2,0,0,0,1,3),(572,196,10,5250,'2025/26',2,0,0,0,1,3),(573,196,12,7000,'2025/26',2,0,7000,0,1,3),(574,171,7,5250,'2025/26',2,0,0,0,1,3),(575,171,10,5250,'2025/26',2,0,0,0,1,3),(576,171,12,7000,'2025/26',2,0,7000,0,1,3),(577,157,7,5250,'2025/26',2,0,0,0,1,3),(578,157,10,5250,'2025/26',2,0,0,0,1,3),(579,157,12,7000,'2025/26',2,0,5000,0,1,3),(580,189,7,5250,'2025/26',2,0,0,0,1,3),(581,189,10,5250,'2025/26',2,0,0,0,1,3),(582,189,12,7000,'2025/26',2,0,7000,0,1,3),(583,182,7,5250,'2025/26',2,0,0,0,1,3),(584,182,10,5250,'2025/26',2,0,0,0,1,3),(585,182,12,7000,'2025/26',2,0,5000,0,1,3),(586,207,7,5250,'2025/26',2,0,0,0,1,3),(587,207,10,5250,'2025/26',2,0,0,0,1,3),(588,207,12,7000,'2025/26',2,0,7000,0,1,3),(589,200,7,5250,'2025/26',2,0,0,0,1,3),(590,200,10,5250,'2025/26',2,0,0,0,1,3),(591,200,12,7000,'2025/26',2,0,1000,0,1,3),(592,175,7,5250,'2025/26',2,0,0,0,1,3),(593,175,10,5250,'2025/26',2,0,0,0,1,3),(594,175,12,7000,'2025/26',2,0,0,0,1,3),(595,168,7,5250,'2025/26',2,0,0,0,1,3),(596,168,10,5250,'2025/26',2,0,0,0,1,3),(597,168,12,7000,'2025/26',2,0,5000,0,1,3),(598,193,7,5250,'2025/26',2,0,0,0,1,3),(599,193,10,5250,'2025/26',2,0,0,0,1,3),(600,193,12,7000,'2025/26',2,0,5000,0,1,3),(601,161,7,5250,'2025/26',2,0,0,0,1,3),(602,161,10,5250,'2025/26',2,0,0,0,1,3),(603,161,12,7000,'2025/26',2,0,2000,0,1,3),(604,154,7,5250,'2025/26',2,0,0,0,1,3),(605,154,10,5250,'2025/26',2,0,0,0,1,3),(606,154,12,7000,'2025/26',2,0,5000,0,1,3),(607,186,7,5250,'2025/26',2,0,0,0,1,3),(608,186,10,5250,'2025/26',2,0,0,0,1,3),(609,186,12,7000,'2025/26',2,0,2500,0,1,3),(610,204,7,5250,'2025/26',2,0,0,0,1,3),(611,204,10,5250,'2025/26',2,0,0,0,1,3),(612,204,12,7000,'2025/26',2,0,7000,0,1,3),(613,179,7,5250,'2025/26',2,0,0,0,1,3),(614,179,10,5250,'2025/26',2,0,0,0,1,3),(615,179,12,7000,'2025/26',2,0,0,0,1,3),(616,165,7,5250,'2025/26',2,0,0,0,1,3),(617,165,10,5250,'2025/26',2,0,0,0,1,3),(618,165,12,7000,'2025/26',2,0,3000,0,1,3),(619,197,7,5250,'2025/26',2,0,0,0,1,3),(620,197,10,5250,'2025/26',2,0,0,0,1,3),(621,197,12,7000,'2025/26',2,0,3000,0,1,3),(622,172,7,5250,'2025/26',2,0,0,0,1,3),(623,172,10,5250,'2025/26',2,0,0,0,1,3),(624,172,12,7000,'2025/26',2,0,3000,0,1,3),(625,158,7,5250,'2025/26',2,0,0,0,1,3),(626,158,10,5250,'2025/26',2,0,0,0,1,3),(627,158,12,7000,'2025/26',2,0,1500,0,1,3),(628,190,7,5250,'2025/26',2,0,0,0,1,3),(629,190,10,5250,'2025/26',2,0,0,0,1,3),(630,190,12,7000,'2025/26',2,0,1550,0,1,3),(631,238,13,7000,'2025/26',2,0,3000,0,1,3),(632,238,16,5750,'2025/26',2,0,0,0,1,3),(633,238,17,5750,'2025/26',2,0,0,0,1,3),(634,231,13,7000,'2025/26',2,0,2750,0,1,3),(635,231,16,5750,'2025/26',2,0,0,0,1,3),(636,231,17,5750,'2025/26',2,0,0,0,1,3),(637,224,13,7000,'2025/26',2,0,0,0,1,3),(638,224,16,5750,'2025/26',2,0,0,0,1,3),(639,224,17,5750,'2025/26',2,0,0,0,1,3),(640,217,13,7000,'2025/26',2,0,7000,0,1,3),(641,217,16,5750,'2025/26',2,0,0,0,1,3),(642,217,17,5750,'2025/26',2,0,0,0,1,3),(643,242,13,7000,'2025/26',2,0,2500,0,1,3),(644,242,16,5750,'2025/26',2,0,0,0,1,3),(645,242,17,5750,'2025/26',2,0,0,0,1,3),(646,228,13,7000,'2025/26',2,0,2000,0,1,3),(647,228,16,5750,'2025/26',2,0,0,0,1,3),(648,228,17,5750,'2025/26',2,0,0,0,1,3),(649,235,13,7000,'2025/26',2,0,7000,0,1,3),(650,235,16,5750,'2025/26',2,0,0,0,1,3),(651,235,17,5750,'2025/26',2,0,0,0,1,3),(652,221,13,7000,'2025/26',2,0,7000,0,1,3),(653,221,16,5750,'2025/26',2,0,0,0,1,3),(654,221,17,5750,'2025/26',2,0,0,0,1,3),(655,214,13,7000,'2025/26',2,0,2000,0,1,3),(656,214,16,5750,'2025/26',2,0,0,0,1,3),(657,214,17,5750,'2025/26',2,0,0,0,1,3),(658,246,13,7000,'2025/26',2,0,0,0,1,3),(659,246,16,5750,'2025/26',2,0,0,0,1,3),(660,246,17,5750,'2025/26',2,0,0,0,1,3),(661,239,13,7000,'2025/26',2,0,7000,0,1,3),(662,239,16,5750,'2025/26',2,0,1500,0,1,3),(663,239,17,5750,'2025/26',2,0,0,0,1,3),(664,232,13,7000,'2025/26',2,0,4500,0,1,3),(665,232,16,5750,'2025/26',2,0,0,0,1,3),(666,232,17,5750,'2025/26',2,0,0,0,1,3),(667,225,13,7000,'2025/26',2,0,0,0,1,3),(668,225,16,5750,'2025/26',2,0,0,0,1,3),(669,225,17,5750,'2025/26',2,0,0,0,1,3),(670,218,13,7000,'2025/26',2,0,4500,0,1,3),(671,218,16,5750,'2025/26',2,0,0,0,1,3),(672,218,17,5750,'2025/26',2,0,0,0,1,3),(673,211,13,7000,'2025/26',2,0,1750,0,1,3),(674,211,16,5750,'2025/26',2,0,0,0,1,3),(675,211,17,5750,'2025/26',2,0,0,0,1,3),(676,243,13,7000,'2025/26',2,0,6500,0,1,3),(677,243,16,5750,'2025/26',2,0,0,0,1,3),(678,243,17,5750,'2025/26',2,0,0,0,1,3),(679,236,13,7000,'2025/26',2,0,2000,0,1,3),(680,236,16,5750,'2025/26',2,0,0,0,1,3),(681,236,17,5750,'2025/26',2,0,0,0,1,3),(682,229,13,7000,'2025/26',2,0,7000,0,1,3),(683,229,16,5750,'2025/26',2,0,0,0,1,3),(684,229,17,5750,'2025/26',2,0,0,0,1,3),(685,222,13,7000,'2025/26',2,0,1500,0,1,3),(686,222,16,5750,'2025/26',2,0,0,0,1,3),(687,222,17,5750,'2025/26',2,0,0,0,1,3),(688,215,13,7000,'2025/26',2,0,1900,0,1,3),(689,215,16,5750,'2025/26',2,0,0,0,1,3),(690,215,17,5750,'2025/26',2,0,0,0,1,3),(691,247,13,7000,'2025/26',2,0,7000,0,1,3),(692,247,16,5750,'2025/26',2,0,0,0,1,3),(693,247,17,5750,'2025/26',2,0,0,0,1,3),(694,240,13,7000,'2025/26',2,0,7000,0,1,3),(695,240,16,5750,'2025/26',2,0,0,0,1,3),(696,240,17,5750,'2025/26',2,0,0,0,1,3),(697,233,13,7000,'2025/26',2,0,7000,0,1,3),(698,233,16,5750,'2025/26',2,0,0,0,1,3),(699,233,17,5750,'2025/26',2,0,0,0,1,3),(700,226,13,7000,'2025/26',2,0,7000,0,1,3),(701,226,16,5750,'2025/26',2,0,0,0,1,3),(702,226,17,5750,'2025/26',2,0,0,0,1,3),(703,219,13,7000,'2025/26',2,0,2000,0,1,3),(704,219,16,5750,'2025/26',2,0,0,0,1,3),(705,219,17,5750,'2025/26',2,0,0,0,1,3),(706,212,13,7000,'2025/26',2,0,3000,0,1,3),(707,212,16,5750,'2025/26',2,0,0,0,1,3),(708,212,17,5750,'2025/26',2,0,0,0,1,3),(709,244,13,7000,'2025/26',2,0,0,0,1,3),(710,244,16,5750,'2025/26',2,0,0,0,1,3),(711,244,17,5750,'2025/26',2,0,0,0,1,3),(712,237,13,7000,'2025/26',2,0,3000,0,1,3),(713,237,16,5750,'2025/26',2,0,0,0,1,3),(714,237,17,5750,'2025/26',2,0,0,0,1,3),(715,230,13,7000,'2025/26',2,0,5000,0,1,3),(716,230,16,5750,'2025/26',2,0,0,0,1,3),(717,230,17,5750,'2025/26',2,0,0,0,1,3),(718,223,13,7000,'2025/26',2,0,7000,0,1,3),(719,223,16,5750,'2025/26',2,0,0,0,1,3),(720,223,17,5750,'2025/26',2,0,0,0,1,3),(721,216,13,7000,'2025/26',2,0,7000,0,1,3),(722,216,16,5750,'2025/26',2,0,0,0,1,3),(723,216,17,5750,'2025/26',2,0,0,0,1,3),(724,241,13,7000,'2025/26',2,0,7000,0,1,3),(725,241,16,5750,'2025/26',2,0,0,0,1,3),(726,241,17,5750,'2025/26',2,0,0,0,1,3),(727,227,13,7000,'2025/26',2,0,5000,0,1,3),(728,227,16,5750,'2025/26',2,0,0,0,1,3),(729,227,17,5750,'2025/26',2,0,0,0,1,3),(730,234,13,7000,'2025/26',2,0,2500,0,1,3),(731,234,16,5750,'2025/26',2,0,0,0,1,3),(732,234,17,5750,'2025/26',2,0,0,0,1,3),(733,220,13,7000,'2025/26',2,0,6000,0,1,3),(734,220,16,5750,'2025/26',2,0,0,0,1,3),(735,220,17,5750,'2025/26',2,0,0,0,1,3),(736,213,13,7000,'2025/26',2,0,4000,0,1,3),(737,213,16,5750,'2025/26',2,0,0,0,1,3),(738,213,17,5750,'2025/26',2,0,0,0,1,3),(739,245,13,7000,'2025/26',2,0,3000,0,1,3),(740,245,16,5750,'2025/26',2,0,0,0,1,3),(741,245,17,5750,'2025/26',2,0,0,0,1,3),(742,275,14,7000,'2025/26',2,0,3000,0,1,3),(743,275,18,5850,'2025/26',2,0,0,0,1,3),(744,275,19,5850,'2025/26',2,0,0,0,1,3),(745,268,14,7000,'2025/26',2,0,4000,0,1,3),(746,268,18,5850,'2025/26',2,0,0,0,1,3),(747,268,19,5850,'2025/26',2,0,0,0,1,3),(748,261,14,7000,'2025/26',2,0,1000,0,1,3),(749,261,18,5850,'2025/26',2,0,0,0,1,3),(750,261,19,5850,'2025/26',2,0,0,0,1,3),(751,254,14,7000,'2025/26',2,0,7000,0,1,3),(752,254,18,5850,'2025/26',2,0,0,0,1,3),(753,254,19,5850,'2025/26',2,0,0,0,1,3),(754,286,14,7000,'2025/26',2,0,7000,0,1,3),(755,286,18,5850,'2025/26',2,0,5850,0,1,3),(756,286,19,5850,'2025/26',2,0,5850,0,1,3),(757,279,14,7000,'2025/26',2,0,5000,0,1,3),(758,279,18,5850,'2025/26',2,0,0,0,1,3),(759,279,19,5850,'2025/26',2,0,0,0,1,3),(760,272,14,7000,'2025/26',2,0,7000,0,1,3),(761,272,18,5850,'2025/26',2,0,0,0,1,3),(762,272,19,5850,'2025/26',2,0,0,0,1,3),(763,265,14,7000,'2025/26',2,0,1000,0,1,3),(764,265,18,5850,'2025/26',2,0,0,0,1,3),(765,265,19,5850,'2025/26',2,0,0,0,1,3),(766,258,14,7000,'2025/26',2,0,0,0,1,3),(767,258,18,5850,'2025/26',2,0,0,0,1,3),(768,258,19,5850,'2025/26',2,0,0,0,1,3),(769,251,14,7000,'2025/26',2,0,7000,0,1,3),(770,251,18,5850,'2025/26',2,0,0,0,1,3),(771,251,19,5850,'2025/26',2,0,0,0,1,3),(772,283,14,7000,'2025/26',2,0,7000,0,1,3),(773,283,18,5850,'2025/26',2,0,2000,0,1,3),(774,283,19,5850,'2025/26',2,0,0,0,1,3),(775,276,14,7000,'2025/26',2,0,5000,0,1,3),(776,276,18,5850,'2025/26',2,0,0,0,1,3),(777,276,19,5850,'2025/26',2,0,0,0,1,3),(778,269,14,7000,'2025/26',2,0,3000,0,1,3),(779,269,18,5850,'2025/26',2,0,0,0,1,3),(780,269,19,5850,'2025/26',2,0,0,0,1,3),(781,262,14,7000,'2025/26',2,0,2000,0,1,3),(782,262,18,5850,'2025/26',2,0,0,0,1,3),(783,262,19,5850,'2025/26',2,0,0,0,1,3),(784,255,14,7000,'2025/26',2,0,7000,0,1,3),(785,255,18,5850,'2025/26',2,0,3000,0,1,3),(786,255,19,5850,'2025/26',2,0,0,0,1,3),(787,248,14,7000,'2025/26',2,0,0,0,1,3),(788,248,18,5850,'2025/26',2,0,0,0,1,3),(789,248,19,5850,'2025/26',2,0,0,0,1,3),(790,287,14,7000,'2025/26',2,0,0,0,1,3),(791,287,18,5850,'2025/26',2,0,0,0,1,3),(792,287,19,5850,'2025/26',2,0,0,0,1,3),(793,280,14,7000,'2025/26',2,0,5700,0,1,3),(794,280,18,5850,'2025/26',2,0,0,0,1,3),(795,280,19,5850,'2025/26',2,0,0,0,1,3),(796,273,14,7000,'2025/26',2,0,0,0,1,3),(797,273,18,5850,'2025/26',2,0,0,0,1,3),(798,273,19,5850,'2025/26',2,0,0,0,1,3),(799,266,14,7000,'2025/26',2,0,1000,0,1,3),(800,266,18,5850,'2025/26',2,0,0,0,1,3),(801,266,19,5850,'2025/26',2,0,0,0,1,3),(802,259,14,7000,'2025/26',2,0,550,0,1,3),(803,259,18,5850,'2025/26',2,0,0,0,1,3),(804,259,19,5850,'2025/26',2,0,0,0,1,3),(805,252,14,7000,'2025/26',2,0,0,0,1,3),(806,252,18,5850,'2025/26',2,0,0,0,1,3),(807,252,19,5850,'2025/26',2,0,0,0,1,3),(808,284,14,7000,'2025/26',2,0,7000,0,1,3),(809,284,18,5850,'2025/26',2,0,0,0,1,3),(810,284,19,5850,'2025/26',2,0,0,0,1,3),(811,277,14,7000,'2025/26',2,0,0,0,1,3),(812,277,18,5850,'2025/26',2,0,0,0,1,3),(813,277,19,5850,'2025/26',2,0,0,0,1,3),(814,270,14,7000,'2025/26',2,0,7000,0,1,3),(815,270,18,5850,'2025/26',2,0,0,0,1,3),(816,270,19,5850,'2025/26',2,0,0,0,1,3),(817,263,14,7000,'2025/26',2,0,1000,0,1,3),(818,263,18,5850,'2025/26',2,0,0,0,1,3),(819,263,19,5850,'2025/26',2,0,0,0,1,3),(820,256,14,7000,'2025/26',2,0,4000,0,1,3),(821,256,18,5850,'2025/26',2,0,0,0,1,3),(822,256,19,5850,'2025/26',2,0,0,0,1,3),(823,249,14,7000,'2025/26',2,0,4000,0,1,3),(824,249,18,5850,'2025/26',2,0,0,0,1,3),(825,249,19,5850,'2025/26',2,0,0,0,1,3),(826,281,14,7000,'2025/26',2,0,1000,0,1,3),(827,281,18,5850,'2025/26',2,0,0,0,1,3),(828,281,19,5850,'2025/26',2,0,0,0,1,3),(829,274,14,7000,'2025/26',2,0,5000,0,1,3),(830,274,18,5850,'2025/26',2,0,0,0,1,3),(831,274,19,5850,'2025/26',2,0,0,0,1,3),(832,267,14,7000,'2025/26',2,0,2000,0,1,3),(833,267,18,5850,'2025/26',2,0,0,0,1,3),(834,267,19,5850,'2025/26',2,0,0,0,1,3),(835,260,14,7000,'2025/26',2,0,0,0,1,3),(836,260,18,5850,'2025/26',2,0,0,0,1,3),(837,260,19,5850,'2025/26',2,0,0,0,1,3),(838,253,14,7000,'2025/26',2,0,7000,0,1,3),(839,253,18,5850,'2025/26',2,0,0,0,1,3),(840,253,19,5850,'2025/26',2,0,0,0,1,3),(841,285,14,7000,'2025/26',2,0,7000,0,1,3),(842,285,18,5850,'2025/26',2,0,0,0,1,3),(843,285,19,5850,'2025/26',2,0,0,0,1,3),(844,278,14,7000,'2025/26',2,0,5000,0,1,3),(845,278,18,5850,'2025/26',2,0,0,0,1,3),(846,278,19,5850,'2025/26',2,0,0,0,1,3),(847,271,14,7000,'2025/26',2,0,6700,0,1,3),(848,271,18,5850,'2025/26',2,0,0,0,1,3),(849,271,19,5850,'2025/26',2,0,0,0,1,3),(850,264,14,7000,'2025/26',2,0,0,0,1,3),(851,264,18,5850,'2025/26',2,0,0,0,1,3),(852,264,19,5850,'2025/26',2,0,0,0,1,3),(853,257,14,7000,'2025/26',2,0,7000,0,1,3),(854,257,18,5850,'2025/26',2,0,1000,0,1,3),(855,257,19,5850,'2025/26',2,0,0,0,1,3),(856,250,14,7000,'2025/26',2,0,7000,0,1,3),(857,250,18,5850,'2025/26',2,0,0,0,1,3),(858,250,19,5850,'2025/26',2,0,0,0,1,3),(859,282,14,7000,'2025/26',2,0,7000,0,1,3),(860,282,18,5850,'2025/26',2,0,0,0,1,3),(861,282,19,5850,'2025/26',2,0,0,0,1,3),(862,315,15,7000,'2025/26',2,0,3000,0,1,3),(863,315,20,5950,'2025/26',2,0,0,0,1,3),(864,315,21,5950,'2025/26',2,0,0,0,1,3),(865,308,15,7000,'2025/26',2,0,3000,0,1,3),(866,308,20,5950,'2025/26',2,0,0,0,1,3),(867,308,21,5950,'2025/26',2,0,0,0,1,3),(868,301,15,7000,'2025/26',2,0,7000,0,1,3),(869,301,20,5950,'2025/26',2,0,0,0,1,3),(870,301,21,5950,'2025/26',2,0,0,0,1,3),(871,294,15,7000,'2025/26',2,0,1000,0,1,3),(872,294,20,5950,'2025/26',2,0,0,0,1,3),(873,294,21,5950,'2025/26',2,0,0,0,1,3),(874,326,15,7000,'2025/26',2,0,1000,0,1,3),(875,326,20,5950,'2025/26',2,0,0,0,1,3),(876,326,21,5950,'2025/26',2,0,0,0,1,3),(877,319,15,7000,'2025/26',2,0,2000,0,1,3),(878,319,20,5950,'2025/26',2,0,0,0,1,3),(879,319,21,5950,'2025/26',2,0,0,0,1,3),(880,312,15,7000,'2025/26',2,0,7000,0,1,3),(881,312,20,5950,'2025/26',2,0,0,0,1,3),(882,312,21,5950,'2025/26',2,0,0,0,1,3),(883,305,15,7000,'2025/26',2,0,0,0,1,3),(884,305,20,5950,'2025/26',2,0,0,0,1,3),(885,305,21,5950,'2025/26',2,0,0,0,1,3),(886,298,15,7000,'2025/26',2,0,2000,0,1,3),(887,298,20,5950,'2025/26',2,0,0,0,1,3),(888,298,21,5950,'2025/26',2,0,0,0,1,3),(889,291,15,7000,'2025/26',2,0,5000,0,1,3),(890,291,20,5950,'2025/26',2,0,0,0,1,3),(891,291,21,5950,'2025/26',2,0,0,0,1,3),(892,323,15,7000,'2025/26',2,0,2100,0,1,3),(893,323,20,5950,'2025/26',2,0,0,0,1,3),(894,323,21,5950,'2025/26',2,0,0,0,1,3),(895,316,15,7000,'2025/26',2,0,5000,0,1,3),(896,316,20,5950,'2025/26',2,0,0,0,1,3),(897,316,21,5950,'2025/26',2,0,0,0,1,3),(898,309,15,7000,'2025/26',2,0,4000,0,1,3),(899,309,20,5950,'2025/26',2,0,0,0,1,3),(900,309,21,5950,'2025/26',2,0,0,0,1,3),(901,302,15,7000,'2025/26',2,0,1500,0,1,3),(902,302,20,5950,'2025/26',2,0,0,0,1,3),(903,302,21,5950,'2025/26',2,0,0,0,1,3),(904,295,15,7000,'2025/26',2,0,0,0,1,3),(905,295,20,5950,'2025/26',2,0,0,0,1,3),(906,295,21,5950,'2025/26',2,0,0,0,1,3),(907,288,15,7000,'2025/26',2,0,1000,0,1,3),(908,288,20,5950,'2025/26',2,0,0,0,1,3),(909,288,21,5950,'2025/26',2,0,0,0,1,3),(910,327,15,7000,'2025/26',2,0,7000,0,1,3),(911,327,20,5950,'2025/26',2,0,0,0,1,3),(912,327,21,5950,'2025/26',2,0,0,0,1,3),(913,320,15,7000,'2025/26',2,0,0,0,1,3),(914,320,20,5950,'2025/26',2,0,0,0,1,3),(915,320,21,5950,'2025/26',2,0,0,0,1,3),(916,313,15,7000,'2025/26',2,0,1900,0,1,3),(917,313,20,5950,'2025/26',2,0,0,0,1,3),(918,313,21,5950,'2025/26',2,0,0,0,1,3),(919,306,15,7000,'2025/26',2,0,2000,0,1,3),(920,306,20,5950,'2025/26',2,0,0,0,1,3),(921,306,21,5950,'2025/26',2,0,0,0,1,3),(922,299,15,7000,'2025/26',2,0,7000,0,1,3),(923,299,20,5950,'2025/26',2,0,0,0,1,3),(924,299,21,5950,'2025/26',2,0,0,0,1,3),(925,292,15,7000,'2025/26',2,0,3000,0,1,3),(926,292,20,5950,'2025/26',2,0,0,0,1,3),(927,292,21,5950,'2025/26',2,0,0,0,1,3),(928,324,15,7000,'2025/26',2,0,7000,0,1,3),(929,324,20,5950,'2025/26',2,0,0,0,1,3),(930,324,21,5950,'2025/26',2,0,0,0,1,3),(931,317,15,7000,'2025/26',2,0,4000,0,1,3),(932,317,20,5950,'2025/26',2,0,0,0,1,3),(933,317,21,5950,'2025/26',2,0,0,0,1,3),(934,310,15,7000,'2025/26',2,0,0,0,1,3),(935,310,20,5950,'2025/26',2,0,0,0,1,3),(936,310,21,5950,'2025/26',2,0,0,0,1,3),(937,303,15,7000,'2025/26',2,0,0,0,1,3),(938,303,20,5950,'2025/26',2,0,0,0,1,3),(939,303,21,5950,'2025/26',2,0,0,0,1,3),(940,296,15,7000,'2025/26',2,0,0,0,1,3),(941,296,20,5950,'2025/26',2,0,0,0,1,3),(942,296,21,5950,'2025/26',2,0,0,0,1,3),(943,289,15,7000,'2025/26',2,0,7000,0,1,3),(944,289,20,5950,'2025/26',2,0,5950,0,1,3),(945,289,21,5950,'2025/26',2,0,2050,0,1,3),(946,321,15,7000,'2025/26',2,0,2000,0,1,3),(947,321,20,5950,'2025/26',2,0,0,0,1,3),(948,321,21,5950,'2025/26',2,0,0,0,1,3),(949,314,15,7000,'2025/26',2,0,7000,0,1,3),(950,314,20,5950,'2025/26',2,0,0,0,1,3),(951,314,21,5950,'2025/26',2,0,0,0,1,3),(952,307,15,7000,'2025/26',2,0,0,0,1,3),(953,307,20,5950,'2025/26',2,0,0,0,1,3),(954,307,21,5950,'2025/26',2,0,0,0,1,3),(955,300,15,7000,'2025/26',2,0,5000,0,1,3),(956,300,20,5950,'2025/26',2,0,0,0,1,3),(957,300,21,5950,'2025/26',2,0,0,0,1,3),(958,293,15,7000,'2025/26',2,0,1000,0,1,3),(959,293,20,5950,'2025/26',2,0,0,0,1,3),(960,293,21,5950,'2025/26',2,0,0,0,1,3),(961,325,15,7000,'2025/26',2,0,0,0,1,3),(962,325,20,5950,'2025/26',2,0,0,0,1,3),(963,325,21,5950,'2025/26',2,0,0,0,1,3),(964,318,15,7000,'2025/26',2,0,3000,0,1,3),(965,318,20,5950,'2025/26',2,0,0,0,1,3),(966,318,21,5950,'2025/26',2,0,0,0,1,3),(967,311,15,7000,'2025/26',2,0,2000,0,1,3),(968,311,20,5950,'2025/26',2,0,0,0,1,3),(969,311,21,5950,'2025/26',2,0,0,0,1,3),(970,304,15,7000,'2025/26',2,0,7000,0,1,3),(971,304,20,5950,'2025/26',2,0,0,0,1,3),(972,304,21,5950,'2025/26',2,0,0,0,1,3),(973,297,15,7000,'2025/26',2,0,0,0,1,3),(974,297,20,5950,'2025/26',2,0,0,0,1,3),(975,297,21,5950,'2025/26',2,0,0,0,1,3),(976,290,15,7000,'2025/26',2,0,0,0,1,3),(977,290,20,5950,'2025/26',2,0,0,0,1,3),(978,290,21,5950,'2025/26',2,0,0,0,1,3),(979,322,15,7000,'2025/26',2,0,7000,0,1,3),(980,322,20,5950,'2025/26',2,0,0,0,1,3),(981,322,21,5950,'2025/26',2,0,0,0,1,3),(994,333,1,5500,'2025/26',2,0,5500,0,1,331),(995,333,2,4750,'2025/26',2,0,0,0,1,331),(996,333,3,4750,'2025/26',2,0,0,0,1,331),(997,335,1,5500,'2025/26',2,0,5500,0,1,2),(998,335,2,4750,'2025/26',2,0,0,0,1,2),(999,335,3,4750,'2025/26',2,0,0,0,1,2),(1000,336,1,5500,'2025/26',2,0,0,0,1,2),(1001,336,2,4750,'2025/26',2,0,0,0,1,2),(1002,336,3,4750,'2025/26',2,0,0,0,1,2),(1003,337,1,5500,'2025/26',2,0,5500,0,1,2),(1004,337,2,4750,'2025/26',2,0,0,0,1,2),(1005,337,3,4750,'2025/26',2,0,0,0,1,2),(1006,334,1,5500,'2025/26',2,0,5500,0,1,2),(1007,334,2,4750,'2025/26',2,0,0,0,1,2),(1008,334,3,4750,'2025/26',2,0,0,0,1,2),(1009,328,1,5500,'2025/26',2,0,5000,0,1,2),(1010,328,2,4750,'2025/26',2,0,0,0,1,2),(1011,328,3,4750,'2025/26',2,0,0,0,1,2),(1012,329,1,5500,'2025/26',2,0,5500,0,1,2),(1013,329,2,4750,'2025/26',2,0,4750,0,1,2),(1014,329,3,4750,'2025/26',2,0,4750,0,1,2),(1015,330,1,5500,'2025/26',2,0,5500,0,1,2),(1016,330,2,4750,'2025/26',2,0,4500,0,1,2),(1017,330,3,4750,'2025/26',2,0,0,0,1,2),(1018,331,1,5500,'2025/26',2,0,5000,0,1,2),(1019,331,2,4750,'2025/26',2,0,0,0,1,2),(1020,331,3,4750,'2025/26',2,0,0,0,1,2),(1021,332,1,5500,'2025/26',2,0,5500,0,1,2),(1022,332,2,4750,'2025/26',2,0,4750,0,1,2),(1023,332,3,4750,'2025/26',2,0,4750,0,1,2),(1024,338,1,5500,'2025/26',2,0,5500,0,1,2),(1025,338,2,4750,'2025/26',2,0,0,0,1,2),(1026,338,3,4750,'2025/26',2,0,0,0,1,2),(1027,339,4,6000,'2025/26',2,0,6000,0,1,2),(1028,339,5,5250,'2025/26',2,0,4000,0,1,2),(1029,339,8,5250,'2025/26',2,0,0,0,1,2),(1030,340,1,5500,'2025/26',2,0,5500,0,1,2),(1031,340,2,4750,'2025/26',2,0,0,0,1,2),(1032,340,3,4750,'2025/26',2,0,0,0,1,2),(1033,341,1,5500,'2025/26',2,0,1000,0,1,2),(1034,341,2,4750,'2025/26',2,0,0,0,1,2),(1035,341,3,4750,'2025/26',2,0,0,0,1,2),(1036,342,1,5500,'2025/26',2,0,3000,0,1,2),(1037,342,2,4750,'2025/26',2,0,0,0,1,2),(1038,342,3,4750,'2025/26',2,0,0,0,1,2),(1039,343,1,5500,'2025/26',2,0,5500,0,1,2),(1040,343,2,4750,'2025/26',2,0,0,0,1,2),(1041,343,3,4750,'2025/26',2,0,0,0,1,2),(1042,344,1,5500,'2025/26',2,0,3500,0,1,2),(1043,344,2,4750,'2025/26',2,0,0,0,1,2),(1044,344,3,4750,'2025/26',2,0,0,0,1,2),(1045,345,1,5500,'2025/26',2,0,5500,0,1,2),(1046,345,2,4750,'2025/26',2,0,4750,0,1,2),(1047,345,3,4750,'2025/26',2,0,4750,0,1,2),(1048,346,1,5500,'2025/26',2,0,5500,0,1,2),(1049,346,2,4750,'2025/26',2,0,0,0,1,2),(1050,346,3,4750,'2025/26',2,0,0,0,1,2),(1051,347,1,5500,'2025/26',2,0,2500,0,1,2),(1052,347,2,4750,'2025/26',2,0,0,0,1,2),(1053,347,3,4750,'2025/26',2,0,0,0,1,2),(1054,348,1,5500,'2025/26',2,0,5500,0,1,2),(1055,348,2,4750,'2025/26',2,0,0,0,1,2),(1056,348,3,4750,'2025/26',2,0,0,0,1,2),(1057,349,1,5500,'2025/26',2,0,5500,0,1,2),(1058,349,2,4750,'2025/26',2,0,0,0,1,2),(1059,349,3,4750,'2025/26',2,0,0,0,1,2),(1060,350,4,6000,'2025/26',2,0,3000,0,1,2),(1061,350,5,5250,'2025/26',2,0,0,0,1,2),(1062,350,8,5250,'2025/26',2,0,0,0,1,2),(1063,351,1,5500,'2025/26',2,0,5500,0,1,2),(1064,351,2,4750,'2025/26',2,0,0,0,1,2),(1065,351,3,4750,'2025/26',2,0,0,0,1,2),(1066,352,15,7000,'2025/26',2,0,3000,0,1,2),(1067,352,20,5950,'2025/26',2,0,0,0,1,2),(1068,352,21,5950,'2025/26',2,0,0,0,1,2),(1069,353,1,5500,'2025/26',2,0,5500,0,1,2),(1070,353,2,4750,'2025/26',2,0,0,0,1,2),(1071,353,3,4750,'2025/26',2,0,0,0,1,2),(1072,354,1,5500,'2025/26',2,0,5500,0,1,2),(1073,354,2,4750,'2025/26',2,0,0,0,1,2),(1074,354,3,4750,'2025/26',2,0,0,0,1,2),(1075,355,1,5500,'2025/26',2,0,5500,0,1,2),(1076,355,2,4750,'2025/26',2,0,0,0,1,2),(1077,355,3,4750,'2025/26',2,0,0,0,1,2),(1078,356,4,6000,'2025/26',2,0,6000,0,1,2),(1079,356,5,5250,'2025/26',2,0,0,0,1,2),(1080,356,8,5250,'2025/26',2,0,0,0,1,2),(1081,357,1,5500,'2025/26',2,0,5500,0,1,2),(1082,357,2,4750,'2025/26',2,0,0,0,1,2),(1083,357,3,4750,'2025/26',2,0,0,0,1,2),(1084,36,6,5250,'2025/26',2,0,0,0,1,2),(1085,36,9,5250,'2025/26',2,0,0,0,1,2),(1086,36,11,6500,'2025/26',2,0,6500,0,1,2),(1087,358,1,5500,'2025/26',2,0,5500,0,1,2),(1088,358,2,4750,'2025/26',2,0,0,0,1,2),(1089,358,3,4750,'2025/26',2,0,0,0,1,2),(1090,359,4,6000,'2025/26',2,0,6000,0,1,2),(1091,359,5,5250,'2025/26',2,0,0,0,1,2),(1092,359,8,5250,'2025/26',2,0,0,0,1,2),(1093,360,1,5500,'2025/26',2,0,3500,0,1,2),(1094,360,2,4750,'2025/26',2,0,0,0,1,2),(1095,360,3,4750,'2025/26',2,0,0,0,1,2),(1096,361,1,5500,'2025/26',2,0,5500,0,1,2),(1097,361,2,4750,'2025/26',2,0,0,0,1,2),(1098,361,3,4750,'2025/26',2,0,0,0,1,2),(1099,362,4,6000,'2025/26',2,0,3000,0,1,2),(1100,362,5,5250,'2025/26',2,0,0,0,1,2),(1101,362,8,5250,'2025/26',2,0,0,0,1,2),(1102,363,1,5500,'2025/26',2,0,5000,0,1,2),(1103,363,2,4750,'2025/26',2,0,0,0,1,2),(1104,363,3,4750,'2025/26',2,0,0,0,1,2),(1105,364,1,5500,'2025/26',2,0,5500,0,1,2),(1106,364,2,4750,'2025/26',2,0,0,0,1,2),(1107,364,3,4750,'2025/26',2,0,0,0,1,2),(1108,365,1,5500,'2025/26',2,0,5000,0,1,2),(1109,365,2,4750,'2025/26',2,0,0,0,1,2),(1110,365,3,4750,'2025/26',2,0,0,0,1,2),(1111,366,1,5500,'2025/26',2,0,5500,0,1,2),(1112,366,2,4750,'2025/26',2,0,0,0,1,2),(1113,366,3,4750,'2025/26',2,0,0,0,1,2),(1114,367,1,5500,'2025/26',2,0,3000,0,1,2),(1115,367,2,4750,'2025/26',2,0,0,0,1,2),(1116,367,3,4750,'2025/26',2,0,0,0,1,2),(1117,368,1,5500,'2025/26',2,0,5000,0,1,2),(1118,368,2,4750,'2025/26',2,0,0,0,1,2),(1119,368,3,4750,'2025/26',2,0,0,0,1,2),(1120,370,7,5250,'2025/26',2,0,0,0,1,2),(1121,370,10,5250,'2025/26',2,0,0,0,1,2),(1122,370,12,7000,'2025/26',2,0,7000,0,1,2),(1123,371,1,5500,'2025/26',2,0,5500,0,1,2),(1124,371,2,4750,'2025/26',2,0,0,0,1,2),(1125,371,3,4750,'2025/26',2,0,0,0,1,2),(1126,372,1,5500,'2025/26',2,0,3000,0,1,2),(1127,372,2,4750,'2025/26',2,0,0,0,1,2),(1128,372,3,4750,'2025/26',2,0,0,0,1,2),(1129,369,1,5500,'2025/26',2,0,5000,0,1,2),(1130,369,2,4750,'2025/26',2,0,0,0,1,2),(1131,369,3,4750,'2025/26',2,0,0,0,1,2),(1132,373,1,5500,'2025/26',2,0,5500,0,1,2),(1133,373,2,4750,'2025/26',2,0,0,0,1,2),(1134,373,3,4750,'2025/26',2,0,0,0,1,2),(1135,375,1,5500,'2025/26',2,0,2500,0,1,2),(1136,375,2,4750,'2025/26',2,0,0,0,1,2),(1137,375,3,4750,'2025/26',2,0,0,0,1,2),(1138,376,1,5500,'2025/26',2,0,5500,0,1,2),(1139,376,2,4750,'2025/26',2,0,0,0,1,2),(1140,376,3,4750,'2025/26',2,0,0,0,1,2),(1141,378,1,5500,'2025/26',2,0,5500,0,1,2),(1142,378,2,4750,'2025/26',2,0,0,0,1,2),(1143,378,3,4750,'2025/26',2,0,0,0,1,2),(1144,381,1,5500,'2025/26',2,0,5500,0,1,2),(1145,381,2,4750,'2025/26',2,0,0,0,1,2),(1146,381,3,4750,'2025/26',2,0,0,0,1,2),(1150,386,13,7000,'2025/26',2,0,2000,0,1,2),(1151,386,16,5750,'2025/26',2,0,0,0,1,2),(1152,386,17,5750,'2025/26',2,0,0,0,1,2),(1153,387,1,5500,'2025/26',2,0,5500,0,1,2),(1154,387,2,4750,'2025/26',2,0,0,0,1,2),(1155,387,3,4750,'2025/26',2,0,0,0,1,2),(1156,388,1,5500,'2025/26',2,0,3000,0,1,2),(1157,388,2,4750,'2025/26',2,0,0,0,1,2),(1158,388,3,4750,'2025/26',2,0,0,0,1,2),(1162,390,1,5500,'2025/26',2,0,5500,0,1,2),(1163,390,2,4750,'2025/26',2,0,0,0,1,2),(1164,390,3,4750,'2025/26',2,0,0,0,1,2),(1165,391,1,5500,'2025/26',2,0,5500,0,1,2),(1166,391,2,4750,'2025/26',2,0,0,0,1,2),(1167,391,3,4750,'2025/26',2,0,0,0,1,2),(1168,374,1,5500,'2025/26',2,0,5500,0,1,2),(1169,374,2,4750,'2025/26',2,0,0,0,1,2),(1170,374,3,4750,'2025/26',2,0,0,0,1,2),(1171,377,1,5500,'2025/26',2,0,5500,0,1,2),(1172,377,2,4750,'2025/26',2,0,0,0,1,2),(1173,377,3,4750,'2025/26',2,0,0,0,1,2),(1174,384,1,5500,'2025/26',2,0,3000,0,1,2),(1175,384,2,4750,'2025/26',2,0,0,0,1,2),(1176,384,3,4750,'2025/26',2,0,0,0,1,2),(1177,380,1,5500,'2025/26',2,0,3500,0,1,2),(1178,380,2,4750,'2025/26',2,0,0,0,1,2),(1179,380,3,4750,'2025/26',2,0,0,0,1,2),(1180,382,1,5500,'2025/26',2,0,2500,0,1,2),(1181,382,2,4750,'2025/26',2,0,0,0,1,2),(1182,382,3,4750,'2025/26',2,0,0,0,1,2),(1183,385,1,5500,'2025/26',2,0,2000,0,1,2),(1184,385,2,4750,'2025/26',2,0,0,0,1,2),(1185,385,3,4750,'2025/26',2,0,0,0,1,2),(1186,383,1,5500,'2025/26',2,0,5000,0,1,2),(1187,383,2,4750,'2025/26',2,0,0,0,1,2),(1188,383,3,4750,'2025/26',2,0,0,0,1,2),(1189,34,6,5250,'2025/26',2,0,0,0,1,2),(1190,34,9,5250,'2025/26',2,0,0,0,1,2),(1191,34,11,6500,'2025/26',2,0,6500,0,1,2),(1192,389,6,5250,'2025/26',2,0,0,0,1,2),(1193,389,9,5250,'2025/26',2,0,0,0,1,2),(1194,389,11,6500,'2025/26',2,0,2000,0,1,2),(1195,392,1,5500,'2025/26',2,0,3000,0,1,2),(1196,392,2,4750,'2025/26',2,0,0,0,1,2),(1197,392,3,4750,'2025/26',2,0,0,0,1,2),(1198,393,15,7000,'2025/26',2,0,5000,0,1,2),(1199,393,20,5950,'2025/26',2,0,0,0,1,2),(1200,393,21,5950,'2025/26',2,0,0,0,1,2),(1201,394,6,5250,'2025/26',2,0,0,0,1,2),(1202,394,9,5250,'2025/26',2,0,0,0,1,2),(1203,394,11,6500,'2025/26',2,0,5000,0,1,2),(1204,395,1,5500,'2025/26',2,0,3000,0,1,2),(1205,395,2,4750,'2025/26',2,0,0,0,1,2),(1206,395,3,4750,'2025/26',2,0,0,0,1,2),(1207,396,1,5500,'2025/26',2,0,3000,0,1,2),(1208,396,2,4750,'2025/26',2,0,0,0,1,2),(1209,396,3,4750,'2025/26',2,0,0,0,1,2),(1210,397,1,5500,'2025/26',2,0,5500,0,1,2),(1211,397,2,4750,'2025/26',2,0,0,0,1,2),(1212,397,3,4750,'2025/26',2,0,0,0,1,2),(1213,398,15,7000,'2025/26',2,0,0,0,1,2),(1214,398,20,5950,'2025/26',2,0,0,0,1,2),(1215,398,21,5950,'2025/26',2,0,0,0,1,2),(1216,379,15,7000,'2025/26',2,0,4000,0,1,2),(1217,379,20,5950,'2025/26',2,0,0,0,1,2),(1218,379,21,5950,'2025/26',2,0,0,0,1,2),(1219,399,1,5500,'2025/26',2,0,2000,0,1,2),(1220,399,2,4750,'2025/26',2,0,0,0,1,2),(1221,399,3,4750,'2025/26',2,0,0,0,1,2),(1222,400,1,5500,'2025/26',2,0,0,0,1,2),(1223,400,2,4750,'2025/26',2,0,0,0,1,2),(1224,400,3,4750,'2025/26',2,0,0,0,1,2);
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
  `userid` int(11) DEFAULT NULL,
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
-- Table structure for table `h_appointment`
--

DROP TABLE IF EXISTS `h_appointment`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `h_appointment` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `stdid` int(11) DEFAULT NULL,
  `externalid` varchar(100) NOT NULL,
  `appointmentdate` date DEFAULT NULL,
  `appointmenttime` varchar(100) DEFAULT NULL,
  `createduserid` int(11) DEFAULT NULL,
  `updateduserid` int(11) DEFAULT NULL,
  `createddate` date DEFAULT NULL,
  `branchid` int(11) DEFAULT NULL,
  `academicyear` varchar(20) DEFAULT NULL,
  `status` varchar(100) DEFAULT NULL,
  `appointmentstarttime` varchar(100) DEFAULT NULL,
  `appointmentendtime` varchar(100) DEFAULT NULL,
  `totaltime` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `h_appointment_FK` (`stdid`),
  CONSTRAINT `h_appointment_FK` FOREIGN KEY (`stdid`) REFERENCES `parents` (`pid`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `h_appointment`
--

LOCK TABLES `h_appointment` WRITE;
/*!40000 ALTER TABLE `h_appointment` DISABLE KEYS */;
/*!40000 ALTER TABLE `h_appointment` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `h_cases`
--

DROP TABLE IF EXISTS `h_cases`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `h_cases` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `court` varchar(400) DEFAULT NULL,
  `casetitle` varchar(1000) DEFAULT NULL,
  `courtname` varchar(400) DEFAULT NULL,
  `status` varchar(50) DEFAULT NULL,
  `dateofdispose` date DEFAULT NULL,
  `filetaken` varchar(50) DEFAULT NULL,
  `filetakenby` varchar(50) DEFAULT NULL,
  `filetakenbyname` varchar(200) DEFAULT NULL,
  `filetakenbynumber` varchar(20) DEFAULT NULL,
  `remarks` varchar(1000) DEFAULT NULL,
  `createddate` date DEFAULT NULL,
  `updateddate` date DEFAULT NULL,
  `createduserid` int(11) DEFAULT NULL,
  `updateduserid` int(11) DEFAULT NULL,
  `branchid` int(11) DEFAULT NULL,
  `assignto` int(11) DEFAULT NULL,
  `sid` int(11) DEFAULT NULL,
  `casenumber` varchar(100) DEFAULT NULL,
  `fileno` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `h_cases`
--

LOCK TABLES `h_cases` WRITE;
/*!40000 ALTER TABLE `h_cases` DISABLE KEYS */;
/*!40000 ALTER TABLE `h_cases` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `h_caveat`
--

DROP TABLE IF EXISTS `h_caveat`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `h_caveat` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `court` varchar(400) DEFAULT NULL,
  `caveattitle` varchar(1000) DEFAULT NULL,
  `courtname` varchar(400) DEFAULT NULL,
  `status` varchar(50) DEFAULT NULL,
  `dateofdispose` date DEFAULT NULL,
  `filetaken` varchar(50) DEFAULT NULL,
  `filetakenby` varchar(50) DEFAULT NULL,
  `filetakenbyname` varchar(200) DEFAULT NULL,
  `filetakenbynumber` varchar(20) DEFAULT NULL,
  `remarks` varchar(1000) DEFAULT NULL,
  `createddate` date DEFAULT NULL,
  `updateddate` date DEFAULT NULL,
  `createduserid` int(11) DEFAULT NULL,
  `updateduserid` int(11) DEFAULT NULL,
  `branchid` int(11) DEFAULT NULL,
  `assignto` int(11) DEFAULT NULL,
  `sid` int(11) DEFAULT NULL,
  `caveatnumber` varchar(100) DEFAULT NULL,
  `fileno` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `h_caveat`
--

LOCK TABLES `h_caveat` WRITE;
/*!40000 ALTER TABLE `h_caveat` DISABLE KEYS */;
/*!40000 ALTER TABLE `h_caveat` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `h_job`
--

DROP TABLE IF EXISTS `h_job`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `h_job` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `externalid` varchar(50) DEFAULT NULL,
  `staffid` int(11) DEFAULT NULL,
  `status` varchar(20) DEFAULT NULL,
  `response` varchar(500) DEFAULT NULL,
  `createduserid` int(11) DEFAULT NULL,
  `updateduserid` int(11) DEFAULT NULL,
  `createddate` date DEFAULT NULL,
  `updateddate` date DEFAULT NULL,
  `branchid` int(11) DEFAULT NULL,
  `academicyear` varchar(20) DEFAULT NULL,
  `feedback` varchar(500) DEFAULT NULL,
  `expecteddeliverydate` date DEFAULT NULL,
  `referredby` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `h_job_FK_1` (`staffid`),
  CONSTRAINT `h_job_FK_1` FOREIGN KEY (`staffid`) REFERENCES `teacher` (`tid`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `h_job`
--

LOCK TABLES `h_job` WRITE;
/*!40000 ALTER TABLE `h_job` DISABLE KEYS */;
/*!40000 ALTER TABLE `h_job` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `h_task`
--

DROP TABLE IF EXISTS `h_task`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `h_task` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `jobid` int(11) NOT NULL,
  `tasks` varchar(500) DEFAULT NULL,
  `description` varchar(1000) DEFAULT NULL,
  `assignto` int(11) DEFAULT NULL,
  `expecteddeliverydate` date DEFAULT NULL,
  `remarks` varchar(500) DEFAULT NULL,
  `branchid` int(11) DEFAULT NULL,
  `updateddate` date DEFAULT NULL,
  `updateduserid` int(11) DEFAULT NULL,
  `status` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `h_task_FK` (`jobid`),
  KEY `h_task_FK_1` (`assignto`),
  CONSTRAINT `h_task_FK` FOREIGN KEY (`jobid`) REFERENCES `h_job` (`id`),
  CONSTRAINT `h_task_FK_1` FOREIGN KEY (`assignto`) REFERENCES `teacher` (`tid`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `h_task`
--

LOCK TABLES `h_task` WRITE;
/*!40000 ALTER TABLE `h_task` DISABLE KEYS */;
/*!40000 ALTER TABLE `h_task` ENABLE KEYS */;
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
  `userid` int(11) DEFAULT NULL,
  PRIMARY KEY (`idleaveapplication`),
  KEY `idteacherleaveapp_idx` (`idteacher`),
  CONSTRAINT `idteacherleaveapp` FOREIGN KEY (`idteacher`) REFERENCES `teacher` (`tid`) ON DELETE CASCADE ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `hr_leaveapplication`
--

LOCK TABLES `hr_leaveapplication` WRITE;
/*!40000 ALTER TABLE `hr_leaveapplication` DISABLE KEYS */;
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
  `userid` int(11) DEFAULT NULL,
  PRIMARY KEY (`idleavedetails`),
  KEY `leavetype_idx` (`idleavetypemaster`),
  KEY `teacherid_idx` (`idteacher`),
  CONSTRAINT `leavetype` FOREIGN KEY (`idleavetypemaster`) REFERENCES `hr_leavetypemaster` (`idleavetypemaster`) ON DELETE CASCADE ON UPDATE NO ACTION,
  CONSTRAINT `teacherid` FOREIGN KEY (`idteacher`) REFERENCES `teacher` (`tid`) ON DELETE CASCADE ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='		';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `hr_leavedetails`
--

LOCK TABLES `hr_leavedetails` WRITE;
/*!40000 ALTER TABLE `hr_leavedetails` DISABLE KEYS */;
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
  `userid` int(11) DEFAULT NULL,
  PRIMARY KEY (`idleavetypemaster`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `hr_leavetypemaster`
--

LOCK TABLES `hr_leavetypemaster` WRITE;
/*!40000 ALTER TABLE `hr_leavetypemaster` DISABLE KEYS */;
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
  `userid` int(11) DEFAULT NULL,
  PRIMARY KEY (`idpayadvancesalary`),
  KEY `idteacheradv_idx` (`idteacher`),
  CONSTRAINT `idteacheradv` FOREIGN KEY (`idteacher`) REFERENCES `teacher` (`tid`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `hr_payadvancesalary`
--

LOCK TABLES `hr_payadvancesalary` WRITE;
/*!40000 ALTER TABLE `hr_payadvancesalary` DISABLE KEYS */;
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
  `userid` int(11) DEFAULT NULL,
  PRIMARY KEY (`idpaybasic`),
  KEY `idteacher_idx` (`idteacher`),
  CONSTRAINT `teachersid` FOREIGN KEY (`idteacher`) REFERENCES `teacher` (`tid`) ON DELETE CASCADE ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `hr_paybasic`
--

LOCK TABLES `hr_paybasic` WRITE;
/*!40000 ALTER TABLE `hr_paybasic` DISABLE KEYS */;
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
  `userid` int(11) DEFAULT NULL,
  PRIMARY KEY (`idpayhead`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `hr_payhead`
--

LOCK TABLES `hr_payhead` WRITE;
/*!40000 ALTER TABLE `hr_payhead` DISABLE KEYS */;
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
  `userid` int(11) DEFAULT NULL,
  PRIMARY KEY (`idpayheadstaffdetails`),
  KEY `teacherid_idx` (`idteacher`),
  KEY `payheadid_idx` (`payheadid`),
  CONSTRAINT `idteacher` FOREIGN KEY (`idteacher`) REFERENCES `teacher` (`tid`) ON DELETE CASCADE ON UPDATE NO ACTION,
  CONSTRAINT `payheadid` FOREIGN KEY (`payheadid`) REFERENCES `hr_payhead` (`idpayhead`) ON DELETE CASCADE ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `hr_payheadstaffdetails`
--

LOCK TABLES `hr_payheadstaffdetails` WRITE;
/*!40000 ALTER TABLE `hr_payheadstaffdetails` DISABLE KEYS */;
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
  `userid` int(11) DEFAULT NULL,
  PRIMARY KEY (`idpf`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='		';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `hr_pf`
--

LOCK TABLES `hr_pf` WRITE;
/*!40000 ALTER TABLE `hr_pf` DISABLE KEYS */;
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
  `userid` int(11) DEFAULT NULL,
  PRIMARY KEY (`idprocesssalarydetails`),
  KEY `processteacherid_idx` (`teacherid`),
  CONSTRAINT `processteacherid` FOREIGN KEY (`teacherid`) REFERENCES `teacher` (`tid`) ON DELETE CASCADE ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `hr_processsalarydetails`
--

LOCK TABLES `hr_processsalarydetails` WRITE;
/*!40000 ALTER TABLE `hr_processsalarydetails` DISABLE KEYS */;
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
  `userid` int(11) DEFAULT NULL,
  PRIMARY KEY (`idprocesssalarydetailsheads`),
  KEY `idprocesssalary_idx` (`idprocesssalary`),
  CONSTRAINT `idprocesssalary` FOREIGN KEY (`idprocesssalary`) REFERENCES `hr_processsalarydetails` (`idprocesssalarydetails`) ON DELETE CASCADE ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `hr_processsalarydetailsheads`
--

LOCK TABLES `hr_processsalarydetailsheads` WRITE;
/*!40000 ALTER TABLE `hr_processsalarydetailsheads` DISABLE KEYS */;
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
  `userid` int(11) DEFAULT NULL,
  PRIMARY KEY (`lid`),
  KEY `branchid_idx` (`branchid`),
  CONSTRAINT `branchid` FOREIGN KEY (`branchid`) REFERENCES `branch` (`idbranch`) ON DELETE CASCADE ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=407 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `login`
--

LOCK TABLES `login` WRITE;
/*!40000 ALTER TABLE `login` DISABLE KEYS */;
INSERT INTO `login` VALUES (1,'headoffice','headadmin','superadmin',1,1),(2,'admin','adminfnps','admin',2,2),(3,'support','fnpssupport','admin',2,3),(4,'FNPS0001','0','parents',2,4),(5,'FNPS0002','0','parents',2,5),(6,'FNPS0003','0','parents',2,6),(7,'FNPS0004','0','parents',2,7),(8,'FNPS0005','0','parents',2,8),(9,'FNPS0006','0','parents',2,9),(10,'FNPS0007','0','parents',2,10),(11,'FNPS0008','0','parents',2,11),(12,'FNPS0009','0','parents',2,12),(13,'FNPS0010','0','parents',2,13),(14,'FNPS0011','0','parents',2,14),(15,'FNPS0012','0','parents',2,15),(16,'FNPS0013','0','parents',2,16),(17,'FNPS0014','0','parents',2,17),(18,'FNPS0015','0','parents',2,18),(19,'FNPS0016','0','parents',2,19),(20,'FNPS0017','0','parents',2,20),(21,'FNPS0018','0','parents',2,21),(22,'FNPS0019','0','parents',2,22),(23,'FNPS0020','0','parents',2,23),(24,'FNPS0021','0','parents',2,24),(25,'FNPS0022','0','parents',2,25),(26,'FNPS0023','0','parents',2,26),(27,'FNPS0024','0','parents',2,27),(28,'FNPS0025','0','parents',2,28),(29,'FNPS0026','0','parents',2,29),(30,'FNPS0027','0','parents',2,30),(31,'FNPS0028','0','parents',2,31),(32,'FNPS0029','0','parents',2,32),(33,'FNPS0030','0','parents',2,33),(34,'FNPS0031','0','parents',2,34),(35,'FNPS0032','0','parents',2,35),(36,'FNPS0033','0','parents',2,36),(37,'FNPS0034','0','parents',2,37),(38,'FNPS0035','0','parents',2,38),(39,'FNPS0036','0','parents',2,39),(40,'FNPS0037','0','parents',2,40),(41,'FNPS0038','0','parents',2,41),(42,'FNPS0039','0','parents',2,42),(43,'FNPS0040','0','parents',2,43),(44,'FNPS0041','0','parents',2,44),(45,'FNPS0042','0','parents',2,45),(46,'FNPS0043','0','parents',2,46),(47,'FNPS0044','0','parents',2,47),(48,'FNPS0045','0','parents',2,48),(49,'FNPS0046','0','parents',2,49),(50,'FNPS0047','0','parents',2,50),(51,'FNPS0048','0','parents',2,51),(52,'FNPS0049','0','parents',2,52),(53,'FNPS0050','0','parents',2,53),(54,'FNPS0051','0','parents',2,54),(55,'FNPS0052','0','parents',2,55),(56,'FNPS0053','0','parents',2,56),(57,'FNPS0054','0','parents',2,57),(58,'FNPS0055','0','parents',2,58),(59,'FNPS0056','0','parents',2,59),(60,'FNPS0057','0','parents',2,60),(61,'FNPS0058','0','parents',2,61),(62,'FNPS0059','0','parents',2,62),(63,'FNPS0060','0','parents',2,63),(64,'FNPS0061','0','parents',2,64),(65,'FNPS0062','0','parents',2,65),(66,'FNPS0063','0','parents',2,66),(67,'FNPS0064','0','parents',2,67),(68,'FNPS0065','0','parents',2,68),(69,'FNPS0066','0','parents',2,69),(70,'FNPS0067','0','parents',2,70),(71,'FNPS0068','0','parents',2,71),(72,'FNPS0069','0','parents',2,72),(73,'FNPS0070','0','parents',2,73),(74,'FNPS0071','0','parents',2,74),(75,'FNPS0072','0','parents',2,75),(76,'FNPS0073','0','parents',2,76),(77,'FNPS0074','0','parents',2,77),(78,'FNPS0075','0','parents',2,78),(79,'FNPS0076','0','parents',2,79),(80,'FNPS0077','0','parents',2,80),(81,'FNPS0078','0','parents',2,81),(82,'FNPS0079','0','parents',2,82),(83,'FNPS0080','0','parents',2,83),(84,'FNPS0081','0','parents',2,84),(85,'FNPS0082','0','parents',2,85),(86,'FNPS0083','0','parents',2,86),(87,'FNPS0084','0','parents',2,87),(88,'FNPS0085','0','parents',2,88),(89,'FNPS0086','0','parents',2,89),(90,'FNPS0087','0','parents',2,90),(91,'FNPS0088','0','parents',2,91),(92,'FNPS0089','0','parents',2,92),(93,'FNPS0090','0','parents',2,93),(94,'FNPS0091','0','parents',2,94),(95,'FNPS0092','0','parents',2,95),(96,'FNPS0093','0','parents',2,96),(97,'FNPS0094','0','parents',2,97),(98,'FNPS0095','0','parents',2,98),(99,'FNPS0096','0','parents',2,99),(100,'FNPS0097','0','parents',2,100),(101,'FNPS0098','0','parents',2,101),(102,'FNPS0099','0','parents',2,102),(103,'FNPS0100','0','parents',2,103),(104,'FNPS0101','0','parents',2,104),(105,'FNPS0102','0','parents',2,105),(106,'FNPS0103','0','parents',2,106),(107,'FNPS0104','0','parents',2,107),(108,'FNPS0105','0','parents',2,108),(109,'FNPS0106','0','parents',2,109),(110,'FNPS0107','0','parents',2,110),(111,'FNPS0108','0','parents',2,111),(112,'FNPS0109','0','parents',2,112),(113,'FNPS0110','0','parents',2,113),(114,'FNPS0111','0','parents',2,114),(115,'FNPS0112','0','parents',2,115),(116,'FNPS0113','0','parents',2,116),(117,'FNPS0114','0','parents',2,117),(118,'FNPS0115','0','parents',2,118),(119,'FNPS0116','0','parents',2,119),(120,'FNPS0117','0','parents',2,120),(121,'FNPS0118','0','parents',2,121),(122,'FNPS0119','0','parents',2,122),(123,'FNPS0120','0','parents',2,123),(124,'FNPS0121','0','parents',2,124),(125,'FNPS0122','0','parents',2,125),(126,'FNPS0123','0','parents',2,126),(127,'FNPS0124','0','parents',2,127),(128,'FNPS0125','0','parents',2,128),(129,'FNPS0126','0','parents',2,129),(130,'FNPS0127','0','parents',2,130),(131,'FNPS0128','0','parents',2,131),(132,'FNPS0129','0','parents',2,132),(133,'FNPS0130','0','parents',2,133),(134,'FNPS0131','0','parents',2,134),(135,'FNPS0132','0','parents',2,135),(136,'FNPS0133','0','parents',2,136),(137,'FNPS0134','0','parents',2,137),(138,'FNPS0135','0','parents',2,138),(139,'FNPS0136','0','parents',2,139),(140,'FNPS0137','0','parents',2,140),(141,'FNPS0138','0','parents',2,141),(142,'FNPS0139','0','parents',2,142),(143,'FNPS0140','0','parents',2,143),(144,'FNPS0141','0','parents',2,144),(145,'FNPS0142','0','parents',2,145),(146,'FNPS0143','0','parents',2,146),(147,'FNPS0144','0','parents',2,147),(148,'FNPS0145','0','parents',2,148),(149,'FNPS0146','0','parents',2,149),(150,'FNPS0147','0','parents',2,150),(151,'FNPS0148','0','parents',2,151),(152,'FNPS0149','0','parents',2,152),(153,'FNPS0150','0','parents',2,153),(154,'FNPS0151','0','parents',2,154),(155,'FNPS0152','0','parents',2,155),(156,'FNPS0153','0','parents',2,156),(157,'FNPS0154','0','parents',2,157),(158,'FNPS0155','0','parents',2,158),(159,'FNPS0156','0','parents',2,159),(160,'FNPS0157','0','parents',2,160),(161,'FNPS0158','0','parents',2,161),(162,'FNPS0159','0','parents',2,162),(163,'FNPS0160','0','parents',2,163),(164,'FNPS0161','0','parents',2,164),(165,'FNPS0162','0','parents',2,165),(166,'FNPS0163','0','parents',2,166),(167,'FNPS0164','0','parents',2,167),(168,'FNPS0165','0','parents',2,168),(169,'FNPS0166','0','parents',2,169),(170,'FNPS0167','0','parents',2,170),(171,'FNPS0168','0','parents',2,171),(172,'FNPS0169','0','parents',2,172),(173,'FNPS0170','0','parents',2,173),(174,'FNPS0171','0','parents',2,174),(175,'FNPS0172','0','parents',2,175),(176,'FNPS0173','0','parents',2,176),(177,'FNPS0174','0','parents',2,177),(178,'FNPS0175','0','parents',2,178),(179,'FNPS0176','0','parents',2,179),(180,'FNPS0177','0','parents',2,180),(181,'FNPS0178','0','parents',2,181),(182,'FNPS0179','0','parents',2,182),(183,'FNPS0180','0','parents',2,183),(184,'FNPS0181','0','parents',2,184),(185,'FNPS0182','0','parents',2,185),(186,'FNPS0183','0','parents',2,186),(187,'FNPS0184','0','parents',2,187),(188,'FNPS0185','0','parents',2,188),(189,'FNPS0186','0','parents',2,189),(190,'FNPS0187','0','parents',2,190),(191,'FNPS0188','0','parents',2,191),(192,'FNPS0189','0','parents',2,192),(193,'FNPS0190','0','parents',2,193),(194,'FNPS0191','0','parents',2,194),(195,'FNPS0192','0','parents',2,195),(196,'FNPS0193','0','parents',2,196),(197,'FNPS0194','0','parents',2,197),(198,'FNPS0195','0','parents',2,198),(199,'FNPS0196','0','parents',2,199),(200,'FNPS0197','0','parents',2,200),(201,'FNPS0198','0','parents',2,201),(202,'FNPS0199','0','parents',2,202),(203,'FNPS0200','0','parents',2,203),(204,'FNPS0201','0','parents',2,204),(205,'FNPS0202','0','parents',2,205),(206,'FNPS0203','0','parents',2,206),(207,'FNPS0204','0','parents',2,207),(208,'FNPS0205','0','parents',2,208),(209,'FNPS0206','0','parents',2,209),(210,'FNPS0207','0','parents',2,210),(211,'FNPS0208','0','parents',2,211),(212,'FNPS0209','0','parents',2,212),(213,'FNPS0210','0','parents',2,213),(214,'FNPS0211','0','parents',2,214),(215,'FNPS0212','0','parents',2,215),(216,'FNPS0213','0','parents',2,216),(217,'FNPS0214','0','parents',2,217),(218,'FNPS0215','0','parents',2,218),(219,'FNPS0216','0','parents',2,219),(220,'FNPS0217','0','parents',2,220),(221,'FNPS0218','0','parents',2,221),(222,'FNPS0219','0','parents',2,222),(223,'FNPS0220','0','parents',2,223),(224,'FNPS0221','0','parents',2,224),(225,'FNPS0222','0','parents',2,225),(226,'FNPS0223','0','parents',2,226),(227,'FNPS0224','0','parents',2,227),(228,'FNPS0225','0','parents',2,228),(229,'FNPS0226','0','parents',2,229),(230,'FNPS0227','0','parents',2,230),(231,'FNPS0228','0','parents',2,231),(232,'FNPS0229','0','parents',2,232),(233,'FNPS0230','0','parents',2,233),(234,'FNPS0231','0','parents',2,234),(235,'FNPS0232','0','parents',2,235),(236,'FNPS0233','0','parents',2,236),(237,'FNPS0234','0','parents',2,237),(238,'FNPS0235','0','parents',2,238),(239,'FNPS0236','0','parents',2,239),(240,'FNPS0237','0','parents',2,240),(241,'FNPS0238','0','parents',2,241),(242,'FNPS0239','0','parents',2,242),(243,'FNPS0240','0','parents',2,243),(244,'FNPS0241','0','parents',2,244),(245,'FNPS0242','0','parents',2,245),(246,'FNPS0243','0','parents',2,246),(247,'FNPS0244','0','parents',2,247),(248,'FNPS0245','0','parents',2,248),(249,'FNPS0246','0','parents',2,249),(250,'FNPS0247','0','parents',2,250),(251,'FNPS0248','0','parents',2,251),(252,'FNPS0249','0','parents',2,252),(253,'FNPS0250','0','parents',2,253),(254,'FNPS0251','0','parents',2,254),(255,'FNPS0252','0','parents',2,255),(256,'FNPS0253','0','parents',2,256),(257,'FNPS0254','0','parents',2,257),(258,'FNPS0255','0','parents',2,258),(259,'FNPS0256','0','parents',2,259),(260,'FNPS0257','0','parents',2,260),(261,'FNPS0258','0','parents',2,261),(262,'FNPS0259','0','parents',2,262),(263,'FNPS0260','0','parents',2,263),(264,'FNPS0261','0','parents',2,264),(265,'FNPS0262','0','parents',2,265),(266,'FNPS0263','0','parents',2,266),(267,'FNPS0264','0','parents',2,267),(268,'FNPS0265','0','parents',2,268),(269,'FNPS0266','0','parents',2,269),(270,'FNPS0267','0','parents',2,270),(271,'FNPS0268','0','parents',2,271),(272,'FNPS0269','0','parents',2,272),(273,'FNPS0270','0','parents',2,273),(274,'FNPS0271','0','parents',2,274),(275,'FNPS0272','0','parents',2,275),(276,'FNPS0273','0','parents',2,276),(277,'FNPS0274','0','parents',2,277),(278,'FNPS0275','0','parents',2,278),(279,'FNPS0276','0','parents',2,279),(280,'FNPS0277','0','parents',2,280),(281,'FNPS0278','0','parents',2,281),(282,'FNPS0279','0','parents',2,282),(283,'FNPS0280','0','parents',2,283),(284,'FNPS0281','0','parents',2,284),(285,'FNPS0282','0','parents',2,285),(286,'FNPS0283','0','parents',2,286),(287,'FNPS0284','0','parents',2,287),(288,'FNPS0285','0','parents',2,288),(289,'FNPS0286','0','parents',2,289),(290,'FNPS0287','0','parents',2,290),(291,'FNPS0288','0','parents',2,291),(292,'FNPS0289','0','parents',2,292),(293,'FNPS0290','0','parents',2,293),(294,'FNPS0291','0','parents',2,294),(295,'FNPS0292','0','parents',2,295),(296,'FNPS0293','0','parents',2,296),(297,'FNPS0294','0','parents',2,297),(298,'FNPS0295','0','parents',2,298),(299,'FNPS0296','0','parents',2,299),(300,'FNPS0297','0','parents',2,300),(301,'FNPS0298','0','parents',2,301),(302,'FNPS0299','0','parents',2,302),(303,'FNPS0300','0','parents',2,303),(304,'FNPS0301','0','parents',2,304),(305,'FNPS0302','0','parents',2,305),(306,'FNPS0303','0','parents',2,306),(307,'FNPS0304','0','parents',2,307),(308,'FNPS0305','0','parents',2,308),(309,'FNPS0306','0','parents',2,309),(310,'FNPS0307','0','parents',2,310),(311,'FNPS0308','0','parents',2,311),(312,'FNPS0309','0','parents',2,312),(313,'FNPS0310','0','parents',2,313),(314,'FNPS0311','0','parents',2,314),(315,'FNPS0312','0','parents',2,315),(316,'FNPS0313','0','parents',2,316),(317,'FNPS0314','0','parents',2,317),(318,'FNPS0315','0','parents',2,318),(319,'FNPS0316','0','parents',2,319),(320,'FNPS0317','0','parents',2,320),(321,'FNPS0318','0','parents',2,321),(322,'FNPS0319','0','parents',2,322),(323,'FNPS0320','0','parents',2,323),(324,'FNPS0321','0','parents',2,324),(325,'FNPS0322','0','parents',2,325),(326,'FNPS0323','0','parents',2,326),(327,'FNPS0324','0','parents',2,327),(328,'FNPS0325','0','parents',2,328),(329,'FNPS0326','0','parents',2,329),(330,'FNPS0327','0','parents',2,330),(331,'officeadmin','fathimaschool','admin',2,331),(332,'FNPS01','C7PE','teacher',2,332),(333,'20250328','7502126730','parents',2,333),(334,'20250329','9384214604','parents',2,334),(335,'20250330','9865179865','parents',2,335),(336,'20250331','8072168772','parents',2,336),(337,'20250332','7667120155','parents',2,337),(338,'20250333','7845869776','parents',2,338),(339,'20250334','9659102653','parents',2,339),(340,'20250335','8056085086','parents',2,340),(341,'20250336','8056313946','parents',2,341),(342,'20250337','7845107040','parents',2,342),(343,'20250338','7806977736','parents',2,343),(344,'20250339','8015803976','parents',2,344),(345,'20250340','8438621994','parents',2,345),(346,'20250341','8344889022','parents',2,346),(347,'20250342','8754779754','parents',2,347),(348,'20250343','9790309624','parents',2,348),(349,'20250344','6383848058','parents',2,349),(350,'20250345','8428767208','parents',2,350),(351,'20250346','9025727108','parents',2,351),(352,'20250347','7708423014','parents',2,352),(353,'20250348','8220724992','parents',2,353),(354,'20250349','7092386306','parents',2,354),(355,'20250350','8220129351','parents',2,355),(356,'20250351','9043950703','parents',2,356),(357,'20250352','9345309630','parents',2,357),(358,'20250353','9587509417','parents',2,358),(359,'20250354','9840544952','parents',2,359),(360,'20250355','9003142160','parents',2,360),(361,'20250356','9597655741','parents',2,361),(362,'20250357','8220015465','parents',2,362),(363,'20250358','9999999999','parents',2,363),(364,'20250359','9159813393','parents',2,364),(365,'20250360','9566260905','parents',2,365),(366,'20250361','9043244241','parents',2,366),(367,'20250362','9361597977','parents',2,367),(368,'20250363','6382638976','parents',2,368),(369,'20250364','8754733253','parents',2,369),(370,'20250365','8056469450','parents',2,370),(371,'20250366','9629376901','parents',2,371),(372,'20250367','9500824594','parents',2,372),(373,'20250368','9787640460','parents',2,373),(374,'20250369','6381059945','parents',2,374),(375,'20250370','9677302776','parents',2,375),(376,'20250371','9361934623','parents',2,376),(377,'20250372','0000000000','parents',2,377),(378,'FNPS02','UDHC','teacher',2,378),(379,'20250373','8110906374','parents',2,379),(380,'20250374','9626812031','parents',2,380),(381,'20250375','8754098695','parents',2,381),(382,'20250376','9865105815','parents',2,382),(383,'20250377','8870840978','parents',2,383),(384,'20250378','7845921212','parents',2,384),(385,'20250379','6383744622','parents',2,385),(386,'20250380','6379117712','parents',2,386),(387,'20250381','9566077336','parents',2,387),(388,'20250382','9944437724','parents',2,388),(389,'20250383','7708560928','parents',2,389),(390,'20250384','9344500381','parents',2,390),(391,'20250385','9942587314','parents',2,391),(392,'20250386','9092658212','parents',2,392),(393,'20250387','9487080885','parents',2,393),(394,'20250388','9629234834','parents',2,394),(395,'20250389','7418328013','parents',2,395),(396,'20250390','9566686932','parents',2,396),(397,'20250391','0000000000','parents',2,397),(398,'20250392','6380691110','parents',2,398),(399,'20250393','8124463247','parents',2,399),(400,'20250394','8124463247','parents',2,400),(401,'20250395','7604989364','parents',2,401),(402,'20250396','9003389708','parents',2,402),(403,'20250397','9677070313','parents',2,403),(404,'20250398','0000000000','parents',2,404),(405,'20250399','0000000000','parents',2,405),(406,'20250400','8056706471','parents',2,406);
/*!40000 ALTER TABLE `login` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `markgrade`
--

DROP TABLE IF EXISTS `markgrade`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `markgrade` (
  `id` int(11) NOT NULL,
  `minpercentage` int(11) NOT NULL,
  `maxpercentage` int(11) NOT NULL,
  `branchid` int(11) DEFAULT NULL,
  `status` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `markgrade`
--

LOCK TABLES `markgrade` WRITE;
/*!40000 ALTER TABLE `markgrade` DISABLE KEYS */;
/*!40000 ALTER TABLE `markgrade` ENABLE KEYS */;
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
  `marksobtained` float DEFAULT NULL,
  `academicyear` varchar(20) DEFAULT NULL,
  `branchid` int(11) DEFAULT NULL,
  `userid` int(11) DEFAULT NULL,
  `subgrade` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`marksid`),
  UNIQUE KEY `sid` (`sid`,`subid`,`examid`,`academicyear`),
  KEY `sid_idx` (`sid`),
  KEY `subid_idx` (`subid`),
  KEY `examid_idx` (`examid`),
  CONSTRAINT `examinationid` FOREIGN KEY (`examid`) REFERENCES `exams` (`exid`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `studentid` FOREIGN KEY (`sid`) REFERENCES `student` (`sid`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `subjectid` FOREIGN KEY (`subid`) REFERENCES `subject` (`subid`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `marks`
--

LOCK TABLES `marks` WRITE;
/*!40000 ALTER TABLE `marks` DISABLE KEYS */;
/*!40000 ALTER TABLE `marks` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `mess_card`
--

DROP TABLE IF EXISTS `mess_card`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `mess_card` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `sid` int(11) DEFAULT NULL,
  `validfrom` date DEFAULT NULL,
  `validto` date DEFAULT NULL,
  `userid` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `mess_card`
--

LOCK TABLES `mess_card` WRITE;
/*!40000 ALTER TABLE `mess_card` DISABLE KEYS */;
/*!40000 ALTER TABLE `mess_card` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `mess_invoicedetails`
--

DROP TABLE IF EXISTS `mess_invoicedetails`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `mess_invoicedetails` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `externalid` varchar(200) NOT NULL,
  `supplierreferenceno` varchar(50) NOT NULL,
  `invoicetotal` decimal(18,5) NOT NULL,
  `suppliersid` int(11) DEFAULT NULL,
  `branchid` int(11) NOT NULL,
  `invoicedate` date DEFAULT NULL,
  `entrydate` date DEFAULT NULL,
  `status` varchar(100) DEFAULT NULL,
  `voucherid` int(11) DEFAULT NULL,
  `userid` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `mess_invoicedetails_mess_suppliers_FK` (`suppliersid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `mess_invoicedetails`
--

LOCK TABLES `mess_invoicedetails` WRITE;
/*!40000 ALTER TABLE `mess_invoicedetails` DISABLE KEYS */;
/*!40000 ALTER TABLE `mess_invoicedetails` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `mess_items`
--

DROP TABLE IF EXISTS `mess_items`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `mess_items` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `externalid` varchar(200) NOT NULL,
  `name` varchar(200) DEFAULT NULL,
  `unitofmeasure` varchar(20) DEFAULT NULL,
  `linkedledgerid` int(11) DEFAULT NULL,
  `branchid` int(11) DEFAULT NULL,
  `linkedledgeridexpense` int(11) DEFAULT NULL,
  `userid` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `mess_items_UN` (`name`),
  KEY `mess_items_acc_accountdetails_FK` (`linkedledgerid`),
  KEY `mess_items_acc_accountdetails_FK_1` (`linkedledgeridexpense`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `mess_items`
--

LOCK TABLES `mess_items` WRITE;
/*!40000 ALTER TABLE `mess_items` DISABLE KEYS */;
/*!40000 ALTER TABLE `mess_items` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `mess_pomaster`
--

DROP TABLE IF EXISTS `mess_pomaster`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `mess_pomaster` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `externalid` varchar(45) DEFAULT NULL,
  `supplierid` int(11) DEFAULT NULL,
  `entrydate` date DEFAULT NULL,
  `status` varchar(45) DEFAULT NULL,
  `totalitem` int(11) DEFAULT NULL,
  `totalquantityordered` int(11) DEFAULT NULL,
  `totalquantityreceived` int(11) DEFAULT NULL,
  `branchid` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `mess_pomaster`
--

LOCK TABLES `mess_pomaster` WRITE;
/*!40000 ALTER TABLE `mess_pomaster` DISABLE KEYS */;
/*!40000 ALTER TABLE `mess_pomaster` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `mess_stockavailable`
--

DROP TABLE IF EXISTS `mess_stockavailable`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `mess_stockavailable` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `itemid` int(11) DEFAULT NULL,
  `availablestock` float DEFAULT NULL,
  `minstock` int(11) DEFAULT NULL,
  `branchid` int(11) DEFAULT NULL,
  `userid` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `mess_stockavailable_mess_items_FK` (`itemid`),
  CONSTRAINT `mess_stockavailable_mess_items_FK` FOREIGN KEY (`itemid`) REFERENCES `mess_items` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `mess_stockavailable`
--

LOCK TABLES `mess_stockavailable` WRITE;
/*!40000 ALTER TABLE `mess_stockavailable` DISABLE KEYS */;
/*!40000 ALTER TABLE `mess_stockavailable` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `mess_stockentry`
--

DROP TABLE IF EXISTS `mess_stockentry`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `mess_stockentry` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `externalid` varchar(200) NOT NULL,
  `itemid` int(11) DEFAULT NULL,
  `batchno` varchar(200) DEFAULT NULL,
  `quantity` float DEFAULT NULL,
  `itemunitprice` decimal(18,5) DEFAULT NULL,
  `branchid` int(11) DEFAULT NULL,
  `invoicedetailsid` int(11) NOT NULL,
  `availablequantity` float DEFAULT NULL,
  `status` varchar(100) DEFAULT NULL,
  `userid` int(11) DEFAULT NULL,
  `receiveddate` date DEFAULT NULL,
  `sgst` decimal(18,5) DEFAULT NULL,
  `cgst` decimal(18,5) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `stockentry_mess_items_FK` (`itemid`),
  KEY `mess_stockentry_mess_invoicedetails_FK` (`invoicedetailsid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `mess_stockentry`
--

LOCK TABLES `mess_stockentry` WRITE;
/*!40000 ALTER TABLE `mess_stockentry` DISABLE KEYS */;
/*!40000 ALTER TABLE `mess_stockentry` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `mess_stockmoveinfo`
--

DROP TABLE IF EXISTS `mess_stockmoveinfo`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `mess_stockmoveinfo` (
  `receiptnumber` int(11) NOT NULL AUTO_INCREMENT,
  `sid` int(11) DEFAULT NULL,
  `date` date DEFAULT NULL,
  `totalamount` decimal(10,0) DEFAULT NULL,
  `academicyear` varchar(15) DEFAULT NULL,
  `branchid` int(11) DEFAULT NULL,
  `cancelreceipt` int(11) DEFAULT NULL,
  `branchreceiptnumber` varchar(20) DEFAULT NULL,
  `paymenttype` varchar(100) DEFAULT NULL,
  `userid` int(11) DEFAULT NULL,
  `classsec` varchar(20) DEFAULT NULL,
  `receiptvoucher` varchar(100) DEFAULT NULL,
  `journalvoucher` int(11) DEFAULT NULL,
  `due` decimal(10,0) DEFAULT NULL,
  `misc` decimal(10,0) DEFAULT NULL,
  `studentname` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`receiptnumber`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `mess_stockmoveinfo`
--

LOCK TABLES `mess_stockmoveinfo` WRITE;
/*!40000 ALTER TABLE `mess_stockmoveinfo` DISABLE KEYS */;
/*!40000 ALTER TABLE `mess_stockmoveinfo` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `mess_stockmoves`
--

DROP TABLE IF EXISTS `mess_stockmoves`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `mess_stockmoves` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `externalid` varchar(200) NOT NULL,
  `itemid` int(11) DEFAULT NULL,
  `quantity` float DEFAULT NULL,
  `purpose` varchar(20) DEFAULT NULL,
  `issuedto` varchar(300) DEFAULT NULL,
  `transactiondate` date DEFAULT NULL,
  `branchid` int(11) DEFAULT NULL,
  `stockentryid` int(11) DEFAULT NULL,
  `status` varchar(100) DEFAULT NULL,
  `voucherid` int(11) DEFAULT NULL,
  `userid` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `mess_stockmoves_mess_stockentry_FK` (`itemid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `mess_stockmoves`
--

LOCK TABLES `mess_stockmoves` WRITE;
/*!40000 ALTER TABLE `mess_stockmoves` DISABLE KEYS */;
/*!40000 ALTER TABLE `mess_stockmoves` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `mess_supplierpaymentdetails`
--

DROP TABLE IF EXISTS `mess_supplierpaymentdetails`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `mess_supplierpaymentdetails` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `externalid` varchar(100) DEFAULT NULL,
  `supplierid` int(11) DEFAULT NULL,
  `chequeno` varchar(100) DEFAULT NULL,
  `amount` decimal(18,5) DEFAULT NULL,
  `voucherid` int(11) DEFAULT NULL,
  `issuedate` date DEFAULT NULL,
  `delivereddate` date DEFAULT NULL,
  `cleareddate` date DEFAULT NULL,
  `status` varchar(50) DEFAULT NULL,
  `branchid` int(11) DEFAULT NULL,
  `entrydate` date DEFAULT NULL,
  `voucheridcleared` int(11) DEFAULT NULL,
  `userid` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `mess_supplierpaymentdetails`
--

LOCK TABLES `mess_supplierpaymentdetails` WRITE;
/*!40000 ALTER TABLE `mess_supplierpaymentdetails` DISABLE KEYS */;
/*!40000 ALTER TABLE `mess_supplierpaymentdetails` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `mess_suppliers`
--

DROP TABLE IF EXISTS `mess_suppliers`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `mess_suppliers` (
  `Id` int(11) NOT NULL AUTO_INCREMENT,
  `externalid` varchar(110) NOT NULL,
  `name` varchar(100) DEFAULT NULL,
  `contactnumber` varchar(12) DEFAULT NULL,
  `address` varchar(200) DEFAULT NULL,
  `bankaccountno` varchar(100) DEFAULT NULL,
  `ifsccode` varchar(20) DEFAULT NULL,
  `linkedledgerid` int(11) NOT NULL,
  `branchid` int(11) DEFAULT NULL,
  `payto` varchar(200) DEFAULT NULL,
  `userid` int(11) DEFAULT NULL,
  PRIMARY KEY (`Id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `mess_suppliers`
--

LOCK TABLES `mess_suppliers` WRITE;
/*!40000 ALTER TABLE `mess_suppliers` DISABLE KEYS */;
/*!40000 ALTER TABLE `mess_suppliers` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `mess_taxinvoice`
--

DROP TABLE IF EXISTS `mess_taxinvoice`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `mess_taxinvoice` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `externalid` varchar(200) DEFAULT NULL,
  `issuedto` varchar(300) DEFAULT NULL,
  `transactiondate` date DEFAULT NULL,
  `branchid` int(11) DEFAULT NULL,
  `status` varchar(45) DEFAULT NULL,
  `quotationid` int(11) DEFAULT NULL,
  `userid` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `mess_taxinvoice`
--

LOCK TABLES `mess_taxinvoice` WRITE;
/*!40000 ALTER TABLE `mess_taxinvoice` DISABLE KEYS */;
/*!40000 ALTER TABLE `mess_taxinvoice` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `otherfee_academicfeesstructure`
--

DROP TABLE IF EXISTS `otherfee_academicfeesstructure`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `otherfee_academicfeesstructure` (
  `feesstructureid` int(11) NOT NULL AUTO_INCREMENT,
  `sid` int(11) NOT NULL,
  `totalfees` decimal(10,0) DEFAULT NULL,
  `paidfees` decimal(10,0) DEFAULT '0',
  `academicyear` varchar(45) DEFAULT NULL,
  `branchid` int(11) DEFAULT NULL,
  `userid` int(11) DEFAULT NULL,
  PRIMARY KEY (`feesstructureid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `otherfee_academicfeesstructure`
--

LOCK TABLES `otherfee_academicfeesstructure` WRITE;
/*!40000 ALTER TABLE `otherfee_academicfeesstructure` DISABLE KEYS */;
/*!40000 ALTER TABLE `otherfee_academicfeesstructure` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `otherfee_feescategory`
--

DROP TABLE IF EXISTS `otherfee_feescategory`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `otherfee_feescategory` (
  `idfeescategory` int(11) NOT NULL AUTO_INCREMENT,
  `feescategoryname` varchar(150) DEFAULT NULL,
  `amount` int(11) DEFAULT NULL,
  `particularname` varchar(150) DEFAULT NULL,
  `branchid` int(11) DEFAULT NULL,
  `userid` int(11) DEFAULT NULL,
  `academicyear` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`idfeescategory`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `otherfee_feescategory`
--

LOCK TABLES `otherfee_feescategory` WRITE;
/*!40000 ALTER TABLE `otherfee_feescategory` DISABLE KEYS */;
/*!40000 ALTER TABLE `otherfee_feescategory` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `otherfee_feescollection`
--

DROP TABLE IF EXISTS `otherfee_feescollection`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `otherfee_feescollection` (
  `feecollectionid` int(11) NOT NULL AUTO_INCREMENT,
  `sfsid` int(11) NOT NULL,
  `amountpaid` decimal(10,0) DEFAULT NULL,
  `sid` int(11) NOT NULL,
  `fine` decimal(10,0) DEFAULT NULL,
  `date` date DEFAULT NULL,
  `academicyear` varchar(45) DEFAULT NULL,
  `receiptnumber` int(11) NOT NULL,
  `branchid` int(11) DEFAULT NULL,
  `userid` int(11) DEFAULT NULL,
  PRIMARY KEY (`feecollectionid`),
  KEY `sid_idx` (`sid`),
  KEY `sfsid_idx` (`sfsid`),
  KEY `receiptnumber_idx` (`receiptnumber`),
  CONSTRAINT `otherfk` FOREIGN KEY (`sid`) REFERENCES `student` (`sid`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `otherreceiptnumber` FOREIGN KEY (`receiptnumber`) REFERENCES `otherfee_receiptinfo` (`receiptnumber`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `othersfsid` FOREIGN KEY (`sfsid`) REFERENCES `otherfee_studentfeesstructure` (`sfsid`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `otherfee_feescollection`
--

LOCK TABLES `otherfee_feescollection` WRITE;
/*!40000 ALTER TABLE `otherfee_feescollection` DISABLE KEYS */;
/*!40000 ALTER TABLE `otherfee_feescollection` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `otherfee_receiptinfo`
--

DROP TABLE IF EXISTS `otherfee_receiptinfo`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `otherfee_receiptinfo` (
  `receiptnumber` int(11) NOT NULL AUTO_INCREMENT,
  `sid` int(11) NOT NULL,
  `date` date DEFAULT NULL,
  `totalamount` decimal(10,0) DEFAULT NULL,
  `academicyear` varchar(15) DEFAULT NULL,
  `branchid` int(11) DEFAULT NULL,
  `cancelreceipt` int(11) DEFAULT '0',
  `branchreceiptnumber` varchar(20) DEFAULT NULL,
  `paymenttype` varchar(100) DEFAULT NULL,
  `userid` int(11) DEFAULT NULL,
  `classsec` varchar(20) DEFAULT NULL,
  `receiptvoucher` int(11) DEFAULT NULL,
  `journalvoucher` int(11) DEFAULT NULL,
  `misc` decimal(10,0) DEFAULT '0',
  `fine` decimal(10,0) DEFAULT '0',
  PRIMARY KEY (`receiptnumber`),
  KEY `studentid_idx` (`sid`),
  CONSTRAINT `otherstudentidreceipt` FOREIGN KEY (`sid`) REFERENCES `student` (`sid`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `otherfee_receiptinfo`
--

LOCK TABLES `otherfee_receiptinfo` WRITE;
/*!40000 ALTER TABLE `otherfee_receiptinfo` DISABLE KEYS */;
/*!40000 ALTER TABLE `otherfee_receiptinfo` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `otherfee_studentfeesstructure`
--

DROP TABLE IF EXISTS `otherfee_studentfeesstructure`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `otherfee_studentfeesstructure` (
  `sfsid` int(11) NOT NULL AUTO_INCREMENT,
  `sid` int(11) NOT NULL,
  `idfeescategory` int(11) NOT NULL,
  `feesamount` decimal(10,0) DEFAULT NULL,
  `academicyear` varchar(45) DEFAULT NULL,
  `branchid` int(11) DEFAULT NULL,
  `concession` int(11) DEFAULT NULL,
  `feespaid` decimal(10,0) DEFAULT '0',
  `waiveoff` decimal(10,0) DEFAULT '0',
  `totalinstallment` int(11) DEFAULT '0',
  `userid` int(11) DEFAULT NULL,
  PRIMARY KEY (`sfsid`),
  KEY `fk_sfs_idx` (`sid`),
  KEY `feescategoryid_idx` (`idfeescategory`),
  CONSTRAINT `otherfeescategoryid` FOREIGN KEY (`idfeescategory`) REFERENCES `otherfee_feescategory` (`idfeescategory`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `otherfk_sfs` FOREIGN KEY (`sid`) REFERENCES `student` (`sid`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `otherfee_studentfeesstructure`
--

LOCK TABLES `otherfee_studentfeesstructure` WRITE;
/*!40000 ALTER TABLE `otherfee_studentfeesstructure` DISABLE KEYS */;
/*!40000 ALTER TABLE `otherfee_studentfeesstructure` ENABLE KEYS */;
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
  `fathersqualification` varchar(45) DEFAULT NULL,
  `mothersqualification` varchar(45) DEFAULT NULL,
  `fatherscastecertno` varchar(25) DEFAULT NULL,
  `motherscastecertno` varchar(100) DEFAULT NULL,
  `fatherscaste` varchar(15) DEFAULT NULL,
  `motherscaste` varchar(15) DEFAULT NULL,
  `userid` int(11) DEFAULT NULL,
  PRIMARY KEY (`pid`),
  KEY `sid_idx` (`sid`),
  KEY `tid_idx` (`tid`),
  CONSTRAINT `sid` FOREIGN KEY (`sid`) REFERENCES `student` (`sid`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `tid` FOREIGN KEY (`tid`) REFERENCES `teacher` (`tid`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=401 DEFAULT CHARSET=utf8 COMMENT='		';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `parents`
--

LOCK TABLES `parents` WRITE;
/*!40000 ALTER TABLE `parents` DISABLE KEYS */;
INSERT INTO `parents` VALUES (1,'THAMEEM ANSARI ','.','38B/6 L.F ROAD,\r\nKAYALPATMAM','',NULL,'',0,1,NULL,'','0','','',2,'','','','',NULL,NULL,331),(2,'A.UMAR KARTHA','.','43/10,PARIMAR STREET,\r\nKAYALPATNAM','',NULL,'',0,2,NULL,'','0','','',2,'','','','',NULL,NULL,331),(3,'M.I.SEYED AHAMED','.','68/124 SOLUKAR STREET,KAYALPATNAM','',NULL,'',0,3,NULL,'','0','','',2,'','','','',NULL,NULL,331),(4,'A.MAHADUM MOHAMED','.','33/A,AZAD STREET,\r\nKAYALPATNAM','',NULL,'',0,4,NULL,'','0','','',2,'','','','',NULL,NULL,331),(5,'ABDUL JABBAR','.','59,NORTH MOSQUE STREET,ERAL','',NULL,'',0,5,NULL,'','0','','',2,'','','','',NULL,NULL,331),(6,'.A.S. ABDUL MALIK','.','38/84,DEEVU STREET,\r\nKAYALPATNAM','',NULL,'',0,6,NULL,'','0','','',2,'','','','',NULL,NULL,331),(7,'B.SINDHA','.','78/84C,MELA SITHAN STREET,KAYALPATNAM','',NULL,'',0,7,NULL,'','0','','',2,'','','','',NULL,NULL,331),(8,'ASARAF ALI','.','27/A,KOMAN MIDDLE STRRET,\r\nKAYALPATNAM','',NULL,'',0,8,NULL,'','0','','',2,'','','','',NULL,NULL,331),(9,'M.A.THARIK','.','171/60C,CHOLUKKAR STREET,\r\nKAYALPATNAM','',NULL,'',0,9,NULL,'','0','','',2,'','','','',NULL,NULL,331),(10,'MOHAMED MASOOD','.','165/88A,HAJI APPA THAIKA STREET,\r\nKAYALPATNAM','',NULL,'',0,10,NULL,'','0','','',2,'','','','',NULL,NULL,331),(11,'MOHAMED ABDUL CADER','.','70/77,AMBALA MARAIKAR STREET,\r\nKAYALPATNAM','',NULL,'',0,11,NULL,'','0','','',2,'','','','',NULL,NULL,331),(12,'MOHAMED RISKAN MUBEEN','.','102A,KEELA SITHAN STREET,\r\nKAYALPATNAM','',NULL,'',0,12,NULL,'','0','','',2,'','','','',NULL,NULL,331),(13,'M.I.MOHAMED ALI','.','221/15 DEEVU STREET,\r\nKAYALPATNAM','',NULL,'',0,13,NULL,'','0','','',2,'','','','',NULL,NULL,331),(14,'N.SINTHA AIDROOS','.','83,ALIYAR STREET,\r\nKAYALPATNAM','',NULL,'',0,14,NULL,'','0','','',2,'','','','',NULL,NULL,331),(15,'N.SINTHA AIDROOS','.','83,ALIYAR STREET,\r\nKAYALPATNAM','',NULL,'',0,15,NULL,'','0','','',2,'','','','',NULL,NULL,331),(16,'O.A.K.SEYED ISMAIL','.','26/147C,\r\nAPPAPALLI STREET,\r\nKAYALPATNAM','',NULL,'',0,16,NULL,'','0','','',2,'','','','',NULL,NULL,331),(17,'SEYED AHAMED BUHARI','.','51/21,CHOLUKAR STREET,\r\nKAYALPATNAM','',NULL,'',0,17,NULL,'','0','','',2,'','','','',NULL,NULL,331),(18,'MOHAMED ISMAIL LEBBAI AZIB','.','18/192B,BEACH STREET\r\nKAYALPATNAM','',NULL,'',0,18,NULL,'','0','','',2,'','','','',NULL,NULL,331),(19,'S.KITHIR SULAIMAN','.','101,PARIMAR STREET,\r\nKAYALPATNAM','',NULL,'',0,19,NULL,'','0','','',2,'','','','',NULL,NULL,331),(20,'S.AZARUDEEN','.','182/90G,PERIYA NESUVU STREET,\r\nKAYALPATNAM','',NULL,'',0,20,NULL,'','0','','',2,'','','','',NULL,NULL,331),(21,'.','.','','',NULL,'',0,21,NULL,'','0','','',2,'','','','',NULL,NULL,331),(22,'S.M.MOHAMMED MOHUDUM','.','41A,MELA SITHAN STREET,KAYALPATNAM','',NULL,'',0,22,NULL,'','0','','',2,'','','','',NULL,NULL,331),(23,'M.M.JAFFER SATHIK','.','169,B MUTHARAMMON KOVIL STREET,\r\nKAYALPATNAM','',NULL,'',0,23,NULL,'','0','','',2,'','','','',NULL,NULL,331),(24,'MOHAMMED HAKEEM','.','12/9O SEETHAKATHI NAGAR,\r\nKAYALPATNAM.','',NULL,'',0,24,NULL,'','0','','',2,'','','','',NULL,NULL,331),(25,'P.ASHIQ SALAHUDEEN','.','54/A3,PANDAGA SALAI,KARANAR STREET,\r\nKAYALPATNAM','',NULL,'',0,25,NULL,'','0','','',2,'','','','',NULL,NULL,331),(26,'JAMAL','.','210/A3, THENGAI PANDAGA SALAI,\r\nKAYALPATNAM.','',NULL,'',0,26,NULL,'','0','','',2,'','','','',NULL,NULL,331),(27,'A.M.T. MOHAIDEEN ABDUL CADER','.','44A/49,\r\nAZAD STREET,\r\nKAYALPATNAM','',NULL,'',0,27,NULL,'','0','','',2,'','','','',NULL,NULL,331),(28,'M.N.MOHAMED IBRAHIM','.','234,MARAIKAR PALLI STREET,KAYALPATNAM','',NULL,'',0,28,NULL,'','0','','',2,'','','','',NULL,NULL,331),(29,'P.JENSON','.','C/127,KARPUDAIYAR PALLI VATTAM,\r\nKAYALPATNAM','',NULL,'',0,29,NULL,'','0','','',2,'','','','',NULL,NULL,331),(30,'N.M.KHAJA MOHIDEEN','.','8D/2,MELA SITHAN STREET,\r\nKAYALPATNAM','',NULL,'',0,30,NULL,'','0','','',2,'','','','',NULL,NULL,331),(31,'MOHAMED HASAN','.','5C,SUNAMI NAGAR','',NULL,'',0,31,NULL,'','0','','',2,'','','','',NULL,NULL,331),(32,'M.M.S.ZINDHA IFHAMUDHEEN','.','167,A DEEVU STREET,KAYALPATNAM','',NULL,'',0,32,NULL,'','0','','',2,'','','','',NULL,NULL,331),(33,'BASHEER','.','88/55,PARIMAR STREET,KAYALPATNAM','',NULL,'',0,33,NULL,'','0','','',2,'','','','',NULL,NULL,331),(34,'AJMEER','.','58/52,SUNAMI NAGAR','',NULL,'',0,34,NULL,'','0','','',2,'','','','',NULL,NULL,2),(35,'MUHAIDEEN THARVES','.','61A,ALIYAR STREET,\r\nKAYALPATNAM','',NULL,'',0,35,NULL,'','0','','',2,'','','','',NULL,NULL,331),(36,'J.A.ABDUL HALEEM','.','4/14,KOCHIYAR STREET,\r\nKAYALPATNAM','',NULL,'',0,36,NULL,'','0','','',2,'','','','',NULL,NULL,2),(37,'JESU','.','C-97,KARUPUDAIYAR PALLIU VATTAM,SINGIDURAI,KAYALPATNAM','',NULL,'',0,37,NULL,'','0','','',2,'','','','',NULL,NULL,331),(38,'.','.','','',NULL,'',0,38,NULL,'','0','','',2,'','','','',NULL,NULL,331),(39,'H.AFILUR REHMAN','.','14A,MUTHU VAAPA THAIKA STREET,\r\nKAYALPATNAM','',NULL,'',0,39,NULL,'','0','','',2,'','','','',NULL,NULL,331),(40,'S.H.HASAN JIFFRY','.','19/2B,MARAIKAR PALLI STREET,\r\nKAYALPATNAM','',NULL,'',0,40,NULL,'','0','','',2,'','','','',NULL,NULL,331),(41,'M.MOHAMED LATHIEEF','.','12/3C QUAITHAE MILLATH NAGAR,6TH STREET,KAYALPATNAM','',NULL,'',0,41,NULL,'','0','','',2,'','','','',NULL,NULL,331),(42,'NAGOOR PITCHAI','.','56/E3 SULAIMAN NAGAR,KAYALPATNAM','',NULL,'',0,42,NULL,'','0','','',2,'','','','',NULL,NULL,331),(43,'mohamed hussain','.','5c,sunami Nagar Kayalpatnam','',NULL,'',0,43,NULL,'','0','','',2,'','','','',NULL,NULL,331),(44,'fakeer mohideen thowhithu','.','111/A keela nainar street,\r\nkayalpatnam','',NULL,'',0,44,NULL,'','0','','',2,'','','','',NULL,NULL,331),(45,'RIFAN','.','ROJA MANZIL,KMT HOSPITAL OPPSITE,\r\nKAYALPATNAM','',NULL,'',0,45,NULL,'','0','','',2,'','','','',NULL,NULL,331),(46,'MOHHAMMED ALI','.','4/5C PANDAGA SALAI CORNER, STREET, KAYALPATNAM','',NULL,'',0,46,NULL,'','0','','',2,'','','','',NULL,NULL,331),(47,'SAKTHI LINGAM','.','37,POONTHOTTAM,\r\nKAYALPATNAM','',NULL,'',0,47,NULL,'','0','','',2,'','','','',NULL,NULL,331),(48,'SEHU NOORDEEN','.','B2A,PARIMAR STREET,KAYALPATNAM','',NULL,'',0,48,NULL,'','0','','',2,'','','','',NULL,NULL,331),(49,'SATHAM','.','50A2,THENGGAI PANDAGA SALAI,KAYALPATNAM','',NULL,'',0,49,NULL,'','0','','',2,'','','','',NULL,NULL,331),(50,'A.L.MOHAMED SADAKATHULLAH','.','4/27,AZAD STREET,KAYALPATNAM','',NULL,'',0,50,NULL,'','0','','',2,'','','','',NULL,NULL,331),(51,'UVAISAL QUARNAIN','.','30A/118,\r\nKEELA NAINAR STREET,\r\nKAYALPATNAM','',NULL,'',0,51,NULL,'','0','','',2,'','','','',NULL,NULL,331),(52,'ABUBACKER SIDDIQ','.','149,MELA SITHAN STREET,\r\nKAYALPATNAM','',NULL,'',0,52,NULL,'','0','','',2,'','','','',NULL,NULL,331),(53,'M.ABDUL RAVOOB','.','60/A HAJI APPA THAIKKA STREET,\r\nKAYALPATNAM','',NULL,'',0,53,NULL,'','0','','',2,'','','','',NULL,NULL,331),(54,'S.M.MOHAMED ALI ','.','64C,TSUNAMI NAGATR,KAYALPATNAM','',NULL,'',0,54,NULL,'','0','','',2,'','','','',NULL,NULL,331),(55,'ANANTH','.','50A,POONTHOTTAM,KAYALPATNAM','',NULL,'',0,55,NULL,'','0','','',2,'','','','',NULL,NULL,331),(56,'S,SHANMUGA VADIVOO','.','82,KEELA LAKSHMI PURAM,KAYALPATNAM','',NULL,'',0,56,NULL,'','0','','',2,'','','','',NULL,NULL,331),(57,'M.M.UMAR FAROOK','.','128A/2,KEELA NAINAR STREET,KAYALPATNAM','',NULL,'',0,57,NULL,'','0','','',2,'','','','',NULL,NULL,331),(58,'S.A.K SAMSUDEEN SUBER','.','67/84 SITHAN STREET,KAYALPANAM','',NULL,'',0,58,NULL,'','0','','',2,'','','','',NULL,NULL,331),(59,'NAJMUDEEN','.','197D,PANDAGA SALAI CORNER STREET,KAYALPATNAM','',NULL,'',0,59,NULL,'','0','','',2,'','','','',NULL,NULL,331),(60,'MOHAIDEEN ABDUL KADER JAILANI','.','3/132 KATTU MOGUDUM PALLI,\r\nVEERAPANDIYANPATNAM','',NULL,'',0,60,NULL,'','0','','',2,'','','','',NULL,NULL,331),(61,'S.M.MOHAMED THAMBY','.','B1/66,PARIMAR STREET,\r\nKAYALPATNAM','',NULL,'',0,61,NULL,'','0','','',2,'','','','',NULL,NULL,331),(62,'NIZAMUDEEN','.','12C,6TH STREET QUAITHAE MILLATH NAGAR, KAYALPATNAM','',NULL,'',0,62,NULL,'','0','','',2,'','','','',NULL,NULL,331),(63,'YASIR ARAFATH','.','61/26C,APPAPALLI STREET,KAYALPATNAM','',NULL,'',0,63,NULL,'','0','','',2,'','','','',NULL,NULL,331),(64,'M.A.ABUL KASIM BARAKAHULLAH','.','173/56D,DEEVU STREET,KAYALPATNAM','',NULL,'',0,64,NULL,'','0','','',2,'','','','',NULL,NULL,331),(65,'J.MOHAMED SABEER ALI','.','4E2,SIVAN KOVIL STREET,KAYALPATNAM','',NULL,'',0,65,NULL,'','0','','',2,'','','','',NULL,NULL,331),(66,'FAKEER MOHIDEEN THOWHITHU','.','111/A KEELA NAINAR STREET,KAYALPATNAM','',NULL,'',0,66,NULL,'','0','','',2,'','','','',NULL,NULL,331),(67,'JEBASINGH','.','138C/3,SINGATHURAI,KARUPUDAIYAR PALLI VATTAM,KAYALPATNAM','',NULL,'',0,67,NULL,'','0','','',2,'','','','',NULL,NULL,331),(68,'MEERA SAHIB','.','212C,PERIYA NESUVU STREET,KAYALPATNAM','',NULL,'',0,68,NULL,'','0','','',2,'','','','',NULL,NULL,331),(69,'SHIEK MOHAMED ALI','.','140/A,UCHINIMAHALI AMMAN KOVIL STREET,KAYALPATNAM','',NULL,'',0,69,NULL,'','0','','',2,'','','','',NULL,NULL,331),(70,'NAVAS','.','26,PARIMAR STREET,KAYALPATNAM','',NULL,'',0,70,NULL,'','0','','',2,'','','','',NULL,NULL,331),(71,'UVAISUL KARANI','.','141,AMBALA MARAIKAR STRREET,KAYALPATNAM','',NULL,'',0,71,NULL,'','0','','',2,'','','','',NULL,NULL,331),(72,'MOHAMED MOHAIDEEN MUBARAK','.','70/216,CHOLUKKAR STREET,KAYALPATNAM','',NULL,'',0,72,NULL,'','0','','',2,'','','','',NULL,NULL,331),(73,'APPAS','.','23I,MANGALA VINAYAGAR KOVIL STREET,KAYALPATNAM','',NULL,'',0,73,NULL,'','0','','',2,'','','','',NULL,NULL,331),(74,'S.SUSAI','.','128/C2,SINGADURAI,KAYALPATNAM','',NULL,'',0,74,NULL,'','0','','',2,'','','','',NULL,NULL,331),(75,'P.PENSIGAR','.','30/A SOUTH COLONY,SINGIDURAI,KAYALPATNAM','',NULL,'',0,75,NULL,'','0','','',2,'','','','',NULL,NULL,331),(76,'A.M.T.ABDUL KADER','.','NEWNO:166,OLDNO:136,\r\nMARAIKAYAR PALLI STREET,KAYALPATNAM','',NULL,'',0,76,NULL,'','0','','',2,'','','','',NULL,NULL,331),(77,'S.S.SEGU NOORDEEN','.','61/A,PARIMAR STREET ,KAYALPATNAM','',NULL,'',0,77,NULL,'','0','','',2,'','','','',NULL,NULL,331),(78,'SHAIK ABDUL KADER','.','126/269A,MARAIKAR PALLI STREET,KAYALPATNAM','',NULL,'',0,78,NULL,'','0','','',2,'','','','',NULL,NULL,331),(79,'S.A.K.ABDUL HASSAN SHADULY','.','1444/C3,APPA PALLI STREET, KAYALPATNAM','',NULL,'',0,79,NULL,'','0','','',2,'','','','',NULL,NULL,331),(80,'SEYED IBRAHIM BADUSHA','.','95/30,MELA SITTAN STREET,\r\nKAYALPATNAM','',NULL,'',0,80,NULL,'','0','','',2,'','','','',NULL,NULL,331),(81,'UTHUMAN RAZIK','.','56/82 PARIMAR STREET,KAYALPATNAM','',NULL,'',0,81,NULL,'','0','','',2,'','','','',NULL,NULL,331),(82,'N.SELVAKUMAR','.','335/2,LIONS TOWNS,SOUTH COTTON STREET,\r\nTHOOTHUKKUDI','',NULL,'',0,82,NULL,'','0','','',2,'','','','',NULL,NULL,331),(83,'M.KITHIR MOHAMED','.','54,THENGAI, PANDAGA SALAI,KAYALPATNAM','',NULL,'',0,83,NULL,'','0','','',2,'','','','',NULL,NULL,331),(84,'K.KARTHICK','.','3A,MANGALAVADI,KAYALPATNAM','',NULL,'',0,84,NULL,'','0','','',2,'','','','',NULL,NULL,331),(85,'I.RIFAN','.','3/E4 THIRCHENDHUR ROAD,KMT HOSPITAL OPPOSITE,\r\nKAYALPATNAM','',NULL,'',0,85,NULL,'','0','','',2,'','','','',NULL,NULL,331),(86,'S.A.K.MIHAMED FAWAZ','.','87/27C SITHAN STREET,KAYALPATNAM','',NULL,'',0,86,NULL,'','0','','',2,'','','','',NULL,NULL,331),(87,'J.ROSINGTON','.','59/C TSUNAMI NAGAR,KAYALPATNAM','',NULL,'',0,87,NULL,'','0','','',2,'','','','',NULL,NULL,331),(88,'L.JAMAL MOHAMED','.','114,PERIYA NESUVU STREET,\r\nKAYALPATNAM','',NULL,'',0,88,NULL,'','0','','',2,'','','','',NULL,NULL,331),(89,'M.S.MOHAMED IBRAHIM','.','7/1D,KOCHIYAR STREET,\r\nKAYALPATNAM','',NULL,'',0,89,NULL,'','0','','',2,'','','','',NULL,NULL,2),(90,'AHAMED MEERA SAHIB ','.','80/76 EAST SITTAN STREET,KAYALPATNAM','',NULL,'',0,90,NULL,'','0','','',2,'','','','',NULL,NULL,331),(91,'K.MUTHUKUMAR','.','100A,RATHINAPURI,KAYALPATNAM','',NULL,'',0,91,NULL,'','0','','',2,'','','','',NULL,NULL,331),(92,'M.MOHAMED NAINA LEBBAI','.','204,DEEVU STREET,\r\nKAYALPATNAM','',NULL,'',0,92,NULL,'','0','','',2,'','','','',NULL,NULL,331),(93,'S.A.MOHAMED IBRAHIM','.','129/37,K.M.K STREET,\r\nKAYALPATNAM','',NULL,'',0,93,NULL,'','0','','',2,'','','','',NULL,NULL,331),(94,'S.M.MOHAMED JUNAITH','.','121A,SITHAN STREET,KAYALPATNAM','',NULL,'',0,94,NULL,'','0','','',2,'','','','',NULL,NULL,331),(95,'SAMSUKANI','.','56E/1,THENGAI PANDAGA SALAI,KAYALPATNAM','',NULL,'',0,95,NULL,'','0','','',2,'','','','',NULL,NULL,331),(96,'ABUBACKER SIDDIQ','.','56/69,\r\nPARIMAR STREET,KAYALPATNAM','',NULL,'',0,96,NULL,'','0','','',2,'','','','',NULL,NULL,331),(97,'P.M.SEGU NOORDEEN','.','20/53,PARIMAR STREET,KAYALPATNAM','',NULL,'',0,97,NULL,'','0','','',2,'','','','',NULL,NULL,331),(98,'J.S.SEYED ISMAHIL','.','16,KEELA NAINAR STREET,\r\nKAYALPATNAM','',NULL,'',0,98,NULL,'','0','','',2,'','','','',NULL,NULL,331),(99,'M.SURYA RAJ','.','115/D VISALATCHI AMMAN KOVIL STREET,KAYALPATNAM','',NULL,'',0,99,NULL,'','0','','',2,'','','','',NULL,NULL,331),(100,'S.A.MOHAMED IBRAHIM','.','48,ALIYAR STREET,\r\nKAYALPATNAM','',NULL,'',0,100,NULL,'','0','','',2,'','','','',NULL,NULL,331),(101,'K,M,MOHAMED ISMAIL','.','196/62-F DEEVU STREET, KAYALPATNAM','',NULL,'',0,101,NULL,'','0','','',2,'','','','',NULL,NULL,331),(102,'M.M.S.ZINDHA IFHAMUDHEEN','.','167A,DEEVU STREET,KAYALPATNAM','',NULL,'',0,102,NULL,'','0','','',2,'','','','',NULL,NULL,331),(103,'S.H.N.SEYED ISMAIL','.','196/62-F DEEVU STREET,\r\nKAYALPATNAM','',NULL,'',0,103,NULL,'','0','','',2,'','','','',NULL,NULL,331),(104,'M.HAJA NADHEEM','.','156/60,KEELA SITHAN STREET,KAYALPATNAM','',NULL,'',0,104,NULL,'','0','','',2,'','','','',NULL,NULL,331),(105,'S.MOHAMMED NEERAJ ROSHAN','.','49,QUAIDEMILLAT 1ST STREET,KAYALPATNAM','',NULL,'',0,105,NULL,'','0','','',2,'','','','',NULL,NULL,331),(106,'MOHAMED BUHARI','.','35A/1,DEEVU STREET,KAYALPATNAM','',NULL,'',0,106,NULL,'','0','','',2,'','','','',NULL,NULL,331),(107,'RAJESH','.','135,SIGITHURAI,KAYALPATNAM','',NULL,'',0,107,NULL,'','0','','',2,'','','','',NULL,NULL,331),(108,'NISHANTH','.','54C, SINGITHURAI,KAYALPATNAM','',NULL,'',0,108,NULL,'','0','','',2,'','','','',NULL,NULL,331),(109,'J.RAGAVAN','.','8,EAST STREET,PEYANVILAI,ARUMUGANERI','',NULL,'',0,109,NULL,'','0','','',2,'','','','',NULL,NULL,331),(110,'M.A.K.SEYED ABUTHAHIR','.','175/56F,DEEVU STREET,\r\nKAYALPATNAM','',NULL,'',0,110,NULL,'','0','','',2,'','','','',NULL,NULL,331),(111,'AHAMED NAVAS KHAN','.','48,PARIMAR STREET,KAYALPATNAM','',NULL,'',0,111,NULL,'','0','','',2,'','','','',NULL,NULL,331),(112,'S.F.SAHIB SARJOON','.','394/133 SADUKAI STREET,KAYALPATNAM','',NULL,'',0,112,NULL,'','0','','',2,'','','','',NULL,NULL,331),(113,'A.S.L.MOHAIDEEN MAMUNA LEBBAI','.','53D,KOCHIYAR STREET,KAYALPATNAM','',NULL,'',0,113,NULL,'','0','','',2,'','','','',NULL,NULL,331),(114,'IBRAHIM ALI','.','164, ALIYAR STREET,KAYALPATNAM','',NULL,'',0,114,NULL,'','0','','',2,'','','','',NULL,NULL,331),(115,'S.THAMEEM ANSARI','.','150/63B,ALIYAR STREET,\r\nKAYALPATNAM','',NULL,'',0,115,NULL,'','0','','',2,'','','','',NULL,NULL,331),(116,'J.S.SHAHUL HAMEED','.','107/B8,SIVAN KOVIL STREET,KAYALPATNAM','',NULL,'',0,116,NULL,'','0','','',2,'','','','',NULL,NULL,331),(117,'CLAIMENT','.','135C,SINGIDURAI,KAYALPATNAM','',NULL,'',0,117,NULL,'','0','','',2,'','','','',NULL,NULL,331),(118,'M.RISWAN','.','20B,QUAITHA MILLATH NAGAR,KAYALPATNAM','',NULL,'',0,118,NULL,'','0','','',2,'','','','',NULL,NULL,331),(119,'MUHAIDHEEN SHIEK ALI','.','144/C3, APPA PALLI STREET,\r\nKAYALPATNAM','',NULL,'',0,119,NULL,'','0','','',2,'','','','',NULL,NULL,331),(120,'N.SYED ISMAIL','.','42,DEEVU STREET KAYALPATNAM','',NULL,'',0,120,NULL,'','0','','',2,'','','','',NULL,NULL,331),(121,'K.M.AYUB AHAMED','.','35,B PARIMAR STREET,KAYALPATNAM','',NULL,'',0,121,NULL,'','0','','',2,'','','','',NULL,NULL,331),(122,'KASALI MARAIKAR','.','102 PARIMAR STREET,KAYALPATNAM.','',NULL,'',0,122,NULL,'','0','','',2,'','','','',NULL,NULL,331),(123,'S.H.SHAIK DAWOOD','.','34/50A,ARSMPALLI STREEET,\r\nKAYALPATNAM','',NULL,'',0,123,NULL,'','0','','',2,'','','','',NULL,NULL,331),(124,'SATHAM HUSSAIN','.','35B,PARIMAR STREET,KAYALPATNAM','',NULL,'',0,124,NULL,'','0','','',2,'','','','',NULL,NULL,331),(125,'L.G.SEYED ALAVI','.','100/170,MAGDOOM STREET,KAYALPATNAM','',NULL,'',0,125,NULL,'','0','','',2,'','','','',NULL,NULL,331),(126,'M.SHAIK ALI NUSUKY','.','173/56D,DEEVU STREET,KAYALPATNAM','',NULL,'',0,126,NULL,'','0','','',2,'','','','',NULL,NULL,331),(127,'M.S.SEYED MOHAMED','.','105/C1,KOMAN KEELA STREET,KAYALPATNAM','',NULL,'',0,127,NULL,'','0','','',2,'','','','',NULL,NULL,331),(128,'RAPISTAN','.','C/27,SINGITHURAI,\r\nKAYALPATNAM','',NULL,'',0,128,NULL,'','0','','',2,'','','','',NULL,NULL,331),(129,'R.N.M.MOHAMED SHAIKNA LEBBAI','.','138/69,DEEVU STREET,KAYALPATNAM','',NULL,'',0,129,NULL,'','0','','',2,'','','','',NULL,NULL,331),(130,'I.FACKIR MOHAMED','.','264/122,MARAICKAR PALLI STREET,KAYALPATNAM','',NULL,'',0,130,NULL,'','0','','',2,'','','','',NULL,NULL,331),(131,'T.MOHAMMED IRFAN','.','150A,SIVAN KOVIL STREET,KAYALPATNAM','',NULL,'',0,131,NULL,'','0','','',2,'','','','',NULL,NULL,331),(132,'A.ABUL KASIM NAINA','.','38/100 DEEVU STREET,KAYALPATNAM','',NULL,'',0,132,NULL,'','0','','',2,'','','','',NULL,NULL,331),(133,'ASAN','.','164,ALIYAR STREET,KAYALPATNAM','',NULL,'',0,133,NULL,'','0','','',2,'','','','',NULL,NULL,331),(134,'S.A.K. SYED ISMAIL','.','230,DEEVU STREET,KAYALPATNAM','',NULL,'',0,134,NULL,'','0','','',2,'','','','',NULL,NULL,331),(135,'S.SANTHANA KUMAR','.','100C,MUTHARAMMAN KOVIL STREET,KAYALPATNAM','',NULL,'',0,135,NULL,'','0','','',2,'','','','',NULL,NULL,331),(136,'PRAKASH','.','8,KARPUDAIYAR PALLI VATTAM,SINGIDURAI,KAYALPATNAM','',NULL,'',0,136,NULL,'','0','','',2,'','','','',NULL,NULL,331),(137,'ABUBAKAR SITHIK','.','244,SULAIMAN NAGAR,\r\nKAYALPATNAM','',NULL,'',0,137,NULL,'','0','','',2,'','','','',NULL,NULL,331),(138,'B.A.SYED AMEER','.','141/39A ALIYAR STREET ,KAYALPATNAM','',NULL,'',0,138,NULL,'','0','','',2,'','','','',NULL,NULL,331),(139,'P.HARIHAAPUTHIRAN','.','11E/3,VANDI MALACHI AMMAN KOVIL STREET,KAYALPATNAM','',NULL,'',0,139,NULL,'','0','','',2,'','','','',NULL,NULL,331),(140,'J.M.S.SEYED AYDROOS BUKHARI','.','121E,NAINAR STREET,KAYALPATNAM','',NULL,'',0,140,NULL,'','0','','',2,'','','','',NULL,NULL,331),(141,'.','.',NULL,NULL,NULL,NULL,NULL,141,NULL,NULL,'0',NULL,NULL,2,NULL,NULL,NULL,NULL,NULL,NULL,0),(142,'seyed ibrahim','.','3/1a aliyar street,kayalpatnam','',NULL,'',0,142,NULL,'','0','','',2,'','','','',NULL,NULL,331),(143,'Z.M.MAHAMMED MEERA SAHIB','.','111/156, DEEVU STREET,KAYALPATNAM','',NULL,'',0,143,NULL,'','0','','',2,'','','','',NULL,NULL,331),(144,'J.S.MOHAMED MOHAIDEEN','.','18,C,KEELA NAINAR STREET,KAYALPATNAM','',NULL,'',0,144,NULL,'','0','','',2,'','','','',NULL,NULL,331),(145,'S.M.B.KADER SULAIMAN','.','41C,QUAITHAE MILLATH NAGAR 1ST CROSS STREET,KAYALPATNAM','',NULL,'',0,145,NULL,'','0','','',2,'','','','',NULL,NULL,331),(146,'S.M.SOLUKU MAHIN','.','38,SADUKKAI STREET,\r\nKAYALPATNAM','',NULL,'',0,146,NULL,'','0','','',2,'','','','',NULL,NULL,331),(147,'MOHAMED EASA','.','7/14,CHOLUKAR STREET,\r\nKAYALPATNAM','',NULL,'',0,147,NULL,'','0','','',2,'','','','',NULL,NULL,331),(148,'S.NIZARUDEEN','.','38,SADUKKAI STREET,\r\nKAYLPATNAM','',NULL,'',0,148,NULL,'','0','','',2,'','','','',NULL,NULL,331),(149,'KAMARUDEEN','.','11/42,THENGAI PANDAGASALAI,KAYALPATNAM','',NULL,'',0,149,NULL,'','0','','',2,'','','','',NULL,NULL,331),(150,'A.UMAR KARTHA','.','43/10,PARIMAR STREET,\r\nKAYALPATNAM','',NULL,'',0,150,NULL,'','0','','',2,'','','','',NULL,NULL,331),(151,'K.A.JASIR','.','108C,KOCIYAR STREET,KAYALPATNAM','',NULL,'',0,151,NULL,'','0','','',2,'','','','',NULL,NULL,331),(152,'SYED SIRAJUDEEN AJMAL','.','184/57D ALIYAR STREET,KAYALPATNAM','',NULL,'',0,152,NULL,'','0','','',2,'','','','',NULL,NULL,331),(153,'MUHAIDEEN ABDUL KADHER','.','21/2G CHINNA NESAVU STREET,\r\nKAYALPATNAM','',NULL,'',0,153,NULL,'','0','','',2,'','','','',NULL,NULL,331),(154,'MOHAIDEEN MOHAMED THAMBY','.','56,A SULAIMAN NAGAR,\r\nKAYALPATNAM','',NULL,'',0,154,NULL,'','0','','',2,'','','','',NULL,NULL,331),(155,'M.SEGU MOHAMED','.','128,SITHAKATHI NAGAR,\r\nKAYALPATNAM','',NULL,'',0,155,NULL,'','0','','',2,'','','','',NULL,NULL,331),(156,'V.CHITHIRAJ','.','28,POONTHOTTAM,\r\nKAYALPATNAM','',NULL,'',0,156,NULL,'','0','','',2,'','','','',NULL,NULL,331),(157,'C.MARIMUTHU','.','9B,COOLAKADAI STREET,\r\nKAYALPATNAM','',NULL,'',0,157,NULL,'','0','','',2,'','','','',NULL,NULL,331),(158,'G.JEYAPANDIAN','.','84,A,SINGITHURAI KADARKARAI,\r\nKAYALPATNAM','',NULL,'',0,158,NULL,'','0','','',2,'','','','',NULL,NULL,331),(159,'N.AHAMED MURSHID','.','102/A2,ODAKKARAI.','',NULL,'',0,159,NULL,'','0','','',2,'','','','',NULL,NULL,331),(160,'R.SELVAM','.','MATTAYAN THOTTAM,KAYALPATNAM','',NULL,'',0,160,NULL,'','0','','',2,'','','','',NULL,NULL,331),(161,'TAMMUL ANSARI','.','54,PARIMAR STREET,KAYALPATNAM','',NULL,'',0,161,NULL,'','0','','',2,'','','','',NULL,NULL,331),(162,'MANO','.','30B,SINGITHURAI,KAYALPATNAM','',NULL,'',0,162,NULL,'','0','','',2,'','','','',NULL,NULL,331),(163,'M.N. SAIT ABDUL KADER','.','96/72,PUTHUKADAI STREET,KAYALPATNAM','',NULL,'',0,163,NULL,'','0','','',2,'','','','',NULL,NULL,331),(164,'ABUBAKKAR SIDDIQ','.','285,CHOLUKKAR STREET,\r\nKAYALPATNAM\r\n','',NULL,'',0,164,NULL,'','0','','',2,'','','','',NULL,NULL,331),(165,'J.AJEES','.','256,CHOLUKKAR STREET,KAYALPATNAM','',NULL,'',0,165,NULL,'','0','','',2,'','','','',NULL,NULL,331),(166,'SITHIK IBRAHIM','.','244,SULAIMAN NAGAR,KAYALPATNAM','',NULL,'',0,166,NULL,'','0','','',2,'','','','',NULL,NULL,331),(167,'K.B.ZAHIR HUSSAIN','.','137/47C DEEVU STREET,KAYALPATNAM','',NULL,'',0,167,NULL,'','0','','',2,'','','','',NULL,NULL,331),(168,'MOHAIDEEN FIRTHOWS','.','21/3F,ALIYAR STREET,KAYALPATNAM','',NULL,'',0,168,NULL,'','0','','',2,'','','','',NULL,NULL,331),(169,'MOHAMED SALIH','.','155,ALIYAR STREET,FATHIMA MANSION,KAYALPATNAM','',NULL,'',0,169,NULL,'','0','','',2,'','','','',NULL,NULL,331),(170,'K.JABURULLAKHAN','.','27C,EAST SITHAN STREET,\r\nKAYALPATNAM','',NULL,'',0,170,NULL,'','0','','',2,'','','','',NULL,NULL,331),(171,'S.FAYAZ AHAMED','.','9A,KOCHIYAR STREET,\r\nKAYALPATNAM','',NULL,'',0,171,NULL,'','0','','',2,'','','','',NULL,NULL,331),(172,'.','.','','',NULL,'',0,172,NULL,'','0','','',2,'','','','',NULL,NULL,331),(173,'MOHAMMED ABDUL KADER BUHARI','.','16/192C MARAICAR PALLI STREET,KAYALPATNAM','',NULL,'',0,173,NULL,'','0','','',2,'','','','',NULL,NULL,331),(174,'MOHAMED TAWFIQ','.','60,KTM STREET,KAYALPATNAM','',NULL,'',0,174,NULL,'','0','','',2,'','','','',NULL,NULL,331),(175,'M.BALASUBRAMANIYAN','.','26B,MELA NESAVU STREET,KAYALPATNAM','',NULL,'',0,175,NULL,'','0','','',2,'','','','',NULL,NULL,331),(176,'T.SHAHUL HAMEED','.','160/60,ALIYAR STREET,\r\nKAYALPATNAM','',NULL,'',0,176,NULL,'','0','','',2,'','','','',NULL,NULL,331),(177,'S.HIDAYATHULLA','.','J.727,MELA NESAVU STREET,KAYALPATNAM','',NULL,'',0,177,NULL,'','0','','',2,'','','','',NULL,NULL,331),(178,'MUBEETH LAHIR','.','38A/1THENGAI PANDAGA SAALAI,\r\nKAYALPATNAM','',NULL,'',0,178,NULL,'','0','','',2,'','','','',NULL,NULL,331),(179,'JAFFER','.','22,DOCTOR COLONY,MANGALAVADI,KAYALPATNAM','',NULL,'',0,179,NULL,'','0','','',2,'','','','',NULL,NULL,331),(180,'N.MOHAIDEEN ABDUL KADER JAILANI','.','44,AZAD STREET,\r\nKAYALPATNAM','',NULL,'',0,180,NULL,'','0','','',2,'','','','',NULL,NULL,331),(181,'ABBAS','.','166A,AKBARSAR STREET,KATALPATNAM','',NULL,'',0,181,NULL,'','0','','',2,'','','','',NULL,NULL,331),(182,'S.MOHAMED YASIN','.','22A/3 MANGALAVADI,KAYALPATNAM','',NULL,'',0,182,NULL,'','0','','',2,'','','','',NULL,NULL,331),(183,'S.M.B. SHEIKNA LEBBAI','.','59,CHINNA NESAVU STREET,KAYALPATNAM','',NULL,'',0,183,NULL,'','0','','',2,'','','','',NULL,NULL,331),(184,'FAKEER MOHIDEEN THOWHITHU','.','111/A,KEELA NAINAR STREET,KAYALPATNAM','',NULL,'',0,184,NULL,'','0','','',2,'','','','',NULL,NULL,331),(185,'S.A.K. MOHAMED OMER','.','19C,PANDAGA SALAI STREET,KAYALPATNAM','',NULL,'',0,185,NULL,'','0','','',2,'','','','',NULL,NULL,331),(186,'S.MOHIDEEN RIYASATH ALI','.','38,AASATH STREET,KAYALPATNAM','',NULL,'',0,186,NULL,'','0','','',2,'','','','',NULL,NULL,331),(187,'N.M.RIYATH RAHMAN','.','73B,CHINNA NESAVU STREET,KAYALPATNAM','',NULL,'',0,187,NULL,'','0','','',2,'','','','',NULL,NULL,331),(188,'H.M.SEYED IBRAHIM','.','176,THEEVU STREET,KAYALPATNAM','',NULL,'',0,188,NULL,'','0','','',2,'','','','',NULL,NULL,331),(189,'K.JAILANI','.','114/58,PARIMAR STREET,KAYALPATNAM','',NULL,'',0,189,NULL,'','0','','',2,'','','','',NULL,NULL,331),(190,'MOHAMED RIYAZDEEN','.','74/A,PARIMAR STREET,KAYALPATNAM\r\n','',NULL,'',0,190,NULL,'','0','','',2,'','','','',NULL,NULL,331),(191,'J.S.BUHARY IRFAN','.','7D/1 MUTHUVAPPA THAIKA STREET,KAYALPATNAM','',NULL,'',0,191,NULL,'','0','','',2,'','','','',NULL,NULL,331),(192,'P.PENSIYAR','.','30A,SINGIDURAI,KAYALPATNAM','',NULL,'',0,192,NULL,'','0','','',2,'','','','',NULL,NULL,331),(193,'JENSON','.','6/A SINGIDURAI,KAYALPATNAM','',NULL,'',0,193,NULL,'','0','','',2,'','','','',NULL,NULL,331),(194,'J.RAGAVAN','.','8,EAST STREET,PEYANVILAI,ARUMUGANERI','',NULL,'',0,194,NULL,'','0','','',2,'','','','',NULL,NULL,331),(195,'B. MOHAIDEEN ABDUL KADAR','.','88/169,K.T.M.STREET, KAYALPATNAM','',NULL,'',0,195,NULL,'','0','','',2,'','','','',NULL,NULL,331),(196,'M.A.K.AZAARUDEEN','.','57A,28G-1, MARUTHUVAR STREET,KAYALPATNAM','',NULL,'',0,196,NULL,'','0','','',2,'','','','',NULL,NULL,331),(197,'S.MOHAIDEEN ABDUL KADER','.','54/172/SITHAN STREET,KAYALPATNAM','',NULL,'',0,197,NULL,'','0','','',2,'','','','',NULL,NULL,331),(198,'SERMARAJ','.','23,ODAKKARAI,KAYALPATNAM','',NULL,'',0,198,NULL,'','0','','',2,'','','','',NULL,NULL,331),(199,'M.SANTHANA KUMAR','.','C4,MOTTAIYAM THOTTAM,KAYALPATNAM','',NULL,'',0,199,NULL,'','0','','',2,'','','','',NULL,NULL,331),(200,'M.W.MOHAMED SALIH','.','191/97,APPA PALLI STREET,KAYALPATNAM','',NULL,'',0,200,NULL,'','0','','',2,'','','','',NULL,NULL,331),(201,'H.A.MUHIYADEEN ABDUL KADER JAILANI','.','36/A3 KOCHIYAR STREET,KAYALPATNAM','',NULL,'',0,201,NULL,'','0','','',2,'','','','',NULL,NULL,331),(202,'IYAPPAN','.','A1,SINGITHURAI,KAYALPATNAM','',NULL,'',0,202,NULL,'','0','','',2,'','','','',NULL,NULL,331),(203,'B.A..SHEIK SULAIMAN','.','36A/138 MELA SITHAN STREET,KAYALPATNAM','',NULL,'',0,203,NULL,'','0','','',2,'','','','',NULL,NULL,331),(204,'ABOOBACKER SIDDIQ','.','131/34A1,ALIYAR STREET.KAYALPATNAM','',NULL,'',0,204,NULL,'','0','','',2,'','','','',NULL,NULL,331),(205,'M.HUSSAIN','.','5C MANGALAVADI,KAYALPATNAM','',NULL,'',0,205,NULL,'','0','','',2,'','','','',NULL,NULL,331),(206,'J.S,UMAR FAROOK','.','292B,MUTHURAMMAN KOVIL STREET,KAYALPATNAM','',NULL,'',0,206,NULL,'','0','','',2,'','','','',NULL,NULL,331),(207,'M.THANGARAJ','.','78,LAKSHMIPURAM,KAYALPATNAM','',NULL,'',0,207,NULL,'','0','','',2,'','','','',NULL,NULL,331),(208,'R.SHAHUL HAMEED','.','27,PALLIVASAL SOUTH STREET,OMANALLUR,MUNNIRPALLAM,TIRUNELVELI','',NULL,'',0,208,NULL,'','0','','',2,'','','','',NULL,NULL,331),(209,'RISWAN','.','20BQUAIDE MILLATH NAGAR,7TH CROSS STREET,KAYALPATNAM','',NULL,'',0,209,NULL,'','0','','',2,'','','','',NULL,NULL,331),(210,'.','.',NULL,NULL,NULL,NULL,NULL,210,NULL,NULL,'0',NULL,NULL,2,NULL,NULL,NULL,NULL,NULL,NULL,0),(211,'B.ABDUL HAMEED','.','KAYALPATNAM','',NULL,'',0,211,NULL,'','0','','',2,'','','','',NULL,NULL,331),(212,'A.MARIYA DEVA SAGAYAM','.','151,SINGITHURAI,KAYALPATNAM','',NULL,'',0,212,NULL,'','0','','',2,'','','','',NULL,NULL,331),(213,'M.S.SHAFI IDRIS','.','83A,K.T.M.STREET,KAYALPATNAM','',NULL,'',0,213,NULL,'','0','','',2,'','','','',NULL,NULL,331),(214,'C.UDHAYA KUMAR','.','59/C SUNAMI NAGAR,KAYALPATNAM','',NULL,'',0,214,NULL,'','0','','',2,'','','','',NULL,NULL,331),(215,'THAMEEM ANSARI ','.','182,SIVAN KOVIL STREET,KAYALPATNAM','',NULL,'',0,215,NULL,'','0','','',2,'','','','',NULL,NULL,2),(216,'S.H.SEYED IBRAHIM','.','73A/212 CHOLUKKAR STREET,KAYALPATNAM','',NULL,'',0,216,NULL,'','0','','',2,'','','','',NULL,NULL,331),(217,'PEER MAIDEEN','.','227B,PERIYA NESAVU STREET,KAYALPATNAM','',NULL,'',0,217,NULL,'','0','','',2,'','','','',NULL,NULL,331),(218,'A.W.JAINUL ABDEEN','.','158,MELA SITHAN STREET,KAYALPATNAM','',NULL,'',0,218,NULL,'','0','','',2,'','','','',NULL,NULL,331),(219,'SHIEK MOHAMMED','.','278-SULAIMAN NAGAR,KAYALPATNAM\r\n','',NULL,'',0,219,NULL,'','0','','',2,'','','','',NULL,NULL,331),(220,'SEYED SAHABUDEEN','.','101/A2 PARIMAR STREET,KAYALPATNAM','',NULL,'',0,220,NULL,'','0','','',2,'','','','',NULL,NULL,331),(221,'N.SHIEK MOHAMMED','.','14,QUIDEA MILLATH NAGAR,KAYALPATNAM','',NULL,'',0,221,NULL,'','0','','',2,'','','','',NULL,NULL,331),(222,'RAJESH','.','119,SIGITHURAI,KAYALPATNAM','',NULL,'',0,222,NULL,'','0','','',2,'','','','',NULL,NULL,331),(223,'S.A.HABEEB MOHAMED RIYAZ','.','19/C1 PANDAGA SALAI CORNER STREET,KAYALPATNAM','',NULL,'',0,223,NULL,'','0','','',2,'','','','',NULL,NULL,331),(224,'S.SANTHANA KUMAR','.','100C,MUTHURAMMAN KOVIL STREET,KAYALPATNAM','',NULL,'',0,224,NULL,'','0','','',2,'','','','',NULL,NULL,331),(225,'.','.','','',NULL,'',0,225,NULL,'','0','','',2,'','','','',NULL,NULL,331),(226,'A.SEYED ABDUL KAREEM','.','127E,VISALACHI AMMAN KOVIL STREET,KAYALPATNAM','',NULL,'',0,226,NULL,'','0','','',2,'','','','',NULL,NULL,331),(227,'M,M.JAFFER SATHIK','.','169B,MUTHURAMAN KOVIL STREET,KAYALPATNAM','',NULL,'',0,227,NULL,'','0','','',2,'','','','',NULL,NULL,331),(228,'S.CHITHIRAVEL','.','53,ODAKKARAI.','',NULL,'',0,228,NULL,'','0','','',2,'','','','',NULL,NULL,331),(229,'S.A.MOHAMMED IBRAHIM MACKEY','.','125/46 DEEVU STREET,\r\nKAYALPATNAM','',NULL,'',0,229,NULL,'','0','','',2,'','','','',NULL,NULL,331),(230,'R.SHIEK DAWOOD','.','21/66,SITHAN STREET,KAYALPATNAM','',NULL,'',0,230,NULL,'','0','','',2,'','','','',NULL,NULL,331),(231,'P.HAJA MYDEEN','.','2A,THENGAI PANDAGA SALAI,KAYALPATNAM','',NULL,'',0,231,NULL,'','0','','',2,'','','','',NULL,NULL,331),(232,'ABDUL KADER','.','57,THENGAAI PANDAGA SALAI,KAYALPATNAM','',NULL,'',0,232,NULL,'','0','','',2,'','','','',NULL,NULL,331),(233,'M.S.MAHIN ABUBACKER','.','146A,ALIYAR STREET,KAYALPATNAM','',NULL,'',0,233,NULL,'','0','','',2,'','','','',NULL,NULL,331),(234,'A.S.ABDUL KADER','.','113/A2,APPAPALLI STREET,KAYALPATNAM','',NULL,'',0,234,NULL,'','0','','',2,'','','','',NULL,NULL,331),(235,'IZZATH MAKKI','.','20C/41 CHOLUKKAR STREERT,KAYALPATNAM','',NULL,'',0,235,NULL,'','0','','',2,'','','','',NULL,NULL,331),(236,'M.SHIEK MOHAMED HAJI','.','68/61,PARIMAR STREET,KAYALPATNAM','',NULL,'',0,236,NULL,'','0','','',2,'','','','',NULL,NULL,331),(237,'J.S.MOHAMED IBRAHIM','.','148,SEETHAKATHI NAGAR,KAYALPATNAM','',NULL,'',0,237,NULL,'','0','','',2,'','','','',NULL,NULL,331),(238,'P.JAWAHERA','.','121,A SITHAN STREET,KAYALPATNAM','',NULL,'',0,238,NULL,'','0','','',2,'','','','',NULL,NULL,331),(239,'MUTHU MOHAMED BALKIS','.','51/19, PARIMAR STREET, KAYALPATNAM','',NULL,'',0,239,NULL,'','0','','',2,'','','','',NULL,NULL,331),(240,'S.NIZARUDEEN','.','38B/70,PARIMAR STREET,KAYALPATNAM','',NULL,'',0,240,NULL,'','0','','',2,'','','','',NULL,NULL,331),(241,'MOHAIDEEN ABDUL KADER','.','183/95,APPA PALLI STREET,KAYALPATNAM','',NULL,'',0,241,NULL,'','0','','',2,'','','','',NULL,NULL,331),(242,'MANOGARAN','.','6A,KANDUPITCHAI THOTTAM,MANNARAJ KOVIL STREET,KAYALPATNAM','',NULL,'',0,242,NULL,'','0','','',2,'','','','',NULL,NULL,331),(243,'S.A.K.MUZAMMIL RAHMANTHULLAH','.','144A,MARAIKAR PALLI STREET,KAYALPATNAM','',NULL,'',0,243,NULL,'','0','','',2,'','','','',NULL,NULL,331),(244,'MAHIN','.','277,SULAIMAN NAGAR,KAYALPATNAM','',NULL,'',0,244,NULL,'','0','','',2,'','','','',NULL,NULL,331),(245,'N.SELVAKUMAR','.','128C/1,KARUPUDAIYAR PALLI VATTAM ,SINGIDHURAI,KAYALPATNAM','',NULL,'',0,245,NULL,'','0','','',2,'','','','',NULL,NULL,331),(246,'V.ANANTHA MAHESH','.','30,THENGAI PANDAGA SALAI,KAYALPATNAM','',NULL,'',0,246,NULL,'','0','','',2,'','','','',NULL,NULL,331),(247,'R.SYED SAHABUDEEN','.','81/1T2,KTM STREET,KAYALPATNAM','',NULL,'',0,247,NULL,'','0','','',2,'','','','',NULL,NULL,331),(248,'A.S.NASEEM SHIHABUDEEN','.','92/23B KOCHIYAR STREET,KAYALPATNAM','',NULL,'',0,248,NULL,'','0','','',2,'','','','',NULL,NULL,331),(249,'MOHAMED TAWFIQ','.','60,KTM,STREET,KAYALPATNAM','',NULL,'',0,249,NULL,'','0','','',2,'','','','',NULL,NULL,331),(250,'K.M.SEGU NOORDEEN','.','38/70,PARIMAR STREET,KAYALPATNAM','',NULL,'',0,250,NULL,'','0','','',2,'','','','',NULL,NULL,331),(251,'M.T.JAINUL ABDEEN','.','94/79,KEELA NAINAR STREET,KAYALPATNAM','',NULL,'',0,251,NULL,'','0','','',2,'','','','',NULL,NULL,331),(252,'S.SHIEK MOHAMED','.','54/172,SITHAN STREET,KAYALPATNAM','',NULL,'',0,252,NULL,'','0','','',2,'','','','',NULL,NULL,331),(253,'A.K.AHAMED MOHAIDEEN','.','128C, KEELA NAINAR STREET,KAYALPATNAM','',NULL,'',0,253,NULL,'','0','','',2,'','','','',NULL,NULL,331),(254,'K.A.A.YOONUS','.','63/42 AASATH STREET,KAYALPATNAM','',NULL,'',0,254,NULL,'','0','','',2,'','','','',NULL,NULL,331),(255,'J.S.JAINUL ABDEEN','.','92/69A,KEELA SITHAN STREET,KAYALPATNAM','',NULL,'',0,255,NULL,'','0','','',2,'','','','',NULL,NULL,331),(256,'A.S.MUNTHASIR','.','18G,7TH CROSS STREET,KAYALPATNAM','',NULL,'',0,256,NULL,'','0','','',2,'','','','',NULL,NULL,331),(257,'M.I.JAFFE SATHIK','.','60A/78,PARIMAR STREET,KAYALPATNAM','',NULL,'',0,257,NULL,'','0','','',2,'','','','',NULL,NULL,331),(258,'S.REEGAN','.','SOUTH COTTON ROAD 7TH STREET,THOOTHUKUDI','',NULL,'',0,258,NULL,'','0','','',2,'','','','',NULL,NULL,331),(259,'CLAIMENT','.','135/C,SINGIDURAI,KAYALPATNAM','',NULL,'',0,259,NULL,'','0','','',2,'','','','',NULL,NULL,331),(260,'S.M.SOLUKU MAHIN','.','38,SADUKKAI STREET,KAYALPATNAM','',NULL,'',0,260,NULL,'','0','','',2,'','','','',NULL,NULL,331),(261,'S.A.K.MUSTHAK','.','41/56,QUITHEA MILLATH NAGAR,KAYALPATNAM','',NULL,'',0,261,NULL,'','0','','',2,'','','','',NULL,NULL,331),(262,'JESURAJ','.','C/133,SINGIDURAI,KAYALPATNAM','',NULL,'',0,262,NULL,'','0','','',2,'','','','',NULL,NULL,331),(263,'S.O.MOHAIDEEN ABDUL KADER','.','282,NORTH MUTHURAMAN KOVIL STREET,KAYALPATNAM','',NULL,'',0,263,NULL,'','0','','',2,'','','','',NULL,NULL,331),(264,'S.H.SEYED IBRAHIM','.','195,PANDAGA SALAI CORNER STREET,KAYALPATNAM','',NULL,'',0,264,NULL,'','0','','',2,'','','','',NULL,NULL,331),(265,'T.RAJENTHIRAN','.','14/7,SINGIDURAI,KAYALPATNAM','',NULL,'',0,265,NULL,'','0','','',2,'','','','',NULL,NULL,331),(266,'S.SUSAI','.','128/C2KARUPUDAIYARPALLI,SINITHURAI,KAYALPATNAM','',NULL,'',0,266,NULL,'','0','','',2,'','','','',NULL,NULL,331),(267,'NOORDEEN','.','98,KOCHIYAR STREET,KAYALPATNAM','',NULL,'',0,267,NULL,'','0','','',2,'','','','',NULL,NULL,331),(268,'B.BASHEER ','.','88/55,PARIMAR STREET,\r\nKAYALPATNAM','',NULL,'',0,268,NULL,'','0','','',2,'','','','',NULL,NULL,331),(269,'A.MARIYA DEVA SAGAYAM','.','151,SINGITHURAI,KAYALPATNAM','',NULL,'',0,269,NULL,'','0','','',2,'','','','',NULL,NULL,331),(270,'S.H.SEYED IBRAHIM','.','31A,ALIYAR STREET,KAYALPATNAM','',NULL,'',0,270,NULL,'','0','','',2,'','','','',NULL,NULL,331),(271,'S.B.THAMEEMNL AMARI','.','17/G,KOCHIYAR STREET,KAYALPATNAM','',NULL,'',0,271,NULL,'','0','','',2,'','','','',NULL,NULL,331),(272,'S.K.HASSAN IBRAHIM ','.','20A/82 KOCHIYAR STRET,KAYALPATNAM','',NULL,'',0,272,NULL,'','0','','',2,'','','','',NULL,NULL,331),(273,'M.M.S.MOHAMED ABDUL KADER NIYAS','.','11A,MARAIKAR PALLI STREET,KAYALPATNAM','',NULL,'',0,273,NULL,'','0','','',2,'','','','',NULL,NULL,331),(274,'A.R.MOHAMED HAJA RASHEED','.','141/39A ALIYAR STREET,KAYALPATNAM','',NULL,'',0,274,NULL,'','0','','',2,'','','','',NULL,NULL,331),(275,'T.KUMAR','.','34,LAKSHMIPURAM,KAYALPATNAM','',NULL,'',0,275,NULL,'','0','','',2,'','','','',NULL,NULL,331),(276,'N.M.RIYATH RAHMAN','.','73B,CHINNA NESAVU STREET,KAYALPATNAM','',NULL,'',0,276,NULL,'','0','','',2,'','','','',NULL,NULL,331),(277,'PETCHI KATHIRAVAN','.','17,THAIKKAPURAM,KAYALPATNAM','',NULL,'',0,277,NULL,'','0','','',2,'','','','',NULL,NULL,331),(278,'MANO','.','30B,SINGITHURAI,KAYALPATNAM','',NULL,'',0,278,NULL,'','0','','',2,'','','','',NULL,NULL,331),(279,'C.BABU DURAI','.','AMBETHKAR PURAM,KATTU MOHUDOOM PALLI,KAYALPATNAM','',NULL,'',0,279,NULL,'','0','','',2,'','','','',NULL,NULL,331),(280,'S.A.K.MOHAMED OMER','.','19,CPANDAGA SALAI STREET,KAYALPATNAM','',NULL,'',0,280,NULL,'','0','','',2,'','','','',NULL,NULL,331),(281,'VIMAL','.','13B,KARUPUDAIYAR PALLI STREET,KAYALPATNAM','',NULL,'',0,281,NULL,'','0','','',2,'','','','',NULL,NULL,331),(282,'ABUBACKER SIDDIQUE','.','16/56-G VISHALAKSHMI AMMAN KOVIL STREET,KAYALPATNAM','',NULL,'',0,282,NULL,'','0','','',2,'','','','',NULL,NULL,331),(283,'NASBER ALI','.','33/5JVANNAKUDI STREET,KAYALPATNAM','',NULL,'',0,283,NULL,'','0','','',2,'','','','',NULL,NULL,331),(284,'K.JALALUDEEN','.','5C,SUNAMI NAGAR,KAYALPATNAM','',NULL,'',0,284,NULL,'','0','','',2,'','','','',NULL,NULL,331),(285,'R.SHAHUL HAMEED','.','27,PALLIVASAL SOUTH STREET,OMANALLUR,MUNNIRPALLAM,\r\nTIRUNELVELI','',NULL,'',0,285,NULL,'','0','','',2,'','','','',NULL,NULL,331),(286,'AHAMED KASIM','.','170A,KARUTHAMBI MARAIKAR STREET,KAYALPATNAM','',NULL,'',0,286,NULL,'','0','','',2,'','','','',NULL,NULL,331),(287,'.','.',NULL,NULL,NULL,NULL,NULL,287,NULL,NULL,'0',NULL,NULL,2,NULL,NULL,NULL,NULL,NULL,NULL,0),(288,'S.M.ISMAIL IRSATH','.','27A,KOMAN STREET,KAYALPATNAM','',NULL,'',0,288,NULL,'','0','','',2,'','','','',NULL,NULL,331),(289,'S.M.B.MOHUDOOM MUHAMATHU','.','44/49,AZAD STREET,KAYALPATNAM','',NULL,'',0,289,NULL,'','0','','',2,'','','','',NULL,NULL,331),(290,'L.ASKAR ALI','.','87/22A,ALIYAR STREET,KAYALPATNAM','',NULL,'',0,290,NULL,'','0','','',2,'','','','',NULL,NULL,331),(291,'M.M.JAFFER SATHIK','.','169B,M.A,K, STREET\r\nKAYALPATNAM','',NULL,'',0,291,NULL,'','0','','',2,'','','','',NULL,NULL,331),(292,'S.H.AKBAR ALI','.','32/92,PARIMAR STREET,KAYALPATNAM','',NULL,'',0,292,NULL,'','0','','',2,'','','','',NULL,NULL,331),(293,'YOUSUF SAHIB','.','275,CHOLUKKAR STREET,\r\nKAYALPATNAM','',NULL,'',0,293,NULL,'','0','','',2,'','','','',NULL,NULL,331),(294,'K.VIMAL','.','13-B,SINGITHURAI,KAYALPATNAM','',NULL,'',0,294,NULL,'','0','','',2,'','','','',NULL,NULL,331),(295,'SYED ALI BADUSHA','.','94A,K.T.M. STREET,\r\nKAYALPATNAM','',NULL,'',0,295,NULL,'','0','','',2,'','','','',NULL,NULL,331),(296,'S.K.V.SABURUDEEN','.','75,PARIMAR STREET,\r\nKAYALPATNAM','',NULL,'',0,296,NULL,'','0','','',2,'','','','',NULL,NULL,331),(297,'SEYED AHAMED KABEER','.','12E,WEST NESUVU STREET,KAYALPATNAM','',NULL,'',0,297,NULL,'','0','','',2,'','','','',NULL,NULL,331),(298,'A.SHIEK SAHUL HAMEED ','.','64/A,APPAPALLI STREET,\r\nKAYALPATNAM','',NULL,'',0,298,NULL,'','0','','',2,'','','','',NULL,NULL,331),(299,'H.KASALI MARAIKAR','.','28/B,ODAKARAI FIRST STREET,\r\nKAYALPATNAM','',NULL,'',0,299,NULL,'','0','','',2,'','','','',NULL,NULL,331),(300,'B.MOHAMMED ASHIK','.','49/15 PARIMAR STREET,\r\nKAYALPATNAM','',NULL,'',0,300,NULL,'','0','','',2,'','','','',NULL,NULL,331),(301,'MUTHUWAPPA','.','15C,,SUNAMI NAGAR,\r\nKAYALPATNAM','',NULL,'',0,301,NULL,'','0','','',2,'','','','',NULL,NULL,331),(302,'M.H.MOOSA','.','27/4,PARIMAR STREET,\r\nKAYALPATNAM','',NULL,'',0,302,NULL,'','0','','',2,'','','','',NULL,NULL,331),(303,'S.A.K.MUZAMMIL RAHMANTHULLAH','.','144A,MARAIKAR PALLI STREET,\r\nKAYALPATNAM','',NULL,'',0,303,NULL,'','0','','',2,'','','','',NULL,NULL,331),(304,'P.S.L.MUTHU MOHAMED','.','251,NAINAR STREET,\r\nKAYALPATNAM','',NULL,'',0,304,NULL,'','0','','',2,'','','','',NULL,NULL,331),(305,'M.BALASUBRAMANIYAN','.','26B,MELA NESAVU STREET,\r\nKAYALPATNAM','',NULL,'',0,305,NULL,'','0','','',2,'','','','',NULL,NULL,331),(306,'C.UDHAYA KUMAR','.','59/C,SUNAMI NAGAR,\r\nKAYALPATNAM','',NULL,'',0,306,NULL,'','0','','',2,'','','','',NULL,NULL,331),(307,'E.MARI MUTHU','.','30,THENGAI PANDAGA SALAI STREET,\r\nKAYALPATNAM','',NULL,'',0,307,NULL,'','0','','',2,'','','','',NULL,NULL,331),(308,'F.HADDAD','.','85/27A,MELA SITHAN STREET,KAYALPATNAM','',NULL,'',0,308,NULL,'','0','','',2,'','','','',NULL,NULL,331),(309,'V.M.M. ABDULLAH SAHIB','.','14-G,QUITHEA MMILLATH NAGAR,\r\nKAYALPATNAM','',NULL,'',0,309,NULL,'','0','','',2,'','','','',NULL,NULL,331),(310,'J.H.NAINA MOHAMMED','.','DHEEVU STREET,KAYALPATNAM','',NULL,'',0,310,NULL,'','0','','',2,'','','','',NULL,NULL,331),(311,'P.PENSIGAR','.','30/A SOUTH COLONY,SINGITHURAI,\r\nKAYALPATNAM','',NULL,'',0,311,NULL,'','0','','',2,'','','','',NULL,NULL,331),(312,'M.M.UMAR FAROOK','.','128A/2 KEELA NAINAR STREET,\r\nKAYALPATNAM','',NULL,'',0,312,NULL,'','0','','',2,'','','','',NULL,NULL,331),(313,'A.THAMEER ANSAARI','.','182A,SIVAN KOVIL,STREET','',NULL,'',0,313,NULL,'','0','','',2,'','','','',NULL,NULL,2),(314,'N.SHIEK MOHAMMED','.','14-G,QUITHEA MILLATH NAGAR,\r\nKAYALPATNAM','',NULL,'',0,314,NULL,'','0','','',2,'','','','',NULL,NULL,331),(315,'F.HADDAD','.','85/27A,MELA SITHAN STREET,\r\nKAYALPATNAM','',NULL,'',0,315,NULL,'','0','','',2,'','','','',NULL,NULL,331),(316,'C.BABU DURAI','.','AMBETHKAR PURAM,KATTU MOHUDOOMPALLI,\r\nKAYALPATNAM','',NULL,'',0,316,NULL,'','0','','',2,'','','','',NULL,NULL,331),(317,'K.ABUBACKER SITHIQ','.','17,A SADUKKAI STREET,\r\nKAYALPATNAM','',NULL,'',0,317,NULL,'','0','','',2,'','','','',NULL,NULL,331),(318,'P.M.BADUL ASHAB','.','84A/1/186 CHOLUKKAR STREET,\r\nKAYALPATNAM','',NULL,'',0,318,NULL,'','0','','',2,'','','','',NULL,NULL,331),(319,'NAGOOR KANI','.','51/40,MANGALAVADI.\r\nKAYALPATNAM','',NULL,'',0,319,NULL,'','0','','',2,'','','','',NULL,NULL,331),(320,'B.A.MOHAIDEEN BADUSA','.','66/120A, K.T.M STREET,\r\nKAYALPATNAM','',NULL,'',0,320,NULL,'','0','','',2,'','','','',NULL,NULL,331),(321,'S.M.SYED IBRAHIM','.','243,SULAIMAN NAGAR,\r\nKAYALPATNAM','',NULL,'',0,321,NULL,'','0','','',2,'','','','',NULL,NULL,331),(322,'M.SANTHANA KUMAR','.','C4,MOTTAIYAM THOTTAM,KAYALPATNAM','',NULL,'',0,322,NULL,'','0','','',2,'','','','',NULL,NULL,331),(323,'RIYAS SALIKHAN','.','30,A,QUAITHE MILLATH NAGAR 6TH CROSS STREET,\r\nKAYALPATNAM','',NULL,'',0,323,NULL,'','0','','',2,'','','','',NULL,NULL,331),(324,'GOODWIN','.','C17,KARPUDAIYAR PALLI VATTAM,SINGITHURAI,KAYALPATNAM','',NULL,'',0,324,NULL,'','0','','',2,'','','','',NULL,NULL,331),(325,'A.CITHIRAI SELVAN','.','22A, POONTHOTTAM,\r\nKAYALPATNAM','',NULL,'',0,325,NULL,'','0','','',2,'','','','',NULL,NULL,331),(326,'VEL DURAI','.','43,C LAKSHMIPURAM,\r\nKAYALPATNAM','',NULL,'',0,326,NULL,'','0','','',2,'','','','',NULL,NULL,331),(327,'S.KITHIR SULAIMAN','.','140/F8 UCHI MAHALI AMMAN KOVIL STREET,,\r\nKAYALPATNAM','',NULL,'',0,327,NULL,'','0','','',2,'','','','',NULL,NULL,331),(328,'M.BUHARI','K.MUHAIDEEN FATHIMA JOKARA','','',NULL,'',NULL,328,NULL,'','7502126730','','',2,'LABOUR','','','',NULL,NULL,331),(329,'D.JAJIN','J.ABITHA','C/4 SINGITHURAI ,KARPUDAIYAR PALLI VATTAM,KAYALPATNAM','',NULL,'',0,329,NULL,'','9384214604','','',2,'SHIP','HOUSE WIFE','','',NULL,NULL,331),(330,'K.HASAN ABDUL KATHER','S.SAFEEKA','','23/59B PARIMAR STREET,\r\nKAYALPATNAM',NULL,'72,000',0,330,NULL,'','9865179865','','',2,'B.SC, CHEMISTRY','12TH STD','','',NULL,NULL,331),(331,'MOHAMED ABDUL KADER','SULAIHA MUNAVARA','','120/242 SADUCKAI STREET,KAYALPATNAM',NULL,'72,000',0,331,NULL,'','8072168772','9994540309','',2,'12TH STD,WAGES','B.COM/HOUSE WIFE','','',NULL,NULL,331),(332,'MUTHU MOGUDOOM','MOGDOOM MEERA NACHI','','11,NAINAR STREET,\r\nKAYALPATNAM',NULL,'120000',0,332,NULL,'','7667120155','','',2,'H.SEC/EMPLOYEE','H.SEC/HOUSE WIFE','','',NULL,NULL,331),(333,'J.S.SEYED ISMAHIL','S.L.ZAHIRA NASEEM','','16,KEELA NAINAR STREET,KAYALPATNAM',NULL,'',NULL,333,NULL,'','7845869776','','',2,'B.E.(CSE),/BUISNESS','B.COM','','',NULL,NULL,331),(334,'A.I.SAMU SIHABUDEEN','T.SITHI HAJARA','','133B,ALIYAR STREET,KAYALPATNAM',NULL,'',NULL,334,NULL,'','9659102653','','',2,'COOLIE','B.A.ENGLISH LITERATURE','','',NULL,NULL,331),(335,'M.J..MOHAMED THAMBY SADAM HUSSAIN','M.A. MUTHU AMINA','','8/6A,MUTHU WAPPA THAIKA ST,KAYALPATNAM',NULL,'1,44,000',NULL,335,NULL,'','8056085086','7010408106','',2,'BBA,DLS/BUISNESS','M.SC(CS),D.EL.ED/HOUSEWIFE','','',NULL,NULL,2),(336,'A.KASSALI MARAICAYAR','M.W.FATHIMA SAMEERA','','94/51A,PARIMAR STREET,KAYALPATNAM',NULL,'',0,336,NULL,'','8056313946','','',2,'B.COM','BBA','','',NULL,NULL,2),(337,'ABDUL KADER','FATHIMA FAHMIDHA','','56/69B,PARIMAR STREET,\r\nKAYALPATNAM',NULL,'84,000',NULL,337,NULL,'','7845107040','','',2,'MCA/SOFTWARE','BBA/HOUSE WIFE','','',NULL,NULL,2),(338,'N,A.AHAMED JUNAIDH','J.S.ZUHARA FAIKA','','41A,KARPUDAIYAR PALLI VATTAM,KAYALPATNAM',NULL,'72,000',NULL,338,NULL,'','7806977736','','',2,'ENGINEER','B.SC(CS)/HOUSE WIFE','','',NULL,NULL,2),(339,'SHAIK MAIDEEN','MAHAMUTHA','','77C,SINGIDURAI,KAYALPATNAM',NULL,'',NULL,339,NULL,'','8015803976','','',2,'','','','',NULL,NULL,2),(340,'K.K.N.SULAIMAN HUSSAIN','M.FATHIMA FASEELA','','5B,KEELA NAINAR STREET,KAYALPATNAM',NULL,'',0,340,NULL,'','8438621994','','',2,'','','','',NULL,NULL,2),(341,'PRAKASH','SARAL','','8,KARPUDAIYAR PALLI VATTAM,SINGIDURAI,KAYALPATNAM',NULL,'',NULL,341,NULL,'','8344889022','','',2,'FISHER MAN','12TH STD','','',NULL,NULL,2),(342,'V.CHITHIRAIRAJ','C.SORNALAKSHMI','','28,POONDHOTTAM,KAYALPATNAM',NULL,'',NULL,342,NULL,'','8754779754','','',2,'','','','',NULL,NULL,2),(343,'SHAFEEQ','FATHIMA SHAHEEN','','21/106,SITHAN STREET,KAYALPATNAM',NULL,'',NULL,343,NULL,'','9790309624','8300760654','',2,'MBA','BE','','',NULL,NULL,2),(344,'A.K.KAJA MOIDEEN','S.JAITHOON JAHIR NISHA','','29A VADAKKU COLONY,KARPUDAIYAR PALLI VATTAM,KAYALPATNAM.',NULL,'96,000',NULL,344,NULL,'','6383848058','','',2,'1OTH/COOLIE','BBA/HOUSE WIFE','','',NULL,NULL,2),(345,'S.M.MAHMOOD MULTHAZIM','M.N.SEYED ALI FATHIMA','','251,MUTHURAMMAN KOVIL STREET,KAYALPATNAM',NULL,'',NULL,345,NULL,'','8428767208','','',2,'EMPLOYEE','HOUSE WIFE','','',NULL,NULL,2),(346,'P.RADHAKRISHNAN','R.SHARADA','','76/56 ODAKKARAI,NORTH,STREET,KAYALPATNAM',NULL,'',0,346,NULL,'','9025727108','8217887482','',2,'D.EEE','B.COM','','',NULL,NULL,2),(347,'BEERMOHAMMED SAFIRULLA','SHABIYA BANU','','35/A10VAANIYAKUDI STREET,KAYALPATNAM',NULL,'',NULL,347,NULL,'','7708423014','','',2,'SSLC/COOLIE','SSLC','','',NULL,NULL,2),(348,'MUHAMADHU MUHAJIR','RABITH JAGIRA','','8C,ALIYAR STREET,KAYALPATNAM',NULL,'',NULL,348,NULL,'','8220724992','8148314791','',2,'','','','',NULL,NULL,2),(349,'A.MOHAMED INAAMUL HASAN','S.BATHUR NISHA','','',NULL,'',NULL,349,NULL,'','7092386306','','',2,'EEE.BE','12TH STD','','',NULL,NULL,2),(350,'KIRAKTAN','BIULA','','135C,KARPUDAIYAR PALLI  VATTAM,KAYALPATNAM',NULL,'',0,350,NULL,'','8220129351','','',2,'','12TH STD','','',NULL,NULL,2),(351,'MURUGAN','UTCHIMAHALI','','15/5B,VANIYAKUDI STREET,KAYALPATNAM',NULL,'72,000',NULL,351,NULL,'','9043950703','','',2,'12TH STD/COOLIE','6TH STD','','',NULL,NULL,2),(352,'M.S.IBRAHIM','M.I.SEYED NASUHA','','177/F,SOLUKKAR STREET,KAYALPATNAM',NULL,'',0,352,NULL,'','9345309630','','',2,'10TH STD','6TH STD','','',NULL,NULL,2),(353,'A.SAMSUKANI','S.KAMILA','','56E/,THENGAI PANDAGA SALAI ,KAYALPATNAM',NULL,'',NULL,353,NULL,'','9587509417','9976699417','',2,'8TH STD/AUTO DRIVER','D.M.E/HOUSE WIFE','','',NULL,NULL,2),(354,'M.M.AHAMED SHAFEEQ','BUSHRA ANWAR','','132/68,ALIYAR STREET,KAYALPATNAM',NULL,'84,000',NULL,354,NULL,'','9840544952','9940651991','',2,'12TH/BUISNESS','','','',NULL,NULL,2),(355,'P.S.L.MUTHU MOHAMED','SADAK FATHIMA FASEEHA','','251,NAINAR STREET,KAYALPATNAM',NULL,'',NULL,355,NULL,'','9003142160','9629658726','',2,'M.COM','12TH STD','','',NULL,NULL,2),(356,'M.S. MOHAMED IBRAHIM','S.D.BALKEES BUSHRA','','7/1D KOCHIYAR STREET,KAYALPATNAM',NULL,'75,000',NULL,356,NULL,'','9597655741','','',2,'MBA','BBA','','',NULL,NULL,2),(357,'J.MICHAEL','R.MUTHU SARITHA','','C5,KARPUDAIYAR PALLI VATTAM,SINGITHURAI,KAYALPATNAM',NULL,'',NULL,357,NULL,'','8220015465','','',2,'','','','',NULL,NULL,2),(358,'M.K.SEYES SAHABUDEEN','A.N.SUMAIYA FARITHA','','101/A2,PARIMAR STREET,KAYALPATNAM',NULL,'',0,358,NULL,'','8056415174','','',2,'8TH/ KARIKKADAI','12TH/HOUSEWIFE','','',NULL,NULL,2),(359,'MANIKANDAN','MEENAKSHI','','299/A2 KOMAN PUTHOOR STREET,KAYALPATNAM',NULL,'1,00,000',NULL,359,NULL,'','9159813393','','',2,'ELECTRICIAN','','','',NULL,NULL,2),(360,'S.KALEEL RAHMAN','N.SEGHU NOORDHEEN UMMAL','','77C,SINGITHURAI,KARPUDAIYAR PALLI VATTAM,KAYALPATNAM',NULL,'',NULL,360,NULL,'','9566260905','','',2,'12TH/FISHERMAN','12TH/DCE','','',NULL,NULL,2),(361,'S.ABUTHAHIR','S.UMAR AMINA','','11,PANDAGA SALAI CORNER STREET,KAYALPATNAM',NULL,'72,000',NULL,361,NULL,'','9043244241','','',2,'11TH/COOLIE','B.SC IT/HOUSEWIFE','','',NULL,NULL,2),(362,'A.KALANTHER SHAHUL HAMEED','R.MOHAMED HASEENA','','229K,ALIYAR STREET,KAYALPATNAM',NULL,'',NULL,362,NULL,'','9361597977','','',2,'10TH/MOBILE SALES-SERVICE','12TH/HOUSEWIFE','','',NULL,NULL,2),(363,'P.M.SHEIK MUHAMMED','J.AHAMAD FATHIMA','','22A/A,PARIMAR STREET,KAYALPATNAM',NULL,'',NULL,363,NULL,'','6382638976','','',2,'DRIVER','','','',NULL,NULL,2),(364,'M.A.C.SULAIMAN','M.M.MOHAMED ASIYA','','113/26,K.M.KATCHERI STREET,KAYALPATNAM',NULL,'',NULL,364,NULL,'','8754733253','','',2,'B.A/COOLIE','B.A/HOUSEWIFE','','',NULL,NULL,2),(365,'KARTHICK','SEETHA LAKSHMI','','3A,MANGALAVADI ,KAYALPATNAM',NULL,'',NULL,365,NULL,'','8056469450','','',2,'DIPLOMA IN CIVIL ENGINEERING','DNA','','',NULL,NULL,2),(366,'SHIEK MOHAMED ALI','MUBEENA ','','140/A,UCCHINIMAHALI AMMAN KOVIL STREET,KAYALPATNAM',NULL,'',NULL,366,NULL,'','9629376901','','',2,'B.SC','12TH','','',NULL,NULL,2),(367,'R.STANLEY','S.BARATHI','','144C,KARPUDAIYAR PALLI VATTAM ,SINGITHURAI KAYALPATNAM,\r\n',NULL,'',0,367,NULL,'','9500824594','9894393477','',2,'8TH','11TH','','',NULL,NULL,2),(368,'M.IYAPPAN','I.PRAVEENA','','30,SINGITHURAI ,KAYALPATNAM',NULL,'',NULL,368,NULL,'','9787640460','','',2,'','','','',NULL,NULL,2),(369,'SHIEK MAITHIN','THAMIM ANSHA','','32,QUAIDEA MILLATH NAGAR,KAYALPATNAM',NULL,'',NULL,369,NULL,'','6381059945','','',2,'','','','',NULL,NULL,2),(370,'K.M.HAJI ALI','KATHIJA BEEVI','','104/2D,UCHI MAHALI AMMAN KOVIL STREET.KAYALPATNAM',NULL,'20,000',NULL,370,NULL,'','9677302776','','',2,'DMLT','5TH','','',NULL,NULL,2),(371,'H.MOHAMED ANUFKHAN','K.M.AYSHA SITHIKKA','','38B/70 PARIMAR STREET,KAYALPATNAM',NULL,'',NULL,371,NULL,'','9361934623','','',2,'DRIVER','12TH STD','','',NULL,NULL,2),(372,'D.IMAM','H.LAILA','','59/13A,SINNA NESAVU STREET,KAYALPATNAM',NULL,'24000 ',0,372,NULL,'','9003569871','','',2,'12THSTD/LABOUR','8TH STD','','',NULL,NULL,2),(373,'IYYAPPAN','KARPAGAVALLI','','24,MELA NEASAVU STREET,KAYALPATNAM',NULL,'',NULL,373,NULL,'','8110906374','8523964604','',2,'ELECTRIC SUPERVISOR','','','',NULL,NULL,2),(374,'MANIKANDAN','SANTHANA SOWMIYA','','11E, VANDI MALAICHI AMMAN KOVIL STREET,KAYALPATNAM',NULL,'',NULL,374,NULL,'','9626812031','','',2,'CARPENTER','12TH STD','','',NULL,NULL,2),(375,'ALLAH PITCHAI','ANANTHA JOTHI','','KATTU MOGUDUM STREET,VEERAPANDIPATTANAM',NULL,'75,000',NULL,375,NULL,'','8754098695','7550342562','',2,'8TH/CENTRING','10TH','','',NULL,NULL,2),(376,'A.R.SULAIMAN THAKIBU','M.N.M.ROSHAN FAIZA','','193I/14 BEACH ROAD KAYALPATNAM',NULL,'',NULL,376,NULL,'','9865105815','','',2,'12TH/BUISNESS','12TH STD','','',NULL,NULL,2),(377,'MANSOOR ALI','BALKIS BADURIYA','','90C,SINGIDURAI,KAYALPATNAM',NULL,'60,000',NULL,377,NULL,'','8870840978','','',2,'8TH STD/COOLIE','','','',NULL,NULL,2),(378,'A.ARABATH IMAN','A.R.SHAJEERA','','14/56F,',NULL,'3,00,000',NULL,378,NULL,'','7845921212','','',2,'B.TECH IT/ ENGINEER','B.TECH IT/ HOME MAKER','','',NULL,NULL,2),(379,'JEROM','REKA','','30/A3,SINGIDURAI ,KAYALPATNAM',NULL,'72,000',0,379,NULL,'','6383744622','','',2,'FISHER MAN','','','',NULL,NULL,2),(380,'ABUBUCKER SIDDIQ','M.N.HASEENA NIHLA','','147/A1,ALIYAR STREET,KAYALPATNAM',NULL,'',NULL,380,NULL,'','6379117712','','',2,'10TH STD','12TH STD','','',NULL,NULL,2),(381,'SEGUMOHAMED','SABURA BEEVI','','',NULL,'',NULL,381,NULL,'','9566077336','','',2,'HOTEL','','','',NULL,NULL,2),(382,'F.SAMU SHAHUL HAMEED','M.M.RAHMATH JALEELA','','119/29B,ALIYAR STREET,KAYALPATNAM',NULL,'84,000',NULL,382,NULL,'','9944437724','','',2,'10TH STD','12TH STD','','',NULL,NULL,2),(383,'RUBALTAN','AROCKIAM','','C13,KARPUDDAIYAR PALLI VATTAM,SINGIDURAI,KAYALPATNAM',NULL,'2,40,000',0,383,NULL,'','7708560928','8122404697','',2,'12TH/FISHERMAN','10TH/HOUSWIFIE','','',NULL,NULL,2),(384,'T.UMAR FAROOK','P.M.JAMALIYA','','57/114,PARIMAR STREET,KAYALPAYTNAM',NULL,'',NULL,384,NULL,'','9344500381','','',2,'9TH/CATERING','12TH STD','','',NULL,NULL,2),(385,'RAKSHAN','YUVANIYA','','126,KARPPUDAIYAR PALLI VATTAM ,KAYALPATNAM',NULL,'',NULL,385,NULL,'','9942587314','','',2,'FISHER MAN','B.A.ENG/HOUSE WIFE','','',NULL,NULL,2),(386,'GULAM RASOOL','SAKILA BANU','','57,PARIMAR STREET,KAYALPATNAM',NULL,'',NULL,386,NULL,'','9092658212','','',2,'6TH/CATERING','10TH/HOUSWIFIE','','',NULL,NULL,2),(387,'M.S.DAWOOD NAINA','HALIMA MURSHIDHA','','199A/73D K.T.M. STREET,KAYALPATNAM',NULL,'72,000',NULL,387,NULL,'','9487080885','','',2,'SALES MAN','','','',NULL,NULL,2),(388,'MOHIDHEEN RIYASATH ALI','MARIYAM MASHKOORA','','38,AZAD STREET,KAYALPATNAM',NULL,'1,10,000',NULL,388,NULL,'','9629234834','9629234835','',2,'12TH/COOLIE','12TH STD','','',NULL,NULL,2),(389,'ASKAR ALI','MUNAWWARA FATHIMA','','106/104 K.T.M STREET,KAYALPATNAM',NULL,'1,00,000',NULL,389,NULL,'','7418328013','','',2,'12TH/BUISNESS','B.A.ENG','','',NULL,NULL,2),(390,'M.ASWIN','A.SNABAHA','','78/74,KARPUDAIYAR PALLI VATTAM ,SINGIDURAI',NULL,'50,000',NULL,390,NULL,'','9566686932','','',2,'SEAMAN','HOUSE WIFE','','',NULL,NULL,2),(391,'II','OO','','',NULL,'',NULL,391,NULL,'','0000000000','','',2,'','','','',NULL,NULL,2),(392,'M.I.MOHAMED YOONUS','SULAIHA FARHANA','','15,AZAD STREET,KAYALPATNAM',NULL,'',NULL,392,NULL,'','6380691110','','',2,'WORK IN ABROAD','HOUSE WIFE','','',NULL,NULL,2),(393,'S.MEERA MUGAIDEEN','M.K.FATHIMA RIZWANA','','62/A2 PARIMAR STREET,KAYALPATNAM',NULL,'1,80,000',NULL,393,NULL,'','8124463247','','',2,'CATERING MANAGEMENT','BBA','','',NULL,NULL,2),(394,'S.MEERA MUGAIDEEN','M.K.FATHIMA RIZWANA','','62/A2 PARIMAR STREET,KAYALPATNAM',NULL,'1,80,000',NULL,394,NULL,'','8124463247','','',2,'CATERING MANAGEMENT','BBA','','',NULL,NULL,2),(395,'S.ASWIN','A.BISMITHA','','128/C2 KARPUDAIYAR PALLI VATTAM,KAYALPATNAM',NULL,'',NULL,395,NULL,'','7604989364','8825876583','',2,'5TH STD','12TH STD','','',NULL,NULL,2),(396,'J.THAMEEM ANZAR','J.YASMIN','','98,KOTCHIYAR STREET,KAYALPATNAM',NULL,'72,000',NULL,396,NULL,'','9003389708','','',2,'COOLIE','9TH STD','','',NULL,NULL,2),(397,'MOHAMED BUHARY','HAIRA BEEVI','','35A/1 DEEVU STREET,KAYALPATNAM',NULL,'90,000',NULL,397,NULL,'','9677070313','','',2,'1OTH/BUISNESS','9TH/HOUSE WIFE','','',NULL,NULL,2),(398,'CCC','CCCC','','',NULL,'',NULL,398,NULL,'','0000000000','','',2,'','','','',NULL,NULL,2),(399,'XXXX','YYYY','','',NULL,'',NULL,399,NULL,'','0000000000','','',2,'','','','',NULL,NULL,2),(400,'M.M.KHADER MUHHYIDEEN NUHMAN','M.A.FATHIMA JASMINE','','95A/25,SITHAN STREET,KAYALPATNAM',NULL,'90,000',NULL,400,NULL,'','8056706471','','',2,'IT MANAGER','B.SC','','',NULL,NULL,2);
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
  `userid` int(11) DEFAULT NULL,
  PRIMARY KEY (`idperioddetails`),
  KEY `periodd_idx` (`periodmasterid`),
  CONSTRAINT `periodd` FOREIGN KEY (`periodmasterid`) REFERENCES `periodmaster` (`idperiodmaster`) ON DELETE CASCADE ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `perioddetails`
--

LOCK TABLES `perioddetails` WRITE;
/*!40000 ALTER TABLE `perioddetails` DISABLE KEYS */;
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
  `userid` int(11) DEFAULT NULL,
  PRIMARY KEY (`idperiodmaster`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `periodmaster`
--

LOCK TABLES `periodmaster` WRITE;
/*!40000 ALTER TABLE `periodmaster` DISABLE KEYS */;
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
  `userid` int(11) DEFAULT NULL,
  PRIMARY KEY (`positionid`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `position`
--

LOCK TABLES `position` WRITE;
/*!40000 ALTER TABLE `position` DISABLE KEYS */;
INSERT INTO `position` VALUES (1,'H.M',2,2),(3,'TEACHER',2,2);
/*!40000 ALTER TABLE `position` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `pudetails`
--

DROP TABLE IF EXISTS `pudetails`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `pudetails` (
  `idpudetails` int(11) NOT NULL AUTO_INCREMENT,
  `exampassedappearance` int(11) DEFAULT NULL,
  `exampassedyear` varchar(45) DEFAULT NULL,
  `exampassedregno` varchar(45) DEFAULT NULL,
  `exampassedresultwithclass` varchar(100) DEFAULT NULL,
  `secondlanguage` varchar(45) DEFAULT NULL,
  `aggregatemarkssslc` varchar(45) DEFAULT NULL,
  `optionalsubjects` varchar(200) DEFAULT NULL,
  `compulsorysubjects` varchar(500) DEFAULT NULL,
  `sslcmediuminstruction` varchar(45) DEFAULT NULL,
  `pumediuminstruction` varchar(45) DEFAULT NULL,
  `userid` int(11) DEFAULT NULL,
  PRIMARY KEY (`idpudetails`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `pudetails`
--

LOCK TABLES `pudetails` WRITE;
/*!40000 ALTER TABLE `pudetails` DISABLE KEYS */;
/*!40000 ALTER TABLE `pudetails` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `purchaseorder`
--

DROP TABLE IF EXISTS `purchaseorder`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `purchaseorder` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `externalid` varchar(45) DEFAULT NULL,
  `itemid` int(11) DEFAULT NULL,
  `invoicedetailsid` int(11) DEFAULT NULL,
  `userid` int(11) DEFAULT NULL,
  `suppliername` varchar(100) DEFAULT NULL,
  `quantity` int(11) DEFAULT NULL,
  `receivedquantity` int(11) DEFAULT NULL,
  `invoicedate` date DEFAULT NULL,
  `uom` varchar(45) DEFAULT NULL,
  `branchid` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `purchaseorder`
--

LOCK TABLES `purchaseorder` WRITE;
/*!40000 ALTER TABLE `purchaseorder` DISABLE KEYS */;
/*!40000 ALTER TABLE `purchaseorder` ENABLE KEYS */;
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
  `pudetailsid` int(11) DEFAULT NULL,
  `languagesstudied` varchar(80) DEFAULT NULL,
  `instructionmediumlastschool` varchar(45) DEFAULT NULL,
  `degreedetailsid` int(11) DEFAULT NULL,
  `passedout` int(11) DEFAULT NULL,
  `droppedout` int(11) DEFAULT NULL,
  `leftout` int(11) DEFAULT NULL,
  `semester` int(11) DEFAULT NULL,
  `stream` varchar(25) DEFAULT NULL,
  `mediumofinstruction` varchar(15) DEFAULT NULL,
  `previousschooltype` varchar(30) DEFAULT NULL,
  `previouschooladdress` varchar(250) DEFAULT NULL,
  `urbanrural` varchar(5) DEFAULT NULL,
  `studentscastecertno` varchar(25) DEFAULT NULL,
  `studentscaste` varchar(15) DEFAULT NULL,
  `socialcategory` varchar(10) DEFAULT NULL,
  `belongtobpl` int(11) DEFAULT NULL,
  `bplcardno` varchar(25) DEFAULT NULL,
  `bhagyalakshmibondnumber` varchar(25) DEFAULT NULL,
  `disabilitychild` varchar(40) DEFAULT NULL,
  `specialcategory` varchar(25) DEFAULT NULL,
  `sts` varchar(30) DEFAULT NULL,
  `rte` int(11) DEFAULT NULL,
  `bankname` varchar(500) DEFAULT NULL,
  `bankbranch` varchar(200) DEFAULT NULL,
  `accno` varchar(50) DEFAULT NULL,
  `bankifsc` varchar(50) DEFAULT NULL,
  `userid` int(11) DEFAULT NULL,
  `studentdoc1` longtext,
  `studentdoc2` longtext,
  `studentdoc3` longtext,
  `studentdoc4` longtext,
  `studentdoc5` longtext,
  `yearofadmission` varchar(10) DEFAULT NULL,
  `promotedyear` varchar(10) DEFAULT NULL,
  PRIMARY KEY (`sid`),
  UNIQUE KEY `studentexternalid_UNIQUE` (`studentexternalid`),
  KEY `pudetailsid_idx` (`pudetailsid`),
  KEY `degreedetailsid_idx` (`degreedetailsid`),
  CONSTRAINT `degreedetailsid` FOREIGN KEY (`degreedetailsid`) REFERENCES `degreedetails` (`iddegreedetails`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `pudetailsid` FOREIGN KEY (`pudetailsid`) REFERENCES `pudetails` (`idpudetails`) ON DELETE CASCADE ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=401 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `student`
--

LOCK TABLES `student` WRITE;
/*!40000 ALTER TABLE `student` DISABLE KEYS */;
INSERT INTO `student` VALUES (1,'T.A.ABDUL KAREEM','U.K.G--A','',4,'Male','2020-07-12','','Indian','Islam',NULL,'2024-06-10','1251','','','','','2025-05-05',0,'','FNPS0001',NULL,NULL,'',NULL,NULL,'',NULL,'',0,NULL,'','',2,NULL,'',NULL,NULL,0,0,0,0,'Admission','','',NULL,NULL,'','','General',0,'','','','','',0,'',NULL,'','',0,'','','','','','','2025/26'),(2,'U.MOHAMMED NUFAIS','U.K.G--A','',4,'Male','2020-09-16','','Indian','Islam',NULL,'2024-06-10','1295','','','','','2025-05-05',0,'','FNPS0002',NULL,NULL,'',NULL,NULL,'',NULL,'',0,NULL,'','',2,NULL,'',NULL,NULL,0,0,0,0,'Admission','','',NULL,NULL,'','','General',0,'','','','','',0,'',NULL,'','',0,'','','','','','','2025/26'),(3,'S.A. JAFFER SADIK','U.K.G--A','',4,'Male','2020-05-27','','Indian','Islam',NULL,'2024-06-10','1235','','','','','2025-05-05',0,'','FNPS0003',NULL,NULL,'',NULL,NULL,'',NULL,'',0,NULL,'','',2,NULL,'',NULL,NULL,0,0,0,0,'Admission','','',NULL,NULL,'','','General',0,'','','','','',0,'',NULL,'','',0,'','','','','','','2025/26'),(4,'M.M.MOHAMED HANIF','U.K.G--A','',-1,'Male','2025-05-16','','Indian','',NULL,'2024-06-10','1276','','','','','2025-05-05',0,'','FNPS0004',NULL,NULL,'',NULL,NULL,'',NULL,'',0,NULL,'','',2,NULL,'',NULL,NULL,0,0,0,0,'Admission','','',NULL,NULL,'','','General',0,'','','','','',0,'',NULL,'','',0,'','','','','','','2025/26'),(5,'A.MOHAMED AADIL','U.K.G--A','L.K.G--',4,'Male','2021-02-10','','Indian','Islam',NULL,'2024-06-11','1299','','','','','2025-05-05',0,'','FNPS0005',NULL,NULL,'',NULL,NULL,'',NULL,'',0,NULL,'','',2,NULL,'',NULL,NULL,0,0,0,0,'Admission','','',NULL,NULL,'','','General',0,'','','','','',0,'',NULL,'','',0,'','','','','','2024/25','2025/26'),(6,'A.M.MOHAMED HAASHIM','U.K.G--A','L.K.G--',4,'Male','2020-11-03','','Indian','Islam',NULL,'2024-06-10','1286','','','','','2025-05-05',0,'','FNPS0006',NULL,NULL,'',NULL,NULL,'',NULL,'',0,NULL,'','',2,NULL,'',NULL,NULL,0,0,0,0,'Admission','','',NULL,NULL,'','','General',0,'','','','','',0,'',NULL,'','',0,'','','','','','2024/25','2025/26'),(7,'S.MOHAMMED SHAHIDH','U.K.G--A','L.K.G--',4,'Male','2020-08-20','','Indian','Islam',NULL,'2024-06-10','1254','','','','','2025-05-05',0,'','FNPS0007',NULL,NULL,'',NULL,NULL,'',NULL,'',0,NULL,'','',2,NULL,'',NULL,NULL,0,0,0,0,'Admission','','',NULL,NULL,'','','General',0,'','','','','',0,'',NULL,'','',0,'','','','','','2024/25','2025/26'),(8,'A.AASHIR','U.K.G--A','L.K.G--',5,'Male','2020-03-05','','Indian','Islam',NULL,'2024-06-10','1261','','','','','2025-05-05',0,'','FNPS0008',NULL,NULL,'',NULL,NULL,'',NULL,'',0,NULL,'','',2,NULL,'',NULL,NULL,0,0,0,0,'Admission','','',NULL,NULL,'','','General',0,'','','','','',0,'',NULL,'','',0,'','','','','','2024/25','2025/26'),(9,'T.M.MUHAMED ILHAN','U.K.G--A','L.K.G--',4,'Male','2020-07-08','','Indian','Islam',NULL,'2024-06-10','1207','','','','','2025-05-05',0,'','FNPS0009',NULL,NULL,'',NULL,NULL,'',NULL,'',0,NULL,'','',2,NULL,'',NULL,NULL,0,0,0,0,'Admission','','',NULL,NULL,'','','General',0,'','','','','',0,'',NULL,'','',0,'','','','','','2024/25','2025/26'),(10,'M.M. DULQUER NAIN','U.K.G--A','L.K.G--',4,'Male','2020-12-20','','Indian','Islam',NULL,'2024-06-21','1311','','','','','2025-05-05',0,'','FNPS0010',NULL,NULL,'',NULL,NULL,'',NULL,'',0,NULL,'','',2,NULL,'',NULL,NULL,0,0,0,0,'Admission','','',NULL,NULL,'','','General',0,'','','','','',0,'',NULL,'','',0,'','','','','','2024/25','2025/26'),(11,'QASIM AHSAN','U.K.G--A','L.K.G--',4,'Male','2020-07-17','','Indian','Islam',NULL,'2024-07-02','1321','','','','','2025-05-05',0,'','FNPS0011',NULL,NULL,'',NULL,NULL,'',NULL,'',0,NULL,'','',2,NULL,'',NULL,NULL,0,0,0,0,'Admission','','',NULL,NULL,'','','General',0,'','','','','',0,'',NULL,'','',0,'','','','','','2024/25','2025/26'),(12,'M.R.M.RIZWA HUZAIFA','U.K.G--A','L.K.G--',4,'Female','2020-10-03','','Indian','Islam',NULL,'2024-06-10','1204','','','','','2025-05-05',0,'','FNPS0012',NULL,NULL,'',NULL,NULL,'',NULL,'',0,NULL,'','',2,NULL,'',NULL,NULL,0,0,0,0,'Admission','','',NULL,NULL,'','','General',0,'','','','','',0,'',NULL,'','',0,'','','','','','2024/25','2025/26'),(13,'M.A.LAFIRA YUMNA','U.K.G--A','L.K.G--',4,'Female','2020-10-31','','Indian','Islam',NULL,'2024-06-10','1216','','','','','2025-05-05',0,'','FNPS0013',NULL,NULL,'',NULL,NULL,'',NULL,'',0,NULL,'','',2,NULL,'',NULL,NULL,0,0,0,0,'Admission','','',NULL,NULL,'','','General',0,'','','','','',0,'',NULL,'','',0,'','','','','','2024/25','2025/26'),(14,'S.A.KATHEEJA AARIFA','U.K.G--A','L.K.G--',5,'Female','2019-11-27','','Indian','Islam',NULL,'2024-06-10','1217','','','','','2025-05-05',0,'','FNPS0014',NULL,NULL,'',NULL,NULL,'',NULL,'',0,NULL,'','',2,NULL,'',NULL,NULL,0,0,0,0,'Admission','','',NULL,NULL,'','','General',0,'','','','','',0,'',NULL,'','',0,'','','','','','2024/25','2025/26'),(15,'S.A.MUMTAJ AAQIFA','U.K.G--A','L.K.G--',5,'Female','2019-11-27','','Indian','Islam',NULL,'2024-06-10','1218','','','','','2025-05-05',0,'','FNPS0015',NULL,NULL,'',NULL,NULL,'',NULL,'',0,NULL,'','',2,NULL,'',NULL,NULL,0,0,0,0,'Admission','','',NULL,NULL,'','','General',0,'','','','','',0,'',NULL,'','',0,'','','','','','2024/25','2025/26'),(16,'S.I.AYISHA AATIFA','U.K.G--A','L.K.G--',4,'Female','2020-10-10','','Indian','Islam',NULL,'2024-06-10','1220','','','','','2025-05-05',0,'','FNPS0016',NULL,NULL,'',NULL,NULL,'',NULL,'',0,NULL,'','',2,NULL,'',NULL,NULL,0,0,0,0,'Admission','','',NULL,NULL,'','','General',0,'','','','','',0,'',NULL,'','',0,'','','','','','2024/25','2025/26'),(17,'S.A.B.SEYED FATHIMA','U.K.G--A','L.K.G--',6,'Female','2018-05-14','','Indian','Islam',NULL,'2024-06-10','1222','','','','','2025-05-05',0,'','FNPS0017',NULL,NULL,'',NULL,NULL,'',NULL,'',0,NULL,'','',2,NULL,'',NULL,NULL,0,0,0,0,'Admission','','',NULL,NULL,'','','General',0,'','','','','',0,'',NULL,'','',0,'','','','','','2024/25','2025/26'),(18,'M.A.KATHEEJATH AAFIYA','U.K.G--A','L.K.G--',4,'Female','2020-12-28','','Indian','Islam',NULL,'2024-06-10','1239','','','','','2025-05-05',0,'','FNPS0018',NULL,NULL,'',NULL,NULL,'',NULL,'',0,NULL,'','',2,NULL,'',NULL,NULL,0,0,0,0,'Admission','','',NULL,NULL,'','','General',0,'','','','','',0,'',NULL,'','',0,'','','','','','2024/25','2025/26'),(19,'K.S.FATHIMA HANA','U.K.G--A','L.K.G--',4,'Female','2020-09-16','','Indian','Islam',NULL,'2024-06-10','1253','','','','','2025-05-05',0,'','FNPS0019',NULL,NULL,'',NULL,NULL,'',NULL,'',0,NULL,'','',2,NULL,'',NULL,NULL,0,0,0,0,'Admission','','',NULL,NULL,'','','General',0,'','','','','',0,'',NULL,'','',0,'','','','','','2024/25','2025/26'),(20,'A.FATHIMA SHAHINA','U.K.G--A','L.K.G--',4,'Female','2020-12-12','','Indian','Islam',NULL,'2024-06-10','1266','','','','','2025-05-05',0,'','FNPS0020',NULL,NULL,'',NULL,NULL,'',NULL,'',0,NULL,'','',2,NULL,'',NULL,NULL,0,0,0,0,'Admission','','',NULL,NULL,'','','General',0,'','','','','',0,'',NULL,'','',0,'','','','','','2024/25','2025/26'),(21,'S.N.ZEENATH HABEEBA','U.K.G--A','L.K.G--',4,'Female','2020-06-06','','Indian','Islam',NULL,'2024-06-10','1247','','','','','2025-05-05',0,'','FNPS0021',NULL,NULL,'',NULL,NULL,'',NULL,'',0,NULL,'','',2,NULL,'',NULL,NULL,0,0,0,0,'Admission','','',NULL,NULL,'','','General',0,'','','','','',0,'',NULL,'','',0,'','','','','','2024/25','2025/26'),(22,'M.M.MUHAMMED IFRAZ','U.K.G--B','L.K.G--',5,'Male','2020-04-06','','Indian','Islam',NULL,'2024-06-10','1203','','','','','2025-05-05',0,'','FNPS0022',NULL,NULL,'',NULL,NULL,'',NULL,'',0,NULL,'','',2,NULL,'',NULL,NULL,0,0,0,0,'Admission','','',NULL,NULL,'','','General',0,'','','','','',0,'',NULL,'','',0,'','','','','','2024/25','2025/26'),(23,'J.S.MUHAMMED MUADH','U.K.G--B','L.K.G--',4,'Male','2020-06-09','','Indian','Islam',NULL,'2024-06-10','1208','','','','','2025-05-05',0,'','FNPS0023',NULL,NULL,'',NULL,NULL,'',NULL,'',0,NULL,'','',2,NULL,'',NULL,NULL,0,0,0,0,'Admission','','',NULL,NULL,'','','General',0,'','','','','',0,'',NULL,'','',0,'','','','','','2024/25','2025/26'),(24,'M.H.SATHAK HASHIR','U.K.G--B','L.K.G--',5,'Male','2020-04-13','','Indian','Islam',NULL,'2024-06-10','1225','','','','','2025-05-05',0,'','FNPS0024',NULL,NULL,'',NULL,NULL,'',NULL,'',0,NULL,'','',2,NULL,'',NULL,NULL,0,0,0,0,'Admission','','',NULL,NULL,'','','General',0,'','','','','',0,'',NULL,'','',0,'','','','','','2024/25','2025/26'),(25,'A.S.ARSHAAN','U.K.G--B','L.K.G--',5,'Male','2019-11-29','','Indian','Islam',NULL,'2024-06-10','1227','','','','','2025-05-05',0,'','FNPS0025',NULL,NULL,'',NULL,NULL,'',NULL,'',0,NULL,'','',2,NULL,'',NULL,NULL,0,0,0,0,'Admission','','',NULL,NULL,'','','General',0,'','','','','',0,'',NULL,'','',0,'','','','','','2024/25','2025/26'),(26,'J.MOHAMMED MAAIZ','U.K.G--B','L.K.G--',4,'Male','2020-11-24','','Indian','Islam',NULL,'2024-06-10','1258','','','','','2025-05-05',0,'','FNPS0026',NULL,NULL,'',NULL,NULL,'',NULL,'',0,NULL,'','',2,NULL,'',NULL,NULL,0,0,0,0,'Admission','','',NULL,NULL,'','','General',0,'','','','','',0,'',NULL,'','',0,'','','','','','2024/25','2025/26'),(27,'M.A.C SEYED AHAMED NEHAN','U.K.G--B','L.K.G--',4,'Male','2020-12-25','','Indian','Islam',NULL,'2024-06-10','1249','','','','','2025-05-05',0,'','FNPS0027',NULL,NULL,'',NULL,NULL,'',NULL,'',0,NULL,'','',2,NULL,'',NULL,NULL,0,0,0,0,'Admission','','',NULL,NULL,'','','General',0,'','','','','',0,'',NULL,'','',0,'','','','','','2024/25','2025/26'),(28,'M.J.MOHAMED ISHAQ','U.K.G--B','L.K.G--',4,'Male','2020-07-20','','Indian','Islam',NULL,'2024-06-10','1296','','','','','2025-05-05',0,'','FNPS0028',NULL,NULL,'',NULL,NULL,'',NULL,'',0,NULL,'','',2,NULL,'',NULL,NULL,0,0,0,0,'Admission','','',NULL,NULL,'','','General',0,'','','','','',0,'',NULL,'','',0,'','','','','','2024/25','2025/26'),(29,'J.JESVIK','U.K.G--B','L.K.G--',5,'Male','2019-10-24','','Indian','Christianity',NULL,'2024-06-19','1308','','','','','2025-05-05',0,'','FNPS0029',NULL,NULL,'',NULL,NULL,'',NULL,'',0,NULL,'','',2,NULL,'',NULL,NULL,0,0,0,0,'Admission','','',NULL,NULL,'','','General',0,'','','','','',0,'',NULL,'','',0,'','','','','','2024/25','2025/26'),(30,'K.M.MOHABOOB ANSARI','U.K.G--B','L.K.G--',5,'Male','2020-02-29','','Indian','Islam',NULL,'2024-06-10','1289','','','','','2025-05-05',0,'','FNPS0030',NULL,NULL,'',NULL,NULL,'',NULL,'',0,NULL,'','',2,NULL,'',NULL,NULL,0,0,0,0,'Admission','','',NULL,NULL,'','','General',0,'','','','','',0,'',NULL,'','',0,'','','','','','2024/25','2025/26'),(31,'MOHAMED AATHIF','U.K.G--B','L.K.G--',4,'Male','2020-06-14','','Indian','Islam',NULL,'2024-06-10','1242','','','','','2025-05-05',0,'','FNPS0031',NULL,NULL,'',NULL,NULL,'',NULL,'',0,NULL,'','',2,NULL,'',NULL,NULL,0,0,0,0,'Admission','','',NULL,NULL,'','','General',0,'','','','','',0,'',NULL,'','',0,'','','','','','2024/25','2025/26'),(32,'Z.I.JAMALUDEEN','U.K.G--B','L.K.G--',4,'Male','2020-08-28','','Indian','Islam',NULL,'2024-06-10','1221','','','','','2025-05-05',0,'','FNPS0032',NULL,NULL,'',NULL,NULL,'',NULL,'',0,NULL,'','',2,NULL,'',NULL,NULL,0,0,0,0,'Admission','','',NULL,NULL,'','','General',0,'','','','','',0,'',NULL,'','',0,'','','','','','2024/25','2025/26'),(33,'B.HAMEEDHA HAFNA','U.K.G--B','L.K.G--',104,'Female','0020-09-30','','Indian','Islam',NULL,'2024-06-10','1260','','','','','2025-05-05',0,'','FNPS0033',NULL,NULL,'',NULL,NULL,'',NULL,'',0,NULL,'','',2,NULL,'',NULL,NULL,0,0,0,0,'Admission','','',NULL,NULL,'','','General',0,'','','','','',0,'',NULL,'','',0,'','','','','','2024/25','2025/26'),(34,'A.AAKIFA FATHIMA','I--B','I--',5,'Female','2020-01-08','','Indian','Islam',NULL,'2024-06-10','1263','','','','','2025-05-05',0,'','FNPS0034',NULL,NULL,'',NULL,NULL,'',NULL,'',0,NULL,'','',2,NULL,'',NULL,NULL,0,0,0,0,'Admission','','',NULL,NULL,'','','General',0,'','','','','',0,'',NULL,'','',0,'','','','','','2024/25','2025/26'),(35,'M.J.AAFIYA','U.K.G--B','L.K.G--',4,'Female','2020-08-18','','Indian','Islam',NULL,'2024-06-10','1264','','','','','2025-05-05',0,'','FNPS0035',NULL,NULL,'',NULL,NULL,'',NULL,'',0,NULL,'','',2,NULL,'',NULL,NULL,0,0,0,0,'Admission','','',NULL,NULL,'','','General',0,'','','','','',0,'',NULL,'','',0,'','','','','','2024/25','2025/26'),(36,'A.H.JAMEELA','I--B','L.K.G--',5,'Female','2020-01-07','','Indian','Islam',NULL,'2024-06-10','1287','','','','','2025-05-05',0,'','FNPS0036',NULL,NULL,'',NULL,NULL,'',NULL,'',0,NULL,'','',2,NULL,'',NULL,NULL,0,0,0,0,'Admission','','',NULL,NULL,'','','General',0,'','','','','',0,'',NULL,'','',0,'','','','','','2024/25','2025/26'),(37,'J.MAKNI','U.K.G--B','L.K.G--',5,'Female','2020-03-19','','Indian','Christianity',NULL,'2024-06-10','1259','','','','','2025-05-05',0,'','FNPS0037',NULL,NULL,'',NULL,NULL,'',NULL,'',0,NULL,'','',2,NULL,'',NULL,NULL,0,0,0,0,'Admission','','',NULL,NULL,'','','General',0,'','','','','',0,'',NULL,'','',0,'','','','','','2024/25','2025/26'),(38,'S.ADHIRA','U.K.G--B','L.K.G--',4,'Female','2020-09-11','','Indian','Hinduism',NULL,'2024-06-10','1275','','','','','2025-05-05',0,'','FNPS0038',NULL,NULL,'',NULL,NULL,'',NULL,'',0,NULL,'S.SUYAMBU LINGAM','',2,NULL,'',NULL,NULL,0,0,0,0,'Admission','','',NULL,NULL,'','','General',0,'','','','','',0,'',NULL,'','',0,'','','','','','2024/25','2025/26'),(39,'H.R.ZAINAB INAAYA','U.K.G--B','L.K.G--',4,'Female','2020-07-25','','Indian','Islam',NULL,'2024-06-10','1294','','','','','2025-05-05',0,'','FNPS0039',NULL,NULL,'',NULL,NULL,'',NULL,'',0,NULL,'','',2,NULL,'',NULL,NULL,0,0,0,0,'Admission','','',NULL,NULL,'','','General',0,'','','','','',0,'',NULL,'','',0,'','','','','','2024/25','2025/26'),(40,'H.J.ZEENATH MUKKARAMA','U.K.G--B','L.K.G--',4,'Female','2020-12-06','','Indian','Islam',NULL,NULL,'1290','','','','','2025-05-05',0,'','FNPS0040',NULL,NULL,'',NULL,NULL,'',NULL,'',0,NULL,'','',2,NULL,'',NULL,NULL,0,0,0,0,'Admission','','',NULL,NULL,'','','General',0,'','','','','',0,'',NULL,'','',0,'','','','','','2024/25','2025/26'),(41,'M.YASMIN NISA','U.K.G--B','L.K.G--',4,'Female','2020-10-15','','Indian','Islam',NULL,'2024-06-10','1229','','','','','2025-05-05',0,'','FNPS0041',NULL,NULL,'',NULL,NULL,'',NULL,'',0,NULL,'','',2,NULL,'',NULL,NULL,0,0,0,0,'Admission','','',NULL,NULL,'','','General',0,'','','','','',0,'',NULL,'','',0,'','','','','','2024/25','2025/26'),(42,'N.FATHIMA RIZA','U.K.G--B','L.K.G--',4,'Female','2021-01-05','','Indian','Islam',NULL,'2024-06-10','1205','','','','','2025-05-05',0,'','FNPS0042',NULL,NULL,'',NULL,NULL,'',NULL,'',0,NULL,'','',2,NULL,'',NULL,NULL,0,0,0,0,'Admission','','',NULL,NULL,'','','General',0,'','','','','',0,'',NULL,'','',0,'','','','','','2024/25','2025/26'),(43,'M.ABDUL ARAFATH','U.K.G--C','L.K.G--',4,'Male','2020-07-31','','Indian','Islam',NULL,'2024-06-10','1243','','','','','2025-05-05',0,'','FNPS0043',NULL,NULL,'',NULL,NULL,'',NULL,'',0,NULL,'','',2,NULL,'',NULL,NULL,0,0,0,0,'Admission','','',NULL,NULL,'','','General',0,'','','','','',0,'',NULL,'','',0,'','','','','','2024/25','2025/26'),(44,'F.M.T.FAROOK FAHEEM','U.K.G--C','L.K.G--',5,'Male','2020-01-20','','Indian','Islam',NULL,'2024-06-10','1270','','','','','2025-05-05',0,'','FNPS0044',NULL,NULL,'',NULL,NULL,'',NULL,'',0,NULL,'','',2,NULL,'',NULL,NULL,0,0,0,0,'Admission','','',NULL,NULL,'','','General',0,'','','','','',0,'',NULL,'','',0,'','','','','','2024/25','2025/26'),(45,'R.FADHIL HAMEED','U.K.G--C','L.K.G--',4,'Male','2021-02-20','','Indian','Islam',NULL,'2024-06-10','1237','','','','','2025-05-05',0,'','FNPS0045',NULL,NULL,'',NULL,NULL,'',NULL,'',0,NULL,'','',2,NULL,'',NULL,NULL,0,0,0,0,'Admission','','',NULL,NULL,'','','General',0,'','','','','',0,'',NULL,'','',0,'','','','','','2024/25','2025/26'),(46,'M.A.JAINUL KUTHBUDEEN','U.K.G--C','L.K.G--',5,'Male','2020-03-21','','Indian','Islam',NULL,'2024-06-10','1250','','','','','2025-05-05',0,'','FNPS0046',NULL,NULL,'',NULL,NULL,'',NULL,'',0,NULL,'','',2,NULL,'',NULL,NULL,0,0,0,0,'Admission','','',NULL,NULL,'','','General',0,'','','','','',0,'',NULL,'','',0,'','','','','','2024/25','2025/26'),(47,'S.LINGA BAIRAV','U.K.G--C','L.K.G--',4,'Male','2020-10-07','','Indian','Christianity',NULL,'2024-06-10','1206','','','','','2025-05-05',0,'','FNPS0047',NULL,NULL,'',NULL,NULL,'',NULL,'',0,NULL,'','',2,NULL,'',NULL,NULL,0,0,0,0,'Admission','','',NULL,NULL,'','','General',0,'','','','','',0,'',NULL,'','',0,'','','','','','2024/25','2025/26'),(48,'S.MOOSA NAINA','U.K.G--C','L.K.G--',5,'Male','2019-12-19','','Indian','Islam',NULL,'2024-06-10','1233','','','','','2025-05-05',0,'','FNPS0048',NULL,NULL,'',NULL,NULL,'',NULL,'',0,NULL,'','',2,NULL,'',NULL,NULL,0,0,0,0,'Admission','','',NULL,NULL,'','','General',0,'','','','','',0,'',NULL,'','',0,'','','','','','2024/25','2025/26'),(49,'S.MOHAMED RASHITH','U.K.G--C','L.K.G--',4,'Male','2020-07-22','','Indian','Islam',NULL,'2024-07-01','1319','','','','','2025-05-05',0,'','FNPS0049',NULL,NULL,'',NULL,NULL,'',NULL,'',0,NULL,'','',2,NULL,'',NULL,NULL,0,0,0,0,'Admission','','',NULL,NULL,'','','General',0,'','','','','',0,'',NULL,'','',0,'','','','','','2024/25','2025/26'),(50,'M.S.ZAINUDEEN ','U.K.G--C','L.K.G--',4,'Male','2020-07-28','','Indian','Islam',NULL,'2024-06-10','1224','','','','','2025-05-05',0,'','FNPS0050',NULL,NULL,'',NULL,NULL,'',NULL,'',0,NULL,'','',2,NULL,'',NULL,NULL,0,0,0,0,'Admission','','',NULL,NULL,'','','General',0,'','','','','',0,'',NULL,'','',0,'','','','','','2024/25','2025/26'),(51,'U.SEYED ZAYAN','U.K.G--C','L.K.G--',4,'Male','2021-01-09','','Indian','Islam',NULL,'2024-06-25','1313','','','','','2025-05-05',0,'','FNPS0051',NULL,NULL,'',NULL,NULL,'',NULL,'',0,NULL,'','',2,NULL,'',NULL,NULL,0,0,0,0,'Admission','','',NULL,NULL,'','','General',0,'','','','','',0,'',NULL,'','',0,'','','','','','2024/25','2025/26'),(52,'A.S.MUHAMMED BASHEER','U.K.G--C','L.K.G--',5,'Male','2020-01-24','','Indian','Islam',NULL,'2024-07-04','1322','','','','','2025-05-05',0,'','FNPS0052',NULL,NULL,'',NULL,NULL,'',NULL,'',0,NULL,'','',2,NULL,'',NULL,NULL,0,0,0,0,'Admission','','',NULL,NULL,'','','General',0,'','','','','',0,'',NULL,'','',0,'','','','','','2024/25','2025/26'),(53,'A.ANAS SIDHIK','U.K.G--C','L.K.G--',4,'Male','2020-05-14','','Indian','',NULL,'2024-10-12','1328','','','','','2025-05-05',0,'','FNPS0053',NULL,NULL,'',NULL,NULL,'',NULL,'',0,NULL,'','',2,NULL,'',NULL,NULL,0,0,0,0,'Admission','','',NULL,NULL,'','','General',0,'','','','','',0,'',NULL,'','',0,'','','','','','2024/25','2025/26'),(54,'AYRA MARIAM','U.K.G--C','L.K.G--',4,'Female','2020-07-02','','Indian','Islam',NULL,'2024-06-10','1211','','','','','2025-05-05',0,'','FNPS0054',NULL,NULL,'',NULL,NULL,'',NULL,'',0,NULL,'','',2,NULL,'',NULL,NULL,0,0,0,0,'Admission','','',NULL,NULL,'','','General',0,'','','','','',0,'',NULL,'','',0,'','','','','','2024/25','2025/26'),(55,'A.AKSHAYA','U.K.G--C','L.K.G--',4,'Female','2020-10-12','','Indian','Hinduism',NULL,'2024-06-10','1236','','','','','2025-05-05',0,'','FNPS0055',NULL,NULL,'',NULL,NULL,'',NULL,'',0,NULL,'','',2,NULL,'',NULL,NULL,0,0,0,0,'Admission','','',NULL,NULL,'','','General',0,'','','','','',0,'',NULL,'','',0,'','','','','','2024/25','2025/26'),(56,'S.AASHVI','U.K.G--C','L.K.G--',0,'Female',NULL,'','Indian','Hinduism',NULL,'2024-06-10','1241','','','','','2025-05-05',0,'','FNPS0056',NULL,NULL,'',NULL,NULL,'',NULL,'',0,NULL,'','',2,NULL,'',NULL,NULL,0,0,0,0,'Admission','','',NULL,NULL,'','','General',0,'','','','','',0,'',NULL,'','',0,'','','','','','2024/25','2025/26'),(57,'U.F.FATHIMA HAMRA','U.K.G--C','L.K.G--',5,'Female','2020-05-01','','Indian','Islam',NULL,'2024-06-10','1257','','','','','2025-05-05',0,'','FNPS0057',NULL,NULL,'',NULL,NULL,'',NULL,'',0,NULL,'','',2,NULL,'',NULL,NULL,0,0,0,0,'Admission','','',NULL,NULL,'','','General',0,'','','','','',0,'',NULL,'','',0,'','','','','','2024/25','2025/26'),(58,'S.S.HAMEEDHA ZAYNAH','U.K.G--C','L.K.G--',5,'Female','2020-04-16','','Indian','Islam',NULL,'2024-06-10','1245','','','','','2025-05-05',0,'','FNPS0058',NULL,NULL,'',NULL,NULL,'',NULL,'',0,NULL,'','',2,NULL,'',NULL,NULL,0,0,0,0,'Admission','','',NULL,NULL,'','','General',0,'','','','','',0,'',NULL,'','',0,'','','','','','2024/25','2025/26'),(59,'M.RABIYA RIFA','U.K.G--C','L.K.G--',5,'Female','2019-11-04','','Indian','Islam',NULL,'2024-06-10','1293','','','','','2025-05-05',0,'','FNPS0059',NULL,NULL,'',NULL,NULL,'',NULL,'',0,NULL,'','',2,NULL,'',NULL,NULL,0,0,0,0,'Admission','','',NULL,NULL,'','','General',0,'','','','','',0,'',NULL,'','',0,'','','','','','2024/25','2025/26'),(60,'M.A.K.J.THAHA SAJNA','U.K.G--C','L.K.G--',4,'Female','2020-09-23','','Indian','Islam',NULL,'2024-06-10','1230','','','','','2025-05-05',0,'','FNPS0060',NULL,NULL,'',NULL,NULL,'',NULL,'',0,NULL,'','',2,NULL,'',NULL,NULL,0,0,0,0,'Admission','','',NULL,NULL,'','','General',0,'','','','','',0,'',NULL,'','',0,'','','','','','2024/25','2025/26'),(61,'M.T.NAJMIYA','U.K.G--C','L.K.G--',4,'Female','2020-08-02','','Indian','Islam',NULL,'2024-06-10','1268','','','','','2025-05-05',0,'','FNPS0061',NULL,NULL,'',NULL,NULL,'',NULL,'',0,NULL,'','',2,NULL,'',NULL,NULL,0,0,0,0,'Admission','','',NULL,NULL,'','','General',0,'','','','','',0,'',NULL,'','',0,'','','','','','2024/25','2025/26'),(62,'N.ZEENATH MUNAWARA','U.K.G--C','L.K.G--',5,'Female','2020-01-13','','Indian','Islam',NULL,'2024-06-10','1240','','','','','2025-05-05',0,'','FNPS0062',NULL,NULL,'',NULL,NULL,'',NULL,'',0,NULL,'','',2,NULL,'',NULL,NULL,0,0,0,0,'Admission','','',NULL,NULL,'','','General',0,'','','','','',0,'',NULL,'','',0,'','','','','','2024/25','2025/26'),(63,'Y.A.ZAHRA RUKKAIYA','U.K.G--C','L.K.G--',4,'Female','2020-07-29','','Indian','Islam',NULL,'2024-06-10','1231','','','','','2025-05-05',0,'','FNPS0063',NULL,NULL,'',NULL,NULL,'',NULL,'',0,NULL,'','',2,NULL,'',NULL,NULL,0,0,0,0,'Admission','','',NULL,NULL,'','','General',0,'','','','','',0,'',NULL,'','',0,'','','','','','2024/25','2025/26'),(64,'A.K.ABDUL QADIR','U.K.G--D','L.K.G--',4,'Male','2020-10-29','','Indian','Islam',NULL,'2024-06-10','1246','','','','','2025-05-05',0,'','FNPS0064',NULL,NULL,'',NULL,NULL,'',NULL,'',0,NULL,'','',2,NULL,'',NULL,NULL,0,0,0,0,'Admission','','',NULL,NULL,'','','General',0,'','','','','',0,'',NULL,'','',0,'','','','','','2024/25','2025/26'),(65,'ABUBAKAR AZIM','U.K.G--D','L.K.G--',5,'Male','2020-04-24','','Indian','Islam',NULL,'2024-06-10','1212','','','','','2025-05-05',0,'','FNPS0065',NULL,NULL,'',NULL,NULL,'',NULL,'',0,NULL,'','',2,NULL,'',NULL,NULL,0,0,0,0,'Admission','','',NULL,NULL,'','','General',0,'','','','','',0,'',NULL,'','',0,'','','','','','2024/25','2025/26'),(66,'F.M.T.HASAN FAHMI','U.K.G--D','L.K.G--',5,'Male','2020-01-20','','Indian','Islam',NULL,'2024-06-10','1269','','','','','2025-05-05',0,'','FNPS0066',NULL,NULL,'',NULL,NULL,'',NULL,'',0,NULL,'','',2,NULL,'',NULL,NULL,0,0,0,0,'Admission','','',NULL,NULL,'','','General',0,'','','','','',0,'',NULL,'','',0,'','','','','','2024/25','2025/26'),(67,'J.JOE DERICK','U.K.G--D','L.K.G--',4,'Male','2020-11-11','','Indian','Christianity',NULL,'2024-07-01','1320','','','','','2025-05-05',0,'','FNPS0067',NULL,NULL,'',NULL,NULL,'',NULL,'',0,NULL,'','',2,NULL,'',NULL,NULL,0,0,0,0,'Admission','','',NULL,NULL,'','','General',0,'','','','','',0,'',NULL,'','',0,'','','','','','2024/25','2025/26'),(68,'MOHAMMED BASIM','U.K.G--D','L.K.G--',4,'Male','2020-10-08','','Indian','Islam',NULL,'2024-06-10','1238','','','','','2025-05-05',0,'','FNPS0068',NULL,NULL,'',NULL,NULL,'',NULL,'',0,NULL,'','',2,NULL,'',NULL,NULL,0,0,0,0,'Admission','','',NULL,NULL,'','','General',0,'','','','','',0,'',NULL,'','',0,'','','','','','2024/25','2025/26'),(69,'S.MOHAMMED SAAD','U.K.G--D','L.K.G--',4,'Male','2020-08-22','','Indian','Islam',NULL,'2024-06-10','1262','','','','','2025-05-05',0,'','FNPS0069',NULL,NULL,'',NULL,NULL,'',NULL,'',0,NULL,'','',2,NULL,'',NULL,NULL,0,0,0,0,'Admission','','',NULL,NULL,'','','General',0,'','','','','',0,'',NULL,'','',0,'','','','','','2024/25','2025/26'),(70,'N.MOHAMMED SAFHAN','U.K.G--D','L.K.G--',4,'Male','2020-12-11','','Indian','Islam',NULL,'2024-06-10','1248','','','','','2025-05-05',0,'','FNPS0070',NULL,NULL,'',NULL,NULL,'',NULL,'',0,NULL,'','',2,NULL,'',NULL,NULL,0,0,0,0,'Admission','','',NULL,NULL,'','','General',0,'','','','','',0,'',NULL,'','',0,'','','','','','2024/25','2025/26'),(71,'U.K.MUTHU IBRAHIM','U.K.G--D','L.K.G--',4,'Male','2020-09-12','','Indian','Islam',NULL,'2024-06-25','1314','','','','','2025-05-05',0,'','FNPS0071',NULL,NULL,'',NULL,NULL,'',NULL,'',0,NULL,'','',2,NULL,'',NULL,NULL,0,0,0,0,'Admission','','',NULL,NULL,'','','General',0,'','','','','',0,'',NULL,'','',0,'','','','','','2024/25','2025/26'),(72,'M.M.M.SHAM SHIHABUDEEN','U.K.G--D','L.K.G--',4,'Male','2020-11-29','','Indian','Islam',NULL,'2024-06-10','1281','','','','','2025-05-05',0,'','FNPS0072',NULL,NULL,'',NULL,NULL,'',NULL,'',0,NULL,'','',2,NULL,'',NULL,NULL,0,0,0,0,'Admission','','',NULL,NULL,'','','General',0,'','','','','',0,'',NULL,'','',0,'','','','','','2024/25','2025/26'),(73,'A.THARIK MASTHAN','U.K.G--D','L.K.G--',6,'Male','2019-04-01','','Indian','Islam',NULL,'2024-06-10','1215','','','','','2025-05-05',0,'','FNPS0073',NULL,NULL,'',NULL,NULL,'',NULL,'',0,NULL,'','',2,NULL,'',NULL,NULL,0,0,0,0,'Admission','','',NULL,NULL,'','','General',0,'','','','','',0,'',NULL,'','',0,'','','','','','2024/25','2025/26'),(74,'S.ANNLIN MARISHA','U.K.G--D','L.K.G--',5,'Female','2020-04-19','','Indian','Christianity',NULL,'2024-06-10','1288','','','','','2025-05-05',0,'','FNPS0074',NULL,NULL,'',NULL,NULL,'',NULL,'',0,NULL,'','',2,NULL,'',NULL,NULL,0,0,0,0,'Admission','','',NULL,NULL,'','','General',0,'','','','','',0,'',NULL,'','',0,'','','','','','2024/25','2025/26'),(75,'P.ECOMISHA','U.K.G--D','L.K.G--',6,'Female','2019-01-05','','Indian','Christianity',NULL,'2024-06-10','1271','','','','','2025-05-05',0,'','FNPS0075',NULL,NULL,'',NULL,NULL,'',NULL,'',0,NULL,'','',2,NULL,'',NULL,NULL,0,0,0,0,'Admission','','',NULL,NULL,'','','General',0,'','','','','',0,'',NULL,'','',0,'','','','','','2024/25','2025/26'),(76,'A.K.HAWWA BEEVI','U.K.G--D','L.K.G--',4,'Female','2020-07-02','','Indian','Islam',NULL,'2024-06-11','1298','','','','','2025-05-05',0,'','FNPS0076',NULL,NULL,'',NULL,NULL,'',NULL,'',0,NULL,'','',2,NULL,'',NULL,NULL,0,0,0,0,'Admission','','',NULL,NULL,'','','General',0,'','','','','',0,'',NULL,'','',0,'','','','','','2024/25','2025/26'),(77,'S.N.LIYANA','U.K.G--D','L.K.G--',4,'Female','2020-12-17','','Indian','Islam',NULL,'2024-06-10','1282','','','','','2025-05-05',0,'','FNPS0077',NULL,NULL,'',NULL,NULL,'',NULL,'',0,NULL,'','',2,NULL,'',NULL,NULL,0,0,0,0,'Admission','','',NULL,NULL,'','','General',0,'','','','','',0,'',NULL,'','',0,'','','','','','2024/25','2025/26'),(78,'S.A.K.NAJEEBA JASRA','U.K.G--D','L.K.G--',4,'Female','2020-11-13','','Indian','Islam',NULL,'2024-06-10','1232','','','','','2025-05-05',0,'','FNPS0078',NULL,NULL,'',NULL,NULL,'',NULL,'',0,NULL,'','',2,NULL,'',NULL,NULL,0,0,0,0,'Admission','','',NULL,NULL,'','','General',0,'','','','','',0,'',NULL,'','',0,'','','','','','2024/25','2025/26'),(79,'A.H.S.RAHMATH MARIYAM','U.K.G--D','L.K.G--',4,'Female','2020-09-22','','Indian','Islam',NULL,'2024-06-10','1210','','','','','2025-05-05',0,'','FNPS0079',NULL,NULL,'',NULL,NULL,'',NULL,'',0,NULL,'','',2,NULL,'',NULL,NULL,0,0,0,0,'Admission','','',NULL,NULL,'','','General',0,'','','','','',0,'',NULL,'','',0,'','','','','','2024/25','2025/26'),(80,'S.I.B.RIDHA','U.K.G--D','L.K.G--',4,'Female','2020-07-13','','Indian','Islam',NULL,'2024-06-10','1279','','','','','2025-05-05',0,'','FNPS0080',NULL,NULL,'',NULL,NULL,'',NULL,'',0,NULL,'','',2,NULL,'',NULL,NULL,0,0,0,0,'Admission','','',NULL,NULL,'','','General',0,'','','','','',0,'',NULL,'','',0,'','','','','','2024/25','2025/26'),(81,'U.R.SALMA SAHIRAH (OLD)','U.K.G--D','L.K.G--',4,'Female','2020-11-04','','Indian','Islam',NULL,'2024-06-10','1234','','','','','2025-05-05',0,'','FNPS0081',NULL,NULL,'',NULL,NULL,'',NULL,'',0,NULL,'','',2,NULL,'',NULL,NULL,0,0,0,0,'Admission','','',NULL,NULL,'','','General',0,'','','','','',0,'',NULL,'','',0,'','','','','','2024/25','2025/26'),(82,'S.THANISHA','U.K.G--D','L.K.G--',5,'Female','2019-10-01','','Indian','Hinduism',NULL,'2024-06-10','1274','','','','','2025-05-05',0,'','FNPS0082',NULL,NULL,'',NULL,NULL,'',NULL,'',0,NULL,'','',2,NULL,'',NULL,NULL,0,0,0,0,'Admission','','',NULL,NULL,'','','General',0,'','','','','',0,'',NULL,'','',0,'','','','','','2024/25','2025/26'),(83,'K.M.ABDUL HAMEED','I--A','L.K.G--',5,'Male','2019-10-13','','Indian','Islam',NULL,'2023-06-14','1177','','','','','2025-05-05',0,'','FNPS0083',NULL,NULL,'',NULL,NULL,'',NULL,'',0,NULL,'','',2,NULL,'',NULL,NULL,0,0,0,0,'Admission','','',NULL,NULL,'','','General',0,'','','','','',0,'',NULL,'','',0,'','','','','','2024/25','2025/26'),(84,'K.KAVIN RAMESH','I--A','L.K.G--',5,'Male','2020-01-26','','Indian','Hinduism',NULL,'2023-06-14','1159','','','','','2025-05-05',0,'','FNPS0084',NULL,NULL,'',NULL,NULL,'',NULL,'',0,NULL,'','',2,NULL,'',NULL,NULL,0,0,0,0,'Admission','','',NULL,NULL,'','','General',0,'','','','','',0,'',NULL,'','',0,'','','','','','2023/24','2025/26'),(85,'F.FADHLAN HAZIM','I--A','L.K.G--',5,'Male','2019-11-01','','Indian','Islam',NULL,'2023-06-14','1124','','','','','2025-05-05',0,'','FNPS0085',NULL,NULL,'',NULL,NULL,'',NULL,'',0,NULL,'','',2,NULL,'',NULL,NULL,0,0,0,0,'Admission','','',NULL,NULL,'','','General',0,'','','','','',0,'',NULL,'','',0,'','','','','','2023/24','2025/26'),(86,'M.F.SHEIK MAHDI','I--A','L.K.G--',5,'Male','2019-06-18','','Indian','Islam',NULL,'2023-06-14','1126','','','','','2025-05-05',0,'','FNPS0086',NULL,NULL,'',NULL,NULL,'',NULL,'',0,NULL,'','',2,NULL,'',NULL,NULL,0,0,0,0,'Admission','','',NULL,NULL,'','','General',0,'','','','','',0,'',NULL,'','',0,'','','','','','2023/24','2025/26'),(87,'R.HANISH','I--A','L.K.G--',7,'Male','2018-01-08','','Indian','Christianity',NULL,'2023-06-14','1147','','','','','2025-05-05',0,'','FNPS0087',NULL,NULL,'',NULL,NULL,'',NULL,'',0,NULL,'','',2,NULL,'',NULL,NULL,0,0,0,0,'Admission','','',NULL,NULL,'','','General',0,'','','','','',0,'',NULL,'','',0,'','','','','','2023/24','2025/26'),(88,'J.M.MOHAMED RISHADH','I--A','L.K.G--',6,'Male','2019-03-24','','Indian','',NULL,'2023-06-14','1123','','','','','2025-05-05',0,'','FNPS0088',NULL,NULL,'',NULL,NULL,'',NULL,'',0,NULL,'','',2,NULL,'',NULL,NULL,0,0,0,0,'Admission','','',NULL,NULL,'','','General',0,'','','','','',0,'',NULL,'','',0,'','','','','','2023/24','2025/26'),(89,'M.I.SADAKATHULLAH','I--A','L.K.G--',5,'Male','2019-05-18','','Indian','Islam',NULL,'2023-06-14','1125','','','','','2025-05-05',0,'','FNPS0089',NULL,NULL,'',NULL,NULL,'',NULL,'',0,NULL,'','',2,NULL,'',NULL,NULL,0,0,0,0,'Admission','','',NULL,NULL,'','','General',0,'','','','','',0,'',NULL,'','',0,'','','','','','2023/24','2025/26'),(90,'A.M.MOHAMED ISHAAQ','I--A','L.K.G--',5,'Male','2019-09-04','','Indian','Islam',NULL,'2023-06-14','1118','','','','','2025-05-05',0,'','FNPS0090',NULL,NULL,'',NULL,NULL,'',NULL,'',0,NULL,'','',2,NULL,'',NULL,NULL,0,0,0,0,'Admission','','',NULL,NULL,'','','General',0,'','','','','',0,'',NULL,'','',0,'','','','','','2022/23','2025/26'),(91,'M.HARISH','I--A','L.K.G--',105,'Male','0019-06-07','','Indian','Hinduism',NULL,'2023-06-14','1163','','','','','2025-05-05',0,'','FNPS0091',NULL,NULL,'',NULL,NULL,'',NULL,'',0,NULL,'','',2,NULL,'',NULL,NULL,0,0,0,0,'Admission','','',NULL,NULL,'','','General',0,'','','','','',0,'',NULL,'','',0,'','','','','','2023/24','2025/26'),(92,'M.N.L.HASAN MULTHAZEEM','I--A','L.K.G--',6,'Male','2019-02-28','','Indian','Islam',NULL,'2023-06-14','1176','','','','','2025-05-05',0,'','FNPS0092',NULL,NULL,'',NULL,NULL,'',NULL,'',0,NULL,'','',2,NULL,'',NULL,NULL,0,0,0,0,'Admission','','',NULL,NULL,'','','General',0,'','','','','',0,'',NULL,'','',0,'','','','','','2023/24','2025/26'),(93,'M.I.AHMED ANSAR ','I--A','L.K.G--',6,'Male','2019-03-31','','Indian','Islam',NULL,'2023-06-14','1108','','','','','2025-05-05',0,'','FNPS0093',NULL,NULL,'',NULL,NULL,'',NULL,'',0,NULL,'','',2,NULL,'',NULL,NULL,0,0,0,0,'Admission','','',NULL,NULL,'','','General',0,'','','','','',0,'',NULL,'','',0,'','','','','','2023/24','2025/26'),(94,'M.J.SHAH MEERAN MOHAMED RILWAN (NEW)','I--A','U.K.G--',5,'Male','2019-07-26','','Indian','Islam',NULL,'2024-06-10','1291','','','','','2025-05-05',0,'','FNPS0094',NULL,NULL,'',NULL,NULL,'',NULL,'',0,NULL,'','',2,NULL,'',NULL,NULL,0,0,0,0,'Admission','','',NULL,NULL,'','','General',0,'','','','','',0,'',NULL,'','',0,'','','','','','2024/25','2025/26'),(95,'S.MOHAMED AQEEL','I--A','L.K.G--',5,'Male','2020-04-07','','Indian','Islam',NULL,'2023-06-14','1107','','','','','2025-05-05',0,'','FNPS0095',NULL,NULL,'',NULL,NULL,'',NULL,'',0,NULL,'','',2,NULL,'',NULL,NULL,0,0,0,0,'Admission','','',NULL,NULL,'','','General',0,'','','','','',0,'',NULL,'','',0,'','','','','','2023/24','2025/26'),(96,'A.S.FATHIMA NAHLA','I--A','L.K.G--',5,'Female','2019-12-18','','Indian','Islam',NULL,'2023-06-14','1169','','','','','2025-05-05',0,'','FNPS0096',NULL,NULL,'',NULL,NULL,'',NULL,'',0,NULL,'','',2,NULL,'',NULL,NULL,0,0,0,0,'Admission','','',NULL,NULL,'','','General',0,'','','','','',0,'',NULL,'','',0,'','','','','','2023/24','2025/26'),(97,'S.N.FATHIMA NAZIYA','I--A','L.K.G--',5,'Female','2019-05-16','','Indian','Islam',NULL,NULL,'1120','','','','','2025-05-05',0,'','FNPS0097',NULL,NULL,'',NULL,NULL,'',NULL,'',0,NULL,'','',2,NULL,'',NULL,NULL,0,0,0,0,'Admission','','',NULL,NULL,'','','General',0,'','','','','',0,'',NULL,'','',0,'','','','','','2023/24','2025/26'),(98,'S.I.UMMU HABIBA','I--A','L.K.G--',4,'Female','2020-06-03','','Indian','Islam',NULL,'2023-06-14','1119','','','','','2025-05-05',0,'','FNPS0098',NULL,NULL,'',NULL,NULL,'',NULL,'',0,NULL,'','',2,NULL,'',NULL,NULL,0,0,0,0,'Admission','','',NULL,NULL,'','','General',0,'','','','','',0,'',NULL,'','',0,'','','','','','2023/24','2025/26'),(99,'S.MARISHABISHRI','I--A','L.K.G--',5,'Female','2019-11-11','','Indian','Hinduism',NULL,'2024-06-10','1137','','','','','2025-05-05',0,'','FNPS0099',NULL,NULL,'',NULL,NULL,'',NULL,'',0,NULL,'','',2,NULL,'',NULL,NULL,0,0,0,0,'Admission','','',NULL,NULL,'','','General',0,'','','','','',0,'',NULL,'','',0,'','','','','','2023/24','2025/26'),(100,'M.I.MYMOON MAHFIYA','I--A','L.K.G--',5,'Female','2019-09-17','','Indian','Islam',NULL,'2023-06-14','1152','','','','','2025-05-05',0,'','FNPS0100',NULL,NULL,'',NULL,NULL,'',NULL,'',0,NULL,'','',2,NULL,'',NULL,NULL,0,0,0,0,'Admission','','',NULL,NULL,'','','General',0,'','','','','',0,'',NULL,'','',0,'','','','','','2023/24','2025/26'),(101,'M.I.HALEEMA FATHIMA','I--A','L.K.G--',4,'Female','2020-07-06','','Indian','',NULL,'2023-06-14','1100','','','','','2025-05-05',0,'','FNPS0101',NULL,NULL,'',NULL,NULL,'',NULL,'',0,NULL,'','',2,NULL,'',NULL,NULL,0,0,0,0,'Admission','','',NULL,NULL,'','','General',0,'','','','','',0,'',NULL,'','',0,'','','','','','2023/24','2025/26'),(102,'Z.I.RABIYA RAFA','I--A','L.K.G--',5,'Female','2019-05-19','','Indian','Islam',NULL,'2022-06-17','962','','','','','2025-05-05',0,'','FNPS0102',NULL,NULL,'',NULL,NULL,'',NULL,'',0,NULL,'','',2,NULL,'',NULL,NULL,0,0,0,0,'Admission','','',NULL,NULL,'','','General',0,'','','','','',0,'',NULL,'','',0,'','','','','','2022/23','2025/26'),(103,'S.I. ZEENAT MUNAWARA','I--A','L.K.G--',5,'Female','2019-11-03','','Indian','Islam',NULL,'2023-06-14','1101','','','','','2025-05-05',0,'','FNPS0103',NULL,NULL,'',NULL,NULL,'',NULL,'',0,NULL,'','',2,NULL,'',NULL,NULL,0,0,0,0,'Admission','','',NULL,NULL,'','','General',0,'','','','','',0,'',NULL,'','',0,'','','','','','2023/24','2025/26'),(104,'H.N.ZAINUL HIFZA (NEW)','I--A','U.K.G--',5,'Female','2019-09-26','','Indian','Islam',NULL,'2024-06-10','1226','','','','','2025-05-05',0,'','FNPS0104',NULL,NULL,'',NULL,NULL,'',NULL,'',0,NULL,'','',2,NULL,'',NULL,NULL,0,0,0,0,'Admission','','',NULL,NULL,'','','General',0,'','','','','',0,'',NULL,'','',0,'','','','','','2024/25','2025/26'),(105,'M.N.R.SAJNA (NEW)','I--A','U.K.G--',6,'Female','2019-02-12','','Indian','Islam',NULL,'2024-10-12','1327','','','','','2025-05-05',0,'','FNPS0105',NULL,NULL,'',NULL,NULL,'',NULL,'',0,NULL,'','',2,NULL,'',NULL,NULL,0,0,0,0,'Admission','','',NULL,NULL,'','','General',0,'','','','','',0,'',NULL,'','',0,'','','','','','2024/25','2025/26'),(106,'M.B.AHAMED FAHEEM','I--B','L.K.G--',6,'Male','2019-03-25','','Indian','Islam',NULL,'2023-06-14','1142','','','','','2025-05-05',0,'','FNPS0106',NULL,NULL,'',NULL,NULL,'',NULL,'',0,NULL,'','',2,NULL,'',NULL,NULL,0,0,0,0,'Admission','','',NULL,NULL,'','','General',0,'','','','','',0,'',NULL,'','',0,'','','','','','2023/24','2025/26'),(107,'R.DENIKSON (NEW)','I--B','U.K.G--',5,'Male','2020-02-12','','Indian','Christianity',NULL,'2024-06-10','1277','','','','','2025-05-05',0,'','FNPS0107',NULL,NULL,'',NULL,NULL,'',NULL,'',0,NULL,'','',2,NULL,'',NULL,NULL,0,0,0,0,'Admission','','',NULL,NULL,'','','General',0,'','','','','',0,'',NULL,'','',0,'','','','','','2024/25','2025/26'),(108,'N.FRIDAN (NEW)','I--B','U.K.G--',106,'Male','0019-02-12','','Indian','Christianity',NULL,'2024-06-10','1306','','','','','2025-05-05',0,'','FNPS0108',NULL,NULL,'',NULL,NULL,'',NULL,'',0,NULL,'','',2,NULL,'',NULL,NULL,0,0,0,0,'Admission','','',NULL,NULL,'','','General',0,'','','','','',0,'',NULL,'','',0,'','','','','','','2025/26'),(109,'R.JEYATHIPAN','I--B','L.K.G--',5,'Male','2019-05-14','','Indian','Hinduism',NULL,'2023-06-14','1171','','','','','2025-05-05',0,'','FNPS0109',NULL,NULL,'',NULL,NULL,'',NULL,'',0,NULL,'','',2,NULL,'',NULL,NULL,0,0,0,0,'Admission','','',NULL,NULL,'','','General',0,'','','','','',0,'',NULL,'','',0,'','','','','','2023/24','2025/26'),(110,'S.A.KASIM RUWAIFI','I--B','U.K.G--',5,'Male','2019-08-08','','Indian','Islam',NULL,'2024-06-10','1252','','','','','2025-05-05',0,'','FNPS0110',NULL,NULL,'',NULL,NULL,'',NULL,'',0,NULL,'','',2,NULL,'',NULL,NULL,0,0,0,0,'Admission','','',NULL,NULL,'','','General',0,'','','','','',0,'',NULL,'','',0,'','','','','','2024/25','2025/26'),(111,'A.N.MOHAMMED  AAFIQ','I--B','L.K.G--',1,'Male','2023-06-12','','Indian','Islam',NULL,'2023-06-14','1173','','','','','2025-05-05',0,'','FNPS0111',NULL,NULL,'',NULL,NULL,'',NULL,'',0,NULL,'','',2,NULL,'',NULL,NULL,0,0,0,0,'Admission','','',NULL,NULL,'','','General',0,'','','','','',0,'',NULL,'','',0,'','','','','','2023/24','2025/26'),(112,'S.S.SALMAN FARIS','I--B','L.K.G--',5,'Male','2019-05-22','','Indian','Islam',NULL,'2023-06-14','1117','','','','','2025-05-05',0,'','FNPS0112',NULL,NULL,'',NULL,NULL,'',NULL,'',0,NULL,'','',2,NULL,'',NULL,NULL,0,0,0,0,'Admission','','',NULL,NULL,'','','General',0,'','','','','',0,'',NULL,'','',0,'','','','','','2023/24','2025/26'),(113,'M.A.K.SEYED SAFWAN','I--B','L.K.G--',6,'Male','2019-01-18','','Indian','Islam',NULL,'2023-06-14','1135','','','','','2025-05-05',0,'','FNPS0113',NULL,NULL,'',NULL,NULL,'',NULL,'',0,NULL,'','',2,NULL,'',NULL,NULL,0,0,0,0,'Admission','','',NULL,NULL,'','','General',0,'','','','','',0,'',NULL,'','',0,'','','','','','2023/24','2025/26'),(114,'I.SEYED AHAMED KABEER','I--B','L.K.G--',6,'Male','2018-12-23','','Indian','Islam',NULL,'2023-06-14','1133','','','','','2025-05-05',0,'','FNPS0114',NULL,NULL,'',NULL,NULL,'',NULL,'',0,NULL,'','',2,NULL,'',NULL,NULL,0,0,0,0,'Admission','','',NULL,NULL,'','','General',0,'','','','','',0,'',NULL,'','',0,'','','','','','2023/24','2025/26'),(115,'J.tHAHSEEN ISHAAQ','I--B','L.K.G--',6,'Male','2019-01-26','','Indian','Islam',NULL,'2023-06-14','1102','','','','','2025-05-05',0,'','FNPS0115',NULL,NULL,'',NULL,NULL,'',NULL,'',0,NULL,'','',2,NULL,'',NULL,NULL,0,0,0,0,'Admission','','',NULL,NULL,'','','General',0,'','','','','',0,'',NULL,'','',0,'','','','','','2023/24','2025/26'),(116,'S.H.THOUFEEK','I--B','L.K.G--',5,'Male','2019-10-13','','Indian','Islam',NULL,'2023-06-15','1183','','','','','2025-05-05',0,'','FNPS0116',NULL,NULL,'',NULL,NULL,'',NULL,'',0,NULL,'','',2,NULL,'',NULL,NULL,0,0,0,0,'Admission','','',NULL,NULL,'','','General',0,'','','','','',0,'',NULL,'','',0,'','','','','','2023/24','2025/26'),(117,'C.ROSHAN (NEW)','I--B','U.K.G--',5,'Male','2019-05-24','','Indian','Christianity',NULL,'2024-06-10','1219','','','','','2025-05-05',0,'','FNPS0117',NULL,NULL,'',NULL,NULL,'',NULL,'',0,NULL,'','',2,NULL,'',NULL,NULL,0,0,0,0,'Admission','','',NULL,NULL,'','','General',0,'','','','','',0,'',NULL,'','',0,'','','','','','2024/25','2025/26'),(118,'R.MOHAMED FAHIM','I--B','U.K.G--',5,'Male','2019-08-28','','Indian','Islam',NULL,'2024-06-10','1273','','','','','2025-05-05',0,'','FNPS0118',NULL,NULL,'',NULL,NULL,'',NULL,'',0,NULL,'','',2,NULL,'',NULL,NULL,0,0,0,0,'Admission','','',NULL,NULL,'','','General',0,'','','','','',0,'',NULL,'','',0,'','','','','','2024/25','2025/26'),(119,'AZMINA NASEEFA (NEW)','I--B','U.K.G--',5,'Female','2019-06-19','','Indian','Islam',NULL,'2024-06-10','1209','','','','','2025-05-05',0,'','FNPS0119',NULL,NULL,'',NULL,NULL,'',NULL,'',0,NULL,'','',2,NULL,'',NULL,NULL,0,0,0,0,'Admission','','',NULL,NULL,'','','General',0,'','','','','',0,'',NULL,'','',0,'','','','','','2024/25','2025/26'),(120,'S.I.FATHIMA','I--B','L.K.G--',5,'Female','2019-12-06','','Indian','Islam',NULL,'2023-06-14','1111','','','','','2025-05-05',0,'','FNPS0120',NULL,NULL,'',NULL,NULL,'',NULL,'',0,NULL,'','',2,NULL,'',NULL,NULL,0,0,0,0,'Admission','','',NULL,NULL,'','','General',0,'','','','','',0,'',NULL,'','',0,'','','','','','2023/24','2025/26'),(121,'A.A.FATHIMA INAAYA','I--B','L.K.G--',6,'Female','2019-02-25','','Indian','Islam',NULL,'2023-06-14','1122','','','','','2025-05-05',0,'','FNPS0121',NULL,NULL,'',NULL,NULL,'',NULL,'',0,NULL,'','',2,NULL,'',NULL,NULL,0,0,0,0,'Admission','','',NULL,NULL,'','','General',0,'','','','','',0,'',NULL,'','',0,'','','','','','2023/24','2025/26'),(122,'K.M.HAFNA (NEW)','I--B','U.K.G--',4,'Female','2020-05-29','','Indian','Islam',NULL,'2024-06-10','1258','','','','','2025-05-05',0,'','FNPS0122',NULL,NULL,'',NULL,NULL,'',NULL,'',0,NULL,'','',2,NULL,'',NULL,NULL,0,0,0,0,'Admission','','',NULL,NULL,'','','General',0,'','','','','',0,'',NULL,'','',0,'','','','','','2024/25','2025/26'),(123,'S.D.HASEENA FIRDOUS','I--B','L.K.G--',5,'Female','2019-11-13','','Indian','',NULL,'2023-06-14','1127','','','','','2025-05-05',0,'','FNPS0123',NULL,NULL,'',NULL,NULL,'',NULL,'',0,NULL,'','',2,NULL,'',NULL,NULL,0,0,0,0,'Admission','','',NULL,NULL,'','','General',0,'','','','','',0,'',NULL,'','',0,'','','','','','2023/24','2025/26'),(124,'S.H.HAWWA RAZEENA','I--B','L.K.G--',5,'Female','2020-02-11','','Indian','Islam',NULL,'2023-06-14','1106','','','','','2025-05-05',0,'','FNPS0124',NULL,NULL,'',NULL,NULL,'',NULL,'',0,NULL,'','',2,NULL,'',NULL,NULL,0,0,0,0,'Admission','','',NULL,NULL,'','','General',0,'','','','','',0,'',NULL,'','',0,'','','','','','2023/24','2025/26'),(125,'KATHEEB ZUBAIDA','I--B','L.K.G--',5,'Female','2019-09-26','','Indian','Islam',NULL,'2023-06-14','1128','','','','','2025-05-05',0,'','FNPS0125',NULL,NULL,'',NULL,NULL,'',NULL,'',0,NULL,'','',2,NULL,'',NULL,NULL,0,0,0,0,'Admission','','',NULL,NULL,'','','General',0,'','','','','',0,'',NULL,'','',0,'','','','','','2024/25','2025/26'),(126,'S.N.KADHIJA RUMAISA','I--B','L.K.G--',6,'Female','2019-02-19','','Indian','Islam',NULL,'2023-06-20','1188','','','','','2025-05-05',0,'','FNPS0126',NULL,NULL,'',NULL,NULL,'',NULL,'',0,NULL,'','',2,NULL,'',NULL,NULL,0,0,0,0,'Admission','','',NULL,NULL,'','','General',0,'','','','','',0,'',NULL,'','',0,'','','','','','2023/24','2025/26'),(127,'S.M.MARIYAM ZIYANA','I--B','U.K.G--',5,'Female','2020-02-04','','Indian','Islam',NULL,'2024-06-19','1307','','','','','2025-05-05',0,'','FNPS0127',NULL,NULL,'',NULL,NULL,'',NULL,'',0,NULL,'','',2,NULL,'',NULL,NULL,0,0,0,0,'Admission','','',NULL,NULL,'','','General',0,'','','','','',0,'',NULL,'','',0,'','','','','','2024/25','2025/26'),(128,'R.REJASRIKA (NEW)','I--B','U.K.G--',4,'Female','2020-06-03','','Indian','Christianity',NULL,'2024-06-10','1278','','','','','2025-05-05',0,'','FNPS0128',NULL,NULL,'',NULL,NULL,'',NULL,'',0,NULL,'','',2,NULL,'',NULL,NULL,0,0,0,0,'Admission','','',NULL,NULL,'','','General',0,'','','','','',0,'',NULL,'','',0,'','','','','','2024/25','2025/26'),(129,'R.S.L.MUHAMED RAYYAN','I--C','L.K.G--',5,'Male','2019-06-05','','Indian','Islam',NULL,'2023-06-14','1139','','','','','2025-05-05',0,'','FNPS0129',NULL,NULL,'',NULL,NULL,'',NULL,'',0,NULL,'','',2,NULL,'',NULL,NULL,0,0,0,0,'Admission','','',NULL,NULL,'','','General',0,'','','','','',0,'',NULL,'','',0,'','','','','','2023/24','2025/26'),(130,'F.AFEF ISMAIL','I--C','L.K.G--',6,'Male','2019-02-21','','Indian','Islam',NULL,'2023-06-14','1153','','','','','2025-05-05',0,'','FNPS0130',NULL,NULL,'',NULL,NULL,'',NULL,'',0,NULL,'','',2,NULL,'',NULL,NULL,0,0,0,0,'Admission','','',NULL,NULL,'','','General',0,'','','','','',0,'',NULL,'','',0,'','','','','','2022/23','2025/26'),(131,'M.IKRAAM HUSSAIN','I--C','L.K.G--',4,'Male','2020-05-17','','Indian','Islam',NULL,'2023-06-14','1164','','','','','2025-05-05',0,'','FNPS0131',NULL,NULL,'',NULL,NULL,'',NULL,'',0,NULL,'','',2,NULL,'',NULL,NULL,0,0,0,0,'Admission','','',NULL,NULL,'','','General',0,'','','','','',0,'',NULL,'','',0,'','','','','','2023/24','2025/26'),(132,'A.MOHAMMED ABUBACKER','I--C','L.K.G--',5,'Male','2019-10-18','','Indian','Islam',NULL,'2023-06-14','1158','','','','','2025-05-05',0,'','FNPS0132',NULL,NULL,'',NULL,NULL,'',NULL,'',0,NULL,'','',2,NULL,'',NULL,NULL,0,0,0,0,'Admission','','',NULL,NULL,'','','General',0,'','','','','',0,'',NULL,'','',0,'','','','','','2023/24','2025/26'),(133,'A.ZUHAIR HAMSA','I--C','L.K.G--',6,'Male','2018-12-31','','Indian','Islam',NULL,'2023-06-14','1112','','','','','2025-05-05',0,'','FNPS0133',NULL,NULL,'',NULL,NULL,'',NULL,'',0,NULL,'','',2,NULL,'',NULL,NULL,0,0,0,0,'Admission','','',NULL,NULL,'','','General',0,'','','','','',0,'',NULL,'','',0,'','','','','','2023/24','2025/26'),(134,'S.I.MOHAMED THAHA','I--C','L.K.G--',6,'Male','2018-11-28','','Indian','Islam',NULL,'2023-06-14','1105','','','','','2025-05-05',0,'','FNPS0134',NULL,NULL,'',NULL,NULL,'',NULL,'',0,NULL,'','',2,NULL,'',NULL,NULL,0,0,0,0,'Admission','','',NULL,NULL,'','','General',0,'','','','','',0,'',NULL,'','',0,'','','','','','2023/24','2025/26'),(135,'S.SANJEEVI SANJAY (NEW)','I--C','U.K.G--',5,'Male','2019-05-30','','Indian','Hinduism',NULL,'2024-06-18','1309','','','','','2025-05-05',0,'','FNPS0135',NULL,NULL,'',NULL,NULL,'',NULL,'',0,NULL,'','',2,NULL,'',NULL,NULL,0,0,0,0,'Admission','','',NULL,NULL,'','','General',0,'','','','','',0,'',NULL,'','',0,'','','','','','2024/25','2025/26'),(136,'P.JOUWIN','I--C','L.K.G--',6,'Male','2018-12-27','','Indian','Christianity',NULL,'2023-06-14','1121','','','','','2025-05-05',0,'','FNPS0136',NULL,NULL,'',NULL,NULL,'',NULL,'',0,NULL,'','',2,NULL,'',NULL,NULL,0,0,0,0,'Admission','','',NULL,NULL,'','','General',0,'','','','','',0,'',NULL,'','',0,'','','','','','2023/24','2025/26'),(137,'A.S.MOHAMED VASIM','I--C','L.K.G--',6,'Male','2019-01-24','','Indian','Islam',NULL,NULL,'1170','','','','','2025-05-05',0,'','FNPS0137',NULL,NULL,'',NULL,NULL,'',NULL,'',0,NULL,'','',2,NULL,'',NULL,NULL,0,0,0,0,'Admission','','',NULL,NULL,'','','General',0,'','','','','',0,'',NULL,'','',0,'','','','','','2023/24','2025/26'),(138,'S.A.MOHAMMED RAKEEN','I--C','L.K.G--',5,'Male','2019-08-17','','Indian','Islam',NULL,'2023-06-19','1186','','','','','2025-05-05',0,'','FNPS0138',NULL,NULL,'',NULL,NULL,'',NULL,'',0,NULL,'','',2,NULL,'',NULL,NULL,0,0,0,0,'Admission','','',NULL,NULL,'','','General',0,'','','','','',0,'',NULL,'','',0,'','','','','','2023/24','2025/26'),(139,'H.SIVARAJ (NEW)','I--C','U.K.G--',5,'Male','2020-01-06','','Indian','Hinduism',NULL,'2024-06-11','1302','','','','','2025-05-05',0,'','FNPS0139',NULL,NULL,'',NULL,NULL,'',NULL,'',0,NULL,'','',2,NULL,'',NULL,NULL,0,0,0,0,'Admission','','',NULL,NULL,'','','General',0,'','','','','',0,'',NULL,'','',0,'','','','','','2024/25','2025/26'),(140,'S.A.B. JAMAL MOHAMED SIDDIQUE','I--C','L.K.G--',5,'Male','2019-12-14','','Indian','Islam',NULL,'2023-06-14','1131','','','','','2025-05-05',0,'','FNPS0140',NULL,NULL,'',NULL,NULL,'',NULL,'',0,NULL,'','',2,NULL,'',NULL,NULL,0,0,0,0,'Admission','','',NULL,NULL,'','','General',0,'','','','','',0,'',NULL,'','',0,'','','','','','2023/24','2025/26'),(141,'M.HATIM RAFAN (LEFT AFTER 1ST TERM)','I--C','',0,'Male',NULL,NULL,'Indian',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'2025-05-05',0,NULL,'FNPS0141',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,2,NULL,NULL,NULL,NULL,0,0,0,NULL,'Admission',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,0,NULL,NULL,NULL,NULL,2,NULL,NULL,NULL,NULL,NULL,NULL,'2025/26'),(142,'S.T.HAMNA','I--C','L.K.G--',6,'Female','2019-02-10','','Indian','Islam',NULL,'2022-06-13','981','','','','','2025-05-05',0,'','FNPS0142',NULL,NULL,'',NULL,NULL,'',NULL,'',0,NULL,'','',2,NULL,'',NULL,NULL,0,0,0,0,'Admission','','',NULL,NULL,'','','General',0,'','','','','',0,'',NULL,'','',0,'','','','','','2022/23','2025/26'),(143,'M.M.S MUHAMMED AYSHA UMMAL','I--C','L.K.G--',6,'Female','2019-05-08','','Indian','Islam',NULL,'2023-06-16','1184','','','','','2025-05-05',0,'','FNPS0143',NULL,NULL,'',NULL,NULL,'',NULL,'',0,NULL,'','',2,NULL,'',NULL,NULL,0,0,0,0,'Admission','','',NULL,NULL,'','','General',0,'','','','','',0,'',NULL,'','',0,'','','','','','2023/24','2025/26'),(144,'M.M.KATHIJA','I--C','L.K.G--',4,'Female','2020-06-19','','Indian','Islam',NULL,'2023-06-14','1113','','','','','2025-05-05',0,'','FNPS0144',NULL,NULL,'',NULL,NULL,'',NULL,'',0,NULL,'','',2,NULL,'',NULL,NULL,0,0,0,0,'Admission','','',NULL,NULL,'','','General',0,'','','','','',0,'',NULL,'','',0,'','','','','','2023/24','2025/26'),(145,'K.S.FATHIMA AARISHA','I--C','L.K.G--',6,'Female','2019-01-25','','Indian','Islam',NULL,'2023-06-14','1115','','','','','2025-05-05',0,'','FNPS0145',NULL,NULL,'',NULL,NULL,'',NULL,'',0,NULL,'','',2,NULL,'',NULL,NULL,0,0,0,0,'Admission','','',NULL,NULL,'','','General',0,'','','','','',0,'',NULL,'','',0,'','','','','','2023/24','2025/26'),(146,'S.M.RABIYA NOWSHEEN','I--C','L.K.G--',6,'Female','2019-01-18','','Indian','Islam',NULL,'2023-06-22','1190','','','','','2025-05-05',0,'','FNPS0146',NULL,NULL,'',NULL,NULL,'',NULL,'',0,NULL,'','',2,NULL,'',NULL,NULL,0,0,0,0,'Admission','','',NULL,NULL,'','','General',0,'','','','','',0,'',NULL,'','',0,'','','','','','2023/24','2025/26'),(147,'M.E.SALMA THASNEEM','I--C','L.K.G--',6,'Female','2018-12-24','','Indian','Islam',NULL,'2023-06-14','1143','','','','','2025-05-05',0,'','FNPS0147',NULL,NULL,'',NULL,NULL,'',NULL,'',0,NULL,'','',2,NULL,'',NULL,NULL,0,0,0,0,'Admission','','',NULL,NULL,'','','General',0,'','','','','',0,'',NULL,'','',0,'','','','','','2023/24','2025/26'),(148,'N.KITHURU NAFIDHA','I--C','L.K.G--',5,'Female','2020-03-24','','Indian','Islam',NULL,'2023-06-20','1189','','','','','2025-05-05',0,'','FNPS0148',NULL,NULL,'',NULL,NULL,'',NULL,'',0,NULL,'','',2,NULL,'',NULL,NULL,0,0,0,0,'Admission','','',NULL,NULL,'','','General',0,'','','','','',0,'',NULL,'','',0,'','','','','','2023/24','2025/26'),(149,'K.ANISHA','I--C','L.K.G--',6,'Female','2019-04-19','','Indian','Islam',NULL,'2023-06-14','1174','','','','','2025-05-05',0,'','FNPS0149',NULL,NULL,'',NULL,NULL,'',NULL,'',0,NULL,'','',2,NULL,'',NULL,NULL,0,0,0,0,'Admission','','',NULL,NULL,'','','General',0,'','','','','',0,'',NULL,'','',0,'','','','','','2023/24','2025/26'),(150,'U.MARIYAM HANAA','I--C','L.K.G--',5,'Female','2019-08-29','','Indian','Islam',NULL,'2023-06-14','1136','','','','','2025-05-05',0,'','FNPS0150',NULL,NULL,'',NULL,NULL,'',NULL,'',0,NULL,'','',2,NULL,'',NULL,NULL,0,0,0,0,'Admission','','',NULL,NULL,'','','General',0,'','','','','',0,'',NULL,'','',0,'','','','','','2023/24','2025/26'),(151,'J.ZAINAB SATHIYA','I--C','L.K.G--',5,'Female','2019-09-26','','Indian','Islam',NULL,'2023-06-14','1099','','','','','2025-05-05',0,'','FNPS0151',NULL,NULL,'',NULL,NULL,'',NULL,'',0,NULL,'','',2,NULL,'',NULL,NULL,0,0,0,0,'Admission','','',NULL,NULL,'','','General',0,'','','','','',0,'',NULL,'','',0,'','','','','','2023/24','2025/26'),(152,'S.S.A.AFZAL','II--A','L.K.G--',7,'Male','2017-07-24','','Indian','Islam',NULL,'2022-06-16','951','','','','','2025-05-05',0,'','FNPS0152',NULL,NULL,'',NULL,NULL,'',NULL,'',0,NULL,'','',2,NULL,'',NULL,NULL,0,0,0,0,'Admission','','',NULL,NULL,'','','General',0,'','','','','',0,'',NULL,'','',0,'','','','','','2022/23','2025/26'),(153,'A.ABUL HASAN ANSARI','II--A','L.K.G--',6,'Male','2018-05-15','','Indian','Islam',NULL,'2022-06-20','949','','','','','2025-05-05',0,'','FNPS0153',NULL,NULL,'',NULL,NULL,'',NULL,'',0,NULL,'','',2,NULL,'',NULL,NULL,0,0,0,0,'Admission','','',NULL,NULL,'','','General',0,'','','','','',0,'',NULL,'','',0,'','','','','','2022/23','2025/26'),(154,'S.U.N.ABDUL HAFIL','II--A','L.K.G--',6,'Male','2018-06-06','','Indian','Islam',NULL,'2022-06-10','930','','','','','2025-05-05',0,'','FNPS0154',NULL,NULL,'',NULL,NULL,'',NULL,'',0,NULL,'','',2,NULL,'',NULL,NULL,0,0,0,0,'Admission','','',NULL,NULL,'','','General',0,'','','','','',0,'',NULL,'','',0,'','','','','','2022/23','2025/26'),(155,'S.ABDUL FAHIM','II--A','L.K.G--',6,'Male','2018-10-22','','Indian','Islam',NULL,'2023-06-14','1156','','','','','2025-05-05',0,'','FNPS0155',NULL,NULL,'',NULL,NULL,'',NULL,'',0,NULL,'','',2,NULL,'',NULL,NULL,0,0,0,0,'Admission','','',NULL,NULL,'','','General',0,'','','','','',0,'',NULL,'','',0,'','','','','','2023/24','2025/26'),(156,'C.DHARUN','II--A','L.K.G--',7,'Male','2018-03-07','','Indian','Hinduism',NULL,'2022-06-13','940','','','','','2025-05-05',0,'','FNPS0156',NULL,NULL,'',NULL,NULL,'',NULL,'',0,NULL,'','',2,NULL,'',NULL,NULL,0,0,0,0,'Admission','','',NULL,NULL,'','','General',0,'','','','','',0,'',NULL,'','',0,'','','','','','2022/23','2025/26'),(157,'M.ESSAKI MUTHU','II--A','L.K.G--',7,'Male','2018-01-24','','Indian','Hinduism',NULL,'2022-06-13','938','','','','','2025-05-05',0,'','FNPS0157',NULL,NULL,'',NULL,NULL,'',NULL,'',0,NULL,'','',2,NULL,'',NULL,NULL,0,0,0,0,'Admission','','',NULL,NULL,'','','General',0,'','','','','',0,'',NULL,'','',0,'','','','','','2022/23','2025/26'),(158,'J.GIFTSON','II--A','',6,'Male','2019-03-30','','Indian','Christianity',NULL,'2022-06-27','908','','','','','2025-05-05',0,'','FNPS0158',NULL,NULL,'',NULL,NULL,'',NULL,'',0,NULL,'','',2,NULL,'',NULL,NULL,0,0,0,0,'Admission','','',NULL,NULL,'','','General',0,'','','','','',0,'',NULL,'','',0,'','','','','','2022/23','2025/26'),(159,'A.M.HUSSAIN AHAMED','II--A','L.K.G--',6,'Male','2018-07-10','','Indian','Islam',NULL,'2022-06-08','936','','','','','2025-05-05',0,'','FNPS0159',NULL,NULL,'',NULL,NULL,'',NULL,'',0,NULL,'','',2,NULL,'',NULL,NULL,0,0,0,0,'Admission','','',NULL,NULL,'','','General',0,'','','','','',0,'',NULL,'','',0,'','','','','','2022/23','2025/26'),(160,'S.HARISH KUMAR','II--A','L.K.G--',7,'Male','2018-02-28','','Indian','Hinduism',NULL,'2022-06-13','914','','','','','2025-05-05',0,'','FNPS0160',NULL,NULL,'',NULL,NULL,'',NULL,'',0,NULL,'','',2,NULL,'',NULL,NULL,0,0,0,0,'Admission','','',NULL,NULL,'','','General',0,'','','','','',0,'',NULL,'','',0,'','','','','','2022/23','2025/26'),(161,'T.A.IZZADDEEN','II--A','L.K.G--',6,'Male','2018-11-23','','Indian','Islam',NULL,'2022-06-07','939','','','','','2025-05-05',0,'','FNPS0161',NULL,NULL,'',NULL,NULL,'',NULL,'',0,NULL,'','',2,NULL,'',NULL,NULL,0,0,0,0,'Admission','','',NULL,NULL,'','','General',0,'','','','','',0,'',NULL,'','',0,'','','','','','2022/23','2025/26'),(162,'M.JEFRON','II--A','U.K.G--',7,'Male','2018-02-04','','Indian','Christianity',NULL,'2023-06-14','1165','','','','','2025-05-05',0,'','FNPS0162',NULL,NULL,'',NULL,NULL,'',NULL,'',0,NULL,'','',2,NULL,'',NULL,NULL,0,0,0,0,'Admission','','',NULL,NULL,'','','General',0,'','','','','',0,'',NULL,'','',0,'','','','','','2023/24','2025/26'),(163,'SA.K.MOHAMED THAIKA SAHILE','II--A','L.K.G--',6,'Male','2018-07-03','','Indian','Islam',NULL,'2022-06-13','945','','','','','2025-05-05',0,'','FNPS0163',NULL,NULL,'',NULL,NULL,'',NULL,'',0,NULL,'','',2,NULL,'',NULL,NULL,0,0,0,0,'Admission','','',NULL,NULL,'','','General',0,'','','','','',0,'',NULL,'','',0,'','','','','','2022/23','2025/26'),(164,'A.S.MOHAMED ISHAAQ','II--A','L.K.G--',7,'Male','2018-04-13','','Indian','Islam',NULL,'2022-06-13','915','','','','','2025-05-05',0,'','FNPS0164',NULL,NULL,'',NULL,NULL,'',NULL,'',0,NULL,'','',2,NULL,'',NULL,NULL,0,0,0,0,'Admission','','',NULL,NULL,'','','General',0,'','','','','',0,'',NULL,'','',0,'','','','','','2022/23','2025/26'),(165,'A.MOHAMED KASIM ','II--A','I--',6,'Male','2018-09-20','','Indian','Islam',NULL,'2024-06-10','1265','','','','','2025-05-05',0,'','FNPS0165',NULL,NULL,'',NULL,NULL,'',NULL,'',0,NULL,'','',2,NULL,'',NULL,NULL,0,0,0,0,'Admission','','',NULL,NULL,'','','General',0,'','','','','',0,'',NULL,'','',0,'','','','','','2024/25','2025/26'),(166,'S.MOHAMED FAHEEM','II--A','L.K.G--',6,'Male','2018-06-11','','Indian','Islam',NULL,'2022-06-20','912','','','','','2025-05-05',0,'','FNPS0166',NULL,NULL,'',NULL,NULL,'',NULL,'',0,NULL,'','',2,NULL,'',NULL,NULL,0,0,0,0,'Admission','','',NULL,NULL,'','','General',0,'','','','','',0,'',NULL,'','',0,'','','','','','2022/23','2025/26'),(167,'Z.H.MOHAMED JIBRAN','II--A','L.K.G--',7,'Male','2018-03-24','','Indian','Islam',NULL,'2022-06-13','918','','','','','2025-05-05',0,'','FNPS0167',NULL,NULL,'',NULL,NULL,'',NULL,'',0,NULL,'','',2,NULL,'',NULL,NULL,0,0,0,0,'Admission','','',NULL,NULL,'','','General',0,'','','','','',0,'',NULL,'','',0,'','','','','','2022/23','2025/26'),(168,'M.MOHAMED FAALIH','II--A','',6,'Male','2019-01-02','','Indian','Islam',NULL,'2022-06-13','909','','','','','2025-05-05',0,'','FNPS0168',NULL,NULL,'',NULL,NULL,'',NULL,'',0,NULL,'','',2,NULL,'',NULL,NULL,0,0,0,0,'Admission','','',NULL,NULL,'','','General',0,'','','','','',0,'',NULL,'','',0,'','','','','','2022/23','2025/26'),(169,'M.MOHAMED FAHUMUDEEN','II--A','L.K.G--',6,'Male','2018-12-26','','Indian','Islam',NULL,'2022-10-14','1092','','','','','2025-05-05',0,'','FNPS0169',NULL,NULL,'',NULL,NULL,'',NULL,'',0,NULL,'','',2,NULL,'',NULL,NULL,0,0,0,0,'Admission','','',NULL,NULL,'','','General',0,'','','','','',0,'',NULL,'','',0,'','','','','','2023/24','2025/26'),(170,'J.K.MOHAMED IBRAHIM','II--A','L.K.G--',106,'Male','0018-09-26','','Indian','Islam',NULL,'2022-06-13','925','','','','','2025-05-05',0,'','FNPS0170',NULL,NULL,'',NULL,NULL,'',NULL,'',0,NULL,'','',2,NULL,'',NULL,NULL,0,0,0,0,'Admission','','',NULL,NULL,'','','General',0,'','','','','',0,'',NULL,'','',0,'','','','','','2022/23','2025/26'),(171,'F.A.MOHAMED BISHRUL BUHARI','II--A','L.K.G--',7,'Male','2018-01-03','','Indian','Islam',NULL,'2022-06-13','928','','','','','2025-05-05',0,'','FNPS0171',NULL,NULL,'',NULL,NULL,'',NULL,'',0,NULL,'','',2,NULL,'',NULL,NULL,0,0,0,0,'Admission','','',NULL,NULL,'','','General',0,'','','','','',0,'',NULL,'','',0,'','','','','','2022/23','2025/26'),(172,'S.RAHATH SIDDEEQI','II--A','I--',5,'Male','2019-05-17','','Indian','Islam',NULL,'2023-12-22','1325','','','','','2025-05-05',0,'','FNPS0172',NULL,NULL,'',NULL,NULL,'',NULL,'',0,NULL,'','',2,NULL,'',NULL,NULL,0,0,0,0,'Admission','','',NULL,NULL,'','','General',0,'','','','','',0,'',NULL,'','',0,'','','','','','2024/25','2025/26'),(173,'M.A.K.B.SAMU SINAN','II--A','L.K.G--',6,'Male','2018-10-09','','Indian','Islam',NULL,'2022-06-13','926','','','','','2025-05-05',0,'','FNPS0173',NULL,NULL,'',NULL,NULL,'',NULL,'',0,NULL,'','',2,NULL,'',NULL,NULL,0,0,0,0,'Admission','','',NULL,NULL,'','','General',0,'','','','','',0,'',NULL,'','',0,'','','','','','2022/23','2025/26'),(174,'T.SAHUL ASHFAQ','II--A','U.K.G--',6,'Male','2018-12-12','','Indian','Islam',NULL,'2023-08-04','1198','','','','','2025-05-05',0,'','FNPS0174',NULL,NULL,'',NULL,NULL,'',NULL,'',0,NULL,'','',2,NULL,'',NULL,NULL,0,0,0,0,'Admission','','',NULL,NULL,'','','General',0,'','','','','',0,'',NULL,'','',0,'','','','','','2023/24','2025/26'),(175,'B.VIVEKANANDHAN','II--A','L.K.G--',7,'Male','2018-02-24','','Indian','Hinduism',NULL,'2022-06-17','916','','','','','2025-05-05',0,'','FNPS0175',NULL,NULL,'',NULL,NULL,'',NULL,'',0,NULL,'','',2,NULL,'',NULL,NULL,0,0,0,0,'Admission','','',NULL,NULL,'','','General',0,'','','','','',0,'',NULL,'','',0,'','','','','','2022/23','2025/26'),(176,'S.H. MOHAMMED HAMZA','II--A','I--',6,'Male','2018-05-25','','Indian','Islam',NULL,'0024-06-27','1315','','','','','2025-05-05',0,'','FNPS0176',NULL,NULL,'',NULL,NULL,'',NULL,'',0,NULL,'','',2,NULL,'',NULL,NULL,0,0,0,0,'Admission','','',NULL,NULL,'','','General',0,'','','','','',0,'',NULL,'','',0,'','','','','','2024/25','2025/26'),(177,'H.AFRASULTHANA (NEW)','II--B','I--',6,'Female','2018-05-14','','Indian','Islam',NULL,'2024-06-10','1223','','','','','2025-05-05',0,'','FNPS0177',NULL,NULL,'',NULL,NULL,'',NULL,'',0,NULL,'','',2,NULL,'',NULL,NULL,0,0,0,0,'Admission','','',NULL,NULL,'','','General',0,'','','','','',0,'',NULL,'','',0,'','','','','','2024/25','2025/26'),(178,'M.F.ALISHA NAAZIYA (NEW)','II--B','I--',6,'Female','2018-12-23','','Indian','Islam',NULL,'2024-06-10','1283','','','','','2025-05-05',0,'','FNPS0178',NULL,NULL,'',NULL,NULL,'',NULL,'',0,NULL,'','',2,NULL,'',NULL,NULL,0,0,0,0,'Admission','','',NULL,NULL,'','','General',0,'','','','','',0,'',NULL,'','',0,'','','','','','2024/25','2025/26'),(179,'J.ANABIYA','II--B','L.K.G--',6,'Female','2018-11-06','','Indian','Islam',NULL,'2022-06-13','971','','','','','2025-05-05',0,'','FNPS0179',NULL,NULL,'',NULL,NULL,'',NULL,'',0,NULL,'','',2,NULL,'',NULL,NULL,0,0,0,0,'Admission','','',NULL,NULL,'','','General',0,'','','','','',0,'',NULL,'','',0,'','','','','','2022/23','2025/26'),(180,'M.ASMA NAJIYA','II--B','L.K.G--',6,'Female','2018-10-13','','Indian','Islam',NULL,'2022-06-10','969','','','','','2025-05-05',0,'','FNPS0180',NULL,NULL,'',NULL,NULL,'',NULL,'',0,NULL,'','',2,NULL,'',NULL,NULL,0,0,0,0,'Admission','','',NULL,NULL,'','','General',0,'','','','','',0,'',NULL,'','',0,'','','','','','2022/23','2025/26'),(181,'DHANUBIYA SHERIN ','II--B','I--',11,'Female','2013-05-17','','Indian','',NULL,'2025-07-01','1318','','','','','2025-05-05',0,'','FNPS0181',NULL,NULL,'',NULL,NULL,'',NULL,'',0,NULL,'','',2,NULL,'',NULL,NULL,0,0,0,0,'Admission','','',NULL,NULL,'','','General',0,'','','','','',0,'',NULL,'','',0,'','','','','','2024/25','2025/26'),(182,'M.FAAZIYA RIMANA','II--B','L.K.G--',7,'Female','2018-03-21','','Indian','Islam',NULL,'2022-06-13','978','','','','','2025-05-05',0,'','FNPS0182',NULL,NULL,'',NULL,NULL,'',NULL,'',0,NULL,'','',2,NULL,'',NULL,NULL,0,0,0,0,'Admission','','',NULL,NULL,'','','General',0,'','','','','',0,'',NULL,'','',0,'','','','','','2022/23','2025/26'),(183,'S.L.FATHIMA AFRAH','II--B','L.K.G--',7,'Female','2018-01-03','','Indian','Islam',NULL,'2022-06-13','958','','','','','2025-05-05',0,'','FNPS0183',NULL,NULL,'',NULL,NULL,'',NULL,'',0,NULL,'','',2,NULL,'',NULL,NULL,0,0,0,0,'Admission','','',NULL,NULL,'','','General',0,'','','','','',0,'',NULL,'','',0,'','','','','','2022/23','2025/26'),(184,'F.M.FATHIMA HAJNA','II--B','L.K.G--',6,'Female','2018-07-31','','Indian','Islam',NULL,'2022-06-13','986','','','','','2025-05-05',0,'','FNPS0184',NULL,NULL,'',NULL,NULL,'',NULL,'',0,NULL,'','',2,NULL,'',NULL,NULL,0,0,0,0,'Admission','','',NULL,NULL,'','','General',0,'','','','','',0,'',NULL,'','',0,'','','','','','2022/23','2025/26'),(185,'M.O.FATHIMA SAMEEHA','II--B','L.K.G--',8,'Female','2017-01-22','','Indian','Islam',NULL,'2022-06-13','989','','','','','2025-05-05',0,'','FNPS0185',NULL,NULL,'',NULL,NULL,'',NULL,'',0,NULL,'','',2,NULL,'',NULL,NULL,0,0,0,0,'Admission','','',NULL,NULL,'','','General',0,'','','','','',0,'',NULL,'','',0,'','','','','','2022/23','2025/26'),(186,'FARAH HAFIYA','II--B','U.K.G--',6,'Female','2019-02-25','','Indian','Islam',NULL,'2023-06-14','1162','','','','','2025-05-05',0,'','FNPS0186',NULL,NULL,'',NULL,NULL,'',NULL,'',0,NULL,'','',2,NULL,'',NULL,NULL,0,0,0,0,'Admission','','',NULL,NULL,'','','General',0,'','','','','',0,'',NULL,'','',0,'','','','','','2023/24','2025/26'),(187,'R.HAFEEZA','II--B','L.K.G--',6,'Female','2018-09-19','','Indian','Islam',NULL,'2022-06-13','959','','','','','2025-05-05',0,'','FNPS0187',NULL,NULL,'',NULL,NULL,'',NULL,'',0,NULL,'','',2,NULL,'',NULL,NULL,0,0,0,0,'Admission','','',NULL,NULL,'','','General',0,'','','','','',0,'',NULL,'','',0,'','','','','','2022/23','2025/26'),(188,'S.I.HALEEMA SULTHANA','II--B','L.K.G--',6,'Female','2018-10-15','','Indian','Islam',NULL,'2022-06-10','968','','','','','2025-05-05',0,'','FNPS0188',NULL,NULL,'',NULL,NULL,'',NULL,'',0,NULL,'','',2,NULL,'',NULL,NULL,0,0,0,0,'Admission','','',NULL,NULL,'','','General',0,'','','','','',0,'',NULL,'','',0,'','','','','','2022/23','2025/26'),(189,'J.HAMNA','II--B','L.K.G--',7,'Female','2018-04-24','','Indian','Islam',NULL,'2022-06-13','994','','','','','2025-05-05',0,'','FNPS0189',NULL,NULL,'',NULL,NULL,'',NULL,'',0,NULL,'','',2,NULL,'',NULL,NULL,0,0,0,0,'Admission','','',NULL,NULL,'','','General',0,'','','','','',0,'',NULL,'','',0,'','','','','','2022/23','2025/26'),(190,'M.R.HAWWA RASHIKA','II--B','L.K.G--',6,'Female','2018-10-30','','Indian','Islam',NULL,'2022-06-13','993','','','','','2025-05-05',0,'','FNPS0190',NULL,NULL,'',NULL,NULL,'',NULL,'',0,NULL,'','',2,NULL,'',NULL,NULL,0,0,0,0,'Admission','','',NULL,NULL,'','','General',0,'','','','','',0,'',NULL,'','',0,'','','','','','2022/23','2025/26'),(191,'B.I.INSHIRAH','II--B','L.K.G--',7,'Female','2018-02-04','','Indian','Islam',NULL,'2022-06-13','987','','','','','2025-05-05',0,'','FNPS0191',NULL,NULL,'',NULL,NULL,'',NULL,'',0,NULL,'','',2,NULL,'',NULL,NULL,0,0,0,0,'Admission','','',NULL,NULL,'','','General',0,'','','','','',0,'',NULL,'','',0,'','','','','','2022/23','2025/26'),(192,'P.JERMISHA','II--B','L.K.G--',7,'Female','2017-10-31','','Indian','Christianity',NULL,'2022-06-13','995','','','','','2025-05-05',0,'','FNPS0192',NULL,NULL,'',NULL,NULL,'',NULL,'',0,NULL,'','',2,NULL,'',NULL,NULL,0,0,0,0,'Admission','','',NULL,NULL,'','','General',0,'','','','','',0,'',NULL,'','',0,'','','','','','2022/23','2025/26'),(193,'J.JESHMIKA','II--B','U.K.G--',6,'Female','2018-08-02','','Indian','Christianity',NULL,'2023-06-14','1138','','','','','2025-05-05',0,'','FNPS0193',NULL,NULL,'',NULL,NULL,'',NULL,'',0,NULL,'','',2,NULL,'',NULL,NULL,0,0,0,0,'Admission','','',NULL,NULL,'','','General',0,'','','','','',0,'',NULL,'','',0,'','','','','','2023/24','2025/26'),(194,'R.JEYA SAVITHA','II--B','U.K.G--',7,'Female','2018-02-03','','Indian','Hinduism',NULL,'2023-06-14','1172','','','','','2025-05-05',0,'','FNPS0194',NULL,NULL,'',NULL,NULL,'',NULL,'',0,NULL,'','',2,NULL,'',NULL,NULL,0,0,0,0,'Admission','','',NULL,NULL,'','','General',0,'','','','','',0,'',NULL,'','',0,'','','','','','2023/24','2025/26'),(195,'KATHEEJA NAFEESHA','II--B','L.K.G--',6,'Female','2018-10-30','','Indian','Islam',NULL,'2022-06-13','974','','','','','2025-05-05',0,'','FNPS0195',NULL,NULL,'',NULL,NULL,'',NULL,'',0,NULL,'','',2,NULL,'',NULL,NULL,0,0,0,0,'Admission','','',NULL,NULL,'','','General',0,'','','','','',0,'',NULL,'','',0,'','','','','','2022/23','2025/26'),(196,'KHAIRUN AMRA','II--B','L.K.G--',6,'Female','2018-05-25','','Indian','Islam',NULL,'2022-06-17','982','','','','','2025-05-05',0,'','FNPS0196',NULL,NULL,'',NULL,NULL,'',NULL,'',0,NULL,'','',2,NULL,'',NULL,NULL,0,0,0,0,'Admission','','',NULL,NULL,'','','General',0,'','','','','',0,'',NULL,'','',0,'','','','','','2022/23','2025/26'),(197,'NAFEES FATHIMA','II--B','L.K.G--',6,'Female','2018-11-05','','Indian','Islam',NULL,'2022-06-13','976','','','','','2025-05-05',0,'','FNPS0197',NULL,NULL,'',NULL,NULL,'',NULL,'',0,NULL,'','',2,NULL,'',NULL,NULL,0,0,0,0,'Admission','','',NULL,NULL,'','','General',0,'','','','','',0,'',NULL,'','',0,'','','','','','2022/23','2025/26'),(198,'NALINA SRI','II--B','L.K.G--',6,'Female','2018-07-26','','Indian','Hinduism',NULL,'2022-06-21','957','','','','','2025-05-05',0,'','FNPS0198',NULL,NULL,'',NULL,NULL,'',NULL,'',0,NULL,'','',2,NULL,'',NULL,NULL,0,0,0,0,'Admission','','',NULL,NULL,'','','General',0,'','','','','',0,'',NULL,'','',0,'','','','','','2022/23','2025/26'),(199,'S.NEGA SRI','II--B','U.K.G--',7,'Female','2018-03-09','','Indian','Hinduism',NULL,'2023-06-14','1141','','','','','2025-05-05',0,'','FNPS0199',NULL,NULL,'',NULL,NULL,'',NULL,'',0,NULL,'','',2,NULL,'',NULL,NULL,0,0,0,0,'Admission','','',NULL,NULL,'','','General',0,'','','','','',0,'',NULL,'','',0,'','','','','','2023/24','2025/26'),(200,'NISHA RUFAIDHA','II--B','L.K.G--',8,'Female','2016-08-16','','Indian','Islam',NULL,'2021-06-21','843','','','','','2025-05-05',0,'','FNPS0200',NULL,NULL,'',NULL,NULL,'',NULL,'',0,NULL,'','',2,NULL,'',NULL,NULL,0,0,0,0,'Admission','','',NULL,NULL,'','','General',0,'','','','','',0,'',NULL,'','',0,'','','','','','2021/22','2025/26'),(201,'RASHEEDHA BANU','II--B','L.K.G--',7,'Female','2018-04-28','','Indian','Islam',NULL,'2022-07-12','1085','','','','','2025-05-05',0,'','FNPS0201',NULL,NULL,'',NULL,NULL,'',NULL,'',0,NULL,'','',2,NULL,'',NULL,NULL,0,0,0,0,'Admission','','',NULL,NULL,'','','General',0,'','','','','',0,'',NULL,'','',0,'','','','','','2022/23','2025/26'),(202,'I.RISA','II--B','L.K.G--',6,'Female','2018-09-13','','Indian','Hinduism',NULL,'2022-06-13','964','','','','','2025-05-05',0,'','FNPS0202',NULL,NULL,'',NULL,NULL,'',NULL,'',0,NULL,'','',2,NULL,'',NULL,NULL,0,0,0,0,'Admission','','',NULL,NULL,'','','General',0,'','','','','',0,'',NULL,'','',0,'','','','','','2022/23','2025/26'),(203,'RUTHAIBA','II--B','L.K.G--',7,'Female','2018-02-28','','Indian','Islam',NULL,'2022-06-13','954','','','','','2025-05-05',0,'','FNPS0203',NULL,NULL,'',NULL,NULL,'',NULL,'',0,NULL,'','',2,NULL,'',NULL,NULL,0,0,0,0,'Admission','','',NULL,NULL,'','','General',0,'','','','','',0,'',NULL,'','',0,'','','','','','2022/23','2025/26'),(204,'A.S.SAFIYA','II--B','L.K.G--',6,'Female','2018-12-20','','Indian','Islam',NULL,'2022-06-13','975','','','','','2025-05-05',0,'','FNPS0204',NULL,NULL,'',NULL,NULL,'',NULL,'',0,NULL,'','',2,NULL,'',NULL,NULL,0,0,0,0,'Admission','','',NULL,NULL,'','','General',0,'','','','','',0,'',NULL,'','',0,'','','','','','2022/23','2025/26'),(205,'SALMA RIZWANA','II--B','L.K.G--',6,'Female','2018-06-12','','Indian','Islam',NULL,'2022-06-13','963','','','','','2025-05-05',0,'','FNPS0205',NULL,NULL,'',NULL,NULL,'',NULL,'',0,NULL,'','',2,NULL,'',NULL,NULL,0,0,0,0,'Admission','','',NULL,NULL,'','','General',0,'','','','','',0,'',NULL,'','',0,'','','','','','2022/23','2025/26'),(206,'U.F.SEYED ALI FATHIMA ','II--B','I--',7,'Female','2017-08-08','','Indian','Islam',NULL,'2024-06-10','1324','','','','','2025-05-05',0,'','FNPS0206',NULL,NULL,'',NULL,NULL,'',NULL,'',0,NULL,'','',2,NULL,'',NULL,NULL,0,0,0,0,'Admission','','',NULL,NULL,'','','General',0,'','','','','',0,'',NULL,'','',0,'','','','','','2024/25','2025/26'),(207,'T.THANYA SRI','II--B','L.K.G--',6,'Female','2018-07-31','','Indian','',NULL,'2023-06-14','1104','','','','','2025-05-05',0,'','FNPS0207',NULL,NULL,'',NULL,NULL,'',NULL,'',0,NULL,'','',2,NULL,'',NULL,NULL,0,0,0,0,'Admission','','',NULL,NULL,'','','General',0,'','','','','',0,'',NULL,'','',0,'','','','','','2023/24','2025/26'),(208,'THASEEFA ','II--B','I--',6,'Female','2018-11-15','','Indian','Islam',NULL,'2024-06-10','1213','','','','','2025-05-05',0,'','FNPS0208',NULL,NULL,'',NULL,NULL,'',NULL,'',0,NULL,'','',2,NULL,'',NULL,NULL,0,0,0,0,'Admission','','',NULL,NULL,'','','General',0,'','','','','',0,'',NULL,'','',0,'','','','','','2024/25','2025/26'),(209,'UMMU SALMA','II--B','L.K.G--',7,'Female','2018-01-07','','Indian','Islam',NULL,'2022-06-13','977','','','','','2025-05-05',0,'','FNPS0209',NULL,NULL,'',NULL,NULL,'',NULL,'',0,NULL,'','',2,NULL,'',NULL,NULL,0,0,0,0,'Admission','','',NULL,NULL,'','','General',0,'','','','','',0,'',NULL,'','',0,'','','','','','2022/23','2025/26'),(210,'F.AFRAH(NEW)(LEFT AFTER II TERM)','II--B','',0,'Female',NULL,NULL,'Indian',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'2025-05-05',0,NULL,'FNPS0210',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,2,NULL,NULL,NULL,NULL,0,0,0,NULL,'Admission',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,0,NULL,NULL,NULL,NULL,2,NULL,NULL,NULL,NULL,NULL,NULL,'2025/26'),(211,'A.H.AHMED HANEEF','III--A','U.K.G--',8,'Male','2017-02-02','','Indian','Islam',NULL,'2022-06-02','1003','','','','','2025-05-05',0,'','FNPS0211',NULL,NULL,'',NULL,NULL,'',NULL,'',0,NULL,'','',2,NULL,'',NULL,NULL,0,0,0,0,'Admission','','',NULL,NULL,'','','General',0,'','','','','',0,'',NULL,'','',0,'','','','','','2022/23','2025/26'),(212,'M.ANTONY AFFIK','III--A','I--',8,'Male','2016-12-21','','Indian','Christianity',NULL,'2023-06-14','1168','','','','','2025-05-05',0,'','FNPS0212',NULL,NULL,'',NULL,NULL,'',NULL,'',0,NULL,'','',2,NULL,'',NULL,NULL,0,0,0,0,'Admission','','',NULL,NULL,'','','General',0,'','','','','',0,'',NULL,'','',0,'','','','','','2023/24','2025/26'),(213,'S.I.IHSAN ABDULLA','III--A','L.K.G--',8,'Male','2016-11-25','','Indian','',NULL,'2022-06-13','923','','','','','2025-05-05',0,'','FNPS0213',NULL,NULL,'',NULL,NULL,'',NULL,'',0,NULL,'','',2,NULL,'',NULL,NULL,0,0,0,0,'Admission','','',NULL,NULL,'','','General',0,'','','','','',0,'',NULL,'','',0,'','','','','','2022/23','2025/26'),(214,'U.MITHRAN','III--A','I--',7,'Male','2017-06-13','','Indian','Hinduism',NULL,'2023-06-14','1148','','','','','2025-05-05',0,'','FNPS0214',NULL,NULL,'',NULL,NULL,'',NULL,'',0,NULL,'','',2,NULL,'',NULL,NULL,0,0,0,0,'Admission','','',NULL,NULL,'','','General',0,'','','','','',0,'',NULL,'','',0,'','','','','','2023/24','2025/26'),(215,'T.A.MOHAMED ARSHAD ','III--A','U.K.G--',8,'Male','2017-01-12','','Indian','Islam',NULL,'2022-06-16','998','','','','','2025-05-05',0,'','FNPS0215',NULL,NULL,'',NULL,NULL,'',NULL,'',0,NULL,'','',2,NULL,'',NULL,NULL,0,0,0,0,'Admission','','',NULL,NULL,'','','General',0,'','','','','',0,'',NULL,'','',0,'','','','','','2022/23','2025/26'),(216,'S.I.MOHAMED HAKIM','III--A','U.K.G--',8,'Male','2016-12-22','','Indian','Islam',NULL,'2022-06-13','1001','','','','','2025-05-05',0,'','FNPS0216',NULL,NULL,'',NULL,NULL,'',NULL,'',0,NULL,'','',2,NULL,'',NULL,NULL,0,0,0,0,'Admission','','',NULL,NULL,'','','General',0,'','','','','',0,'',NULL,'','',0,'','','','','','2022/23','2025/26'),(217,'P.MOHAMED SAMEER','III--A','U.K.G--',7,'Male','2017-10-04','','Indian','Islam',NULL,'2022-06-16','1021','','','','','2025-05-05',0,'','FNPS0217',NULL,NULL,'',NULL,NULL,'',NULL,'',0,NULL,'','',2,NULL,'',NULL,NULL,0,0,0,0,'Admission','','',NULL,NULL,'','','General',0,'','','','','',0,'',NULL,'','',0,'','','','','','2022/23','2025/26'),(218,'J.A.MOHAMED SHAFIN','III--A','L.K.G--',7,'Male','2017-05-31','','Indian','Islam',NULL,'2021-06-30','826','','','','','2025-05-05',0,'','FNPS0218',NULL,NULL,'',NULL,NULL,'',NULL,'',0,NULL,'','',2,NULL,'',NULL,NULL,0,0,0,0,'Admission','','',NULL,NULL,'','','General',0,'','','','','',0,'',NULL,'','',0,'','','','','','2021/22','2025/26'),(219,'S.M.MOHAMED ZAHIRUL','III--A','U.K.G--',7,'Male','2017-09-22','','Indian','Islam',NULL,'2022-06-13','1083','','','','','2025-05-05',0,'','FNPS0219',NULL,NULL,'',NULL,NULL,'',NULL,'',0,NULL,'','',2,NULL,'',NULL,NULL,0,0,0,0,'Admission','','',NULL,NULL,'','','General',0,'','','','','',0,'',NULL,'','',0,'','','','','','2022/23','2025/26'),(220,'S.S.MOHAMMED NAWAS','III--A','U.K.G--',8,'Male','2016-08-09','','Indian','Islam',NULL,'2022-06-10','1008','','','','','2025-05-05',0,'','FNPS0220',NULL,NULL,'',NULL,NULL,'',NULL,'',0,NULL,'','',2,NULL,'',NULL,NULL,0,0,0,0,'Admission','','',NULL,NULL,'','','General',0,'','','','','',0,'',NULL,'','',0,'','','','','','2022/23','2025/26'),(221,'S.MOHAMED SUHAIB ','III--A','L.K.G--',7,'Male','2017-09-26','','Indian','Islam',NULL,'2022-02-23','841','','','','','2025-05-05',0,'','FNPS0221',NULL,NULL,'',NULL,NULL,'',NULL,'',0,NULL,'','',2,NULL,'',NULL,NULL,0,0,0,0,'Admission','','',NULL,NULL,'','','General',0,'','','','','',0,'',NULL,'','',0,'','','','','','2021/22','2025/26'),(222,'R.RIYOSON','III--A','U.K.G--',7,'Male','2017-11-09','','Indian','Christianity',NULL,'2022-06-06','1006','','','','','2025-05-05',0,'','FNPS0222',NULL,NULL,'',NULL,NULL,'',NULL,'',0,NULL,'','',2,NULL,'',NULL,NULL,0,0,0,0,'Admission','','',NULL,NULL,'','','General',0,'','','','','',0,'',NULL,'','',0,'','','','','','2021/22','2025/26'),(223,'H.M.R.SEYED AHMAD','III--A','I--',7,'Male','2017-10-27','','Indian','Islam',NULL,'2023-06-14','1110','','','','','2025-05-05',0,'','FNPS0223',NULL,NULL,'',NULL,NULL,'',NULL,'',0,NULL,'','',2,NULL,'',NULL,NULL,0,0,0,0,'Admission','','',NULL,NULL,'','','General',0,'','','','','',0,'',NULL,'','',0,'','','','','','2023/24','2025/26'),(224,'T.SHAKTHI VEL','III--A','II--',8,'Male','2016-08-12','','Indian','Hinduism',NULL,'2024-06-18','1310','','','','','2025-05-05',0,'','FNPS0224',NULL,NULL,'',NULL,NULL,'',NULL,'',0,NULL,'','',2,NULL,'',NULL,NULL,0,0,0,0,'Admission','','',NULL,NULL,'','','General',0,'','','','','',0,'',NULL,'','',0,'','','','','','2024/25','2025/26'),(225,'S.N.IFHAM LEFT ON 31/05/21','III--A','',0,'Male',NULL,'','Indian','',NULL,NULL,'678','','','','','2025-05-05',0,'','FNPS0225',NULL,NULL,'',NULL,NULL,'',NULL,'',0,NULL,'','',2,NULL,'',NULL,NULL,0,0,0,0,'Admission','','',NULL,NULL,'','','General',0,'','','','','',0,'',NULL,'','',0,'','','','','','','2025/26'),(226,'S.AFROSE FATHIMA','III--B','L.K.G--',7,'Female','2017-08-05','','Indian','Islam',NULL,'2021-10-21','858','','','','','2025-05-05',0,'','FNPS0226',NULL,NULL,'',NULL,NULL,'',NULL,'',0,NULL,'','',2,NULL,'',NULL,NULL,0,0,0,0,'Admission','','',NULL,NULL,'','','General',0,'','','','','',0,'',NULL,'','',0,'','','','','','2021/22','2025/26'),(227,'AHAMAD SAHIB NACHI','III--B','L.K.G--',8,'Female','2017-01-18','','Indian','Islam',NULL,'2021-07-13','856','','','','','2025-05-05',0,'','FNPS0227',NULL,NULL,'',NULL,NULL,'',NULL,'',0,NULL,'','',2,NULL,'',NULL,NULL,0,0,0,0,'Admission','','',NULL,NULL,'','','General',0,'','','','','',0,'',NULL,'','',0,'','','','','','2021/22','2025/26'),(228,'C.AKSHAYA','III--B','U.K.G--',8,'Female','2017-01-04','','Indian','Hinduism',NULL,'2022-06-13','1036','','','','','2025-05-05',0,'','FNPS0228',NULL,NULL,'',NULL,NULL,'',NULL,'',0,NULL,'','',2,NULL,'',NULL,NULL,0,0,0,0,'Admission','','',NULL,NULL,'','','General',0,'','','','','',0,'',NULL,'','',0,'','','','','','2022/23','2025/26'),(229,'M.I.M.AYESHA','III--B','U.K.G--',7,'Female','2017-05-24','','Indian','Hinduism',NULL,'2022-06-13','1095','','','','','2025-05-05',0,'','FNPS0229',NULL,NULL,'',NULL,NULL,'',NULL,'',0,NULL,'','',2,NULL,'',NULL,NULL,0,0,0,0,'Admission','','',NULL,NULL,'','','General',0,'','','','','',0,'',NULL,'','',0,'','','','','','2022/23','2025/26'),(230,'S.D.AYSHA ATHIFA','III--B','L.K.G--',8,'Female','2016-08-28','','Indian','Islam',NULL,'2021-07-06','849','','','','','2025-05-05',0,'','FNPS0230',NULL,NULL,'',NULL,NULL,'',NULL,'',0,NULL,'','',2,NULL,'',NULL,NULL,0,0,0,0,'Admission','','',NULL,NULL,'','','General',0,'','','','','',0,'',NULL,'','',0,'','','','','','2021/22','2025/26'),(231,'H.AYSHA SHAREEFA','III--B','L.K.G--',7,'Female','2017-09-10','','Indian','Islam',NULL,'2022-06-30','991','','','','','2025-05-05',0,'','FNPS0231',NULL,NULL,'',NULL,NULL,'',NULL,'',0,NULL,'','',2,NULL,'',NULL,NULL,0,0,0,0,'Admission','','',NULL,NULL,'','','General',0,'','','','','',0,'',NULL,'','',0,'','','','','','2022/23','2025/26'),(232,'A.DIYANA','III--B','U.K.G--',7,'Female','2017-06-01','','Indian','Islam',NULL,'2022-06-13','1042','','','','','2025-05-05',0,'','FNPS0232',NULL,NULL,'',NULL,NULL,'',NULL,'',0,NULL,'','',2,NULL,'',NULL,NULL,0,0,0,0,'Admission','','',NULL,NULL,'','','General',0,'','','','','',0,'',NULL,'','',0,'','','','','','2022/23','2025/26'),(233,'M.A.FATHIMA RAIHANA','III--B','L.K.G--',7,'Female','2017-10-30','','Indian','Islam',NULL,'2021-07-30','851','','','','','2025-05-05',0,'','FNPS0233',NULL,NULL,'',NULL,NULL,'',NULL,'',0,NULL,'','',2,NULL,'',NULL,NULL,0,0,0,0,'Admission','','',NULL,NULL,'','','General',0,'','','','','',0,'',NULL,'','',0,'','','','','','2021/22','2025/26'),(234,'A.K.FATHIMA YUSHRA','III--B','L.K.G--',7,'Female','2017-07-17','','Indian','Islam',NULL,NULL,'857','','','','','2025-05-05',0,'','FNPS0234',NULL,NULL,'',NULL,NULL,'',NULL,'',0,NULL,'','',2,NULL,'',NULL,NULL,0,0,0,0,'Admission','','',NULL,NULL,'','','General',0,'','','','','',0,'',NULL,'','',0,'','','','','','2021/22','2025/26'),(235,'I.M.HABEEBA MAZHAFIRA','III--B','U.K.G--',8,'Female','2017-04-01','','Indian','Islam',NULL,'2022-06-07','1028','','','','','2025-05-05',0,'','FNPS0235',NULL,NULL,'',NULL,NULL,'',NULL,'',0,NULL,'','',2,NULL,'',NULL,NULL,0,0,0,0,'Admission','','',NULL,NULL,'','','General',0,'','','','','',0,'',NULL,'','',0,'','','','','','2022/23','2025/26'),(236,'S.HAWWA RAMEESHA','III--B','U.K.G--',7,'Female','2017-05-23','','Indian','Islam',NULL,'2022-06-13','1027','','','','','2025-05-05',0,'','FNPS0236',NULL,NULL,'',NULL,NULL,'',NULL,'',0,NULL,'','',2,NULL,'',NULL,NULL,0,0,0,0,'Admission','','',NULL,NULL,'','','General',0,'','','','','',0,'',NULL,'','',0,'','','','','','2022/23','2025/26'),(237,'M.I.JANNATHUL AADHILA','III--B','U.K.G--',7,'Female','2017-06-29','','Indian','Islam',NULL,'2022-06-13','1023','','','','','2025-05-05',0,'','FNPS0237',NULL,NULL,'',NULL,NULL,'',NULL,'',0,NULL,'','',2,NULL,'',NULL,NULL,0,0,0,0,'Admission','','',NULL,NULL,'','','General',0,'','','','','',0,'',NULL,'','',0,'','','','','','2022/23','2025/26'),(238,'J.JIYARAH ','III--B','II--',0,'Female',NULL,'','Indian','Islam',NULL,'2024-06-12','1292','','','','','2025-05-05',0,'','FNPS0238',NULL,NULL,'',NULL,NULL,'',NULL,'',0,NULL,'','',2,NULL,'',NULL,NULL,0,0,0,0,'Admission','','',NULL,NULL,'','','General',0,'','','','','',0,'',NULL,'','',0,'','','','','','2024/25','2025/26'),(239,'M.M.B.MASOONA','III--B','L.K.G--',8,'Female','2017-01-14','','Indian','Islam',NULL,'2021-08-23','852','','','','','2025-05-05',0,'','FNPS0239',NULL,NULL,'',NULL,NULL,'',NULL,'',0,NULL,'','',2,NULL,'',NULL,NULL,0,0,0,0,'Admission','','',NULL,NULL,'','','General',0,'','','','','',0,'',NULL,'','',0,'','','','','','2021/22','2025/26'),(240,'N.NAMEERA','III--B','',7,'Female','2017-11-25','','Indian','Islam',NULL,'2022-06-13','1026','','','','','2025-05-05',0,'','FNPS0240',NULL,NULL,'',NULL,NULL,'',NULL,'',0,NULL,'','',2,NULL,'',NULL,NULL,0,0,0,0,'Admission','','',NULL,NULL,'','','General',0,'','','','','',0,'',NULL,'','',0,'','','','','','2022/23','2025/26'),(241,'M.A.K.NOOR HANIYA ','III--B','U.K.G--',8,'Female','2017-03-31','','Indian','Islam',NULL,'2022-06-14','1037','','','','','2025-05-05',0,'','FNPS0241',NULL,NULL,'',NULL,NULL,'',NULL,'',0,NULL,'','',2,NULL,'',NULL,NULL,0,0,0,0,'Admission','','',NULL,NULL,'','','General',0,'','','','','',0,'',NULL,'','',0,'','','','','','2022/23','2025/26'),(242,'M.RAKSHANA ','III--B','II--',7,'Female','2017-05-30','','Indian','Hinduism',NULL,'2024-06-14','1304','','','','','2025-05-05',0,'','FNPS0242',NULL,NULL,'',NULL,NULL,'',NULL,'',0,NULL,'','',2,NULL,'',NULL,NULL,0,0,0,0,'Admission','','',NULL,NULL,'','','General',0,'','','','','',0,'',NULL,'','',0,'','','','','','2024/25','2025/26'),(243,'M.R.RASEENA BEEVI','III--B','L.K.G--',7,'Female','2017-08-15','','Indian','Islam',NULL,'2022-03-16','906','','','','','2025-05-05',0,'','FNPS0243',NULL,NULL,'',NULL,NULL,'',NULL,'',0,NULL,'','',2,NULL,'',NULL,NULL,0,0,0,0,'Admission','','',NULL,NULL,'','','General',0,'','','','','',0,'',NULL,'','',0,'','','','','','2021/22','2025/26'),(244,'A.RIHANA','III--B','U.K.G--',7,'Female','2017-07-18','','Indian','Islam',NULL,'2022-06-21','1041','','','','','2025-05-05',0,'','FNPS0244',NULL,NULL,'',NULL,NULL,'',NULL,'',0,NULL,'','',2,NULL,'',NULL,NULL,0,0,0,0,'Admission','','',NULL,NULL,'','','General',0,'','','','','',0,'',NULL,'','',0,'','','','','','2022/23','2025/26'),(245,'S.SANCIA','III--B','U.K.G--',7,'Female','2017-09-08','','Indian','',NULL,'0022-06-13','1022','','','','','2025-05-05',0,'','FNPS0245',NULL,NULL,'',NULL,NULL,'',NULL,'',0,NULL,'','',2,NULL,'',NULL,NULL,0,0,0,0,'Admission','','',NULL,NULL,'','','General',0,'','','','','',0,'',NULL,'','',0,'','','','','','2022/23','2025/26'),(246,'A.SUWATHIKA','III--B','U.K.G--',7,'Female','2017-08-11','','Indian','',NULL,'2022-06-13','1044','','','','','2025-05-05',0,'','FNPS0246',NULL,NULL,'',NULL,NULL,'',NULL,'',0,NULL,'','',2,NULL,'',NULL,NULL,0,0,0,0,'Admission','','',NULL,NULL,'','','General',0,'','','','','',0,'',NULL,'','',0,'','','','','','2022/23','2025/26'),(247,'S.UMMU REGINA','III--B','L.K.G--',7,'Female','2017-08-01','','Indian','Islam',NULL,'2021-08-03','854','','','','','2025-05-05',0,'','FNPS0247',NULL,NULL,'',NULL,NULL,'',NULL,'',0,NULL,'','',2,NULL,'',NULL,NULL,0,0,0,0,'Admission','','',NULL,NULL,'','','General',0,'','','','','',0,'',NULL,'','',0,'','','','','','2021/22','2025/26'),(248,'N.S.AHAMED RASHADH','IV--A','I--',9,'Male','2015-12-09','','Indian','Islam',NULL,'2022-06-13','1052','','','','','2025-05-05',0,'','FNPS0248',NULL,NULL,'',NULL,NULL,'',NULL,'',0,NULL,'','',2,NULL,'',NULL,NULL,0,0,0,0,'Admission','','',NULL,NULL,'','','General',0,'','','','','',0,'',NULL,'','',0,'','','','','','2022/23','2025/26'),(249,'T.KASSIM MARZOOK','IV--A','II--',9,'Male','2016-04-06','','Indian','Islam',NULL,'2023-08-04','1200','','','','','2025-05-05',0,'','FNPS0249',NULL,NULL,'',NULL,NULL,'',NULL,'',0,NULL,'','',2,NULL,'',NULL,NULL,0,0,0,0,'Admission','','',NULL,NULL,'','','General',0,'','','','','',0,'',NULL,'','',0,'','','','','','2023/24','2025/26'),(250,'S.N.KITHURU MUGAMADH','IV--A','U.K.G--',8,'Male','2016-07-28','','Indian','Islam',NULL,'2021-07-05','867','','','','','2025-05-05',0,'','FNPS0250',NULL,NULL,'',NULL,NULL,'',NULL,'',0,NULL,'','',2,NULL,'',NULL,NULL,0,0,0,0,'Admission','','',NULL,NULL,'','','General',0,'','','','','',0,'',NULL,'','',0,'','','','','','2021/22','2025/26'),(251,'J.A.MOHAMED MUKSHID','IV--A','U.K.G--',8,'Male','2016-10-11','','Indian','Islam',NULL,'2021-07-05','868','','','','','2025-05-05',0,'','FNPS0251',NULL,NULL,'',NULL,NULL,'',NULL,'',0,NULL,'','',2,NULL,'',NULL,NULL,0,0,0,0,'Admission','','',NULL,NULL,'','','General',0,'','','','','',0,'',NULL,'','',0,'','','','','','2021/22','2025/26'),(252,'S.MOHAMED INZAMAM (NEW)','IV--A','III--',9,'Male','2015-09-17','','Indian','Islam',NULL,'2024-06-10','1255','','','','','2025-05-05',0,'','FNPS0252',NULL,NULL,'',NULL,NULL,'',NULL,'',0,NULL,'','',2,NULL,'',NULL,NULL,0,0,0,0,'Admission','','',NULL,NULL,'','','General',0,'','','','','',0,'',NULL,'','',0,'','','','','','2024/25','2025/26'),(253,'A.M.MOHAMED HUNAIF','IV--A','I--',8,'Male','2016-06-06','','Indian','Islam',NULL,'2022-06-13','1047','','','','','2025-05-05',0,'','FNPS0253',NULL,NULL,'',NULL,NULL,'',NULL,'',0,NULL,'','',2,NULL,'',NULL,NULL,0,0,0,0,'Admission','','',NULL,NULL,'','','General',0,'','','','','',0,'',NULL,'','',0,'','','','','','2022/23','2025/26'),(254,'Y.MOHAMED ATHEEQ','IV--A','I--',8,'Male','2016-08-26','','Indian','Islam',NULL,'2022-06-13','1045','','','','','2025-05-05',0,'','FNPS0254',NULL,NULL,'',NULL,NULL,'',NULL,'',0,NULL,'','',2,NULL,'',NULL,NULL,0,0,0,0,'Admission','','',NULL,NULL,'','','General',0,'','','','','',0,'',NULL,'','',0,'','','','','','2022/23','2025/26'),(255,'J.A.MOHAMED AFNAN','IV--A','U.K.G--',8,'Male','2017-01-17','','Indian','',NULL,'2022-02-21','877','','','','','2025-05-05',0,'','FNPS0255',NULL,NULL,'',NULL,NULL,'',NULL,'',0,NULL,'','',2,NULL,'',NULL,NULL,0,0,0,0,'Admission','','',NULL,NULL,'','','General',0,'','','','','',0,'',NULL,'','',0,'','','','','','2021/22','2025/26'),(256,'M.MOHAMED SAABIR','IV--A','L.K.G--',9,'Male','2016-04-17','','Indian','Islam',NULL,'2021-06-25','865','','','','','2025-05-05',0,'','FNPS0256',NULL,NULL,'',NULL,NULL,'',NULL,'',0,NULL,'','',2,NULL,'',NULL,NULL,0,0,0,0,'Admission','','',NULL,NULL,'','','General',0,'','','','','',0,'',NULL,'','',0,'','','','','','2021/22','2025/26'),(257,'J.S.MOHAMMED IQBAL','IV--A','I--',8,'Male','2016-05-24','','Indian','',NULL,'2022-06-13','1046','','','','','2025-05-05',0,'','FNPS0257',NULL,NULL,'',NULL,NULL,'',NULL,'',0,NULL,'','',2,NULL,'',NULL,NULL,0,0,0,0,'Admission','','',NULL,NULL,'','','General',0,'','','','','',0,'',NULL,'','',0,'','','','','','2022/23','2025/26'),(258,'R.REBISON','IV--A','II--',8,'Male','2016-11-03','','Indian','Christianity',NULL,'2023-11-28','1202','','','','','2025-05-05',0,'','FNPS0258',NULL,NULL,'',NULL,NULL,'',NULL,'',0,NULL,'','',2,NULL,'',NULL,NULL,0,0,0,0,'Admission','','',NULL,NULL,'','','General',0,'','','','','',0,'',NULL,'','',0,'','','','','','2023/24','2025/26'),(259,'C.RISWIN','IV--A','II--',8,'Male','2016-11-10','','Indian','Christianity',NULL,'2023-06-14','1180','','','','','2025-05-05',0,'','FNPS0259',NULL,NULL,'',NULL,NULL,'',NULL,'',0,NULL,'','',2,NULL,'',NULL,NULL,0,0,0,0,'Admission','','',NULL,NULL,'','','General',0,'','','','','',0,'',NULL,'','',0,'','','','','','2023/24','2025/26'),(260,'S.M.SHEIK YAASEEN','IV--A','L.K.G--',8,'Male','2016-07-20','','Indian','Islam',NULL,'2021-07-13','881','','','','','2025-05-05',0,'','FNPS0260',NULL,NULL,'',NULL,NULL,'',NULL,'',0,NULL,'','',2,NULL,'',NULL,NULL,0,0,0,0,'Admission','','',NULL,NULL,'','','General',0,'','','','','',0,'',NULL,'','',0,'','','','','','2021/22','2025/26'),(261,'M.SHAIK ABDUL BUHARI ','IV--A','II--',9,'Male','2015-08-08','','Indian','Islam',NULL,'2023-07-10','1196','','','','','2025-05-05',0,'','FNPS0261',NULL,NULL,'',NULL,NULL,'',NULL,'',0,NULL,'','',2,NULL,'',NULL,NULL,0,0,0,0,'Admission','','',NULL,NULL,'','','General',0,'','','','','',0,'',NULL,'','',0,'','','','','','2023/24','2025/26'),(262,'J.STHEVAN','IV--A','II--',8,'Male','2017-02-14','','Indian','Christianity',NULL,'2023-06-19','1185','','','','','2025-05-05',0,'','FNPS0262',NULL,NULL,'',NULL,NULL,'',NULL,'',0,NULL,'','',2,NULL,'',NULL,NULL,0,0,0,0,'Admission','','',NULL,NULL,'','','General',0,'','','','','',0,'',NULL,'','',0,'','','','','','2023/24','2025/26'),(263,'M.A.K.UMAR ABDUL CADER ','IV--A','III--',10,'Male','2014-10-16','','Indian','Islam',NULL,'2024-06-10','1326','','','','','2025-05-05',0,'','FNPS0263',NULL,NULL,'',NULL,NULL,'',NULL,'',0,NULL,'','',2,NULL,'',NULL,NULL,0,0,0,0,'Admission','','',NULL,NULL,'','','General',0,'','','','','',0,'',NULL,'','',0,'','','','','','2024/25','2025/26'),(264,'S.I.AHAMED HALEEMA','IV--B','I--',9,'Female','2015-11-04','','Indian','Islam',NULL,'2021-08-04','796','','','','','2025-05-05',0,'','FNPS0264',NULL,NULL,'',NULL,NULL,'',NULL,'',0,NULL,'','',2,NULL,'',NULL,NULL,0,0,0,0,'Admission','','',NULL,NULL,'','','General',0,'','','','','',0,'',NULL,'','',0,'','','','','','2021/22','2025/26'),(265,'R.ANTONY VARSHIGA','IV--B','I--',8,'Female','2016-12-14','','Indian','Christianity',NULL,'2022-06-02','1063','','','','','2025-05-05',0,'','FNPS0265',NULL,NULL,'',NULL,NULL,'',NULL,'',0,NULL,'','',2,NULL,'',NULL,NULL,0,0,0,0,'Admission','','',NULL,NULL,'','','General',0,'','','','','',0,'',NULL,'','',0,'','','','','','2022/23','2025/26'),(266,'S.ANNFA IRANZU','IV--B','I--',8,'Female','2017-03-03','','Indian','Christianity',NULL,'2022-06-13','1048','','','','','2025-05-05',0,'','FNPS0266',NULL,NULL,'',NULL,NULL,'',NULL,'',0,NULL,'','',2,NULL,'',NULL,NULL,0,0,0,0,'Admission','','',NULL,NULL,'','','General',0,'','','','','',0,'',NULL,'','',0,'','','','','','2022/23','2025/26'),(267,'N.AYSHA MUBASSARA','IV--B','I--',8,'Female','2016-09-09','','Indian','Islam',NULL,'2022-06-13','1064','','','','','2025-05-05',0,'','FNPS0267',NULL,NULL,'',NULL,NULL,'',NULL,'',0,NULL,'','',2,NULL,'',NULL,NULL,0,0,0,0,'Admission','','',NULL,NULL,'','','General',0,'','','','','',0,'',NULL,'','',0,'','','','','','2022/23','2025/26'),(268,'B.AYNUL MARLIYA ','IV--B','U.K.G--',8,'Female','2016-06-13','','Indian','Islam',NULL,'2021-07-13','885','','','','','2025-05-05',0,'','FNPS0268',NULL,NULL,'',NULL,NULL,'',NULL,'',0,NULL,'','',2,NULL,'',NULL,NULL,0,0,0,0,'Admission','','',NULL,NULL,'','','General',0,'','','','','',0,'',NULL,'','',0,'','','','','','2021/22','2025/26'),(269,'M.BLESSIYA','IV--B','II--',8,'Female','2016-07-02','','Indian','Christianity',NULL,'2023-06-14','1167','','','','','2025-05-05',0,'','FNPS0269',NULL,NULL,'',NULL,NULL,'',NULL,'',0,NULL,'','',2,NULL,'',NULL,NULL,0,0,0,0,'Admission','','',NULL,NULL,'','','General',0,'','','','','',0,'',NULL,'','',0,'','','','','','2023/24','2025/26'),(270,'S.I.FATHIMA HASNA','IV--B','L.K.G--',9,'Female','2015-07-17','','Indian','',NULL,'2019-06-10','669','','','','','2025-05-05',0,'','FNPS0270',NULL,NULL,'',NULL,NULL,'',NULL,'',0,NULL,'','',2,NULL,'',NULL,NULL,0,0,0,0,'Admission','','',NULL,NULL,'','','General',0,'','','','','',0,'',NULL,'','',0,'','','','','','2019/20','2025/26'),(271,'T.A.FATHIMA HAFSA','IV--B','L.K.G--',8,'Female','2016-08-09','','Indian','',NULL,'2020-12-21','890','','','','','2025-05-05',0,'','FNPS0271',NULL,NULL,'',NULL,NULL,'',NULL,'',0,NULL,'','',2,NULL,'',NULL,NULL,0,0,0,0,'Admission','','',NULL,NULL,'','','General',0,'','','','','',0,'',NULL,'','',0,'','','','','','2020/21','2025/26'),(272,'H.I.FAWZIYA LIYANA','IV--B','U.K.G--',8,'Female','2016-11-04','','Indian','Islam',NULL,'2022-06-13','1039','','','','','2025-05-05',0,'','FNPS0272',NULL,NULL,'',NULL,NULL,'',NULL,'',0,NULL,'','',2,NULL,'',NULL,NULL,0,0,0,0,'Admission','','',NULL,NULL,'','','General',0,'','','','','',0,'',NULL,'','',0,'','','','','','2022/23','2025/26'),(273,'M.A.K.FATHIMA ZAHRA','IV--B','I--',8,'Female','2016-07-13','','Indian','Islam',NULL,'2022-07-13','1094','','','','','2025-05-05',0,'','FNPS0273',NULL,NULL,'',NULL,NULL,'',NULL,'',0,NULL,'','',2,NULL,'',NULL,NULL,0,0,0,0,'Admission','','',NULL,NULL,'','','General',0,'','','','','',0,'',NULL,'','',0,'','','','','','2022/23','2025/26'),(274,'M.H.R.HANIYA','IV--B','U.K.G--',8,'Female','2017-03-20','','Indian','',NULL,'2021-11-02','900','','','','','2025-05-05',0,'','FNPS0274',NULL,NULL,'',NULL,NULL,'',NULL,'',0,NULL,'','',2,NULL,'',NULL,NULL,0,0,0,0,'Admission','','',NULL,NULL,'','','General',0,'','','','','',0,'',NULL,'','',0,'','','','','','2021/22','2025/26'),(275,'K.HARINI','IV--B','I--',8,'Female','2016-05-24','','Indian','Hinduism',NULL,'2021-10-13','795','','','','','2025-05-05',0,'','FNPS0275',NULL,NULL,'',NULL,NULL,'',NULL,'',0,NULL,'','',2,NULL,'',NULL,NULL,0,0,0,0,'Admission','','',NULL,NULL,'','','General',0,'','','','','',0,'',NULL,'','',0,'','','','','','2021/22','2025/26'),(276,'R.HALIMA HANAH','IV--B','I--',8,'Female','2016-11-23','','Indian','Islam',NULL,'2022-06-13','1057','','','','','2025-05-05',0,'','FNPS0276',NULL,NULL,'',NULL,NULL,'',NULL,'',0,NULL,'','',2,NULL,'',NULL,NULL,0,0,0,0,'Admission','','',NULL,NULL,'','','General',0,'','','','','',0,'',NULL,'','',0,'','','','','','2022/23','2025/26'),(277,'P.JANANI JOSHIKA','IV--B','L.K.G--',8,'Female','2016-08-16','','Indian','Hinduism',NULL,'2020-08-18','888','','','','','2025-05-05',0,'','FNPS0277',NULL,NULL,'',NULL,NULL,'',NULL,'',0,NULL,'','',2,NULL,'',NULL,NULL,0,0,0,0,'Admission','','',NULL,NULL,'','','General',0,'','','','','',0,'',NULL,'','',0,'','','','','','2020/21','2025/26'),(278,'M.JEFINA','IV--B','II--',9,'Female','2016-04-01','','Indian','Christianity',NULL,'2023-06-14','1166','','','','','2025-05-05',0,'','FNPS0278',NULL,NULL,'',NULL,NULL,'',NULL,'',0,NULL,'','',2,NULL,'',NULL,NULL,0,0,0,0,'Admission','','',NULL,NULL,'','','General',0,'','','','','',0,'',NULL,'','',0,'','','','','','2023/24','2025/26'),(279,'B.JENIFER ','IV--B','III--',8,'Female','2017-03-31','','Indian','Hinduism',NULL,'2024-06-11','1301','','','','','2025-05-05',0,'','FNPS0279',NULL,NULL,'',NULL,NULL,'',NULL,'',0,NULL,'','',2,NULL,'',NULL,NULL,0,0,0,0,'Admission','','',NULL,NULL,'','','General',0,'','','','','',0,'',NULL,'','',0,'','','','','','2024/25','2025/26'),(280,'M.O.KADHEEJA RUSHDHA','IV--B','I--',9,'Female','2015-12-11','','Indian','Islam',NULL,'2022-06-13','1060','','','','','2025-05-05',0,'','FNPS0280',NULL,NULL,'',NULL,NULL,'',NULL,'',0,NULL,'','',2,NULL,'',NULL,NULL,0,0,0,0,'Admission','','',NULL,NULL,'','','General',0,'','','','','',0,'',NULL,'','',0,'','','','','','2022/23','2025/26'),(281,'V.MARIYA KIROSLIN','IV--B','I--',8,'Female','2017-02-24','','Indian','Christianity',NULL,'2022-06-02','1055','','','','','2025-05-05',0,'','FNPS0281',NULL,NULL,'',NULL,NULL,'',NULL,'',0,NULL,'','',2,NULL,'',NULL,NULL,0,0,0,0,'Admission','','',NULL,NULL,'','','General',0,'','','','','',0,'',NULL,'','',0,'','','','','','2022/23','2025/26'),(282,'A.S.NUFAIZA','IV--B','I--',8,'Female','2016-08-29','','Indian','Islam',NULL,'2022-06-13','1058','','','','','2025-05-05',0,'','FNPS0282',NULL,NULL,'',NULL,NULL,'',NULL,'',0,NULL,'','',2,NULL,'',NULL,NULL,0,0,0,0,'Admission','','',NULL,NULL,'','','General',0,'','','','','',0,'',NULL,'','',0,'','','','','','2022/23','2025/26'),(283,'N.A.SEYED RABIYATH UMMAL','IV--B','I--',9,'Female','2015-10-13','','Indian','Islam',NULL,'2022-06-02','1054','','','','','2025-05-05',0,'','FNPS0283',NULL,NULL,'',NULL,NULL,'',NULL,'',0,NULL,'','',2,NULL,'',NULL,NULL,0,0,0,0,'Admission','','',NULL,NULL,'','','General',0,'','','','','',0,'',NULL,'','',0,'','','','','','2022/23','2025/26'),(284,'J.THAHIRA ASMA','IV--B','I--',9,'Female','2016-01-10','','Indian','Islam',NULL,'2022-06-19','1061','','','','','2025-05-05',0,'','FNPS0284',NULL,NULL,'',NULL,NULL,'',NULL,'',0,NULL,'','',2,NULL,'',NULL,NULL,0,0,0,0,'Admission','','',NULL,NULL,'','','General',0,'','','','','',0,'',NULL,'','',0,'','','','','','2022/23','2025/26'),(285,'S.THANZEELA RAHEMA ','IV--B','III--',9,'Female','2016-02-21','','Indian','Islam',NULL,'2024-06-10','1214','','','','','2025-05-05',0,'','FNPS0285',NULL,NULL,'',NULL,NULL,'',NULL,'',0,NULL,'','',2,NULL,'',NULL,NULL,0,0,0,0,'Admission','','',NULL,NULL,'','','General',0,'','','','','',0,'',NULL,'','',0,'','','','','','2024/25','2025/26'),(286,'A.K.ZEENATH NOWFA','IV--B','I--',8,'Female','2016-06-28','','Indian','Islam',NULL,'2022-06-13','1062','','','','','2025-05-05',0,'','FNPS0286',NULL,NULL,'',NULL,NULL,'',NULL,'',0,NULL,'','',2,NULL,'',NULL,NULL,0,0,0,0,'Admission','','',NULL,NULL,'','','General',0,'','','','','',0,'',NULL,'','',0,'','','','','','2022/23','2025/26'),(287,'S.A.MOHAMMED HAFILA (NEW) LEFT AFTER I TERM','IV--B','',0,'Female',NULL,NULL,'Indian',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'2025-05-05',0,NULL,'FNPS0287',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,2,NULL,NULL,NULL,NULL,0,0,0,NULL,'Admission',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,0,NULL,NULL,NULL,NULL,2,NULL,NULL,NULL,NULL,NULL,NULL,'2025/26'),(288,'I.AAFEQ','V--A','I--',10,'Male','2014-12-09','','Indian','Islam',NULL,'2021-08-31','789','','','','','2025-05-05',0,'','FNPS0288',NULL,NULL,'',NULL,NULL,'',NULL,'',0,NULL,'','',2,NULL,'',NULL,NULL,0,0,0,0,'Admission','','',NULL,NULL,'','','General',0,'','','','','',0,'',NULL,'','',0,'','','','','','2021/22','2025/26'),(289,'M.M.AALIF NOORDEEN ','V--A','L.K.G--',10,'Male','2014-12-03','','Indian','Islam',NULL,'2019-06-10','679','','','','','2025-05-05',0,'','FNPS0289',NULL,NULL,'',NULL,NULL,'',NULL,'',0,NULL,'','',2,NULL,'',NULL,NULL,0,0,0,0,'Admission','','',NULL,NULL,'','','General',0,'','','','','',0,'',NULL,'','',0,'','','','','','2019/20','2025/26'),(290,'A.A.AATHIL','V--A','I--',9,'Male','2016-03-12','','Indian','Islam',NULL,'2021-08-04','791','','','','','2025-05-05',0,'','FNPS0290',NULL,NULL,'',NULL,NULL,'',NULL,'',0,NULL,'','',2,NULL,'',NULL,NULL,0,0,0,0,'Admission','','',NULL,NULL,'','','General',0,'','','','','',0,'',NULL,'','',0,'','','','','','2021/22','2025/26'),(291,'J.S.ABDUR RAHMAN','V--A','L.K.G--',11,'Male','2014-01-18','','Indian','Islam',NULL,'0019-06-10','730','','','','','2025-05-05',0,'','FNPS0291',NULL,NULL,'',NULL,NULL,'',NULL,'',0,NULL,'','',2,NULL,'',NULL,NULL,0,0,0,0,'Admission','','',NULL,NULL,'','','General',0,'','','','','',0,'',NULL,'','',0,'','','','','','2019/20','2025/26'),(292,'A.A.ABDUL AWF','V--A','I--',9,'Male','2015-09-11','','Indian','Islam',NULL,'0021-07-30','905','','','','','2025-05-05',0,'','FNPS0292',NULL,NULL,'',NULL,NULL,'',NULL,'',0,NULL,'','',2,NULL,'',NULL,NULL,0,0,0,0,'Admission','','',NULL,NULL,'','','General',0,'','','','','',0,'',NULL,'','',0,'','','','','','2021/22','2025/26'),(293,'Y.KADER ATHIF','V--A','L.K.G--',4,'Male','2020-10-21','','Indian','Islam',NULL,'2019-06-10','718','','','','','2025-05-05',0,'','FNPS0293',NULL,NULL,'',NULL,NULL,'',NULL,'',0,NULL,'','',2,NULL,'',NULL,NULL,0,0,0,0,'Admission','','',NULL,NULL,'','','General',0,'','','','','',0,'',NULL,'','',0,'','','','','','2019/20','2025/26'),(294,'V.MEROSWIN','V--A','I--',0,'Male','2025-01-30','','Indian','Christianity',NULL,'2021-06-21','799','','','','','2025-05-05',0,'','FNPS0294',NULL,NULL,'',NULL,NULL,'',NULL,'',0,NULL,'','',2,NULL,'',NULL,NULL,0,0,0,0,'Admission','','',NULL,NULL,'','','General',0,'','','','','',0,'',NULL,'','',0,'','','','','','2021/22','2025/26'),(295,'S.A.B.MOHAMMED ASHRAF','V--A','II--',9,'Male','2015-10-19','','Indian','Islam',NULL,'2022-10-14','1093','','','','','2025-05-05',0,'','FNPS0295',NULL,NULL,'',NULL,NULL,'',NULL,'',0,NULL,'','',2,NULL,'',NULL,NULL,0,0,0,0,'Admission','','',NULL,NULL,'','','General',0,'','','','','',0,'',NULL,'','',0,'','','','','','2022/23','2025/26'),(296,'S.MOHAMED MUSTHAFA','V--A','L.K.G--',9,'Male','2016-01-12','','Indian','Islam',NULL,'2019-06-10','689','','','','','2025-05-05',0,'','FNPS0296',NULL,NULL,'',NULL,NULL,'',NULL,'',0,NULL,'','',2,NULL,'',NULL,NULL,0,0,0,0,'Admission','','',NULL,NULL,'','','General',0,'','','','','',0,'',NULL,'','',0,'','','','','','2019/20','2025/26'),(297,'S.A.K.MOHAMED UTHUMAN','V--A','L.K.G--',10,'Male','2014-12-04','','Indian','Islam',NULL,'2019-06-10','715','','','','','2025-05-05',0,'','FNPS0297',NULL,NULL,'',NULL,NULL,'',NULL,'',0,NULL,'','',2,NULL,'',NULL,NULL,0,0,0,0,'Admission','','',NULL,NULL,'','','General',0,'','','','','',0,'',NULL,'','',0,'','','','','','2019/20','2025/26'),(298,'S.MOHIDEEN HAFEEZ','V--A','I--',9,'Male','2015-06-17','','Indian','Islam',NULL,'2021-07-16','794','','','','','2025-05-05',0,'','FNPS0298',NULL,NULL,'',NULL,NULL,'',NULL,'',0,NULL,'','',2,NULL,'',NULL,NULL,0,0,0,0,'Admission','','',NULL,NULL,'','','General',0,'','','','','',0,'',NULL,'','',0,'','','','','','2021/22','2025/26'),(299,'K.NAWFAL RAHMAN ','V--A','IV--',9,'Male','2016-02-14','','Indian','Islam',NULL,'2024-06-10','1244','','','','','2025-05-05',0,'','FNPS0299',NULL,NULL,'',NULL,NULL,'',NULL,'',0,NULL,'','',2,NULL,'',NULL,NULL,0,0,0,0,'Admission','','',NULL,NULL,'','','General',0,'','','','','',0,'',NULL,'','',0,'','','','','','2024/25','2025/26'),(300,'M.A.SAHIL AKTHAR','V--A','L.K.G--',9,'Male','2016-02-24','','Indian','Islam',NULL,'2019-06-10','721','','','','','2025-05-05',0,'','FNPS0300',NULL,NULL,'',NULL,NULL,'',NULL,'',0,NULL,'','',2,NULL,'',NULL,NULL,0,0,0,0,'Admission','','',NULL,NULL,'','','General',0,'','','','','',0,'',NULL,'','',0,'','','','','','2019/20','2025/26'),(301,'M.V.SALMAN FARIZ','V--A','II--',9,'Male','2015-09-05','','Indian','Islam',NULL,'2022-06-13','1066','','','','','2025-05-05',0,'','FNPS0301',NULL,NULL,'',NULL,NULL,'',NULL,'',0,NULL,'','',2,NULL,'',NULL,NULL,0,0,0,0,'Admission','','',NULL,NULL,'','','General',0,'','','','','',0,'',NULL,'','',0,'','','','','','2022/23','2025/26'),(302,'M.SEYED AHAMED','V--A','L.K.G--',8,'Male','2016-06-06','','Indian','Islam',NULL,'2019-06-10','739','','','','','2025-05-05',0,'','FNPS0302',NULL,NULL,'',NULL,NULL,'',NULL,'',0,NULL,'','',2,NULL,'',NULL,NULL,0,0,0,0,'Admission','','',NULL,NULL,'','','General',0,'','','','','',0,'',NULL,'','',0,'','','','','','2019/20','2025/26'),(303,'M.R.SHAIK ABDUL KADER','V--A','I--',8,'Male','2016-08-16','','Indian','Islam',NULL,'2021-11-11','792','','','','','2025-05-05',0,'','FNPS0303',NULL,NULL,'',NULL,NULL,'',NULL,'',0,NULL,'','',2,NULL,'',NULL,NULL,0,0,0,0,'Admission','','',NULL,NULL,'','','General',0,'','','','','',0,'',NULL,'','',0,'','','','','','2021/22','2025/26'),(304,'M.M.SOOFI HUSSAIN','V--A','L.K.G--',10,'Male','2015-04-16','','Indian','Islam',NULL,'2019-05-09','695','','','','','2025-05-05',0,'','FNPS0304',NULL,NULL,'',NULL,NULL,'',NULL,'',0,NULL,'','',2,NULL,'',NULL,NULL,0,0,0,0,'Admission','','',NULL,NULL,'','','General',0,'','','','','',0,'',NULL,'','',0,'','','','','','2019/20','2025/26'),(305,'B.SRI PRAKASH','V--A','L.K.G--',10,'Male','2015-05-07','','Indian','Hinduism',NULL,'2019-06-10','694','','','','','2025-05-05',0,'','FNPS0305',NULL,NULL,'',NULL,NULL,'',NULL,'',0,NULL,'','',2,NULL,'',NULL,NULL,0,0,0,0,'Admission','','',NULL,NULL,'','','General',0,'','','','','',0,'',NULL,'','',0,'','','','','','2019/20','2025/26'),(306,'U.TRUMAN NAGARAJ','V--A','III--',9,'Male','2016-02-25','','Indian','Hinduism',NULL,'2023-06-14','1149','','','','','2025-05-05',0,'','FNPS0306',NULL,NULL,'',NULL,NULL,'',NULL,'',0,NULL,'','',2,NULL,'',NULL,NULL,0,0,0,0,'Admission','','',NULL,NULL,'','','General',0,'','','','','',0,'',NULL,'','',0,'','','','','','2023/24','2025/26'),(307,'M.VETRI MARAN','V--A','L.K.G--',9,'Male','2015-07-17','','Indian','Hinduism',NULL,'2019-06-10','731','','','','','2025-05-05',0,'','FNPS0307',NULL,NULL,'',NULL,NULL,'',NULL,'',0,NULL,'','',2,NULL,'',NULL,NULL,0,0,0,0,'Admission','','',NULL,NULL,'','','General',0,'','','','','',0,'',NULL,'','',0,'','','','','','2019/20','2025/26'),(308,'H.AFNA','V--B','II--',10,'Female','2015-03-02','','Indian','Islam',NULL,'2022-06-13','1069','','','','','2025-05-05',0,'','FNPS0308',NULL,NULL,'',NULL,NULL,'',NULL,'',0,NULL,'','',2,NULL,'',NULL,NULL,0,0,0,0,'Admission','','',NULL,NULL,'','','General',0,'','','','','',0,'',NULL,'','',0,'','','','','','2022/23','2025/26'),(309,'V.A.S.AHAMED FASEEHA','V--B','L.K.G--',9,'Female','2015-08-25','','Indian','Islam',NULL,'2019-06-10','732','','','','','2025-05-05',0,'','FNPS0309',NULL,NULL,'',NULL,NULL,'',NULL,'',0,NULL,'','',2,NULL,'',NULL,NULL,0,0,0,0,'Admission','','',NULL,NULL,'','','General',0,'','','','','',0,'',NULL,'','',0,'','','','','','2019/20','2025/26'),(310,'M.N.L.AYSHATH MADHEENA','V--B','L.K.G--',9,'Female','2015-09-01','','Indian','Islam',NULL,'2019-06-10','719','','','','','2025-05-05',0,'','FNPS0310',NULL,NULL,'',NULL,NULL,'',NULL,'',0,NULL,'','',2,NULL,'',NULL,NULL,0,0,0,0,'Admission','','',NULL,NULL,'','','General',0,'','','','','',0,'',NULL,'','',0,'','','','','','2019/20','2025/26'),(311,'P.DARISHA ','V--B','IV--',8,'Female','2016-07-24','','Indian','Christianity',NULL,'2024-06-10','1272','','','','','2025-05-05',0,'','FNPS0311',NULL,NULL,'',NULL,NULL,'',NULL,'',0,NULL,'','',2,NULL,'',NULL,NULL,0,0,0,0,'Admission','','',NULL,NULL,'','','General',0,'','','','','',0,'',NULL,'','',0,'','','','','','2024/25','2025/26'),(312,'U.F.FATHIMA HUMAIRA','V--B','L.K.G--',9,'Female','2015-06-29','','Indian','Islam',NULL,'2019-06-10','698','','','','','2025-05-05',0,'','FNPS0312',NULL,NULL,'',NULL,NULL,'',NULL,'',0,NULL,'','',2,NULL,'',NULL,NULL,0,0,0,0,'Admission','','',NULL,NULL,'','','General',0,'','','','','',0,'',NULL,'','',0,'','','','','','2019/20','2025/26'),(313,'t.FATHIMA NAFEESA','V--B','I--',9,'Female','2015-09-17','','Indian','Islam',NULL,'2021-11-02','790','','','','','2025-05-05',0,'','FNPS0313',NULL,NULL,'',NULL,NULL,'',NULL,'',0,NULL,'','',2,NULL,'',NULL,NULL,0,0,0,0,'Admission','','',NULL,NULL,'','','General',0,'','','','','',0,'',NULL,'','',0,'','','','','','2021/22','2025/26'),(314,'S.FATHIMA ZAINAB AAFIA','V--B','L.K.G--',8,'Female','2016-06-09','','Indian','Islam',NULL,'2019-06-10','733','','','','','2025-05-05',0,'','FNPS0314',NULL,NULL,'',NULL,NULL,'',NULL,'',0,NULL,'','',2,NULL,'',NULL,NULL,0,0,0,0,'Admission','','',NULL,NULL,'','','General',0,'','','','','',0,'',NULL,'','',0,'','','','','','2019/20','2025/26'),(315,'H.HAMNA','V--B','II--',9,'Female','2015-12-30','','Indian','Islam',NULL,'2022-06-13','1070','','','','','2025-05-05',0,'','FNPS0315',NULL,NULL,'',NULL,NULL,'',NULL,'',0,NULL,'','',2,NULL,'',NULL,NULL,0,0,0,0,'Admission','','',NULL,NULL,'','','General',0,'','','','','',0,'',NULL,'','',0,'','','','','','2022/23','2025/26'),(316,'B.JANCI RANI ','V--B','IV--',9,'Female','2015-07-26','','Indian','Hinduism',NULL,'2024-06-11','1300','','','','','2025-05-05',0,'','FNPS0316',NULL,NULL,'',NULL,NULL,'',NULL,'',0,NULL,'','',2,NULL,'',NULL,NULL,0,0,0,0,'Admission','','',NULL,NULL,'','','General',0,'','','','','',0,'',NULL,'','',0,'','','','','','2024/25','2025/26'),(317,'A.S.KATHEEJA AADHILA','V--B','L.K.G--',10,'Female','2014-07-17','','Indian','Islam',NULL,'2019-06-10','712','','','','','2025-05-05',0,'','FNPS0317',NULL,NULL,'',NULL,NULL,'',NULL,'',0,NULL,'','',2,NULL,'',NULL,NULL,0,0,0,0,'Admission','','',NULL,NULL,'','','General',0,'','','','','',0,'',NULL,'','',0,'','','','','','2019/20','2025/26'),(318,'B.A.MARYAM SIDDIQA ','V--B','L.K.G--',8,'Female','2016-11-28','','Indian','Islam',NULL,'2019-06-10','709','','','','','2025-05-05',0,'','FNPS0318',NULL,NULL,'',NULL,NULL,'',NULL,'',0,NULL,'','',2,NULL,'',NULL,NULL,0,0,0,0,'Admission','','',NULL,NULL,'','','General',0,'','','','','',0,'',NULL,'','',0,'','','','','','2019/20','2025/26'),(319,'N.K.PAUL AMINA','V--B','L.K.G--',9,'Female','2016-02-18','','Indian','Islam',NULL,'2019-06-11','741','','','','','2025-05-05',0,'','FNPS0319',NULL,NULL,'',NULL,NULL,'',NULL,'',0,NULL,'','',2,NULL,'',NULL,NULL,0,0,0,0,'Admission','','',NULL,NULL,'','','General',0,'','','','','',0,'',NULL,'','',0,'','','','','','2019/20','2025/26'),(320,'M.B.RABIYA IFFATH','V--B','L.K.G--',9,'Female','2015-07-20','','Indian','Islam',NULL,'2019-06-11','707','','','','','2025-05-05',0,'','FNPS0320',NULL,NULL,'',NULL,NULL,'',NULL,'',0,NULL,'','',2,NULL,'',NULL,NULL,0,0,0,0,'Admission','','',NULL,NULL,'','','General',0,'','','','','',0,'',NULL,'','',0,'','','','','','2019/20','2025/26'),(321,'S.I.RUKKIYA AJWA','V--B','II--',10,'Female','2015-01-05','','Indian','Islam',NULL,'2022-06-13','1067','','','','','2025-05-05',0,'','FNPS0321',NULL,NULL,'',NULL,NULL,'',NULL,'',0,NULL,'','',2,NULL,'',NULL,NULL,0,0,0,0,'Admission','','',NULL,NULL,'','','General',0,'','','','','',0,'',NULL,'','',0,'','','','','','2022/23','2025/26'),(322,'S.SATHANA SRI','V--B','III--',9,'Female','2015-08-13','','Indian','Hinduism',NULL,'2023-06-14','1140','','','','','2025-05-05',0,'','FNPS0322',NULL,NULL,'',NULL,NULL,'',NULL,'',0,NULL,'','',2,NULL,'',NULL,NULL,0,0,0,0,'Admission','','',NULL,NULL,'','','General',0,'','','','','',0,'',NULL,'','',0,'','','','','','2023/24','2025/26'),(323,'R.SAMRAH (NEW)','V--B','IV--',8,'Female','2016-09-22','','Indian','Islam',NULL,'2024-06-10','1256','','','','','2025-05-05',0,'','FNPS0323',NULL,NULL,'',NULL,NULL,'',NULL,'',0,NULL,'','',2,NULL,'',NULL,NULL,0,0,0,0,'Admission','','',NULL,NULL,'','','General',0,'','','','','',0,'',NULL,'','',0,'','','','','','2024/25','2025/26'),(324,'G.SHIYANSIYA (NEW)','V--B','IV--',9,'Female','2016-01-13','','Indian','Christianity',NULL,'2024-06-27','1317','','','','','2025-05-05',0,'','FNPS0324',NULL,NULL,'',NULL,NULL,'',NULL,'',0,NULL,'','',2,NULL,'',NULL,NULL,0,0,0,0,'Admission','','',NULL,NULL,'','','General',0,'','','','','',0,'',NULL,'','',0,'','','','','','2024/25','2025/26'),(325,'C.SWATHI LAKSHMI','V--B','L.K.G--',10,'Female','2014-11-06','','Indian','Hinduism',NULL,'2019-06-10','701','','','','','2025-05-05',0,'','FNPS0325',NULL,NULL,'',NULL,NULL,'',NULL,'',0,NULL,'','',2,NULL,'',NULL,NULL,0,0,0,0,'Admission','','',NULL,NULL,'','','General',0,'','','','','',0,'',NULL,'','',0,'','','','','','2019/20','2025/26'),(326,'V.THANSHIKA','V--B','L.K.G--',9,'Female','2015-08-20','','Indian','Hinduism',NULL,'2019-06-11','738','','','','','2025-05-05',0,'','FNPS0326',NULL,NULL,'',NULL,NULL,'',NULL,'',0,NULL,'','',2,NULL,'',NULL,NULL,0,0,0,0,'Admission','','',NULL,NULL,'','','General',0,'','','','','',0,'',NULL,'','',0,'','','','','','2019/20','2025/26'),(327,'K.S.ZAHRA SANAA','V--B','L.K.G--',9,'Female','2016-01-10','','Indian','Islam',NULL,'2019-06-10','687','','','','','2025-05-05',0,'','FNPS0327',NULL,NULL,'',NULL,NULL,'',NULL,'',0,NULL,'','',2,NULL,'',NULL,NULL,0,0,0,0,'Admission','','',NULL,NULL,'','','General',0,'','','','','',0,'',NULL,'','',0,'','','','','','2019/20','2025/26'),(328,'B.AISHA JABIRA','L.K.G--','',3,'Female','2022-04-08','O +ve','Indian','Islam',NULL,NULL,'1329','Tamil','','','','2025-05-15',0,NULL,'20250328',NULL,NULL,'',NULL,NULL,'',NULL,'',NULL,NULL,'','',2,NULL,'',NULL,NULL,0,0,0,NULL,'Admission','','',NULL,NULL,'','','',0,'','','','None',NULL,0,'',NULL,'','',331,NULL,NULL,NULL,NULL,NULL,'2025/26',NULL),(329,'J.INFANT','L.K.G--','',4,'Male','2020-10-30','O +ve','Indian','Christianity',NULL,NULL,'1330','Tamil','','','','2025-05-15',0,'','20250329',NULL,NULL,'',NULL,NULL,'',NULL,'',0,NULL,'','',2,NULL,'',NULL,NULL,0,0,0,0,'Admission','','',NULL,NULL,'','','General',0,'','','813687150604','None','',0,'',NULL,'','',0,'','','','','','2025/26',''),(330,'H.SAFRAZ','L.K.G--','',4,NULL,'2020-10-17','B +ve','Indian','Islam',NULL,'2025-04-30','1331','Tamil','','KIDDOPIA PLAY SCHOOL','Nursery','2025-05-15',0,'','20250330',NULL,NULL,'RAZACK HOSPITAL,KAYALPATNAM,TIRUCHENDUR,THOOTHUKUDI',NULL,NULL,'',NULL,'',0,NULL,'','',2,NULL,'',NULL,NULL,0,0,0,0,'Admission','','',NULL,NULL,'','','General',0,'','','309863204142','None','',0,'',NULL,'','',0,'','','','','','2025/26',''),(331,'MUHAMMAD SHAIKH NASIR','L.K.G--','',3,NULL,'2021-08-17','B +ve','Indian','Islam',NULL,NULL,'1332','Tamil','','KINDER GARDEN BUDZ AND CUBZ','Nursery','2025-05-15',0,'','20250331',NULL,NULL,'KIRUBAI HOSPITAL,25,DASON STREET,NAZARETH,THOOTHUKUDI',NULL,NULL,'',NULL,'',0,NULL,'','',2,NULL,'',NULL,NULL,0,0,0,0,'Admission','','Local Bodies',NULL,NULL,'','','General',0,'NO','','200610057269','None','',0,'',NULL,'','',0,'','','','','','2025/26',''),(332,'M.M. KHUBAIB ASLAM','L.K.G--','',3,NULL,'2021-07-31','B +ve','Indian','Islam',NULL,NULL,'1333','Tamil','','','','2025-05-15',0,'','20250332',NULL,NULL,'KMT,HOSPITAL,KAYALPATNAM,THOOTHUKUDI',NULL,NULL,'',NULL,'',0,NULL,'','',2,NULL,'',NULL,NULL,0,0,0,0,'Admission','','',NULL,NULL,'','','General',0,'','','593222937519','None','',0,'',NULL,'','',0,'','','','','','2025/26',''),(333,'S.I.JAFAR SADIQ','L.K.G--','',3,'Male','2021-11-05','A +ve','Indian','Islam',NULL,'2025-05-05','1334','Tamil','','','','2025-05-15',0,NULL,'20250333',NULL,NULL,'',NULL,NULL,'',NULL,'',NULL,NULL,'','',2,NULL,'',NULL,NULL,0,0,0,NULL,'Admission','','',NULL,NULL,'','','',0,'','','206324806557','None',NULL,0,'',NULL,'','',331,NULL,NULL,NULL,NULL,NULL,'2025/26',NULL),(334,'S.S.SAHINA','L.K.G--','',3,'Female','2021-06-12','A +ve','Indian','',NULL,NULL,'1335','Tamil','','','','2025-05-15',0,NULL,'20250334',NULL,NULL,'',NULL,NULL,'',NULL,'',NULL,NULL,'','',2,NULL,'',NULL,NULL,0,0,0,NULL,'Admission','','',NULL,NULL,'','','',0,'','','796316716158','None',NULL,0,'',NULL,'','',331,NULL,NULL,NULL,NULL,NULL,'2025/26',NULL),(335,'M.T.S.H. ABU JUNAIDH','L.K.G--','',3,'Male','2021-09-13','A +ve','Indian','Islam',NULL,'2025-05-05','1336','Tamil','','','','2025-05-16',0,NULL,'20250335',NULL,NULL,'',NULL,NULL,'',NULL,'',NULL,NULL,'','',2,NULL,'',NULL,NULL,0,0,0,NULL,'Admission','','',NULL,NULL,'','','',0,'','','791604404999','None',NULL,0,'',NULL,'','',2,NULL,NULL,NULL,NULL,NULL,'2025/26',NULL),(336,'K.M.AYNUL MUIZZA','L.K.G--','L.K.G--',3,'Female','2021-05-17','A +ve','Indian','Islam',NULL,NULL,'1337','Tamil','','','','2025-05-16',0,'','20250336',NULL,NULL,'',NULL,NULL,'',NULL,'',0,NULL,'','',2,NULL,'',NULL,NULL,0,0,0,0,'Admission','','',NULL,NULL,'','','General',0,'','','647955155244','None','',0,'',NULL,'','',0,'','','','','','2025/26',''),(337,'A.K.JAHIR AFRAN','L.K.G--','',3,'Male','2021-10-18','','Indian','Islam',NULL,NULL,'1338','Tamil','','','','2025-05-16',0,NULL,'20250337',NULL,NULL,'',NULL,NULL,'',NULL,'',NULL,NULL,'','',2,NULL,'',NULL,NULL,0,0,0,NULL,'Admission','','',NULL,NULL,'','','',0,'','','819871610523','None',NULL,0,'',NULL,'','',2,NULL,NULL,NULL,NULL,NULL,'2025/26',NULL),(338,'A.J.HABEEBA HAFNA','L.K.G--','',3,'Female','2021-09-23','O +ve','Indian','Islam',NULL,NULL,'1339','Tamil','','ANGANWADI CENTER,KEELA NANINAR STREET,KAYALPATNAM','Nursery','2025-05-16',0,NULL,'20250338',NULL,NULL,'',NULL,NULL,'',NULL,'',NULL,NULL,'','',2,NULL,'',NULL,NULL,0,0,0,NULL,'Admission','','',NULL,NULL,'','','',0,'','','44476691953','None',NULL,0,'',NULL,'','',2,NULL,NULL,NULL,NULL,NULL,'2025/26',NULL),(339,'S.MOHAMED FARIS','U.K.G--','',5,'Male','2020-02-05','O +ve','Indian','Islam',NULL,NULL,'1340','Tamil','','','','2025-05-16',0,NULL,'20250339',NULL,NULL,'',NULL,NULL,'',NULL,'',NULL,NULL,'','',2,NULL,'',NULL,NULL,0,0,0,NULL,'Admission','','',NULL,NULL,'','','',0,'','','271287791011','None',NULL,0,'',NULL,'','',2,NULL,NULL,NULL,NULL,NULL,'2025/26',NULL),(340,'S.H.ZAINAB RALIYA','L.K.G--','',3,'Female','2021-12-30','O +ve','Indian','Islam',NULL,'2025-05-07','1341','Tamil','','','','2025-05-16',0,'','20250340',NULL,NULL,'',NULL,NULL,'',NULL,'',0,NULL,'','',2,NULL,'',NULL,NULL,0,0,0,0,'Admission','','',NULL,NULL,'','','General',0,'','','560958380687','None','',0,'',NULL,'','',0,'','','','','','2025/26',''),(341,'P.JONAH','L.K.G--','',4,'Female','2020-08-11','B +ve','Indian','Christianity',NULL,NULL,'1342','Tamil','','','','2025-05-16',0,NULL,'20250341',NULL,NULL,'',NULL,NULL,'',NULL,'',NULL,NULL,'','',2,NULL,'',NULL,NULL,0,0,0,NULL,'Admission','','',NULL,NULL,'','','',0,'','','502718209497','None',NULL,0,'',NULL,'','',2,NULL,NULL,NULL,NULL,NULL,'2025/26',NULL),(342,'C.SINDUJA','L.K.G--','',3,'Female','2021-08-20','B -ve','Indian','Hinduism',NULL,NULL,'1343','Tamil','','','','2025-05-16',0,NULL,'20250342',NULL,NULL,'',NULL,NULL,'',NULL,'',NULL,NULL,'','',2,NULL,'',NULL,NULL,0,0,0,NULL,'Admission','','',NULL,NULL,'','','',0,'','','','None',NULL,0,'',NULL,'','',2,NULL,NULL,NULL,NULL,NULL,'2025/26',NULL),(343,'S.AYSHA SHYMA','L.K.G--','',4,'Female','2020-11-21','','Indian','',NULL,'2025-05-02','1344','Tamil','','','','2025-05-16',0,NULL,'20250343',NULL,NULL,'',NULL,NULL,'',NULL,'',NULL,NULL,'','',2,NULL,'',NULL,NULL,0,0,0,NULL,'Admission','','',NULL,NULL,'','','',0,'','','492340569730','None',NULL,0,'',NULL,'','',2,NULL,NULL,NULL,NULL,NULL,'2025/26',NULL),(344,'K.M.NOORUL JUHAINA','L.K.G--','',3,'Female','2021-11-27','B +ve','Indian','Islam',NULL,'2025-05-12','1345','Tamil','','','','2025-05-16',0,NULL,'20250344',NULL,NULL,'',NULL,NULL,'',NULL,'',NULL,NULL,'','',2,NULL,'',NULL,NULL,0,0,0,NULL,'Admission','','',NULL,NULL,'','','',0,'','','867214547048','None',NULL,0,'',NULL,'','',2,NULL,NULL,NULL,NULL,NULL,'2025/26',NULL),(345,'M.M.SEYED MUHAMMED','L.K.G--','',3,'Male','2022-02-27','A +ve','Indian','Islam',NULL,NULL,'1346','Tamil','','','','2025-05-16',0,NULL,'20250345',NULL,NULL,'',NULL,NULL,'',NULL,'',NULL,NULL,'','',2,NULL,'',NULL,NULL,0,0,0,NULL,'Admission','','',NULL,NULL,'','','',0,'','','630837320776','None',NULL,0,'',NULL,'','',2,NULL,NULL,NULL,NULL,NULL,'2025/26',NULL),(346,'R.SASWIN LINGAM','L.K.G--','',3,'Male','2022-02-06','O -ve','Indian','Hinduism',NULL,NULL,'1347','Tamil','','','','2025-05-16',0,'','20250346',NULL,NULL,'',NULL,NULL,'',NULL,'',0,NULL,'','',2,NULL,'',NULL,NULL,0,0,0,0,'Admission','','',NULL,NULL,'','','General',0,'','','490617862329','None','',0,'',NULL,'','',0,'','','','','','2025/26',''),(347,'FATHIMA SHARAFIYA','L.K.G--','',3,NULL,'2021-08-07','','Indian','Islam',NULL,NULL,'1348','Tamil','','','','2025-05-16',0,NULL,'20250347',NULL,NULL,'',NULL,NULL,'',NULL,'',NULL,NULL,'','',2,NULL,'',NULL,NULL,0,0,0,NULL,'Admission','','',NULL,NULL,'','','',0,'','','317623667705','None',NULL,0,'',NULL,'','',2,NULL,NULL,NULL,NULL,NULL,'2025/26',NULL),(348,'M.SUMAIYA ASHIFA','L.K.G--','',3,'Female','2021-09-04','','Indian','Islam',NULL,'2025-05-12',' 1349','Tamil','','','','2025-05-16',0,NULL,'20250348',NULL,NULL,'',NULL,NULL,'',NULL,'',NULL,NULL,'','',2,NULL,'',NULL,NULL,0,0,0,NULL,'Admission','','',NULL,NULL,'','','',0,'','','-','None',NULL,0,'',NULL,'','',2,NULL,NULL,NULL,NULL,NULL,'2025/26',NULL),(349,'M.SARA INAYA','L.K.G--','',3,NULL,'2021-10-06','AB +ve','Indian','Islam',NULL,NULL,'1350','Tamil','','','','2025-05-16',0,NULL,'20250349',NULL,NULL,'',NULL,NULL,'',NULL,'',NULL,NULL,'','',2,NULL,'',NULL,NULL,0,0,0,NULL,'Admission','','',NULL,NULL,'','','',0,'','','667564619406','None',NULL,0,'',NULL,'','',2,NULL,NULL,NULL,NULL,NULL,'2025/26',NULL),(350,'K. MARIYA DILOSAN','U.K.G--','',4,'Male','2020-11-09','','Indian','Christianity',NULL,'2025-05-12','1351','Tamil','','','','2025-05-16',0,'','20250350',NULL,NULL,'',NULL,NULL,'',NULL,'',0,NULL,'','',2,NULL,'',NULL,NULL,0,0,0,0,'Admission','','',NULL,NULL,'','','General',0,'','','525321502534','None','',0,'',NULL,'','',0,'','','','','','2025/26',''),(351,'HARISH MADHAV','L.K.G--','',4,'Male','2021-01-06','','Indian','Hinduism',NULL,NULL,'1352','','','','','2025-05-16',0,NULL,'20250351',NULL,NULL,'',NULL,NULL,'',NULL,'',NULL,NULL,'','',2,NULL,'',NULL,NULL,0,0,0,NULL,'Admission','','',NULL,NULL,'','','',0,'','','330288174556','None',NULL,0,'',NULL,'','',2,NULL,NULL,NULL,NULL,NULL,'2025/26',NULL),(352,'i.NADHIRA','V--','V--',0,'Female',NULL,'','Indian','',NULL,'2025-05-19','1353','Tamil','','L.K.MATRIC HR SEC.SCHOOL,KAYALPATNAM','','2025-05-19',0,'','20250352',NULL,NULL,'',NULL,NULL,'',NULL,'',0,NULL,'','',2,NULL,'',NULL,NULL,0,0,0,0,'Admission','','',NULL,NULL,'','','General',0,'','','','None','',0,'',NULL,'','',0,'','','','','','2025/26',''),(353,'S.MOHAMED SAQEEL','L.K.G--','',3,'Male','2022-04-07','','Indian','Islam',NULL,'2025-05-19','1354','Tamil','','','','2025-05-19',0,NULL,'20250353',NULL,NULL,'',NULL,NULL,'',NULL,'',NULL,NULL,'','',2,NULL,'',NULL,NULL,0,0,0,NULL,'Admission','','',NULL,NULL,'','','',0,'','','980564922839','None',NULL,0,'',NULL,'','',2,NULL,NULL,NULL,NULL,NULL,'2025/26',NULL),(354,'A.S.AFEEZA FATHIMA','L.K.G--','',4,'Female','2021-01-17','O +ve','Indian','Islam',NULL,'2025-05-19','1355','Tamil','','','','2025-05-19',0,NULL,'20250354',NULL,NULL,'',NULL,NULL,'',NULL,'',NULL,NULL,'','',2,NULL,'',NULL,NULL,0,0,0,NULL,'Admission','','',NULL,NULL,'','','',0,'','','526818285252','None',NULL,0,'',NULL,'','',2,NULL,NULL,NULL,NULL,NULL,'2025/26',NULL),(355,'M.MSAYEED IBRAHIM','L.K.G--','',4,'Male','2020-12-15','A +ve','Indian','Islam',NULL,NULL,'1356','Tamil','','','','2025-05-19',0,NULL,'20250355',NULL,NULL,'',NULL,NULL,'',NULL,'',NULL,NULL,'','',2,NULL,'',NULL,NULL,0,0,0,NULL,'Admission','','',NULL,NULL,'','','',0,'','','871478931521','None',NULL,0,'',NULL,'','',2,NULL,NULL,NULL,NULL,NULL,'2025/26',NULL),(356,'M.I.MAIMOON RUQAYYA','U.K.G--','',4,'Male','2020-12-11','O +ve','Indian','Islam',NULL,'2025-05-19','1357','Tamil','','MUHYIDDEEN MATICULATION HIGHER SECONDARY SCHOOL,KAYALPATNAM','L.K.G','2025-05-19',0,NULL,'20250356',NULL,NULL,'',NULL,NULL,'',NULL,'',NULL,NULL,'','',2,NULL,'',NULL,NULL,0,0,0,NULL,'Admission','','',NULL,NULL,'','','',0,'','','899969038109','None',NULL,0,'',NULL,'','',2,NULL,NULL,NULL,NULL,NULL,'2025/26',NULL),(357,'M.MELROY','L.K.G--','',4,'Male','2021-05-17','A -ve','Indian','Christianity',NULL,'2025-05-19','1358','Tamil','','','','2025-05-19',0,NULL,'20250357',NULL,NULL,'',NULL,NULL,'',NULL,'',NULL,NULL,'','',2,NULL,'',NULL,NULL,0,0,0,NULL,'Admission','','',NULL,NULL,'','','',0,'','','856464275047','None',NULL,0,'',NULL,'','',2,NULL,NULL,NULL,NULL,NULL,'2025/26',NULL),(358,'S.S.abdul hawaz','L.K.G--','L.K.G--',4,'Male','2021-01-29','','Indian','Islam',NULL,'2025-06-02','1388','Tamil','','','','2025-05-21',0,'','20250358',NULL,NULL,'',NULL,NULL,'',NULL,'',0,NULL,'','',2,NULL,'',NULL,NULL,0,0,0,0,'Admission','','',NULL,NULL,'','','General',0,'','','','None','',0,'',NULL,'','',0,'','','','','','2025/26',''),(359,'m.MAHISHRI','U.K.G--','',4,'Female','2020-10-01','A -ve','Indian','Hinduism',NULL,'2025-05-19','1359','Tamil','','','','2025-05-21',0,NULL,'20250359',NULL,NULL,'',NULL,NULL,'',NULL,'',NULL,NULL,'','',2,NULL,'',NULL,NULL,0,0,0,NULL,'Admission','','',NULL,NULL,'','','',0,'','','431897910499','None',NULL,0,'',NULL,'','',2,NULL,NULL,NULL,NULL,NULL,'2025/26',NULL),(360,'K.SAMSU AQDAS','L.K.G--','',3,'Male','2021-12-06','','Indian','Islam',NULL,NULL,'1360','Tamil','','','','2025-05-21',0,NULL,'20250360',NULL,NULL,'',NULL,NULL,'',NULL,'',NULL,NULL,'','',2,NULL,'',NULL,NULL,0,0,0,NULL,'Admission','','',NULL,NULL,'','','',0,'','','696364028762','None',NULL,0,'',NULL,'','',2,NULL,NULL,NULL,NULL,NULL,'2025/26',NULL),(361,'A.MOHAMMED FAIZAL','L.K.G--','',3,NULL,'2022-02-16','B +ve','Indian','Islam',NULL,'2025-05-21','1361','Tamil','','','','2025-05-21',0,NULL,'20250361',NULL,NULL,'',NULL,NULL,'',NULL,'',NULL,NULL,'','',2,NULL,'',NULL,NULL,0,0,0,NULL,'Admission','','',NULL,NULL,'','','',0,'','','444423192136','None',NULL,0,'',NULL,'','',2,NULL,NULL,NULL,NULL,NULL,'2025/26',NULL),(362,'K.MOHUDHUM FATHIMA ZUHA','U.K.G--','',4,'Female','2021-01-25','','Indian','Islam',NULL,'2025-05-22','1362','Tamil','','','','2025-05-22',0,NULL,'20250362',NULL,NULL,'',NULL,NULL,'',NULL,'',NULL,NULL,'','',2,NULL,'',NULL,NULL,0,0,0,NULL,'Admission','','',NULL,NULL,'','','',0,'','','','None',NULL,0,'',NULL,'','',2,NULL,NULL,NULL,NULL,NULL,'2025/26',NULL),(363,'S.M.FATHIMA SANAA','L.K.G--','',4,'Female','2021-03-14','B +ve','Indian','Islam',NULL,'2025-05-26','1364','Tamil','','','','2025-05-27',0,NULL,'20250363',NULL,NULL,'',NULL,NULL,'',NULL,'',NULL,NULL,'','',2,NULL,'',NULL,NULL,0,0,0,NULL,'Admission','','',NULL,NULL,'','','',0,'','','','None',NULL,0,'',NULL,'','',2,NULL,NULL,NULL,NULL,NULL,'2025/26',NULL),(364,'S.FATHIMA INARA','L.K.G--','',3,'Female','2021-09-11','','Indian','Islam',NULL,'2025-05-26','1365','Tamil','','','','2025-05-27',0,NULL,'20250364',NULL,NULL,'',NULL,NULL,'',NULL,'',NULL,NULL,'','',2,NULL,'',NULL,NULL,0,0,0,NULL,'Admission','','',NULL,NULL,'','','',0,'','','577601105355','None',NULL,0,'',NULL,'','',2,NULL,NULL,NULL,NULL,NULL,'2025/26',NULL),(365,'THEEKSHAN','L.K.G--','',3,'Male','2021-12-03','O +ve','Indian','Islam',NULL,'2025-05-26','1363','Tamil','','','','2025-05-27',0,NULL,'20250365',NULL,NULL,'',NULL,NULL,'',NULL,'',NULL,NULL,'','',2,NULL,'',NULL,NULL,0,0,0,NULL,'Admission','','',NULL,NULL,'','','',0,'','','','None',NULL,0,'',NULL,'','',2,NULL,NULL,NULL,NULL,NULL,'2025/26',NULL),(366,'HAFSA','L.K.G--','',3,'Female','2021-12-24','','Indian','Islam',NULL,'2025-05-27','1366','Tamil','','','','2025-05-27',0,NULL,'20250366',NULL,NULL,'',NULL,NULL,'',NULL,'',NULL,NULL,'','',2,NULL,'',NULL,NULL,0,0,0,NULL,'Admission','','',NULL,NULL,'','','',0,'','','745103014652','None',NULL,0,'',NULL,'','',2,NULL,NULL,NULL,NULL,NULL,'2025/26',NULL),(367,'EUGENIA GRACE','L.K.G--','L.K.G--',3,'Female','2021-11-16','','Indian','Christianity',NULL,'2025-05-27','1367','Tamil','','','','2025-05-27',0,'','20250367',NULL,NULL,'',NULL,NULL,'',NULL,'',0,NULL,'','',2,NULL,'',NULL,NULL,0,0,0,0,'Admission','','',NULL,NULL,'','','General',0,'','','250028569198','None','',0,'',NULL,'','',0,'','','','','','2025/26',''),(368,'I.JOEL RIYAS','L.K.G--','',4,'Male','2021-05-18','','Indian','Christianity',NULL,'2025-05-28','1368','Tamil','','','','2025-05-28',0,NULL,'20250368',NULL,NULL,'',NULL,NULL,'',NULL,'',NULL,NULL,'','',2,NULL,'',NULL,NULL,0,0,0,NULL,'Admission','','',NULL,NULL,'','','',0,'','','964957130987','None',NULL,0,'',NULL,'','',2,NULL,NULL,NULL,NULL,NULL,'2025/26',NULL),(369,'S.M..AFFAN','L.K.G--','',5,'Male','2020-04-02','B +ve','Indian','Islam',NULL,'2025-05-28','1369','Tamil','','','','2025-05-28',0,NULL,'20250369',NULL,NULL,'',NULL,NULL,'',NULL,'',NULL,NULL,'','',2,NULL,'',NULL,NULL,0,0,0,NULL,'Admission','','',NULL,NULL,'','','',0,'','','972215959143','None',NULL,0,'',NULL,'','',2,NULL,NULL,NULL,NULL,NULL,'2025/26',NULL),(370,'H.A.KADAR MUHIDEEN MAFAZ','II--','',6,'Male','2018-09-13','','Indian','Islam',NULL,'2025-05-28','1370','Tamil','','L.K.MATRIC HR SEC.SCHOOL,KAYALPATNAM','I','2025-05-28',0,NULL,'20250370',NULL,NULL,'',NULL,NULL,'',NULL,'',NULL,NULL,'','',2,NULL,'',NULL,NULL,0,0,0,NULL,'Admission','','',NULL,NULL,'','','',0,'','','310488602530','None',NULL,0,'',NULL,'','',2,NULL,NULL,NULL,NULL,NULL,'2025/26',NULL),(371,'M.AALIYA ','L.K.G--','',4,'Female','2020-08-10','','Indian','Islam',NULL,'2025-05-28','1371','Tamil','','','','2025-05-28',0,NULL,'20250371',NULL,NULL,'',NULL,NULL,'',NULL,'',NULL,NULL,'','',2,NULL,'',NULL,NULL,0,0,0,NULL,'Admission','','',NULL,NULL,'','','',0,'','','340753891262','None',NULL,0,'',NULL,'','',2,NULL,NULL,NULL,NULL,NULL,'2025/26',NULL),(372,'HABEEBUR RAHUMAN','L.K.G--','L.K.G--',3,'Male','2021-09-09','','Indian','Islam',NULL,'2025-06-02','1384','Telugu','','','','2025-05-28',0,'','20250372',NULL,NULL,'',NULL,NULL,'',NULL,'',0,NULL,'','',2,NULL,'',NULL,NULL,0,0,0,0,'Admission','','',NULL,NULL,'','','General',0,'','','','None','',0,'',NULL,'','',0,'','','','','','2025/26',''),(373,'I.MUTHU THARAN','L.K.G--','',3,'Male','2021-07-05','B +ve','Indian','Hinduism',NULL,NULL,'1372','Tamil','','','','2025-06-03',0,NULL,'20250373',NULL,NULL,'',NULL,NULL,'',NULL,'',NULL,NULL,'','',2,NULL,'',NULL,NULL,0,0,0,NULL,'Admission','','',NULL,NULL,'','','',0,'','','','None',NULL,0,'',NULL,'','',2,NULL,NULL,NULL,NULL,NULL,'2025/26',NULL),(374,'MUGIN','L.K.G--','',4,'Male','2021-04-17','','Indian','Hinduism',NULL,NULL,'1373','Tamil','','','','2025-06-03',0,NULL,'20250374',NULL,NULL,'',NULL,NULL,'',NULL,'',NULL,NULL,'','',2,NULL,'',NULL,NULL,0,0,0,NULL,'Admission','','',NULL,NULL,'','','',0,'','','','None',NULL,0,'',NULL,'','',2,NULL,NULL,NULL,NULL,NULL,'2025/26',NULL),(375,'A.KADER FATHIMA','L.K.G--','',3,'Female','2021-07-01','','Indian','Islam',NULL,'2025-05-30','1374','Tamil','','','','2025-06-03',0,NULL,'20250375',NULL,NULL,'',NULL,NULL,'',NULL,'',NULL,NULL,'','',2,NULL,'',NULL,NULL,0,0,0,NULL,'Admission','','',NULL,NULL,'','','',0,'','','','None',NULL,0,'',NULL,'','',2,NULL,NULL,NULL,NULL,NULL,'2025/26',NULL),(376,'ZUBAIDHA FARHA','L.K.G--','',3,'Female','2021-08-21','O +ve','Indian','Islam',NULL,NULL,'1375','Tamil','','','','2025-06-03',0,NULL,'20250376',NULL,NULL,'',NULL,NULL,'',NULL,'',NULL,NULL,'','',2,NULL,'',NULL,NULL,0,0,0,NULL,'Admission','','',NULL,NULL,'','','',0,'','','993738698287','None',NULL,0,'',NULL,'','',2,NULL,NULL,NULL,NULL,NULL,'2025/26',NULL),(377,'M.A.RAHMATH MANSOORA','L.K.G--','',3,'Female','2022-01-01','','Indian','Islam',NULL,NULL,'1376','Tamil','','','','2025-06-03',0,NULL,'20250377',NULL,NULL,'',NULL,NULL,'',NULL,'',NULL,NULL,'','',2,NULL,'',NULL,NULL,0,0,0,NULL,'Admission','','',NULL,NULL,'','','',0,'','','','None',NULL,0,'',NULL,'','',2,NULL,NULL,NULL,NULL,NULL,'2025/26',NULL),(378,'A..ABDUL RAZZAQ','L.K.G--','',3,'Male','2021-06-07','O +ve','Indian','Islam',NULL,NULL,'1377','Tamil','','','','2025-06-03',0,NULL,'20250378',NULL,NULL,'',NULL,NULL,'',NULL,'',NULL,NULL,'','',2,NULL,'',NULL,NULL,0,0,0,NULL,'Admission','','',NULL,NULL,'','','',0,'','','','None',NULL,0,'',NULL,'','',2,NULL,NULL,NULL,NULL,NULL,'2025/26',NULL),(379,'J.MARIYA JAKKINA','V--','V--',10,'Female','2014-11-23','O +ve','Indian','Christianity',NULL,NULL,'1378','Tamil','','00RATCHIYA ONDRIYA THODAKKAPALLI','III','2025-06-03',0,'','20250379',NULL,NULL,'',NULL,NULL,'',NULL,'',0,NULL,'','',2,NULL,'',NULL,NULL,0,0,0,0,'Admission','','Government',NULL,NULL,'','','General',0,'','','336385768133','None','',0,'',NULL,'','',0,'','','','','','2025/26',''),(380,'A.ZUNAIRAH','L.K.G--','',3,'Female','2021-06-20','','Indian','Islam',NULL,NULL,'1379','Tamil','','','','2025-06-03',0,NULL,'20250380',NULL,NULL,'',NULL,NULL,'',NULL,'',NULL,NULL,'','',2,NULL,'',NULL,NULL,0,0,0,NULL,'Admission','','',NULL,NULL,'','','',0,'','','355558564290','None',NULL,0,'',NULL,'','',2,NULL,NULL,NULL,NULL,NULL,'2025/26',NULL),(381,'S.MOHAMMED HANIF','L.K.G--','',4,'Male','2020-12-25','','Indian','Islam',NULL,'2025-06-02','1380','Tamil','','PALVADI','','2025-06-03',0,NULL,'20250381',NULL,NULL,'',NULL,NULL,'',NULL,'',NULL,NULL,'','',2,NULL,'',NULL,NULL,0,0,0,NULL,'Admission','','',NULL,NULL,'','','',0,'','','879892152208','None',NULL,0,'',NULL,'','',2,NULL,NULL,NULL,NULL,NULL,'2025/26',NULL),(382,'S.MADEEHA','L.K.G--','',3,NULL,'2021-09-04','B +ve','Indian','Islam',NULL,'2025-06-02','1381','Tamil','','','','2025-06-03',0,NULL,'20250382',NULL,NULL,'',NULL,NULL,'',NULL,'',NULL,NULL,'','',2,NULL,'',NULL,NULL,0,0,0,NULL,'Admission','','',NULL,NULL,'','','',NULL,'','','893553982321','None',NULL,0,'',NULL,'','',2,NULL,NULL,NULL,NULL,NULL,'2025/26',NULL),(383,'R.VERONIKA','L.K.G--','L.K.G--',3,'Female','2021-06-12','B +ve','Indian','Christianity',NULL,'2025-06-02','1382','Tamil','','','','2025-06-03',0,'','20250383',NULL,NULL,'',NULL,NULL,'',NULL,'',0,NULL,'','',2,NULL,'',NULL,NULL,0,0,0,0,'Admission','','',NULL,NULL,'','','General',0,'','','','None','',0,'',NULL,'','',0,'','','','','','2025/26',''),(384,'U.AZRA FATHIMA','L.K.G--','',3,'Female','2022-05-15','','Indian','Islam',NULL,'2025-06-02','1383','Tamil','','','','2025-06-03',0,NULL,'20250384',NULL,NULL,'',NULL,NULL,'',NULL,'',NULL,NULL,'','',2,NULL,'',NULL,NULL,0,0,0,NULL,'Admission','','',NULL,NULL,'','','',0,'','','213003288436','None',NULL,0,'',NULL,'','',2,NULL,NULL,NULL,NULL,NULL,'2025/26',NULL),(385,'R.SHAM','L.K.G--','',4,'Male','2020-11-18','','Indian','Christianity',NULL,'2025-06-02','1385','Tamil','','','','2025-06-03',0,NULL,'20250385',NULL,NULL,'',NULL,NULL,'',NULL,'',NULL,NULL,'','',2,NULL,'',NULL,NULL,0,0,0,NULL,'Admission','','',NULL,NULL,'','','',0,'','','','None',NULL,0,'',NULL,'','',2,NULL,NULL,NULL,NULL,NULL,'2025/26',NULL),(386,'S.G.ABDUL BASITH','III--','',7,'Male','2017-06-10','B +ve','Indian','Islam',NULL,'2025-06-02','1386 ','Tamil','','','','2025-06-03',0,NULL,'20250386',NULL,NULL,'',NULL,NULL,'',NULL,'',NULL,NULL,'','',2,NULL,'',NULL,NULL,0,0,0,NULL,'Admission','','',NULL,NULL,'','','',0,'','','413407447722','None',NULL,0,'',NULL,'','',2,NULL,NULL,NULL,NULL,NULL,'2025/26',NULL),(387,'MOHAMED AASIYA','L.K.G--','',3,'Female','2021-07-16','O +ve','Indian','Islam',NULL,'2025-06-02','1387','Tamil','','','','2025-06-03',0,NULL,'20250387',NULL,NULL,'',NULL,NULL,'',NULL,'',NULL,NULL,'','',2,NULL,'',NULL,NULL,0,0,0,NULL,'Admission','','',NULL,NULL,'','','',0,'','','370871896376','None',NULL,0,'',NULL,'','',2,NULL,NULL,NULL,NULL,NULL,'2025/26',NULL),(388,'M.HAMZA SIDDIQ','L.K.G--','',4,'Male','2021-05-07','','Indian','Islam',NULL,'2025-06-03','1389','Tamil','','','','2025-06-03',0,NULL,'20250388',NULL,NULL,'',NULL,NULL,'',NULL,'',NULL,NULL,'','',2,NULL,'',NULL,NULL,0,0,0,NULL,'Admission','','',NULL,NULL,'','','',0,'','','','None',NULL,0,'',NULL,'','',2,NULL,NULL,NULL,NULL,NULL,'2025/26',NULL),(389,'A.A.AAQIL','I--','',5,'Male','2019-10-24','B +ve','Indian','Islam',NULL,'2025-06-03','1390','Tamil','','AL-AMEEN SCHOOL','U.K.G','2025-06-03',0,NULL,'20250389',NULL,NULL,'',NULL,NULL,'',NULL,'',NULL,NULL,'','',2,NULL,'',NULL,NULL,0,0,0,NULL,'Admission','','Private Unaided School',NULL,NULL,'','','',0,'','','718052984464','None',NULL,0,'',NULL,'','',2,NULL,NULL,NULL,NULL,NULL,'2025/26',NULL),(390,'A.HADRIEL','L.K.G--','',3,'Male','2021-08-22','O -ve','Indian','Christianity',NULL,'2025-06-03','1391','Tamil','','','','2025-06-03',0,NULL,'20250390',NULL,NULL,'',NULL,NULL,'',NULL,'',NULL,NULL,'','',2,NULL,'',NULL,NULL,0,0,0,NULL,'Admission','','',NULL,NULL,'','','',0,'','','201454650668','None',NULL,0,'',NULL,'','',2,NULL,NULL,NULL,NULL,NULL,'2025/26',NULL),(391,'FATHIMA FAHIMA','L.K.G--','',NULL,NULL,NULL,'','Indian','Islam',NULL,NULL,'NF','','','','','2025-06-03',0,NULL,'20250391',NULL,NULL,'',NULL,NULL,'',NULL,'',NULL,NULL,'','',2,NULL,'',NULL,NULL,0,0,0,NULL,'Admission','','',NULL,NULL,'','','',0,'','','','None',NULL,0,'',NULL,'','',2,NULL,NULL,NULL,NULL,NULL,'2025/26',NULL),(392,'M.Y.HAMEED ZAAFIR','L.K.G--','',3,'Male','2021-08-19','','Indian','Islam',NULL,NULL,'1392','','','','','2025-06-10',0,NULL,'20250392',NULL,NULL,'',NULL,NULL,'',NULL,'',NULL,NULL,'','',2,NULL,'',NULL,NULL,0,0,0,NULL,'Admission','','',NULL,NULL,'','','',0,'','','','None',NULL,0,'',NULL,'','',2,NULL,NULL,NULL,NULL,NULL,'2025/26',NULL),(393,'M.MOHAMED HAFEEZ','V--','',9,NULL,'2015-11-20','B +ve','Indian','Islam',NULL,'2025-06-02','1393','Tamil','','AUXILIUM METRIC HIGHER SECONDARY SCHOOL,KAYALPATNAM','III','2025-06-10',0,NULL,'20250393',NULL,NULL,'',NULL,NULL,'',NULL,'',NULL,NULL,'','',2,NULL,'',NULL,NULL,0,0,0,NULL,'Admission','','',NULL,NULL,'','','',0,'','','','None',NULL,0,'',NULL,'','',2,NULL,NULL,NULL,NULL,NULL,'2025/26',NULL),(394,'M.MOHAMED HADAF','I--','',5,'Male','2019-12-22','B +ve','Indian','Islam',NULL,'2025-06-02','1394','Tamil','','AUXILIUM METRIC HIGHER SECONDARY SCHOOL,KAYALPATNAM','U.K.G','2025-06-10',0,NULL,'20250394',NULL,NULL,'',NULL,NULL,'',NULL,'',NULL,NULL,'','',2,NULL,'',NULL,NULL,0,0,0,NULL,'Admission','','',NULL,NULL,'','','',0,'','','','None',NULL,0,'',NULL,'','',2,NULL,NULL,NULL,NULL,NULL,'2025/26',NULL),(395,'A.ALENA','L.K.G--','',3,'Male','2022-03-31','','Indian','Christianity',NULL,NULL,'1395','Tamil','','','','2025-06-10',0,NULL,'20250395',NULL,NULL,'',NULL,NULL,'',NULL,'',NULL,NULL,'','',2,NULL,'',NULL,NULL,0,0,0,NULL,'Admission','','',NULL,NULL,'','','',0,'','','965817865797','None',NULL,0,'',NULL,'','',2,NULL,NULL,NULL,NULL,NULL,'2025/26',NULL),(396,'T.MOHAMMED FAZIL','L.K.G--','',4,'Male','2021-04-13','','Indian','Islam',NULL,NULL,'1396','Tamil','','','','2025-06-10',0,NULL,'20250396',NULL,NULL,'',NULL,NULL,'',NULL,'',NULL,NULL,'','',2,NULL,'',NULL,NULL,0,0,0,NULL,'Admission','','',NULL,NULL,'','','',0,'','','257236108942','None',NULL,0,'',NULL,'','',2,NULL,NULL,NULL,NULL,NULL,'2025/26',NULL),(397,'M.B.ABDUL BASEER FAWAZ','L.K.G--','',4,'Male','2020-11-22','O +ve','Indian','Islam',NULL,'2025-06-10','1397','Tamil','','','','2025-06-10',0,NULL,'20250397',NULL,NULL,'',NULL,NULL,'',NULL,'',NULL,NULL,'','',2,NULL,'',NULL,NULL,0,0,0,NULL,'Admission','','',NULL,NULL,'','','',0,'','','','None',NULL,0,'',NULL,'','',2,NULL,NULL,NULL,NULL,NULL,'2025/26',NULL),(398,'MARIYA SAKEEMA','V--','',NULL,'Female',NULL,'','Indian','',NULL,NULL,'NF1','','','','','2025-06-10',1,NULL,'20250398',NULL,NULL,'',NULL,NULL,'',NULL,'',NULL,NULL,'','',2,NULL,'',NULL,NULL,0,0,0,NULL,'Admission','','',NULL,NULL,'','','',0,'','','','None',NULL,0,'',NULL,'','',2,NULL,NULL,NULL,NULL,NULL,'2025/26',NULL),(399,'ABDUL AAKIF','L.K.G--','',NULL,'Male',NULL,'','Indian','',NULL,NULL,'NOFORM','','','','','2025-06-10',0,NULL,'20250399',NULL,NULL,'',NULL,NULL,'',NULL,'',NULL,NULL,'','',2,NULL,'',NULL,NULL,0,0,0,NULL,'Admission','','',NULL,NULL,'','','',NULL,'','','','None',NULL,NULL,'',NULL,'','',2,NULL,NULL,NULL,NULL,NULL,'2025/26',NULL),(400,'k.m.n. ABDUR RAHMAN','L.K.G--','',3,'Male','2022-02-12','O +ve','Indian','',NULL,'2025-06-10','1398','Tamil','','','','2025-06-10',0,NULL,'20250400',NULL,NULL,'',NULL,NULL,'',NULL,'',NULL,NULL,'','',2,NULL,'',NULL,NULL,0,0,0,NULL,'Admission','','',NULL,NULL,'','','',0,'','','206375638418','None',NULL,0,'',NULL,'','',2,NULL,NULL,NULL,NULL,NULL,'2025/26',NULL);
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
  `userid` int(11) DEFAULT NULL,
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
-- Table structure for table `studentdiary`
--

DROP TABLE IF EXISTS `studentdiary`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `studentdiary` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `sid` int(11) DEFAULT NULL,
  `classsec` varchar(45) DEFAULT NULL,
  `academicyear` varchar(45) DEFAULT NULL,
  `subject` varchar(100) DEFAULT NULL,
  `message` varchar(1000) DEFAULT NULL,
  `createddate` date DEFAULT NULL,
  `branchid` int(11) DEFAULT NULL,
  `userid` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `studentdiary`
--

LOCK TABLES `studentdiary` WRITE;
/*!40000 ALTER TABLE `studentdiary` DISABLE KEYS */;
/*!40000 ALTER TABLE `studentdiary` ENABLE KEYS */;
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
  `minmarks` float DEFAULT NULL,
  `maxmarks` float DEFAULT NULL,
  `branchid` int(11) DEFAULT NULL,
  `examname` varchar(150) DEFAULT NULL,
  `examclass` varchar(50) DEFAULT NULL,
  `userid` int(11) DEFAULT NULL,
  `subjectid` int(11) DEFAULT NULL,
  PRIMARY KEY (`subid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `subject`
--

LOCK TABLES `subject` WRITE;
/*!40000 ALTER TABLE `subject` DISABLE KEYS */;
/*!40000 ALTER TABLE `subject` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `subjectgrade`
--

DROP TABLE IF EXISTS `subjectgrade`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `subjectgrade` (
  `id` int(11) NOT NULL,
  `minmarks` int(11) NOT NULL,
  `maxmarks` int(11) NOT NULL,
  `status` varchar(45) NOT NULL,
  `branchid` int(11) DEFAULT NULL,
  `examid` varchar(100) DEFAULT NULL,
  `classsec` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `subjectgrade`
--

LOCK TABLES `subjectgrade` WRITE;
/*!40000 ALTER TABLE `subjectgrade` DISABLE KEYS */;
/*!40000 ALTER TABLE `subjectgrade` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `subjectmaster`
--

DROP TABLE IF EXISTS `subjectmaster`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `subjectmaster` (
  `subjectid` int(11) NOT NULL AUTO_INCREMENT,
  `subjectname` varchar(100) DEFAULT NULL,
  `branchid` int(11) DEFAULT NULL,
  `userid` int(11) DEFAULT NULL,
  PRIMARY KEY (`subjectid`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `subjectmaster`
--

LOCK TABLES `subjectmaster` WRITE;
/*!40000 ALTER TABLE `subjectmaster` DISABLE KEYS */;
INSERT INTO `subjectmaster` VALUES (1,'TAMIL',2,2),(2,'ENGLISH',2,2),(3,'MATHEMATICS',2,2);
/*!40000 ALTER TABLE `subjectmaster` ENABLE KEYS */;
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
  `joiningdate` date DEFAULT NULL,
  `leavingdate` date DEFAULT NULL,
  `bankname` varchar(500) DEFAULT NULL,
  `bankbranch` varchar(200) DEFAULT NULL,
  `bankifsc` varchar(50) DEFAULT NULL,
  `accno` varchar(500) DEFAULT NULL,
  `currentemployee` varchar(20) DEFAULT NULL,
  `userid` int(11) DEFAULT NULL,
  `employeephoto` longtext,
  `employeedoc1` longtext,
  `employeedoc2` longtext,
  `employeedoc3` longtext,
  `employeedoc4` longtext,
  `employeedoc5` longtext,
  PRIMARY KEY (`tid`),
  UNIQUE KEY `teacherexternalid_UNIQUE` (`teacherexternalid`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `teacher`
--

LOCK TABLES `teacher` WRITE;
/*!40000 ALTER TABLE `teacher` DISABLE KEYS */;
INSERT INTO `teacher` VALUES (2,'Aysha Ameera','TEACHER',NULL,'1999-12-10',NULL,'','NON-TEACHING','b.sc computer science','','Unknown','9940615320','','','female','FNPS02',2,'2025-05-05',NULL,'',NULL,'','','1',0,NULL,NULL,NULL,NULL,NULL,NULL);
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
  `userid` int(11) DEFAULT NULL,
  PRIMARY KEY (`tcid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `transfercertificate`
--

LOCK TABLES `transfercertificate` WRITE;
/*!40000 ALTER TABLE `transfercertificate` DISABLE KEYS */;
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
  `userid` int(11) DEFAULT NULL,
  PRIMARY KEY (`vouchermasterid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `vouchermaster`
--

LOCK TABLES `vouchermaster` WRITE;
/*!40000 ALTER TABLE `vouchermaster` DISABLE KEYS */;
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

-- Dump completed on 2025-06-10 15:27:29
