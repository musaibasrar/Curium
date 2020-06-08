package org.ideoholic.admin.dto;

public class AdminProfileDto {
	
	private String branchId;
	private String item;
	private String quantity;
	private String price;
	private String entrydate;
	private String[] expensesIds;
	
	

	public String getBranchId() {
		return branchId;
	}

	public void setBranchId(String branchId) {
		this.branchId = branchId;
	}

	public String getItem() {
		return item;
	}

	public void setItem(String item) {
		this.item = item;
	}

	public String getQuantity() {
		return quantity;
	}

	public void setQuantity(String quantity) {
		this.quantity = quantity;
	}

	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price;
	}

	public String getEntrydate() {
		return entrydate;
	}

	public void setEntrydate(String entrydate) {
		this.entrydate = entrydate;
	}

	public String[] getExpensesIds() {
		return expensesIds;
	}

	public void setExpensesIds(String[] expensesIds) {
		this.expensesIds = expensesIds;
	}

}
