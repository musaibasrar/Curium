package org.ideoholic.curium.model.feescollection.service;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.ideoholic.curium.dto.ResultResponse;
import org.ideoholic.curium.model.account.dao.AccountDAO;
import org.ideoholic.curium.model.account.dto.Accountdetails;
import org.ideoholic.curium.model.account.dto.VoucherEntrytransactions;
import org.ideoholic.curium.model.feescollection.dao.feesCollectionDAO;
import org.ideoholic.curium.model.feescollection.dto.*;
import org.ideoholic.curium.model.feesdetails.dao.feesDetailsDAO;
import org.ideoholic.curium.model.feesdetails.dto.Feesdetails;
import org.ideoholic.curium.model.parents.dao.parentsDetailsDAO;
import org.ideoholic.curium.model.parents.dto.Parents;
import org.ideoholic.curium.model.sendsms.service.SmsService;
import org.ideoholic.curium.model.std.action.StandardActionAdapter;
import org.ideoholic.curium.model.std.dto.ClassesHierarchyDto;
import org.ideoholic.curium.model.std.dto.Classsec;
import org.ideoholic.curium.model.student.dao.studentDetailsDAO;
import org.ideoholic.curium.model.student.dto.Student;
import org.ideoholic.curium.model.student.dto.Studentfeesstructure;
import org.ideoholic.curium.model.student.dto.Studentotherfeesstructure;
import org.ideoholic.curium.model.user.dao.UserDAO;
import org.ideoholic.curium.model.user.dto.Login;
import org.ideoholic.curium.util.DataUtil;
import org.ideoholic.curium.util.DateUtil;
import org.ideoholic.curium.util.NumberToWord;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.*;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Period;
import java.util.*;

public class FeesCollectionService {

	private StandardActionAdapter standardActionAdapter;

	private HttpServletRequest request;
	private HttpServletResponse response;
	private HttpSession httpSession;
	private String CURRENTACADEMICYEAR = "currentAcademicYear";
	private String BRANCHID = "branchid";
	private String USERID = "userloginid";
	private String username = "username";
	
	private static final int BUFFER_SIZE = 4096;

	public FeesCollectionService(HttpServletRequest request,
			HttpServletResponse response, StandardActionAdapter standardActionAdapter) {
		this.request = request;
		this.response = response;
		this.httpSession = request.getSession();
		this.standardActionAdapter = standardActionAdapter;
	}

	public Feescollection add(Feesdetails feesdetails) {

		Feescollection feescollection = new Feescollection();
		//Feesdetails feesdetails = new Feesdetails();
		String months = null;
		String amount = null;
		String feesCategory = null;

		String sid = request.getParameter("studentId");
		String[] feesIDS = request.getParameterValues("feesIDS");
		String[] feesMonths = request.getParameterValues("feesQuantities");
		String[] feesAmounts = request.getParameterValues("feesAmounts");
		String[] feesCat = request.getParameterValues("feesNames");
		if(feesIDS!=null && feesMonths!=null && feesAmounts!=null && feesCat!=null){
		/*for (int i = 0; i < feesIDS.length; i++) {
			feescollection.setFeeid(DataUtil.parseInt(feesIDS[i]));
			months = DataUtil.emptyString(feesMonths[i]);
			amount = DataUtil.emptyString(feesAmounts[i]);
			feesCategory = DataUtil.emptyString(feesCat[i]);

			feescollection.setFeescategory(feesCategory);
			feescollection.setFeesamount(amount);
			feescollection.setFormonth(months);
			feescollection.setSid(DataUtil.parseInt(sid));
			feescollection.setFeesdetailsid(feesdetails.getFeesdetailsid());
			feescollection = new feesCollectionDAO().create(feescollection);
		}*/

		}

		return feescollection;
	}

	public void preview(Feescollection feescollection) {

		/*if(feescollection != null){
    List<Feescollection> list = new feesCollectionDAO()
				.readListOfObject(feescollection.getFeesdetailsid());
		httpSession.setAttribute("feescollection", list);
		Student student = new studentDetailsDAO()
				.readUniqueObject(feescollection.getSid());
		httpSession.setAttribute("student", student);
		Feesdetails feesdetails = new feesDetailsDAO()
				.readUniqueObject(feescollection.getFeesdetailsid().longValue());
		httpSession.setAttribute("feesdetails", feesdetails);
		}*/
	}

	public void preview() {
		
		 long sid=DataUtil.parseLong(request.getParameter("sid"));	
		 int id=DataUtil.parseInt(request.getParameter("id"));
		 long idFees=DataUtil.parseLong(request.getParameter("id"));
		    List<Feescollection> list = new feesCollectionDAO()
						.readListOfObject(id);
				httpSession.setAttribute("feescollection", list);
				Student student = new studentDetailsDAO()
						.readUniqueObject(sid);
				httpSession.setAttribute("student", student);
				Parents parents = new parentsDetailsDAO().readUniqueObject(sid);
				httpSession.setAttribute("parents", parents);
				Feesdetails feesdetails = new feesDetailsDAO()
						.readUniqueObject(idFees);
				NumberToWord toWord = new NumberToWord();
				String grandTotal = "";
				if(feesdetails.getGrandtotal() != null || !feesdetails.getGrandtotal().equalsIgnoreCase("")){
					String gTotal = feesdetails.getGrandtotal();
					gTotal =  gTotal.substring(0, gTotal.indexOf('.'));
					grandTotal = toWord.convert(Integer.parseInt(gTotal));
				}
				
				StringBuffer res = new StringBuffer();
				String[] strArr = grandTotal.split(" ");
				
				for(String str : strArr){
					char[] stringArray = str.trim().toCharArray();
					stringArray[0] = Character.toUpperCase(stringArray[0]);
					str = new String(stringArray);
					res.append(str).append(" ");
				}
				grandTotal = res.toString().trim();
				httpSession.setAttribute("grandTotal", grandTotal+" "+"Only");
				httpSession.setAttribute("feesdetails", feesdetails);
		
	}

	public StampFeeResponseDto getStampFees(StampFeesDto dto, String currentAcademicYear) {
		StampFeeResponseDto result = StampFeeResponseDto.builder().build();

		if(currentAcademicYear!=null){
		String academicYear = dto.getAcademicYear();
			
		long id = Long.parseLong(dto.getId());
		List<Studentfeesstructure> feesstructure = new studentDetailsDAO().getStudentFeesStructure(id, academicYear);
		//List<Feescollection> feesCollection = new feesCollectionDAO().getFeesForTheCurrentYear(id, httpSession.getAttribute(CURRENTACADEMICYEAR).toString());
		Map<Studentfeesstructure,Long> feesMap = new LinkedHashMap<Studentfeesstructure, Long>();
		
		for (Map.Entry<Studentfeesstructure, Long> feescollection2 : feesMap.entrySet()) {
			Studentfeesstructure sf = feescollection2.getKey();
			sf.getFeescategory().getFeescategoryname();
		}
		
		for (Studentfeesstructure singleFeesStructure : feesstructure) {
			Long totalAmountPerCategory = 0l;
			/*for (Feescollection singleFeescollection : feesCollection) {
				
				if(singleFeescollection.getSfsid() == singleFeesStructure.getSfsid()){
					totalAmountPerCategory = totalAmountPerCategory + singleFeescollection.getAmountpaid();
				}
				
			}*/
			Long totalDueAmount = singleFeesStructure.getFeesamount() - singleFeesStructure.getFeespaid() - singleFeesStructure.getConcession() - singleFeesStructure.getWaiveoff();
			
				if(totalDueAmount>0) {
					feesMap.put(singleFeesStructure,totalDueAmount);
				}
			
		}
		
		String[] yearParts = academicYear.split("/");
		String previousYear = "";
        if (yearParts.length == 2) {
            int previousYearFirstPart = Integer.parseInt(yearParts[0]) - 1 ;
            int previousYearSecondPart = Integer.parseInt(yearParts[1]) - 1;
            previousYear = previousYearFirstPart + "/" + previousYearSecondPart;
            
        } 
        
		List<Studentfeesstructure> feesstructurePreviousYear = new studentDetailsDAO().getStudentFeesStructure(id, previousYear);
		//List<Feescollection> feesCollection = new feesCollectionDAO().getFeesForTheCurrentYear(id, httpSession.getAttribute(CURRENTACADEMICYEAR).toString());
		Map<Studentfeesstructure,Long> feesMapPreviousYear = new LinkedHashMap<Studentfeesstructure, Long>();
		
		for (Map.Entry<Studentfeesstructure, Long> feescollection2 : feesMapPreviousYear.entrySet()) {
			Studentfeesstructure sf = feescollection2.getKey();
			sf.getFeescategory().getFeescategoryname();
		}
		
		for (Studentfeesstructure singleFeesStructure : feesstructurePreviousYear) {
			Long totalAmountPerCategory = 0l;
			/*for (Feescollection singleFeescollection : feesCollection) {
				
				if(singleFeescollection.getSfsid() == singleFeesStructure.getSfsid()){
					totalAmountPerCategory = totalAmountPerCategory + singleFeescollection.getAmountpaid();
				}
				
			}*/
			Long totalDueAmount = singleFeesStructure.getFeesamount() - singleFeesStructure.getFeespaid() - singleFeesStructure.getConcession() - singleFeesStructure.getWaiveoff();
			
				if(totalDueAmount>0) {
					feesMapPreviousYear.put(singleFeesStructure,totalDueAmount);
				}
			
		}

		//request.setAttribute("admnoDetails", request.getParameter("admno"));
		result.setPreviousYearFeesMap(feesMapPreviousYear);
		result.setPreviousYear(previousYear);
		result.setFeesMap(feesMap);
		result.setStudentNameDetails(dto.getStudentName());
		result.setAdmNoDetails(dto.getAdmissionNo());
		result.setClassAndSecDetails(dto.getClassAndSec());
		result.setStudentIdDetails(dto.getStudentId());
		result.setDateOfFeesDetails(dto.getDateOfFees());
		result.setSuccess(true);
		
		}
		return result;
	}

