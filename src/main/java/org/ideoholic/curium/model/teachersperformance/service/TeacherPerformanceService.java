package org.ideoholic.curium.model.teachersperformance.service;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.ideoholic.curium.model.examdetails.dao.ExamDetailsDAO;
import org.ideoholic.curium.model.examdetails.dto.Exams;
import org.ideoholic.curium.model.marksdetails.dao.MarksDetailsDAO;
import org.ideoholic.curium.model.marksdetails.dto.Marks;
import org.ideoholic.curium.model.parents.dto.Parents;
import org.ideoholic.curium.model.student.dao.StudentDetailsDAO;
import org.ideoholic.curium.model.studentdiary.dto.TeacherDetailResponseDto;
import org.ideoholic.curium.model.subjectdetails.dao.SubjectDetailsDAO;
import org.ideoholic.curium.model.subjectdetails.dto.Subject;
import org.ideoholic.curium.model.subjectdetails.dto.SubjectsResponseDto;
import org.ideoholic.curium.model.teachersperformance.dto.TeacherDetailsDto;
import org.ideoholic.curium.util.SubjectAverage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TeacherPerformanceService {
	 private HttpServletRequest request;
     private HttpServletResponse response;
     private HttpSession httpSession;
     private String BRANCHID = "branchid";
     private String academicyear = "academicyear";

	@Autowired
	private ExamDetailsDAO examDetailsDao;
	
	public SubjectsResponseDto readListOfSubjects(String branchid) {
		SubjectsResponseDto subjectsResponseDto = new SubjectsResponseDto();
	    try {
	    	List<Subject> list = new SubjectDetailsDAO().readAllSubjects(Integer.parseInt(branchid));
	    	subjectsResponseDto.setSubjects(list);
	    	subjectsResponseDto.setSuccess(true);
	    } catch (Exception e) {
	        e.printStackTrace();
	        subjectsResponseDto.setSuccess(false);
	    }
		return subjectsResponseDto;
	}

	
	public TeacherDetailResponseDto getDetailofteacher(TeacherDetailsDto teacherDetailsDto,String branchId) {
		
		TeacherDetailResponseDto teacherDetailResponseDto = new TeacherDetailResponseDto();
		String[] classsec = teacherDetailsDto.getClasssec();
		String subjectDetails = teacherDetailsDto.getSubjectDetails();
		String[] subject = subjectDetails.split("--");
		String AcademicYear = teacherDetailsDto.getAcademicYear();
		List<Parents> searchStudentList = new ArrayList<Parents>();
		List<Exams> examsList = examDetailsDao.readListOfExams(Integer.parseInt(branchId));
		List<SubjectAverage> subjectaverageList = new ArrayList<SubjectAverage>();
		
		
		for (Exams exams : examsList) {
			
			SubjectAverage subjectAverage = new SubjectAverage();
			List<String> classsection = new LinkedList<String>();
			List<Integer> averageMarksScored = new LinkedList<Integer>();
			subjectAverage.setExamName("\""+exams.getExamname()+"\"");
			
			for (String classOne : classsec) {
				String queryMain = "From Parents as parents where";
				String querySub = "";

					querySub = querySub + " parents.student.classstudying = '"
							+ classOne + "' AND parents.student.archive=0 and parents.student.passedout=0 AND parents.student.droppedout=0 and parents.student.leftout=0 AND parents.branchid="+Integer.parseInt(branchId)+" order by parents.student.admissionnumber ASC";

				if(!"".equalsIgnoreCase(querySub)) {
					queryMain = queryMain + querySub;
					searchStudentList = new StudentDetailsDAO().getStudentsList(queryMain);
				}
				
				//get Student Marks of classone
				List<Integer> studentIds = new ArrayList<Integer>();
				for (Parents student : searchStudentList) {
					studentIds.add(student.getStudent().getSid());
				}
				List<Marks> marksList = new  MarksDetailsDAO().readListOfMarksPerSubject(studentIds,Integer.parseInt(subject[0]),exams.getExid());
				int averageMarks = 0;
				float sum = 0;
				int i = marksList.size();
				for (Marks marks : marksList) {
					
				sum= sum + marks.getMarksobtained();
				}
				if(i>0) {
				averageMarks =(int) (sum/i);	
				classsection.add("\""+classOne+"\"");
				averageMarksScored.add(averageMarks);
				}
				//End Student Marks
				
			}
			subjectAverage.setClasssec(classsection);
			subjectAverage.setAverageMarks(averageMarksScored);
			if(!averageMarksScored.isEmpty()) {
				subjectaverageList.add(subjectAverage);
	
			}
					}
		
		teacherDetailResponseDto.setSubjectAverageList(subjectaverageList);
		teacherDetailResponseDto.setSubjectAverageListSize(subjectaverageList.size());
		teacherDetailResponseDto.setSubjectName(subject[1]);
		return teacherDetailResponseDto;
	
	}

	}
