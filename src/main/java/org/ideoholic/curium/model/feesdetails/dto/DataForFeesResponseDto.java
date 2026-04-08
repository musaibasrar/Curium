package org.ideoholic.curium.model.feesdetails.dto;

import java.util.Map;

import org.ideoholic.curium.model.feescollection.dto.Receiptinfo;
import org.ideoholic.curium.model.feescollection.dto.StudentFeesReport;

import lombok.Data;

@Data
public class DataForFeesResponseDto {
	
	private Map<Receiptinfo,StudentFeesReport> feesMap;
	private long sumOfDetailsFees;
	private long sumOfOnlyFee;
	private long sumOfFine;
	private long sumOfMisc;
	private String dateRangeFeesCollection;
	private boolean success;

}
