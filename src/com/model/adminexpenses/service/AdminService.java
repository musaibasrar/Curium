package com.model.adminexpenses.service;

import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.model.adminexpenses.dao.AdminDetailsDAO;
import com.model.adminexpenses.dto.Adminexpenses;
import com.model.feescollection.dto.Receiptinfo;
import com.model.student.dao.studentDetailsDAO;
import com.model.student.dto.Student;
import com.model.user.dao.UserDAO;
import com.util.DataUtil;
import com.util.DateUtil;

public class AdminService {
	
	 private HttpServletRequest request;
	    private HttpServletResponse response;
	    private HttpSession httpSession;
	    private String BRANCHID = "branchid";
	    private String USERID = "userloginid";
	
	public AdminService(HttpServletRequest request, HttpServletResponse response) {
		this.request = request;
        this.response = response;
        this.httpSession = request.getSession();
	}


	public boolean addExpenses() {

		Adminexpenses adminexpenses = new Adminexpenses();
		
		if(httpSession.getAttribute(BRANCHID)!=null){
			adminexpenses.setItemdescription(DataUtil.emptyString(request.getParameter("item")));
			adminexpenses.setPriceofitem(DataUtil.emptyString(request.getParameter("price")));
			adminexpenses.setPaidto(DataUtil.emptyString(request.getParameter("paidto")));
			adminexpenses.setPaymenttype(DataUtil.emptyString(request.getParameter("paymenttype")));
			adminexpenses.setChequeno(DataUtil.emptyString(request.getParameter("chequeno")));
			adminexpenses.setBankname(DataUtil.emptyString(request.getParameter("bankname")));
			adminexpenses.setChequedate(DateUtil.indiandateParser(request.getParameter("chequedate")));
			adminexpenses.setEntrydate(DateUtil.indiandateParser(request.getParameter("entrydate")));
			adminexpenses.setVoucherstatus("pending");
			adminexpenses.setBranchid(Integer.parseInt(httpSession.getAttribute(BRANCHID).toString()));
			adminexpenses.setUserid(Integer.parseInt(httpSession.getAttribute(USERID).toString()));
			
			if(!adminexpenses.getItemdescription().equalsIgnoreCase("") && !adminexpenses.getPriceofitem().equalsIgnoreCase(""))
				{
					adminexpenses = new AdminDetailsDAO().create(adminexpenses);
					return true;
				}
		}

			return false;
		
	}


	public boolean viewAllExpenses() {
		boolean result = false;
        try {
        	List<Adminexpenses> list = new AdminDetailsDAO().readListOfExpenses(Integer.parseInt(httpSession.getAttribute(BRANCHID).toString()));
            httpSession.setAttribute("adminexpenses", list);

            result = true;
        } catch (Exception e) {
            e.printStackTrace();
            result = false;
        }
        return result;
	}


	

	public void deleteMultiple() {
		 String[] expensesIds = request.getParameterValues("expensesIDs");
		 if(expensesIds!=null){
	        List ids = new ArrayList();
	        for (String id : expensesIds) {
	            System.out.println("id" + id);
	            ids.add(Integer.valueOf(id));

	        }
	        System.out.println("id length" + expensesIds.length);
	        new AdminDetailsDAO().deleteMultiple(ids);
	}
	}


