package com.model.feescollection.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.model.employee.dao.EmployeeDAO;
import com.model.employee.dto.Teacher;
import com.model.feescollection.dao.feesCollectionDAO;
import com.model.feescollection.dto.Feescollection;
import com.model.feesdetails.dao.feesDetailsDAO;
import com.model.feesdetails.dto.Feesdetails;
import com.model.parents.dao.parentsDetailsDAO;
import com.model.parents.dto.Parents;
import com.model.student.dao.studentDetailsDAO;
import com.model.student.dto.Student;
import com.util.DataUtil;
import com.util.NumberToWord;

public class FeesCollectionService {

	private HttpServletRequest request;
	private HttpServletResponse response;
	private HttpSession httpSession;

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
		for (int i = 0; i < feesIDS.length; i++) {
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
		}

		}

		return feescollection;
	}

	public void preview(Feescollection feescollection) {

		if(feescollection.getSid() != null){
    List<Feescollection> list = new feesCollectionDAO()
				.readListOfObject(feescollection.getFeesdetailsid());
		httpSession.setAttribute("feescollection", list);
		Student student = new studentDetailsDAO()
				.readUniqueObject(feescollection.getSid());
		httpSession.setAttribute("student", student);
		Feesdetails feesdetails = new feesDetailsDAO()
				.readUniqueObject(feescollection.getFeesdetailsid().longValue());
		httpSession.setAttribute("feesdetails", feesdetails);
		}
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
				Parents parents = new parentsDetailsDAO()
				.readUniqueObject(sid);
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

}
