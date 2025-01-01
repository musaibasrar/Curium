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
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class StudentDiaryApiActionImpl implements StudentDiaryApiAction {
	@Autowired
	private DiaryService diaryService;
    @Autowired
    private StudentDiaryservice studentDiaryservice;
    @Autowired
    private StudentService studentService;
	
	@GetMapping("/getdiarystudent")
	public ResponseEntity<ParentListResponseDto> getdiarystudent(@RequestParam(value="page")
	String page,@RequestHeader(value = "branchid") String branchId) {
		ParentListResponseDto result = studentService.viewAllStudentsParents(page,branchId);
		return ResponseEntity.ok(result);
	}
	
	@PostMapping("/addDiary")
	public ResponseEntity addDiary(@RequestBody AddStudentDiaryDto addStudentDiaryDto,
			@RequestHeader(value = "branchid") String branchId,
			@RequestHeader(value = "currentAcademicYear") String currentAcademicYear,
			@RequestHeader(value = "userloginid") String userLoginId) {
		studentDiaryservice.addDiary(addStudentDiaryDto, branchId, userLoginId, currentAcademicYear);
		return ResponseEntity.ok().build();

	}
	
	
	@RequestMapping(value = "/viewdiarystudent", method = { RequestMethod.GET, RequestMethod.POST })
	public ResponseEntity<DiaryResponseDto> viewdiarystudent(@RequestHeader(value = "branchid") String branchId, @RequestParam(value="page")
	String page) {
		DiaryResponseDto result = studentDiaryservice.viewDiary(branchId, page);
		return ResponseEntity.ok(result);
	}
	
	@RequestMapping(value = "/viewDiaryStudentParent", method = { RequestMethod.GET, RequestMethod.POST })
	public ResponseEntity<DiaryResponseDto> viewDiaryStudentParent(@RequestBody StudentIdPageDto studentIdPageDto,
			@RequestHeader(value = "branchid") String branchId) {
		DiaryResponseDto result = studentDiaryservice.viewDiaryParent(studentIdPageDto, branchId);
		return ResponseEntity.ok(result);
	}
	@PostMapping("/deleteRecord")
	public ResponseEntity<DiaryResponseDto>  deleteRecord(@RequestBody DairyIdsDto dairyIdsDto,@RequestParam(value="page")
	String page,
	@RequestHeader(value = "branchid") String branchId) {
		studentDiaryservice.deleteRecord(dairyIdsDto);
		DiaryResponseDto result = studentDiaryservice.viewDiary(page,branchId);
		return ResponseEntity.ok(result);
	}
	@PostMapping("/diarySaved")
	public ResponseEntity<String> diarySaved() {
		return ResponseEntity.ok("viewdiarystudent");
	}
	@PostMapping("/ViewDiaryDetails")
	public ResponseEntity<DiaryDetailsMessageResponseDto> ViewDiaryDetails(@RequestBody StudentIdDto studentIdDto) {
		DiaryDetailsMessageResponseDto result = diaryService.viewDetailsOfDiaryMessage(studentIdDto);
		if(result.isSuccess()) {
			return ResponseEntity.ok(result);
		}
		else {
		throw new CustomResponseException(CustomErrorMessage.ERROR);
		}
		}
	@PostMapping("/ViewDiaryDetailsParent")
	public ResponseEntity<DiaryDetailsMessageResponseDto> ViewDiaryDetailsParent(@RequestBody StudentIdDto studentIdDto) {
		DiaryDetailsMessageResponseDto result = diaryService.viewDetailsOfDiaryMessage(studentIdDto);
		if(result.isSuccess()) { 
			return ResponseEntity.ok(result);
		}
		throw new CustomResponseException(CustomErrorMessage.ERROR);
		}

		}

