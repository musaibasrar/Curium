package org.ideoholic.curium.model.studentdiary.action;

import org.ideoholic.curium.model.diary.dto.DairyIdsDto;
import org.ideoholic.curium.model.diary.dto.DiaryDetailsMessageResponseDto;
import org.ideoholic.curium.model.diary.dto.DiaryResponseDto;
import org.ideoholic.curium.model.parents.dto.ParentListResponseDto;
import org.ideoholic.curium.model.student.dto.StudentIdDto;
import org.ideoholic.curium.model.student.dto.StudentIdPageDto;
import org.ideoholic.curium.model.studentdiary.dto.AddStudentDiaryDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@RequestMapping("/api/v1/studentDiaryProcess")
public interface StudentDiaryApiAction {
	
	@GetMapping("/getdiarystudent")
	public ResponseEntity<ParentListResponseDto> getdiarystudent(@RequestParam(value="page")
	String page,@RequestHeader(value = "branchid") String branchId);
	
	@PostMapping("/addDiary")
	public ResponseEntity addDiary(@RequestBody AddStudentDiaryDto addStudentDiaryDto,
			@RequestHeader(value = "branchid") String branchId,
			@RequestHeader(value = "userloginid") String userLoginId,
			@RequestHeader(value = "currentAcademicYear") String currentAcademicYear);
	
	
	@RequestMapping(value = "/viewdiarystudent", method = { RequestMethod.GET, RequestMethod.POST })
	public ResponseEntity<DiaryResponseDto> viewdiarystudent(@RequestHeader(value = "branchid") String branchId, @RequestParam(value="page")
	String page);
	
	@GetMapping(value = "/viewDiaryStudentParent")
	  public ResponseEntity<DiaryResponseDto> viewDiaryStudentParentGet(@RequestParam(value="page") String page, @RequestParam(value="studentId") String studentId,
	      @RequestHeader(value = "branchid") String branchId);
	
	@PostMapping(value = "/viewDiaryStudentParent")
	  public ResponseEntity<DiaryResponseDto> viewDiaryStudentParentPost(@RequestBody StudentIdPageDto studentIdPageDto,
	      @RequestHeader(value = "branchid") String branchId);
	
	@PostMapping("/deleteRecord")
	public ResponseEntity<DiaryResponseDto>  deleteRecord(@RequestBody DairyIdsDto dairyIdsDto,@RequestParam(value="page")
	String page,
	@RequestHeader(value = "branchid") String branchId);
	
	@PostMapping("/diarySaved")
	public ResponseEntity<String> diarySaved();
	
	@PostMapping("/viewDiaryDetails")
	public ResponseEntity<DiaryDetailsMessageResponseDto> ViewDiaryDetails(@RequestBody StudentIdDto studentIdDto) ;
	
	@PostMapping("/viewDiaryDetailsParent")
	public ResponseEntity<DiaryDetailsMessageResponseDto> ViewDiaryDetailsParent(@RequestBody StudentIdDto studentIdDto);
}
