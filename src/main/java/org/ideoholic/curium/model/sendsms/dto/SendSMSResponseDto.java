package org.ideoholic.curium.model.sendsms.dto;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Data;
import org.ideoholic.curium.model.department.dto.Department;

import java.util.List;


@Data
@JsonInclude(JsonInclude.Include.NON_ABSENT)
public class SendSMSResponseDto {

    private boolean success = false;

    private List<Department> departmentList;

}
