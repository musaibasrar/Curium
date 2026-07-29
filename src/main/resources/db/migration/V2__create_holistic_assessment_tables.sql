-- SQL Migration Script for Holistic Development Assessment Module
-- This script creates all tables required for the independent assessment module
-- Duplicated from Examination module tables with renamed structures

-- Table: holisticassessment (equivalent to exams)
CREATE TABLE IF NOT EXISTS `holisticassessment` (
  `assessmentid` INT NOT NULL AUTO_INCREMENT,
  `assessmentname` VARCHAR(45) DEFAULT NULL,
  `branchid` INT DEFAULT NULL,
  `userid` INT DEFAULT NULL,
  PRIMARY KEY (`assessmentid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- Table: holisticassessmentschedule (equivalent to examschedule)
CREATE TABLE IF NOT EXISTS `holisticassessmentschedule` (
  `idassessmentschedule` INT NOT NULL AUTO_INCREMENT,
  `date` DATE DEFAULT NULL,
  `starttime` VARCHAR(20) DEFAULT NULL,
  `endtime` VARCHAR(20) DEFAULT NULL,
  `subject` VARCHAR(30) DEFAULT NULL,
  `assessmentname` VARCHAR(30) DEFAULT NULL,
  `classes` VARCHAR(45) DEFAULT NULL,
  `academicyear` VARCHAR(45) DEFAULT NULL,
  `branchid` INT DEFAULT NULL,
  `userid` INT DEFAULT NULL,
  PRIMARY KEY (`idassessmentschedule`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- Table: assessmentsubjectmaster (equivalent to subjectmaster)
CREATE TABLE IF NOT EXISTS `assessmentsubjectmaster` (
  `subjectid` INT NOT NULL AUTO_INCREMENT,
  `subjectname` VARCHAR(100) DEFAULT NULL,
  `category` VARCHAR(100) DEFAULT NULL,
  `branchid` INT DEFAULT NULL,
  `userid` INT DEFAULT NULL,
  PRIMARY KEY (`subjectid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- Table: assessmentsubject (equivalent to subject)
CREATE TABLE IF NOT EXISTS `assessmentsubject` (
  `assessmentsubjectid` INT NOT NULL AUTO_INCREMENT,
  `subjectname` VARCHAR(45) DEFAULT NULL,
  `subjectid` INT DEFAULT NULL,
  `minrating` FLOAT DEFAULT NULL,
  `maxrating` FLOAT DEFAULT NULL,
  `branchid` INT DEFAULT NULL,
  `assessmentname` VARCHAR(150) DEFAULT NULL,
  `assessmentclass` VARCHAR(10) DEFAULT NULL,
  `userid` INT DEFAULT NULL,
  PRIMARY KEY (`assessmentsubjectid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- Table: holisticrating (equivalent to marks)
-- Stores both rating grade (A+, A, B+, etc.) and numeric value for comparison
CREATE TABLE IF NOT EXISTS `holisticrating` (
  `ratingid` INT NOT NULL AUTO_INCREMENT,
  `sid` INT DEFAULT NULL,
  `assessmentsubjectid` INT DEFAULT NULL,
  `assessmentsubsubjectid` INT DEFAULT NULL,
  `assessmentid` INT DEFAULT NULL,
  `ratingvalue` FLOAT DEFAULT NULL COMMENT 'Numeric value for calculations/comparisons',
  `ratinggrade` VARCHAR(10) DEFAULT NULL COMMENT 'Grade code: A+, A, B+, B, C, etc.',
  `academicyear` VARCHAR(45) DEFAULT NULL,
  `branchid` INT DEFAULT NULL,
  `userid` INT DEFAULT NULL,
  PRIMARY KEY (`ratingid`),
  UNIQUE KEY `unique_rating` (`sid`, `assessmentsubjectid`, `assessmentid`, `academicyear`, `branchid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- Table: assessmentrank (equivalent to examrank)
CREATE TABLE IF NOT EXISTS `assessmentrank` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `sid` INT DEFAULT NULL,
  `assessmentid` INT DEFAULT NULL,
  `ratingobtained` FLOAT DEFAULT NULL,
  `academicyear` VARCHAR(45) DEFAULT NULL,
  `status` VARCHAR(50) DEFAULT NULL,
  `rank` INT DEFAULT NULL,
  `branchid` INT DEFAULT NULL,
  `userid` INT DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- Add indexes for better performance
CREATE INDEX idx_holisticassessment_branch ON holisticassessment(branchid);
CREATE INDEX idx_holisticassessmentschedule_branch ON holisticassessmentschedule(branchid);
CREATE INDEX idx_holisticassessmentschedule_assessment ON holisticassessmentschedule(assessmentname, classes, academicyear);
CREATE INDEX idx_assessmentsubjectmaster_branch ON assessmentsubjectmaster(branchid);
CREATE INDEX idx_assessmentsubject_branch ON assessmentsubject(branchid);
CREATE INDEX idx_assessmentsubject_class ON assessmentsubject(assessmentclass);
CREATE INDEX idx_holisticrating_student ON holisticrating(sid);
CREATE INDEX idx_holisticrating_assessment ON holisticrating(assessmentid);
CREATE INDEX idx_holisticrating_subject ON holisticrating(assessmentsubjectid);
CREATE INDEX idx_holisticrating_year ON holisticrating(academicyear);
CREATE INDEX idx_assessmentrank_student ON assessmentrank(sid);
CREATE INDEX idx_assessmentrank_assessment ON assessmentrank(assessmentid);
CREATE INDEX idx_assessmentrank_year ON assessmentrank(academicyear);

-- Comments for documentation
ALTER TABLE holisticassessment COMMENT='Holistic Development Assessment Master - Independent from Exams module';
ALTER TABLE holisticassessmentschedule COMMENT='Assessment Schedule - Independent from Exam Schedule';
ALTER TABLE assessmentsubjectmaster COMMENT='Assessment Subject Master - Independent from Subject Master';
ALTER TABLE assessmentsubject COMMENT='Assessment Subject Details - Independent from Exam Subjects';
ALTER TABLE holisticrating COMMENT='Holistic Ratings - Grade-based assessment using A+/A/B+ etc. Independent from Marks';
ALTER TABLE assessmentrank COMMENT='Assessment Ranking - Independent from Exam Ranking';