	public Receiptinfo add() {
		
		List<Feescollection> feescollection = new ArrayList<Feescollection>();
		Receiptinfo receiptInfo =new Receiptinfo();
		boolean createFeesCollection = false;
		if(httpSession.getAttribute(CURRENTACADEMICYEAR)!=null){
		
		String sid = request.getParameter("studentIdDetails");
		String[] amountPaying = request.getParameterValues("amountpaying");
		Long fineAmount = DataUtil.parseLong(request.getParameter("fineamount"));
		Long miscAmount = DataUtil.parseLong(request.getParameter("miscamount"));
		String[] studentSfsIds = request.getParameterValues("studentsfsids");
		
		
		//Get Payment Details
		String paymentMethod = request.getParameter("paymentmethod");
		String ackNo = request.getParameter("ackno");
		String ackNoVoucherNarration = "";
		String transferDate = request.getParameter("transferdate");
		String transferBankname = request.getParameter("transferbankname");
		String chequeNo = request.getParameter("chequeno");
		String chequeNoVoucherNarration = "";
		String chequeDate = request.getParameter("chequedate");
		String chequeBankname = request.getParameter("chequebankname");
		String paymentType = "Cash";
				
			if("banktransfer".equalsIgnoreCase(paymentMethod)) {
				ackNoVoucherNarration = " acknowledgement number: "+ackNo+" , Amount transfer date: "+transferDate;
				paymentType = "Bank Transfer";
			}else if("chequetransfer".equalsIgnoreCase(paymentMethod)) {
				chequeNoVoucherNarration = " cheque number: "+chequeNo+" , Amount clearance date: "+chequeDate;
				paymentType = "Cheque";
			}
				
		//End Payment Details
		
		if(studentSfsIds!=null || miscAmount!=0 || fineAmount!=0){
			
			// create receipt information
			receiptInfo.setAcademicyear(request.getParameter("academicyear"));
			receiptInfo.setDate(DateUtil.indiandateParser(request.getParameter("dateoffeesDetails")));
			receiptInfo.setSid(DataUtil.parseInt(sid));
			receiptInfo.setBranchid(Integer.parseInt(httpSession.getAttribute(BRANCHID).toString()));
			receiptInfo.setUserid(Integer.parseInt(httpSession.getAttribute(USERID).toString()));
			receiptInfo.setClasssec(request.getParameter("classandsecDetails"));
			Long grantTotal = 0l;
			
			/* new feesCollectionDAO().createReceipt(receiptInfo); */
			if(studentSfsIds!=null) {
				for (int i = 0; i < studentSfsIds.length; i++) {
					Feescollection feesCollect = new Feescollection();
					String[] studentSfsIdamount = studentSfsIds[i].split("_");
					feesCollect.setSfsid(DataUtil.parseInt(studentSfsIdamount[0]));
					feesCollect.setAmountpaid(DataUtil.parseLong(amountPaying[Integer.parseInt(studentSfsIdamount[1])]));
					feesCollect.setSid(DataUtil.parseInt(sid));
					feesCollect.setFine(Long.parseLong("0"));
					feesCollect.setDate(DateUtil.indiandateParser(request.getParameter("dateoffeesDetails")));
					feesCollect.setAcademicyear(request.getParameter("academicyear"));
					//feesCollect.setReceiptnumber(receiptInfo.getReceiptnumber());
					feesCollect.setBranchid(Integer.parseInt(httpSession.getAttribute(BRANCHID).toString()));
					feesCollect.setUserid(Integer.parseInt(httpSession.getAttribute("userloginid").toString()));
					feescollection.add(feesCollect);
					
					String[] totalAmount = studentSfsIds[i].split("_");
					grantTotal+=DataUtil.parseLong(amountPaying[Integer.parseInt(totalAmount[1])]);
				}
			}
				receiptInfo.setPaymenttype(paymentType);
				receiptInfo.setFine(fineAmount);
				receiptInfo.setMisc(miscAmount);
				receiptInfo.setTotalamount(grantTotal+fineAmount+miscAmount);
				/* createFeesCollection = new feesCollectionDAO().create(feescollection); */
				
			//Pass Receipt : Credit the student Fees Receivable & debit the cash
			
			BigDecimal onlyTotalFee = new BigDecimal(receiptInfo.getTotalamount()-receiptInfo.getFine()-receiptInfo.getMisc());	
				
			int crFees = getLedgerAccountId("studentfeesreceivable"+Integer.parseInt(httpSession.getAttribute(BRANCHID).toString()));
			int drAccount = 0;
			
			if("cashpayment".equalsIgnoreCase(paymentMethod)) {
				drAccount = getLedgerAccountId(httpSession.getAttribute(username).toString()+Integer.parseInt(httpSession.getAttribute(BRANCHID).toString()));
			}else if("banktransfer".equalsIgnoreCase(paymentMethod)) {
				drAccount = getLedgerAccountId(transferBankname+Integer.parseInt(httpSession.getAttribute(BRANCHID).toString()));
			}else if("chequetransfer".equalsIgnoreCase(paymentMethod)) {
				drAccount = getLedgerAccountId(chequeBankname+Integer.parseInt(httpSession.getAttribute(BRANCHID).toString()));
			} 
			
			
			VoucherEntrytransactions transactions = new VoucherEntrytransactions();
			
			transactions.setDraccountid(drAccount);
			transactions.setCraccountid(crFees);
			transactions.setDramount(onlyTotalFee);
			transactions.setCramount(onlyTotalFee);
			transactions.setVouchertype(1);
			transactions.setTransactiondate(receiptInfo.getDate());
			transactions.setEntrydate(DateUtil.todaysDate());
			transactions.setNarration("Towards Fees Payment:  "+ackNoVoucherNarration+" "+chequeNoVoucherNarration);
			transactions.setCancelvoucher("no");
			transactions.setFinancialyear(new AccountDAO().getCurrentFinancialYear(Integer.parseInt(httpSession.getAttribute(BRANCHID).toString())).getFinancialid());
			transactions.setBranchid(Integer.parseInt(httpSession.getAttribute(BRANCHID).toString()));
			transactions.setUserid(Integer.parseInt(httpSession.getAttribute(USERID).toString()));
			
			String updateDrAccount="update Accountdetailsbalance set currentbalance=currentbalance+"+onlyTotalFee+" where accountdetailsid="+drAccount;

			String updateCrAccount="update Accountdetailsbalance set currentbalance=currentbalance+"+onlyTotalFee+" where accountdetailsid="+crFees;
			
			// End Receipt
			
			//Pass J.V. : Credit the student Fees as Income & debit the unearned revenue
			
			int crFeesIncome = getLedgerAccountId("studentfeesincome"+Integer.parseInt(httpSession.getAttribute(BRANCHID).toString()));
			int drAccountIncome = getLedgerAccountId("unearnedstudentfeesincome"+Integer.parseInt(httpSession.getAttribute(BRANCHID).toString()));
			
			VoucherEntrytransactions transactionsIncome = new VoucherEntrytransactions();
			
			transactionsIncome.setDraccountid(drAccountIncome);
			transactionsIncome.setCraccountid(crFeesIncome);
			transactionsIncome.setDramount(onlyTotalFee);
			transactionsIncome.setCramount(onlyTotalFee);
			transactionsIncome.setVouchertype(4);
			transactionsIncome.setTransactiondate(receiptInfo.getDate());
			transactionsIncome.setEntrydate(DateUtil.todaysDate());
			transactionsIncome.setNarration("Towards Fees Payment:  "+ackNoVoucherNarration+" "+chequeNoVoucherNarration);
			transactionsIncome.setCancelvoucher("no");
			transactionsIncome.setFinancialyear(new AccountDAO().getCurrentFinancialYear(Integer.parseInt(httpSession.getAttribute(BRANCHID).toString())).getFinancialid());
			transactionsIncome.setBranchid(Integer.parseInt(httpSession.getAttribute(BRANCHID).toString()));
			transactionsIncome.setUserid(Integer.parseInt(httpSession.getAttribute(USERID).toString()));
			
			String updateDrAccountIncome="update Accountdetailsbalance set currentbalance=currentbalance+"+onlyTotalFee+" where accountdetailsid="+drAccountIncome;

			String updateCrAccountIncome="update Accountdetailsbalance set currentbalance=currentbalance+"+onlyTotalFee+" where accountdetailsid="+crFeesIncome;
			
			// End J.V
			  
			createFeesCollection = new feesCollectionDAO().create(receiptInfo,feescollection,transactions,updateDrAccount,updateCrAccount, transactionsIncome, updateDrAccountIncome,updateCrAccountIncome);
			
			if(createFeesCollection) {
				getFeesDetails(sid,request.getParameter("academicyear"));
				Parents parent = new studentDetailsDAO().readUniqueObjectParents(Integer.parseInt(sid));
				String studentName = parent.getStudent().getName().substring(0, Math.min(parent.getStudent().getName().length(), 17));
				new SmsService(request, response).sendSMS(parent.getContactnumber(), "of "+studentName+",Rs."+String.valueOf(receiptInfo.getTotalamount()) , "fees");
				//new SmsService(request, response).sendSMS(parent.getContactnumber(), "Total "+String.valueOf(receiptInfo.getTotalamount()) , "fees");
			}
			
		}
		}
		return receiptInfo;
	}

