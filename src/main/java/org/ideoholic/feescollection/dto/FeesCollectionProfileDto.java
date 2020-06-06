package org.ideoholic.feescollection.dto;

public class FeesCollectionProfileDto {

	private String sid;
	private String[] feesIDS;
	private String[] feesMonths;
	private String[] feesAmounts;
	private String[] feesCat;
	private String studentName;
	private String admno;
	private String classandsec;
	private String studentId;
	private String dateoffees;
	private String currentAcademicYear;
	private long id;
	private int receiptId;
	private String branchId;
	private String branchId1 ;
	private String toDate;
	private String fromDate;
	private String oneDay;
	private String dayOne;
	private String dayonecancel;
	private String datefromcancel;
	private String datetocancel;
	
	
	
	public String getCurrentAcademicYear() {
		return currentAcademicYear;
	}
	public void setCurrentAcademicYear(String currentAcademicYear) {
		this.currentAcademicYear = currentAcademicYear;
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getSid() {
		return sid;
	}
	public void setSid(String sid) {
		this.sid = sid;
	}
	public String[] getFeesIDS() {
		return feesIDS;
	}
	public void setFeesIDS(String[] feesIDS) {
		this.feesIDS = feesIDS;
	}
	public String[] getFeesMonths() {
		return feesMonths;
	}
	public void setFeesMonths(String[] feesMonths) {
		this.feesMonths = feesMonths;
	}
	public String[] getFeesAmounts() {
		return feesAmounts;
	}
	public void setFeesAmounts(String[] feesAmounts) {
		this.feesAmounts = feesAmounts;
	}
	public String[] getFeesCat() {
		return feesCat;
	}
	public void setFeesCat(String[] feesCat) {
		this.feesCat = feesCat;
	}
	public String getStudentName() {
		return studentName;
	}
	public void setStudentName(String studentName) {
		this.studentName = studentName;
	}
	public String getAdmno() {
		return admno;
	}
	public void setAdmno(String admno) {
		this.admno = admno;
	}
	public String getClassandsec() {
		return classandsec;
	}
	public void setClassandsec(String classandsec) {
		this.classandsec = classandsec;
	}
	public String getStudentId() {
		return studentId;
	}
	public void setStudentId(String studentId) {
		this.studentId = studentId;
	}
	public String getDateoffees() {
		return dateoffees;
	}
	public void setDateoffees(String dateoffees) {
		this.dateoffees = dateoffees;
	}
	public int getReceiptId() {
		return receiptId;
	}
	public void setReceiptId(int receiptId) {
		this.receiptId = receiptId;
	}
	public String getBranchId() {
		return branchId;
	}
	public void setBranchId(String branchId) {
		this.branchId = branchId;
	}
	public String getBranchId1() {
		return branchId1;
	}
	public void setBranchId1(String branchId1) {
		this.branchId1 = branchId1;
	}
	public String getToDate() {
		return toDate;
	}
	public void setToDate(String toDate) {
		this.toDate = toDate;
	}
	public String getFromDate() {
		return fromDate;
	}
	public void setFromDate(String fromDate) {
		this.fromDate = fromDate;
	}
	public String getOneDay() {
		return oneDay;
	}
	public void setOneDay(String oneDay) {
		this.oneDay = oneDay;
	}
	public String getDayOne() {
		return dayOne;
	}
	public void setDayOne(String dayOne) {
		this.dayOne = dayOne;
	}
	public String getDayonecancel() {
		return dayonecancel;
	}
	public void setDayonecancel(String dayonecancel) {
		this.dayonecancel = dayonecancel;
	}
	public String getDatefromcancel() {
		return datefromcancel;
	}
	public void setDatefromcancel(String datefromcancel) {
		this.datefromcancel = datefromcancel;
	}
	public String getDatetocancel() {
		return datetocancel;
	}
	public void setDatetocancel(String datetocancel) {
		this.datetocancel = datetocancel;
	}
}
