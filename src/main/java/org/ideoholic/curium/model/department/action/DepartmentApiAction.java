package org.ideoholic.curium.model.department.action;

import org.ideoholic.curium.dto.ResultResponse;
import org.ideoholic.curium.model.department.dto.AddDepartmentDto;
import org.ideoholic.curium.model.department.dto.DeleteMultipleDto;
import org.ideoholic.curium.model.department.dto.DepartmentResponseDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/api/v1/departmentProcess")
public interface DepartmentApiAction {
    @PostMapping("/deleteMultiple")
    public ResponseEntity<ResultResponse> deleteMultiple(@RequestBody DeleteMultipleDto dto);
    @GetMapping("/departmentView")
    public ResponseEntity<DepartmentResponseDto> departmentView(@RequestHeader(value = "branchid") String branchId);
    @PostMapping("/addDepartment")
    public ResponseEntity<ResultResponse> addDepartment(@RequestBody AddDepartmentDto dto, @RequestHeader(value = "branchid") String branchId);
}
