package org.ideoholic.curium.model.diary.action;

import org.ideoholic.curium.dto.ResultResponse;
import org.ideoholic.curium.model.diary.dto.AddDiaryDto;
import org.ideoholic.curium.model.diary.dto.DairyIdsDto;
import org.ideoholic.curium.model.diary.dto.DiaryResponseDto;
import org.ideoholic.curium.model.diary.dto.ViewDetailsOfDiaryMessageResponseDto;
import org.ideoholic.curium.model.student.dto.StudentIdDto;
import org.ideoholic.curium.model.student.dto.StudentIdPageDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;

public interface DiaryApiAction {

	 ResponseEntity<ResultResponse> getdiarystudent(@RequestHeader(value = "branchid") String branchId) ;

	 ResponseEntity addDiary(@RequestBody AddDiaryDto addDiaryDto,
			@RequestHeader(value = "branchid") String branchId,
			@RequestHeader(value = "currentAcademicYear") String currentAcademicYear,
			@RequestHeader(value = "userloginid") String userLoginId);

	 ResponseEntity<DiaryResponseDto> viewdiarystudent(@RequestHeader(value = "branchid") String branchId, @RequestParam(value="page")
			String page) ;

	 ResponseEntity<DiaryResponseDto> viewDiaryStudentParent(@RequestBody StudentIdPageDto studentIdPageDto,
			@RequestHeader(value = "branchid") String branchId);

	 ResponseEntity<DiaryResponseDto> deleteRecord(@RequestBody DairyIdsDto dairyIdsDto,
			@RequestHeader(value = "branchid") String branchId,@RequestParam(value="page")
		String page) ;

     ResponseEntity<String> diarySaved(); 
		

	 ResponseEntity<ViewDetailsOfDiaryMessageResponseDto> viewDiaryDetails(
			@RequestBody StudentIdDto studentIdDto) ;

	 ResponseEntity<ViewDetailsOfDiaryMessageResponseDto> viewDiaryDetailsParent(
			@RequestBody StudentIdDto studentIdDto) ;
}
