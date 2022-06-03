package org.ideoholic.curium.model.account.dto;

import java.math.BigDecimal;
import java.util.Date;

public class AccountDto {
	
	/* Accountdetails*/
	private Integer accountdetailsid;
	private int accountsubgroupmasterid;
	private int ssgroupmasterid;
	private int accountgroupid;
	private String accountname;
	private String accountcode;
	private Accountssgroupmaster accountSSGroupMaster;
	private Accountsubgroupmaster accountSubGroupMaster;
	private Accountgroupmaster accountGroupMaster;
	private int branchid;
	private int userid;
	
	/*Accountdetailsbalance*/
	private Integer accountdetailsbalanceid;
	private BigDecimal openingbalance;
	private BigDecimal currentbalance;
	private Integer financialid;
	private String crdr;
	private Date enteredon;
	private Accountdetails accountDetails;
	
	/*Accountgroupmaster*/
	private String accountgroupname;
	
	/*Accountssgroupmaster*/
	private String ssgroupname;
	private Integer subgroupmasterid;


	/*Accountsubgroupmaster*/
	private String accountsubgroupname;


	/*Financialaccountingyear*/
	private Date financialstartdate;
	private Date financialenddate;
	private String active;
	
	/*VoucherEntryTransaction*/
	private Integer transactionsid;
	private Integer draccountid;
	private Integer craccountid;
	private BigDecimal dramount;
	private BigDecimal cramount;
	private Integer vouchertype;
	private Date transactiondate;
	private Date entrydate;
	private Date vouchercancellationdate;
	private String narration;
	private int financialyear;
	private String cancelvoucher;
	

}
