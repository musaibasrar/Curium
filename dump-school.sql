-- MySQL dump 10.13  Distrib 8.0.19, for Win64 (x86_64)
--
-- Host: localhost    Database: school
-- ------------------------------------------------------
-- Server version	11.6.2-MariaDB

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
-- Table structure for table `acc_accountdetails`
--

DROP TABLE IF EXISTS `acc_accountdetails`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
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
  KEY `FKn53q3oi6yakwed6heaaobb4bo` (`accountgroupid`),
  CONSTRAINT `FK96lgah8uh86o6p35tjk8kgpk3` FOREIGN KEY (`accountsubgroupmasterid`) REFERENCES `acc_accountsubgroupmaster` (`accountsubgroupmasterid`),
  CONSTRAINT `FK9tx946pcu5c46avc9yyogmvox` FOREIGN KEY (`ssgroupmasterid`) REFERENCES `acc_accountssubgroupmaster` (`ssgroupmasterid`),
  CONSTRAINT `FKn53q3oi6yakwed6heaaobb4bo` FOREIGN KEY (`accountgroupid`) REFERENCES `acc_accountgroupmaster` (`accountgroupid`),
  CONSTRAINT `acc_accountdetails_acc_accountssubgroupmaster_FK` FOREIGN KEY (`ssgroupmasterid`) REFERENCES `acc_accountssubgroupmaster` (`ssgroupmasterid`),
  CONSTRAINT `accountdetailsfk` FOREIGN KEY (`accountsubgroupmasterid`) REFERENCES `acc_accountsubgroupmaster` (`accountsubgroupmasterid`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=169 DEFAULT CHARSET=utf8mb3 COLLATE=utf8mb3_uca1400_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `acc_accountdetails`
--

LOCK TABLES `acc_accountdetails` WRITE;
/*!40000 ALTER TABLE `acc_accountdetails` DISABLE KEYS */;
INSERT INTO `acc_accountdetails` VALUES (1,1,'Cash in Hand',1,2,'ACA01',1,0),(2,1,'SBI Bank',1,2,'ACA02',2,0),(3,1,'Prepaid Insurance',1,2,'ACA03',3,0),(4,1,'Prepaid License',1,2,'ACA04',3,0),(5,1,'Unearned Student Fees ',1,2,'ACA06',4,0),(6,2,'School Building',1,2,'AFA01',5,0),(7,2,'Wooden Furniture',1,2,'AFA02',6,0),(8,2,'Steel & Metal Furniture',1,2,'AFA03',6,0),(9,2,'Electrical Items',1,2,'AFA04',6,0),(10,2,'Refrigerator',1,2,'AFA05',7,0),(11,2,'Power Generator',1,2,'AFA06',8,0),(12,2,'Tools & Equipment',1,2,'AFA07',9,0),(13,2,'Accumulated Depreciation on Building',1,2,'AFA08',10,0),(14,2,'Accumulated Depreciation on Furniture & Fixture',1,2,'AFA09',10,0),(15,2,'Accumulated Depreciation on Electronics Appliances',1,2,'AFA10',10,0),(16,2,'Accumulated Depreciation on Machinery',1,2,'AFA11',10,0),(17,2,'Accumulated Depreciation on Tools & Equipment',1,2,'AFA12',10,0),(18,3,'Loan Liabilities',2,2,'LCL01',11,0),(19,3,'Supplier A/c',2,2,'LCL02',12,0),(20,3,'Salary Payable',2,2,'LCL03',13,0),(21,3,'Rent Payable',2,2,'LCL04',13,0),(22,3,'Tax Payables',2,2,'LCL05',13,0),(23,3,'Other Outstanding Expenses',2,2,'LCL06',13,0),(24,4,'Other Non-Current Liabilities',2,2,'LNCL01',14,0),(25,3,'Payment Awaiting Settlement',2,2,'LCL07',13,0),(26,3,'Cheque Awaiting Settlement',2,2,'LCL08',13,0),(27,5,'Income from Student Fees',4,2,'IR01',15,0),(28,5,'Other Income',4,2,'IR02',16,0),(29,5,'Unearned Student Fee',4,2,'IR03',17,0),(30,6,'Rent Expenses',5,2,'EOE01',18,0),(31,6,'Salaries & Wages',5,2,'EOE02',18,0),(32,6,'License & Permits Expenses',5,2,'EOE03',18,0),(33,6,'Electricity Expenses',5,2,'EOE04',18,0),(34,6,'Drinking Water Expenses',5,2,'EOE05',18,0),(35,6,'Petrol & Fuel Expenses',5,2,'EOE06',18,0),(36,6,'Travelling & Transportation Expenses',5,2,'EOE07',18,0),(37,6,'Stationary Expenses',5,2,'EOE08',18,0),(38,6,'Vehicle Repair & Maintenance Exp',5,2,'EOE09',18,0),(39,6,'Miscellaneous Expenses',5,2,'EOE10',18,0),(40,7,'Expense Stock',5,2,'EOE11',19,0),(41,8,'Capital',3,2,'EQOE01',20,0),(42,8,'Retained Earnings',3,2,'EQOE02',20,0),(43,8,'Year Profits / Losses',3,2,'EQOE03',20,0),(44,8,'Owners Deposits',3,2,'EQOE04',20,0),(45,8,'Shareholders Deposits',3,2,'EQOE05',20,0),(46,8,'Shareholders Equity',3,2,'EQOE06',20,0),(47,9,'Cash in Hand',1,3,'ACA01',21,0),(48,9,'SBI Bank',1,3,'ACA02',22,0),(49,9,'Prepaid Insurance',1,3,'ACA03',23,0),(50,9,'Prepaid License',1,3,'ACA04',23,0),(51,9,'Unearned Student Fees ',1,3,'ACA06',24,0),(52,10,'School Building',1,3,'AFA01',25,0),(53,10,'Wooden Furniture',1,3,'AFA02',26,0),(54,10,'Steel & Metal Furniture',1,3,'AFA03',26,0),(55,10,'Electrical Items',1,3,'AFA04',26,0),(56,10,'Refrigerator',1,3,'AFA05',27,0),(57,10,'Power Generator',1,3,'AFA06',28,0),(58,10,'Tools & Equipment',1,3,'AFA07',29,0),(59,10,'Accumulated Depreciation on Building',1,3,'AFA08',30,0),(60,10,'Accumulated Depreciation on Furniture & Fixture',1,3,'AFA09',30,0),(61,10,'Accumulated Depreciation on Electronics Appliances',1,3,'AFA10',30,0),(62,10,'Accumulated Depreciation on Machinery',1,3,'AFA11',30,0),(63,10,'Accumulated Depreciation on Tools & Equipment',1,3,'AFA12',30,0),(64,11,'Loan Liabilities',2,3,'LCL01',31,0),(65,11,'Supplier A/c',2,3,'LCL02',32,0),(66,11,'Salary Payable',2,3,'LCL03',33,0),(67,11,'Rent Payable',2,3,'LCL04',33,0),(68,11,'Tax Payables',2,3,'LCL05',33,0),(69,11,'Other Outstanding Expenses',2,3,'LCL06',33,0),(70,12,'Other Non-Current Liabilities',2,3,'LNCL01',34,0),(71,11,'Payment Awaiting Settlement',2,3,'LCL07',33,0),(72,11,'Cheque Awaiting Settlement',2,3,'LCL08',33,0),(73,13,'Income from Student Fees',4,3,'IR01',35,0),(74,13,'Other Income',4,3,'IR02',36,0),(75,13,'Unearned Student Fee',4,3,'IR03',37,0),(76,14,'Rent Expenses',5,3,'EOE01',38,0),(77,14,'Salaries & Wages',5,3,'EOE02',38,0),(78,14,'License & Permits Expenses',5,3,'EOE03',38,0),(79,14,'Electricity Expenses',5,3,'EOE04',38,0),(80,14,'Drinking Water Expenses',5,3,'EOE05',38,0),(81,14,'Petrol & Fuel Expenses',5,3,'EOE06',38,0),(82,14,'Travelling & Transportation Expenses',5,3,'EOE07',38,0),(83,14,'Stationary Expenses',5,3,'EOE08',38,0),(84,14,'Vehicle Repair & Maintenance Exp',5,3,'EOE09',38,0),(85,14,'Miscellaneous Expenses',5,3,'EOE10',38,0),(86,15,'Expense Stock',5,3,'EOE11',39,0),(87,16,'Capital',3,3,'EQOE01',40,0),(88,16,'Retained Earnings',3,3,'EQOE02',40,0),(89,16,'Year Profits / Losses',3,3,'EQOE03',40,0),(90,16,'Owners Deposits',3,3,'EQOE04',40,0),(91,16,'Shareholders Deposits',3,3,'EQOE05',40,0),(92,16,'Shareholders Equity',3,3,'EQOE06',40,0),(93,17,'Cash in Hand',1,4,'ACA01',41,0),(94,17,'SBI Bank',1,4,'ACA02',42,0),(95,17,'Prepaid Insurance',1,4,'ACA03',43,0),(96,17,'Prepaid License',1,4,'ACA04',43,0),(97,17,'Unearned Student Fees ',1,4,'ACA06',44,0),(98,18,'School Building',1,4,'AFA01',45,0),(99,18,'Wooden Furniture',1,4,'AFA02',46,0),(100,18,'Steel & Metal Furniture',1,4,'AFA03',46,0),(101,18,'Electrical Items',1,4,'AFA04',46,0),(102,18,'Refrigerator',1,4,'AFA05',47,0),(103,18,'Power Generator',1,4,'AFA06',48,0),(104,18,'Tools & Equipment',1,4,'AFA07',49,0),(105,18,'Accumulated Depreciation on Building',1,4,'AFA08',50,0),(106,18,'Accumulated Depreciation on Furniture & Fixture',1,4,'AFA09',50,0),(107,18,'Accumulated Depreciation on Electronics Appliances',1,4,'AFA10',50,0),(108,18,'Accumulated Depreciation on Machinery',1,4,'AFA11',50,0),(109,18,'Accumulated Depreciation on Tools & Equipment',1,4,'AFA12',50,0),(110,19,'Loan Liabilities',2,4,'LCL01',51,0),(111,19,'Supplier A/c',2,4,'LCL02',52,0),(112,19,'Salary Payable',2,4,'LCL03',53,0),(113,19,'Rent Payable',2,4,'LCL04',53,0),(114,19,'Tax Payables',2,4,'LCL05',53,0),(115,19,'Other Outstanding Expenses',2,4,'LCL06',53,0),(116,20,'Other Non-Current Liabilities',2,4,'LNCL01',54,0),(117,19,'Payment Awaiting Settlement',2,4,'LCL07',53,0),(118,19,'Cheque Awaiting Settlement',2,4,'LCL08',53,0),(119,21,'Income from Student Fees',4,4,'IR01',55,0),(120,21,'Other Income',4,4,'IR02',56,0),(121,21,'Unearned Student Fee',4,4,'IR03',57,0),(122,22,'Rent Expenses',5,4,'EOE01',58,0),(123,22,'Salaries & Wages',5,4,'EOE02',58,0),(124,22,'License & Permits Expenses',5,4,'EOE03',58,0),(125,22,'Electricity Expenses',5,4,'EOE04',58,0),(126,22,'Drinking Water Expenses',5,4,'EOE05',58,0),(127,22,'Petrol & Fuel Expenses',5,4,'EOE06',58,0),(128,22,'Travelling & Transportation Expenses',5,4,'EOE07',58,0),(129,22,'Stationary Expenses',5,4,'EOE08',58,0),(130,22,'Vehicle Repair & Maintenance Exp',5,4,'EOE09',58,0),(131,22,'Miscellaneous Expenses',5,4,'EOE10',58,0),(132,23,'Expense Stock',5,4,'EOE11',59,0),(133,24,'Capital',3,4,'EQOE01',60,0),(134,24,'Retained Earnings',3,4,'EQOE02',60,0),(135,24,'Year Profits / Losses',3,4,'EQOE03',60,0),(136,24,'Owners Deposits',3,4,'EQOE04',60,0),(137,24,'Shareholders Deposits',3,4,'EQOE05',60,0),(138,24,'Shareholders Equity',3,4,'EQOE06',60,0),(164,NULL,'Test Account',1,2,'TA001',NULL,NULL),(165,NULL,'Test Account3',1,2,'TA003',NULL,NULL),(166,NULL,'Test Account4',1,2,'TA004',NULL,NULL),(167,NULL,'Test Account5',1,2,'TA005',NULL,NULL),(168,NULL,'Test Account6',1,2,'TA006',NULL,NULL);
/*!40000 ALTER TABLE `acc_accountdetails` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `acc_accountdetailsbalance`
--

DROP TABLE IF EXISTS `acc_accountdetailsbalance`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
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
  CONSTRAINT `FKl5ri73rv6onrvqmsetlnrp80s` FOREIGN KEY (`financialid`) REFERENCES `acc_financialaccountingyear` (`financialid`),
  CONSTRAINT `FKtotj219sxifdpjgxid4sx6egy` FOREIGN KEY (`accountdetailsid`) REFERENCES `acc_accountdetails` (`accountdetailsid`),
  CONSTRAINT `accoutdetailsidfk` FOREIGN KEY (`accountdetailsid`) REFERENCES `acc_accountdetails` (`accountdetailsid`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `financialidfk` FOREIGN KEY (`financialid`) REFERENCES `acc_financialaccountingyear` (`financialid`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=168 DEFAULT CHARSET=utf8mb3 COLLATE=utf8mb3_uca1400_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `acc_accountdetailsbalance`
--

LOCK TABLES `acc_accountdetailsbalance` WRITE;
/*!40000 ALTER TABLE `acc_accountdetailsbalance` DISABLE KEYS */;
INSERT INTO `acc_accountdetailsbalance` VALUES (1,1,0.0000,0.0000,1,'Dr','2022-01-05',2,0),(2,2,0.0000,0.0000,1,'Dr','2022-01-05',2,0),(3,3,0.0000,0.0000,1,'Dr','2022-01-05',2,0),(4,4,0.0000,0.0000,1,'Dr','2022-01-05',2,0),(5,5,0.0000,0.0000,1,'Dr','2022-01-05',2,0),(6,6,0.0000,0.0000,1,'Dr','2022-01-05',2,0),(7,7,0.0000,0.0000,1,'Dr','2022-01-05',2,0),(8,8,0.0000,0.0000,1,'Dr','2022-01-05',2,0),(9,9,0.0000,0.0000,1,'Dr','2022-01-05',2,0),(10,10,0.0000,0.0000,1,'Dr','2022-01-05',2,0),(11,11,0.0000,0.0000,1,'Dr','2022-01-05',2,0),(12,12,0.0000,0.0000,1,'Dr','2022-01-05',2,0),(13,13,0.0000,0.0000,1,'Dr','2022-01-05',2,0),(14,14,0.0000,0.0000,1,'Dr','2022-01-05',2,0),(15,15,0.0000,0.0000,1,'Dr','2022-01-05',2,0),(16,16,0.0000,0.0000,1,'Dr','2022-01-05',2,0),(17,17,0.0000,0.0000,1,'Dr','2022-01-05',2,0),(18,18,0.0000,0.0000,1,'Cr','2022-01-05',2,0),(19,19,0.0000,0.0000,1,'Cr','2022-01-05',2,0),(20,20,0.0000,0.0000,1,'Cr','2022-01-05',2,0),(21,21,0.0000,0.0000,1,'Cr','2022-01-05',2,0),(22,22,0.0000,0.0000,1,'Cr','2022-01-05',2,0),(23,23,0.0000,0.0000,1,'Cr','2022-01-05',2,0),(24,24,0.0000,0.0000,1,'Cr','2022-01-05',2,0),(25,25,0.0000,0.0000,1,'Cr','2022-01-05',2,0),(26,26,0.0000,0.0000,1,'Cr','2022-01-05',2,0),(27,27,0.0000,0.0000,1,'Cr','2022-01-05',2,0),(28,28,0.0000,0.0000,1,'Cr','2022-01-05',2,0),(29,29,0.0000,0.0000,1,'Cr','2022-01-05',2,0),(30,30,0.0000,0.0000,1,'Dr','2022-01-05',2,0),(31,31,0.0000,0.0000,1,'Dr','2022-01-05',2,0),(32,32,0.0000,0.0000,1,'Dr','2022-01-05',2,0),(33,33,0.0000,0.0000,1,'Dr','2022-01-05',2,0),(34,34,0.0000,0.0000,1,'Dr','2022-01-05',2,0),(35,35,0.0000,0.0000,1,'Dr','2022-01-05',2,0),(36,36,0.0000,0.0000,1,'Dr','2022-01-05',2,0),(37,37,0.0000,0.0000,1,'Dr','2022-01-05',2,0),(38,38,0.0000,0.0000,1,'Dr','2022-01-05',2,0),(39,39,0.0000,0.0000,1,'Dr','2022-01-05',2,0),(40,40,0.0000,0.0000,1,'Dr','2022-01-05',2,0),(41,41,0.0000,0.0000,1,'Cr','2022-01-05',2,0),(42,42,0.0000,0.0000,1,'Cr','2022-01-05',2,0),(43,43,0.0000,0.0000,1,'Cr','2022-01-05',2,0),(44,44,0.0000,0.0000,1,'Cr','2022-01-05',2,0),(45,45,0.0000,0.0000,1,'Cr','2022-01-05',2,0),(46,46,0.0000,0.0000,1,'Cr','2022-01-05',2,0),(47,47,0.0000,0.0000,1,'Dr','2022-01-05',3,0),(48,48,0.0000,0.0000,1,'Dr','2022-01-05',3,0),(49,49,0.0000,0.0000,1,'Dr','2022-01-05',3,0),(50,50,0.0000,0.0000,1,'Dr','2022-01-05',3,0),(51,51,0.0000,0.0000,1,'Dr','2022-01-05',3,0),(52,52,0.0000,0.0000,1,'Dr','2022-01-05',3,0),(53,53,0.0000,0.0000,1,'Dr','2022-01-05',3,0),(54,54,0.0000,0.0000,1,'Dr','2022-01-05',3,0),(55,55,0.0000,0.0000,1,'Dr','2022-01-05',3,0),(56,56,0.0000,0.0000,1,'Dr','2022-01-05',3,0),(57,57,0.0000,0.0000,1,'Dr','2022-01-05',3,0),(58,58,0.0000,0.0000,1,'Dr','2022-01-05',3,0),(59,59,0.0000,0.0000,1,'Dr','2022-01-05',3,0),(60,60,0.0000,0.0000,1,'Dr','2022-01-05',3,0),(61,61,0.0000,0.0000,1,'Dr','2022-01-05',3,0),(62,62,0.0000,0.0000,1,'Dr','2022-01-05',3,0),(63,63,0.0000,0.0000,1,'Dr','2022-01-05',3,0),(64,64,0.0000,0.0000,1,'Cr','2022-01-05',3,0),(65,65,0.0000,0.0000,1,'Cr','2022-01-05',3,0),(66,66,0.0000,0.0000,1,'Cr','2022-01-05',3,0),(67,67,0.0000,0.0000,1,'Cr','2022-01-05',3,0),(68,68,0.0000,0.0000,1,'Cr','2022-01-05',3,0),(69,69,0.0000,0.0000,1,'Cr','2022-01-05',3,0),(70,70,0.0000,0.0000,1,'Cr','2022-01-05',3,0),(71,71,0.0000,0.0000,1,'Cr','2022-01-05',3,0),(72,72,0.0000,0.0000,1,'Cr','2022-01-05',3,0),(73,73,0.0000,0.0000,1,'Cr','2022-01-05',3,0),(74,74,0.0000,0.0000,1,'Cr','2022-01-05',3,0),(75,75,0.0000,0.0000,1,'Cr','2022-01-05',3,0),(76,76,0.0000,0.0000,1,'Dr','2022-01-05',3,0),(77,77,0.0000,0.0000,1,'Dr','2022-01-05',3,0),(78,78,0.0000,0.0000,1,'Dr','2022-01-05',3,0),(79,79,0.0000,0.0000,1,'Dr','2022-01-05',3,0),(80,80,0.0000,0.0000,1,'Dr','2022-01-05',3,0),(81,81,0.0000,0.0000,1,'Dr','2022-01-05',3,0),(82,82,0.0000,0.0000,1,'Dr','2022-01-05',3,0),(83,83,0.0000,0.0000,1,'Dr','2022-01-05',3,0),(84,84,0.0000,0.0000,1,'Dr','2022-01-05',3,0),(85,85,0.0000,0.0000,1,'Dr','2022-01-05',3,0),(86,86,0.0000,0.0000,1,'Dr','2022-01-05',3,0),(87,87,0.0000,0.0000,1,'Cr','2022-01-05',3,0),(88,88,0.0000,0.0000,1,'Cr','2022-01-05',3,0),(89,89,0.0000,0.0000,1,'Cr','2022-01-05',3,0),(90,90,0.0000,0.0000,1,'Cr','2022-01-05',3,0),(91,91,0.0000,0.0000,1,'Cr','2022-01-05',3,0),(92,92,0.0000,0.0000,1,'Cr','2022-01-05',3,0),(93,93,0.0000,0.0000,1,'Dr','2022-01-05',4,0),(94,94,0.0000,0.0000,1,'Dr','2022-01-05',4,0),(95,95,0.0000,0.0000,1,'Dr','2022-01-05',4,0),(96,96,0.0000,0.0000,1,'Dr','2022-01-05',4,0),(97,97,0.0000,0.0000,1,'Dr','2022-01-05',4,0),(98,98,0.0000,0.0000,1,'Dr','2022-01-05',4,0),(99,99,0.0000,0.0000,1,'Dr','2022-01-05',4,0),(100,100,0.0000,0.0000,1,'Dr','2022-01-05',4,0),(101,101,0.0000,0.0000,1,'Dr','2022-01-05',4,0),(102,102,0.0000,0.0000,1,'Dr','2022-01-05',4,0),(103,103,0.0000,0.0000,1,'Dr','2022-01-05',4,0),(104,104,0.0000,0.0000,1,'Dr','2022-01-05',4,0),(105,105,0.0000,0.0000,1,'Dr','2022-01-05',4,0),(106,106,0.0000,0.0000,1,'Dr','2022-01-05',4,0),(107,107,0.0000,0.0000,1,'Dr','2022-01-05',4,0),(108,108,0.0000,0.0000,1,'Dr','2022-01-05',4,0),(109,109,0.0000,0.0000,1,'Dr','2022-01-05',4,0),(110,110,0.0000,0.0000,1,'Cr','2022-01-05',4,0),(111,111,0.0000,0.0000,1,'Cr','2022-01-05',4,0),(112,112,0.0000,0.0000,1,'Cr','2022-01-05',4,0),(113,113,0.0000,0.0000,1,'Cr','2022-01-05',4,0),(114,114,0.0000,0.0000,1,'Cr','2022-01-05',4,0),(115,115,0.0000,0.0000,1,'Cr','2022-01-05',4,0),(116,116,0.0000,0.0000,1,'Cr','2022-01-05',4,0),(117,117,0.0000,0.0000,1,'Cr','2022-01-05',4,0),(118,118,0.0000,0.0000,1,'Cr','2022-01-05',4,0),(119,119,0.0000,0.0000,1,'Cr','2022-01-05',4,0),(120,120,0.0000,0.0000,1,'Cr','2022-01-05',4,0),(121,121,0.0000,0.0000,1,'Cr','2022-01-05',4,0),(122,122,0.0000,0.0000,1,'Dr','2022-01-05',4,0),(123,123,0.0000,0.0000,1,'Dr','2022-01-05',4,0),(124,124,0.0000,0.0000,1,'Dr','2022-01-05',4,0),(125,125,0.0000,0.0000,1,'Dr','2022-01-05',4,0),(126,126,0.0000,0.0000,1,'Dr','2022-01-05',4,0),(127,127,0.0000,0.0000,1,'Dr','2022-01-05',4,0),(128,128,0.0000,0.0000,1,'Dr','2022-01-05',4,0),(129,129,0.0000,0.0000,1,'Dr','2022-01-05',4,0),(130,130,0.0000,0.0000,1,'Dr','2022-01-05',4,0),(131,131,0.0000,0.0000,1,'Dr','2022-01-05',4,0),(132,132,0.0000,0.0000,1,'Dr','2022-01-05',4,0),(133,133,0.0000,0.0000,1,'Cr','2022-01-05',4,0),(134,134,0.0000,0.0000,1,'Cr','2022-01-05',4,0),(135,135,0.0000,0.0000,1,'Cr','2022-01-05',4,0),(136,136,0.0000,0.0000,1,'Cr','2022-01-05',4,0),(137,137,0.0000,0.0000,1,'Cr','2022-01-05',4,0),(138,138,0.0000,0.0000,1,'Cr','2022-01-05',4,0),(163,164,0.0000,0.0000,4,'Dr','2025-03-10',2,NULL),(164,165,0.0000,0.0000,4,'Dr','2025-03-10',2,NULL),(165,166,0.0000,0.0000,4,'Dr','2025-03-10',2,NULL),(166,167,0.0000,0.0000,4,'Dr','2025-03-10',2,NULL),(167,168,0.0000,0.0000,4,'Dr','2025-03-10',2,NULL);
/*!40000 ALTER TABLE `acc_accountdetailsbalance` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `acc_accountgroupmaster`
--

DROP TABLE IF EXISTS `acc_accountgroupmaster`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `acc_accountgroupmaster` (
  `accountgroupid` int(11) NOT NULL AUTO_INCREMENT,
  `accountgroupname` varchar(150) NOT NULL,
  PRIMARY KEY (`accountgroupid`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb3 COLLATE=utf8mb3_uca1400_ai_ci;
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
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `acc_accountssubgroupmaster` (
  `ssgroupmasterid` int(11) NOT NULL AUTO_INCREMENT,
  `ssgroupname` varchar(100) DEFAULT NULL,
  `subgroupmasterid` int(11) NOT NULL,
  `branchid` int(11) DEFAULT NULL,
  `userid` int(11) DEFAULT NULL,
  PRIMARY KEY (`ssgroupmasterid`),
  KEY `acc_accountssubgroupmaster_acc_accountsubgroupmaster_FK` (`subgroupmasterid`),
  CONSTRAINT `FKh8wf9d1cn6dqc6e6oyfmudyp9` FOREIGN KEY (`subgroupmasterid`) REFERENCES `acc_accountsubgroupmaster` (`accountsubgroupmasterid`),
  CONSTRAINT `acc_accountssubgroupmaster_acc_accountsubgroupmaster_FK` FOREIGN KEY (`subgroupmasterid`) REFERENCES `acc_accountsubgroupmaster` (`accountsubgroupmasterid`)
) ENGINE=InnoDB AUTO_INCREMENT=64 DEFAULT CHARSET=latin1 COLLATE=latin1_swedish_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `acc_accountssubgroupmaster`
--

LOCK TABLES `acc_accountssubgroupmaster` WRITE;
/*!40000 ALTER TABLE `acc_accountssubgroupmaster` DISABLE KEYS */;
INSERT INTO `acc_accountssubgroupmaster` VALUES (1,'Cash In Hand',1,2,1),(2,'Cash In Bank',1,2,1),(3,'Prepayment',1,2,1),(4,'Receivables',1,2,1),(5,'Land & Building',2,2,1),(6,'Furniture & Fixture',2,2,1),(7,'Electronic Appliances',2,2,1),(8,'Machinery',2,2,1),(9,'Tools & Equipment',2,2,1),(10,'Accumulated Depreciation on Fixed Assets',2,2,1),(11,'Loan Liabilities',3,2,1),(12,'Payable Suppliers',3,2,1),(13,'Payable Others',3,2,1),(14,'Other Non-Current Liabilities',4,2,1),(15,'Student Fees',5,2,1),(16,'Other Income',5,2,1),(17,'General & Administrative',6,2,1),(18,'Owners\' Equity',7,2,1),(19,'Unearned Income',5,2,1),(20,'Stock',1,2,1),(21,'Cost of Sales',8,2,1),(22,'Cash In Hand',9,3,1),(23,'Cash In Bank',9,3,1),(24,'Prepayment',9,3,1),(25,'Receivables',9,3,1),(26,'Land & Building',10,3,1),(27,'Furniture & Fixture',10,3,1),(28,'Electronic Appliances',10,3,1),(29,'Machinery',10,3,1),(30,'Tools & Equipment',10,3,1),(31,'Accumulated Depreciation on Fixed Assets',10,3,1),(32,'Loan Liabilities',11,3,1),(33,'Payable Suppliers',11,3,1),(34,'Payable Others',11,3,1),(35,'Other Non-Current Liabilities',12,3,1),(36,'Student Fees',13,3,1),(37,'Other Income',13,3,1),(38,'General & Administrative',14,3,1),(39,'Owners\' Equity',15,3,1),(40,'Unearned Income',13,3,1),(41,'Stock',9,3,1),(42,'Cost of Sales',16,3,1),(43,'Cash In Hand',17,4,1),(44,'Cash In Bank',17,4,1),(45,'Prepayment',17,4,1),(46,'Receivables',17,4,1),(47,'Land & Building',18,4,1),(48,'Furniture & Fixture',18,4,1),(49,'Electronic Appliances',18,4,1),(50,'Machinery',18,4,1),(51,'Tools & Equipment',18,4,1),(52,'Accumulated Depreciation on Fixed Assets',18,4,1),(53,'Loan Liabilities',19,4,1),(54,'Payable Suppliers',19,4,1),(55,'Payable Others',19,4,1),(56,'Other Non-Current Liabilities',20,4,1),(57,'Student Fees',21,4,1),(58,'Other Income',21,4,1),(59,'General & Administrative',22,4,1),(60,'Owners\' Equity',23,4,1),(61,'Unearned Income',24,4,1),(62,'Stock',17,4,1),(63,'Cost of Sales',24,4,1);
/*!40000 ALTER TABLE `acc_accountssubgroupmaster` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `acc_accountsubgroupmaster`
--

DROP TABLE IF EXISTS `acc_accountsubgroupmaster`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `acc_accountsubgroupmaster` (
  `accountsubgroupmasterid` int(11) NOT NULL AUTO_INCREMENT,
  `accountsubgroupname` varchar(100) DEFAULT NULL,
  `accountgroupid` int(11) DEFAULT NULL,
  `branchid` int(11) DEFAULT NULL,
  `userid` int(11) DEFAULT NULL,
  PRIMARY KEY (`accountsubgroupmasterid`),
  KEY `accountgroupid_idx` (`accountgroupid`),
  CONSTRAINT `FK15x3bo1506g4h7ukc31xhndrf` FOREIGN KEY (`accountgroupid`) REFERENCES `acc_accountgroupmaster` (`accountgroupid`),
  CONSTRAINT `accountgroupid` FOREIGN KEY (`accountgroupid`) REFERENCES `acc_accountgroupmaster` (`accountgroupid`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=25 DEFAULT CHARSET=utf8mb3 COLLATE=utf8mb3_uca1400_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `acc_accountsubgroupmaster`
--

LOCK TABLES `acc_accountsubgroupmaster` WRITE;
/*!40000 ALTER TABLE `acc_accountsubgroupmaster` DISABLE KEYS */;
INSERT INTO `acc_accountsubgroupmaster` VALUES (1,'Current Assets',1,2,1),(2,'Fixed Assets',1,2,1),(3,'Current Liabilities',2,2,1),(4,'Non-Current Liabilities',2,2,1),(5,'Revenue',4,2,1),(6,'Operating Expenses',5,2,1),(7,'Owners\' Equity',3,2,1),(8,'Cost of Revenue',5,2,1),(9,'Current Assets',1,3,1),(10,'Fixed Assets',1,3,1),(11,'Current Liabilities',2,3,1),(12,'Non-Current Liabilities',2,3,1),(13,'Revenue',4,3,1),(14,'Operating Expenses',5,3,1),(15,'Owners\' Equity',3,3,1),(16,'Cost of Revenue',5,3,1),(17,'Current Assets',1,4,1),(18,'Fixed Assets',1,4,1),(19,'Current Liabilities',2,4,1),(20,'Non-Current Liabilities',2,4,1),(21,'Revenue',4,4,1),(22,'Operating Expenses',5,4,1),(23,'Owners\' Equity',3,4,1),(24,'Cost of Revenue',5,4,1);
/*!40000 ALTER TABLE `acc_accountsubgroupmaster` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `acc_contratransactions`
--

DROP TABLE IF EXISTS `acc_contratransactions`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
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
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3 COLLATE=utf8mb3_uca1400_ai_ci;
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
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `acc_financialaccountingyear` (
  `financialid` int(11) NOT NULL AUTO_INCREMENT,
  `financialstartdate` date NOT NULL,
  `financialenddate` date NOT NULL,
  `active` varchar(10) NOT NULL,
  `branchid` int(11) DEFAULT NULL,
  `userid` int(11) DEFAULT NULL,
  PRIMARY KEY (`financialid`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb3 COLLATE=utf8mb3_uca1400_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `acc_financialaccountingyear`
--

LOCK TABLES `acc_financialaccountingyear` WRITE;
/*!40000 ALTER TABLE `acc_financialaccountingyear` DISABLE KEYS */;
INSERT INTO `acc_financialaccountingyear` VALUES (1,'2024-04-01','2025-03-31','no',2,1),(2,'2024-04-01','2025-03-31','yes',3,1),(3,'2024-04-01','2025-03-31','yes',4,1),(4,'2025-04-01','2026-03-31','yes',2,0);
/*!40000 ALTER TABLE `acc_financialaccountingyear` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `acc_journaltransactions`
--

DROP TABLE IF EXISTS `acc_journaltransactions`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
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
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3 COLLATE=utf8mb3_uca1400_ai_ci;
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
/*!50503 SET character_set_client = utf8mb4 */;
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
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3 COLLATE=utf8mb3_uca1400_ai_ci;
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
/*!50503 SET character_set_client = utf8mb4 */;
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
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3 COLLATE=utf8mb3_uca1400_ai_ci;
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
/*!50503 SET character_set_client = utf8mb4 */;
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
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb3 COLLATE=utf8mb3_uca1400_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `acc_voucherentrytransactions`
--

LOCK TABLES `acc_voucherentrytransactions` WRITE;
/*!40000 ALTER TABLE `acc_voucherentrytransactions` DISABLE KEYS */;
INSERT INTO `acc_voucherentrytransactions` VALUES (1,30,29,10000.00000,NULL,NULL,'2024-06-06',NULL,2024,'no',2,'2024-06-06',NULL,1),(2,29,30,NULL,5000.00000,NULL,'2024-07-07',NULL,2024,'no',2,'2024-07-07',NULL,1);
/*!40000 ALTER TABLE `acc_voucherentrytransactions` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `adminexpenses`
--

DROP TABLE IF EXISTS `adminexpenses`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
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
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=latin1 COLLATE=latin1_swedish_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `adminexpenses`
--

LOCK TABLES `adminexpenses` WRITE;
/*!40000 ALTER TABLE `adminexpenses` DISABLE KEYS */;
INSERT INTO `adminexpenses` VALUES (1,'someitem','1234','2025-04-01',NULL,2,'someone','12345','pending','cheque','2025-04-01','sbi',1);
/*!40000 ALTER TABLE `adminexpenses` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `att_attendancemaster`
--

DROP TABLE IF EXISTS `att_attendancemaster`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
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
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3 COLLATE=utf8mb3_uca1400_ai_ci;
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
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `att_holidaysmaster` (
  `shid` int(11) NOT NULL AUTO_INCREMENT,
  `fromdate` date NOT NULL,
  `todate` date NOT NULL,
  `holidayname` varchar(40) NOT NULL,
  `academicyear` varchar(10) NOT NULL,
  `branchid` int(11) DEFAULT NULL,
  `userid` int(11) DEFAULT NULL,
  PRIMARY KEY (`shid`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb3 COLLATE=utf8mb3_uca1400_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `att_holidaysmaster`
--

LOCK TABLES `att_holidaysmaster` WRITE;
/*!40000 ALTER TABLE `att_holidaysmaster` DISABLE KEYS */;
INSERT INTO `att_holidaysmaster` VALUES (1,'2025-05-18','2025-05-18','Birthday','2025',1,0);
/*!40000 ALTER TABLE `att_holidaysmaster` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `att_staffdailyattendance`
--

DROP TABLE IF EXISTS `att_staffdailyattendance`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
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
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3 COLLATE=utf8mb3_uca1400_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `att_staffdailyattendance`
--

LOCK TABLES `att_staffdailyattendance` WRITE;
/*!40000 ALTER TABLE `att_staffdailyattendance` DISABLE KEYS */;
/*!40000 ALTER TABLE `att_staffdailyattendance` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `att_studentdailyattendance`
--

DROP TABLE IF EXISTS `att_studentdailyattendance`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
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
  CONSTRAINT `FK705iae9k8xpys148558pexj0l` FOREIGN KEY (`attendeeid`) REFERENCES `student` (`studentexternalid`),
  CONSTRAINT `stdfk` FOREIGN KEY (`attendeeid`) REFERENCES `student` (`studentexternalid`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3 COLLATE=utf8mb3_uca1400_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `att_studentdailyattendance`
--

LOCK TABLES `att_studentdailyattendance` WRITE;
/*!40000 ALTER TABLE `att_studentdailyattendance` DISABLE KEYS */;
/*!40000 ALTER TABLE `att_studentdailyattendance` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `att_weeklyoff`
--

DROP TABLE IF EXISTS `att_weeklyoff`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `att_weeklyoff` (
  `wid` int(11) NOT NULL AUTO_INCREMENT,
  `weeklyoffday` varchar(100) NOT NULL,
  `academicyear` varchar(10) DEFAULT NULL,
  `branchid` int(11) DEFAULT NULL,
  `userid` int(11) DEFAULT NULL,
  PRIMARY KEY (`wid`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb3 COLLATE=utf8mb3_uca1400_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `att_weeklyoff`
--

LOCK TABLES `att_weeklyoff` WRITE;
/*!40000 ALTER TABLE `att_weeklyoff` DISABLE KEYS */;
INSERT INTO `att_weeklyoff` VALUES (1,'Sunday','2025',1,0);
/*!40000 ALTER TABLE `att_weeklyoff` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `book`
--

DROP TABLE IF EXISTS `book`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
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
  PRIMARY KEY (`bid`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COLLATE=latin1_swedish_ci;
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
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `bookhistory` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `actualreturndate` datetime(6) DEFAULT NULL,
  `bid` varchar(200) DEFAULT NULL,
  `bookname` varchar(200) DEFAULT NULL,
  `expectedreturn_date` datetime(6) DEFAULT NULL,
  `issuedate` datetime(6) DEFAULT NULL,
  `sid` varchar(45) DEFAULT NULL,
  `studentname` varchar(200) DEFAULT NULL,
  `uid` varchar(200) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_uca1400_ai_ci;
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
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `bookissue` (
  `accountdetailsid` int(11) NOT NULL AUTO_INCREMENT,
  `actualreturndate` datetime(6) DEFAULT NULL,
  `bookholder` varchar(100) DEFAULT NULL,
  `bookid` int(11) DEFAULT NULL,
  `bookname` varchar(100) DEFAULT NULL,
  `enddate` datetime(6) DEFAULT NULL,
  `noofdays` int(11) DEFAULT NULL,
  `returned` varchar(50) DEFAULT NULL,
  `sid` int(11) DEFAULT NULL,
  `startdate` datetime(6) DEFAULT NULL,
  `studentname` varchar(200) DEFAULT NULL,
  PRIMARY KEY (`accountdetailsid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_uca1400_ai_ci;
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
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `branch` (
  `idbranch` int(11) NOT NULL AUTO_INCREMENT,
  `branchname` varchar(100) DEFAULT NULL,
  `userid` int(11) DEFAULT NULL,
  `branchcode` varchar(50) DEFAULT NULL,
  `address` varchar(500) DEFAULT NULL,
  `contact` varchar(200) DEFAULT NULL,
  PRIMARY KEY (`idbranch`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb3 COLLATE=utf8mb3_uca1400_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `branch`
--

LOCK TABLES `branch` WRITE;
/*!40000 ALTER TABLE `branch` DISABLE KEYS */;
INSERT INTO `branch` VALUES (1,'Head Office',1,'GIA','Talimabad, Eidgah Road, Malmal, Kaluahi, Madhubani, Bihar- 847229','Contact No.: 8757864274 Email:8757864274 Website:http://www.greatindiaacademy.com'),(2,'Great India Academy',1,'GIA','Talimabad, Eidgah Road, Malmal, Kaluahi, Madhubani, Bihar- 847229','Contact No.: 8757864274 Email:8757864274 Website:http://www.greatindiaacademy.com'),(3,'Great India Academy',1,'GIA','Talimabad, Eidgah Road, Malmal, Kaluahi, Madhubani, Bihar- 847229','Contact No.: 8757864274 Email:8757864274 Website:http://www.greatindiaacademy.com'),(4,'Great India Academy',1,'GIA','Talimabad, Eidgah Road, Malmal, Kaluahi, Madhubani, Bihar- 847229','Contact No.: 8757864274 Email:8757864274 Website:http://www.greatindiaacademy.com');
/*!40000 ALTER TABLE `branch` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `classhierarchy`
--

DROP TABLE IF EXISTS `classhierarchy`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `classhierarchy` (
  `idclasshierarchy` int(11) NOT NULL AUTO_INCREMENT,
  `lowerclass` varchar(45) DEFAULT NULL,
  `upperclass` varchar(45) DEFAULT NULL,
  `branchid` int(11) DEFAULT NULL,
  `userid` int(11) DEFAULT NULL,
  PRIMARY KEY (`idclasshierarchy`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COLLATE=latin1_swedish_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `classhierarchy`
--

LOCK TABLES `classhierarchy` WRITE;
/*!40000 ALTER TABLE `classhierarchy` DISABLE KEYS */;
/*!40000 ALTER TABLE `classhierarchy` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `classsec`
--

DROP TABLE IF EXISTS `classsec`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `classsec` (
  `stdrdid` int(11) NOT NULL AUTO_INCREMENT,
  `classdetails` varchar(45) DEFAULT NULL,
  `section` varchar(45) DEFAULT NULL,
  `branchid` int(11) DEFAULT NULL,
  `userid` int(11) DEFAULT NULL,
  PRIMARY KEY (`stdrdid`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8mb3 COLLATE=utf8mb3_uca1400_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `classsec`
--

LOCK TABLES `classsec` WRITE;
/*!40000 ALTER TABLE `classsec` DISABLE KEYS */;
INSERT INTO `classsec` VALUES (1,'I','',2,2),(2,'II','',2,2),(3,'','A',2,2),(5,'','B',2,2),(6,'I','',1,1),(7,'','A',1,1),(8,'II','',1,1),(9,'III','',1,1),(10,'','B',1,1);
/*!40000 ALTER TABLE `classsec` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `currentacademicyear`
--

DROP TABLE IF EXISTS `currentacademicyear`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `currentacademicyear` (
  `cayid` int(11) NOT NULL AUTO_INCREMENT,
  `currentacademicyear` varchar(100) DEFAULT NULL,
  `userid` int(11) DEFAULT NULL,
  PRIMARY KEY (`cayid`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb3 COLLATE=utf8mb3_uca1400_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `currentacademicyear`
--

LOCK TABLES `currentacademicyear` WRITE;
/*!40000 ALTER TABLE `currentacademicyear` DISABLE KEYS */;
INSERT INTO `currentacademicyear` VALUES (1,'2024/25',1);
/*!40000 ALTER TABLE `currentacademicyear` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `degreedetails`
--

DROP TABLE IF EXISTS `degreedetails`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
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
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8mb3 COLLATE=utf8mb3_uca1400_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `degreedetails`
--

LOCK TABLES `degreedetails` WRITE;
/*!40000 ALTER TABLE `degreedetails` DISABLE KEYS */;
INSERT INTO `degreedetails` VALUES (3,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(4,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(5,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(6,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(7,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(8,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(9,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(10,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL);
/*!40000 ALTER TABLE `degreedetails` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `department`
--

DROP TABLE IF EXISTS `department`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `department` (
  `depid` int(11) NOT NULL AUTO_INCREMENT,
  `departmentname` varchar(100) DEFAULT NULL,
  `branchid` int(11) DEFAULT NULL,
  `userid` int(11) DEFAULT NULL,
  PRIMARY KEY (`depid`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb3 COLLATE=utf8mb3_uca1400_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `department`
--

LOCK TABLES `department` WRITE;
/*!40000 ALTER TABLE `department` DISABLE KEYS */;
INSERT INTO `department` VALUES (1,'Teaching',1,0);
/*!40000 ALTER TABLE `department` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `diary`
--

DROP TABLE IF EXISTS `diary`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
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
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COLLATE=latin1_swedish_ci;
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
/*!50503 SET character_set_client = utf8mb4 */;
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
  `created` date DEFAULT NULL,
  `branchid` int(11) DEFAULT NULL,
  `userid` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=latin1 COLLATE=latin1_swedish_ci;
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
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `examrank` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `sid` int(11) DEFAULT NULL,
  `examid` int(11) DEFAULT NULL,
  `marksobtained` float DEFAULT NULL,
  `status` varchar(20) DEFAULT NULL,
  `rank` int(11) DEFAULT NULL,
  `academicyear` varchar(20) DEFAULT NULL,
  `branchid` int(11) DEFAULT NULL,
  `userid` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `sid` (`sid`),
  KEY `examid` (`examid`),
  CONSTRAINT `FKr8tcx76qltys68xaxry81skhx` FOREIGN KEY (`sid`) REFERENCES `student` (`sid`),
  CONSTRAINT `FKs8anp77iy139t95cmqkqglrir` FOREIGN KEY (`examid`) REFERENCES `exams` (`exid`),
  CONSTRAINT `examrank_ibfk_1` FOREIGN KEY (`sid`) REFERENCES `student` (`sid`),
  CONSTRAINT `examrank_ibfk_2` FOREIGN KEY (`examid`) REFERENCES `exams` (`exid`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COLLATE=latin1_swedish_ci;
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
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `exams` (
  `exid` int(11) NOT NULL AUTO_INCREMENT,
  `examname` varchar(45) DEFAULT NULL,
  `branchid` int(11) DEFAULT NULL,
  `userid` int(11) DEFAULT NULL,
  PRIMARY KEY (`exid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3 COLLATE=utf8mb3_uca1400_ai_ci;
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
/*!50503 SET character_set_client = utf8mb4 */;
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
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3 COLLATE=utf8mb3_uca1400_ai_ci;
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
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `fee_academicfeesstructure` (
  `feesstructureid` int(11) NOT NULL AUTO_INCREMENT,
  `sid` int(11) NOT NULL,
  `totalfees` decimal(10,0) DEFAULT NULL,
  `paidfees` decimal(10,0) DEFAULT 0,
  `academicyear` varchar(45) DEFAULT NULL,
  `branchid` int(11) DEFAULT NULL,
  `userid` int(11) DEFAULT NULL,
  PRIMARY KEY (`feesstructureid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3 COLLATE=utf8mb3_uca1400_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `fee_academicfeesstructure`
--

LOCK TABLES `fee_academicfeesstructure` WRITE;
/*!40000 ALTER TABLE `fee_academicfeesstructure` DISABLE KEYS */;
/*!40000 ALTER TABLE `fee_academicfeesstructure` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `fee_feescategory`
--

DROP TABLE IF EXISTS `fee_feescategory`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `fee_feescategory` (
  `idfeescategory` int(11) NOT NULL AUTO_INCREMENT,
  `feescategoryname` varchar(150) DEFAULT NULL,
  `amount` int(11) DEFAULT NULL,
  `particularname` varchar(150) DEFAULT NULL,
  `branchid` int(11) DEFAULT NULL,
  `userid` int(11) DEFAULT NULL,
  `academicyear` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`idfeescategory`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb3 COLLATE=utf8mb3_uca1400_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `fee_feescategory`
--

LOCK TABLES `fee_feescategory` WRITE;
/*!40000 ALTER TABLE `fee_feescategory` DISABLE KEYS */;
INSERT INTO `fee_feescategory` VALUES (1,'Gardening fees',100,'I--',1,1,'2024/25'),(2,'Gardening fees',100,'II--',1,1,'2024/25'),(3,'Gardening fees',100,'III--',1,1,'2024/25');
/*!40000 ALTER TABLE `fee_feescategory` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `fee_feescollection`
--

DROP TABLE IF EXISTS `fee_feescollection`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
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
  CONSTRAINT `FK3lmhm00kt3guobu1j19ns34sx` FOREIGN KEY (`receiptnumber`) REFERENCES `fee_receiptinfo` (`receiptnumber`),
  CONSTRAINT `FK6gfayf7jy87bmep3wj0g9dis8` FOREIGN KEY (`sfsid`) REFERENCES `fee_studentfeesstructure` (`sfsid`),
  CONSTRAINT `FKs8rugbedk849jp8aeb9gflu6o` FOREIGN KEY (`sid`) REFERENCES `student` (`sid`),
  CONSTRAINT `fk` FOREIGN KEY (`sid`) REFERENCES `student` (`sid`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `receiptnumber` FOREIGN KEY (`receiptnumber`) REFERENCES `fee_receiptinfo` (`receiptnumber`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `sfsid` FOREIGN KEY (`sfsid`) REFERENCES `fee_studentfeesstructure` (`sfsid`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3 COLLATE=utf8mb3_uca1400_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `fee_feescollection`
--

LOCK TABLES `fee_feescollection` WRITE;
/*!40000 ALTER TABLE `fee_feescollection` DISABLE KEYS */;
/*!40000 ALTER TABLE `fee_feescollection` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `fee_feesdetails`
--

DROP TABLE IF EXISTS `fee_feesdetails`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
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
  CONSTRAINT `FKdm9l84t4dqeer64n1l6sjk295` FOREIGN KEY (`sid`) REFERENCES `student` (`sid`),
  CONSTRAINT `fk_fees` FOREIGN KEY (`sid`) REFERENCES `student` (`sid`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3 COLLATE=utf8mb3_uca1400_ai_ci COMMENT='				';
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
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `fee_receiptinfo` (
  `receiptnumber` int(11) NOT NULL AUTO_INCREMENT,
  `sid` int(11) NOT NULL,
  `date` date DEFAULT NULL,
  `totalamount` decimal(10,0) DEFAULT NULL,
  `academicyear` varchar(15) DEFAULT NULL,
  `branchid` int(11) DEFAULT NULL,
  `cancelreceipt` int(11) DEFAULT 0,
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
  CONSTRAINT `FKq7fydoo87m15pa50jk6wlx6i0` FOREIGN KEY (`sid`) REFERENCES `student` (`sid`),
  CONSTRAINT `studentidreceipt` FOREIGN KEY (`sid`) REFERENCES `student` (`sid`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3 COLLATE=utf8mb3_uca1400_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `fee_receiptinfo`
--

LOCK TABLES `fee_receiptinfo` WRITE;
/*!40000 ALTER TABLE `fee_receiptinfo` DISABLE KEYS */;
/*!40000 ALTER TABLE `fee_receiptinfo` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `fee_studentfeesstructure`
--

DROP TABLE IF EXISTS `fee_studentfeesstructure`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `fee_studentfeesstructure` (
  `sfsid` int(11) NOT NULL AUTO_INCREMENT,
  `sid` int(11) NOT NULL,
  `idfeescategory` int(11) NOT NULL,
  `feesamount` decimal(10,0) DEFAULT NULL,
  `academicyear` varchar(45) DEFAULT NULL,
  `branchid` int(11) DEFAULT NULL,
  `concession` int(11) DEFAULT NULL,
  `feespaid` decimal(10,0) DEFAULT 0,
  `waiveoff` decimal(10,0) DEFAULT 0,
  `totalinstallment` int(11) DEFAULT 0,
  `userid` int(11) DEFAULT NULL,
  PRIMARY KEY (`sfsid`),
  KEY `fk_sfs_idx` (`sid`),
  KEY `feescategoryid_idx` (`idfeescategory`),
  CONSTRAINT `FKi8qjri8tvl1fx8in81j23yhcq` FOREIGN KEY (`idfeescategory`) REFERENCES `fee_feescategory` (`idfeescategory`),
  CONSTRAINT `feescategoryid` FOREIGN KEY (`idfeescategory`) REFERENCES `fee_feescategory` (`idfeescategory`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_sfs` FOREIGN KEY (`sid`) REFERENCES `student` (`sid`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3 COLLATE=utf8mb3_uca1400_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `fee_studentfeesstructure`
--

LOCK TABLES `fee_studentfeesstructure` WRITE;
/*!40000 ALTER TABLE `fee_studentfeesstructure` DISABLE KEYS */;
/*!40000 ALTER TABLE `fee_studentfeesstructure` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `fileuploaddetails`
--

DROP TABLE IF EXISTS `fileuploaddetails`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `fileuploaddetails` (
  `fudid` int(11) NOT NULL AUTO_INCREMENT,
  `date` date NOT NULL,
  `uploadstatus` varchar(10) DEFAULT NULL,
  `branchid` int(11) DEFAULT NULL,
  `userid` int(11) DEFAULT NULL,
  PRIMARY KEY (`fudid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3 COLLATE=utf8mb3_uca1400_ai_ci;
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
/*!50503 SET character_set_client = utf8mb4 */;
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
  CONSTRAINT `FKam46xlwwecq7fgxdqtc1hxtjv` FOREIGN KEY (`stdid`) REFERENCES `parents` (`pid`),
  CONSTRAINT `h_appointment_FK` FOREIGN KEY (`stdid`) REFERENCES `parents` (`pid`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=latin1 COLLATE=latin1_swedish_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `h_appointment`
--

LOCK TABLES `h_appointment` WRITE;
/*!40000 ALTER TABLE `h_appointment` DISABLE KEYS */;
INSERT INTO `h_appointment` VALUES (1,1,'AP001','0006-08-18','06:00 AM',1,NULL,'2025-03-07',2,'2024/25','Scheduled',NULL,NULL,NULL),(4,1,'AP002','0006-08-18','06:00 AM',1,NULL,'2025-03-07',2,'2024/25','Scheduled',NULL,NULL,NULL);
/*!40000 ALTER TABLE `h_appointment` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `h_cases`
--

DROP TABLE IF EXISTS `h_cases`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
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
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COLLATE=latin1_swedish_ci;
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
/*!50503 SET character_set_client = utf8mb4 */;
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
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COLLATE=latin1_swedish_ci;
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
/*!50503 SET character_set_client = utf8mb4 */;
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
  CONSTRAINT `FKel28obrfkq89y0xtqb3hdr4bc` FOREIGN KEY (`staffid`) REFERENCES `teacher` (`tid`),
  CONSTRAINT `h_job_FK_1` FOREIGN KEY (`staffid`) REFERENCES `teacher` (`tid`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COLLATE=latin1_swedish_ci;
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
/*!50503 SET character_set_client = utf8mb4 */;
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
  CONSTRAINT `FKbxp3vm887ahodtaumfpmy0ufx` FOREIGN KEY (`jobid`) REFERENCES `h_job` (`id`),
  CONSTRAINT `FKsn1ls5ogj4p5vdprsjm596sx8` FOREIGN KEY (`assignto`) REFERENCES `teacher` (`tid`),
  CONSTRAINT `h_task_FK` FOREIGN KEY (`jobid`) REFERENCES `h_job` (`id`),
  CONSTRAINT `h_task_FK_1` FOREIGN KEY (`assignto`) REFERENCES `teacher` (`tid`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COLLATE=latin1_swedish_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `h_task`
--

LOCK TABLES `h_task` WRITE;
/*!40000 ALTER TABLE `h_task` DISABLE KEYS */;
/*!40000 ALTER TABLE `h_task` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `hibernate_sequence`
--

DROP TABLE IF EXISTS `hibernate_sequence`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `hibernate_sequence` (
  `next_val` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_uca1400_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `hibernate_sequence`
--

LOCK TABLES `hibernate_sequence` WRITE;
/*!40000 ALTER TABLE `hibernate_sequence` DISABLE KEYS */;
INSERT INTO `hibernate_sequence` VALUES (1);
/*!40000 ALTER TABLE `hibernate_sequence` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `hr_leaveapplication`
--

DROP TABLE IF EXISTS `hr_leaveapplication`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
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
  CONSTRAINT `FK2stavj470kxtd4oruyxuqj13h` FOREIGN KEY (`idteacher`) REFERENCES `teacher` (`tid`),
  CONSTRAINT `idteacherleaveapp` FOREIGN KEY (`idteacher`) REFERENCES `teacher` (`tid`) ON DELETE CASCADE ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3 COLLATE=utf8mb3_uca1400_ai_ci;
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
/*!50503 SET character_set_client = utf8mb4 */;
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
  CONSTRAINT `FKjg1f9ar7qpo4c3oh5vvhacy5b` FOREIGN KEY (`idleavetypemaster`) REFERENCES `hr_leavetypemaster` (`idleavetypemaster`),
  CONSTRAINT `leavetype` FOREIGN KEY (`idleavetypemaster`) REFERENCES `hr_leavetypemaster` (`idleavetypemaster`) ON DELETE CASCADE ON UPDATE NO ACTION,
  CONSTRAINT `teacherid` FOREIGN KEY (`idteacher`) REFERENCES `teacher` (`tid`) ON DELETE CASCADE ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3 COLLATE=utf8mb3_uca1400_ai_ci COMMENT='		';
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
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `hr_leavetypemaster` (
  `idleavetypemaster` int(11) NOT NULL AUTO_INCREMENT,
  `leavetypename` varchar(100) DEFAULT NULL,
  `branchid` int(11) DEFAULT NULL,
  `userid` int(11) DEFAULT NULL,
  PRIMARY KEY (`idleavetypemaster`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb3 COLLATE=utf8mb3_uca1400_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `hr_leavetypemaster`
--

LOCK TABLES `hr_leavetypemaster` WRITE;
/*!40000 ALTER TABLE `hr_leavetypemaster` DISABLE KEYS */;
INSERT INTO `hr_leavetypemaster` VALUES (1,'Sick Leave',1,1);
/*!40000 ALTER TABLE `hr_leavetypemaster` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `hr_payadvancesalary`
--

DROP TABLE IF EXISTS `hr_payadvancesalary`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
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
  CONSTRAINT `FKsh07brakjudh1ms1t5udf6q5i` FOREIGN KEY (`idteacher`) REFERENCES `teacher` (`tid`),
  CONSTRAINT `idteacheradv` FOREIGN KEY (`idteacher`) REFERENCES `teacher` (`tid`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3 COLLATE=utf8mb3_uca1400_ai_ci;
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
/*!50503 SET character_set_client = utf8mb4 */;
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
  CONSTRAINT `FK85smh2iv0mg1utx3fsrpswf8i` FOREIGN KEY (`idteacher`) REFERENCES `teacher` (`tid`),
  CONSTRAINT `teachersid` FOREIGN KEY (`idteacher`) REFERENCES `teacher` (`tid`) ON DELETE CASCADE ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3 COLLATE=utf8mb3_uca1400_ai_ci;
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
/*!50503 SET character_set_client = utf8mb4 */;
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
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3 COLLATE=utf8mb3_uca1400_ai_ci;
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
/*!50503 SET character_set_client = utf8mb4 */;
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
  CONSTRAINT `FKbxsx9xmxrjyi828bk4ei33cow` FOREIGN KEY (`idteacher`) REFERENCES `teacher` (`tid`),
  CONSTRAINT `FKnemphy1iicdlytvb4futm1ga1` FOREIGN KEY (`payheadid`) REFERENCES `payhead` (`idpayhead`),
  CONSTRAINT `idteacher` FOREIGN KEY (`idteacher`) REFERENCES `teacher` (`tid`) ON DELETE CASCADE ON UPDATE NO ACTION,
  CONSTRAINT `payheadid` FOREIGN KEY (`payheadid`) REFERENCES `hr_payhead` (`idpayhead`) ON DELETE CASCADE ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3 COLLATE=utf8mb3_uca1400_ai_ci;
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
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `hr_pf` (
  `idpf` int(11) NOT NULL AUTO_INCREMENT,
  `paidbymanagement` int(11) DEFAULT NULL,
  `paidbyemployee` int(11) DEFAULT NULL,
  `date` date DEFAULT NULL,
  `branchid` int(11) DEFAULT NULL,
  `userid` int(11) DEFAULT NULL,
  PRIMARY KEY (`idpf`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3 COLLATE=utf8mb3_uca1400_ai_ci COMMENT='		';
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
/*!50503 SET character_set_client = utf8mb4 */;
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
  CONSTRAINT `FK2j5nj9vck2u5tu4sn30akil2d` FOREIGN KEY (`teacherid`) REFERENCES `teacher` (`tid`),
  CONSTRAINT `processteacherid` FOREIGN KEY (`teacherid`) REFERENCES `teacher` (`tid`) ON DELETE CASCADE ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3 COLLATE=utf8mb3_uca1400_ai_ci;
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
/*!50503 SET character_set_client = utf8mb4 */;
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
  CONSTRAINT `FKsx034ub4jhjxt8tjrcri3fx8v` FOREIGN KEY (`idprocesssalary`) REFERENCES `hr_processsalarydetails` (`idprocesssalarydetails`),
  CONSTRAINT `idprocesssalary` FOREIGN KEY (`idprocesssalary`) REFERENCES `hr_processsalarydetails` (`idprocesssalarydetails`) ON DELETE CASCADE ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3 COLLATE=utf8mb3_uca1400_ai_ci;
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
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `login` (
  `lid` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(45) NOT NULL,
  `password` varchar(45) NOT NULL,
  `usertype` varchar(45) DEFAULT NULL,
  `branchid` int(11) DEFAULT NULL,
  `userid` int(11) DEFAULT NULL,
  PRIMARY KEY (`lid`),
  KEY `branchid_idx` (`branchid`),
  CONSTRAINT `FKsc1p3jqlmqklyqhrah21ikcch` FOREIGN KEY (`branchid`) REFERENCES `branch` (`idbranch`),
  CONSTRAINT `branchid` FOREIGN KEY (`branchid`) REFERENCES `branch` (`idbranch`) ON DELETE CASCADE ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8mb3 COLLATE=utf8mb3_uca1400_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `login`
--

LOCK TABLES `login` WRITE;
/*!40000 ALTER TABLE `login` DISABLE KEYS */;
INSERT INTO `login` VALUES (1,'headoffice','headadmin','superadmin',1,1),(2,'admin','adminschool','admin',2,2),(3,'admin','adminpuc','admin',3,3),(4,'admin','adminreserve2','admin',4,4),(5,'GIA121227','9512364785','parents',2,5),(6,'GIA510675','9532164870','parents',2,6),(7,'GIA224592','9523164870','parents',2,7),(8,'GIA681045','8521364790','parents',2,8),(9,'GIA130060','9521132460','parents',2,9),(13,'GIA728940','9856235461','parents',2,2);
/*!40000 ALTER TABLE `login` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `markgrade`
--

DROP TABLE IF EXISTS `markgrade`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `markgrade` (
  `id` int(11) NOT NULL,
  `minpercentage` int(11) NOT NULL,
  `maxpercentage` int(11) NOT NULL,
  `branchid` int(11) DEFAULT NULL,
  `status` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COLLATE=latin1_swedish_ci;
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
/*!50503 SET character_set_client = utf8mb4 */;
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
  CONSTRAINT `FK4r64m1urwqr9ksjaom16fnl9j` FOREIGN KEY (`subid`) REFERENCES `subject` (`subid`),
  CONSTRAINT `FKb8h0nsajs25yy8hneeqhcihs3` FOREIGN KEY (`examid`) REFERENCES `exams` (`exid`),
  CONSTRAINT `FKp24mm8slqb8d327sjrqhapvvx` FOREIGN KEY (`sid`) REFERENCES `student` (`sid`),
  CONSTRAINT `examinationid` FOREIGN KEY (`examid`) REFERENCES `exams` (`exid`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `studentid` FOREIGN KEY (`sid`) REFERENCES `student` (`sid`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `subjectid` FOREIGN KEY (`subid`) REFERENCES `subject` (`subid`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3 COLLATE=utf8mb3_uca1400_ai_ci;
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
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `mess_card` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `sid` int(11) DEFAULT NULL,
  `validfrom` date DEFAULT NULL,
  `validto` date DEFAULT NULL,
  `userid` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3 COLLATE=utf8mb3_uca1400_ai_ci;
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
/*!50503 SET character_set_client = utf8mb4 */;
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
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3 COLLATE=utf8mb3_uca1400_ai_ci;
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
/*!50503 SET character_set_client = utf8mb4 */;
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
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3 COLLATE=utf8mb3_uca1400_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `mess_items`
--

LOCK TABLES `mess_items` WRITE;
/*!40000 ALTER TABLE `mess_items` DISABLE KEYS */;
/*!40000 ALTER TABLE `mess_items` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `mess_stockavailable`
--

DROP TABLE IF EXISTS `mess_stockavailable`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `mess_stockavailable` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `itemid` int(11) DEFAULT NULL,
  `availablestock` float DEFAULT NULL,
  `minstock` int(11) DEFAULT NULL,
  `branchid` int(11) DEFAULT NULL,
  `userid` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_qba8e5erde7cds5xsddu097wr` (`itemid`),
  KEY `mess_stockavailable_mess_items_FK` (`itemid`),
  CONSTRAINT `FKg6125k3d65pfivynnw0gkjxqy` FOREIGN KEY (`itemid`) REFERENCES `mess_items` (`id`),
  CONSTRAINT `mess_stockavailable_mess_items_FK` FOREIGN KEY (`itemid`) REFERENCES `mess_items` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3 COLLATE=utf8mb3_uca1400_ai_ci;
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
/*!50503 SET character_set_client = utf8mb4 */;
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
  UNIQUE KEY `UK_hcsk5o30atwansxgp82d7r7pi` (`invoicedetailsid`),
  KEY `stockentry_mess_items_FK` (`itemid`),
  KEY `mess_stockentry_mess_invoicedetails_FK` (`invoicedetailsid`),
  CONSTRAINT `FK5s7g9y89d1lak2rq60m16ftsd` FOREIGN KEY (`invoicedetailsid`) REFERENCES `mess_invoicedetails` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3 COLLATE=utf8mb3_uca1400_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `mess_stockentry`
--

LOCK TABLES `mess_stockentry` WRITE;
/*!40000 ALTER TABLE `mess_stockentry` DISABLE KEYS */;
/*!40000 ALTER TABLE `mess_stockentry` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `mess_stockmoves`
--

DROP TABLE IF EXISTS `mess_stockmoves`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
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
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3 COLLATE=utf8mb3_uca1400_ai_ci;
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
/*!50503 SET character_set_client = utf8mb4 */;
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
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3 COLLATE=utf8mb3_uca1400_ai_ci;
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
/*!50503 SET character_set_client = utf8mb4 */;
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
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3 COLLATE=utf8mb3_uca1400_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `mess_suppliers`
--

LOCK TABLES `mess_suppliers` WRITE;
/*!40000 ALTER TABLE `mess_suppliers` DISABLE KEYS */;
/*!40000 ALTER TABLE `mess_suppliers` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `otherfee_academicfeesstructure`
--

DROP TABLE IF EXISTS `otherfee_academicfeesstructure`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `otherfee_academicfeesstructure` (
  `feesstructureid` int(11) NOT NULL AUTO_INCREMENT,
  `sid` int(11) NOT NULL,
  `totalfees` decimal(10,0) DEFAULT NULL,
  `paidfees` decimal(10,0) DEFAULT 0,
  `academicyear` varchar(45) DEFAULT NULL,
  `branchid` int(11) DEFAULT NULL,
  `userid` int(11) DEFAULT NULL,
  PRIMARY KEY (`feesstructureid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3 COLLATE=utf8mb3_uca1400_ai_ci;
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
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `otherfee_feescategory` (
  `idfeescategory` int(11) NOT NULL AUTO_INCREMENT,
  `feescategoryname` varchar(150) DEFAULT NULL,
  `amount` int(11) DEFAULT NULL,
  `particularname` varchar(150) DEFAULT NULL,
  `branchid` int(11) DEFAULT NULL,
  `userid` int(11) DEFAULT NULL,
  `academicyear` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`idfeescategory`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb3 COLLATE=utf8mb3_uca1400_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `otherfee_feescategory`
--

LOCK TABLES `otherfee_feescategory` WRITE;
/*!40000 ALTER TABLE `otherfee_feescategory` DISABLE KEYS */;
INSERT INTO `otherfee_feescategory` VALUES (1,'Annual Function',500,'I-III',1,1,'2024/25');
/*!40000 ALTER TABLE `otherfee_feescategory` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `otherfee_feescollection`
--

DROP TABLE IF EXISTS `otherfee_feescollection`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
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
  CONSTRAINT `FKe10kkp88ubndhk1gef8iaxnmx` FOREIGN KEY (`sid`) REFERENCES `student` (`sid`),
  CONSTRAINT `FKfgbgn6ovsr2n9f9ob6fdr42qc` FOREIGN KEY (`receiptnumber`) REFERENCES `otherfee_receiptinfo` (`receiptnumber`),
  CONSTRAINT `FKgg9a8cju5lnytdy07x94sv65n` FOREIGN KEY (`sfsid`) REFERENCES `otherfee_studentfeesstructure` (`sfsid`),
  CONSTRAINT `otherfk` FOREIGN KEY (`sid`) REFERENCES `student` (`sid`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `otherreceiptnumber` FOREIGN KEY (`receiptnumber`) REFERENCES `otherfee_receiptinfo` (`receiptnumber`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `othersfsid` FOREIGN KEY (`sfsid`) REFERENCES `otherfee_studentfeesstructure` (`sfsid`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3 COLLATE=utf8mb3_uca1400_ai_ci;
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
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `otherfee_receiptinfo` (
  `receiptnumber` int(11) NOT NULL AUTO_INCREMENT,
  `sid` int(11) NOT NULL,
  `date` date DEFAULT NULL,
  `totalamount` decimal(10,0) DEFAULT NULL,
  `academicyear` varchar(15) DEFAULT NULL,
  `branchid` int(11) DEFAULT NULL,
  `cancelreceipt` int(11) DEFAULT 0,
  `branchreceiptnumber` varchar(20) DEFAULT NULL,
  `paymenttype` varchar(100) DEFAULT NULL,
  `userid` int(11) DEFAULT NULL,
  `classsec` varchar(20) DEFAULT NULL,
  `receiptvoucher` int(11) DEFAULT NULL,
  `journalvoucher` int(11) DEFAULT NULL,
  `misc` decimal(10,0) DEFAULT 0,
  `fine` decimal(10,0) DEFAULT 0,
  PRIMARY KEY (`receiptnumber`),
  KEY `studentid_idx` (`sid`),
  CONSTRAINT `FKfhhgxw57m606mrchgna4t3o55` FOREIGN KEY (`sid`) REFERENCES `student` (`sid`),
  CONSTRAINT `otherstudentidreceipt` FOREIGN KEY (`sid`) REFERENCES `student` (`sid`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3 COLLATE=utf8mb3_uca1400_ai_ci;
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
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `otherfee_studentfeesstructure` (
  `sfsid` int(11) NOT NULL AUTO_INCREMENT,
  `sid` int(11) NOT NULL,
  `idfeescategory` int(11) NOT NULL,
  `feesamount` decimal(10,0) DEFAULT NULL,
  `academicyear` varchar(45) DEFAULT NULL,
  `branchid` int(11) DEFAULT NULL,
  `concession` int(11) DEFAULT NULL,
  `feespaid` decimal(10,0) DEFAULT 0,
  `waiveoff` decimal(10,0) DEFAULT 0,
  `totalinstallment` int(11) DEFAULT 0,
  `userid` int(11) DEFAULT NULL,
  PRIMARY KEY (`sfsid`),
  KEY `fk_sfs_idx` (`sid`),
  KEY `feescategoryid_idx` (`idfeescategory`),
  CONSTRAINT `FK1jtqfjwop4b5ktw4s48hmmnjl` FOREIGN KEY (`sid`) REFERENCES `student` (`sid`),
  CONSTRAINT `FKilhuuafjmc030jjt2wjocalxv` FOREIGN KEY (`idfeescategory`) REFERENCES `otherfee_feescategory` (`idfeescategory`),
  CONSTRAINT `otherfeescategoryid` FOREIGN KEY (`idfeescategory`) REFERENCES `otherfee_feescategory` (`idfeescategory`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `otherfk_sfs` FOREIGN KEY (`sid`) REFERENCES `student` (`sid`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3 COLLATE=utf8mb3_uca1400_ai_ci;
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
/*!50503 SET character_set_client = utf8mb4 */;
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
  UNIQUE KEY `UK_782oo4knj0jub6uy6t854fy7x` (`sid`),
  KEY `sid_idx` (`sid`),
  KEY `tid_idx` (`tid`),
  CONSTRAINT `FKc1vbk3p896evcotnpbh8txsnp` FOREIGN KEY (`sid`) REFERENCES `student` (`sid`),
  CONSTRAINT `FKkypfia9w20kletb3vhijfnph3` FOREIGN KEY (`tid`) REFERENCES `teacher` (`tid`),
  CONSTRAINT `sid` FOREIGN KEY (`sid`) REFERENCES `student` (`sid`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `tid` FOREIGN KEY (`tid`) REFERENCES `teacher` (`tid`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8mb3 COLLATE=utf8mb3_uca1400_ai_ci COMMENT='		';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `parents`
--

LOCK TABLES `parents` WRITE;
/*!40000 ALTER TABLE `parents` DISABLE KEYS */;
INSERT INTO `parents` VALUES (1,'Father1','Mother1','','',NULL,'',0,1,NULL,'','','9523164870','',2,'','','','',NULL,NULL,2),(2,'Father','Mother','','',NULL,'',0,2,NULL,'','','8521364790','',2,'','','','',NULL,NULL,2),(3,'Father New','Mother New','','',NULL,'',0,3,NULL,'','','9521132460','',2,'','','','',NULL,NULL,2),(4,'SS Father','SS Mother','','',NULL,'',NULL,4,NULL,'','','9512456230','ssf@mail.com',2,'','','','',NULL,NULL,2),(5,'AA Father','AA Mother','','',NULL,'',2,5,NULL,'','','8512364578','aafm@mail.com',2,'','','','',NULL,NULL,2),(6,'BB Father','BB Mother','','',NULL,'',NULL,6,NULL,'','','9123456780','bbfm@mail.com',2,'','','','',NULL,NULL,2),(7,'CC Father','CC Mother','','',NULL,'',NULL,7,NULL,'','','9854621301','',2,'','','','',NULL,NULL,2),(8,'DD Father','DD Mother','','',NULL,'',0,8,NULL,'','','9856235461','',2,'','','','',NULL,NULL,2);
/*!40000 ALTER TABLE `parents` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `payhead`
--

DROP TABLE IF EXISTS `payhead`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `payhead` (
  `idpayhead` int(11) NOT NULL AUTO_INCREMENT,
  `academicyear` varchar(45) DEFAULT NULL,
  `branchid` int(11) DEFAULT NULL,
  `description` varchar(200) DEFAULT NULL,
  `payheadname` varchar(200) DEFAULT NULL,
  `payheadtype` varchar(40) DEFAULT NULL,
  `userid` int(11) DEFAULT NULL,
  `validatory` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`idpayhead`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_uca1400_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `payhead`
--

LOCK TABLES `payhead` WRITE;
/*!40000 ALTER TABLE `payhead` DISABLE KEYS */;
INSERT INTO `payhead` VALUES (1,'2024/25',1,'	Basic Pay									\r\n								','Basic','Earning',1,'Every Month');
/*!40000 ALTER TABLE `payhead` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `perioddetails`
--

DROP TABLE IF EXISTS `perioddetails`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
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
  CONSTRAINT `FKbwfbejwf0pv4l55qjukc5vg4e` FOREIGN KEY (`periodmasterid`) REFERENCES `periodmaster` (`idperiodmaster`),
  CONSTRAINT `periodd` FOREIGN KEY (`periodmasterid`) REFERENCES `periodmaster` (`idperiodmaster`) ON DELETE CASCADE ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3 COLLATE=utf8mb3_uca1400_ai_ci;
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
/*!50503 SET character_set_client = utf8mb4 */;
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
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3 COLLATE=utf8mb3_uca1400_ai_ci;
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
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `position` (
  `positionid` int(11) NOT NULL AUTO_INCREMENT,
  `positionname` varchar(100) DEFAULT NULL,
  `branchid` int(11) DEFAULT NULL,
  `userid` int(11) DEFAULT NULL,
  PRIMARY KEY (`positionid`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb3 COLLATE=utf8mb3_uca1400_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `position`
--

LOCK TABLES `position` WRITE;
/*!40000 ALTER TABLE `position` DISABLE KEYS */;
INSERT INTO `position` VALUES (1,'PT',1,1),(2,'Science',1,1);
/*!40000 ALTER TABLE `position` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `pudetails`
--

DROP TABLE IF EXISTS `pudetails`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
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
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8mb3 COLLATE=utf8mb3_uca1400_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `pudetails`
--

LOCK TABLES `pudetails` WRITE;
/*!40000 ALTER TABLE `pudetails` DISABLE KEYS */;
INSERT INTO `pudetails` VALUES (3,NULL,NULL,NULL,NULL,NULL,NULL,'','',NULL,NULL,NULL),(4,NULL,NULL,NULL,NULL,NULL,NULL,'','',NULL,NULL,NULL),(5,NULL,NULL,NULL,NULL,NULL,NULL,'','',NULL,NULL,NULL),(6,NULL,NULL,NULL,NULL,NULL,NULL,'','',NULL,NULL,NULL),(7,NULL,NULL,NULL,NULL,NULL,NULL,'','',NULL,NULL,NULL),(8,NULL,NULL,NULL,NULL,NULL,NULL,'','',NULL,NULL,NULL),(9,NULL,NULL,NULL,NULL,NULL,NULL,'','',NULL,NULL,NULL),(10,NULL,NULL,NULL,NULL,NULL,NULL,'','',NULL,NULL,NULL);
/*!40000 ALTER TABLE `pudetails` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `student`
--

DROP TABLE IF EXISTS `student`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
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
  `studentpic` longtext DEFAULT NULL,
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
  `studentscaste` varchar(45) DEFAULT NULL,
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
  `studentdoc1` longtext DEFAULT NULL,
  `studentdoc2` longtext DEFAULT NULL,
  `studentdoc3` longtext DEFAULT NULL,
  `studentdoc4` longtext DEFAULT NULL,
  `studentdoc5` longtext DEFAULT NULL,
  `yearofadmission` varchar(10) DEFAULT NULL,
  `promotedyear` varchar(10) DEFAULT NULL,
  PRIMARY KEY (`sid`),
  UNIQUE KEY `studentexternalid_UNIQUE` (`studentexternalid`),
  UNIQUE KEY `UKeknnxvapy9e8uytadirol4mdw` (`studentexternalid`),
  KEY `pudetailsid_idx` (`pudetailsid`),
  KEY `degreedetailsid_idx` (`degreedetailsid`),
  CONSTRAINT `FK6yusougogkodea6kts11p1u9` FOREIGN KEY (`pudetailsid`) REFERENCES `pudetails` (`idpudetails`),
  CONSTRAINT `FK7nl5tw7phpwb2lmuscikkn32x` FOREIGN KEY (`degreedetailsid`) REFERENCES `degreedetails` (`iddegreedetails`),
  CONSTRAINT `degreedetailsid` FOREIGN KEY (`degreedetailsid`) REFERENCES `degreedetails` (`iddegreedetails`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `pudetailsid` FOREIGN KEY (`pudetailsid`) REFERENCES `pudetails` (`idpudetails`) ON DELETE CASCADE ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8mb3 COLLATE=utf8mb3_uca1400_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `student`
--

LOCK TABLES `student` WRITE;
/*!40000 ALTER TABLE `student` DISABLE KEYS */;
INSERT INTO `student` VALUES (1,'SchoolStudent 12','I--A','A--',24,'Male','2001-02-01','','Indian','',NULL,NULL,'15236','','','','','2025-01-06',0,'','123456',NULL,NULL,'',NULL,NULL,'',NULL,'',0,NULL,'','',2,NULL,'',NULL,NULL,0,0,0,0,NULL,'','',NULL,NULL,'','','General',0,'','','','None','',0,'',NULL,'','',0,'','','','','','2024/25',''),(2,'School Student updated','I--A','A--',0,NULL,NULL,'','Indian','',NULL,NULL,'4546','','','','','2025-01-30',0,'','GIA681045',NULL,NULL,'',NULL,NULL,'',NULL,'',0,NULL,'','',2,NULL,'',NULL,NULL,0,0,0,0,NULL,'','',NULL,NULL,'','','General',0,'','','','None','',0,'',NULL,'','',0,'','','','','','2024/25',''),(3,'Student New update','I--A','A--',0,NULL,NULL,'','Indian','',NULL,NULL,'123456','','','','','2025-01-30',0,'','GIA130060',NULL,NULL,'',NULL,NULL,'',NULL,'',0,NULL,'','',2,NULL,'',NULL,NULL,0,0,0,0,NULL,'','',NULL,NULL,'','','General',0,'','','','None','',0,'',NULL,'','',0,'','','','','','2024/25',''),(4,'SS Student','I--B','B',10,NULL,'2015-03-03','','Indian','',NULL,NULL,'2261','','','','','2025-03-11',0,NULL,'GIA522539',NULL,NULL,'',NULL,NULL,'',NULL,'',NULL,NULL,'','',2,6,'',NULL,6,0,0,0,NULL,NULL,'','',NULL,NULL,'','','',NULL,'','','','None',NULL,NULL,'',NULL,'','',2,NULL,NULL,NULL,NULL,NULL,'2024/25',NULL),(5,'AA Student','I--A','A',NULL,NULL,NULL,'','Indian','',NULL,NULL,'2356','','','','','2025-03-11',0,NULL,'GIA796194',NULL,NULL,'',NULL,NULL,'',NULL,'',NULL,NULL,'','',2,7,'',NULL,7,0,0,0,NULL,NULL,'','',NULL,NULL,'','','',NULL,'','','','None',NULL,NULL,'',NULL,'','',2,NULL,NULL,NULL,NULL,NULL,'2024/25',NULL),(6,'BB Student','I--A','A',NULL,NULL,NULL,'','Indian','',NULL,NULL,'12345','','','','','2025-03-11',0,NULL,'GIA243891',NULL,NULL,'',NULL,NULL,'',NULL,'',NULL,NULL,'','',2,8,'',NULL,8,0,0,0,NULL,NULL,'','',NULL,NULL,'','','',NULL,'','','','None',NULL,NULL,'',NULL,'','',2,NULL,NULL,NULL,NULL,NULL,'2024/25',NULL),(7,'CC Student','I--A','A',NULL,NULL,NULL,'','Indian','',NULL,NULL,'95123','','','','','2025-03-11',0,NULL,'GIA036660',NULL,NULL,'',NULL,NULL,'',NULL,'',NULL,NULL,'','',2,9,'',NULL,9,0,0,0,NULL,NULL,'','',NULL,NULL,'','','',NULL,'','','','None',NULL,NULL,'',NULL,'','',2,NULL,NULL,NULL,NULL,NULL,'2024/25',NULL),(8,'DD Student','I--A','A--',10,NULL,NULL,'','Indian','',NULL,NULL,'7788','','','','','2025-03-11',0,'','GIA728940',NULL,NULL,'',NULL,NULL,'',NULL,'',0,NULL,'','',2,NULL,'',NULL,NULL,0,0,0,0,NULL,'','',NULL,NULL,'','','General',0,'','','','None','',0,'',NULL,'','',0,'','','','','','2024/25','');
/*!40000 ALTER TABLE `student` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `student_documents`
--

DROP TABLE IF EXISTS `student_documents`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
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
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3 COLLATE=utf8mb3_uca1400_ai_ci COMMENT='	';
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
/*!50503 SET character_set_client = utf8mb4 */;
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
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COLLATE=latin1_swedish_ci;
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
/*!50503 SET character_set_client = utf8mb4 */;
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
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3 COLLATE=utf8mb3_uca1400_ai_ci;
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
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `subjectgrade` (
  `id` int(11) NOT NULL,
  `minmarks` int(11) NOT NULL,
  `maxmarks` int(11) NOT NULL,
  `status` varchar(45) NOT NULL,
  `branchid` int(11) DEFAULT NULL,
  `examid` varchar(100) DEFAULT NULL,
  `classsec` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COLLATE=latin1_swedish_ci;
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
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `subjectmaster` (
  `subjectid` int(11) NOT NULL AUTO_INCREMENT,
  `subjectname` varchar(100) DEFAULT NULL,
  `branchid` int(11) DEFAULT NULL,
  `userid` int(11) DEFAULT NULL,
  PRIMARY KEY (`subjectid`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COLLATE=latin1_swedish_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `subjectmaster`
--

LOCK TABLES `subjectmaster` WRITE;
/*!40000 ALTER TABLE `subjectmaster` DISABLE KEYS */;
/*!40000 ALTER TABLE `subjectmaster` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `teacher`
--

DROP TABLE IF EXISTS `teacher`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
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
  `employeephoto` longtext DEFAULT NULL,
  `employeedoc1` longtext DEFAULT NULL,
  `employeedoc2` longtext DEFAULT NULL,
  `employeedoc3` longtext DEFAULT NULL,
  `employeedoc4` longtext DEFAULT NULL,
  `employeedoc5` longtext DEFAULT NULL,
  `classteacher` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`tid`),
  UNIQUE KEY `teacherexternalid_UNIQUE` (`teacherexternalid`),
  UNIQUE KEY `UK_f08exefr9h342cndbl4yq4mku` (`teacherexternalid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3 COLLATE=utf8mb3_uca1400_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `teacher`
--

LOCK TABLES `teacher` WRITE;
/*!40000 ALTER TABLE `teacher` DISABLE KEYS */;
/*!40000 ALTER TABLE `teacher` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `transfercertificate`
--

DROP TABLE IF EXISTS `transfercertificate`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
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
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3 COLLATE=utf8mb3_uca1400_ai_ci;
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
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `vouchermaster` (
  `vouchermasterid` int(11) NOT NULL AUTO_INCREMENT,
  `vouchername` varchar(100) DEFAULT NULL,
  `branchid` int(11) DEFAULT NULL,
  `userid` int(11) DEFAULT NULL,
  PRIMARY KEY (`vouchermasterid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3 COLLATE=utf8mb3_uca1400_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `vouchermaster`
--

LOCK TABLES `vouchermaster` WRITE;
/*!40000 ALTER TABLE `vouchermaster` DISABLE KEYS */;
/*!40000 ALTER TABLE `vouchermaster` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping routines for database 'school'
--
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2025-08-26 10:50:49
