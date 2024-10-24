package org.ideoholic.curium.model.subjectdetails.action;

import org.ideoholic.curium.model.subjectdetails.dto.SubjectsExamsResponseDto;
import org.ideoholic.curium.model.subjectdetails.dto.SubjectDto;
import org.ideoholic.curium.model.subjectdetails.dto.SubjectIdsDto;
import org.ideoholic.curium.model.subjectdetails.dto.SubjectsResponseDto;
import org.springframework.http.ResponseEntity;

public interface SubjectDetailsApiAction {
     ResponseEntity<SubjectsResponseDto> deleteMultipleSubjects( SubjectIdsDto dto, String branchId);

     ResponseEntity<SubjectsResponseDto> readListOfSubjectNames(String branchId);

     ResponseEntity<SubjectsResponseDto> addSubjectMaster(SubjectDto dto,String branchId,String userLoginId);

     ResponseEntity<SubjectsExamsResponseDto> deleteMultiple(SubjectIdsDto dto, String branchId);

     ResponseEntity<SubjectsExamsResponseDto> addSubject(SubjectDto dto, String branchId, String userLoginId);

     ResponseEntity<SubjectsExamsResponseDto> readListOfSubjectsExams(String branchId);





    }
