package org.ideoholic.curium.model.mess.stockmove.dto;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Builder;
import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_ABSENT)
public class BillResponseDto {
	
	 private  List<Bill> billList;
	 private String itemsGrandTotalAmountWOGST;
	 private double sumSgst;
	 private double sumCgst;
	 private List<MessTaxInvoice> messTaxInvoice;
	 private String billdetailsstudentname;
	 private String billdetailsclassstudying;
	 private String billdetailsfathername;
	 private String billdetailstransactiondate;
	 private String billdetailstotaltotal;
	 private BigDecimal billgrandtotal;
	 private String billNo;

}
