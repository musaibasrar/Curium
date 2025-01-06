package org.ideoholic.curium.model.studentdiary.action;

import org.ideoholic.curium.exceptions.CustomErrorMessage;
import org.ideoholic.curium.exceptions.CustomResponseException;
import org.ideoholic.curium.model.diary.dto.DairyIdsDto;
import org.ideoholic.curium.model.diary.dto.DiaryDetailsMessageResponseDto;
import org.ideoholic.curium.model.diary.dto.DiaryResponseDto;
import org.ideoholic.curium.model.diary.service.DiaryService;
import org.ideoholic.curium.model.parents.dto.ParentListResponseDto;
import org.ideoholic.curium.model.student.dto.StudentIdDto;
import org.ideoholic.curium.model.student.dto.StudentIdPageDto;
import org.ideoholic.curium.model.student.service.StudentService;
import org.ideoholic.curium.model.studentdiary.dto.AddStudentDiaryDto;
import org.ideoholic.curium.model.studentdiary.service.StudentDiaryservice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class StudentDiaryApiActionImpl implements StudentDiaryApiAction {
	@Autowired
	private DiaryService diaryService;
	@Autowired
	private StudentDiaryservice studentDiaryservice;
	@Autowired
	private StudentService studentService;

	public ResponseEntity<ParentListResponseDto> getdiarystudent(String page, String branchId) {
		ParentListResponseDto result = studentService.viewAllStudentsParents(page, branchId);
		return ResponseEntity.ok(result);
	}

	public ResponseEntity addDiary(AddStudentDiaryDto addStudentDiaryDto, String branchId, String currentAcademicYear,
			String userLoginId) {
		studentDiaryservice.addDiary(addStudentDiaryDto, branchId, currentAcademicYear, userLoginId);
		return ResponseEntity.ok().build();

	}

	public ResponseEntity<DiaryResponseDto> viewdiarystudent(String branchId, String page) {
		DiaryResponseDto result = studentDiaryservice.viewDiary(branchId, page);
		return ResponseEntity.ok(result);
	}

	public ResponseEntity<DiaryResponseDto> viewDiaryStudentParentGet(String page, String studentId, String branchId) {
		StudentIdPageDto studentIdPageDto = new StudentIdPageDto();
		studentIdPageDto.setPage(page);
		studentIdPageDto.setStudentId(studentId);

		DiaryResponseDto result = studentDiaryservice.viewDiaryParent(studentIdPageDto, branchId);
		return ResponseEntity.ok(result);
	}

	public ResponseEntity<DiaryResponseDto> viewDiaryStudentParentPost(StudentIdPageDto studentIdPageDto,
			String branchId) {
		DiaryResponseDto result = studentDiaryservice.viewDiaryParent(studentIdPageDto, branchId);
		return ResponseEntity.ok(result);
	}

	public ResponseEntity<DiaryResponseDto> deleteRecord(DairyIdsDto dairyIdsDto, String page, String branchId) {
		studentDiaryservice.deleteRecord(dairyIdsDto);
		DiaryResponseDto result = studentDiaryservice.viewDiary(page, branchId);
		return ResponseEntity.ok(result);
	}

	public ResponseEntity<String> diarySaved() {
		return ResponseEntity.ok("viewdiarystudent");
	}

	public ResponseEntity<DiaryDetailsMessageResponseDto> ViewDiaryDetails(StudentIdDto studentIdDto) {
		DiaryDetailsMessageResponseDto result = diaryService.viewDetailsOfDiaryMessage(studentIdDto);
		if (result.isSuccess()) {
			return ResponseEntity.ok(result);
		} else {
			throw new CustomResponseException(CustomErrorMessage.ERROR);
		}
	}

	public ResponseEntity<DiaryDetailsMessageResponseDto> ViewDiaryDetailsParent(StudentIdDto studentIdDto) {
		DiaryDetailsMessageResponseDto result = diaryService.viewDetailsOfDiaryMessage(studentIdDto);
		if (result.isSuccess()) {
			return ResponseEntity.ok(result);
		}
		throw new CustomResponseException(CustomErrorMessage.ERROR);
	}

}
