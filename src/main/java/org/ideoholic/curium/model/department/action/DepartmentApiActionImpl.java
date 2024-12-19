package org.ideoholic.curium.model.department.action;

import lombok.extern.slf4j.Slf4j;
import org.ideoholic.curium.dto.ResultResponse;
import org.ideoholic.curium.model.department.dto.AddDepartmentDto;
import org.ideoholic.curium.model.department.dto.DeleteMultipleDto;
import org.ideoholic.curium.model.department.dto.DepartmentResponseDto;
import org.ideoholic.curium.model.department.service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@Slf4j
public class DepartmentApiActionImpl implements DepartmentApiAction {

    @Autowired
    private DepartmentService departmentService;


    public ResponseEntity<ResultResponse> deleteMultiple(DeleteMultipleDto dto) {
        ResultResponse result = departmentService.deleteMultiple(dto);
        return ResponseEntity.ok(result);
    }

    public ResponseEntity<DepartmentResponseDto> departmentView(String branchId) {
       DepartmentResponseDto result = departmentService.viewDepartment(branchId);
         log.debug("IN action's department view");
        return ResponseEntity.ok(result);
    }

    public ResponseEntity<ResultResponse> addDepartment(AddDepartmentDto dto,String branchId) {
        ResultResponse result =departmentService.addDepartment(dto,branchId);
        log.debug("IN action's add department");
        return ResponseEntity.ok(result);
    }

}