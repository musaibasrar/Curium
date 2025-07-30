package org.ideoholic.curium.model.feescollection.dto;

import java.util.List;

import org.ideoholic.curium.model.parents.dto.Parents;

public class ReceiptDetails {

	Receiptinfo receipt;
    Parents parents;
    List<String> feeCategories;
    
    public ReceiptDetails() {
	}


	public ReceiptDetails(Receiptinfo receipt,Parents parents, List<String> feeCategories) {
		this.receipt = receipt;
		this.parents = parents;
		this.feeCategories = feeCategories;
	}


	public Receiptinfo getReceipt() {
		return receipt;
	}


	public void setReceipt(Receiptinfo receipt) {
		this.receipt = receipt;
	}


	public Parents getParents() {
		return parents;
	}


	public void setParents(Parents parents) {
		this.parents = parents;
	}


	public List<String> getFeeCategories() {
		return feeCategories;
	}


	public void setFeeCategories(List<String> feeCategories) {
		this.feeCategories = feeCategories;
	}
    
	
}