	private void getFeesDetails(String sid, String academicYear) {
		
		try {
			long id = Long.parseLong(sid);
			
			List<Receiptinfo> rinfo = new feesCollectionDAO().getReceiptDetailsPerStudent(id,academicYear);
			request.setAttribute("receiptinfo",rinfo);
			List<Studentfeesstructure> feesstructure = new studentDetailsDAO().getStudentFeesStructure(id, academicYear);
			
			long totalSum = 0l;
			for (Receiptinfo receiptInfoSingle : rinfo) {
				totalSum = totalSum + receiptInfoSingle.getTotalamount()-receiptInfoSingle.getMisc()-receiptInfoSingle.getFine();
			}
			
			long totalFeesAmount = 0l;
			for (Studentfeesstructure studentfeesstructureSingle : feesstructure) {
				totalFeesAmount = totalFeesAmount+studentfeesstructureSingle.getFeesamount()-studentfeesstructureSingle.getWaiveoff()-studentfeesstructureSingle.getConcession();
			}
			
				httpSession.setAttribute("feesstructure", feesstructure);
				httpSession.setAttribute("sumoffees", totalSum);
				httpSession.setAttribute("dueamount", totalFeesAmount-totalSum);
				httpSession.setAttribute("totalfees", totalFeesAmount);
				httpSession.setAttribute("academicPerYear", academicYear);
				httpSession.setAttribute("currentAcademicYear", academicYear);
				
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void preview(Receiptinfo receiptInfo) {
		
		if(httpSession.getAttribute(CURRENTACADEMICYEAR)!=null){
		
			Receiptinfo rinfo = new feesCollectionDAO().getReceiptInfoDetails(receiptInfo.getReceiptnumber());
			Set<Feescollection> setFeesCollection = rinfo.getFeesCollectionRecords();
			Map<String,Long> feeCatMap = new HashMap<String, Long>();

			for (Feescollection feescollectionSingle : setFeesCollection) {
				List<Studentfeesstructure> studentfeesstructure = new studentDetailsDAO().getStudentFeesStructureDetails(feescollectionSingle.getSfsid());
				feeCatMap.put(studentfeesstructure.get(0).getFeescategory().getFeescategoryname(), feescollectionSingle.getAmountpaid());
			}
			Date receiptDate = receiptInfo.getDate();
			String reDate = new SimpleDateFormat("dd/MM/yyyy").format(receiptDate);
			Student student = new studentDetailsDAO().readUniqueObject(receiptInfo.getSid());
			httpSession.setAttribute("student", student);
			request.setAttribute("recieptdate", reDate);
			request.setAttribute("recieptinfo", receiptInfo);
			request.setAttribute("feescatmap", feeCatMap);
		}
		
	}

	public DetailsResponseDto previewDetails(String strReceiptNumber, String duplicate, String currentAcademicYear) {
		DetailsResponseDto result = DetailsResponseDto.builder().build();
		
		if(currentAcademicYear!=null){
            Receiptinfo rinfo = new feesCollectionDAO().getReceiptInfoDetails(Integer.parseInt(strReceiptNumber));
			Set<Feescollection> setFeesCollection = rinfo.getFeesCollectionRecords();
			Map<String,Long> feeCatMap = new HashMap<String, Long>();

			for (Feescollection feescollectionSingle : setFeesCollection) {
				List<Studentfeesstructure> studentfeesstructure = new studentDetailsDAO().getStudentFeesStructureDetails(feescollectionSingle.getSfsid());
				feeCatMap.put(studentfeesstructure.get(0).getFeescategory().getFeescategoryname(), feescollectionSingle.getAmountpaid());
			}
			Date receiptDate = rinfo.getDate();
			String reDate = new SimpleDateFormat("dd/MM/yyyy").format(receiptDate);
			Student student = new studentDetailsDAO().readUniqueObject(rinfo.getSid());
			Parents parents = new parentsDetailsDAO().readUniqueObject(rinfo.getSid());
			Login userLogin = new UserDAO().getUniqueObject(rinfo.getUserid());
			result.setParents(parents);
			result.setStudent(student);
			result.setReceiptDate(reDate);
			result.setReceiptInfo(rinfo);
			result.setFeeCatMap(feeCatMap);
			result.setDuplicate(duplicate);
			result.setUserLogin(userLogin);
			NumberToWord toWord = new NumberToWord();
			String grandTotal = toWord.convert(rinfo.getTotalamount().intValue());
			result.setGrandTotal(grandTotal+" "+"Only");
			result.setSuccess(true);
			
			getFeesDetails(String.valueOf(rinfo.getSid()), rinfo.getAcademicyear());
		}
		return result;
	}

	public DetailsResponseDto previewFeesDetails(String sId, String strReceiptNo, String currentAcademicYear) {
		DetailsResponseDto result = DetailsResponseDto.builder().build();
		
		if(currentAcademicYear!=null){
			long sid=DataUtil.parseLong(sId);
			int receiptNo = DataUtil.parseInt(strReceiptNo);
			 
			Receiptinfo rinfo = new feesCollectionDAO().getReceiptInfoDetails(receiptNo);
			Set<Feescollection> setFeesCollection = rinfo.getFeesCollectionRecords();
			Map<String,Long> feeCatMap = new HashMap<String, Long>();

			for (Feescollection feescollectionSingle : setFeesCollection) {
				List<Studentfeesstructure> studentfeesstructure = new studentDetailsDAO().getStudentFeesStructureDetails(feescollectionSingle.getSfsid());
				feeCatMap.put(studentfeesstructure.get(0).getFeescategory().getFeescategoryname(), feescollectionSingle.getAmountpaid());
			}
			Date receiptDate = rinfo.getDate();
			String reDate = new SimpleDateFormat("yyyy-MM-dd").format(receiptDate);
			Student student = new studentDetailsDAO().readUniqueObject(sid);
			result.setStudent(student);
			result.setReceiptDate(reDate);
			result.setReceiptInfo(rinfo);
			result.setFeeCatMap(feeCatMap);
			result.setDuplicate("duplicate");
			result.setSuccess(true);

			getFeesDetails(String.valueOf(rinfo.getSid()), rinfo.getAcademicyear());
			
		}
		return result;
	}

	public void cancelFeesReceipt() {
		
		if(httpSession.getAttribute(CURRENTACADEMICYEAR)!=null){
			
			String rid = request.getParameter("receiptid");
			String jid = request.getParameter("journalid");
			int feesReceiptId = DataUtil.parseInt(request.getParameter("id"));
			boolean result=false;
			List<Feescollection> feesCollection = new feesCollectionDAO().getFeesCollectionDetails(feesReceiptId);
			Date now = new Date();
	        String pattern = "yyyy-MM-dd";
	        SimpleDateFormat formatter = new SimpleDateFormat(pattern);
			String todaysDate = formatter.format(now);
			
			if(rid !=null && jid !=null ) {
				
				int receiptId = DataUtil.parseInt(rid);
				int journalId = DataUtil.parseInt(jid);
			
			// Cancel Voucher

				VoucherEntrytransactions receiptVoucherTransaction = new AccountDAO().getVoucherDetails(String.valueOf(receiptId));
				
			
				String updateReceiptDrAccount="update Accountdetailsbalance set currentbalance=currentbalance-"+receiptVoucherTransaction.getDramount()+" where accountdetailsid="+receiptVoucherTransaction.getDraccountid();
				String updateReceiptCrAccount="update Accountdetailsbalance set currentbalance=currentbalance-"+receiptVoucherTransaction.getCramount()+" where accountdetailsid="+receiptVoucherTransaction.getCraccountid();

				String cancelReceiptVoucher = "update VoucherEntrytransactions set cancelvoucher='yes', vouchercancellationdate='"+todaysDate+"' where transactionsid="+receiptId;
				
			
				VoucherEntrytransactions journalVoucherTransaction = new AccountDAO().getVoucherDetails(String.valueOf(journalId));
				
				// Dr
				Accountdetails accountDetailsDr = new AccountDAO().getAccountDetails(journalVoucherTransaction.getDraccountid());
				String updateJournalDrAccount= null;
				if(accountDetailsDr.getAccountGroupMaster().getAccountgroupid()==1 || accountDetailsDr.getAccountGroupMaster().getAccountgroupid()==5) {
					updateJournalDrAccount="update Accountdetailsbalance set currentbalance=currentbalance-"+journalVoucherTransaction.getDramount()+" where accountdetailsid="+journalVoucherTransaction.getDraccountid();
				}else {
					updateJournalDrAccount="update Accountdetailsbalance set currentbalance=currentbalance+"+journalVoucherTransaction.getDramount()+" where accountdetailsid="+journalVoucherTransaction.getDraccountid();
				}
				
				//Cr
				
				Accountdetails accountDetailsCr = new AccountDAO().getAccountDetails(journalVoucherTransaction.getCraccountid());
				String updateJournalCrAccount= null;
				
				if(accountDetailsCr.getAccountGroupMaster().getAccountgroupid()==2 || accountDetailsCr.getAccountGroupMaster().getAccountgroupid()==3 || accountDetailsCr.getAccountGroupMaster().getAccountgroupid()==4) {
					updateJournalCrAccount="update Accountdetailsbalance set currentbalance=currentbalance-"+journalVoucherTransaction.getCramount()+" where accountdetailsid="+journalVoucherTransaction.getCraccountid();
				}else {
					updateJournalCrAccount="update Accountdetailsbalance set currentbalance=currentbalance+"+journalVoucherTransaction.getCramount()+" where accountdetailsid="+journalVoucherTransaction.getCraccountid();
				}
				
				String cancelJournalVoucher = "update VoucherEntrytransactions set cancelvoucher='yes', vouchercancellationdate='"+todaysDate+"' where transactionsid="+journalId;
				
			
			// End Cancel Voucher
			result = new feesDetailsDAO().cancelFeesReceipt(feesReceiptId, feesCollection, updateReceiptDrAccount, updateReceiptCrAccount, cancelReceiptVoucher,
					updateJournalDrAccount, updateJournalCrAccount, cancelJournalVoucher);
		}else {
			result = new feesDetailsDAO().cancelFeesReceipt(feesReceiptId, feesCollection, null, null, null,
					null, null, null);
		}
		
			request.setAttribute("cancelreceiptresult", result);
				
		}
	}

	public CancelledReceiptsResponseDto viewCancelledReceipts(CancelledReceiptsDto dto, String strBranchId, String dayOne, String dayOneCancel, String dateFromCancel, String dateToCancel) {
		CancelledReceiptsResponseDto result = CancelledReceiptsResponseDto.builder().build();

		List<Receiptinfo> feesDetailsList = new ArrayList<Receiptinfo>();
		String branchId = dto.getBranchId();
		int idBranch = 0;
               
		if(strBranchId!=null){
		

	        if(branchId!=null) {
	        	String[] branchIdName = branchId.split(":");
	        	idBranch = Integer.parseInt(branchIdName[0]);
				result.setFeesDetailsBranchName(branchIdName[1]);
				result.setBranchName("Branch Name:");
	        }else {
	        	idBranch = Integer.parseInt(strBranchId);
	        }
	        
		String queryMain ="From Receiptinfo as feesdetails where cancelreceipt=1 and feesdetails.branchid="+idBranch+" AND";
		String toDate= DataUtil.emptyString(dto.getToDate());
		String fromDate = DataUtil.emptyString(dto.getFromDate());
		String oneDay = DataUtil.emptyString(dto.getOneDay());
		
		
			String querySub = "";
			
			if(!oneDay.equalsIgnoreCase("")){
				querySub = " feesdetails.date = '"+oneDay+"'" ;
				 result.setDayOneCancel(oneDay);
				 result.setDateFromCancel("");
				 result.setDateToCancel("");
			}else if(!"".equalsIgnoreCase(DataUtil.emptyString(dayOne))) {
				querySub = " feesdetails.date = '"+dayOneCancel+"'" ;
			}
			
			if(!fromDate.equalsIgnoreCase("")  && !toDate.equalsIgnoreCase("")){
				querySub = " feesdetails.date between '"+fromDate+"' AND '"+toDate+"'";
				result.setDateFromCancel(fromDate);
				result.setDateToCancel(toDate);
				 result.setDayOneCancel("");
				
			}else if(!"".equalsIgnoreCase(DataUtil.emptyString(dateFromCancel)) &&
					!"".equalsIgnoreCase(DataUtil.emptyString(dateToCancel)) ) {
				querySub = " feesdetails.date between '"+dateFromCancel+"' AND '"+dateToCancel+"'";
			}
			
			queryMain = queryMain+querySub;
			/*queryMain = "FROM Parents as parents where  parents.Student.dateofbirth = '2006-04-06'"; */
			feesDetailsList = new UserDAO().getReceiptDetailsList(queryMain);
			
	}
			long sumOfFees = 0l;
			for (Receiptinfo receiptinfo : feesDetailsList) {
				sumOfFees = sumOfFees + receiptinfo.getTotalamount();
			}

			result.setFeesDetailsList(feesDetailsList);
			result.setSumOfFees(sumOfFees);
			result.setSuccess(true);

			return result;
	}

	public ResultResponse undoFeesReceipt(String strReceiptId, String currentAcademicYear) {
		ResultResponse resultResponse = ResultResponse.builder().success(true).build();

		if(currentAcademicYear!=null){
			
			int receiptId = DataUtil.parseInt(strReceiptId);
			List<Feescollection> feesCollection = new feesCollectionDAO().getFeesCollectionDetails(receiptId);
			boolean result = new feesDetailsDAO().undoFeesReceipt(receiptId, feesCollection);
			
			resultResponse.setSuccess(result);
				
		}
		return resultResponse;
	}

	public ResultResponse getFeesReport(FeesReportDto dto, String branchId, String currentAcademicYear) {
		ResultResponse result = ResultResponse.builder().success(true).build();


		String academicYear = dto.getAcademicYear();
		String[] feesCat = dto.getFeesCat();
		
		//Get Students
		
		List<Parents> searchStudentList = new ArrayList<Parents>();
		
		if(branchId!=null){
		
		String queryMain = "From Parents as parents where";
		String[] addClass = dto.getAddClass();
		StringBuffer conClassStudying = new StringBuffer();

			int i = 0;
			for (String classOne : addClass) {
				
				if(i>0) {
					conClassStudying.append("' OR parents.Student.classstudying LIKE '"+classOne+"--"+"%");
				}else {
					conClassStudying.append(classOne+"--"+"%");
				}
				
				i++;
			}
		
		String classStudying = DataUtil.emptyString(conClassStudying.toString());
		String querySub = "";

		if (!classStudying.equalsIgnoreCase("")) {
			querySub = querySub + " (parents.Student.classstudying like '"
					+ classStudying + "') AND parents.Student.archive=0 and parents.Student.passedout=0 AND parents.Student.droppedout=0 and parents.Student.leftout=0 AND parents.Student.branchid="+Integer.parseInt(branchId)+" order by parents.Student.admissionnumber ASC";
		}

		if(!"".equalsIgnoreCase(querySub)) {
			queryMain = queryMain + querySub;
			searchStudentList = new studentDetailsDAO().getStudentsList(queryMain);
		}
		
	}
		//End Students
		
		
		if(currentAcademicYear!=null){
			
			List<StudentFeesReport> studentFeesReportList = new ArrayList<StudentFeesReport>();
			
			for (Parents parents : searchStudentList) {
				
				StudentFeesReport studentFeesReport = new StudentFeesReport();
				
				long id = parents.getStudent().getSid();
				
				List<Integer> feesCatList = new ArrayList<>(); 
				for (String feescat : feesCat) {
					feesCatList.add(Integer.parseInt(feescat));
				}
				List<Studentfeesstructure> feesstructure = new studentDetailsDAO().getStudentFeesStructurebyFeesCategory(id,feesCatList);
				
				if (feesstructure.size() > 0) {
					
					studentFeesReport.setParents(parents);
					studentFeesReport.setStudentFeesStructure(feesstructure);
					
					studentFeesReportList.add(studentFeesReport);
					
				}
			}

			result.setResultList(studentFeesReportList);
		}

		return result;
	  }
	
	
	
	public FeesDashboardResponseDto getFeesDetailsDashBoard(ClassesHierarchyDto dto, String strBranchId, String currentAcademicYear) {
		FeesDashboardResponseDto result = FeesDashboardResponseDto.builder().build();

		Long totalFeesAmount = 0l;
		Long totalPaidAmount = 0l;
		Long totalDueAmount = 0l;
		

		// Get Students

		List<Parents> searchStudentList = new ArrayList<Parents>();

		if (strBranchId != null) {

			String queryMain = "From Parents as parents where parents.Student.branchid="+Integer.parseInt(strBranchId)+" AND ";
			standardActionAdapter.viewClasses();
			List<Classsec> classList = dto.getClasssecList();
			
			
			StringBuffer conClassStudying = new StringBuffer();

			int i = 0;
			for (Classsec classOne : classList) {

				if (i > 0) {
					conClassStudying.append("' OR parents.Student.classstudying LIKE '" + classOne.getClassdetails() + "--" + "%");
				} else {
					conClassStudying.append(classOne.getClassdetails() + "--" + "%");
				}

				i++;
			}

			String classStudying = DataUtil.emptyString(conClassStudying.toString());
			String querySub = "";

			if (!classStudying.equalsIgnoreCase("")) {
				querySub = querySub + " (parents.Student.classstudying like '"
						+ classStudying	+ "') AND parents.Student.archive=0 and parents.Student.passedout=0 AND parents.Student.droppedout=0 and parents.Student.leftout=0 "
						+ " order by parents.Student.admissionnumber ASC";
			}

			if (!"".equalsIgnoreCase(querySub)) {
				queryMain = queryMain + querySub;
				searchStudentList = new studentDetailsDAO().getStudentsList(queryMain);
			}

		}
		// End Students

		if (currentAcademicYear != null) {

			List<StudentFeesReport> studentFeesReportList = new ArrayList<StudentFeesReport>();

			for (Parents parents : searchStudentList) {

				StudentFeesReport studentFeesReport = new StudentFeesReport();

				long id = parents.getStudent().getSid();
				List<Studentfeesstructure> feesstructure = new studentDetailsDAO().getStudentFeesStructure(id,
						currentAcademicYear);

				studentFeesReport.setParents(parents);
				studentFeesReport.setStudentFeesStructure(feesstructure);

				studentFeesReportList.add(studentFeesReport);
			}
			
			
			for (StudentFeesReport studentFeesReport : studentFeesReportList) {
				
				for (Studentfeesstructure studentFeesStructure : studentFeesReport.getStudentFeesStructure()) {
					totalFeesAmount+=studentFeesStructure.getFeesamount() - studentFeesStructure.getConcession() - studentFeesStructure.getWaiveoff();
					totalPaidAmount+=studentFeesStructure.getFeespaid();
					totalDueAmount = totalDueAmount + (studentFeesStructure.getFeesamount()-studentFeesStructure.getFeespaid()  - studentFeesStructure.getConcession() - studentFeesStructure.getWaiveoff());
				}
			}
;
			result.setTotalFeesAmount(totalFeesAmount);
			result.setTotalPaidAmount(totalPaidAmount);
			result.setTotalDueAmount(totalDueAmount);
		}
		
		
		
		//get monthly and daily fees details
		 
		List<Receiptinfo> feesDetailsListDaily = new ArrayList<Receiptinfo>();
		List<Receiptinfo> feesDetailsListMonthly = new ArrayList<Receiptinfo>();
		String branchId = dto.getSelectedBranchId();
		int idBranch = 0;
		String Currentmonth = null;        
		
		if(strBranchId!=null){
		

	        if(branchId!=null) {
	        	String[] branchIdName = branchId.split(":");
	        	idBranch = Integer.parseInt(branchIdName[0]);
				result.setBranchIdName(branchIdName[1]);
				result.setBranchName("Branch Name:");
	        }else {
	        	idBranch = Integer.parseInt(strBranchId);
	        }
	        
		String queryMainDaily ="From Receiptinfo as feesdetails where feesdetails.cancelreceipt=0 and feesdetails.branchid="+idBranch+" AND";
		String queryMainMonthly ="From Receiptinfo as feesdetails where feesdetails.cancelreceipt=0 and feesdetails.branchid="+idBranch+" AND";


		Date monthOf = new Date();
		 
		Calendar cStart = Calendar.getInstance();
		cStart.setTime(monthOf);
		cStart.set(Calendar.DAY_OF_MONTH, cStart.getActualMinimum(Calendar.DAY_OF_MONTH));
		monthOf = cStart.getTime();
		Timestamp dateFrom = new Timestamp(monthOf.getTime());
		String TimestampFrom = new SimpleDateFormat("YYYY-MM-dd").format(dateFrom);
		
		cStart.set(Calendar.DAY_OF_MONTH, cStart.getActualMaximum(Calendar.DAY_OF_MONTH));
		Date lastDayOfMonth = cStart.getTime();
		Timestamp dateto = new Timestamp(lastDayOfMonth.getTime());
		String Timestampto = new SimpleDateFormat("YYYY-MM-dd").format(dateto);
		
		Currentmonth = cStart.getDisplayName(Calendar.MONTH, Calendar.LONG, Locale.getDefault());
		
		
		String pattern = "yyyy-MM-dd";
		String todaysDate =new SimpleDateFormat(pattern).format(new Date());
		
			String querySubDaily = "";
			String querySubMonthly = "";
			// Daily Fees
			querySubDaily = " feesdetails.date = '"+todaysDate+"'" ;
			queryMainDaily = queryMainDaily+querySubDaily;
			/*queryMain = "FROM Parents as parents where  parents.Student.dateofbirth = '2006-04-06'"; */
			System.out.println("SEARCH QUERY ***** "+queryMainDaily);
			feesDetailsListDaily = new UserDAO().getReceiptDetailsList(queryMainDaily);
			
			// Monthly Fees
			    querySubMonthly = " feesdetails.date between '"+TimestampFrom+"' AND '"+Timestampto+"'";
			    queryMainMonthly = queryMainMonthly+querySubMonthly;
			/*queryMain = "FROM Parents as parents where  parents.Student.dateofbirth = '2006-04-06'"; */
			System.out.println("SEARCH QUERY ***** "+queryMainMonthly);
			feesDetailsListMonthly = new UserDAO().getReceiptDetailsList(queryMainMonthly);
			
				}
			long sumOfFeesDaily = 0l;
			for (Receiptinfo receiptinfo : feesDetailsListDaily) {
				sumOfFeesDaily = sumOfFeesDaily + receiptinfo.getTotalamount();
			}
			
			long sumOfFeesMonthly = 0l;
			for (Receiptinfo receiptinfo : feesDetailsListMonthly) {
				sumOfFeesMonthly = sumOfFeesMonthly + receiptinfo.getTotalamount();
			}

			result.setSumOfFeesDaily(sumOfFeesDaily);
			result.setSumOfFeesMonthly(sumOfFeesMonthly);
			result.setCurrentMonth(Currentmonth+"'s");
			result.setSuccess(true);

			return result;
	}
	
	private Integer getLedgerAccountId(String itemAccount) {
		
		int result = 0;
	 	
	 	Properties properties = new Properties();
        InputStream inputStream = this.getClass().getClassLoader().getResourceAsStream("Util.properties");
		
        		try {
					properties.load(inputStream);
				} catch (IOException e) {
					e.printStackTrace();
				}
		    
        		String ItemLedgerId = properties.getProperty(itemAccount);
		    
		    if(ItemLedgerId!=null) {
		    	result = Integer.parseInt(ItemLedgerId);
		    }else {
		    	String ItemLedger = properties.getProperty(itemAccount.toLowerCase());
		    	result = Integer.parseInt(ItemLedger.toLowerCase());
		    }
		    
		    return result;
	}

	public FeesDetailsResponseDto getFeesDetails(FeesReportDto dto) {
		FeesDetailsResponseDto result = FeesDetailsResponseDto.builder().build();
		
		try {
			long id = Long.parseLong(dto.getStudentId());
			String academicYear = dto.getAcademicYear();
			
			//Currentacademicyear currentYear = new YearDAO().showYear();
			//httpSession.setAttribute("currentyearfromservice",currentYear.getCurrentacademicyear());
			
			List<Receiptinfo> rinfo = new feesCollectionDAO().getReceiptDetailsPerStudent(id,academicYear);
			result.setReceiptInfo(rinfo);
			List<Studentfeesstructure> feesstructure = new studentDetailsDAO().getStudentFeesStructure(id, academicYear);
			
			long totalSum = 0l;
			for (Receiptinfo receiptInfoSingle : rinfo) {
				totalSum = totalSum + receiptInfoSingle.getTotalamount();
			}
			
			long totalFeesAmount = 0l;
			for (Studentfeesstructure studentfeesstructureSingle : feesstructure) {
				totalFeesAmount = totalFeesAmount+studentfeesstructureSingle.getFeesamount()-studentfeesstructureSingle.getWaiveoff()-studentfeesstructureSingle.getConcession();
			}

				result.setFeesStructure(feesstructure);
				result.setTotalSum(totalSum);
				result.setDueAmount(totalFeesAmount-totalSum);
				result.setTotalFeesAmount(totalFeesAmount);
				result.setAcademicPerYear(academicYear);
				result.setCurrentAcademicYear(academicYear);
				result.setSuccess(true);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	public boolean exportDataForStudentsFeesReport() {

		boolean writeSucees = false;
		
		try {

			List<StudentFeesReport> studentFeesReportList = (List<StudentFeesReport>) httpSession.getAttribute("studentfeesreportlist");
			
			// Creating an excel file
			XSSFWorkbook workbook = new XSSFWorkbook();
			XSSFSheet sheet = workbook.createSheet("ListOfStudents");
			Map<String, Object[]> data = new HashMap<String, Object[]>();
			Map<String, Object[]> headerData = new HashMap<String, Object[]>();
			headerData.put("Header",
					new Object[] { "UID", "Admission Number","Student Name", "Class & Sec","Father Name", "Contact Number", "Fees Details", "Total Due Summary", "Total Fees Summary"});
			int i = 1;
			for (StudentFeesReport studentFeesReport : studentFeesReportList) {
				
				List<Studentfeesstructure> sfs = studentFeesReport.getStudentFeesStructure();
				String feesDetails = "";
				Long dueAmount = 0l;
				Long totalAmount = 0l;
				
				for (Studentfeesstructure studentFee : sfs) {
					Long feesDue = studentFee.getFeesamount()-studentFee.getFeespaid() - studentFee.getConcession() - studentFee.getWaiveoff();
					Long feesTotal = studentFee.getFeesamount() - studentFee.getConcession() - studentFee.getWaiveoff();
					dueAmount = dueAmount+studentFee.getFeesamount()-studentFee.getFeespaid()-studentFee.getConcession()-studentFee.getWaiveoff();
					totalAmount = totalAmount+studentFee.getFeesamount()-studentFee.getConcession()-studentFee.getWaiveoff();
					feesDetails=feesDetails+studentFee.getFeescategory().getFeescategoryname()+":"+feesDue+"/"+feesTotal+"\n";
				}
				
				data.put(Integer.toString(i),
						new Object[] { DataUtil.emptyString(studentFeesReport.getParents().getStudent().getStudentexternalid()),  DataUtil.emptyString(studentFeesReport.getParents().getStudent().getAdmissionnumber()),
								 DataUtil.emptyString(studentFeesReport.getParents().getStudent().getName()),
								 DataUtil.emptyString(studentFeesReport.getParents().getStudent().getClassstudying().replace("--", " ")),
								 DataUtil.emptyString(studentFeesReport.getParents().getFathersname()),
								 DataUtil.emptyString(studentFeesReport.getParents().getContactnumber()),
								 DataUtil.emptyString(feesDetails),
								 String.valueOf(dueAmount),
								 String.valueOf(totalAmount) });
				i++;
			}
			
			Row headerRow = sheet.createRow(0);
			Object[] objArrHeader = headerData.get("Header");
			int cellnum1 = 0;
			for (Object obj : objArrHeader) {
				Cell cell = headerRow.createCell(cellnum1++);
				if (obj instanceof String)
					cell.setCellValue((String) obj);
			}
			Set<String> keyset = data.keySet();
			int rownum = 1;
			for (String key : keyset) {
				Row row = sheet.createRow(rownum++);
				Object[] objArr = data.get(key);
				int cellnum = 0;
				for (Object obj : objArr) {
					Cell cell = row.createCell(cellnum++);
					if (obj instanceof Date)
						cell.setCellValue((Date) obj);
					else if (obj instanceof Boolean)
						cell.setCellValue((Boolean) obj);
					else if (obj instanceof String)
						cell.setCellValue((String) obj);
					else if (obj instanceof Double)
						cell.setCellValue((Double) obj);
				}
			}
				
				FileOutputStream out = new FileOutputStream(new File(System.getProperty("java.io.tmpdir")+"/studentsfeesreport.xlsx"));
				workbook.write(out);
				out.close();
				writeSucees = true;
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return writeSucees;
		// getFile(name, path);
	}

	public boolean downlaod() {
		boolean result = false;
		try {

			File downloadFile = new File(System.getProperty("java.io.tmpdir")+"/studentsfeesreport.xlsx");
	        FileInputStream inStream = new FileInputStream(downloadFile);

	        // get MIME type of the file
			String mimeType = "application/vnd.ms-excel";

			// set content attributes for the response
			response.setContentType(mimeType);
			// response.setContentLength((int) bis.length());

			// set headers for the response
			String headerKey = "Content-Disposition";
			String headerValue = String.format("attachment; filename=\"%s\"",
					"studentsfeesreport.xlsx");
			response.setHeader(headerKey, headerValue);

			// get output stream of the response
			OutputStream outStream = response.getOutputStream();

			byte[] buffer = new byte[BUFFER_SIZE];
			int bytesRead = -1;

			// write bytes read from the input stream into the output stream
			while ((bytesRead = inStream.read(buffer)) != -1) {
				outStream.write(buffer, 0, bytesRead);
			}

			inStream.close();
			outStream.close();
			result = true;
		} catch (Exception e) {
			System.out.println("" + e);
		}
		return result;
	}

	public ResultResponse getFeesStampDueReport(FeesReportDto dto, String branchId, String currentAcademicYear) {
		ResultResponse result = ResultResponse.builder().build();
		
		String academicYear = dto.getAcademicYear();
		//Get Students
		
		List<Parents> searchStudentList = new ArrayList<Parents>();
		
		if(branchId!=null){
		
		String queryMain = "From Parents as parents where";
		String[] addClass = dto.getAddClass();
		StringBuffer conClassStudying = new StringBuffer();

			int i = 0;
			for (String classOne : addClass) {
				
				if(i>0) {
					conClassStudying.append("' OR parents.Student.classstudying LIKE '"+classOne+"--"+"%");
				}else {
					conClassStudying.append(classOne+"--"+"%");
				}
				
				i++;
			}
		
		String classStudying = DataUtil.emptyString(conClassStudying.toString());
		String querySub = "";

		if (!classStudying.equalsIgnoreCase("")) {
			querySub = querySub + " (parents.Student.classstudying like '"
					+ classStudying + "') AND parents.Student.archive=0 and parents.Student.passedout=0 AND parents.Student.droppedout=0 and parents.Student.leftout=0 AND parents.branchid="+Integer.parseInt(branchId)+" order by parents.Student.admissionnumber ASC";
		}

		if(!"".equalsIgnoreCase(querySub)) {
			queryMain = queryMain + querySub;
			searchStudentList = new studentDetailsDAO().getStudentsList(queryMain);
		}
		
	}
		//End Students
		
		
		if(currentAcademicYear!=null){
			
			List<StudentFeesReport> studentFeesReportList = new ArrayList<StudentFeesReport>();
			
			for (Parents parents : searchStudentList) {
				
				StudentFeesReport studentFeesReport = new StudentFeesReport();
				
				long id = parents.getStudent().getSid();
				List<Studentfeesstructure> feesstructure = new studentDetailsDAO().getStudentFeesStructure(id, academicYear);
				
				if (feesstructure.size() == 0) {
					
					studentFeesReport.setParents(parents);
					studentFeesReport.setStudentFeesStructure(feesstructure);
					
					studentFeesReportList.add(studentFeesReport);
					
				}
			}

			result.setResultList(studentFeesReportList);
			result.setSuccess(true);
		}
		return result;
	  }
	
	public StampFeeResponseDto getotherStampFees(StampFeesDto dto, String currentAcademicYear) {
		StampFeeResponseDto result = StampFeeResponseDto.builder().build();

		if(currentAcademicYear!=null){
		String academicYear = dto.getAcademicYear();

		long id = Long.parseLong(dto.getStudentId());
		List<Studentotherfeesstructure> feesstructure = new studentDetailsDAO().getStudentOtherFeesStructure(id, academicYear);
		//List<Feescollection> feesCollection = new feesCollectionDAO().getFeesForTheCurrentYear(id, httpSession.getAttribute(CURRENTACADEMICYEAR).toString());
		Map<Studentotherfeesstructure,Long> feesMap = new LinkedHashMap<Studentotherfeesstructure, Long>();

		for (Map.Entry<Studentotherfeesstructure, Long> feescollection2 : feesMap.entrySet()) {
			Studentotherfeesstructure sf = feescollection2.getKey();
			sf.getOtherfeescategory().getFeescategoryname();
		}

		for (Studentotherfeesstructure singleFeesStructure : feesstructure) {
			Long totalAmountPerCategory = 0l;
			/*for (Feescollection singleFeescollection : feesCollection) {
				
				if(singleFeescollection.getSfsid() == singleFeesStructure.getSfsid()){
					totalAmountPerCategory = totalAmountPerCategory + singleFeescollection.getAmountpaid();
				}
				
			}*/
			Long totalDueAmount = singleFeesStructure.getFeesamount() - singleFeesStructure.getFeespaid() - singleFeesStructure.getConcession() - singleFeesStructure.getWaiveoff();

				if(totalDueAmount>0) {
					feesMap.put(singleFeesStructure,totalDueAmount);
				}

		}

		result.setOtherFeesMap(feesMap);
		result.setStudentNameDetails(dto.getStudentName());
		result.setAdmNoDetails(dto.getAdmissionNo());
		result.setClassAndSecDetails(dto.getClassAndSec());
		result.setStudentIdDetails(dto.getStudentId());
		result.setDateOfFeesDetails(dto.getDateOfFees());
		result.setSuccess(true);
		}
		return result;
	}
	
	public FeesDetailsResponseDto getotherFeesDetails(FeesReportDto dto) {
		FeesDetailsResponseDto result = FeesDetailsResponseDto.builder().build();

		try {
			long id = Long.parseLong(dto.getStudentId());
			String academicYear = dto.getAcademicYear();

			//Currentacademicyear currentYear = new YearDAO().showYear();
			//httpSession.setAttribute("currentyearfromservice",currentYear.getCurrentacademicyear());

			List<Receiptinfo> rinfo = new feesCollectionDAO().getReceiptDetailsPerStudent(id,academicYear);
			result.setReceiptInfo(rinfo);
			List<Studentotherfeesstructure> feesstructure = new studentDetailsDAO().getStudentOtherFeesStructure(id, academicYear);

			long totalSum = 0l;
			for (Receiptinfo receiptInfoSingle : rinfo) {
				totalSum = totalSum + receiptInfoSingle.getTotalamount();
			}

			long totalFeesAmount = 0l;
			for (Studentotherfeesstructure studentfeesstructureSingle : feesstructure) {
				totalFeesAmount = totalFeesAmount+studentfeesstructureSingle.getFeesamount()-studentfeesstructureSingle.getWaiveoff()-studentfeesstructureSingle.getConcession();
			}

				result.setOtherFeesStructure(feesstructure);
				result.setTotalSum(totalSum);
				result.setDueAmount(totalFeesAmount-totalSum);
				result.setTotalFeesAmount(totalFeesAmount);
				result.setAcademicPerYear(academicYear);
				result.setCurrentAcademicYear(academicYear);
				result.setSuccess(true);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	public void otherpreview() {

		 long sid=DataUtil.parseLong(request.getParameter("sid"));	
		 int id=DataUtil.parseInt(request.getParameter("id"));
		 long idFees=DataUtil.parseLong(request.getParameter("id"));
		    List<Otherfeescollection> list = new feesCollectionDAO().otherreadListOfObject(id);
				httpSession.setAttribute("feescollection", list);
				Student student = new studentDetailsDAO()
						.readUniqueObject(sid);
				httpSession.setAttribute("student", student);
				Parents parents = new parentsDetailsDAO().readUniqueObject(sid);
				httpSession.setAttribute("parents", parents);
				Feesdetails feesdetails = new feesDetailsDAO()
						.readUniqueObject(idFees);
				NumberToWord toWord = new NumberToWord();
				String grandTotal = "";
				if(feesdetails.getGrandtotal() != null || !feesdetails.getGrandtotal().equalsIgnoreCase("")){
					String gTotal = feesdetails.getGrandtotal();
					gTotal =  gTotal.substring(0, gTotal.indexOf('.'));
					grandTotal = toWord.convert(Integer.parseInt(gTotal));
				}

				StringBuffer res = new StringBuffer();
				String[] strArr = grandTotal.split(" ");

				for(String str : strArr){
					char[] stringArray = str.trim().toCharArray();
					stringArray[0] = Character.toUpperCase(stringArray[0]);
					str = new String(stringArray);
					res.append(str).append(" ");
				}
				grandTotal = res.toString().trim();
				httpSession.setAttribute("grandTotal", grandTotal+" "+"Only");
				httpSession.setAttribute("feesdetails", feesdetails);

	}
	
	public Otherreceiptinfo addother() {

		List<Otherfeescollection> feescollection = new ArrayList<Otherfeescollection>();
		Otherreceiptinfo receiptInfo =new Otherreceiptinfo();
		boolean createFeesCollection = false;
		if(httpSession.getAttribute(CURRENTACADEMICYEAR)!=null){

		String sid = request.getParameter("studentIdDetails");
		String[] amountPaying = request.getParameterValues("amountpaying");
		String[] fine = request.getParameterValues("fine");
		String[] studentSfsIds = request.getParameterValues("studentsfsids");

		//Get Payment Details
		String paymentMethod = request.getParameter("paymentmethod");
		String ackNo = request.getParameter("ackno");
		String ackNoVoucherNarration = "";
		String transferDate = request.getParameter("transferdate");
		String transferBankname = request.getParameter("transferbankname");
		String chequeNo = request.getParameter("chequeno");
		String chequeNoVoucherNarration = "";
		String chequeDate = request.getParameter("chequedate");
		String chequeBankname = request.getParameter("chequebankname");
		String paymentType = "Cash";

			if("banktransfer".equalsIgnoreCase(paymentMethod)) {
				ackNoVoucherNarration = " acknowledgement number: "+ackNo+" , Amount transfer date: "+transferDate;
				paymentType = "Bank Transfer";
			}else if("chequetransfer".equalsIgnoreCase(paymentMethod)) {
				chequeNoVoucherNarration = " cheque number: "+chequeNo+" , Amount clearance date: "+chequeDate;
				paymentType = "Cheque";
			}

		//End Payment Details

		if(studentSfsIds!=null){

			// create receipt information
			receiptInfo.setAcademicyear(request.getParameter("academicyear"));
			receiptInfo.setDate(DateUtil.indiandateParser(request.getParameter("dateoffeesDetails")));
			receiptInfo.setSid(DataUtil.parseInt(sid));
			receiptInfo.setBranchid(Integer.parseInt(httpSession.getAttribute(BRANCHID).toString()));
			receiptInfo.setUserid(Integer.parseInt(httpSession.getAttribute(USERID).toString()));
			receiptInfo.setClasssec(request.getParameter("classandsecDetails"));
			Long grantTotal = 0l;
			for (int i = 0; i < studentSfsIds.length; i++) {
				String[] totalAmount = studentSfsIds[i].split("_");
				grantTotal+=DataUtil.parseLong(amountPaying[Integer.parseInt(totalAmount[1])]);
			}
			receiptInfo.setTotalamount(grantTotal);
			receiptInfo.setPaymenttype(paymentType);
			/* new feesCollectionDAO().createReceipt(receiptInfo); */

				for (int i = 0; i < studentSfsIds.length; i++) {
					Otherfeescollection feesCollect = new Otherfeescollection();
					String[] studentSfsIdamount = studentSfsIds[i].split("_");
					feesCollect.setSfsid(DataUtil.parseInt(studentSfsIdamount[0]));
					feesCollect.setAmountpaid(DataUtil.parseLong(amountPaying[Integer.parseInt(studentSfsIdamount[1])]));
					feesCollect.setSid(DataUtil.parseInt(sid));
					feesCollect.setFine(DataUtil.parseLong(fine[i]));
					feesCollect.setDate(DateUtil.indiandateParser(request.getParameter("dateoffeesDetails")));
					feesCollect.setAcademicyear(request.getParameter("academicyear"));
					//feesCollect.setReceiptnumber(receiptInfo.getReceiptnumber());
					feesCollect.setBranchid(Integer.parseInt(httpSession.getAttribute(BRANCHID).toString()));
					feesCollect.setUserid(Integer.parseInt(httpSession.getAttribute("userloginid").toString()));
					feescollection.add(feesCollect);
				}
				/* createFeesCollection = new feesCollectionDAO().create(feescollection); */

			//Pass Receipt : Credit the student Fees Receivable & debit the cash

			int crFees = getLedgerAccountId("studentfeesreceivable"+Integer.parseInt(httpSession.getAttribute(BRANCHID).toString()));
			int drAccount = 0;

			if("cashpayment".equalsIgnoreCase(paymentMethod)) {
				drAccount = getLedgerAccountId(httpSession.getAttribute(username).toString()+Integer.parseInt(httpSession.getAttribute(BRANCHID).toString()));
			}else if("banktransfer".equalsIgnoreCase(paymentMethod)) {
				drAccount = getLedgerAccountId(transferBankname+Integer.parseInt(httpSession.getAttribute(BRANCHID).toString()));
			}else if("chequetransfer".equalsIgnoreCase(paymentMethod)) {
				drAccount = getLedgerAccountId(chequeBankname+Integer.parseInt(httpSession.getAttribute(BRANCHID).toString()));
			} 


			VoucherEntrytransactions transactions = new VoucherEntrytransactions();

			transactions.setDraccountid(drAccount);
			transactions.setCraccountid(crFees);
			transactions.setDramount(new BigDecimal(receiptInfo.getTotalamount()));
			transactions.setCramount(new BigDecimal(receiptInfo.getTotalamount()));
			transactions.setVouchertype(1);
			transactions.setTransactiondate(receiptInfo.getDate());
			transactions.setEntrydate(DateUtil.todaysDate());
			transactions.setNarration("Towards Fees Payment:  "+ackNoVoucherNarration+" "+chequeNoVoucherNarration);
			transactions.setCancelvoucher("no");
			transactions.setFinancialyear(new AccountDAO().getCurrentFinancialYear(Integer.parseInt(httpSession.getAttribute(BRANCHID).toString())).getFinancialid());
			transactions.setBranchid(Integer.parseInt(httpSession.getAttribute(BRANCHID).toString()));
			transactions.setUserid(Integer.parseInt(httpSession.getAttribute(USERID).toString()));

			String updateDrAccount="update Accountdetailsbalance set currentbalance=currentbalance+"+receiptInfo.getTotalamount()+" where accountdetailsid="+drAccount;

			String updateCrAccount="update Accountdetailsbalance set currentbalance=currentbalance+"+receiptInfo.getTotalamount()+" where accountdetailsid="+crFees;

			// End Receipt

			//Pass J.V. : Credit the student Fees as Income & debit the unearned revenue

			int crFeesIncome = getLedgerAccountId("studentfeesincome"+Integer.parseInt(httpSession.getAttribute(BRANCHID).toString()));
			int drAccountIncome = getLedgerAccountId("unearnedstudentfeesincome"+Integer.parseInt(httpSession.getAttribute(BRANCHID).toString()));

			VoucherEntrytransactions transactionsIncome = new VoucherEntrytransactions();

			transactionsIncome.setDraccountid(drAccountIncome);
			transactionsIncome.setCraccountid(crFeesIncome);
			transactionsIncome.setDramount(new BigDecimal(receiptInfo.getTotalamount()));
			transactionsIncome.setCramount(new BigDecimal(receiptInfo.getTotalamount()));
			transactionsIncome.setVouchertype(4);
			transactionsIncome.setTransactiondate(receiptInfo.getDate());
			transactionsIncome.setEntrydate(DateUtil.todaysDate());
			transactionsIncome.setNarration("Towards Fees Payment:  "+ackNoVoucherNarration+" "+chequeNoVoucherNarration);
			transactionsIncome.setCancelvoucher("no");
			transactionsIncome.setFinancialyear(new AccountDAO().getCurrentFinancialYear(Integer.parseInt(httpSession.getAttribute(BRANCHID).toString())).getFinancialid());
			transactionsIncome.setBranchid(Integer.parseInt(httpSession.getAttribute(BRANCHID).toString()));
			transactionsIncome.setUserid(Integer.parseInt(httpSession.getAttribute(USERID).toString()));

			String updateDrAccountIncome="update Accountdetailsbalance set currentbalance=currentbalance+"+receiptInfo.getTotalamount()+" where accountdetailsid="+drAccountIncome;

			String updateCrAccountIncome="update Accountdetailsbalance set currentbalance=currentbalance+"+receiptInfo.getTotalamount()+" where accountdetailsid="+crFeesIncome;

			// End J.V

			createFeesCollection = new feesCollectionDAO().createother(receiptInfo,feescollection,transactions,updateDrAccount,updateCrAccount, transactionsIncome, updateDrAccountIncome,updateCrAccountIncome);

			if(createFeesCollection) {
				getFeesDetails(sid,request.getParameter("academicyear"));
				Parents parent = new studentDetailsDAO().readUniqueObjectParents(Integer.parseInt(sid));
				String studentName = parent.getStudent().getName().substring(0, Math.min(parent.getStudent().getName().length(), 17));
				new SmsService(request, response).sendSMS(parent.getContactnumber(), "of "+studentName+",Rs."+String.valueOf(receiptInfo.getTotalamount()) , "fees");
				//new SmsService(request, response).sendSMS(parent.getContactnumber(), "Total "+String.valueOf(receiptInfo.getTotalamount()) , "fees");
			}

		}
		}
		return receiptInfo;
	}
	
	public void otherpreview(Otherreceiptinfo receiptInfo) {

		if(httpSession.getAttribute(CURRENTACADEMICYEAR)!=null){

			Otherreceiptinfo rinfo = new feesCollectionDAO().getOtherReceiptInfoDetails(receiptInfo.getReceiptnumber());
			Set<Otherfeescollection> setFeesCollection = rinfo.getFeesCollectionRecords();
			Map<String,Long> feeCatMap = new HashMap<String, Long>();

			for (Otherfeescollection feescollectionSingle : setFeesCollection) {
				List<Studentotherfeesstructure> studentfeesstructure = new studentDetailsDAO().getotherStudentFeesStructureDetails(feescollectionSingle.getSfsid());
				feeCatMap.put(studentfeesstructure.get(0).getOtherfeescategory().getFeescategoryname(), feescollectionSingle.getAmountpaid());
			}
			Date receiptDate = receiptInfo.getDate();
			String reDate = new SimpleDateFormat("dd/MM/yyyy").format(receiptDate);
			Student student = new studentDetailsDAO().readUniqueObject(receiptInfo.getSid());
			httpSession.setAttribute("student", student);
			request.setAttribute("recieptdate", reDate);
			request.setAttribute("recieptinfo", receiptInfo);
			request.setAttribute("feescatmap", feeCatMap);
		}

	}
	
	public DetailsResponseDto otherpreviewDetails(String receiptNumber, String duplicate, String currentAcademicYear) {
		DetailsResponseDto result = DetailsResponseDto.builder().build();

		if(currentAcademicYear!=null){
            Otherreceiptinfo rinfo = new feesCollectionDAO().getOtherReceiptInfoDetails(Integer.parseInt(receiptNumber));
			Set<Otherfeescollection> setFeesCollection = rinfo.getFeesCollectionRecords();
			Map<String,Long> feeCatMap = new HashMap<String, Long>();

			for (Otherfeescollection feescollectionSingle : setFeesCollection) {
				List<Studentotherfeesstructure> studentfeesstructure = new studentDetailsDAO().getotherStudentFeesStructureDetails(feescollectionSingle.getSfsid());
				feeCatMap.put(studentfeesstructure.get(0).getOtherfeescategory().getFeescategoryname(), feescollectionSingle.getAmountpaid());
			}
			Date receiptDate = rinfo.getDate();
			String reDate = new SimpleDateFormat("dd/MM/yyyy").format(receiptDate);
			Student student = new studentDetailsDAO().readUniqueObject(rinfo.getSid());
			Parents parents = new parentsDetailsDAO().readUniqueObject(rinfo.getSid());
			Login userLogin = new UserDAO().getUniqueObject(rinfo.getUserid());
			result.setParents(parents);
			result.setStudent(student);
			result.setReceiptDate(reDate);
			result.setOtherReceiptInfo(rinfo);
			result.setFeeCatMap(feeCatMap);
			result.setDuplicate(duplicate);
			result.setUserLogin(userLogin);
			NumberToWord toWord = new NumberToWord();
			String grandTotal = toWord.convert(rinfo.getTotalamount().intValue());
			result.setGrandTotal(grandTotal+" "+"Only");
			result.setSuccess(true);

			getOtherFeesDetails(String.valueOf(rinfo.getSid()), rinfo.getAcademicyear());
		}
		return result;
	}
	
	public ResultResponse getotherFeesReport(FeesReportDto dto, String branchId, String currentAcademicYear) {
		ResultResponse result = ResultResponse.builder().build();

		String academicYear = dto.getAcademicYear();
		String[] feesCat = dto.getFeesCat();

		//Get Students

		List<Parents> searchStudentList = new ArrayList<Parents>();

		if(branchId!=null){

		String queryMain = "From Parents as parents where";
		String[] addClass = dto.getAddClass();
		StringBuffer conClassStudying = new StringBuffer();

			int i = 0;
			for (String classOne : addClass) {

				if(i>0) {
					conClassStudying.append("' OR parents.Student.classstudying LIKE '"+classOne+"--"+"%");
				}else {
					conClassStudying.append(classOne+"--"+"%");
				}

				i++;
			}

		String classStudying = DataUtil.emptyString(conClassStudying.toString());
		String querySub = "";

		if (!classStudying.equalsIgnoreCase("")) {
			querySub = querySub + " (parents.Student.classstudying like '"
					+ classStudying + "') AND parents.Student.archive=0 and parents.Student.passedout=0 AND parents.Student.droppedout=0 and parents.Student.leftout=0 AND parents.branchid="+Integer.parseInt(branchId)+" order by parents.Student.admissionnumber ASC";
		}

		if(!"".equalsIgnoreCase(querySub)) {
			queryMain = queryMain + querySub;
			searchStudentList = new studentDetailsDAO().getStudentsList(queryMain);
		}

	}
		//End Students


		if(currentAcademicYear!=null){

			List<Studentotherfeesreport> studentOtherFeesReportList = new ArrayList<Studentotherfeesreport>();

			for (Parents parents : searchStudentList) {

				Studentotherfeesreport studentFeesReport = new Studentotherfeesreport();

				long id = parents.getStudent().getSid();

				List<Integer> feesCatList = new ArrayList<>(); 
				for (String feescat : feesCat) {
					feesCatList.add(Integer.parseInt(feescat));
				}
				List<Studentotherfeesstructure> feesstructure = new studentDetailsDAO().getStudentotherFeesStructurebyFeesCategory(id,feesCatList);

				if (feesstructure.size() > 0) {

					studentFeesReport.setParents(parents);
					studentFeesReport.setStudentFeesStructure(feesstructure);

					studentOtherFeesReportList.add(studentFeesReport);

				}
			}

			result.setResultList(studentOtherFeesReportList);
			result.setSuccess(true);
		}
		return result;
	  }

	public CancelledReceiptsResponseDto searchOtherFeesCollection(CancelledReceiptsDto dto, String strBranchId, String dayOne, String dateFrom, String dateTo) {
		CancelledReceiptsResponseDto result = CancelledReceiptsResponseDto.builder().build();

		List<Otherreceiptinfo> feesDetailsList = new ArrayList<Otherreceiptinfo>();
		String branchId = dto.getBranchId();
		int idBranch = 0;
               
		if(strBranchId!=null){
		

	        if(branchId!=null) {
	        	String[] branchIdName = branchId.split(":");
	        	idBranch = Integer.parseInt(branchIdName[0]);
				result.setFeesDetailsBranchName(branchIdName[1]);
				result.setBranchName("Branch Name:");
	        }else {
	        	idBranch = Integer.parseInt(strBranchId);
	        }
	        
		String queryMain ="From Otherreceiptinfo as feesdetails where feesdetails.cancelreceipt=0 and feesdetails.branchid="+idBranch+" AND";
		String toDate= DataUtil.emptyString(dto.getToDate());
		String fromDate = DataUtil.emptyString(dto.getFromDate());
		String oneDay = DataUtil.emptyString(dto.getOneDay());
		
		
			String querySub = "";
			
			if(!oneDay.equalsIgnoreCase("")){
				querySub = " feesdetails.date = '"+oneDay+"'" ;
				 result.setDayOneCancel(oneDay);
				 result.setDateFromCancel("");
				 result.setDateToCancel("");
			}else if(!"".equalsIgnoreCase(DataUtil.emptyString(dayOne))) {
				querySub = " feesdetails.date = '"+dayOne+"'" ;
			}
			
			if(!fromDate.equalsIgnoreCase("")  && !toDate.equalsIgnoreCase("")){
				querySub = " feesdetails.date between '"+fromDate+"' AND '"+toDate+"'";
				result.setDateFromCancel(fromDate);
				result.setDateToCancel(toDate);
				result.setDayOneCancel("");
			}else if(!"".equalsIgnoreCase(DataUtil.emptyString(dateFrom)) &&
					!"".equalsIgnoreCase(DataUtil.emptyString(dateTo)) ) {
				querySub = " feesdetails.date between '"+dateFrom+"' AND '"+dateTo+"'";
			}
			
			queryMain = queryMain+querySub;
			/*queryMain = "FROM Parents as parents where  parents.Student.dateofbirth = '2006-04-06'"; */
			System.out.println("SEARCH QUERY ***** "+queryMain);
			feesDetailsList = new UserDAO().getOtherReceiptDetailsList(queryMain);
			
	}
			long sumOfFees = 0l;
			for (Otherreceiptinfo receiptinfo : feesDetailsList) {
				sumOfFees = sumOfFees + receiptinfo.getTotalamount();
			}

			result.setOtherfeesDetailsList(feesDetailsList);
			result.setSumOfFees(sumOfFees);
			result.setSuccess(true);

			return result;
	}
	
	public DetailsResponseDto previewOtherFeesDetails(String sId, String strReceiptNo, String currentAcademicYear) {
		DetailsResponseDto result = DetailsResponseDto.builder().build();
		
		if(currentAcademicYear!=null){
			long sid=DataUtil.parseLong(sId);
			int receiptNo = DataUtil.parseInt(strReceiptNo);
			 
			Otherreceiptinfo rinfo = new feesCollectionDAO().getOtherReceiptInfoDetails(receiptNo);
			Set<Otherfeescollection> setFeesCollection = rinfo.getFeesCollectionRecords();
			Map<String,Long> feeCatMap = new HashMap<String, Long>();

			for (Otherfeescollection feescollectionSingle : setFeesCollection) {
				List<Studentotherfeesstructure> studentfeesstructure = new studentDetailsDAO().getStudentOtherFeesStructureDetails(feescollectionSingle.getSfsid());
				feeCatMap.put(studentfeesstructure.get(0).getOtherfeescategory().getFeescategoryname(), feescollectionSingle.getAmountpaid());
			}
			Date receiptDate = rinfo.getDate();
			String reDate = new SimpleDateFormat("yyyy-MM-dd").format(receiptDate);
			Student student = new studentDetailsDAO().readUniqueObject(sid);
			result.setStudent(student);
			result.setReceiptDate(reDate);
			result.setOtherReceiptInfo(rinfo);
			result.setFeeCatMap(feeCatMap);
			result.setDuplicate("duplicate");
			result.setSuccess(true);

			getOtherFeesDetails(String.valueOf(rinfo.getSid()), rinfo.getAcademicyear());
			
		}
		return result;
	}
	
	private void getOtherFeesDetails(String sid, String academicYear) {
		
		try {
			long id = Long.parseLong(sid);
			
			List<Otherreceiptinfo> rinfo = new feesCollectionDAO().getOtherReceiptDetailsPerStudent(id,academicYear);
			request.setAttribute("receiptinfo",rinfo);
			List<Studentotherfeesstructure> feesstructure = new studentDetailsDAO().getStudentOtherFeesStructure(id, academicYear);
			
			long totalSum = 0l;
			for (Otherreceiptinfo receiptInfoSingle : rinfo) {
				totalSum = totalSum + receiptInfoSingle.getTotalamount();
			}
			
			long totalFeesAmount = 0l;
			for (Studentotherfeesstructure studentfeesstructureSingle : feesstructure) {
				totalFeesAmount = totalFeesAmount+studentfeesstructureSingle.getFeesamount()-studentfeesstructureSingle.getWaiveoff()-studentfeesstructureSingle.getConcession();
			}
			
				httpSession.setAttribute("feesstructure", feesstructure);
				httpSession.setAttribute("sumoffees", totalSum);
				httpSession.setAttribute("dueamount", totalFeesAmount-totalSum);
				httpSession.setAttribute("totalfees", totalFeesAmount);
				httpSession.setAttribute("academicPerYear", academicYear);
				httpSession.setAttribute("currentAcademicYear", academicYear);
				
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public ResultResponse cancelOtherFeesReceipt(String receiptId, String journalId, String strFeeReceiptId, String currentAcademicYear) {
		ResultResponse resultResponse = ResultResponse.builder().build();
		
		if(currentAcademicYear!=null){

            int feesReceiptId = DataUtil.parseInt(strFeeReceiptId);
			boolean result=false;
			List<Otherfeescollection> feesCollection = new feesCollectionDAO().getOtherFeesCollectionDetails(feesReceiptId);
			Date now = new Date();
	        String pattern = "yyyy-MM-dd";
	        SimpleDateFormat formatter = new SimpleDateFormat(pattern);
			String todaysDate = formatter.format(now);
			
			result = new feesDetailsDAO().cancelOtherFeesReceipt(feesReceiptId, feesCollection, null, null, null,
					null, null, null);
			
			/*if(rid !=null && jid !=null ) {
				
				int receiptId = DataUtil.parseInt(rid);
				int journalId = DataUtil.parseInt(jid);
			
			// Cancel Voucher

				VoucherEntrytransactions receiptVoucherTransaction = new AccountDAO().getVoucherDetails(String.valueOf(receiptId));
				
			
				String updateReceiptDrAccount="update Accountdetailsbalance set currentbalance=currentbalance-"+receiptVoucherTransaction.getDramount()+" where accountdetailsid="+receiptVoucherTransaction.getDraccountid();
				String updateReceiptCrAccount="update Accountdetailsbalance set currentbalance=currentbalance-"+receiptVoucherTransaction.getCramount()+" where accountdetailsid="+receiptVoucherTransaction.getCraccountid();

				String cancelReceiptVoucher = "update VoucherEntrytransactions set cancelvoucher='yes', vouchercancellationdate='"+todaysDate+"' where transactionsid="+receiptId;
				
			
				VoucherEntrytransactions journalVoucherTransaction = new AccountDAO().getVoucherDetails(String.valueOf(journalId));
				
				// Dr
				Accountdetails accountDetailsDr = new AccountDAO().getAccountDetails(journalVoucherTransaction.getDraccountid());
				String updateJournalDrAccount= null;
				if(accountDetailsDr.getAccountGroupMaster().getAccountgroupid()==1 || accountDetailsDr.getAccountGroupMaster().getAccountgroupid()==5) {
					updateJournalDrAccount="update Accountdetailsbalance set currentbalance=currentbalance-"+journalVoucherTransaction.getDramount()+" where accountdetailsid="+journalVoucherTransaction.getDraccountid();
				}else {
					updateJournalDrAccount="update Accountdetailsbalance set currentbalance=currentbalance+"+journalVoucherTransaction.getDramount()+" where accountdetailsid="+journalVoucherTransaction.getDraccountid();
				}
				
				//Cr
				
				Accountdetails accountDetailsCr = new AccountDAO().getAccountDetails(journalVoucherTransaction.getCraccountid());
				String updateJournalCrAccount= null;
				
				if(accountDetailsCr.getAccountGroupMaster().getAccountgroupid()==2 || accountDetailsCr.getAccountGroupMaster().getAccountgroupid()==3 || accountDetailsCr.getAccountGroupMaster().getAccountgroupid()==4) {
					updateJournalCrAccount="update Accountdetailsbalance set currentbalance=currentbalance-"+journalVoucherTransaction.getCramount()+" where accountdetailsid="+journalVoucherTransaction.getCraccountid();
				}else {
					updateJournalCrAccount="update Accountdetailsbalance set currentbalance=currentbalance+"+journalVoucherTransaction.getCramount()+" where accountdetailsid="+journalVoucherTransaction.getCraccountid();
				}
				
				String cancelJournalVoucher = "update VoucherEntrytransactions set cancelvoucher='yes', vouchercancellationdate='"+todaysDate+"' where transactionsid="+journalId;
				
			
			// End Cancel Voucher
			result = new feesDetailsDAO().cancelOtherFeesReceipt(feesReceiptId, feesCollection, updateReceiptDrAccount, updateReceiptCrAccount, cancelReceiptVoucher,
					updateJournalDrAccount, updateJournalCrAccount, cancelJournalVoucher);
		}else {
			result = new feesDetailsDAO().cancelOtherFeesReceipt(feesReceiptId, feesCollection, null, null, null,
					null, null, null);
		}*/

			resultResponse.setSuccess(result);
			resultResponse.setSuccess(true);
		}
		return resultResponse;
	}

	public boolean exportDataForStudentsOtherFeesReport() {

		boolean writeSucees = false;
		
		try {

			List<Studentotherfeesreport> studentOtherFeesReportList = (List<Studentotherfeesreport>) httpSession.getAttribute("studentotherfeesreportlist");
			
			// Creating an excel file
			XSSFWorkbook workbook = new XSSFWorkbook();
			XSSFSheet sheet = workbook.createSheet("ListOfStudents");
			Map<String, Object[]> data = new HashMap<String, Object[]>();
			Map<String, Object[]> headerData = new HashMap<String, Object[]>();
			headerData.put("Header",
					new Object[] { "UID", "Admission Number","Student Name", "Class & Sec","Father Name", "Contact Number", "Fees Details", "Total Due Summary", "Total Fees Summary"});
			int i = 1;
			for (Studentotherfeesreport studentFeesReport : studentOtherFeesReportList) {
				
				List<Studentotherfeesstructure> sfs = studentFeesReport.getStudentFeesStructure();
				String feesDetails = "";
				Long dueAmount = 0l;
				Long totalAmount = 0l;
				
				for (Studentotherfeesstructure studentFee : sfs) {
					Long feesDue = studentFee.getFeesamount()-studentFee.getFeespaid() - studentFee.getConcession() - studentFee.getWaiveoff();
					Long feesTotal = studentFee.getFeesamount() - studentFee.getConcession() - studentFee.getWaiveoff();
					dueAmount = dueAmount+studentFee.getFeesamount()-studentFee.getFeespaid()-studentFee.getConcession()-studentFee.getWaiveoff();
					totalAmount = totalAmount+studentFee.getFeesamount()-studentFee.getConcession()-studentFee.getWaiveoff();
					feesDetails=feesDetails+studentFee.getOtherfeescategory().getFeescategoryname()+":"+feesDue+"/"+feesTotal+"\n";
				}
				
				data.put(Integer.toString(i),
						new Object[] { DataUtil.emptyString(studentFeesReport.getParents().getStudent().getStudentexternalid()),  DataUtil.emptyString(studentFeesReport.getParents().getStudent().getAdmissionnumber()),
								 DataUtil.emptyString(studentFeesReport.getParents().getStudent().getName()),
								 DataUtil.emptyString(studentFeesReport.getParents().getStudent().getClassstudying().replace("--", " ")),
								 DataUtil.emptyString(studentFeesReport.getParents().getFathersname()),
								 DataUtil.emptyString(studentFeesReport.getParents().getContactnumber()),
								 DataUtil.emptyString(feesDetails),
								 String.valueOf(dueAmount),
								 String.valueOf(totalAmount) });
				i++;
			}
			
			Row headerRow = sheet.createRow(0);
			Object[] objArrHeader = headerData.get("Header");
			int cellnum1 = 0;
			for (Object obj : objArrHeader) {
				Cell cell = headerRow.createCell(cellnum1++);
				if (obj instanceof String)
					cell.setCellValue((String) obj);
			}
			Set<String> keyset = data.keySet();
			int rownum = 1;
			for (String key : keyset) {
				Row row = sheet.createRow(rownum++);
				Object[] objArr = data.get(key);
				int cellnum = 0;
				for (Object obj : objArr) {
					Cell cell = row.createCell(cellnum++);
					if (obj instanceof Date)
						cell.setCellValue((Date) obj);
					else if (obj instanceof Boolean)
						cell.setCellValue((Boolean) obj);
					else if (obj instanceof String)
						cell.setCellValue((String) obj);
					else if (obj instanceof Double)
						cell.setCellValue((Double) obj);
				}
			}
				
				FileOutputStream out = new FileOutputStream(new File(System.getProperty("java.io.tmpdir")+"/studentsfeesreport.xlsx"));
				workbook.write(out);
				out.close();
				writeSucees = true;
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return writeSucees;
		// getFile(name, path);
	}

	public void getFeesCollectionCategory() {
		 
		List<Receiptinfo> feesDetailsList = new ArrayList<Receiptinfo>();
		List<Otherreceiptinfo> otherFeesDetailsList = new ArrayList<Otherreceiptinfo>();
		String branchId = request.getParameter("selectedbranchid");
		int idBranch = 0;
               
		Map<String, Long> feeCategoryCollectionMapReport = new LinkedHashMap<String, Long>();

		if(httpSession.getAttribute(BRANCHID)!=null){
		

	        if(branchId!=null) {
	        	String[] branchIdName = branchId.split(":");
	        	idBranch = Integer.parseInt(branchIdName[0]);
	        	httpSession.setAttribute("feesdetailsbranchname", branchIdName[1]);
	        	httpSession.setAttribute("branchname", "Branch Name:");
	        }else {
	        	idBranch = Integer.parseInt(httpSession.getAttribute(BRANCHID).toString());
	        }
	        
		String queryMain ="From Receiptinfo as feesdetails where feesdetails.cancelreceipt=0 and feesdetails.branchid="+idBranch+" AND";
		String toDate= DataUtil.emptyString(request.getParameter("todate"));
		String fromDate = DataUtil.emptyString(request.getParameter("fromdate"));
		String oneDay = DataUtil.emptyString(request.getParameter("oneday"));
		
		
			String querySub = "";
			
			if(!oneDay.equalsIgnoreCase("")){
				querySub = " feesdetails.date = '"+oneDay+"'" ;
				 httpSession.setAttribute("dayone", oneDay);
				 httpSession.setAttribute("datefrom", "");
				 httpSession.setAttribute("dateto", "");
			}else if(!"".equalsIgnoreCase(DataUtil.emptyString((String) httpSession.getAttribute("dayone")))) {
				querySub = " feesdetails.date = '"+(String) httpSession.getAttribute("dayone")+"'" ;
			}
			
			if(!fromDate.equalsIgnoreCase("")  && !toDate.equalsIgnoreCase("")){
				querySub = " feesdetails.date between '"+fromDate+"' AND '"+toDate+"'";
				httpSession.setAttribute("datefrom", fromDate);
				httpSession.setAttribute("dateto", toDate);
				httpSession.setAttribute("dayone", "");
			}else if(!"".equalsIgnoreCase(DataUtil.emptyString((String) httpSession.getAttribute("datefrom"))) && 
					!"".equalsIgnoreCase(DataUtil.emptyString((String) httpSession.getAttribute("dateto"))) ) {
				querySub = " feesdetails.date between '"+(String) httpSession.getAttribute("datefrom")+"' AND '"+(String) httpSession.getAttribute("dateto")+"'";
			}
			
			queryMain = queryMain+querySub;
			/*queryMain = "FROM Parents as parents where  parents.Student.dateofbirth = '2006-04-06'"; */
			System.out.println("SEARCH QUERY ***** "+queryMain);
			feesDetailsList = new UserDAO().getReceiptDetailsList(queryMain);
			
//Other Fees Details

			String queryMainOtherFees ="From Otherreceiptinfo as feesdetails where feesdetails.cancelreceipt=0 and feesdetails.branchid="+idBranch+" AND";
			String toDateOtherFees = DataUtil.emptyString(request.getParameter("todate"));
			String fromDateOtherFees = DataUtil.emptyString(request.getParameter("fromdate"));
			String oneDayOtherFees = DataUtil.emptyString(request.getParameter("oneday"));


				String querySubOtherFees = "";

				if(!oneDayOtherFees.equalsIgnoreCase("")){
					querySub = " feesdetails.date = '"+oneDayOtherFees+"'" ;
					 httpSession.setAttribute("dayone", oneDayOtherFees);
					 httpSession.setAttribute("datefrom", "");
					 httpSession.setAttribute("dateto", "");
				}else if(!"".equalsIgnoreCase(DataUtil.emptyString((String) httpSession.getAttribute("dayone")))) {
					querySubOtherFees = " feesdetails.date = '"+(String) httpSession.getAttribute("dayone")+"'" ;
				}

				if(!fromDateOtherFees.equalsIgnoreCase("")  && !toDateOtherFees.equalsIgnoreCase("")){
					querySubOtherFees = " feesdetails.date between '"+fromDateOtherFees+"' AND '"+toDateOtherFees+"'";
					httpSession.setAttribute("datefrom", fromDateOtherFees);
					httpSession.setAttribute("dateto", toDateOtherFees);
					httpSession.setAttribute("dayone", "");
				}else if(!"".equalsIgnoreCase(DataUtil.emptyString((String) httpSession.getAttribute("datefrom"))) &&
						!"".equalsIgnoreCase(DataUtil.emptyString((String) httpSession.getAttribute("dateto"))) ) {
					querySubOtherFees = " feesdetails.date between '"+(String) httpSession.getAttribute("datefrom")+"' AND '"+(String) httpSession.getAttribute("dateto")+"'";
				}

				queryMainOtherFees = queryMainOtherFees+querySubOtherFees;
				/*queryMain = "FROM Parents as parents where  parents.Student.dateofbirth = '2006-04-06'"; */
				System.out.println("SEARCH QUERY ***** "+queryMainOtherFees);
				otherFeesDetailsList = new UserDAO().getOtherReceiptDetailsList(queryMainOtherFees);

			//End Other Fees Details

	}
			long sumOfFees = 0l;
			long fine = 0l;
			long misc = 0l;
			long feesByCash = 0;
			long feesByBank = 0;
			long feesByCashOtherFees = 0;
			long feesByBankOtherFees = 0;

			for (Receiptinfo receiptinfo : feesDetailsList) {
				sumOfFees = sumOfFees + receiptinfo.getTotalamount();
				fine = fine + receiptinfo.getFine();
				misc = misc + receiptinfo.getMisc();
			}
			
			Map<String, Long> feeCategoryCollectionMap = new LinkedHashMap<String, Long>();
			
			for (Receiptinfo receiptinfo : feesDetailsList) {
				
				Set<Feescollection> setFeesCollection = receiptinfo.getFeesCollectionRecords();
				Map<String,Long> feeCatMap = new HashMap<String, Long>();

				for (Feescollection feescollectionSingle : setFeesCollection) {
					List<Studentfeesstructure> studentfeesstructure = new studentDetailsDAO().getStudentFeesStructureDetails(feescollectionSingle.getSfsid());
					//feeCategoryFeeCollectionMap.get(studentfeesstructure.get(0).getFeescategory().getFeescategoryname());
					//feeCatMap.put(studentfeesstructure.get(0).getFeescategory().getFeescategoryname(), feescollectionSingle.getAmountpaid());
					
					for (Studentfeesstructure studentfeesSingle : studentfeesstructure) {
			            if (feeCategoryCollectionMap.containsKey(studentfeesSingle.getFeescategory().getFeescategoryname())) {
			            	feeCategoryCollectionMap.put(studentfeesSingle.getFeescategory().getFeescategoryname(), feeCategoryCollectionMap.get(studentfeesSingle.getFeescategory().getFeescategoryname()) + feescollectionSingle.getAmountpaid());
			            } else {
			            	feeCategoryCollectionMap.put(studentfeesSingle.getFeescategory().getFeescategoryname(), feescollectionSingle.getAmountpaid());
			            }
			        }
				}

				String cashOrBank = receiptinfo.getPaymenttype();
				if(cashOrBank.contains("Cash")) {
					feesByCash = feesByCash+receiptinfo.getTotalamount();
				}else if(cashOrBank.contains("Bank")) {
					feesByBank = feesByBank+receiptinfo.getTotalamount();
				}
			}
			
		for (Receiptinfo receiptinfo : feesDetailsList) {
				
			
			if(receiptinfo.getFine()>0) {
				
				if (feeCategoryCollectionMap.containsKey("Fine")) {
	            	feeCategoryCollectionMap.put("Fine", feeCategoryCollectionMap.get("Fine") + receiptinfo.getFine());
	            } else {
	            	feeCategoryCollectionMap.put("Fine", receiptinfo.getFine());
	            }
			}
			if(receiptinfo.getMisc()>0) {
				if (feeCategoryCollectionMap.containsKey("Miscellaneous")) {
	            	feeCategoryCollectionMap.put("Miscellaneous", feeCategoryCollectionMap.get("Miscellaneous") + receiptinfo.getMisc());
	            } else {
	            	feeCategoryCollectionMap.put("Miscellaneous", receiptinfo.getMisc());
	            }
			}
		}

		for (Map.Entry<String, Long> entry : feeCategoryCollectionMap.entrySet()) {

			feeCategoryCollectionMapReport.put(entry.getKey(), entry.getValue());
			/*
			 if(entry.getKey().contains("Fee")) { tutionFees =
			  tutionFees+entry.getValue(); }else {
			  feeCategoryCollectionMapReport.put(entry.getKey(), entry.getValue()); }
			 */
		}

		//feeCategoryCollectionMapReport.put("Tuition Fee", tutionFees);

		//Other Fees

		long sumOfFeesOtherFees = 0l;
		for (Otherreceiptinfo receiptinfo : otherFeesDetailsList) {
			sumOfFeesOtherFees = sumOfFeesOtherFees + receiptinfo.getTotalamount();
		}

		Map<String, Long> otherFeeCategoryCollectionMap = new LinkedHashMap<String, Long>();

		for (Otherreceiptinfo receiptinfo : otherFeesDetailsList) {

			Set<Otherfeescollection> setFeesCollectionOtherFees = receiptinfo.getFeesCollectionRecords();

			for (Otherfeescollection feescollectionSingle : setFeesCollectionOtherFees) {
				List<Studentotherfeesstructure> studentfeesstructure = new studentDetailsDAO().getStudentOtherFeesStructureDetails(feescollectionSingle.getSfsid());
				//feeCategoryFeeCollectionMap.get(studentfeesstructure.get(0).getFeescategory().getFeescategoryname());
				//feeCatMap.put(studentfeesstructure.get(0).getFeescategory().getFeescategoryname(), feescollectionSingle.getAmountpaid());

				for (Studentotherfeesstructure studentfeesSingle : studentfeesstructure) {
		            if (otherFeeCategoryCollectionMap.containsKey(studentfeesSingle.getOtherfeescategory().getFeescategoryname())) {
		            	otherFeeCategoryCollectionMap.put(studentfeesSingle.getOtherfeescategory().getFeescategoryname(), otherFeeCategoryCollectionMap.get(studentfeesSingle.getOtherfeescategory().getFeescategoryname()) + feescollectionSingle.getAmountpaid());
		            } else {
		            	otherFeeCategoryCollectionMap.put(studentfeesSingle.getOtherfeescategory().getFeescategoryname(), feescollectionSingle.getAmountpaid());
		            }
		        }
			}

			String cashOrBank = receiptinfo.getPaymenttype();
			if(cashOrBank.contains("Cash")) {
				feesByCashOtherFees = feesByCash+receiptinfo.getTotalamount();
			}else if(cashOrBank.contains("Bank")) {
				feesByBankOtherFees = feesByBank+receiptinfo.getTotalamount();
			}
		}

		for (Map.Entry<String, Long> entry : otherFeeCategoryCollectionMap.entrySet()) {
			feeCategoryCollectionMapReport.put(entry.getKey(), entry.getValue());
			}
		//feeCategoryCollectionMapReport.put("Other Fee", otherFees);

		//End Other Fees


		httpSession.setAttribute("feeCategoryCollectionMap", feeCategoryCollectionMapReport);
		httpSession.setAttribute("feesbycash", feesByCash);
		httpSession.setAttribute("feesbybank", feesByBank);
		httpSession.setAttribute("feesbycashotherfees", feesByCashOtherFees);
		httpSession.setAttribute("feesbybankotherfees", feesByBankOtherFees);
	}

	public void printFeesDueHeadWiseReport() {

		boolean writeSucees = false;
		
			List<StudentFeesReport> studentFeesReportList = (List<StudentFeesReport>) httpSession.getAttribute("studentfeesreportlist");
			
			// Creating an excel file
			int i = 1;
			for (StudentFeesReport studentFeesReport : studentFeesReportList) {
				
				List<Studentfeesstructure> sfs = studentFeesReport.getStudentFeesStructure();
				String feesDetails = "";
				Long dueAmount = 0l;
				Long totalAmount = 0l;
				
				for (Studentfeesstructure studentFee : sfs) {
					Long feesDue = studentFee.getFeesamount()-studentFee.getFeespaid() - studentFee.getConcession() - studentFee.getWaiveoff();
					Long feesTotal = studentFee.getFeesamount() - studentFee.getConcession() - studentFee.getWaiveoff();
					dueAmount = dueAmount+studentFee.getFeesamount()-studentFee.getFeespaid()-studentFee.getConcession()-studentFee.getWaiveoff();
					totalAmount = totalAmount+studentFee.getFeesamount()-studentFee.getConcession()-studentFee.getWaiveoff();
					feesDetails=feesDetails+studentFee.getFeescategory().getFeescategoryname()+":"+feesDue+"/"+feesTotal+"\n";
				}
			}
	}

	public CancelledReceiptsResponseDto printOtherDataForFees(CancelledReceiptsDto dto) {
		CancelledReceiptsResponseDto result = CancelledReceiptsResponseDto.builder().build();
		
		String[] feesIds = dto.getFeesIds();
		Otherreceiptinfo receiptInfo = new Otherreceiptinfo();
		Parents student = new Parents();
		Map<Parents,Otherreceiptinfo> feesMap = new HashMap<Parents,Otherreceiptinfo>();
		String toDate= DataUtil.dateFromatConversionDashToSlash(dto.getToDate());
		String fromDate = DataUtil.dateFromatConversionDashToSlash(dto.getFromDate());
		String oneDay = DataUtil.dateFromatConversionDashToSlash(dto.getOneDay());
		
		long sumOfFees = 0l;
		
		if (feesIds != null) {
			for (String id : feesIds) {
				if (id != null || id != "") {
					
					receiptInfo = new feesDetailsDAO().readOtherFeesDetails(Long.parseLong(id));
					student = new studentDetailsDAO().readUniqueObjectParents(receiptInfo.getSid());
					feesMap.put(student, receiptInfo);
					sumOfFees = sumOfFees + receiptInfo.getTotalamount();
				}

			}
		}
		result.setFeesMap(feesMap);
		result.setSumOfFees(sumOfFees);
		result.setSuccess(true);
		if(oneDay.equalsIgnoreCase("")) {
			result.setDateToCancel("From Date: "+fromDate+"              To Date: "+toDate+"");
		}else {
			result.setDateToCancel("Date: "+oneDay+"");
		}
		return result;
	}
	
	public ResultResponse getDefaultersReport(FeesReportDto dto, String branchId, String currentAcademicYear) {
		ResultResponse result = ResultResponse.builder().build();

		String academicYear = dto.getAcademicYear();
		String[] feesCat = dto.getFeesCat();

		//Get Students

		List<Parents> searchStudentList = new ArrayList<Parents>();

		if(branchId!=null){

		String queryMain = "From Parents as parents where";
		String[] addClass = dto.getAddClass();
		StringBuffer conClassStudying = new StringBuffer();

			int i = 0;
			for (String classOne : addClass) {

				if(i>0) {
					conClassStudying.append("' OR parents.Student.classstudying LIKE '"+classOne+"--"+"%");
				}else {
					conClassStudying.append(classOne+"--"+"%");
				}

				i++;
			}

		String classStudying = DataUtil.emptyString(conClassStudying.toString());
		String querySub = "";

		if (!classStudying.equalsIgnoreCase("")) {
			querySub = querySub + " (parents.Student.classstudying like '"
					+ classStudying + "') AND parents.Student.archive=0 and parents.Student.passedout=0 AND parents.Student.droppedout=0 and parents.Student.leftout=0 AND parents.Student.branchid="+Integer.parseInt(branchId)+" order by parents.Student.admissionnumber ASC";
		}

		if(!"".equalsIgnoreCase(querySub)) {
			queryMain = queryMain + querySub;
			searchStudentList = new studentDetailsDAO().getStudentsList(queryMain);
		}

	}
		//End Students


		if(currentAcademicYear!=null){

			List<StudentFeesReport> studentFeesReportList = new ArrayList<StudentFeesReport>();

			for (Parents parents : searchStudentList) {

				Date dateNow = new Date();
				SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		        String formattedDate = dateFormat.format(dateNow);
		        Date cDate = new Date();
		        try {
					cDate = dateFormat.parse(formattedDate);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				Date DNDDate = parents.getStudent().getCrecorddate();

				if (DNDDate == null || cDate.compareTo(DNDDate) > 0) {

				StudentFeesReport studentFeesReport = new StudentFeesReport();

				long id = parents.getStudent().getSid();

				List<Integer> feesCatList = new ArrayList<>(); 
				for (String feescat : feesCat) {
					feesCatList.add(Integer.parseInt(feescat));
				}
				List<Studentfeesstructure> feesstructure = new studentDetailsDAO().getStudentFeesStructurebyFeesCategory(id,feesCatList);
				List<Studentfeesstructure> defaulterFeesstructure = new ArrayList<Studentfeesstructure>();
				Long totalDue = 0l;

				for (Studentfeesstructure studentFeesStructure : feesstructure) {

					String[] feesStartMonth = new DataUtil().getPropertiesValue("feesstartmonth").split("/");
					 LocalDate currentDate = LocalDate.now();
				     LocalDate startDate = LocalDate.of(Integer.parseInt(feesStartMonth[2]), Integer.parseInt(feesStartMonth[1]), Integer.parseInt(feesStartMonth[0]));
				     LocalDate endDate = LocalDate.of(currentDate.getYear(), currentDate.getMonthValue(), currentDate.getDayOfMonth()); // Change this to your desired end date

				        // Calculate the period between the start date and end date
				        Period period = Period.between(startDate, endDate);

				        // Calculate the total number of months between the dates
				        int totalMonths = period.getYears() * 12 + period.getMonths();

				        // Add one month if the end date has not passed the start date's day
				        if (endDate.getDayOfMonth() < startDate.getDayOfMonth()) {
				            totalMonths--;
				        }

				        int totalFeesInstallments = studentFeesStructure.getTotalinstallment();
				        int value = 12/totalFeesInstallments;
				        int quotient = totalMonths/value;
				        Long feesPerInstallment = studentFeesStructure.getFeesamount()/totalFeesInstallments;
				        int installmentTillDate = quotient+1;
						Long ActualFeesToBePaid = feesPerInstallment*installmentTillDate;
						Long feesPaid = studentFeesStructure.getFeespaid();
						Long committedFees = studentFeesStructure.getFeesamount()/totalFeesInstallments;

						if(feesPaid < ActualFeesToBePaid) {
							totalDue = totalDue + (ActualFeesToBePaid-feesPaid);
							//studentFeesStructure.setFeespaid(ActualFeesToBePaid-feesPaid); //Using Fees paid as fees due amount as it is not being used elsewhere
							defaulterFeesstructure.add(studentFeesStructure);
						}
				}
				if(defaulterFeesstructure.size()>0) {
					studentFeesReport.setParents(parents);
					studentFeesReport.setStudentFeesStructure(defaulterFeesstructure);
					studentFeesReport.setDueAmount(totalDue);
					studentFeesReportList.add(studentFeesReport);
				}
			}

			}
			result.setResultList(studentFeesReportList);
			result.setSuccess(true);
		}
		return result;
	  }

	public void viewCancelledOtherFeesReceipts() {
		 
		List<Otherreceiptinfo> feesDetailsList = new ArrayList<Otherreceiptinfo>();
		String branchId = request.getParameter("selectedbranchid");
		int idBranch = 0;
              
		if(httpSession.getAttribute(BRANCHID)!=null){
		

	        if(branchId!=null) {
	        	String[] branchIdName = branchId.split(":");
	        	idBranch = Integer.parseInt(branchIdName[0]);
	        	httpSession.setAttribute("feesdetailsbranchname", branchIdName[1]);
	        	httpSession.setAttribute("branchname", "Branch Name:");
	        }else {
	        	idBranch = Integer.parseInt(httpSession.getAttribute(BRANCHID).toString());
	        }
	        
		String queryMain ="From Otherreceiptinfo as feesdetails where cancelreceipt=1 and feesdetails.branchid="+idBranch+" AND";
		String toDate= DataUtil.emptyString(request.getParameter("todate"));
		String fromDate = DataUtil.emptyString(request.getParameter("fromdate"));
		String oneDay = DataUtil.emptyString(request.getParameter("oneday"));
		
		
			String querySub = "";
			
			if(!oneDay.equalsIgnoreCase("")){
				querySub = " feesdetails.date = '"+oneDay+"'" ;
				 httpSession.setAttribute("dayonecancel", oneDay);
				 httpSession.setAttribute("datefromcancel", "");
				 httpSession.setAttribute("datetocancel", "");
			}else if(!"".equalsIgnoreCase(DataUtil.emptyString((String) httpSession.getAttribute("dayone")))) {
				querySub = " feesdetails.date = '"+(String) httpSession.getAttribute("dayonecancel")+"'" ;
			}
			
			if(!fromDate.equalsIgnoreCase("")  && !toDate.equalsIgnoreCase("")){
				querySub = " feesdetails.date between '"+fromDate+"' AND '"+toDate+"'";
				httpSession.setAttribute("datefromcancel", fromDate);
				httpSession.setAttribute("datetocancel", toDate);
				 httpSession.setAttribute("dayonecancel", "");
				
			}else if(!"".equalsIgnoreCase(DataUtil.emptyString((String) httpSession.getAttribute("datefromcancel"))) && 
					!"".equalsIgnoreCase(DataUtil.emptyString((String) httpSession.getAttribute("datetocancel"))) ) {
				querySub = " feesdetails.date between '"+(String) httpSession.getAttribute("datefromcancel")+"' AND '"+(String) httpSession.getAttribute("datetocancel")+"'";
			}
			
			queryMain = queryMain+querySub;
			/*queryMain = "FROM Parents as parents where  parents.Student.dateofbirth = '2006-04-06'"; */
			feesDetailsList = new UserDAO().getOtherReceiptDetailsList(queryMain);
			
	}
			long sumOfFees = 0l;
			for (Otherreceiptinfo receiptinfo : feesDetailsList) {
				sumOfFees = sumOfFees + receiptinfo.getTotalamount();
			}
			
			httpSession.setAttribute("searchfeesdetailslistcancelled", feesDetailsList);
			httpSession.setAttribute("sumofdetailsfeescancelled", sumOfFees);
	}

	public ResultResponse getFeesReportDue(FeesReportDto dto, String branchId, String currentAcademicYear) {
		ResultResponse result = ResultResponse.builder().build();
		
		String academicYear = dto.getAcademicYear();
		String[] feesCat = dto.getFeesCat();
		
		//Get Students
		
		List<Parents> searchStudentList = new ArrayList<Parents>();
		
		if(branchId!=null){
		
		String queryMain = "From Parents as parents where";
		String[] addClass = dto.getAddClass();
		StringBuffer conClassStudying = new StringBuffer();

			int i = 0;
			for (String classOne : addClass) {
				
				if(i>0) {
					conClassStudying.append("' OR parents.Student.classstudying LIKE '"+classOne+"--"+"%");
				}else {
					conClassStudying.append(classOne+"--"+"%");
				}
				
				i++;
			}
		
		String classStudying = DataUtil.emptyString(conClassStudying.toString());
		String querySub = "";

		if (!classStudying.equalsIgnoreCase("")) {
			querySub = querySub + " (parents.Student.classstudying like '"
					+ classStudying + "') AND parents.Student.archive=0 and parents.Student.passedout=0 AND parents.Student.droppedout=0 and parents.Student.leftout=0 AND parents.Student.branchid="+Integer.parseInt(branchId)+" order by parents.Student.admissionnumber ASC";
		}

		if(!"".equalsIgnoreCase(querySub)) {
			queryMain = queryMain + querySub;
			searchStudentList = new studentDetailsDAO().getStudentsList(queryMain);
		}
		
	}
		//End Students
		
		
		if(currentAcademicYear!=null){
			
			List<StudentFeesReport> studentFeesReportList = new ArrayList<StudentFeesReport>();
			
			for (Parents parents : searchStudentList) {
				
				StudentFeesReport studentFeesReport = new StudentFeesReport();
				
				long id = parents.getStudent().getSid();
				
				List<Integer> feesCatList = new ArrayList<>(); 
				for (String feescat : feesCat) {
					feesCatList.add(Integer.parseInt(feescat));
				}
				List<Studentfeesstructure> feesstructureMain = new studentDetailsDAO().getStudentFeesStructurebyFeesCategory(id,feesCatList);
				List<Studentfeesstructure> feesStructure = new ArrayList<Studentfeesstructure>();
				
				for (Studentfeesstructure studentFeesStructure : feesstructureMain) {
					Long dueAmount =0l;
					dueAmount = dueAmount+(studentFeesStructure.getFeesamount()-studentFeesStructure.getFeespaid()-studentFeesStructure.getConcession()-studentFeesStructure.getWaiveoff());
					if(dueAmount>0) {
						feesStructure.add(studentFeesStructure);
					}
				}
				
				if (feesStructure.size() > 0) {
					
					studentFeesReport.setParents(parents);
					studentFeesReport.setStudentFeesStructure(feesStructure);
					
					studentFeesReportList.add(studentFeesReport);
					
				}
			}

			result.setResultList(studentFeesReportList);
			result.setSuccess(true);
		}
		return result;
	  }

}


