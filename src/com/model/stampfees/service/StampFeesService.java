package com.model.stampfees.service;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.model.parents.dto.Parents;
import com.model.stampfees.dao.StampFeesDAO;
import com.model.stampfees.dto.Academicfeesstructure;
import com.model.student.dao.studentDetailsDAO;
import com.model.student.dto.Studentfeesstructure;
import com.model.user.dao.UserDAO;
import com.util.DataUtil;

public class StampFeesService {

	HttpServletRequest request;
	HttpServletResponse response;
	HttpSession httpSession;
	

	public StampFeesService(HttpServletRequest request,
			HttpServletResponse response) {
		this.request = request;
		this.response = response;
		this.httpSession = request.getSession();
	}

	public void advanceSearch() {

		String queryMain = "From Parents as parents where";
		String studentname = DataUtil.emptyString(request
				.getParameter("namesearch"));

		String addClass = request.getParameter("classsearch");
		String addSec = request.getParameter("secsearch");
		String conClassStudying = "";

		if (!addClass.equalsIgnoreCase("Class")) {

			conClassStudying = addClass + " " + "%";

		}
		if (!addSec.equalsIgnoreCase("Sec")) {
			conClassStudying = addClass;
			conClassStudying = conClassStudying + " " + addSec;
		}

		String classStudying = DataUtil.emptyString(conClassStudying);
		String querySub = "";

		if (!studentname.equalsIgnoreCase("")) {
			querySub = " parents.Student.name like '%" + studentname + "%' AND parents.Student.archive=0";
		}

		if (!classStudying.equalsIgnoreCase("")
				&& !querySub.equalsIgnoreCase("")) {
			querySub = querySub + " AND parents.Student.classstudying like '"
					+ classStudying + "' AND parents.Student.archive=0";
		} else if (!classStudying.equalsIgnoreCase("")) {
			querySub = querySub + " parents.Student.classstudying like '"
					+ classStudying + "' AND parents.Student.archive=0";
		}

		queryMain = queryMain + querySub;
		/*
		 * queryMain =
		 * "FROM Parents as parents where  parents.Student.dateofbirth = '2006-04-06'"
		 * ;
		 */
		System.out.println("SEARCH QUERY ***** " + queryMain);
		List<Parents> searchStudentList = new studentDetailsDAO().getStudentsList(queryMain);
		request.setAttribute("searchStudentList", searchStudentList);

	}

	public void advanceSearchByParents() {

		String queryMain = "From Parents as parents where";
		String fathersname = DataUtil.emptyString(request
				.getParameter("fathersname"));
		String mothersname = DataUtil.emptyString(request
				.getParameter("mothersname"));

		String querySub = "";

		if (!fathersname.equalsIgnoreCase("")) {
			querySub = " parents.fathersname like '%" + fathersname + "%'";
		}

		if (!mothersname.equalsIgnoreCase("") && !querySub.equalsIgnoreCase("")) {
			querySub = querySub + " AND parents.mothersname like '%"
					+ mothersname + "%'";
		} else if (!mothersname.equalsIgnoreCase("")) {
			querySub = querySub + " parents.mothersname like '%" + mothersname
					+ "%'";
		}

		queryMain = queryMain + querySub;
		/*
		 * queryMain =
		 * "FROM Parents as parents where  parents.Student.dateofbirth = '2006-04-06'"
		 * ;
		 */
		System.out.println("SEARCH QUERY ***** " + queryMain);
		List<Parents> searchParentsList = new studentDetailsDAO()
				.getStudentsList(queryMain);
		request.setAttribute("studentList", searchParentsList);

	}

	public void addFeesStamp() {
		String[] studentIds = request.getParameterValues("studentIDs");
		if(studentIds!=null){
		Academicfeesstructure academicfessstructure = new Academicfeesstructure();
		List<Academicfeesstructure> listOfacademicfessstructure = new ArrayList<Academicfeesstructure>();
		Studentfeesstructure studentfeesstructure = new Studentfeesstructure();
		List<Studentfeesstructure> listOfstudentfeesstructure = new ArrayList<Studentfeesstructure>();
		
		String feesTotalAmount = request.getParameter("feesTotalAmount");
		String currentYear = request.getParameter("currentyear");

		String[] feesCategory = request.getParameterValues("feesNames");
		String[] feesAmount = request.getParameterValues("fessFullCat");

    
		System.out.println("Current Academic Year is " + currentYear);
		List ids = new ArrayList();
		listOfacademicfessstructure.clear();
		for (String id : studentIds) {
			System.out.println("id" + id);
			academicfessstructure = new Academicfeesstructure();
			academicfessstructure.setSid(Integer.valueOf(id));
			academicfessstructure.setAcademicyear(currentYear);
			academicfessstructure.setTotalfees(feesTotalAmount);
			listOfacademicfessstructure.add(academicfessstructure);
			// ids.add(Integer.valueOf(id));

		}
		
		listOfstudentfeesstructure.clear();
		
		
		
		for (String id : studentIds) {

			for(int i=0; i < feesCategory.length ; i++){
				int idint = Integer.valueOf(id);
			studentfeesstructure.setSid(idint);
			studentfeesstructure.setFeescategory(feesCategory[i]);
			studentfeesstructure.setFeesamount(feesAmount[i]);
			studentfeesstructure.setAcademicyear(currentYear);
			System.out.println("feesId :" + id);
			System.out.println("feesCategory :" + feesCategory[i]);
			System.out.println("fees amount :" + feesAmount[i]);
			
			listOfstudentfeesstructure.add(studentfeesstructure);
			studentfeesstructure = new Studentfeesstructure();
		}
			

			
		}
			
		System.out.println("id length" + studentIds.length);
		new StampFeesDAO().addStampFees(listOfacademicfessstructure,currentYear);
		
		
		new studentDetailsDAO().addStudentfeesstructure(listOfstudentfeesstructure,currentYear);
		}
	}

	public void deleteFeesStamp() {
		String currentYear = request.getParameter("currentyear");
		String[] studentIds = request.getParameterValues("studentIDs");
		if(studentIds!=null){
			List ids = new ArrayList();
	        for (String id : studentIds) {
	            System.out.println("id" + id);
	            ids.add(Integer.valueOf(id));
	            
	        }
	        System.out.println("id length" + studentIds.length);
	        new StampFeesDAO().deleteMultiple(ids,currentYear);
		
	}
		}

}
