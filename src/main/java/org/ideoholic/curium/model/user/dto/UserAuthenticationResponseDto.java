package org.ideoholic.curium.model.user.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@JsonInclude(JsonInclude.Include.NON_ABSENT)
public class UserAuthenticationResponseDto {
    private String academicYear;
    private String userName;
    private Integer branchId;
    private String branchName;
    private String branchCode;
    private String branchAddress;
    private String branchContact;
    private String userType;
    private String typeOfUser;
    private String userAuth;
    private String superUserAuth;
    private Integer userLoginId;
    private String attendanceStatus;
    @Builder.Default
    private boolean success = false;
}
