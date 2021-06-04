package com.model.feescollection.service;

import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.model.account.dao.AccountDAO;
import com.model.account.dto.VoucherEntrytransactions;
import com.model.feescollection.dao.feesCollectionDAO;
import com.model.feescollection.dto.Feescollection;
import com.model.feescollection.dto.Receiptinfo;
import com.model.feescollection.dto.StudentFeesReport;
import com.model.feesdetails.dao.feesDetailsDAO;
import com.model.feesdetails.dto.Feesdetails;
import com.model.parents.dao.parentsDetailsDAO;
import com.model.parents.dto.Parents;
import com.model.std.dto.Classsec;
import com.model.std.service.StandardService;
import com.model.student.dao.studentDetailsDAO;
import com.model.student.dto.Student;
import com.model.student.dto.Studentfeesstructure;
import com.model.user.dao.UserDAO;
import com.util.DataUtil;
import com.util.DateUtil;
import com.util.NumberToWord;

public class FeesCollectionService {

	private HttpServletRequest request;
	private HttpServletResponse response;
	private HttpSession httpSession;
	private String CURRENTACADEMICYEAR = "currentAcademicYear";
	private String BRANCHID = "branchid";
	private String USERID = "userloginid";
	private String username = "username";
	
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
		long id = Long.parseLong(request.getParameter("studentId"));
		List<Studentfeesstructure> feesstructure = new studentDetailsDAO().getStudentFeesStructure(id, httpSession.getAttribute(CURRENTACADEMICYEAR).toString());
		//List<Feescollection> feesCollection = new feesCollectionDAO().getFeesForTheCurrentYear(id, httpSession.getAttribute(CURRENTACADEMICYEAR).toString());
		Map<Studentfeesstructure,Long> feesMap = new HashMap<Studentfeesstructure, Long>();
		
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
			Long totalDueAmount = singleFeesStructure.getFeesamount() - singleFeesStructure.getFeespaid();
			feesMap.put(singleFeesStructure,totalDueAmount);
		}
		request.setAttribute("studentfeesdetails", feesMap);
		request.setAttribute("studentNameDetails", request.getParameter("studentName"));
		request.setAttribute("admnoDetails", request.getParameter("admno"));
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
		
			if("banktransfer".equalsIgnoreCase(paymentMethod)) {
				ackNoVoucherNarration = " acknowledgement number: "+ackNo+" , Amount transfer date: "+transferDate;
			}else if("chequetransfer".equalsIgnoreCase(paymentMethod)) {
				chequeNoVoucherNarration = " cheque number: "+chequeNoVoucherNarration+" , Amount clearance date: "+chequeDate;
			}
		
		//End Payment Details
		
		if(studentSfsIds!=null){
			
			// create receipt information
			receiptInfo.setAcademicyear(httpSession.getAttribute(CURRENTACADEMICYEAR).toString());
			receiptInfo.setDate(new Date());
			receiptInfo.setSid(DataUtil.parseInt(sid));
			receiptInfo.setBranchid(Integer.parseInt(httpSession.getAttribute(BRANCHID).toString()));
			receiptInfo.setUserid(Integer.parseInt(httpSession.getAttribute(USERID).toString()));
			Long grantTotal = 0l;
			for (int i = 0; i < studentSfsIds.length; i++) {
				String[] totalAmount = studentSfsIds[i].split("_");
				grantTotal+=DataUtil.parseLong(amountPaying[Integer.parseInt(totalAmount[1])]);
			}
			receiptInfo.setTotalamount(grantTotal);
			//new feesCollectionDAO().createReceipt(receiptInfo);
			 
				for (int i = 0; i < studentSfsIds.length; i++) {
					Feescollection feesCollect = new Feescollection();
					String[] studentSfsIdamount = studentSfsIds[i].split("_");
					feesCollect.setSfsid(DataUtil.parseInt(studentSfsIdamount[0]));
					feesCollect.setAmountpaid(DataUtil.parseLong(amountPaying[Integer.parseInt(studentSfsIdamount[1])]));
					feesCollect.setSid(DataUtil.parseInt(sid));
					feesCollect.setFine(DataUtil.parseLong("0"));
					feesCollect.setDate(new Date());
					feesCollect.setAcademicyear(httpSession.getAttribute(CURRENTACADEMICYEAR).toString());
					//HERE feesCollect.setReceiptnumber(receiptInfo.getReceiptnumber());
					feesCollect.setBranchid(Integer.parseInt(httpSession.getAttribute(BRANCHID).toString()));
					feesCollect.setUserid(Integer.parseInt(httpSession.getAttribute(USERID).toString()));
					feescollection.add(feesCollect);
				}
				
				//Pass J.V. : credit the Fees as income & debit the cash
				
				int crFees = getLedgerAccountId("feesaccountid");
				int drAccount = 0;
				
				if("cashpayment".equalsIgnoreCase(paymentMethod)) {
					drAccount = getLedgerAccountId(httpSession.getAttribute(username).toString());
				}else if("banktransfer".equalsIgnoreCase(paymentMethod)) {
					drAccount = getLedgerAccountId(transferBankname);
				}else if("chequetransfer".equalsIgnoreCase(paymentMethod)) {
					drAccount = getLedgerAccountId(chequeBankname);
				} 
				
				
				VoucherEntrytransactions transactions = new VoucherEntrytransactions();
				
				transactions.setDraccountid(drAccount);
				transactions.setCraccountid(crFees);
				transactions.setDramount(new BigDecimal(receiptInfo.getTotalamount()));
				transactions.setCramount(new BigDecimal(receiptInfo.getTotalamount()));
				transactions.setVouchertype(4);
				transactions.setTransactiondate(receiptInfo.getDate());
				transactions.setEntrydate(DateUtil.todaysDate());
				transactions.setNarration("Towards Fees Payment:  "+ackNoVoucherNarration+" "+chequeNoVoucherNarration);
				transactions.setCancelvoucher("no");
				transactions.setFinancialyear(new AccountDAO().getCurrentFinancialYear(Integer.parseInt(httpSession.getAttribute(BRANCHID).toString())).getFinancialid());
				transactions.setBranchid(Integer.parseInt(httpSession.getAttribute(BRANCHID).toString()));
				transactions.setUserid(Integer.parseInt(httpSession.getAttribute(USERID).toString()));
				
				String updateDrAccount="update Accountdetailsbalance set currentbalance=currentbalance+"+receiptInfo.getTotalamount()+" where accountdetailsid="+drAccount;

				String updateCrAccount="update Accountdetailsbalance set currentbalance=currentbalance+"+receiptInfo.getTotalamount()+" where accountdetailsid="+crFees;
				
				// End J.V
				createFeesCollection = new feesCollectionDAO().create(receiptInfo,feescollection,transactions,updateDrAccount,updateCrAccount);
				
			}
		}
		return receiptInfo;
	}


	public void preview(Receiptinfo receiptInfo) {
		
		if(httpSession.getAttribute(CURRENTACADEMICYEAR)!=null){
		
			Receiptinfo rinfo = new feesCollectionDAO().getReceiptInfoDetails(receiptInfo.getReceiptnumber(),httpSession.getAttribute(CURRENTACADEMICYEAR).toString());
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
			Receiptinfo rinfo = new feesCollectionDAO().getReceiptInfoDetails(Integer.parseInt(receiptNumber),httpSession.getAttribute(CURRENTACADEMICYEAR).toString());
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
			httpSession.setAttribute("parents", parents);
			httpSession.setAttribute("student", student);
			request.setAttribute("recieptdate", reDate);
			request.setAttribute("recieptinfo", rinfo);
			request.setAttribute("feescatmap", feeCatMap);
			request.setAttribute("duplicate", dp);
			NumberToWord toWord = new NumberToWord();
			String grandTotal = toWord.convert(rinfo.getTotalamount().intValue());
			httpSession.setAttribute("grandTotal", grandTotal+" "+"Only");
		}
		
	}

	public void previewFeesDetails() {
		
		if(httpSession.getAttribute(CURRENTACADEMICYEAR)!=null){
			long sid=DataUtil.parseLong(request.getParameter("sid"));	
			int receiptNo = DataUtil.parseInt(request.getParameter("id"));
			 
			Receiptinfo rinfo = new feesCollectionDAO().getReceiptInfoDetails(receiptNo,httpSession.getAttribute(CURRENTACADEMICYEAR).toString());
			Set<Feescollection> setFeesCollection = rinfo.getFeesCollectionRecords();
			Map<String,Long> feeCatMap = new HashMap<String, Long>();

			for (Feescollection feescollectionSingle : setFeesCollection) {
				List<Studentfeesstructure> studentfeesstructure = new studentDetailsDAO().getStudentFeesStructureDetails(feescollectionSingle.getSfsid());
				feeCatMap.put(studentfeesstructure.get(0).getFeescategory().getFeescategoryname(), feescollectionSingle.getAmountpaid());
			}
			Date receiptDate = rinfo.getDate();
			String reDate = new SimpleDateFormat("dd/MM/yyyy").format(receiptDate);
			Student student = new studentDetailsDAO().readUniqueObject(sid);
			httpSession.setAttribute("student", student);
			request.setAttribute("recieptdate", reDate);
			request.setAttribute("recieptinfo", rinfo);
			request.setAttribute("feescatmap", feeCatMap);
			request.setAttribute("duplicate", "duplicate");
			
		}
	}

	public void cancelFeesReceipt() {
		
		if(httpSession.getAttribute(CURRENTACADEMICYEAR)!=null){
			
			int receiptId = DataUtil.parseInt(request.getParameter("id"));
			List<Feescollection> feesCollection = new feesCollectionDAO().getFeesCollectionDetails(receiptId);
			boolean result = new feesDetailsDAO().cancelFeesReceipt(receiptId, feesCollection);
			
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
			querySub = querySub + " parents.Student.classstudying like '"
					+ classStudying + "' AND parents.Student.archive=0 and parents.Student.passedout=0 AND parents.Student.droppedout=0 and parents.Student.leftout=0 AND parents.branchid="+Integer.parseInt(httpSession.getAttribute(BRANCHID).toString())+" order by parents.Student.admissionnumber ASC";
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
				List<Studentfeesstructure> feesstructure = new studentDetailsDAO().getStudentFeesStructure(id, httpSession.getAttribute(CURRENTACADEMICYEAR).toString());
				
				studentFeesReport.setStudent(parents.getStudent());
				studentFeesReport.setStudentFeesStructure(feesstructure);
				
				studentFeesReportList.add(studentFeesReport);
			}
		
			request.setAttribute("studentfeesreportlist", studentFeesReportList);
		}
		
	  }
	
	
	
	public void getFeesDetailsDashBoard() {
		
		Long totalFeesAmount = 0l;
		Long totalPaidAmount = 0l;
		Long totalDueAmount = 0l;
		

		// Get Students

		List<Parents> searchStudentList = new ArrayList<Parents>();

		if (httpSession.getAttribute(BRANCHID) != null) {

			String queryMain = "From Parents as parents where";
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
						+ "' AND parents.Student.archive=0 and parents.Student.passedout=0 AND parents.Student.droppedout=0 and parents.Student.leftout=0 AND parents.branchid="
						+ Integer.parseInt(httpSession.getAttribute(BRANCHID).toString())
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

				studentFeesReport.setStudent(parents.getStudent());
				studentFeesReport.setStudentFeesStructure(feesstructure);

				studentFeesReportList.add(studentFeesReport);
			}
			
			
			for (StudentFeesReport studentFeesReport : studentFeesReportList) {
				
				for (Studentfeesstructure studentFeesStructure : studentFeesReport.getStudentFeesStructure()) {
					totalFeesAmount+=studentFeesStructure.getFeesamount();
					totalPaidAmount+=studentFeesStructure.getFeespaid();
					totalDueAmount = totalDueAmount + (studentFeesStructure.getFeesamount()-studentFeesStructure.getFeespaid());
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
		String fromDayOne = new SimpleDateFormat(pattern).format(new Date(TimestampFrom.getTime()));
		String toLastDay = new SimpleDateFormat(pattern).format(new Date(Timestampto.getTime()));
		
			String querySubDaily = "";
			String querySubMonthly = "";
			// Daily Fees
			querySubDaily = " feesdetails.date = '"+todaysDate+"'" ;
			queryMainDaily = queryMainDaily+querySubDaily;
			/*queryMain = "FROM Parents as parents where  parents.Student.dateofbirth = '2006-04-06'"; */
			feesDetailsListDaily = new UserDAO().getReceiptDetailsList(queryMainDaily);
			
			// Monthly Fees
			    querySubMonthly = " feesdetails.date between '"+fromDayOne+"' AND '"+toLastDay+"'";
			    queryMainMonthly = queryMainMonthly+querySubMonthly;
			/*queryMain = "FROM Parents as parents where  parents.Student.dateofbirth = '2006-04-06'"; */
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
	
	}


