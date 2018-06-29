package com.model.stampfees.service;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.model.feescategory.dto.Feescategory;
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
	private String CURRENTACADEMICYEAR = "currentAcademicYear";
	private String BRANCHID = "branchid";

	public StampFeesService(HttpServletRequest request,
			HttpServletResponse response) {
		this.request = request;
		this.response = response;
		this.httpSession = request.getSession();
	}

	public void advanceSearch() {
		List<Parents> searchStudentList = new ArrayList<Parents>();
		
		if(httpSession.getAttribute(BRANCHID)!=null){
		
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
			querySub = " parents.Student.name like '%" + studentname + "%' AND parents.Student.archive=0 AND parents.branchid="+Integer.parseInt(httpSession.getAttribute(BRANCHID).toString());
		}

		if (!classStudying.equalsIgnoreCase("")
				&& !querySub.equalsIgnoreCase("")) {
			querySub = querySub + " AND parents.Student.classstudying like '"
					+ classStudying + "' AND parents.Student.archive=0";
		} else if (!classStudying.equalsIgnoreCase("")) {
			querySub = querySub + " parents.Student.classstudying like '"
					+ classStudying + "' AND parents.Student.archive=0 AND parents.branchid="+Integer.parseInt(httpSession.getAttribute(BRANCHID).toString());
		}

		queryMain = queryMain + querySub;
		/*
		 * queryMain =
		 * "FROM Parents as parents where  parents.Student.dateofbirth = '2006-04-06'"
		 * ;
		 */
		System.out.println("SEARCH QUERY ***** " + queryMain);
		searchStudentList = new studentDetailsDAO().getStudentsList(queryMain);
		
	}
		request.setAttribute("searchStudentList", searchStudentList);

	}

	public void advanceSearchByParents() {

		List<Parents> searchParentsList = new ArrayList<Parents>();
		
		if(httpSession.getAttribute(BRANCHID)!=null){
			String queryMain = "From Parents as parents where parents.branchid="+Integer.parseInt(httpSession.getAttribute(BRANCHID).toString());
			String fathersname = DataUtil.emptyString(request
					.getParameter("fathersname"));
			String mothersname = DataUtil.emptyString(request
					.getParameter("mothersname"));

			String querySub = "";

			if (!fathersname.equalsIgnoreCase("")) {
				querySub = "AND parents.fathersname like '%" + fathersname + "%'";
			}

			if (!mothersname.equalsIgnoreCase("") && !querySub.equalsIgnoreCase("")) {
				querySub = querySub + " AND parents.mothersname like '%"
						+ mothersname + "%'";
			} else if (!mothersname.equalsIgnoreCase("")) {
				querySub = querySub + "AND parents.mothersname like '%" + mothersname
						+ "%'";
			}

			queryMain = queryMain + querySub;
			/*
			 * queryMain =
			 * "FROM Parents as parents where  parents.Student.dateofbirth = '2006-04-06'"
			 * ;
			 */
			System.out.println("SEARCH QUERY ***** " + queryMain);
			searchParentsList = new studentDetailsDAO()
					.getStudentsList(queryMain);
		}
		
		request.setAttribute("studentList", searchParentsList);

	}

	public void addFeesStamp() {
		
		if(httpSession.getAttribute(CURRENTACADEMICYEAR)!=null){
		String[] studentIds = request.getParameterValues("studentIDs");
		if(studentIds!=null){
		Academicfeesstructure academicfessstructure = new Academicfeesstructure();
		List<Academicfeesstructure> listOfacademicfessstructure = new ArrayList<Academicfeesstructure>();
		Studentfeesstructure studentfeesstructure = new Studentfeesstructure();
		List<Studentfeesstructure> listOfstudentfeesstructure = new ArrayList<Studentfeesstructure>();
		
		String feesTotalAmount = request.getParameter("feesTotalAmount");

		String[] feesCategoryIds = request.getParameterValues("feesIDS");
		String[] feesAmount = request.getParameterValues("fessFullCat");
		String[] concession = request.getParameterValues("feesConcession");
		
		List ids = new ArrayList();
		listOfacademicfessstructure.clear();
		for (String id : studentIds) {
			System.out.println("id" + id);
			academicfessstructure = new Academicfeesstructure();
			academicfessstructure.setSid(Integer.valueOf(id));
			academicfessstructure.setAcademicyear(httpSession.getAttribute(CURRENTACADEMICYEAR).toString());
			academicfessstructure.setTotalfees(feesTotalAmount);
			academicfessstructure.setBranchid(Integer.parseInt(httpSession.getAttribute(BRANCHID).toString()));
			listOfacademicfessstructure.add(academicfessstructure);
			// ids.add(Integer.valueOf(id));

		}
		
		for (String id : studentIds) {

			for(int i=0; i < feesCategoryIds.length ; i++){
			    
			Feescategory feescategory = new Feescategory();
			studentfeesstructure.setSid(Integer.valueOf(id));
			feescategory.setIdfeescategory(Integer.parseInt(feesCategoryIds[i]));
			studentfeesstructure.setFeescategory(feescategory);
			studentfeesstructure.setFeesamount(Long.parseLong(feesAmount[i]));
			studentfeesstructure.setAcademicyear(httpSession.getAttribute(CURRENTACADEMICYEAR).toString());
			studentfeesstructure.setBranchid(Integer.parseInt(httpSession.getAttribute(BRANCHID).toString()));
			studentfeesstructure.setConcession(Integer.parseInt(concession[i]));
			listOfstudentfeesstructure.add(studentfeesstructure);
			studentfeesstructure = new Studentfeesstructure();
		}
			

			
		}
		
		new StampFeesDAO().addStampFees(listOfacademicfessstructure,httpSession.getAttribute(CURRENTACADEMICYEAR).toString());
		new studentDetailsDAO().addStudentfeesstructure(listOfstudentfeesstructure,httpSession.getAttribute(CURRENTACADEMICYEAR).toString());

		}
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