	public void searchExpensesbydate() {
		 
		List<Adminexpenses> adminExpensesList = new ArrayList<Adminexpenses>();
		String branchId = request.getParameter("selectedbranchid");
		int idBranch = 0;
               
		if(httpSession.getAttribute(BRANCHID)!=null){
		

	        if(branchId!=null) {
	        	String[] branchIdName = branchId.split(":");
	        	idBranch = Integer.parseInt(branchIdName[0]);
	        	httpSession.setAttribute("expensesdatebranchname", branchIdName[1]);
	        	httpSession.setAttribute("branchname", "Branch Name:");
	        }else {
	        	idBranch = Integer.parseInt(httpSession.getAttribute(BRANCHID).toString());
	        }
	        
		String queryMain ="From Adminexpenses as adminexpenses where adminexpenses.voucherstatus='approved' AND adminexpenses.branchid="+idBranch+" AND";
		String toDate= DataUtil.emptyString(request.getParameter("todate"));
		String fromDate = DataUtil.emptyString(request.getParameter("fromdate"));
		String oneDay = DataUtil.emptyString(request.getParameter("oneday"));
		
		
			String querySub = "";
			
			if(!oneDay.equalsIgnoreCase("")){
				Date dateOne = DateUtil.indiandateParser(oneDay);
				String ondate = DateUtil.dateParseryyyymmdd(dateOne);
				querySub = " adminexpenses.entrydate = '"+ondate+"'" ;
				request.setAttribute("dayone", oneDay);
				request.setAttribute("datefrom", "");
				request.setAttribute("dateto", "");
			}
			
			if(!fromDate.equalsIgnoreCase("")  && !toDate.equalsIgnoreCase("")){
				Date dateFrom = DateUtil.indiandateParser(fromDate);
				String fromDateString = DateUtil.dateParseryyyymmdd(dateFrom);
				Date dateToDate = DateUtil.indiandateParser(toDate);
				String toDateString = DateUtil.dateParseryyyymmdd(dateToDate);
				querySub = " adminexpenses.entrydate between '"+fromDateString+"' AND '"+toDateString+"'";
				request.setAttribute("datefrom", fromDate);
				request.setAttribute("dateto", toDate);
				request.setAttribute("dayone", "");
			}
			
			queryMain = queryMain+querySub;
			/*queryMain = "FROM Parents as parents where  parents.Student.dateofbirth = '2006-04-06'"; */
			adminExpensesList = new AdminDetailsDAO().searchExpensesbydate(queryMain);
			
	}
			long sumOfExpenses = 0l;
			for (Adminexpenses adminexp : adminExpensesList) {
				String bigNumber = adminexp.getPriceofitem();
				long expadmin = Long.valueOf(bigNumber.replaceAll(",", "").toString());
				sumOfExpenses = sumOfExpenses + expadmin;
			}
			
			request.setAttribute("adminexpenses", adminExpensesList);
			request.setAttribute("sumofexpenses", "Total: "+sumOfExpenses);
	}
	
	
	public void dailyExpenses() {

		 
		List<Adminexpenses> adminExpensesList = new ArrayList<Adminexpenses>();
		String branchId = request.getParameter("selectedbranchid");
		int idBranch = 0;
               
		if(httpSession.getAttribute(BRANCHID)!=null){


	        if(branchId!=null) {
	        	String[] branchIdName = branchId.split(":");
	        	idBranch = Integer.parseInt(branchIdName[0]);
	        	httpSession.setAttribute("expensesdatebranchname", branchIdName[1]);
	        	httpSession.setAttribute("branchname", "Branch Name:");
	        }else {
	        	idBranch = Integer.parseInt(httpSession.getAttribute(BRANCHID).toString());
	        }
	        
		String queryMain ="From Adminexpenses as adminexpenses where branchid="+idBranch+" AND";
		String pattern = "yyyy-MM-dd";
		String oneDay =new SimpleDateFormat(pattern).format(new Date());
		
		
			String querySub = "";
			
			if(!oneDay.equalsIgnoreCase("")){
				querySub = " adminexpenses.entrydate = '"+oneDay+"'" ;
				request.setAttribute("dayone", oneDay);
			}
			
						
			queryMain = queryMain+querySub;
			/*queryMain = "FROM Parents as parents where  parents.Student.dateofbirth = '2006-04-06'"; */
			adminExpensesList = new AdminDetailsDAO().searchExpensesbydate(queryMain);
			
	}
			long sumOfExpenses = 0l;
			for (Adminexpenses adminexp : adminExpensesList) {
				String bigNumber = adminexp.getPriceofitem();
				long expadmin = Long.valueOf(bigNumber.replaceAll(",", "").toString());
				sumOfExpenses = sumOfExpenses + expadmin;
			}
			
			request.setAttribute("dailyadminexpenses", adminExpensesList);
			request.setAttribute("dailyexpenses", sumOfExpenses);
	
	}
	
	
	public void getMonthlyExpenses() {

		
		List<String> monthList = new LinkedList<String>();
		List<String> totalExpensesSum = new LinkedList<String>();
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		Date newdate = new Date();
		String todaysDate = df.format(newdate);
		List<Adminexpenses> adminExpenseList = new ArrayList<Adminexpenses>();
		Date dateBefore = null;
		Date dateAfter = null;
		String queryMain = "From Adminexpenses as adminexpenses where ";
		String toDate = DataUtil.emptyString(request.getParameter("todate"));
		String fromDate = DataUtil.emptyString(request.getParameter("fromdate"));
		
		try {
			dateBefore = df.parse(todaysDate);
			dateAfter = df.parse(todaysDate);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		Calendar start1 = Calendar.getInstance();
		start1.setTime(dateBefore);
		Calendar end1 = Calendar.getInstance();
		end1.setTime(dateAfter);
		start1.set(Calendar.MONTH, start1.getActualMinimum(Calendar.MONTH));
		start1.set(Calendar.DAY_OF_MONTH, start1.getActualMinimum(Calendar.DAY_OF_MONTH));
		end1.set(Calendar.MONTH, end1.getActualMaximum(Calendar.MONTH));
		end1.add(Calendar.DAY_OF_MONTH, 1);
		
		for (Date date = start1.getTime(); start1.before(end1); start1.add(Calendar.MONTH,+1), date = start1.getTime()) {
			fromDate = new SimpleDateFormat("YYYY-MM-dd").format(date);
			Calendar endday = Calendar.getInstance();
			endday.setTime(date);
			endday.set(Calendar.DAY_OF_MONTH, start1.getActualMaximum(Calendar.DAY_OF_MONTH));
			Date enddayofmonth = endday.getTime();
			toDate = new SimpleDateFormat("YYYY-MM-dd").format(enddayofmonth);
			String querySub = "";
			querySub = " adminexpenses.entrydate between '" + fromDate + "' AND '" + toDate + "'";
			adminExpenseList = new AdminDetailsDAO().searchExpensesbydate(queryMain + querySub);
			BigDecimal sumOfExpenses = BigDecimal.ZERO;
			for (Adminexpenses expenseAdmin : adminExpenseList) {
				BigDecimal fee = new BigDecimal(expenseAdmin.getPriceofitem());
				sumOfExpenses = sumOfExpenses.add(fee);
			}
			totalExpensesSum.add("\"" + sumOfExpenses + "\"");
			//Date Format
			SimpleDateFormat month_date = new SimpleDateFormat("MMM yyyy", Locale.ENGLISH);
			String monthYear = month_date.format(date);
			
			monthList.add("\"" + monthYear + "\"");
		}
		
		request.setAttribute("monthlyexpenses", totalExpensesSum);
		request.setAttribute("monthlistexpenses", monthList);
	
	}
	
	
	public void getTotalBoysGirls() {
		List<Student> studentsList = new ArrayList<Student>();
		int branchId = Integer.parseInt(httpSession.getAttribute(BRANCHID).toString());
		int totalBoys = 0, totalGirls = 0;
		List<String> boysGirls = new ArrayList<String>();
		
		studentsList = new studentDetailsDAO().getListStudents("From Student as student where student.archive=0 and student.passedout=0 AND student.droppedout=0 and student.leftout=0 AND branchid = "+branchId+" order by name ASC");
		
		for (Student student : studentsList) {
			if("Male".equalsIgnoreCase(student.getGender())) {
					totalBoys+=1;
			}else if("Female".equalsIgnoreCase(student.getGender())) {
					totalGirls+=1;
			}
		}
		System.out.println("boys "+totalBoys);
		System.out.println("girls "+totalGirls);
		boysGirls.add("\"" + totalBoys + "\""); 
		boysGirls.add("\"" + totalGirls + "\"");
		request.setAttribute("totalboysgirls", boysGirls);
	}


	public void printVoucher() {
		 String[] expensesIds = request.getParameterValues("expensesIDs");
        try {
        	Adminexpenses adminExpense = new AdminDetailsDAO().readExpenses(Integer.parseInt(expensesIds[0]),Integer.parseInt(httpSession.getAttribute(BRANCHID).toString()));
            httpSession.setAttribute("adminexpenses", adminExpense);
        } catch (Exception e) {
            e.printStackTrace();
        }
	}


	public void rejectVoucher() {
		 String[] expensesIds = request.getParameterValues("expensesIDs");
		 if(expensesIds!=null){
	        List ids = new ArrayList();
	        for (String id : expensesIds) {
	            System.out.println("id" + id);
	            ids.add(Integer.valueOf(id));
	        }
	        new AdminDetailsDAO().rejectVoucher(ids);
	}
	}


	public void approveVoucher() {
		 String[] expensesIds = request.getParameterValues("expensesIDs");
		 if(expensesIds!=null){
	        List ids = new ArrayList();
	        for (String id : expensesIds) {
	            System.out.println("id" + id);
	            ids.add(Integer.valueOf(id));
	        }
	        new AdminDetailsDAO().approveVoucher(ids);
	}
	}


	
}
