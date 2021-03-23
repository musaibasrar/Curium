package com.model.stampfees.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.model.feescategory.dto.Feescategory;
import com.model.mess.card.dto.Card;
import com.model.parents.dto.Parents;
import com.model.stampfees.dao.StampFeesDAO;
import com.model.stampfees.dto.Academicfeesstructure;
import com.model.student.dao.studentDetailsDAO;
import com.model.student.dto.Studentfeesstructure;
import com.util.DataUtil;
import com.util.DateUtil;

public class StampFeesService {

	HttpServletRequest request;
	HttpServletResponse response;
	HttpSession httpSession;
	private String CURRENTACADEMICYEAR = "currentAcademicYear";
	private String BRANCHID = "branchid";
	private String USERID = "userloginid";

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
		String studentname = DataUtil.emptyString(request.getParameter("namesearch"));
		String addClass = request.getParameter("classsearch");
		String addSec = request.getParameter("secsearch");
		String breakfast = DataUtil.emptyString(request.getParameter("breakfast"));
		String lunch = DataUtil.emptyString(request.getParameter("lunch"));
		String dinner = DataUtil.emptyString(request.getParameter("dinner"));
		
		String conClassStudying = "";

		if (!addClass.equalsIgnoreCase("")) {
			conClassStudying = addClass+"--"+"%";
		}
		if (!addSec.equalsIgnoreCase("")) {
			conClassStudying = addClass;
			conClassStudying = conClassStudying+"--"+addSec+"%";
		}

		String classStudying = DataUtil.emptyString(conClassStudying);
		String querySub = "";

		if (!studentname.equalsIgnoreCase("")) {
			querySub = " parents.Student.name like '%" + studentname + "%' AND ";
		}
		
		if (!classStudying.equalsIgnoreCase("")) {
			querySub = querySub + " parents.Student.classstudying like '"+ classStudying + "' AND ";
		}
		
		if (!breakfast.equalsIgnoreCase("") || !lunch.equalsIgnoreCase("") || !dinner.equalsIgnoreCase("")) {
			
			if(breakfast.equalsIgnoreCase("")) {
				querySub = " parents.Student.breakfast is NULL AND ";
			}else {
				querySub = " parents.Student.breakfast = '" + breakfast + "' AND ";
			}
			
			if(lunch.equalsIgnoreCase("")) {
				querySub = querySub+" parents.Student.lunch is NULL AND ";
			}else {
				querySub = querySub+" parents.Student.lunch = '" + lunch + "' AND ";
			}
			
			if(dinner.equalsIgnoreCase("")) {
				querySub = querySub+" parents.Student.dinner is NULL AND ";
			}else {
				querySub = querySub+" parents.Student.dinner = '" + dinner + "' AND ";
			}
			
		}

