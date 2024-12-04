package org.ideoholic.curium.model.user.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AdvanceSearchDto {
    private String name;
    private String gender;
    private String dateOfBirth;
    private String age;
    private String addClass;
    private String addSec;
    private String admClassE;
    private String admSecE;
    private String admNo;
    private String dateOfAdmission;
    private String bloodGroup;
    private String nationality;
    private String religion;
    private String caste;
    private String motherTongue;
    private String createdDate;
    private String remarks;
    private String sts;
    private String uId;
}
