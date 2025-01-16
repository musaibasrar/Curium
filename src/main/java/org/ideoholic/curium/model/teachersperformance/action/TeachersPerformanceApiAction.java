package org.ideoholic.curium.model.teachersperformance.action;

import org.ideoholic.curium.model.studentdiary.dto.TeacherDetailResponseDto;
import org.ideoholic.curium.model.teachersperformance.dto.ExamSubectResponseDto;
import org.ideoholic.curium.model.teachersperformance.dto.TeacherDetailsDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/api/v1/teachersPerformanceProcess")
public interface TeachersPerformanceApiAction {

	@GetMapping("/searchTeachers")
	public ResponseEntity<ExamSubectResponseDto> SearchTeachers(@RequestHeader(value = "branchid") String branchId);
	
	@PostMapping("/searchForTeacherDetail")
	public ResponseEntity<TeacherDetailResponseDto> searchForTeacherDetail(@RequestBody TeacherDetailsDto teacherDetailsDto,@RequestHeader(value = "branchid") String branchId);


}
