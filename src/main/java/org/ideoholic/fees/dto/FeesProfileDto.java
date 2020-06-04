package org.ideoholic.fees.dto;

public class FeesProfileDto {

	private String branchId;
	private String feescategory1;
	private String fromclass;
	private String toclass;
	private String amount;
	private String[] idfeescategory;
	private String studentId;

	public String getBranchId() {
		return branchId;
	}

	public void setBranchId(String branchId) {
		this.branchId = branchId;
	}

	public String getFeescategory1() {
		return feescategory1;
	}

	public void setFeescategory1(String feescategory1) {
		this.feescategory1 = feescategory1;
	}

	public String getFromclass() {
		return fromclass;
	}

	public void setFromclass(String fromclass) {
		this.fromclass = fromclass;
	}

	public String getToclass() {
		return toclass;
	}

	public void setToclass(String toclass) {
		this.toclass = toclass;
	}

	public String getAmount() {
		return amount;
	}

	public void setAmount(String amount) {
		this.amount = amount;
	}

	public String[] getIdfeescategory() {
		return idfeescategory;
	}

	public void setIdfeescategory(String[] idfeescategory) {
		this.idfeescategory = idfeescategory;
	}

	public String getStudentId() {
		return studentId;
	}

	public void setStudentId(String studentId) {
		this.studentId = studentId;
	}
}
