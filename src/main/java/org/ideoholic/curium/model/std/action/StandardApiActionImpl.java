/**
 *
 */
package org.ideoholic.curium.model.std.action;

import org.ideoholic.curium.dto.ResultResponse;
import org.ideoholic.curium.exceptions.CustomErrorMessage;
import org.ideoholic.curium.exceptions.CustomResponseException;
import org.ideoholic.curium.model.std.dto.ClassDto;
import org.ideoholic.curium.model.std.dto.ClassIdsDto;
import org.ideoholic.curium.model.std.dto.ClassesHierarchyDto;
import org.ideoholic.curium.model.std.dto.StdOfClassDto;
import org.ideoholic.curium.model.std.dto.UpperLowerClassDto;
import org.ideoholic.curium.model.std.service.StandardService;
import org.ideoholic.curium.model.student.dto.StudentIdsDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class StandardApiActionImpl implements StandardApiAction {

    @Autowired
    private StandardService standardService;


    public ResponseEntity<ResultResponse> restoreMultipleLeftout( StudentIdsDto dto) {
        standardService.restoreMultipleLeftout(dto);
        return viewLeftOut();
    }

    public ResponseEntity<ResultResponse> viewLeftOut() {
        ResultResponse result = standardService.viewleft();
        return ResponseEntity.ok(result);
    }

    public ResponseEntity<ResultResponse> searchByClass(StdOfClassDto dto, String branchId,  String currentAcademicYear) {
        ResultResponse result = standardService.searchByClass(dto, branchId, currentAcademicYear);
        return ResponseEntity.ok(result);
    }

    public ResponseEntity<ResultResponse> restoreMultipleDroppedout( StudentIdsDto dto) {
        standardService.restoreMultipleDroppedout(dto);
        return viewDropped();
    }

    public ResponseEntity<ResultResponse> restoreMultipleGraduate( StudentIdsDto dto) {
        standardService.restoreMultipleGraduate(dto);
        return viewGraduated();
    }

    public ResponseEntity<ResultResponse> viewDropped() {
        ResultResponse result = standardService.viewDropped();
        return ResponseEntity.ok(result);
    }

    public ResponseEntity<ResultResponse> viewGraduated() {
        ResultResponse result = standardService.viewGraduated();
        return ResponseEntity.ok(result);
    }

    public ResponseEntity<ResultResponse> leftoutMultiple( StudentIdsDto dto) {
        ResultResponse result = standardService.leftoutMultiple(dto);
        if(result.isSuccess()) {
            return ResponseEntity.ok(result);
        }
        throw new CustomResponseException(CustomErrorMessage.FAILURELEFTOUT);
    }

    public ResponseEntity<ResultResponse> droppedoutMultiple( StudentIdsDto dto) {
        ResultResponse result = standardService.droppedoutMultiple(dto);
        if(result.isSuccess()) {
            return ResponseEntity.ok(result);
        }
        throw new CustomResponseException(CustomErrorMessage.FAILUREDROPPEDOUT);
    }

    public ResponseEntity<ResultResponse> graduateMultiple(StudentIdsDto dto) {
        ResultResponse result = standardService.graduateMultiple(dto);
        if(result.isSuccess()) {
            return ResponseEntity.ok(result);
        }
        throw new CustomResponseException(CustomErrorMessage.FAILUREGRADUATE);
    }


    public ResponseEntity<ClassesHierarchyDto> deleteClassHierarchy(ClassIdsDto dto,  String branchId) {
        standardService.deleteClassHierarchy(dto, branchId);
        return classHierarchy(branchId);
    }

    public ResponseEntity<ClassesHierarchyDto> addClassHierarchy(UpperLowerClassDto dto, String branchId, String userId) {
        standardService.addClassHierarchy(dto, branchId, userId);
        return classHierarchy(branchId);
    }

    public ResponseEntity<ClassesHierarchyDto> classHierarchy( String branchId) {
        return ResponseEntity.ok(ClassesHierarchyDto.builder()
            .classsecList(standardService.viewClasses(branchId).getResultList())
            .classHierarchy(standardService.viewClassHierarchy(branchId).getResultList())
            .build());
    }

    public ResponseEntity<ResultResponse> promoteClass( String branchId) {
        ResultResponse result = standardService.viewClasses(branchId);
        if(result.isSuccess()) {
            return ResponseEntity.ok(result);
        }
        throw new CustomResponseException(CustomErrorMessage.ERROR);
    }

    public ResponseEntity<ResultResponse> deleteClass( ClassIdsDto dto, String branchId) {
        ResultResponse result = standardService.deleteClasses(dto, branchId);
        if(result.isSuccess()) {
            return ResponseEntity.ok(result);
        }
        throw new CustomResponseException(CustomErrorMessage.ERROR);
    }

    public ResponseEntity<ResultResponse> viewClasses( String branchId) {
        ResultResponse result = standardService.viewClasses(branchId);
        if(result.isSuccess()) {
            return ResponseEntity.ok(result);
        }
        throw new CustomResponseException(CustomErrorMessage.ERROR);
    }

    public ResponseEntity<ResultResponse> createClass( ClassDto classDto,  String branchId,  String userId) {
        ResultResponse result = standardService.createClass(classDto, branchId, userId);
        if(result.isSuccess()) {
            return ResponseEntity.ok(result);
        }
        throw new CustomResponseException(CustomErrorMessage.ERROR);
    }

}