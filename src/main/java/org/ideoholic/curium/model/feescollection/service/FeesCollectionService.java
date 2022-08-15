package org.ideoholic.curium.model.feescollection.service;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.ideoholic.curium.model.academicyear.dao.YearDAO;
import org.ideoholic.curium.model.academicyear.dto.Currentacademicyear;
import org.ideoholic.curium.model.account.dao.AccountDAO;
import org.ideoholic.curium.model.account.dto.Accountdetails;
import org.ideoholic.curium.model.account.dto.VoucherEntrytransactions;
import org.ideoholic.curium.model.feescollection.dao.feesCollectionDAO;
import org.ideoholic.curium.model.feescollection.dto.Feescollection;
import org.ideoholic.curium.model.feescollection.dto.Receiptinfo;
import org.ideoholic.curium.model.feescollection.dto.StudentFeesReport;
import org.ideoholic.curium.model.feesdetails.dao.feesDetailsDAO;
import org.ideoholic.curium.model.feesdetails.dto.Feesdetails;
import org.ideoholic.curium.model.parents.dao.parentsDetailsDAO;
import org.ideoholic.curium.model.parents.dto.Parents;
import org.ideoholic.curium.model.sendsms.service.SmsService;
import org.ideoholic.curium.model.std.dto.Classsec;
import org.ideoholic.curium.model.std.service.StandardService;
import org.ideoholic.curium.model.student.dao.studentDetailsDAO;
import org.ideoholic.curium.model.student.dto.Student;
import org.ideoholic.curium.model.student.dto.Studentfeesstructure;
import org.ideoholic.curium.model.user.dao.UserDAO;
import org.ideoholic.curium.model.user.dto.Login;
import org.ideoholic.curium.util.DataUtil;
import org.ideoholic.curium.util.DateUtil;
import org.ideoholic.curium.util.NumberToWord;

public class FeesCollectionService {

	private HttpServletRequest request;
	private HttpServletResponse response;
	private HttpSession httpSession;
	private String CURRENTACADEMICYEAR = "currentAcademicYear";
	private String BRANCHID = "branchid";
	private String USERID = "userloginid";
	private String username = "username";
	
	private static final int BUFFER_SIZE = 4096;
	
