package org.ideoholic.curium.model.diary.service;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.ideoholic.curium.model.diary.dao.diaryDAO;
import org.ideoholic.curium.model.diary.dto.Diary;
import org.ideoholic.curium.model.feescategory.dao.feesCategoryDAO;
import org.ideoholic.curium.model.feescategory.dto.Feescategory;
import org.ideoholic.curium.model.parents.dto.Parents;
import org.ideoholic.curium.model.student.dao.studentDetailsDAO;
import org.ideoholic.curium.model.student.dto.Student;
import org.ideoholic.curium.model.user.dto.Login;
import org.ideoholic.curium.util.DataUtil;
import org.ideoholic.curium.util.DateUtil;

public class Diaryservice {
	  private HttpServletRequest request;
      private HttpServletResponse response;
      private HttpSession httpSession;
      private String BRANCHID = "branchid";
      /**
       * Size of a byte buffer to read/write file
       */
      private static final int BUFFER_SIZE = 4096;
  
  public Diaryservice(HttpServletRequest request, HttpServletResponse response) {
          this.request = request;
 this.response = response;
 this.httpSession = request.getSession();
  }

	public void adddetail() {
		// TODO Auto-generated method stub
		Login login=new Login();
		/*
		 * String Id=login.getUsername(); Student student = new
		 * studentDetailsDAO().readploginUniqueObject(Id);
		 
		String studentcls = student.getClassstudying();
		request.setAttribute("studentcls", studentcls);
		*/
		
	}

	public void addDiary() {
		// TODO Auto-generated method stub
		 Diary diary = new Diary();
         
         if(httpSession.getAttribute(BRANCHID)!=null){
        	 
        	 		String secString = DataUtil.emptyString(request.getParameter("addsec"));
        	 		String classString = request.getParameter("addclass")+"--"+secString;
                 
                 diary.setClasssec(DataUtil.emptyString(classString));
                 diary.setMessage(request.getParameter("messagebody"));
                 diary.setSubject(request.getParameter("subject"));
                 diary.setBranchid(httpSession.getAttribute(BRANCHID).toString());
                 diary.setUserid(Integer.parseInt(httpSession.getAttribute("userloginid").toString()));
                 diary.setAcademicyear(httpSession.getAttribute("currentAcademicYear").toString());
                 diary.setCreateddate(DateUtil.indiandateParser(request.getParameter("createddate")));
                 diary.setEnddate(DateUtil.indiandateParser(request.getParameter("enddate")));
                 diary.setStartdate(DateUtil.indiandateParser(request.getParameter("startdate")));
                 diary =  new diaryDAO().create(diary);
                 }
         }

	public boolean viewDiary() {
		// TODO Auto-generated method stub
		 boolean result = false;
         
         if(httpSession.getAttribute(BRANCHID)!=null){
                 try {
                	 int page = 1;
     				int recordsPerPage = 100;
     				if (!"".equalsIgnoreCase(DataUtil.emptyString(request.getParameter("page")))) {
						page = Integer.parseInt(request.getParameter("page"));
					}
                        List<Object[]> list = new diaryDAO().readListOfObjects((page - 1) * recordsPerPage,
        						recordsPerPage, Integer.parseInt(httpSession.getAttribute(BRANCHID).toString()));
                        
                        List<Diary> diaryDetails = new ArrayList<Diary>();
        	            for(Object[] diaryObject: list){
        	            	Diary diary = new Diary();
        	            	
        	            	diary.setId((Integer)diaryObject[0]);
        	                diary.setClasssec((String)diaryObject[1]);
        	                diary.setAcademicyear((String)diaryObject[2]);
        	                diary.setBranchid((String)diaryObject[3]);
        	                diary.setSubject((String)diaryObject[4]);
        	                diary.setMessage((String)diaryObject[5]);
        	                diary.setStartdate((Date)diaryObject[6]);
        	                diary.setEnddate((Date)diaryObject[7]);
        	                diary.setCreateddate((Date)diaryObject[8]);
        	                diaryDetails.add(diary);
        	            }
                        
                        
                    int noOfRecords = new diaryDAO().getNoOfRecords(Integer.parseInt(httpSession.getAttribute(BRANCHID).toString()));
    				int noOfPages = (int) Math.ceil(noOfRecords * 1.0 / recordsPerPage);
    				request.setAttribute("diary", diaryDetails);
    				request.setAttribute("noOfPages", noOfPages);
    				request.setAttribute("currentPage", page);
                    result = true;
                } catch (Exception e) {
                    e.printStackTrace();
                    result = false;
                }
         }
                       return result;
	}
//viewDiaryparent

	
	  public boolean viewDiaryParent() {
		return viewDiaryparent(request.getParameter("id"));
	}
	  public boolean viewDiaryparent(String studentId) {
		 boolean result = false;
         
         if(httpSession.getAttribute(BRANCHID)!=null){
                 try {
			         Student student = new studentDetailsDAO().readploginUniqueObject(studentId);
			         String classsec = student.getClassstudying();
                	 int page = 1;
     				int recordsPerPage = 20;
     				if (!"".equalsIgnoreCase(DataUtil.emptyString(request.getParameter("page")))) {
						page = Integer.parseInt(request.getParameter("page"));
					}
                        List<Object[]> list = new diaryDAO().readListOfParentObjects((page - 1) * recordsPerPage,
        						recordsPerPage, Integer.parseInt(httpSession.getAttribute(BRANCHID).toString()),classsec);
                        
                        List<Diary> diaryDetails = new ArrayList<Diary>();
        	            for(Object[] diaryObject: list){
        	            	Diary diary = new Diary();
        	            	
        	            	diary.setId((Integer)diaryObject[0]);
        	                diary.setClasssec((String)diaryObject[1]);
        	                diary.setAcademicyear((String)diaryObject[2]);
        	                diary.setBranchid((String)diaryObject[3]);
        	                diary.setSubject((String)diaryObject[4]);
        	                diary.setMessage((String)diaryObject[5]);
        	                diary.setStartdate((Date)diaryObject[6]);
        	                diary.setEnddate((Date)diaryObject[7]);
        	                diary.setCreateddate((Date)diaryObject[8]);
        	                diaryDetails.add(diary);
        	            }
                        
                        
                    int noOfRecords = new diaryDAO().getNoOfRecords(Integer.parseInt(httpSession.getAttribute(BRANCHID).toString()));
    				int noOfPages = (int) Math.ceil(noOfRecords * 1.0 / recordsPerPage);
    				request.setAttribute("diaryparents", diaryDetails);
    				request.setAttribute("noOfPages", noOfPages);
    				request.setAttribute("currentPage", page);
                    result = true;
                } catch (Exception e) {
                    e.printStackTrace();
                    result = false;
                }
         }
                       return result;
	}
	public void deleteRecord() {
		// TODO Auto-generated method stub
		String[] iddiary = request.getParameterValues("id");
        if(iddiary!=null){
       List<Integer> ids = new ArrayList();
       for (String id : iddiary) {
           System.out.println("id" + id);
           ids.add(Integer.valueOf(id));
       }
       new diaryDAO().deleteRecord(ids);
        }
	}

	public boolean viewDetailsOfDiaryMessage() {
		return viewDetailsOfDiaryMessage(request.getParameter("id").toString());
		
	}
	
	public boolean viewDetailsOfDiaryMessage(String studentId) {
		boolean result = false;
		long id = Long.parseLong(studentId);
		Diary diary = new diaryDAO().getMessage(id);
		httpSession.setAttribute("diary", diary);
		result = true;
		return result;
	}	
	}


