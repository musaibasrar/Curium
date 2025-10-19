package org.ideoholic.curium.model.std.dto;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class ClassesHierarchyDto {
    private List<Classsec> classsecList;
    private List<Classhierarchy> classHierarchy;
    private String selectedBranchId;
}