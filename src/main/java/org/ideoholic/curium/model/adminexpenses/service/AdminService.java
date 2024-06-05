package org.ideoholic.curium.model.adminexpenses.service;

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

import org.ideoholic.curium.model.adminexpenses.dao.AdminDetailsDAO;
import org.ideoholic.curium.model.adminexpenses.dto.AdminExpenseResponseDto;
import org.ideoholic.curium.model.adminexpenses.dto.AdminExpensesDateDto;
import org.ideoholic.curium.model.adminexpenses.dto.AdminExpensesDto;
import org.ideoholic.curium.model.adminexpenses.dto.Adminexpenses;
import org.ideoholic.curium.model.adminexpenses.dto.ExpensesIdDto;
import org.ideoholic.curium.model.feescollection.dto.Receiptinfo;
import org.ideoholic.curium.model.student.dao.studentDetailsDAO;
import org.ideoholic.curium.model.student.dto.Student;
import org.ideoholic.curium.model.user.dao.UserDAO;
import org.ideoholic.curium.util.DataUtil;
import org.ideoholic.curium.util.DateUtil;
import org.ideoholic.curium.util.ResultResponse;

public class AdminService {
	
	 private HttpServletRequest request;
	    private HttpServletResponse response;
	    private HttpSession httpSession;
	    private String BRANCHID = "branchid";
	
	public AdminService(HttpServletRequest request, HttpServletResponse response) {
		this.request = request;
        this.response = response;
        this.httpSession = request.getSession();
	}