		if(!"".equalsIgnoreCase(querySub)) {
			queryMain = queryMain + querySub + "parents.Student.archive=0 and parents.Student.passedout=0 AND parents.Student.droppedout=0 and parents.Student.leftout=0 AND parents.branchid="+Integer.parseInt(httpSession.getAttribute(BRANCHID).toString());
			searchStudentList = new studentDetailsDAO().getStudentsList(queryMain);
		}
		
	}
		request.setAttribute("searchStudentList", searchStudentList);

	}
	
	public void multiClassSearch() {

		List<Parents> searchStudentList = new ArrayList<Parents>();
		
		if(httpSession.getAttribute(BRANCHID)!=null){
		
		String queryMain = "From Parents as parents where ";
		String studentname = DataUtil.emptyString(request.getParameter("namesearch"));
		String[] addClass = request.getParameterValues("classsearch");
		String addClassAll = request.getParameter("classsearchall");
		//String addSec = request.getParameter("secsearch");
		StringBuffer conClassStudying = new StringBuffer();

			int i = 0;
			if (addClass != null) {
				for (String classOne : addClass) {
					
					if(i>0) {
						conClassStudying.append("' OR parents.Student.classstudying LIKE '"+classOne+"--"+"%");
					}else {
						conClassStudying.append(classOne+"--"+"%");
					}
					
					i++;
				}
			}
			
			
		
		/*if (!addSec.equalsIgnoreCase("")) {
			//conClassStudying = addClass;
			conClassStudying = conClassStudying+"--"+addSec+"%";
		}*/

		String classStudying = DataUtil.emptyString(conClassStudying.toString());
		String querySub = "";

		if (!studentname.equalsIgnoreCase("")) {
			querySub = " parents.Student.name like '%" + studentname + "%' AND parents.Student.archive=0 and parents.Student.passedout=0 AND parents.Student.droppedout=0 and parents.Student.leftout=0 AND parents.branchid="+Integer.parseInt(httpSession.getAttribute(BRANCHID).toString());
		}
		
		if("ALL".equalsIgnoreCase(addClassAll)) {
			
			if (!querySub.equalsIgnoreCase("")) {
				querySub = querySub + " AND parents.Student.archive=0 and parents.Student.passedout=0 AND parents.Student.droppedout=0 and parents.Student.leftout=0";
			} else{
				querySub = querySub + "parents.Student.archive=0 and parents.Student.passedout=0 AND parents.Student.droppedout=0 and parents.Student.leftout=0 AND parents.branchid="+Integer.parseInt(httpSession.getAttribute(BRANCHID).toString())+" order by parents.Student.admissionnumber ASC";
			}
			
		}else {
			
			if (!classStudying.equalsIgnoreCase("")	&& !querySub.equalsIgnoreCase("")) {
				querySub = querySub + " AND parents.Student.classstudying like '"
						+ classStudying + "' AND parents.Student.archive=0 and parents.Student.passedout=0 AND parents.Student.droppedout=0 and parents.Student.leftout=0";
			} else if (!classStudying.equalsIgnoreCase("")) {
				querySub = querySub + " parents.Student.classstudying like '"
						+ classStudying + "' AND parents.Student.archive=0 and parents.Student.passedout=0 AND parents.Student.droppedout=0 and parents.Student.leftout=0 AND parents.branchid="+Integer.parseInt(httpSession.getAttribute(BRANCHID).toString())+" order by parents.Student.admissionnumber ASC";
			}
		}

		

		if(!"".equalsIgnoreCase(querySub)) {
			queryMain = queryMain + querySub;
			searchStudentList = new studentDetailsDAO().getStudentsList(queryMain);
		}
		
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
		List<Studentfeesstructure> listOfstudentfeesstructure = new ArrayList<Studentfeesstructure>();
		List<Card> cardList = new ArrayList<Card>();
		
		String feesTotalAmount = request.getParameter("feesTotalAmount");

		String[] feesCategoryIds = request.getParameterValues("feesIDS");
		String[] feesAmount = request.getParameterValues("fessFullCat");
		String[] concession = request.getParameterValues("feesConcession");
		Date validFrom = DateUtil.indiandateParser(request.getParameter("validfrom"));
		Date validTill = DateUtil.indiandateParser(request.getParameter("validtill"));
		
		List ids = new ArrayList();
		listOfacademicfessstructure.clear();
		for (String id : studentIds) {
			System.out.println("id" + id);
			academicfessstructure = new Academicfeesstructure();
			academicfessstructure.setSid(Integer.valueOf(id));
			academicfessstructure.setAcademicyear(httpSession.getAttribute(CURRENTACADEMICYEAR).toString());
			academicfessstructure.setTotalfees(feesTotalAmount);
			academicfessstructure.setBranchid(Integer.parseInt(httpSession.getAttribute(BRANCHID).toString()));
			academicfessstructure.setUserid(Integer.parseInt(httpSession.getAttribute(USERID).toString()));
			listOfacademicfessstructure.add(academicfessstructure);
			// ids.add(Integer.valueOf(id));
			
			//Mess Card
			Card cardDetails = new Card();
			cardDetails.setSid(Integer.valueOf(id));
			cardDetails.setValidfrom(validFrom);
			cardDetails.setValidto(validTill);
			cardDetails.setUserid(Integer.parseInt(httpSession.getAttribute(USERID).toString()));
			cardList.add(cardDetails);
			//End Mess Card

		}
		
		for (String id : studentIds) {

			for(int i=0; i < feesCategoryIds.length ; i++){
			
			Studentfeesstructure studentfeesstructure = new Studentfeesstructure();   
			Feescategory feescategory = new Feescategory();
			studentfeesstructure.setSid(Integer.valueOf(id));
			feescategory.setIdfeescategory(Integer.parseInt(feesCategoryIds[i]));
			studentfeesstructure.setFeescategory(feescategory);
			studentfeesstructure.setFeesamount(Long.parseLong(feesAmount[i]));
			studentfeesstructure.setFeespaid((long) 0);
			studentfeesstructure.setAcademicyear(httpSession.getAttribute(CURRENTACADEMICYEAR).toString());
			studentfeesstructure.setBranchid(Integer.parseInt(httpSession.getAttribute(BRANCHID).toString()));
			studentfeesstructure.setConcession(Integer.parseInt(concession[i]));
			studentfeesstructure.setUserid(Integer.parseInt(httpSession.getAttribute(USERID).toString()));
			listOfstudentfeesstructure.add(studentfeesstructure);
		}
			

			
		}
		
		new StampFeesDAO().addStampFees(listOfacademicfessstructure,httpSession.getAttribute(CURRENTACADEMICYEAR).toString(), cardList);
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
	
	
	public Academicfeesstructure stampAcademicFessStructure(String feesTotalAmount) {

			Academicfeesstructure academicFessStructure = new Academicfeesstructure();
		
			if(httpSession.getAttribute(CURRENTACADEMICYEAR)!=null){
				
					academicFessStructure.setSid(1);
					academicFessStructure.setAcademicyear(httpSession.getAttribute(CURRENTACADEMICYEAR).toString());
					academicFessStructure.setTotalfees(feesTotalAmount);
					academicFessStructure.setBranchid(Integer.parseInt(httpSession.getAttribute(BRANCHID).toString()));
					academicFessStructure.setUserid(Integer.parseInt(httpSession.getAttribute(USERID).toString()));
				}
			
			return academicFessStructure;
	}
	
	
	public Studentfeesstructure stampStudentFeesStructure(int feesCategoryId, Long feesAmount) {

		Studentfeesstructure studentfeesstructure = new Studentfeesstructure();   
		
		if(httpSession.getAttribute(CURRENTACADEMICYEAR)!=null){
				
				Feescategory feescategory = new Feescategory();
				studentfeesstructure.setSid(0);
				feescategory.setIdfeescategory(feesCategoryId);
				studentfeesstructure.setFeescategory(feescategory);
				studentfeesstructure.setFeesamount(feesAmount);
				studentfeesstructure.setFeespaid((long) 0);
				studentfeesstructure.setAcademicyear(httpSession.getAttribute(CURRENTACADEMICYEAR).toString());
				studentfeesstructure.setBranchid(Integer.parseInt(httpSession.getAttribute(BRANCHID).toString()));
				studentfeesstructure.setUserid(Integer.parseInt(httpSession.getAttribute(USERID).toString()));
				studentfeesstructure.setConcession(0);
			
			}
		
				return studentfeesstructure;
	}
}