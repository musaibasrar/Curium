/**
 *
 */
package org.ideoholic.curium.model.std.action;

import org.ideoholic.curium.dto.ResultResponse;
import org.ideoholic.curium.model.std.dto.ClassDto;
import org.ideoholic.curium.model.std.dto.ClassIdsDto;
import org.ideoholic.curium.model.std.dto.ClassesHierarchyDto;
import org.ideoholic.curium.model.std.dto.StdOfClassDto;
import org.ideoholic.curium.model.std.dto.UpperLowerClassDto;
import org.ideoholic.curium.model.student.dto.StudentIdsDto;
import org.ideoholic.curium.util.Constants;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;



@RequestMapping("/api/v1/classProcess")
public interface StandardApiAction {

    @PostMapping("/restoreMultipleLeftout")
    public ResponseEntity<ResultResponse> restoreMultipleLeftout(@RequestBody StudentIdsDto dto);

    @GetMapping("/viewLeftOut")
    public ResponseEntity<ResultResponse> viewLeftOut();

    @PostMapping("/searchByClass")
    public ResponseEntity<ResultResponse> searchByClass(@RequestBody StdOfClassDto dto, @RequestHeader(value = Constants.BRANCHID) String branchId, @RequestHeader(value = Constants.CURRENTACADEMICYEAR) String currentAcademicYear);
   
    @PostMapping("/restoreMultipleDroppedout")
    public ResponseEntity<ResultResponse> restoreMultipleDroppedout(@RequestBody StudentIdsDto dto);

    @PostMapping("/restoreMultipleGraduate")
    public ResponseEntity<ResultResponse> restoreMultipleGraduate(@RequestBody StudentIdsDto dto);

    @GetMapping("/viewDropped")
    public ResponseEntity<ResultResponse> viewDropped();

    @GetMapping("/viewGraduated")
    public ResponseEntity<ResultResponse> viewGraduated();

    @PostMapping("/leftoutMultiple")
    public ResponseEntity<ResultResponse> leftoutMultiple(@RequestBody StudentIdsDto dto);

    @PostMapping("/dropoutMultiple")
    public ResponseEntity<ResultResponse> droppedoutMultiple(@RequestBody StudentIdsDto dto);

    @PostMapping("/graduateMultiple")
    public ResponseEntity<ResultResponse> graduateMultiple(@RequestBody StudentIdsDto dto);


    @PostMapping("/deleteClassHierarchy")
    public ResponseEntity<ClassesHierarchyDto> deleteClassHierarchy(@RequestBody ClassIdsDto dto, @RequestHeader(value = Constants.BRANCHID) String branchId);
       

    @PostMapping("/addClassHierarchy")
    public ResponseEntity<ClassesHierarchyDto> addClassHierarchy(@RequestBody UpperLowerClassDto dto, @RequestHeader(value = Constants.BRANCHID) String branchId, @RequestHeader(value = Constants.USERID) String userId);
        
    @GetMapping("/classHierarchy")
    public ResponseEntity<ClassesHierarchyDto> classHierarchy(@RequestHeader(value = Constants.BRANCHID) String branchId);

    @GetMapping("/promoteClass")
    public ResponseEntity<ResultResponse> promoteClass(@RequestHeader(value = Constants.BRANCHID) String branchId);

    @PostMapping("/deleteClass")
    public ResponseEntity<ResultResponse> deleteClass(@RequestBody ClassIdsDto dto, @RequestHeader(value = Constants.BRANCHID) String branchId);
       

    @GetMapping("/viewClasses")
    public ResponseEntity<ResultResponse> viewClasses(@RequestParam(value = Constants.BRANCHID) String branchId);
        
    @PostMapping("/createClass")
    public ResponseEntity<ResultResponse> createClass(@RequestBody ClassDto classDto, @RequestHeader(value = Constants.BRANCHID) String branchId, @RequestHeader(value = Constants.USERID) String userId);
       

}