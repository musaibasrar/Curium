package org.ideoholic.curium.model.std.dto;

import lombok.Data;

import java.util.List;

@Data
public class ClassesHierarchyDto {
    private List<Classsec> classsecList;
    private List<Classhierarchy> classHierarchy;
    private String selectedBranchId;
}
