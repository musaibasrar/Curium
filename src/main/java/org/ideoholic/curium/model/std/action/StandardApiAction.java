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
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/api/v1/classProcess")
public class StandardApiAction {

    @Autowired
    private StandardService standardService;


    @PostMapping("/restoreMultipleLeftout")
    public ResponseEntity<ResultResponse> restoreMultipleLeftout(@RequestBody StudentIdsDto dto) {
        standardService.restoreMultipleLeftout(dto);
        return viewLeftOut();
    }

    @GetMapping("/viewLeftOut")
    public ResponseEntity<ResultResponse> viewLeftOut() {
        ResultResponse result = standardService.viewleft();
        return ResponseEntity.ok(result);
    }

    @PostMapping("/searchByClass")
    public ResponseEntity<ResultResponse> searchByClass(@RequestBody StdOfClassDto dto, @RequestHeader(value = "branchid") String branchId, @RequestHeader(value = "currentacademicyear") String currentAcademicYear) {
        ResultResponse result = standardService.searchByClass(dto, branchId, currentAcademicYear);
        return ResponseEntity.ok(result);
    }

    @PostMapping("/restoreMultipleDroppedout")
    public ResponseEntity<ResultResponse> restoreMultipleDroppedout(@RequestBody StudentIdsDto dto) {
        standardService.restoreMultipleDroppedout(dto);
        return viewDropped();
    }

    @PostMapping("/restoreMultipleGraduate")
    public ResponseEntity<ResultResponse> restoreMultipleGraduate(@RequestBody StudentIdsDto dto) {
        standardService.restoreMultipleGraduate(dto);
        return viewGraduated();
    }

    @GetMapping("/viewDropped")
    public ResponseEntity<ResultResponse> viewDropped() {
        ResultResponse result = standardService.viewDropped();
        return ResponseEntity.ok(result);
    }

    @GetMapping("/viewGraduated")
    public ResponseEntity<ResultResponse> viewGraduated() {
        ResultResponse result = standardService.viewGraduated();
        return ResponseEntity.ok(result);
    }

    @PostMapping("/leftoutMultiple")
    public ResponseEntity<ResultResponse> leftoutMultiple(@RequestBody StudentIdsDto dto) {
        ResultResponse result = standardService.leftoutMultiple(dto);
        if(result.isSuccess()) {
            return ResponseEntity.ok(result);
        }
        throw new CustomResponseException(CustomErrorMessage.FAILURELEFTOUT);
    }

    @PostMapping("/dropoutMultiple")
    public ResponseEntity<ResultResponse> droppedoutMultiple(@RequestBody StudentIdsDto dto) {
        ResultResponse result = standardService.droppedoutMultiple(dto);
        if(result.isSuccess()) {
            return ResponseEntity.ok(result);
        }
        throw new CustomResponseException(CustomErrorMessage.FAILUREDROPPEDOUT);
    }

    @PostMapping("/graduateMultiple")
    public ResponseEntity<ResultResponse> graduateMultiple(@RequestBody StudentIdsDto dto) {
        ResultResponse result = standardService.graduateMultiple(dto);
        if(result.isSuccess()) {
            return ResponseEntity.ok(result);
        }
        throw new CustomResponseException(CustomErrorMessage.FAILUREGRADUATE);
    }


    @PostMapping("/deleteClassHierarchy")
    public ResponseEntity<ClassesHierarchyDto> deleteClassHierarchy(@RequestBody ClassIdsDto dto, @RequestHeader(value = "branchid") String branchId) {
        standardService.deleteClassHierarchy(dto, branchId);
        return classHierarchy(branchId);
    }

    @PostMapping("/addClassHierarchy")
    public ResponseEntity<ClassesHierarchyDto> addClassHierarchy(@RequestBody UpperLowerClassDto dto, @RequestHeader(value = "branchid") String branchId, @RequestHeader(value = "userid") String userId) {
        standardService.addClassHierarchy(dto, branchId, userId);
        return classHierarchy(branchId);
    }

    @GetMapping("/classHierarchy")
    public ResponseEntity<ClassesHierarchyDto> classHierarchy(@RequestHeader(value = "branchid") String branchId) {
        ClassesHierarchyDto classesHierarchyDto = new ClassesHierarchyDto();
        ResultResponse result = standardService.viewClasses(branchId);
        classesHierarchyDto.setClasssecList(result.getResultList());
        result = standardService.viewClassHierarchy(branchId);
        classesHierarchyDto.setClassHierarchy(result.getResultList());
        return ResponseEntity.ok(classesHierarchyDto);
    }

    @GetMapping("/promoteClass")
    public ResponseEntity<ResultResponse> promoteClass(@RequestHeader(value = "branchid") String branchId) {
        ResultResponse result = standardService.viewClasses(branchId);
        if(result.isSuccess()) {
            return ResponseEntity.ok(result);
        }
        throw new CustomResponseException(CustomErrorMessage.ERROR);
    }

    @PostMapping("/deleteClass")
    public ResponseEntity<ResultResponse> deleteClass(@RequestBody ClassIdsDto dto, @RequestHeader(value = "branchid") String branchId) {
        ResultResponse result = standardService.deleteClasses(dto, branchId);
        if(result.isSuccess()) {
            return ResponseEntity.ok(result);
        }
        throw new CustomResponseException(CustomErrorMessage.ERROR);
    }

    @GetMapping("/viewClasses")
    public ResponseEntity<ResultResponse> viewClasses(@RequestParam(value = "branchid") String branchId) {
        ResultResponse result = standardService.viewClasses(branchId);
        if(result.isSuccess()) {
            return ResponseEntity.ok(result);
        }
        throw new CustomResponseException(CustomErrorMessage.ERROR);
    }

    @PostMapping("/createClass")
    public ResponseEntity<ResultResponse> createClass(@RequestBody ClassDto classDto, @RequestHeader(value = "branchid") String branchId, @RequestHeader(value = "userid") String userId) {
        ResultResponse result = standardService.createClass(classDto, branchId, userId);
        if(result.isSuccess()) {
            return ResponseEntity.ok(result);
        }
        throw new CustomResponseException(CustomErrorMessage.ERROR);
    }

}
