/**
 *
 */
package org.ideoholic.curium.model.std.action;

import org.ideoholic.curium.exceptions.CustomErrorMessage;
import org.ideoholic.curium.exceptions.CustomResponseException;
import org.ideoholic.curium.model.std.dto.ClassDto;
import org.ideoholic.curium.model.std.dto.ClassIdsDto;
import org.ideoholic.curium.model.std.dto.StdOfClassDto;
import org.ideoholic.curium.model.std.dto.UpperLowerClassDto;
import org.ideoholic.curium.model.std.service.StandardService;
import org.ideoholic.curium.model.student.dto.StudentIdsDto;
import org.ideoholic.curium.util.ResultResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/v1/ClassProcess")
public class StandardApiAction {

    @Autowired
    private StandardService standardService;


    @PostMapping("/restoreMultipleLeftout")
    public String restoreMultipleLeftout(@RequestBody StudentIdsDto dto) {
        standardService.restoreMultipleLeftout(dto);
        return viewLeftOut();
    }

    @GetMapping("/viewLeftOut")
    public String viewLeftOut() {
        standardService.viewleft();
        return "leftout";
    }

    @PostMapping("/searchByClass")
    public String searchByClass(@RequestBody StdOfClassDto dto, @RequestHeader(value = "branchid") String branchId, @RequestHeader(value = "currentacademicyear") String currentAcademicYear) {
        standardService.searchByClass(dto, branchId, currentAcademicYear);
        return "Promotion";
    }

    @PostMapping("/restoreMultipleDroppedout")
    public String restoreMultipleDroppedout(@RequestBody StudentIdsDto dto) {
        standardService.restoreMultipleDroppedout(dto);
        return viewDropped();
    }

    @PostMapping("/restoreMultipleGraduate")
    public String restoreMultipleGraduate(@RequestBody StudentIdsDto dto) {
        standardService.restoreMultipleGraduate(dto);
        return viewGraduated();
    }

    @GetMapping("/viewDropped")
    public String viewDropped() {
        standardService.viewDropped();
        return "droppedout";
    }

    @GetMapping("/viewGraduated")
    public String viewGraduated() {
        standardService.viewGraduated();
        return "graduated";
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
    public String deleteClassHierarchy(@RequestBody ClassIdsDto dto, @RequestParam(value = "branchid") String branchId) {
        standardService.deleteClassHierarchy(dto);
        return classHierarchy(branchId);
    }

    @PostMapping("/addClassHierarchy")
    public String addClassHierarchy(@RequestBody UpperLowerClassDto dto, @RequestHeader(value = "branchid") String branchId, @RequestHeader(value = "userid") String userId) {
        standardService.addClassHierarchy(dto, branchId, userId);
        return classHierarchy(branchId);
    }

    @GetMapping("/classHierarchy")
    public String classHierarchy(@RequestParam(value = "branchid") String branchId) {
        standardService.viewClasses();
        standardService.viewClassHierarchy(branchId);
        return "classhierarchy";
    }

    @GetMapping("/promoteClass")
    public String promoteClass() {
        standardService.viewClasses();
        return "Promotion";
    }

    @PostMapping("/deleteClass")
    public ResponseEntity<ResultResponse> deleteClass(@RequestBody ClassIdsDto dto) {
        ResultResponse result = standardService.deleteClasses(dto);
        if(result.isSuccess()) {
            return ResponseEntity.ok(result);
        }
        throw new CustomResponseException(CustomErrorMessage.ERROR);
    }

    @GetMapping("/viewClasses")
    public String viewClasses() {
        if(standardService.viewClasses()) {
            return "addclass";
        }
        return "error";
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