	public FeesCollectionService(HttpServletRequest request,
			HttpServletResponse response) {
		this.request = request;
		this.response = response;
		this.httpSession = request.getSession();
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

	public void getStampFees() {
		if(httpSession.getAttribute(CURRENTACADEMICYEAR)!=null){
		String academicYear = request.getParameter("academicyear");	
			
		long id = Long.parseLong(request.getParameter("studentId"));
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
		request.setAttribute("studentfeesdetails", feesMap);
		request.setAttribute("studentNameDetails", request.getParameter("studentname"));
		//request.setAttribute("admnoDetails", request.getParameter("admno"));
		request.setAttribute("admnoDetails", request.getParameter("admissionno"));
		request.setAttribute("classandsecDetails", request.getParameter("classandsec"));
		request.setAttribute("studentIdDetails", request.getParameter("studentId"));
		request.setAttribute("dateoffeesDetails", request.getParameter("dateoffees"));
		
		}
	}

	public Receiptinfo add() {
		
		List<Feescollection> feescollection = new ArrayList<Feescollection>();
		Receiptinfo receiptInfo =new Receiptinfo();
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
					Feescollection feesCollect = new Feescollection();
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
				totalSum = totalSum + receiptInfoSingle.getTotalamount();
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

	public void previewDetails() {
		
		
		if(httpSession.getAttribute(CURRENTACADEMICYEAR)!=null){
			String receiptNumber = request.getParameter("id");
			String dp = request.getParameter("duplicate");
			Receiptinfo rinfo = new feesCollectionDAO().getReceiptInfoDetails(Integer.parseInt(receiptNumber));
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
			httpSession.setAttribute("parents", parents);
			httpSession.setAttribute("student", student);
			request.setAttribute("recieptdate", reDate);
			request.setAttribute("recieptinfo", rinfo);
			request.setAttribute("feescatmap", feeCatMap);
			request.setAttribute("duplicate", dp);
			request.setAttribute("user", userLogin);
			NumberToWord toWord = new NumberToWord();
			String grandTotal = toWord.convert(rinfo.getTotalamount().intValue());
			httpSession.setAttribute("grandTotal", grandTotal+" "+"Only");
			
			getFeesDetails(String.valueOf(rinfo.getSid()), rinfo.getAcademicyear());
		}
		
	}

	public void previewFeesDetails() {
		
		if(httpSession.getAttribute(CURRENTACADEMICYEAR)!=null){
			long sid=DataUtil.parseLong(request.getParameter("sid"));	
			int receiptNo = DataUtil.parseInt(request.getParameter("id"));
			 
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
			httpSession.setAttribute("student", student);
			request.setAttribute("recieptdate", reDate);
			request.setAttribute("recieptinfo", rinfo);
			request.setAttribute("feescatmap", feeCatMap);
			request.setAttribute("duplicate", "duplicate");

			getFeesDetails(String.valueOf(rinfo.getSid()), rinfo.getAcademicyear());
			
		}
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

	public void viewCancelledReceipts() {
		 
		List<Receiptinfo> feesDetailsList = new ArrayList<Receiptinfo>();
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
	        
		String queryMain ="From Receiptinfo as feesdetails where cancelreceipt=1 and feesdetails.branchid="+idBranch+" AND";
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
			feesDetailsList = new UserDAO().getReceiptDetailsList(queryMain);
			
	}
			long sumOfFees = 0l;
			for (Receiptinfo receiptinfo : feesDetailsList) {
				sumOfFees = sumOfFees + receiptinfo.getTotalamount();
			}
			
			httpSession.setAttribute("searchfeesdetailslistcancelled", feesDetailsList);
			httpSession.setAttribute("sumofdetailsfeescancelled", sumOfFees);
	}

	public void undoFeesReceipt() {
		
		if(httpSession.getAttribute(CURRENTACADEMICYEAR)!=null){
			
			int receiptId = DataUtil.parseInt(request.getParameter("id"));
			List<Feescollection> feesCollection = new feesCollectionDAO().getFeesCollectionDetails(receiptId);
			boolean result = new feesDetailsDAO().undoFeesReceipt(receiptId, feesCollection);
			
			request.setAttribute("cancelreceiptresult", result);
				
		}
	}

	public void getFeesReport() {
		
		
		String academicYear = request.getParameter("academicyear");
		String[] feesCat = request.getParameterValues("feescategory");
		
		//Get Students
		
		List<Parents> searchStudentList = new ArrayList<Parents>();
		
		if(httpSession.getAttribute(BRANCHID)!=null){
		
		String queryMain = "From Parents as parents where";
		String[] addClass = request.getParameterValues("classsearch");
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
					+ classStudying + "') AND parents.Student.archive=0 and parents.Student.passedout=0 AND parents.Student.droppedout=0 and parents.Student.leftout=0 AND parents.branchid="+Integer.parseInt(httpSession.getAttribute(BRANCHID).toString())+" order by parents.Student.admissionnumber ASC";
		}

		if(!"".equalsIgnoreCase(querySub)) {
			queryMain = queryMain + querySub;
			searchStudentList = new studentDetailsDAO().getStudentsList(queryMain);
		}
		
	}
		//End Students
		
		
		if(httpSession.getAttribute(CURRENTACADEMICYEAR)!=null){
			
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
		
			httpSession.setAttribute("studentfeesreportlist", studentFeesReportList);
		}
		
	  }
	
	
	
	public void getFeesDetailsDashBoard() {
		
		Long totalFeesAmount = 0l;
		Long totalPaidAmount = 0l;
		Long totalDueAmount = 0l;
		

		// Get Students

		List<Parents> searchStudentList = new ArrayList<Parents>();

		if (httpSession.getAttribute(BRANCHID) != null) {

			String queryMain = "From Parents as parents where parents.Student.branchid="+Integer.parseInt(httpSession.getAttribute(BRANCHID).toString())+" AND ";
			new StandardService(request, response).viewClasses();
			List<Classsec> classList = (List<Classsec>) httpSession.getAttribute("classdetailslist");
			
			
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
				querySub = querySub + " parents.Student.classstudying like '" + classStudying
						+ "' AND parents.Student.archive=0 and parents.Student.passedout=0 AND parents.Student.droppedout=0 and parents.Student.leftout=0 "
						+ " order by parents.Student.admissionnumber ASC";
			}

			if (!"".equalsIgnoreCase(querySub)) {
				queryMain = queryMain + querySub;
				searchStudentList = new studentDetailsDAO().getStudentsList(queryMain);
			}

		}
		// End Students

		if (httpSession.getAttribute(CURRENTACADEMICYEAR) != null) {

			List<StudentFeesReport> studentFeesReportList = new ArrayList<StudentFeesReport>();

			for (Parents parents : searchStudentList) {

				StudentFeesReport studentFeesReport = new StudentFeesReport();

				long id = parents.getStudent().getSid();
				List<Studentfeesstructure> feesstructure = new studentDetailsDAO().getStudentFeesStructure(id,
						httpSession.getAttribute(CURRENTACADEMICYEAR).toString());

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

			request.setAttribute("totalFeesAmountDashBoard", totalFeesAmount);
			request.setAttribute("totalPaidAmountDashBoard", totalPaidAmount);
			request.setAttribute("totalDueAmountDashBoard", totalDueAmount);
		}
		
		
		
		//get monthly and daily fees details
		 
		List<Receiptinfo> feesDetailsListDaily = new ArrayList<Receiptinfo>();
		List<Receiptinfo> feesDetailsListMonthly = new ArrayList<Receiptinfo>();
		String branchId = request.getParameter("selectedbranchid");
		int idBranch = 0;
		String Currentmonth = null;        
		
		if(httpSession.getAttribute(BRANCHID)!=null){
		

	        if(branchId!=null) {
	        	String[] branchIdName = branchId.split(":");
	        	idBranch = Integer.parseInt(branchIdName[0]);
	        	httpSession.setAttribute("feesdetailsbranchname", branchIdName[1]);
	        	httpSession.setAttribute("branchname", "Branch Name:");
	        }else {
	        	idBranch = Integer.parseInt(httpSession.getAttribute(BRANCHID).toString());
	        }
	        
		String queryMainDaily ="From Receiptinfo as feesdetails where feesdetails.cancelreceipt=0 and feesdetails.branchid="+idBranch+" AND";
		String queryMainMonthly ="From Receiptinfo as feesdetails where feesdetails.cancelreceipt=0 and feesdetails.branchid="+idBranch+" AND";


		Date monthOf = new Date();
		 
		Calendar cStart = Calendar.getInstance();
		cStart.setTime(monthOf);
		cStart.set(Calendar.DAY_OF_MONTH, cStart.getActualMinimum(Calendar.DAY_OF_MONTH));
		monthOf = cStart.getTime();
		Timestamp TimestampFrom = new Timestamp(monthOf.getTime());
		
		cStart.set(Calendar.DAY_OF_MONTH, cStart.getActualMaximum(Calendar.DAY_OF_MONTH));
		Date lastDayOfMonth = cStart.getTime();
		Timestamp Timestampto = new Timestamp(lastDayOfMonth.getTime());
		
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
			
			
			httpSession.setAttribute("sumOfFeesDaily", sumOfFeesDaily);
			httpSession.setAttribute("sumOfFeesMonthly", sumOfFeesMonthly);
			httpSession.setAttribute("Currentmonth", Currentmonth+"'s");
	
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

	public void getFeesDetails() {
		
		try {
			long id = Long.parseLong(request.getParameter("studentId"));
			String academicYear = request.getParameter("academicyear");
			
			//Currentacademicyear currentYear = new YearDAO().showYear();
			//httpSession.setAttribute("currentyearfromservice",currentYear.getCurrentacademicyear());
			
			List<Receiptinfo> rinfo = new feesCollectionDAO().getReceiptDetailsPerStudent(id,academicYear);
			request.setAttribute("receiptinfo",rinfo);
			List<Studentfeesstructure> feesstructure = new studentDetailsDAO().getStudentFeesStructure(id, academicYear);
			
			long totalSum = 0l;
			for (Receiptinfo receiptInfoSingle : rinfo) {
				totalSum = totalSum + receiptInfoSingle.getTotalamount();
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

	public void getFeesStampDueReport() {
		
		
		String academicYear = request.getParameter("academicyear");
		//Get Students
		
		List<Parents> searchStudentList = new ArrayList<Parents>();
		
		if(httpSession.getAttribute(BRANCHID)!=null){
		
		String queryMain = "From Parents as parents where";
		String[] addClass = request.getParameterValues("classsearch");
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
					+ classStudying + "') AND parents.Student.archive=0 and parents.Student.passedout=0 AND parents.Student.droppedout=0 and parents.Student.leftout=0 AND parents.branchid="+Integer.parseInt(httpSession.getAttribute(BRANCHID).toString())+" order by parents.Student.admissionnumber ASC";
		}

		if(!"".equalsIgnoreCase(querySub)) {
			queryMain = queryMain + querySub;
			searchStudentList = new studentDetailsDAO().getStudentsList(queryMain);
		}
		
	}
		//End Students
		
		
		if(httpSession.getAttribute(CURRENTACADEMICYEAR)!=null){
			
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
		
			httpSession.setAttribute("studentfeesreportlist", studentFeesReportList);
		}
		
	  }

}


