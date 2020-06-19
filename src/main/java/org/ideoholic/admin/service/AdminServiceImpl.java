package org.ideoholic.admin.service;

import java.util.ArrayList;
import java.util.List;

import com.model.adminexpenses.dao.AdminDetailsDAO;
import com.model.adminexpenses.dto.Adminexpenses;
import com.util.DataUtil;
import com.util.DateUtil;

public class AdminServiceImpl implements AdminService {

	
	public String viewAllExpenses(String branchId) {
		StringBuffer sb = new StringBuffer();
		sb.append("{");
		boolean result = false;
        try {
        	List<Adminexpenses> list = new AdminDetailsDAO().readListOfExpenses(Integer.parseInt(branchId.toString()));
        	sb.append("adminexpenses").append(list);

            result = true;
        } catch (Exception e) {
            e.printStackTrace();
            result = false;
        }
        sb.append("}");
        return sb.toString();
	}
	
	public String addExpenses(String branchId, String item, String quantity, String price, String entrydate) {
		StringBuffer sb = new StringBuffer();
		sb.append("{");

		Adminexpenses adminexpenses = new Adminexpenses();
		if(branchId!=null){
			
			adminexpenses.setBranchid(Integer.parseInt(branchId.toString()));
			
			if(!adminexpenses.getItemDescription().equalsIgnoreCase("") && adminexpenses.getQuantity() != 0
					&& adminexpenses.getPriceofitem() != 0				
					){
			adminexpenses = new AdminDetailsDAO().create(adminexpenses);
			sb.append("result:").append("true");
			    return sb.toString();
			}
		}
		sb.append("result:").append("false");
		sb.append("}");
		return sb.toString();
		
	}
	
	public String deleteMultiple(String[] expensesIds) {
		StringBuffer sb = new StringBuffer();
		sb.append("{");
		 if(expensesIds!=null){
	        List ids = new ArrayList();
	        for (String id : expensesIds) {
	            System.out.println("id" + id);
	            ids.add(Integer.valueOf(id));
	        }
	        System.out.println("id length" + expensesIds.length);
	        new AdminDetailsDAO().deleteMultiple(ids);
	}
		 sb.append("}");
		return sb.toString();
	}
}
