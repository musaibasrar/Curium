package org.ideoholic.curium.model.diary.action;

import org.ideoholic.curium.dto.ResultResponse;
import org.ideoholic.curium.exceptions.CustomErrorMessage;
import org.ideoholic.curium.exceptions.CustomResponseException;
import org.ideoholic.curium.model.diary.dto.AddDiaryDto;
import org.ideoholic.curium.model.diary.dto.DairyIdsDto;
import org.ideoholic.curium.model.diary.dto.DiaryResponseDto;
import org.ideoholic.curium.model.diary.dto.ViewDetailsOfDiaryMessageResponseDto;
import org.ideoholic.curium.model.diary.service.DiaryService;
import org.ideoholic.curium.model.std.service.StandardService;
import org.ideoholic.curium.model.student.dto.StudentIdDto;
import org.ideoholic.curium.model.student.dto.StudentIdPageDto;
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

@Controller
@RequestMapping("/api/v1/DiaryProcess")
public class DiaryApiActionImpl implements DiaryApiAction{
	@Autowired
	private DiaryService diaryService;
	@Autowired
	private StandardService standardService;

	@GetMapping("/getdiarystudent")
	public ResponseEntity<ResultResponse> getdiarystudent(@RequestHeader(value = "branchid") String branchId) {
		ResultResponse result = standardService.viewClasses(branchId);
		return ResponseEntity.ok(result);

	}

	@PostMapping("/addDiary")
	public ResponseEntity addDiary(@RequestBody AddDiaryDto addDiaryDto,
			@RequestHeader(value = "branchid") String branchId,
			@RequestHeader(value = "currentAcademicYear") String currentAcademicYear,
			@RequestHeader(value = "userloginid") String userLoginId) {
		diaryService.addDiary(addDiaryDto, branchId, userLoginId, currentAcademicYear);
		return ResponseEntity.ok().build();

	}

	@RequestMapping(value = "/viewdiarystudent", method = { RequestMethod.GET, RequestMethod.POST })
	public ResponseEntity<DiaryResponseDto> viewdiarystudent(@RequestHeader(value = "branchid") String branchId, @RequestParam(value="page")
			String page) {
		DiaryResponseDto result = diaryService.viewDiary(branchId, page);
		return ResponseEntity.ok(result);
	}

	@RequestMapping(value = "/viewDiaryStudentParent", method = { RequestMethod.GET, RequestMethod.POST })
	public ResponseEntity<DiaryResponseDto> viewDiaryStudentParent(@RequestBody StudentIdPageDto studentIdPageDto,
			@RequestHeader(value = "branchid") String branchId) {
		DiaryResponseDto result = diaryService.viewDiaryParent(studentIdPageDto, branchId);
		return ResponseEntity.ok(result);
	}

	@PostMapping("/deleteRecord")
	public ResponseEntity<DiaryResponseDto> deleteRecord(@RequestBody DairyIdsDto dairyIdsDto,@RequestParam(value="page")
	String page,
			@RequestHeader(value = "branchid") String branchId) {
		diaryService.deleteRecord(dairyIdsDto);
		DiaryResponseDto result = diaryService.viewDiary( page,branchId);
		return ResponseEntity.ok(result);
	}

	@PostMapping("/diarySaved")
	public ResponseEntity<String> diarySaved() {
		
		return ResponseEntity.ok("viewDiary");
	}

	@PostMapping("/viewDiaryDetails")
	public ResponseEntity<ViewDetailsOfDiaryMessageResponseDto> viewDiaryDetails(
			@RequestBody StudentIdDto studentIdDto) {
		ViewDetailsOfDiaryMessageResponseDto result = diaryService.viewDetailsOfDiaryMessage(studentIdDto);
		if (result.isSuccess()) {
			return ResponseEntity.ok(result);
		}else {
			throw new CustomResponseException(CustomErrorMessage.ERROR);
		}
		
	}

	@PostMapping("/viewDiaryDetailsParent")
	public ResponseEntity<ViewDetailsOfDiaryMessageResponseDto> viewDiaryDetailsParent(
			@RequestBody StudentIdDto studentIdDto) {
		ViewDetailsOfDiaryMessageResponseDto result = diaryService.viewDetailsOfDiaryMessage(studentIdDto);
		if (result.isSuccess()) {
			return ResponseEntity.ok(result);
		}else {
			throw new CustomResponseException(CustomErrorMessage.ERROR);
		}
		
	}
}
