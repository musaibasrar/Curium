package org.ideoholic.curium.model.department.dto;

import lombok.Data;

@Data
public class DeleteMultipleDto {
    private String[] departmentIds;
}
