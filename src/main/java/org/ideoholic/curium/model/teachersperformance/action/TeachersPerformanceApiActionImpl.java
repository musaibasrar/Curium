package org.ideoholic.curium.model.teachersperformance.action;

import org.ideoholic.curium.dto.ResultResponse;
import org.ideoholic.curium.exceptions.CustomErrorMessage;
import org.ideoholic.curium.exceptions.CustomResponseException;
import org.ideoholic.curium.model.examdetails.dto.ExamsListResponseDto;
import org.ideoholic.curium.model.examdetails.service.ExamDetailsService;
import org.ideoholic.curium.model.std.service.StandardService;
import org.ideoholic.curium.model.studentdiary.dto.TeacherDetailResponseDto;
import org.ideoholic.curium.model.subjectdetails.dto.SubjectsResponseDto;
import org.ideoholic.curium.model.subjectdetails.service.SubjectDetailsService;
import org.ideoholic.curium.model.teachersperformance.dto.ExamSubectResponseDto;
import org.ideoholic.curium.model.teachersperformance.dto.TeacherDetailsDto;
import org.ideoholic.curium.model.teachersperformance.service.TeacherPerformanceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TeachersPerformanceApiActionImpl implements TeachersPerformanceApiAction {

	
	 @Autowired
     private TeacherPerformanceService teacherPerformanceService;
	
	 @Autowired
	 private StandardService standardService;
	 
	 @Autowired
	 private SubjectDetailsService subjectDetailsService;
	 
	 @Autowired
	 private ExamDetailsService examDetailsService;

	public ResponseEntity<ExamSubectResponseDto> SearchTeachers(String branchId) {
		ExamSubectResponseDto examSubectResponseDto = new ExamSubectResponseDto();
		ResultResponse result = standardService.viewClasses(branchId);
		if(!result.isSuccess())
		throw new CustomResponseException(CustomErrorMessage.ERROR);
		examSubectResponseDto.copyClassSec(result);
		SubjectsResponseDto subjectsResponseDto = subjectDetailsService.readListOfSubjectNames(branchId);
		if(!subjectsResponseDto.isSuccess())
		throw new CustomResponseException(CustomErrorMessage.ERROR);
		examSubectResponseDto.copySubjectsResponseDto(subjectsResponseDto);
		ExamsListResponseDto examsListResponseDto = examDetailsService.readListOfExams(branchId);
		if(!examsListResponseDto.isSuccess())
		throw new CustomResponseException(CustomErrorMessage.ERROR);
		examSubectResponseDto.copyExamsListResponseDto(examsListResponseDto);
		return ResponseEntity.ok(examSubectResponseDto);
	}
	
	public ResponseEntity<TeacherDetailResponseDto> searchForTeacherDetail(TeacherDetailsDto teacherDetailsDto,String branchId) {
		TeacherDetailResponseDto result = teacherPerformanceService.getDetailofteacher(teacherDetailsDto,branchId);
		return ResponseEntity.ok(result);
	}


}
