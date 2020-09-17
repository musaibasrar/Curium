package com.model.adminexpenses.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.model.adminexpenses.dao.AdminDetailsDAO;
import com.model.adminexpenses.dto.Adminexpenses;
import com.model.feescollection.dto.Receiptinfo;
import com.model.user.dao.UserDAO;
import com.util.DataUtil;
import com.util.DateUtil;

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


	public boolean addExpenses() {

		Adminexpenses adminexpenses = new Adminexpenses();
		if(httpSession.getAttribute(BRANCHID)!=null){
			adminexpenses.setItemDescription(DataUtil.emptyString(request.getParameter("item")));
			adminexpenses.setQuantity(DataUtil.parseInt(request.getParameter("quantity")));
			adminexpenses.setPriceofitem(DataUtil.parseInt(request.getParameter("price")));
			adminexpenses.setEntrydate(DateUtil.indiandateParser(request.getParameter("entrydate")));
			adminexpenses.setBranchid(Integer.parseInt(httpSession.getAttribute(BRANCHID).toString()));
			
			if(!adminexpenses.getItemDescription().equalsIgnoreCase("") && adminexpenses.getQuantity() != 0
					&& adminexpenses.getPriceofitem() != 0				
					){
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
	        
		String queryMain ="From Adminexpenses as adminexpenses where branchid="+idBranch+" AND";
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
			System.out.println("SEARCH QUERY ***** "+queryMain);
			adminExpensesList = new AdminDetailsDAO().searchExpensesbydate(queryMain);
			
	}
			long sumOfExpenses = 0l;
			for (Adminexpenses adminexp : adminExpensesList) {
				sumOfExpenses = sumOfExpenses + adminexp.getPriceofitem();
			}
			
			request.setAttribute("adminexpenses", adminExpensesList);
			request.setAttribute("sumofexpenses", "Total: "+sumOfExpenses);
	}


	
}
