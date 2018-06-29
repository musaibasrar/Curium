package com.model.adminexpenses.service;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.model.adminexpenses.dao.AdminDetailsDAO;
import com.model.adminexpenses.dto.Adminexpenses;
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
			adminexpenses.setEntrydate(DateUtil.simpleDateParser(request.getParameter("entrydate")));
			
			
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


	
}
