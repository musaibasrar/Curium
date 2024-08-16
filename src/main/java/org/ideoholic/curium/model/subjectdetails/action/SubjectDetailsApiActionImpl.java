package org.ideoholic.curium.model.subjectdetails.action;

import org.ideoholic.curium.dto.ResultResponse;
import org.ideoholic.curium.exceptions.CustomErrorMessage;
import org.ideoholic.curium.exceptions.CustomResponseException;
import org.ideoholic.curium.model.examdetails.action.ExamDetailsActionAdapter;
import org.ideoholic.curium.model.std.service.StandardService;
import org.ideoholic.curium.model.subjectdetails.dto.SubjectsExamsResponseDto;
import org.ideoholic.curium.model.subjectdetails.dto.SubjectDto;
import org.ideoholic.curium.model.subjectdetails.dto.SubjectIdsDto;
import org.ideoholic.curium.model.subjectdetails.dto.SubjectsResponseDto;
import org.ideoholic.curium.model.subjectdetails.service.SubjectDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/subjectDetailsProcess")
public class SubjectDetailsApiActionImpl implements SubjectDetailsApiAction {

    @Autowired
    private StandardService standardService;

    @Autowired
    private ExamDetailsActionAdapter examDetailsActionAdapter;

    @Autowired
    private SubjectDetailsService subjectDetailsService;


    @PostMapping("/deleteMultipleSubjects")
    public ResponseEntity<SubjectsResponseDto> deleteMultipleSubjects(@RequestBody SubjectIdsDto dto, @RequestHeader(value = "branchid") String branchId) {

        ResultResponse result = subjectDetailsService.deleteMultipleSubjects(dto);

        if (result.isSuccess()) {
            return readListOfSubjectNames(branchId);
        } else {
            throw new CustomResponseException(CustomErrorMessage.ERROR);
        }
    }

    @GetMapping("/readListOfSubjectNames")
    public ResponseEntity<SubjectsResponseDto> readListOfSubjectNames(@RequestHeader(value = "branchid") String branchId) {
        SubjectsResponseDto result = subjectDetailsService.readListOfSubjectNames(branchId);
        return ResponseEntity.ok(result);
    }

    @PostMapping("/addSubjectMaster")
    public ResponseEntity<SubjectsResponseDto> addSubjectMaster(@RequestBody SubjectDto dto, @RequestHeader(value = "branchid") String branchId, @RequestHeader(value = "userloginid") String userLoginId) {
        ResultResponse result = subjectDetailsService.addSubjectMaster(dto, branchId, userLoginId);
        if (result.isSuccess()) {
            return readListOfSubjectNames(branchId);
        } else {
            throw new CustomResponseException(CustomErrorMessage.ERROR);
        }
    }

    @PostMapping("/deleteMultiple")
    public ResponseEntity<SubjectsExamsResponseDto> deleteMultiple(@RequestBody SubjectIdsDto dto, @RequestHeader(value = "branchid") String branchId) {
        ResultResponse result = subjectDetailsService.deleteMultiple(dto);
        if (result.isSuccess()) {
            return readListOfSubjectsExams(branchId);
        } else {
            throw new CustomResponseException(CustomErrorMessage.ERROR);
        }
    }

    @PostMapping("/addSubject")
    public ResponseEntity<SubjectsExamsResponseDto> addSubject(@RequestBody SubjectDto dto, @RequestHeader(value = "branchid") String branchId, @RequestHeader(value = "userloginid") String userLoginId) {
        ResultResponse result = subjectDetailsService.addSubject(dto, branchId, userLoginId);
        if (result.isSuccess()) {
            return readListOfSubjectsExams(branchId);
        } else {
            throw new CustomResponseException(CustomErrorMessage.ERROR);
        }
    }

    @GetMapping("/readListOfSubjects")
    public ResponseEntity<SubjectsExamsResponseDto> readListOfSubjectsExams(@RequestHeader(value = "branchid") String branchId) {
        SubjectsExamsResponseDto result = new SubjectsExamsResponseDto();
        SubjectsResponseDto responseDto = subjectDetailsService.readListOfSubjects(branchId);
        result.setSubjects(responseDto.getList());
        result.setSuccess(responseDto.isSuccess());

        responseDto = subjectDetailsService.readListOfSubjectNames(branchId);
        result.setSubjectNames(responseDto.getList());
        result.setSuccess(result.isSuccess() & responseDto.isSuccess());

        // TODO fix this after changing this to service call
        examDetailsActionAdapter.readListOfExams();

        ResultResponse response = standardService.viewClasses(branchId);
        result.setClasssecList(response.getResultList());
        result.setSuccess(result.isSuccess() & response.isSuccess());

        return ResponseEntity.ok(result);
    }
}