	public ResultResponse addExpenses(AdminExpensesDto adminexpensesdto) {

		
		Adminexpenses adminexpenses = new Adminexpenses();
		
		if(adminexpensesdto.getBranchId()!=null){
			adminexpenses.setItemdescription(DataUtil.emptyString(adminexpensesdto.getItemdescription()));
			adminexpenses.setPriceofitem(DataUtil.emptyString(adminexpensesdto.getPriceofitem()));
			adminexpenses.setPaidto(DataUtil.emptyString(adminexpensesdto.getPaidto()));
			adminexpenses.setPaymenttype(DataUtil.emptyString(adminexpensesdto.getPaymenttype()));
			adminexpenses.setChequeno(DataUtil.emptyString(adminexpensesdto.getChequeno()));
			adminexpenses.setBankname(DataUtil.emptyString(adminexpensesdto.getBankname()));
			adminexpenses.setChequedate(DateUtil.indiandateParser(adminexpensesdto.getChequedate()));
			adminexpenses.setEntrydate(DateUtil.indiandateParser(adminexpensesdto.getEntrydate()));
			adminexpenses.setVoucherstatus("pending");
			adminexpenses.setUserid(adminexpensesdto.getUserid());
			adminexpenses.setBranchid(adminexpensesdto.getBranchId());
			
			if(!adminexpenses.getItemdescription().equalsIgnoreCase("") && !adminexpenses.getPriceofitem().equalsIgnoreCase(""))
				{
					adminexpenses = new AdminDetailsDAO().create(adminexpenses);
					return ResultResponse.builder().success(true).build();
				}
		}
         
		return ResultResponse.builder().success(false).build();
			
		
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
	        List<Integer> ids = new ArrayList<>();
	        for (String id : expensesIds) {
	            System.out.println("id" + id);
	            ids.add(Integer.valueOf(id));

	        }
	        System.out.println("id length" + expensesIds.length);
	        new AdminDetailsDAO().deleteMultiple(ids);
	}
	}


	public AdminExpenseResponseDto searchExpensesbydate(AdminExpensesDto adminexpensesdto) {
		AdminExpenseResponseDto adminExpenseResponseDto = new AdminExpenseResponseDto();
		List<Adminexpenses> adminExpensesList = new ArrayList<Adminexpenses>();
		String branchId = adminexpensesdto.getSelectedbranchid();
		int idBranch = 0;

		if (adminexpensesdto.getBranchId() != null) {

			if (branchId != null) {
				String[] branchIdName = branchId.split(":");
				idBranch = Integer.parseInt(branchIdName[0]);
				adminExpenseResponseDto.setExpensesdatebranchname(branchIdName[1]);
				adminExpenseResponseDto.setBranchname("Branch Name:");
			} else {
				idBranch = adminexpensesdto.getBranchId();
			}

			String queryMain = "From Adminexpenses as adminexpenses where adminexpenses.voucherstatus='approved' AND adminexpenses.branchid="
					+ idBranch + " AND";
			String toDate = DataUtil.emptyString(adminexpensesdto.getTodate());
			String fromDate = DataUtil.emptyString(adminexpensesdto.getFromdate());
			String oneDay = DataUtil.emptyString(adminexpensesdto.getOneday());

			String querySub = "";

			if (!oneDay.equalsIgnoreCase("")) {
				Date dateOne = DateUtil.indiandateParser(oneDay);
				String ondate = DateUtil.dateParseryyyymmdd(dateOne);
				querySub = " adminexpenses.entrydate = '" + ondate + "'";
				adminExpenseResponseDto.setDayone(oneDay);
				adminExpenseResponseDto.setDatefrom("");
				adminExpenseResponseDto.setDateto("");
			}

			if (!fromDate.equalsIgnoreCase("") && !toDate.equalsIgnoreCase("")) {
				Date dateFrom = DateUtil.indiandateParser(fromDate);
				String fromDateString = DateUtil.dateParseryyyymmdd(dateFrom);
				Date dateToDate = DateUtil.indiandateParser(toDate);
				String toDateString = DateUtil.dateParseryyyymmdd(dateToDate);
				querySub = " adminexpenses.entrydate between '" + fromDateString + "' AND '" + toDateString + "'";
				adminExpenseResponseDto.setDatefrom(fromDate);
				adminExpenseResponseDto.setDateto(toDate);
				adminExpenseResponseDto.setDayone("");
			}

			queryMain = queryMain + querySub;
			/*
			 * queryMain =
			 * "FROM Parents as parents where  parents.Student.dateofbirth = '2006-04-06'";
			 */
			adminExpensesList = new AdminDetailsDAO().searchExpensesbydate(queryMain);

		}
		long sumOfExpenses = 0l;
		for (Adminexpenses adminexp : adminExpensesList) {
			String bigNumber = adminexp.getPriceofitem();
			long expadmin = Long.valueOf(bigNumber.replaceAll(",", "").toString());
			sumOfExpenses = sumOfExpenses + expadmin;
		}
		adminExpenseResponseDto.setAdminexpenses(adminExpensesList);
		adminExpenseResponseDto.setSumofexpenses("Total: " + sumOfExpenses);
		return adminExpenseResponseDto;
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
		String queryMain = "From Adminexpenses as adminexpenses where branchid="+Integer.parseInt(httpSession.getAttribute(BRANCHID).toString())+" AND ";
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
		
		start1.set(Calendar.MONTH, Calendar.JUNE);
		start1.set(Calendar.DAY_OF_MONTH, 1);
		end1.set(Calendar.YEAR, start1.get(Calendar.YEAR) + 1);
		end1.set(Calendar.MONTH, Calendar.MAY);
		end1.add(Calendar.DAY_OF_MONTH, end1.getActualMaximum(Calendar.DAY_OF_MONTH));
		
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
		
		studentsList = new studentDetailsDAO().getListStudents("From Student as student where student.archive=0 and student.passedout=0 AND student.droppedout=0 and student.leftout=0 AND student.branchid = "+branchId+" order by name ASC");

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


	public Adminexpenses printVoucher(ExpensesIdDto expenseiddto) {
        try {
        	Adminexpenses adminExpense = new AdminDetailsDAO().readExpenses(Integer.parseInt(expenseiddto.getExpensesIds()[0]),expenseiddto.getBranchId());
           return adminExpense; 
        } catch (Exception e) {
            e.printStackTrace();
        }
      return null;  
	}

	
	
	public void rejectVoucher(ExpensesIdDto expenseiddto) {
		 if(expenseiddto.getExpensesIds()!=null){
	        List<Integer> ids = new ArrayList<>();
	        for (String id : expenseiddto.getExpensesIds()) {
	            System.out.println("id" + id);
	            ids.add(Integer.valueOf(id));
	        }
	        new AdminDetailsDAO().rejectVoucher(ids);
	}
	}


	public void approveVoucher(ExpensesIdDto expenseiddto) {
		 if(expenseiddto.getExpensesIds()!=null){
	        List<Integer> ids = new ArrayList<>();
	        for (String id : expenseiddto.getExpensesIds()) {
	            System.out.println("id" + id);
	            ids.add(Integer.valueOf(id));
	        }
	        new AdminDetailsDAO().approveVoucher(ids);
	}
	}


	public AdminExpenseResponseDto viewExpensesBetweenDates(AdminExpensesDateDto adminExpensesDateDto) {
		AdminExpenseResponseDto adminExpenseResponseDto = new AdminExpenseResponseDto();

		try {
			String toDate = DateUtil.dateFromatConversionSlash(DataUtil.emptyString(adminExpensesDateDto.getTodate()));
			String fromDate = DateUtil
					.dateFromatConversionSlash(DataUtil.emptyString(adminExpensesDateDto.getFromdate()));
			String voucherStatus = DataUtil.emptyString(adminExpensesDateDto.getVoucherstatus());
			String paymentType = DataUtil.emptyString(adminExpensesDateDto.getPaymenttype());
			String queryMain = "From Adminexpenses as adminexpenses where adminexpenses.branchid="
					+ adminExpensesDateDto.getBranchId() + " AND";

			String querySub = " adminexpenses.entrydate between '" + fromDate + "' AND '" + toDate + "'";

			if (voucherStatus != "") {
				querySub = querySub + " and adminexpenses.voucherstatus='" + voucherStatus + "'";
			}

			if (paymentType != "") {
				querySub = querySub + " and adminexpenses.paymenttype='" + paymentType + "'";
			}

			List<Adminexpenses> adminExpenseList = new ArrayList<Adminexpenses>();

			adminExpenseList = new AdminDetailsDAO().searchExpensesbydate(queryMain + querySub);
			BigDecimal sumOfExpenses = BigDecimal.ZERO;
			for (Adminexpenses expenseAdmin : adminExpenseList) {
				BigDecimal fee = new BigDecimal(expenseAdmin.getPriceofitem());
				sumOfExpenses = sumOfExpenses.add(fee);
			}
			adminExpenseResponseDto.setAdminexpenses(adminExpenseList);
			adminExpenseResponseDto.setSumofexpenses(sumOfExpenses.toPlainString());
			adminExpenseResponseDto.setSuccess(true);
		} catch (Exception e) {
			e.printStackTrace();
			adminExpenseResponseDto.setSuccess(false);
		}

		return adminExpenseResponseDto;
	}
}
