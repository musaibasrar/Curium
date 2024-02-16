package org.ideoholic.curium.model.studentdiary.service;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.ideoholic.curium.model.student.dao.studentDetailsDAO;
import org.ideoholic.curium.model.student.dto.Student;
import org.ideoholic.curium.model.studentdiary.dao.StudentDiaryDAO;
import org.ideoholic.curium.model.studentdiary.dto.StudentDiary;
import org.ideoholic.curium.model.studentdiary.dto.StudentDiaryDTO;
import org.ideoholic.curium.util.DataUtil;
import org.ideoholic.curium.util.DateUtil;

public class StudentDiaryservice {
	  private HttpServletRequest request;
      private HttpServletResponse response;
      private HttpSession httpSession;
      private String BRANCHID = "branchid";
      /**
       * Size of a byte buffer to read/write file
       */
      private static final int BUFFER_SIZE = 4096;
  
  public StudentDiaryservice(HttpServletRequest request, HttpServletResponse response) {
          this.request = request;
 this.response = response;
 this.httpSession = request.getSession();
  }

	public void addDiary() {
		// TODO Auto-generated method stub
		 StudentDiary diary = new StudentDiary();
         
         if(httpSession.getAttribute(BRANCHID)!=null){
        	 
        	 diary.setSid(Integer.parseInt(request.getParameter("studentId")));
             diary.setClasssec(request.getParameter("classandsec"));
             diary.setMessage(request.getParameter("messagebody"));
             diary.setSubject(request.getParameter("subject"));
             diary.setBranchid(Integer.parseInt(httpSession.getAttribute(BRANCHID).toString()));
             diary.setUserid(Integer.parseInt(httpSession.getAttribute("userloginid").toString()));
             diary.setAcademicyear(httpSession.getAttribute("currentAcademicYear").toString());
             diary.setCreateddate(DateUtil.indiandateParser(request.getParameter("createddate")));
             diary =  new StudentDiaryDAO().create(diary);
        	 
        	 		String secString = DataUtil.emptyString(request.getParameter("addsec"));
        	 		String classString = request.getParameter("addclass")+"--"+secString;
                 
                 diary.setClasssec(DataUtil.emptyString(classString));
                 diary.setMessage(request.getParameter("messagebody"));
                 diary.setSubject(request.getParameter("subject"));
                 diary.setBranchid(Integer.parseInt(httpSession.getAttribute(BRANCHID).toString()));
                 diary.setUserid(Integer.parseInt(httpSession.getAttribute("userloginid").toString()));
                 diary.setAcademicyear(httpSession.getAttribute("currentAcademicYear").toString());
                 diary.setCreateddate(DateUtil.indiandateParser(request.getParameter("createddate")));
                 diary =  new StudentDiaryDAO().create(diary);
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
                        List<Object[]> list = new StudentDiaryDAO().readListOfObjects((page - 1) * recordsPerPage,
        						recordsPerPage, Integer.parseInt(httpSession.getAttribute(BRANCHID).toString()));
                        
                        List<StudentDiaryDTO> diaryDetails = new ArrayList<StudentDiaryDTO>();
        	            for(Object[] diaryObject: list){
        	            	StudentDiaryDTO diary = new StudentDiaryDTO();
        	            	
        	            	diary.setId((Integer)diaryObject[0]);
        	            	diary.setSid((Integer)diaryObject[1]);
        	            	diary.setStudentName((String)diaryObject[2]);
        	                diary.setClasssec((String)diaryObject[3]);
        	                diary.setAcademicyear((String)diaryObject[4]);
        	                diary.setBranchid((Integer)diaryObject[5]);
        	                diary.setSubject((String)diaryObject[6]);
        	                diary.setMessage((String)diaryObject[7]);
        	                diary.setCreateddate((Date)diaryObject[8]);
        	                diary.setUserid((Integer)diaryObject[9]);
        	                diaryDetails.add(diary);
        	            }
                        
                        
                    int noOfRecords = new StudentDiaryDAO().getNoOfRecords(Integer.parseInt(httpSession.getAttribute(BRANCHID).toString()));
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
     				int recordsPerPage = 100;
     				if (!"".equalsIgnoreCase(DataUtil.emptyString(request.getParameter("page")))) {
						page = Integer.parseInt(request.getParameter("page"));
					}
                        List<Object[]> list = new StudentDiaryDAO().readListOfParentObjects((page - 1) * recordsPerPage,
        						recordsPerPage, Integer.parseInt(httpSession.getAttribute(BRANCHID).toString()),student.getSid());
                        
                        List<StudentDiaryDTO> diaryDetails = new ArrayList<StudentDiaryDTO>();
        	            for(Object[] diaryObject: list){
        	            	StudentDiaryDTO diary = new StudentDiaryDTO();
        	            	
        	            	diary.setId((Integer)diaryObject[0]);
        	            	diary.setSid((Integer)diaryObject[1]);
        	            	diary.setStudentName((String)diaryObject[2]);
        	                diary.setClasssec((String)diaryObject[3]);
        	                diary.setAcademicyear((String)diaryObject[4]);
        	                diary.setBranchid((Integer)diaryObject[5]);
        	                diary.setSubject((String)diaryObject[6]);
        	                diary.setMessage((String)diaryObject[7]);
        	                diary.setCreateddate((Date)diaryObject[8]);
        	                diary.setUserid((Integer)diaryObject[9]);
        	                diaryDetails.add(diary);
        	            }
                        
                        
                        
                    int noOfRecords = new StudentDiaryDAO().getNoOfRecords(Integer.parseInt(httpSession.getAttribute(BRANCHID).toString()),student.getSid());
    				int noOfPages = (int) Math.ceil(noOfRecords * 1.0 / recordsPerPage);
    				request.setAttribute("studentdiaryparents", diaryDetails);
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
       new StudentDiaryDAO().deleteRecord(ids);
        }
	}

	public boolean viewDetailsOfDiaryMessage() {
		return viewDetailsOfDiaryMessage(request.getParameter("id").toString());
		
	}
	
	public boolean viewDetailsOfDiaryMessage(String studentId) {
		boolean result = false;
		long id = Long.parseLong(studentId);
		StudentDiary diary = new StudentDiaryDAO().getMessage(id);
		httpSession.setAttribute("studentdiary", diary);
		result = true;
		return result;
	}	
	}


