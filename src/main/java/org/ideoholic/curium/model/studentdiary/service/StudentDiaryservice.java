package org.ideoholic.curium.model.studentdiary.service;

import java.util.ArrayList;
import java.util.List;

import org.ideoholic.curium.model.diary.dto.DairyIdsDto;
import org.ideoholic.curium.model.diary.dto.DiaryDetailsMessageResponseDto;
import org.ideoholic.curium.model.diary.dto.DiaryResponseDto;
import org.ideoholic.curium.model.student.dao.StudentDetailsDAO;
import org.ideoholic.curium.model.student.dto.Student;
import org.ideoholic.curium.model.student.dto.StudentIdDto;
import org.ideoholic.curium.model.student.dto.StudentIdPageDto;
import org.ideoholic.curium.model.studentdiary.dao.StudentDiaryDAO;
import org.ideoholic.curium.model.studentdiary.dto.AddStudentDiaryDto;
import org.ideoholic.curium.model.studentdiary.dto.StudentDiary;
import org.ideoholic.curium.model.studentdiary.dto.StudentDiaryDTO;
import org.ideoholic.curium.model.studentdiary.dto.StudentDiaryProjection;
import org.ideoholic.curium.util.DataUtil;
import org.ideoholic.curium.util.DateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StudentDiaryservice {
	
	@Autowired
	private StudentDiaryDAO studentDiaryDAO;
	
	@Autowired
	private StudentDetailsDAO studentDetailsDao;
	  
      /**
       * Size of a byte buffer to read/write file
       */
      private static final int BUFFER_SIZE = 4096;
  

	public void addDiary(AddStudentDiaryDto addStudentDiaryDto,String branchId,String userLoginId,String currentAcademicYear) {
		 StudentDiary diary = new StudentDiary();
         
         if(branchId!=null){
        	 
        	 diary.setSid(Integer.parseInt(addStudentDiaryDto.getStudentId()));
             diary.setClasssec(addStudentDiaryDto.getClassAndSec());
             diary.setMessage(addStudentDiaryDto.getMessageBody());
             diary.setSubject(addStudentDiaryDto.getSubject());
             diary.setBranchid(Integer.parseInt(branchId));
             diary.setUserid(Integer.parseInt(userLoginId));
             diary.setAcademicyear(currentAcademicYear);
             diary.setCreateddate(DateUtil.indiandateParser(addStudentDiaryDto.getCreatedDate()));
             diary =  studentDiaryDAO.create(diary);
                 }
         }

	public DiaryResponseDto viewDiary(String strPage, String branchId) {
		DiaryResponseDto diaryResponseDto = new DiaryResponseDto();
         
         if(branchId!=null){
                 try {
                	 int page = 1;
     				int recordsPerPage = 100;
     				if (!"".equalsIgnoreCase(DataUtil.emptyString(strPage))) {
						page = Integer.parseInt(strPage);
					}
                        List<StudentDiaryProjection> list = studentDiaryDAO.readListOfObjects((page - 1) * recordsPerPage,
        						recordsPerPage, Integer.parseInt(branchId));
                        
                        List<StudentDiaryDTO> diaryDetails = new ArrayList<StudentDiaryDTO>();
        	            for(StudentDiaryProjection diaryObject: list){
        	            	StudentDiaryDTO diary = new StudentDiaryDTO();
        	            	
        	            	diary.setId(diaryObject.getId());
        	            	diary.setSid(diaryObject.getSid());
        	            	diary.setStudentName(diaryObject.getName());
        	                diary.setClasssec(diaryObject.getClasssec());
        	                diary.setAcademicyear(diaryObject.getAcademicyear());
        	                diary.setBranchid(diaryObject.getBranchid());
        	                diary.setSubject(diaryObject.getSubject());
        	                diary.setMessage(diaryObject.getMessage());
        	                diary.setCreateddate(diaryObject.getCreateddate());
        	                diary.setUserid(diaryObject.getUserid());
        	                diaryDetails.add(diary);
        	            }
                        
                        
                    int noOfRecords = studentDiaryDAO.getNoOfRecords(Integer.parseInt(branchId));
    				int noOfPages = (int) Math.ceil(noOfRecords * 1.0 / recordsPerPage);
    				diaryResponseDto.setDiaryDetails(diaryDetails);
    				diaryResponseDto.setNoOfPages(noOfPages);
    				diaryResponseDto.setCurrentPage(page);
    				
    				diaryResponseDto.setSuccess(true);
                } catch (Exception e) {
                    e.printStackTrace();
                    diaryResponseDto.setSuccess(false);
                }
         }
                       return diaryResponseDto;
	}
	
	
//viewDiaryparent
	
		
	public DiaryResponseDto viewDiaryParent(StudentIdPageDto studentIdPageDto, String branchId) {
		DiaryResponseDto diaryResponseDto = new DiaryResponseDto();
         
         if(branchId!=null){
                 try {
			         Student student = studentDetailsDao.readploginUniqueObject(studentIdPageDto.getStudentId());
			         String classsec = student.getClassstudying();
                	 int page = 1;
     				int recordsPerPage = 100;
     				if (!"".equalsIgnoreCase(DataUtil.emptyString(studentIdPageDto.getPage()))) {
						page = Integer.parseInt(studentIdPageDto.getPage());
					}
     				List<StudentDiaryProjection> list =  studentDiaryDAO.readListOfParentObjects((page - 1) * recordsPerPage,
        						recordsPerPage, Integer.parseInt(branchId),student.getSid());
                        
                        List<StudentDiaryDTO> diaryDetails = new ArrayList<StudentDiaryDTO>();
        	            for(StudentDiaryProjection diaryObject: list){
        	            	StudentDiaryDTO diary = new StudentDiaryDTO();
        	            	
        	            	diary.setId(diaryObject.getId());
        	            	diary.setSid(diaryObject.getSid());
        	            	diary.setStudentName(diaryObject.getName());
        	                diary.setClasssec(diaryObject.getClasssec());
        	                diary.setAcademicyear(diaryObject.getAcademicyear());
        	                diary.setBranchid(diaryObject.getBranchid());
        	                diary.setSubject(diaryObject.getSubject());
        	                diary.setMessage(diaryObject.getMessage());
        	                diary.setCreateddate(diaryObject.getCreateddate());
        	                diary.setUserid(diaryObject.getUserid());
        	                diaryDetails.add(diary);
        	            }
                        
                        
                        
                    int noOfRecords = studentDiaryDAO.getNoOfRecords(Integer.parseInt(branchId),student.getSid());
    				int noOfPages = (int) Math.ceil(noOfRecords * 1.0 / recordsPerPage);
    				diaryResponseDto.setDiaryDetails(diaryDetails);
    				diaryResponseDto.setNoOfPages(noOfPages);
    				diaryResponseDto.setCurrentPage(page);
    				diaryResponseDto.setSuccess(true);
                } catch (Exception e) {
                    e.printStackTrace();
                    diaryResponseDto.setSuccess(false);
                }
         }
                       return diaryResponseDto;
	}
	public void deleteRecord(DairyIdsDto dairyIdsDto) {
		String[] iddiary = dairyIdsDto.getIdDiary();
        if(iddiary!=null){
       List<Integer> ids = new ArrayList();
       for (String id : iddiary) {
           System.out.println("id" + id);
           ids.add(Integer.valueOf(id));
       }
       studentDiaryDAO.deleteRecord(ids);
        }
	}

		public DiaryDetailsMessageResponseDto viewDetailsOfDiaryMessage(StudentIdDto studentIdDto) {
		DiaryDetailsMessageResponseDto viewDetailsOfDiaryMessageResponseDto = new DiaryDetailsMessageResponseDto();
		long id = Long.parseLong(studentIdDto.getStudentId());
		StudentDiary diary = studentDiaryDAO.getMessage(id);
		viewDetailsOfDiaryMessageResponseDto.setStudentDiary(diary);
		viewDetailsOfDiaryMessageResponseDto.setSuccess(true);

		return viewDetailsOfDiaryMessageResponseDto;

	}	
	}


