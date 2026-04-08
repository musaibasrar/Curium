package org.ideoholic.curium.model.feesdetails.dto;

import java.util.Map;

import org.ideoholic.curium.model.feescollection.dto.Receiptinfo;
import org.ideoholic.curium.model.feescollection.dto.StudentFeesReport;
import org.ideoholic.curium.model.parents.dto.Parents;

import lombok.Data;

@Data
public class DataForFeesResponseDto {
	
	private Map<Receiptinfo,StudentFeesReport> feesMap;
	private double sumOfDetailsFees;
	private double sumOfOnlyFee;
	private double sumOfFine;
	private double sumOfMisc;
	private String dateRangeFeesCollection;
	private boolean success;

}
