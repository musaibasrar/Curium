package com.util;

public class ResultAnalysis  implements java.io.Serializable{
    
	   
	   String centerCode;
	   String centerName;
	   String examLevelCode;
	   Integer totalStudent;
	   Integer fail;
	   Integer pass;
	   Integer secondClass;
	   Integer firstClass;
	   Integer distinction;
	   Integer absent;
	   Integer present;
	   
	   public ResultAnalysis() {
		}

	   
	public ResultAnalysis(String centerCode, String centerName, String examLevelCode, Integer totalStudent,
			Integer fail, Integer pass, Integer secondClass, Integer firstClass, Integer distinction, Integer absent,
			Integer present) {
		super();
		this.centerCode = centerCode;
		this.centerName = centerName;
		this.examLevelCode = examLevelCode;
		this.totalStudent = totalStudent;
		this.fail = fail;
		this.pass = pass;
		this.secondClass = secondClass;
		this.firstClass = firstClass;
		this.distinction = distinction;
		this.absent = absent;
		this.present = present;
	}


	public String getCenterCode() {
		return centerCode;
	}


	public void setCenterCode(String centerCode) {
		this.centerCode = centerCode;
	}


	public String getCenterName() {
		return centerName;
	}


	public void setCenterName(String centerName) {
		this.centerName = centerName;
	}


	public String getExamLevelCode() {
		return examLevelCode;
	}


	public void setExamLevelCode(String examLevelCode) {
		this.examLevelCode = examLevelCode;
	}


	public Integer getTotalStudent() {
		return totalStudent;
	}


	public void setTotalStudent(Integer totalStudent) {
		this.totalStudent = totalStudent;
	}


	public Integer getFail() {
		return fail;
	}


	public void setFail(Integer fail) {
		this.fail = fail;
	}


	public Integer getPass() {
		return pass;
	}


	public void setPass(Integer pass) {
		this.pass = pass;
	}


	public Integer getSecondClass() {
		return secondClass;
	}


	public void setSecondClass(Integer secondClass) {
		this.secondClass = secondClass;
	}


	public Integer getFirstClass() {
		return firstClass;
	}


	public void setFirstClass(Integer firstClass) {
		this.firstClass = firstClass;
	}


	public Integer getDistinction() {
		return distinction;
	}


	public void setDistinction(Integer distinction) {
		this.distinction = distinction;
	}


	public Integer getAbsent() {
		return absent;
	}


	public void setAbsent(Integer absent) {
		this.absent = absent;
	}


	public Integer getPresent() {
		return present;
	}


	public void setPresent(Integer present) {
		this.present = present;
	}
	   
 
  
  }
