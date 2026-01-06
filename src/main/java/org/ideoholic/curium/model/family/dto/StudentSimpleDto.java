package org.ideoholic.curium.model.family.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Simple DTO for returning student info in API/service responses.
 * Add fields you need; keep it small to avoid expensive lazy-loads.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class StudentSimpleDto {
    private Integer sid;
    private String name;
    private String classStudying;
    private String admissionNumber;
}